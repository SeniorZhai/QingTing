package fm.qingting.qtradio.helper;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.downloadnew.HttpDownloadHelper;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.SharedCfg;
import java.util.ArrayList;
import java.util.Iterator;

public class OnlineUpdateHelper
{
  private static OnlineUpdateHelper mInstance;
  private Context mContext = InfoManager.getInstance().getContext();
  private String mDownloadUrl;
  private boolean mHasUpdate = false;
  private String mLatestVersion = "";
  private ArrayList<OnUpdateListener> mListeners;
  private String mMessage;
  private boolean mNeedQuickDownload = false;
  private QuickDownload mQuickDownload = new QuickDownload(this.mContext);

  private OnlineUpdateHelper()
  {
    checkUpdate();
  }

  private void checkUpdate()
  {
    String str2 = SharedCfg.getInstance().getLatestVersion();
    String str3 = this.mContext.getResources().getString(2131492874);
    long l = SharedCfg.getInstance().getUpgradeTime();
    this.mMessage = MobclickAgent.getConfigParams(this.mContext, "updateMessage");
    this.mLatestVersion = MobclickAgent.getConfigParams(this.mContext, "latestVersion");
    this.mDownloadUrl = MobclickAgent.getConfigParams(this.mContext, "onlineUpdateDownloadUrl");
    this.mQuickDownload.setChannel(MobclickAgent.getConfigParams(this.mContext, "quickChannel"));
    if (this.mLatestVersion == null)
      this.mLatestVersion = "";
    if ((this.mDownloadUrl == null) || (this.mDownloadUrl.equalsIgnoreCase("")))
      this.mDownloadUrl = "http://qingting.fm/app/download";
    String str1 = str2;
    if (str2 == null)
      str1 = "";
    if (compareVersion(this.mLatestVersion, str3))
      this.mHasUpdate = true;
    if (this.mHasUpdate)
    {
      boolean bool = compareVersion(this.mLatestVersion, str1);
      if (bool)
        SharedCfg.getInstance().setLatestVersion(this.mLatestVersion);
      if ((l < System.currentTimeMillis()) || (bool))
      {
        SharedCfg.getInstance().setUpgradeTime(System.currentTimeMillis() + 604800000L);
        if ((InfoManager.getInstance().hasWifi()) && (!SharedCfg.getInstance().isNewUser()))
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              OnlineUpdateHelper.this.showUpgradeAlert();
            }
          }
          , 1000L);
      }
    }
  }

  private boolean compareVersion(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return true;
    while (true)
    {
      int i;
      try
      {
        paramString1 = paramString1.split("\\.");
        paramString2 = paramString2.split("\\.");
        int j = Math.min(paramString1.length, paramString2.length);
        i = 0;
        if (i < j)
        {
          int k = Integer.parseInt(paramString1[i]) - Integer.parseInt(paramString2[i]);
          if (k > 0)
            break;
          if (k >= 0)
            break label86;
          return false;
        }
        i = paramString1.length;
        j = paramString2.length;
        if (i > j)
          break;
        return false;
      }
      catch (Exception paramString1)
      {
        return true;
      }
      label86: i += 1;
    }
  }

  private void download(String paramString1, String paramString2)
  {
    Handler localHandler = new Handler();
    new HttpDownloadHelper(this.mContext, localHandler, paramString1, paramString2, false).start();
  }

  public static OnlineUpdateHelper getInstance()
  {
    if (mInstance == null)
      mInstance = new OnlineUpdateHelper();
    return mInstance;
  }

  public void addListener(OnUpdateListener paramOnUpdateListener)
  {
    if (paramOnUpdateListener == null)
      return;
    if (this.mListeners == null)
      this.mListeners = new ArrayList();
    this.mListeners.add(paramOnUpdateListener);
  }

  public void dispatchUpdate()
  {
    if (this.mListeners == null);
    while (true)
    {
      return;
      try
      {
        Iterator localIterator = this.mListeners.iterator();
        while (localIterator.hasNext())
          ((OnUpdateListener)localIterator.next()).onUpdate();
      }
      catch (Exception localException)
      {
      }
    }
  }

  public void download()
  {
    HttpDownloadHelper.deleteFile("QTRadioUpgrade.apk");
    download(this.mDownloadUrl, "QTRadioUpgrade.apk");
  }

  public String getLatestVersion()
  {
    return this.mLatestVersion;
  }

  public boolean hasUpdate()
  {
    return this.mHasUpdate;
  }

  public boolean needQuickDownload()
  {
    return this.mNeedQuickDownload;
  }

  public void quickDownload()
  {
    this.mQuickDownload.quickDownload();
  }

  public void sendEventMessage(String paramString)
  {
    MobclickAgent.onEvent(InfoManager.getInstance().getContext(), paramString);
    TCAgent.onEvent(InfoManager.getInstance().getContext(), paramString);
  }

  public void sendEventMessage(String paramString1, String paramString2)
  {
    MobclickAgent.onEvent(InfoManager.getInstance().getContext(), paramString1, paramString2);
    TCAgent.onEvent(InfoManager.getInstance().getContext(), paramString1, paramString2);
  }

  public void setNeedQuickDownload(boolean paramBoolean)
  {
    this.mNeedQuickDownload = paramBoolean;
  }

  public void showUpgradeAlert()
  {
    if (!this.mHasUpdate)
      return;
    EventDispacthManager.getInstance().dispatchAction("onlineUpgrade", this.mMessage);
    sendEventMessage("updateDialog");
  }

  public class EventType
  {
    public static final String UPDATE_CHANNEL = "updateChannel";
    public static final String UPDATE_DIALOG = "updateDialog";
    public static final String UPDATE_DOWNLOAD = "updateDownload";
    public static final String UPDATE_LIGHT_DOWNLOAD = "updateLightDownload";
    public static final String UPDATE_QTApp = "updateQTApp";
    public static final String UPDATE_WAIT = "updateWait";

    public EventType()
    {
    }
  }

  public static abstract interface OnUpdateListener
  {
    public abstract void onUpdate();
  }

  private class QTAppInfo
  {
    private static final String DOWNLOAD_PATH = "QTRadioUpgrade.apk";
    private static final String DOWNLOAD_URL = "http://qingting.fm/app/download";
    private static final long UPGRADE_TIME_STAMP = 604800000L;

    private QTAppInfo()
    {
    }
  }

  private class UmengParamType
  {
    private static final String LATEST_VERSION = "latestVersion";
    private static final String MESSAGE = "updateMessage";
    private static final String ONLINE_UPDATE_DOWNLOAD_URL = "onlineUpdateDownloadUrl";
    private static final String QUICK_CHANNEL = "quickChannel";

    private UmengParamType()
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.helper.OnlineUpdateHelper
 * JD-Core Version:    0.6.2
 */