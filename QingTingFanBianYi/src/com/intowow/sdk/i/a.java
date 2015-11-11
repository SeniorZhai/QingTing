package com.intowow.sdk.i;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;

public class a
{
  private static Set<Integer> a = new HashSet();
  private ConcurrentLinkedQueue<a> b = new ConcurrentLinkedQueue();
  private LruCache<String, Bitmap> c = new LruCache((int)Runtime.getRuntime().maxMemory() / 8)
  {
    protected int a(String paramAnonymousString, Bitmap paramAnonymousBitmap)
    {
      return paramAnonymousBitmap.getRowBytes() * paramAnonymousBitmap.getHeight();
    }
  };
  private ExecutorService d = null;

  public static void a()
  {
    a.clear();
  }

  public Bitmap a(String paramString)
  {
    Bitmap localBitmap = (Bitmap)this.c.get(paramString);
    if (localBitmap != null)
    {
      paramString = localBitmap;
      if (!localBitmap.isRecycled());
    }
    else
    {
      paramString = null;
    }
    return paramString;
  }

  public void a(a parama)
  {
    try
    {
      Bitmap localBitmap = a(parama.b());
      if (localBitmap != null)
      {
        parama.a(localBitmap);
        return;
      }
      parama.a();
      this.b.add(parama);
      this.d.execute(new b());
      return;
    }
    finally
    {
    }
    throw parama;
  }

  public static abstract interface a
  {
    public abstract void a();

    public abstract void a(int paramInt, String paramString);

    public abstract void a(Bitmap paramBitmap);

    public abstract String b();

    public abstract String c();
  }

  class b
    implements Runnable
  {
    b()
    {
    }

    public void run()
    {
      while (true)
        try
        {
          a.a locala = (a.a)a.a(a.this).poll();
          if (locala == null)
            return;
          String str = locala.b();
          Bitmap localBitmap = a.this.a(str);
          Object localObject = localBitmap;
          if (localBitmap == null)
          {
            localBitmap = BitmapFactory.decodeFile(locala.c());
            localObject = localBitmap;
            if (localBitmap != null)
            {
              a.b(a.this).put(str, localBitmap);
              localObject = localBitmap;
            }
          }
          if (localObject != null)
          {
            locala.a((Bitmap)localObject);
          }
          else
          {
            localObject = new File(locala.c());
            if ((!((File)localObject).exists()) || (!((File)localObject).isFile()))
            {
              int i = Integer.parseInt(str.split("_")[0]);
              if (!a.b().contains(Integer.valueOf(i)))
              {
                a.b().add(Integer.valueOf(i));
                locala.a(i, ((File)localObject).getName());
              }
            }
          }
        }
        catch (Exception localException)
        {
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
        }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.a
 * JD-Core Version:    0.6.2
 */