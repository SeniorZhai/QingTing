package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.security.MessageDigest;
import java.util.Locale;

public class c
{
  private final byte[] a = { 0, 0, 0, 0, 0, 0, 0, 0 };
  private String b = "1.0";
  private String c = null;
  private byte[] d = null;
  private byte[] e = null;
  private byte[] f = null;
  private int g = 0;
  private int h = 0;
  private int i = 0;
  private byte[] j = null;
  private byte[] k = null;

  private c(byte[] paramArrayOfByte1, String paramString, byte[] paramArrayOfByte2)
    throws Exception
  {
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length == 0))
      throw new Exception("entity is null or empty");
    this.c = paramString;
    this.i = paramArrayOfByte1.length;
    this.j = bu.a(paramArrayOfByte1);
    this.h = ((int)(System.currentTimeMillis() / 1000L));
    this.k = paramArrayOfByte2;
  }

  public static c a(Context paramContext, String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      String str1 = bi.p(paramContext);
      String str2 = bi.f(paramContext);
      paramContext = u.a(paramContext);
      String str3 = paramContext.getString("signature", null);
      int m = paramContext.getInt("serial", 1);
      paramString = new c(paramArrayOfByte, paramString, (str2 + str1).getBytes());
      paramString.a(str3);
      paramString.a(m);
      paramString.b();
      paramContext.edit().putInt("serial", m + 1).putString("signature", paramString.a()).commit();
      return paramString;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }

  public static byte[] a(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.reset();
      localMessageDigest.update(paramArrayOfByte);
      paramArrayOfByte = localMessageDigest.digest();
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }

  private byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    int i4 = 0;
    byte[] arrayOfByte1 = a(this.k);
    byte[] arrayOfByte2 = a(this.j);
    int i5 = arrayOfByte1.length;
    byte[] arrayOfByte3 = new byte[i5 * 2];
    int i3 = 0;
    while (i3 < i5)
    {
      arrayOfByte3[(i3 * 2)] = arrayOfByte2[i3];
      arrayOfByte3[(i3 * 2 + 1)] = arrayOfByte1[i3];
      i3 += 1;
    }
    i3 = 0;
    while (i3 < 2)
    {
      arrayOfByte3[i3] = paramArrayOfByte[i3];
      arrayOfByte3[(arrayOfByte3.length - i3 - 1)] = paramArrayOfByte[(paramArrayOfByte.length - i3 - 1)];
      i3 += 1;
    }
    int m = (byte)(paramInt & 0xFF);
    int n = (byte)(paramInt >> 8 & 0xFF);
    int i1 = (byte)(paramInt >> 16 & 0xFF);
    int i2 = (byte)(paramInt >>> 24);
    paramInt = i4;
    while (paramInt < arrayOfByte3.length)
    {
      arrayOfByte3[paramInt] = ((byte)(arrayOfByte3[paramInt] ^ new byte[] { m, n, i1, i2 }[(paramInt % 4)]));
      paramInt += 1;
    }
    return arrayOfByte3;
  }

  public static String b(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int m = 0;
    while (m < paramArrayOfByte.length)
    {
      localStringBuffer.append(String.format("%02X", new Object[] { Byte.valueOf(paramArrayOfByte[m]) }));
      m += 1;
    }
    return localStringBuffer.toString().toLowerCase(Locale.US);
  }

  public static byte[] b(String paramString)
  {
    Object localObject = null;
    if (paramString == null);
    int n;
    do
    {
      return localObject;
      n = paramString.length();
    }
    while (n % 2 != 0);
    byte[] arrayOfByte = new byte[n / 2];
    int m = 0;
    while (true)
    {
      localObject = arrayOfByte;
      if (m >= n)
        break;
      arrayOfByte[(m / 2)] = ((byte)Integer.valueOf(paramString.substring(m, m + 2), 16).intValue());
      m += 2;
    }
  }

  private byte[] d()
  {
    return a(this.a, (int)(System.currentTimeMillis() / 1000L));
  }

  private byte[] e()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(b(this.d));
    localStringBuilder.append(this.g);
    localStringBuilder.append(this.h);
    localStringBuilder.append(this.i);
    localStringBuilder.append(b(this.e));
    return a(localStringBuilder.toString().getBytes());
  }

  public String a()
  {
    return b(this.d);
  }

  public void a(int paramInt)
  {
    this.g = paramInt;
  }

  public void a(String paramString)
  {
    this.d = b(paramString);
  }

  public void b()
  {
    if (this.d == null)
      this.d = d();
    this.e = a(this.d, this.h);
    this.f = e();
  }

  public byte[] c()
  {
    Object localObject = new bh();
    ((bh)localObject).a(this.b);
    ((bh)localObject).b(this.c);
    ((bh)localObject).c(b(this.d));
    ((bh)localObject).a(this.g);
    ((bh)localObject).c(this.h);
    ((bh)localObject).d(this.i);
    ((bh)localObject).a(this.j);
    ((bh)localObject).d(b(this.e));
    ((bh)localObject).e(b(this.f));
    try
    {
      localObject = new ci().a((bz)localObject);
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(String.format("version : %s\n", new Object[] { this.b }));
    localStringBuilder.append(String.format("address : %s\n", new Object[] { this.c }));
    localStringBuilder.append(String.format("signature : %s\n", new Object[] { b(this.d) }));
    localStringBuilder.append(String.format("serial : %s\n", new Object[] { Integer.valueOf(this.g) }));
    localStringBuilder.append(String.format("timestamp : %d\n", new Object[] { Integer.valueOf(this.h) }));
    localStringBuilder.append(String.format("length : %d\n", new Object[] { Integer.valueOf(this.i) }));
    localStringBuilder.append(String.format("guid : %s\n", new Object[] { b(this.e) }));
    localStringBuilder.append(String.format("checksum : %s ", new Object[] { b(this.f) }));
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.c
 * JD-Core Version:    0.6.2
 */