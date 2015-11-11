package com.taobao.munion.base.utdid.c;

import android.content.Context;
import android.provider.Settings.System;
import com.taobao.munion.base.utdid.a.a.b;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.zip.Adler32;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class d
{
  static final String a = "dxCRMxhQkdGePGnp";
  static final String b = "mqBRboGZkQPcAkyk";
  private static final String c = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
  private static final Object e = new Object();
  private static d f = null;
  private static final String l = ".DataStorage";
  private static final String m = "ContextData";
  private static final String o = ".UTSystemConfig" + File.separator + "Global";
  private static final String p = "Alvin2";
  private Context d = null;
  private String g = null;
  private e h = null;
  private String i = "xx_utdid_key";
  private String j = "xx_utdid_domain";
  private com.taobao.munion.base.utdid.b.a.c k = null;
  private com.taobao.munion.base.utdid.b.a.c n = null;

  public d(Context paramContext)
  {
    this.d = paramContext;
    this.n = new com.taobao.munion.base.utdid.b.a.c(paramContext, o, "Alvin2", false, true);
    this.k = new com.taobao.munion.base.utdid.b.a.c(paramContext, ".DataStorage", "ContextData", false, true);
    this.h = new e();
    this.i = String.format("K_%d", new Object[] { Integer.valueOf(com.taobao.munion.base.utdid.a.a.f.b(this.i)) });
    this.j = String.format("D_%d", new Object[] { Integer.valueOf(com.taobao.munion.base.utdid.a.a.f.b(this.j)) });
  }

  static long a(a parama)
  {
    if (parama != null)
    {
      parama = String.format("%s%s%s%s%s", new Object[] { parama.f(), parama.e(), Long.valueOf(parama.b()), parama.d(), parama.c() });
      if (!com.taobao.munion.base.utdid.a.a.f.a(parama))
      {
        Adler32 localAdler32 = new Adler32();
        localAdler32.reset();
        localAdler32.update(parama.getBytes());
        return localAdler32.getValue();
      }
    }
    return 0L;
  }

  public static d a(Context paramContext)
  {
    if ((paramContext != null) && (f == null));
    synchronized (e)
    {
      if (f == null)
        f = new d(paramContext);
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
      if (com.taobao.munion.base.utdid.a.a.f.a((String)localObject1))
        paramString = com.taobao.munion.base.utdid.a.a.e.a(this.d);
      Object localObject2 = this.n.b("SI");
      localObject1 = localObject2;
      if (com.taobao.munion.base.utdid.a.a.f.a((String)localObject2))
        localObject1 = com.taobao.munion.base.utdid.a.a.e.b(this.d);
      Object localObject3 = this.n.b("DID");
      localObject2 = localObject3;
      if (com.taobao.munion.base.utdid.a.a.f.a((String)localObject3))
        localObject2 = paramString;
      if ((str2 == null) || (!str2.equals(str1)))
      {
        localObject3 = new a();
        ((a)localObject3).a(paramString);
        ((a)localObject3).b((String)localObject1);
        ((a)localObject3).d(str1);
        ((a)localObject3).c((String)localObject2);
        ((a)localObject3).b(System.currentTimeMillis());
        this.n.a("UTDID", str1);
        this.n.a("EI", ((a)localObject3).c());
        this.n.a("SI", ((a)localObject3).d());
        this.n.a("DID", ((a)localObject3).e());
        this.n.a("timestamp", ((a)localObject3).b());
        this.n.a("S", a((a)localObject3));
        this.n.c();
      }
    }
  }

  private String b()
  {
    if (this.n != null)
    {
      String str = this.n.b("UTDID");
      if ((!com.taobao.munion.base.utdid.a.a.f.a(str)) && (this.h.a(str) != null))
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
      if ((24 == str.length()) && (com.taobao.munion.base.utdid.a.a.f.a(Settings.System.getString(this.d.getContentResolver(), "mqBRboGZkQPcAkyk"))))
        Settings.System.putString(this.d.getContentResolver(), "mqBRboGZkQPcAkyk", str);
    }
  }

  private final byte[] c()
    throws Exception
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int i1 = (int)(System.currentTimeMillis() / 1000L);
    int i2 = new Random().nextInt();
    Object localObject = com.taobao.munion.base.utdid.a.a.c.a(i1);
    byte[] arrayOfByte = com.taobao.munion.base.utdid.a.a.c.a(i2);
    localByteArrayOutputStream.write((byte[])localObject, 0, 4);
    localByteArrayOutputStream.write(arrayOfByte, 0, 4);
    localByteArrayOutputStream.write(3);
    localByteArrayOutputStream.write(0);
    try
    {
      localObject = com.taobao.munion.base.utdid.a.a.e.a(this.d);
      localByteArrayOutputStream.write(com.taobao.munion.base.utdid.a.a.c.a(com.taobao.munion.base.utdid.a.a.f.b((String)localObject)), 0, 4);
      localByteArrayOutputStream.write(com.taobao.munion.base.utdid.a.a.c.a(com.taobao.munion.base.utdid.a.a.f.b(a(localByteArrayOutputStream.toByteArray()))));
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
    f localf;
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
        localf = new f();
        i1 = 0;
        Object localObject1 = Settings.System.getString(this.d.getContentResolver(), "dxCRMxhQkdGePGnp");
        if (com.taobao.munion.base.utdid.a.a.f.a((String)localObject1))
          break label422;
        str2 = localf.b((String)localObject1);
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
      str2 = localf.a(str1);
      if (str2 == null)
        break label419;
      str2 = this.h.a(str2);
      if (com.taobao.munion.base.utdid.a.a.f.a(str2))
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
        if (!com.taobao.munion.base.utdid.a.a.f.a(str3))
        {
          str2 = localf.a(str3);
          localObject2 = str2;
          if (str2 == null)
            localObject2 = this.h.b(str3);
          if (f((String)localObject2))
          {
            str2 = this.h.a((String)localObject2);
            if (!com.taobao.munion.base.utdid.a.a.f.a((String)localObject2))
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
 * Qualified Name:     com.taobao.munion.base.utdid.c.d
 * JD-Core Version:    0.6.2
 */