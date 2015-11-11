package com.lmax.disruptor.util;

import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.Sequence;
import java.lang.reflect.Field;
import sun.misc.Unsafe;

public final class Util
{
  private static final Unsafe THE_UNSAFE;

  static
  {
    try
    {
      Field localField = Unsafe.class.getDeclaredField("THE_ONE");
      localField.setAccessible(true);
      THE_UNSAFE = (Unsafe)localField.get(null);
      return;
    }
    catch (Exception localException)
    {
      throw new RuntimeException("Unable to load unsafe", localException);
    }
  }

  public static int ceilingNextPowerOfTwo(int paramInt)
  {
    return 1 << 32 - Integer.numberOfLeadingZeros(paramInt - 1);
  }

  public static long getMinimumSequence(Sequence[] paramArrayOfSequence)
  {
    long l1 = 9223372036854775807L;
    int j = paramArrayOfSequence.length;
    int i = 0;
    if (i < j)
    {
      long l2 = paramArrayOfSequence[i].get();
      if (l1 < l2);
      while (true)
      {
        i += 1;
        break;
        l1 = l2;
      }
    }
    return l1;
  }

  public static Sequence[] getSequencesFor(EventProcessor[] paramArrayOfEventProcessor)
  {
    Sequence[] arrayOfSequence = new Sequence[paramArrayOfEventProcessor.length];
    int i = 0;
    while (i < arrayOfSequence.length)
    {
      arrayOfSequence[i] = paramArrayOfEventProcessor[i].getSequence();
      i += 1;
    }
    return arrayOfSequence;
  }

  public static Unsafe getUnsafe()
  {
    return THE_UNSAFE;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.util.Util
 * JD-Core Version:    0.6.2
 */