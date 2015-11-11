package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.sina.weibo.sdk.utils.LogUtil;

public abstract class BaseMediaObject
  implements Parcelable
{
  public static final int MEDIA_TYPE_CMD = 7;
  public static final int MEDIA_TYPE_IMAGE = 2;
  public static final int MEDIA_TYPE_MUSIC = 3;
  public static final int MEDIA_TYPE_TEXT = 1;
  public static final int MEDIA_TYPE_VIDEO = 4;
  public static final int MEDIA_TYPE_VOICE = 6;
  public static final int MEDIA_TYPE_WEBPAGE = 5;
  public String actionUrl;
  public String description;
  public String identify;
  public String schema;
  public byte[] thumbData;
  public String title;

  public BaseMediaObject()
  {
  }

  public BaseMediaObject(Parcel paramParcel)
  {
    this.actionUrl = paramParcel.readString();
    this.schema = paramParcel.readString();
    this.identify = paramParcel.readString();
    this.title = paramParcel.readString();
    this.description = paramParcel.readString();
    this.thumbData = paramParcel.createByteArray();
  }

  protected boolean checkArgs()
  {
    if ((this.actionUrl == null) || (this.actionUrl.length() > 512))
    {
      LogUtil.e("Weibo.BaseMediaObject", "checkArgs fail, actionUrl is invalid");
      return false;
    }
    if ((this.identify == null) || (this.identify.length() > 512))
    {
      LogUtil.e("Weibo.BaseMediaObject", "checkArgs fail, identify is invalid");
      return false;
    }
    if ((this.thumbData == null) || (this.thumbData.length > 32768))
    {
      StringBuilder localStringBuilder = new StringBuilder("checkArgs fail, thumbData is invalid,size is ");
      if (this.thumbData != null);
      for (int i = this.thumbData.length; ; i = -1)
      {
        LogUtil.e("Weibo.BaseMediaObject", i + "! more then 32768.");
        return false;
      }
    }
    if ((this.title == null) || (this.title.length() > 512))
    {
      LogUtil.e("Weibo.BaseMediaObject", "checkArgs fail, title is invalid");
      return false;
    }
    if ((this.description == null) || (this.description.length() > 1024))
    {
      LogUtil.e("Weibo.BaseMediaObject", "checkArgs fail, description is invalid");
      return false;
    }
    return true;
  }

  public int describeContents()
  {
    return 0;
  }

  public abstract int getObjType();

  // ERROR //
  public final void setThumbImage(android.graphics.Bitmap paramBitmap)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: new 111	java/io/ByteArrayOutputStream
    //   8: dup
    //   9: invokespecial 112	java/io/ByteArrayOutputStream:<init>	()V
    //   12: astore_3
    //   13: aload_1
    //   14: getstatic 118	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   17: bipush 85
    //   19: aload_3
    //   20: invokevirtual 124	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   23: pop
    //   24: aload_0
    //   25: aload_3
    //   26: invokevirtual 127	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   29: putfield 57	com/sina/weibo/sdk/api/BaseMediaObject:thumbData	[B
    //   32: aload_3
    //   33: ifnull +66 -> 99
    //   36: aload_3
    //   37: invokevirtual 130	java/io/ByteArrayOutputStream:close	()V
    //   40: return
    //   41: astore_3
    //   42: aload 4
    //   44: astore_1
    //   45: aload_1
    //   46: astore_2
    //   47: aload_3
    //   48: invokevirtual 133	java/lang/Exception:printStackTrace	()V
    //   51: aload_1
    //   52: astore_2
    //   53: ldc 67
    //   55: ldc 135
    //   57: invokestatic 75	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   60: aload_1
    //   61: ifnull -21 -> 40
    //   64: aload_1
    //   65: invokevirtual 130	java/io/ByteArrayOutputStream:close	()V
    //   68: return
    //   69: astore_1
    //   70: aload_1
    //   71: invokevirtual 136	java/io/IOException:printStackTrace	()V
    //   74: return
    //   75: astore_1
    //   76: aload_2
    //   77: ifnull +7 -> 84
    //   80: aload_2
    //   81: invokevirtual 130	java/io/ByteArrayOutputStream:close	()V
    //   84: aload_1
    //   85: athrow
    //   86: astore_2
    //   87: aload_2
    //   88: invokevirtual 136	java/io/IOException:printStackTrace	()V
    //   91: goto -7 -> 84
    //   94: astore_1
    //   95: aload_1
    //   96: invokevirtual 136	java/io/IOException:printStackTrace	()V
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

  protected abstract BaseMediaObject toExtraMediaObject(String paramString);

  protected abstract String toExtraMediaString();

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.actionUrl);
    paramParcel.writeString(this.schema);
    paramParcel.writeString(this.identify);
    paramParcel.writeString(this.title);
    paramParcel.writeString(this.description);
    paramParcel.writeByteArray(this.thumbData);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.api.BaseMediaObject
 * JD-Core Version:    0.6.2
 */