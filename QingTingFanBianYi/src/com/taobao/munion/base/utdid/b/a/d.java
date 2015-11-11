package com.taobao.munion.base.utdid.b.a;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

public class d
{
  public static final int a = 2;
  public static final int b = 1;
  public static final int c = 0;
  private static final Object f = new Object();
  private final Object d = new Object();
  private File e;
  private HashMap<File, a> g = new HashMap();

  public d(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      this.e = new File(paramString);
      return;
    }
    throw new RuntimeException("Directory can not be empty");
  }

  private File a(File paramFile, String paramString)
  {
    if (paramString.indexOf(File.separatorChar) < 0)
      return new File(paramFile, paramString);
    throw new IllegalArgumentException("File " + paramString + " contains a path separator");
  }

  private File a(String paramString)
  {
    return a(b(), paramString + ".xml");
  }

  private File b()
  {
    synchronized (this.d)
    {
      File localFile = this.e;
      return localFile;
    }
  }

  private static File b(File paramFile)
  {
    return new File(paramFile.getPath() + ".bak");
  }

  public b a(String arg1, int paramInt)
  {
    File localFile = a(???);
    a locala;
    Object localObject6;
    Object localObject7;
    Object localObject8;
    Object localObject1;
    Object localObject4;
    Object localObject5;
    synchronized (f)
    {
      locala = (a)this.g.get(localFile);
      if ((locala != null) && (!locala.d()))
        return locala;
      ??? = b(localFile);
      if (???.exists())
      {
        localFile.delete();
        ???.renameTo(localFile);
      }
      if ((localFile.exists()) && (!localFile.canRead()));
      localObject6 = null;
      localObject7 = null;
      localObject8 = null;
      localObject1 = null;
      ??? = (String)localObject1;
      if (localFile.exists())
      {
        ??? = (String)localObject1;
        if (localFile.canRead())
        {
          localObject1 = localObject6;
          localObject4 = localObject7;
          localObject5 = localObject8;
        }
      }
    }
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(localFile);
      localObject1 = localObject6;
      localObject4 = localObject7;
      localObject5 = localObject8;
      ??? = e.a(localFileInputStream);
      localObject1 = ???;
      localObject4 = ???;
      localObject5 = ???;
      localFileInputStream.close();
      localObject5 = f;
      if (locala != null)
      {
        try
        {
          locala.a(???);
          localObject1 = locala;
          return localObject1;
        }
        finally
        {
        }
        localObject2 = finally;
        throw localObject2;
      }
    }
    catch (XmlPullParserException )
    {
      while (true)
      {
        try
        {
          ??? = new FileInputStream(localFile);
          localObject4 = new byte[???.available()];
          ???.read((byte[])localObject4);
          new String((byte[])localObject4, 0, localObject4.length, "UTF-8");
          ??? = localObject2;
        }
        catch (FileNotFoundException )
        {
          ???.printStackTrace();
          ??? = localObject2;
        }
        catch (IOException )
        {
          ???.printStackTrace();
          ??? = localObject2;
        }
        continue;
        localObject4 = (a)this.g.get(localFile);
        Object localObject3 = localObject4;
        if (localObject4 == null)
        {
          localObject3 = new a(localFile, paramInt, ???);
          this.g.put(localFile, localObject3);
        }
      }
    }
    catch (IOException )
    {
      while (true)
        ??? = (String)localObject4;
    }
    catch (FileNotFoundException )
    {
      while (true)
        ??? = (String)localObject5;
    }
  }

  private static final class a
    implements b
  {
    private static final Object f = new Object();
    private final File a;
    private final File b;
    private final int c;
    private Map d;
    private boolean e = false;
    private WeakHashMap<b.b, Object> g;

    a(File paramFile, int paramInt, Map paramMap)
    {
      this.a = paramFile;
      this.b = d.a(paramFile);
      this.c = paramInt;
      if (paramMap != null);
      while (true)
      {
        this.d = paramMap;
        this.g = new WeakHashMap();
        return;
        paramMap = new HashMap();
      }
    }

    private FileOutputStream a(File paramFile)
    {
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
        return localFileOutputStream;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        if (!paramFile.getParentFile().mkdir())
          return null;
        try
        {
          paramFile = new FileOutputStream(paramFile);
          return paramFile;
        }
        catch (FileNotFoundException paramFile)
        {
        }
      }
      return null;
    }

    private boolean e()
    {
      if (this.a.exists())
        if (!this.b.exists())
          if (this.a.renameTo(this.b))
            break label44;
      while (true)
      {
        return false;
        this.a.delete();
        try
        {
          label44: FileOutputStream localFileOutputStream = a(this.a);
          if (localFileOutputStream == null)
            continue;
          e.a(this.d, localFileOutputStream);
          localFileOutputStream.close();
          this.b.delete();
          return true;
        }
        catch (IOException localIOException)
        {
          if ((!this.a.exists()) || (this.a.delete()))
            continue;
          return false;
        }
        catch (XmlPullParserException localXmlPullParserException)
        {
          label80: break label80;
        }
      }
    }

    public float a(String paramString, float paramFloat)
    {
      try
      {
        paramString = (Float)this.d.get(paramString);
        if (paramString != null)
          paramFloat = paramString.floatValue();
        return paramFloat;
      }
      finally
      {
      }
      throw paramString;
    }

    public int a(String paramString, int paramInt)
    {
      try
      {
        paramString = (Integer)this.d.get(paramString);
        if (paramString != null)
          paramInt = paramString.intValue();
        return paramInt;
      }
      finally
      {
      }
      throw paramString;
    }

    public long a(String paramString, long paramLong)
    {
      try
      {
        paramString = (Long)this.d.get(paramString);
        if (paramString != null)
          paramLong = paramString.longValue();
        return paramLong;
      }
      finally
      {
      }
      throw paramString;
    }

    public String a(String paramString1, String paramString2)
    {
      while (true)
      {
        try
        {
          paramString1 = (String)this.d.get(paramString1);
          if (paramString1 != null)
            return paramString1;
        }
        finally
        {
        }
        paramString1 = paramString2;
      }
    }

    public void a(b.b paramb)
    {
      try
      {
        this.g.put(paramb, f);
        return;
      }
      finally
      {
      }
      throw paramb;
    }

    public void a(Map paramMap)
    {
      if (paramMap != null)
        try
        {
          this.d = paramMap;
          return;
        }
        finally
        {
        }
    }

    public void a(boolean paramBoolean)
    {
      try
      {
        this.e = paramBoolean;
        return;
      }
      finally
      {
      }
    }

    public boolean a()
    {
      return (this.a != null) && (new File(this.a.getAbsolutePath()).exists());
    }

    public boolean a(String paramString)
    {
      try
      {
        boolean bool = this.d.containsKey(paramString);
        return bool;
      }
      finally
      {
      }
      throw paramString;
    }

    public boolean a(String paramString, boolean paramBoolean)
    {
      try
      {
        paramString = (Boolean)this.d.get(paramString);
        if (paramString != null)
          paramBoolean = paramString.booleanValue();
        return paramBoolean;
      }
      finally
      {
      }
      throw paramString;
    }

    public Map<String, ?> b()
    {
      try
      {
        HashMap localHashMap = new HashMap(this.d);
        return localHashMap;
      }
      finally
      {
      }
    }

    public void b(b.b paramb)
    {
      try
      {
        this.g.remove(paramb);
        return;
      }
      finally
      {
      }
      throw paramb;
    }

    public b.a c()
    {
      return new a();
    }

    public boolean d()
    {
      try
      {
        boolean bool = this.e;
        return bool;
      }
      finally
      {
      }
    }

    public final class a
      implements b.a
    {
      private final Map<String, Object> b = new HashMap();
      private boolean c = false;

      public a()
      {
      }

      public b.a a()
      {
        try
        {
          this.c = true;
          return this;
        }
        finally
        {
        }
      }

      public b.a a(String paramString)
      {
        try
        {
          this.b.put(paramString, this);
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public b.a a(String paramString, float paramFloat)
      {
        try
        {
          this.b.put(paramString, Float.valueOf(paramFloat));
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public b.a a(String paramString, int paramInt)
      {
        try
        {
          this.b.put(paramString, Integer.valueOf(paramInt));
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public b.a a(String paramString, long paramLong)
      {
        try
        {
          this.b.put(paramString, Long.valueOf(paramLong));
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public b.a a(String paramString1, String paramString2)
      {
        try
        {
          this.b.put(paramString1, paramString2);
          return this;
        }
        finally
        {
        }
        throw paramString1;
      }

      public b.a a(String paramString, boolean paramBoolean)
      {
        try
        {
          this.b.put(paramString, Boolean.valueOf(paramBoolean));
          return this;
        }
        finally
        {
        }
        throw paramString;
      }

      public boolean b()
      {
        int i = 0;
        while (true)
        {
          Iterator localIterator;
          Object localObject6;
          Object localObject5;
          synchronized (d.a())
          {
            if (d.a.a(d.a.this).size() > 0)
              i = 1;
            if (i == 0)
              break label338;
            localArrayList = new ArrayList();
            HashSet localHashSet = new HashSet(d.a.a(d.a.this).keySet());
            try
            {
              if (this.c)
              {
                d.a.b(d.a.this).clear();
                this.c = false;
              }
              localIterator = this.b.entrySet().iterator();
              if (!localIterator.hasNext())
                break label209;
              localObject6 = (Map.Entry)localIterator.next();
              localObject5 = (String)((Map.Entry)localObject6).getKey();
              localObject6 = ((Map.Entry)localObject6).getValue();
              if (localObject6 == this)
              {
                d.a.b(d.a.this).remove(localObject5);
                if (i == 0)
                  continue;
                localArrayList.add(localObject5);
                continue;
              }
            }
            finally
            {
            }
          }
          d.a.b(d.a.this).put(localObject5, localObject6);
          continue;
          label209: this.b.clear();
          boolean bool = d.a.c(d.a.this);
          if (bool)
            d.a.this.a(true);
          if (i != 0)
          {
            i = localArrayList.size() - 1;
            while (i >= 0)
            {
              ??? = (String)localArrayList.get(i);
              localIterator = localObject2.iterator();
              while (localIterator.hasNext())
              {
                localObject5 = (b.b)localIterator.next();
                if (localObject5 != null)
                  ((b.b)localObject5).a(d.a.this, (String)???);
              }
              i -= 1;
            }
          }
          return bool;
          label338: Object localObject3 = null;
          ArrayList localArrayList = null;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.utdid.b.a.d
 * JD-Core Version:    0.6.2
 */