package com.intowow.sdk.b.a;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.intowow.sdk.SplashAdActivity;
import com.intowow.sdk.b.h;
import com.intowow.sdk.b.h.b;
import com.intowow.sdk.model.k;

public class b
{
  private Activity a = null;
  private Intent b = null;
  private a c = null;
  private h d = null;
  private com.intowow.sdk.f.b e = null;

  public b(boolean paramBoolean, Activity paramActivity, h paramh, com.intowow.sdk.f.b paramb)
  {
    this.a = paramActivity;
    this.b = this.a.getIntent();
    this.d = paramh;
    this.e = paramb;
  }

  private void b()
  {
    String str = this.c.b();
    int i = this.c.c();
    if ((str == null) || (i == -1))
      return;
    this.e.a(str);
    this.e.a(i);
  }

  private void c()
  {
    if (this.c.f() == null)
      return;
    this.e.b(this.c.f());
  }

  private void d()
  {
    if (this.c.f() == null)
      return;
    Bundle localBundle = new Bundle();
    localBundle.putInt("type", h.b.n.ordinal());
    localBundle.putString("snapshot_url", this.c.f());
    this.d.a(localBundle);
  }

  private void e()
  {
    if (!this.c.d())
      return;
    this.a.getIntent().setData(null);
    this.e.a(k.b);
    Object localObject = new Intent();
    ((Intent)localObject).setClass(this.a, SplashAdActivity.class);
    ((Intent)localObject).putExtra("INTENT_PREVIEW_FETCH_ADLIST", "");
    if (Build.VERSION.SDK_INT >= 11)
      ((Intent)localObject).addFlags(276856832);
    while (true)
    {
      this.a.startActivity((Intent)localObject);
      this.a.finish();
      localObject = new Bundle();
      ((Bundle)localObject).putInt("type", h.b.l.ordinal());
      ((Bundle)localObject).putInt("adid", this.c.e());
      this.d.a((Bundle)localObject);
      return;
      ((Intent)localObject).addFlags(276824064);
    }
  }

  public void a()
  {
    while (true)
    {
      try
      {
        Object localObject1;
        if (this.d != null)
        {
          if ((this.b != null) && (this.b.getData() != null))
          {
            localObject1 = this.b.getData().getHost();
            if ((localObject1 == null) || ((!((String)localObject1).equals("adpreview")) && (!((String)localObject1).equals("crystalexpress"))));
          }
        }
        else
        {
          this.c = a.a(this.b.getData());
          localObject1 = this.c;
          if (localObject1 == null)
            return;
          localObject1 = this.c.a();
          if (localObject1 == null)
            return;
          if (!((String)localObject1).equals("adpreview"))
            continue;
          e();
        }
        return;
        if (((String)localObject1).equals("snapshot"))
        {
          d();
          continue;
        }
      }
      catch (Exception localException)
      {
        return;
        if (localException.equals("realtime_debugger"))
        {
          c();
          continue;
        }
      }
      finally
      {
        this.a = null;
        this.b = null;
        this.e = null;
        this.d = null;
      }
      if (localObject2.equals("location"))
        b();
    }
  }

  static class a
  {
    private int a = -1;
    private String b = null;
    private String c = null;
    private String d = null;
    private String e = null;
    private int f = -1;

    public static a a(Uri paramUri)
    {
      int j = -1;
      try
      {
        a locala = new a();
        locala.b = paramUri.toString();
        int i;
        if (paramUri.getQueryParameter("adid") == null)
        {
          i = -1;
          locala.a = i;
          if (paramUri.getQueryParameter("action") != null)
            break label135;
          str = null;
          label46: locala.d = str;
          if (paramUri.getQueryParameter("geo_group") != null)
            break label145;
          str = null;
          label62: locala.e = str;
          if (paramUri.getQueryParameter("geo_id") != null)
            break label155;
          i = j;
          label79: locala.f = i;
          if (paramUri.getQueryParameter("endpoint") != null)
            break label168;
        }
        label135: label145: label155: label168: for (String str = null; ; str = paramUri.getQueryParameter("endpoint"))
        {
          locala.c = str;
          if (locala.d != null)
            break label264;
          if (!locala.d())
            break label178;
          locala.d = "adpreview";
          return locala;
          i = Integer.parseInt(paramUri.getQueryParameter("adid"));
          break;
          str = paramUri.getQueryParameter("action");
          break label46;
          str = paramUri.getQueryParameter("geo_group");
          break label62;
          i = Integer.parseInt(paramUri.getQueryParameter("geo_id"));
          break label79;
        }
        label178: if (paramUri.getQueryParameter("snapshot") != null)
        {
          locala.d = "snapshot";
          if (paramUri.getQueryParameter("snapshot") == null);
          for (paramUri = null; ; paramUri = paramUri.getQueryParameter("snapshot"))
          {
            locala.c = paramUri;
            return locala;
          }
        }
        if (paramUri.getQueryParameter("debugger_endpoint") != null)
        {
          locala.d = "realtime_debugger";
          if (paramUri.getQueryParameter("debugger_endpoint") == null);
          for (paramUri = null; ; paramUri = paramUri.getQueryParameter("debugger_endpoint"))
          {
            locala.c = paramUri;
            return locala;
          }
        }
        label264: return locala;
      }
      catch (Exception paramUri)
      {
      }
      return null;
    }

    public String a()
    {
      return this.d;
    }

    public String b()
    {
      return this.e;
    }

    public int c()
    {
      return this.f;
    }

    public boolean d()
    {
      return this.a != -1;
    }

    public int e()
    {
      return this.a;
    }

    public String f()
    {
      return this.c;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.b.a.b
 * JD-Core Version:    0.6.2
 */