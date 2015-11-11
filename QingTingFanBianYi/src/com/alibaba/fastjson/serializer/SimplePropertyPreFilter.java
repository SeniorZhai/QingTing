package com.alibaba.fastjson.serializer;

import java.util.HashSet;
import java.util.Set;

public class SimplePropertyPreFilter
  implements PropertyPreFilter
{
  private final Class<?> clazz;
  private final Set<String> excludes = new HashSet();
  private final Set<String> includes = new HashSet();

  public SimplePropertyPreFilter(Class<?> paramClass, String[] paramArrayOfString)
  {
    this.clazz = paramClass;
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      paramClass = paramArrayOfString[i];
      if (paramClass != null)
        this.includes.add(paramClass);
      i += 1;
    }
  }

  public SimplePropertyPreFilter(String[] paramArrayOfString)
  {
    this(null, paramArrayOfString);
  }

  public boolean apply(JSONSerializer paramJSONSerializer, Object paramObject, String paramString)
  {
    if (paramObject == null);
    do
    {
      do
        return true;
      while ((this.clazz != null) && (!this.clazz.isInstance(paramObject)));
      if (this.excludes.contains(paramString))
        return false;
    }
    while ((this.includes.size() == 0) || (this.includes.contains(paramString)));
    return false;
  }

  public Class<?> getClazz()
  {
    return this.clazz;
  }

  public Set<String> getExcludes()
  {
    return this.excludes;
  }

  public Set<String> getIncludes()
  {
    return this.includes;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.SimplePropertyPreFilter
 * JD-Core Version:    0.6.2
 */