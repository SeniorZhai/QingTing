package com.mediav.ads.sdk.log;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class LogFileManager
{
  private static void clearLogs()
  {
    String str = getLogPath();
    if (str == null)
      return;
    new File(str).delete();
  }

  public static ArrayList<JSONObject> getAllLogs()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      Object localObject1 = getLogPath();
      if (localObject1 != null)
      {
        localObject1 = new FileInputStream((String)localObject1);
        int i = ((FileInputStream)localObject1).available();
        if (i > 0)
        {
          Object localObject2 = new byte[i];
          ((FileInputStream)localObject1).read((byte[])localObject2);
          localObject2 = EncodingUtils.getString((byte[])localObject2, "UTF-8").split("\n");
          int j = localObject2.length;
          i = 0;
          while (i < j)
          {
            localArrayList.add(new JSONObject(localObject2[i]));
            i += 1;
          }
        }
        ((FileInputStream)localObject1).close();
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.getMessage();
    }
    return localArrayList;
  }

  private static String getLogPath()
  {
    File localFile = new File(Utils.getCacheDir(), "mvad_updatesdk_error");
    if (!localFile.exists());
    try
    {
      localFile.createNewFile();
      return Utils.getCacheDir() + "/" + "mvad_updatesdk_error";
    }
    catch (IOException localIOException)
    {
      MVLog.e("create log file error:" + localIOException.getMessage());
    }
    return null;
  }

  public static void saveLog(HashMap<String, String> paramHashMap)
  {
    try
    {
      Object localObject = getLogPath();
      if (localObject == null);
      while (true)
      {
        return;
        ArrayList localArrayList = getAllLogs();
        int i = localArrayList.size();
        localObject = localArrayList;
        if (i >= 50)
          localObject = new ArrayList(localArrayList.subList(i - 20, i));
        ((ArrayList)localObject).add(new JSONObject(paramHashMap));
        int j = ((ArrayList)localObject).size();
        paramHashMap = new StringBuilder();
        i = 0;
        while (i < j)
        {
          paramHashMap.append(((JSONObject)((ArrayList)localObject).get(i)).toString());
          if (i != j - 1)
            paramHashMap.append("\n");
          i += 1;
        }
        try
        {
          localObject = new FileOutputStream(getLogPath());
          ((FileOutputStream)localObject).write(paramHashMap.toString().getBytes());
          ((FileOutputStream)localObject).close();
        }
        catch (Exception paramHashMap)
        {
          MVLog.e(paramHashMap.getMessage());
        }
      }
    }
    finally
    {
    }
    throw paramHashMap;
  }

  public static void uploadAllLogs(Context paramContext)
  {
    ArrayList localArrayList = getAllLogs();
    clearLogs();
    int j = localArrayList.size();
    int i = 0;
    while (i < j)
    {
      HashMap localHashMap = new HashMap();
      JSONObject localJSONObject = (JSONObject)localArrayList.get(i);
      Iterator localIterator = localJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        try
        {
          localHashMap.put(str, localJSONObject.getString(str));
        }
        catch (JSONException localJSONException)
        {
          MVLog.e("Read Logs Error:" + localJSONException.getMessage());
        }
      }
      LogUploader.postLog(localHashMap, paramContext, false);
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.log.LogFileManager
 * JD-Core Version:    0.6.2
 */