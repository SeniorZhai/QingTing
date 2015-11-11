package com.google.android.apps.analytics;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GoogleAnalyticsTracker
{
  public static final String LOG_TAG = "GoogleAnalyticsTracker";
  public static final String PRODUCT = "GoogleAnalytics";
  public static final String VERSION = "1.4.2";
  public static final String WIRE_VERSION = "4.8.1ma";
  private static GoogleAnalyticsTracker instance = new GoogleAnalyticsTracker();
  private String accountId;
  private AdHitIdGenerator adHitIdGenerator;
  private boolean anonymizeIp = false;
  private ConnectivityManager connectivityManager;
  private CustomVariableBuffer customVariableBuffer;
  private boolean debug = false;
  private int dispatchPeriod;
  private Runnable dispatchRunner = new Runnable()
  {
    public void run()
    {
      GoogleAnalyticsTracker.this.dispatch();
    }
  };
  private Dispatcher dispatcher;
  private boolean dispatcherIsBusy;
  private boolean dryRun = false;
  private Handler handler;
  private HitStore hitStore;
  private Map<String, Map<String, Item>> itemMap = new HashMap();
  private Context parent;
  private boolean powerSaveMode;
  private int sampleRate = 100;
  private Map<String, Transaction> transactionMap = new HashMap();
  private boolean useServerTime = false;
  private String userAgentProduct = "GoogleAnalytics";
  private String userAgentVersion = "1.4.2";

  private void cancelPendingDispatches()
  {
    if (this.handler != null)
      this.handler.removeCallbacks(this.dispatchRunner);
  }

  private void createEvent(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    paramString1 = new Event(paramString1, paramString2, paramString3, paramString4, paramInt, this.parent.getResources().getDisplayMetrics().widthPixels, this.parent.getResources().getDisplayMetrics().heightPixels);
    paramString1.setCustomVariableBuffer(this.customVariableBuffer);
    paramString1.setAdHitId(this.adHitIdGenerator.getAdHitId());
    paramString1.setUseServerTime(this.useServerTime);
    this.customVariableBuffer = new CustomVariableBuffer();
    this.hitStore.putEvent(paramString1);
    resetPowerSaveMode();
  }

  public static GoogleAnalyticsTracker getInstance()
  {
    return instance;
  }

  private void maybeScheduleNextDispatch()
  {
    if (this.dispatchPeriod < 0);
    while ((!this.handler.postDelayed(this.dispatchRunner, this.dispatchPeriod * 1000)) || (!this.debug))
      return;
    Log.v("GoogleAnalyticsTracker", "Scheduled next dispatch");
  }

  private void resetPowerSaveMode()
  {
    if (this.powerSaveMode)
    {
      this.powerSaveMode = false;
      maybeScheduleNextDispatch();
    }
  }

  public void addItem(Item paramItem)
  {
    if ((Transaction)this.transactionMap.get(paramItem.getOrderId()) == null)
    {
      Log.i("GoogleAnalyticsTracker", "No transaction with orderId " + paramItem.getOrderId() + " found, creating one");
      localObject = new Transaction.Builder(paramItem.getOrderId(), 0.0D).build();
      this.transactionMap.put(paramItem.getOrderId(), localObject);
    }
    Map localMap = (Map)this.itemMap.get(paramItem.getOrderId());
    Object localObject = localMap;
    if (localMap == null)
    {
      localObject = new HashMap();
      this.itemMap.put(paramItem.getOrderId(), localObject);
    }
    ((Map)localObject).put(paramItem.getItemSKU(), paramItem);
  }

  public void addTransaction(Transaction paramTransaction)
  {
    this.transactionMap.put(paramTransaction.getOrderId(), paramTransaction);
  }

  public void clearTransactions()
  {
    this.transactionMap.clear();
    this.itemMap.clear();
  }

  public boolean dispatch()
  {
    if (this.debug)
      Log.v("GoogleAnalyticsTracker", "Called dispatch");
    if (this.dispatcherIsBusy)
    {
      if (this.debug)
        Log.v("GoogleAnalyticsTracker", "...but dispatcher was busy");
      maybeScheduleNextDispatch();
    }
    do
    {
      return false;
      Object localObject = this.connectivityManager.getActiveNetworkInfo();
      if ((localObject == null) || (!((NetworkInfo)localObject).isAvailable()))
      {
        if (this.debug)
          Log.v("GoogleAnalyticsTracker", "...but there was no network available");
        maybeScheduleNextDispatch();
        return false;
      }
      if (this.hitStore.getNumStoredHits() != 0)
      {
        localObject = this.hitStore.peekHits();
        this.dispatcher.dispatchHits((Hit[])localObject);
        this.dispatcherIsBusy = true;
        maybeScheduleNextDispatch();
        if (this.debug)
          Log.v("GoogleAnalyticsTracker", "Sending " + localObject.length + " hits to dispatcher");
        return true;
      }
      this.powerSaveMode = true;
    }
    while (!this.debug);
    Log.v("GoogleAnalyticsTracker", "...but there was nothing to dispatch");
    return false;
  }

  void dispatchFinished()
  {
    this.dispatcherIsBusy = false;
  }

  public boolean getAnonymizeIp()
  {
    return this.anonymizeIp;
  }

  public boolean getDebug()
  {
    return this.debug;
  }

  Dispatcher getDispatcher()
  {
    return this.dispatcher;
  }

  HitStore getHitStore()
  {
    return this.hitStore;
  }

  public int getSampleRate()
  {
    return this.sampleRate;
  }

  String getSessionIdForAds()
  {
    if (this.hitStore == null)
      return null;
    return this.hitStore.getSessionId();
  }

  public String getVisitorCustomVar(int paramInt)
  {
    if ((paramInt < 1) || (paramInt > 5))
      throw new IllegalArgumentException("Index must be between 1 and 5 inclusive.");
    return this.hitStore.getVisitorCustomVar(paramInt);
  }

  String getVisitorIdForAds()
  {
    if (this.hitStore == null)
      return null;
    return this.hitStore.getVisitorId();
  }

  public boolean isDryRun()
  {
    return this.dryRun;
  }

  void returnToInitialState()
  {
    instance = new GoogleAnalyticsTracker();
  }

  void setAdHitIdGenerator(AdHitIdGenerator paramAdHitIdGenerator)
  {
    this.adHitIdGenerator = paramAdHitIdGenerator;
  }

  public void setAnonymizeIp(boolean paramBoolean)
  {
    this.anonymizeIp = paramBoolean;
    if (this.hitStore != null)
      this.hitStore.setAnonymizeIp(this.anonymizeIp);
  }

  public boolean setCustomVar(int paramInt, String paramString1, String paramString2)
  {
    return setCustomVar(paramInt, paramString1, paramString2, 3);
  }

  public boolean setCustomVar(int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    try
    {
      paramString1 = new CustomVariable(paramInt1, paramString1, paramString2, paramInt2);
      if (this.customVariableBuffer == null)
        this.customVariableBuffer = new CustomVariableBuffer();
      this.customVariableBuffer.setCustomVariable(paramString1);
      return true;
    }
    catch (IllegalArgumentException paramString1)
    {
    }
    return false;
  }

  public void setDebug(boolean paramBoolean)
  {
    this.debug = paramBoolean;
  }

  public void setDispatchPeriod(int paramInt)
  {
    int i = this.dispatchPeriod;
    this.dispatchPeriod = paramInt;
    if (i <= 0)
      maybeScheduleNextDispatch();
    while (i <= 0)
      return;
    cancelPendingDispatches();
    maybeScheduleNextDispatch();
  }

  public boolean setDispatcher(Dispatcher paramDispatcher)
  {
    if (this.dispatcherIsBusy)
      return false;
    if (this.dispatcher != null)
      this.dispatcher.stop();
    this.dispatcher = paramDispatcher;
    this.dispatcher.init(new DispatcherCallbacks());
    this.dispatcher.setDryRun(this.dryRun);
    return true;
  }

  public void setDryRun(boolean paramBoolean)
  {
    this.dryRun = paramBoolean;
    if (this.dispatcher != null)
      this.dispatcher.setDryRun(paramBoolean);
  }

  public void setProductVersion(String paramString1, String paramString2)
  {
    this.userAgentProduct = paramString1;
    this.userAgentVersion = paramString2;
  }

  public boolean setReferrer(String paramString)
  {
    if (this.hitStore == null)
      throw new IllegalStateException("Can't set a referrer before starting the tracker");
    return this.hitStore.setReferrer(paramString);
  }

  public void setSampleRate(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 100))
      Log.w("GoogleAnalyticsTracker", "Invalid sample rate: " + paramInt + " (should be between 0 and 100");
    do
    {
      return;
      this.sampleRate = paramInt;
    }
    while (this.hitStore == null);
    this.hitStore.setSampleRate(this.sampleRate);
  }

  public void setUseServerTime(boolean paramBoolean)
  {
    this.useServerTime = paramBoolean;
  }

  @Deprecated
  public void start(String paramString, int paramInt, Context paramContext)
  {
    startNewSession(paramString, paramInt, paramContext);
  }

  void start(String paramString, int paramInt, Context paramContext, HitStore paramHitStore, Dispatcher paramDispatcher, boolean paramBoolean)
  {
    start(paramString, paramInt, paramContext, paramHitStore, paramDispatcher, paramBoolean, new DispatcherCallbacks());
  }

  void start(String paramString, int paramInt, Context paramContext, HitStore paramHitStore, Dispatcher paramDispatcher, boolean paramBoolean, Dispatcher.Callbacks paramCallbacks)
  {
    this.accountId = paramString;
    if (paramContext == null)
      throw new NullPointerException("Context cannot be null");
    this.parent = paramContext.getApplicationContext();
    this.hitStore = paramHitStore;
    this.adHitIdGenerator = new AdHitIdGenerator();
    if (paramBoolean)
      this.hitStore.startNewVisit();
    this.dispatcher = paramDispatcher;
    this.dispatcher.init(paramCallbacks);
    this.dispatcherIsBusy = false;
    if (this.connectivityManager == null)
      this.connectivityManager = ((ConnectivityManager)this.parent.getSystemService("connectivity"));
    if (this.handler == null)
      this.handler = new Handler(paramContext.getMainLooper());
    while (true)
    {
      setDispatchPeriod(paramInt);
      return;
      cancelPendingDispatches();
    }
  }

  void start(String paramString, int paramInt, Context paramContext, boolean paramBoolean)
  {
    if (paramContext == null)
      throw new NullPointerException("Context cannot be null");
    Object localObject1;
    Object localObject2;
    if (this.hitStore == null)
    {
      localObject1 = new PersistentHitStore(paramContext);
      ((HitStore)localObject1).setAnonymizeIp(this.anonymizeIp);
      ((HitStore)localObject1).setSampleRate(this.sampleRate);
      if (this.dispatcher != null)
        break label112;
      localObject2 = new NetworkDispatcher(this.userAgentProduct, this.userAgentVersion);
      ((Dispatcher)localObject2).setDryRun(this.dryRun);
    }
    while (true)
    {
      start(paramString, paramInt, paramContext, (HitStore)localObject1, (Dispatcher)localObject2, paramBoolean);
      return;
      localObject1 = this.hitStore;
      break;
      label112: localObject2 = this.dispatcher;
    }
  }

  @Deprecated
  public void start(String paramString, Context paramContext)
  {
    start(paramString, -1, paramContext);
  }

  public void startNewSession(String paramString, int paramInt, Context paramContext)
  {
    start(paramString, paramInt, paramContext, true);
  }

  public void startNewSession(String paramString, Context paramContext)
  {
    startNewSession(paramString, -1, paramContext);
  }

  @Deprecated
  public void stop()
  {
    if (this.dispatcher != null)
      this.dispatcher.stop();
    cancelPendingDispatches();
  }

  public void stopSession()
  {
    stop();
  }

  public void trackEvent(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("category cannot be null");
    if (paramString2 == null)
      throw new IllegalArgumentException("action cannot be null");
    createEvent(this.accountId, paramString1, paramString2, paramString3, paramInt);
  }

  public void trackPageView(String paramString)
  {
    createEvent(this.accountId, "__##GOOGLEPAGEVIEW##__", paramString, null, -1);
  }

  public void trackTransactions()
  {
    Iterator localIterator = this.transactionMap.values().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (Transaction)localIterator.next();
      Object localObject2 = new Event(this.accountId, "__##GOOGLETRANSACTION##__", "", "", 0, this.parent.getResources().getDisplayMetrics().widthPixels, this.parent.getResources().getDisplayMetrics().heightPixels);
      ((Event)localObject2).setTransaction((Transaction)localObject1);
      this.hitStore.putEvent((Event)localObject2);
      localObject1 = (Map)this.itemMap.get(((Transaction)localObject1).getOrderId());
      if (localObject1 != null)
      {
        localObject1 = ((Map)localObject1).values().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Item)((Iterator)localObject1).next();
          Event localEvent = new Event(this.accountId, "__##GOOGLEITEM##__", "", "", 0, this.parent.getResources().getDisplayMetrics().widthPixels, this.parent.getResources().getDisplayMetrics().heightPixels);
          localEvent.setItem((Item)localObject2);
          this.hitStore.putEvent(localEvent);
        }
      }
    }
    clearTransactions();
    resetPowerSaveMode();
  }

  final class DispatcherCallbacks
    implements Dispatcher.Callbacks
  {
    DispatcherCallbacks()
    {
    }

    public void dispatchFinished()
    {
      GoogleAnalyticsTracker.this.handler.post(new Runnable()
      {
        public void run()
        {
          GoogleAnalyticsTracker.this.dispatchFinished();
        }
      });
    }

    public void hitDispatched(long paramLong)
    {
      GoogleAnalyticsTracker.this.hitStore.deleteHit(paramLong);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.analytics.GoogleAnalyticsTracker
 * JD-Core Version:    0.6.2
 */