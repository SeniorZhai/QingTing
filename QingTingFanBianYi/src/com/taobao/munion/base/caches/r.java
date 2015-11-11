package com.taobao.munion.base.caches;

import android.os.Environment;
import android.os.StatFs;
import java.io.File;

public class r
{
  public static final int a = -1;

  public static boolean a()
  {
    String str = Environment.getExternalStorageState();
    return (str != null) && (str.equals("mounted"));
  }

  public static long b()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }

  public static long c()
  {
    if (a())
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      return localStatFs.getAvailableBlocks() * l;
    }
    return -1L;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.r
 * JD-Core Version:    0.6.2
 */