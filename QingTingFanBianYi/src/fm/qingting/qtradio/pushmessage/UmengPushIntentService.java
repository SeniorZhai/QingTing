package fm.qingting.qtradio.pushmessage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umeng.message.UmengBaseIntentService;

public class UmengPushIntentService extends UmengBaseIntentService
{
  private static final String TAG = "UmengPushIntentService";
  private String mAlias;
  private String mMessage;
  private String mRegId;
  private String mTopic;

  private void log(String paramString)
  {
  }

  protected void onMessage(Context paramContext, Intent paramIntent)
  {
    super.onMessage(paramContext, paramIntent);
    if ((paramIntent == null) || (paramContext == null));
    while (true)
    {
      return;
      try
      {
        paramIntent = paramIntent.getStringExtra("body");
        if (paramIntent != null)
        {
          log(paramIntent);
          paramIntent = ((JSONObject)JSON.parse(paramIntent)).getJSONObject("body").getJSONObject("custom");
          this.mTopic = paramIntent.getString("topic");
          this.mAlias = paramIntent.getString("alias");
          this.mMessage = paramIntent.getString("msg");
          paramIntent = new Intent();
          paramIntent.setAction("fm.qingting.qtradio.GEXIN_MESSAGE");
          paramIntent.putExtra("alias", this.mAlias);
          paramIntent.putExtra("msg", this.mMessage);
          paramIntent.putExtra("topic", this.mTopic);
          paramIntent.putExtra("reg", this.mRegId);
          paramIntent.putExtra("type", String.valueOf(0));
          paramContext.sendBroadcast(paramIntent);
          return;
        }
      }
      catch (Exception paramContext)
      {
        Log.e("UmengPushIntentService", paramContext.getMessage());
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.pushmessage.UmengPushIntentService
 * JD-Core Version:    0.6.2
 */