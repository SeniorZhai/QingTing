package fm.qingting.downloadnew;

import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class DownloadUtils
{
  private static String EMPTY_STRING = "";
  private static char[] ILLEGAL_CHARS = { 58, 32, 63, 34, 60, 62, 33, 92, 47, 9, 13, 10 };
  private static char[] SUB_CHARS = { -230, 95, -225, 95, 12298, 12299, -255, 95, 95, 95, 95, 95 };

  public static File createFile(String paramString)
    throws IOException
  {
    paramString = new File(paramString);
    if (paramString.exists());
    while ((!createFolder(paramString.getParentFile())) || (!paramString.createNewFile()))
      return paramString;
    return paramString;
  }

  private static boolean createFolder(File paramFile)
  {
    if (paramFile.exists())
      return true;
    if (createFolder(paramFile.getParentFile()))
      return paramFile.mkdir();
    return false;
  }

  public static void deleteFile(String paramString)
  {
    paramString = new File(paramString);
    if (paramString.exists())
      paramString.delete();
  }

  public static String escapeFileName(String paramString)
  {
    if (paramString == null)
      return null;
    paramString = paramString.trim();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramString.length())
    {
      localStringBuilder.append(getLegalChar(paramString.charAt(i)));
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public static String formatSize(long paramLong, boolean paramBoolean)
  {
    if (paramBoolean);
    for (int i = 1000; paramLong < i; i = 1024)
      return paramLong + " B";
    int j = (int)(Math.log(paramLong) / Math.log(i));
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramBoolean)
    {
      str = "kMGTPE";
      localStringBuilder = localStringBuilder.append(str.charAt(j - 1));
      if (!paramBoolean)
        break label149;
    }
    label149: for (String str = ""; ; str = "i")
    {
      str = str;
      return String.format(Locale.US, "%.1f%sB", new Object[] { Double.valueOf(paramLong / Math.pow(i, j)), str });
      str = "KMGTPE";
      break;
    }
  }

  public static long getAvailableExternalMemorySize()
  {
    long l = 0L;
    try
    {
      if (isSDCardAvailable())
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        l = localStatFs.getBlockSize();
        int i = localStatFs.getAvailableBlocks();
        l = i * l;
      }
      return l;
    }
    catch (Exception localException)
    {
    }
    return 0L;
  }

  public static long getAvailableExternalMemorySize(String paramString)
  {
    try
    {
      paramString = new StatFs(paramString);
      long l = paramString.getBlockSize();
      int i = paramString.getAvailableBlocks();
      return i * l;
    }
    catch (Exception paramString)
    {
    }
    return 0L;
  }

  public static String getDownloadFileName(String paramString)
  {
    if (paramString == null)
      return "";
    return paramString.substring(paramString.lastIndexOf('/') + 1, paramString.length());
  }

  private static char getLegalChar(char paramChar)
  {
    int i = 0;
    if (i < ILLEGAL_CHARS.length)
      if (paramChar != ILLEGAL_CHARS[i]);
    while (true)
    {
      if (i != -1)
        paramChar = SUB_CHARS[i];
      return paramChar;
      i += 1;
      break;
      i = -1;
    }
  }

  public static long getTotalExternalMemorySize(String paramString)
  {
    try
    {
      paramString = new StatFs(paramString);
      long l = paramString.getBlockSize();
      int i = paramString.getBlockCount();
      return i * l;
    }
    catch (Exception paramString)
    {
    }
    return 0L;
  }

  public static boolean isSDCardAvailable()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }

  public static String notNull(String paramString)
  {
    String str = paramString;
    if (paramString == null)
      str = EMPTY_STRING;
    return str;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.downloadnew.DownloadUtils
 * JD-Core Version:    0.6.2
 */