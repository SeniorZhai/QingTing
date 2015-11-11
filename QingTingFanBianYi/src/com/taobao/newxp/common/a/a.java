package com.taobao.newxp.common.a;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import com.taobao.newxp.common.a.a.b;
import com.taobao.newxp.common.a.a.d;
import com.taobao.newxp.common.a.a.f;
import com.taobao.newxp.common.a.a.f.a;
import com.taobao.newxp.common.a.a.j;
import com.taobao.newxp.common.a.a.k;
import com.taobao.newxp.common.a.a.l;
import com.taobao.newxp.common.a.a.m;
import com.taobao.newxp.common.a.a.n;
import com.taobao.newxp.common.a.a.o;
import java.util.Vector;

public class a
{
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  public static final int d = 3;
  public static final int e = 4;
  private static d f;
  private static a h = new a();
  private Vector<l> g;

  private a()
  {
    f = new d();
    this.g = new Vector(4);
    this.g.add(new b());
    this.g.add(new k());
    this.g.add(new n());
    this.g.add(new j());
    this.g.add(new f());
  }

  public static a a()
  {
    return h;
  }

  public void a(int paramInt)
  {
    ((l)this.g.get(paramInt)).b();
  }

  public void a(Context paramContext, MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent != null)
    {
      ((b)this.g.get(0)).a(paramContext, paramMotionEvent);
      ((k)this.g.get(1)).a(paramContext, paramMotionEvent);
      ((j)this.g.get(3)).a(paramContext, paramMotionEvent);
    }
  }

  public void a(Bundle paramBundle)
  {
    if (paramBundle != null)
      f.a(paramBundle);
  }

  public void a(com.taobao.newxp.common.a.a.a parama)
  {
    if (parama != null)
      ((k)this.g.get(1)).a(parama);
  }

  public void a(f.a parama)
  {
    if (parama != null)
      ((f)this.g.get(4)).a(parama);
  }

  public void a(m paramm)
  {
    if (paramm != null)
      ((k)this.g.get(1)).a(paramm);
  }

  public void a(o paramo)
  {
    if (paramo != null)
      ((j)this.g.get(3)).a(paramo);
  }

  public d b()
  {
    return f;
  }

  public l b(int paramInt)
  {
    return (l)this.g.get(paramInt);
  }

  public void b(com.taobao.newxp.common.a.a.a parama)
  {
    if (parama != null)
      ((n)this.g.get(2)).a(parama);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.a.a
 * JD-Core Version:    0.6.2
 */