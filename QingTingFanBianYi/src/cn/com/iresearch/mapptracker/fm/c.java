package cn.com.iresearch.mapptracker.fm;

import android.content.SharedPreferences.Editor;
import android.util.Log;
import cn.com.iresearch.mapptracker.fm.dao.MATMessage;
import cn.com.iresearch.mapptracker.fm.util.f;

final class c extends Thread
{
  c(IRMonitor paramIRMonitor, String paramString1, String paramString2)
  {
  }

  public final void run()
  {
    try
    {
      if (IRMonitor.d != null)
        IRMonitor.d.preSend();
      Object localObject = f.a(IRMonitor.d(IRMonitor.a()), this.b, this.c);
      if (localObject == null)
        return;
      boolean bool;
      if (((MATMessage)localObject).flag)
      {
        if (IRMonitor.c)
          Log.e("MAT_SESSION", "send---dayData---data success!");
        SharedPreferences.Editor localEditor = IRMonitor.e(this.a);
        if (((MATMessage)localObject).flag)
        {
          bool = false;
          localEditor.putBoolean("isFirstRun", bool).commit();
          localObject = f.a();
          if (!"".equals(localObject))
          {
            long l = Long.valueOf((String)localObject).longValue();
            IRMonitor.e(this.a).putLong("daysend", l);
          }
          if (IRMonitor.d != null)
            IRMonitor.d.sendSuccess();
        }
      }
      while (true)
      {
        IRMonitor.k(this.a);
        return;
        bool = true;
        break;
        if (IRMonitor.d != null)
          IRMonitor.d.sendFail(((MATMessage)localObject).msg);
        localObject = "send---dayData---data fail!: " + ((MATMessage)localObject).msg;
        if (IRMonitor.c)
          Log.e("MAT_SESSION", (String)localObject);
      }
    }
    catch (Exception localException)
    {
      while (true)
        IRMonitor.d.sendFail(localException.getMessage());
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.c
 * JD-Core Version:    0.6.2
 */