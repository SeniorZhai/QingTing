package com.taobao.newxp.imagecache.utils;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class a
  implements Closeable
{
  static final String a = "journal";
  static final String b = "journal.tmp";
  static final String c = "libcore.io.DiskLruCache";
  static final String d = "1";
  static final long e = -1L;
  private static final String f = "CLEAN";
  private static final String g = "DIRTY";
  private static final String h = "REMOVE";
  private static final String i = "READ";
  private static final Charset j = Charset.forName("UTF-8");
  private static final int k = 8192;
  private final File l;
  private final File m;
  private final File n;
  private final int o;
  private final long p;
  private final int q;
  private long r = 0L;
  private Writer s;
  private final LinkedHashMap<String, b> t = new LinkedHashMap(0, 0.75F, true);
  private int u;
  private long v = 0L;
  private final ExecutorService w = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  private final Callable<Void> x = new Callable()
  {
    public Void a()
      throws Exception
    {
      synchronized (a.this)
      {
        if (a.a(a.this) == null)
          return null;
        a.b(a.this);
        if (a.c(a.this))
        {
          a.d(a.this);
          a.a(a.this, 0);
        }
        return null;
      }
    }
  };

  private a(File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    this.l = paramFile;
    this.o = paramInt1;
    this.m = new File(paramFile, "journal");
    this.n = new File(paramFile, "journal.tmp");
    this.q = paramInt2;
    this.p = paramLong;
  }

  private a a(String paramString, long paramLong)
    throws IOException
  {
    while (true)
    {
      b localb;
      try
      {
        l();
        e(paramString);
        localb = (b)this.t.get(paramString);
        if (paramLong != -1L)
          if (localb != null)
          {
            long l1 = b.e(localb);
            if (l1 == paramLong);
          }
          else
          {
            paramString = null;
            return paramString;
          }
        if (localb == null)
        {
          localb = new b(paramString, null);
          this.t.put(paramString, localb);
          locala = new a(localb, null);
          b.a(localb, locala);
          this.s.write("DIRTY " + paramString + '\n');
          this.s.flush();
          paramString = locala;
          continue;
        }
      }
      finally
      {
      }
      a locala = b.a(localb);
      if (locala != null)
        paramString = null;
    }
  }

  public static a a(File paramFile, int paramInt1, int paramInt2, long paramLong)
    throws IOException
  {
    if (paramLong <= 0L)
      throw new IllegalArgumentException("maxSize <= 0");
    if (paramInt2 <= 0)
      throw new IllegalArgumentException("valueCount <= 0");
    a locala = new a(paramFile, paramInt1, paramInt2, paramLong);
    if (locala.m.exists())
      try
      {
        locala.h();
        locala.i();
        locala.s = new BufferedWriter(new FileWriter(locala.m, true), 8192);
        return locala;
      }
      catch (IOException localIOException)
      {
        locala.f();
      }
    paramFile.mkdirs();
    paramFile = new a(paramFile, paramInt1, paramInt2, paramLong);
    paramFile.j();
    return paramFile;
  }

  public static String a(InputStream paramInputStream)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder(80);
    while (true)
    {
      int i1 = paramInputStream.read();
      if (i1 == -1)
        throw new EOFException();
      if (i1 == 10)
      {
        i1 = localStringBuilder.length();
        if ((i1 > 0) && (localStringBuilder.charAt(i1 - 1) == '\r'))
          localStringBuilder.setLength(i1 - 1);
        return localStringBuilder.toString();
      }
      localStringBuilder.append((char)i1);
    }
  }

  public static String a(Reader paramReader)
    throws IOException
  {
    try
    {
      StringWriter localStringWriter = new StringWriter();
      char[] arrayOfChar = new char[1024];
      while (true)
      {
        int i1 = paramReader.read(arrayOfChar);
        if (i1 == -1)
          break;
        localStringWriter.write(arrayOfChar, 0, i1);
      }
    }
    finally
    {
      paramReader.close();
    }
    String str = localObject.toString();
    paramReader.close();
    return str;
  }

  private void a(a parama, boolean paramBoolean)
    throws IOException
  {
    int i3 = 0;
    b localb;
    try
    {
      localb = a.a(parama);
      if (b.a(localb) != parama)
        throw new IllegalStateException();
    }
    finally
    {
    }
    int i2 = i3;
    int i1;
    if (paramBoolean)
    {
      i2 = i3;
      if (!b.d(localb))
      {
        i1 = 0;
        i2 = i3;
        if (i1 < this.q)
        {
          if (localb.b(i1).exists())
            break label386;
          parama.b();
          throw new IllegalStateException("edit didn't create file " + i1);
        }
      }
    }
    while (true)
    {
      long l1;
      if (i2 < this.q)
      {
        parama = localb.b(i2);
        if (paramBoolean)
        {
          if (parama.exists())
          {
            File localFile = localb.a(i2);
            parama.renameTo(localFile);
            l1 = b.b(localb)[i2];
            long l2 = localFile.length();
            b.b(localb)[i2] = l2;
            this.r = (this.r - l1 + l2);
          }
        }
        else
          b(parama);
      }
      else
      {
        this.u += 1;
        b.a(localb, null);
        if ((b.d(localb) | paramBoolean))
        {
          b.a(localb, true);
          this.s.write("CLEAN " + b.c(localb) + localb.a() + '\n');
          if (paramBoolean)
          {
            l1 = this.v;
            this.v = (1L + l1);
            b.a(localb, l1);
          }
        }
        while (true)
        {
          if ((this.r > this.p) || (k()))
            this.w.submit(this.x);
          return;
          this.t.remove(b.c(localb));
          this.s.write("REMOVE " + b.c(localb) + '\n');
        }
        label386: i1 += 1;
        break;
      }
      i2 += 1;
    }
  }

  public static void a(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (RuntimeException paramCloseable)
    {
      throw paramCloseable;
    }
    catch (Exception paramCloseable)
    {
    }
  }

  public static void a(File paramFile)
    throws IOException
  {
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null)
      throw new IllegalArgumentException("not a directory: " + paramFile);
    int i2 = arrayOfFile.length;
    int i1 = 0;
    while (i1 < i2)
    {
      paramFile = arrayOfFile[i1];
      if (paramFile.isDirectory())
        a(paramFile);
      if (!paramFile.delete())
        throw new IOException("failed to delete file: " + paramFile);
      i1 += 1;
    }
  }

  private static <T> T[] a(T[] paramArrayOfT, int paramInt1, int paramInt2)
  {
    int i1 = paramArrayOfT.length;
    if (paramInt1 > paramInt2)
      throw new IllegalArgumentException();
    if ((paramInt1 < 0) || (paramInt1 > i1))
      throw new ArrayIndexOutOfBoundsException();
    paramInt2 -= paramInt1;
    i1 = Math.min(paramInt2, i1 - paramInt1);
    Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), paramInt2);
    System.arraycopy(paramArrayOfT, paramInt1, arrayOfObject, 0, i1);
    return arrayOfObject;
  }

  private static void b(File paramFile)
    throws IOException
  {
    if ((paramFile.exists()) && (!paramFile.delete()))
      throw new IOException();
  }

  private static String c(InputStream paramInputStream)
    throws IOException
  {
    return a(new InputStreamReader(paramInputStream, j));
  }

  private void d(String paramString)
    throws IOException
  {
    String[] arrayOfString = paramString.split(" ");
    if (arrayOfString.length < 2)
      throw new IOException("unexpected journal line: " + paramString);
    String str = arrayOfString[1];
    if ((arrayOfString[0].equals("REMOVE")) && (arrayOfString.length == 2))
    {
      this.t.remove(str);
      return;
    }
    b localb = (b)this.t.get(str);
    if (localb == null)
    {
      localb = new b(str, null);
      this.t.put(str, localb);
    }
    while (true)
    {
      if ((arrayOfString[0].equals("CLEAN")) && (arrayOfString.length == this.q + 2))
      {
        b.a(localb, true);
        b.a(localb, null);
        b.a(localb, (String[])a(arrayOfString, 2, arrayOfString.length));
        return;
      }
      if ((arrayOfString[0].equals("DIRTY")) && (arrayOfString.length == 2))
      {
        b.a(localb, new a(localb, null));
        return;
      }
      if ((arrayOfString[0].equals("READ")) && (arrayOfString.length == 2))
        break;
      throw new IOException("unexpected journal line: " + paramString);
    }
  }

  private void e(String paramString)
  {
    if ((paramString.contains(" ")) || (paramString.contains("\n")) || (paramString.contains("\r")))
      throw new IllegalArgumentException("keys must not contain spaces or newlines: \"" + paramString + "\"");
  }

  // ERROR //
  private void h()
    throws IOException
  {
    // Byte code:
    //   0: new 457	java/io/BufferedInputStream
    //   3: dup
    //   4: new 459	java/io/FileInputStream
    //   7: dup
    //   8: aload_0
    //   9: getfield 133	com/taobao/newxp/imagecache/utils/a:m	Ljava/io/File;
    //   12: invokespecial 461	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   15: sipush 8192
    //   18: invokespecial 464	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;I)V
    //   21: astore_1
    //   22: aload_1
    //   23: invokestatic 466	com/taobao/newxp/imagecache/utils/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   26: astore_2
    //   27: aload_1
    //   28: invokestatic 466	com/taobao/newxp/imagecache/utils/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   31: astore_3
    //   32: aload_1
    //   33: invokestatic 466	com/taobao/newxp/imagecache/utils/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   36: astore 4
    //   38: aload_1
    //   39: invokestatic 466	com/taobao/newxp/imagecache/utils/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   42: astore 5
    //   44: aload_1
    //   45: invokestatic 466	com/taobao/newxp/imagecache/utils/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   48: astore 6
    //   50: ldc 26
    //   52: aload_2
    //   53: invokevirtual 433	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   56: ifeq +53 -> 109
    //   59: ldc 29
    //   61: aload_3
    //   62: invokevirtual 433	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   65: ifeq +44 -> 109
    //   68: aload_0
    //   69: getfield 126	com/taobao/newxp/imagecache/utils/a:o	I
    //   72: invokestatic 471	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   75: aload 4
    //   77: invokevirtual 433	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   80: ifeq +29 -> 109
    //   83: aload_0
    //   84: getfield 137	com/taobao/newxp/imagecache/utils/a:q	I
    //   87: invokestatic 471	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   90: aload 5
    //   92: invokevirtual 433	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   95: ifeq +14 -> 109
    //   98: ldc_w 473
    //   101: aload 6
    //   103: invokevirtual 433	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   106: ifne +76 -> 182
    //   109: new 145	java/io/IOException
    //   112: dup
    //   113: new 178	java/lang/StringBuilder
    //   116: dup
    //   117: invokespecial 179	java/lang/StringBuilder:<init>	()V
    //   120: ldc_w 475
    //   123: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: aload_2
    //   127: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: ldc_w 477
    //   133: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: aload_3
    //   137: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: ldc_w 477
    //   143: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: aload 5
    //   148: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: ldc_w 477
    //   154: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: aload 6
    //   159: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: ldc_w 479
    //   165: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: invokevirtual 192	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   171: invokespecial 370	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   174: athrow
    //   175: astore_2
    //   176: aload_1
    //   177: invokestatic 481	com/taobao/newxp/imagecache/utils/a:a	(Ljava/io/Closeable;)V
    //   180: aload_2
    //   181: athrow
    //   182: aload_0
    //   183: aload_1
    //   184: invokestatic 466	com/taobao/newxp/imagecache/utils/a:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   187: invokespecial 483	com/taobao/newxp/imagecache/utils/a:d	(Ljava/lang/String;)V
    //   190: goto -8 -> 182
    //   193: astore_2
    //   194: aload_1
    //   195: invokestatic 481	com/taobao/newxp/imagecache/utils/a:a	(Ljava/io/Closeable;)V
    //   198: return
    //
    // Exception table:
    //   from	to	target	type
    //   22	109	175	finally
    //   109	175	175	finally
    //   182	190	175	finally
    //   182	190	193	java/io/EOFException
  }

  private void i()
    throws IOException
  {
    b(this.n);
    Iterator localIterator = this.t.values().iterator();
    while (localIterator.hasNext())
    {
      b localb = (b)localIterator.next();
      int i1;
      if (b.a(localb) == null)
      {
        i1 = 0;
        while (i1 < this.q)
        {
          this.r += b.b(localb)[i1];
          i1 += 1;
        }
      }
      else
      {
        b.a(localb, null);
        i1 = 0;
        while (i1 < this.q)
        {
          b(localb.a(i1));
          b(localb.b(i1));
          i1 += 1;
        }
        localIterator.remove();
      }
    }
  }

  private void j()
    throws IOException
  {
    while (true)
    {
      b localb;
      try
      {
        if (this.s != null)
          this.s.close();
        BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(this.n), 8192);
        localBufferedWriter.write("libcore.io.DiskLruCache");
        localBufferedWriter.write("\n");
        localBufferedWriter.write("1");
        localBufferedWriter.write("\n");
        localBufferedWriter.write(Integer.toString(this.o));
        localBufferedWriter.write("\n");
        localBufferedWriter.write(Integer.toString(this.q));
        localBufferedWriter.write("\n");
        localBufferedWriter.write("\n");
        Iterator localIterator = this.t.values().iterator();
        if (!localIterator.hasNext())
          break;
        localb = (b)localIterator.next();
        if (b.a(localb) != null)
        {
          localBufferedWriter.write("DIRTY " + b.c(localb) + '\n');
          continue;
        }
      }
      finally
      {
      }
      localObject.write("CLEAN " + b.c(localb) + localb.a() + '\n');
    }
    localObject.close();
    this.n.renameTo(this.m);
    this.s = new BufferedWriter(new FileWriter(this.m, true), 8192);
  }

  private boolean k()
  {
    return (this.u >= 2000) && (this.u >= this.t.size());
  }

  private void l()
  {
    if (this.s == null)
      throw new IllegalStateException("cache is closed");
  }

  private void m()
    throws IOException
  {
    while (this.r > this.p)
      c((String)((Map.Entry)this.t.entrySet().iterator().next()).getKey());
  }

  public c a(String paramString)
    throws IOException
  {
    try
    {
      l();
      e(paramString);
      b localb = (b)this.t.get(paramString);
      if (localb == null)
        paramString = null;
      while (true)
      {
        return paramString;
        if (!b.d(localb))
        {
          paramString = null;
        }
        else
        {
          InputStream[] arrayOfInputStream = new InputStream[this.q];
          int i1 = 0;
          try
          {
            while (i1 < this.q)
            {
              arrayOfInputStream[i1] = new FileInputStream(localb.a(i1));
              i1 += 1;
            }
          }
          catch (FileNotFoundException paramString)
          {
            paramString = null;
          }
          continue;
          this.u += 1;
          this.s.append("READ " + paramString + '\n');
          if (k())
            this.w.submit(this.x);
          paramString = new c(paramString, b.e(localb), arrayOfInputStream, null);
        }
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public File a()
  {
    return this.l;
  }

  public long b()
  {
    return this.p;
  }

  public a b(String paramString)
    throws IOException
  {
    return a(paramString, -1L);
  }

  public long c()
  {
    try
    {
      long l1 = this.r;
      return l1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean c(String paramString)
    throws IOException
  {
    int i1 = 0;
    while (true)
    {
      try
      {
        l();
        e(paramString);
        b localb = (b)this.t.get(paramString);
        Object localObject;
        if (localb != null)
        {
          localObject = b.a(localb);
          if (localObject == null);
        }
        else
        {
          bool = false;
          return bool;
          this.r -= b.b(localb)[i1];
          b.b(localb)[i1] = 0L;
          i1 += 1;
        }
        if (i1 < this.q)
        {
          localObject = localb.a(i1);
          if (((File)localObject).delete())
            continue;
          throw new IOException("failed to delete " + localObject);
        }
      }
      finally
      {
      }
      this.u += 1;
      this.s.append("REMOVE " + paramString + '\n');
      this.t.remove(paramString);
      if (k())
        this.w.submit(this.x);
      boolean bool = true;
    }
  }

  public void close()
    throws IOException
  {
    while (true)
    {
      try
      {
        Object localObject1 = this.s;
        if (localObject1 == null)
          return;
        localObject1 = new ArrayList(this.t.values()).iterator();
        if (((Iterator)localObject1).hasNext())
        {
          b localb = (b)((Iterator)localObject1).next();
          if (b.a(localb) == null)
            continue;
          b.a(localb).b();
          continue;
        }
      }
      finally
      {
      }
      m();
      this.s.close();
      this.s = null;
    }
  }

  public boolean d()
  {
    return this.s == null;
  }

  public void e()
    throws IOException
  {
    try
    {
      l();
      m();
      this.s.flush();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void f()
    throws IOException
  {
    close();
    a(this.l);
  }

  public final class a
  {
    private final a.b b;
    private boolean c;

    private a(a.b arg2)
    {
      Object localObject;
      this.b = localObject;
    }

    public InputStream a(int paramInt)
      throws IOException
    {
      synchronized (a.this)
      {
        if (a.b.a(this.b) != this)
          throw new IllegalStateException();
      }
      if (!a.b.d(this.b))
        return null;
      FileInputStream localFileInputStream = new FileInputStream(this.b.a(paramInt));
      return localFileInputStream;
    }

    public void a()
      throws IOException
    {
      if (this.c)
      {
        a.a(a.this, this, false);
        a.this.c(a.b.c(this.b));
        return;
      }
      a.a(a.this, this, true);
    }

    // ERROR //
    public void a(int paramInt, String paramString)
      throws IOException
    {
      // Byte code:
      //   0: new 67	java/io/OutputStreamWriter
      //   3: dup
      //   4: aload_0
      //   5: iload_1
      //   6: invokevirtual 70	com/taobao/newxp/imagecache/utils/a$a:c	(I)Ljava/io/OutputStream;
      //   9: invokestatic 74	com/taobao/newxp/imagecache/utils/a:g	()Ljava/nio/charset/Charset;
      //   12: invokespecial 77	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/nio/charset/Charset;)V
      //   15: astore_3
      //   16: aload_3
      //   17: aload_2
      //   18: invokevirtual 83	java/io/Writer:write	(Ljava/lang/String;)V
      //   21: aload_3
      //   22: invokestatic 86	com/taobao/newxp/imagecache/utils/a:a	(Ljava/io/Closeable;)V
      //   25: return
      //   26: astore_2
      //   27: aconst_null
      //   28: astore_3
      //   29: aload_3
      //   30: invokestatic 86	com/taobao/newxp/imagecache/utils/a:a	(Ljava/io/Closeable;)V
      //   33: aload_2
      //   34: athrow
      //   35: astore_2
      //   36: goto -7 -> 29
      //
      // Exception table:
      //   from	to	target	type
      //   0	16	26	finally
      //   16	21	35	finally
    }

    public String b(int paramInt)
      throws IOException
    {
      InputStream localInputStream = a(paramInt);
      if (localInputStream != null)
        return a.b(localInputStream);
      return null;
    }

    public void b()
      throws IOException
    {
      a.a(a.this, this, false);
    }

    public OutputStream c(int paramInt)
      throws IOException
    {
      synchronized (a.this)
      {
        if (a.b.a(this.b) != this)
          throw new IllegalStateException();
      }
      a locala1 = new a(new FileOutputStream(this.b.b(paramInt)), null);
      return locala1;
    }

    private class a extends FilterOutputStream
    {
      private a(OutputStream arg2)
      {
        super();
      }

      public void close()
      {
        try
        {
          this.out.close();
          return;
        }
        catch (IOException localIOException)
        {
          a.a.a(a.a.this, true);
        }
      }

      public void flush()
      {
        try
        {
          this.out.flush();
          return;
        }
        catch (IOException localIOException)
        {
          a.a.a(a.a.this, true);
        }
      }

      public void write(int paramInt)
      {
        try
        {
          this.out.write(paramInt);
          return;
        }
        catch (IOException localIOException)
        {
          a.a.a(a.a.this, true);
        }
      }

      public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      {
        try
        {
          this.out.write(paramArrayOfByte, paramInt1, paramInt2);
          return;
        }
        catch (IOException paramArrayOfByte)
        {
          a.a.a(a.a.this, true);
        }
      }
    }
  }

  private final class b
  {
    private final String b;
    private final long[] c;
    private boolean d;
    private a.a e;
    private long f;

    private b(String arg2)
    {
      Object localObject;
      this.b = localObject;
      this.c = new long[a.e(a.this)];
    }

    private void a(String[] paramArrayOfString)
      throws IOException
    {
      if (paramArrayOfString.length != a.e(a.this))
        throw b(paramArrayOfString);
      int i = 0;
      try
      {
        while (i < paramArrayOfString.length)
        {
          this.c[i] = Long.parseLong(paramArrayOfString[i]);
          i += 1;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw b(paramArrayOfString);
      }
    }

    private IOException b(String[] paramArrayOfString)
      throws IOException
    {
      throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
    }

    public File a(int paramInt)
    {
      return new File(a.f(a.this), this.b + "." + paramInt);
    }

    public String a()
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      long[] arrayOfLong = this.c;
      int j = arrayOfLong.length;
      int i = 0;
      while (i < j)
      {
        long l = arrayOfLong[i];
        localStringBuilder.append(' ').append(l);
        i += 1;
      }
      return localStringBuilder.toString();
    }

    public File b(int paramInt)
    {
      return new File(a.f(a.this), this.b + "." + paramInt + ".tmp");
    }
  }

  public final class c
    implements Closeable
  {
    private final String b;
    private final long c;
    private final InputStream[] d;

    private c(String paramLong, long arg3, InputStream[] arg5)
    {
      this.b = paramLong;
      this.c = ???;
      Object localObject;
      this.d = localObject;
    }

    public a.a a()
      throws IOException
    {
      return a.a(a.this, this.b, this.c);
    }

    public InputStream a(int paramInt)
    {
      return this.d[paramInt];
    }

    public String b(int paramInt)
      throws IOException
    {
      return a.b(a(paramInt));
    }

    public void close()
    {
      InputStream[] arrayOfInputStream = this.d;
      int j = arrayOfInputStream.length;
      int i = 0;
      while (i < j)
      {
        a.a(arrayOfInputStream[i]);
        i += 1;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.imagecache.utils.a
 * JD-Core Version:    0.6.2
 */