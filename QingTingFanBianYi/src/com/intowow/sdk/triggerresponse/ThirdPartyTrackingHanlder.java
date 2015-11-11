package com.intowow.sdk.triggerresponse;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.intowow.sdk.j.l;
import java.util.Arrays;
import org.json.JSONObject;

public class ThirdPartyTrackingHanlder extends TriggerResponse
{
  public static final Parcelable.Creator<ThirdPartyTrackingHanlder> CREATOR = new Parcelable.Creator()
  {
    public ThirdPartyTrackingHanlder a(Parcel paramAnonymousParcel)
    {
      return new ThirdPartyTrackingHanlder(paramAnonymousParcel);
    }

    public ThirdPartyTrackingHanlder[] a(int paramAnonymousInt)
    {
      return new ThirdPartyTrackingHanlder[paramAnonymousInt];
    }
  };
  private String[] h = null;

  public ThirdPartyTrackingHanlder()
  {
  }

  public ThirdPartyTrackingHanlder(Parcel paramParcel)
  {
    b(paramParcel);
  }

  public void a(d paramd)
  {
    if ((paramd == null) || (this.h.length == 0))
      return;
    a.a(this.c, paramd, this.h);
  }

  public boolean a(boolean paramBoolean, JSONObject paramJSONObject)
  {
    if (!super.a(paramBoolean, paramJSONObject))
      return false;
    if (paramJSONObject.has("value"))
      this.h = l.a(paramJSONObject, "value");
    return true;
  }

  public String b()
  {
    return Arrays.toString(this.h);
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
 * Qualified Name:     com.intowow.sdk.triggerresponse.ThirdPartyTrackingHanlder
 * JD-Core Version:    0.6.2
 */