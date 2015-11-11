package com.taobao.munion.base.download;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.taobao.munion.base.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class i
{
  private static final String a = "UMENG_RUNTIME_CACHE";
  private static final String b = i.class.getName();
  private final Context c;

  public i(Context paramContext)
  {
    this.c = paramContext;
  }

  public List<Integer> a()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      SharedPreferences localSharedPreferences = this.c.getSharedPreferences("UMENG_RUNTIME_CACHE", 0);
      Iterator localIterator = localSharedPreferences.getAll().keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        try
        {
          int i = Integer.parseInt(str);
          localArrayList.add(Integer.valueOf(i));
          Log.i("get nid [" + i + "]", new Object[0]);
        }
        catch (NumberFormatException localNumberFormatException)
        {
        }
      }
      localSharedPreferences.edit().clear().commit();
      return localArrayList;
    }
    catch (Exception localException)
    {
    }
    return localArrayList;
  }

  public void a(int paramInt)
  {
    try
    {
      SharedPreferences localSharedPreferences = this.c.getSharedPreferences("UMENG_RUNTIME_CACHE", 0);
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      try
      {
        localEditor.putString("" + paramInt, "");
        localEditor.commit();
        Log.i("add nid [" + paramInt + "] to runtime cache.", new Object[0]);
        return;
      }
      finally
      {
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void b(int paramInt)
  {
    try
    {
      Object localObject = this.c.getSharedPreferences("UMENG_RUNTIME_CACHE", 0);
      if (((SharedPreferences)localObject).contains("" + paramInt))
      {
        localObject = ((SharedPreferences)localObject).edit();
        ((SharedPreferences.Editor)localObject).remove("" + paramInt);
        ((SharedPreferences.Editor)localObject).commit();
      }
      Log.i("remove nid [" + paramInt + "] to runtime cache.", new Object[0]);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public boolean b()
  {
    boolean bool = false;
    if (this.c.getSharedPreferences("UMENG_RUNTIME_CACHE", 0).getAll().size() > 0)
      bool = true;
    return bool;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.download.i
 * JD-Core Version:    0.6.2
 */