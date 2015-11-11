package com.alipay.mobilesecuritysdk.model;

import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigNameEnum;
import com.alipay.mobilesecuritysdk.constant.LocationNameEnum;
import com.alipay.mobilesecuritysdk.datainfo.AppInfo;
import com.alipay.mobilesecuritysdk.datainfo.GeoResponseInfo;
import com.alipay.mobilesecuritysdk.datainfo.LocationInfo;
import com.alipay.mobilesecuritysdk.datainfo.SdkConfig;
import com.alipay.mobilesecuritysdk.datainfo.WifiCollectInfo;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import java.io.File;
import java.io.StringReader;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class DataProfile
{
  private List<String> tid;

  private SdkConfig GetDefaultConfig()
  {
    SdkConfig localSdkConfig = SdkConfig.getInstance();
    localSdkConfig.setMainSwitchLUT(0L);
    localSdkConfig.setMainSwitchState("on");
    localSdkConfig.setMainSwitchInterval(1);
    localSdkConfig.setLocateLUT(0L);
    localSdkConfig.setLocateInterval(30);
    localSdkConfig.setLocationMaxLines(24);
    localSdkConfig.setAppLUT(0L);
    localSdkConfig.setAppInterval(7);
    return localSdkConfig;
  }

  private JSONArray GetWifiToJson(List<WifiCollectInfo> paramList)
  {
    JSONArray localJSONArray = new JSONArray();
    paramList = paramList.iterator();
    if (!paramList.hasNext())
      return localJSONArray;
    WifiCollectInfo localWifiCollectInfo = (WifiCollectInfo)paramList.next();
    while (true)
    {
      JSONObject localJSONObject;
      try
      {
        localJSONObject = new JSONObject();
        if (localWifiCollectInfo.getMbssid() != null)
          break label143;
        localJSONObject.put(LocationNameEnum.BSSID.getValue(), "");
        if (localWifiCollectInfo.getMssid() != null)
          break label162;
        localJSONObject.put(LocationNameEnum.SSID.getValue(), "");
        localJSONObject.put(LocationNameEnum.CURRENT.getValue(), localWifiCollectInfo.isMiscurrent());
        localJSONObject.put(LocationNameEnum.LEVEL.getValue(), localWifiCollectInfo.getMlevel());
        localJSONArray.put(localJSONObject);
      }
      catch (JSONException localJSONException)
      {
        Log.d("location", localJSONException.getLocalizedMessage());
      }
      break;
      label143: localJSONObject.put(LocationNameEnum.BSSID.getValue(), localJSONException.getMbssid());
      continue;
      label162: localJSONObject.put(LocationNameEnum.SSID.getValue(), localJSONException.getMssid());
    }
  }

  public String AppToString(String paramString, List<AppInfo> paramList)
  {
    Object localObject = new File(paramString);
    if (((File)localObject).length() > 51200L)
    {
      ((File)localObject).delete();
      Log.i("delete file", "app file size > 50k, file path is" + paramString);
    }
    paramString = new JSONArray();
    localObject = new JSONObject();
    JSONObject localJSONObject1 = new JSONObject();
    JSONArray localJSONArray = new JSONArray();
    paramList = paramList.iterator();
    while (true)
    {
      if (!paramList.hasNext());
      try
      {
        if (GetTIDJson() == null)
          localJSONObject1.put("tid", "");
        while (true)
        {
          while (true)
          {
            localJSONObject1.put("appList", localJSONArray);
            localJSONObject1.put("timestamp", CommonUtils.convertDate2String(new Date()));
            ((JSONObject)localObject).put("type", ConfigNameEnum.START_TAG.getValue());
            ((JSONObject)localObject).put("model", localJSONObject1);
            paramString.put(localObject);
            return paramString.toString();
            AppInfo localAppInfo = (AppInfo)paramList.next();
            try
            {
              JSONObject localJSONObject2 = new JSONObject();
              localJSONObject2.put(ConfigNameEnum.PKG_NAME.getValue(), localAppInfo.getPkgName());
              localJSONObject2.put(ConfigNameEnum.PUB_KEY_HASH.getValue(), localAppInfo.getPkeyhash());
              localJSONArray.put(localJSONObject2);
            }
            catch (JSONException localJSONException)
            {
              Log.d("appinfo", localJSONException.getLocalizedMessage());
            }
          }
          break;
          localJSONObject1.put("tid", GetTIDJson());
        }
      }
      catch (JSONException paramList)
      {
        while (true)
          Log.i("apptojason", paramList.getLocalizedMessage());
      }
    }
  }

  public JSONArray GetJsonFromFile(String paramString)
  {
    if (paramString.length() > 0)
    {
      paramString = CommonUtils.ReadFile(paramString);
      if (paramString.length() <= 0)
        return null;
      try
      {
        paramString = new JSONArray(paramString);
        return paramString;
      }
      catch (JSONException paramString)
      {
        Log.d("getjsonfromfile", paramString.getLocalizedMessage());
        return null;
      }
    }
    return null;
  }

  public JSONArray GetTIDJson()
  {
    Object localObject;
    if ((this.tid == null) || (this.tid.isEmpty()))
    {
      localObject = null;
      return localObject;
    }
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = this.tid.iterator();
    while (true)
    {
      localObject = localJSONArray;
      if (!localIterator.hasNext())
        break;
      localJSONArray.put((String)localIterator.next());
    }
  }

  public String LocationToString(String paramString, List<LocationInfo> paramList)
  {
    Log.i("LocationToString path is ", paramString);
    Iterator localIterator = null;
    Object localObject2 = new File(paramString);
    Object localObject1;
    if (((File)localObject2).length() > 51200L)
    {
      ((File)localObject2).delete();
      Log.i("delete file", "lc file size > 50k");
      localObject1 = localIterator;
      paramString = (String)localObject1;
      if (localObject1 == null)
        paramString = new JSONArray();
      localObject1 = new JSONObject();
      localIterator = paramList.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        paramString.put(localObject1);
        return paramString.toString();
        localObject1 = localIterator;
        if (paramString.length() <= 0)
          break;
        localObject1 = localIterator;
        if (((File)localObject2).isDirectory())
          break;
        localObject1 = localIterator;
        if (!((File)localObject2).exists())
          break;
        localObject1 = GetJsonFromFile(paramString);
        break;
      }
      LocationInfo localLocationInfo = (LocationInfo)localIterator.next();
      try
      {
        localObject2 = new JSONObject();
        ((JSONObject)localObject2).put(LocationNameEnum.LOCATE_LATITUDE.getValue(), localLocationInfo.getLatitude());
        ((JSONObject)localObject2).put(LocationNameEnum.LOCATE_LONGITUDE.getValue(), localLocationInfo.getLongitude());
        ((JSONObject)localObject2).put(LocationNameEnum.LOCATE_CELL_ID.getValue(), localLocationInfo.getCid());
        ((JSONObject)localObject2).put(LocationNameEnum.LOCATE_LAC.getValue(), localLocationInfo.getLac());
        ((JSONObject)localObject2).put(LocationNameEnum.TIME_STAMP.getValue(), localLocationInfo.getTime());
        ((JSONObject)localObject2).put("tid", GetTIDJson());
        ((JSONObject)localObject2).put(LocationNameEnum.MCC.getValue(), localLocationInfo.getMcc());
        ((JSONObject)localObject2).put(LocationNameEnum.MNC.getValue(), localLocationInfo.getMnc());
        ((JSONObject)localObject2).put(LocationNameEnum.PHONETYPE.getValue(), localLocationInfo.getPhonetype());
        paramList = null;
        if (localLocationInfo.getWifi() != null)
          paramList = GetWifiToJson(localLocationInfo.getWifi());
        if (paramList != null)
          ((JSONObject)localObject2).put(LocationNameEnum.LOCATE_WIFI.getValue(), paramList);
        ((JSONObject)localObject1).put("type", LocationNameEnum.START_TAG.getValue());
        ((JSONObject)localObject1).put("model", localObject2);
      }
      catch (JSONException paramList)
      {
        Log.d("location", paramList.getLocalizedMessage());
      }
    }
  }

  public GeoResponseInfo analysisServerRespond(String paramString)
  {
    GeoResponseInfo localGeoResponseInfo = new GeoResponseInfo();
    try
    {
      localXmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
      localXmlPullParser.setInput(new StringReader(paramString));
      i = localXmlPullParser.getEventType();
      if (i == 1)
      {
        localGeoResponseInfo.setSuccess(true);
        return localGeoResponseInfo;
      }
    }
    catch (Exception paramString)
    {
      while (true)
        try
        {
          XmlPullParser localXmlPullParser;
          int i;
          paramString = localXmlPullParser.getName();
          if (i == 2)
          {
            if (CommonUtils.equalsIgnoreCase(paramString, ConfigNameEnum.MAIN_SWITCH_STATE.getValue()))
              localGeoResponseInfo.setMainSwitchState(localXmlPullParser.nextText());
          }
          else
          {
            i = localXmlPullParser.next();
            continue;
          }
          if (CommonUtils.equalsIgnoreCase(paramString, ConfigNameEnum.MAIN_SWITCH_INTERVAL.getValue()))
          {
            localGeoResponseInfo.setMainSwitchInterval(CommonUtils.string2int(localXmlPullParser.nextText()));
          }
          else if (CommonUtils.equalsIgnoreCase(paramString, ConfigNameEnum.LOCATE_INTERVAL.getValue()))
          {
            localGeoResponseInfo.setLocateInterval(CommonUtils.string2int(localXmlPullParser.nextText()));
          }
          else if (CommonUtils.equalsIgnoreCase(paramString, ConfigNameEnum.LOCATION_MAX_LINES.getValue()))
          {
            localGeoResponseInfo.setLocationMaxLines(CommonUtils.string2int(localXmlPullParser.nextText()));
          }
          else if (CommonUtils.equalsIgnoreCase(paramString, ConfigNameEnum.APP_INTERVAL.getValue()))
          {
            localGeoResponseInfo.setAppInterval(CommonUtils.string2int(localXmlPullParser.nextText()));
            continue;
            paramString = paramString;
            Log.i("ALP", paramString.getMessage());
          }
        }
        catch (Exception paramString)
        {
        }
    }
  }

  public void cleanUploadFiles(String paramString)
  {
    try
    {
      paramString = new File(paramString);
      if (paramString.exists())
        paramString.delete();
      return;
    }
    catch (Exception paramString)
    {
      Log.i("ALP", paramString.getMessage());
    }
  }

  public SdkConfig getConfigs(String paramString)
  {
    if (paramString.length() != 0)
      try
      {
        paramString = new File(paramString + File.separator + "seccliconfig.xml");
        if (!paramString.exists())
          return GetDefaultConfig();
        Object localObject = CommonUtils.ReadFile(paramString.getPath());
        if (((String)localObject).length() <= 0)
        {
          Log.d("read json", "file size o");
          return GetDefaultConfig();
        }
        paramString = SdkConfig.getInstance();
        try
        {
          localObject = new JSONObject((String)localObject).getJSONObject("configs");
          if (localObject == null)
            return GetDefaultConfig();
          paramString.setAppInterval(((JSONObject)localObject).getInt(ConfigNameEnum.APP_INTERVAL.getValue()));
          paramString.setAppLUT(((JSONObject)localObject).getLong(ConfigNameEnum.APP_LUT.getValue()));
          paramString.setLocateInterval(((JSONObject)localObject).getInt(ConfigNameEnum.LOCATE_INTERVAL.getValue()));
          paramString.setLocateLUT(((JSONObject)localObject).getLong(ConfigNameEnum.LOCATE_LUT.getValue()));
          paramString.setLocationMaxLines(((JSONObject)localObject).getInt(ConfigNameEnum.LOCATION_MAX_LINES.getValue()));
          paramString.setMainSwitchInterval(((JSONObject)localObject).getInt(ConfigNameEnum.MAIN_SWITCH_INTERVAL.getValue()));
          paramString.setMainSwitchLUT(((JSONObject)localObject).getLong(ConfigNameEnum.MAIN_SWITCH_LUT.getValue()));
          paramString.setMainSwitchState(((JSONObject)localObject).getString(ConfigNameEnum.MAIN_SWITCH_STATE.getValue()));
          return paramString;
        }
        catch (Exception paramString)
        {
          paramString = GetDefaultConfig();
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        SecurityClientMobile.setError(true);
        return GetDefaultConfig();
      }
    return null;
  }

  public List<String> getTid()
  {
    return this.tid;
  }

  public void saveConfigs(SdkConfig paramSdkConfig, String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put(ConfigNameEnum.MAIN_SWITCH_LUT.getValue(), paramSdkConfig.getMainSwitchLUT());
      localJSONObject.put(ConfigNameEnum.MAIN_SWITCH_STATE.getValue(), paramSdkConfig.getMainSwitchState());
      localJSONObject.put(ConfigNameEnum.MAIN_SWITCH_INTERVAL.getValue(), paramSdkConfig.getMainSwitchInterval());
      localJSONObject.put(ConfigNameEnum.LOCATE_LUT.getValue(), paramSdkConfig.getLocateLUT());
      localJSONObject.put(ConfigNameEnum.LOCATE_INTERVAL.getValue(), paramSdkConfig.getLocateInterval());
      localJSONObject.put(ConfigNameEnum.LOCATION_MAX_LINES.getValue(), paramSdkConfig.getLocationMaxLines());
      localJSONObject.put(ConfigNameEnum.APP_LUT.getValue(), paramSdkConfig.getAppLUT());
      localJSONObject.put(ConfigNameEnum.APP_INTERVAL.getValue(), paramSdkConfig.getAppInterval());
      paramSdkConfig = new JSONObject();
      paramSdkConfig.put(ConfigNameEnum.CONFIGS.getValue(), localJSONObject);
      if (SecurityClientMobile.isDebug())
        Log.i("ALP", "loadConfig" + paramSdkConfig.toString());
      CommonUtils.WriteFile(paramString, paramSdkConfig.toString());
      return;
    }
    catch (Exception paramSdkConfig)
    {
      SecurityClientMobile.setError(true);
    }
  }

  public void setTid(List<String> paramList)
  {
    this.tid = paramList;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.model.DataProfile
 * JD-Core Version:    0.6.2
 */