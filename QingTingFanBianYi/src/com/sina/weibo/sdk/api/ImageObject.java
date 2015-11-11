package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.sina.weibo.sdk.utils.LogUtil;
import java.io.File;

public class ImageObject extends BaseMediaObject
{
  public static final Parcelable.Creator<ImageObject> CREATOR = new Parcelable.Creator()
  {
    public ImageObject createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ImageObject(paramAnonymousParcel);
    }

    public ImageObject[] newArray(int paramAnonymousInt)
    {
      return new ImageObject[paramAnonymousInt];
    }
  };
  private static final int DATA_SIZE = 2097152;
  public byte[] imageData;
  public String imagePath;

  public ImageObject()
  {
  }

  public ImageObject(Parcel paramParcel)
  {
    this.imageData = paramParcel.createByteArray();
    this.imagePath = paramParcel.readString();
  }

  public boolean checkArgs()
  {
    if ((this.imageData == null) && (this.imagePath == null))
    {
      LogUtil.e("Weibo.ImageObject", "imageData and imagePath are null");
      return false;
    }
    if ((this.imageData != null) && (this.imageData.length > 2097152))
    {
      LogUtil.e("Weibo.ImageObject", "imageData is too large");
      return false;
    }
    if ((this.imagePath != null) && (this.imagePath.length() > 512))
    {
      LogUtil.e("Weibo.ImageObject", "imagePath is too length");
      return false;
    }
    if (this.imagePath != null)
    {
      File localFile = new File(this.imagePath);
      try
      {
        if ((!localFile.exists()) || (localFile.length() == 0L) || (localFile.length() > 10485760L))
        {
          LogUtil.e("Weibo.ImageObject", "checkArgs fail, image content is too large or not exists");
          return false;
        }
      }
      catch (SecurityException localSecurityException)
      {
        LogUtil.e("Weibo.ImageObject", "checkArgs fail, image content is too large or not exists");
        return false;
      }
    }
    return true;
  }

  public int describeContents()
  {
    return 0;
  }

  public int getObjType()
  {
    return 2;
  }

  // ERROR //
  public final void setImageObject(android.graphics.Bitmap paramBitmap)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: new 89	java/io/ByteArrayOutputStream
    //   8: dup
    //   9: invokespecial 90	java/io/ByteArrayOutputStream:<init>	()V
    //   12: astore_3
    //   13: aload_1
    //   14: getstatic 96	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   17: bipush 85
    //   19: aload_3
    //   20: invokevirtual 102	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   23: pop
    //   24: aload_0
    //   25: aload_3
    //   26: invokevirtual 105	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   29: putfield 34	com/sina/weibo/sdk/api/ImageObject:imageData	[B
    //   32: aload_3
    //   33: ifnull +66 -> 99
    //   36: aload_3
    //   37: invokevirtual 108	java/io/ByteArrayOutputStream:close	()V
    //   40: return
    //   41: astore_3
    //   42: aload 4
    //   44: astore_1
    //   45: aload_1
    //   46: astore_2
    //   47: aload_3
    //   48: invokevirtual 111	java/lang/Exception:printStackTrace	()V
    //   51: aload_1
    //   52: astore_2
    //   53: ldc 46
    //   55: ldc 113
    //   57: invokestatic 54	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   60: aload_1
    //   61: ifnull -21 -> 40
    //   64: aload_1
    //   65: invokevirtual 108	java/io/ByteArrayOutputStream:close	()V
    //   68: return
    //   69: astore_1
    //   70: aload_1
    //   71: invokevirtual 114	java/io/IOException:printStackTrace	()V
    //   74: return
    //   75: astore_1
    //   76: aload_2
    //   77: ifnull +7 -> 84
    //   80: aload_2
    //   81: invokevirtual 108	java/io/ByteArrayOutputStream:close	()V
    //   84: aload_1
    //   85: athrow
    //   86: astore_2
    //   87: aload_2
    //   88: invokevirtual 114	java/io/IOException:printStackTrace	()V
    //   91: goto -7 -> 84
    //   94: astore_1
    //   95: aload_1
    //   96: invokevirtual 114	java/io/IOException:printStackTrace	()V
    //   99: return
    //   100: astore_1
    //   101: aload_3
    //   102: astore_2
    //   103: goto -27 -> 76
    //   106: astore_2
    //   107: aload_3
    //   108: astore_1
    //   109: aload_2
    //   110: astore_3
    //   111: goto -66 -> 45
    //
    // Exception table:
    //   from	to	target	type
    //   5	13	41	java/lang/Exception
    //   64	68	69	java/io/IOException
    //   5	13	75	finally
    //   47	51	75	finally
    //   53	60	75	finally
    //   80	84	86	java/io/IOException
    //   36	40	94	java/io/IOException
    //   13	32	100	finally
    //   13	32	106	java/lang/Exception
  }

  protected BaseMediaObject toExtraMediaObject(String paramString)
  {
    return this;
  }

  protected String toExtraMediaString()
  {
    return "";
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeByteArray(this.imageData);
    paramParcel.writeString(this.imagePath);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.api.ImageObject
 * JD-Core Version:    0.6.2
 */