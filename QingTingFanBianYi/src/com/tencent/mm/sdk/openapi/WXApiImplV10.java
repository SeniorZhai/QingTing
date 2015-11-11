package com.tencent.mm.sdk.openapi;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.mm.sdk.a.a.b;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelbiz.AddCardToWXCardPackage.Resp;
import com.tencent.mm.sdk.modelmsg.GetMessageFromWX.Req;
import com.tencent.mm.sdk.modelmsg.LaunchFromWX.Req;
import com.tencent.mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX.Resp;
import com.tencent.mm.sdk.modelmsg.ShowMessageFromWX.Req;
import com.tencent.mm.sdk.modelpay.PayResp;

final class WXApiImplV10
  implements IWXAPI
{
  private static final String TAG = "MicroMsg.SDK.WXApiImplV10";
  private static String wxappPayEntryClassname = null;
  private String appId;
  private boolean checkSignature = false;
  private Context context;
  private boolean detached = false;

  WXApiImplV10(Context paramContext, String paramString, boolean paramBoolean)
  {
    com.tencent.mm.sdk.b.a.c("MicroMsg.SDK.WXApiImplV10", "<init>, appId = " + paramString + ", checkSignature = " + paramBoolean);
    this.context = paramContext;
    this.appId = paramString;
    this.checkSignature = paramBoolean;
  }

  private boolean checkSumConsistent(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length == 0) || (paramArrayOfByte2 == null) || (paramArrayOfByte2.length == 0))
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "checkSumConsistent fail, invalid arguments");
      return false;
    }
    if (paramArrayOfByte1.length != paramArrayOfByte2.length)
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "checkSumConsistent fail, length is different");
      return false;
    }
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfByte1.length)
        break label67;
      if (paramArrayOfByte1[i] != paramArrayOfByte2[i])
        break;
      i += 1;
    }
    label67: return true;
  }

  private boolean sendAddCardToWX(Context paramContext, Bundle paramBundle)
  {
    paramContext = paramContext.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/addCardToWX"), null, null, new String[] { this.appId, paramBundle.getString("_wxapi_add_card_to_wx_card_list"), paramBundle.getString("_wxapi_basereq_transaction") }, null);
    if (paramContext != null)
      paramContext.close();
    return true;
  }

  private boolean sendJumpToBizProfileReq(Context paramContext, Bundle paramBundle)
  {
    paramContext = paramContext.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizProfile"), null, null, new String[] { this.appId, paramBundle.getString("_wxapi_jump_to_biz_profile_req_to_user_name"), paramBundle.getString("_wxapi_jump_to_biz_profile_req_ext_msg"), paramBundle.getInt("_wxapi_jump_to_biz_profile_req_scene"), paramBundle.getInt("_wxapi_jump_to_biz_profile_req_profile_type") }, null);
    if (paramContext != null)
      paramContext.close();
    return true;
  }

  private boolean sendJumpToBizWebviewReq(Context paramContext, Bundle paramBundle)
  {
    paramContext = paramContext.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizProfile"), null, null, new String[] { this.appId, paramBundle.getString("_wxapi_jump_to_biz_webview_req_to_user_name"), paramBundle.getString("_wxapi_jump_to_biz_webview_req_ext_msg"), paramBundle.getInt("_wxapi_jump_to_biz_webview_req_scene") }, null);
    if (paramContext != null)
      paramContext.close();
    return true;
  }

  private boolean sendPayReq(Context paramContext, Bundle paramBundle)
  {
    if (wxappPayEntryClassname == null)
    {
      wxappPayEntryClassname = new MMSharedPreferences(paramContext).getString("_wxapp_pay_entry_classname_", null);
      com.tencent.mm.sdk.b.a.c("MicroMsg.SDK.WXApiImplV10", "pay, set wxappPayEntryClassname = " + wxappPayEntryClassname);
      if (wxappPayEntryClassname == null)
      {
        com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "pay fail, wxappPayEntryClassname is null");
        return false;
      }
    }
    com.tencent.mm.sdk.a.a.a locala = new com.tencent.mm.sdk.a.a.a();
    locala.e = paramBundle;
    locala.b = "com.tencent.mm";
    locala.c = wxappPayEntryClassname;
    return com.tencent.mm.sdk.a.a.a(paramContext, locala);
  }

  public final void detach()
  {
    com.tencent.mm.sdk.b.a.c("MicroMsg.SDK.WXApiImplV10", "detach");
    this.detached = true;
    this.context = null;
  }

  public final int getWXAppSupportAPI()
  {
    if (this.detached)
      throw new IllegalStateException("getWXAppSupportAPI fail, WXMsgImpl has been detached");
    if (!isWXAppInstalled())
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "open wx app failed, not installed or signature check failed");
      return 0;
    }
    return new MMSharedPreferences(this.context).getInt("_build_info_sdk_int_", 0);
  }

  public final boolean handleIntent(Intent paramIntent, IWXAPIEventHandler paramIWXAPIEventHandler)
  {
    if (!WXApiImplComm.isIntentFromWx(paramIntent, "com.tencent.mm.openapi.token"))
    {
      com.tencent.mm.sdk.b.a.b("MicroMsg.SDK.WXApiImplV10", "handleIntent fail, intent not from weixin msg");
      return false;
    }
    if (this.detached)
      throw new IllegalStateException("handleIntent fail, WXMsgImpl has been detached");
    String str1 = paramIntent.getStringExtra("_mmessage_content");
    int i = paramIntent.getIntExtra("_mmessage_sdkVersion", 0);
    String str2 = paramIntent.getStringExtra("_mmessage_appPackage");
    if ((str2 == null) || (str2.length() == 0))
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "invalid argument");
      return false;
    }
    if (!checkSumConsistent(paramIntent.getByteArrayExtra("_mmessage_checksum"), b.a(str1, i, str2)))
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "checksum fail");
      return false;
    }
    i = paramIntent.getIntExtra("_wxapi_command_type", 0);
    switch (i)
    {
    case 7:
    case 8:
    default:
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "unknown cmd = " + i);
      return false;
    case 1:
      paramIWXAPIEventHandler.onResp(new SendAuth.Resp(paramIntent.getExtras()));
      return true;
    case 2:
      paramIWXAPIEventHandler.onResp(new SendMessageToWX.Resp(paramIntent.getExtras()));
      return true;
    case 3:
      paramIWXAPIEventHandler.onReq(new GetMessageFromWX.Req(paramIntent.getExtras()));
      return true;
    case 4:
      paramIWXAPIEventHandler.onReq(new ShowMessageFromWX.Req(paramIntent.getExtras()));
      return true;
    case 5:
      paramIWXAPIEventHandler.onResp(new PayResp(paramIntent.getExtras()));
      return true;
    case 6:
      paramIWXAPIEventHandler.onReq(new LaunchFromWX.Req(paramIntent.getExtras()));
      return true;
    case 9:
    }
    paramIWXAPIEventHandler.onResp(new AddCardToWXCardPackage.Resp(paramIntent.getExtras()));
    return true;
  }

  public final boolean isWXAppInstalled()
  {
    if (this.detached)
      throw new IllegalStateException("isWXAppInstalled fail, WXMsgImpl has been detached");
    try
    {
      PackageInfo localPackageInfo = this.context.getPackageManager().getPackageInfo("com.tencent.mm", 64);
      if (localPackageInfo == null)
        return false;
      boolean bool = WXApiImplComm.validateAppSignature(this.context, localPackageInfo.signatures, this.checkSignature);
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    return false;
  }

  public final boolean isWXAppSupportAPI()
  {
    if (this.detached)
      throw new IllegalStateException("isWXAppSupportAPI fail, WXMsgImpl has been detached");
    return getWXAppSupportAPI() >= 570490883;
  }

  public final boolean openWXApp()
  {
    if (this.detached)
      throw new IllegalStateException("openWXApp fail, WXMsgImpl has been detached");
    if (!isWXAppInstalled())
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "open wx app failed, not installed or signature check failed");
      return false;
    }
    try
    {
      this.context.startActivity(this.context.getPackageManager().getLaunchIntentForPackage("com.tencent.mm"));
      return true;
    }
    catch (Exception localException)
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "startActivity fail, exception = " + localException.getMessage());
    }
    return false;
  }

  public final boolean registerApp(String paramString)
  {
    if (this.detached)
      throw new IllegalStateException("registerApp fail, WXMsgImpl has been detached");
    if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature))
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "register app failed for wechat app signature check failed");
      return false;
    }
    com.tencent.mm.sdk.b.a.c("MicroMsg.SDK.WXApiImplV10", "registerApp, appId = " + paramString);
    if (paramString != null)
      this.appId = paramString;
    com.tencent.mm.sdk.b.a.c("MicroMsg.SDK.WXApiImplV10", "register app " + this.context.getPackageName());
    paramString = new com.tencent.mm.sdk.a.a.a.a();
    paramString.f = "com.tencent.mm";
    paramString.g = "com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_REGISTER";
    paramString.d = ("weixin://registerapp?appid=" + this.appId);
    return com.tencent.mm.sdk.a.a.a.a(this.context, paramString);
  }

  public final boolean sendReq(BaseReq paramBaseReq)
  {
    if (this.detached)
      throw new IllegalStateException("sendReq fail, WXMsgImpl has been detached");
    if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature))
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "sendReq failed for wechat app signature check failed");
      return false;
    }
    if (!paramBaseReq.checkArgs())
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "sendReq checkArgs fail");
      return false;
    }
    com.tencent.mm.sdk.b.a.c("MicroMsg.SDK.WXApiImplV10", "sendReq, req type = " + paramBaseReq.getType());
    Bundle localBundle = new Bundle();
    paramBaseReq.toBundle(localBundle);
    if (paramBaseReq.getType() == 5)
      return sendPayReq(this.context, localBundle);
    if (paramBaseReq.getType() == 7)
      return sendJumpToBizProfileReq(this.context, localBundle);
    if (paramBaseReq.getType() == 8)
      return sendJumpToBizWebviewReq(this.context, localBundle);
    if (paramBaseReq.getType() == 9)
      return sendAddCardToWX(this.context, localBundle);
    paramBaseReq = new com.tencent.mm.sdk.a.a.a();
    paramBaseReq.e = localBundle;
    paramBaseReq.d = ("weixin://sendreq?appid=" + this.appId);
    paramBaseReq.b = "com.tencent.mm";
    paramBaseReq.c = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
    return com.tencent.mm.sdk.a.a.a(this.context, paramBaseReq);
  }

  public final boolean sendResp(BaseResp paramBaseResp)
  {
    if (this.detached)
      throw new IllegalStateException("sendResp fail, WXMsgImpl has been detached");
    if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature))
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "sendResp failed for wechat app signature check failed");
      return false;
    }
    if (!paramBaseResp.checkArgs())
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "sendResp checkArgs fail");
      return false;
    }
    Bundle localBundle = new Bundle();
    paramBaseResp.toBundle(localBundle);
    paramBaseResp = new com.tencent.mm.sdk.a.a.a();
    paramBaseResp.e = localBundle;
    paramBaseResp.d = ("weixin://sendresp?appid=" + this.appId);
    paramBaseResp.b = "com.tencent.mm";
    paramBaseResp.c = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
    return com.tencent.mm.sdk.a.a.a(this.context, paramBaseResp);
  }

  public final void unregisterApp()
  {
    if (this.detached)
      throw new IllegalStateException("unregisterApp fail, WXMsgImpl has been detached");
    if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature))
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "unregister app failed for wechat app signature check failed");
      return;
    }
    com.tencent.mm.sdk.b.a.c("MicroMsg.SDK.WXApiImplV10", "unregisterApp, appId = " + this.appId);
    if ((this.appId == null) || (this.appId.length() == 0))
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.WXApiImplV10", "unregisterApp fail, appId is empty");
      return;
    }
    com.tencent.mm.sdk.b.a.c("MicroMsg.SDK.WXApiImplV10", "unregister app " + this.context.getPackageName());
    com.tencent.mm.sdk.a.a.a.a locala = new com.tencent.mm.sdk.a.a.a.a();
    locala.f = "com.tencent.mm";
    locala.g = "com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_UNREGISTER";
    locala.d = ("weixin://unregisterapp?appid=" + this.appId);
    com.tencent.mm.sdk.a.a.a.a(this.context, locala);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.openapi.WXApiImplV10
 * JD-Core Version:    0.6.2
 */