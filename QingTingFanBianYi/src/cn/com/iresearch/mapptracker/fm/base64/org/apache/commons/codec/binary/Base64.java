package cn.com.iresearch.mapptracker.fm.base64.org.apache.commons.codec.binary;

import java.math.BigInteger;

public class Base64 extends BaseNCodec
{
  private static final int BITS_PER_ENCODED_BYTE = 6;
  private static final int BYTES_PER_ENCODED_BLOCK = 4;
  private static final int BYTES_PER_UNENCODED_BLOCK = 3;
  static final byte[] CHUNK_SEPARATOR = { 13, 10 };
  private static final byte[] DECODE_TABLE = arrayOfByte;
  private static final int MASK_6BITS = 63;
  private static final byte[] STANDARD_ENCODE_TABLE = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  private static final byte[] URL_SAFE_ENCODE_TABLE = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
  private final int decodeSize;
  private final byte[] decodeTable;
  private final int encodeSize;
  private final byte[] encodeTable;
  private final byte[] lineSeparator;

  static
  {
    byte[] arrayOfByte = new byte[123];
    arrayOfByte[0] = -1;
    arrayOfByte[1] = -1;
    arrayOfByte[2] = -1;
    arrayOfByte[3] = -1;
    arrayOfByte[4] = -1;
    arrayOfByte[5] = -1;
    arrayOfByte[6] = -1;
    arrayOfByte[7] = -1;
    arrayOfByte[8] = -1;
    arrayOfByte[9] = -1;
    arrayOfByte[10] = -1;
    arrayOfByte[11] = -1;
    arrayOfByte[12] = -1;
    arrayOfByte[13] = -1;
    arrayOfByte[14] = -1;
    arrayOfByte[15] = -1;
    arrayOfByte[16] = -1;
    arrayOfByte[17] = -1;
    arrayOfByte[18] = -1;
    arrayOfByte[19] = -1;
    arrayOfByte[20] = -1;
    arrayOfByte[21] = -1;
    arrayOfByte[22] = -1;
    arrayOfByte[23] = -1;
    arrayOfByte[24] = -1;
    arrayOfByte[25] = -1;
    arrayOfByte[26] = -1;
    arrayOfByte[27] = -1;
    arrayOfByte[28] = -1;
    arrayOfByte[29] = -1;
    arrayOfByte[30] = -1;
    arrayOfByte[31] = -1;
    arrayOfByte[32] = -1;
    arrayOfByte[33] = -1;
    arrayOfByte[34] = -1;
    arrayOfByte[35] = -1;
    arrayOfByte[36] = -1;
    arrayOfByte[37] = -1;
    arrayOfByte[38] = -1;
    arrayOfByte[39] = -1;
    arrayOfByte[40] = -1;
    arrayOfByte[41] = -1;
    arrayOfByte[42] = -1;
    arrayOfByte[43] = 62;
    arrayOfByte[44] = -1;
    arrayOfByte[45] = 62;
    arrayOfByte[46] = -1;
    arrayOfByte[47] = 63;
    arrayOfByte[48] = 52;
    arrayOfByte[49] = 53;
    arrayOfByte[50] = 54;
    arrayOfByte[51] = 55;
    arrayOfByte[52] = 56;
    arrayOfByte[53] = 57;
    arrayOfByte[54] = 58;
    arrayOfByte[55] = 59;
    arrayOfByte[56] = 60;
    arrayOfByte[57] = 61;
    arrayOfByte[58] = -1;
    arrayOfByte[59] = -1;
    arrayOfByte[60] = -1;
    arrayOfByte[61] = -1;
    arrayOfByte[62] = -1;
    arrayOfByte[63] = -1;
    arrayOfByte[64] = -1;
    arrayOfByte[66] = 1;
    arrayOfByte[67] = 2;
    arrayOfByte[68] = 3;
    arrayOfByte[69] = 4;
    arrayOfByte[70] = 5;
    arrayOfByte[71] = 6;
    arrayOfByte[72] = 7;
    arrayOfByte[73] = 8;
    arrayOfByte[74] = 9;
    arrayOfByte[75] = 10;
    arrayOfByte[76] = 11;
    arrayOfByte[77] = 12;
    arrayOfByte[78] = 13;
    arrayOfByte[79] = 14;
    arrayOfByte[80] = 15;
    arrayOfByte[81] = 16;
    arrayOfByte[82] = 17;
    arrayOfByte[83] = 18;
    arrayOfByte[84] = 19;
    arrayOfByte[85] = 20;
    arrayOfByte[86] = 21;
    arrayOfByte[87] = 22;
    arrayOfByte[88] = 23;
    arrayOfByte[89] = 24;
    arrayOfByte[90] = 25;
    arrayOfByte[91] = -1;
    arrayOfByte[92] = -1;
    arrayOfByte[93] = -1;
    arrayOfByte[94] = -1;
    arrayOfByte[95] = 63;
    arrayOfByte[96] = -1;
    arrayOfByte[97] = 26;
    arrayOfByte[98] = 27;
    arrayOfByte[99] = 28;
    arrayOfByte[100] = 29;
    arrayOfByte[101] = 30;
    arrayOfByte[102] = 31;
    arrayOfByte[103] = 32;
    arrayOfByte[104] = 33;
    arrayOfByte[105] = 34;
    arrayOfByte[106] = 35;
    arrayOfByte[107] = 36;
    arrayOfByte[108] = 37;
    arrayOfByte[109] = 38;
    arrayOfByte[110] = 39;
    arrayOfByte[111] = 40;
    arrayOfByte[112] = 41;
    arrayOfByte[113] = 42;
    arrayOfByte[114] = 43;
    arrayOfByte[115] = 44;
    arrayOfByte[116] = 45;
    arrayOfByte[117] = 46;
    arrayOfByte[118] = 47;
    arrayOfByte[119] = 48;
    arrayOfByte[120] = 49;
    arrayOfByte[121] = 50;
    arrayOfByte[122] = 51;
  }

  public Base64()
  {
    this(0);
  }

  public Base64(int paramInt)
  {
    this(paramInt, CHUNK_SEPARATOR);
  }

  public Base64(int paramInt, byte[] paramArrayOfByte)
  {
    this(paramInt, paramArrayOfByte, false);
  }

  public Base64(int paramInt, byte[] paramArrayOfByte, boolean paramBoolean)
  {
  }

  public Base64(boolean paramBoolean)
  {
    this(76, CHUNK_SEPARATOR, paramBoolean);
  }

  public static byte[] decodeBase64(String paramString)
  {
    return new Base64().decode(paramString);
  }

  public static byte[] decodeBase64(byte[] paramArrayOfByte)
  {
    return new Base64().decode(paramArrayOfByte);
  }

  public static BigInteger decodeInteger(byte[] paramArrayOfByte)
  {
    return new BigInteger(1, decodeBase64(paramArrayOfByte));
  }

  public static byte[] encodeBase64(byte[] paramArrayOfByte)
  {
    return encodeBase64(paramArrayOfByte, false);
  }

  public static byte[] encodeBase64(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    return encodeBase64(paramArrayOfByte, paramBoolean, false);
  }

  public static byte[] encodeBase64(byte[] paramArrayOfByte, boolean paramBoolean1, boolean paramBoolean2)
  {
    return encodeBase64(paramArrayOfByte, paramBoolean1, paramBoolean2, 2147483647);
  }

  public static byte[] encodeBase64(byte[] paramArrayOfByte, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return paramArrayOfByte;
    if (paramBoolean1);
    for (Base64 localBase64 = new Base64(paramBoolean2); ; localBase64 = new Base64(0, CHUNK_SEPARATOR, paramBoolean2))
    {
      long l = localBase64.getEncodedLength(paramArrayOfByte);
      if (l <= paramInt)
        break;
      throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + l + ") than the specified maximum size of " + paramInt);
    }
    return localBase64.encode(paramArrayOfByte);
  }

  public static byte[] encodeBase64Chunked(byte[] paramArrayOfByte)
  {
    return encodeBase64(paramArrayOfByte, true);
  }

  public static String encodeBase64String(byte[] paramArrayOfByte)
  {
    return StringUtils.newStringUtf8(encodeBase64(paramArrayOfByte, false));
  }

  public static byte[] encodeBase64URLSafe(byte[] paramArrayOfByte)
  {
    return encodeBase64(paramArrayOfByte, false, true);
  }

  public static String encodeBase64URLSafeString(byte[] paramArrayOfByte)
  {
    return StringUtils.newStringUtf8(encodeBase64(paramArrayOfByte, false, true));
  }

  public static byte[] encodeInteger(BigInteger paramBigInteger)
  {
    if (paramBigInteger == null)
      throw new NullPointerException("encodeInteger called with null parameter");
    return encodeBase64(toIntegerBytes(paramBigInteger), false);
  }

  @Deprecated
  public static boolean isArrayByteBase64(byte[] paramArrayOfByte)
  {
    return isBase64(paramArrayOfByte);
  }

  public static boolean isBase64(byte paramByte)
  {
    return (paramByte == 61) || ((paramByte >= 0) && (paramByte < DECODE_TABLE.length) && (DECODE_TABLE[paramByte] != -1));
  }

  public static boolean isBase64(String paramString)
  {
    return isBase64(StringUtils.getBytesUtf8(paramString));
  }

  public static boolean isBase64(byte[] paramArrayOfByte)
  {
    boolean bool2 = false;
    int i = 0;
    while (true)
    {
      boolean bool1;
      if (i >= paramArrayOfByte.length)
        bool1 = true;
      do
      {
        return bool1;
        if (isBase64(paramArrayOfByte[i]))
          break;
        bool1 = bool2;
      }
      while (!isWhiteSpace(paramArrayOfByte[i]));
      i += 1;
    }
  }

  static byte[] toIntegerBytes(BigInteger paramBigInteger)
  {
    int m = paramBigInteger.bitLength() + 7 >> 3 << 3;
    byte[] arrayOfByte = paramBigInteger.toByteArray();
    if ((paramBigInteger.bitLength() % 8 != 0) && (paramBigInteger.bitLength() / 8 + 1 == m / 8))
      return arrayOfByte;
    int j = 0;
    int k = arrayOfByte.length;
    int i = k;
    if (paramBigInteger.bitLength() % 8 == 0)
    {
      j = 1;
      i = k - 1;
    }
    k = m / 8;
    paramBigInteger = new byte[m / 8];
    System.arraycopy(arrayOfByte, j, paramBigInteger, k - i, i);
    return paramBigInteger;
  }

  void decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BaseNCodec.Context paramContext)
  {
    if (paramContext.eof)
      return;
    if (paramInt2 < 0)
      paramContext.eof = true;
    int i = 0;
    while (true)
    {
      if (i >= paramInt2);
      byte[] arrayOfByte;
      int j;
      while ((paramContext.eof) && (paramContext.modulus != 0))
      {
        paramArrayOfByte = ensureBufferSize(this.decodeSize, paramContext);
        switch (paramContext.modulus)
        {
        case 1:
        default:
          throw new IllegalStateException("Impossible modulus " + paramContext.modulus);
          arrayOfByte = ensureBufferSize(this.decodeSize, paramContext);
          j = paramArrayOfByte[paramInt1];
          if (j != 61)
            break label150;
          paramContext.eof = true;
        case 2:
        case 3:
        }
      }
      label150: if ((j >= 0) && (j < DECODE_TABLE.length))
      {
        j = DECODE_TABLE[j];
        if (j >= 0)
        {
          paramContext.modulus = ((paramContext.modulus + 1) % 4);
          paramContext.ibitWorkArea = (j + (paramContext.ibitWorkArea << 6));
          if (paramContext.modulus == 0)
          {
            j = paramContext.pos;
            paramContext.pos = (j + 1);
            arrayOfByte[j] = ((byte)(paramContext.ibitWorkArea >> 16));
            j = paramContext.pos;
            paramContext.pos = (j + 1);
            arrayOfByte[j] = ((byte)(paramContext.ibitWorkArea >> 8));
            j = paramContext.pos;
            paramContext.pos = (j + 1);
            arrayOfByte[j] = ((byte)paramContext.ibitWorkArea);
          }
        }
      }
      i += 1;
      paramInt1 += 1;
    }
    paramContext.ibitWorkArea >>= 4;
    paramInt1 = paramContext.pos;
    paramContext.pos = (paramInt1 + 1);
    paramArrayOfByte[paramInt1] = ((byte)paramContext.ibitWorkArea);
    return;
    paramContext.ibitWorkArea >>= 2;
    paramInt1 = paramContext.pos;
    paramContext.pos = (paramInt1 + 1);
    paramArrayOfByte[paramInt1] = ((byte)(paramContext.ibitWorkArea >> 8));
    paramInt1 = paramContext.pos;
    paramContext.pos = (paramInt1 + 1);
    paramArrayOfByte[paramInt1] = ((byte)paramContext.ibitWorkArea);
  }

  void encode(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BaseNCodec.Context paramContext)
  {
    if (paramContext.eof);
    while (true)
    {
      return;
      if (paramInt2 < 0)
      {
        paramContext.eof = true;
        if ((paramContext.modulus != 0) || (this.lineLength != 0))
        {
          paramArrayOfByte = ensureBufferSize(this.encodeSize, paramContext);
          paramInt1 = paramContext.pos;
          switch (paramContext.modulus)
          {
          default:
            throw new IllegalStateException("Impossible modulus " + paramContext.modulus);
          case 1:
            paramInt2 = paramContext.pos;
            paramContext.pos = (paramInt2 + 1);
            paramArrayOfByte[paramInt2] = this.encodeTable[(paramContext.ibitWorkArea >> 2 & 0x3F)];
            paramInt2 = paramContext.pos;
            paramContext.pos = (paramInt2 + 1);
            paramArrayOfByte[paramInt2] = this.encodeTable[(paramContext.ibitWorkArea << 4 & 0x3F)];
            if (this.encodeTable == STANDARD_ENCODE_TABLE)
            {
              paramInt2 = paramContext.pos;
              paramContext.pos = (paramInt2 + 1);
              paramArrayOfByte[paramInt2] = 61;
              paramInt2 = paramContext.pos;
              paramContext.pos = (paramInt2 + 1);
              paramArrayOfByte[paramInt2] = 61;
            }
            break;
          case 0:
          case 2:
          }
          while (true)
          {
            paramInt2 = paramContext.currentLinePos;
            paramContext.currentLinePos = (paramContext.pos - paramInt1 + paramInt2);
            if ((this.lineLength <= 0) || (paramContext.currentLinePos <= 0))
              break;
            System.arraycopy(this.lineSeparator, 0, paramArrayOfByte, paramContext.pos, this.lineSeparator.length);
            paramContext.pos += this.lineSeparator.length;
            return;
            paramInt2 = paramContext.pos;
            paramContext.pos = (paramInt2 + 1);
            paramArrayOfByte[paramInt2] = this.encodeTable[(paramContext.ibitWorkArea >> 10 & 0x3F)];
            paramInt2 = paramContext.pos;
            paramContext.pos = (paramInt2 + 1);
            paramArrayOfByte[paramInt2] = this.encodeTable[(paramContext.ibitWorkArea >> 4 & 0x3F)];
            paramInt2 = paramContext.pos;
            paramContext.pos = (paramInt2 + 1);
            paramArrayOfByte[paramInt2] = this.encodeTable[(paramContext.ibitWorkArea << 2 & 0x3F)];
            if (this.encodeTable == STANDARD_ENCODE_TABLE)
            {
              paramInt2 = paramContext.pos;
              paramContext.pos = (paramInt2 + 1);
              paramArrayOfByte[paramInt2] = 61;
            }
          }
        }
      }
      else
      {
        int i = 0;
        while (i < paramInt2)
        {
          byte[] arrayOfByte = ensureBufferSize(this.encodeSize, paramContext);
          paramContext.modulus = ((paramContext.modulus + 1) % 3);
          int k = paramArrayOfByte[paramInt1];
          int j = k;
          if (k < 0)
            j = k + 256;
          paramContext.ibitWorkArea = (j + (paramContext.ibitWorkArea << 8));
          if (paramContext.modulus == 0)
          {
            j = paramContext.pos;
            paramContext.pos = (j + 1);
            arrayOfByte[j] = this.encodeTable[(paramContext.ibitWorkArea >> 18 & 0x3F)];
            j = paramContext.pos;
            paramContext.pos = (j + 1);
            arrayOfByte[j] = this.encodeTable[(paramContext.ibitWorkArea >> 12 & 0x3F)];
            j = paramContext.pos;
            paramContext.pos = (j + 1);
            arrayOfByte[j] = this.encodeTable[(paramContext.ibitWorkArea >> 6 & 0x3F)];
            j = paramContext.pos;
            paramContext.pos = (j + 1);
            arrayOfByte[j] = this.encodeTable[(paramContext.ibitWorkArea & 0x3F)];
            paramContext.currentLinePos += 4;
            if ((this.lineLength > 0) && (this.lineLength <= paramContext.currentLinePos))
            {
              System.arraycopy(this.lineSeparator, 0, arrayOfByte, paramContext.pos, this.lineSeparator.length);
              paramContext.pos += this.lineSeparator.length;
              paramContext.currentLinePos = 0;
            }
          }
          i += 1;
          paramInt1 += 1;
        }
      }
    }
  }

  protected boolean isInAlphabet(byte paramByte)
  {
    return (paramByte >= 0) && (paramByte < this.decodeTable.length) && (this.decodeTable[paramByte] != -1);
  }

  public boolean isUrlSafe()
  {
    return this.encodeTable == URL_SAFE_ENCODE_TABLE;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.base64.org.apache.commons.codec.binary.Base64
 * JD-Core Version:    0.6.2
 */