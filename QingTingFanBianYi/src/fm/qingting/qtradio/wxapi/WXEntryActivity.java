package fm.qingting.qtradio.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX.Resp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.social.api.WechatApi;

public class WXEntryActivity extends Activity
  implements IWXAPIEventHandler
{
  private static final String TAG = "WXEntryActivity";

  public void onCreate(Bundle paramBundle)
  {
    Log.d("WXEntryActivity", "onCreate");
    super.onCreate(paramBundle);
    WechatApi.handleIntent(this, getIntent(), this);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    Log.d("WXEntryActivity", "onDestroy");
  }

  public void onGetMessageFromWXReq(BaseReq paramBaseReq)
  {
    startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
  }

  protected void onNewIntent(Intent paramIntent)
  {
    Log.d("WXEntryActivity", "onNewIntent");
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
    WechatApi.handleIntent(this, paramIntent, this);
  }

  public void onReq(BaseReq paramBaseReq)
  {
    Log.d("WXEntryActivity", "onRequest");
    switch (paramBaseReq.getType())
    {
    case 3:
    case 4:
    }
    onGetMessageFromWXReq(paramBaseReq);
    finish();
  }

  public void onResp(BaseResp paramBaseResp)
  {
    Log.d("WXEntryActivity", "onResponse");
    if ((paramBaseResp instanceof SendAuth.Resp))
      switch (paramBaseResp.errCode)
      {
      case -3:
      case -2:
      case -1:
      default:
      case 0:
      case -4:
      }
    while (true)
    {
      finish();
      return;
      paramBaseResp = ((SendAuth.Resp)paramBaseResp).code;
      Log.d("WXEntryActivity", "获取到微信code=" + paramBaseResp);
      InfoManager.getInstance().getWeChatToken("wx7726d8211afb6b05", "3b8a7bf420b180a37880bbd474e2950c", paramBaseResp, null);
      continue;
      Toast.makeText(this, "微信登录失败", 0).show();
      continue;
      if ((paramBaseResp instanceof SendMessageToWX.Resp))
        switch (paramBaseResp.errCode)
        {
        case -2:
        case -3:
        case -1:
        default:
          break;
        case -4:
          Toast.makeText(this, "分享失败", 0).show();
          break;
        case 0:
          Toast.makeText(this, "分享成功", 0).show();
        }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.wxapi.WXEntryActivity
 * JD-Core Version:    0.6.2
 */