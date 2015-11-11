package fm.qingting.qtradio.helper;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fm.qingting.downloadnew.HttpDownloadHelper;
import fm.qingting.qtradio.voice.ThreadHelper;
import fm.qingting.utils.MD5Util;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class QuickDownload
{
  private static String[][] APP_INFO = { { "cn.goapk.market", "http://m.anzhi.com/redirect.php?do=dlapk&puid=1099", "Anzhi.apk", "anzhimarket://details?id=fm.qingting.qtradio&flag=1" }, { "com.baidu.appsearch", "http://downpack.baidu.com/appsearch_AndroidPhone_1010523e.apk", "baidu.apk", "http://m.baidu.com/api?action=update&from=1010523e&token=qingtingfm&type=app" }, { "com.pp.assistant", "http://ucan.25pp.com/PPAssistant_PM_2255.apk", "ppassistant.apk", "com.pp.intent.action.INVOKE" } };
  private int mAppIndex;
  private AppInfo mAppInfo;
  private String mChannel;
  private Context mContext;
  private String mParamString;

  public QuickDownload(Context paramContext)
  {
    this.mContext = paramContext;
    this.mChannel = "anzhi";
    this.mAppIndex = 0;
    try
    {
      paramContext = new JSONArray();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("package", this.mContext.getPackageName());
      localJSONObject.put("versioncode", Integer.valueOf(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode));
      localJSONObject.put("signmd5", MD5Util.getSignMD5(this.mContext));
      localJSONObject.put("md5", MD5Util.getFileMD5(this.mContext));
      paramContext.add(localJSONObject);
      this.mParamString = paramContext.toString();
      return;
    }
    catch (Exception paramContext)
    {
      this.mParamString = "";
      paramContext.printStackTrace();
    }
  }

  private void download()
  {
    Handler localHandler = new Handler();
    HttpDownloadHelper.deleteFile(APP_INFO[this.mAppIndex][2]);
    new HttpDownloadHelper(this.mContext, localHandler, APP_INFO[this.mAppIndex][1], APP_INFO[this.mAppIndex][2], false).start();
  }

  private void formatXML(String paramString)
  {
    if (paramString == null)
      return;
    while (true)
    {
      try
      {
        paramString = new SAXBuilder().build(new InputSource(new StringReader(paramString))).getRootElement().getChild("result").getChild("apps").getChild("app");
        this.mAppInfo = new AppInfo();
        this.mAppInfo.packageName = this.mContext.getPackageName();
        this.mAppInfo.sname = paramString.getChildText("sname");
        this.mAppInfo.versionCode = paramString.getChildText("versioncode");
        this.mAppInfo.downloadUrl = paramString.getChildText("download_url");
        this.mAppInfo.md5 = paramString.getChildText("signmd5");
        this.mAppInfo.versionName = paramString.getChildText("versionname");
        this.mAppInfo.iconUrl = paramString.getChildText("icon");
        this.mAppInfo.updatetime = paramString.getChildText("updatetime");
        this.mAppInfo.size = paramString.getChildText("size");
        this.mAppInfo.changelog = paramString.getChildText("changelog");
        if (paramString.getChildText("patch") != null)
        {
          this.mAppInfo.patchDownloadUrl = paramString.getChildText("patch");
          if (paramString.getChildText("patch_size") == null)
            break;
          this.mAppInfo.patchSzie = paramString.getChildText("patch_size");
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      this.mAppInfo.patchDownloadUrl = "";
    }
    this.mAppInfo.patchSzie = "0";
  }

  private String postRequest(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = (HttpURLConnection)new URL(paramString1).openConnection();
      paramString1.setDoOutput(true);
      paramString1.setDoInput(true);
      paramString1.setRequestMethod("POST");
      paramString1.setUseCaches(false);
      paramString1.connect();
      Object localObject = new DataOutputStream(paramString1.getOutputStream());
      ((DataOutputStream)localObject).write(paramString2.getBytes());
      ((DataOutputStream)localObject).flush();
      ((DataOutputStream)localObject).close();
      localObject = new BufferedReader(new InputStreamReader(paramString1.getInputStream()));
      String str;
      for (paramString1 = ""; ; paramString1 = paramString1 + str)
      {
        str = ((BufferedReader)localObject).readLine();
        paramString2 = paramString1;
        if (str == null)
          break;
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      paramString2 = null;
    }
    return paramString2;
  }

  private void update()
  {
    Intent localIntent1;
    if (this.mChannel.equalsIgnoreCase("anzhi"))
    {
      localIntent1 = new Intent();
      localIntent1.addFlags(268435456);
      localIntent1.setData(Uri.parse(APP_INFO[this.mAppIndex][3]));
      this.mContext.startActivity(localIntent1);
      OnlineUpdateHelper.getInstance().sendEventMessage("updateQTApp", this.mChannel);
    }
    do
    {
      return;
      if (this.mChannel.equalsIgnoreCase("baidu"))
      {
        if (this.mAppInfo != null)
          while (true)
          {
            Bundle localBundle;
            try
            {
              localIntent1 = new Intent("com.baidu.appsearch.extinvoker.LAUNCH");
              localIntent1.addFlags(32);
              localIntent1.putExtra("id", this.mContext.getPackageName());
              localIntent1.putExtra("backop", "0");
              localIntent1.putExtra("func", "11");
              localBundle = new Bundle();
              localBundle.putString("sname", this.mAppInfo.sname);
              localBundle.putString("packagename", this.mAppInfo.packageName);
              localBundle.putInt("versioncode", Integer.valueOf(this.mAppInfo.versionCode).intValue());
              localBundle.putString("downurl", this.mAppInfo.downloadUrl);
              localBundle.putString("signmd5", this.mAppInfo.md5);
              localBundle.putString("tj", this.mAppInfo.md5 + this.mAppInfo.sname);
              localBundle.putString("versionname", this.mAppInfo.versionName);
              localBundle.putString("fparam", "lc");
              localBundle.putString("iconurl", this.mAppInfo.iconUrl);
              localBundle.putString("updatetime", this.mAppInfo.updatetime);
              localBundle.putString("size", this.mAppInfo.size);
              localBundle.putString("changelog", this.mAppInfo.changelog);
              localBundle.putString("patch_url", this.mAppInfo.patchDownloadUrl);
              if (this.mAppInfo != null)
              {
                localBundle.putLong("patch_size", Long.valueOf(this.mAppInfo.patchSzie).longValue());
                localIntent1.putExtra("extra_client_downloadinfo", localBundle);
                this.mContext.startActivity(localIntent1);
                OnlineUpdateHelper.getInstance().sendEventMessage("updateQTApp", this.mChannel);
                return;
              }
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
              return;
            }
            localBundle.putLong("patch_size", 0L);
          }
        ThreadHelper.getInstance().execute(new Runnable()
        {
          public void run()
          {
            String str = QuickDownload.this.postRequest(QuickDownload.APP_INFO[1][3], QuickDownload.this.mParamString);
            if (str != null)
            {
              QuickDownload.this.formatXML(str);
              if (QuickDownload.this.mAppInfo != null)
                QuickDownload.this.update();
            }
          }
        });
        return;
      }
    }
    while (!this.mChannel.equalsIgnoreCase("ppzhushou"));
    Intent localIntent2 = new Intent("com.pp.intent.action.INVOKE");
    localIntent2.putExtra("pd", "incr_update");
    localIntent2.putExtra("key_app_id", 27325);
    localIntent2.putExtra("key_app_type", 0);
    localIntent2.putExtra("key_package_name", "fm.qingting.qtradio");
    this.mContext.startService(localIntent2);
  }

  private boolean updateFromChannel()
  {
    boolean bool2 = true;
    String str = APP_INFO[this.mAppIndex][0];
    PackageManager localPackageManager = this.mContext.getPackageManager();
    boolean bool1;
    try
    {
      localPackageManager.getPackageInfo(str, 1);
      if (this.mChannel.equalsIgnoreCase("baidu"))
      {
        if (localPackageManager.getPackageInfo(str, 0).versionCode <= 16782633)
          break label106;
        return true;
      }
      bool1 = bool2;
      if (this.mChannel.equalsIgnoreCase("ppzhushou"))
      {
        int i = localPackageManager.getPackageInfo(str, 0).versionCode;
        bool1 = bool2;
        if (i <= 303)
          return false;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      bool1 = false;
    }
    return bool1;
    label106: return false;
  }

  public void quickDownload()
  {
    if (updateFromChannel())
    {
      update();
      return;
    }
    download();
    OnlineUpdateHelper.getInstance().sendEventMessage("updateChannel", this.mChannel);
  }

  public void setChannel(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    do
    {
      return;
      this.mChannel = paramString;
      if (this.mChannel.equalsIgnoreCase("anzhi"))
      {
        this.mAppIndex = 0;
        return;
      }
      if (this.mChannel.equalsIgnoreCase("baidu"))
      {
        this.mAppIndex = 1;
        return;
      }
    }
    while (!this.mChannel.equalsIgnoreCase("ppzhushou"));
    this.mAppIndex = 2;
  }

  class AppInfo
  {
    public String changelog;
    public String downloadUrl;
    public String iconUrl;
    public String md5;
    public String packageName;
    public String patchDownloadUrl;
    public String patchSzie;
    public String size;
    public String sname;
    public String updatetime;
    public String versionCode;
    public String versionName;

    AppInfo()
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.helper.QuickDownload
 * JD-Core Version:    0.6.2
 */