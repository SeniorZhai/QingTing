package com.taobao.munion.base.caches;

import java.io.IOException;
import java.io.InputStream;

public class d extends InputStream
{
  private static final String a = "CacheWrapperStream";
  private String b;
  private m c;
  private InputStream d;
  private boolean e = false;

  public d(String paramString, m paramm)
  {
    this.b = paramString;
    this.c = paramm;
  }

  private int a(String paramString, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.d == null)
    {
      paramString = b.a().c(paramString);
      if (paramString != null)
        this.d = paramString.e;
    }
    else
    {
      return this.d.read(paramArrayOfByte, paramInt1, paramInt2);
    }
    return -1;
  }

  public int available()
    throws IOException
  {
    return super.available();
  }

  // ERROR //
  public void close()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 32	com/taobao/munion/base/caches/d:d	Ljava/io/InputStream;
    //   4: ifnull +15 -> 19
    //   7: aload_0
    //   8: getfield 32	com/taobao/munion/base/caches/d:d	Ljava/io/InputStream;
    //   11: invokevirtual 56	java/io/InputStream:close	()V
    //   14: aload_0
    //   15: aconst_null
    //   16: putfield 32	com/taobao/munion/base/caches/d:d	Ljava/io/InputStream;
    //   19: aload_0
    //   20: invokespecial 56	java/io/InputStream:close	()V
    //   23: return
    //   24: astore_1
    //   25: aload_0
    //   26: aconst_null
    //   27: putfield 32	com/taobao/munion/base/caches/d:d	Ljava/io/InputStream;
    //   30: goto -11 -> 19
    //   33: astore_1
    //   34: aload_0
    //   35: aconst_null
    //   36: putfield 32	com/taobao/munion/base/caches/d:d	Ljava/io/InputStream;
    //   39: aload_1
    //   40: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   7	14	24	java/io/IOException
    //   7	14	33	finally
  }

  public void mark(int paramInt)
  {
    super.mark(paramInt);
  }

  public boolean markSupported()
  {
    return super.markSupported();
  }

  public int read()
    throws IOException
  {
    return 0;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (!this.e)
    {
      this.e = true;
      if (this.c != null)
        this.c.a();
    }
    if ((this.c != null) && (this.c.a == 0))
      return a(this.b, paramArrayOfByte, paramInt1, paramInt2);
    return -1;
  }

  public void reset()
    throws IOException
  {
    try
    {
      super.reset();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long skip(long paramLong)
    throws IOException
  {
    return super.skip(paramLong);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.d
 * JD-Core Version:    0.6.2
 */