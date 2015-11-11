package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.umeng.message.entity.UMessage;
import org.json.JSONObject;

public class UmengIntentService extends UmengBaseIntentService
{
  private static final String a = UmengIntentService.class.getName();

  protected void onMessage(Context paramContext, Intent paramIntent)
  {
    super.onMessage(paramContext, paramIntent);
    try
    {
      paramContext = new UMessage(new JSONObject(paramIntent.getStringExtra("body")));
      paramIntent = PushAgent.getInstance(getApplicationContext()).getMessageHandler();
      if (paramIntent != null)
        paramIntent.handleMessage(getApplicationContext(), paramContext);
      return;
    }
    catch (Exception paramContext)
    {
      Log.e(a, paramContext.getMessage());
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengIntentService
 * JD-Core Version:    0.6.2
 */