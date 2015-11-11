package com.alipay.mobilesecuritysdk.deviceID;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile
{
  public static final String devicever = "0";

  private String MaptoString(Map<String, String> paramMap)
    throws JSONException
  {
    if ((paramMap != null) && (paramMap.size() > 0))
    {
      paramMap = paramMap.entrySet().iterator();
      JSONObject localJSONObject = new JSONObject();
      while (true)
      {
        if (!paramMap.hasNext())
          return localJSONObject.toString();
        Object localObject = (Map.Entry)paramMap.next();
        String str = (String)((Map.Entry)localObject).getKey();
        localObject = (String)((Map.Entry)localObject).getValue();
        try
        {
          localJSONObject.put(str, localObject);
        }
        catch (JSONException localJSONException)
        {
        }
      }
    }
    return null;
  }

  String GetDataFromSharedPre(SharedPreferences paramSharedPreferences, String paramString)
  {
    return paramSharedPreferences.getString(paramString, "");
  }

  public IdResponseInfo ParseResponse(String paramString)
  {
    if (paramString == null)
      paramString = null;
    IdResponseInfo localIdResponseInfo;
    while (true)
    {
      return paramString;
      Log.i("deviceid", "server response is" + paramString);
      localIdResponseInfo = new IdResponseInfo();
      try
      {
        localObject = new JSONObject(paramString);
        localIdResponseInfo.setMsuccess(((JSONObject)localObject).getBoolean("success"));
        paramString = localIdResponseInfo;
        if (localIdResponseInfo.isMsuccess())
        {
          localObject = ((JSONObject)localObject).getJSONObject("data");
          paramString = localIdResponseInfo;
          if (localObject != null)
          {
            localIdResponseInfo.setMversion(((JSONObject)localObject).getString("version"));
            localIdResponseInfo.setMapdid(((JSONObject)localObject).getString("apdid"));
            localIdResponseInfo.setMapdtk(((JSONObject)localObject).getString("apdtk"));
            paramString = ((JSONObject)localObject).getJSONObject("rule");
            if (paramString != null)
              localIdResponseInfo.setFuction(paramString.getString("function"));
            localIdResponseInfo.setMrule(paramString.toString());
            localIdResponseInfo.setMtime(((JSONObject)localObject).getString("time"));
            localIdResponseInfo.setMcheckcode(((JSONObject)localObject).getString("checkcode"));
            return localIdResponseInfo;
          }
        }
      }
      catch (JSONException paramString)
      {
        Object localObject = new ArrayList();
        ((List)localObject).add("");
        ((List)localObject).add("");
        ((List)localObject).add("");
        ((List)localObject).add(LOG.getStackString(paramString));
        LOG.logMessage((List)localObject);
      }
    }
    return localIdResponseInfo;
  }

  void SetDataToSharePre(SharedPreferences paramSharedPreferences, Map<String, String> paramMap)
  {
    if ((paramSharedPreferences != null) && (paramMap != null))
    {
      paramSharedPreferences = paramSharedPreferences.edit();
      if (paramSharedPreferences != null)
      {
        paramSharedPreferences.clear();
        paramMap = paramMap.entrySet().iterator();
      }
    }
    while (true)
    {
      if (!paramMap.hasNext())
      {
        paramSharedPreferences.commit();
        return;
      }
      Object localObject = (Map.Entry)paramMap.next();
      String str = (String)((Map.Entry)localObject).getKey();
      localObject = ((Map.Entry)localObject).getValue();
      if ((localObject instanceof String))
        paramSharedPreferences.putString(str, (String)localObject);
      else if ((localObject instanceof Integer))
        paramSharedPreferences.putInt(str, ((Integer)localObject).intValue());
      else if ((localObject instanceof Long))
        paramSharedPreferences.putLong(str, ((Long)localObject).longValue());
      else if ((localObject instanceof Float))
        paramSharedPreferences.putFloat(str, ((Float)localObject).floatValue());
      else if ((localObject instanceof Boolean))
        paramSharedPreferences.putBoolean(str, ((Boolean)localObject).booleanValue());
    }
  }

  public String generatePrivateData(Map<String, String> paramMap)
    throws JSONException
  {
    return MaptoString(paramMap);
  }

  public String generateUploadData(Map<String, Object> paramMap)
  {
    JSONObject localJSONObject1 = new JSONObject();
    JSONObject localJSONObject2 = new JSONObject();
    JSONObject localJSONObject3 = new JSONObject();
    if (paramMap != null);
    try
    {
      if (paramMap.size() > 0)
        paramMap = paramMap.entrySet().iterator();
      while (true)
      {
        if (!paramMap.hasNext())
        {
          localJSONObject2.put("os", "android");
          localJSONObject2.put("data", localJSONObject3);
          localJSONObject1.put("type", "deviceinfo");
          localJSONObject1.put("model", localJSONObject2);
          label94: return localJSONObject1.toString();
        }
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        String str = (String)localEntry.getKey();
        if (str.equals("deviceInfo"))
          localJSONObject3.put(str, new JSONObject(MaptoString((Map)localEntry.getValue())));
        else
          localJSONObject3.put(str, (String)localEntry.getValue());
      }
    }
    catch (JSONException paramMap)
    {
      break label94;
    }
  }

  public Map<String, String> getMap(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      Iterator localIterator = paramString.keys();
      HashMap localHashMap = new HashMap();
      while (true)
      {
        if (!localIterator.hasNext())
          return localHashMap;
        String str = (String)localIterator.next();
        localHashMap.put(str, (String)paramString.get(str));
      }
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.deviceID.Profile
 * JD-Core Version:    0.6.2
 */