package fm.qingting.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util
{
  protected static char[] hexDigits = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  protected static MessageDigest messagedigest = null;

  static
  {
    try
    {
      messagedigest = MessageDigest.getInstance("MD5");
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
  }

  private static void appendHexPair(byte paramByte, StringBuffer paramStringBuffer)
  {
    char c1 = hexDigits[((paramByte & 0xF0) >> 4)];
    char c2 = hexDigits[(paramByte & 0xF)];
    paramStringBuffer.append(c1);
    paramStringBuffer.append(c2);
  }

  private static String bufferToHex(byte[] paramArrayOfByte)
  {
    return bufferToHex(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  private static String bufferToHex(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramInt2 * 2);
    int i = paramInt1;
    while (i < paramInt1 + paramInt2)
    {
      appendHexPair(paramArrayOfByte[i], localStringBuffer);
      i += 1;
    }
    return localStringBuffer.toString();
  }

  public static String getFileMD5(Context paramContext)
    throws Exception
  {
    if ((messagedigest == null) || (paramContext == null))
      return "";
    paramContext = new File(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.publicSourceDir);
    paramContext = new FileInputStream(paramContext).getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, paramContext.length());
    messagedigest.update(paramContext);
    return bufferToHex(messagedigest.digest());
  }

  public static String getSignMD5(Context paramContext)
    throws Exception
  {
    int j = 0;
    paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64);
    Object localObject = MessageDigest.getInstance("MD5");
    ((MessageDigest)localObject).update(paramContext.signatures[0].toCharsString().getBytes());
    paramContext = ((MessageDigest)localObject).digest();
    localObject = new char[16];
    Object tmp50_49 = localObject;
    tmp50_49[0] = 48;
    Object tmp55_50 = tmp50_49;
    tmp55_50[1] = 49;
    Object tmp60_55 = tmp55_50;
    tmp60_55[2] = 50;
    Object tmp65_60 = tmp60_55;
    tmp65_60[3] = 51;
    Object tmp70_65 = tmp65_60;
    tmp70_65[4] = 52;
    Object tmp75_70 = tmp70_65;
    tmp75_70[5] = 53;
    Object tmp80_75 = tmp75_70;
    tmp80_75[6] = 54;
    Object tmp86_80 = tmp80_75;
    tmp86_80[7] = 55;
    Object tmp92_86 = tmp86_80;
    tmp92_86[8] = 56;
    Object tmp98_92 = tmp92_86;
    tmp98_92[9] = 57;
    Object tmp104_98 = tmp98_92;
    tmp104_98[10] = 97;
    Object tmp110_104 = tmp104_98;
    tmp110_104[11] = 98;
    Object tmp116_110 = tmp110_104;
    tmp116_110[12] = 99;
    Object tmp122_116 = tmp116_110;
    tmp122_116[13] = 100;
    Object tmp128_122 = tmp122_116;
    tmp128_122[14] = 101;
    Object tmp134_128 = tmp128_122;
    tmp134_128[15] = 102;
    tmp134_128;
    StringBuilder localStringBuilder = new StringBuilder(paramContext.length * 2);
    int i = 0;
    while (i < paramContext.length)
    {
      localStringBuilder.append(localObject[((paramContext[i] & 0xF0) >>> 4)]);
      localStringBuilder.append(localObject[(paramContext[i] & 0xF)]);
      i += 1;
    }
    paramContext = localStringBuilder.toString();
    if ((paramContext == null) || (paramContext.length() < 32))
      return "-1";
    paramContext = paramContext.substring(8, 24);
    long l1 = 0L;
    i = j;
    while (i < 8)
    {
      l1 = l1 * 16L + Integer.parseInt(paramContext.substring(i, i + 1), 16);
      i += 1;
    }
    i = 8;
    long l2 = 0L;
    while (i < paramContext.length())
    {
      l2 = l2 * 16L + Integer.parseInt(paramContext.substring(i, i + 1), 16);
      i += 1;
    }
    return String.valueOf(l2 + l1 & 0xFFFFFFFF);
  }

  public static String md5(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = ((MessageDigest)localObject).digest();
      StringBuffer localStringBuffer = new StringBuffer();
      int i = 0;
      while (i < localObject.length)
      {
        localStringBuffer.append(Integer.toHexString(localObject[i] & 0xFF));
        i += 1;
      }
      localObject = localStringBuffer.toString();
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.MD5Util
 * JD-Core Version:    0.6.2
 */