package fm.qingting.qtradio.pushmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import fm.qingting.qtradio.NotificationService;
import fm.qingting.qtradio.log.LogModule;

public class IxintuiMessageReceiver extends BroadcastReceiver
{
  private String mAlias;
  private String mMessage;
  private String mRegId;
  private String mTopic;

  private void log(String paramString)
  {
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramContext == null) || (paramIntent == null));
    while (true)
    {
      return;
      try
      {
        Object localObject = paramIntent.getAction();
        if (localObject != null)
        {
          log("message received, action is: " + (String)localObject + " " + "com.ixintui.action.MESSAGE");
          if (((String)localObject).equals("com.ixintui.action.MESSAGE"))
          {
            paramIntent = paramIntent.getStringExtra("com.ixintui.MESSAGE");
            log("message received, msg is: " + paramIntent);
            localObject = (JSONObject)JSON.parse(paramIntent);
            this.mTopic = ((JSONObject)localObject).getString("topic");
            this.mAlias = ((JSONObject)localObject).getString("alias");
            this.mMessage = ((JSONObject)localObject).getString("msg");
            if ((paramIntent != null) && (paramIntent.equalsIgnoreCase("{\"topic\":\"qingting:startService\"}")))
            {
              log("message received, msg is: " + paramIntent + " startservice");
              paramIntent = new Intent(paramContext, NotificationService.class);
              paramIntent.setAction("fm.qingting.connectivity.change");
              paramContext.startService(paramIntent);
              return;
            }
            paramIntent = new Intent();
            paramIntent.setAction("fm.qingting.qtradio.GEXIN_MESSAGE");
            paramIntent.putExtra("alias", this.mAlias);
            paramIntent.putExtra("msg", this.mMessage);
            paramIntent.putExtra("topic", this.mTopic);
            paramIntent.putExtra("reg", this.mRegId);
            paramIntent.putExtra("type", String.valueOf(0));
            LogModule.getInstance().init(paramContext);
            PushMessageLog.sendGetuiMsgLog(paramContext, ((JSONObject)JSON.parse(this.mMessage)).getString("uuid"), "ixintui");
            paramContext.sendBroadcast(paramIntent);
            return;
          }
        }
      }
      catch (Exception paramContext)
      {
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.pushmessage.IxintuiMessageReceiver
 * JD-Core Version:    0.6.2
 */