package fm.qingting.qtradio;

import android.app.Application;
import android.content.Context;

public class QTApplication extends Application
{
  public static Context appContext;

  public void onCreate()
  {
    super.onCreate();
    if (appContext == null)
      appContext = this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.QTApplication
 * JD-Core Version:    0.6.2
 */