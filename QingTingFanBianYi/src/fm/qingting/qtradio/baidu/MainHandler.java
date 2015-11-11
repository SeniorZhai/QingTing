package fm.qingting.qtradio.baidu;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class MainHandler extends Handler
{
  public MainHandler()
  {
    super(Looper.getMainLooper());
  }

  public void handleMessage(Message paramMessage)
  {
    paramMessage = (BDResponse)paramMessage.obj;
    if (paramMessage.getListener() != null)
      paramMessage.getListener().onResponse(paramMessage);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.baidu.MainHandler
 * JD-Core Version:    0.6.2
 */