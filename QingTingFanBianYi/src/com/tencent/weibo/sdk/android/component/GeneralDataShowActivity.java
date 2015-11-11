package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class GeneralDataShowActivity extends Activity
{
  private TextView tv;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.tv = new TextView(this);
    paramBundle = getIntent().getExtras().getString("data");
    this.tv.setText(paramBundle);
    this.tv.setMovementMethod(ScrollingMovementMethod.getInstance());
    setContentView(this.tv);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.GeneralDataShowActivity
 * JD-Core Version:    0.6.2
 */