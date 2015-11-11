package com.taobao.newxp.view.common.gif;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import java.io.InputStream;
import java.util.Vector;

public class a
{
  protected Bitmap A;
  protected Bitmap B;
  protected byte[] C = new byte[256];
  protected int D = 0;
  protected int E = 0;
  protected int F = 0;
  protected boolean G = false;
  protected int H = 0;
  protected int I;
  protected short[] J;
  protected byte[] K;
  protected byte[] L;
  protected byte[] M;
  protected Vector<a> N;
  protected int O;
  private final int P = 0;
  private final int Q = 1;
  private final int R = 2;
  private final int S = 3;
  protected final int a = 4096;
  protected InputStream b;
  protected int c;
  protected int d;
  protected int e;
  protected boolean f;
  protected int g;
  protected int h = 1;
  protected int[] i;
  protected int[] j;
  protected int[] k;
  protected int l;
  protected int m;
  protected int n;
  protected int o;
  protected boolean p;
  protected boolean q;
  protected int r;
  protected int s;
  protected int t;
  protected int u;
  protected int v;
  protected int w;
  protected int x;
  protected int y;
  protected int z;

  public int a()
  {
    return this.O;
  }

  public int a(int paramInt)
  {
    this.H = -1;
    if ((paramInt >= 0) && (paramInt < this.O))
      this.H = ((a)this.N.elementAt(paramInt)).b;
    return this.H;
  }

  public int a(InputStream paramInputStream)
  {
    g();
    if (paramInputStream != null)
    {
      this.b = paramInputStream;
      l();
      if (!f())
      {
        j();
        if (this.O < 0)
          this.c = 1;
      }
    }
    while (true)
    {
      if (paramInputStream != null);
      try
      {
        paramInputStream.close();
        label48: return this.c;
        this.c = 2;
      }
      catch (Exception paramInputStream)
      {
        break label48;
      }
    }
  }

  public Bitmap b()
  {
    return b(0);
  }

  public Bitmap b(int paramInt)
  {
    if (this.O <= 0)
      return null;
    int i1 = this.O;
    return ((a)this.N.elementAt(paramInt % i1)).a;
  }

  public int c()
  {
    return this.h;
  }

  protected int[] c(int paramInt)
  {
    int i2 = 0;
    int i3 = paramInt * 3;
    Object localObject = null;
    byte[] arrayOfByte = new byte[i3];
    int i1;
    int[] arrayOfInt;
    try
    {
      i1 = this.b.read(arrayOfByte);
      if (i1 < i3)
      {
        this.c = 1;
        return localObject;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        i1 = 0;
      }
      arrayOfInt = new int[256];
      i3 = 0;
      i1 = i2;
      i2 = i3;
    }
    while (true)
    {
      localObject = arrayOfInt;
      if (i1 >= paramInt)
        break;
      int i5 = i2 + 1;
      i3 = arrayOfByte[i2];
      int i4 = i5 + 1;
      i5 = arrayOfByte[i5];
      i2 = i4 + 1;
      arrayOfInt[i1] = ((i3 & 0xFF) << 16 | 0xFF000000 | (i5 & 0xFF) << 8 | arrayOfByte[i4] & 0xFF);
      i1 += 1;
    }
  }

  protected void d()
  {
    int i6 = 0;
    int[] arrayOfInt = new int[this.d * this.e];
    int i1;
    if (this.F > 0)
      if (this.F == 3)
      {
        i1 = this.O - 2;
        if (i1 > 0)
          this.B = b(i1 - 1);
      }
      else
      {
        if (this.B == null)
          break label179;
        this.B.getPixels(arrayOfInt, 0, this.d, 0, 0, this.d, this.e);
        if (this.F != 2)
          break label179;
        if (this.G)
          break label469;
      }
    label179: label460: label469: for (int i2 = this.n; ; i2 = 0)
    {
      int i3 = 0;
      while (true)
      {
        if (i3 >= this.z)
          break label179;
        i4 = (this.x + i3) * this.d + this.w;
        i5 = this.y;
        i1 = i4;
        while (true)
          if (i1 < i5 + i4)
          {
            arrayOfInt[i1] = i2;
            i1 += 1;
            continue;
            this.B = null;
            break;
          }
        i3 += 1;
      }
      int i4 = 8;
      int i5 = 1;
      i1 = 0;
      i3 = i6;
      i6 = i1;
      int i7;
      if (i3 < this.v)
      {
        if (!this.q)
          break label460;
        i1 = i6;
        i2 = i4;
        i7 = i5;
        if (i6 >= this.v)
          i7 = i5 + 1;
        switch (i7)
        {
        default:
          i2 = i4;
          i1 = i6;
          i6 = i1 + i2;
          i5 = i7;
        case 2:
        case 3:
        case 4:
        }
      }
      while (true)
      {
        i1 += this.t;
        if (i1 < this.e)
        {
          int i8 = this.d * i1;
          i7 = i8 + this.s;
          i4 = this.u + i7;
          i1 = i4;
          if (this.d + i8 < i4)
            i1 = this.d + i8;
          i4 = this.u * i3;
          while (true)
            if (i7 < i1)
            {
              i8 = this.M[i4];
              i8 = this.k[(i8 & 0xFF)];
              if (i8 != 0)
                arrayOfInt[i7] = i8;
              i7 += 1;
              i4 += 1;
              continue;
              i1 = 4;
              i2 = i4;
              break;
              i1 = 2;
              i2 = 4;
              break;
              i1 = 1;
              i2 = 2;
              break;
            }
        }
        i3 += 1;
        i4 = i2;
        break;
        this.A = Bitmap.createBitmap(arrayOfInt, this.d, this.e, Bitmap.Config.ARGB_4444);
        return;
        i1 = i3;
        i2 = i4;
      }
    }
  }

  protected void e()
  {
    int i16 = this.u * this.v;
    if ((this.M == null) || (this.M.length < i16))
      this.M = new byte[i16];
    if (this.J == null)
      this.J = new short[4096];
    if (this.K == null)
      this.K = new byte[4096];
    if (this.L == null)
      this.L = new byte[4097];
    int i17 = h();
    int i18 = 1 << i17;
    int i7 = i18 + 2;
    int i2 = i17 + 1;
    int i3 = (1 << i2) - 1;
    int i1 = 0;
    while (i1 < i18)
    {
      this.J[i1] = 0;
      this.K[i1] = ((byte)i1);
      i1 += 1;
    }
    int i10 = 0;
    int i4 = 0;
    int i8 = 0;
    int i5 = 0;
    int i13 = 0;
    int i11 = 0;
    int i6 = 0;
    i1 = -1;
    int i12 = 0;
    int i9;
    if (i13 < i16)
    {
      if (i4 != 0)
        break label666;
      if (i6 >= i2)
        break label276;
      i9 = i11;
      if (i11 != 0)
        break label235;
      i9 = i();
      if (i9 > 0)
        break label232;
    }
    label232: label235: int i14;
    label276: int i15;
    while (true)
      if (i10 < i16)
      {
        this.M[i10] = 0;
        i10 += 1;
        continue;
        i12 = 0;
        i5 += ((this.C[i12] & 0xFF) << i6);
        i6 += 8;
        i12 += 1;
        i11 = i9 - 1;
        break;
        i9 = i5 & i3;
        i14 = i5 >> i2;
        i6 -= i2;
        if ((i9 <= i7) && (i9 != i18 + 1))
        {
          if (i9 == i18)
          {
            i2 = i17 + 1;
            i3 = (1 << i2) - 1;
            i7 = i18 + 2;
            i1 = -1;
            i5 = i14;
            break;
          }
          if (i1 == -1)
          {
            this.L[i4] = this.K[i9];
            i4 += 1;
            i1 = i9;
            i8 = i9;
            i5 = i14;
            break;
          }
          if (i9 != i7)
            break label659;
          byte[] arrayOfByte = this.L;
          i15 = i4 + 1;
          arrayOfByte[i4] = ((byte)i8);
          i5 = i1;
          i4 = i15;
          label416: 
          while (i5 > i18)
          {
            this.L[i4] = this.K[i5];
            i5 = this.J[i5];
            i4 += 1;
          }
          i15 = this.K[i5] & 0xFF;
          if (i7 < 4096)
          {
            this.L[i4] = ((byte)i15);
            this.J[i7] = ((short)i1);
            this.K[i7] = ((byte)i15);
            i8 = i7 + 1;
            i1 = i2;
            i5 = i3;
            if ((i8 & i3) == 0)
            {
              i1 = i2;
              i5 = i3;
              if (i8 < 4096)
              {
                i1 = i2 + 1;
                i5 = i3 + i8;
              }
            }
            i3 = i14;
            i7 = i5;
            i5 = i15;
            i14 = i4 + 1;
            i2 = i8;
            i4 = i7;
            i7 = i1;
            i1 = i9;
            i8 = i14;
          }
        }
      }
    while (true)
    {
      i15 = i8 - 1;
      this.M[i10] = this.L[i15];
      i13 += 1;
      i14 = i10 + 1;
      i8 = i7;
      i9 = i3;
      i10 = i15;
      i7 = i2;
      i2 = i8;
      i3 = i4;
      i4 = i10;
      i8 = i5;
      i5 = i9;
      i10 = i14;
      break;
      return;
      label659: i5 = i9;
      break label416;
      label666: i9 = i3;
      i3 = i8;
      i8 = i4;
      i4 = i5;
      i14 = i7;
      i5 = i3;
      i3 = i4;
      i7 = i2;
      i4 = i9;
      i2 = i14;
    }
  }

  protected boolean f()
  {
    return this.c != 0;
  }

  protected void g()
  {
    this.c = 0;
    this.O = 0;
    this.N = new Vector();
    this.i = null;
    this.j = null;
  }

  protected int h()
  {
    try
    {
      int i1 = this.b.read();
      return i1;
    }
    catch (Exception localException)
    {
      this.c = 1;
    }
    return 0;
  }

  protected int i()
  {
    this.D = h();
    int i2 = 0;
    int i1 = 0;
    if (this.D > 0);
    try
    {
      while (true)
      {
        if (i1 < this.D)
        {
          i2 = this.b.read(this.C, i1, this.D - i1);
          if (i2 != -1);
        }
        else
        {
          i2 = i1;
          if (i1 < this.D)
          {
            this.c = 1;
            i2 = i1;
          }
          return i2;
        }
        i1 += i2;
      }
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  protected void j()
  {
    int i1 = 0;
    while ((i1 == 0) && (!f()))
      switch (h())
      {
      default:
        this.c = 1;
        break;
      case 44:
        if (!f())
          m();
        break;
      case 33:
        switch (h())
        {
        default:
          r();
          break;
        case 249:
          k();
          break;
        case 255:
          i();
          StringBuffer localStringBuffer = new StringBuffer();
          int i2 = 0;
          while (i2 < 11)
          {
            localStringBuffer.append((char)this.C[i2]);
            i2 += 1;
          }
          if (localStringBuffer.toString().equals("NETSCAPE2.0"))
            o();
          else
            r();
          break;
        case 254:
          r();
          break;
        case 1:
          r();
        }
        break;
      case 59:
        i1 = 1;
      }
  }

  protected void k()
  {
    boolean bool = true;
    h();
    int i1 = h();
    this.E = ((i1 & 0x1C) >> 2);
    if (this.E == 0)
      this.E = 1;
    if ((i1 & 0x1) != 0);
    while (true)
    {
      this.G = bool;
      this.H = (p() * 10);
      this.I = h();
      h();
      return;
      bool = false;
    }
  }

  protected void l()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i1 = 0;
    while (i1 < 6)
    {
      localStringBuffer.append((char)h());
      i1 += 1;
    }
    if (!localStringBuffer.toString().startsWith("GIF"))
      this.c = 1;
    do
    {
      return;
      n();
    }
    while ((!this.f) || (f()));
    this.i = c(this.g);
    this.m = this.i[this.l];
  }

  protected void m()
  {
    boolean bool2 = true;
    int i1 = 0;
    while (true)
    {
      try
      {
        this.s = p();
        this.t = p();
        this.u = p();
        this.v = p();
        int i2 = h();
        if ((i2 & 0x80) == 0)
          break label294;
        bool1 = true;
        this.p = bool1;
        this.r = ((int)Math.pow(2.0D, (i2 & 0x7) + 1));
        if ((i2 & 0x40) == 0)
          break label300;
        bool1 = bool2;
        this.q = bool1;
        if (this.p)
        {
          this.j = c(this.r);
          this.k = this.j;
          if (this.G)
          {
            i1 = this.k[this.I];
            this.k[this.I] = 0;
          }
          if (this.k == null)
            this.c = 1;
          if (!f());
        }
        else
        {
          this.k = this.i;
          if (this.l != this.I)
            continue;
          this.m = 0;
          continue;
        }
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        this.c = 3;
        return;
      }
      e();
      r();
      if (!f())
      {
        this.A = Bitmap.createBitmap(this.d, this.e, Bitmap.Config.ARGB_4444);
        d();
        this.O += 1;
        this.N.addElement(new a(this.A, this.H));
        if (this.G)
          this.k[this.I] = i1;
        q();
      }
      return;
      label294: boolean bool1 = false;
      continue;
      label300: bool1 = false;
    }
  }

  protected void n()
  {
    this.d = p();
    this.e = p();
    int i1 = h();
    if ((i1 & 0x80) != 0);
    for (boolean bool = true; ; bool = false)
    {
      this.f = bool;
      this.g = (2 << (i1 & 0x7));
      this.l = h();
      this.o = h();
      return;
    }
  }

  protected void o()
  {
    do
    {
      i();
      if (this.C[0] == 1)
        this.h = (this.C[1] & 0xFF | (this.C[2] & 0xFF) << 8);
    }
    while ((this.D > 0) && (!f()));
  }

  protected int p()
  {
    return h() | h() << 8;
  }

  protected void q()
  {
    this.F = this.E;
    this.w = this.s;
    this.x = this.t;
    this.y = this.u;
    this.z = this.v;
    this.B = this.A;
    this.n = this.m;
    this.E = 0;
    this.G = false;
    this.H = 0;
    this.j = null;
  }

  protected void r()
  {
    do
      i();
    while ((this.D > 0) && (!f()));
  }

  private class a
  {
    public Bitmap a;
    public int b;

    public a(Bitmap paramInt, int arg3)
    {
      this.a = paramInt;
      int i;
      this.b = i;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.common.gif.a
 * JD-Core Version:    0.6.2
 */