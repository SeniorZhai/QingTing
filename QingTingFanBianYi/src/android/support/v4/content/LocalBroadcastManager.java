package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class LocalBroadcastManager
{
  private static final boolean DEBUG = false;
  static final int MSG_EXEC_PENDING_BROADCASTS = 1;
  private static final String TAG = "LocalBroadcastManager";
  private static LocalBroadcastManager mInstance;
  private static final Object mLock = new Object();
  private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap();
  private final Context mAppContext;
  private final Handler mHandler;
  private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList();
  private final HashMap<BroadcastReceiver, ArrayList<IntentFilter>> mReceivers = new HashMap();

  private LocalBroadcastManager(Context paramContext)
  {
    this.mAppContext = paramContext;
    this.mHandler = new Handler(paramContext.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default:
          super.handleMessage(paramAnonymousMessage);
          return;
        case 1:
        }
        LocalBroadcastManager.this.executePendingBroadcasts();
      }
    };
  }

  private void executePendingBroadcasts()
  {
    while (true)
    {
      int i;
      synchronized (this.mReceivers)
      {
        i = this.mPendingBroadcasts.size();
        if (i <= 0)
          return;
        BroadcastRecord[] arrayOfBroadcastRecord = new BroadcastRecord[i];
        this.mPendingBroadcasts.toArray(arrayOfBroadcastRecord);
        this.mPendingBroadcasts.clear();
        i = 0;
        if (i >= arrayOfBroadcastRecord.length)
          continue;
        ??? = arrayOfBroadcastRecord[i];
        int j = 0;
        if (j < ???.receivers.size())
        {
          ((ReceiverRecord)???.receivers.get(j)).receiver.onReceive(this.mAppContext, ???.intent);
          j += 1;
        }
      }
      i += 1;
    }
  }

  public static LocalBroadcastManager getInstance(Context paramContext)
  {
    synchronized (mLock)
    {
      if (mInstance == null)
        mInstance = new LocalBroadcastManager(paramContext.getApplicationContext());
      paramContext = mInstance;
      return paramContext;
    }
  }

  public void registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter)
  {
    synchronized (this.mReceivers)
    {
      ReceiverRecord localReceiverRecord = new ReceiverRecord(paramIntentFilter, paramBroadcastReceiver);
      Object localObject2 = (ArrayList)this.mReceivers.get(paramBroadcastReceiver);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new ArrayList(1);
        this.mReceivers.put(paramBroadcastReceiver, localObject1);
      }
      ((ArrayList)localObject1).add(paramIntentFilter);
      int i = 0;
      while (i < paramIntentFilter.countActions())
      {
        localObject2 = paramIntentFilter.getAction(i);
        localObject1 = (ArrayList)this.mActions.get(localObject2);
        paramBroadcastReceiver = (BroadcastReceiver)localObject1;
        if (localObject1 == null)
        {
          paramBroadcastReceiver = new ArrayList(1);
          this.mActions.put(localObject2, paramBroadcastReceiver);
        }
        paramBroadcastReceiver.add(localReceiverRecord);
        i += 1;
      }
      return;
    }
  }

  public boolean sendBroadcast(Intent paramIntent)
  {
    int i;
    label164: int j;
    Object localObject2;
    int k;
    synchronized (this.mReceivers)
    {
      String str1 = paramIntent.getAction();
      String str2 = paramIntent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
      Uri localUri = paramIntent.getData();
      String str3 = paramIntent.getScheme();
      Set localSet = paramIntent.getCategories();
      if ((paramIntent.getFlags() & 0x8) == 0)
        break label514;
      i = 1;
      if (i != 0)
        Log.v("LocalBroadcastManager", "Resolving type " + str2 + " scheme " + str3 + " of intent " + paramIntent);
      ArrayList localArrayList = (ArrayList)this.mActions.get(paramIntent.getAction());
      if (localArrayList == null)
        break label490;
      if (i == 0)
        break label495;
      Log.v("LocalBroadcastManager", "Action list: " + localArrayList);
      break label495;
      if (j >= localArrayList.size())
        break label544;
      ReceiverRecord localReceiverRecord = (ReceiverRecord)localArrayList.get(j);
      if (i != 0)
        Log.v("LocalBroadcastManager", "Matching against filter " + localReceiverRecord.filter);
      if (localReceiverRecord.broadcasting)
      {
        localObject1 = localObject2;
        if (i != 0)
        {
          Log.v("LocalBroadcastManager", "  Filter's target already added");
          localObject1 = localObject2;
        }
      }
      else
      {
        k = localReceiverRecord.filter.match(str1, str2, str3, localUri, localSet, "LocalBroadcastManager");
        if (k >= 0)
        {
          if (i != 0)
            Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(k));
          localObject1 = localObject2;
          if (localObject2 == null)
            localObject1 = new ArrayList();
          ((ArrayList)localObject1).add(localReceiverRecord);
          localReceiverRecord.broadcasting = true;
        }
      }
    }
    Object localObject1 = localObject2;
    if (i != 0)
      switch (k)
      {
      default:
        localObject1 = "unknown reason";
        label387: Log.v("LocalBroadcastManager", "  Filter did not match: " + (String)localObject1);
        localObject1 = localObject2;
        break;
      case -3:
      case -4:
      case -2:
      case -1:
      }
    while (true)
      if (i < ((ArrayList)localObject2).size())
      {
        ((ReceiverRecord)((ArrayList)localObject2).get(i)).broadcasting = false;
        i += 1;
      }
      else
      {
        this.mPendingBroadcasts.add(new BroadcastRecord(paramIntent, (ArrayList)localObject2));
        if (!this.mHandler.hasMessages(1))
          this.mHandler.sendEmptyMessage(1);
        return true;
        label490: label495: 
        do
        {
          return false;
          localObject2 = null;
          j = 0;
          break label164;
          j += 1;
          localObject2 = localObject1;
          break label164;
          i = 0;
          break;
          localObject1 = "action";
          break label387;
          localObject1 = "category";
          break label387;
          localObject1 = "data";
          break label387;
          localObject1 = "type";
          break label387;
        }
        while (localObject2 == null);
        label514: label544: i = 0;
      }
  }

  public void sendBroadcastSync(Intent paramIntent)
  {
    if (sendBroadcast(paramIntent))
      executePendingBroadcasts();
  }

  public void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver)
  {
    while (true)
    {
      int k;
      int m;
      synchronized (this.mReceivers)
      {
        ArrayList localArrayList1 = (ArrayList)this.mReceivers.remove(paramBroadcastReceiver);
        if (localArrayList1 == null)
        {
          return;
          if (j < localArrayList1.size())
          {
            IntentFilter localIntentFilter = (IntentFilter)localArrayList1.get(j);
            k = 0;
            if (k >= localIntentFilter.countActions())
              break label191;
            String str = localIntentFilter.getAction(k);
            ArrayList localArrayList2 = (ArrayList)this.mActions.get(str);
            if (localArrayList2 == null)
              break label182;
            i = 0;
            if (i < localArrayList2.size())
            {
              m = i;
              if (((ReceiverRecord)localArrayList2.get(i)).receiver != paramBroadcastReceiver)
                break label173;
              localArrayList2.remove(i);
              m = i - 1;
              break label173;
            }
            if (localArrayList2.size() > 0)
              break label182;
            this.mActions.remove(str);
            break label182;
          }
          return;
        }
      }
      int j = 0;
      continue;
      label173: int i = m + 1;
      continue;
      label182: k += 1;
      continue;
      label191: j += 1;
    }
  }

  private static class BroadcastRecord
  {
    final Intent intent;
    final ArrayList<LocalBroadcastManager.ReceiverRecord> receivers;

    BroadcastRecord(Intent paramIntent, ArrayList<LocalBroadcastManager.ReceiverRecord> paramArrayList)
    {
      this.intent = paramIntent;
      this.receivers = paramArrayList;
    }
  }

  private static class ReceiverRecord
  {
    boolean broadcasting;
    final IntentFilter filter;
    final BroadcastReceiver receiver;

    ReceiverRecord(IntentFilter paramIntentFilter, BroadcastReceiver paramBroadcastReceiver)
    {
      this.filter = paramIntentFilter;
      this.receiver = paramBroadcastReceiver;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(128);
      localStringBuilder.append("Receiver{");
      localStringBuilder.append(this.receiver);
      localStringBuilder.append(" filter=");
      localStringBuilder.append(this.filter);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.content.LocalBroadcastManager
 * JD-Core Version:    0.6.2
 */