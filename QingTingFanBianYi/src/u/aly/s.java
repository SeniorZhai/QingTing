package u.aly;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class s
{
  public static Object a(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return null;
    try
    {
      paramString = new ObjectInputStream(new ByteArrayInputStream(b(paramString))).readObject();
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  public static String a(Serializable paramSerializable)
  {
    if (paramSerializable == null)
      return "";
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
      localObjectOutputStream.writeObject(paramSerializable);
      localObjectOutputStream.close();
      paramSerializable = a(localByteArrayOutputStream.toByteArray());
      return paramSerializable;
    }
    catch (Exception paramSerializable)
    {
      paramSerializable.printStackTrace();
    }
    return null;
  }

  public static String a(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      localStringBuffer.append((char)((paramArrayOfByte[i] >> 4 & 0xF) + 97));
      localStringBuffer.append((char)((paramArrayOfByte[i] & 0xF) + 97));
      i += 1;
    }
    return localStringBuffer.toString();
  }

  public static byte[] b(String paramString)
  {
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    int i = 0;
    while (i < paramString.length())
    {
      int j = paramString.charAt(i);
      arrayOfByte[(i / 2)] = ((byte)(j - 97 << 4));
      j = paramString.charAt(i + 1);
      int k = i / 2;
      arrayOfByte[k] = ((byte)(j - 97 + arrayOfByte[k]));
      i += 2;
    }
    return arrayOfByte;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.s
 * JD-Core Version:    0.6.2
 */