package fm.qingting.qtradio.pushmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import fm.qingting.qtradio.model.GlobalCfg;
import java.util.ArrayList;
import java.util.List;

public class MessagePump
{
  private static MessagePump _instance;
  private boolean mAlias = false;
  private Context mContext;
  private boolean mGlobalPush = false;
  private List<IRecvPushMsgListener> mLstRecvPushMsgListeners = new ArrayList();
  private MsgReceiver mMsgReceiver;
  private boolean mRecvPushMsgBak = false;

  private void dispatchRecvMsg(PushMessage paramPushMessage, int paramInt)
  {
    if (paramPushMessage == null);
    while (true)
    {
      return;
      int i = 0;
      while ((i < this.mLstRecvPushMsgListeners.size()) && (!((IRecvPushMsgListener)this.mLstRecvPushMsgListeners.get(i)).onRecvPushMsg(paramPushMessage, paramInt)))
        i += 1;
    }
  }

  public static MessagePump getInstance()
  {
    if (_instance == null)
      _instance = new MessagePump();
    return _instance;
  }

  private void initMsgReceiver()
  {
    if (this.mContext == null)
      return;
    this.mMsgReceiver = new MsgReceiver();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("fm.qingting.qtradio.GEXIN_MESSAGE");
    localIntentFilter.addAction("fm.qingting.qtradio.GEXIN_MESSAGE_BAK");
    this.mContext.registerReceiver(this.mMsgReceiver, localIntentFilter);
  }

  private void releaseMsgReceiver()
  {
    try
    {
      if (this.mMsgReceiver != null)
      {
        this.mContext.unregisterReceiver(this.mMsgReceiver);
        this.mMsgReceiver = null;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void init(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mContext = paramContext;
    initMsgReceiver();
    this.mGlobalPush = paramBoolean1;
    this.mAlias = paramBoolean2;
  }

  public void registerRecvMsg(IRecvPushMsgListener paramIRecvPushMsgListener)
  {
    if (paramIRecvPushMsgListener == null)
      return;
    int i = 0;
    while (true)
    {
      if (i >= this.mLstRecvPushMsgListeners.size())
        break label41;
      if (this.mLstRecvPushMsgListeners.get(i) == paramIRecvPushMsgListener)
        break;
      i += 1;
    }
    label41: this.mLstRecvPushMsgListeners.add(paramIRecvPushMsgListener);
  }

  public void release()
  {
    this.mLstRecvPushMsgListeners.clear();
    releaseMsgReceiver();
  }

  public static abstract interface IRecvPushMsgListener
  {
    public abstract boolean onRecvPushMsg(PushMessage paramPushMessage, int paramInt);
  }

  class MsgReceiver extends BroadcastReceiver
  {
    MsgReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      label4: if (paramIntent == null)
        break label4;
      do
      {
        do
          return;
        while ((paramIntent.getAction() == null) || ((!paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.GEXIN_MESSAGE")) && (!paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.GEXIN_MESSAGE_BAK"))));
        localObject = paramIntent.getExtras();
        if (localObject == null)
          break;
        paramContext = ((Bundle)localObject).getString("type");
        if (paramContext == null)
          break;
        switch (Integer.valueOf(paramContext).intValue())
        {
        case 1:
        default:
          return;
        case 0:
        case 2:
        }
      }
      while ((paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.GEXIN_MESSAGE_BAK")) && (MessagePump.this.mRecvPushMsgBak));
      MessagePump.access$002(MessagePump.this, true);
      paramContext = ((Bundle)localObject).getString("msg");
      paramIntent = ((Bundle)localObject).getString("topic");
      String str = ((Bundle)localObject).getString("alias");
      Object localObject = new PushMessage();
      ((PushMessage)localObject).mAlias = str;
      ((PushMessage)localObject).mTopic = paramIntent;
      ((PushMessage)localObject).mMessage = paramContext;
      if ((paramContext != null) && (!paramContext.contains("qingting:startService")))
      {
        paramIntent = GlobalCfg.getInstance(MessagePump.this.mContext).getRecvPushMsgTask();
        if ((paramIntent != null) && (paramContext.equalsIgnoreCase(paramIntent)))
        {
          PushMessageLog.sendGetuiMsgFromServiceLog(MessagePump.this.mContext, MessagePump.this.mAlias, MessagePump.this.mGlobalPush);
          return;
        }
        GlobalCfg.getInstance(MessagePump.this.mContext).setRecvPushMsgTask(paramContext);
      }
      MessagePump.this.dispatchRecvMsg((PushMessage)localObject, 0);
      PushMessageLog.sendGetuiMsgFromServiceLog(MessagePump.this.mContext, MessagePump.this.mAlias, MessagePump.this.mGlobalPush);
      return;
      paramContext = ((Bundle)localObject).getString("reg");
      paramIntent = new PushMessage();
      paramIntent.mRegId = paramContext;
      MessagePump.this.dispatchRecvMsg(paramIntent, 2);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.pushmessage.MessagePump
 * JD-Core Version:    0.6.2
 */