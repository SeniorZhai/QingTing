package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;
import java.io.ByteArrayOutputStream;

public class WeiboAPI extends BaseAPI
{
  private static final String SERVER_URL_ADD = "http://open.t.qq.com/api/t/add_multi";
  private static final String SERVER_URL_ADDMULTIURL = "http://open.t.qq.com/api/t/add_multi";
  private static final String SERVER_URL_ADDPIC = "http://open.t.qq.com/api/t/add_pic";
  private static final String SERVER_URL_ADDPICURL = "http://open.t.qq.com/api/t/add_pic_url";
  private static final String SERVER_URL_ADDWEIBO = "http://open.t.qq.com/api/t/add";
  private static final String SERVER_URL_RLIST = "http://open.t.qq.com/api/t/re_list";
  private static final String SERVER_URL_VIDEO = "http://open.t.qq.com/api/t/getvideoinfo";

  public WeiboAPI(AccountModel paramAccountModel)
  {
    super(paramAccountModel);
  }

  public void addMulti(Context paramContext, String paramString1, String paramString2, double paramDouble1, double paramDouble2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt1, int paramInt2, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt3)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("format", paramString1);
    localReqParam.addParam("content", paramString2);
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    if ((paramDouble1 != 0.0D) && (paramDouble2 != 0.0D))
    {
      localReqParam.addParam("longitude", Double.valueOf(paramDouble1));
      localReqParam.addParam("latitude", Double.valueOf(paramDouble2));
    }
    if ((paramString3 != null) && (!"".equals(paramString3)))
      localReqParam.addParam("pic_url", paramString3);
    if ((paramString4 != null) && (!"".equals(paramString4)))
    {
      localReqParam.addParam("video_url", paramString4);
      if ((paramString5 != null) && (!"".equals(paramString5)))
        localReqParam.addParam("video_title", paramString5);
    }
    if ((paramString6 != null) && (!"".equals(paramString6)) && (paramString7 != null) && (!"".equals(paramString7)) && (paramString8 != null) && (!"".equals(paramString8)))
    {
      localReqParam.addParam("music_url", paramString6);
      localReqParam.addParam("music_title", paramString7);
      localReqParam.addParam("music_author", paramString8);
    }
    localReqParam.addParam("syncflag", Integer.valueOf(paramInt1));
    localReqParam.addParam("compatibleflag", Integer.valueOf(paramInt2));
    startRequest(paramContext, "http://open.t.qq.com/api/t/add_multi", localReqParam, paramHttpCallback, paramClass, "POST", paramInt3);
  }

  public void addPic(Context paramContext, String paramString1, String paramString2, double paramDouble1, double paramDouble2, Bitmap paramBitmap, int paramInt1, int paramInt2, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt3)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("format", paramString2);
    if (paramString1 != null)
    {
      paramString2 = paramString1;
      if (!"".equals(paramString1));
    }
    else
    {
      paramString2 = "#分享图片#";
    }
    localReqParam.addParam("content", paramString2);
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    if (paramDouble1 != 0.0D)
      localReqParam.addParam("longitude", Double.valueOf(paramDouble1));
    if (paramDouble2 != 0.0D)
      localReqParam.addParam("latitude", Double.valueOf(paramDouble2));
    localReqParam.addParam("syncflag", Integer.valueOf(paramInt1));
    localReqParam.addParam("compatibleflag", Integer.valueOf(paramInt2));
    localReqParam.setBitmap(paramBitmap);
    paramString1 = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, paramString1);
    localReqParam.addParam("pic", paramString1.toByteArray());
    startRequest(paramContext, "http://open.t.qq.com/api/t/add_pic", localReqParam, paramHttpCallback, paramClass, "POST", paramInt3);
  }

  public void addPicUrl(Context paramContext, String paramString1, String paramString2, double paramDouble1, double paramDouble2, String paramString3, int paramInt1, int paramInt2, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt3)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("format", paramString2);
    localReqParam.addParam("content", paramString1);
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    if (paramDouble1 != 0.0D)
      localReqParam.addParam("longitude", Double.valueOf(paramDouble1));
    if (paramDouble2 != 0.0D)
      localReqParam.addParam("latitude", Double.valueOf(paramDouble2));
    localReqParam.addParam("syncflag", Integer.valueOf(paramInt1));
    localReqParam.addParam("compatibleflag", Integer.valueOf(paramInt2));
    localReqParam.addParam("pic_url", paramString3);
    startRequest(paramContext, "http://open.t.qq.com/api/t/add_pic_url", localReqParam, paramHttpCallback, paramClass, "POST", paramInt3);
  }

  public void addWeibo(Context paramContext, String paramString1, String paramString2, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt3)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("format", paramString2);
    localReqParam.addParam("content", paramString1);
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    if (paramDouble1 != 0.0D)
      localReqParam.addParam("longitude", Double.valueOf(paramDouble1));
    if (paramDouble2 != 0.0D)
      localReqParam.addParam("latitude", Double.valueOf(paramDouble2));
    localReqParam.addParam("syncflag", Integer.valueOf(paramInt1));
    localReqParam.addParam("compatibleflag", Integer.valueOf(paramInt2));
    startRequest(paramContext, "http://open.t.qq.com/api/t/add", localReqParam, paramHttpCallback, paramClass, "POST", paramInt3);
  }

  public void getVideoInfo(Context paramContext, String paramString, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", "json");
    localReqParam.addParam("video_url", paramString);
    startRequest(paramContext, "http://open.t.qq.com/api/t/getvideoinfo", localReqParam, paramHttpCallback, paramClass, "POST", paramInt);
  }

  public void reAddWeibo(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("content", paramString1);
    localReqParam.addParam("pic_url", paramString2);
    localReqParam.addParam("video_url", paramString3);
    localReqParam.addParam("music_url", paramString4);
    localReqParam.addParam("music_title", paramString5);
    localReqParam.addParam("music_author", paramString6);
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("pageflag", "0");
    localReqParam.addParam("type", "0");
    localReqParam.addParam("format", "json");
    localReqParam.addParam("reqnum", "30");
    localReqParam.addParam("pagetime", "0");
    localReqParam.addParam("contenttype", "0");
    startRequest(paramContext, "http://open.t.qq.com/api/t/add_multi", localReqParam, paramHttpCallback, paramClass, "POST", paramInt);
  }

  public void reList(Context paramContext, String paramString1, int paramInt1, String paramString2, int paramInt2, String paramString3, int paramInt3, String paramString4, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt4)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("format", paramString1);
    localReqParam.addParam("flag", Integer.valueOf(paramInt1));
    localReqParam.addParam("rootid", paramString2);
    localReqParam.addParam("pageflag", Integer.valueOf(paramInt2));
    localReqParam.addParam("pagetime", paramString3);
    localReqParam.addParam("reqnum", Integer.valueOf(paramInt3));
    localReqParam.addParam("twitterid", paramString4);
    startRequest(paramContext, "http://open.t.qq.com/api/t/re_list", localReqParam, paramHttpCallback, paramClass, "GET", paramInt4);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.WeiboAPI
 * JD-Core Version:    0.6.2
 */