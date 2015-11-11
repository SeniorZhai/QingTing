package fm.qingting.async.http.libcore;

import java.nio.ByteOrder;

public final class Memory
{
  public static native void memmove(Object paramObject1, int paramInt1, Object paramObject2, int paramInt2, long paramLong);

  public static native byte peekByte(int paramInt);

  public static native void peekByteArray(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3);

  public static native void peekCharArray(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3, boolean paramBoolean);

  public static native void peekDoubleArray(int paramInt1, double[] paramArrayOfDouble, int paramInt2, int paramInt3, boolean paramBoolean);

  public static native void peekFloatArray(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3, boolean paramBoolean);

  public static native int peekInt(int paramInt, boolean paramBoolean);

  public static int peekInt(byte[] paramArrayOfByte, int paramInt, ByteOrder paramByteOrder)
  {
    if (paramByteOrder == ByteOrder.BIG_ENDIAN)
    {
      i = paramInt + 1;
      paramInt = paramArrayOfByte[paramInt];
      j = i + 1;
      return (paramArrayOfByte[i] & 0xFF) << 16 | (paramInt & 0xFF) << 24 | (paramArrayOfByte[j] & 0xFF) << 8 | (paramArrayOfByte[(j + 1)] & 0xFF) << 0;
    }
    int i = paramInt + 1;
    paramInt = paramArrayOfByte[paramInt];
    int j = i + 1;
    return (paramArrayOfByte[i] & 0xFF) << 8 | (paramInt & 0xFF) << 0 | (paramArrayOfByte[j] & 0xFF) << 16 | (paramArrayOfByte[(j + 1)] & 0xFF) << 24;
  }

  public static native void peekIntArray(int paramInt1, int[] paramArrayOfInt, int paramInt2, int paramInt3, boolean paramBoolean);

  public static native long peekLong(int paramInt, boolean paramBoolean);

  public static long peekLong(byte[] paramArrayOfByte, int paramInt, ByteOrder paramByteOrder)
  {
    if (paramByteOrder == ByteOrder.BIG_ENDIAN)
    {
      i = paramInt + 1;
      paramInt = paramArrayOfByte[paramInt];
      j = i + 1;
      i = paramArrayOfByte[i];
      k = j + 1;
      j = paramArrayOfByte[j];
      m = k + 1;
      k = paramArrayOfByte[k];
      i1 = m + 1;
      m = paramArrayOfByte[m];
      n = i1 + 1;
      i1 = paramArrayOfByte[i1];
      int i2 = paramArrayOfByte[n];
      n = paramArrayOfByte[(n + 1)];
      long l = (i & 0xFF) << 16 | (paramInt & 0xFF) << 24 | (j & 0xFF) << 8 | (k & 0xFF) << 0;
      return ((i1 & 0xFF) << 16 | (m & 0xFF) << 24 | (i2 & 0xFF) << 8 | (n & 0xFF) << 0) & 0xFFFFFFFF | l << 32;
    }
    int i = paramInt + 1;
    paramInt = paramArrayOfByte[paramInt];
    int j = i + 1;
    i = paramArrayOfByte[i];
    int k = j + 1;
    j = paramArrayOfByte[j];
    int n = k + 1;
    k = paramArrayOfByte[k];
    int m = n + 1;
    n = paramArrayOfByte[n];
    int i1 = m + 1;
    return ((paramArrayOfByte[m] & 0xFF) << 8 | (n & 0xFF) << 0 | (paramArrayOfByte[i1] & 0xFF) << 16 | (paramArrayOfByte[(i1 + 1)] & 0xFF) << 24) << 32 | ((i & 0xFF) << 8 | (paramInt & 0xFF) << 0 | (j & 0xFF) << 16 | (k & 0xFF) << 24) & 0xFFFFFFFF;
  }

  public static native void peekLongArray(int paramInt1, long[] paramArrayOfLong, int paramInt2, int paramInt3, boolean paramBoolean);

  public static native short peekShort(int paramInt, boolean paramBoolean);

  public static short peekShort(byte[] paramArrayOfByte, int paramInt, ByteOrder paramByteOrder)
  {
    if (paramByteOrder == ByteOrder.BIG_ENDIAN)
      return (short)(paramArrayOfByte[paramInt] << 8 | paramArrayOfByte[(paramInt + 1)] & 0xFF);
    return (short)(paramArrayOfByte[(paramInt + 1)] << 8 | paramArrayOfByte[paramInt] & 0xFF);
  }

  public static native void peekShortArray(int paramInt1, short[] paramArrayOfShort, int paramInt2, int paramInt3, boolean paramBoolean);

  public static native void pokeByte(int paramInt, byte paramByte);

  public static native void pokeByteArray(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3);

  public static native void pokeCharArray(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3, boolean paramBoolean);

  public static native void pokeDoubleArray(int paramInt1, double[] paramArrayOfDouble, int paramInt2, int paramInt3, boolean paramBoolean);

  public static native void pokeFloatArray(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3, boolean paramBoolean);

  public static native void pokeInt(int paramInt1, int paramInt2, boolean paramBoolean);

  public static void pokeInt(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ByteOrder paramByteOrder)
  {
    if (paramByteOrder == ByteOrder.BIG_ENDIAN)
    {
      i = paramInt1 + 1;
      paramArrayOfByte[paramInt1] = ((byte)(paramInt2 >> 24 & 0xFF));
      paramInt1 = i + 1;
      paramArrayOfByte[i] = ((byte)(paramInt2 >> 16 & 0xFF));
      paramArrayOfByte[paramInt1] = ((byte)(paramInt2 >> 8 & 0xFF));
      paramArrayOfByte[(paramInt1 + 1)] = ((byte)(paramInt2 >> 0 & 0xFF));
      return;
    }
    int i = paramInt1 + 1;
    paramArrayOfByte[paramInt1] = ((byte)(paramInt2 >> 0 & 0xFF));
    paramInt1 = i + 1;
    paramArrayOfByte[i] = ((byte)(paramInt2 >> 8 & 0xFF));
    paramArrayOfByte[paramInt1] = ((byte)(paramInt2 >> 16 & 0xFF));
    paramArrayOfByte[(paramInt1 + 1)] = ((byte)(paramInt2 >> 24 & 0xFF));
  }

  public static native void pokeIntArray(int paramInt1, int[] paramArrayOfInt, int paramInt2, int paramInt3, boolean paramBoolean);

  public static native void pokeLong(int paramInt, long paramLong, boolean paramBoolean);

  public static void pokeLong(byte[] paramArrayOfByte, int paramInt, long paramLong, ByteOrder paramByteOrder)
  {
    if (paramByteOrder == ByteOrder.BIG_ENDIAN)
    {
      i = (int)(paramLong >> 32);
      j = paramInt + 1;
      paramArrayOfByte[paramInt] = ((byte)(i >> 24 & 0xFF));
      paramInt = j + 1;
      paramArrayOfByte[j] = ((byte)(i >> 16 & 0xFF));
      j = paramInt + 1;
      paramArrayOfByte[paramInt] = ((byte)(i >> 8 & 0xFF));
      paramInt = j + 1;
      paramArrayOfByte[j] = ((byte)(i >> 0 & 0xFF));
      i = (int)paramLong;
      j = paramInt + 1;
      paramArrayOfByte[paramInt] = ((byte)(i >> 24 & 0xFF));
      paramInt = j + 1;
      paramArrayOfByte[j] = ((byte)(i >> 16 & 0xFF));
      paramArrayOfByte[paramInt] = ((byte)(i >> 8 & 0xFF));
      paramArrayOfByte[(paramInt + 1)] = ((byte)(i >> 0 & 0xFF));
      return;
    }
    int i = (int)paramLong;
    int j = paramInt + 1;
    paramArrayOfByte[paramInt] = ((byte)(i >> 0 & 0xFF));
    paramInt = j + 1;
    paramArrayOfByte[j] = ((byte)(i >> 8 & 0xFF));
    j = paramInt + 1;
    paramArrayOfByte[paramInt] = ((byte)(i >> 16 & 0xFF));
    paramInt = j + 1;
    paramArrayOfByte[j] = ((byte)(i >> 24 & 0xFF));
    i = (int)(paramLong >> 32);
    j = paramInt + 1;
    paramArrayOfByte[paramInt] = ((byte)(i >> 0 & 0xFF));
    paramInt = j + 1;
    paramArrayOfByte[j] = ((byte)(i >> 8 & 0xFF));
    paramArrayOfByte[paramInt] = ((byte)(i >> 16 & 0xFF));
    paramArrayOfByte[(paramInt + 1)] = ((byte)(i >> 24 & 0xFF));
  }

  public static native void pokeLongArray(int paramInt1, long[] paramArrayOfLong, int paramInt2, int paramInt3, boolean paramBoolean);

  public static native void pokeShort(int paramInt, short paramShort, boolean paramBoolean);

  public static void pokeShort(byte[] paramArrayOfByte, int paramInt, short paramShort, ByteOrder paramByteOrder)
  {
    if (paramByteOrder == ByteOrder.BIG_ENDIAN)
    {
      paramArrayOfByte[paramInt] = ((byte)(paramShort >> 8 & 0xFF));
      paramArrayOfByte[(paramInt + 1)] = ((byte)(paramShort >> 0 & 0xFF));
      return;
    }
    paramArrayOfByte[paramInt] = ((byte)(paramShort >> 0 & 0xFF));
    paramArrayOfByte[(paramInt + 1)] = ((byte)(paramShort >> 8 & 0xFF));
  }

  public static native void pokeShortArray(int paramInt1, short[] paramArrayOfShort, int paramInt2, int paramInt3, boolean paramBoolean);

  public static native void unsafeBulkGet(Object paramObject, int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3, int paramInt4, boolean paramBoolean);

  public static native void unsafeBulkPut(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Object paramObject, int paramInt3, int paramInt4, boolean paramBoolean);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.libcore.Memory
 * JD-Core Version:    0.6.2
 */