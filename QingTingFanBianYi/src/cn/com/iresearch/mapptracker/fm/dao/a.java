package cn.com.iresearch.mapptracker.fm.dao;

import android.database.Cursor;
import android.text.TextUtils;
import cn.com.iresearch.mapptracker.fm.a.e.c;
import cn.com.iresearch.mapptracker.fm.a.e.d;
import cn.com.iresearch.mapptracker.fm.a.e.f;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class a
{
  public static String buildDeleteSql(Class<?> paramClass, String paramString)
  {
    paramClass = f.a(paramClass).a();
    paramClass = new StringBuffer("DELETE FROM " + paramClass);
    if (!TextUtils.isEmpty(paramString))
    {
      paramClass.append(" WHERE ");
      paramClass.append(paramString);
    }
    return paramClass.toString();
  }

  public static cn.com.iresearch.mapptracker.fm.a.d.a buildInsertSql(Object paramObject)
  {
    LinkedList localLinkedList = new LinkedList();
    Object localObject1 = f.a(paramObject.getClass());
    Object localObject2 = ((f)localObject1).b().a(paramObject);
    if ((!(localObject2 instanceof Integer)) && ((localObject2 instanceof String)) && (localObject2 != null))
      localLinkedList.add(new cn.com.iresearch.mapptracker.fm.a.e.b(((f)localObject1).b().c(), localObject2));
    localObject2 = ((f)localObject1).a.values().iterator();
    label100: int j;
    label172: int i;
    if (!((Iterator)localObject2).hasNext())
    {
      localObject1 = ((f)localObject1).b.values().iterator();
      if (((Iterator)localObject1).hasNext())
        break label277;
      localObject2 = new StringBuffer();
      localObject1 = null;
      if (localLinkedList.size() > 0)
      {
        localObject1 = new cn.com.iresearch.mapptracker.fm.a.d.a();
        ((StringBuffer)localObject2).append("INSERT INTO ");
        ((StringBuffer)localObject2).append(f.a(paramObject.getClass()).a());
        ((StringBuffer)localObject2).append(" (");
        paramObject = localLinkedList.iterator();
        if (paramObject.hasNext())
          break label306;
        ((StringBuffer)localObject2).deleteCharAt(((StringBuffer)localObject2).length() - 1);
        ((StringBuffer)localObject2).append(") VALUES ( ");
        j = localLinkedList.size();
        i = 0;
      }
    }
    while (true)
    {
      if (i >= j)
      {
        ((StringBuffer)localObject2).deleteCharAt(((StringBuffer)localObject2).length() - 1);
        ((StringBuffer)localObject2).append(")");
        ((cn.com.iresearch.mapptracker.fm.a.d.a)localObject1).a(((StringBuffer)localObject2).toString());
        return localObject1;
        cn.com.iresearch.mapptracker.fm.a.e.b localb = property2KeyValue((cn.com.iresearch.mapptracker.fm.a.e.e)((Iterator)localObject2).next(), paramObject);
        if (localb == null)
          break;
        localLinkedList.add(localb);
        break;
        label277: localObject2 = manyToOne2KeyValue((c)((Iterator)localObject1).next(), paramObject);
        if (localObject2 == null)
          break label100;
        localLinkedList.add(localObject2);
        break label100;
        label306: localb = (cn.com.iresearch.mapptracker.fm.a.e.b)paramObject.next();
        ((StringBuffer)localObject2).append(localb.a()).append(",");
        ((cn.com.iresearch.mapptracker.fm.a.d.a)localObject1).a(localb.b());
        break label172;
      }
      ((StringBuffer)localObject2).append("?,");
      i += 1;
    }
  }

  public static String getCreatTableSQL(Class<?> paramClass)
  {
    Object localObject1 = f.a(paramClass);
    Object localObject2 = ((f)localObject1).b();
    paramClass = new StringBuffer();
    paramClass.append("CREATE TABLE IF NOT EXISTS ");
    paramClass.append(((f)localObject1).a());
    paramClass.append(" ( ");
    Object localObject3 = ((cn.com.iresearch.mapptracker.fm.a.e.a)localObject2).e();
    if ((localObject3 == Integer.TYPE) || (localObject3 == Integer.class))
    {
      paramClass.append("\"").append(((cn.com.iresearch.mapptracker.fm.a.e.a)localObject2).c()).append("\"    INTEGER PRIMARY KEY AUTOINCREMENT,");
      localObject2 = ((f)localObject1).a.values().iterator();
      label91: if (((Iterator)localObject2).hasNext())
        break label167;
      localObject1 = ((f)localObject1).b.values().iterator();
    }
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        paramClass.deleteCharAt(paramClass.length() - 1);
        paramClass.append(" )");
        return paramClass.toString();
        paramClass.append("\"").append(((cn.com.iresearch.mapptracker.fm.a.e.a)localObject2).c()).append("\"    TEXT PRIMARY KEY,");
        break;
        label167: localObject3 = (cn.com.iresearch.mapptracker.fm.a.e.e)((Iterator)localObject2).next();
        paramClass.append("\"").append(((cn.com.iresearch.mapptracker.fm.a.e.e)localObject3).c());
        paramClass.append("\",");
        break label91;
      }
      localObject2 = (c)((Iterator)localObject1).next();
      paramClass.append("\"").append(((c)localObject2).c()).append("\",");
    }
  }

  private static String getDeleteSqlBytableName(String paramString)
  {
    return "DELETE FROM " + paramString;
  }

  public static <T> T getEntity(Cursor paramCursor, Class<T> paramClass)
  {
    if (paramCursor != null);
    while (true)
    {
      int j;
      int i;
      try
      {
        f localf = f.a(paramClass);
        j = paramCursor.getColumnCount();
        if (j > 0)
        {
          paramClass = paramClass.newInstance();
          i = 0;
          break label117;
          String str = paramCursor.getColumnName(i);
          cn.com.iresearch.mapptracker.fm.a.e.e locale = (cn.com.iresearch.mapptracker.fm.a.e.e)localf.a.get(str);
          if (locale != null)
            locale.a(paramClass, paramCursor.getString(i));
          else if (localf.b().c().equals(str))
            localf.b().a(paramClass, paramCursor.getString(i));
        }
      }
      catch (Exception paramCursor)
      {
        paramCursor.printStackTrace();
      }
      return null;
      label117: 
      while (i >= j)
      {
        return paramClass;
        i += 1;
      }
    }
  }

  public static List<c> getManyToOneList(Class<?> paramClass)
  {
    ArrayList localArrayList = new ArrayList();
    int j;
    int i;
    do
      try
      {
        Field[] arrayOfField = paramClass.getDeclaredFields();
        j = arrayOfField.length;
        i = 0;
        continue;
        Field localField = arrayOfField[i];
        if ((!cn.com.iresearch.mapptracker.fm.a.c.a.c(localField)) && (cn.com.iresearch.mapptracker.fm.a.c.a.d(localField)))
        {
          c localc = new c();
          localField.getType();
          c.a();
          localc.a(cn.com.iresearch.mapptracker.fm.a.c.a.a(localField));
          localField.getName();
          c.b();
          localc.a(localField.getType());
          localc.b(cn.com.iresearch.mapptracker.fm.a.c.a.b(paramClass, localField));
          localc.a(cn.com.iresearch.mapptracker.fm.a.c.a.a(paramClass, localField));
          localArrayList.add(localc);
        }
        i += 1;
      }
      catch (Exception paramClass)
      {
        throw new RuntimeException(paramClass.getMessage(), paramClass);
      }
    while (i < j);
    return localArrayList;
  }

  public static List<d> getOneToManyList(Class<?> paramClass)
  {
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      int j;
      int i;
      try
      {
        Field[] arrayOfField = paramClass.getDeclaredFields();
        j = arrayOfField.length;
        i = 0;
        break label190;
        Field localField = arrayOfField[i];
        if ((!cn.com.iresearch.mapptracker.fm.a.c.a.c(localField)) && (cn.com.iresearch.mapptracker.fm.a.c.a.e(localField)))
        {
          d locald = new d();
          locald.a(cn.com.iresearch.mapptracker.fm.a.c.a.a(localField));
          localField.getName();
          d.b();
          if ((localField.getGenericType() instanceof ParameterizedType))
          {
            if ((Class)((ParameterizedType)localField.getGenericType()).getActualTypeArguments()[0] != null)
              d.a();
            locald.a(localField.getClass());
            locald.b(cn.com.iresearch.mapptracker.fm.a.c.a.b(paramClass, localField));
            locald.a(cn.com.iresearch.mapptracker.fm.a.c.a.a(paramClass, localField));
            localArrayList.add(locald);
          }
          else
          {
            throw new cn.com.iresearch.mapptracker.fm.a.b.b("getOneToManyList Exception:" + localField.getName() + "'s type is null");
          }
        }
      }
      catch (Exception paramClass)
      {
        throw new RuntimeException(paramClass.getMessage(), paramClass);
      }
      label190: 
      while (i >= j)
      {
        return localArrayList;
        i += 1;
      }
    }
  }

  public static Field getPrimaryKeyField(Class<?> paramClass)
  {
    Field[] arrayOfField = paramClass.getDeclaredFields();
    if (arrayOfField != null)
    {
      int j = arrayOfField.length;
      int i = 0;
      Object localObject;
      if (i >= j)
      {
        localObject = null;
        label25: if (localObject == null)
        {
          j = arrayOfField.length;
          i = 0;
          label36: if (i < j)
            break label93;
        }
        paramClass = (Class<?>)localObject;
        label45: if (paramClass != null)
          break label182;
        j = arrayOfField.length;
        i = 0;
      }
      while (true)
      {
        if (i >= j)
          localObject = paramClass;
        label93: Field localField;
        do
        {
          return localObject;
          paramClass = arrayOfField[i];
          localObject = paramClass;
          if (paramClass.getAnnotation(cn.com.iresearch.mapptracker.fm.a.a.a.a.class) != null)
            break label25;
          i += 1;
          break;
          localField = arrayOfField[i];
          paramClass = localField;
          if ("_id".equals(localField.getName()))
            break label45;
          i += 1;
          break label36;
          localField = arrayOfField[i];
          localObject = localField;
        }
        while ("id".equals(localField.getName()));
        i += 1;
      }
    }
    else
    {
      throw new RuntimeException("this model[" + paramClass + "] has no field");
    }
    label182: return paramClass;
  }

  public static String getPrimaryKeyFieldName(Class<?> paramClass)
  {
    paramClass = getPrimaryKeyField(paramClass);
    if (paramClass == null)
      return null;
    return paramClass.getName();
  }

  public static List<cn.com.iresearch.mapptracker.fm.a.e.e> getPropertyList(Class<?> paramClass)
  {
    ArrayList localArrayList = new ArrayList();
    int j;
    int i;
    do
      try
      {
        Field[] arrayOfField = paramClass.getDeclaredFields();
        String str = getPrimaryKeyFieldName(paramClass);
        j = arrayOfField.length;
        i = 0;
        continue;
        Field localField = arrayOfField[i];
        if ((!cn.com.iresearch.mapptracker.fm.a.c.a.c(localField)) && (cn.com.iresearch.mapptracker.fm.a.c.a.f(localField)) && (!localField.getName().equals(str)))
        {
          cn.com.iresearch.mapptracker.fm.a.e.e locale = new cn.com.iresearch.mapptracker.fm.a.e.e();
          locale.a(cn.com.iresearch.mapptracker.fm.a.c.a.a(localField));
          localField.getName();
          cn.com.iresearch.mapptracker.fm.a.e.e.b();
          locale.a(localField.getType());
          locale.b(cn.com.iresearch.mapptracker.fm.a.c.a.b(localField));
          locale.b(cn.com.iresearch.mapptracker.fm.a.c.a.b(paramClass, localField));
          locale.a(cn.com.iresearch.mapptracker.fm.a.c.a.a(paramClass, localField));
          locale.a(localField);
          localArrayList.add(locale);
        }
        i += 1;
      }
      catch (Exception paramClass)
      {
        throw new RuntimeException(paramClass.getMessage(), paramClass);
      }
    while (i < j);
    return localArrayList;
  }

  public static List<cn.com.iresearch.mapptracker.fm.a.e.b> getSaveKeyValueListByEntity(Object paramObject)
  {
    LinkedList localLinkedList = new LinkedList();
    Object localObject1 = f.a(paramObject.getClass());
    Object localObject2 = ((f)localObject1).b().a(paramObject);
    if ((!(localObject2 instanceof Integer)) && ((localObject2 instanceof String)) && (localObject2 != null))
      localLinkedList.add(new cn.com.iresearch.mapptracker.fm.a.e.b(((f)localObject1).b().c(), localObject2));
    localObject2 = ((f)localObject1).a.values().iterator();
    if (!((Iterator)localObject2).hasNext())
      localObject1 = ((f)localObject1).b.values().iterator();
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        return localLinkedList;
        cn.com.iresearch.mapptracker.fm.a.e.b localb = property2KeyValue((cn.com.iresearch.mapptracker.fm.a.e.e)((Iterator)localObject2).next(), paramObject);
        if (localb == null)
          break;
        localLinkedList.add(localb);
        break;
      }
      localObject2 = manyToOne2KeyValue((c)((Iterator)localObject1).next(), paramObject);
      if (localObject2 != null)
        localLinkedList.add(localObject2);
    }
  }

  public static String getSelectSQL(Class<?> paramClass)
  {
    return getSelectSqlByTableName(f.a(paramClass).a());
  }

  public static String getSelectSQLByWhere(Class<?> paramClass, String paramString)
  {
    paramClass = new StringBuffer(getSelectSqlByTableName(f.a(paramClass).a()));
    if (!TextUtils.isEmpty(paramString))
      paramClass.append(" WHERE ").append(paramString);
    return paramClass.toString();
  }

  private static String getSelectSqlByTableName(String paramString)
  {
    return "SELECT * FROM " + paramString;
  }

  public static String getTableName(Class<?> paramClass)
  {
    cn.com.iresearch.mapptracker.fm.a.a.a.e locale = (cn.com.iresearch.mapptracker.fm.a.a.a.e)paramClass.getAnnotation(cn.com.iresearch.mapptracker.fm.a.a.a.e.class);
    if ((locale == null) || (locale.a().trim().length() == 0))
      return paramClass.getName().replace('.', '_');
    return locale.a();
  }

  public static cn.com.iresearch.mapptracker.fm.a.d.a getUpdateSqlAsSqlInfo(Object paramObject, String paramString)
  {
    Object localObject1 = f.a(paramObject.getClass());
    Object localObject2 = new ArrayList();
    Object localObject3 = ((f)localObject1).a.values().iterator();
    if (!((Iterator)localObject3).hasNext())
      localObject3 = ((f)localObject1).b.values().iterator();
    while (true)
    {
      if (!((Iterator)localObject3).hasNext())
      {
        if (((List)localObject2).size() != 0)
          break label173;
        throw new cn.com.iresearch.mapptracker.fm.a.b.b("this entity[" + paramObject.getClass() + "] has no property");
        localb = property2KeyValue((cn.com.iresearch.mapptracker.fm.a.e.e)((Iterator)localObject3).next(), paramObject);
        if (localb == null)
          break;
        ((List)localObject2).add(localb);
        break;
      }
      cn.com.iresearch.mapptracker.fm.a.e.b localb = manyToOne2KeyValue((c)((Iterator)localObject3).next(), paramObject);
      if (localb != null)
        ((List)localObject2).add(localb);
    }
    label173: paramObject = new cn.com.iresearch.mapptracker.fm.a.d.a();
    localObject3 = new StringBuffer("UPDATE ");
    ((StringBuffer)localObject3).append(((f)localObject1).a());
    ((StringBuffer)localObject3).append(" SET ");
    localObject1 = ((List)localObject2).iterator();
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        ((StringBuffer)localObject3).deleteCharAt(((StringBuffer)localObject3).length() - 1);
        if (!TextUtils.isEmpty(paramString))
          ((StringBuffer)localObject3).append(" WHERE ").append(paramString);
        paramObject.a(((StringBuffer)localObject3).toString());
        return paramObject;
      }
      localObject2 = (cn.com.iresearch.mapptracker.fm.a.e.b)((Iterator)localObject1).next();
      ((StringBuffer)localObject3).append(((cn.com.iresearch.mapptracker.fm.a.e.b)localObject2).a()).append("=?,");
      paramObject.a(((cn.com.iresearch.mapptracker.fm.a.e.b)localObject2).b());
    }
  }

  private static cn.com.iresearch.mapptracker.fm.a.e.b manyToOne2KeyValue(c paramc, Object paramObject)
  {
    Object localObject = null;
    String str = paramc.c();
    paramObject = paramc.a(paramObject);
    paramc = localObject;
    if (paramObject != null)
    {
      paramObject = f.a(paramObject.getClass()).b().a(paramObject);
      paramc = localObject;
      if (str != null)
      {
        paramc = localObject;
        if (paramObject != null)
          paramc = new cn.com.iresearch.mapptracker.fm.a.e.b(str, paramObject);
      }
    }
    return paramc;
  }

  private static cn.com.iresearch.mapptracker.fm.a.e.b property2KeyValue(cn.com.iresearch.mapptracker.fm.a.e.e parame, Object paramObject)
  {
    Object localObject = null;
    String str = parame.c();
    paramObject = parame.a(paramObject);
    if (paramObject != null)
      paramObject = new cn.com.iresearch.mapptracker.fm.a.e.b(str, paramObject);
    do
    {
      do
      {
        return paramObject;
        paramObject = localObject;
      }
      while (parame.d() == null);
      paramObject = localObject;
    }
    while (parame.d().trim().length() == 0);
    return new cn.com.iresearch.mapptracker.fm.a.e.b(str, parame.d());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.dao.a
 * JD-Core Version:    0.6.2
 */