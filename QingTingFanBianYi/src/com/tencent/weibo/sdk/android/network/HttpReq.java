package com.tencent.weibo.sdk.android.network;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public abstract class HttpReq extends AsyncTask<Void, Integer, Object>
{
  private final String GET = "GET";
  private final String POST = "POST";
  protected HttpCallback mCallBack = null;
  protected String mHost = null;
  protected String mMethod = null;
  protected ReqParam mParam = new ReqParam();
  protected int mPort = 8088;
  private int mServiceTag = -1;
  protected String mUrl = null;

  public static String decode(String paramString)
  {
    if (paramString == null)
      return "";
    try
    {
      paramString = URLDecoder.decode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
    }
    throw new RuntimeException(paramString.getMessage(), paramString);
  }

  private InputStream picMethod()
  {
    HttpClient localHttpClient = new HttpClient();
    Object localObject3 = null;
    PostMethod localPostMethod = new PostMethod(this.mUrl);
    Object localObject1 = this.mParam.toString();
    try
    {
      localArrayList = new ArrayList();
      if ((localObject1 != null) && (!((String)localObject1).equals("")))
      {
        localObject4 = ((String)localObject1).split("&");
        int j = localObject4.length;
        i = 0;
        localObject1 = null;
        if (i < j)
          break label198;
      }
    }
    catch (IOException localIOException2)
    {
      try
      {
        localObject1 = ((String)this.mParam.getmParams().get("pic")).toCharArray();
        localObject4 = new byte[localObject1.length];
        i = 0;
        if (i >= localObject1.length)
          localObject1 = new FilePart("pic", new ByteArrayPartSource("123456.jpg", (byte[])localObject4), "image/jpeg", "utf-8");
      }
      catch (IOException localIOException2)
      {
        try
        {
          while (true)
          {
            ArrayList localArrayList;
            Object localObject4;
            localArrayList.add(localObject1);
            localPostMethod.setRequestEntity(new MultipartRequestEntity((Part[])localArrayList.toArray(new Part[localArrayList.size()]), localPostMethod.getParams()));
            int i = localHttpClient.executeMethod(localPostMethod);
            if (i == 200)
              break;
            return null;
            label198: String[] arrayOfString = localObject4[i];
            if ((arrayOfString == null) || (arrayOfString.equals("")) || (arrayOfString.indexOf("=") <= -1))
              break label337;
            arrayOfString = arrayOfString.split("=");
            if (arrayOfString.length == 2);
            for (localObject1 = decode(arrayOfString[1]); ; localObject1 = "")
            {
              localObject1 = new StringPart(arrayOfString[0], (String)localObject1, "utf-8");
              localArrayList.add(localObject1);
              i += 1;
              break;
            }
            localObject4[i] = ((byte)localObject1[i]);
            i += 1;
          }
          localObject1 = localPostMethod.getResponseBodyAsStream();
          return localObject1;
          for (localIOException1 = localIOException1; ; localIOException2 = localIOException2)
          {
            localIOException1.printStackTrace();
            Object localObject2 = localObject3;
            break;
          }
        }
        catch (IOException localIOException3)
        {
          label337: 
          while (true);
        }
      }
    }
  }

  private static InputStream readHttpResponse(HttpResponse paramHttpResponse)
  {
    Object localObject3 = paramHttpResponse.getEntity();
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      localObject3 = ((HttpEntity)localObject3).getContent();
      localObject1 = localObject3;
      localObject2 = localObject3;
      Header localHeader = paramHttpResponse.getFirstHeader("Content-Encoding");
      paramHttpResponse = (HttpResponse)localObject3;
      if (localHeader != null)
      {
        paramHttpResponse = (HttpResponse)localObject3;
        localObject1 = localObject3;
        localObject2 = localObject3;
        if (localHeader.getValue().toLowerCase().indexOf("gzip") > -1)
        {
          localObject1 = localObject3;
          localObject2 = localObject3;
          paramHttpResponse = new GZIPInputStream((InputStream)localObject3);
        }
      }
      return paramHttpResponse;
    }
    catch (IOException paramHttpResponse)
    {
      return localObject1;
    }
    catch (IllegalStateException paramHttpResponse)
    {
      while (true)
        localObject1 = localObject2;
    }
  }

  public void addParam(String paramString, Object paramObject)
  {
    this.mParam.addParam(paramString, paramObject);
  }

  public void addParam(String paramString1, String paramString2)
  {
    this.mParam.addParam(paramString1, paramString2);
  }

  protected Object doInBackground(Void[] paramArrayOfVoid)
  {
    try
    {
      paramArrayOfVoid = runReq();
      return paramArrayOfVoid;
    }
    catch (Exception paramArrayOfVoid)
    {
      paramArrayOfVoid.printStackTrace();
    }
    return null;
  }

  protected HttpCallback getCallBack()
  {
    return this.mCallBack;
  }

  public int getServiceTag()
  {
    return this.mServiceTag;
  }

  protected void onCancelled()
  {
    if (this.mCallBack != null)
      this.mCallBack.onResult(null);
    HttpService.getInstance().onReqFinish(this);
  }

  protected void onPostExecute(Object paramObject)
  {
    if (this.mCallBack != null)
      this.mCallBack.onResult(paramObject);
    HttpService.getInstance().onReqFinish(this);
  }

  protected void onPreExecute()
  {
    super.onPreExecute();
  }

  protected abstract Object processResponse(InputStream paramInputStream)
    throws Exception;

  public Object runReq()
    throws Exception
  {
    HttpClient localHttpClient = new HttpClient();
    if (this.mMethod.equals("GET"))
      this.mUrl = (this.mUrl + "?" + this.mParam.toString().substring(0, this.mParam.toString().length() - 1));
    for (Object localObject = new GetMethod(this.mUrl); ; localObject = new UTF8PostMethod(this.mUrl))
    {
      localHttpClient.getHostConfiguration().setHost(this.mHost, this.mPort, "http");
      ((HttpMethod)localObject).setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      setReq((HttpMethod)localObject);
      if (localHttpClient.executeMethod((HttpMethod)localObject) == 200)
        break label197;
      return null;
      if (!this.mMethod.equals("POST"))
        break;
      if (this.mParam.getmParams().get("pic") != null)
        return processResponse(picMethod());
    }
    throw new Exception("unrecognized http method");
    label197: return processResponse(((HttpMethod)localObject).getResponseBodyAsStream());
  }

  public void setParam(ReqParam paramReqParam)
  {
    this.mParam = paramReqParam;
  }

  protected abstract void setReq(HttpMethod paramHttpMethod)
    throws Exception;

  public void setServiceTag(int paramInt)
  {
    this.mServiceTag = paramInt;
  }

  public static class UTF8PostMethod extends PostMethod
  {
    public UTF8PostMethod(String paramString)
    {
      super();
    }

    public String getRequestCharSet()
    {
      return "UTF-8";
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.network.HttpReq
 * JD-Core Version:    0.6.2
 */