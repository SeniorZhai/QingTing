package cn.com.iresearch.mapptracker.fm;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cn.com.iresearch.mapptracker.fm.dao.SessionInfo;

final class d extends Thread
{
  d(IRMonitor paramIRMonitor, SessionInfo paramSessionInfo)
  {
  }

  public final void run()
  {
    long l1 = IRMonitor.a(this.a).getLong("endPoint", 0L);
    long l2 = Math.abs(System.currentTimeMillis() / 1000L - l1);
    if (((0L == l1) || (l2 >= 30L)) && (IRMonitor.b(IRMonitor.a()).g() % 2 != 0))
      new cn.com.iresearch.mapptracker.fm.util.b(IRMonitor.f(this.a), IRMonitor.d(this.a)).a(this.b);
    while (true)
    {
      IRMonitor.e(this.a).putLong("endPoint", IRMonitor.l(this.a)).commit();
      return;
      IRMonitor.a(this.b);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.d
 * JD-Core Version:    0.6.2
 */