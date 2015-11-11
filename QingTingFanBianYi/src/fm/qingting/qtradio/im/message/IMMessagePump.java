package fm.qingting.qtradio.im.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;

public class IMMessagePump
{
  private static IMMessagePump _instance;
  private Context mContext;
  private List<IRecvIMMsgListener> mLstRecvIMMsgListeners = new ArrayList();
  private IMMessageReceiver mMessageReceiver;
  private IMMessageReceiver mMessagereceiver;
  private boolean mRecvPushMsgBak = false;

  public static IMMessagePump getInstance()
  {
    if (_instance == null)
      _instance = new IMMessagePump();
    return _instance;
  }

  private void initMsgReceiver()
  {
    if (this.mContext == null)
      return;
    this.mMessagereceiver = new IMMessageReceiver();
  }

  private void releaseMsgReceiver()
  {
    try
    {
      if (this.mMessagereceiver != null)
      {
        this.mContext.unregisterReceiver(this.mMessagereceiver);
        this.mMessagereceiver = null;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void init(Context paramContext)
  {
    this.mContext = paramContext;
    initMsgReceiver();
  }

  public void registerRecvMsg(IRecvIMMsgListener paramIRecvIMMsgListener)
  {
    if (paramIRecvIMMsgListener == null)
      return;
    int i = 0;
    while (true)
    {
      if (i >= this.mLstRecvIMMsgListeners.size())
        break label41;
      if (this.mLstRecvIMMsgListeners.get(i) == paramIRecvIMMsgListener)
        break;
      i += 1;
    }
    label41: this.mLstRecvIMMsgListeners.add(paramIRecvIMMsgListener);
  }

  public void release()
  {
    this.mLstRecvIMMsgListeners.clear();
    releaseMsgReceiver();
  }

  public class IMMessageReceiver extends BroadcastReceiver
  {
    public IMMessageReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
    }
  }

  public static abstract interface IRecvIMMsgListener
  {
    public abstract boolean onRecvIMMsg(IMMessage paramIMMessage, String paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.im.message.IMMessagePump
 * JD-Core Version:    0.6.2
 */