package com.alipay.sdk.widget;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface.OnCancelListener;

public class Loading
{
  private Activity a;
  private ProgressDialog b;

  public Loading(Activity paramActivity)
  {
    this.a = paramActivity;
  }

  private void a(String paramString)
  {
    a(paramString, false, null);
  }

  public final void a(CharSequence paramCharSequence, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    paramCharSequence = new Loading.1(this, paramBoolean, paramOnCancelListener, paramCharSequence);
    this.a.runOnUiThread(paramCharSequence);
  }

  public final boolean a()
  {
    return (this.b != null) && (this.b.isShowing());
  }

  public final void b()
  {
    a("正在加载", false, null);
  }

  public final void c()
  {
    Loading.2 local2 = new Loading.2(this);
    this.a.runOnUiThread(local2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.widget.Loading
 * JD-Core Version:    0.6.2
 */