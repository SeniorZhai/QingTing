package com.taobao.newxp.view.common.gif;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class d extends Thread
{
  private static final int O = 4096;
  private static final int U = 15;
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  public static final int d = -1;
  private int A;
  private int B;
  private int C;
  private int D;
  private int E;
  private Bitmap F;
  private Bitmap G;
  private byte[] H = new byte[256];
  private int I = 0;
  private int J = 0;
  private int K = 0;
  private boolean L = false;
  private int M = 0;
  private int N;
  private short[] P;
  private byte[] Q;
  private byte[] R;
  private byte[] S;
  private int T;
  private Queue<f> V = new ArrayBlockingQueue(15);
  private final ReentrantLock W = new ReentrantLock();
  private final Condition X = this.W.newCondition();
  private final Condition Y = this.W.newCondition();
  private int Z = 0;
  private boolean aa = false;
  private ArrayList<f> ab = new ArrayList(this.T);
  private int ac = 0;
  private boolean ad = false;
  private b ae = null;
  private byte[] af = null;
  private boolean ag = false;
  private int ah = 0;
  private Resources ai = null;
  private int aj = 0;
  private String ak = null;
  private int[] al = null;
  private int[] am = new int[256];
  public boolean e = false;
  public int f;
  public int g;
  private InputStream h;
  private InputStream i;
  private volatile int j;
  private boolean k;
  private int l;
  private int m = 1;
  private int[] n;
  private int[] o;
  private int[] p;
  private int q;
  private int r;
  private int s;
  private int t;
  private boolean u;
  private boolean v;
  private int w;
  private int x;
  private int y;
  private int z;

  public d(b paramb)
  {
    this.ae = paramb;
  }

  public d(b paramb, boolean paramBoolean)
  {
    this.ae = paramb;
    this.ag = paramBoolean;
  }

  private void A()
  {
    do
    {
      u();
      if (this.H[0] == 1)
        this.m = (this.H[1] & 0xFF | (this.H[2] & 0xFF) << 8);
    }
    while ((this.I > 0) && (!r()));
  }

  private int B()
  {
    return t() | t() << 8;
  }

  private void C()
  {
    this.K = this.J;
    this.B = this.x;
    this.C = this.y;
    this.D = this.z;
    this.E = this.A;
    this.G = this.F;
    this.s = this.r;
    this.J = 0;
    this.L = false;
    this.M = 0;
    this.o = null;
  }

  private void D()
  {
    do
      u();
    while ((this.I > 0) && (!r()));
  }

  private int[] a(int paramInt)
  {
    int i2 = 0;
    int i3 = paramInt * 3;
    byte[] arrayOfByte = new byte[i3];
    while (true)
    {
      int i1;
      try
      {
        i1 = this.h.read(arrayOfByte);
        if (i1 < i3)
        {
          this.j = 1;
          return this.am;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        i1 = 0;
        continue;
        i3 = 0;
        i1 = i2;
        i2 = i3;
      }
      while (i1 < paramInt)
      {
        int i5 = i2 + 1;
        i3 = arrayOfByte[i2];
        int i4 = i5 + 1;
        i5 = arrayOfByte[i5];
        i2 = i4 + 1;
        i4 = arrayOfByte[i4];
        this.am[i1] = ((i3 & 0xFF) << 16 | 0xFF000000 | (i5 & 0xFF) << 8 | i4 & 0xFF);
        i1 += 1;
      }
    }
  }

  private void j()
  {
    this.h = new ByteArrayInputStream(this.af);
  }

  private void k()
  {
    this.h = this.ai.openRawResource(this.aj);
  }

  private void l()
  {
    try
    {
      this.h = new FileInputStream(this.ak);
      return;
    }
    catch (Exception localException)
    {
      Log.e("open failed", localException.toString());
    }
  }

  private void m()
  {
    if (this.h != null);
    try
    {
      this.h.close();
      label14: this.h = null;
      this.af = null;
      this.j = 0;
      if (this.ab != null)
      {
        this.ab.clear();
        this.ab = null;
      }
      if (this.V != null)
      {
        this.V.clear();
        this.V = null;
      }
      return;
    }
    catch (Exception localException)
    {
      break label14;
    }
  }

  private void n()
  {
    while (true)
    {
      int i6;
      int i7;
      label347: int i8;
      try
      {
        if (this.al == null)
          this.al = new int[this.f * this.g];
        if (this.K > 0)
        {
          if (this.K == 3)
          {
            if (this.T - 2 > 0)
              this.G = null;
          }
          else
          {
            if (this.G == null)
              continue;
            this.G.getPixels(this.al, 0, this.f, 0, 0, this.f, this.g);
            if (this.K != 2)
              continue;
            if (this.L)
              break label448;
            i2 = this.s;
            break label450;
            if (i3 >= this.E)
              continue;
            i4 = (this.C + i3) * this.f + this.B;
            i5 = this.D;
            i1 = i4;
            if (i1 >= i5 + i4)
              continue;
            this.al[i1] = i2;
            i1 += 1;
            continue;
          }
          this.G = null;
          continue;
        }
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        localOutOfMemoryError.printStackTrace();
        return;
        i3 += 1;
        continue;
        i2 = 8;
        i6 = 0;
        i4 = 0;
        i3 = 1;
        if (i6 < this.A)
        {
          if (!this.v)
            break label438;
          i1 = i4;
          i5 = i2;
          i7 = i3;
          if (i4 < this.A)
            break label462;
          i7 = i3 + 1;
        }
        switch (i7)
        {
        default:
          i4 += this.y;
          if (i4 >= this.g)
            break label523;
          i5 = i4 * this.f;
          i7 = i5 + this.x;
          i4 = this.z + i7;
          if (this.f + i5 < i4)
          {
            i4 = this.f + i5;
            i5 = this.z * i6;
            if (i7 >= i4)
              break label523;
            i8 = this.S[i5];
            i8 = this.p[(i8 & 0xFF)];
            if (i8 == 0)
              break label484;
            this.al[i7] = i8;
            break label484;
            this.F = Bitmap.createBitmap(this.al, this.f, this.g, Bitmap.Config.RGB_565);
            return;
          }
          break;
        case 2:
        case 3:
        case 4:
        }
      }
      catch (StackOverflowError localStackOverflowError)
      {
        localStackOverflowError.printStackTrace();
        return;
      }
      catch (Exception localException)
      {
        Log.e("GifView decode setpixel", localException.toString());
        return;
      }
      continue;
      label438: int i1 = i4;
      int i4 = i6;
      continue;
      label448: int i2 = 0;
      label450: int i3 = 0;
      continue;
      int i5 = i2;
      i1 = i4;
      while (true)
      {
        label462: i8 = i1 + i5;
        i2 = i5;
        i3 = i7;
        i4 = i1;
        i1 = i8;
        break;
        label484: i7 += 1;
        i5 += 1;
        break label347;
        i1 = 4;
        i5 = i2;
        continue;
        i1 = 2;
        i5 = 4;
        continue;
        i1 = 1;
        i5 = 2;
      }
      label523: i6 += 1;
      i4 = i1;
    }
  }

  private int o()
  {
    s();
    if (this.h != null)
    {
      x();
      if (!r())
      {
        v();
        if ((!this.aa) && (this.T < 0))
        {
          this.j = 1;
          if (this.ae != null)
            this.ae.parseReturn(4);
        }
      }
    }
    while (true)
    {
      try
      {
        if (this.h != null)
          this.h.close();
        this.h = null;
        p();
        return this.j;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        continue;
      }
      this.j = 2;
      if (this.ae != null)
        this.ae.parseReturn(4);
    }
  }

  private void p()
  {
    if ((!this.ag) || (this.ad))
      label14: return;
    if (this.T <= 15);
    while (true)
    {
      try
      {
        this.W.lockInterruptibly();
        this.ad = true;
        this.j = -1;
        if (this.ae != null)
          this.ae.parseReturn(2);
        this.X.signal();
        this.W.unlock();
        switch (this.ah)
        {
        default:
          this.aa = true;
          if (this.e)
            break label14;
          o();
          return;
        case 1:
        case 2:
        case 3:
        }
      }
      catch (Exception localException)
      {
        this.W.unlock();
        continue;
      }
      finally
      {
        this.W.unlock();
      }
      if (this.ab != null)
      {
        this.ab.clear();
        continue;
        k();
        continue;
        l();
        continue;
        j();
      }
    }
  }

  private void q()
  {
    int i16 = this.z * this.A;
    if ((this.S == null) || (this.S.length < i16))
      this.S = new byte[i16];
    if (this.P == null)
      this.P = new short[4096];
    if (this.Q == null)
      this.Q = new byte[4096];
    if (this.R == null)
      this.R = new byte[4097];
    int i17 = t();
    int i18 = 1 << i17;
    int i7 = i18 + 2;
    int i2 = i17 + 1;
    int i3 = (1 << i2) - 1;
    int i1 = 0;
    while (i1 < i18)
    {
      this.P[i1] = 0;
      this.Q[i1] = ((byte)i1);
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
      i9 = u();
      if (i9 > 0)
        break label232;
    }
    label232: label235: int i14;
    label276: int i15;
    while (true)
      if (i10 < i16)
      {
        this.S[i10] = 0;
        i10 += 1;
        continue;
        i12 = 0;
        i5 += ((this.H[i12] & 0xFF) << i6);
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
            this.R[i4] = this.Q[i9];
            i4 += 1;
            i1 = i9;
            i8 = i9;
            i5 = i14;
            break;
          }
          if (i9 != i7)
            break label659;
          byte[] arrayOfByte = this.R;
          i15 = i4 + 1;
          arrayOfByte[i4] = ((byte)i8);
          i5 = i1;
          i4 = i15;
          label416: 
          while (i5 > i18)
          {
            this.R[i4] = this.Q[i5];
            i5 = this.P[i5];
            i4 += 1;
          }
          i15 = this.Q[i5] & 0xFF;
          if (i7 < 4096)
          {
            this.R[i4] = ((byte)i15);
            this.P[i7] = ((short)i1);
            this.Q[i7] = ((byte)i15);
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
      this.S[i10] = this.R[i15];
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

  private boolean r()
  {
    return this.j != 0;
  }

  private void s()
  {
    this.j = 0;
    if (!this.aa)
      this.T = 0;
    this.n = null;
    this.o = null;
    this.Z = 0;
  }

  private int t()
  {
    try
    {
      int i1 = this.h.read();
      return i1;
    }
    catch (Exception localException)
    {
      this.j = 1;
    }
    return 0;
  }

  private int u()
  {
    this.I = t();
    int i2 = 0;
    int i1 = 0;
    if (this.I > 0);
    try
    {
      while (true)
      {
        if (i1 < this.I)
        {
          i2 = this.h.read(this.H, i1, this.I - i1);
          if (i2 != -1);
        }
        else
        {
          i2 = i1;
          if (i1 < this.I)
          {
            this.j = 1;
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

  private void v()
  {
    int i1 = 0;
    while ((i1 == 0) && (!r()) && (!this.e))
      switch (t())
      {
      case 0:
      default:
        this.j = 1;
        break;
      case 44:
        y();
        break;
      case 33:
        switch (t())
        {
        default:
          D();
          break;
        case 249:
          w();
          break;
        case 255:
          u();
          String str = "";
          int i2 = 0;
          while (i2 < 11)
          {
            str = str + (char)this.H[i2];
            i2 += 1;
          }
          if (str.equals("NETSCAPE2.0"))
            A();
          else
            D();
          break;
        }
        break;
      case 59:
        i1 = 1;
      }
  }

  private void w()
  {
    boolean bool = true;
    t();
    int i1 = t();
    this.J = ((i1 & 0x1C) >> 2);
    if (this.J == 0)
      this.J = 1;
    if ((i1 & 0x1) != 0);
    while (true)
    {
      this.L = bool;
      this.M = (B() * 10);
      if (this.M == 0)
        this.M = 100;
      this.N = t();
      t();
      return;
      bool = false;
    }
  }

  private void x()
  {
    String str = "";
    int i1 = 0;
    while (i1 < 6)
    {
      str = str + (char)t();
      i1 += 1;
    }
    if (!str.startsWith("GIF"))
      this.j = 1;
    do
    {
      return;
      z();
    }
    while ((!this.k) || (r()));
    this.n = a(this.l);
    this.r = this.n[this.q];
  }

  private void y()
  {
    int i2 = 0;
    this.x = B();
    this.y = B();
    this.z = B();
    this.A = B();
    int i1 = t();
    boolean bool;
    if ((i1 & 0x80) != 0)
    {
      bool = true;
      this.u = bool;
      if ((i1 & 0x40) == 0)
        break label197;
      bool = true;
      label66: this.v = bool;
      this.w = (2 << (i1 & 0x7));
      if (!this.u)
        break label203;
      this.o = a(this.w);
      this.p = this.o;
      label109: i1 = i2;
      if (this.L)
      {
        i1 = i2;
        if (this.p != null)
        {
          i1 = i2;
          if (this.p.length > 0)
          {
            i1 = i2;
            if (this.p.length > this.N)
            {
              i1 = this.p[this.N];
              this.p[this.N] = 0;
            }
          }
        }
      }
      if (this.p == null)
        this.j = 1;
      if (!r())
        break label230;
    }
    label197: label203: label230: 
    do
    {
      return;
      bool = false;
      break;
      bool = false;
      break label66;
      this.p = this.n;
      if (this.q != this.N)
        break label109;
      this.r = 0;
      break label109;
      q();
      D();
    }
    while (r());
    if (!this.aa)
      this.T += 1;
    n();
    try
    {
      this.W.lockInterruptibly();
    }
    catch (Exception localException)
    {
      try
      {
        while ((this.V != null) && (this.V.size() >= 15))
          this.Y.await();
      }
      catch (InterruptedException localInterruptedException)
      {
        this.Y.signal();
        this.W.unlock();
        if (this.L)
          this.p[this.N] = i1;
        C();
        return;
        if (this.V != null)
        {
          f localf = new f(this.F, this.M);
          this.V.add(localf);
          if (!this.aa)
            this.ab.add(localf);
          this.X.signal();
          if ((!this.aa) && (this.Z >= 0))
          {
            this.Z += 1;
            if (this.Z < 15)
              break label470;
            this.ae.parseReturn(3);
            this.Z = -1;
          }
        }
        while (true)
        {
          this.W.unlock();
          break;
          localException = localException;
          localException.printStackTrace();
          break;
          label470: if (this.Z == 1)
            this.ae.parseReturn(1);
        }
      }
      finally
      {
        this.W.unlock();
      }
    }
  }

  private void z()
  {
    this.f = B();
    this.g = B();
    int i1 = t();
    if ((i1 & 0x80) != 0);
    for (boolean bool = true; ; bool = false)
    {
      this.k = bool;
      this.l = (2 << (i1 & 0x7));
      this.q = t();
      this.t = t();
      return;
    }
  }

  public void a()
  {
    this.ag = true;
  }

  public void a(Resources paramResources, int paramInt)
  {
    this.ai = paramResources;
    this.aj = paramInt;
    k();
    this.ah = 1;
  }

  public void a(String paramString)
  {
    this.ak = paramString;
    this.ah = 2;
    l();
  }

  public void a(byte[] paramArrayOfByte)
  {
    this.af = paramArrayOfByte;
    j();
    this.ah = 3;
  }

  public InputStream b()
  {
    return this.i;
  }

  public int c()
  {
    return this.j;
  }

  public int d()
  {
    if ((!this.aa) && (this.j != -1))
      return -1;
    return this.T;
  }

  public void destroy()
  {
    this.e = true;
    m();
    this.ae = null;
  }

  public Bitmap e()
  {
    return g();
  }

  public int f()
  {
    return this.m;
  }

  public Bitmap g()
  {
    f localf = h();
    if (localf == null)
      return null;
    return localf.a;
  }

  public f h()
  {
    if ((this.ad) && (this.V.size() == 0))
      try
      {
        if (this.ac >= this.T)
        {
          this.ac = 0;
          this.ae.loopEnd();
        }
        f localf1 = (f)this.ab.get(this.ac);
        this.ac += 1;
        return localf1;
      }
      catch (Exception localException1)
      {
        return null;
      }
    try
    {
      this.W.lockInterruptibly();
      try
      {
        while ((!this.ad) && (this.V.size() == 0))
          this.X.await();
      }
      catch (Exception localException2)
      {
        this.X.signal();
        return null;
      }
      f localf2 = (f)this.V.poll();
      this.Y.signal();
      this.ac += 1;
      if ((this.aa) && (this.ac >= this.T))
      {
        this.ae.loopEnd();
        this.ac = 0;
      }
      return localf2;
    }
    catch (Exception localException3)
    {
      return null;
    }
    finally
    {
      this.W.unlock();
    }
  }

  public f i()
  {
    return h();
  }

  public void run()
  {
    try
    {
      o();
      return;
    }
    catch (Exception localException)
    {
      Log.e("GifView decode run", localException.toString());
      localException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.common.gif.d
 * JD-Core Version:    0.6.2
 */