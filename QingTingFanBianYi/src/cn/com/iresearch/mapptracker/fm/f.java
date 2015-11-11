package cn.com.iresearch.mapptracker.fm;

import android.content.SharedPreferences.Editor;
import android.util.Log;
import cn.com.iresearch.mapptracker.fm.a.a;
import cn.com.iresearch.mapptracker.fm.dao.EventInfo;
import cn.com.iresearch.mapptracker.fm.dao.b;
import java.util.List;

final class f extends Thread
{
  f(boolean paramBoolean, EventInfo paramEventInfo, String paramString)
  {
  }

  public final void run()
  {
    int i = 0;
    try
    {
      if (this.a)
      {
        if (IRMonitor.i(IRMonitor.a()) <= IRMonitor.b(IRMonitor.a()).g())
        {
          Object localObject1 = IRMonitor.a();
          IRMonitor.a((IRMonitor)localObject1, IRMonitor.i((IRMonitor)localObject1) + 1);
          IRMonitor.e(IRMonitor.a()).putInt("event_Count", IRMonitor.i(IRMonitor.a()));
          IRMonitor.e(IRMonitor.a()).commit();
          IRMonitor.f(IRMonitor.a()).a(this.b);
          localObject1 = "保存event_id= " + this.c + " 的事件";
          if (!IRMonitor.c)
            return;
          Log.e("MAT_EVENT", (String)localObject1);
          return;
        }
        if (!IRMonitor.c)
          return;
        Log.e("MAT_EVENT", "Event记录数过多,存储失败");
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    Object localObject2 = IRMonitor.f(IRMonitor.a()).a(EventInfo.class, "event_id='" + this.c + "'");
    if (localObject2 != null)
      i = ((List)localObject2).size();
    if (i == 0)
    {
      if (IRMonitor.i(IRMonitor.a()) <= IRMonitor.b(IRMonitor.a()).g())
      {
        localObject2 = IRMonitor.a();
        IRMonitor.a((IRMonitor)localObject2, IRMonitor.i((IRMonitor)localObject2) + 1);
        IRMonitor.e(IRMonitor.a()).putInt("event_Count", IRMonitor.i(IRMonitor.a()));
        IRMonitor.e(IRMonitor.a()).commit();
        IRMonitor.f(IRMonitor.a()).a(this.b);
        localObject2 = "新增加event_id= " + this.c + " 的事件";
        if (IRMonitor.c)
          Log.e("MAT_EVENT", (String)localObject2);
      }
      else if (IRMonitor.c)
      {
        Log.e("MAT_EVENT", "Event记录数过多,存储失败");
      }
    }
    else
    {
      long l1 = this.b.getDuration();
      long l2 = this.b.getOpen_count();
      localObject2 = IRMonitor.f(IRMonitor.a()).a(EventInfo.class, "event_id='" + this.c + "'");
      if ((localObject2 != null) && (((List)localObject2).size() > 0))
      {
        long l3 = ((EventInfo)((List)localObject2).get(0)).getDuration();
        long l4 = ((EventInfo)((List)localObject2).get(0)).getOpen_count();
        this.b.setDuration(l1 + l3);
        this.b.setOpen_count(l2 + l4);
      }
      IRMonitor.f(IRMonitor.a()).a(this.b, "event_id='" + this.c + "'");
      localObject2 = "更新event_id= " + this.c + " 的事件";
      if (IRMonitor.c)
        Log.e("MAT_EVENT", (String)localObject2);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.f
 * JD-Core Version:    0.6.2
 */