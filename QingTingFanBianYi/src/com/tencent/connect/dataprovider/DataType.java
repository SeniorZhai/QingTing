package com.tencent.connect.dataprovider;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class DataType
{
  public static final int CONTENT_AND_IMAGE_PATH = 1;
  public static final int CONTENT_AND_VIDEO_PATH = 2;
  public static final int CONTENT_ONLY = 4;

  public static class TextAndMediaPath
    implements Parcelable
  {
    public static final Parcelable.Creator<TextAndMediaPath> CREATOR = new Parcelable.Creator()
    {
      public DataType.TextAndMediaPath createFromParcel(Parcel paramAnonymousParcel)
      {
        return new DataType.TextAndMediaPath(paramAnonymousParcel, null);
      }

      public DataType.TextAndMediaPath[] newArray(int paramAnonymousInt)
      {
        return new DataType.TextAndMediaPath[paramAnonymousInt];
      }
    };
    private String a;
    private String b;

    private TextAndMediaPath(Parcel paramParcel)
    {
      this.a = paramParcel.readString();
      this.b = paramParcel.readString();
    }

    public TextAndMediaPath(String paramString1, String paramString2)
    {
      this.a = paramString1;
      this.b = paramString2;
    }

    public int describeContents()
    {
      return 0;
    }

    public String getMediaPath()
    {
      return this.b;
    }

    public String getText()
    {
      return this.a;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeString(this.a);
      paramParcel.writeString(this.b);
    }
  }

  public static class TextOnly
    implements Parcelable
  {
    public static final Parcelable.Creator<TextOnly> CREATOR = new Parcelable.Creator()
    {
      public DataType.TextOnly createFromParcel(Parcel paramAnonymousParcel)
      {
        return new DataType.TextOnly(paramAnonymousParcel, null);
      }

      public DataType.TextOnly[] newArray(int paramAnonymousInt)
      {
        return new DataType.TextOnly[paramAnonymousInt];
      }
    };
    private String a;

    private TextOnly(Parcel paramParcel)
    {
      this.a = paramParcel.readString();
    }

    public TextOnly(String paramString)
    {
      this.a = paramString;
    }

    public int describeContents()
    {
      return 0;
    }

    public String getText()
    {
      return this.a;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeString(this.a);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.dataprovider.DataType
 * JD-Core Version:    0.6.2
 */