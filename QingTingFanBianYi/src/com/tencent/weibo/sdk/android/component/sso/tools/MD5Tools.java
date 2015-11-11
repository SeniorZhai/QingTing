package com.tencent.weibo.sdk.android.component.sso.tools;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class MD5Tools
{
  static final byte[] PADDING = arrayOfByte;
  static final int S11 = 7;
  static final int S12 = 12;
  static final int S13 = 17;
  static final int S14 = 22;
  static final int S21 = 5;
  static final int S22 = 9;
  static final int S23 = 14;
  static final int S24 = 20;
  static final int S31 = 4;
  static final int S32 = 11;
  static final int S33 = 16;
  static final int S34 = 23;
  static final int S41 = 6;
  static final int S42 = 10;
  static final int S43 = 15;
  static final int S44 = 21;
  private byte[] buffer = new byte[64];
  private long[] count = new long[2];
  private byte[] digest = new byte[16];
  public String digestHexStr;
  private long[] state = new long[4];

  static
  {
    byte[] arrayOfByte = new byte[64];
    arrayOfByte[0] = -128;
  }

  public MD5Tools()
  {
    md5Init();
  }

  private void Decode(long[] paramArrayOfLong, byte[] paramArrayOfByte, int paramInt)
  {
    int j = 0;
    int i = 0;
    while (true)
    {
      if (i >= paramInt)
        return;
      paramArrayOfLong[j] = (b2iu(paramArrayOfByte[i]) | b2iu(paramArrayOfByte[(i + 1)]) << 8 | b2iu(paramArrayOfByte[(i + 2)]) << 16 | b2iu(paramArrayOfByte[(i + 3)]) << 24);
      j += 1;
      i += 4;
    }
  }

  private void Encode(byte[] paramArrayOfByte, long[] paramArrayOfLong, int paramInt)
  {
    int j = 0;
    int i = 0;
    while (true)
    {
      if (i >= paramInt)
        return;
      paramArrayOfByte[i] = ((byte)(int)(paramArrayOfLong[j] & 0xFF));
      paramArrayOfByte[(i + 1)] = ((byte)(int)(paramArrayOfLong[j] >>> 8 & 0xFF));
      paramArrayOfByte[(i + 2)] = ((byte)(int)(paramArrayOfLong[j] >>> 16 & 0xFF));
      paramArrayOfByte[(i + 3)] = ((byte)(int)(paramArrayOfLong[j] >>> 24 & 0xFF));
      j += 1;
      i += 4;
    }
  }

  private long F(long paramLong1, long paramLong2, long paramLong3)
  {
    return paramLong1 & paramLong2 | (0xFFFFFFFF ^ paramLong1) & paramLong3;
  }

  private long FF(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    paramLong1 += F(paramLong2, paramLong3, paramLong4) + paramLong5 + paramLong7;
    return ((int)paramLong1 << (int)paramLong6 | (int)paramLong1 >>> (int)(32L - paramLong6)) + paramLong2;
  }

  private long G(long paramLong1, long paramLong2, long paramLong3)
  {
    return paramLong1 & paramLong3 | (0xFFFFFFFF ^ paramLong3) & paramLong2;
  }

  private long GG(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    paramLong1 += G(paramLong2, paramLong3, paramLong4) + paramLong5 + paramLong7;
    return ((int)paramLong1 << (int)paramLong6 | (int)paramLong1 >>> (int)(32L - paramLong6)) + paramLong2;
  }

  private long H(long paramLong1, long paramLong2, long paramLong3)
  {
    return paramLong1 ^ paramLong2 ^ paramLong3;
  }

  public static String HEXByte(byte[] paramArrayOfByte)
  {
    try
    {
      byte[] arrayOfByte = new byte[paramArrayOfByte.length / 2];
      int i = 0;
      while (true)
      {
        if (i >= arrayOfByte.length)
          return new String(arrayOfByte, "ISO-8859-1");
        arrayOfByte[i] = ((byte)((getByte(paramArrayOfByte[(i * 2)]) << 4) + getByte(paramArrayOfByte[(i * 2 + 1)])));
        i += 1;
      }
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return "";
  }

  private long HH(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    paramLong1 += H(paramLong2, paramLong3, paramLong4) + paramLong5 + paramLong7;
    return ((int)paramLong1 << (int)paramLong6 | (int)paramLong1 >>> (int)(32L - paramLong6)) + paramLong2;
  }

  private long I(long paramLong1, long paramLong2, long paramLong3)
  {
    return (0xFFFFFFFF ^ paramLong3 | paramLong1) ^ paramLong2;
  }

  private long II(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
  {
    paramLong1 += I(paramLong2, paramLong3, paramLong4) + paramLong5 + paramLong7;
    return ((int)paramLong1 << (int)paramLong6 | (int)paramLong1 >>> (int)(32L - paramLong6)) + paramLong2;
  }

  public static long b2iu(byte paramByte)
  {
    int i = paramByte;
    if (paramByte < 0)
      i = paramByte & 0xFF;
    return i;
  }

  public static String byteHEX(byte paramByte)
  {
    char[] arrayOfChar = new char[16];
    char[] tmp6_5 = arrayOfChar;
    tmp6_5[0] = 48;
    char[] tmp11_6 = tmp6_5;
    tmp11_6[1] = 49;
    char[] tmp16_11 = tmp11_6;
    tmp16_11[2] = 50;
    char[] tmp21_16 = tmp16_11;
    tmp21_16[3] = 51;
    char[] tmp26_21 = tmp21_16;
    tmp26_21[4] = 52;
    char[] tmp31_26 = tmp26_21;
    tmp31_26[5] = 53;
    char[] tmp36_31 = tmp31_26;
    tmp36_31[6] = 54;
    char[] tmp42_36 = tmp36_31;
    tmp42_36[7] = 55;
    char[] tmp48_42 = tmp42_36;
    tmp48_42[8] = 56;
    char[] tmp54_48 = tmp48_42;
    tmp54_48[9] = 57;
    char[] tmp60_54 = tmp54_48;
    tmp60_54[10] = 65;
    char[] tmp66_60 = tmp60_54;
    tmp66_60[11] = 66;
    char[] tmp72_66 = tmp66_60;
    tmp72_66[12] = 67;
    char[] tmp78_72 = tmp72_66;
    tmp78_72[13] = 68;
    char[] tmp84_78 = tmp78_72;
    tmp84_78[14] = 69;
    char[] tmp90_84 = tmp84_78;
    tmp90_84[15] = 70;
    tmp90_84;
    return new String(new char[] { arrayOfChar[(paramByte >>> 4 & 0xF)], arrayOfChar[(paramByte & 0xF)] });
  }

  private static byte getByte(byte paramByte)
  {
    byte b2 = 48;
    byte b1;
    if ((paramByte >= 48) && (paramByte <= 57))
      b1 = (byte)(paramByte - 48);
    do
    {
      do
      {
        return b1;
        if ((paramByte >= 97) && (paramByte <= 102))
          return (byte)(paramByte - 97 + 10);
        b1 = b2;
      }
      while (paramByte < 65);
      b1 = b2;
    }
    while (paramByte > 70);
    return (byte)(paramByte - 65 + 10);
  }

  // ERROR //
  public static String getFileMD5(java.io.File paramFile)
    throws java.io.FileNotFoundException
  {
    // Byte code:
    //   0: new 145	java/io/FileInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 148	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   8: astore_0
    //   9: sipush 1024
    //   12: newarray byte
    //   14: astore_2
    //   15: bipush 16
    //   17: newarray char
    //   19: astore_1
    //   20: aload_1
    //   21: dup
    //   22: iconst_0
    //   23: ldc 119
    //   25: castore
    //   26: dup
    //   27: iconst_1
    //   28: ldc 120
    //   30: castore
    //   31: dup
    //   32: iconst_2
    //   33: ldc 121
    //   35: castore
    //   36: dup
    //   37: iconst_3
    //   38: ldc 122
    //   40: castore
    //   41: dup
    //   42: iconst_4
    //   43: ldc 123
    //   45: castore
    //   46: dup
    //   47: iconst_5
    //   48: ldc 124
    //   50: castore
    //   51: dup
    //   52: bipush 6
    //   54: ldc 125
    //   56: castore
    //   57: dup
    //   58: bipush 7
    //   60: ldc 126
    //   62: castore
    //   63: dup
    //   64: bipush 8
    //   66: ldc 127
    //   68: castore
    //   69: dup
    //   70: bipush 9
    //   72: ldc 128
    //   74: castore
    //   75: dup
    //   76: bipush 10
    //   78: ldc 149
    //   80: castore
    //   81: dup
    //   82: bipush 11
    //   84: ldc 150
    //   86: castore
    //   87: dup
    //   88: bipush 12
    //   90: ldc 151
    //   92: castore
    //   93: dup
    //   94: bipush 13
    //   96: ldc 152
    //   98: castore
    //   99: dup
    //   100: bipush 14
    //   102: ldc 153
    //   104: castore
    //   105: dup
    //   106: bipush 15
    //   108: ldc 154
    //   110: castore
    //   111: pop
    //   112: ldc 156
    //   114: invokestatic 162	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   117: astore_3
    //   118: aload_0
    //   119: aload_2
    //   120: iconst_0
    //   121: sipush 1024
    //   124: invokevirtual 166	java/io/FileInputStream:read	([BII)I
    //   127: istore 4
    //   129: iload 4
    //   131: iconst_m1
    //   132: if_icmpne +41 -> 173
    //   135: aload_3
    //   136: invokevirtual 169	java/security/MessageDigest:digest	()[B
    //   139: astore_2
    //   140: bipush 32
    //   142: newarray char
    //   144: astore_3
    //   145: iconst_0
    //   146: istore 4
    //   148: iconst_0
    //   149: istore 5
    //   151: iload 4
    //   153: bipush 16
    //   155: if_icmplt +47 -> 202
    //   158: new 96	java/lang/String
    //   161: dup
    //   162: aload_3
    //   163: invokespecial 137	java/lang/String:<init>	([C)V
    //   166: astore_1
    //   167: aload_0
    //   168: invokevirtual 172	java/io/FileInputStream:close	()V
    //   171: aload_1
    //   172: areturn
    //   173: aload_3
    //   174: aload_2
    //   175: iconst_0
    //   176: iload 4
    //   178: invokevirtual 176	java/security/MessageDigest:update	([BII)V
    //   181: goto -63 -> 118
    //   184: astore_1
    //   185: aload_1
    //   186: invokevirtual 108	java/lang/Exception:printStackTrace	()V
    //   189: aload_0
    //   190: invokevirtual 172	java/io/FileInputStream:close	()V
    //   193: aconst_null
    //   194: areturn
    //   195: astore_0
    //   196: aload_0
    //   197: invokevirtual 177	java/io/IOException:printStackTrace	()V
    //   200: aconst_null
    //   201: areturn
    //   202: aload_2
    //   203: iload 4
    //   205: baload
    //   206: istore 6
    //   208: iload 5
    //   210: iconst_1
    //   211: iadd
    //   212: istore 7
    //   214: aload_3
    //   215: iload 5
    //   217: aload_1
    //   218: iload 6
    //   220: iconst_4
    //   221: iushr
    //   222: bipush 15
    //   224: iand
    //   225: caload
    //   226: castore
    //   227: iload 7
    //   229: iconst_1
    //   230: iadd
    //   231: istore 5
    //   233: aload_3
    //   234: iload 7
    //   236: aload_1
    //   237: iload 6
    //   239: bipush 15
    //   241: iand
    //   242: caload
    //   243: castore
    //   244: iload 4
    //   246: iconst_1
    //   247: iadd
    //   248: istore 4
    //   250: goto -99 -> 151
    //   253: astore_1
    //   254: aload_0
    //   255: invokevirtual 172	java/io/FileInputStream:close	()V
    //   258: aload_1
    //   259: athrow
    //   260: astore_0
    //   261: aload_0
    //   262: invokevirtual 177	java/io/IOException:printStackTrace	()V
    //   265: goto -7 -> 258
    //   268: astore_0
    //   269: aload_0
    //   270: invokevirtual 177	java/io/IOException:printStackTrace	()V
    //   273: aload_1
    //   274: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   112	118	184	java/lang/Exception
    //   118	129	184	java/lang/Exception
    //   135	145	184	java/lang/Exception
    //   158	167	184	java/lang/Exception
    //   173	181	184	java/lang/Exception
    //   189	193	195	java/io/IOException
    //   112	118	253	finally
    //   118	129	253	finally
    //   135	145	253	finally
    //   158	167	253	finally
    //   173	181	253	finally
    //   185	189	253	finally
    //   254	258	260	java/io/IOException
    //   167	171	268	java/io/IOException
  }

  public static String getMD5String(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[16];
    char[] tmp6_5 = arrayOfChar;
    tmp6_5[0] = 48;
    char[] tmp11_6 = tmp6_5;
    tmp11_6[1] = 49;
    char[] tmp16_11 = tmp11_6;
    tmp16_11[2] = 50;
    char[] tmp21_16 = tmp16_11;
    tmp21_16[3] = 51;
    char[] tmp26_21 = tmp21_16;
    tmp26_21[4] = 52;
    char[] tmp31_26 = tmp26_21;
    tmp31_26[5] = 53;
    char[] tmp36_31 = tmp31_26;
    tmp36_31[6] = 54;
    char[] tmp42_36 = tmp36_31;
    tmp42_36[7] = 55;
    char[] tmp48_42 = tmp42_36;
    tmp48_42[8] = 56;
    char[] tmp54_48 = tmp48_42;
    tmp54_48[9] = 57;
    char[] tmp60_54 = tmp54_48;
    tmp60_54[10] = 97;
    char[] tmp66_60 = tmp60_54;
    tmp66_60[11] = 98;
    char[] tmp72_66 = tmp66_60;
    tmp72_66[12] = 99;
    char[] tmp78_72 = tmp72_66;
    tmp78_72[13] = 100;
    char[] tmp84_78 = tmp78_72;
    tmp84_78[14] = 101;
    char[] tmp90_84 = tmp84_78;
    tmp90_84[15] = 102;
    tmp90_84;
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramArrayOfByte);
      paramArrayOfByte = ((MessageDigest)localObject).digest();
      localObject = new char[32];
      int i = 0;
      int j = 0;
      while (true)
      {
        if (i >= 16)
          return new String((char[])localObject);
        int k = paramArrayOfByte[i];
        int m = j + 1;
        localObject[j] = arrayOfChar[(k >>> 4 & 0xF)];
        j = m + 1;
        localObject[m] = arrayOfChar[(k & 0xF)];
        i += 1;
      }
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }

  private void md5Final()
  {
    byte[] arrayOfByte = new byte[8];
    Encode(arrayOfByte, this.count, 8);
    int i = (int)(this.count[0] >>> 3) & 0x3F;
    if (i < 56);
    for (i = 56 - i; ; i = 120 - i)
    {
      md5Update(PADDING, i);
      md5Update(arrayOfByte, 8);
      Encode(this.digest, this.state, 16);
      return;
    }
  }

  private void md5Init()
  {
    this.count[0] = 0L;
    this.count[1] = 0L;
    this.state[0] = 1732584193L;
    this.state[1] = 4023233417L;
    this.state[2] = 2562383102L;
    this.state[3] = 271733878L;
  }

  private void md5Memcpy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 0;
    while (true)
    {
      if (i >= paramInt3)
        return;
      paramArrayOfByte1[(paramInt1 + i)] = paramArrayOfByte2[(paramInt2 + i)];
      i += 1;
    }
  }

  private void md5Transform(byte[] paramArrayOfByte)
  {
    long l2 = this.state[0];
    long l1 = this.state[1];
    long l4 = this.state[2];
    long l3 = this.state[3];
    long[] arrayOfLong = new long[16];
    Decode(arrayOfLong, paramArrayOfByte, 64);
    l2 = FF(l2, l1, l4, l3, arrayOfLong[0], 7L, 3614090360L);
    l3 = FF(l3, l2, l1, l4, arrayOfLong[1], 12L, 3905402710L);
    l4 = FF(l4, l3, l2, l1, arrayOfLong[2], 17L, 606105819L);
    l1 = FF(l1, l4, l3, l2, arrayOfLong[3], 22L, 3250441966L);
    l2 = FF(l2, l1, l4, l3, arrayOfLong[4], 7L, 4118548399L);
    l3 = FF(l3, l2, l1, l4, arrayOfLong[5], 12L, 1200080426L);
    l4 = FF(l4, l3, l2, l1, arrayOfLong[6], 17L, 2821735955L);
    l1 = FF(l1, l4, l3, l2, arrayOfLong[7], 22L, 4249261313L);
    l2 = FF(l2, l1, l4, l3, arrayOfLong[8], 7L, 1770035416L);
    l3 = FF(l3, l2, l1, l4, arrayOfLong[9], 12L, 2336552879L);
    l4 = FF(l4, l3, l2, l1, arrayOfLong[10], 17L, 4294925233L);
    l1 = FF(l1, l4, l3, l2, arrayOfLong[11], 22L, 2304563134L);
    l2 = FF(l2, l1, l4, l3, arrayOfLong[12], 7L, 1804603682L);
    l3 = FF(l3, l2, l1, l4, arrayOfLong[13], 12L, 4254626195L);
    l4 = FF(l4, l3, l2, l1, arrayOfLong[14], 17L, 2792965006L);
    l1 = FF(l1, l4, l3, l2, arrayOfLong[15], 22L, 1236535329L);
    l2 = GG(l2, l1, l4, l3, arrayOfLong[1], 5L, 4129170786L);
    l3 = GG(l3, l2, l1, l4, arrayOfLong[6], 9L, 3225465664L);
    l4 = GG(l4, l3, l2, l1, arrayOfLong[11], 14L, 643717713L);
    l1 = GG(l1, l4, l3, l2, arrayOfLong[0], 20L, 3921069994L);
    l2 = GG(l2, l1, l4, l3, arrayOfLong[5], 5L, 3593408605L);
    l3 = GG(l3, l2, l1, l4, arrayOfLong[10], 9L, 38016083L);
    l4 = GG(l4, l3, l2, l1, arrayOfLong[15], 14L, 3634488961L);
    l1 = GG(l1, l4, l3, l2, arrayOfLong[4], 20L, 3889429448L);
    l2 = GG(l2, l1, l4, l3, arrayOfLong[9], 5L, 568446438L);
    l3 = GG(l3, l2, l1, l4, arrayOfLong[14], 9L, 3275163606L);
    l4 = GG(l4, l3, l2, l1, arrayOfLong[3], 14L, 4107603335L);
    l1 = GG(l1, l4, l3, l2, arrayOfLong[8], 20L, 1163531501L);
    l2 = GG(l2, l1, l4, l3, arrayOfLong[13], 5L, 2850285829L);
    l3 = GG(l3, l2, l1, l4, arrayOfLong[2], 9L, 4243563512L);
    l4 = GG(l4, l3, l2, l1, arrayOfLong[7], 14L, 1735328473L);
    l1 = GG(l1, l4, l3, l2, arrayOfLong[12], 20L, 2368359562L);
    l2 = HH(l2, l1, l4, l3, arrayOfLong[5], 4L, 4294588738L);
    l3 = HH(l3, l2, l1, l4, arrayOfLong[8], 11L, 2272392833L);
    l4 = HH(l4, l3, l2, l1, arrayOfLong[11], 16L, 1839030562L);
    l1 = HH(l1, l4, l3, l2, arrayOfLong[14], 23L, 4259657740L);
    l2 = HH(l2, l1, l4, l3, arrayOfLong[1], 4L, 2763975236L);
    l3 = HH(l3, l2, l1, l4, arrayOfLong[4], 11L, 1272893353L);
    l4 = HH(l4, l3, l2, l1, arrayOfLong[7], 16L, 4139469664L);
    l1 = HH(l1, l4, l3, l2, arrayOfLong[10], 23L, 3200236656L);
    l2 = HH(l2, l1, l4, l3, arrayOfLong[13], 4L, 681279174L);
    l3 = HH(l3, l2, l1, l4, arrayOfLong[0], 11L, 3936430074L);
    l4 = HH(l4, l3, l2, l1, arrayOfLong[3], 16L, 3572445317L);
    l1 = HH(l1, l4, l3, l2, arrayOfLong[6], 23L, 76029189L);
    l2 = HH(l2, l1, l4, l3, arrayOfLong[9], 4L, 3654602809L);
    l3 = HH(l3, l2, l1, l4, arrayOfLong[12], 11L, 3873151461L);
    l4 = HH(l4, l3, l2, l1, arrayOfLong[15], 16L, 530742520L);
    l1 = HH(l1, l4, l3, l2, arrayOfLong[2], 23L, 3299628645L);
    l2 = II(l2, l1, l4, l3, arrayOfLong[0], 6L, 4096336452L);
    l3 = II(l3, l2, l1, l4, arrayOfLong[7], 10L, 1126891415L);
    l4 = II(l4, l3, l2, l1, arrayOfLong[14], 15L, 2878612391L);
    l1 = II(l1, l4, l3, l2, arrayOfLong[5], 21L, 4237533241L);
    l2 = II(l2, l1, l4, l3, arrayOfLong[12], 6L, 1700485571L);
    l3 = II(l3, l2, l1, l4, arrayOfLong[3], 10L, 2399980690L);
    l4 = II(l4, l3, l2, l1, arrayOfLong[10], 15L, 4293915773L);
    l1 = II(l1, l4, l3, l2, arrayOfLong[1], 21L, 2240044497L);
    l2 = II(l2, l1, l4, l3, arrayOfLong[8], 6L, 1873313359L);
    l3 = II(l3, l2, l1, l4, arrayOfLong[15], 10L, 4264355552L);
    l4 = II(l4, l3, l2, l1, arrayOfLong[6], 15L, 2734768916L);
    l1 = II(l1, l4, l3, l2, arrayOfLong[13], 21L, 1309151649L);
    l2 = II(l2, l1, l4, l3, arrayOfLong[4], 6L, 4149444226L);
    l3 = II(l3, l2, l1, l4, arrayOfLong[11], 10L, 3174756917L);
    l4 = II(l4, l3, l2, l1, arrayOfLong[2], 15L, 718787259L);
    l1 = II(l1, l4, l3, l2, arrayOfLong[9], 21L, 3951481745L);
    paramArrayOfByte = this.state;
    paramArrayOfByte[0] += l2;
    paramArrayOfByte = this.state;
    paramArrayOfByte[1] += l1;
    paramArrayOfByte = this.state;
    paramArrayOfByte[2] += l4;
    paramArrayOfByte = this.state;
    paramArrayOfByte[3] += l3;
  }

  private void md5Update(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = new byte[64];
    int i = (int)(this.count[0] >>> 3) & 0x3F;
    long[] arrayOfLong = this.count;
    long l = arrayOfLong[0] + (paramInt << 3);
    arrayOfLong[0] = l;
    if (l < paramInt << 3)
    {
      arrayOfLong = this.count;
      arrayOfLong[1] += 1L;
    }
    arrayOfLong = this.count;
    arrayOfLong[1] += (paramInt >>> 29);
    int j = 64 - i;
    if (paramInt >= j)
    {
      md5Memcpy(this.buffer, paramArrayOfByte, i, 0, j);
      md5Transform(this.buffer);
      i = j;
      if (i + 63 >= paramInt)
      {
        int k = 0;
        j = i;
        i = k;
      }
    }
    while (true)
    {
      md5Memcpy(this.buffer, paramArrayOfByte, i, j, paramInt - j);
      return;
      md5Memcpy(arrayOfByte, paramArrayOfByte, 0, i, 64);
      md5Transform(arrayOfByte);
      i += 64;
      break;
      j = 0;
    }
  }

  // ERROR //
  private boolean md5Update(InputStream paramInputStream, long paramLong)
  {
    // Byte code:
    //   0: bipush 64
    //   2: newarray byte
    //   4: astore 4
    //   6: aload_0
    //   7: getfield 58	com/tencent/weibo/sdk/android/component/sso/tools/MD5Tools:count	[J
    //   10: iconst_0
    //   11: laload
    //   12: iconst_3
    //   13: lushr
    //   14: l2i
    //   15: bipush 63
    //   17: iand
    //   18: istore 6
    //   20: aload_0
    //   21: getfield 58	com/tencent/weibo/sdk/android/component/sso/tools/MD5Tools:count	[J
    //   24: astore 5
    //   26: aload 5
    //   28: iconst_0
    //   29: laload
    //   30: lload_2
    //   31: iconst_3
    //   32: lshl
    //   33: ladd
    //   34: lstore 9
    //   36: aload 5
    //   38: iconst_0
    //   39: lload 9
    //   41: lastore
    //   42: lload 9
    //   44: lload_2
    //   45: iconst_3
    //   46: lshl
    //   47: lcmp
    //   48: ifge +19 -> 67
    //   51: aload_0
    //   52: getfield 58	com/tencent/weibo/sdk/android/component/sso/tools/MD5Tools:count	[J
    //   55: astore 5
    //   57: aload 5
    //   59: iconst_1
    //   60: aload 5
    //   62: iconst_1
    //   63: laload
    //   64: lconst_1
    //   65: ladd
    //   66: lastore
    //   67: aload_0
    //   68: getfield 58	com/tencent/weibo/sdk/android/component/sso/tools/MD5Tools:count	[J
    //   71: astore 5
    //   73: aload 5
    //   75: iconst_1
    //   76: aload 5
    //   78: iconst_1
    //   79: laload
    //   80: lload_2
    //   81: bipush 29
    //   83: lushr
    //   84: ladd
    //   85: lastore
    //   86: bipush 64
    //   88: iload 6
    //   90: isub
    //   91: istore 7
    //   93: lload_2
    //   94: iload 7
    //   96: i2l
    //   97: lcmp
    //   98: iflt +140 -> 238
    //   101: iload 7
    //   103: newarray byte
    //   105: astore 5
    //   107: aload_1
    //   108: aload 5
    //   110: iconst_0
    //   111: iload 7
    //   113: invokevirtual 378	java/io/InputStream:read	([BII)I
    //   116: pop
    //   117: aload_0
    //   118: aload_0
    //   119: getfield 60	com/tencent/weibo/sdk/android/component/sso/tools/MD5Tools:buffer	[B
    //   122: aload 5
    //   124: iload 6
    //   126: iconst_0
    //   127: iload 7
    //   129: invokespecial 372	com/tencent/weibo/sdk/android/component/sso/tools/MD5Tools:md5Memcpy	([B[BIII)V
    //   132: aload_0
    //   133: aload_0
    //   134: getfield 60	com/tencent/weibo/sdk/android/component/sso/tools/MD5Tools:buffer	[B
    //   137: invokespecial 374	com/tencent/weibo/sdk/android/component/sso/tools/MD5Tools:md5Transform	([B)V
    //   140: iload 7
    //   142: istore 6
    //   144: iload 6
    //   146: bipush 63
    //   148: iadd
    //   149: i2l
    //   150: lload_2
    //   151: lcmp
    //   152: iflt +56 -> 208
    //   155: iconst_0
    //   156: istore 8
    //   158: iload 6
    //   160: istore 7
    //   162: iload 8
    //   164: istore 6
    //   166: lload_2
    //   167: iload 7
    //   169: i2l
    //   170: lsub
    //   171: l2i
    //   172: newarray byte
    //   174: astore 4
    //   176: aload_1
    //   177: aload 4
    //   179: invokevirtual 381	java/io/InputStream:read	([B)I
    //   182: pop
    //   183: aload_0
    //   184: aload_0
    //   185: getfield 60	com/tencent/weibo/sdk/android/component/sso/tools/MD5Tools:buffer	[B
    //   188: aload 4
    //   190: iload 6
    //   192: iconst_0
    //   193: aload 4
    //   195: arraylength
    //   196: invokespecial 372	com/tencent/weibo/sdk/android/component/sso/tools/MD5Tools:md5Memcpy	([B[BIII)V
    //   199: iconst_1
    //   200: ireturn
    //   201: astore_1
    //   202: aload_1
    //   203: invokevirtual 108	java/lang/Exception:printStackTrace	()V
    //   206: iconst_0
    //   207: ireturn
    //   208: aload_1
    //   209: aload 4
    //   211: invokevirtual 381	java/io/InputStream:read	([B)I
    //   214: pop
    //   215: aload_0
    //   216: aload 4
    //   218: invokespecial 374	com/tencent/weibo/sdk/android/component/sso/tools/MD5Tools:md5Transform	([B)V
    //   221: iload 6
    //   223: bipush 64
    //   225: iadd
    //   226: istore 6
    //   228: goto -84 -> 144
    //   231: astore_1
    //   232: aload_1
    //   233: invokevirtual 108	java/lang/Exception:printStackTrace	()V
    //   236: iconst_0
    //   237: ireturn
    //   238: iconst_0
    //   239: istore 7
    //   241: goto -75 -> 166
    //   244: astore_1
    //   245: aload_1
    //   246: invokevirtual 108	java/lang/Exception:printStackTrace	()V
    //   249: iconst_0
    //   250: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   107	117	201	java/lang/Exception
    //   208	215	231	java/lang/Exception
    //   176	183	244	java/lang/Exception
  }

  public static String toMD5(InputStream paramInputStream, long paramLong)
  {
    byte[] arrayOfByte = new MD5Tools().getMD5(paramInputStream, paramLong);
    paramInputStream = "";
    int i = 0;
    while (true)
    {
      if (i >= 16)
        return paramInputStream;
      paramInputStream = paramInputStream + byteHEX(arrayOfByte[i]);
      i += 1;
    }
  }

  public static String toMD5(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("ISO8859_1");
      paramString = arrayOfByte;
      arrayOfByte = new MD5Tools().getMD5(paramString);
      paramString = "";
      i = 0;
      if (i >= 16)
        return paramString;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        int i;
        paramString = paramString.getBytes();
        continue;
        paramString = paramString + byteHEX(localUnsupportedEncodingException[i]);
        i += 1;
      }
    }
  }

  public static String toMD5(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new MD5Tools().getMD5(paramArrayOfByte);
    paramArrayOfByte = "";
    int i = 0;
    while (true)
    {
      if (i >= 16)
        return paramArrayOfByte;
      paramArrayOfByte = paramArrayOfByte + byteHEX(arrayOfByte[i]);
      i += 1;
    }
  }

  public static byte[] toMD5Byte(InputStream paramInputStream, long paramLong)
  {
    return new MD5Tools().getMD5(paramInputStream, paramLong);
  }

  public static byte[] toMD5Byte(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("ISO8859_1");
      paramString = arrayOfByte;
      return new MD5Tools().getMD5(paramString);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        paramString = paramString.getBytes();
    }
  }

  public static byte[] toMD5Byte(byte[] paramArrayOfByte)
  {
    return new MD5Tools().getMD5(paramArrayOfByte);
  }

  public byte[] getMD5(InputStream paramInputStream, long paramLong)
  {
    md5Init();
    if (!md5Update(paramInputStream, paramLong))
      return new byte[16];
    md5Final();
    return this.digest;
  }

  public byte[] getMD5(byte[] paramArrayOfByte)
  {
    md5Init();
    md5Update(new ByteArrayInputStream(paramArrayOfByte), paramArrayOfByte.length);
    md5Final();
    return this.digest;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.sso.tools.MD5Tools
 * JD-Core Version:    0.6.2
 */