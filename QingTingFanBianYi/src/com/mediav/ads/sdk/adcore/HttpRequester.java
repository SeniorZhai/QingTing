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

public class HttpRequester
{
  private static Handler handler = null;
  public static Boolean isOpenLog = Boolean.valueOf(true);

  public static void getAsynData(Context paramContext, String paramString, Boolean paramBoolean, Listener paramListener)
  {
    if (handler == null)
      handler = new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          super.handleMessage(paramAnonymousMessage);
          HttpRequester.handler.post(new ResultRunable(paramAnonymousMessage));
        }
      };
    new Thread(new HttpRunable(paramString, handler, paramListener, paramContext, paramBoolean)).start();
  }

  private static byte[] getBytes(InputStream paramInputStream)
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

  public static byte[] getSyncData(Context paramContext, String paramString, Boolean paramBoolean)
  {
    Context localContext2 = null;
    Context localContext1 = null;
    Object localObject = null;
    try
    {
      paramContext = HttpCacher.get(paramContext);
      localContext1 = paramContext;
      arrayOfByte = paramContext.getAsBinary(paramString);
      localObject = arrayOfByte;
      localContext1 = paramContext;
      paramContext = (Context)localObject;
      if ((paramContext != null) && (paramBoolean.booleanValue()))
      {
        if (isOpenLog.booleanValue())
          MVLog.d("同步:缓存命中");
        localContext2 = paramContext;
        return localContext2;
      }
    }
    catch (Exception paramContext)
    {
      byte[] arrayOfByte;
      while (true)
      {
        MVLog.e("Cache error" + paramContext.getMessage());
        paramContext = (Context)localObject;
      }
      if (paramBoolean.booleanValue())
        if (isOpenLog.booleanValue())
          MVLog.d("同步:缓存未命中");
      while (true)
      {
        while (true)
        {
          localObject = localContext2;
          try
          {
            paramContext = (HttpURLConnection)new URL(paramString).openConnection();
            localObject = localContext2;
            paramContext.setConnectTimeout(1000);
            localObject = localContext2;
            paramContext.setUseCaches(false);
            localObject = localContext2;
            paramContext.setRequestMethod("GET");
            localObject = localContext2;
            paramContext.setDoInput(true);
            localObject = localContext2;
            if (paramContext.getResponseCode() != 200)
              break label314;
            localObject = localContext2;
            InputStream localInputStream = paramContext.getInputStream();
            localObject = localContext2;
            BufferedInputStream localBufferedInputStream = new BufferedInputStream(localInputStream);
            localObject = localContext2;
            arrayOfByte = getBytes(localInputStream);
            localObject = localContext2;
            localBufferedInputStream.close();
            localObject = localContext2;
            localInputStream.close();
            localObject = localContext2;
            paramContext.disconnect();
            paramContext = arrayOfByte;
            localContext2 = paramContext;
            localObject = paramContext;
            if (!paramBoolean.booleanValue())
              break;
            localContext2 = paramContext;
            if (localContext1 == null)
              break;
            localObject = paramContext;
            localContext1.put(paramString, arrayOfByte, 86400);
            return paramContext;
          }
          catch (Exception paramContext)
          {
            MVLog.e(paramContext.getMessage());
            return localObject;
          }
        }
        if (isOpenLog.booleanValue())
          MVLog.d("同步:不使用缓存");
      }
      label314: localObject = localContext2;
      paramContext.disconnect();
    }
    return null;
  }

  public static abstract interface Listener
  {
    public abstract void onGetDataFailed(String paramString);

    public abstract void onGetDataSucceed(byte[] paramArrayOfByte);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.adcore.HttpRequester
 * JD-Core Version:    0.6.2
 */