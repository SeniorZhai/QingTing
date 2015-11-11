package com.mediav.ads.sdk.log;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

public class LogUploader
{
  private static final int DAILY_MAX_LIMIT = 100;
  private static final String ERROR_LOG_KEY = "mvadsdkerrordaycheck";
  private static int logid = 0;

  private static boolean checkLimit(Context paramContext)
  {
    Object localObject = new SimpleDateFormat("yyyyMMdd");
    localObject = "count" + ((SimpleDateFormat)localObject).format(Long.valueOf(System.currentTimeMillis()));
    int i = paramContext.getSharedPreferences("mvadsdkerrordaycheck", 0).getInt((String)localObject, -1);
    if (i < 0)
    {
      paramContext = paramContext.getSharedPreferences("mvadsdkerrordaycheck", 0).edit();
      paramContext.clear();
      paramContext.putInt((String)localObject, 0);
      paramContext.commit();
    }
    while (i < 100)
      return true;
    return false;
  }

  private static void incLogCount(Context paramContext)
  {
    Object localObject = new SimpleDateFormat("yyyyMMdd");
    localObject = "count" + ((SimpleDateFormat)localObject).format(Long.valueOf(System.currentTimeMillis()));
    int i = paramContext.getSharedPreferences("mvadsdkerrordaycheck", 0).getInt((String)localObject, 0);
    paramContext.getSharedPreferences("mvadsdkerrordaycheck", 0).edit().putInt((String)localObject, i + 1).commit();
  }

  public static boolean postData(String paramString, HashMap<String, String> paramHashMap)
  {
    paramString = new HttpPost(paramString);
    ArrayList localArrayList = new ArrayList();
    if (paramHashMap != null)
    {
      paramHashMap = paramHashMap.entrySet().iterator();
      while (paramHashMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramHashMap.next();
        localArrayList.add(new BasicNameValuePair((String)localEntry.getKey(), (String)localEntry.getValue()));
      }
    }
    try
    {
      paramString.setEntity(new UrlEncodedFormEntity(localArrayList, "UTF-8"));
      paramHashMap = new DefaultHttpClient();
      paramHashMap.getParams().setParameter("http.connection.timeout", Integer.valueOf(20000));
      paramHashMap.getParams().setParameter("http.socket.timeout", Integer.valueOf(20000));
      paramString = paramHashMap.execute(paramString);
      if (paramString.getStatusLine().getStatusCode() == 200)
        return true;
      MVLog.d("POST异常:Code=" + paramString.getStatusLine().getStatusCode());
      return false;
    }
    catch (Exception paramString)
    {
      while (true)
        MVLog.d("POST异常:" + paramString.getMessage());
    }
  }

  public static void postLog(HashMap<String, String> paramHashMap, Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean);
    try
    {
      logid += 1;
      paramHashMap.put("elogid", logid + "");
      incLogCount(paramContext);
      if (!checkLimit(paramContext))
        MVLog.d("上传LOG数已超过上限，取消上传");
      while (true)
      {
        return;
        MVLog.d("上传LOG");
        if ((Utils.isNetEnable()) && (!postData("http://tran.mediav.com/t?type=15", paramHashMap)) && (paramBoolean))
          LogFileManager.saveLog(paramHashMap);
      }
    }
    finally
    {
    }
    throw paramHashMap;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.log.LogUploader
 * JD-Core Version:    0.6.2
 */