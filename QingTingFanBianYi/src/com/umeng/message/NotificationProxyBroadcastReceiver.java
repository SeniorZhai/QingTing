package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.umeng.common.message.Log;
import com.umeng.message.entity.UMessage;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationProxyBroadcastReceiver extends BroadcastReceiver
{
  public static final int EXTRA_ACTION_CLICK = 10;
  public static final int EXTRA_ACTION_DISMISS = 11;
  public static final int EXTRA_ACTION_NOT_EXIST = -1;
  public static final String EXTRA_KEY_ACTION = "ACTION";
  public static final String EXTRA_KEY_MSG = "MSG";
  private static final String a = NotificationProxyBroadcastReceiver.class.getName();
  private UHandler b;

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("MSG");
    try
    {
      int i = paramIntent.getIntExtra("ACTION", -1);
      Log.c(a, String.format("onReceive[msg=%s, action=%d]", new Object[] { str, Integer.valueOf(i) }));
      paramIntent = new UMessage(new JSONObject(str));
      switch (i)
      {
      case 11:
        Log.c(a, "dismiss notification");
        UTrack.getInstance(paramContext).trackMsgDismissed(paramIntent);
        return;
      case 10:
      }
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
      return;
      Log.c(a, "click notification");
      UTrack.getInstance(paramContext).trackMsgClick(paramIntent);
      this.b = PushAgent.getInstance(paramContext).getNotificationClickHandler();
      if (this.b != null)
      {
        this.b.handleMessage(paramContext, paramIntent);
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.NotificationProxyBroadcastReceiver
 * JD-Core Version:    0.6.2
 */