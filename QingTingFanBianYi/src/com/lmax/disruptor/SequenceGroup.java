package com.lmax.disruptor;

import com.lmax.disruptor.util.Util;
import java.util.concurrent.atomic.AtomicReference;

public final class SequenceGroup extends Sequence
{
  private final AtomicReference<Sequence[]> sequencesRef = new AtomicReference(new Sequence[0]);

  public SequenceGroup()
  {
    super(-1L);
  }

  public void add(Sequence paramSequence)
  {
    Sequence[] arrayOfSequence1;
    Sequence[] arrayOfSequence2;
    do
    {
      arrayOfSequence1 = (Sequence[])this.sequencesRef.get();
      int i = arrayOfSequence1.length;
      arrayOfSequence2 = new Sequence[i + 1];
      System.arraycopy(arrayOfSequence1, 0, arrayOfSequence2, 0, i);
      arrayOfSequence2[i] = paramSequence;
    }
    while (!this.sequencesRef.compareAndSet(arrayOfSequence1, arrayOfSequence2));
  }

  public long get()
  {
    if (((Sequence[])this.sequencesRef.get()).length != 0)
      return Util.getMinimumSequence((Sequence[])this.sequencesRef.get());
    return -1L;
  }

  public boolean remove(Sequence paramSequence)
  {
    boolean bool1 = false;
    Sequence[] arrayOfSequence1;
    Sequence[] arrayOfSequence2;
    boolean bool2;
    do
    {
      arrayOfSequence1 = (Sequence[])this.sequencesRef.get();
      int m = arrayOfSequence1.length;
      arrayOfSequence2 = new Sequence[m - 1];
      int j = 0;
      int i = 0;
      bool2 = bool1;
      if (j < m)
      {
        Sequence localSequence = arrayOfSequence1[j];
        if ((paramSequence == localSequence) && (!bool2))
          bool2 = true;
        while (true)
        {
          j += 1;
          break;
          int k = i + 1;
          arrayOfSequence2[i] = localSequence;
          i = k;
        }
      }
      if (!bool2)
        return bool2;
      bool1 = bool2;
    }
    while (!this.sequencesRef.compareAndSet(arrayOfSequence1, arrayOfSequence2));
    return bool2;
  }

  public void set(long paramLong)
  {
    Sequence[] arrayOfSequence = (Sequence[])this.sequencesRef.get();
    int i = 0;
    int j = arrayOfSequence.length;
    while (i < j)
    {
      arrayOfSequence[i].set(paramLong);
      i += 1;
    }
  }

  public int size()
  {
    return ((Sequence[])this.sequencesRef.get()).length;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.SequenceGroup
 * JD-Core Version:    0.6.2
 */