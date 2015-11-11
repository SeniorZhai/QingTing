package com.taobao.munion.base.volley;

import android.content.Intent;

public class a extends s
{
  private Intent b;

  public a()
  {
  }

  public a(Intent paramIntent)
  {
    this.b = paramIntent;
  }

  public a(i parami)
  {
    super(parami);
  }

  public a(String paramString)
  {
    super(paramString);
  }

  public a(String paramString, Exception paramException)
  {
    super(paramString, paramException);
  }

  public Intent a()
  {
    return this.b;
  }

  public String getMessage()
  {
    if (this.b != null)
      return "User needs to (re)enter credentials.";
    return super.getMessage();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.a
 * JD-Core Version:    0.6.2
 */