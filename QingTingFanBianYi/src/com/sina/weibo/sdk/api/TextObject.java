package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.sina.weibo.sdk.utils.LogUtil;

public class TextObject extends BaseMediaObject
{
  public static final Parcelable.Creator<TextObject> CREATOR = new Parcelable.Creator()
  {
    public TextObject createFromParcel(Parcel paramAnonymousParcel)
    {
      return new TextObject(paramAnonymousParcel);
    }

    public TextObject[] newArray(int paramAnonymousInt)
    {
      return new TextObject[paramAnonymousInt];
    }
  };
  public String text;

  public TextObject()
  {
  }

  public TextObject(Parcel paramParcel)
  {
    this.text = paramParcel.readString();
  }

  public boolean checkArgs()
  {
    if ((this.text == null) || (this.text.length() == 0) || (this.text.length() > 1024))
    {
      LogUtil.e("Weibo.TextObject", "checkArgs fail, text is invalid");
      return false;
    }
    return true;
  }

  public int describeContents()
  {
    return 0;
  }

  public int getObjType()
  {
    return 1;
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
    paramParcel.writeString(this.text);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.api.TextObject
 * JD-Core Version:    0.6.2
 */