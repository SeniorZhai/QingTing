package fm.qingting.qtradio.manager;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import fm.qingting.downloadnew.DownloadUtils;
import fm.qingting.framework.data.RequestTrait;
import fm.qingting.framework.data.ServerConfig;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.UserProfile;
import java.io.File;
import java.io.IOException;

public class RecorderManager
{
  private static final String CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/QTRadioUploadCache";
  private static final String LOG_TAG = "RecorderMgr";
  private static final int MAX_AMP_VALUE = 32767;
  private static RecorderManager instance = null;
  private final Runnable mApmRunnable = new Runnable()
  {
    public void run()
    {
      if ((RecorderManager.this.state == 0) && (RecorderManager.this.mRecorder != null))
      {
        RecorderManager.this.onAmpChanged(RecorderManager.this.mRecorder.getMaxAmplitude());
        RecorderManager.this.mHandler.postDelayed(this, 100L);
      }
    }
  };
  private long mCachedFileDurationMSec = 0L;
  private final Handler mHandler = new Handler();
  private long mMaxDurationSec = 0L;
  private MediaPlayer mPlayer = null;
  private RecorderHandler mRecordHandler = null;
  private MediaRecorder mRecorder = null;
  private final Runnable mSecRunnable = new Runnable()
  {
    public void run()
    {
      if (RecorderManager.this.state == 0)
        RecorderManager.this.onRecordingSecond();
      while (RecorderManager.this.state != 1)
        return;
      RecorderManager.this.onReplaySecond();
    }
  };
  private long mStartTime = 0L;
  private String mTagID = "";
  private String mTitle = null;
  private final Handler mUploadHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      int i = paramAnonymousMessage.getData().getInt("status");
      RecorderManager.this.cleanup();
      if (i >= 0)
      {
        RecorderManager.this.deleteCachedFile();
        EventDispacthManager.getInstance().dispatchAction("refreshUploadView", String.format("上传\"%s\"成功！", new Object[] { RecorderManager.this.mTitle }));
      }
      while (true)
      {
        Log.d("RecorderMgr", String.format("upload result: %d", new Object[] { Integer.valueOf(i) }));
        return;
        RecorderManager.this.renameCachedFile();
        EventDispacthManager.getInstance().dispatchAction("refreshUploadView", String.format("上传\"%s\"失败！请稍后重试。", new Object[] { RecorderManager.this.mTitle }));
      }
    }
  };
  private final Runnable mUploadRunnable = new Runnable()
  {
    public void run()
    {
      int i = -1;
      try
      {
        int j = RecorderManager.this.doUploadCachedFile();
        i = j;
        Message localMessage = new Message();
        Bundle localBundle = new Bundle();
        localBundle.putInt("status", i);
        localMessage.setData(localBundle);
        RecorderManager.this.mUploadHandler.sendMessage(localMessage);
        return;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
  };
  private String mUploadUrl = "";
  private String mUserId = null;
  private String mUserName = null;
  private int state = -1;

  // ERROR //
  private int doUploadCachedFile()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_2
    //   4: new 169	java/io/FileInputStream
    //   7: dup
    //   8: aload_0
    //   9: invokevirtual 172	fm/qingting/qtradio/manager/RecorderManager:getCachedFile	()Ljava/io/File;
    //   12: invokespecial 175	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   15: astore 4
    //   17: new 177	java/net/URL
    //   20: dup
    //   21: aload_0
    //   22: getfield 117	fm/qingting/qtradio/manager/RecorderManager:mUploadUrl	Ljava/lang/String;
    //   25: invokespecial 180	java/net/URL:<init>	(Ljava/lang/String;)V
    //   28: invokevirtual 184	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   31: checkcast 186	java/net/HttpURLConnection
    //   34: astore_1
    //   35: aload_1
    //   36: iconst_1
    //   37: invokevirtual 190	java/net/HttpURLConnection:setDoInput	(Z)V
    //   40: aload_1
    //   41: iconst_1
    //   42: invokevirtual 193	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   45: aload_1
    //   46: iconst_0
    //   47: invokevirtual 196	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   50: aload_1
    //   51: ldc 198
    //   53: invokevirtual 201	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   56: aload_1
    //   57: ldc 203
    //   59: ldc 205
    //   61: invokevirtual 209	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   64: aload_1
    //   65: ldc 211
    //   67: new 58	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   74: ldc 213
    //   76: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: ldc 215
    //   81: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokevirtual 209	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   90: new 217	java/io/DataOutputStream
    //   93: dup
    //   94: aload_1
    //   95: invokevirtual 221	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   98: invokespecial 224	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   101: astore_2
    //   102: aload_2
    //   103: new 58	java/lang/StringBuilder
    //   106: dup
    //   107: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   110: ldc 226
    //   112: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: ldc 215
    //   117: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: ldc 228
    //   122: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   131: aload_2
    //   132: new 58	java/lang/StringBuilder
    //   135: dup
    //   136: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   139: ldc 233
    //   141: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: ldc 228
    //   146: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   152: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   155: aload_2
    //   156: ldc 228
    //   158: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   161: aload_2
    //   162: aload_0
    //   163: getfield 96	fm/qingting/qtradio/manager/RecorderManager:mUserName	Ljava/lang/String;
    //   166: ldc 235
    //   168: invokevirtual 241	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   171: invokevirtual 245	java/io/DataOutputStream:write	([B)V
    //   174: aload_2
    //   175: ldc 228
    //   177: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   180: aload_2
    //   181: new 58	java/lang/StringBuilder
    //   184: dup
    //   185: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   188: ldc 226
    //   190: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: ldc 215
    //   195: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: ldc 228
    //   200: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   209: aload_2
    //   210: new 58	java/lang/StringBuilder
    //   213: dup
    //   214: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   217: ldc 247
    //   219: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: ldc 228
    //   224: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   230: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   233: aload_2
    //   234: ldc 228
    //   236: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   239: aload_2
    //   240: aload_0
    //   241: getfield 98	fm/qingting/qtradio/manager/RecorderManager:mUserId	Ljava/lang/String;
    //   244: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   247: aload_2
    //   248: ldc 228
    //   250: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   253: aload_2
    //   254: new 58	java/lang/StringBuilder
    //   257: dup
    //   258: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   261: ldc 226
    //   263: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: ldc 215
    //   268: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: ldc 228
    //   273: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   279: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   282: aload_2
    //   283: new 58	java/lang/StringBuilder
    //   286: dup
    //   287: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   290: ldc 249
    //   292: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   295: ldc 228
    //   297: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   303: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   306: aload_2
    //   307: ldc 228
    //   309: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   312: aload_2
    //   313: aload_0
    //   314: getfield 92	fm/qingting/qtradio/manager/RecorderManager:mTagID	Ljava/lang/String;
    //   317: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   320: aload_2
    //   321: ldc 228
    //   323: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   326: aload_2
    //   327: new 58	java/lang/StringBuilder
    //   330: dup
    //   331: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   334: ldc 226
    //   336: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: ldc 215
    //   341: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: ldc 228
    //   346: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   352: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   355: aload_2
    //   356: new 58	java/lang/StringBuilder
    //   359: dup
    //   360: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   363: ldc 251
    //   365: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   368: ldc 228
    //   370: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   373: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   376: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   379: aload_2
    //   380: ldc 228
    //   382: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   385: aload_2
    //   386: aload_0
    //   387: getfield 94	fm/qingting/qtradio/manager/RecorderManager:mTitle	Ljava/lang/String;
    //   390: ldc 235
    //   392: invokevirtual 241	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   395: invokevirtual 245	java/io/DataOutputStream:write	([B)V
    //   398: aload_2
    //   399: ldc 228
    //   401: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   404: aload_2
    //   405: new 58	java/lang/StringBuilder
    //   408: dup
    //   409: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   412: ldc 226
    //   414: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: ldc 215
    //   419: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   422: ldc 228
    //   424: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   427: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   430: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   433: aload_2
    //   434: new 58	java/lang/StringBuilder
    //   437: dup
    //   438: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   441: ldc 253
    //   443: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   446: aload_0
    //   447: invokespecial 256	fm/qingting/qtradio/manager/RecorderManager:getCachedFilePath	()Ljava/lang/String;
    //   450: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: ldc_w 258
    //   456: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: ldc 228
    //   461: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   464: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   467: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   470: aload_2
    //   471: ldc 228
    //   473: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   476: aload 4
    //   478: invokevirtual 261	java/io/FileInputStream:available	()I
    //   481: ldc_w 262
    //   484: invokestatic 268	java/lang/Math:min	(II)I
    //   487: istore 5
    //   489: iload 5
    //   491: newarray byte
    //   493: astore_3
    //   494: aload 4
    //   496: aload_3
    //   497: iconst_0
    //   498: iload 5
    //   500: invokevirtual 272	java/io/FileInputStream:read	([BII)I
    //   503: istore 6
    //   505: iload 6
    //   507: ifle +38 -> 545
    //   510: aload_2
    //   511: aload_3
    //   512: iconst_0
    //   513: iload 5
    //   515: invokevirtual 275	java/io/DataOutputStream:write	([BII)V
    //   518: aload 4
    //   520: invokevirtual 261	java/io/FileInputStream:available	()I
    //   523: ldc_w 262
    //   526: invokestatic 268	java/lang/Math:min	(II)I
    //   529: istore 5
    //   531: aload 4
    //   533: aload_3
    //   534: iconst_0
    //   535: iload 5
    //   537: invokevirtual 272	java/io/FileInputStream:read	([BII)I
    //   540: istore 6
    //   542: goto -37 -> 505
    //   545: aload_2
    //   546: ldc 228
    //   548: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   551: aload_2
    //   552: new 58	java/lang/StringBuilder
    //   555: dup
    //   556: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   559: ldc 226
    //   561: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   564: ldc 215
    //   566: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   569: ldc 226
    //   571: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   574: ldc 228
    //   576: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   579: invokevirtual 82	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   582: invokevirtual 231	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   585: aload 4
    //   587: invokevirtual 278	java/io/FileInputStream:close	()V
    //   590: aload_2
    //   591: invokevirtual 281	java/io/DataOutputStream:flush	()V
    //   594: aload_2
    //   595: invokevirtual 282	java/io/DataOutputStream:close	()V
    //   598: new 284	java/io/DataInputStream
    //   601: dup
    //   602: aload_1
    //   603: invokevirtual 288	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   606: invokespecial 291	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   609: astore_1
    //   610: bipush 254
    //   612: istore 5
    //   614: aload_1
    //   615: invokevirtual 294	java/io/DataInputStream:readLine	()Ljava/lang/String;
    //   618: astore_2
    //   619: aload_2
    //   620: ifnull +47 -> 667
    //   623: aload_2
    //   624: invokestatic 300	com/alibaba/fastjson/JSON:parse	(Ljava/lang/String;)Ljava/lang/Object;
    //   627: checkcast 302	com/alibaba/fastjson/JSONObject
    //   630: ldc_w 304
    //   633: invokevirtual 308	com/alibaba/fastjson/JSONObject:getIntValue	(Ljava/lang/String;)I
    //   636: istore 6
    //   638: iload 6
    //   640: istore 5
    //   642: goto -28 -> 614
    //   645: astore_3
    //   646: aload_2
    //   647: astore_1
    //   648: aload_3
    //   649: astore_2
    //   650: aload_2
    //   651: invokevirtual 311	java/net/MalformedURLException:printStackTrace	()V
    //   654: goto -56 -> 598
    //   657: astore_2
    //   658: aload_3
    //   659: astore_1
    //   660: aload_2
    //   661: invokevirtual 312	java/io/IOException:printStackTrace	()V
    //   664: goto -66 -> 598
    //   667: aload_1
    //   668: invokevirtual 313	java/io/DataInputStream:close	()V
    //   671: iload 5
    //   673: ireturn
    //   674: astore_1
    //   675: bipush 254
    //   677: istore 5
    //   679: aload_1
    //   680: invokevirtual 314	java/lang/Exception:printStackTrace	()V
    //   683: iload 5
    //   685: ireturn
    //   686: astore_1
    //   687: goto -8 -> 679
    //   690: astore_2
    //   691: goto -31 -> 660
    //   694: astore_2
    //   695: goto -45 -> 650
    //
    // Exception table:
    //   from	to	target	type
    //   4	35	645	java/net/MalformedURLException
    //   4	35	657	java/io/IOException
    //   598	610	674	java/lang/Exception
    //   614	619	686	java/lang/Exception
    //   623	638	686	java/lang/Exception
    //   667	671	686	java/lang/Exception
    //   35	505	690	java/io/IOException
    //   510	542	690	java/io/IOException
    //   545	598	690	java/io/IOException
    //   35	505	694	java/net/MalformedURLException
    //   510	542	694	java/net/MalformedURLException
    //   545	598	694	java/net/MalformedURLException
  }

  private String getCachedFilePath()
  {
    return getCachedFilePath(this.mTagID);
  }

  public static String getCachedFilePath(String paramString)
  {
    paramString = new File(CACHE_PATH + "/" + paramString);
    if ((paramString.exists()) && (paramString.isDirectory()))
    {
      paramString = paramString.listFiles();
      if (paramString.length > 0)
        return paramString[0].getAbsolutePath();
    }
    return "";
  }

  public static RecorderManager getInstance()
  {
    if (instance == null)
      instance = new RecorderManager();
    return instance;
  }

  private String newCachedFilePath()
  {
    String str = CACHE_PATH + "/" + this.mTagID;
    new File(str).mkdirs();
    return str + "/" + this.mTitle + ".m4a";
  }

  private void onAmpChanged(int paramInt)
  {
    float f1 = 0.0F;
    float f2;
    if (this.mRecordHandler != null)
    {
      f2 = (paramInt + 0.0F) / 32767.0F;
      if (f2 >= 0.0F)
        break label35;
    }
    while (true)
    {
      this.mRecordHandler.onMonitorAmpChanged(f1);
      return;
      label35: if (f2 > 1.0F)
        f1 = 1.0F;
      else
        f1 = f2;
    }
  }

  private void onRecordingSecond()
  {
    if (this.mRecorder != null)
    {
      long l = (int)Math.ceil((System.currentTimeMillis() - this.mStartTime) / 1000L);
      if (this.mRecordHandler != null)
        this.mRecordHandler.onRecordingSecond(Long.valueOf(l));
      this.mHandler.postDelayed(this.mSecRunnable, 1000L);
    }
  }

  private void onReplaySecond()
  {
    if (this.mPlayer != null)
    {
      long l = this.mPlayer.getCurrentPosition();
      if (this.mRecordHandler != null)
        this.mRecordHandler.onReplaySecond(Long.valueOf(l));
      this.mHandler.postDelayed(this.mSecRunnable, 200L);
    }
  }

  public void cleanup()
  {
    this.mHandler.removeCallbacks(this.mSecRunnable);
    this.state = -1;
    this.mCachedFileDurationMSec = 0L;
    if (this.mRecorder != null)
    {
      this.mRecorder.reset();
      this.mRecorder.release();
      this.mRecorder = null;
    }
    if (this.mPlayer != null)
    {
      this.mPlayer.reset();
      this.mPlayer.release();
      this.mPlayer = null;
    }
  }

  public boolean deleteCachedFile()
  {
    return getCachedFile().delete();
  }

  public long getAudioDurationMSec(File paramFile)
  {
    long l2 = 0L;
    if (!paramFile.exists())
      return 0L;
    paramFile = new MediaPlayer();
    long l1 = l2;
    try
    {
      paramFile.setDataSource(getCachedFilePath());
      l1 = l2;
      paramFile.prepare();
      l1 = l2;
      l2 = paramFile.getDuration();
      l1 = l2;
      paramFile.reset();
      l1 = l2;
      paramFile.release();
      return l2;
    }
    catch (IOException paramFile)
    {
      Log.e("RecorderMgr", "player prepare() failed");
      paramFile.printStackTrace();
    }
    return l1;
  }

  public long getAvaliableMemSize()
  {
    return DownloadUtils.getAvailableExternalMemorySize();
  }

  public File getCachedFile()
  {
    return new File(getCachedFilePath());
  }

  public long getCachedFileDurationMSec()
  {
    if (this.mCachedFileDurationMSec <= 0L)
    {
      this.mCachedFileDurationMSec = getAudioDurationMSec(getCachedFile());
      if ((this.mMaxDurationSec > 0L) && (this.mCachedFileDurationMSec > this.mMaxDurationSec * 1000L))
        this.mCachedFileDurationMSec = (this.mMaxDurationSec * 1000L);
    }
    return this.mCachedFileDurationMSec;
  }

  public String getCachedFileName()
  {
    return this.mTitle;
  }

  public long getMaxDurationSec()
  {
    return this.mMaxDurationSec;
  }

  public String getUserName()
  {
    return this.mUserName;
  }

  public String init(String paramString, int paramInt)
  {
    this.mUploadUrl = ServerConfig.getInstance().getRequestTrait("post_recorded_voice").getCommand();
    this.mMaxDurationSec = paramInt;
    this.mCachedFileDurationMSec = 0L;
    this.mTagID = paramString;
    try
    {
      this.mUserName = InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_name;
      this.mTitle = (this.mUserName + "的录音");
      if ((float)getAvaliableMemSize() < 10000000.0F)
        return "requireMem";
    }
    catch (Exception paramString)
    {
      while (true)
        this.mUserName = "蜻蜓用户";
      if (!CloudCenter.getInstance().isLogin(false))
        return "requireLogin";
      if (NetWorkManage.getInstance().getNetWorkType().equalsIgnoreCase("noNet"))
        return "requireNet";
      deleteCachedFile();
    }
    return "";
  }

  public boolean initByFile(String paramString)
  {
    this.mTagID = paramString;
    paramString = getCachedFile();
    if (!paramString.exists())
      return false;
    cleanup();
    this.mMaxDurationSec = 0L;
    this.mCachedFileDurationMSec = 0L;
    this.mUploadUrl = ServerConfig.getInstance().getRequestTrait("post_recorded_voice").getCommand();
    try
    {
      this.mUserName = InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_name;
      this.mTitle = paramString.getName().split("\\.(?=[^\\.]+$)")[0];
      return true;
    }
    catch (Exception localException)
    {
      while (true)
        this.mUserName = "蜻蜓用户";
    }
  }

  public boolean isReplaying()
  {
    if (this.mPlayer != null)
      return this.mPlayer.isPlaying();
    return false;
  }

  public boolean isUploading()
  {
    return this.state == 3;
  }

  public void pauseReplay()
  {
    try
    {
      this.mPlayer.pause();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public boolean renameCachedFile()
  {
    return getCachedFile().renameTo(new File(CACHE_PATH + "/" + this.mTagID + "/" + this.mTitle + ".m4a"));
  }

  @TargetApi(10)
  public void startRecording(RecorderHandler paramRecorderHandler)
  {
    if (this.mMaxDurationSec <= 0L);
    while (true)
    {
      return;
      cleanup();
      this.state = 0;
      this.mRecorder = new MediaRecorder();
      this.mRecorder.setAudioSource(1);
      this.mRecorder.setOutputFormat(2);
      String str2 = getCachedFilePath();
      String str1 = str2;
      if (str2.length() == 0)
        str1 = newCachedFilePath();
      this.mRecorder.setOutputFile(str1);
      if (QtApiLevelManager.isApiLevelSupported(10))
      {
        this.mRecorder.setAudioEncoder(3);
        this.mRecorder.setAudioEncodingBitRate(64000);
        this.mRecorder.setAudioSamplingRate(44100);
        this.mRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener()
        {
          public void onInfo(MediaRecorder paramAnonymousMediaRecorder, int paramAnonymousInt1, int paramAnonymousInt2)
          {
            if (paramAnonymousInt1 == 800)
              RecorderManager.this.stopRecording();
          }
        });
        this.mRecorder.setMaxDuration((int)this.mMaxDurationSec * 1000);
        this.mRecordHandler = paramRecorderHandler;
      }
      try
      {
        this.mRecorder.prepare();
        this.mStartTime = System.currentTimeMillis();
        this.mHandler.postDelayed(this.mSecRunnable, 1000L);
        this.mHandler.postDelayed(this.mApmRunnable, 0L);
        this.mRecorder.start();
        if (this.mRecordHandler != null)
        {
          this.mRecordHandler.onRecordingStart();
          return;
        }
      }
      catch (IOException paramRecorderHandler)
      {
        Log.e("RecorderMgr", "recorder prepare() failed");
        paramRecorderHandler.printStackTrace();
        return;
        this.mRecorder.setAudioEncoder(1);
        this.mRecorder.setAudioEncodingBitRate(12200);
        this.mRecorder.setAudioSamplingRate(8000);
      }
      catch (Exception paramRecorderHandler)
      {
        paramRecorderHandler.printStackTrace();
      }
    }
  }

  public void startReplay(RecorderHandler paramRecorderHandler)
  {
    if ((this.mPlayer != null) && (this.state == 1));
    try
    {
      while (true)
      {
        this.mPlayer.start();
        return;
        cleanup();
        PlayerAgent.getInstance().stop();
        this.state = 1;
        this.mPlayer = new MediaPlayer();
        this.mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
          public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
          {
            RecorderManager.this.mRecordHandler.onReplayStop();
            RecorderManager.this.cleanup();
          }
        });
        this.mRecordHandler = paramRecorderHandler;
        try
        {
          this.mPlayer.setDataSource(getCachedFilePath());
          this.mPlayer.prepare();
          this.mPlayer.setVolume(1.0F, 1.0F);
          this.mHandler.postDelayed(this.mSecRunnable, 200L);
        }
        catch (IOException paramRecorderHandler)
        {
          Log.e("RecorderMgr", "player prepare() failed");
          paramRecorderHandler.printStackTrace();
        }
        catch (Exception paramRecorderHandler)
        {
          paramRecorderHandler.printStackTrace();
        }
      }
    }
    catch (Exception paramRecorderHandler)
    {
      paramRecorderHandler.printStackTrace();
    }
  }

  public void stopRecording()
  {
    try
    {
      this.mRecorder.stop();
      if (this.mRecordHandler != null)
        this.mRecordHandler.onRecordingStop();
      cleanup();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void uploadCachedFile(String paramString)
  {
    this.state = 3;
    this.mUserId = InfoManager.getInstance().getUserProfile().getUserKey();
    if ((this.mUserId == null) || (this.mUserId.equalsIgnoreCase("")))
      this.mUserId = InfoManager.getInstance().getDeviceId();
    try
    {
      this.mUserName = InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_name;
      if (paramString != null)
      {
        this.mTitle = paramString;
        Log.d("RecorderMgr", String.format("NewThread: Upload %s, %s, %s, %s, %s", new Object[] { getCachedFilePath(), this.mTagID, this.mUserId, this.mUserName, this.mTitle }));
        new Thread(this.mUploadRunnable).start();
        EventDispacthManager.getInstance().dispatchAction("refreshUploadView", String.format("开始上传\"%s\"！", new Object[] { this.mTitle }));
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        this.mUserName = "蜻蜓用户";
        continue;
        paramString = this.mUserName + "的录音";
      }
    }
  }

  public static abstract interface RecorderHandler
  {
    public abstract void onMonitorAmpChanged(float paramFloat);

    public abstract void onRecordingSecond(Long paramLong);

    public abstract void onRecordingStart();

    public abstract void onRecordingStop();

    public abstract void onReplaySecond(Long paramLong);

    public abstract void onReplayStop();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.manager.RecorderManager
 * JD-Core Version:    0.6.2
 */