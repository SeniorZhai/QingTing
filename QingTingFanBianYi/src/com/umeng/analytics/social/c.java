package com.umeng.analytics.social;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public abstract class c
{
  private static String a(InputStream paramInputStream)
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
      b.b("MobclickAgent", "Caught IOException in convertStreamToString()", localIOException);
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
          b.b("MobclickAgent", "Caught IOException in convertStreamToString()", paramInputStream);
          return null;
        }
      }
      catch (IOException paramInputStream)
      {
        b.b("MobclickAgent", "Caught IOException in convertStreamToString()", paramInputStream);
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
        b.b("MobclickAgent", "Caught IOException in convertStreamToString()", paramInputStream);
      }
    }
    return null;
  }

  protected static String a(String paramString)
  {
    int i = new Random().nextInt(1000);
    label423: 
    while (true)
      try
      {
        String str = System.getProperty("line.separator");
        if (paramString.length() <= 1)
        {
          b.b("MobclickAgent", i + ":\tInvalid baseUrl.");
          return null;
        }
        Object localObject1 = new HttpGet(paramString);
        b.a("MobclickAgent", i + ": GET_URL: " + paramString);
        Object localObject2 = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout((HttpParams)localObject2, 10000);
        HttpConnectionParams.setSoTimeout((HttpParams)localObject2, 20000);
        localObject2 = new DefaultHttpClient((HttpParams)localObject2).execute((HttpUriRequest)localObject1);
        if (((HttpResponse)localObject2).getStatusLine().getStatusCode() == 200)
        {
          localObject1 = ((HttpResponse)localObject2).getEntity();
          if (localObject1 == null)
            break;
          localObject1 = ((HttpEntity)localObject1).getContent();
          localObject2 = ((HttpResponse)localObject2).getFirstHeader("Content-Encoding");
          if ((localObject2 != null) && (((Header)localObject2).getValue().equalsIgnoreCase("gzip")))
          {
            b.a("MobclickAgent", i + "  Use GZIPInputStream get data....");
            localObject1 = new GZIPInputStream((InputStream)localObject1);
            localObject1 = a((InputStream)localObject1);
            b.a("MobclickAgent", i + ":\tresponse: " + str + (String)localObject1);
            if (localObject1 == null)
              break;
            return localObject1;
          }
          if ((localObject2 == null) || (!((Header)localObject2).getValue().equalsIgnoreCase("deflate")))
            break label423;
          b.a("MobclickAgent", i + "  Use InflaterInputStream get data....");
          localObject1 = new InflaterInputStream((InputStream)localObject1);
          continue;
        }
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        b.c("MobclickAgent", i + ":\tClientProtocolException,Failed to send message." + paramString, localClientProtocolException);
        return null;
        b.a("MobclickAgent", i + ":\tFailed to get message." + paramString);
        return null;
      }
      catch (Exception localException)
      {
        b.c("MobclickAgent", i + ":\tIOException,Failed to send message." + paramString, localException);
        return null;
      }
    return null;
  }

  protected static String a(String paramString1, String paramString2)
  {
    int i = new Random().nextInt(1000);
    String str = System.getProperty("line.separator");
    Object localObject = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 10000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject, 20000);
    localObject = new DefaultHttpClient((HttpParams)localObject);
    b.a("MobclickAgent", i + ": POST_URL: " + paramString1);
    while (true)
      try
      {
        HttpPost localHttpPost = new HttpPost(paramString1);
        if (!TextUtils.isEmpty(paramString2))
        {
          b.a("MobclickAgent", i + ": POST_BODY: " + paramString2);
          ArrayList localArrayList = new ArrayList(1);
          localArrayList.add(new BasicNameValuePair("data", paramString2));
          localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList, "UTF-8"));
        }
        localObject = ((HttpClient)localObject).execute(localHttpPost);
        if (((HttpResponse)localObject).getStatusLine().getStatusCode() == 200)
        {
          paramString2 = ((HttpResponse)localObject).getEntity();
          if (paramString2 == null)
            break;
          paramString2 = paramString2.getContent();
          localObject = ((HttpResponse)localObject).getFirstHeader("Content-Encoding");
          if ((localObject != null) && (((Header)localObject).getValue().equalsIgnoreCase("deflate")))
          {
            paramString2 = new InflaterInputStream(paramString2);
            paramString2 = a(paramString2);
            b.a("MobclickAgent", i + ":\tresponse: " + str + paramString2);
            if (paramString2 != null)
              break label397;
            return null;
          }
        }
        else
        {
          b.c("MobclickAgent", i + ":\tFailed to send message." + paramString1);
          return null;
        }
      }
      catch (ClientProtocolException paramString2)
      {
        b.c("MobclickAgent", i + ":\tClientProtocolException,Failed to send message." + paramString1, paramString2);
        return null;
      }
      catch (IOException paramString2)
      {
        b.c("MobclickAgent", i + ":\tIOException,Failed to send message." + paramString1, paramString2);
        return null;
      }
    return null;
    label397: return paramString2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.analytics.social.c
 * JD-Core Version:    0.6.2
 */