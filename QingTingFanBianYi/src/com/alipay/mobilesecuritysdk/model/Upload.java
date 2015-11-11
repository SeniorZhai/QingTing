package com.alipay.mobilesecuritysdk.model;

import HttpUtils.HttpFetcher;
import android.content.Context;
import android.util.Log;
import com.alipay.mobilesecuritysdk.datainfo.GeoResponseInfo;
import com.alipay.mobilesecuritysdk.datainfo.UploadInfo;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

public class Upload
{
  private UploadInfo info;
  private Context mcontext;
  private DataProfile profile = new DataProfile();

  public Upload(Context paramContext)
  {
    this.mcontext = paramContext;
  }

  public GeoResponseInfo communicateSwitch()
  {
    GeoResponseInfo localGeoResponseInfo = new GeoResponseInfo();
    if (!CommonUtils.isNetWorkActive(this.mcontext))
      return localGeoResponseInfo;
    try
    {
      Object localObject = new HttpGet("http://secclientgw.alipay.com/mobile/switch.xml");
      localObject = new HttpFetcher().getHttpClient().execute((HttpUriRequest)localObject);
      if (((HttpResponse)localObject).getStatusLine().getStatusCode() == 200)
        return this.profile.analysisServerRespond(EntityUtils.toString(((HttpResponse)localObject).getEntity()));
      localGeoResponseInfo.setSuccess(false);
      return localGeoResponseInfo;
    }
    catch (Exception localException)
    {
      localGeoResponseInfo.setSuccess(false);
    }
    return localGeoResponseInfo;
  }

  public UploadInfo getInfo()
  {
    return this.info;
  }

  public void setInfo(UploadInfo paramUploadInfo)
  {
    this.info = paramUploadInfo;
  }

  public GeoResponseInfo uploadCollectedData(String paramString1, String paramString2, String paramString3)
  {
    GeoResponseInfo localGeoResponseInfo = new GeoResponseInfo();
    try
    {
      paramString1 = new HttpFetcher().uploadCollectedData(this.mcontext, "https://seccliprod.alipay.com/api/do.htm", paramString1, paramString2, paramString3, true);
      if ((paramString1 != null) && (paramString1.getStatusLine().getStatusCode() == 200))
        return this.profile.analysisServerRespond(EntityUtils.toString(paramString1.getEntity()));
      localGeoResponseInfo.setSuccess(false);
      return localGeoResponseInfo;
    }
    catch (IOException paramString1)
    {
      Log.i("upload data  error", paramString1.getLocalizedMessage());
    }
    return localGeoResponseInfo;
  }

  // ERROR //
  public GeoResponseInfo uploadData(java.util.List<String> paramList, com.alipay.mobilesecuritysdk.datainfo.SdkConfig paramSdkConfig)
  {
    // Byte code:
    //   0: new 29	com/alipay/mobilesecuritysdk/datainfo/GeoResponseInfo
    //   3: dup
    //   4: invokespecial 30	com/alipay/mobilesecuritysdk/datainfo/GeoResponseInfo:<init>	()V
    //   7: astore_3
    //   8: aload_1
    //   9: invokestatic 119	com/alipay/mobilesecuritysdk/util/CommonUtils:isBlankCollection	(Ljava/util/List;)Z
    //   12: ifeq +10 -> 22
    //   15: aload_3
    //   16: iconst_0
    //   17: invokevirtual 86	com/alipay/mobilesecuritysdk/datainfo/GeoResponseInfo:setSuccess	(Z)V
    //   20: aload_3
    //   21: areturn
    //   22: aload_3
    //   23: astore_2
    //   24: aload_0
    //   25: getfield 90	com/alipay/mobilesecuritysdk/model/Upload:info	Lcom/alipay/mobilesecuritysdk/datainfo/UploadInfo;
    //   28: invokevirtual 125	com/alipay/mobilesecuritysdk/datainfo/UploadInfo:getAppinfos	()Ljava/util/List;
    //   31: invokeinterface 130 1 0
    //   36: ifle +149 -> 185
    //   39: aload_0
    //   40: getfield 20	com/alipay/mobilesecuritysdk/model/Upload:profile	Lcom/alipay/mobilesecuritysdk/model/DataProfile;
    //   43: aload_1
    //   44: invokevirtual 134	com/alipay/mobilesecuritysdk/model/DataProfile:setTid	(Ljava/util/List;)V
    //   47: aload_0
    //   48: getfield 20	com/alipay/mobilesecuritysdk/model/Upload:profile	Lcom/alipay/mobilesecuritysdk/model/DataProfile;
    //   51: new 136	java/lang/StringBuilder
    //   54: dup
    //   55: aload_0
    //   56: getfield 22	com/alipay/mobilesecuritysdk/model/Upload:mcontext	Landroid/content/Context;
    //   59: invokevirtual 142	android/content/Context:getFilesDir	()Ljava/io/File;
    //   62: invokevirtual 147	java/io/File:getPath	()Ljava/lang/String;
    //   65: invokestatic 153	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   68: invokespecial 154	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   71: getstatic 158	java/io/File:separator	Ljava/lang/String;
    //   74: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: ldc 164
    //   79: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: aload_0
    //   86: getfield 90	com/alipay/mobilesecuritysdk/model/Upload:info	Lcom/alipay/mobilesecuritysdk/datainfo/UploadInfo;
    //   89: invokevirtual 125	com/alipay/mobilesecuritysdk/datainfo/UploadInfo:getAppinfos	()Ljava/util/List;
    //   92: invokevirtual 170	com/alipay/mobilesecuritysdk/model/DataProfile:AppToString	(Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
    //   95: astore 4
    //   97: invokestatic 176	com/alipay/mobilesecuritysdk/face/SecurityClientMobile:isDebug	()Z
    //   100: ifeq +11 -> 111
    //   103: ldc 178
    //   105: aload 4
    //   107: invokestatic 113	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   110: pop
    //   111: aload_3
    //   112: astore_2
    //   113: aload 4
    //   115: ifnull +24 -> 139
    //   118: aload_3
    //   119: astore_2
    //   120: aload 4
    //   122: invokevirtual 181	java/lang/String:length	()I
    //   125: ifle +14 -> 139
    //   128: aload_0
    //   129: ldc 183
    //   131: aload 4
    //   133: ldc 185
    //   135: invokevirtual 187	com/alipay/mobilesecuritysdk/model/Upload:uploadCollectedData	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/alipay/mobilesecuritysdk/datainfo/GeoResponseInfo;
    //   138: astore_2
    //   139: aload_2
    //   140: invokevirtual 190	com/alipay/mobilesecuritysdk/datainfo/GeoResponseInfo:isSuccess	()Z
    //   143: ifne +217 -> 360
    //   146: new 136	java/lang/StringBuilder
    //   149: dup
    //   150: aload_0
    //   151: getfield 22	com/alipay/mobilesecuritysdk/model/Upload:mcontext	Landroid/content/Context;
    //   154: invokevirtual 142	android/content/Context:getFilesDir	()Ljava/io/File;
    //   157: invokevirtual 147	java/io/File:getPath	()Ljava/lang/String;
    //   160: invokestatic 153	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   163: invokespecial 154	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   166: getstatic 158	java/io/File:separator	Ljava/lang/String;
    //   169: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: ldc 164
    //   174: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: aload 4
    //   182: invokestatic 194	com/alipay/mobilesecuritysdk/util/CommonUtils:WriteFile	(Ljava/lang/String;Ljava/lang/String;)V
    //   185: aload_2
    //   186: astore_3
    //   187: aload_0
    //   188: getfield 90	com/alipay/mobilesecuritysdk/model/Upload:info	Lcom/alipay/mobilesecuritysdk/datainfo/UploadInfo;
    //   191: invokevirtual 197	com/alipay/mobilesecuritysdk/datainfo/UploadInfo:getLocates	()Ljava/util/List;
    //   194: invokeinterface 130 1 0
    //   199: ifle +145 -> 344
    //   202: aload_0
    //   203: getfield 20	com/alipay/mobilesecuritysdk/model/Upload:profile	Lcom/alipay/mobilesecuritysdk/model/DataProfile;
    //   206: aload_1
    //   207: invokevirtual 134	com/alipay/mobilesecuritysdk/model/DataProfile:setTid	(Ljava/util/List;)V
    //   210: aload_0
    //   211: getfield 20	com/alipay/mobilesecuritysdk/model/Upload:profile	Lcom/alipay/mobilesecuritysdk/model/DataProfile;
    //   214: new 136	java/lang/StringBuilder
    //   217: dup
    //   218: aload_0
    //   219: getfield 22	com/alipay/mobilesecuritysdk/model/Upload:mcontext	Landroid/content/Context;
    //   222: invokevirtual 142	android/content/Context:getFilesDir	()Ljava/io/File;
    //   225: invokevirtual 147	java/io/File:getPath	()Ljava/lang/String;
    //   228: invokestatic 153	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   231: invokespecial 154	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   234: getstatic 158	java/io/File:separator	Ljava/lang/String;
    //   237: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   240: ldc 199
    //   242: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   248: aload_0
    //   249: getfield 90	com/alipay/mobilesecuritysdk/model/Upload:info	Lcom/alipay/mobilesecuritysdk/datainfo/UploadInfo;
    //   252: invokevirtual 197	com/alipay/mobilesecuritysdk/datainfo/UploadInfo:getLocates	()Ljava/util/List;
    //   255: invokevirtual 202	com/alipay/mobilesecuritysdk/model/DataProfile:LocationToString	(Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
    //   258: astore_3
    //   259: invokestatic 176	com/alipay/mobilesecuritysdk/face/SecurityClientMobile:isDebug	()Z
    //   262: ifeq +10 -> 272
    //   265: ldc 204
    //   267: aload_3
    //   268: invokestatic 113	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   271: pop
    //   272: aload_2
    //   273: astore_1
    //   274: aload_3
    //   275: ifnull +22 -> 297
    //   278: aload_2
    //   279: astore_1
    //   280: aload_3
    //   281: invokevirtual 181	java/lang/String:length	()I
    //   284: ifle +13 -> 297
    //   287: aload_0
    //   288: ldc 183
    //   290: aload_3
    //   291: ldc 185
    //   293: invokevirtual 187	com/alipay/mobilesecuritysdk/model/Upload:uploadCollectedData	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/alipay/mobilesecuritysdk/datainfo/GeoResponseInfo;
    //   296: astore_1
    //   297: aload_1
    //   298: invokevirtual 190	com/alipay/mobilesecuritysdk/datainfo/GeoResponseInfo:isSuccess	()Z
    //   301: ifne +127 -> 428
    //   304: new 136	java/lang/StringBuilder
    //   307: dup
    //   308: aload_0
    //   309: getfield 22	com/alipay/mobilesecuritysdk/model/Upload:mcontext	Landroid/content/Context;
    //   312: invokevirtual 142	android/content/Context:getFilesDir	()Ljava/io/File;
    //   315: invokevirtual 147	java/io/File:getPath	()Ljava/lang/String;
    //   318: invokestatic 153	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   321: invokespecial 154	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   324: getstatic 158	java/io/File:separator	Ljava/lang/String;
    //   327: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: ldc 199
    //   332: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   335: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   338: aload_3
    //   339: invokestatic 194	com/alipay/mobilesecuritysdk/util/CommonUtils:WriteFile	(Ljava/lang/String;Ljava/lang/String;)V
    //   342: aload_1
    //   343: astore_3
    //   344: aload_3
    //   345: areturn
    //   346: astore_3
    //   347: ldc 206
    //   349: aload_3
    //   350: invokevirtual 107	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   353: invokestatic 209	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   356: pop
    //   357: goto -172 -> 185
    //   360: aload_0
    //   361: getfield 20	com/alipay/mobilesecuritysdk/model/Upload:profile	Lcom/alipay/mobilesecuritysdk/model/DataProfile;
    //   364: new 136	java/lang/StringBuilder
    //   367: dup
    //   368: aload_0
    //   369: getfield 22	com/alipay/mobilesecuritysdk/model/Upload:mcontext	Landroid/content/Context;
    //   372: invokevirtual 142	android/content/Context:getFilesDir	()Ljava/io/File;
    //   375: invokevirtual 147	java/io/File:getPath	()Ljava/lang/String;
    //   378: invokestatic 153	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   381: invokespecial 154	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   384: getstatic 158	java/io/File:separator	Ljava/lang/String;
    //   387: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: ldc 164
    //   392: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   395: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   398: invokevirtual 212	com/alipay/mobilesecuritysdk/model/DataProfile:cleanUploadFiles	(Ljava/lang/String;)V
    //   401: ldc 206
    //   403: ldc 214
    //   405: invokestatic 113	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   408: pop
    //   409: goto -224 -> 185
    //   412: astore_2
    //   413: ldc 216
    //   415: aload_2
    //   416: invokevirtual 107	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   419: invokestatic 209	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   422: pop
    //   423: aload_1
    //   424: astore_3
    //   425: goto -81 -> 344
    //   428: aload_0
    //   429: getfield 20	com/alipay/mobilesecuritysdk/model/Upload:profile	Lcom/alipay/mobilesecuritysdk/model/DataProfile;
    //   432: new 136	java/lang/StringBuilder
    //   435: dup
    //   436: aload_0
    //   437: getfield 22	com/alipay/mobilesecuritysdk/model/Upload:mcontext	Landroid/content/Context;
    //   440: invokevirtual 142	android/content/Context:getFilesDir	()Ljava/io/File;
    //   443: invokevirtual 147	java/io/File:getPath	()Ljava/lang/String;
    //   446: invokestatic 153	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   449: invokespecial 154	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   452: getstatic 158	java/io/File:separator	Ljava/lang/String;
    //   455: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   458: ldc 199
    //   460: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   466: invokevirtual 212	com/alipay/mobilesecuritysdk/model/DataProfile:cleanUploadFiles	(Ljava/lang/String;)V
    //   469: ldc 216
    //   471: ldc 214
    //   473: invokestatic 113	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   476: pop
    //   477: aload_1
    //   478: astore_3
    //   479: goto -135 -> 344
    //
    // Exception table:
    //   from	to	target	type
    //   146	185	346	java/io/IOException
    //   304	342	412	java/io/IOException
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.model.Upload
 * JD-Core Version:    0.6.2
 */