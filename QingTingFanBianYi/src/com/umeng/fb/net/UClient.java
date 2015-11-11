package com.umeng.fb.net;

import android.text.TextUtils;
import com.umeng.fb.util.DeflaterHelper;
import com.umeng.fb.util.Helper;
import com.umeng.fb.util.Log;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;

public class UClient
{
  private static final String TAG = UClient.class.getName();
  private Map<String, String> headers;

  private JSONObject HttpRequestGet(String paramString)
  {
    int i = new Random().nextInt(1000);
    try
    {
      str = System.getProperty("line.separator");
      if (paramString.length() <= 1)
      {
        Log.e(TAG, i + ":\tInvalid baseUrl.");
        return null;
      }
      Log.i(TAG, i + ":\tget: " + paramString);
      HttpGet localHttpGet = new HttpGet(paramString);
      if ((this.headers != null) && (this.headers.size() > 0))
      {
        localObject2 = this.headers.keySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (String)((Iterator)localObject2).next();
          localHttpGet.addHeader((String)localObject3, (String)this.headers.get(localObject3));
        }
      }
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      String str;
      Object localObject2;
      Object localObject3;
      Log.d(TAG, i + ":\tClientProtocolException,Failed to send message." + paramString, localClientProtocolException);
      return null;
      Object localObject1 = new DefaultHttpClient(getHttpParams()).execute(localClientProtocolException);
      if (((HttpResponse)localObject1).getStatusLine().getStatusCode() == 200)
      {
        localObject2 = ((HttpResponse)localObject1).getEntity();
        if (localObject2 == null)
          break label529;
        localObject2 = ((HttpEntity)localObject2).getContent();
        localObject3 = ((HttpResponse)localObject1).getFirstHeader("Content-Encoding");
        if ((localObject3 != null) && (((Header)localObject3).getValue().equalsIgnoreCase("gzip")))
        {
          Log.i(TAG, i + "  Use GZIPInputStream get data....");
          localObject1 = new GZIPInputStream((InputStream)localObject2);
        }
        while (true)
        {
          localObject1 = convertStreamToString((InputStream)localObject1);
          Log.i(TAG, i + ":\tresponse: " + str + (String)localObject1);
          if (localObject1 != null)
            break;
          return null;
          localObject1 = localObject2;
          if (localObject3 != null)
          {
            localObject1 = localObject2;
            if (((Header)localObject3).getValue().equalsIgnoreCase("deflate"))
            {
              Log.i(TAG, i + "  Use InflaterInputStream get data....");
              localObject1 = new InflaterInputStream((InputStream)localObject2);
            }
          }
        }
        localObject1 = new JSONObject((String)localObject1);
        return localObject1;
      }
    }
    catch (Exception localException)
    {
      Log.d(TAG, i + ":\tIOException,Failed to send message." + paramString, localException);
      return null;
    }
    Log.d(TAG, i + ":\tFailed to send message. StatusCode = " + localException.getStatusLine().getStatusCode() + Helper.LINE_SEPARATOR + paramString);
    label529: return null;
  }

  private JSONObject HttpRequestPost(String paramString, JSONObject paramJSONObject)
  {
    Object localObject2 = paramJSONObject.toString();
    int i = new Random().nextInt(1000);
    Log.d(TAG, i + ":\trequest: " + paramString + Helper.LINE_SEPARATOR + (String)localObject2);
    paramJSONObject = new HttpPost(paramString);
    Object localObject1 = new DefaultHttpClient(getHttpParams());
    try
    {
      if (shouldCompressData())
      {
        localObject2 = DeflaterHelper.deflaterCompress("content=" + (String)localObject2, Charset.defaultCharset().toString());
        paramJSONObject.addHeader("Content-Encoding", "deflate");
        paramJSONObject.setEntity(new InputStreamEntity(new ByteArrayInputStream((byte[])localObject2), localObject2.length));
      }
      while (true)
      {
        paramJSONObject = ((HttpClient)localObject1).execute(paramJSONObject);
        if (paramJSONObject.getStatusLine().getStatusCode() != 200)
          break label416;
        localObject1 = paramJSONObject.getEntity();
        if (localObject1 == null)
          break label414;
        localObject1 = ((HttpEntity)localObject1).getContent();
        localObject2 = paramJSONObject.getFirstHeader("Content-Encoding");
        paramJSONObject = (JSONObject)localObject1;
        if (localObject2 != null)
        {
          paramJSONObject = (JSONObject)localObject1;
          if (((Header)localObject2).getValue().equalsIgnoreCase("deflate"))
            paramJSONObject = new InflaterInputStream((InputStream)localObject1);
        }
        paramJSONObject = convertStreamToString(paramJSONObject);
        Log.i(TAG, i + ":\tresponse: " + Helper.LINE_SEPARATOR + paramJSONObject);
        if (paramJSONObject != null)
          break;
        return null;
        ArrayList localArrayList = new ArrayList(1);
        localArrayList.add(new BasicNameValuePair("content", (String)localObject2));
        paramJSONObject.setEntity(new UrlEncodedFormEntity(localArrayList, "UTF-8"));
      }
    }
    catch (ClientProtocolException paramJSONObject)
    {
      Log.d(TAG, i + ":\tClientProtocolException,Failed to send message." + paramString, paramJSONObject);
      return null;
      paramJSONObject = new JSONObject(paramJSONObject);
      return paramJSONObject;
    }
    catch (IOException paramJSONObject)
    {
      Log.d(TAG, i + ":\tIOException,Failed to send message." + paramString, paramJSONObject);
      return null;
      return null;
      Log.d(TAG, i + ":\tFailed to send message. StatusCode = " + paramJSONObject.getStatusLine().getStatusCode() + Helper.LINE_SEPARATOR + paramString);
      return null;
    }
    catch (JSONException paramJSONObject)
    {
      label414: label416: Log.d(TAG, i + ":\tIOException,Failed to send message." + paramString, paramJSONObject);
    }
    return null;
  }

  private static String convertStreamToString(InputStream paramInputStream)
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream), 8192);
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          break;
        localStringBuilder.append(str + "\n");
      }
    }
    catch (IOException localIOException)
    {
      Log.e(TAG, "Caught IOException in convertStreamToString()", localIOException);
      try
      {
        paramInputStream.close();
        return null;
        try
        {
          paramInputStream.close();
          return localStringBuilder.toString();
        }
        catch (IOException paramInputStream)
        {
          Log.e(TAG, "Caught IOException in convertStreamToString()", paramInputStream);
          return null;
        }
      }
      catch (IOException paramInputStream)
      {
        Log.e(TAG, "Caught IOException in convertStreamToString()", paramInputStream);
        return null;
      }
    }
    finally
    {
      try
      {
        paramInputStream.close();
        throw localObject;
      }
      catch (IOException paramInputStream)
      {
        Log.e(TAG, "Caught IOException in convertStreamToString()", paramInputStream);
      }
    }
    return null;
  }

  private HttpParams getHttpParams()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 10000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
    HttpProtocolParams.setUserAgent(localBasicHttpParams, System.getProperty("http.agent"));
    return localBasicHttpParams;
  }

  private void verifyMethod(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (!(URequest.GET.equals(paramString.trim()) ^ URequest.POST.equals(paramString.trim()))))
      throw new RuntimeException("验证请求方式失败[" + paramString + "]");
  }

  public <T extends UResponse> T execute(URequest paramURequest, Class<T> paramClass)
  {
    String str = paramURequest.getHttpMethod().trim();
    verifyMethod(str);
    JSONObject localJSONObject = null;
    if (URequest.GET.equals(str))
      localJSONObject = HttpRequestGet(paramURequest.toGetUrl());
    while (localJSONObject == null)
    {
      return null;
      if (URequest.POST.equals(str))
        localJSONObject = HttpRequestPost(paramURequest.baseUrl, paramURequest.toJson());
    }
    try
    {
      paramURequest = (UResponse)paramClass.getConstructor(new Class[] { JSONObject.class }).newInstance(new Object[] { localJSONObject });
      return paramURequest;
    }
    catch (SecurityException paramURequest)
    {
      Log.e(TAG, "SecurityException", paramURequest);
      return null;
    }
    catch (NoSuchMethodException paramURequest)
    {
      while (true)
        Log.e(TAG, "NoSuchMethodException", paramURequest);
    }
    catch (IllegalArgumentException paramURequest)
    {
      while (true)
        Log.e(TAG, "IllegalArgumentException", paramURequest);
    }
    catch (InstantiationException paramURequest)
    {
      while (true)
        Log.e(TAG, "InstantiationException", paramURequest);
    }
    catch (IllegalAccessException paramURequest)
    {
      while (true)
        Log.e(TAG, "IllegalAccessException", paramURequest);
    }
    catch (InvocationTargetException paramURequest)
    {
      while (true)
        Log.e(TAG, "InvocationTargetException", paramURequest);
    }
  }

  public UClient setHeader(Map<String, String> paramMap)
  {
    this.headers = paramMap;
    return this;
  }

  public boolean shouldCompressData()
  {
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.net.UClient
 * JD-Core Version:    0.6.2
 */