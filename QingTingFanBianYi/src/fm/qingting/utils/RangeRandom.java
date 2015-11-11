package fm.qingting.utils;

import java.util.Random;

public class RangeRandom
{
  private static Random random = new Random(System.nanoTime());

  public static long Random(long paramLong)
  {
    long l2 = random.nextLong();
    long l1 = l2;
    if (l2 < 0L)
      l1 = 0L - l2;
    return l1 % paramLong;
  }

  public static boolean random(double paramDouble)
  {
    return Random(1000000L) < 1000000L * paramDouble;
  }

  public static boolean randomSwitch()
  {
    return Random(1000000L) % 2L == 1L;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.RangeRandom
 * JD-Core Version:    0.6.2
 */