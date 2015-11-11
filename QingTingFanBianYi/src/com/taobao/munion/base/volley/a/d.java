package com.taobao.munion.base.volley.a;

import android.os.SystemClock;
import com.taobao.munion.base.Log;
import com.taobao.munion.base.volley.b;
import com.taobao.munion.base.volley.b.a;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class d
  implements b
{
  private static final int e = 5242880;
  private static final float f = 0.9F;
  private static final int g = 538051844;
  private final Map<String, a> a = new LinkedHashMap(16, 0.75F, true);
  private long b = 0L;
  private final File c;
  private final int d;

  public d(File paramFile)
  {
    this(paramFile, 5242880);
  }

  public d(File paramFile, int paramInt)
  {
    this.c = paramFile;
    this.d = paramInt;
  }

  static int a(InputStream paramInputStream)
    throws IOException
  {
    return 0x0 | e(paramInputStream) << 0 | e(paramInputStream) << 8 | e(paramInputStream) << 16 | e(paramInputStream) << 24;
  }

  private void a(int paramInt)
  {
    if (this.b + paramInt < this.d);
    label226: label230: 
    while (true)
    {
      return;
      if (Log.DEBUG)
        Log.v("Pruning old cache entries.", new Object[0]);
      long l1 = this.b;
      long l2 = SystemClock.elapsedRealtime();
      Iterator localIterator = this.a.entrySet().iterator();
      int i = 0;
      a locala;
      if (localIterator.hasNext())
      {
        locala = (a)((Map.Entry)localIterator.next()).getValue();
        if (c(locala.b).delete())
        {
          this.b -= locala.a;
          label115: localIterator.remove();
          i += 1;
          if ((float)(this.b + paramInt) >= this.d * 0.9F)
            break label226;
        }
      }
      while (true)
      {
        if (!Log.DEBUG)
          break label230;
        Log.v("pruned %d files, %d bytes, %d ms", new Object[] { Integer.valueOf(i), Long.valueOf(this.b - l1), Long.valueOf(SystemClock.elapsedRealtime() - l2) });
        return;
        Log.d("Could not delete cache entry for key=%s, filename=%s", new Object[] { locala.b, d(locala.b) });
        break label115;
        break;
      }
    }
  }

  static void a(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    paramOutputStream.write(paramInt >> 0 & 0xFF);
    paramOutputStream.write(paramInt >> 8 & 0xFF);
    paramOutputStream.write(paramInt >> 16 & 0xFF);
    paramOutputStream.write(paramInt >> 24 & 0xFF);
  }

  static void a(OutputStream paramOutputStream, long paramLong)
    throws IOException
  {
    paramOutputStream.write((byte)(int)(paramLong >>> 0));
    paramOutputStream.write((byte)(int)(paramLong >>> 8));
    paramOutputStream.write((byte)(int)(paramLong >>> 16));
    paramOutputStream.write((byte)(int)(paramLong >>> 24));
    paramOutputStream.write((byte)(int)(paramLong >>> 32));
    paramOutputStream.write((byte)(int)(paramLong >>> 40));
    paramOutputStream.write((byte)(int)(paramLong >>> 48));
    paramOutputStream.write((byte)(int)(paramLong >>> 56));
  }

  static void a(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    paramString = paramString.getBytes("UTF-8");
    a(paramOutputStream, paramString.length);
    paramOutputStream.write(paramString, 0, paramString.length);
  }

  private void a(String paramString, a parama)
  {
    if (!this.a.containsKey(paramString));
    a locala;
    for (this.b += parama.a; ; this.b += parama.a - locala.a)
    {
      this.a.put(paramString, parama);
      return;
      locala = (a)this.a.get(paramString);
    }
  }

  static void a(Map<String, String> paramMap, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramMap != null)
    {
      a(paramOutputStream, paramMap.size());
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        a(paramOutputStream, (String)localEntry.getKey());
        a(paramOutputStream, (String)localEntry.getValue());
      }
    }
    a(paramOutputStream, 0);
  }

  private static byte[] a(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    while (i < paramInt)
    {
      int j = paramInputStream.read(arrayOfByte, i, paramInt - i);
      if (j == -1)
        break;
      i += j;
    }
    if (i != paramInt)
      throw new IOException("Expected " + paramInt + " bytes, read " + i + " bytes");
    return arrayOfByte;
  }

  static long b(InputStream paramInputStream)
    throws IOException
  {
    return 0L | (e(paramInputStream) & 0xFF) << 0 | (e(paramInputStream) & 0xFF) << 8 | (e(paramInputStream) & 0xFF) << 16 | (e(paramInputStream) & 0xFF) << 24 | (e(paramInputStream) & 0xFF) << 32 | (e(paramInputStream) & 0xFF) << 40 | (e(paramInputStream) & 0xFF) << 48 | (e(paramInputStream) & 0xFF) << 56;
  }

  static String c(InputStream paramInputStream)
    throws IOException
  {
    return new String(a(paramInputStream, (int)b(paramInputStream)), "UTF-8");
  }

  private String d(String paramString)
  {
    int i = paramString.length() / 2;
    int j = paramString.substring(0, i).hashCode();
    return String.valueOf(j) + String.valueOf(paramString.substring(i).hashCode());
  }

  static Map<String, String> d(InputStream paramInputStream)
    throws IOException
  {
    int j = a(paramInputStream);
    if (j == 0);
    for (Object localObject = Collections.emptyMap(); ; localObject = new HashMap(j))
    {
      int i = 0;
      while (i < j)
      {
        ((Map)localObject).put(c(paramInputStream).intern(), c(paramInputStream).intern());
        i += 1;
      }
    }
    return localObject;
  }

  private static int e(InputStream paramInputStream)
    throws IOException
  {
    int i = paramInputStream.read();
    if (i == -1)
      throw new EOFException();
    return i;
  }

  private void e(String paramString)
  {
    a locala = (a)this.a.get(paramString);
    if (locala != null)
    {
      this.b -= locala.a;
      this.a.remove(paramString);
    }
  }

  // ERROR //
  public b.a a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 45	com/taobao/munion/base/volley/a/d:a	Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface 173 2 0
    //   12: checkcast 10	com/taobao/munion/base/volley/a/d$a
    //   15: astore 4
    //   17: aload 4
    //   19: ifnonnull +9 -> 28
    //   22: aconst_null
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: areturn
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual 109	com/taobao/munion/base/volley/a/d:c	(Ljava/lang/String;)Ljava/io/File;
    //   33: astore 5
    //   35: new 13	com/taobao/munion/base/volley/a/d$b
    //   38: dup
    //   39: new 272	java/io/FileInputStream
    //   42: dup
    //   43: aload 5
    //   45: invokespecial 274	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   48: aconst_null
    //   49: invokespecial 277	com/taobao/munion/base/volley/a/d$b:<init>	(Ljava/io/InputStream;Lcom/taobao/munion/base/volley/a/d$1;)V
    //   52: astore_3
    //   53: aload_3
    //   54: astore_2
    //   55: aload_3
    //   56: invokestatic 280	com/taobao/munion/base/volley/a/d$a:a	(Ljava/io/InputStream;)Lcom/taobao/munion/base/volley/a/d$a;
    //   59: pop
    //   60: aload_3
    //   61: astore_2
    //   62: aload 4
    //   64: aload_3
    //   65: aload 5
    //   67: invokevirtual 282	java/io/File:length	()J
    //   70: aload_3
    //   71: invokestatic 285	com/taobao/munion/base/volley/a/d$b:a	(Lcom/taobao/munion/base/volley/a/d$b;)I
    //   74: i2l
    //   75: lsub
    //   76: l2i
    //   77: invokestatic 225	com/taobao/munion/base/volley/a/d:a	(Ljava/io/InputStream;I)[B
    //   80: invokevirtual 288	com/taobao/munion/base/volley/a/d$a:a	([B)Lcom/taobao/munion/base/volley/b$a;
    //   83: astore 4
    //   85: aload 4
    //   87: astore_2
    //   88: aload_2
    //   89: astore_1
    //   90: aload_3
    //   91: ifnull -67 -> 24
    //   94: aload_3
    //   95: invokevirtual 291	com/taobao/munion/base/volley/a/d$b:close	()V
    //   98: aload_2
    //   99: astore_1
    //   100: goto -76 -> 24
    //   103: astore_1
    //   104: aconst_null
    //   105: astore_1
    //   106: goto -82 -> 24
    //   109: astore 4
    //   111: aconst_null
    //   112: astore_3
    //   113: aload_3
    //   114: astore_2
    //   115: ldc_w 293
    //   118: iconst_2
    //   119: anewarray 4	java/lang/Object
    //   122: dup
    //   123: iconst_0
    //   124: aload 5
    //   126: invokevirtual 296	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   129: aastore
    //   130: dup
    //   131: iconst_1
    //   132: aload 4
    //   134: invokevirtual 297	java/io/IOException:toString	()Ljava/lang/String;
    //   137: aastore
    //   138: invokestatic 139	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   141: aload_3
    //   142: astore_2
    //   143: aload_0
    //   144: aload_1
    //   145: invokevirtual 299	com/taobao/munion/base/volley/a/d:b	(Ljava/lang/String;)V
    //   148: aload_3
    //   149: ifnull +7 -> 156
    //   152: aload_3
    //   153: invokevirtual 291	com/taobao/munion/base/volley/a/d$b:close	()V
    //   156: aconst_null
    //   157: astore_1
    //   158: goto -134 -> 24
    //   161: astore_1
    //   162: aconst_null
    //   163: astore_1
    //   164: goto -140 -> 24
    //   167: astore_1
    //   168: aconst_null
    //   169: astore_2
    //   170: aload_2
    //   171: ifnull +7 -> 178
    //   174: aload_2
    //   175: invokevirtual 291	com/taobao/munion/base/volley/a/d$b:close	()V
    //   178: aload_1
    //   179: athrow
    //   180: astore_1
    //   181: aload_0
    //   182: monitorexit
    //   183: aload_1
    //   184: athrow
    //   185: astore_1
    //   186: aconst_null
    //   187: astore_1
    //   188: goto -164 -> 24
    //   191: astore_1
    //   192: goto -22 -> 170
    //   195: astore 4
    //   197: goto -84 -> 113
    //
    // Exception table:
    //   from	to	target	type
    //   94	98	103	java/io/IOException
    //   35	53	109	java/io/IOException
    //   152	156	161	java/io/IOException
    //   35	53	167	finally
    //   2	17	180	finally
    //   28	35	180	finally
    //   94	98	180	finally
    //   152	156	180	finally
    //   174	178	180	finally
    //   178	180	180	finally
    //   174	178	185	java/io/IOException
    //   55	60	191	finally
    //   62	85	191	finally
    //   115	141	191	finally
    //   143	148	191	finally
    //   55	60	195	java/io/IOException
    //   62	85	195	java/io/IOException
  }

  // ERROR //
  public void a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 49	com/taobao/munion/base/volley/a/d:c	Ljava/io/File;
    //   6: invokevirtual 302	java/io/File:exists	()Z
    //   9: ifne +36 -> 45
    //   12: aload_0
    //   13: getfield 49	com/taobao/munion/base/volley/a/d:c	Ljava/io/File;
    //   16: invokevirtual 305	java/io/File:mkdirs	()Z
    //   19: ifne +23 -> 42
    //   22: ldc_w 307
    //   25: iconst_1
    //   26: anewarray 4	java/lang/Object
    //   29: dup
    //   30: iconst_0
    //   31: aload_0
    //   32: getfield 49	com/taobao/munion/base/volley/a/d:c	Ljava/io/File;
    //   35: invokevirtual 296	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   38: aastore
    //   39: invokestatic 309	com/taobao/munion/base/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: aload_0
    //   46: getfield 49	com/taobao/munion/base/volley/a/d:c	Ljava/io/File;
    //   49: invokevirtual 313	java/io/File:listFiles	()[Ljava/io/File;
    //   52: astore_3
    //   53: aload_3
    //   54: ifnull -12 -> 42
    //   57: aload_3
    //   58: arraylength
    //   59: istore 7
    //   61: iconst_0
    //   62: istore 6
    //   64: iload 6
    //   66: iload 7
    //   68: if_icmpge -26 -> 42
    //   71: aload_3
    //   72: iload 6
    //   74: aaload
    //   75: astore 4
    //   77: aconst_null
    //   78: astore_1
    //   79: new 272	java/io/FileInputStream
    //   82: dup
    //   83: aload 4
    //   85: invokespecial 274	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   88: astore_2
    //   89: aload_2
    //   90: astore_1
    //   91: aload_2
    //   92: invokestatic 280	com/taobao/munion/base/volley/a/d$a:a	(Ljava/io/InputStream;)Lcom/taobao/munion/base/volley/a/d$a;
    //   95: astore 5
    //   97: aload_2
    //   98: astore_1
    //   99: aload 5
    //   101: aload 4
    //   103: invokevirtual 282	java/io/File:length	()J
    //   106: putfield 116	com/taobao/munion/base/volley/a/d$a:a	J
    //   109: aload_2
    //   110: astore_1
    //   111: aload_0
    //   112: aload 5
    //   114: getfield 106	com/taobao/munion/base/volley/a/d$a:b	Ljava/lang/String;
    //   117: aload 5
    //   119: invokespecial 315	com/taobao/munion/base/volley/a/d:a	(Ljava/lang/String;Lcom/taobao/munion/base/volley/a/d$a;)V
    //   122: aload_2
    //   123: ifnull +7 -> 130
    //   126: aload_2
    //   127: invokevirtual 316	java/io/FileInputStream:close	()V
    //   130: iload 6
    //   132: iconst_1
    //   133: iadd
    //   134: istore 6
    //   136: goto -72 -> 64
    //   139: astore_1
    //   140: aconst_null
    //   141: astore_2
    //   142: aload 4
    //   144: ifnull +11 -> 155
    //   147: aload_2
    //   148: astore_1
    //   149: aload 4
    //   151: invokevirtual 114	java/io/File:delete	()Z
    //   154: pop
    //   155: aload_2
    //   156: ifnull -26 -> 130
    //   159: aload_2
    //   160: invokevirtual 316	java/io/FileInputStream:close	()V
    //   163: goto -33 -> 130
    //   166: astore_1
    //   167: goto -37 -> 130
    //   170: astore_3
    //   171: aload_1
    //   172: astore_2
    //   173: aload_3
    //   174: astore_1
    //   175: aload_2
    //   176: ifnull +7 -> 183
    //   179: aload_2
    //   180: invokevirtual 316	java/io/FileInputStream:close	()V
    //   183: aload_1
    //   184: athrow
    //   185: astore_1
    //   186: aload_0
    //   187: monitorexit
    //   188: aload_1
    //   189: athrow
    //   190: astore_1
    //   191: goto -61 -> 130
    //   194: astore_2
    //   195: goto -12 -> 183
    //   198: astore_3
    //   199: aload_1
    //   200: astore_2
    //   201: aload_3
    //   202: astore_1
    //   203: goto -28 -> 175
    //   206: astore_1
    //   207: goto -65 -> 142
    //
    // Exception table:
    //   from	to	target	type
    //   79	89	139	java/io/IOException
    //   159	163	166	java/io/IOException
    //   79	89	170	finally
    //   2	42	185	finally
    //   45	53	185	finally
    //   57	61	185	finally
    //   126	130	185	finally
    //   159	163	185	finally
    //   179	183	185	finally
    //   183	185	185	finally
    //   126	130	190	java/io/IOException
    //   179	183	194	java/io/IOException
    //   91	97	198	finally
    //   99	109	198	finally
    //   111	122	198	finally
    //   149	155	198	finally
    //   91	97	206	java/io/IOException
    //   99	109	206	java/io/IOException
    //   111	122	206	java/io/IOException
  }

  public void a(String paramString, b.a parama)
  {
    try
    {
      a(parama.a.length);
      File localFile = c(paramString);
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
        a locala = new a(paramString, parama);
        locala.a(localFileOutputStream);
        localFileOutputStream.write(parama.a);
        localFileOutputStream.close();
        a(paramString, locala);
        return;
      }
      catch (IOException paramString)
      {
        while (true)
          if (!localFile.delete())
            Log.d("Could not clean up file %s", new Object[] { localFile.getAbsolutePath() });
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public void a(String paramString, boolean paramBoolean)
  {
    try
    {
      b.a locala = a(paramString);
      if (locala != null)
      {
        locala.e = 0L;
        if (paramBoolean)
          locala.d = 0L;
        a(paramString, locala);
      }
      return;
    }
    finally
    {
    }
    throw paramString;
  }

  public void b()
  {
    int i = 0;
    try
    {
      File[] arrayOfFile = this.c.listFiles();
      if (arrayOfFile != null)
      {
        int j = arrayOfFile.length;
        while (i < j)
        {
          arrayOfFile[i].delete();
          i += 1;
        }
      }
      this.a.clear();
      this.b = 0L;
      Log.d("Cache cleared.", new Object[0]);
      return;
    }
    finally
    {
    }
  }

  public void b(String paramString)
  {
    try
    {
      boolean bool = c(paramString).delete();
      e(paramString);
      if (!bool)
        Log.d("Could not delete cache entry for key=%s, filename=%s", new Object[] { paramString, d(paramString) });
      return;
    }
    finally
    {
    }
    throw paramString;
  }

  public File c(String paramString)
  {
    return new File(this.c, d(paramString));
  }

  static class a
  {
    public long a;
    public String b;
    public String c;
    public long d;
    public long e;
    public long f;
    public Map<String, String> g;

    private a()
    {
    }

    public a(String paramString, b.a parama)
    {
      this.b = paramString;
      this.a = parama.a.length;
      this.c = parama.b;
      this.d = parama.c;
      this.e = parama.d;
      this.f = parama.e;
      this.g = parama.f;
    }

    public static a a(InputStream paramInputStream)
      throws IOException
    {
      a locala = new a();
      if (d.a(paramInputStream) != 538051844)
        throw new IOException();
      locala.b = d.c(paramInputStream);
      locala.c = d.c(paramInputStream);
      if (locala.c.equals(""))
        locala.c = null;
      locala.d = d.b(paramInputStream);
      locala.e = d.b(paramInputStream);
      locala.f = d.b(paramInputStream);
      locala.g = d.d(paramInputStream);
      return locala;
    }

    public b.a a(byte[] paramArrayOfByte)
    {
      b.a locala = new b.a();
      locala.a = paramArrayOfByte;
      locala.b = this.c;
      locala.c = this.d;
      locala.d = this.e;
      locala.e = this.f;
      locala.f = this.g;
      return locala;
    }

    public boolean a(OutputStream paramOutputStream)
    {
      try
      {
        d.a(paramOutputStream, 538051844);
        d.a(paramOutputStream, this.b);
        if (this.c == null);
        for (String str = ""; ; str = this.c)
        {
          d.a(paramOutputStream, str);
          d.a(paramOutputStream, this.d);
          d.a(paramOutputStream, this.e);
          d.a(paramOutputStream, this.f);
          d.a(this.g, paramOutputStream);
          paramOutputStream.flush();
          return true;
        }
      }
      catch (IOException paramOutputStream)
      {
        Log.d("%s", new Object[] { paramOutputStream.toString() });
      }
      return false;
    }
  }

  private static class b extends FilterInputStream
  {
    private int a = 0;

    private b(InputStream paramInputStream)
    {
      super();
    }

    public int read()
      throws IOException
    {
      int i = super.read();
      if (i != -1)
        this.a += 1;
      return i;
    }

    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      paramInt1 = super.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt1 != -1)
        this.a += paramInt1;
      return paramInt1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.a.d
 * JD-Core Version:    0.6.2
 */