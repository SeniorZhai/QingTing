package com.intowow.sdk.triggerresponse;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.intowow.sdk.j.l;
import org.json.JSONObject;

public class CustomHandler extends TriggerResponse
{
  public static final Parcelable.Creator<CustomHandler> CREATOR = new Parcelable.Creator()
  {
    public CustomHandler a(Parcel paramAnonymousParcel)
    {
      return new CustomHandler(paramAnonymousParcel);
    }

    public CustomHandler[] a(int paramAnonymousInt)
    {
      return new CustomHandler[paramAnonymousInt];
    }
  };
  private JSONObject h = null;
  private String i = null;
  private String[] j = null;
  private boolean k = true;

  public CustomHandler()
  {
  }

  public CustomHandler(Parcel paramParcel)
  {
    b(paramParcel);
  }

  public String a()
  {
    if (this.i != null)
      return this.i;
    return "";
  }

  public void a(d paramd)
  {
    if (paramd == null)
      return;
    if (this.k)
      a(this, paramd.b(), this.i);
    a.a(this.c, paramd, this.j);
  }

  public boolean a(boolean paramBoolean, JSONObject paramJSONObject)
  {
    if (!super.a(paramBoolean, paramJSONObject))
      return false;
    if (paramJSONObject.has("value"))
    {
      this.h = paramJSONObject.getJSONObject("value");
      if (this.h.has("data"))
        this.i = this.h.optString("data");
      if (this.h.has("tracking"))
        this.j = l.a(this.h, "tracking");
      if (this.h.has("should_open"))
        this.k = this.h.optBoolean("should_open", this.k);
    }
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
 * Qualified Name:     com.intowow.sdk.triggerresponse.CustomHandler
 * JD-Core Version:    0.6.2
 */