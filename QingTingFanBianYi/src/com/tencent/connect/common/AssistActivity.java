package com.tencent.connect.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AssistActivity extends Activity
{
  private static BaseApi sApiObject;
  private BaseApi mAPiObject;

  public static Intent getAssistActivityIntent(Context paramContext)
  {
    return new Intent(paramContext, AssistActivity.class);
  }

  public static void setApiObject(BaseApi paramBaseApi)
  {
    sApiObject = paramBaseApi;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (this.mAPiObject != null)
      this.mAPiObject.onActivityResult(paramInt1, paramInt2, paramIntent);
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    if (sApiObject == null)
    {
      finish();
      return;
    }
    this.mAPiObject = sApiObject;
    sApiObject = null;
    int i = this.mAPiObject.getActivityIntent().getIntExtra("key_request_code", 0);
    startActivityForResult(this.mAPiObject.getActivityIntent(), i);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.common.AssistActivity
 * JD-Core Version:    0.6.2
 */