package com.umeng.message.proguard;

import android.content.Context;
import android.provider.Settings.System;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.zip.Adler32;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class p
{
  static final String a = "dxCRMxhQkdGePGnp";
  static final String b = "mqBRboGZkQPcAkyk";
  private static final String c = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
  private static final Object e = new Object();
  private static p f = null;
  private static final String l = ".DataStorage";
  private static final String m = "ContextData";
  private static final String o = ".UTSystemConfig" + File.separator + "Global";
  private static final String p = "Alvin2";
  private Context d = null;
  private String g = null;
  private q h = null;
  private String i = "xx_utdid_key";
  private String j = "xx_utdid_domain";
  private j k = null;
  private j n = null;

  public p(Context paramContext)
  {
    this.d = paramContext;
    this.n = new j(paramContext, o, "Alvin2", false, true);
    this.k = new j(paramContext, ".DataStorage", "ContextData", false, true);
    this.h = new q();
    this.i = String.format("K_%d", new Object[] { Integer.valueOf(f.b(this.i)) });
    this.j = String.format("D_%d", new Object[] { Integer.valueOf(f.b(this.j)) });
  }

  static long a(m paramm)
  {
    if (paramm != null)
    {
      paramm = String.format("%s%s%s%s%s", new Object[] { paramm.f(), paramm.e(), Long.valueOf(paramm.b()), paramm.d(), paramm.c() });
      if (!f.a(paramm))
      {
        Adler32 localAdler32 = new Adler32();
        localAdler32.reset();
        localAdler32.update(paramm.getBytes());
        return localAdler32.getValue();
      }
    }
    return 0L;
  }

  public static p a(Context paramContext)
  {
    if ((paramContext != null) && (f == null));
    synchronized (e)
    {
      if (f == null)
        f = new p(paramContext);
      return f;
    }
  }

  private static String a(byte[] paramArrayOfByte)
    throws Exception
  {
    Mac localMac = Mac.getInstance("HmacSHA1");
    localMac.init(new SecretKeySpec("d6fc3a4a06adbde89223bvefedc24fecde188aaa9161".getBytes(), localMac.getAlgorithm()));
    return b.b(localMac.doFinal(paramArrayOfByte), 2);
  }

  private void a(String paramString)
  {
    String str1 = paramString;
    if (paramString != null)
    {
      str1 = paramString;
      if (paramString.endsWith("\n"))
        str1 = paramString.substring(0, paramString.length() - 1);
    }
    if ((str1 != null) && (str1.length() == 24) && (this.n != null))
    {
      String str2 = this.n.b("UTDID");
      Object localObject1 = this.n.b("EI");
      paramString = (String)localObject1;
      if (f.a((String)localObject1))
        paramString = e.a(this.d);
      Object localObject2 = this.n.b("SI");
      localObject1 = localObject2;
      if (f.a((String)localObject2))
        localObject1 = e.b(this.d);
      Object localObject3 = this.n.b("DID");
      localObject2 = localObject3;
      if (f.a((String)localObject3))
        localObject2 = paramString;
      if ((str2 == null) || (!str2.equals(str1)))
      {
        localObject3 = new m();
        ((m)localObject3).a(paramString);
        ((m)localObject3).b((String)localObject1);
        ((m)localObject3).d(str1);
        ((m)localObject3).c((String)localObject2);
        ((m)localObject3).b(System.currentTimeMillis());
        this.n.a("UTDID", str1);
        this.n.a("EI", ((m)localObject3).c());
        this.n.a("SI", ((m)localObject3).d());
        this.n.a("DID", ((m)localObject3).e());
        this.n.a("timestamp", ((m)localObject3).b());
        this.n.a("S", a((m)localObject3));
        this.n.c();
      }
    }
  }

  private String b()
  {
    if (this.n != null)
    {
      String str = this.n.b("UTDID");
      if ((!f.a(str)) && (this.h.a(str) != null))
        return str;
    }
    return null;
  }

  private void b(String paramString)
  {
    if ((paramString != null) && (this.k != null) && (!paramString.equals(this.k.b(this.i))))
    {
      this.k.a(this.i, paramString);
      this.k.c();
    }
  }

  private void c(String paramString)
  {
    if ((this.d.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) && (paramString != null))
    {
      String str = paramString;
      if (paramString.endsWith("\n"))
        str = paramString.substring(0, paramString.length() - 1);
      if ((24 == str.length()) && (f.a(Settings.System.getString(this.d.getContentResolver(), "mqBRboGZkQPcAkyk"))))
        Settings.System.putString(this.d.getContentResolver(), "mqBRboGZkQPcAkyk", str);
    }
  }

  private final byte[] c()
    throws Exception
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int i1 = (int)(System.currentTimeMillis() / 1000L);
    int i2 = new Random().nextInt();
    Object localObject = c.a(i1);
    byte[] arrayOfByte = c.a(i2);
    localByteArrayOutputStream.write((byte[])localObject, 0, 4);
    localByteArrayOutputStream.write(arrayOfByte, 0, 4);
    localByteArrayOutputStream.write(3);
    localByteArrayOutputStream.write(0);
    try
    {
      localObject = e.a(this.d);
      localByteArrayOutputStream.write(c.a(f.b((String)localObject)), 0, 4);
      localByteArrayOutputStream.write(c.a(f.b(a(localByteArrayOutputStream.toByteArray()))));
      return localByteArrayOutputStream.toByteArray();
    }
    catch (Exception localException)
    {
      while (true)
        String str = "" + new Random().nextInt();
    }
  }

  private void d(String paramString)
  {
    if (!paramString.equals(Settings.System.getString(this.d.getContentResolver(), "dxCRMxhQkdGePGnp")))
      Settings.System.putString(this.d.getContentResolver(), "dxCRMxhQkdGePGnp", paramString);
  }

  private void e(String paramString)
  {
    if ((this.d.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) && (paramString != null))
      d(paramString);
  }

  private boolean f(String paramString)
  {
    return (paramString != null) && ((24 == paramString.length()) || ((25 == paramString.length()) && (paramString.endsWith("\n"))));
  }

  public String a()
  {
    String str2;
    r localr;
    int i1;
    Object localObject2;
    while (true)
    {
      try
      {
        if (this.g != null)
        {
          localObject1 = this.g;
          return localObject1;
        }
        str2 = Settings.System.getString(this.d.getContentResolver(), "mqBRboGZkQPcAkyk");
        if (str2 != null)
        {
          localObject1 = str2;
          if (24 == str2.length())
            continue;
        }
        localr = new r();
        i1 = 0;
        Object localObject1 = Settings.System.getString(this.d.getContentResolver(), "dxCRMxhQkdGePGnp");
        if (f.a((String)localObject1))
          break label422;
        str2 = localr.b((String)localObject1);
        if (f(str2))
        {
          c(str2);
          localObject1 = str2;
          continue;
        }
      }
      finally
      {
      }
      str2 = localr.a(str1);
      if (str2 == null)
        break label419;
      str2 = this.h.a(str2);
      if (f.a(str2))
        break label419;
      e(str2);
      localObject2 = Settings.System.getString(this.d.getContentResolver(), "dxCRMxhQkdGePGnp");
      label150: str2 = this.h.b((String)localObject2);
      if (!f(str2))
        break;
      this.g = str2;
      a(str2);
      b((String)localObject2);
      c(this.g);
      localObject2 = this.g;
    }
    while (true)
    {
      while (true)
      {
        localObject2 = b();
        if (f((String)localObject2))
        {
          str2 = this.h.a((String)localObject2);
          if (i1 != 0)
            e(str2);
          c((String)localObject2);
          b(str2);
          this.g = ((String)localObject2);
          break;
        }
        String str3 = this.k.b(this.i);
        if (!f.a(str3))
        {
          str2 = localr.a(str3);
          localObject2 = str2;
          if (str2 == null)
            localObject2 = this.h.b(str3);
          if (f((String)localObject2))
          {
            str2 = this.h.a((String)localObject2);
            if (!f.a((String)localObject2))
            {
              this.g = ((String)localObject2);
              if (i1 != 0)
                e(str2);
              a(this.g);
              localObject2 = this.g;
              break;
            }
          }
        }
        try
        {
          localObject2 = c();
          if (localObject2 != null)
          {
            this.g = b.b((byte[])localObject2, 2);
            a(this.g);
            localObject2 = this.h.a((byte[])localObject2);
            if (localObject2 != null)
            {
              if (i1 != 0)
                e((String)localObject2);
              b((String)localObject2);
            }
            localObject2 = this.g;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          Object localObject3 = null;
        }
      }
      break;
      label419: break label150;
      label422: i1 = 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.p
 * JD-Core Version:    0.6.2
 */