package com.alipay.sdk.protocol;

import android.text.TextUtils;
import org.json.JSONObject;

public enum ActionType
{
  private String h;
  private String i;
  private String j;
  private JSONObject k;
  private String l;
  private String m;
  private String n;
  private boolean o;
  private boolean p;
  private boolean q;
  private String r;
  private String s;
  private String t;
  private JSONObject u;

  private ActionType(String arg3)
  {
    Object localObject;
    this.h = localObject;
  }

  public static ActionType[] a(ElementAction paramElementAction)
  {
    if (paramElementAction != null)
    {
      localObject1 = paramElementAction.e();
      if (TextUtils.isEmpty((CharSequence)localObject1))
        break label261;
    }
    label258: label261: for (Object localObject1 = ((String)localObject1).split(";"); ; localObject1 = null)
    {
      if (localObject1 == null)
        return new ActionType[] { c };
      ActionType[] arrayOfActionType1 = new ActionType[localObject1.length];
      int i4 = localObject1.length;
      int i2 = 0;
      int i1 = 0;
      Object localObject3;
      Object localObject2;
      int i3;
      if (i2 < i4)
      {
        localObject3 = localObject1[i2];
        localObject2 = c;
        ActionType[] arrayOfActionType2 = values();
        int i5 = arrayOfActionType2.length;
        i3 = 0;
        label85: if (i3 >= i5)
          break label258;
        ActionType localActionType = arrayOfActionType2[i3];
        if (localObject3.startsWith(localActionType.h))
          localObject2 = localActionType;
      }
      while (true)
      {
        ((ActionType)localObject2).i = localObject3;
        ((ActionType)localObject2).j = paramElementAction.f();
        ((ActionType)localObject2).k = paramElementAction.h();
        ((ActionType)localObject2).l = paramElementAction.g();
        ((ActionType)localObject2).m = paramElementAction.i();
        ((ActionType)localObject2).n = paramElementAction.j();
        ((ActionType)localObject2).o = paramElementAction.k();
        ((ActionType)localObject2).p = paramElementAction.l();
        ((ActionType)localObject2).q = paramElementAction.m();
        ((ActionType)localObject2).r = paramElementAction.c();
        ((ActionType)localObject2).s = paramElementAction.d();
        ((ActionType)localObject2).t = paramElementAction.b();
        ((ActionType)localObject2).u = paramElementAction.a();
        arrayOfActionType1[i1] = localObject2;
        i2 += 1;
        i1 += 1;
        break;
        i3 += 1;
        break label85;
        return arrayOfActionType1;
        return new ActionType[] { c };
      }
    }
  }

  private static String[] a(String paramString)
  {
    String[] arrayOfString = null;
    if (!TextUtils.isEmpty(paramString))
      arrayOfString = paramString.split(";");
    return arrayOfString;
  }

  private JSONObject m()
  {
    return this.k;
  }

  public final JSONObject a()
  {
    return this.u;
  }

  public final String b()
  {
    return this.t;
  }

  public final String c()
  {
    return this.r;
  }

  public final String d()
  {
    return this.s;
  }

  public final String e()
  {
    return this.i;
  }

  public final String f()
  {
    return this.j;
  }

  public final String g()
  {
    return this.m;
  }

  public final String h()
  {
    return this.n;
  }

  public final boolean i()
  {
    return this.o;
  }

  public final boolean j()
  {
    return this.p;
  }

  public final boolean k()
  {
    return this.q;
  }

  public final String l()
  {
    return this.l;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.protocol.ActionType
 * JD-Core Version:    0.6.2
 */