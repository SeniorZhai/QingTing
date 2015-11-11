package com.taobao.newxp.common.a.a;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import com.taobao.newxp.common.a.a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class c
{
  private static final String F = c.class.getName();
  private static int G = 0;
  public double A;
  public double B;
  public boolean C;
  public String D;
  public String E;
  private byte[] H;
  private Context I;
  private g J;
  public String a;
  public int b;
  public int c;
  public String d;
  public String e;
  public String f;
  public String g;
  public byte h;
  public String i;
  public int j;
  public int k;
  public int l;
  public int m;
  public int n;
  public int o;
  public boolean p;
  public byte q;
  public boolean r;
  public long s;
  public int t;
  public int u;
  public int v;
  public int w;
  public int x;
  public int y;
  public String z;

  private c()
  {
  }

  private c(Context paramContext, Bundle paramBundle)
  {
    this.I = paramContext;
    paramContext = a.a().b();
    if (paramContext != null)
    {
      this.t = paramContext.f;
      this.u = paramContext.g;
      this.A = paramContext.i;
      this.B = paramContext.j;
    }
    d();
  }

  public static int a(InputStream paramInputStream)
    throws IOException
  {
    int i1 = 0x0 | paramInputStream.read() << 8 & 0xFF00 | paramInputStream.read() & 0xFF;
    if (i1 != 65535)
      return i1;
    return -1;
  }

  public static c a(Context paramContext, Bundle paramBundle)
  {
    return new c(paramContext, paramBundle);
  }

  public static c a(String paramString)
    throws NullPointerException, IOException, SecurityException
  {
    return a(paramString.getBytes());
  }

  private static c a(byte[] paramArrayOfByte)
    throws NullPointerException, IOException, SecurityException
  {
    boolean bool2 = true;
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 7))
    {
      byte[] arrayOfByte1 = Base64.decode(paramArrayOfByte, 0);
      byte[] arrayOfByte2 = new byte[4];
      byte[] arrayOfByte3 = new byte[arrayOfByte1.length - 7];
      c localc = new c();
      paramArrayOfByte = new ByteArrayInputStream(arrayOfByte1);
      localc.a = a((byte)paramArrayOfByte.read(), (byte)paramArrayOfByte.read());
      localc.H = a((byte)paramArrayOfByte.read(), (byte)paramArrayOfByte.read(), (byte)paramArrayOfByte.read(), (byte)paramArrayOfByte.read());
      localc.b = paramArrayOfByte.read();
      System.arraycopy(arrayOfByte1, 7, arrayOfByte3, 0, arrayOfByte3.length);
      a(com.taobao.newxp.common.b.b.a(arrayOfByte3), arrayOfByte2, 0);
      boolean bool1;
      if ((localc.H != null) && (arrayOfByte2 != null) && (com.taobao.munion.base.anticheat.b.a(localc.H, arrayOfByte2)))
      {
        localc.d = c(paramArrayOfByte);
        localc.c = a(paramArrayOfByte);
        localc.e = a(paramArrayOfByte, 17);
        localc.f = c(paramArrayOfByte);
        localc.g = c(paramArrayOfByte);
        localc.h = ((byte)paramArrayOfByte.read());
        localc.i = c(paramArrayOfByte);
        localc.j = a(paramArrayOfByte);
        localc.k = a(paramArrayOfByte);
        localc.l = paramArrayOfByte.read();
        localc.m = paramArrayOfByte.read();
        localc.n = paramArrayOfByte.read();
        localc.o = paramArrayOfByte.read();
        localc.s = b(paramArrayOfByte);
        if ((byte)paramArrayOfByte.read() == 1)
          bool1 = true;
      }
      while (true)
      {
        localc.p = bool1;
        localc.q = ((byte)paramArrayOfByte.read());
        if ((byte)paramArrayOfByte.read() == 1)
        {
          bool1 = bool2;
          label302: localc.r = bool1;
          localc.t = a(paramArrayOfByte);
          localc.u = a(paramArrayOfByte);
          localc.J.a = a(paramArrayOfByte);
          localc.J.e = a(paramArrayOfByte);
          localc.J.f = a(paramArrayOfByte);
          localc.J.c = a(paramArrayOfByte);
          localc.J.d = a(paramArrayOfByte);
          localc.J.g = a(paramArrayOfByte);
          localc.J.h = a(paramArrayOfByte);
          localc.J.i = a(paramArrayOfByte);
          localc.v = ((byte)paramArrayOfByte.read());
          localc.w = a(paramArrayOfByte);
          localc.x = a(paramArrayOfByte);
          localc.y = a(paramArrayOfByte);
          localc.z = c(paramArrayOfByte);
          localc.A = d(paramArrayOfByte);
          localc.B = d(paramArrayOfByte);
          localc.C = false;
          localc.D = c(paramArrayOfByte);
          localc.E = c(paramArrayOfByte);
        }
        try
        {
          paramArrayOfByte.close();
          return localc;
          bool1 = false;
          continue;
          bool1 = false;
          break label302;
          throw new SecurityException();
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
          return localc;
        }
        finally
        {
          if (paramArrayOfByte == null);
        }
      }
    }
    throw new NullPointerException();
  }

  private static String a(byte paramByte1, byte paramByte2)
    throws NullPointerException
  {
    return new Integer(paramByte1).toString() + "." + new Integer(paramByte2).toString();
  }

  public static String a(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    paramInputStream.read(arrayOfByte);
    if (arrayOfByte[0] != -1)
      return new String(arrayOfByte);
    return null;
  }

  private static void a(byte paramByte, byte[] paramArrayOfByte, int paramInt)
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > paramInt))
      paramArrayOfByte[paramInt] = paramByte;
  }

  public static void a(OutputStream paramOutputStream, double paramDouble, int paramInt)
    throws IOException
  {
    paramInt = -1;
    int i1;
    if (paramDouble != 0.0D)
    {
      paramInt = (int)Math.floor(Math.abs(paramDouble));
      i1 = (int)Math.floor(com.taobao.munion.base.anticheat.b.a(Math.abs(paramDouble)) * 60.0D);
      if (paramDouble <= 0.0D)
        break label69;
      paramInt += 180;
    }
    while (true)
    {
      paramInt = paramInt << 6 & 0x7FC0 | (i1 & 0x3F | 0x0);
      a(paramOutputStream, paramInt);
      return;
      label69: paramInt = 180 - paramInt;
    }
  }

  public static void a(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    paramOutputStream.write((byte)(paramInt >> 8));
    paramOutputStream.write((byte)(paramInt >> 0));
  }

  public static void a(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    if (paramString != null)
    {
      if (paramString.trim().length() > 0)
      {
        paramOutputStream.write(paramString.getBytes());
        return;
      }
      paramOutputStream.write((byte)paramString.trim().length());
      return;
    }
    paramOutputStream.write(b.b());
  }

  private static void a(String paramString, byte[] paramArrayOfByte, int paramInt)
    throws NullPointerException, NumberFormatException
  {
    if ((paramString != null) && (paramString.trim().length() > 0) && (paramArrayOfByte != null) && (paramArrayOfByte.length > 0))
    {
      paramString = paramString.split("\\.");
      paramArrayOfByte[paramInt] = Integer.valueOf(paramString[0]).byteValue();
      paramArrayOfByte[(paramInt + 1)] = Integer.valueOf(paramString[1]).byteValue();
    }
  }

  private static void a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
  {
    if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length >= paramInt + 4) && (paramArrayOfByte2 != null) && (paramArrayOfByte2.length >= 16))
    {
      int i1 = paramInt + 1;
      paramArrayOfByte2[paramInt] = paramArrayOfByte1[0];
      paramInt = i1 + 1;
      paramArrayOfByte2[i1] = paramArrayOfByte1[5];
      paramArrayOfByte2[paramInt] = paramArrayOfByte1[10];
      paramArrayOfByte2[(paramInt + 1)] = paramArrayOfByte1[15];
    }
  }

  private static byte[] a(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4)
  {
    return new byte[] { paramByte1, paramByte2, paramByte3, paramByte4 };
  }

  public static int b(InputStream paramInputStream)
    throws IOException
  {
    int i1 = 0x0 | paramInputStream.read() << 24 & 0xFF000000 | paramInputStream.read() << 16 & 0xFF0000 | paramInputStream.read() << 8 & 0xFF00 | paramInputStream.read() & 0xFF;
    if (i1 != -1)
      return i1;
    return -1;
  }

  private String b()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("emulator:" + localStringBuilder);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("osVersion:" + this.c);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("MAC:" + this.e);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("IMSI:" + this.f);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("deviceId:" + this.g);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("deviceType:" + this.h);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("manufacturer:" + this.i);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("appWidth:" + this.j);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("appHight:" + this.k);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("screenDensity:" + this.l);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("screenBright:" + this.m);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("netType:" + this.n);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("netProtocol:" + this.o);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("appRunTime:" + G);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("isConvered:-1");
    localStringBuilder.append("\r\n");
    localStringBuilder.append("adOpenness:" + this.q);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("inVisio:-1");
    localStringBuilder.append("\r\n");
    localStringBuilder.append("adWidth:" + this.t);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("adHeight:" + this.u);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("touchNum:" + this.J.a);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("touchDownX:" + this.J.e);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("touchDownY:" + this.J.f);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("touchUpX:" + this.J.c);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("touchUpY:" + this.J.d);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("touchMoveX:" + this.J.a());
    localStringBuilder.append("\r\n");
    localStringBuilder.append("touchMoveY:" + this.J.b());
    localStringBuilder.append("\r\n");
    localStringBuilder.append("touchTime:" + this.J.i);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("availPower:" + this.v);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("totalMemory:" + this.w);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("availMemory:" + this.x);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("netTraffic:" + this.y);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("packName:" + this.z);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("longitude:" + this.A);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("latitude:" + this.B);
    localStringBuilder.append("\r\n");
    localStringBuilder.append("isRoot:-1");
    localStringBuilder.append("\r\n");
    return localStringBuilder.toString();
  }

  public static void b(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    paramOutputStream.write((byte)(paramInt >> 24));
    paramOutputStream.write((byte)(paramInt >> 16));
    paramOutputStream.write((byte)(paramInt >> 8));
    paramOutputStream.write((byte)(paramInt >> 0));
  }

  public static void b(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    if (paramString != null)
    {
      if (paramString.trim().length() > 0)
      {
        paramOutputStream.write((byte)paramString.length());
        paramOutputStream.write(paramString.getBytes());
        return;
      }
      paramOutputStream.write((byte)paramString.trim().length());
      return;
    }
    paramOutputStream.write(b.c());
  }

  public static String c(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = null;
    int i1 = (byte)paramInputStream.read();
    if (i1 > 0)
    {
      arrayOfByte = new byte[i1];
      paramInputStream.read(arrayOfByte);
      paramInputStream = new String(arrayOfByte);
    }
    do
    {
      return paramInputStream;
      paramInputStream = arrayOfByte;
    }
    while (i1 != 0);
    return "";
  }

  private byte[] c()
    throws IOException
  {
    int i2 = 1;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    b(localByteArrayOutputStream, this.d);
    a(localByteArrayOutputStream, this.c);
    a(localByteArrayOutputStream, this.e);
    b(localByteArrayOutputStream, this.f);
    b(localByteArrayOutputStream, this.g);
    localByteArrayOutputStream.write(this.h);
    b(localByteArrayOutputStream, this.i);
    a(localByteArrayOutputStream, this.j);
    a(localByteArrayOutputStream, this.k);
    localByteArrayOutputStream.write((byte)this.l);
    localByteArrayOutputStream.write((byte)this.m);
    localByteArrayOutputStream.write((byte)this.n);
    localByteArrayOutputStream.write((byte)this.o);
    b(localByteArrayOutputStream, G);
    int i1;
    if (this.p)
      i1 = 1;
    while (true)
    {
      localByteArrayOutputStream.write(i1);
      localByteArrayOutputStream.write(this.q);
      label161: byte[] arrayOfByte1;
      if (this.r)
      {
        i1 = i2;
        localByteArrayOutputStream.write(i1);
        a(localByteArrayOutputStream, this.t);
        a(localByteArrayOutputStream, this.u);
        a(localByteArrayOutputStream, this.J.a);
        a(localByteArrayOutputStream, this.J.e);
        a(localByteArrayOutputStream, this.J.f);
        a(localByteArrayOutputStream, this.J.c);
        a(localByteArrayOutputStream, this.J.d);
        a(localByteArrayOutputStream, this.J.a());
        a(localByteArrayOutputStream, this.J.b());
        a(localByteArrayOutputStream, (int)this.J.i);
        localByteArrayOutputStream.write((byte)this.v);
        a(localByteArrayOutputStream, this.w);
        a(localByteArrayOutputStream, this.x);
        a(localByteArrayOutputStream, this.y);
        b(localByteArrayOutputStream, this.z);
        a(localByteArrayOutputStream, this.A, 180);
        a(localByteArrayOutputStream, this.B, 180);
        localByteArrayOutputStream.write(b.a());
        b(localByteArrayOutputStream, this.D);
        b(localByteArrayOutputStream, this.E);
        byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
        arrayOfByte1 = new byte[arrayOfByte2.length + 7];
        this.H = com.taobao.newxp.common.b.b.a(arrayOfByte2);
        a(this.a, arrayOfByte1, 0);
        a(this.H, arrayOfByte1, 2);
        a((byte)this.b, arrayOfByte1, 6);
        System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 7, arrayOfByte2.length);
      }
      try
      {
        localByteArrayOutputStream.close();
        return Base64.encode(arrayOfByte1, 0);
        i1 = 0;
        continue;
        i1 = 0;
        break label161;
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          localIOException.printStackTrace();
          if (localByteArrayOutputStream == null);
        }
      }
      finally
      {
        if (localByteArrayOutputStream == null);
      }
    }
  }

  public static double d(InputStream paramInputStream)
    throws IOException
  {
    double d1 = 0.0D;
    int i1 = a(paramInputStream);
    double d2;
    if (i1 != -1)
    {
      d1 = i1 & 0x3F;
      d2 = ((i1 & 0x7FC0) >> 6) - 180.0D;
      if (d2 < 0.0D)
        d1 = -(Math.abs(d2) + Math.abs(d1) / 60.0D);
    }
    else
    {
      return d1;
    }
    return Math.abs(d2) + Math.abs(d1) / 60.0D;
  }

  private void d()
  {
    this.a = "1.2";
    if (G == 0)
      G = com.taobao.munion.base.anticheat.b.c();
    this.b = 1;
    this.c = com.taobao.munion.base.anticheat.b.b();
    this.d = com.taobao.munion.base.anticheat.b.a();
    this.e = com.taobao.munion.base.anticheat.b.d(this.I);
    this.f = com.taobao.munion.base.anticheat.b.b(this.I);
    this.g = com.taobao.munion.base.anticheat.b.c(this.I);
    this.h = 0;
    this.i = com.taobao.munion.base.anticheat.b.d();
    this.j = com.taobao.munion.base.anticheat.b.k(this.I);
    this.k = com.taobao.munion.base.anticheat.b.l(this.I);
    this.p = false;
    this.r = false;
    this.q = -1;
    this.v = -1;
    this.l = com.taobao.munion.base.anticheat.b.e(this.I);
    this.m = com.taobao.munion.base.anticheat.b.f(this.I);
    this.n = com.taobao.munion.base.anticheat.b.n(this.I);
    this.o = com.taobao.munion.base.anticheat.b.o(this.I);
    this.w = com.taobao.munion.base.anticheat.b.g(this.I);
    this.x = com.taobao.munion.base.anticheat.b.h(this.I);
    this.y = -1;
    this.z = com.taobao.munion.base.anticheat.b.i(this.I);
    this.C = false;
    this.D = com.taobao.munion.base.anticheat.b.p(this.I);
    this.E = "";
  }

  public String a()
    throws IOException
  {
    this.J = ((b)a.a().b(0)).a();
    return com.taobao.munion.base.anticheat.b.a(new String(c()));
  }

  public static class a
  {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 0;
    public static final int i = 1;
    public static final int j = 2;
    public static final int k = 3;
  }

  public static class b
  {
    public static final int a = 0;
    public static final int b = -1;
    public static final double c = 0.0D;
    private static final byte d = 1;
    private static final byte e = 0;
    private static final byte[] f = { 0 };
    private static final byte[] g = { -1 };
    private static final int h = 180;
    private static final String i = "1.2";
    private static final String j = "\r\n";
    private static final byte[] k = { -1, -1, -1, -1, -1, -1, -1 };
    private static final byte[] l = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.a.a.c
 * JD-Core Version:    0.6.2
 */