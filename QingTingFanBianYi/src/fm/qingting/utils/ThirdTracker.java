package fm.qingting.utils;

import android.content.Context;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.com.mma.mobile.tracking.api.Countly;
import com.miaozhen.mzmonitor.MZMonitor;
import fm.qingting.qtradio.ad.AdConfig;
import fm.qingting.qtradio.jd.data.CommodityInfo;
import fm.qingting.qtradio.jd.data.JDApi;
import fm.qingting.qtradio.jd.data.JDApi.ErrorCode;
import fm.qingting.qtradio.jd.data.JDApi.OnResponseListener;
import fm.qingting.qtradio.jd.data.Response;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.AdvertisementInfoNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.QTADLocation;
import fm.qingting.qtradio.model.QTLocation;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.notification.Constants;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ThirdTracker
{
  public static final String AndroidUA = "QingTing Mozilla/5.0 (Linux; U; Android 4.2.0; zh-cn; MB200 Build/GRJ22;) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
  private static ThirdTracker _instance;
  private static WebView webView = null;
  private int INTERVAL = 120;
  private long changeRecvJdTime = System.currentTimeMillis() / 1000L + 60L;
  private int mADIndex = 0;
  private String mADRegions;
  private int mAMIndex = 0;
  private Context mContext;
  private int mJDIndex = 0;
  private int mJDSeed = 1;
  private List<Integer> mLstADPercents;
  private List<String> mLstADUrls;
  private List<CommodityInfo> mLstJDAdv = null;
  private List<Integer> mLstMZPercents;
  private List<String> mLstMZUrls;
  private int mMZIndex = 0;
  private boolean trueIMEI = false;
  private qtWebViewClient webViewClient = new qtWebViewClient(null);

  public static ThirdTracker getInstance()
  {
    if (_instance == null)
      _instance = new ThirdTracker();
    return _instance;
  }

  private boolean inRegion(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.equalsIgnoreCase("CN"));
      Object localObject;
      label57: 
      do
      {
        do
        {
          return true;
          if (!InfoManager.getInstance().enableADVLocation())
            break label57;
          localObject = InfoManager.getInstance().getADVLocation();
          if (localObject == null)
            break;
          if (((QTADLocation)localObject).regionCode == null)
            break label57;
        }
        while (paramString.contains(((QTADLocation)localObject).regionCode));
        return false;
        return false;
        localObject = InfoManager.getInstance().getCurrentLocation();
        if (localObject == null)
          break;
        localObject = ((QTLocation)localObject).getRegionCode();
      }
      while ((localObject != null) && (paramString.contains((CharSequence)localObject)));
    }
    return false;
  }

  private void initWebView()
  {
    if ((this.mContext == null) || (webView != null))
      return;
    webView = new WebView(this.mContext);
    WebSettings localWebSettings = webView.getSettings();
    if (localWebSettings != null)
    {
      localWebSettings.setJavaScriptEnabled(true);
      localWebSettings.setUserAgentString("QingTing Mozilla/5.0 (Linux; U; Android 4.2.0; zh-cn; MB200 Build/GRJ22;) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
      localWebSettings.setCacheMode(2);
      localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }
    webView.setWebChromeClient(new WebChromeClient()
    {
      public boolean onJsAlert(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
      {
        return true;
      }
    });
    webView.setHorizontalScrollBarEnabled(false);
    webView.setVerticalScrollBarEnabled(false);
    webView.setWebViewClient(this.webViewClient);
  }

  private boolean isInRegion()
  {
    if ((this.mADRegions != null) && (!this.mADRegions.equalsIgnoreCase("")) && (!this.mADRegions.equalsIgnoreCase("#")))
    {
      if (this.mADRegions.equalsIgnoreCase("all"));
      String str;
      do
      {
        return true;
        str = InfoManager.getInstance().getCurrentRegion();
        if (str == null)
          return false;
      }
      while (this.mADRegions.contains(str));
    }
    return false;
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

  private void sendLog(AdConfig paramAdConfig)
  {
    if (paramAdConfig != null);
  }

  public String buildThirdTrackerLog(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      String str = "" + "\"";
      str = str + InfoManager.getInstance().getDeviceId();
      str = str + "\"";
      str = str + ",";
      str = str + "\"";
      if (InfoManager.getInstance().getCurrentCity() != null);
      for (str = str + InfoManager.getInstance().getCurrentCity(); ; str = str + "")
      {
        str = str + "\"";
        str = str + ",";
        str = str + "\"";
        paramString = str + paramString;
        paramString = paramString + "\"";
        paramString = paramString + ",";
        paramString = paramString + "\"";
        paramString = paramString + String.valueOf(paramInt1);
        paramString = paramString + "\"";
        paramString = paramString + ",";
        paramString = paramString + "\"";
        paramString = paramString + String.valueOf(paramInt2);
        paramString = paramString + "\"";
        paramString = paramString + ",";
        paramString = paramString + "\"";
        paramString = paramString + String.valueOf(paramInt3);
        paramString = paramString + "\"";
        paramString = paramString + ",";
        paramString = paramString + "\"";
        paramString = paramString + "0";
        paramString = paramString + "\"";
        paramString = paramString + ",";
        paramString = paramString + "\"";
        paramString = paramString + String.valueOf(paramInt4);
        paramString = paramString + "\"";
        return paramString + "\n";
      }
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  public void changeJD()
  {
    if (InfoManager.getInstance().hasWifi())
    {
      if ((InfoManager.getInstance().enableJDCity()) && (this.mJDSeed > 0))
      {
        if ((this.mLstJDAdv != null) && (this.mJDIndex < this.mLstJDAdv.size()))
          return;
        this.changeRecvJdTime = (System.currentTimeMillis() / 1000L + RangeRandom.Random(this.INTERVAL) + 1L);
        this.mJDSeed -= 1;
        sendThirdTrackLog("ThirdAdvF", "1", 0, InfoManager.getInstance().getJdAdPosition(), Constants.ADV_IMPRESSION, 0);
        JDApi.request(new JDApi.OnResponseListener()
        {
          public void onResponse(Response paramAnonymousResponse)
          {
            if (paramAnonymousResponse.getErrorCode() == JDApi.ErrorCode.SUCCESS)
            {
              ThirdTracker.access$102(ThirdTracker.this, (ArrayList)paramAnonymousResponse.getData());
              ThirdTracker.access$202(ThirdTracker.this, 0);
              ThirdTracker.access$302(ThirdTracker.this, false);
              InfoManager.getInstance().root().setInfoUpdate(12);
            }
          }
        });
        return;
      }
      this.changeRecvJdTime = 0L;
      return;
    }
    this.changeRecvJdTime = 0L;
  }

  public List<CommodityInfo> getJDAdv()
  {
    return this.mLstJDAdv;
  }

  public long getJDAdvTime()
  {
    return this.changeRecvJdTime;
  }

  public void init(Context paramContext)
  {
    if (paramContext == null)
      return;
    this.mContext = paramContext;
  }

  public String macroReplace(String paramString)
  {
    String str1 = "";
    try
    {
      if (InfoManager.getInstance().getCurrentLocation() != null)
        str1 = InfoManager.getInstance().getCurrentLocation().ip;
      String str2 = URLEncoder.encode("蜻蜓FM", "UTF-8");
      String str3 = DeviceInfo.getDeviceIMEI(InfoManager.getInstance().getContext());
      str1 = paramString.replace("__OS__", "0").replace("__IP__", str1).replace("__APP__", str2).replace("__IMEI__", MD5Util.md5(str3));
      return str1;
    }
    catch (Exception localException)
    {
    }
    return paramString;
  }

  public void monitorMMA(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
    {
      initWebView();
      paramString = macroReplace(paramString);
      webView.loadUrl(paramString);
    }
  }

  public void sendThirdTrackLog(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!InfoManager.getInstance().enableAdTrack());
    do
    {
      return;
      paramString2 = buildThirdTrackerLog(paramString2, paramInt1, paramInt2 + 1, paramInt3, paramInt4);
    }
    while ((paramString2 == null) || (paramString2.equalsIgnoreCase("")));
    LogModule.getInstance().send(paramString1, paramString2);
  }

  public void setADPercent(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")) && (!paramString.equalsIgnoreCase("#")))
    {
      this.mLstADPercents = new ArrayList();
      paramString = paramString.split(";;");
      if (paramString != null)
        break label46;
    }
    while (true)
    {
      return;
      label46: int i = 0;
      while (i < paramString.length)
      {
        this.mLstADPercents.add(Integer.valueOf(paramString[i]));
        i += 1;
      }
    }
  }

  public void setADUrl(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")) && (!paramString.equalsIgnoreCase("#")))
    {
      this.mLstADUrls = new ArrayList();
      paramString = paramString.split(";;");
      if (paramString != null)
        break label46;
    }
    while (true)
    {
      return;
      label46: int i = 0;
      while (i < paramString.length)
      {
        this.mLstADUrls.add(paramString[i]);
        i += 1;
      }
    }
  }

  public void setJDAdv(List<CommodityInfo> paramList, boolean paramBoolean)
  {
    if (paramList == null)
      return;
    this.changeRecvJdTime = (RangeRandom.Random(this.INTERVAL) + 1L + System.currentTimeMillis() / 1000L);
    this.trueIMEI = paramBoolean;
    this.mLstJDAdv = paramList;
  }

  public void setJDSeed(int paramInt)
  {
    this.mJDSeed = paramInt;
  }

  public void setMZPercent(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")) && (!paramString.equalsIgnoreCase("#")))
    {
      this.mLstMZPercents = new ArrayList();
      paramString = paramString.split(";;");
      if (paramString != null)
        break label46;
    }
    while (true)
    {
      return;
      label46: int i = 0;
      while (i < paramString.length)
      {
        this.mLstMZPercents.add(Integer.valueOf(paramString[i]));
        i += 1;
      }
    }
  }

  public void setMZUrl(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")) && (!paramString.equalsIgnoreCase("#")))
    {
      this.mLstMZUrls = new ArrayList();
      paramString = paramString.split(";;");
      if (paramString != null)
        break label46;
    }
    while (true)
    {
      return;
      label46: int i = 0;
      while (i < paramString.length)
      {
        this.mLstMZUrls.add(paramString[i]);
        i += 1;
      }
    }
  }

  public void setThirdAdv(ActivityNode paramActivityNode, int paramInt)
  {
    int k;
    int i;
    int j;
    try
    {
      if ((InfoManager.getInstance().hasWifi()) && (paramActivityNode != null) && (InfoManager.getInstance().enableAirWave()) && (InfoManager.getInstance().enableAirWaveCity()))
      {
        initWebView();
        if (webView != null)
        {
          k = InfoManager.getInstance().getAirWaveShow();
          if ((paramActivityNode.imageTracking != null) && (k > 0))
          {
            i = 0;
            break label271;
            while (j < paramActivityNode.imageTracking.size())
            {
              JDApi.feedback(new JDApi.OnResponseListener()
              {
                public void onResponse(Response paramAnonymousResponse)
                {
                }
              }
              , (String)paramActivityNode.imageTracking.get(j));
              j += 1;
            }
            label114: sendThirdTrackLog("ThirdAdv", "3", 1, 7, Constants.ADV_IMPRESSION, paramInt);
            QTMSGManage.getInstance().sendStatistcsMessage("ThirdAdv", "impression");
          }
          if (((paramActivityNode.clickTracking != null) || (paramActivityNode.contentUrl != null)) && (RangeRandom.random(InfoManager.getInstance().getAirWaveClick() / 1000.0D)))
          {
            if (paramActivityNode.clickTracking != null)
            {
              i = 0;
              while (i < paramActivityNode.clickTracking.size())
              {
                webView.loadUrl((String)paramActivityNode.clickTracking.get(i));
                i += 1;
              }
            }
            if (paramActivityNode.contentUrl != null)
              webView.loadUrl(paramActivityNode.contentUrl);
            sendThirdTrackLog("ThirdAdv", "3", 1, 7, Constants.ADV_CLICK, paramInt);
            QTMSGManage.getInstance().sendStatistcsMessage("ThirdAdv", "click");
          }
        }
      }
      return;
    }
    catch (Exception paramActivityNode)
    {
      return;
    }
    while (true)
    {
      label271: if (i >= k)
        break label114;
      j = 0;
      break;
      i += 1;
    }
  }

  public void setThirdAdvBaidu(ActivityNode paramActivityNode, int paramInt)
  {
    int k;
    int i;
    int j;
    try
    {
      if ((InfoManager.getInstance().hasWifi()) && (paramActivityNode != null) && (InfoManager.getInstance().enableAirWave()) && (InfoManager.getInstance().enableAirWaveCity()))
      {
        initWebView();
        if (webView != null)
        {
          k = InfoManager.getInstance().getAirWaveShow();
          if ((paramActivityNode.imageTracking != null) && (k > 0))
          {
            i = 0;
            break label271;
            while (j < paramActivityNode.imageTracking.size())
            {
              JDApi.feedback(new JDApi.OnResponseListener()
              {
                public void onResponse(Response paramAnonymousResponse)
                {
                }
              }
              , (String)paramActivityNode.imageTracking.get(j));
              j += 1;
            }
            label114: sendThirdTrackLog("ThirdAdv", "4", 1, 7, Constants.ADV_IMPRESSION, paramInt);
            QTMSGManage.getInstance().sendStatistcsMessage("BDAdv", "impression");
          }
          if (((paramActivityNode.clickTracking != null) || (paramActivityNode.contentUrl != null)) && (RangeRandom.random(InfoManager.getInstance().getAirWaveClick() / 1000.0D)))
          {
            if (paramActivityNode.clickTracking != null)
            {
              i = 0;
              while (i < paramActivityNode.clickTracking.size())
              {
                webView.loadUrl((String)paramActivityNode.clickTracking.get(i));
                i += 1;
              }
            }
            if (paramActivityNode.contentUrl != null)
              webView.loadUrl(paramActivityNode.contentUrl);
            sendThirdTrackLog("ThirdAdv", "4", 1, 7, Constants.ADV_CLICK, paramInt);
            QTMSGManage.getInstance().sendStatistcsMessage("BDAdv", "click");
          }
        }
      }
      return;
    }
    catch (Exception paramActivityNode)
    {
      return;
    }
    while (true)
    {
      label271: if (i >= k)
        break label114;
      j = 0;
      break;
      i += 1;
    }
  }

  public void setTrackerRegions(String paramString)
  {
    this.mADRegions = paramString;
  }

  public void start()
  {
    if (isInRegion())
    {
      startAD();
      startMZ();
    }
  }

  public void startAD()
  {
    if ((this.mLstADUrls != null) && (this.mLstADPercents != null) && (InfoManager.getInstance().hasWifi()) && (this.mADIndex < this.mLstADUrls.size()))
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (this.mLstADPercents != null)
      {
        bool1 = bool2;
        if (this.mADIndex < this.mLstADPercents.size())
          bool1 = RangeRandom.random(((Integer)this.mLstADPercents.get(this.mADIndex)).intValue() / 100.0D);
      }
      if ((bool1) && (!((String)this.mLstADUrls.get(this.mADIndex)).equalsIgnoreCase("#")))
      {
        if (this.mADIndex % 2 != 0)
          break label210;
        Countly.sharedInstance().onExpose((String)this.mLstADUrls.get(this.mADIndex));
      }
    }
    while (true)
    {
      QTMSGManage.getInstance().sendStatistcsMessage("ZeusTracker", "ad:" + (String)this.mLstADUrls.get(this.mADIndex));
      this.mADIndex += 1;
      return;
      label210: Countly.sharedInstance().onClick((String)this.mLstADUrls.get(this.mADIndex));
    }
  }

  public void startAM()
  {
    if ((!InfoManager.getInstance().hasWifi()) || ((InfoManager.getInstance().enableADVLocation()) && (InfoManager.getInstance().getADVLocation() == null)))
      return;
    List localList = InfoManager.getInstance().root().mAdvertisementInfoNode.getADAMConfigs();
    int i;
    boolean bool;
    if ((localList != null) && (this.mAMIndex < localList.size()))
      if (inRegion(((AdConfig)localList.get(this.mAMIndex)).mRegions))
      {
        i = ((AdConfig)localList.get(this.mAMIndex)).percent;
        bool = RangeRandom.random(i / 1000.0D);
        if (i <= 1000)
          break label571;
        i /= 1000;
      }
    while (true)
    {
      String str1 = ((AdConfig)localList.get(this.mAMIndex)).mEventType;
      String str2 = ((AdConfig)localList.get(this.mAMIndex)).mAdMasterUrl;
      String str3 = ((AdConfig)localList.get(this.mAMIndex)).mMiaozhenUrl;
      String str4 = ((AdConfig)localList.get(this.mAMIndex)).mCustomerUrl;
      String str5 = ((AdConfig)localList.get(this.mAMIndex)).mMMAUrl;
      if (bool)
      {
        int j = 0;
        if (j < i)
        {
          if ((str2 != null) && (!str2.equalsIgnoreCase("")))
          {
            if ((str1 == null) || (!str1.equalsIgnoreCase("click")))
              break label525;
            Countly.sharedInstance().onClick(str2);
          }
          while (true)
          {
            QTMSGManage.getInstance().sendStatistcsMessage("ThirdTracker1", "ad:" + str2);
            String str6;
            if ((str3 != null) && (!str3.equalsIgnoreCase("")))
            {
              str6 = macroReplace(str3);
              MZMonitor.adTrack(InfoManager.getInstance().getContext(), str6);
              QTMSGManage.getInstance().sendStatistcsMessage("ThirdTracker", "mz:" + str3);
            }
            if ((str4 != null) && (!str4.equalsIgnoreCase("")))
            {
              initWebView();
              if (webView != null)
              {
                str6 = macroReplace(str4);
                webView.loadUrl(str6);
                QTMSGManage.getInstance().sendStatistcsMessage("ThirdTracker", "zs:" + str4);
              }
            }
            if ((str5 != null) && (!str5.equalsIgnoreCase("")))
            {
              initWebView();
              if (webView != null)
              {
                str6 = macroReplace(str5);
                webView.loadUrl(str6);
                QTMSGManage.getInstance().sendStatistcsMessage("ThirdTracker", "zs:" + str5);
              }
            }
            sendLog((AdConfig)localList.get(this.mAMIndex));
            j += 1;
            break;
            label525: Countly.sharedInstance().onExpose(str2);
          }
        }
      }
      this.mAMIndex += 1;
      return;
      if (webView == null)
        break;
      try
      {
        webView.removeAllViews();
        webView.destroy();
        webView = null;
        return;
      }
      catch (Exception localException)
      {
        return;
      }
      label571: i = 1;
    }
  }

  public void startMZ()
  {
    if ((this.mLstMZUrls != null) && (this.mLstMZPercents != null) && (InfoManager.getInstance().hasWifi()) && (this.mMZIndex < this.mLstMZUrls.size()))
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (this.mLstMZPercents != null)
      {
        bool1 = bool2;
        if (this.mMZIndex < this.mLstMZPercents.size())
          bool1 = RangeRandom.random(((Integer)this.mLstMZPercents.get(this.mMZIndex)).intValue() / 100.0D);
      }
      if (bool1)
      {
        String str = macroReplace((String)this.mLstMZUrls.get(this.mMZIndex));
        MZMonitor.adTrack(InfoManager.getInstance().getContext(), str);
        QTMSGManage.getInstance().sendStatistcsMessage("ZeusTracker", "mz:" + (String)this.mLstMZUrls.get(this.mMZIndex));
      }
      this.mMZIndex += 1;
    }
  }

  public void trackJD()
  {
    if ((InfoManager.getInstance().hasWifi()) && (this.mLstJDAdv != null) && (this.mLstJDAdv.size() > 0) && (this.mJDIndex < this.mLstJDAdv.size()) && (InfoManager.getInstance().enableJDCity()))
    {
      int j = InfoManager.getInstance().getJDShow();
      int i = 0;
      while (i < j)
      {
        JDApi.feedback(null, ((CommodityInfo)this.mLstJDAdv.get(this.mJDIndex)).getTrackUrl());
        QTMSGManage.getInstance().sendStatistcsMessage("jdimpression", "fake");
        i += 1;
      }
      if ((RangeRandom.random(InfoManager.getInstance().getJDClick() / 1000.0D)) && (this.trueIMEI))
      {
        initWebView();
        if (webView != null)
        {
          webView.loadUrl(((CommodityInfo)this.mLstJDAdv.get(this.mJDIndex)).getShopUrl());
          QTMSGManage.getInstance().sendStatistcsMessage("jdclick", "fake");
        }
        sendThirdTrackLog("ThirdAdvF", "1", 0, InfoManager.getInstance().getJdAdPosition(), Constants.ADV_CLICK, 0);
      }
      this.mJDIndex += 1;
    }
  }

  private class qtWebViewClient extends WebViewClient
  {
    private qtWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      return false;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.ThirdTracker
 * JD-Core Version:    0.6.2
 */