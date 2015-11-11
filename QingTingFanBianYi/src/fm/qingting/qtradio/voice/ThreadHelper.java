package fm.qingting.qtradio.voice;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadHelper extends ThreadPoolExecutor
{
  private static ThreadHelper mInstance;

  private ThreadHelper()
  {
    super(2, 4, 6L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  }

  public static ThreadHelper getInstance()
  {
    if (mInstance == null)
      mInstance = new ThreadHelper();
    return mInstance;
  }

  public void setCorePoolSize(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 2)
      i = 2;
    super.setCorePoolSize(i);
  }

  public void setKeepAliveTime(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 6)
      i = 6;
    super.setKeepAliveTime(i, TimeUnit.SECONDS);
  }

  public void setMaximumPoolSize(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 4)
      i = 4;
    super.setMaximumPoolSize(i);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.voice.ThreadHelper
 * JD-Core Version:    0.6.2
 */