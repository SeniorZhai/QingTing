package fm.qingting.qtradio.view.groupselect;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.webkit.JavascriptInterface;
import fm.qingting.qtradio.fm.PlayerAgent;

public class QTPlayerJavascriptInterface
{
  private QTPlayerHandler mHandler = new QTPlayerHandler();

  @JavascriptInterface
  public void pause()
  {
    this.mHandler.sendEmptyMessage(0);
  }

  @JavascriptInterface
  public void play()
  {
    this.mHandler.sendEmptyMessage(1);
  }

  class QTPlayerHandler extends Handler
  {
    @SuppressLint({"HandlerLeak"})
    public QTPlayerHandler()
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        PlayerAgent.getInstance().stop();
        return;
      case 1:
      }
      PlayerAgent.getInstance().play();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.groupselect.QTPlayerJavascriptInterface
 * JD-Core Version:    0.6.2
 */