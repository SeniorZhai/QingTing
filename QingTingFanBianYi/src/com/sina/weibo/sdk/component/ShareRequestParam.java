package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseRequest;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareRequestParam extends BrowserRequestParamBase
{
  public static final String REQ_PARAM_AID = "aid";
  public static final String REQ_PARAM_KEY_HASH = "key_hash";
  public static final String REQ_PARAM_PACKAGENAME = "packagename";
  public static final String REQ_PARAM_PICINFO = "picinfo";
  public static final String REQ_PARAM_SOURCE = "source";
  public static final String REQ_PARAM_TITLE = "title";
  public static final String REQ_PARAM_TOKEN = "access_token";
  public static final String REQ_PARAM_VERSION = "version";
  public static final String REQ_UPLOAD_PIC_PARAM_IMG = "img";
  public static final String RESP_UPLOAD_PIC_PARAM_CODE = "code";
  public static final String RESP_UPLOAD_PIC_PARAM_DATA = "data";
  public static final int RESP_UPLOAD_PIC_SUCC_CODE = 1;
  private static final String SHARE_URL = "http://service.weibo.com/share/mobilesdk.php";
  public static final String UPLOAD_PIC_URL = "http://service.weibo.com/share/mobilesdk_uppic.php";
  private String mAppKey;
  private String mAppPackage;
  private WeiboAuthListener mAuthListener;
  private String mAuthListenerKey;
  private byte[] mBase64ImgData;
  private BaseRequest mBaseRequest;
  private String mHashKey;
  private String mShareContent;
  private String mToken;

  public ShareRequestParam(Context paramContext)
  {
    super(paramContext);
    this.mLaucher = BrowserLauncher.SHARE;
  }

  // ERROR //
  private void handleMblogPic(String paramString, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 90	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifne +95 -> 99
    //   7: new 92	java/io/File
    //   10: dup
    //   11: aload_1
    //   12: invokespecial 95	java/io/File:<init>	(Ljava/lang/String;)V
    //   15: astore_1
    //   16: aload_1
    //   17: invokevirtual 99	java/io/File:exists	()Z
    //   20: ifeq +79 -> 99
    //   23: aload_1
    //   24: invokevirtual 102	java/io/File:canRead	()Z
    //   27: ifeq +72 -> 99
    //   30: aload_1
    //   31: invokevirtual 106	java/io/File:length	()J
    //   34: lconst_0
    //   35: lcmp
    //   36: ifle +63 -> 99
    //   39: aload_1
    //   40: invokevirtual 106	java/io/File:length	()J
    //   43: l2i
    //   44: newarray byte
    //   46: astore 5
    //   48: aconst_null
    //   49: astore_3
    //   50: aconst_null
    //   51: astore 4
    //   53: new 108	java/io/FileInputStream
    //   56: dup
    //   57: aload_1
    //   58: invokespecial 111	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   61: astore_1
    //   62: aload_1
    //   63: aload 5
    //   65: invokevirtual 115	java/io/FileInputStream:read	([B)I
    //   68: pop
    //   69: aload_0
    //   70: aload 5
    //   72: invokestatic 121	com/sina/weibo/sdk/utils/Base64:encodebyte	([B)[B
    //   75: putfield 123	com/sina/weibo/sdk/component/ShareRequestParam:mBase64ImgData	[B
    //   78: aload_1
    //   79: ifnull +7 -> 86
    //   82: aload_1
    //   83: invokevirtual 127	java/io/FileInputStream:close	()V
    //   86: return
    //   87: astore_1
    //   88: aload 4
    //   90: astore_1
    //   91: aload_1
    //   92: ifnull +7 -> 99
    //   95: aload_1
    //   96: invokevirtual 127	java/io/FileInputStream:close	()V
    //   99: aload_2
    //   100: ifnull -14 -> 86
    //   103: aload_2
    //   104: arraylength
    //   105: ifle -19 -> 86
    //   108: aload_0
    //   109: aload_2
    //   110: invokestatic 121	com/sina/weibo/sdk/utils/Base64:encodebyte	([B)[B
    //   113: putfield 123	com/sina/weibo/sdk/component/ShareRequestParam:mBase64ImgData	[B
    //   116: return
    //   117: astore_1
    //   118: aload_3
    //   119: ifnull +7 -> 126
    //   122: aload_3
    //   123: invokevirtual 127	java/io/FileInputStream:close	()V
    //   126: aload_1
    //   127: athrow
    //   128: astore_1
    //   129: goto -30 -> 99
    //   132: astore_1
    //   133: return
    //   134: astore_1
    //   135: goto -36 -> 99
    //   138: astore_3
    //   139: goto -13 -> 126
    //   142: astore 4
    //   144: aload_1
    //   145: astore_3
    //   146: aload 4
    //   148: astore_1
    //   149: goto -31 -> 118
    //   152: astore_3
    //   153: goto -62 -> 91
    //
    // Exception table:
    //   from	to	target	type
    //   53	62	87	java/io/IOException
    //   53	62	117	finally
    //   0	48	128	java/lang/SecurityException
    //   82	86	128	java/lang/SecurityException
    //   95	99	128	java/lang/SecurityException
    //   122	126	128	java/lang/SecurityException
    //   126	128	128	java/lang/SecurityException
    //   82	86	132	java/lang/Exception
    //   95	99	134	java/lang/Exception
    //   122	126	138	java/lang/Exception
    //   62	78	142	finally
    //   62	78	152	java/io/IOException
  }

  private void handleSharedMessage(Bundle paramBundle)
  {
    Object localObject1 = new WeiboMultiMessage();
    ((WeiboMultiMessage)localObject1).toObject(paramBundle);
    paramBundle = new StringBuilder();
    if ((((WeiboMultiMessage)localObject1).textObject instanceof TextObject))
      paramBundle.append(((WeiboMultiMessage)localObject1).textObject.text);
    Object localObject2;
    if ((((WeiboMultiMessage)localObject1).imageObject instanceof ImageObject))
    {
      localObject2 = ((WeiboMultiMessage)localObject1).imageObject;
      handleMblogPic(((ImageObject)localObject2).imagePath, ((ImageObject)localObject2).imageData);
    }
    if ((((WeiboMultiMessage)localObject1).mediaObject instanceof TextObject))
      paramBundle.append(((TextObject)((WeiboMultiMessage)localObject1).mediaObject).text);
    if ((((WeiboMultiMessage)localObject1).mediaObject instanceof ImageObject))
    {
      localObject2 = (ImageObject)((WeiboMultiMessage)localObject1).mediaObject;
      handleMblogPic(((ImageObject)localObject2).imagePath, ((ImageObject)localObject2).imageData);
    }
    if ((((WeiboMultiMessage)localObject1).mediaObject instanceof WebpageObject))
    {
      localObject2 = (WebpageObject)((WeiboMultiMessage)localObject1).mediaObject;
      paramBundle.append(" ").append(((WebpageObject)localObject2).actionUrl);
    }
    if ((((WeiboMultiMessage)localObject1).mediaObject instanceof MusicObject))
    {
      localObject2 = (MusicObject)((WeiboMultiMessage)localObject1).mediaObject;
      paramBundle.append(" ").append(((MusicObject)localObject2).actionUrl);
    }
    if ((((WeiboMultiMessage)localObject1).mediaObject instanceof VideoObject))
    {
      localObject2 = (VideoObject)((WeiboMultiMessage)localObject1).mediaObject;
      paramBundle.append(" ").append(((VideoObject)localObject2).actionUrl);
    }
    if ((((WeiboMultiMessage)localObject1).mediaObject instanceof VoiceObject))
    {
      localObject1 = (VoiceObject)((WeiboMultiMessage)localObject1).mediaObject;
      paramBundle.append(" ").append(((VoiceObject)localObject1).actionUrl);
    }
    this.mShareContent = paramBundle.toString();
  }

  private void sendSdkResponse(Activity paramActivity, int paramInt, String paramString)
  {
    Bundle localBundle = paramActivity.getIntent().getExtras();
    if (localBundle == null)
      return;
    Intent localIntent = new Intent("com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY");
    localIntent.setFlags(131072);
    localIntent.setPackage(localBundle.getString("_weibo_appPackage"));
    localIntent.putExtras(localBundle);
    localIntent.putExtra("_weibo_appPackage", paramActivity.getPackageName());
    localIntent.putExtra("_weibo_resp_errcode", paramInt);
    localIntent.putExtra("_weibo_resp_errstr", paramString);
    try
    {
      paramActivity.startActivityForResult(localIntent, 765);
      return;
    }
    catch (ActivityNotFoundException paramActivity)
    {
    }
  }

  public WeiboParameters buildUploadPicParam(WeiboParameters paramWeiboParameters)
  {
    if (!hasImage())
      return paramWeiboParameters;
    paramWeiboParameters.put("img", new String(this.mBase64ImgData));
    return paramWeiboParameters;
  }

  public String buildUrl(String paramString)
  {
    Uri.Builder localBuilder = Uri.parse("http://service.weibo.com/share/mobilesdk.php").buildUpon();
    localBuilder.appendQueryParameter("title", this.mShareContent);
    localBuilder.appendQueryParameter("version", "0030105000");
    if (!TextUtils.isEmpty(this.mAppKey))
      localBuilder.appendQueryParameter("source", this.mAppKey);
    if (!TextUtils.isEmpty(this.mToken))
      localBuilder.appendQueryParameter("access_token", this.mToken);
    String str = Utility.getAid(this.mContext, this.mAppKey);
    if (!TextUtils.isEmpty(str))
      localBuilder.appendQueryParameter("aid", str);
    if (!TextUtils.isEmpty(this.mAppPackage))
      localBuilder.appendQueryParameter("packagename", this.mAppPackage);
    if (!TextUtils.isEmpty(this.mHashKey))
      localBuilder.appendQueryParameter("key_hash", this.mHashKey);
    if (!TextUtils.isEmpty(paramString))
      localBuilder.appendQueryParameter("picinfo", paramString);
    return localBuilder.build().toString();
  }

  public void execRequest(Activity paramActivity, int paramInt)
  {
    if (paramInt == 3)
    {
      sendSdkCancleResponse(paramActivity);
      WeiboSdkBrowser.closeBrowser(paramActivity, this.mAuthListenerKey, null);
    }
  }

  public String getAppKey()
  {
    return this.mAppKey;
  }

  public String getAppPackage()
  {
    return this.mAppPackage;
  }

  public WeiboAuthListener getAuthListener()
  {
    return this.mAuthListener;
  }

  public String getAuthListenerKey()
  {
    return this.mAuthListenerKey;
  }

  public byte[] getBase64ImgData()
  {
    return this.mBase64ImgData;
  }

  public String getHashKey()
  {
    return this.mHashKey;
  }

  public String getShareContent()
  {
    return this.mShareContent;
  }

  public String getToken()
  {
    return this.mToken;
  }

  public boolean hasImage()
  {
    return (this.mBase64ImgData != null) && (this.mBase64ImgData.length > 0);
  }

  public void onCreateRequestParamBundle(Bundle paramBundle)
  {
    if (this.mBaseRequest != null)
      this.mBaseRequest.toBundle(paramBundle);
    if (!TextUtils.isEmpty(this.mAppPackage))
      this.mHashKey = MD5.hexdigest(Utility.getSign(this.mContext, this.mAppPackage));
    paramBundle.putString("access_token", this.mToken);
    paramBundle.putString("source", this.mAppKey);
    paramBundle.putString("packagename", this.mAppPackage);
    paramBundle.putString("key_hash", this.mHashKey);
    paramBundle.putString("_weibo_appPackage", this.mAppPackage);
    paramBundle.putString("_weibo_appKey", this.mAppKey);
    paramBundle.putInt("_weibo_flag", 538116905);
    paramBundle.putString("_weibo_sign", this.mHashKey);
    if (this.mAuthListener != null)
    {
      WeiboCallbackManager localWeiboCallbackManager = WeiboCallbackManager.getInstance(this.mContext);
      this.mAuthListenerKey = localWeiboCallbackManager.genCallbackKey();
      localWeiboCallbackManager.setWeiboAuthListener(this.mAuthListenerKey, this.mAuthListener);
      paramBundle.putString("key_listener", this.mAuthListenerKey);
    }
  }

  protected void onSetupRequestParam(Bundle paramBundle)
  {
    this.mAppKey = paramBundle.getString("source");
    this.mAppPackage = paramBundle.getString("packagename");
    this.mHashKey = paramBundle.getString("key_hash");
    this.mToken = paramBundle.getString("access_token");
    this.mAuthListenerKey = paramBundle.getString("key_listener");
    if (!TextUtils.isEmpty(this.mAuthListenerKey))
      this.mAuthListener = WeiboCallbackManager.getInstance(this.mContext).getWeiboAuthListener(this.mAuthListenerKey);
    handleSharedMessage(paramBundle);
    this.mUrl = buildUrl("");
  }

  public void sendSdkCancleResponse(Activity paramActivity)
  {
    sendSdkResponse(paramActivity, 1, "send cancel!!!");
  }

  public void sendSdkErrorResponse(Activity paramActivity, String paramString)
  {
    sendSdkResponse(paramActivity, 2, paramString);
  }

  public void sendSdkOkResponse(Activity paramActivity)
  {
    sendSdkResponse(paramActivity, 0, "send ok!!!");
  }

  public void setAppKey(String paramString)
  {
    this.mAppKey = paramString;
  }

  public void setAppPackage(String paramString)
  {
    this.mAppPackage = paramString;
  }

  public void setAuthListener(WeiboAuthListener paramWeiboAuthListener)
  {
    this.mAuthListener = paramWeiboAuthListener;
  }

  public void setBaseRequest(BaseRequest paramBaseRequest)
  {
    this.mBaseRequest = paramBaseRequest;
  }

  public void setToken(String paramString)
  {
    this.mToken = paramString;
  }

  public static class UploadPicResult
  {
    private int code = -2;
    private String picId;

    public static UploadPicResult parse(String paramString)
    {
      if (TextUtils.isEmpty(paramString))
        return null;
      UploadPicResult localUploadPicResult = new UploadPicResult();
      try
      {
        paramString = new JSONObject(paramString);
        localUploadPicResult.code = paramString.optInt("code", -2);
        localUploadPicResult.picId = paramString.optString("data", "");
        return localUploadPicResult;
      }
      catch (JSONException paramString)
      {
      }
      return localUploadPicResult;
    }

    public int getCode()
    {
      return this.code;
    }

    public String getPicId()
    {
      return this.picId;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.component.ShareRequestParam
 * JD-Core Version:    0.6.2
 */