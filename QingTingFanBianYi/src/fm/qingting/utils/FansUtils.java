package fm.qingting.utils;

public class FansUtils
{
  public static String toString(long paramLong)
  {
    if (paramLong <= 0L)
      return "暂无粉丝";
    if (paramLong < 10000L)
      return String.format("%d个粉丝", new Object[] { Long.valueOf(paramLong) });
    return String.format("%.1f万粉丝", new Object[] { Float.valueOf((float)paramLong / 10000.0F) });
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.FansUtils
 * JD-Core Version:    0.6.2
 */