package fm.qingting.framework.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;

public class MemManager
{
  private static double freeMemForApp = -1.0D;
  private static double heapMemLimit;
  private static double startUpMem;

  public static double getFreeMemForApp(Context paramContext)
  {
    if (freeMemForApp < 0.0D)
    {
      heapMemLimit = ((ActivityManager)paramContext.getSystemService("activity")).getMemoryClass() * 1024;
      log("dalvik.vm.heapsize:" + heapMemLimit);
      paramContext = Runtime.getRuntime();
      heapMemLimit = paramContext.maxMemory() / 1024L;
      log("Max heap:" + heapMemLimit + ";Current heap:" + paramContext.totalMemory() / 1024L + ";Free heap:" + paramContext.freeMemory() / 1024L + ";Allocated heap:" + (paramContext.totalMemory() / 1024L - paramContext.freeMemory() / 1024L));
      log("Native heap size:" + Debug.getNativeHeapSize() / 1024L + ";Native allocated heap size:" + Debug.getNativeHeapAllocatedSize() / 1024L + ";Native free heap size:" + Debug.getNativeHeapFreeSize() / 1024L);
      startUpMem = (paramContext.totalMemory() - paramContext.freeMemory() + Debug.getNativeHeapAllocatedSize()) / 1024L;
      log("Start up memory:" + startUpMem);
      log("Free Mem Now:" + (heapMemLimit - startUpMem));
      freeMemForApp = heapMemLimit - startUpMem;
      return freeMemForApp;
    }
    return freeMemForApp;
  }

  public static void log(String paramString)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.MemManager
 * JD-Core Version:    0.6.2
 */