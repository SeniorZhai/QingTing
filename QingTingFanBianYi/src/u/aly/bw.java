package u.aly;

public class bw
{
  public static final byte a(byte paramByte, int paramInt, boolean paramBoolean)
  {
    return (byte)a(paramByte, paramInt, paramBoolean);
  }

  public static final int a(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramBoolean)
      return 1 << paramInt2 | paramInt1;
    return b(paramInt1, paramInt2);
  }

  public static final int a(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, 0);
  }

  public static final int a(byte[] paramArrayOfByte, int paramInt)
  {
    return (paramArrayOfByte[paramInt] & 0xFF) << 24 | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 16 | (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 8 | paramArrayOfByte[(paramInt + 3)] & 0xFF;
  }

  public static final long a(long paramLong, int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
      return 1L << paramInt | paramLong;
    return b(paramLong, paramInt);
  }

  public static final short a(short paramShort, int paramInt, boolean paramBoolean)
  {
    return (short)a(paramShort, paramInt, paramBoolean);
  }

  public static final void a(int paramInt, byte[] paramArrayOfByte)
  {
    a(paramInt, paramArrayOfByte, 0);
  }

  public static final void a(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 >> 24 & 0xFF));
    paramArrayOfByte[(paramInt2 + 1)] = ((byte)(paramInt1 >> 16 & 0xFF));
    paramArrayOfByte[(paramInt2 + 2)] = ((byte)(paramInt1 >> 8 & 0xFF));
    paramArrayOfByte[(paramInt2 + 3)] = ((byte)(paramInt1 & 0xFF));
  }

  public static final boolean a(byte paramByte, int paramInt)
  {
    return a(paramByte, paramInt);
  }

  public static final boolean a(int paramInt1, int paramInt2)
  {
    return (1 << paramInt2 & paramInt1) != 0;
  }

  public static final boolean a(long paramLong, int paramInt)
  {
    return (1L << paramInt & paramLong) != 0L;
  }

  public static final boolean a(short paramShort, int paramInt)
  {
    return a(paramShort, paramInt);
  }

  public static final byte b(byte paramByte, int paramInt)
  {
    return (byte)b(paramByte, paramInt);
  }

  public static final int b(int paramInt1, int paramInt2)
  {
    return (1 << paramInt2 ^ 0xFFFFFFFF) & paramInt1;
  }

  public static final long b(long paramLong, int paramInt)
  {
    return (1L << paramInt ^ 0xFFFFFFFF) & paramLong;
  }

  public static final short b(short paramShort, int paramInt)
  {
    return (short)b(paramShort, paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.bw
 * JD-Core Version:    0.6.2
 */