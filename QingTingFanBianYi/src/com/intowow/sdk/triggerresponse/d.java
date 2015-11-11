package com.intowow.sdk.triggerresponse;

import android.content.Context;
import com.intowow.sdk.b.k.a;
import com.intowow.sdk.f.b;
import com.intowow.sdk.h.k;
import java.io.File;
import java.util.concurrent.ExecutorService;

public class d
{
  private Context a = null;
  private ExecutorService b = null;
  private k.a c = null;
  private Object d = null;

  public d(Object paramObject, Context paramContext, ExecutorService paramExecutorService, b paramb, k.a parama)
  {
    this.d = paramObject;
    this.a = paramContext;
    this.b = paramExecutorService;
    this.c = parama;
  }

  public Object a()
  {
    return this.d;
  }

  public Context b()
  {
    return this.a;
  }

  public File c()
  {
    return this.a.getDir("tracking", 0);
  }

  public String d()
  {
    return "t_";
  }

  public ExecutorService e()
  {
    return this.b;
  }

  public boolean f()
  {
    if (this.c != null)
      return k.a(this.c.a());
    return false;
  }

  public void g()
  {
    this.a = null;
    this.b = null;
    this.c = null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.triggerresponse.d
 * JD-Core Version:    0.6.2
 */