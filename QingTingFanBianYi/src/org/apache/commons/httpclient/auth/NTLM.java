package org.apache.commons.httpclient.auth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.util.EncodingUtil;

final class NTLM
{
  public static final String DEFAULT_CHARSET = "ASCII";
  private String credentialCharset = "ASCII";
  private int currentPosition = 0;
  private byte[] currentResponse;

  private void addByte(byte paramByte)
  {
    this.currentResponse[this.currentPosition] = paramByte;
    this.currentPosition += 1;
  }

  private void addBytes(byte[] paramArrayOfByte)
  {
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfByte.length)
        return;
      this.currentResponse[this.currentPosition] = paramArrayOfByte[i];
      this.currentPosition += 1;
      i += 1;
    }
  }

  private void calcResp(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws AuthenticationException
  {
    byte[] arrayOfByte3 = new byte[7];
    byte[] arrayOfByte2 = new byte[7];
    byte[] arrayOfByte1 = new byte[7];
    int i = 0;
    if (i >= 7)
    {
      i = 0;
      label31: if (i < 7)
        break label122;
      i = 0;
      label41: if (i < 7)
        break label143;
      paramArrayOfByte1 = encrypt(arrayOfByte3, paramArrayOfByte2);
      arrayOfByte2 = encrypt(arrayOfByte2, paramArrayOfByte2);
      paramArrayOfByte2 = encrypt(arrayOfByte1, paramArrayOfByte2);
      i = 0;
      label76: if (i < 8)
        break label164;
      i = 0;
      label86: if (i < 8)
        break label181;
      i = 0;
    }
    while (true)
    {
      if (i >= 8)
      {
        return;
        arrayOfByte3[i] = paramArrayOfByte1[i];
        i += 1;
        break;
        label122: arrayOfByte2[i] = paramArrayOfByte1[(i + 7)];
        i += 1;
        break label31;
        label143: arrayOfByte1[i] = paramArrayOfByte1[(i + 14)];
        i += 1;
        break label41;
        label164: paramArrayOfByte3[i] = paramArrayOfByte1[i];
        i += 1;
        break label76;
        label181: paramArrayOfByte3[(i + 8)] = arrayOfByte2[i];
        i += 1;
        break label86;
      }
      paramArrayOfByte3[(i + 16)] = paramArrayOfByte2[i];
      i += 1;
    }
  }

  private byte[] convertShort(int paramInt)
  {
    for (String str1 = Integer.toString(paramInt, 16); ; str1 = "0" + str1)
      if (str1.length() >= 4)
      {
        String str2 = str1.substring(2, 4);
        str1 = str1.substring(0, 2);
        return new byte[] { (byte)Integer.parseInt(str2, 16), (byte)Integer.parseInt(str1, 16) };
      }
  }

  private byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws AuthenticationException
  {
    paramArrayOfByte1 = getCipher(paramArrayOfByte1);
    try
    {
      paramArrayOfByte1 = paramArrayOfByte1.doFinal(paramArrayOfByte2);
      return paramArrayOfByte1;
    }
    catch (IllegalBlockSizeException paramArrayOfByte1)
    {
      throw new AuthenticationException("Invalid block size for DES encryption.", paramArrayOfByte1);
    }
    catch (BadPaddingException paramArrayOfByte1)
    {
    }
    throw new AuthenticationException("Data not padded correctly for DES encryption.", paramArrayOfByte1);
  }

  private Cipher getCipher(byte[] paramArrayOfByte)
    throws AuthenticationException
  {
    try
    {
      Cipher localCipher = Cipher.getInstance("DES/ECB/NoPadding");
      localCipher.init(1, new SecretKeySpec(setupKey(paramArrayOfByte), "DES"));
      return localCipher;
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
      throw new AuthenticationException("DES encryption is not available.", paramArrayOfByte);
    }
    catch (InvalidKeyException paramArrayOfByte)
    {
      throw new AuthenticationException("Invalid key for DES encryption.", paramArrayOfByte);
    }
    catch (NoSuchPaddingException paramArrayOfByte)
    {
    }
    throw new AuthenticationException("NoPadding option for DES is not available.", paramArrayOfByte);
  }

  private String getResponse()
  {
    byte[] arrayOfByte;
    int i;
    if (this.currentResponse.length > this.currentPosition)
    {
      arrayOfByte = new byte[this.currentPosition];
      i = 0;
      if (i < this.currentPosition);
    }
    while (true)
    {
      return EncodingUtil.getAsciiString(Base64.encodeBase64(arrayOfByte));
      arrayOfByte[i] = this.currentResponse[i];
      i += 1;
      break;
      arrayOfByte = this.currentResponse;
    }
  }

  private byte[] hashPassword(String paramString, byte[] paramArrayOfByte)
    throws AuthenticationException
  {
    byte[] arrayOfByte2 = EncodingUtil.getBytes(paramString.toUpperCase(), this.credentialCharset);
    byte[] arrayOfByte1 = new byte[7];
    paramString = new byte[7];
    int i = arrayOfByte2.length;
    int j = i;
    if (i > 7)
      j = 7;
    i = 0;
    if (i >= j)
    {
      label53: if (i < 7)
        break label233;
      i = arrayOfByte2.length;
      j = i;
      if (i > 14)
        j = 14;
      i = 7;
      label84: if (i < j)
        break label247;
      label91: if (i < 14)
        break label268;
      arrayOfByte2 = new byte[8];
      byte[] tmp106_104 = arrayOfByte2;
      tmp106_104[0] = 75;
      byte[] tmp111_106 = tmp106_104;
      tmp111_106[1] = 71;
      byte[] tmp116_111 = tmp111_106;
      tmp116_111[2] = 83;
      byte[] tmp121_116 = tmp116_111;
      tmp121_116[3] = 33;
      byte[] tmp126_121 = tmp121_116;
      tmp126_121[4] = 64;
      byte[] tmp131_126 = tmp126_121;
      tmp131_126[5] = 35;
      byte[] tmp136_131 = tmp131_126;
      tmp136_131[6] = 36;
      byte[] tmp142_136 = tmp136_131;
      tmp142_136[7] = 37;
      tmp142_136;
      arrayOfByte1 = encrypt(arrayOfByte1, arrayOfByte2);
      arrayOfByte2 = encrypt(paramString, arrayOfByte2);
      paramString = new byte[21];
      i = 0;
      label174: if (i < arrayOfByte1.length)
        break label285;
      i = 0;
      label184: if (i < arrayOfByte2.length)
        break label302;
      i = 0;
    }
    while (true)
    {
      if (i >= 5)
      {
        arrayOfByte1 = new byte[24];
        calcResp(paramString, paramArrayOfByte, arrayOfByte1);
        return arrayOfByte1;
        arrayOfByte1[i] = arrayOfByte2[i];
        i += 1;
        break;
        label233: arrayOfByte1[i] = 0;
        i += 1;
        break label53;
        label247: paramString[(i - 7)] = arrayOfByte2[i];
        i += 1;
        break label84;
        label268: paramString[(i - 7)] = 0;
        i += 1;
        break label91;
        label285: paramString[i] = arrayOfByte1[i];
        i += 1;
        break label174;
        label302: paramString[(i + 8)] = arrayOfByte2[i];
        i += 1;
        break label184;
      }
      paramString[(i + 16)] = 0;
      i += 1;
    }
  }

  private void prepareResponse(int paramInt)
  {
    this.currentResponse = new byte[paramInt];
    this.currentPosition = 0;
  }

  private byte[] setupKey(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[8];
    arrayOfByte[0] = ((byte)(paramArrayOfByte[0] >> 1 & 0xFF));
    arrayOfByte[1] = ((byte)(((paramArrayOfByte[0] & 0x1) << 6 | (paramArrayOfByte[1] & 0xFF) >> 2 & 0xFF) & 0xFF));
    arrayOfByte[2] = ((byte)(((paramArrayOfByte[1] & 0x3) << 5 | (paramArrayOfByte[2] & 0xFF) >> 3 & 0xFF) & 0xFF));
    arrayOfByte[3] = ((byte)(((paramArrayOfByte[2] & 0x7) << 4 | (paramArrayOfByte[3] & 0xFF) >> 4 & 0xFF) & 0xFF));
    arrayOfByte[4] = ((byte)(((paramArrayOfByte[3] & 0xF) << 3 | (paramArrayOfByte[4] & 0xFF) >> 5 & 0xFF) & 0xFF));
    arrayOfByte[5] = ((byte)(((paramArrayOfByte[4] & 0x1F) << 2 | (paramArrayOfByte[5] & 0xFF) >> 6 & 0xFF) & 0xFF));
    arrayOfByte[6] = ((byte)(((paramArrayOfByte[5] & 0x3F) << 1 | (paramArrayOfByte[6] & 0xFF) >> 7 & 0xFF) & 0xFF));
    arrayOfByte[7] = ((byte)(paramArrayOfByte[6] & 0x7F));
    int i = 0;
    while (true)
    {
      if (i >= arrayOfByte.length)
        return arrayOfByte;
      arrayOfByte[i] = ((byte)(arrayOfByte[i] << 1));
      i += 1;
    }
  }

  public String getCredentialCharset()
  {
    return this.credentialCharset;
  }

  public final String getResponseFor(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws AuthenticationException
  {
    if ((paramString1 == null) || (paramString1.trim().equals("")))
      return getType1Message(paramString4, paramString5);
    return getType3Message(paramString2, paramString3, paramString4, paramString5, parseType2Message(paramString1));
  }

  public String getType1Message(String paramString1, String paramString2)
  {
    paramString1 = paramString1.toUpperCase();
    paramString2 = paramString2.toUpperCase();
    paramString1 = EncodingUtil.getBytes(paramString1, "ASCII");
    paramString2 = EncodingUtil.getBytes(paramString2, "ASCII");
    prepareResponse(paramString1.length + 32 + paramString2.length);
    addBytes(EncodingUtil.getBytes("NTLMSSP", "ASCII"));
    addByte((byte)0);
    addByte((byte)1);
    addByte((byte)0);
    addByte((byte)0);
    addByte((byte)0);
    addByte((byte)6);
    addByte((byte)82);
    addByte((byte)0);
    addByte((byte)0);
    byte[] arrayOfByte = convertShort(paramString2.length);
    addByte(arrayOfByte[0]);
    addByte(arrayOfByte[1]);
    addByte(arrayOfByte[0]);
    addByte(arrayOfByte[1]);
    arrayOfByte = convertShort(paramString1.length + 32);
    addByte(arrayOfByte[0]);
    addByte(arrayOfByte[1]);
    addByte((byte)0);
    addByte((byte)0);
    arrayOfByte = convertShort(paramString1.length);
    addByte(arrayOfByte[0]);
    addByte(arrayOfByte[1]);
    addByte(arrayOfByte[0]);
    addByte(arrayOfByte[1]);
    arrayOfByte = convertShort(32);
    addByte(arrayOfByte[0]);
    addByte(arrayOfByte[1]);
    addByte((byte)0);
    addByte((byte)0);
    addBytes(paramString1);
    addBytes(paramString2);
    return getResponse();
  }

  public String getType3Message(String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte)
    throws AuthenticationException
  {
    paramString4 = paramString4.toUpperCase();
    paramString3 = paramString3.toUpperCase();
    paramString1 = paramString1.toUpperCase();
    paramString4 = EncodingUtil.getBytes(paramString4, "ASCII");
    paramString3 = EncodingUtil.getBytes(paramString3, "ASCII");
    paramString1 = EncodingUtil.getBytes(paramString1, this.credentialCharset);
    int i = paramString4.length;
    int k = paramString3.length;
    int m = paramString1.length;
    int j = i + 88 + m + k;
    prepareResponse(j);
    addBytes(EncodingUtil.getBytes("NTLMSSP", "ASCII"));
    addByte((byte)0);
    addByte((byte)3);
    addByte((byte)0);
    addByte((byte)0);
    addByte((byte)0);
    addBytes(convertShort(24));
    addBytes(convertShort(24));
    addBytes(convertShort(j - 24));
    addByte((byte)0);
    addByte((byte)0);
    addBytes(convertShort(0));
    addBytes(convertShort(0));
    addBytes(convertShort(j));
    addByte((byte)0);
    addByte((byte)0);
    addBytes(convertShort(i));
    addBytes(convertShort(i));
    addBytes(convertShort(64));
    addByte((byte)0);
    addByte((byte)0);
    addBytes(convertShort(m));
    addBytes(convertShort(m));
    addBytes(convertShort(i + 64));
    addByte((byte)0);
    addByte((byte)0);
    addBytes(convertShort(k));
    addBytes(convertShort(k));
    addBytes(convertShort(i + 64 + m));
    i = 0;
    while (true)
    {
      if (i >= 6)
      {
        addBytes(convertShort(j));
        addByte((byte)0);
        addByte((byte)0);
        addByte((byte)6);
        addByte((byte)82);
        addByte((byte)0);
        addByte((byte)0);
        addBytes(paramString4);
        addBytes(paramString1);
        addBytes(paramString3);
        addBytes(hashPassword(paramString2, paramArrayOfByte));
        return getResponse();
      }
      addByte((byte)0);
      i += 1;
    }
  }

  public byte[] parseType2Message(String paramString)
  {
    paramString = Base64.decodeBase64(EncodingUtil.getBytes(paramString, "ASCII"));
    byte[] arrayOfByte = new byte[8];
    int i = 0;
    while (true)
    {
      if (i >= 8)
        return arrayOfByte;
      arrayOfByte[i] = paramString[(i + 24)];
      i += 1;
    }
  }

  public void setCredentialCharset(String paramString)
  {
    this.credentialCharset = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.NTLM
 * JD-Core Version:    0.6.2
 */