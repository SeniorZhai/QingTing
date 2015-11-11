package com.intowow.sdk.triggerresponse;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.intowow.sdk.j.l;
import org.json.JSONObject;

public class InstallHandler extends TriggerResponse
{
  public static final Parcelable.Creator<InstallHandler> CREATOR = new Parcelable.Creator()
  {
    public InstallHandler a(Parcel paramAnonymousParcel)
    {
      return new InstallHandler(paramAnonymousParcel);
    }

    public InstallHandler[] a(int paramAnonymousInt)
    {
      return new InstallHandler[paramAnonymousInt];
    }
  };
  private boolean h = false;

  public InstallHandler()
  {
  }

  public InstallHandler(Parcel paramParcel)
  {
    b(paramParcel);
  }

  public void a(d paramd)
  {
    if (this.h)
      a(this, paramd.b(), this.b);
    while (true)
    {
      paramd.g();
      return;
      a(this, paramd.b(), String.format("market://details?id=%s", new Object[] { this.b }));
    }
  }

  public boolean a(boolean paramBoolean, JSONObject paramJSONObject)
  {
    boolean bool = false;
    if (!super.a(paramBoolean, paramJSONObject))
      return false;
    if (paramJSONObject.has("value"))
    {
      this.b = paramJSONObject.optString("value");
      if (!l.a(this.b))
      {
        paramBoolean = bool;
        if (this.b.indexOf("http://") != -1)
          paramBoolean = true;
        this.h = paramBoolean;
      }
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
 * Qualified Name:     com.intowow.sdk.triggerresponse.InstallHandler
 * JD-Core Version:    0.6.2
 */