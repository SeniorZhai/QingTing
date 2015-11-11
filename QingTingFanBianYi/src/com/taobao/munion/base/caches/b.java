package com.taobao.munion.base.caches;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.munion.base.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class b
  implements v
{
  public static final String a = "_wvcrc=";
  public static Context b;
  public static String c = "";
  public static String d = "";
  public static String e = "";
  public static boolean f = false;
  public static boolean g = false;
  public static String h = "";
  public static String i = "";
  public static boolean j = false;
  private static final String k = "CacheManager";
  private static final String l = "wvcache";
  private static final int m = 150;
  private static final String n = "wvimage";
  private static final int o = 200;
  private static final String p = "yyz.config";
  private static b q;
  private g r;
  private g s;
  private int t;
  private boolean u = false;
  private boolean v = false;

  public static b a()
  {
    try
    {
      if (q == null)
        q = new b();
      b localb = q;
      return localb;
    }
    finally
    {
    }
  }

  private Map<String, String> a(w paramw)
  {
    Object localObject = null;
    if (paramw != null)
    {
      HashMap localHashMap = new HashMap();
      if (!TextUtils.isEmpty(paramw.g()))
        localHashMap.put("If-None-Match", paramw.g());
      localObject = localHashMap;
      if (paramw.h() > 0L)
      {
        localHashMap.put("If-Modified-Since", e.a(paramw.h()));
        localObject = localHashMap;
      }
    }
    return localObject;
  }

  private void a(i parami)
  {
    if (this.r == null)
      return;
    if (e.f(parami.d()))
    {
      this.s.a(parami);
      return;
    }
    this.r.a(parami);
  }

  public w a(String paramString1, String paramString2)
  {
    m localm = new m();
    t.a.add(localm);
    Object localObject2 = u.f(paramString1);
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2))
      localObject1 = "text/html";
    localObject2 = new d(paramString1, localm);
    w localw = new w();
    localw.c((String)localObject1);
    localw.b("utf-8");
    localw.e = ((InputStream)localObject2);
    t.b().a(new q(paramString1, this, paramString2, localm));
    return localw;
  }

  public String a(boolean paramBoolean)
  {
    if (this.r == null)
      return null;
    if (paramBoolean)
      return this.s.a();
    return this.r.a();
  }

  public void a(Context paramContext)
  {
    a(paramContext, null);
  }

  public void a(Context paramContext, String paramString)
  {
    if (paramContext == null)
      try
      {
        throw new NullPointerException("CacheManager init error, context is null");
      }
      finally
      {
      }
    boolean bool = this.v;
    if (bool);
    while (true)
    {
      return;
      b = paramContext.getApplicationContext();
      Log.i("cache start init", new Object[0]);
      if (this.r == null)
      {
        this.r = h.a().a(paramString, "wvcache", 150, true);
        this.s = h.a().a(paramString, "wvimage", 200, true);
      }
      this.v = true;
    }
  }

  public void a(String paramString)
  {
    c = paramString;
    if (c(paramString) != null)
      a().d(paramString);
    a().a(paramString, new w(), "");
  }

  public void a(String paramString1, w paramw, String paramString2)
  {
    t.b().a(new q(paramString1, this, paramString2, null));
  }

  public void a(byte[] paramArrayOfByte, Map<String, String> paramMap, String paramString, m paramm)
  {
    if (paramMap == null)
    {
      if (paramm != null)
        paramm.b();
      return;
    }
    String str1 = (String)paramMap.get("url");
    paramString = (String)paramMap.get("response-code");
    long l2 = c.a().c(str1);
    long l3 = l2;
    while (true)
    {
      int i2;
      int i1;
      try
      {
        i2 = 13 - Long.valueOf(l2).toString().length();
        if (i2 <= 0)
          break label525;
        i1 = 0;
        break label497;
        l3 = l2;
        l1 = l2;
        if (i1 < Math.abs(i2))
        {
          l3 = l2;
          l2 /= 10L;
          i1 += 1;
          continue;
        }
      }
      catch (Exception localException)
      {
        l1 = l3;
        str1 = com.taobao.munion.base.e.a(str1);
        if ("304".equals(paramString))
        {
          paramArrayOfByte = s.a().a(str1);
          if (paramArrayOfByte != null)
            paramArrayOfByte.a(l1);
          paramMap = this.r.a(str1);
          paramArrayOfByte = paramMap;
          if (paramMap == null)
            paramArrayOfByte = this.s.a(str1);
          if (paramArrayOfByte == null)
            break;
          paramArrayOfByte.a(l1);
          a(paramArrayOfByte);
          return;
        }
      }
      if ((!"200".equals(paramString)) || (paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
        break;
      paramString = (String)paramMap.get("content-type");
      if (TextUtils.isEmpty(paramString))
        paramString = (String)paramMap.get("Content-Type");
      while (true)
      {
        String str2 = (String)paramMap.get("last-modified");
        String str3 = (String)paramMap.get("etag");
        String str4 = e.c(paramString);
        paramString = e.b(paramString);
        paramMap = paramString;
        if (TextUtils.isEmpty(paramString))
          paramMap = "utf-8";
        l3 = e.e(str2);
        l2 = l3;
        if (l3 <= 0L)
          l2 = System.currentTimeMillis();
        paramString = new w();
        paramString.a(str1);
        paramString.c(str4);
        paramString.d(str3);
        paramString.b(paramMap);
        paramString.c(l2);
        l2 = l1;
        if (l1 <= 0L)
          if (!e.g(str4))
            break label482;
        label482: for (l2 = System.currentTimeMillis() + 1800000L; ; l2 = System.currentTimeMillis() + 604800000L)
        {
          paramString.a(l2);
          paramString.d = paramArrayOfByte.length;
          paramString.e = new ByteArrayInputStream(paramArrayOfByte);
          if (s.a().c())
          {
            s.a().a(str1, paramString);
            if (paramm != null)
            {
              t.b().a(paramm);
              paramm.b();
            }
          }
          a(paramString, paramArrayOfByte);
          if (paramm == null)
            break;
          t.b().a(paramm);
          paramm.b();
          return;
        }
      }
      while (true)
      {
        label497: l1 = l2;
        if (i1 >= i2)
          break;
        i1 += 1;
        l2 = 10L * l2;
      }
      label525: long l1 = l2;
      if (i2 < 0)
        i1 = 0;
    }
  }

  public boolean a(i parami, byte[] paramArrayOfByte)
  {
    if (this.r == null);
    ByteBuffer localByteBuffer;
    do
    {
      return false;
      localByteBuffer = ByteBuffer.wrap(paramArrayOfByte);
      if (e.f(parami.d()))
        return this.s.a(parami, localByteBuffer);
      paramArrayOfByte = com.taobao.munion.base.e.a(paramArrayOfByte);
    }
    while (paramArrayOfByte == null);
    parami.e(paramArrayOfByte);
    return this.r.a(parami, localByteBuffer);
  }

  public boolean a(String paramString1, String paramString2, long paramLong)
  {
    try
    {
      paramString1 = com.taobao.munion.base.e.a(paramString1);
      w localw = new w();
      localw.a(paramString1);
      localw.c("none");
      localw.d("");
      localw.b("utf-8");
      localw.c(0L);
      localw.a(System.currentTimeMillis() + 1000L * paramLong);
      paramString2 = paramString2.getBytes("utf-8");
      localw.d = paramString2.length;
      localw.e = new ByteArrayInputStream(paramString2);
      if (s.a().c())
        s.a().a(paramString1, localw);
      a(localw, paramString2);
      return true;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }

  public File b(boolean paramBoolean)
  {
    if (this.r == null)
      return null;
    if (paramBoolean);
    for (Object localObject = this.s.a() + File.separator + "temp"; ; localObject = this.r.a() + File.separator + "temp")
    {
      localObject = new File((String)localObject);
      if (!((File)localObject).exists())
        ((File)localObject).mkdir();
      return localObject;
    }
  }

  public void b(Context paramContext)
  {
    if (this.r == null)
      return;
    try
    {
      s.a().b();
      t.b().a(new Runnable()
      {
        public void run()
        {
          b.a(b.this).e();
          b.b(b.this).e();
        }
      });
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public boolean b()
  {
    if (this.r == null)
      return true;
    return this.r.b();
  }

  public boolean b(String paramString)
  {
    return c.a().a(paramString);
  }

  public int c()
  {
    int i1 = 0;
    if (this.r != null)
      i1 = this.r.c();
    int i2 = i1;
    if (this.s != null)
      i2 = i1 + this.s.c();
    return i2;
  }

  public w c(String paramString)
  {
    Object localObject = paramString;
    if (!TextUtils.isEmpty(paramString))
      localObject = paramString.replace("&mmusdkwakeup=1", "").replace("mmusdkwakeup=1", "").replace("&mmusdk_cache=1", "").replace("mmusdk_cache=1", "");
    if (this.r == null);
    while (true)
    {
      return null;
      String str = com.taobao.munion.base.e.a((String)localObject);
      paramString = s.a().a(str);
      if (paramString != null)
        return paramString;
      localObject = this.r.a(str);
      paramString = this.r.a();
      if (localObject == null)
      {
        localObject = this.s.a(str);
        paramString = this.s.a();
      }
      for (int i1 = 1; localObject != null; i1 = 0)
      {
        w localw = w.b((i)localObject);
        localObject = ((i)localObject).i();
        if ((i1 == 0) && (!TextUtils.isEmpty((CharSequence)localObject)))
        {
          paramString = this.r.b(str);
          if ((paramString != null) && (((String)localObject).equals(com.taobao.munion.base.e.a(paramString))))
          {
            localw.d = paramString.length;
            localw.e = new ByteArrayInputStream(paramString);
            return localw;
          }
          this.r.c(str);
          return null;
        }
        try
        {
          paramString = new File(paramString + File.separator + str);
          localw.d = paramString.length();
          localw.e = new a(paramString);
          label252: return localw;
        }
        catch (FileNotFoundException paramString)
        {
          break label252;
        }
      }
    }
  }

  public void d(final String paramString)
  {
    if ((this.r == null) || (paramString == null))
      return;
    t.b().a(new Runnable()
    {
      public void run()
      {
        String str = com.taobao.munion.base.e.a(paramString);
        s.a().b(str);
        b.a(b.this).c(str);
      }
    });
  }

  public void e(String paramString)
  {
    try
    {
      paramString = new n().a(paramString);
      if ((paramString != null) && (paramString.size() > 0))
      {
        int i1 = 0;
        while (i1 < paramString.size())
        {
          String str = ((a)paramString.get(i1)).a();
          if (!TextUtils.isEmpty(str))
          {
            w localw = c(str);
            if (localw == null)
              a(str.replace("&mmusdkwakeup=1", "").replace("mmusdkwakeup=1", "").replace("&mmusdk_cache=1", "").replace("mmusdk_cache=1", ""), localw, "");
          }
          i1 += 1;
        }
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }

  public static class a
  {
    private String a;

    public String a()
    {
      return this.a;
    }

    public void a(String paramString)
    {
      this.a = paramString;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.b
 * JD-Core Version:    0.6.2
 */