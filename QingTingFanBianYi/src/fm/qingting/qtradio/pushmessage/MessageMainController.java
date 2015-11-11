package fm.qingting.qtradio.pushmessage;

import android.content.Context;
import fm.qingting.qtradio.manager.INETEventListener;
import fm.qingting.qtradio.model.GlobalCfg;

public class MessageMainController
  implements MessagePump.IRecvPushMsgListener, INETEventListener
{
  private static MessageMainController _instance;
  private boolean mAliasPush = true;
  private Context mContext;
  private String mDeviceId;
  private boolean mGlobalPush = true;
  private boolean mRegisterPush = false;
  private boolean mStarted = false;

  public static MessageMainController getInstance()
  {
    if (_instance == null)
      _instance = new MessageMainController();
    return _instance;
  }

  private void register()
  {
  }

  private void saveClientID(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
    {
      String str = GlobalCfg.getInstance(this.mContext).getGeTuiClientID();
      if ((str == null) || (!str.equalsIgnoreCase(paramString)));
    }
    else
    {
      return;
    }
    GlobalCfg.getInstance(this.mContext).saveGeTuiClientID(paramString);
    PushMessageLog.sendPushLog(this.mContext, paramString, this.mDeviceId);
  }

  private void subscribe()
  {
    if (this.mContext == null);
  }

  public String getDeviceId()
  {
    return this.mDeviceId;
  }

  public boolean hasRegister()
  {
    return this.mRegisterPush;
  }

  public void init(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mContext = paramContext;
    this.mGlobalPush = paramBoolean1;
    this.mAliasPush = paramBoolean2;
    MessagePump.getInstance().init(this.mContext, paramBoolean1, paramBoolean2);
    if (this.mGlobalPush)
      GlobalMessageHandler.getInstance().init(this.mContext);
    if (this.mAliasPush)
      AliasMessageHandler.getInstance().init(this.mContext);
    IMHandler.getInstance().init(this.mContext);
    subscribe();
  }

  public void onNetChanged(String paramString)
  {
    if ((this.mStarted) && (!this.mRegisterPush))
      register();
  }

  public boolean onRecvPushMsg(PushMessage paramPushMessage, int paramInt)
  {
    if (paramPushMessage == null);
    while (paramInt != 2)
      return false;
    this.mRegisterPush = true;
    subscribe();
    if (this.mAliasPush)
      AliasMessageHandler.getInstance().setAlias(paramPushMessage.mRegId);
    saveClientID(paramPushMessage.mRegId);
    return true;
  }

  public void setDeviceId(String paramString)
  {
    this.mDeviceId = paramString;
  }

  public boolean start()
  {
    if (this.mContext == null)
      return false;
    MessagePump.getInstance().registerRecvMsg(this);
    register();
    this.mStarted = true;
    return true;
  }

  public void stop()
  {
    MessagePump.getInstance().release();
  }

  public void subscribeLiveTopic(Context paramContext)
  {
    if (paramContext == null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.pushmessage.MessageMainController
 * JD-Core Version:    0.6.2
 */