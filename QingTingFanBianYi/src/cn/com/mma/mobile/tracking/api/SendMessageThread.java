package cn.com.mma.mobile.tracking.api;

import android.content.Context;
import android.content.SharedPreferences;
import cn.com.mma.mobile.tracking.util.DeviceInfoUtil;
import cn.com.mma.mobile.tracking.util.Logger;
import cn.com.mma.mobile.tracking.util.SharedPreferencedUtil;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

public class SendMessageThread extends Thread
{
  private Context context;
  private boolean isInterruptThread = false;
  private boolean isNormalList;
  private String spName;
  public int totalSize = 0;

  SendMessageThread(String paramString, Context paramContext, boolean paramBoolean)
  {
    this.spName = paramString;
    this.context = paramContext;
    this.isNormalList = paramBoolean;
  }

  private void handleFailedResult(String paramString, long paramLong)
  {
    if (this.isNormalList)
    {
      SharedPreferencedUtil.removeFromSharedPreferences(this.context, "cn.com.mma.mobile.tracking.normal", paramString);
      SharedPreferencedUtil.putLong(this.context, "cn.com.mma.mobile.tracking.falied", paramString, paramLong);
      SharedPreferencedUtil.putLong(this.context, "cn.com.mma.mobile.tracking.other", paramString, 1L);
      return;
    }
    paramLong = SharedPreferencedUtil.getLong(this.context, "cn.com.mma.mobile.tracking.other", paramString) + 1L;
    if (paramLong > 3L)
    {
      SharedPreferencedUtil.removeFromSharedPreferences(this.context, "cn.com.mma.mobile.tracking.falied", paramString);
      boolean bool = SharedPreferencedUtil.removeFromSharedPreferences(this.context, "cn.com.mma.mobile.tracking.other", paramString);
      Logger.d("mma_failed发送失败超过三次，删除other中记录" + bool);
      return;
    }
    SharedPreferencedUtil.putLong(this.context, "cn.com.mma.mobile.tracking.other", paramString, paramLong);
  }

  private void handleSuccessResult(String paramString1, String paramString2)
  {
    SharedPreferencedUtil.removeFromSharedPreferences(this.context, paramString1, paramString2);
    if (!this.isNormalList)
    {
      boolean bool = SharedPreferencedUtil.removeFromSharedPreferences(this.context, "cn.com.mma.mobile.tracking.other", paramString2);
      Logger.d("mma_failed数据发送成功，删除other中记录" + bool);
    }
  }

  private void sendData()
  {
    Iterator localIterator = SharedPreferencedUtil.getSharedPreferences(this.context, this.spName).getAll().keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext());
      String str;
      do
      {
        return;
        str = (String)localIterator.next();
      }
      while ((this.isInterruptThread) || (!DeviceInfoUtil.isNetworkAvailable(this.context)));
      Long localLong;
      HttpResponse localHttpResponse;
      try
      {
        localLong = Long.valueOf(SharedPreferencedUtil.getLong(this.context, this.spName, str));
        if ((str == null) || ("".equals(str)))
          continue;
        if (localLong.longValue() <= System.currentTimeMillis())
          break label249;
        localHttpResponse = sendMessage(str);
        Logger.d("isNormal:" + this.isNormalList + " mma_request_sendUrl:" + str);
        if (localHttpResponse != null)
          break label167;
        handleFailedResult(str, localLong.longValue());
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      continue;
      label167: int i = localHttpResponse.getStatusLine().getStatusCode();
      Logger.d("mma_result_code:" + i);
      if ((i == 200) || (i == 301) || (i == 302))
      {
        handleSuccessResult(this.spName, localException);
      }
      else
      {
        handleFailedResult(localException, localLong.longValue());
        continue;
        label249: SharedPreferencedUtil.removeFromSharedPreferences(this.context, this.spName, localException);
      }
    }
  }

  // ERROR //
  private HttpResponse sendMessage(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 5
    //   5: aconst_null
    //   6: astore 4
    //   8: new 178	org/apache/http/impl/client/DefaultHttpClient
    //   11: dup
    //   12: invokespecial 179	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   15: astore_2
    //   16: aload_2
    //   17: invokevirtual 183	org/apache/http/impl/client/DefaultHttpClient:getParams	()Lorg/apache/http/params/HttpParams;
    //   20: ldc 185
    //   22: getstatic 190	cn/com/mma/mobile/tracking/api/Global:OFFLINECACHE_TIMEOUT	I
    //   25: sipush 1000
    //   28: imul
    //   29: invokestatic 195	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   32: invokeinterface 201 3 0
    //   37: pop
    //   38: aload_2
    //   39: invokevirtual 183	org/apache/http/impl/client/DefaultHttpClient:getParams	()Lorg/apache/http/params/HttpParams;
    //   42: ldc 203
    //   44: getstatic 190	cn/com/mma/mobile/tracking/api/Global:OFFLINECACHE_TIMEOUT	I
    //   47: sipush 1000
    //   50: imul
    //   51: invokestatic 195	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   54: invokeinterface 201 3 0
    //   59: pop
    //   60: new 205	org/apache/http/client/methods/HttpGet
    //   63: dup
    //   64: new 207	java/net/URI
    //   67: dup
    //   68: aload_1
    //   69: invokespecial 208	java/net/URI:<init>	(Ljava/lang/String;)V
    //   72: invokespecial 211	org/apache/http/client/methods/HttpGet:<init>	(Ljava/net/URI;)V
    //   75: astore_1
    //   76: aload_2
    //   77: aload_1
    //   78: invokevirtual 215	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   81: astore_1
    //   82: aload_2
    //   83: ifnull +19 -> 102
    //   86: aload_2
    //   87: invokevirtual 219	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   90: ifnull +12 -> 102
    //   93: aload_2
    //   94: invokevirtual 219	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   97: invokeinterface 224 1 0
    //   102: aload_1
    //   103: astore_2
    //   104: aload_2
    //   105: areturn
    //   106: astore_2
    //   107: aload 5
    //   109: astore_1
    //   110: aload_1
    //   111: astore_3
    //   112: aload_2
    //   113: invokevirtual 157	java/lang/Exception:printStackTrace	()V
    //   116: aload_1
    //   117: astore_3
    //   118: ldc 226
    //   120: invokestatic 73	cn/com/mma/mobile/tracking/util/Logger:d	(Ljava/lang/String;)V
    //   123: aload 4
    //   125: astore_2
    //   126: aload_1
    //   127: ifnull -23 -> 104
    //   130: aload 4
    //   132: astore_2
    //   133: aload_1
    //   134: invokevirtual 219	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   137: ifnull -33 -> 104
    //   140: aload_1
    //   141: invokevirtual 219	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   144: invokeinterface 224 1 0
    //   149: aconst_null
    //   150: areturn
    //   151: astore_1
    //   152: aload_3
    //   153: ifnull +19 -> 172
    //   156: aload_3
    //   157: invokevirtual 219	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   160: ifnull +12 -> 172
    //   163: aload_3
    //   164: invokevirtual 219	org/apache/http/impl/client/DefaultHttpClient:getConnectionManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   167: invokeinterface 224 1 0
    //   172: aload_1
    //   173: athrow
    //   174: astore_1
    //   175: aload_2
    //   176: astore_3
    //   177: goto -25 -> 152
    //   180: astore_1
    //   181: aload_2
    //   182: astore_3
    //   183: goto -31 -> 152
    //   186: astore_3
    //   187: aload_2
    //   188: astore_1
    //   189: aload_3
    //   190: astore_2
    //   191: goto -81 -> 110
    //   194: astore_3
    //   195: aload_2
    //   196: astore_1
    //   197: aload_3
    //   198: astore_2
    //   199: goto -89 -> 110
    //
    // Exception table:
    //   from	to	target	type
    //   8	16	106	java/lang/Exception
    //   8	16	151	finally
    //   112	116	151	finally
    //   118	123	151	finally
    //   16	76	174	finally
    //   76	82	180	finally
    //   16	76	186	java/lang/Exception
    //   76	82	194	java/lang/Exception
  }

  public void interrupt()
  {
    this.isInterruptThread = true;
    super.interrupt();
  }

  public void run()
  {
    sendData();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.mma.mobile.tracking.api.SendMessageThread
 * JD-Core Version:    0.6.2
 */