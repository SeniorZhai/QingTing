package com.alipay.mobilesecuritysdk.deviceID;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DataShare
{
  String GetDataFromSharedPre(SharedPreferences paramSharedPreferences, String paramString)
  {
    return paramSharedPreferences.getString(paramString, "");
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
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.deviceID.DataShare
 * JD-Core Version:    0.6.2
 */