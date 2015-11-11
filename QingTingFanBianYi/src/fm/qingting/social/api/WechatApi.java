package fm.qingting.social.api;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import fm.qingting.social.ISocialEventListener;

public class WechatApi
{
  private static final String APP_ID = "wxaca5032bab461438";
  private static final String WEIXIN_SCOPE = "snsapi_userinfo";
  private static IWXAPI api = null;
  private static String mRegisterToken = null;

  public static void handleIntent(Context paramContext, Intent paramIntent, IWXAPIEventHandler paramIWXAPIEventHandler)
  {
    register(paramContext);
    api.handleIntent(paramIntent, paramIWXAPIEventHandler);
  }

  public static void init(Context paramContext, Intent paramIntent, IWXAPIEventHandler paramIWXAPIEventHandler)
  {
    register(paramContext);
  }

  public static boolean isWXAppInstalled()
  {
    if (api == null)
      return false;
    return api.isWXAppInstalled();
  }

  public static void login()
  {
    SendAuth.Req localReq = new SendAuth.Req();
    localReq.scope = "snsapi_userinfo";
    localReq.state = "Master";
    api.sendReq(localReq);
  }

  public static void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
  }

  public static void onReq(BaseReq paramBaseReq)
  {
  }

  public static void onResp(BaseResp paramBaseResp)
  {
    if ((paramBaseResp != null) && ((paramBaseResp instanceof SendAuth.Resp)));
  }

  private static void register(Context paramContext)
  {
    if (api == null)
    {
      api = WXAPIFactory.createWXAPI(paramContext, "wxaca5032bab461438", true);
      api.registerApp("wxaca5032bab461438");
    }
  }

  public static void shareAudio(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, Bitmap paramBitmap, Boolean paramBoolean, ISocialEventListener paramISocialEventListener)
  {
    register(paramContext);
    if (((!api.isWXAppInstalled()) || (!api.isWXAppSupportAPI())) && (paramISocialEventListener != null))
      paramISocialEventListener.onException(new Exception("wechat not installed."));
    if ((paramString1 == null) && (paramString2 == null))
      return;
    paramContext = paramString3;
    if (paramString3 == null)
      paramContext = "有声世界,无限精彩";
    paramString3 = paramString4;
    if (paramString4 == null)
      paramString3 = "有声世界,无限精彩";
    paramString4 = new WXMusicObject();
    paramString4.musicUrl = paramString1;
    paramString4.musicLowBandUrl = paramString1;
    paramString4.musicDataUrl = paramString2;
    paramString4.musicLowBandDataUrl = paramString2;
    paramString1 = new WXMediaMessage();
    paramString1.mediaObject = paramString4;
    paramString1.title = paramContext;
    paramString1.description = paramString3;
    paramString1.setThumbImage(paramBitmap);
    paramContext = new SendMessageToWX.Req();
    paramContext.transaction = String.valueOf(System.currentTimeMillis());
    paramContext.message = paramString1;
    if (paramBoolean.booleanValue());
    for (int i = 1; ; i = 0)
    {
      paramContext.scene = i;
      api.sendReq(paramContext);
      return;
    }
  }

  public static void shareUrlToMoments(Context paramContext, String paramString1, String paramString2, String paramString3, Bitmap paramBitmap, ISocialEventListener paramISocialEventListener)
  {
    register(paramContext);
    if (((!api.isWXAppInstalled()) || (!api.isWXAppSupportAPI())) && (paramISocialEventListener != null))
      paramISocialEventListener.onException(new Exception("wechat not installed."));
    paramContext = new WXWebpageObject();
    paramContext.webpageUrl = paramString1;
    paramString1 = new WXMediaMessage();
    paramString1.mediaObject = paramContext;
    paramString1.title = paramString2;
    paramString1.description = paramString3;
    paramString1.setThumbImage(paramBitmap);
    paramContext = new SendMessageToWX.Req();
    paramContext.transaction = String.valueOf(System.currentTimeMillis());
    paramContext.message = paramString1;
    paramContext.scene = 1;
    api.sendReq(paramContext);
  }

  public static void shareWebPage(Context paramContext, String paramString1, String paramString2, String paramString3, Bitmap paramBitmap, Boolean paramBoolean, ISocialEventListener paramISocialEventListener)
  {
    register(paramContext);
    if (((!api.isWXAppInstalled()) || (!api.isWXAppSupportAPI())) && (paramISocialEventListener != null))
    {
      paramISocialEventListener.onException(new Exception("wechat not installed."));
      return;
    }
    paramContext = new WXWebpageObject();
    paramContext.webpageUrl = paramString1;
    paramString1 = new WXMediaMessage();
    paramString1.mediaObject = paramContext;
    paramString1.title = paramString2;
    paramString1.description = paramString3;
    paramString1.setThumbImage(paramBitmap);
    paramContext = new SendMessageToWX.Req();
    paramContext.transaction = String.valueOf(System.currentTimeMillis());
    paramContext.message = paramString1;
    if (paramBoolean.booleanValue());
    for (int i = 1; ; i = 0)
    {
      paramContext.scene = i;
      api.sendReq(paramContext);
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.social.api.WechatApi
 * JD-Core Version:    0.6.2
 */