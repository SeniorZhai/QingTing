package com.alibaba.fastjson.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Set<Ljava.lang.String;>;

public class ServiceLoader
{
  private static final String PREFIX = "META-INF/services/";
  private static final Set<String> loadedUrls = new HashSet();

  public static <T> Set<T> load(Class<T> paramClass, ClassLoader paramClassLoader)
  {
    if (paramClassLoader == null)
    {
      paramClass = Collections.emptySet();
      return paramClass;
    }
    HashSet localHashSet = new HashSet();
    paramClass = paramClass.getName();
    Object localObject = "META-INF/services/" + paramClass;
    paramClass = new HashSet();
    Iterator localIterator;
    try
    {
      localObject = paramClassLoader.getResources((String)localObject);
      while (((Enumeration)localObject).hasMoreElements())
      {
        URL localURL = (URL)((Enumeration)localObject).nextElement();
        if (!loadedUrls.contains(localURL.toString()))
        {
          load(localURL, paramClass);
          loadedUrls.add(localURL.toString());
        }
      }
    }
    catch (IOException localIOException)
    {
      localIterator = paramClass.iterator();
    }
    while (true)
    {
      paramClass = localHashSet;
      if (!localIterator.hasNext())
        break;
      paramClass = (String)localIterator.next();
      try
      {
        localHashSet.add(paramClassLoader.loadClass(paramClass).newInstance());
      }
      catch (Exception paramClass)
      {
      }
    }
  }

  public static void load(URL paramURL, Set<String> paramSet)
    throws IOException
  {
    Object localObject1 = null;
    Object localObject4 = null;
    try
    {
      paramURL = paramURL.openStream();
      localObject1 = paramURL;
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramURL, "utf-8"));
      try
      {
        while (true)
        {
          localObject1 = localBufferedReader.readLine();
          if (localObject1 == null)
          {
            IOUtils.close(localBufferedReader);
            IOUtils.close(paramURL);
            return;
          }
          int i = ((String)localObject1).indexOf('#');
          localObject4 = localObject1;
          if (i >= 0)
            localObject4 = ((String)localObject1).substring(0, i);
          localObject1 = ((String)localObject4).trim();
          if (((String)localObject1).length() != 0)
            paramSet.add(localObject1);
        }
      }
      finally
      {
        paramSet = localBufferedReader;
      }
      IOUtils.close(paramSet);
      IOUtils.close(paramURL);
      throw localObject2;
    }
    finally
    {
      while (true)
      {
        paramURL = localObject2;
        paramSet = (Set<String>)localObject4;
        Object localObject3 = localObject5;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.util.ServiceLoader
 * JD-Core Version:    0.6.2
 */