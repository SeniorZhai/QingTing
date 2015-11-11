package u.aly;

import java.util.BitSet;

public final class de extends cs
{
  public de(dm paramdm)
  {
    super(paramdm);
  }

  public static BitSet a(byte[] paramArrayOfByte)
  {
    BitSet localBitSet = new BitSet();
    int i = 0;
    while (i < paramArrayOfByte.length * 8)
    {
      if ((paramArrayOfByte[(paramArrayOfByte.length - i / 8 - 1)] & 1 << i % 8) > 0)
        localBitSet.set(i);
      i += 1;
    }
    return localBitSet;
  }

  public static byte[] b(BitSet paramBitSet, int paramInt)
  {
    byte[] arrayOfByte = new byte[(int)Math.ceil(paramInt / 8.0D)];
    paramInt = 0;
    while (paramInt < paramBitSet.length())
    {
      if (paramBitSet.get(paramInt))
      {
        int i = arrayOfByte.length - paramInt / 8 - 1;
        arrayOfByte[i] = ((byte)(arrayOfByte[i] | 1 << paramInt % 8));
      }
      paramInt += 1;
    }
    return arrayOfByte;
  }

  public Class<? extends dg> D()
  {
    return dj.class;
  }

  public void a(BitSet paramBitSet, int paramInt)
    throws cf
  {
    paramBitSet = b(paramBitSet, paramInt);
    int i = paramBitSet.length;
    paramInt = 0;
    while (paramInt < i)
    {
      a(paramBitSet[paramInt]);
      paramInt += 1;
    }
  }

  public BitSet b(int paramInt)
    throws cf
  {
    int i = (int)Math.ceil(paramInt / 8.0D);
    byte[] arrayOfByte = new byte[i];
    paramInt = 0;
    while (paramInt < i)
    {
      arrayOfByte[paramInt] = u();
      paramInt += 1;
    }
    return a(arrayOfByte);
  }

  public static class a
    implements da
  {
    public cy a(dm paramdm)
    {
      return new de(paramdm);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.de
 * JD-Core Version:    0.6.2
 */