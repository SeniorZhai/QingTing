package master.flame.danmaku.danmaku.model;

public class GlobalFlagValues
{
  public static int MEASURE_RESET_FLAG = 0;
  public static int VISIBLE_RESET_FLAG = 0;

  public static void resetAll()
  {
    VISIBLE_RESET_FLAG = 0;
    MEASURE_RESET_FLAG = 0;
  }

  public static void updateMeasureFlag()
  {
    MEASURE_RESET_FLAG += 1;
  }

  public static void updateVisibleFlag()
  {
    VISIBLE_RESET_FLAG += 1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.GlobalFlagValues
 * JD-Core Version:    0.6.2
 */