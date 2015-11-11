package com.alipay.mobilesecuritysdk.deviceID;

import HttpUtils.HttpFetcher;
import android.content.Context;
import android.os.Environment;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceIdModel
{
  public static final String PREFS_NAME = "profiles";
  public static final String PRIVATE_NAME = "deviceid";
  public static final String SERVICEID = "deviceFingerprint";
  public static final String VER = "1";
  public static final String mApdtk = "apdtk";
  public static final String mAppId = "appId";
  public static final String mCheckCode = "checkcode";
  public static final String mDeviceId = "deviceId";
  public static final String mDeviceInfo = "deviceInfo";
  public static final String mPriDeviceId = "priDeviceId";
  public static final String mRule = "rule";
  public static final String mah1 = "AH1";
  public static final String mah10 = "AH10";
  public static final String mah2 = "AH2";
  public static final String mah3 = "AH3";
  public static final String mah4 = "AH4";
  public static final String mah5 = "AH5";
  public static final String mah6 = "AH6";
  public static final String mah7 = "AH7";
  public static final String mah8 = "AH8";
  public static final String mah9 = "AH9";
  public static final String mas1 = "AS1";
  public static final String mas2 = "AS2";
  public static final String mas3 = "AS3";
  public static final String mas4 = "AS4";
  public static final String mtid = "AC1";
  public static final String mtime = "time";
  public static final String mutdid = "AC2";
  private DeviceMetaData dv = new DeviceMetaData();
  private Profile profile = new Profile();

  private void Log(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (CommonUtils.isBlank(this.dv.getMtid()))
      localArrayList.add(this.dv.getMtid().substring(0, 20));
    if (CommonUtils.isBlank(this.dv.getMutdid()))
      localArrayList.add(this.dv.getMutdid().substring(0, 20));
    if (CommonUtils.isBlank(this.dv.getMappId()))
      localArrayList.add(this.dv.getMappId().substring(0, 20));
    localArrayList.add(paramString);
    LOG.logMessage(localArrayList);
  }

  private String getCheckCodeString()
  {
    Object localObject2;
    if (CommonUtils.isBlank(this.dv.getMrule()))
    {
      localObject2 = null;
      return localObject2;
    }
    while (true)
    {
      int i;
      try
      {
        JSONArray localJSONArray = new JSONObject(this.dv.getMrule()).getJSONArray("params");
        if (localJSONArray == null)
          return null;
        Object localObject1 = new String();
        i = 0;
        localObject2 = localObject1;
        if (i == localJSONArray.length())
          break;
        localObject2 = localObject1;
        if (localJSONArray.getString(i).equals("AC1"))
        {
          if (!CommonUtils.isBlank(this.dv.getMtid()))
            localObject2 = localObject1 + this.dv.getMtid();
        }
        else
        {
          localObject1 = localObject2;
          if (localJSONArray.getString(i).equals("AC2"))
          {
            if (CommonUtils.isBlank(this.dv.getMutdid()))
              continue;
            localObject1 = localObject2 + this.dv.getMutdid();
          }
          localObject2 = localObject1;
          if (localJSONArray.getString(i).equals("AH1"))
          {
            if (CommonUtils.isBlank(this.dv.getMah1()))
              continue;
            localObject2 = localObject1 + this.dv.getMah1();
          }
          localObject1 = localObject2;
          if (localJSONArray.getString(i).equals("AH2"))
          {
            if (CommonUtils.isBlank(this.dv.getMah2()))
              continue;
            localObject1 = localObject2 + this.dv.getMah2();
          }
          localObject2 = localObject1;
          if (localJSONArray.getString(i).equals("AH3"))
          {
            if (CommonUtils.isBlank(this.dv.getMah3()))
              continue;
            localObject2 = localObject1 + this.dv.getMah3();
          }
          localObject1 = localObject2;
          if (localJSONArray.getString(i).equals("AH4"))
          {
            if (CommonUtils.isBlank(this.dv.getMah4()))
              continue;
            localObject1 = localObject2 + this.dv.getMah4();
          }
          localObject2 = localObject1;
          if (localJSONArray.getString(i).equals("AH5"))
          {
            if (CommonUtils.isBlank(this.dv.getMah5()))
              continue;
            localObject2 = localObject1 + this.dv.getMah5();
          }
          localObject1 = localObject2;
          if (localJSONArray.getString(i).equals("AH6"))
          {
            if (CommonUtils.isBlank(this.dv.getMah6()))
              continue;
            localObject1 = localObject2 + this.dv.getMah6();
          }
          localObject2 = localObject1;
          if (localJSONArray.getString(i).equals("AH7"))
          {
            if (CommonUtils.isBlank(this.dv.getMah7()))
              continue;
            localObject2 = localObject1 + this.dv.getMah7();
          }
          localObject1 = localObject2;
          if (localJSONArray.getString(i).equals("AH8"))
          {
            if (CommonUtils.isBlank(this.dv.getMah8()))
              continue;
            localObject1 = localObject2 + this.dv.getMah8();
          }
          localObject2 = localObject1;
          if (localJSONArray.getString(i).equals("AH9"))
          {
            if (CommonUtils.isBlank(this.dv.getMah9()))
              continue;
            localObject2 = localObject1 + this.dv.getMah9();
          }
          localObject1 = localObject2;
          if (localJSONArray.getString(i).equals("AH10"))
          {
            if (CommonUtils.isBlank(this.dv.getMah10()))
              continue;
            localObject1 = localObject2 + this.dv.getMah10();
          }
          localObject2 = localObject1;
          if (localJSONArray.getString(i).equals("AS1"))
          {
            if (CommonUtils.isBlank(this.dv.getMas1()))
              continue;
            localObject2 = localObject1 + this.dv.getMas1();
          }
          localObject1 = localObject2;
          if (localJSONArray.getString(i).equals("AS2"))
          {
            if (CommonUtils.isBlank(this.dv.getMas2()))
              continue;
            localObject1 = localObject2 + this.dv.getMas2();
          }
          localObject2 = localObject1;
          if (localJSONArray.getString(i).equals("AS3"))
          {
            if (CommonUtils.isBlank(this.dv.getMas3()))
              continue;
            localObject2 = localObject1 + this.dv.getMas3();
          }
          localObject1 = localObject2;
          if (!localJSONArray.getString(i).equals("AS4"))
            break label1231;
          if (CommonUtils.isBlank(this.dv.getMas4()))
            continue;
          localObject1 = localObject2 + this.dv.getMas4();
          break label1231;
        }
        localObject2 = localObject1;
        continue;
        localObject1 = localObject2;
        continue;
        localObject2 = localObject1;
        continue;
        localObject1 = localObject2;
        continue;
        localObject2 = localObject1;
        continue;
        localObject1 = localObject2;
        continue;
        localObject2 = localObject1;
        continue;
        localObject1 = localObject2;
        continue;
        localObject2 = localObject1;
        continue;
        localObject1 = localObject2;
        continue;
        localObject2 = localObject1;
        continue;
        localObject1 = localObject2;
        continue;
        localObject2 = localObject1;
        continue;
        localObject1 = localObject2;
        continue;
        localObject2 = localObject1;
        continue;
        localObject1 = localObject2;
      }
      catch (JSONException localJSONException)
      {
        Log(LOG.getStackString(localJSONException));
        return null;
      }
      label1231: i += 1;
    }
  }

  private boolean hasDataInSdcard()
  {
    if (CommonUtils.isBlank(readDataFromSdCard()));
    while (readDataFromSdCard().length() <= 0)
      return false;
    return true;
  }

  private boolean hasDataInSettings()
  {
    if (CommonUtils.isBlank(readDataFromSettings()));
    while (readDataFromSettings().length() <= 0)
      return false;
    return true;
  }

  public boolean CheckPrivateData(Map<String, String> paramMap)
  {
    if ((paramMap == null) || (paramMap.size() < 0));
    while ((!paramMap.containsKey("deviceId")) || (!paramMap.containsKey("checkcode")) || (!paramMap.containsKey("apdtk")) || (!paramMap.containsKey("time")) || (!paramMap.containsKey("rule")))
      return false;
    return true;
  }

  public Map<String, Object> GetLocalInfo()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("deviceId", this.dv.getMdeviceId());
    localHashMap.put("priDeviceId", this.dv.getMpriDeviceId());
    localHashMap.put("appId", this.dv.getMappId());
    localHashMap.put("time", this.dv.getMtime());
    localHashMap.put("apdtk", this.dv.getMapdtk());
    return localHashMap;
  }

  public Map<String, String> GetPrivateData(Context paramContext)
  {
    paramContext = this.profile.GetDataFromSharedPre(paramContext.getSharedPreferences("profiles", 0), "deviceid");
    if (CommonUtils.isBlank(paramContext));
    do
    {
      return null;
      paramContext = SecurityUtils.decrypt(SecurityUtils.getSeed(), paramContext);
    }
    while (CommonUtils.isBlank(paramContext));
    return new Profile().getMap(paramContext);
  }

  public Map<String, String> GetShareData(Context paramContext)
  {
    return null;
  }

  public Map<String, Object> GetUploadInfo()
  {
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    if (!CommonUtils.isBlank(this.dv.getMah1()))
    {
      localHashMap2.put("AH1", this.dv.getMah1());
      if (CommonUtils.isBlank(this.dv.getMah2()))
        break label652;
      localHashMap2.put("AH2", this.dv.getMah2());
      label74: if (CommonUtils.isBlank(this.dv.getMah3()))
        break label667;
      localHashMap2.put("AH3", this.dv.getMah3());
      label103: if (CommonUtils.isBlank(this.dv.getMah4()))
        break label682;
      localHashMap2.put("AH4", this.dv.getMah4());
      label132: if (CommonUtils.isBlank(this.dv.getMah5()))
        break label697;
      localHashMap2.put("AH5", this.dv.getMah5());
      label161: if (CommonUtils.isBlank(this.dv.getMah6()))
        break label712;
      localHashMap2.put("AH6", this.dv.getMah6());
      label190: if (CommonUtils.isBlank(this.dv.getMah7()))
        break label727;
      localHashMap2.put("AH7", this.dv.getMah7());
      label219: if (CommonUtils.isBlank(this.dv.getMah8()))
        break label742;
      localHashMap2.put("AH8", this.dv.getMah8());
      label248: if (CommonUtils.isBlank(this.dv.getMah9()))
        break label757;
      localHashMap2.put("AH9", this.dv.getMah9());
      label277: if (CommonUtils.isBlank(this.dv.getMah10()))
        break label772;
      localHashMap2.put("AH10", this.dv.getMah10());
      label306: if (CommonUtils.isBlank(this.dv.getMas1()))
        break label787;
      localHashMap2.put("AS1", this.dv.getMas1());
      label335: if (CommonUtils.isBlank(this.dv.getMas2()))
        break label802;
      localHashMap2.put("AS2", this.dv.getMas2());
      label364: if (CommonUtils.isBlank(this.dv.getMas3()))
        break label817;
      localHashMap2.put("AS3", this.dv.getMas3());
      label393: if (CommonUtils.isBlank(this.dv.getMas4()))
        break label832;
      localHashMap2.put("AS4", this.dv.getMas4());
      label422: if (CommonUtils.isBlank(this.dv.getMtid()))
        break label847;
      localHashMap2.put("AC1", this.dv.getMtid());
      label451: if (CommonUtils.isBlank(this.dv.getMutdid()))
        break label862;
      localHashMap2.put("AC2", this.dv.getMutdid());
    }
    while (true)
    {
      localHashMap1.put("deviceInfo", localHashMap2);
      if (!CommonUtils.isBlank(this.dv.getMdeviceId()))
        localHashMap1.put("deviceId", this.dv.getMdeviceId());
      if (!CommonUtils.isBlank(this.dv.getMpriDeviceId()))
        localHashMap1.put("priDeviceId", this.dv.getMpriDeviceId());
      if (!CommonUtils.isBlank(this.dv.getMappId()))
        localHashMap1.put("appId", this.dv.getMappId());
      if (!CommonUtils.isBlank(this.dv.getMtime()))
        localHashMap1.put("time", this.dv.getMtime());
      if (!CommonUtils.isBlank(this.dv.getMapdtk()))
        localHashMap1.put("apdtk", this.dv.getMapdtk());
      return localHashMap1;
      localHashMap2.put("AH1", "");
      break;
      label652: localHashMap2.put("AH2", "");
      break label74;
      label667: localHashMap2.put("AH3", "");
      break label103;
      label682: localHashMap2.put("AH4", "");
      break label132;
      label697: localHashMap2.put("AH4", "");
      break label161;
      label712: localHashMap2.put("AH6", "");
      break label190;
      label727: localHashMap2.put("AH7", "");
      break label219;
      label742: localHashMap2.put("AH8", "");
      break label248;
      label757: localHashMap2.put("AH9", "");
      break label277;
      label772: localHashMap2.put("AH10", "");
      break label306;
      label787: localHashMap2.put("AS1", "");
      break label335;
      label802: localHashMap2.put("AS2", "");
      break label364;
      label817: localHashMap2.put("AS3", "");
      break label393;
      label832: localHashMap2.put("AS4", "");
      break label422;
      label847: localHashMap2.put("AC1", "");
      break label451;
      label862: localHashMap2.put("AC2", "");
    }
  }

  public void Init(Context paramContext, Map<String, String> paramMap)
  {
    CollectDeviceInfo localCollectDeviceInfo = CollectDeviceInfo.getInstance();
    LOG.init(paramContext);
    if (paramMap != null);
    try
    {
      if (paramMap.size() > 0)
      {
        if (!CommonUtils.isBlank((String)paramMap.get("tid")))
          this.dv.setMtid((String)paramMap.get("tid"));
        if (!CommonUtils.isBlank((String)paramMap.get("utdid")))
          this.dv.setMutdid((String)paramMap.get("utdid"));
      }
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getImei(paramContext)))
        this.dv.setMah1(localCollectDeviceInfo.getImei(paramContext));
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getImsi(paramContext)))
        this.dv.setMah2(localCollectDeviceInfo.getImsi(paramContext));
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getMacAddress(paramContext)))
        this.dv.setMah3(localCollectDeviceInfo.getMacAddress(paramContext));
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getCpuFre()))
        this.dv.setMah4(localCollectDeviceInfo.getCpuFre());
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getCpuNum()))
        this.dv.setMah5(localCollectDeviceInfo.getCpuNum());
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getBluMac()))
        this.dv.setMah6(localCollectDeviceInfo.getBluMac());
      if (!CommonUtils.isBlank(Long.toString(localCollectDeviceInfo.getTotalMemory())))
        this.dv.setMah7(Long.toString(localCollectDeviceInfo.getTotalMemory()));
      if (!CommonUtils.isBlank(Long.toString(localCollectDeviceInfo.getSDCardMemory())))
        this.dv.setMah8(Long.toString(localCollectDeviceInfo.getSDCardMemory()));
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getDeviceMx(paramContext)))
        this.dv.setMah9(localCollectDeviceInfo.getDeviceMx(paramContext));
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getPhoneModel()))
        this.dv.setMah10(localCollectDeviceInfo.getPhoneModel());
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getRomName()))
        this.dv.setMas1(localCollectDeviceInfo.getRomName());
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getSDKVer()))
        this.dv.setMas2(localCollectDeviceInfo.getSDKVer());
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getBandVer()))
        this.dv.setMas3(localCollectDeviceInfo.getBandVer());
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getOsVer()))
        this.dv.setMas4(localCollectDeviceInfo.getOsVer());
      if (!CommonUtils.isBlank(localCollectDeviceInfo.getPackageName(paramContext)))
        this.dv.setMappId(localCollectDeviceInfo.getPackageName(paramContext));
      paramContext = GetPrivateData(paramContext);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        if (!CommonUtils.isBlank((String)paramContext.get("apdtk")))
          this.dv.setMapdtk((String)paramContext.get("apdtk"));
        if (!CommonUtils.isBlank((String)paramContext.get("deviceId")))
          this.dv.setMpriDeviceId((String)paramContext.get("deviceId"));
        if (!CommonUtils.isBlank((String)paramContext.get("time")))
          this.dv.setMtime((String)paramContext.get("time"));
        if (!CommonUtils.isBlank((String)paramContext.get("rule")))
          this.dv.setMrule((String)paramContext.get("rule"));
      }
      if ((!CommonUtils.isBlank(readDataFromSettings())) && (readDataFromSettings().length() > 32))
      {
        this.dv.setMdeviceId(readDataFromSettings().substring(0, 32));
        return;
      }
      if ((!CommonUtils.isBlank(readDataFromSdCard())) && (readDataFromSdCard().length() > 32))
      {
        this.dv.setMdeviceId(readDataFromSdCard().substring(0, 32));
        return;
      }
    }
    catch (Exception paramContext)
    {
      Log(LOG.getStackString(paramContext));
    }
  }

  // ERROR //
  public String UpdateId(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 461	com/alipay/mobilesecuritysdk/deviceID/DeviceIdModel:UploadData	(Landroid/content/Context;)Lcom/alipay/mobilesecuritysdk/deviceID/IdResponseInfo;
    //   5: astore_2
    //   6: aload_2
    //   7: ifnull +175 -> 182
    //   10: aload_2
    //   11: invokevirtual 466	com/alipay/mobilesecuritysdk/deviceID/IdResponseInfo:isMsuccess	()Z
    //   14: ifeq +168 -> 182
    //   17: new 179	java/lang/StringBuilder
    //   20: dup
    //   21: aload_2
    //   22: invokevirtual 469	com/alipay/mobilesecuritysdk/deviceID/IdResponseInfo:getMapdid	()Ljava/lang/String;
    //   25: invokestatic 183	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   28: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   31: aload_2
    //   32: invokevirtual 470	com/alipay/mobilesecuritysdk/deviceID/IdResponseInfo:getMtime	()Ljava/lang/String;
    //   35: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: astore_3
    //   42: new 265	java/util/HashMap
    //   45: dup
    //   46: invokespecial 266	java/util/HashMap:<init>	()V
    //   49: astore 4
    //   51: aload 4
    //   53: ldc 29
    //   55: aload_2
    //   56: invokevirtual 469	com/alipay/mobilesecuritysdk/deviceID/IdResponseInfo:getMapdid	()Ljava/lang/String;
    //   59: invokeinterface 273 3 0
    //   64: pop
    //   65: aload 4
    //   67: ldc 35
    //   69: aload_2
    //   70: invokevirtual 469	com/alipay/mobilesecuritysdk/deviceID/IdResponseInfo:getMapdid	()Ljava/lang/String;
    //   73: invokeinterface 273 3 0
    //   78: pop
    //   79: aload 4
    //   81: ldc 86
    //   83: aload_2
    //   84: invokevirtual 470	com/alipay/mobilesecuritysdk/deviceID/IdResponseInfo:getMtime	()Ljava/lang/String;
    //   87: invokeinterface 273 3 0
    //   92: pop
    //   93: aload 4
    //   95: ldc 26
    //   97: aload_2
    //   98: invokevirtual 473	com/alipay/mobilesecuritysdk/deviceID/IdResponseInfo:getMcheckcode	()Ljava/lang/String;
    //   101: invokeinterface 273 3 0
    //   106: pop
    //   107: aload 4
    //   109: ldc 38
    //   111: aload_2
    //   112: invokevirtual 474	com/alipay/mobilesecuritysdk/deviceID/IdResponseInfo:getMrule	()Ljava/lang/String;
    //   115: invokeinterface 273 3 0
    //   120: pop
    //   121: aload 4
    //   123: ldc 20
    //   125: aload_2
    //   126: invokevirtual 475	com/alipay/mobilesecuritysdk/deviceID/IdResponseInfo:getMapdtk	()Ljava/lang/String;
    //   129: invokeinterface 273 3 0
    //   134: pop
    //   135: new 104	com/alipay/mobilesecuritysdk/deviceID/Profile
    //   138: dup
    //   139: invokespecial 105	com/alipay/mobilesecuritysdk/deviceID/Profile:<init>	()V
    //   142: astore 5
    //   144: aload_0
    //   145: aload_1
    //   146: aload 5
    //   148: aload 4
    //   150: invokevirtual 479	com/alipay/mobilesecuritysdk/deviceID/Profile:generatePrivateData	(Ljava/util/Map;)Ljava/lang/String;
    //   153: invokevirtual 483	com/alipay/mobilesecuritysdk/deviceID/DeviceIdModel:WritePrivateData	(Landroid/content/Context;Ljava/lang/String;)V
    //   156: aload_0
    //   157: aload_3
    //   158: invokevirtual 486	com/alipay/mobilesecuritysdk/deviceID/DeviceIdModel:WriteDataToSettings	(Ljava/lang/String;)V
    //   161: aload_0
    //   162: aload_3
    //   163: invokevirtual 489	com/alipay/mobilesecuritysdk/deviceID/DeviceIdModel:WriteDataToSdCard	(Ljava/lang/String;)V
    //   166: aload_2
    //   167: invokevirtual 469	com/alipay/mobilesecuritysdk/deviceID/IdResponseInfo:getMapdid	()Ljava/lang/String;
    //   170: astore_1
    //   171: aload_1
    //   172: areturn
    //   173: astore_1
    //   174: aload_0
    //   175: aload_1
    //   176: invokestatic 237	com/alipay/mobilesecuritysdk/deviceID/LOG:getStackString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   179: invokespecial 239	com/alipay/mobilesecuritysdk/deviceID/DeviceIdModel:Log	(Ljava/lang/String;)V
    //   182: aconst_null
    //   183: areturn
    //   184: astore_1
    //   185: goto -29 -> 156
    //
    // Exception table:
    //   from	to	target	type
    //   10	144	173	java/lang/Exception
    //   144	156	173	java/lang/Exception
    //   156	171	173	java/lang/Exception
    //   144	156	184	org/json/JSONException
  }

  public String UpdateId(Context paramContext, Map<String, String> paramMap)
  {
    if (paramMap == null)
      return UpdateId(paramContext);
    boolean bool = hasInPublic();
    if (CheckPrivateData(paramMap))
    {
      if ((!bool) && (!CommonUtils.isBlank((String)paramMap.get("priDeviceId"))) && (!CommonUtils.isBlank((String)paramMap.get("time"))))
      {
        str1 = (String)paramMap.get("priDeviceId") + (String)paramMap.get("time");
        WriteDataToSettings(str1);
        WriteDataToSdCard(str1);
      }
      String str1 = (String)paramMap.get("checkcode");
      String str2 = generaterCheckCode();
      if ((checkApdid()) && (checkCheckCode(str1, str2)))
        return (String)paramMap.get("apdid");
    }
    return UpdateId(paramContext);
  }

  public IdResponseInfo UploadData(Context paramContext)
  {
    IdResponseInfo localIdResponseInfo = new IdResponseInfo();
    localIdResponseInfo.setMsuccess(false);
    String str = this.profile.generateUploadData(GetUploadInfo());
    if ((str == null) || (str.length() < 0))
      return localIdResponseInfo;
    try
    {
      paramContext = new HttpFetcher().uploadCollectedData(paramContext, "https://seccliprod.alipay.com/api/do.htm", "deviceFingerprint", str, "1", false);
      if ((paramContext != null) && (paramContext.getStatusLine().getStatusCode() == 200))
        return new Profile().ParseResponse(EntityUtils.toString(paramContext.getEntity()));
      localIdResponseInfo.setMsuccess(false);
      return localIdResponseInfo;
    }
    catch (IOException paramContext)
    {
      Log(LOG.getStackString(paramContext));
    }
    return localIdResponseInfo;
  }

  // ERROR //
  public void WriteDataToSdCard(String paramString)
  {
    // Byte code:
    //   0: invokestatic 558	com/alipay/mobilesecuritysdk/util/CommonUtils:GetSdCardFile	()Z
    //   3: ifeq +94 -> 97
    //   6: invokestatic 300	com/alipay/mobilesecuritysdk/deviceID/SecurityUtils:getSeed	()Ljava/lang/String;
    //   9: aload_1
    //   10: invokestatic 561	com/alipay/mobilesecuritysdk/deviceID/SecurityUtils:encrypt	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   13: astore_3
    //   14: new 563	java/io/File
    //   17: dup
    //   18: invokestatic 569	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   21: ldc_w 571
    //   24: invokespecial 574	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   27: astore_1
    //   28: aload_1
    //   29: ifnull +68 -> 97
    //   32: aload_1
    //   33: invokevirtual 577	java/io/File:exists	()Z
    //   36: ifne +8 -> 44
    //   39: aload_1
    //   40: invokevirtual 580	java/io/File:mkdir	()Z
    //   43: pop
    //   44: new 155	org/json/JSONObject
    //   47: dup
    //   48: invokespecial 581	org/json/JSONObject:<init>	()V
    //   51: astore_2
    //   52: aload_2
    //   53: ldc_w 583
    //   56: aload_3
    //   57: invokevirtual 586	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   60: pop
    //   61: new 179	java/lang/StringBuilder
    //   64: dup
    //   65: aload_1
    //   66: invokevirtual 589	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   69: invokestatic 183	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   72: invokespecial 184	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   75: getstatic 592	java/io/File:separator	Ljava/lang/String;
    //   78: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: ldc_w 594
    //   84: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   90: aload_2
    //   91: invokevirtual 595	org/json/JSONObject:toString	()Ljava/lang/String;
    //   94: invokestatic 599	com/alipay/mobilesecuritysdk/util/CommonUtils:WriteFile	(Ljava/lang/String;Ljava/lang/String;)V
    //   97: return
    //   98: astore_3
    //   99: aload_0
    //   100: aload_3
    //   101: invokestatic 237	com/alipay/mobilesecuritysdk/deviceID/LOG:getStackString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   104: invokespecial 239	com/alipay/mobilesecuritysdk/deviceID/DeviceIdModel:Log	(Ljava/lang/String;)V
    //   107: goto -46 -> 61
    //   110: astore_1
    //   111: aload_0
    //   112: aload_1
    //   113: invokestatic 237	com/alipay/mobilesecuritysdk/deviceID/LOG:getStackString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   116: invokespecial 239	com/alipay/mobilesecuritysdk/deviceID/DeviceIdModel:Log	(Ljava/lang/String;)V
    //   119: return
    //   120: astore_1
    //   121: aload_0
    //   122: aload_1
    //   123: invokestatic 237	com/alipay/mobilesecuritysdk/deviceID/LOG:getStackString	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   126: invokespecial 239	com/alipay/mobilesecuritysdk/deviceID/DeviceIdModel:Log	(Ljava/lang/String;)V
    //   129: return
    //
    // Exception table:
    //   from	to	target	type
    //   52	61	98	org/json/JSONException
    //   0	28	110	java/lang/Exception
    //   32	44	110	java/lang/Exception
    //   44	52	110	java/lang/Exception
    //   52	61	110	java/lang/Exception
    //   61	97	110	java/lang/Exception
    //   99	107	110	java/lang/Exception
    //   121	129	110	java/lang/Exception
    //   61	97	120	java/io/IOException
  }

  public void WriteDataToSettings(String paramString)
  {
    if (CommonUtils.isBlank(paramString));
    do
    {
      return;
      paramString = SecurityUtils.encrypt(SecurityUtils.getSeed(), paramString);
    }
    while (CommonUtils.isBlank(paramString));
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("device", paramString);
      System.setProperty("deviceid", localJSONObject.toString());
      return;
    }
    catch (JSONException paramString)
    {
      Log(LOG.getStackString(paramString));
    }
  }

  public void WritePrivateData(Context paramContext, String paramString)
  {
    paramString = SecurityUtils.encrypt(SecurityUtils.getSeed(), paramString);
    if (CommonUtils.isBlank(paramString))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("deviceid", paramString);
    this.profile.SetDataToSharePre(paramContext.getSharedPreferences("profiles", 0), localHashMap);
  }

  public boolean checkApdid()
  {
    boolean bool = false;
    String str1 = readDataFromSettings();
    String str2 = readDataFromSdCard();
    if (!CommonUtils.isBlank(str1))
      bool = this.dv.getMpriDeviceId().equals(str1.substring(0, 32));
    while (CommonUtils.isBlank(str2))
      return bool;
    return this.dv.getMpriDeviceId().equals(str2.subSequence(0, 32));
  }

  public boolean checkCheckCode(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return false;
    return paramString1.equals(paramString2);
  }

  public String generaterCheckCode()
  {
    String str2 = getCheckCodeString();
    String str1 = str2;
    if (str2 == null)
      str1 = "";
    str2 = CommonUtils.MD5(str1);
    str1 = str2;
    if (str2 == null)
      str1 = "";
    return str1;
  }

  public boolean hasInPublic()
  {
    return (hasDataInSettings()) && (hasDataInSdcard());
  }

  public String readDataFromSdCard()
  {
    try
    {
      Object localObject;
      String str;
      if (CommonUtils.GetSdCardFile())
      {
        localObject = new File(Environment.getExternalStorageDirectory(), ".SystemConfig");
        if ((localObject != null) && (!((File)localObject).exists()))
          ((File)localObject).mkdir();
        str = CommonUtils.ReadFile(((File)localObject).getAbsolutePath() + File.separator + "data");
        boolean bool = CommonUtils.isBlank(str);
        if (!bool)
          break label80;
      }
      while (true)
      {
        return null;
        label80: localObject = null;
        try
        {
          str = new JSONObject(str).getString("device");
          localObject = str;
          if (!CommonUtils.isBlank((String)localObject))
            return SecurityUtils.decrypt(SecurityUtils.getSeed(), (String)localObject);
        }
        catch (JSONException localJSONException)
        {
          while (true)
            Log(LOG.getStackString(localJSONException));
        }
      }
    }
    catch (Exception localException)
    {
      Log(LOG.getStackString(localException));
    }
    return null;
  }

  public String readDataFromSettings()
  {
    String str2 = System.getProperty("deviceid");
    if (!CommonUtils.isBlank(str2))
    {
      String str1 = null;
      try
      {
        str2 = new JSONObject(str2).getString("device");
        str1 = str2;
        if (!CommonUtils.isBlank(str1))
          return SecurityUtils.decrypt(SecurityUtils.getSeed(), str1);
      }
      catch (JSONException localJSONException)
      {
        while (true)
          Log(LOG.getStackString(localJSONException));
      }
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.deviceID.DeviceIdModel
 * JD-Core Version:    0.6.2
 */