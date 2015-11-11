package com.tencent.weiyun;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.DataConvert;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileManager extends BaseApi
{
  private static final String[] a = { "https://graph.qq.com/weiyun/get_photo_list", "https://graph.qq.com/weiyun/get_music_list", "https://graph.qq.com/weiyun/get_video_list" };
  private static final String[] b = { "https://graph.qq.com/weiyun/delete_photo", "https://graph.qq.com/weiyun/delete_music", "https://graph.qq.com/weiyun/delete_video" };

  public FileManager(Context paramContext, QQAuth paramQQAuth, QQToken paramQQToken)
  {
    super(paramContext, paramQQAuth, paramQQToken);
  }

  public FileManager(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
  }

  public void deleteFile(WeiyunFileType paramWeiyunFileType, String paramString, IUiListener paramIUiListener)
  {
    paramWeiyunFileType = b[paramWeiyunFileType.value()];
    Bundle localBundle = composeCGIParams();
    localBundle.putString("file_id", paramString);
    paramString = new BaseApi.TempRequestListener(this, paramIUiListener);
    HttpUtils.requestAsync(this.mToken, this.mContext, paramWeiyunFileType, localBundle, "GET", paramString);
  }

  public void downLoadFile(WeiyunFileType paramWeiyunFileType, WeiyunFile paramWeiyunFile, String paramString, IDownLoadFileCallBack paramIDownLoadFileCallBack)
  {
    new DownLoadImp(this.mContext, paramWeiyunFileType, paramWeiyunFile, paramString, paramIDownLoadFileCallBack).start();
  }

  public void downLoadThumb(WeiyunFile paramWeiyunFile, String paramString1, String paramString2, IDownLoadFileCallBack paramIDownLoadFileCallBack)
  {
    paramWeiyunFile = new DownLoadImp(this.mContext, WeiyunFileType.ImageFile, paramWeiyunFile, paramString1, paramIDownLoadFileCallBack);
    paramWeiyunFile.setThumbSize(paramString2);
    paramWeiyunFile.start();
  }

  public void getFileList(WeiyunFileType paramWeiyunFileType, IGetFileListListener paramIGetFileListListener)
  {
    paramWeiyunFileType = a[paramWeiyunFileType.value()];
    Bundle localBundle = composeCGIParams();
    localBundle.putString("offset", "0");
    localBundle.putString("number", "100");
    paramIGetFileListListener = new BaseApi.TempRequestListener(this, new GetFileListListener(paramIGetFileListListener));
    HttpUtils.requestAsync(this.mToken, this.mContext, paramWeiyunFileType, localBundle, "GET", paramIGetFileListListener);
  }

  public void uploadFile(WeiyunFileType paramWeiyunFileType, String paramString, IUploadFileCallBack paramIUploadFileCallBack)
  {
    new UploadFileImp(this.mContext, paramWeiyunFileType, paramString, paramIUploadFileCallBack).start();
  }

  private class DownLoadImp
  {
    private static final String DOWNLOAD_COOKIE_NAME = "dl_cookie_name";
    private static final String DOWNLOAD_COOKIE_VALUE = "dl_cookie_value";
    private static final String DOWNLOAD_ENCRYPT_URL = "dl_encrypt_url";
    private static final String DOWNLOAD_MUSIC_URL = "https://graph.qq.com/weiyun/download_music";
    private static final String DOWNLOAD_PIC_URL = "https://graph.qq.com/weiyun/download_photo";
    private static final int DOWNLOAD_PROGRESS = 1;
    private static final int DOWNLOAD_PROGRESS_DONE = 2;
    private static final String DOWNLOAD_SERVER_HOST = "dl_svr_host";
    private static final String DOWNLOAD_SERVER_PORT = "dl_svr_port";
    private static final String DOWNLOAD_THUMB_SIZE = "dl_thumb_size";
    private static final String DOWNLOAD_THUMB_URL = "https://graph.qq.com/weiyun/get_photo_thumb";
    private static final String DOWNLOAD_VIDEO_URL = "https://graph.qq.com/weiyun/download_video";
    private static final int GET_PERMISSON_DOWN = 0;
    private static final int MAX_ERROR_TIMES = 10;
    private IDownLoadFileCallBack mCallback;
    private Context mContext;
    private String mDownloadCookieName;
    private String mDownloadCookieValue;
    private String mDownloadEncryptUrl;
    private String mDownloadServerHost;
    private int mDownloadServerPort;
    private String mDownloadThumbSize;
    private File mFile;
    private FileManager.WeiyunFileType mFileType;
    private Handler mHandler;
    private String mSavePath;
    private String mThumbSize;
    private WeiyunFile mWeiyunFile;

    public DownLoadImp(Context paramWeiyunFileType, FileManager.WeiyunFileType paramWeiyunFile, WeiyunFile paramString, String paramIDownLoadFileCallBack, IDownLoadFileCallBack arg6)
    {
      this.mContext = paramWeiyunFileType;
      this.mFileType = paramWeiyunFile;
      this.mWeiyunFile = paramString;
      this.mSavePath = paramIDownLoadFileCallBack;
      Object localObject;
      this.mCallback = localObject;
      this.mHandler = new Handler(this.mContext.getMainLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          int i;
          switch (paramAnonymousMessage.what)
          {
          default:
            FileManager.DownLoadImp.this.mCallback.onError(new UiError(paramAnonymousMessage.what, (String)paramAnonymousMessage.obj, null));
            return;
          case 0:
            paramAnonymousMessage = (JSONObject)paramAnonymousMessage.obj;
            try
            {
              i = paramAnonymousMessage.getInt("ret");
              if (i != 0)
              {
                FileManager.DownLoadImp.this.mCallback.onError(new UiError(i, paramAnonymousMessage.toString(), null));
                return;
              }
            }
            catch (JSONException paramAnonymousMessage)
            {
              FileManager.DownLoadImp.this.mCallback.onError(new UiError(-4, paramAnonymousMessage.getMessage(), null));
              return;
            }
            paramAnonymousMessage = paramAnonymousMessage.getJSONObject("data");
            FileManager.DownLoadImp.access$1602(FileManager.DownLoadImp.this, paramAnonymousMessage.getString("dl_encrypt_url"));
            FileManager.DownLoadImp.access$1702(FileManager.DownLoadImp.this, paramAnonymousMessage.getString("dl_cookie_name"));
            FileManager.DownLoadImp.access$1802(FileManager.DownLoadImp.this, paramAnonymousMessage.getString("dl_cookie_value"));
            FileManager.DownLoadImp.access$1902(FileManager.DownLoadImp.this, paramAnonymousMessage.getInt("dl_svr_port"));
            FileManager.DownLoadImp.access$2002(FileManager.DownLoadImp.this, paramAnonymousMessage.getString("dl_svr_host"));
            if (paramAnonymousMessage.has("dl_thumb_size"))
              FileManager.DownLoadImp.access$2102(FileManager.DownLoadImp.this, paramAnonymousMessage.getString("dl_thumb_size"));
            FileManager.DownLoadImp.this.mCallback.onDownloadStart();
            FileManager.DownLoadImp.this.doDownload();
            return;
          case 1:
            i = Integer.parseInt((String)paramAnonymousMessage.obj);
            FileManager.DownLoadImp.this.mCallback.onDownloadProgress(i);
            return;
          case 2:
          }
          FileManager.DownLoadImp.this.mCallback.onDownloadSuccess(FileManager.DownLoadImp.this.mSavePath);
        }
      };
    }

    private void doDownload()
    {
      new Thread()
      {
        // ERROR //
        public void run()
        {
          // Byte code:
          //   0: new 30	org/apache/http/params/BasicHttpParams
          //   3: dup
          //   4: invokespecial 31	org/apache/http/params/BasicHttpParams:<init>	()V
          //   7: astore_2
          //   8: aload_2
          //   9: sipush 15000
          //   12: invokestatic 37	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
          //   15: aload_2
          //   16: sipush 20000
          //   19: invokestatic 40	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
          //   22: aload_2
          //   23: getstatic 46	org/apache/http/HttpVersion:HTTP_1_1	Lorg/apache/http/HttpVersion;
          //   26: invokestatic 52	org/apache/http/params/HttpProtocolParams:setVersion	(Lorg/apache/http/params/HttpParams;Lorg/apache/http/ProtocolVersion;)V
          //   29: aload_2
          //   30: ldc 54
          //   32: invokestatic 58	org/apache/http/params/HttpProtocolParams:setContentCharset	(Lorg/apache/http/params/HttpParams;Ljava/lang/String;)V
          //   35: aload_2
          //   36: ldc 60
          //   38: invokestatic 63	org/apache/http/params/HttpProtocolParams:setUserAgent	(Lorg/apache/http/params/HttpParams;Ljava/lang/String;)V
          //   41: new 65	java/lang/StringBuilder
          //   44: dup
          //   45: invokespecial 66	java/lang/StringBuilder:<init>	()V
          //   48: ldc 68
          //   50: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   53: aload_0
          //   54: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   57: invokestatic 76	com/tencent/weiyun/FileManager$DownLoadImp:access$2000	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Ljava/lang/String;
          //   60: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   63: ldc 78
          //   65: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   68: aload_0
          //   69: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   72: invokestatic 82	com/tencent/weiyun/FileManager$DownLoadImp:access$1900	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)I
          //   75: invokevirtual 85	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   78: ldc 87
          //   80: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   83: aload_0
          //   84: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   87: invokestatic 90	com/tencent/weiyun/FileManager$DownLoadImp:access$1600	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Ljava/lang/String;
          //   90: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   93: ldc 92
          //   95: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   98: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   101: astore_1
          //   102: aload_1
          //   103: astore_3
          //   104: aload_0
          //   105: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   108: invokestatic 99	com/tencent/weiyun/FileManager$DownLoadImp:access$2100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Ljava/lang/String;
          //   111: invokestatic 105	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
          //   114: ifne +33 -> 147
          //   117: new 65	java/lang/StringBuilder
          //   120: dup
          //   121: invokespecial 66	java/lang/StringBuilder:<init>	()V
          //   124: aload_1
          //   125: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   128: ldc 107
          //   130: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   133: aload_0
          //   134: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   137: invokestatic 99	com/tencent/weiyun/FileManager$DownLoadImp:access$2100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Ljava/lang/String;
          //   140: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   143: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   146: astore_3
          //   147: new 109	org/apache/http/impl/client/DefaultHttpClient
          //   150: dup
          //   151: aload_2
          //   152: invokespecial 112	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
          //   155: astore 7
          //   157: aconst_null
          //   158: astore 4
          //   160: aconst_null
          //   161: astore_2
          //   162: new 114	java/io/File
          //   165: dup
          //   166: aload_0
          //   167: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   170: invokestatic 117	com/tencent/weiyun/FileManager$DownLoadImp:access$2300	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Ljava/lang/String;
          //   173: invokespecial 120	java/io/File:<init>	(Ljava/lang/String;)V
          //   176: astore_1
          //   177: new 122	java/io/FileOutputStream
          //   180: dup
          //   181: aload_1
          //   182: invokespecial 125	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
          //   185: astore 5
          //   187: ldc 126
          //   189: newarray byte
          //   191: astore 6
          //   193: aload_0
          //   194: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   197: invokestatic 129	com/tencent/weiyun/FileManager$DownLoadImp:access$2600	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Ljava/lang/String;
          //   200: invokestatic 105	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
          //   203: ifeq +717 -> 920
          //   206: aload_0
          //   207: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   210: invokestatic 133	com/tencent/weiyun/FileManager$DownLoadImp:access$2500	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Lcom/tencent/weiyun/WeiyunFile;
          //   213: invokevirtual 139	com/tencent/weiyun/WeiyunFile:getFileSize	()J
          //   216: ldc2_w 140
          //   219: lcmp
          //   220: ifle +400 -> 620
          //   223: ldc2_w 140
          //   226: lstore 11
          //   228: iconst_0
          //   229: istore 8
          //   231: lconst_0
          //   232: lstore 13
          //   234: aconst_null
          //   235: astore_2
          //   236: lload 11
          //   238: lconst_0
          //   239: ladd
          //   240: lstore 15
          //   242: lload 13
          //   244: lstore 11
          //   246: aload_2
          //   247: astore_1
          //   248: lload 15
          //   250: aload_0
          //   251: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   254: invokestatic 133	com/tencent/weiyun/FileManager$DownLoadImp:access$2500	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Lcom/tencent/weiyun/WeiyunFile;
          //   257: invokevirtual 139	com/tencent/weiyun/WeiyunFile:getFileSize	()J
          //   260: lcmp
          //   261: ifgt +473 -> 734
          //   264: new 143	org/apache/http/client/methods/HttpGet
          //   267: dup
          //   268: aload_3
          //   269: invokespecial 144	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
          //   272: astore 4
          //   274: aload 4
          //   276: ldc 146
          //   278: ldc 148
          //   280: invokeinterface 154 3 0
          //   285: aload 4
          //   287: ldc 156
          //   289: aload_0
          //   290: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   293: invokestatic 76	com/tencent/weiyun/FileManager$DownLoadImp:access$2000	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Ljava/lang/String;
          //   296: invokeinterface 154 3 0
          //   301: aload 4
          //   303: ldc 158
          //   305: ldc 160
          //   307: invokeinterface 154 3 0
          //   312: aload 4
          //   314: ldc 162
          //   316: new 65	java/lang/StringBuilder
          //   319: dup
          //   320: invokespecial 66	java/lang/StringBuilder:<init>	()V
          //   323: aload_0
          //   324: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   327: invokestatic 165	com/tencent/weiyun/FileManager$DownLoadImp:access$1700	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Ljava/lang/String;
          //   330: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   333: ldc 167
          //   335: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   338: aload_0
          //   339: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   342: invokestatic 170	com/tencent/weiyun/FileManager$DownLoadImp:access$1800	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Ljava/lang/String;
          //   345: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   348: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   351: invokeinterface 154 3 0
          //   356: aload 4
          //   358: ldc 172
          //   360: ldc 174
          //   362: invokeinterface 154 3 0
          //   367: aload 4
          //   369: ldc 176
          //   371: new 65	java/lang/StringBuilder
          //   374: dup
          //   375: invokespecial 66	java/lang/StringBuilder:<init>	()V
          //   378: ldc 178
          //   380: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   383: lload 11
          //   385: invokevirtual 181	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
          //   388: ldc 183
          //   390: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   393: lload 15
          //   395: invokevirtual 181	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
          //   398: ldc 185
          //   400: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   403: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   406: invokeinterface 154 3 0
          //   411: lload 11
          //   413: lstore 13
          //   415: aload_2
          //   416: astore_1
          //   417: aload 7
          //   419: aload 4
          //   421: invokeinterface 191 2 0
          //   426: astore 4
          //   428: lload 11
          //   430: lstore 13
          //   432: aload_2
          //   433: astore_1
          //   434: ldc 193
          //   436: new 65	java/lang/StringBuilder
          //   439: dup
          //   440: invokespecial 66	java/lang/StringBuilder:<init>	()V
          //   443: ldc 195
          //   445: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   448: aload 4
          //   450: invokevirtual 198	java/lang/Object:toString	()Ljava/lang/String;
          //   453: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   456: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   459: invokestatic 204	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
          //   462: pop
          //   463: lload 11
          //   465: lstore 13
          //   467: aload_2
          //   468: astore_1
          //   469: aload 4
          //   471: invokeinterface 210 1 0
          //   476: invokeinterface 216 1 0
          //   481: istore 9
          //   483: iload 9
          //   485: sipush 200
          //   488: if_icmpeq +13 -> 501
          //   491: aload_2
          //   492: astore_1
          //   493: iload 9
          //   495: sipush 206
          //   498: if_icmpne +236 -> 734
          //   501: lload 11
          //   503: lstore 13
          //   505: aload_2
          //   506: astore_1
          //   507: aload 4
          //   509: invokeinterface 220 1 0
          //   514: invokeinterface 226 1 0
          //   519: astore 4
          //   521: lload 11
          //   523: lstore 13
          //   525: aload 4
          //   527: astore_1
          //   528: aload 4
          //   530: aload 6
          //   532: invokevirtual 232	java/io/InputStream:read	([B)I
          //   535: istore 10
          //   537: iload 8
          //   539: istore 9
          //   541: lload 11
          //   543: lstore 13
          //   545: aload 4
          //   547: astore_2
          //   548: iload 10
          //   550: ifle +222 -> 772
          //   553: lload 11
          //   555: lstore 13
          //   557: aload 4
          //   559: astore_1
          //   560: aload 5
          //   562: aload 6
          //   564: iconst_0
          //   565: iload 10
          //   567: invokevirtual 236	java/io/FileOutputStream:write	([BII)V
          //   570: lload 11
          //   572: iload 10
          //   574: i2l
          //   575: ladd
          //   576: lstore 11
          //   578: goto -57 -> 521
          //   581: astore_1
          //   582: aload_0
          //   583: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   586: invokestatic 240	com/tencent/weiyun/FileManager$DownLoadImp:access$3100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Landroid/os/Handler;
          //   589: invokevirtual 246	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   592: astore_2
          //   593: aload_2
          //   594: bipush 254
          //   596: putfield 252	android/os/Message:what	I
          //   599: aload_2
          //   600: aload_1
          //   601: invokevirtual 255	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
          //   604: putfield 259	android/os/Message:obj	Ljava/lang/Object;
          //   607: aload_0
          //   608: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   611: invokestatic 240	com/tencent/weiyun/FileManager$DownLoadImp:access$3100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Landroid/os/Handler;
          //   614: aload_2
          //   615: invokevirtual 263	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   618: pop
          //   619: return
          //   620: aload_0
          //   621: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   624: invokestatic 133	com/tencent/weiyun/FileManager$DownLoadImp:access$2500	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Lcom/tencent/weiyun/WeiyunFile;
          //   627: invokevirtual 139	com/tencent/weiyun/WeiyunFile:getFileSize	()J
          //   630: lstore 11
          //   632: goto -404 -> 228
          //   635: astore 4
          //   637: iload 8
          //   639: iconst_1
          //   640: iadd
          //   641: istore 8
          //   643: iload 8
          //   645: istore 9
          //   647: aload_1
          //   648: astore_2
          //   649: iload 8
          //   651: bipush 10
          //   653: if_icmple +119 -> 772
          //   656: aload 4
          //   658: invokevirtual 266	java/lang/Exception:printStackTrace	()V
          //   661: ldc 193
          //   663: new 65	java/lang/StringBuilder
          //   666: dup
          //   667: invokespecial 66	java/lang/StringBuilder:<init>	()V
          //   670: ldc_w 268
          //   673: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   676: aload 4
          //   678: invokevirtual 269	java/lang/Exception:getMessage	()Ljava/lang/String;
          //   681: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   684: ldc 185
          //   686: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   689: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   692: invokestatic 272	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
          //   695: pop
          //   696: aload_0
          //   697: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   700: invokestatic 240	com/tencent/weiyun/FileManager$DownLoadImp:access$3100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Landroid/os/Handler;
          //   703: invokevirtual 246	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   706: astore_2
          //   707: aload_2
          //   708: bipush 254
          //   710: putfield 252	android/os/Message:what	I
          //   713: aload_2
          //   714: aload 4
          //   716: invokevirtual 269	java/lang/Exception:getMessage	()Ljava/lang/String;
          //   719: putfield 259	android/os/Message:obj	Ljava/lang/Object;
          //   722: aload_0
          //   723: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   726: invokestatic 240	com/tencent/weiyun/FileManager$DownLoadImp:access$3100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Landroid/os/Handler;
          //   729: aload_2
          //   730: invokevirtual 263	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   733: pop
          //   734: aload 5
          //   736: invokevirtual 275	java/io/FileOutputStream:close	()V
          //   739: aload_1
          //   740: invokevirtual 276	java/io/InputStream:close	()V
          //   743: aload_0
          //   744: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   747: invokestatic 240	com/tencent/weiyun/FileManager$DownLoadImp:access$3100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Landroid/os/Handler;
          //   750: invokevirtual 246	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   753: astore_1
          //   754: aload_1
          //   755: iconst_2
          //   756: putfield 252	android/os/Message:what	I
          //   759: aload_0
          //   760: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   763: invokestatic 240	com/tencent/weiyun/FileManager$DownLoadImp:access$3100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Landroid/os/Handler;
          //   766: aload_1
          //   767: invokevirtual 263	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   770: pop
          //   771: return
          //   772: aload_2
          //   773: astore_1
          //   774: aload_0
          //   775: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   778: invokestatic 133	com/tencent/weiyun/FileManager$DownLoadImp:access$2500	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Lcom/tencent/weiyun/WeiyunFile;
          //   781: invokevirtual 139	com/tencent/weiyun/WeiyunFile:getFileSize	()J
          //   784: lload 15
          //   786: lsub
          //   787: lconst_0
          //   788: lcmp
          //   789: ifle -55 -> 734
          //   792: aload_0
          //   793: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   796: invokestatic 133	com/tencent/weiyun/FileManager$DownLoadImp:access$2500	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Lcom/tencent/weiyun/WeiyunFile;
          //   799: invokevirtual 139	com/tencent/weiyun/WeiyunFile:getFileSize	()J
          //   802: lload 13
          //   804: lsub
          //   805: ldc2_w 140
          //   808: lcmp
          //   809: ifle +93 -> 902
          //   812: ldc2_w 140
          //   815: lstore 11
          //   817: lload 11
          //   819: lload 13
          //   821: ladd
          //   822: lstore 15
          //   824: aload_0
          //   825: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   828: invokestatic 240	com/tencent/weiyun/FileManager$DownLoadImp:access$3100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Landroid/os/Handler;
          //   831: invokevirtual 246	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   834: astore_1
          //   835: aload_1
          //   836: iconst_1
          //   837: putfield 252	android/os/Message:what	I
          //   840: aload_1
          //   841: new 65	java/lang/StringBuilder
          //   844: dup
          //   845: invokespecial 66	java/lang/StringBuilder:<init>	()V
          //   848: ldc2_w 277
          //   851: lload 15
          //   853: lmul
          //   854: aload_0
          //   855: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   858: invokestatic 133	com/tencent/weiyun/FileManager$DownLoadImp:access$2500	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Lcom/tencent/weiyun/WeiyunFile;
          //   861: invokevirtual 139	com/tencent/weiyun/WeiyunFile:getFileSize	()J
          //   864: ldiv
          //   865: invokevirtual 181	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
          //   868: ldc 185
          //   870: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   873: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   876: putfield 259	android/os/Message:obj	Ljava/lang/Object;
          //   879: aload_0
          //   880: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   883: invokestatic 240	com/tencent/weiyun/FileManager$DownLoadImp:access$3100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Landroid/os/Handler;
          //   886: aload_1
          //   887: invokevirtual 263	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   890: pop
          //   891: iload 9
          //   893: istore 8
          //   895: lload 13
          //   897: lstore 11
          //   899: goto -653 -> 246
          //   902: aload_0
          //   903: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   906: invokestatic 133	com/tencent/weiyun/FileManager$DownLoadImp:access$2500	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Lcom/tencent/weiyun/WeiyunFile;
          //   909: invokevirtual 139	com/tencent/weiyun/WeiyunFile:getFileSize	()J
          //   912: lload 13
          //   914: lsub
          //   915: lstore 11
          //   917: goto -100 -> 817
          //   920: new 143	org/apache/http/client/methods/HttpGet
          //   923: dup
          //   924: aload_3
          //   925: invokespecial 144	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
          //   928: astore_3
          //   929: aload_3
          //   930: ldc 146
          //   932: ldc 148
          //   934: invokeinterface 154 3 0
          //   939: aload_3
          //   940: ldc 156
          //   942: aload_0
          //   943: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   946: invokestatic 76	com/tencent/weiyun/FileManager$DownLoadImp:access$2000	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Ljava/lang/String;
          //   949: invokeinterface 154 3 0
          //   954: aload_3
          //   955: ldc 158
          //   957: ldc 160
          //   959: invokeinterface 154 3 0
          //   964: aload_3
          //   965: ldc 162
          //   967: new 65	java/lang/StringBuilder
          //   970: dup
          //   971: invokespecial 66	java/lang/StringBuilder:<init>	()V
          //   974: aload_0
          //   975: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   978: invokestatic 165	com/tencent/weiyun/FileManager$DownLoadImp:access$1700	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Ljava/lang/String;
          //   981: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   984: ldc 167
          //   986: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   989: aload_0
          //   990: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   993: invokestatic 170	com/tencent/weiyun/FileManager$DownLoadImp:access$1800	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Ljava/lang/String;
          //   996: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   999: ldc 185
          //   1001: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1004: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1007: invokeinterface 154 3 0
          //   1012: aload_3
          //   1013: ldc 172
          //   1015: ldc 174
          //   1017: invokeinterface 154 3 0
          //   1022: aload_2
          //   1023: astore_1
          //   1024: aload 7
          //   1026: aload_3
          //   1027: invokeinterface 191 2 0
          //   1032: astore_3
          //   1033: aload_2
          //   1034: astore_1
          //   1035: ldc 193
          //   1037: new 65	java/lang/StringBuilder
          //   1040: dup
          //   1041: invokespecial 66	java/lang/StringBuilder:<init>	()V
          //   1044: ldc 195
          //   1046: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1049: aload_3
          //   1050: invokevirtual 198	java/lang/Object:toString	()Ljava/lang/String;
          //   1053: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1056: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1059: invokestatic 204	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
          //   1062: pop
          //   1063: aload_2
          //   1064: astore_1
          //   1065: aload_3
          //   1066: invokeinterface 210 1 0
          //   1071: invokeinterface 216 1 0
          //   1076: istore 8
          //   1078: iload 8
          //   1080: sipush 200
          //   1083: if_icmpeq +14 -> 1097
          //   1086: aload 4
          //   1088: astore_1
          //   1089: iload 8
          //   1091: sipush 206
          //   1094: if_icmpne +137 -> 1231
          //   1097: aload_2
          //   1098: astore_1
          //   1099: aload_3
          //   1100: invokeinterface 220 1 0
          //   1105: invokeinterface 226 1 0
          //   1110: astore_2
          //   1111: aload_2
          //   1112: astore_1
          //   1113: aload_2
          //   1114: aload 6
          //   1116: invokevirtual 232	java/io/InputStream:read	([B)I
          //   1119: istore 8
          //   1121: aload_2
          //   1122: astore_1
          //   1123: iload 8
          //   1125: ifle +106 -> 1231
          //   1128: aload_2
          //   1129: astore_1
          //   1130: aload 5
          //   1132: aload 6
          //   1134: iconst_0
          //   1135: iload 8
          //   1137: invokevirtual 236	java/io/FileOutputStream:write	([BII)V
          //   1140: goto -29 -> 1111
          //   1143: astore_2
          //   1144: aload_2
          //   1145: invokevirtual 266	java/lang/Exception:printStackTrace	()V
          //   1148: ldc 193
          //   1150: new 65	java/lang/StringBuilder
          //   1153: dup
          //   1154: invokespecial 66	java/lang/StringBuilder:<init>	()V
          //   1157: ldc_w 268
          //   1160: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1163: aload_2
          //   1164: invokevirtual 269	java/lang/Exception:getMessage	()Ljava/lang/String;
          //   1167: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1170: ldc 185
          //   1172: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   1175: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   1178: invokestatic 272	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
          //   1181: pop
          //   1182: aload_0
          //   1183: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   1186: invokestatic 240	com/tencent/weiyun/FileManager$DownLoadImp:access$3100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Landroid/os/Handler;
          //   1189: invokevirtual 246	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   1192: astore_3
          //   1193: aload_3
          //   1194: bipush 254
          //   1196: putfield 252	android/os/Message:what	I
          //   1199: aload_3
          //   1200: aload_2
          //   1201: invokevirtual 269	java/lang/Exception:getMessage	()Ljava/lang/String;
          //   1204: putfield 259	android/os/Message:obj	Ljava/lang/Object;
          //   1207: aload_0
          //   1208: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   1211: invokestatic 240	com/tencent/weiyun/FileManager$DownLoadImp:access$3100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Landroid/os/Handler;
          //   1214: aload_3
          //   1215: invokevirtual 263	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   1218: pop
          //   1219: aload 5
          //   1221: invokevirtual 275	java/io/FileOutputStream:close	()V
          //   1224: aload_1
          //   1225: invokevirtual 276	java/io/InputStream:close	()V
          //   1228: return
          //   1229: astore_1
          //   1230: return
          //   1231: goto -497 -> 734
          //   1234: astore_1
          //   1235: aload_0
          //   1236: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   1239: invokestatic 240	com/tencent/weiyun/FileManager$DownLoadImp:access$3100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Landroid/os/Handler;
          //   1242: invokevirtual 246	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   1245: astore_2
          //   1246: aload_2
          //   1247: bipush 254
          //   1249: putfield 252	android/os/Message:what	I
          //   1252: aload_2
          //   1253: aload_1
          //   1254: invokevirtual 279	java/io/IOException:getMessage	()Ljava/lang/String;
          //   1257: putfield 259	android/os/Message:obj	Ljava/lang/Object;
          //   1260: aload_0
          //   1261: getfield 18	com/tencent/weiyun/FileManager$DownLoadImp$3:this$1	Lcom/tencent/weiyun/FileManager$DownLoadImp;
          //   1264: invokestatic 240	com/tencent/weiyun/FileManager$DownLoadImp:access$3100	(Lcom/tencent/weiyun/FileManager$DownLoadImp;)Landroid/os/Handler;
          //   1267: aload_2
          //   1268: invokevirtual 263	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   1271: pop
          //   1272: return
          //
          // Exception table:
          //   from	to	target	type
          //   177	187	581	java/io/FileNotFoundException
          //   417	428	635	java/lang/Exception
          //   434	463	635	java/lang/Exception
          //   469	483	635	java/lang/Exception
          //   507	521	635	java/lang/Exception
          //   528	537	635	java/lang/Exception
          //   560	570	635	java/lang/Exception
          //   1024	1033	1143	java/lang/Exception
          //   1035	1063	1143	java/lang/Exception
          //   1065	1078	1143	java/lang/Exception
          //   1099	1111	1143	java/lang/Exception
          //   1113	1121	1143	java/lang/Exception
          //   1130	1140	1143	java/lang/Exception
          //   1219	1228	1229	java/io/IOException
          //   734	743	1234	java/io/IOException
        }
      }
      .start();
    }

    private void getDownloadPermission()
    {
      new Thread()
      {
        public void run()
        {
          Object localObject = FileManager.c(FileManager.this);
          ((Bundle)localObject).putString("file_id", FileManager.DownLoadImp.this.mWeiyunFile.getFileId());
          if (!TextUtils.isEmpty(FileManager.DownLoadImp.this.mThumbSize))
            ((Bundle)localObject).putString("thumb", FileManager.DownLoadImp.this.mThumbSize);
          try
          {
            localObject = HttpUtils.request(FileManager.d(FileManager.this), FileManager.DownLoadImp.this.mContext, FileManager.DownLoadImp.this.getDownloadUrl(FileManager.DownLoadImp.this.mFileType), (Bundle)localObject, "GET");
            localMessage = FileManager.DownLoadImp.this.mHandler.obtainMessage();
            localMessage.what = 0;
            localMessage.obj = localObject;
            FileManager.DownLoadImp.this.mHandler.sendMessage(localMessage);
            return;
          }
          catch (MalformedURLException localMalformedURLException)
          {
            localMessage = FileManager.DownLoadImp.this.mHandler.obtainMessage();
            localMessage.what = -3;
            localMessage.obj = localMalformedURLException.getMessage();
            FileManager.DownLoadImp.this.mHandler.sendMessage(localMessage);
            return;
          }
          catch (IOException localIOException)
          {
            localMessage = FileManager.DownLoadImp.this.mHandler.obtainMessage();
            localMessage.obj = localIOException.getMessage();
            localMessage.what = -2;
            FileManager.DownLoadImp.this.mHandler.sendMessage(localMessage);
            return;
          }
          catch (JSONException localJSONException)
          {
            localMessage = FileManager.DownLoadImp.this.mHandler.obtainMessage();
            localMessage.obj = localJSONException.getMessage();
            localMessage.what = -4;
            FileManager.DownLoadImp.this.mHandler.sendMessage(localMessage);
            return;
          }
          catch (HttpUtils.NetworkUnavailableException localNetworkUnavailableException)
          {
            localMessage = FileManager.DownLoadImp.this.mHandler.obtainMessage();
            localMessage.obj = localNetworkUnavailableException.getMessage();
            localMessage.what = -10;
            FileManager.DownLoadImp.this.mHandler.sendMessage(localMessage);
            return;
          }
          catch (HttpUtils.HttpStatusException localHttpStatusException)
          {
            Message localMessage = FileManager.DownLoadImp.this.mHandler.obtainMessage();
            localMessage.obj = localHttpStatusException.getMessage();
            localMessage.what = -9;
            FileManager.DownLoadImp.this.mHandler.sendMessage(localMessage);
          }
        }
      }
      .start();
    }

    private String getDownloadUrl(FileManager.WeiyunFileType paramWeiyunFileType)
    {
      if (paramWeiyunFileType == FileManager.WeiyunFileType.ImageFile)
      {
        if (this.mThumbSize != null)
          return "https://graph.qq.com/weiyun/get_photo_thumb";
        return "https://graph.qq.com/weiyun/download_photo";
      }
      if (paramWeiyunFileType == FileManager.WeiyunFileType.MusicFile)
        return "https://graph.qq.com/weiyun/download_music";
      if (paramWeiyunFileType == FileManager.WeiyunFileType.VideoFile)
        return "https://graph.qq.com/weiyun/download_video";
      return "https://graph.qq.com/weiyun/download_photo";
    }

    public void setThumbSize(String paramString)
    {
      this.mThumbSize = paramString;
    }

    public void start()
    {
      Message localMessage;
      if ((this.mSavePath == null) || (this.mFileType == null) || (this.mWeiyunFile == null) || (this.mWeiyunFile.getFileId() == null))
      {
        localMessage = this.mHandler.obtainMessage();
        localMessage.what = -5;
        localMessage.obj = new String("");
        this.mHandler.sendMessage(localMessage);
        return;
      }
      this.mFile = new File(this.mSavePath);
      if (this.mFile.exists())
      {
        localMessage = this.mHandler.obtainMessage();
        localMessage.what = -11;
        localMessage.obj = new String("");
        this.mHandler.sendMessage(localMessage);
        return;
      }
      this.mCallback.onPrepareStart();
      getDownloadPermission();
    }
  }

  private class GetFileListListener
    implements IUiListener
  {
    private IGetFileListListener mListener;

    public GetFileListListener(IGetFileListListener arg2)
    {
      Object localObject;
      this.mListener = localObject;
    }

    public void onCancel()
    {
    }

    public void onComplete(Object paramObject)
    {
      paramObject = (JSONObject)paramObject;
      try
      {
        ArrayList localArrayList = new ArrayList();
        Object localObject = paramObject.getJSONObject("data");
        if (!((JSONObject)localObject).isNull("content"))
        {
          localObject = ((JSONObject)localObject).getJSONArray("content");
          int i = 0;
          while (i < ((JSONArray)localObject).length())
          {
            JSONObject localJSONObject = ((JSONArray)localObject).getJSONObject(i);
            localArrayList.add(new WeiyunFile(localJSONObject.getString("file_id"), localJSONObject.getString("file_name"), localJSONObject.getString("file_ctime"), localJSONObject.getInt("file_size")));
            i += 1;
          }
        }
        this.mListener.onComplete(localArrayList);
        return;
      }
      catch (JSONException localJSONException)
      {
        this.mListener.onError(new UiError(-4, "服务器返回数据格式有误!", paramObject.toString()));
      }
    }

    public void onError(UiError paramUiError)
    {
      this.mListener.onError(paramUiError);
    }
  }

  private class UploadFileImp
  {
    private static final int GET_PERMISSON_DONE = 0;
    private static final String UPLOAD_IMAGE_URL = "https://graph.qq.com/weiyun/upload_photo";
    private static final String UPLOAD_MUSIC_URL = "https://graph.qq.com/weiyun/upload_music";
    private static final int UPLOAD_PROGRESS = 1;
    private static final int UPLOAD_PROGRESS_DONE = 2;
    private static final String UPLOAD_VIDEO_URL = "https://graph.qq.com/weiyun/upload_video";
    private final IUploadFileCallBack mCallback;
    private final Context mContext;
    private String mFileKey;
    private final String mFilePath;
    private long mFileSize;
    private final FileManager.WeiyunFileType mFileType;
    private final Handler mHandler;
    private String mHost;
    private String mMD5Hash;
    private byte[] mUKey;

    public UploadFileImp(Context paramWeiyunFileType, FileManager.WeiyunFileType paramString, String paramIUploadFileCallBack, IUploadFileCallBack arg5)
    {
      this.mContext = paramWeiyunFileType;
      this.mFileType = paramString;
      this.mFilePath = paramIUploadFileCallBack;
      Object localObject;
      this.mCallback = localObject;
      this.mHandler = new Handler(this.mContext.getMainLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          int i;
          switch (paramAnonymousMessage.what)
          {
          default:
            FileManager.UploadFileImp.this.mCallback.onError(new UiError(paramAnonymousMessage.what, (String)paramAnonymousMessage.obj, null));
            return;
          case 0:
            paramAnonymousMessage = (JSONObject)paramAnonymousMessage.obj;
            try
            {
              i = paramAnonymousMessage.getInt("ret");
              if (i != 0)
              {
                FileManager.UploadFileImp.this.mCallback.onError(new UiError(i, paramAnonymousMessage.toString(), null));
                return;
              }
            }
            catch (Exception paramAnonymousMessage)
            {
              FileManager.UploadFileImp.this.mCallback.onError(new UiError(-4, paramAnonymousMessage.getMessage(), null));
              return;
            }
            paramAnonymousMessage = paramAnonymousMessage.getJSONObject("data");
            String str = paramAnonymousMessage.getString("csum");
            FileManager.UploadFileImp.access$102(FileManager.UploadFileImp.this, DataConvert.string2bytes(str));
            FileManager.UploadFileImp.access$202(FileManager.UploadFileImp.this, paramAnonymousMessage.getString("host"));
            FileManager.UploadFileImp.this.mCallback.onUploadStart();
            FileManager.UploadFileImp.this.doUpload();
            return;
          case 1:
            i = Integer.parseInt((String)paramAnonymousMessage.obj);
            FileManager.UploadFileImp.this.mCallback.onUploadProgress(i);
            return;
          case 2:
          }
          FileManager.UploadFileImp.this.mCallback.onUploadSuccess();
        }
      };
    }

    private void doUpload()
    {
      new Thread()
      {
        // ERROR //
        public void run()
        {
          // Byte code:
          //   0: new 28	org/apache/http/params/BasicHttpParams
          //   3: dup
          //   4: invokespecial 29	org/apache/http/params/BasicHttpParams:<init>	()V
          //   7: astore_1
          //   8: aload_1
          //   9: sipush 15000
          //   12: invokestatic 35	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
          //   15: aload_1
          //   16: sipush 20000
          //   19: invokestatic 38	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
          //   22: aload_1
          //   23: getstatic 44	org/apache/http/HttpVersion:HTTP_1_1	Lorg/apache/http/HttpVersion;
          //   26: invokestatic 50	org/apache/http/params/HttpProtocolParams:setVersion	(Lorg/apache/http/params/HttpParams;Lorg/apache/http/ProtocolVersion;)V
          //   29: aload_1
          //   30: ldc 52
          //   32: invokestatic 56	org/apache/http/params/HttpProtocolParams:setContentCharset	(Lorg/apache/http/params/HttpParams;Ljava/lang/String;)V
          //   35: aload_1
          //   36: ldc 58
          //   38: invokestatic 61	org/apache/http/params/HttpProtocolParams:setUserAgent	(Lorg/apache/http/params/HttpParams;Ljava/lang/String;)V
          //   41: new 63	org/apache/http/impl/client/DefaultHttpClient
          //   44: dup
          //   45: aload_1
          //   46: invokespecial 66	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
          //   49: astore_2
          //   50: ldc 67
          //   52: newarray byte
          //   54: astore_3
          //   55: new 69	java/io/FileInputStream
          //   58: dup
          //   59: aload_0
          //   60: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   63: invokestatic 73	com/tencent/weiyun/FileManager$UploadFileImp:access$400	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Ljava/lang/String;
          //   66: invokespecial 76	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
          //   69: astore_1
          //   70: iconst_0
          //   71: istore 6
          //   73: iload 6
          //   75: i2l
          //   76: aload_0
          //   77: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   80: invokestatic 80	com/tencent/weiyun/FileManager$UploadFileImp:access$800	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)J
          //   83: lcmp
          //   84: ifge +36 -> 120
          //   87: aload_1
          //   88: aload_3
          //   89: invokevirtual 84	java/io/FileInputStream:read	([B)I
          //   92: istore 7
          //   94: aload_0
          //   95: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   98: aload_3
          //   99: iload 7
          //   101: iload 6
          //   103: invokestatic 88	com/tencent/weiyun/FileManager$UploadFileImp:access$1400	(Lcom/tencent/weiyun/FileManager$UploadFileImp;[BII)[B
          //   106: astore 4
          //   108: iload 6
          //   110: iload 7
          //   112: iadd
          //   113: istore 7
          //   115: aload 4
          //   117: ifnonnull +84 -> 201
          //   120: aload_1
          //   121: invokevirtual 91	java/io/FileInputStream:close	()V
          //   124: return
          //   125: astore_1
          //   126: aload_0
          //   127: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   130: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   133: invokevirtual 101	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   136: astore_1
          //   137: aload_1
          //   138: bipush 254
          //   140: putfield 107	android/os/Message:what	I
          //   143: aload_1
          //   144: ldc 109
          //   146: putfield 113	android/os/Message:obj	Ljava/lang/Object;
          //   149: aload_0
          //   150: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   153: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   156: aload_1
          //   157: invokevirtual 117	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   160: pop
          //   161: return
          //   162: astore_2
          //   163: aload_0
          //   164: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   167: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   170: invokevirtual 101	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   173: astore_2
          //   174: aload_2
          //   175: bipush 254
          //   177: putfield 107	android/os/Message:what	I
          //   180: aload_2
          //   181: ldc 109
          //   183: putfield 113	android/os/Message:obj	Ljava/lang/Object;
          //   186: aload_0
          //   187: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   190: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   193: aload_2
          //   194: invokevirtual 117	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   197: pop
          //   198: goto -78 -> 120
          //   201: new 119	org/apache/http/client/methods/HttpPost
          //   204: dup
          //   205: new 121	java/lang/StringBuilder
          //   208: dup
          //   209: invokespecial 122	java/lang/StringBuilder:<init>	()V
          //   212: ldc 124
          //   214: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   217: aload_0
          //   218: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   221: invokestatic 131	com/tencent/weiyun/FileManager$UploadFileImp:access$200	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Ljava/lang/String;
          //   224: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   227: ldc 133
          //   229: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   232: aload_0
          //   233: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   236: invokestatic 136	com/tencent/weiyun/FileManager$UploadFileImp:access$700	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Ljava/lang/String;
          //   239: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   242: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   245: invokespecial 141	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
          //   248: astore 5
          //   250: aload 5
          //   252: ldc 143
          //   254: ldc 145
          //   256: invokevirtual 149	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
          //   259: aload 5
          //   261: ldc 151
          //   263: ldc 153
          //   265: invokevirtual 156	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
          //   268: aload 5
          //   270: ldc 158
          //   272: ldc 160
          //   274: invokevirtual 156	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
          //   277: aload 5
          //   279: ldc 162
          //   281: ldc 164
          //   283: invokevirtual 156	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
          //   286: aload 5
          //   288: new 166	org/apache/http/entity/ByteArrayEntity
          //   291: dup
          //   292: aload 4
          //   294: invokespecial 169	org/apache/http/entity/ByteArrayEntity:<init>	([B)V
          //   297: invokevirtual 173	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
          //   300: aload_2
          //   301: aload 5
          //   303: invokeinterface 179 2 0
          //   308: invokeinterface 185 1 0
          //   313: invokeinterface 191 1 0
          //   318: istore 6
          //   320: iload 6
          //   322: sipush 200
          //   325: if_icmpne +190 -> 515
          //   328: iload 7
          //   330: i2l
          //   331: aload_0
          //   332: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   335: invokestatic 80	com/tencent/weiyun/FileManager$UploadFileImp:access$800	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)J
          //   338: lcmp
          //   339: ifge +131 -> 470
          //   342: iload 7
          //   344: i2l
          //   345: ldc2_w 192
          //   348: lmul
          //   349: aload_0
          //   350: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   353: invokestatic 80	com/tencent/weiyun/FileManager$UploadFileImp:access$800	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)J
          //   356: ldiv
          //   357: l2i
          //   358: istore 6
          //   360: aload_0
          //   361: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   364: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   367: invokevirtual 101	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   370: astore 4
          //   372: aload 4
          //   374: iconst_1
          //   375: putfield 107	android/os/Message:what	I
          //   378: aload 4
          //   380: new 121	java/lang/StringBuilder
          //   383: dup
          //   384: invokespecial 122	java/lang/StringBuilder:<init>	()V
          //   387: iload 6
          //   389: invokevirtual 196	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
          //   392: ldc 109
          //   394: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   397: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   400: putfield 113	android/os/Message:obj	Ljava/lang/Object;
          //   403: aload_0
          //   404: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   407: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   410: aload 4
          //   412: invokevirtual 117	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   415: pop
          //   416: iload 7
          //   418: istore 6
          //   420: goto -347 -> 73
          //   423: astore 4
          //   425: aload_0
          //   426: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   429: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   432: invokevirtual 101	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   435: astore 4
          //   437: aload 4
          //   439: bipush 254
          //   441: putfield 107	android/os/Message:what	I
          //   444: aload 4
          //   446: ldc 109
          //   448: putfield 113	android/os/Message:obj	Ljava/lang/Object;
          //   451: aload_0
          //   452: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   455: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   458: aload 4
          //   460: invokevirtual 117	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   463: pop
          //   464: iconst_0
          //   465: istore 6
          //   467: goto -147 -> 320
          //   470: aload_0
          //   471: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   474: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   477: invokevirtual 101	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   480: astore 4
          //   482: aload 4
          //   484: iconst_2
          //   485: putfield 107	android/os/Message:what	I
          //   488: aload 4
          //   490: ldc 109
          //   492: putfield 113	android/os/Message:obj	Ljava/lang/Object;
          //   495: aload_0
          //   496: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   499: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   502: aload 4
          //   504: invokevirtual 117	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   507: pop
          //   508: iload 7
          //   510: istore 6
          //   512: goto -439 -> 73
          //   515: aload_0
          //   516: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   519: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   522: invokevirtual 101	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   525: astore_2
          //   526: aload_2
          //   527: bipush 247
          //   529: putfield 107	android/os/Message:what	I
          //   532: aload_2
          //   533: ldc 109
          //   535: putfield 113	android/os/Message:obj	Ljava/lang/Object;
          //   538: aload_0
          //   539: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   542: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   545: aload_2
          //   546: invokevirtual 117	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   549: pop
          //   550: goto -430 -> 120
          //   553: astore_1
          //   554: aload_0
          //   555: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   558: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   561: invokevirtual 101	android/os/Handler:obtainMessage	()Landroid/os/Message;
          //   564: astore_2
          //   565: aload_2
          //   566: bipush 254
          //   568: putfield 107	android/os/Message:what	I
          //   571: aload_2
          //   572: aload_1
          //   573: invokevirtual 199	java/io/IOException:getMessage	()Ljava/lang/String;
          //   576: putfield 113	android/os/Message:obj	Ljava/lang/Object;
          //   579: aload_0
          //   580: getfield 18	com/tencent/weiyun/FileManager$UploadFileImp$3:this$1	Lcom/tencent/weiyun/FileManager$UploadFileImp;
          //   583: invokestatic 95	com/tencent/weiyun/FileManager$UploadFileImp:access$1300	(Lcom/tencent/weiyun/FileManager$UploadFileImp;)Landroid/os/Handler;
          //   586: aload_2
          //   587: invokevirtual 117	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
          //   590: pop
          //   591: return
          //
          // Exception table:
          //   from	to	target	type
          //   55	70	125	java/io/FileNotFoundException
          //   87	108	162	java/io/IOException
          //   300	320	423	java/io/IOException
          //   120	124	553	java/io/IOException
        }
      }
      .start();
    }

    private String getRequestUrl(FileManager.WeiyunFileType paramWeiyunFileType)
    {
      if (paramWeiyunFileType == FileManager.WeiyunFileType.ImageFile)
        return "https://graph.qq.com/weiyun/upload_photo";
      if (paramWeiyunFileType == FileManager.WeiyunFileType.MusicFile)
        return "https://graph.qq.com/weiyun/upload_music";
      return "https://graph.qq.com/weiyun/upload_video";
    }

    private void getUploadPermission()
    {
      new Thread()
      {
        public void run()
        {
          Object localObject1 = Uri.parse(FileManager.UploadFileImp.this.mFilePath);
          localObject1 = SystemClock.elapsedRealtime() + "__" + ((Uri)localObject1).getLastPathSegment();
          Object localObject2 = FileManager.a(FileManager.this);
          ((Bundle)localObject2).putString("sha", FileManager.UploadFileImp.this.mFileKey);
          ((Bundle)localObject2).putString("md5", FileManager.UploadFileImp.this.mMD5Hash);
          ((Bundle)localObject2).putString("size", FileManager.UploadFileImp.this.mFileSize + "");
          ((Bundle)localObject2).putString("name", (String)localObject1);
          ((Bundle)localObject2).putString("upload_type", "control");
          try
          {
            localObject1 = HttpUtils.request(FileManager.b(FileManager.this), FileManager.UploadFileImp.this.mContext, FileManager.UploadFileImp.this.getRequestUrl(FileManager.UploadFileImp.this.mFileType), (Bundle)localObject2, "GET");
            localObject2 = FileManager.UploadFileImp.this.mHandler.obtainMessage();
            ((Message)localObject2).what = 0;
            ((Message)localObject2).obj = localObject1;
            FileManager.UploadFileImp.this.mHandler.sendMessage((Message)localObject2);
            return;
          }
          catch (MalformedURLException localMalformedURLException)
          {
            localObject2 = FileManager.UploadFileImp.this.mHandler.obtainMessage();
            ((Message)localObject2).what = -3;
            ((Message)localObject2).obj = localMalformedURLException.getMessage();
            FileManager.UploadFileImp.this.mHandler.sendMessage((Message)localObject2);
            return;
          }
          catch (IOException localIOException)
          {
            localObject2 = FileManager.UploadFileImp.this.mHandler.obtainMessage();
            ((Message)localObject2).obj = localIOException.getMessage();
            ((Message)localObject2).what = -2;
            FileManager.UploadFileImp.this.mHandler.sendMessage((Message)localObject2);
            return;
          }
          catch (JSONException localJSONException)
          {
            localObject2 = FileManager.UploadFileImp.this.mHandler.obtainMessage();
            ((Message)localObject2).obj = localJSONException.getMessage();
            ((Message)localObject2).what = -4;
            FileManager.UploadFileImp.this.mHandler.sendMessage((Message)localObject2);
            return;
          }
          catch (HttpUtils.NetworkUnavailableException localNetworkUnavailableException)
          {
            localObject2 = FileManager.UploadFileImp.this.mHandler.obtainMessage();
            ((Message)localObject2).obj = localNetworkUnavailableException.getMessage();
            ((Message)localObject2).what = -10;
            FileManager.UploadFileImp.this.mHandler.sendMessage((Message)localObject2);
            return;
          }
          catch (HttpUtils.HttpStatusException localHttpStatusException)
          {
            localObject2 = FileManager.UploadFileImp.this.mHandler.obtainMessage();
            ((Message)localObject2).obj = localHttpStatusException.getMessage();
            ((Message)localObject2).what = -9;
            FileManager.UploadFileImp.this.mHandler.sendMessage((Message)localObject2);
          }
        }
      }
      .start();
    }

    private byte[] packPostBody(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      try
      {
        localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject).update(paramArrayOfByte, 0, paramInt1);
        this.mMD5Hash = DataConvert.toHexString(((MessageDigest)localObject).digest());
        ((MessageDigest)localObject).reset();
        int i = paramInt1 + 340;
        localObject = new byte[i + 4 + 4 + 4 + 4];
        int j = DataConvert.putInt2Bytes(-1412589450, (byte[])localObject, 0) + 0;
        j += DataConvert.putInt2Bytes(1000, (byte[])localObject, j);
        j += DataConvert.putInt2Bytes(0, (byte[])localObject, j);
        i = DataConvert.putInt2Bytes(i, (byte[])localObject, j) + j;
        i += DataConvert.putShort2Bytes(304, (byte[])localObject, i);
        i += DataConvert.putBytes2Bytes(this.mUKey, (byte[])localObject, i);
        i += DataConvert.putShort2Bytes(20, (byte[])localObject, i);
        i += DataConvert.putString2Bytes(this.mFileKey, (byte[])localObject, i);
        i += DataConvert.putInt2Bytes((int)this.mFileSize, (byte[])localObject, i);
        paramInt2 = i + DataConvert.putInt2Bytes(paramInt2, (byte[])localObject, i);
        paramInt2 += DataConvert.putInt2Bytes(paramInt1, (byte[])localObject, paramInt2);
        DataConvert.putBytes2Bytes(paramArrayOfByte, paramInt1, (byte[])localObject, paramInt2);
        return localObject;
      }
      catch (NoSuchAlgorithmException paramArrayOfByte)
      {
        Object localObject = this.mHandler.obtainMessage();
        ((Message)localObject).what = -2;
        ((Message)localObject).obj = paramArrayOfByte.getMessage();
        this.mHandler.sendMessage((Message)localObject);
      }
      return null;
    }

    // ERROR //
    public void start()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 61	com/tencent/weiyun/FileManager$UploadFileImp:mFilePath	Ljava/lang/String;
      //   4: ifnull +20 -> 24
      //   7: new 210	java/io/File
      //   10: dup
      //   11: aload_0
      //   12: getfield 61	com/tencent/weiyun/FileManager$UploadFileImp:mFilePath	Ljava/lang/String;
      //   15: invokespecial 213	java/io/File:<init>	(Ljava/lang/String;)V
      //   18: invokevirtual 217	java/io/File:exists	()Z
      //   21: ifne +40 -> 61
      //   24: aload_0
      //   25: getfield 74	com/tencent/weiyun/FileManager$UploadFileImp:mHandler	Landroid/os/Handler;
      //   28: invokevirtual 189	android/os/Handler:obtainMessage	()Landroid/os/Message;
      //   31: astore_1
      //   32: aload_1
      //   33: bipush 251
      //   35: putfield 194	android/os/Message:what	I
      //   38: aload_1
      //   39: new 219	java/lang/String
      //   42: dup
      //   43: ldc 221
      //   45: invokespecial 222	java/lang/String:<init>	(Ljava/lang/String;)V
      //   48: putfield 202	android/os/Message:obj	Ljava/lang/Object;
      //   51: aload_0
      //   52: getfield 74	com/tencent/weiyun/FileManager$UploadFileImp:mHandler	Landroid/os/Handler;
      //   55: aload_1
      //   56: invokevirtual 206	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   59: pop
      //   60: return
      //   61: aload_0
      //   62: getfield 63	com/tencent/weiyun/FileManager$UploadFileImp:mCallback	Lcom/tencent/weiyun/IUploadFileCallBack;
      //   65: invokeinterface 227 1 0
      //   70: new 210	java/io/File
      //   73: dup
      //   74: aload_0
      //   75: getfield 61	com/tencent/weiyun/FileManager$UploadFileImp:mFilePath	Ljava/lang/String;
      //   78: invokespecial 213	java/io/File:<init>	(Ljava/lang/String;)V
      //   81: astore_1
      //   82: aload_0
      //   83: aload_1
      //   84: invokevirtual 231	java/io/File:length	()J
      //   87: putfield 121	com/tencent/weiyun/FileManager$UploadFileImp:mFileSize	J
      //   90: ldc 233
      //   92: invokestatic 147	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
      //   95: astore_3
      //   96: new 235	java/io/FileInputStream
      //   99: dup
      //   100: aload_1
      //   101: invokespecial 238	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   104: astore_2
      //   105: new 240	java/security/DigestInputStream
      //   108: dup
      //   109: aload_2
      //   110: aload_3
      //   111: invokespecial 243	java/security/DigestInputStream:<init>	(Ljava/io/InputStream;Ljava/security/MessageDigest;)V
      //   114: astore_3
      //   115: ldc 244
      //   117: newarray byte
      //   119: astore 4
      //   121: aload_3
      //   122: aload 4
      //   124: invokevirtual 248	java/security/DigestInputStream:read	([B)I
      //   127: ifgt -6 -> 121
      //   130: aload_3
      //   131: invokevirtual 252	java/security/DigestInputStream:getMessageDigest	()Ljava/security/MessageDigest;
      //   134: astore 4
      //   136: aload_0
      //   137: aload 4
      //   139: invokevirtual 155	java/security/MessageDigest:digest	()[B
      //   142: invokestatic 161	com/tencent/utils/DataConvert:toHexString	([B)Ljava/lang/String;
      //   145: putfield 114	com/tencent/weiyun/FileManager$UploadFileImp:mFileKey	Ljava/lang/String;
      //   148: aload 4
      //   150: invokevirtual 164	java/security/MessageDigest:reset	()V
      //   153: aload_2
      //   154: invokevirtual 255	java/io/FileInputStream:close	()V
      //   157: aload_3
      //   158: invokevirtual 256	java/security/DigestInputStream:close	()V
      //   161: ldc 141
      //   163: invokestatic 147	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
      //   166: astore_2
      //   167: new 235	java/io/FileInputStream
      //   170: dup
      //   171: aload_1
      //   172: invokespecial 238	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   175: astore_1
      //   176: new 240	java/security/DigestInputStream
      //   179: dup
      //   180: aload_1
      //   181: aload_2
      //   182: invokespecial 243	java/security/DigestInputStream:<init>	(Ljava/io/InputStream;Ljava/security/MessageDigest;)V
      //   185: astore_2
      //   186: ldc 244
      //   188: newarray byte
      //   190: astore_3
      //   191: aload_2
      //   192: aload_3
      //   193: invokevirtual 248	java/security/DigestInputStream:read	([B)I
      //   196: ifgt -5 -> 191
      //   199: aload_2
      //   200: invokevirtual 252	java/security/DigestInputStream:getMessageDigest	()Ljava/security/MessageDigest;
      //   203: astore_3
      //   204: aload_0
      //   205: aload_3
      //   206: invokevirtual 155	java/security/MessageDigest:digest	()[B
      //   209: invokestatic 161	com/tencent/utils/DataConvert:toHexString	([B)Ljava/lang/String;
      //   212: putfield 117	com/tencent/weiyun/FileManager$UploadFileImp:mMD5Hash	Ljava/lang/String;
      //   215: aload_3
      //   216: invokevirtual 164	java/security/MessageDigest:reset	()V
      //   219: aload_1
      //   220: invokevirtual 255	java/io/FileInputStream:close	()V
      //   223: aload_2
      //   224: invokevirtual 256	java/security/DigestInputStream:close	()V
      //   227: aload_0
      //   228: invokespecial 258	com/tencent/weiyun/FileManager$UploadFileImp:getUploadPermission	()V
      //   231: return
      //   232: astore_1
      //   233: aload_0
      //   234: getfield 74	com/tencent/weiyun/FileManager$UploadFileImp:mHandler	Landroid/os/Handler;
      //   237: invokevirtual 189	android/os/Handler:obtainMessage	()Landroid/os/Message;
      //   240: astore_1
      //   241: aload_1
      //   242: bipush 254
      //   244: putfield 194	android/os/Message:what	I
      //   247: aload_1
      //   248: new 219	java/lang/String
      //   251: dup
      //   252: ldc 221
      //   254: invokespecial 222	java/lang/String:<init>	(Ljava/lang/String;)V
      //   257: putfield 202	android/os/Message:obj	Ljava/lang/Object;
      //   260: aload_0
      //   261: getfield 74	com/tencent/weiyun/FileManager$UploadFileImp:mHandler	Landroid/os/Handler;
      //   264: aload_1
      //   265: invokevirtual 206	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   268: pop
      //   269: return
      //   270: astore_1
      //   271: aload_0
      //   272: getfield 74	com/tencent/weiyun/FileManager$UploadFileImp:mHandler	Landroid/os/Handler;
      //   275: invokevirtual 189	android/os/Handler:obtainMessage	()Landroid/os/Message;
      //   278: astore_1
      //   279: aload_1
      //   280: bipush 254
      //   282: putfield 194	android/os/Message:what	I
      //   285: aload_1
      //   286: new 219	java/lang/String
      //   289: dup
      //   290: ldc 221
      //   292: invokespecial 222	java/lang/String:<init>	(Ljava/lang/String;)V
      //   295: putfield 202	android/os/Message:obj	Ljava/lang/Object;
      //   298: aload_0
      //   299: getfield 74	com/tencent/weiyun/FileManager$UploadFileImp:mHandler	Landroid/os/Handler;
      //   302: aload_1
      //   303: invokevirtual 206	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   306: pop
      //   307: return
      //
      // Exception table:
      //   from	to	target	type
      //   90	121	232	java/lang/Exception
      //   121	161	232	java/lang/Exception
      //   161	191	270	java/lang/Exception
      //   191	227	270	java/lang/Exception
    }
  }

  public static enum WeiyunFileType
  {
    ImageFile(0), MusicFile(1), VideoFile(2);

    private final int mType;

    private WeiyunFileType(int paramInt)
    {
      this.mType = paramInt;
    }

    public int value()
    {
      return this.mType;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weiyun.FileManager
 * JD-Core Version:    0.6.2
 */