package fm.qingting.qtradio.room;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class DoActionHandler extends Handler
{
  public DoActionHandler(Looper paramLooper)
  {
    super(paramLooper);
  }

  public void handleMessage(Message paramMessage)
  {
    try
    {
      paramMessage = (Action)paramMessage.obj;
      StateMachine.getInstance().getCurrState().execute(paramMessage);
      return;
    }
    catch (Exception paramMessage)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.DoActionHandler
 * JD-Core Version:    0.6.2
 */