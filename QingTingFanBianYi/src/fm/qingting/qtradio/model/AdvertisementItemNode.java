package fm.qingting.qtradio.model;

import android.graphics.Bitmap;
import android.widget.ImageView;
import cn.com.mma.mobile.tracking.api.Countly;
import com.miaozhen.mzmonitor.MZMonitor;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.utils.ImageLoader;
import fm.qingting.framework.utils.ImageLoaderHandler;
import fm.qingting.qtradio.ad.AdPos;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AdvertisementItemNode extends Node
  implements ImageLoaderHandler
{
  public String audioPath;
  public List<String> clickTracking;
  public String desc;
  public int duration;
  public long endTime;
  public int height;
  public String id;
  public String image;
  public List<String> imageTracking;
  public int internal_catid;
  public int internal_channelid;
  public String internal_landing;
  public int interval;
  public int interval_channeltype;
  public int interval_programid;
  public boolean isSplash = false;
  public String landing;
  private final String logType = "ad_track_v6";
  private String mDescription;
  private transient Node mNode;
  private transient RecommendItemNode mRecommendItemNode;
  private String mTitle;
  public AdTrackers mTracker = new AdTrackers();
  public int resType;
  public String skin;
  public String splash_landing = null;
  public long startTime;
  public boolean useLocalWebview = false;
  public int width;

  public AdvertisementItemNode()
  {
    this.nodeName = "advertisementitem";
  }

  // ERROR //
  private void restoreImage(Bitmap paramBitmap, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +28 -> 29
    //   4: aload_2
    //   5: ifnull +24 -> 29
    //   8: invokestatic 89	fm/qingting/qtradio/model/SharedCfg:getInstance	()Lfm/qingting/qtradio/model/SharedCfg;
    //   11: aload_2
    //   12: invokevirtual 93	fm/qingting/qtradio/model/SharedCfg:getAdvertisementImage	(Ljava/lang/String;)Ljava/lang/String;
    //   15: astore_3
    //   16: aload_3
    //   17: ifnull +13 -> 30
    //   20: aload_3
    //   21: ldc 95
    //   23: invokevirtual 101	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   26: ifne +4 -> 30
    //   29: return
    //   30: invokestatic 106	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   33: invokevirtual 110	fm/qingting/qtradio/model/InfoManager:getContext	()Landroid/content/Context;
    //   36: invokevirtual 115	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   39: invokevirtual 119	android/content/Context:getFilesDir	()Ljava/io/File;
    //   42: invokevirtual 125	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   45: astore_3
    //   46: new 127	java/lang/StringBuilder
    //   49: dup
    //   50: invokespecial 128	java/lang/StringBuilder:<init>	()V
    //   53: aload_3
    //   54: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: ldc 134
    //   59: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: astore_3
    //   66: new 121	java/io/File
    //   69: dup
    //   70: aload_3
    //   71: invokespecial 140	java/io/File:<init>	(Ljava/lang/String;)V
    //   74: astore 4
    //   76: aload 4
    //   78: invokevirtual 144	java/io/File:createNewFile	()Z
    //   81: pop
    //   82: new 146	java/io/FileOutputStream
    //   85: dup
    //   86: aload 4
    //   88: invokespecial 149	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   91: astore 4
    //   93: aload_1
    //   94: getstatic 155	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   97: bipush 50
    //   99: aload 4
    //   101: invokevirtual 161	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   104: pop
    //   105: aload 4
    //   107: invokevirtual 164	java/io/FileOutputStream:flush	()V
    //   110: aload 4
    //   112: invokevirtual 167	java/io/FileOutputStream:close	()V
    //   115: invokestatic 89	fm/qingting/qtradio/model/SharedCfg:getInstance	()Lfm/qingting/qtradio/model/SharedCfg;
    //   118: aload_2
    //   119: aload_3
    //   120: invokevirtual 171	fm/qingting/qtradio/model/SharedCfg:setAdvertisementImage	(Ljava/lang/String;Ljava/lang/String;)V
    //   123: aload_0
    //   124: getfield 62	fm/qingting/qtradio/model/AdvertisementItemNode:isSplash	Z
    //   127: ifeq -98 -> 29
    //   130: invokestatic 89	fm/qingting/qtradio/model/SharedCfg:getInstance	()Lfm/qingting/qtradio/model/SharedCfg;
    //   133: aload_3
    //   134: invokevirtual 174	fm/qingting/qtradio/model/SharedCfg:setLocalAdvertisementPic	(Ljava/lang/String;)V
    //   137: return
    //   138: astore_1
    //   139: aload_1
    //   140: invokevirtual 177	java/io/IOException:printStackTrace	()V
    //   143: return
    //   144: astore_1
    //   145: aload_1
    //   146: invokevirtual 178	java/io/FileNotFoundException:printStackTrace	()V
    //   149: return
    //   150: astore_1
    //   151: aload_1
    //   152: invokevirtual 177	java/io/IOException:printStackTrace	()V
    //   155: return
    //   156: astore_1
    //   157: aload_1
    //   158: invokevirtual 177	java/io/IOException:printStackTrace	()V
    //   161: return
    //   162: astore_1
    //   163: return
    //
    // Exception table:
    //   from	to	target	type
    //   76	82	138	java/io/IOException
    //   82	93	144	java/io/FileNotFoundException
    //   105	110	150	java/io/IOException
    //   110	115	156	java/io/IOException
    //   66	76	162	java/lang/Exception
    //   76	82	162	java/lang/Exception
    //   82	93	162	java/lang/Exception
    //   93	105	162	java/lang/Exception
    //   105	110	162	java/lang/Exception
    //   110	115	162	java/lang/Exception
    //   115	137	162	java/lang/Exception
    //   139	143	162	java/lang/Exception
    //   145	149	162	java/lang/Exception
    //   151	155	162	java/lang/Exception
    //   157	161	162	java/lang/Exception
  }

  private void sendLog(String paramString1, String paramString2)
  {
    Object localObject = QTLogger.getInstance().buildCommonLog();
    label76: ChannelNode localChannelNode;
    if (localObject != null)
    {
      localObject = new StringBuilder().append((String)localObject).append("\"");
      if (paramString1 != "click")
        break label378;
      paramString1 = "2";
      localObject = paramString1 + "\"";
      if ((this.parent == null) || (!(this.parent instanceof AdPos)))
        break label384;
      paramString1 = (AdPos)this.parent;
      localObject = (String)localObject + ",\"" + paramString1.adtype + "\"";
      localObject = (String)localObject + ",\"" + this.id + "\"";
      localObject = (String)localObject + ",\"" + paramString1.posdesc + "\"";
      paramString1 = (String)localObject + ",\"" + paramString1.posid + "\"";
      localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
      if ((localObject == null) || (!((Node)localObject).nodeName.equalsIgnoreCase("program")))
        break label412;
      localObject = paramString1 + ",\"" + ((ProgramNode)localObject).id + "\"";
      localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      paramString1 = (String)localObject;
      if (localChannelNode != null)
        paramString1 = (String)localObject + ",\"" + localChannelNode.channelId;
    }
    label384: label412: for (paramString1 = paramString1 + ",\"" + localChannelNode.categoryId; ; paramString1 = paramString1 + ",\"\",\"\",\"\"")
    {
      paramString1 = paramString1 + ",\"" + paramString2 + "\"";
      LogModule.getInstance().send("ad_track_v6", paramString1);
      return;
      label378: paramString1 = "1";
      break;
      paramString1 = new AdPos();
      paramString1.adtype = 0;
      paramString1.posdesc = "";
      paramString1.posid = "";
      break label76;
    }
  }

  public RecommendItemNode convertToRecommendItem(int paramInt)
  {
    if (this.mRecommendItemNode != null)
      return this.mRecommendItemNode;
    this.mRecommendItemNode = new RecommendItemNode();
    this.mRecommendItemNode.id = this.id;
    this.mRecommendItemNode.isAds = true;
    this.mRecommendItemNode.mAdNode = this;
    this.mRecommendItemNode.name = this.desc;
    this.mRecommendItemNode.briefName = "广告";
    this.mRecommendItemNode.desc = this.desc;
    this.mRecommendItemNode.setSmallThumb(this.image);
    this.mRecommendItemNode.setMediumThumb(null);
    this.mRecommendItemNode.setLargeThumb(null);
    this.mRecommendItemNode.isweb = true;
    this.mRecommendItemNode.categoryPos = 0;
    this.mRecommendItemNode.mCategoryId = paramInt;
    Object localObject;
    if ((this.internal_landing != null) && (!this.internal_landing.equalsIgnoreCase("")))
    {
      localObject = new ProgramNode();
      ((ProgramNode)localObject).uniqueId = this.interval_programid;
      ((ProgramNode)localObject).id = ((ProgramNode)localObject).uniqueId;
      ((ProgramNode)localObject).channelId = this.internal_channelid;
      ((ProgramNode)localObject).title = this.desc;
      ((ProgramNode)localObject).channelType = this.interval_channeltype;
      this.mRecommendItemNode.mCategoryId = this.internal_catid;
    }
    for (this.mRecommendItemNode.mNode = ((Node)localObject); ; this.mRecommendItemNode.mNode = ((Node)localObject))
    {
      return this.mRecommendItemNode;
      localObject = new ActivityNode();
      ((ActivityNode)localObject).id = 1;
      ((ActivityNode)localObject).name = this.desc;
      ((ActivityNode)localObject).type = "1";
      ((ActivityNode)localObject).updatetime = 25200;
      ((ActivityNode)localObject).infoUrl = this.image;
      ((ActivityNode)localObject).infoTitle = this.desc;
      ((ActivityNode)localObject).desc = this.desc;
      ((ActivityNode)localObject).titleIconUrl = null;
      ((ActivityNode)localObject).network = null;
      ((ActivityNode)localObject).putUserInfo = false;
      ((ActivityNode)localObject).contentUrl = this.landing;
      ((ActivityNode)localObject).useLocalWebview = this.useLocalWebview;
      ((ActivityNode)localObject).imageTracking = this.imageTracking;
      ((ActivityNode)localObject).clickTracking = this.clickTracking;
    }
  }

  public Node getAdvNode()
  {
    if (this.mNode != null)
      return this.mNode;
    Object localObject;
    if ((this.internal_landing != null) && (!this.internal_landing.equalsIgnoreCase("")))
    {
      localObject = new ProgramNode();
      ((ProgramNode)localObject).uniqueId = this.interval_programid;
      ((ProgramNode)localObject).id = ((ProgramNode)localObject).uniqueId;
      ((ProgramNode)localObject).channelId = this.internal_channelid;
      ((ProgramNode)localObject).title = this.desc;
      ((ProgramNode)localObject).channelType = this.interval_channeltype;
    }
    for (this.mNode = ((Node)localObject); ; this.mNode = ((Node)localObject))
    {
      return this.mNode;
      localObject = new ActivityNode();
      ((ActivityNode)localObject).id = 1;
      ((ActivityNode)localObject).name = this.desc;
      ((ActivityNode)localObject).type = "1";
      ((ActivityNode)localObject).updatetime = 25200;
      ((ActivityNode)localObject).infoUrl = this.image;
      ((ActivityNode)localObject).infoTitle = this.desc;
      ((ActivityNode)localObject).desc = this.desc;
      ((ActivityNode)localObject).titleIconUrl = null;
      ((ActivityNode)localObject).network = null;
      ((ActivityNode)localObject).putUserInfo = false;
      ((ActivityNode)localObject).contentUrl = this.landing;
      ((ActivityNode)localObject).useLocalWebview = this.useLocalWebview;
    }
  }

  public String getDescription()
  {
    if (this.mDescription == null)
      this.mDescription = "";
    return this.mDescription;
  }

  public Bitmap getImage()
  {
    if ((this.image == null) || (this.image.equalsIgnoreCase("")))
      return null;
    Object localObject;
    if ((this.parent != null) && (((AdPos)this.parent).isSplash()))
    {
      this.isSplash = true;
      localObject = SharedCfg.getInstance().getNetAdvertisementPic();
      SharedCfg.getInstance().setSplashLanding(null);
      if (localObject != null)
      {
        if ((this.splash_landing == null) || (this.splash_landing.equalsIgnoreCase("")))
          break label180;
        SharedCfg.getInstance().setSplashLanding(this.splash_landing);
        if (((String)localObject).equalsIgnoreCase(this.image))
          break label212;
        SharedCfg.getInstance().setLocalAdvertisementPic("");
      }
    }
    label180: label212: 
    do
    {
      SharedCfg.getInstance().setNetAdvertisementPic(this.image);
      localObject = ImageLoader.getInstance(InfoManager.getInstance().getContext()).getImage(this.image, this.width, this.height);
      if (localObject == null)
        ImageLoader.getInstance(InfoManager.getInstance().getContext()).loadImage(this.image, null, this.width, this.height, false, this);
      return localObject;
      if ((this.landing == null) || (this.landing.equalsIgnoreCase("")))
        break;
      SharedCfg.getInstance().setSplashLanding(this.landing);
      break;
      localObject = SharedCfg.getInstance().getLocalAdvertisementPic();
    }
    while ((localObject == null) || (((String)localObject).equalsIgnoreCase("")));
    return null;
  }

  public int getInternalLandingCatid()
  {
    return this.internal_catid;
  }

  public String getTitle()
  {
    if (this.mTitle == null)
      this.mTitle = "";
    return this.mTitle;
  }

  public boolean isSplashLanding()
  {
    return (this.isSplash) && (this.splash_landing != null) && (!this.splash_landing.equalsIgnoreCase(""));
  }

  public void loadImageFinish(boolean paramBoolean, String paramString, Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if ((paramBitmap != null) && (paramString != null) && (this.image != null) && (paramString.equalsIgnoreCase(this.image)))
      restoreImage(paramBitmap, paramString);
  }

  public void onClick()
  {
    sendLog("click", this.mTracker.trackClick(true));
  }

  public void onShow()
  {
    sendLog("display", this.mTracker.trackDisplay(true));
  }

  public void setDescription(String paramString)
  {
    this.mDescription = paramString;
  }

  public void setTitle(String paramString)
  {
    this.mTitle = paramString;
  }

  public void trackAdMaster()
  {
    String str = SharedCfg.getInstance().getAdMasterUrl();
    if ((str != null) && (!str.equalsIgnoreCase("")))
      Countly.sharedInstance().onExpose(str);
  }

  public void updateImageViewFinish(boolean paramBoolean, ImageView paramImageView, String paramString, Bitmap paramBitmap)
  {
  }

  public void updateTracker()
  {
    this.mTracker.update();
  }

  public void zeusClick()
  {
    this.mTracker.trackClick(false);
  }

  public void zeusShow()
  {
    this.mTracker.trackDisplay(false);
  }

  public class AdTrackers
  {
    public List<String> lstEventType;
    public List<String> lstProvider;
    public List<String> lstTrackerUrl;

    public AdTrackers()
    {
    }

    // ERROR //
    private String macroReplace(String paramString)
    {
      // Byte code:
      //   0: invokestatic 49	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
      //   3: invokevirtual 53	fm/qingting/qtradio/model/InfoManager:getCurrentLocation	()Lfm/qingting/qtradio/model/QTLocation;
      //   6: ifnull +97 -> 103
      //   9: invokestatic 49	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
      //   12: invokevirtual 53	fm/qingting/qtradio/model/InfoManager:getCurrentLocation	()Lfm/qingting/qtradio/model/QTLocation;
      //   15: getfield 59	fm/qingting/qtradio/model/QTLocation:ip	Ljava/lang/String;
      //   18: astore_2
      //   19: ldc 61
      //   21: ldc 63
      //   23: invokestatic 69	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   26: astore_3
      //   27: ldc 71
      //   29: astore 4
      //   31: invokestatic 49	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
      //   34: invokevirtual 75	fm/qingting/qtradio/model/InfoManager:getContext	()Landroid/content/Context;
      //   37: invokestatic 81	fm/qingting/utils/DeviceInfo:getDeviceIMEI	(Landroid/content/Context;)Ljava/lang/String;
      //   40: astore 5
      //   42: aload 5
      //   44: astore 4
      //   46: aload_1
      //   47: ifnull +62 -> 109
      //   50: aload_1
      //   51: ldc 71
      //   53: invokevirtual 87	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   56: ifeq +5 -> 61
      //   59: aload_1
      //   60: areturn
      //   61: aload_1
      //   62: ldc 89
      //   64: ldc 91
      //   66: invokevirtual 95	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   69: ldc 97
      //   71: aload_2
      //   72: invokevirtual 95	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   75: ldc 99
      //   77: aload_3
      //   78: invokevirtual 95	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   81: ldc 101
      //   83: aload_0
      //   84: aload 4
      //   86: invokespecial 104	fm/qingting/qtradio/model/AdvertisementItemNode$AdTrackers:md5	(Ljava/lang/String;)Ljava/lang/String;
      //   89: invokevirtual 95	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   92: astore_2
      //   93: aload_2
      //   94: areturn
      //   95: astore_2
      //   96: aload_1
      //   97: areturn
      //   98: astore 5
      //   100: goto -54 -> 46
      //   103: ldc 71
      //   105: astore_2
      //   106: goto -87 -> 19
      //   109: aload_1
      //   110: areturn
      //   111: astore_3
      //   112: ldc 61
      //   114: astore_3
      //   115: goto -88 -> 27
      //
      // Exception table:
      //   from	to	target	type
      //   0	19	95	java/lang/Exception
      //   19	27	95	java/lang/Exception
      //   50	59	95	java/lang/Exception
      //   61	93	95	java/lang/Exception
      //   31	42	98	java/lang/Exception
      //   19	27	111	java/io/UnsupportedEncodingException
    }

    private String md5(String paramString)
    {
      try
      {
        Object localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject).update(paramString.getBytes());
        paramString = ((MessageDigest)localObject).digest();
        localObject = new StringBuffer();
        int i = 0;
        while (i < paramString.length)
        {
          ((StringBuffer)localObject).append(Integer.toHexString(paramString[i] & 0xFF));
          i += 1;
        }
        paramString = ((StringBuffer)localObject).toString();
        return paramString;
      }
      catch (NoSuchAlgorithmException paramString)
      {
        paramString.printStackTrace();
      }
      return "";
    }

    private String trackClick(boolean paramBoolean)
    {
      String str1 = "";
      String str2 = str1;
      int i;
      if (this.lstProvider != null)
      {
        str2 = str1;
        if (this.lstTrackerUrl != null)
        {
          i = 0;
          str2 = str1;
          if (i < this.lstEventType.size())
          {
            str2 = str1;
            if (i < this.lstEventType.size())
            {
              if (i < this.lstTrackerUrl.size())
                break label74;
              str2 = str1;
            }
          }
        }
      }
      return str2;
      label74: if (!paramBoolean);
      for (str2 = "ZeusTracker"; ; str2 = "RealTracker")
      {
        Object localObject2 = (String)this.lstProvider.get(i);
        Object localObject1 = (String)this.lstEventType.get(i);
        String str3 = (String)this.lstTrackerUrl.get(i);
        if (((String)localObject1).equalsIgnoreCase("click"))
          if (((String)localObject2).equalsIgnoreCase("miaozhen"))
          {
            localObject2 = new StringBuilder().append(str1);
            if (str1.equals(""))
            {
              str1 = "1";
              label174: str1 = str1;
              str3 = macroReplace(str3);
              MZMonitor.eventTrack(InfoManager.getInstance().getContext(), str3, (String)localObject1);
              MobclickAgent.onEvent(InfoManager.getInstance().getContext(), str2, "mzclick:" + str3);
            }
          }
        while (true)
        {
          i += 1;
          break;
          str1 = "|1";
          break label174;
          if (((String)localObject2).equalsIgnoreCase("AdMaster"))
          {
            localObject1 = new StringBuilder().append(str1);
            if (str1.equals(""));
            for (str1 = "2"; ; str1 = "|2")
            {
              str1 = str1;
              Countly.sharedInstance().onClick(str3);
              MobclickAgent.onEvent(InfoManager.getInstance().getContext(), str2, "adclick:" + str3);
              break;
            }
          }
        }
      }
    }

    private String trackDisplay(boolean paramBoolean)
    {
      String str1 = "";
      String str3 = str1;
      if (this.lstProvider != null)
      {
        str3 = str1;
        if (this.lstTrackerUrl != null)
          if (paramBoolean)
            break label364;
      }
      label87: label364: for (String str2 = "ZeusTracker"; ; str2 = "RealTracker")
      {
        int i = 0;
        str3 = str1;
        if (i < this.lstProvider.size())
        {
          str3 = str1;
          if (i < this.lstEventType.size())
          {
            if (i < this.lstTrackerUrl.size())
              break label87;
            str3 = str1;
          }
        }
        return str3;
        Object localObject = (String)this.lstProvider.get(i);
        String str4 = (String)this.lstEventType.get(i);
        str3 = (String)this.lstTrackerUrl.get(i);
        if ((str4 != null) && (str4.equalsIgnoreCase("display")))
          if ((localObject != null) && (((String)localObject).equalsIgnoreCase("AdMaster")))
          {
            localObject = new StringBuilder().append(str1);
            if (str1.equals(""))
            {
              str1 = "2";
              str1 = str1;
              Countly.sharedInstance().onExpose(str3);
              MobclickAgent.onEvent(InfoManager.getInstance().getContext(), str2, "addisplay:" + str3);
            }
          }
        while (true)
        {
          i += 1;
          break;
          str1 = "|2";
          break label190;
          if ((localObject != null) && (((String)localObject).equalsIgnoreCase("miaozhen")))
          {
            localObject = new StringBuilder().append(str1);
            if (str1.equals(""));
            for (str1 = "1"; ; str1 = "|1")
            {
              str1 = str1;
              str3 = macroReplace(str3);
              MZMonitor.adTrack(InfoManager.getInstance().getContext(), str3);
              MobclickAgent.onEvent(InfoManager.getInstance().getContext(), str2, "mzdisplay:" + str3);
              break;
            }
          }
        }
      }
    }

    private void update()
    {
      int i;
      if ((this.lstProvider != null) && (this.lstTrackerUrl != null))
      {
        SharedCfg.getInstance().setAdMasterUrl(null);
        i = 0;
      }
      while (true)
      {
        if (i < this.lstProvider.size())
        {
          if ((((String)this.lstProvider.get(i)).equalsIgnoreCase("AdMaster")) && (i < this.lstTrackerUrl.size()))
            SharedCfg.getInstance().setAdMasterUrl((String)this.lstTrackerUrl.get(i));
        }
        else
          return;
        i += 1;
      }
    }
  }

  class TrackType
  {
    public static final String click = "click";
    public static final String display = "display";

    TrackType()
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.AdvertisementItemNode
 * JD-Core Version:    0.6.2
 */