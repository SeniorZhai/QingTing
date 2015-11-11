package weibo4android.http;

import java.io.IOException;
import java.io.InputStream;

public class ImageItem
{
  private String ImageType;
  private byte[] content;
  private String name;

  public ImageItem(String paramString, byte[] paramArrayOfByte)
    throws Exception
  {
    String str = getImageType(paramArrayOfByte);
    if ((str != null) && ((str.equalsIgnoreCase("image/gif")) || (str.equalsIgnoreCase("image/png")) || (str.equalsIgnoreCase("image/jpeg"))))
    {
      this.content = paramArrayOfByte;
      this.name = paramString;
      this.ImageType = str;
      return;
    }
    throw new IllegalStateException("Unsupported image type, Only Suport JPG ,GIF,PNG!");
  }

  public ImageItem(byte[] paramArrayOfByte)
    throws Exception
  {
    this("pic", paramArrayOfByte);
  }

  // ERROR //
  public static String getImageType(java.io.File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_2
    //   3: astore_1
    //   4: aload_0
    //   5: ifnull +12 -> 17
    //   8: aload_0
    //   9: invokevirtual 61	java/io/File:exists	()Z
    //   12: ifne +7 -> 19
    //   15: aload_2
    //   16: astore_1
    //   17: aload_1
    //   18: areturn
    //   19: new 63	java/io/FileInputStream
    //   22: dup
    //   23: aload_0
    //   24: invokespecial 66	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   27: astore_0
    //   28: aload_0
    //   29: invokestatic 69	weibo4android/http/ImageItem:getImageType	(Ljava/io/InputStream;)Ljava/lang/String;
    //   32: astore_1
    //   33: aload_1
    //   34: astore_2
    //   35: aload_2
    //   36: astore_1
    //   37: aload_0
    //   38: ifnull -21 -> 17
    //   41: aload_0
    //   42: invokevirtual 74	java/io/InputStream:close	()V
    //   45: aload_2
    //   46: areturn
    //   47: astore_0
    //   48: aload_2
    //   49: areturn
    //   50: astore_0
    //   51: aconst_null
    //   52: astore_0
    //   53: aload_2
    //   54: astore_1
    //   55: aload_0
    //   56: ifnull -39 -> 17
    //   59: aload_0
    //   60: invokevirtual 74	java/io/InputStream:close	()V
    //   63: aconst_null
    //   64: areturn
    //   65: astore_0
    //   66: aconst_null
    //   67: areturn
    //   68: astore_1
    //   69: aconst_null
    //   70: astore_0
    //   71: aload_0
    //   72: ifnull +7 -> 79
    //   75: aload_0
    //   76: invokevirtual 74	java/io/InputStream:close	()V
    //   79: aload_1
    //   80: athrow
    //   81: astore_0
    //   82: goto -3 -> 79
    //   85: astore_1
    //   86: goto -15 -> 71
    //   89: astore_1
    //   90: goto -37 -> 53
    //
    // Exception table:
    //   from	to	target	type
    //   41	45	47	java/io/IOException
    //   19	28	50	java/io/IOException
    //   59	63	65	java/io/IOException
    //   19	28	68	finally
    //   75	79	81	java/io/IOException
    //   28	33	85	finally
    //   28	33	89	java/io/IOException
  }

  public static String getImageType(InputStream paramInputStream)
  {
    if (paramInputStream == null)
      return null;
    try
    {
      byte[] arrayOfByte = new byte[8];
      paramInputStream.read(arrayOfByte);
      paramInputStream = getImageType(arrayOfByte);
      return paramInputStream;
    }
    catch (IOException paramInputStream)
    {
    }
    return null;
  }

  public static String getImageType(byte[] paramArrayOfByte)
  {
    if (isJPEG(paramArrayOfByte))
      return "image/jpeg";
    if (isGIF(paramArrayOfByte))
      return "image/gif";
    if (isPNG(paramArrayOfByte))
      return "image/png";
    if (isBMP(paramArrayOfByte))
      return "application/x-bmp";
    return null;
  }

  private static boolean isBMP(byte[] paramArrayOfByte)
  {
    boolean bool = true;
    if (paramArrayOfByte.length < 2)
      return false;
    if ((paramArrayOfByte[0] == 66) && (paramArrayOfByte[1] == 77));
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  private static boolean isGIF(byte[] paramArrayOfByte)
  {
    boolean bool = true;
    if (paramArrayOfByte.length < 6)
      return false;
    if ((paramArrayOfByte[0] == 71) && (paramArrayOfByte[1] == 73) && (paramArrayOfByte[2] == 70) && (paramArrayOfByte[3] == 56) && ((paramArrayOfByte[4] == 55) || (paramArrayOfByte[4] == 57)) && (paramArrayOfByte[5] == 97));
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  private static boolean isJPEG(byte[] paramArrayOfByte)
  {
    boolean bool = true;
    if (paramArrayOfByte.length < 2)
      return false;
    if ((paramArrayOfByte[0] == -1) && (paramArrayOfByte[1] == -40));
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  private static boolean isPNG(byte[] paramArrayOfByte)
  {
    boolean bool = true;
    if (paramArrayOfByte.length < 8)
      return false;
    if ((paramArrayOfByte[0] == -119) && (paramArrayOfByte[1] == 80) && (paramArrayOfByte[2] == 78) && (paramArrayOfByte[3] == 71) && (paramArrayOfByte[4] == 13) && (paramArrayOfByte[5] == 10) && (paramArrayOfByte[6] == 26) && (paramArrayOfByte[7] == 10));
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public byte[] getContent()
  {
    return this.content;
  }

  public String getImageType()
  {
    return this.ImageType;
  }

  public String getName()
  {
    return this.name;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.http.ImageItem
 * JD-Core Version:    0.6.2
 */