package fm.qingting.qtradio.ad.platforms.mediav;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.mediav.ads.sdk.adcore.Mvad;
import com.mediav.ads.sdk.interfaces.IMvNativeAd;
import com.mediav.ads.sdk.interfaces.IMvNativeAdListener;
import com.mediav.ads.sdk.interfaces.IMvNativeAdLoader;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.qtradio.ad.platforms.mediav.model.IMediaVListener;
import fm.qingting.qtradio.ad.platforms.mediav.model.MediaVNativeAd;
import fm.qingting.qtradio.ad.platforms.mediav.model.MediaVRequest;
import fm.qingting.utils.AppInfo;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONObject;

public class MediaVAgent
{
  private static final int AD_POSITION = 7;
  private static final String AD_SPACE_ID = "FFFv5SDTCw";
  public static final int UPDATE_FREQUENCY = 50;
  private static String enabledChannels = null;
  private static ArrayList<String> enabledSecs = null;
  private static MediaVAgent instance;
  private static final boolean mIsTest = false;
  private MediaVWebView mCustomView;
  private ArrayList<IMediaVListener> mListeners;
  private IMvNativeAdLoader mLoader;
  private LinkedList<MediaVRequest> requestQueue;

  private MediaVAgent(Activity paramActivity)
  {
    if (isChannelEnabled(paramActivity))
      init(paramActivity, "FFFv5SDTCw");
  }

  private static void getAdPos(Context paramContext)
  {
  }

  public static int getAdPosition()
  {
    return 7;
  }

  public static MediaVAgent getInstance(Activity paramActivity)
  {
    if (instance == null)
      instance = new MediaVAgent(paramActivity);
    return instance;
  }

  private void init(Activity paramActivity, String paramString)
  {
    this.requestQueue = new LinkedList();
    this.mListeners = new ArrayList();
    this.mCustomView = new MediaVWebView();
    Mvad.setLandingPageView(paramActivity, this.mCustomView);
    this.mLoader = Mvad.initNativeAdLoader(paramActivity, paramString, new IMvNativeAdListener()
    {
      public void onNativeAdLoadFailed()
      {
        MediaVAgent.this.response(MediaVAgent.access$000(MediaVAgent.this), null);
        MediaVAgent.this.loadQueueHead();
      }

      public void onNativeAdLoadSucceeded(ArrayList<IMvNativeAd> paramAnonymousArrayList)
      {
        if (paramAnonymousArrayList != null);
        while (true)
        {
          ArrayList localArrayList;
          try
          {
            if (MediaVAgent.this.requestQueue.isEmpty())
              break label304;
            localMediaVRequest = (MediaVRequest)MediaVAgent.this.requestQueue.getFirst();
            localArrayList = new ArrayList();
            Iterator localIterator = paramAnonymousArrayList.iterator();
            if (!localIterator.hasNext())
              break label251;
            paramAnonymousArrayList = (IMvNativeAd)localIterator.next();
            try
            {
              MediaVNativeAd localMediaVNativeAd = new MediaVNativeAd();
              JSONObject localJSONObject = paramAnonymousArrayList.getContent();
              localMediaVNativeAd.logo = localJSONObject.getString("logo");
              localMediaVNativeAd.title = localJSONObject.getString("title");
              localMediaVNativeAd.desc = localJSONObject.getString("desc");
              localMediaVNativeAd.thumbnail = localJSONObject.getString("contentimg");
              localMediaVNativeAd.buttonText = localJSONObject.getString("btntext");
              localMediaVNativeAd.extendText = localJSONObject.getString("ext_text");
              localMediaVNativeAd.rawAd = paramAnonymousArrayList;
              if (localMediaVRequest == null)
                break label233;
              paramAnonymousArrayList = localMediaVRequest.getAdPos();
              localMediaVNativeAd.adPos = paramAnonymousArrayList;
              localMediaVNativeAd.setCategoryId(localMediaVRequest.getCategoryId());
              localArrayList.add(localMediaVNativeAd);
              if (localMediaVNativeAd.buttonText == null)
                break label238;
              QTMSGManage.getInstance().sendStatistcsMessage("mediav", localMediaVNativeAd.buttonText);
            }
            catch (Exception paramAnonymousArrayList)
            {
              paramAnonymousArrayList.printStackTrace();
            }
            continue;
          }
          finally
          {
          }
          label233: paramAnonymousArrayList = null;
          continue;
          label238: QTMSGManage.getInstance().sendStatistcsMessage("mediav", "hasmediav_null");
          continue;
          label251: if (localArrayList.size() > 0)
            QTMSGManage.getInstance().sendStatistcsMessage("mediav", "load_success");
          while (true)
          {
            MediaVAgent.this.response(MediaVAgent.access$000(MediaVAgent.this), localArrayList);
            MediaVAgent.this.loadQueueHead();
            return;
            QTMSGManage.getInstance().sendStatistcsMessage("mediav", "load_empty");
          }
          label304: MediaVRequest localMediaVRequest = null;
        }
      }
    }
    , Boolean.valueOf(false));
  }

  private static boolean isChannelEnabled(Context paramContext)
  {
    if (enabledChannels == null)
      enabledChannels = MobclickAgent.getConfigParams(paramContext, "mediav_enable_channels");
    if ((enabledChannels != null) && (enabledChannels.equalsIgnoreCase("all")));
    do
    {
      return true;
      paramContext = AppInfo.getChannelName(paramContext);
    }
    while ((enabledChannels != null) && (!TextUtils.isEmpty(paramContext)) && (enabledChannels.contains(paramContext)));
    return false;
  }

  public static boolean isSectionEnabled(Context paramContext, int paramInt)
  {
    return isChannelEnabled(paramContext);
  }

  // ERROR //
  private void loadQueueHead()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 73	fm/qingting/qtradio/ad/platforms/mediav/MediaVAgent:requestQueue	Ljava/util/LinkedList;
    //   6: invokevirtual 154	java/util/LinkedList:isEmpty	()Z
    //   9: ifne +82 -> 91
    //   12: aload_0
    //   13: getfield 73	fm/qingting/qtradio/ad/platforms/mediav/MediaVAgent:requestQueue	Ljava/util/LinkedList;
    //   16: invokevirtual 158	java/util/LinkedList:getFirst	()Ljava/lang/Object;
    //   19: checkcast 160	fm/qingting/qtradio/ad/platforms/mediav/model/MediaVRequest
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: ifnull +30 -> 56
    //   29: aload_0
    //   30: aload_1
    //   31: invokevirtual 164	fm/qingting/qtradio/ad/platforms/mediav/model/MediaVRequest:getKeywords	()[Ljava/lang/String;
    //   34: invokespecial 168	fm/qingting/qtradio/ad/platforms/mediav/MediaVAgent:updateKeywords	([Ljava/lang/String;)V
    //   37: invokestatic 173	fm/qingting/utils/QTMSGManage:getInstance	()Lfm/qingting/utils/QTMSGManage;
    //   40: ldc 175
    //   42: ldc 177
    //   44: invokevirtual 181	fm/qingting/utils/QTMSGManage:sendStatistcsMessage	(Ljava/lang/String;Ljava/lang/String;)V
    //   47: aload_0
    //   48: getfield 116	fm/qingting/qtradio/ad/platforms/mediav/MediaVAgent:mLoader	Lcom/mediav/ads/sdk/interfaces/IMvNativeAdLoader;
    //   51: invokeinterface 186 1 0
    //   56: return
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    //   62: astore_1
    //   63: invokestatic 173	fm/qingting/utils/QTMSGManage:getInstance	()Lfm/qingting/utils/QTMSGManage;
    //   66: ldc 175
    //   68: ldc 188
    //   70: invokevirtual 181	fm/qingting/utils/QTMSGManage:sendStatistcsMessage	(Ljava/lang/String;Ljava/lang/String;)V
    //   73: aload_1
    //   74: invokevirtual 191	java/lang/Exception:printStackTrace	()V
    //   77: aload_0
    //   78: aload_0
    //   79: invokespecial 58	fm/qingting/qtradio/ad/platforms/mediav/MediaVAgent:removeQueueHead	()Lfm/qingting/qtradio/ad/platforms/mediav/model/MediaVRequest;
    //   82: aconst_null
    //   83: invokespecial 64	fm/qingting/qtradio/ad/platforms/mediav/MediaVAgent:response	(Lfm/qingting/qtradio/ad/platforms/mediav/model/MediaVRequest;Ljava/util/ArrayList;)V
    //   86: aload_0
    //   87: invokespecial 69	fm/qingting/qtradio/ad/platforms/mediav/MediaVAgent:loadQueueHead	()V
    //   90: return
    //   91: aconst_null
    //   92: astore_1
    //   93: goto -70 -> 23
    //
    // Exception table:
    //   from	to	target	type
    //   2	23	57	finally
    //   23	25	57	finally
    //   58	60	57	finally
    //   37	56	62	java/lang/Exception
  }

  private MediaVRequest removeQueueHead()
  {
    MediaVRequest localMediaVRequest = null;
    try
    {
      if (!this.requestQueue.isEmpty())
        localMediaVRequest = (MediaVRequest)this.requestQueue.removeFirst();
      return localMediaVRequest;
    }
    finally
    {
    }
  }

  private void response(MediaVRequest paramMediaVRequest, ArrayList<MediaVNativeAd> paramArrayList)
  {
    if (paramMediaVRequest == null);
    while (true)
    {
      return;
      Iterator localIterator = this.mListeners.iterator();
      while (localIterator.hasNext())
      {
        IMediaVListener localIMediaVListener = (IMediaVListener)localIterator.next();
        try
        {
          localIMediaVListener.onMediaVNativeAdResponse(paramMediaVRequest, paramArrayList);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
  }

  public static void setEnableChannels(String paramString)
  {
    enabledChannels = paramString;
  }

  private void updateKeywords(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length < 1))
      return;
    this.mLoader.clearKeywords();
    HashSet localHashSet = new HashSet();
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      localHashSet.add(paramArrayOfString[i]);
      i += 1;
    }
    this.mLoader.setKeywords(localHashSet);
  }

  public void addListener(IMediaVListener paramIMediaVListener)
  {
    if ((paramIMediaVListener != null) && (!this.mListeners.contains(paramIMediaVListener)))
      this.mListeners.add(paramIMediaVListener);
  }

  public void loadAds(MediaVRequest paramMediaVRequest)
  {
    QTMSGManage.getInstance().sendStatistcsMessage("mediav", "request_intent");
    if (paramMediaVRequest == null)
      return;
    try
    {
      this.requestQueue.addLast(paramMediaVRequest);
      if (this.requestQueue.size() > 1)
        return;
    }
    finally
    {
    }
    loadQueueHead();
  }

  public void removeListener(IMediaVListener paramIMediaVListener)
  {
    if (paramIMediaVListener != null)
      try
      {
        this.mListeners.remove(paramIMediaVListener);
        return;
      }
      finally
      {
      }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.ad.platforms.mediav.MediaVAgent
 * JD-Core Version:    0.6.2
 */