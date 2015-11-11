package com.taobao.newxp.location;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import org.json.JSONObject;

public class a
{
  private static final String a = "TB_LOCATION";
  private static final String b = "location";

  public static Location a(Context paramContext)
  {
    return a(paramContext.getSharedPreferences("TB_LOCATION", 0).getString("location", ""));
  }

  private static Location a(String paramString)
  {
    Location localLocation = new Location("");
    try
    {
      paramString = new JSONObject(paramString);
      localLocation.setLatitude(paramString.optDouble("lat"));
      localLocation.setLongitude(paramString.optDouble("lon"));
      localLocation.setTime(paramString.optLong("time"));
      localLocation.setAccuracy(paramString.optInt("accuracy"));
      localLocation.setProvider(paramString.optString("provider"));
      return localLocation;
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  private static String a(Location paramLocation)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("lat", paramLocation.getLatitude());
      localJSONObject.put("lon", paramLocation.getLongitude());
      localJSONObject.put("time", paramLocation.getTime());
      localJSONObject.put("accuracy", paramLocation.getAccuracy());
      localJSONObject.put("provider", paramLocation.getProvider());
      paramLocation = localJSONObject.toString();
      return paramLocation;
    }
    catch (Exception paramLocation)
    {
    }
    return "";
  }

  public static void a(Context paramContext, Location paramLocation)
  {
    paramContext = paramContext.getSharedPreferences("TB_LOCATION", 0);
    paramLocation = a(paramLocation);
    try
    {
      SharedPreferences.Editor localEditor = paramContext.edit();
      localEditor.putString("location", paramLocation);
      localEditor.commit();
      return;
    }
    finally
    {
    }
    throw paramLocation;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.location.a
 * JD-Core Version:    0.6.2
 */