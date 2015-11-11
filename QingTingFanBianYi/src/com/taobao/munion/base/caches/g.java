package com.taobao.munion.base.caches;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class g
{
  public static final int a = 1;
  public static final int b = 2;
  public static final int c = 3;
  public static final int d = 4;
  private static String e = "FileCache";
  private static final String f = "wv_web_info.dat";
  private String g;
  private String h;
  private boolean i;
  private boolean j;
  private Map<String, i> k = Collections.synchronizedMap(new LinkedHashMap());
  private RandomAccessFile l;
  private FileChannel m;
  private boolean n = true;
  private int o = 100;
  private FileLock p;

  public g(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
  {
    this.g = paramString1;
    this.h = paramString2;
    this.o = paramInt;
    this.i = paramBoolean;
    this.j = false;
  }

  private void a(int paramInt)
  {
    if (this.k.size() > paramInt)
      f();
  }

  private void f()
  {
    Object localObject1 = new ArrayList();
    Object localObject2 = this.k.entrySet();
    int i1 = this.k.size();
    localObject2 = ((Set)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      Object localObject3 = (Map.Entry)((Iterator)localObject2).next();
      if (i1 < this.o)
        break;
      localObject3 = (i)((Map.Entry)localObject3).getValue();
      if (localObject3 != null)
        ((ArrayList)localObject1).add(localObject3);
      i1 -= 1;
    }
    localObject1 = ((ArrayList)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
      c(((i)((Iterator)localObject1).next()).b());
  }

  private boolean g()
  {
    int i4 = 0;
    System.currentTimeMillis();
    Object localObject1 = null;
    try
    {
      Object localObject2 = ByteBuffer.allocate((int)this.m.size());
      this.m.read((ByteBuffer)localObject2);
      localObject2 = ((ByteBuffer)localObject2).array();
      localObject1 = localObject2;
      label37: System.currentTimeMillis();
      if (localObject1 != null)
      {
        int i2 = 128;
        localObject2 = new ByteArrayOutputStream();
        int i1 = 0;
        if (i2 < localObject1.length)
        {
          int i5 = i4;
          int i6 = i2;
          int i3 = i1;
          if (localObject1[i2] == 10)
          {
            i locali = j.a((byte[])localObject1, i4, i2 - i4);
            if (locali == null)
              break label202;
            String str = locali.b();
            if (this.k.containsKey(str))
              break label196;
            locali.b(((ByteArrayOutputStream)localObject2).size());
            this.k.put(str, locali);
            ((ByteArrayOutputStream)localObject2).write((byte[])localObject1, i4, i2 - i4 + 1);
          }
          while (true)
          {
            i5 = i2 + 1;
            i6 = i2 + 128;
            i3 = i1;
            i2 = i6 + 1;
            i4 = i5;
            i1 = i3;
            break;
            label196: i1 = 1;
            continue;
            label202: i1 = 1;
          }
        }
        System.currentTimeMillis();
        if (i1 == 0);
      }
      try
      {
        this.m.truncate(0L);
        this.m.position(0L);
        localObject1 = ByteBuffer.wrap(((ByteArrayOutputStream)localObject2).toByteArray());
        ((ByteBuffer)localObject1).position(0);
        this.m.write((ByteBuffer)localObject1);
        try
        {
          label258: ((ByteArrayOutputStream)localObject2).close();
          return true;
        }
        catch (IOException localIOException1)
        {
          localIOException1.printStackTrace();
          return true;
        }
        return false;
      }
      catch (IOException localIOException2)
      {
        break label258;
      }
    }
    catch (IOException localIOException3)
    {
      break label37;
    }
  }

  public i a(String paramString)
  {
    Object localObject;
    if (!this.j)
      localObject = null;
    i locali;
    do
    {
      return localObject;
      locali = (i)this.k.get(paramString);
      if (locali == null)
        return null;
      localObject = locali;
    }
    while (new File(this.g, paramString).exists());
    j.a(3, locali, this.m);
    this.k.remove(paramString);
    return null;
  }

  public String a()
  {
    return this.g;
  }

  public void a(i parami)
  {
    String str;
    if ((this.j) && (parami != null))
    {
      str = parami.b();
      if (str != null)
        break label21;
    }
    label21: i locali;
    do
    {
      return;
      locali = (i)this.k.get(str);
    }
    while (locali == null);
    parami.b(locali.e());
    parami = j.a(2, parami, this.m);
    this.k.put(str, parami);
  }

  public boolean a(i parami, ByteBuffer paramByteBuffer)
  {
    String str;
    if (parami != null)
    {
      str = parami.b();
      if (str != null)
        break label15;
    }
    while (true)
    {
      return false;
      label15: if (this.j)
      {
        File localFile = new File(this.g, str);
        try
        {
          bool = f.a(localFile, paramByteBuffer);
          if (!bool);
        }
        catch (p localp)
        {
          try
          {
            boolean bool;
            paramByteBuffer = (i)this.k.get(str);
            if (paramByteBuffer != null)
            {
              parami.b(paramByteBuffer.e());
              parami = j.a(2, parami, this.m);
              this.k.put(str, parami);
            }
            while (true)
            {
              while (true)
              {
                return true;
                localp = localp;
                if (this.n)
                {
                  e();
                  try
                  {
                    bool = f.a(localFile, paramByteBuffer);
                  }
                  catch (p paramByteBuffer)
                  {
                    paramByteBuffer.printStackTrace();
                  }
                }
              }
              bool = false;
              break;
              parami = j.a(4, parami, this.m);
              this.k.put(str, parami);
            }
          }
          finally
          {
            if (this.k.size() > this.o)
            {
              paramByteBuffer = this.k.entrySet().iterator();
              if (paramByteBuffer.hasNext())
                c(((i)((Map.Entry)paramByteBuffer.next()).getValue()).b());
            }
          }
        }
      }
    }
    throw parami;
  }

  public boolean b()
  {
    return this.i;
  }

  public byte[] b(String paramString)
  {
    if (!this.j)
      return null;
    i locali = (i)this.k.get(paramString);
    if (locali == null)
      return null;
    this.k.remove(paramString);
    locali = j.a(1, locali, this.m);
    this.k.put(paramString, locali);
    return f.a(new File(this.g, paramString));
  }

  public int c()
  {
    if (this.j)
      return this.k.size();
    return 0;
  }

  public boolean c(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    Object localObject;
    if (this.j)
    {
      bool1 = bool2;
      if (paramString != null)
      {
        System.currentTimeMillis();
        localObject = new File(this.g, paramString);
        if (!((File)localObject).isFile())
          break label105;
      }
    }
    label105: for (bool1 = ((File)localObject).delete(); ; bool1 = false)
    {
      if ((bool1) || (!((File)localObject).exists()))
      {
        localObject = (i)this.k.get(paramString);
        if (localObject != null)
        {
          j.a(3, (i)localObject, this.m);
          this.k.remove(paramString);
          bool1 = true;
          return bool1;
        }
      }
      return bool1;
    }
  }

  // ERROR //
  public boolean d()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 68	com/taobao/munion/base/caches/g:j	Z
    //   8: ifne +199 -> 207
    //   11: new 201	java/io/File
    //   14: dup
    //   15: aload_0
    //   16: getfield 64	com/taobao/munion/base/caches/g:h	Ljava/lang/String;
    //   19: ldc 18
    //   21: invokespecial 204	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   24: astore_1
    //   25: aload_1
    //   26: invokevirtual 207	java/io/File:exists	()Z
    //   29: ifne +23 -> 52
    //   32: new 201	java/io/File
    //   35: dup
    //   36: aload_0
    //   37: getfield 64	com/taobao/munion/base/caches/g:h	Ljava/lang/String;
    //   40: invokespecial 242	java/io/File:<init>	(Ljava/lang/String;)V
    //   43: invokevirtual 245	java/io/File:mkdirs	()Z
    //   46: pop
    //   47: aload_1
    //   48: invokevirtual 248	java/io/File:createNewFile	()Z
    //   51: pop
    //   52: new 201	java/io/File
    //   55: dup
    //   56: aload_0
    //   57: getfield 62	com/taobao/munion/base/caches/g:g	Ljava/lang/String;
    //   60: invokespecial 242	java/io/File:<init>	(Ljava/lang/String;)V
    //   63: invokevirtual 245	java/io/File:mkdirs	()Z
    //   66: pop
    //   67: aload_0
    //   68: new 250	java/io/RandomAccessFile
    //   71: dup
    //   72: aload_1
    //   73: invokevirtual 253	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   76: ldc 255
    //   78: invokespecial 256	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   81: putfield 258	com/taobao/munion/base/caches/g:l	Ljava/io/RandomAccessFile;
    //   84: aload_0
    //   85: getfield 128	com/taobao/munion/base/caches/g:m	Ljava/nio/channels/FileChannel;
    //   88: ifnonnull +14 -> 102
    //   91: aload_0
    //   92: aload_0
    //   93: getfield 258	com/taobao/munion/base/caches/g:l	Ljava/io/RandomAccessFile;
    //   96: invokevirtual 262	java/io/RandomAccessFile:getChannel	()Ljava/nio/channels/FileChannel;
    //   99: putfield 128	com/taobao/munion/base/caches/g:m	Ljava/nio/channels/FileChannel;
    //   102: aload_0
    //   103: aload_0
    //   104: getfield 128	com/taobao/munion/base/caches/g:m	Ljava/nio/channels/FileChannel;
    //   107: invokevirtual 266	java/nio/channels/FileChannel:tryLock	()Ljava/nio/channels/FileLock;
    //   110: putfield 268	com/taobao/munion/base/caches/g:p	Ljava/nio/channels/FileLock;
    //   113: aload_0
    //   114: getfield 268	com/taobao/munion/base/caches/g:p	Ljava/nio/channels/FileLock;
    //   117: astore_1
    //   118: aload_1
    //   119: ifnonnull +9 -> 128
    //   122: iload_3
    //   123: istore_2
    //   124: aload_0
    //   125: monitorexit
    //   126: iload_2
    //   127: ireturn
    //   128: invokestatic 126	java/lang/System:currentTimeMillis	()J
    //   131: pop2
    //   132: aload_0
    //   133: invokespecial 270	com/taobao/munion/base/caches/g:g	()Z
    //   136: ifne +41 -> 177
    //   139: aload_0
    //   140: getfield 268	com/taobao/munion/base/caches/g:p	Ljava/nio/channels/FileLock;
    //   143: astore_1
    //   144: iload_3
    //   145: istore_2
    //   146: aload_1
    //   147: ifnull -23 -> 124
    //   150: aload_0
    //   151: getfield 268	com/taobao/munion/base/caches/g:p	Ljava/nio/channels/FileLock;
    //   154: invokevirtual 275	java/nio/channels/FileLock:release	()V
    //   157: iload_3
    //   158: istore_2
    //   159: goto -35 -> 124
    //   162: astore_1
    //   163: aload_1
    //   164: invokevirtual 194	java/io/IOException:printStackTrace	()V
    //   167: iload_3
    //   168: istore_2
    //   169: goto -45 -> 124
    //   172: astore_1
    //   173: aload_0
    //   174: monitorexit
    //   175: aload_1
    //   176: athrow
    //   177: aload_0
    //   178: iconst_1
    //   179: putfield 68	com/taobao/munion/base/caches/g:j	Z
    //   182: aload_0
    //   183: aload_0
    //   184: getfield 60	com/taobao/munion/base/caches/g:o	I
    //   187: invokespecial 277	com/taobao/munion/base/caches/g:a	(I)V
    //   190: aload_0
    //   191: getfield 56	com/taobao/munion/base/caches/g:k	Ljava/util/Map;
    //   194: invokeinterface 75 1 0
    //   199: ifne +8 -> 207
    //   202: aload_0
    //   203: invokevirtual 226	com/taobao/munion/base/caches/g:e	()Z
    //   206: pop
    //   207: iconst_1
    //   208: istore_2
    //   209: goto -85 -> 124
    //   212: astore_1
    //   213: iload_3
    //   214: istore_2
    //   215: goto -91 -> 124
    //   218: astore_1
    //   219: iload_3
    //   220: istore_2
    //   221: goto -97 -> 124
    //
    // Exception table:
    //   from	to	target	type
    //   150	157	162	java/io/IOException
    //   4	47	172	finally
    //   47	52	172	finally
    //   52	67	172	finally
    //   67	102	172	finally
    //   102	118	172	finally
    //   128	144	172	finally
    //   150	157	172	finally
    //   163	167	172	finally
    //   177	207	172	finally
    //   47	52	212	java/io/IOException
    //   67	102	218	java/lang/Exception
    //   102	118	218	java/lang/Exception
  }

  public boolean e()
  {
    boolean bool1 = false;
    boolean bool2 = bool1;
    if (this.j)
    {
      String[] arrayOfString = new File(this.g).list();
      bool2 = bool1;
      if (arrayOfString != null)
      {
        int i2 = arrayOfString.length;
        bool1 = true;
        int i1 = 0;
        while (true)
        {
          bool2 = bool1;
          if (i1 >= i2)
            break;
          bool2 = c(arrayOfString[i1]);
          i1 += 1;
          bool1 = bool2 & bool1;
        }
      }
    }
    return bool2;
  }

  protected void finalize()
    throws Throwable
  {
    if (this.p != null)
      this.p.release();
    if (this.l != null);
    try
    {
      this.l.close();
      if (this.m == null);
    }
    catch (Exception localException1)
    {
      try
      {
        this.m.close();
        super.finalize();
        return;
        localException1 = localException1;
        localException1.printStackTrace();
      }
      catch (Exception localException2)
      {
        while (true)
          localException2.printStackTrace();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.g
 * JD-Core Version:    0.6.2
 */