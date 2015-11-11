package com.intowow.sdk.triggerresponse;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class CommonTriggerHandler extends TriggerResponse
{
  public static final Parcelable.Creator<CommonTriggerHandler> CREATOR = new Parcelable.Creator()
  {
    public CommonTriggerHandler a(Parcel paramAnonymousParcel)
    {
      return new CommonTriggerHandler(paramAnonymousParcel);
    }

    public CommonTriggerHandler[] a(int paramAnonymousInt)
    {
      return new CommonTriggerHandler[paramAnonymousInt];
    }
  };

  public CommonTriggerHandler()
  {
  }

  public CommonTriggerHandler(Parcel paramParcel)
  {
    b(paramParcel);
  }

  public CommonTriggerHandler(boolean paramBoolean1, String paramString1, String paramString2, boolean paramBoolean2)
  {
    this.c = paramBoolean1;
    this.a = paramString1;
    this.b = paramString2;
    this.e = paramBoolean2;
  }

  public void a(d paramd)
  {
    paramd.g();
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a(paramParcel);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.triggerresponse.CommonTriggerHandler
 * JD-Core Version:    0.6.2
 */