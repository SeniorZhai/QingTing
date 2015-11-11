package com.mediav.ads.sdk.adcore;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.mediav.ads.sdk.log.MVLog;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

class HttpRunable
  implements Runnable
{
  private final int NET_TIMEOUT = 1000;
  private Context context = null;
  private Handler handler = null;
  private Boolean isUsecache = Boolean.valueOf(true);
  private HttpRequester.Listener listener = null;
  private String urlString = null;

  public HttpRunable(String paramString, Handler paramHandler, HttpRequester.Listener paramListener, Context paramContext, Boolean paramBoolean)
  {
    this.urlString = paramString;
    this.handler = paramHandler;
    this.listener = paramListener;
    this.context = paramContext;
    this.isUsecache = paramBoolean;
  }

  private byte[] getBytes(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[1024];
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte, 0, 1024);
      if (i == -1)
        break;
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
      localByteArrayOutputStream.flush();
    }
    return localByteArrayOutputStream.toByteArray();
  }

  public void run()
  {
    Message localMessage = new Message();
    HashMap localHashMap = new HashMap();
    localHashMap.put("callback", this.listener);
    Object localObject1 = null;
    Object localObject2 = null;
    try
    {
      HttpCacher localHttpCacher = HttpCacher.get(this.context);
      localObject1 = localHttpCacher;
      localObject3 = localHttpCacher.getAsBinary(this.urlString);
      localObject2 = localObject3;
      localObject1 = localHttpCacher;
      if ((localObject2 != null) && (this.isUsecache.booleanValue()))
      {
        if (HttpRequester.isOpenLog.booleanValue())
          MVLog.d("异步:缓存命中");
        localMessage.what = 0;
        localHashMap.put("data", localObject2);
        localMessage.obj = localHashMap;
        this.handler.dispatchMessage(localMessage);
        return;
      }
    }
    catch (Exception localException2)
    {
      while (true)
      {
        Object localObject3;
        MVLog.e("Cache error" + localException2.getMessage());
        continue;
        if (this.isUsecache.booleanValue())
          if (HttpRequester.isOpenLog.booleanValue())
            MVLog.d("异步:缓存未命中");
        HttpURLConnection localHttpURLConnection;
        while (true)
        {
          try
          {
            localHttpURLConnection = (HttpURLConnection)new URL(this.urlString).openConnection();
            localHttpURLConnection.setConnectTimeout(1000);
            localHttpURLConnection.setUseCaches(false);
            localHttpURLConnection.setRequestMethod("GET");
            localHttpURLConnection.setDoInput(true);
            if (localHttpURLConnection.getResponseCode() != 200)
              break label383;
            localObject2 = localHttpURLConnection.getInputStream();
            localObject3 = new BufferedInputStream((InputStream)localObject2);
            byte[] arrayOfByte = getBytes((InputStream)localObject2);
            ((BufferedInputStream)localObject3).close();
            ((InputStream)localObject2).close();
            localHttpURLConnection.disconnect();
            localMessage.what = 0;
            localHashMap.put("data", arrayOfByte);
            localMessage.obj = localHashMap;
            if ((!this.isUsecache.booleanValue()) || (localObject1 == null))
              break;
            localObject1.put(this.urlString, arrayOfByte, 86400);
          }
          catch (Exception localException1)
          {
            localMessage.what = 2;
            localHashMap.put("error", localException1.getMessage());
            localMessage.obj = localHashMap;
            MVLog.e(1, "HttpRunable Run Error,url: " + this.urlString, localException1);
          }
          break;
          if (HttpRequester.isOpenLog.booleanValue())
            MVLog.d("异步:不使用缓存");
        }
        label383: localMessage.what = 1;
        localHashMap.put("error", String.valueOf(localHttpURLConnection.getResponseCode()));
        localMessage.obj = localHashMap;
        localHttpURLConnection.disconnect();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.adcore.HttpRunable
 * JD-Core Version:    0.6.2
 */