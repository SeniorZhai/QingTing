package cn.com.iresearch.mapptracker.fm;

import android.content.SharedPreferences.Editor;
import android.util.Log;
import cn.com.iresearch.mapptracker.fm.dao.MATMessage;
import cn.com.iresearch.mapptracker.fm.util.f;

final class e extends Thread
{
  e(IRMonitor paramIRMonitor, String paramString1, String paramString2)
  {
  }

  public final void run()
  {
    while (true)
    {
      try
      {
        if (IRMonitor.d != null)
          IRMonitor.d.preSend();
        Object localObject = f.a(IRMonitor.d(IRMonitor.a()), this.b, this.c);
        if (localObject == null)
          return;
        SharedPreferences.Editor localEditor = IRMonitor.e(this.a);
        if (((MATMessage)localObject).flag)
        {
          bool = false;
          localEditor.putBoolean("isFirstRun", bool).commit();
          if (((MATMessage)localObject).flag)
          {
            if (IRMonitor.d != null)
              IRMonitor.d.sendSuccess();
            localObject = "send" + " session " + "data success!";
            if (IRMonitor.c)
              Log.e("MAT_SESSION", (String)localObject);
          }
          else
          {
            if (IRMonitor.d != null)
              IRMonitor.d.sendFail(((MATMessage)localObject).msg);
            localObject = "send" + " session " + "data fail!: " + ((MATMessage)localObject).msg;
            if (IRMonitor.c)
              Log.e("MAT_SESSION", (String)localObject);
          }
          return;
        }
      }
      catch (Exception localException)
      {
        return;
      }
      boolean bool = true;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.e
 * JD-Core Version:    0.6.2
 */