package cn.com.iresearch.mapptracker.fm;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import cn.com.iresearch.mapptracker.fm.a.a;
import cn.com.iresearch.mapptracker.fm.dao.EventInfo;
import cn.com.iresearch.mapptracker.fm.dao.MATMessage;
import cn.com.iresearch.mapptracker.fm.dao.SessionInfo;
import cn.com.iresearch.mapptracker.fm.util.f;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

final class b extends Thread
{
  b(List paramList, String paramString)
  {
  }

  public final void run()
  {
    long l3;
    long l5;
    long l1;
    try
    {
      IRMonitor.a(IRMonitor.a(), true);
      if (IRMonitor.d != null)
        IRMonitor.d.preSend();
      l3 = 0L;
      l5 = IRMonitor.a(IRMonitor.a()).getLong("open_count", 1L);
      l1 = 1L;
      if (IRMonitor.d(IRMonitor.a()) == null)
      {
        IRMonitor.a(IRMonitor.a(), false);
        return;
      }
      if (f.b(IRMonitor.d(IRMonitor.a())))
        break label144;
      IRMonitor.e(IRMonitor.a()).putLong("open_count", 1L + l5).commit();
      if (IRMonitor.c)
        Log.d("MAT_SESSION", "网络未打开！");
      IRMonitor.a(IRMonitor.a(), false);
      return;
    }
    catch (Exception localException)
    {
      if (IRMonitor.c)
        Log.e("MAT_SESSION", "数据发送失败，发生异常");
      localException.printStackTrace();
    }
    IRMonitor.a(IRMonitor.a(), false);
    return;
    label144: Object localObject2 = new JSONObject();
    Object localObject3 = new JSONArray();
    long l6 = this.a.size();
    label201: int i;
    List localList;
    JSONArray localJSONArray;
    if (0L == l6)
    {
      if (IRMonitor.c)
        Log.e("MAT_SESSION", "Session数据为空");
      IRMonitor.a(IRMonitor.a(), false);
      return;
      if (i >= l6)
      {
        localList = IRMonitor.f(IRMonitor.a()).b(EventInfo.class);
        localJSONArray = new JSONArray();
        i = 0;
        label235: if (i < localList.size())
          break label484;
        ((JSONObject)localObject2).put("event_list", localJSONArray);
        ((JSONObject)localObject2).put("header", f.f(IRMonitor.d(IRMonitor.a())));
        ((JSONObject)localObject2).put("open_count", l5);
        ((JSONObject)localObject2).put("page_count", l1);
        ((JSONObject)localObject2).put("run_time", l3);
        ((JSONObject)localObject2).put("page_list", localObject3);
        ((JSONObject)localObject2).put("lat", "");
        if (!f.b())
          break label1027;
      }
    }
    label1027: for (Object localObject1 = "1"; ; localObject1 = "0")
    {
      ((JSONObject)localObject2).put("lng", localObject1);
      localObject1 = ((JSONObject)localObject2).toString();
      long l2;
      long l4;
      if ((1 == IRMonitor.b(IRMonitor.a()).f()) && (!f.a(IRMonitor.d(IRMonitor.a()))))
      {
        if (IRMonitor.c)
          Log.d("MAT_SESSION", "当前发送模式为只在wifi下发送，当前网络wifi不可用，故发送取消！");
        IRMonitor.e(IRMonitor.a()).putLong("open_count", 1L + l5).commit();
        IRMonitor.a(IRMonitor.a(), false);
        return;
        localObject1 = (SessionInfo)this.a.get(i);
        long l7 = ((SessionInfo)localObject1).getDuration();
        l2 = l1;
        l4 = l3;
        if (0L != l7)
        {
          l4 = l3 + l7;
          l2 = l1;
          if (0L == ((SessionInfo)localObject1).inapp)
            l2 = l1 + 1L;
          ((JSONArray)localObject3).put(i, IRMonitor.a((SessionInfo)localObject1));
          break label1001;
          label484: localObject1 = (EventInfo)localList.get(i);
          if (((EventInfo)localObject1).eventisStart)
            break label1018;
          JSONObject localJSONObject = IRMonitor.a((EventInfo)localObject1);
          String str = ((EventInfo)localObject1).getEvent_params();
          localObject1 = null;
          if (str != null)
            localObject1 = new JSONObject(str);
          localJSONObject.put("event_params", localObject1);
          localJSONArray.put(i, localJSONObject);
          break label1018;
        }
      }
      else
      {
        new MATMessage();
        localObject2 = IRMonitor.d(IRMonitor.a());
        localObject3 = this.b;
        localObject1 = f.a((Context)localObject2, (String)localObject1, IRMonitor.b(IRMonitor.a()).a());
        System.out.println("send all data");
        if (((MATMessage)localObject1).isFlag())
        {
          IRMonitor.e += 1;
          if (IRMonitor.c)
            Log.e("MAT_SESSION", "send data success!");
          localObject1 = "send data num:" + IRMonitor.e;
          if (IRMonitor.c)
            Log.e("MAT_SESSION", (String)localObject1);
          IRMonitor.g(IRMonitor.a());
          IRMonitor.e(IRMonitor.a()).putInt("sPage_Count", IRMonitor.h(IRMonitor.a()));
          IRMonitor.a(IRMonitor.a(), 0);
          IRMonitor.e(IRMonitor.a()).putInt("event_Count", IRMonitor.i(IRMonitor.a()));
          IRMonitor.e(IRMonitor.a()).putLong("open_count", 1L);
          IRMonitor.e(IRMonitor.a()).commit();
          IRMonitor.f(IRMonitor.a()).a(SessionInfo.class);
          IRMonitor.f(IRMonitor.a()).a(EventInfo.class);
          localObject1 = new HashMap();
          if (IRMonitor.j(IRMonitor.a()) != null)
            localObject2 = IRMonitor.j(IRMonitor.a()).values().iterator();
          while (true)
          {
            if (!((Iterator)localObject2).hasNext())
            {
              IRMonitor.j(IRMonitor.a()).clear();
              IRMonitor.j(IRMonitor.a()).putAll((Map)localObject1);
              ((Map)localObject1).clear();
              if (IRMonitor.d == null)
                break;
              IRMonitor.d.sendSuccess();
              break;
            }
            localObject3 = (EventInfo)((Iterator)localObject2).next();
            if (((EventInfo)localObject3).eventisStart)
            {
              ((Map)localObject1).put(((EventInfo)localObject3).getEvent_id(), localObject3);
              localObject3 = "未结束的事件:" + ((EventInfo)localObject3).getEvent_id();
              if (IRMonitor.c)
                Log.d("MAT_EVENT", (String)localObject3);
            }
          }
        }
        if (IRMonitor.d != null)
          IRMonitor.d.sendFail(((MATMessage)localObject1).msg);
        IRMonitor.e(IRMonitor.a()).putLong("open_count", 1L + l5).commit();
        localObject1 = "数据发送失败，open_count++ " + ((MATMessage)localObject1).msg;
        if (!IRMonitor.c)
          break;
        Log.e("MAT_SESSION", (String)localObject1);
        break;
        i = 0;
        break label201;
      }
      label1001: i += 1;
      l3 = l4;
      l1 = l2;
      break label201;
      label1018: i += 1;
      break label235;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.b
 * JD-Core Version:    0.6.2
 */