package fm.qingting.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public abstract class QThread
{
  protected Handler handler = null;
  private String name;
  private Boolean shouldStop = Boolean.valueOf(false);
  private int startMsgNum = 0;
  private HandlerThread thread = null;

  public QThread(String paramString)
  {
    this.name = paramString;
  }

  protected abstract void handleMsg(Message paramMessage);

  protected void log(String paramString)
  {
  }

  protected void setStartMsgNum(int paramInt)
  {
    this.startMsgNum = paramInt;
  }

  public void start()
  {
    start(true);
  }

  protected void start(boolean paramBoolean)
  {
    if (this.thread == null)
    {
      log("start");
      this.thread = new HandlerThread(this.name);
      this.thread.start();
      this.handler = new MyHandler(this.thread.getLooper());
    }
    this.shouldStop = Boolean.valueOf(false);
    if (paramBoolean)
      this.handler.sendEmptyMessage(this.startMsgNum);
    log("started");
  }

  public void stop()
  {
    this.shouldStop = Boolean.valueOf(true);
    if (this.handler != null)
      this.handler.removeMessages(this.startMsgNum);
    log("stop");
  }

  private class MyHandler extends Handler
  {
    public MyHandler(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      if (QThread.this.shouldStop.booleanValue())
      {
        QThread.this.log("this thread is stopped. call start() to launch.");
        return;
      }
      QThread.this.handleMsg(paramMessage);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.thread.QThread
 * JD-Core Version:    0.6.2
 */