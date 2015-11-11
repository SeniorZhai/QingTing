package com.intowow.sdk.triggerresponse;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import org.json.JSONObject;

public class RedirectHandler extends TriggerResponse
{
  public static final Parcelable.Creator<RedirectHandler> CREATOR = new Parcelable.Creator()
  {
    public RedirectHandler a(Parcel paramAnonymousParcel)
    {
      return new RedirectHandler(paramAnonymousParcel);
    }

    public RedirectHandler[] a(int paramAnonymousInt)
    {
      return new RedirectHandler[paramAnonymousInt];
    }
  };

  public RedirectHandler()
  {
  }

  public RedirectHandler(Parcel paramParcel)
  {
    b(paramParcel);
  }

  public void a(d paramd)
  {
    a(this, paramd.b(), this.b);
    paramd.g();
  }

  public boolean a(boolean paramBoolean, JSONObject paramJSONObject)
  {
    if (!super.a(paramBoolean, paramJSONObject))
      return false;
    if (paramJSONObject.has("value"))
      this.b = paramJSONObject.optString("value");
    return true;
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
 * Qualified Name:     com.intowow.sdk.triggerresponse.RedirectHandler
 * JD-Core Version:    0.6.2
 */