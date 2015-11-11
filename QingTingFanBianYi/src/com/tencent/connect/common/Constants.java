package com.tencent.connect.common;

public class Constants
{
  public static final int ACTIVITY_CANCEL = 0;
  public static final int ACTIVITY_OK = -1;
  public static final String CANCEL_URI = "auth://cancel";
  public static final String CLOSE_URI = "auth://close";
  public static final int CODE_REQUEST_MAX = 6656;
  public static final int CODE_REQUEST_MIN = 5656;
  public static final String DEFAULT_PF = "openmobile_android";
  public static final String DOWNLOAD_URI = "download://";
  public static final int ERROR_CONNECTTIMEOUT = -7;
  public static final int ERROR_FILE_EXISTED = -11;
  public static final int ERROR_HTTPSTATUS_ERROR = -9;
  public static final int ERROR_IO = -2;
  public static final int ERROR_JSON = -4;
  public static final int ERROR_LOCATION_TIMEOUT = -13;
  public static final int ERROR_LOCATION_VERIFY_FAILED = -14;
  public static final int ERROR_NETWORK_UNAVAILABLE = -10;
  public static final int ERROR_NO_SDCARD = -12;
  public static final int ERROR_PARAM = -5;
  public static final int ERROR_SOCKETTIMEOUT = -8;
  public static final int ERROR_UNKNOWN = -6;
  public static final int ERROR_URL = -3;
  public static final String GRAPH_BASE = "https://openmobile.qq.com/";
  public static final String GRAPH_INTIMATE_FRIENDS = "friends/get_intimate_friends_weibo";
  public static final String GRAPH_NICK_TIPS = "friends/match_nick_tips_weibo";
  public static final String HTTP_GET = "GET";
  public static final String HTTP_POST = "POST";
  public static final String KEY_ACTION = "key_action";
  public static final String KEY_APP_NAME = "oauth_app_name";
  public static final String KEY_ERROR_CODE = "key_error_code";
  public static final String KEY_ERROR_DETAIL = "key_error_detail";
  public static final String KEY_ERROR_MSG = "key_error_msg";
  public static final String KEY_PARAMS = "key_params";
  public static final String KEY_REQUEST_CODE = "key_request_code";
  public static final String KEY_RESPONSE = "key_response";
  public static final String MOBILEQQ_PACKAGE_NAME = "com.tencent.mobileqq";
  public static final String MSG_CONNECTTIMEOUT_ERROR = "网络连接超时!";
  public static final String MSG_IMAGE_ERROR = "图片读取失败，请检查该图片是否有效";
  public static final String MSG_IO_ERROR = "网络连接异常，请检查后重试!";
  public static final String MSG_JSON_ERROR = "服务器返回数据格式有误!";
  public static final String MSG_LOCATION_TIMEOUT_ERROR = "定位超时，请稍后再试或检查网络状况！";
  public static final String MSG_LOCATION_VERIFY_ERROR = "定位失败，验证定位码错误！";
  public static final String MSG_NO_SDCARD = "检测不到SD卡，无法发送语音！";
  public static final String MSG_OPEN_BROWSER_ERROR = "打开浏览器失败!";
  public static final String MSG_PARAM_ERROR = "传入参数有误!";
  public static final String MSG_PARAM_IMAGE_ERROR = "纯图分享，imageUrl 不能为空";
  public static final String MSG_PARAM_IMAGE_URL_FORMAT_ERROR = "非法的图片地址!";
  public static final String MSG_PARAM_IMAGE_URL_MUST_BE_LOCAL = "手Q版本过低，纯图分享不支持网路图片";
  public static final String MSG_PARAM_NULL_ERROR = "传入参数不可以为空";
  public static final String MSG_PARAM_QQ_VERSION_ERROR = "低版本手Q不支持该项功能!";
  public static final String MSG_PARAM_TARGETURL_ERROR = "targetUrl有误";
  public static final String MSG_PARAM_TARGETURL_NULL_ERROR = "targetUrl为必填项，请补充后分享";
  public static final String MSG_PARAM_TITLE_NULL_ERROR = "title不能为空!";
  public static final String MSG_SHARE_GETIMG_ERROR = "获取分享图片失败!";
  public static final String MSG_SHARE_NOSD_ERROR = "分享图片失败，检测不到SD卡!";
  public static final String MSG_SHARE_TO_QQ_ERROR = "分享的手机QQ失败!";
  public static final String MSG_SHARE_TYPE_ERROR = "暂不支持纯图片分享到空间，建议使用图文分享";
  public static final String MSG_SOCKETTIMEOUT_ERROR = "网络连接超时!";
  public static final String MSG_UNKNOWN_ERROR = "未知错误!";
  public static final String MSG_URL_ERROR = "访问url有误!";
  public static String PACKAGE_QQ = "com.tencent.mobileqq";
  public static String PACKAGE_QZONE = "com.qzone";
  public static final String PARAM_ACCESS_TOKEN = "access_token";
  public static final String PARAM_APP_ID = "appid";
  public static final String PARAM_CLIENT_ID = "client_id";
  public static final String PARAM_CONSUMER_KEY = "oauth_consumer_key";
  public static final String PARAM_EXPIRES_IN = "expires_in";
  public static final String PARAM_HOPEN_ID = "hopenid";
  public static final String PARAM_KEY_STR = "keystr";
  public static final String PARAM_KEY_TYPE = "keytype";
  public static final String PARAM_OPEN_ID = "openid";
  public static final String PARAM_PLATFORM = "platform";
  public static final String PARAM_PLATFORM_ID = "pf";
  public static final String PARAM_SCOPE = "scope";
  public static final String PREFERENCE_PF = "pfStore";
  public static final String SDK_BUILD = "1171";
  public static final String SDK_QUA = "V1_AND_OpenSDK_2.2.1_1077_RDM_B";
  public static final String SDK_VERSION = "2.2.1";
  public static final String SDK_VERSION_STRING = "Android_SDK_2.2.1";
  public static String SIGNATRUE_QZONE = "ec96e9ac1149251acbb1b0c5777cae95";
  public static final String SOURCE_QQ = "QQ";
  public static final String SOURCE_QZONE = "qzone";
  public static final int UI_ACTIVITY = 1;
  public static final int UI_CHECK_TOKEN = 3;
  public static final int UI_DIALOG = 2;
  public static final int UI_NONE = -1;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.common.Constants
 * JD-Core Version:    0.6.2
 */