package cn.com.iresearch.mapptracker.fm.a;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cn.com.iresearch.mapptracker.fm.a.e.e;
import cn.com.iresearch.mapptracker.fm.a.e.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public final class a
{
  private static HashMap<String, a> a = new HashMap();
  private SQLiteDatabase b;
  private b c;

  private a(b paramb)
  {
    if (paramb == null)
      throw new RuntimeException("daoConfig is null");
    if (paramb.a() == null)
      throw new RuntimeException("android context is null");
    this.b = new c(paramb.a().getApplicationContext(), paramb.b(), paramb.c()).getWritableDatabase();
    this.c = paramb;
  }

  public static a a(Context paramContext, String paramString)
  {
    b localb = new b();
    localb.a(paramContext);
    localb.a(paramString);
    localb.e();
    return a(localb);
  }

  private static a a(b paramb)
  {
    try
    {
      a locala2 = (a)a.get(paramb.b());
      a locala1 = locala2;
      if (locala2 == null)
      {
        locala1 = new a(paramb);
        a.put(paramb.b(), locala1);
      }
      return locala1;
    }
    finally
    {
    }
    throw paramb;
  }

  private void a(cn.com.iresearch.mapptracker.fm.a.d.a parama)
  {
    if (parama != null)
    {
      a(parama.a());
      this.b.execSQL(parama.a(), parama.b());
      return;
    }
    Log.e("FinalDb", "sava error:sqlInfo is null");
  }

  private void a(String paramString)
  {
    if ((this.c != null) && (this.c.d()))
      Log.d("Debug SQL", ">>>>>>  " + paramString);
  }

  private boolean a(f paramf)
  {
    Object localObject2 = null;
    Cursor localCursor2 = null;
    if (paramf.c())
      return true;
    Cursor localCursor1 = localCursor2;
    Object localObject1 = localObject2;
    while (true)
    {
      try
      {
        String str = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type ='table' AND name ='" + paramf.a() + "' ";
        localCursor1 = localCursor2;
        localObject1 = localObject2;
        a(str);
        localCursor1 = localCursor2;
        localObject1 = localObject2;
        localCursor2 = this.b.rawQuery(str, null);
        if (localCursor2 != null)
        {
          localCursor1 = localCursor2;
          localObject1 = localCursor2;
          if (localCursor2.moveToNext())
          {
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            if (localCursor2.getInt(0) > 0)
            {
              localCursor1 = localCursor2;
              localObject1 = localCursor2;
              paramf.d();
              if (localCursor2 == null)
                break;
              localCursor2.close();
              return true;
            }
          }
        }
      }
      catch (Exception paramf)
      {
        localObject1 = localCursor1;
        paramf.printStackTrace();
        if (localCursor1 != null)
          localCursor1.close();
        return false;
      }
      finally
      {
        if (localObject1 != null)
          ((Cursor)localObject1).close();
      }
      if (localCursor2 != null)
        localCursor2.close();
    }
  }

  private <T> List<T> b(Class<T> paramClass, String paramString)
  {
    c(paramClass);
    a(paramString);
    paramString = this.b.rawQuery(paramString, null);
    try
    {
      ArrayList localArrayList = new ArrayList();
      while (true)
      {
        boolean bool = paramString.moveToNext();
        if (!bool)
          return localArrayList;
        localArrayList.add(cn.com.iresearch.mapptracker.fm.dao.a.getEntity(paramString, paramClass));
      }
    }
    catch (Exception paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    finally
    {
      if (paramString != null)
        paramString.close();
    }
    throw paramClass;
  }

  private void c(Class<?> paramClass)
  {
    while (true)
    {
      Object localObject1;
      try
      {
        if (a(f.a(paramClass)))
          break;
        localObject1 = f.a(paramClass);
        localObject2 = ((f)localObject1).b();
        paramClass = new StringBuffer();
        paramClass.append("CREATE TABLE IF NOT EXISTS ");
        paramClass.append(((f)localObject1).a());
        paramClass.append(" ( ");
        localObject3 = ((cn.com.iresearch.mapptracker.fm.a.e.a)localObject2).e();
        if ((localObject3 == Integer.TYPE) || (localObject3 == Integer.class))
        {
          paramClass.append("\"").append(((cn.com.iresearch.mapptracker.fm.a.e.a)localObject2).c()).append("\"    INTEGER PRIMARY KEY AUTOINCREMENT,");
          localObject2 = ((f)localObject1).a.values().iterator();
          if (!((Iterator)localObject2).hasNext())
          {
            localObject1 = ((f)localObject1).b.values().iterator();
            if (((Iterator)localObject1).hasNext())
              break label239;
            paramClass.deleteCharAt(paramClass.length() - 1);
            paramClass.append(" )");
            paramClass = paramClass.toString();
            a(paramClass);
            this.b.execSQL(paramClass);
          }
        }
        else
        {
          paramClass.append("\"").append(((cn.com.iresearch.mapptracker.fm.a.e.a)localObject2).c()).append("\"    TEXT PRIMARY KEY,");
          continue;
        }
      }
      catch (SQLException paramClass)
      {
        paramClass.printStackTrace();
        return;
      }
      Object localObject3 = (e)((Iterator)localObject2).next();
      paramClass.append("\"").append(((e)localObject3).c());
      paramClass.append("\",");
      continue;
      label239: Object localObject2 = (cn.com.iresearch.mapptracker.fm.a.e.c)((Iterator)localObject1).next();
      paramClass.append("\"").append(((cn.com.iresearch.mapptracker.fm.a.e.c)localObject2).c()).append("\",");
    }
  }

  public final <T> List<T> a(Class<T> paramClass, String paramString)
  {
    c(paramClass);
    return b(paramClass, cn.com.iresearch.mapptracker.fm.dao.a.getSelectSQLByWhere(paramClass, paramString));
  }

  public final void a(Class<?> paramClass)
  {
    c(paramClass);
    paramClass = cn.com.iresearch.mapptracker.fm.dao.a.buildDeleteSql(paramClass, null);
    a(paramClass);
    this.b.execSQL(paramClass);
  }

  public final void a(Object paramObject)
  {
    c(paramObject.getClass());
    a(cn.com.iresearch.mapptracker.fm.dao.a.buildInsertSql(paramObject));
  }

  public final void a(Object paramObject, String paramString)
  {
    c(paramObject.getClass());
    a(cn.com.iresearch.mapptracker.fm.dao.a.getUpdateSqlAsSqlInfo(paramObject, paramString));
  }

  public final <T> List<T> b(Class<T> paramClass)
  {
    c(paramClass);
    return b(paramClass, cn.com.iresearch.mapptracker.fm.dao.a.getSelectSQL(paramClass));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.a.a
 * JD-Core Version:    0.6.2
 */