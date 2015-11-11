package com.alipay.mobilesecuritysdk.face;

import android.content.Context;
import android.util.Log;
import com.alipay.mobilesecuritysdk.MainHandler;
import com.alipay.mobilesecuritysdk.deviceID.DeviceIdManager;
import java.util.List;
import java.util.Map;

public class SecurityClientMobile
{
  private static boolean isDebug = false;
  private static boolean isError = false;
  private static Thread workThread;

  public static String GetApdid(Context paramContext, Map<String, String> paramMap)
  {
    try
    {
      paramContext = new DeviceIdManager(paramContext).GetApDid(paramMap);
      return paramContext;
    }
    finally
    {
      paramContext = finally;
    }
    throw paramContext;
  }

  public static boolean isDebug()
  {
    return isDebug;
  }

  public static void setDebug(boolean paramBoolean)
  {
    isDebug = paramBoolean;
  }

  public static void setError(boolean paramBoolean)
  {
    isError = paramBoolean;
  }

  // ERROR //
  public static void start(Context paramContext, final List<String> paramList, final boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 17	com/alipay/mobilesecuritysdk/face/SecurityClientMobile:isDebug	Z
    //   6: ifeq +11 -> 17
    //   9: ldc 45
    //   11: ldc 47
    //   13: invokestatic 53	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   16: pop
    //   17: aload_0
    //   18: ifnonnull +21 -> 39
    //   21: getstatic 17	com/alipay/mobilesecuritysdk/face/SecurityClientMobile:isDebug	Z
    //   24: ifeq +11 -> 35
    //   27: ldc 45
    //   29: ldc 55
    //   31: invokestatic 53	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   34: pop
    //   35: ldc 2
    //   37: monitorexit
    //   38: return
    //   39: getstatic 57	com/alipay/mobilesecuritysdk/face/SecurityClientMobile:workThread	Ljava/lang/Thread;
    //   42: ifnull +29 -> 71
    //   45: getstatic 57	com/alipay/mobilesecuritysdk/face/SecurityClientMobile:workThread	Ljava/lang/Thread;
    //   48: invokevirtual 62	java/lang/Thread:isAlive	()Z
    //   51: ifeq +20 -> 71
    //   54: getstatic 17	com/alipay/mobilesecuritysdk/face/SecurityClientMobile:isDebug	Z
    //   57: ifeq -22 -> 35
    //   60: ldc 45
    //   62: ldc 64
    //   64: invokestatic 53	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   67: pop
    //   68: goto -33 -> 35
    //   71: aconst_null
    //   72: putstatic 57	com/alipay/mobilesecuritysdk/face/SecurityClientMobile:workThread	Ljava/lang/Thread;
    //   75: getstatic 15	com/alipay/mobilesecuritysdk/face/SecurityClientMobile:isError	Z
    //   78: ifeq +26 -> 104
    //   81: getstatic 17	com/alipay/mobilesecuritysdk/face/SecurityClientMobile:isDebug	Z
    //   84: ifeq -49 -> 35
    //   87: ldc 45
    //   89: ldc 66
    //   91: invokestatic 53	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   94: pop
    //   95: goto -60 -> 35
    //   98: astore_0
    //   99: ldc 2
    //   101: monitorexit
    //   102: aload_0
    //   103: athrow
    //   104: new 59	java/lang/Thread
    //   107: dup
    //   108: new 6	com/alipay/mobilesecuritysdk/face/SecurityClientMobile$1
    //   111: dup
    //   112: aload_0
    //   113: aload_1
    //   114: iload_2
    //   115: invokespecial 68	com/alipay/mobilesecuritysdk/face/SecurityClientMobile$1:<init>	(Landroid/content/Context;Ljava/util/List;Z)V
    //   118: invokespecial 71	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   121: putstatic 57	com/alipay/mobilesecuritysdk/face/SecurityClientMobile:workThread	Ljava/lang/Thread;
    //   124: getstatic 57	com/alipay/mobilesecuritysdk/face/SecurityClientMobile:workThread	Ljava/lang/Thread;
    //   127: invokevirtual 73	java/lang/Thread:start	()V
    //   130: goto -95 -> 35
    //   133: astore_0
    //   134: goto -99 -> 35
    //
    // Exception table:
    //   from	to	target	type
    //   3	17	98	finally
    //   21	35	98	finally
    //   39	68	98	finally
    //   71	95	98	finally
    //   104	130	98	finally
    //   3	17	133	java/lang/Throwable
    //   21	35	133	java/lang/Throwable
    //   39	68	133	java/lang/Throwable
    //   71	95	133	java/lang/Throwable
    //   104	130	133	java/lang/Throwable
  }

  public static void stop()
  {
    try
    {
      if (isDebug)
        Log.i("ALP", "stop have been called.");
      if (workThread != null)
      {
        if (!workThread.isAlive())
          return;
        workThread.interrupt();
        workThread = null;
        return;
      }
    }
    catch (Throwable localThrowable)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.face.SecurityClientMobile
 * JD-Core Version:    0.6.2
 */