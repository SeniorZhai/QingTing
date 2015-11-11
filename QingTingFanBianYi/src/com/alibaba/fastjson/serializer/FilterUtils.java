package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

public class FilterUtils
{
  public static boolean apply(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, byte paramByte)
  {
    paramJSONSerializer = paramJSONSerializer.getPropertyFiltersDirect();
    if (paramJSONSerializer != null)
    {
      boolean bool2 = true;
      paramJSONSerializer = paramJSONSerializer.iterator();
      do
      {
        bool1 = bool2;
        if (!paramJSONSerializer.hasNext())
          break;
      }
      while (((PropertyFilter)paramJSONSerializer.next()).apply(paramObject, paramString, Byte.valueOf(paramByte)));
      boolean bool1 = false;
      return bool1;
    }
    return true;
  }

  public static boolean apply(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, char paramChar)
  {
    paramJSONSerializer = paramJSONSerializer.getPropertyFiltersDirect();
    if (paramJSONSerializer != null)
    {
      boolean bool2 = true;
      paramJSONSerializer = paramJSONSerializer.iterator();
      do
      {
        bool1 = bool2;
        if (!paramJSONSerializer.hasNext())
          break;
      }
      while (((PropertyFilter)paramJSONSerializer.next()).apply(paramObject, paramString, Character.valueOf(paramChar)));
      boolean bool1 = false;
      return bool1;
    }
    return true;
  }

  public static boolean apply(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, double paramDouble)
  {
    paramJSONSerializer = paramJSONSerializer.getPropertyFiltersDirect();
    if (paramJSONSerializer != null)
    {
      boolean bool2 = true;
      paramJSONSerializer = paramJSONSerializer.iterator();
      do
      {
        bool1 = bool2;
        if (!paramJSONSerializer.hasNext())
          break;
      }
      while (((PropertyFilter)paramJSONSerializer.next()).apply(paramObject, paramString, Double.valueOf(paramDouble)));
      boolean bool1 = false;
      return bool1;
    }
    return true;
  }

  public static boolean apply(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, float paramFloat)
  {
    paramJSONSerializer = paramJSONSerializer.getPropertyFiltersDirect();
    if (paramJSONSerializer != null)
    {
      boolean bool2 = true;
      paramJSONSerializer = paramJSONSerializer.iterator();
      do
      {
        bool1 = bool2;
        if (!paramJSONSerializer.hasNext())
          break;
      }
      while (((PropertyFilter)paramJSONSerializer.next()).apply(paramObject, paramString, Float.valueOf(paramFloat)));
      boolean bool1 = false;
      return bool1;
    }
    return true;
  }

  public static boolean apply(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, int paramInt)
  {
    paramJSONSerializer = paramJSONSerializer.getPropertyFiltersDirect();
    if (paramJSONSerializer != null)
    {
      boolean bool2 = true;
      paramJSONSerializer = paramJSONSerializer.iterator();
      do
      {
        bool1 = bool2;
        if (!paramJSONSerializer.hasNext())
          break;
      }
      while (((PropertyFilter)paramJSONSerializer.next()).apply(paramObject, paramString, Integer.valueOf(paramInt)));
      boolean bool1 = false;
      return bool1;
    }
    return true;
  }

  public static boolean apply(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, long paramLong)
  {
    paramJSONSerializer = paramJSONSerializer.getPropertyFiltersDirect();
    if (paramJSONSerializer != null)
    {
      boolean bool2 = true;
      paramJSONSerializer = paramJSONSerializer.iterator();
      do
      {
        bool1 = bool2;
        if (!paramJSONSerializer.hasNext())
          break;
      }
      while (((PropertyFilter)paramJSONSerializer.next()).apply(paramObject, paramString, Long.valueOf(paramLong)));
      boolean bool1 = false;
      return bool1;
    }
    return true;
  }

  public static boolean apply(JSONSerializer paramJSONSerializer, Object paramObject1, String paramString, Object paramObject2)
  {
    paramJSONSerializer = paramJSONSerializer.getPropertyFiltersDirect();
    if (paramJSONSerializer == null);
    do
      while (!paramJSONSerializer.hasNext())
      {
        return true;
        paramJSONSerializer = paramJSONSerializer.iterator();
      }
    while (((PropertyFilter)paramJSONSerializer.next()).apply(paramObject1, paramString, paramObject2));
    return false;
  }

  public static boolean apply(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, short paramShort)
  {
    paramJSONSerializer = paramJSONSerializer.getPropertyFiltersDirect();
    if (paramJSONSerializer != null)
    {
      boolean bool2 = true;
      paramJSONSerializer = paramJSONSerializer.iterator();
      do
      {
        bool1 = bool2;
        if (!paramJSONSerializer.hasNext())
          break;
      }
      while (((PropertyFilter)paramJSONSerializer.next()).apply(paramObject, paramString, Short.valueOf(paramShort)));
      boolean bool1 = false;
      return bool1;
    }
    return true;
  }

  public static boolean applyName(JSONSerializer paramJSONSerializer, Object paramObject, String paramString)
  {
    Object localObject = paramJSONSerializer.getPropertyPreFiltersDirect();
    if (localObject == null);
    do
      while (!((Iterator)localObject).hasNext())
      {
        return true;
        localObject = ((List)localObject).iterator();
      }
    while (((PropertyPreFilter)((Iterator)localObject).next()).apply(paramJSONSerializer, paramObject, paramString));
    return false;
  }

  public static Type getExtratype(DefaultJSONParser paramDefaultJSONParser, Object paramObject, String paramString)
  {
    Object localObject = paramDefaultJSONParser.getExtraTypeProvidersDirect();
    if (localObject == null)
      paramDefaultJSONParser = null;
    while (true)
    {
      return paramDefaultJSONParser;
      paramDefaultJSONParser = null;
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
        paramDefaultJSONParser = ((ExtraTypeProvider)((Iterator)localObject).next()).getExtraType(paramObject, paramString);
    }
  }

  public static void processExtra(DefaultJSONParser paramDefaultJSONParser, Object paramObject1, String paramString, Object paramObject2)
  {
    paramDefaultJSONParser = paramDefaultJSONParser.getExtraProcessorsDirect();
    if (paramDefaultJSONParser == null);
    while (true)
    {
      return;
      paramDefaultJSONParser = paramDefaultJSONParser.iterator();
      while (paramDefaultJSONParser.hasNext())
        ((ExtraProcessor)paramDefaultJSONParser.next()).processExtra(paramObject1, paramString, paramObject2);
    }
  }

  public static String processKey(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, byte paramByte)
  {
    Object localObject = paramJSONSerializer.getNameFiltersDirect();
    paramJSONSerializer = paramString;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (true)
      {
        paramJSONSerializer = paramString;
        if (!((Iterator)localObject).hasNext())
          break;
        paramString = ((NameFilter)((Iterator)localObject).next()).process(paramObject, paramString, Byte.valueOf(paramByte));
      }
    }
    return paramJSONSerializer;
  }

  public static String processKey(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, char paramChar)
  {
    Object localObject = paramJSONSerializer.getNameFiltersDirect();
    paramJSONSerializer = paramString;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (true)
      {
        paramJSONSerializer = paramString;
        if (!((Iterator)localObject).hasNext())
          break;
        paramString = ((NameFilter)((Iterator)localObject).next()).process(paramObject, paramString, Character.valueOf(paramChar));
      }
    }
    return paramJSONSerializer;
  }

  public static String processKey(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, double paramDouble)
  {
    Object localObject = paramJSONSerializer.getNameFiltersDirect();
    paramJSONSerializer = paramString;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (true)
      {
        paramJSONSerializer = paramString;
        if (!((Iterator)localObject).hasNext())
          break;
        paramString = ((NameFilter)((Iterator)localObject).next()).process(paramObject, paramString, Double.valueOf(paramDouble));
      }
    }
    return paramJSONSerializer;
  }

  public static String processKey(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, float paramFloat)
  {
    Object localObject = paramJSONSerializer.getNameFiltersDirect();
    paramJSONSerializer = paramString;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (true)
      {
        paramJSONSerializer = paramString;
        if (!((Iterator)localObject).hasNext())
          break;
        paramString = ((NameFilter)((Iterator)localObject).next()).process(paramObject, paramString, Float.valueOf(paramFloat));
      }
    }
    return paramJSONSerializer;
  }

  public static String processKey(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, int paramInt)
  {
    Object localObject = paramJSONSerializer.getNameFiltersDirect();
    paramJSONSerializer = paramString;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (true)
      {
        paramJSONSerializer = paramString;
        if (!((Iterator)localObject).hasNext())
          break;
        paramString = ((NameFilter)((Iterator)localObject).next()).process(paramObject, paramString, Integer.valueOf(paramInt));
      }
    }
    return paramJSONSerializer;
  }

  public static String processKey(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, long paramLong)
  {
    Object localObject = paramJSONSerializer.getNameFiltersDirect();
    paramJSONSerializer = paramString;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (true)
      {
        paramJSONSerializer = paramString;
        if (!((Iterator)localObject).hasNext())
          break;
        paramString = ((NameFilter)((Iterator)localObject).next()).process(paramObject, paramString, Long.valueOf(paramLong));
      }
    }
    return paramJSONSerializer;
  }

  public static String processKey(JSONSerializer paramJSONSerializer, Object paramObject1, String paramString, Object paramObject2)
  {
    Object localObject = paramJSONSerializer.getNameFiltersDirect();
    paramJSONSerializer = paramString;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (true)
      {
        paramJSONSerializer = paramString;
        if (!((Iterator)localObject).hasNext())
          break;
        paramString = ((NameFilter)((Iterator)localObject).next()).process(paramObject1, paramString, paramObject2);
      }
    }
    return paramJSONSerializer;
  }

  public static String processKey(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, short paramShort)
  {
    Object localObject = paramJSONSerializer.getNameFiltersDirect();
    paramJSONSerializer = paramString;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (true)
      {
        paramJSONSerializer = paramString;
        if (!((Iterator)localObject).hasNext())
          break;
        paramString = ((NameFilter)((Iterator)localObject).next()).process(paramObject, paramString, Short.valueOf(paramShort));
      }
    }
    return paramJSONSerializer;
  }

  public static String processKey(JSONSerializer paramJSONSerializer, Object paramObject, String paramString, boolean paramBoolean)
  {
    Object localObject = paramJSONSerializer.getNameFiltersDirect();
    paramJSONSerializer = paramString;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (true)
      {
        paramJSONSerializer = paramString;
        if (!((Iterator)localObject).hasNext())
          break;
        paramString = ((NameFilter)((Iterator)localObject).next()).process(paramObject, paramString, Boolean.valueOf(paramBoolean));
      }
    }
    return paramJSONSerializer;
  }

  public static Object processValue(JSONSerializer paramJSONSerializer, Object paramObject1, String paramString, Object paramObject2)
  {
    Object localObject = paramJSONSerializer.getValueFiltersDirect();
    paramJSONSerializer = paramObject2;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (true)
      {
        paramJSONSerializer = paramObject2;
        if (!((Iterator)localObject).hasNext())
          break;
        paramObject2 = ((ValueFilter)((Iterator)localObject).next()).process(paramObject1, paramString, paramObject2);
      }
    }
    return paramJSONSerializer;
  }

  public static char writeAfter(JSONSerializer paramJSONSerializer, Object paramObject, char paramChar)
  {
    Object localObject = paramJSONSerializer.getAfterFiltersDirect();
    char c = paramChar;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (true)
      {
        c = paramChar;
        if (!((Iterator)localObject).hasNext())
          break;
        paramChar = ((AfterFilter)((Iterator)localObject).next()).writeAfter(paramJSONSerializer, paramObject, paramChar);
      }
    }
    return c;
  }

  public static char writeBefore(JSONSerializer paramJSONSerializer, Object paramObject, char paramChar)
  {
    Object localObject = paramJSONSerializer.getBeforeFiltersDirect();
    char c = paramChar;
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (true)
      {
        c = paramChar;
        if (!((Iterator)localObject).hasNext())
          break;
        paramChar = ((BeforeFilter)((Iterator)localObject).next()).writeBefore(paramJSONSerializer, paramObject, paramChar);
      }
    }
    return c;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.FilterUtils
 * JD-Core Version:    0.6.2
 */