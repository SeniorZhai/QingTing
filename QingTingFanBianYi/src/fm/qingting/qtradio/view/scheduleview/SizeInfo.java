package fm.qingting.qtradio.view.scheduleview;

import java.util.Locale;

public class SizeInfo
{
  private static final String SIZEMODEL = "共%d个文件，占用%s";
  public int mCnt;
  public int mFileSize;
  public String mSizeString;

  public static String getFileSize(long paramLong)
  {
    if (paramLong < 0L);
    do
    {
      return "";
      if (paramLong == 0L)
        return "0";
      if (paramLong < 1000L)
        return String.format(Locale.US, "%dB", new Object[] { Long.valueOf(paramLong) });
      if (paramLong < 1000000L)
        return String.format(Locale.US, "%.1fkB", new Object[] { Float.valueOf((float)paramLong / 1000.0F) });
      if (paramLong < 1000000000L)
        return String.format(Locale.US, "%.1fM", new Object[] { Float.valueOf((float)paramLong / 1000000.0F) });
    }
    while (paramLong >= 1000000000000L);
    return String.format(Locale.US, "%.1fG", new Object[] { Float.valueOf((float)paramLong / 1.0E+009F) });
  }

  public static String getStorageInfo(int paramInt, long paramLong)
  {
    return String.format(Locale.CHINA, "共%d个文件，占用%s", new Object[] { Integer.valueOf(paramInt), getFileSize(paramLong) });
  }

  public static SizeInfo getSumInfo(SizeInfo paramSizeInfo1, SizeInfo paramSizeInfo2)
  {
    if (paramSizeInfo1 == null)
      return paramSizeInfo2;
    if (paramSizeInfo2 == null)
      return paramSizeInfo1;
    SizeInfo localSizeInfo = new SizeInfo();
    paramSizeInfo1.mCnt += paramSizeInfo2.mCnt;
    paramSizeInfo1.mFileSize += paramSizeInfo2.mFileSize;
    localSizeInfo.mSizeString = getFileSize(localSizeInfo.mFileSize);
    return localSizeInfo;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.scheduleview.SizeInfo
 * JD-Core Version:    0.6.2
 */