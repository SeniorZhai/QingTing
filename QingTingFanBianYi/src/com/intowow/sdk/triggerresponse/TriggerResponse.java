package com.intowow.sdk.triggerresponse;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.intowow.sdk.WebViewActivity;
import com.intowow.sdk.j.l;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class TriggerResponse
  implements Parcelable
{
  protected String a = null;
  protected String b = null;
  protected boolean c = false;
  protected boolean d = false;
  protected boolean e = true;
  protected d f = null;
  protected JSONObject g = null;

  public static void a(TriggerResponse paramTriggerResponse, Context paramContext, String paramString)
  {
    a(paramTriggerResponse.d, paramContext, paramString);
  }

  public static void a(boolean paramBoolean, Context paramContext, String paramString)
  {
    if (paramContext != null);
    try
    {
      if (l.a(paramString))
        return;
      if (paramBoolean)
      {
        Intent localIntent = new Intent();
        localIntent.setClass(paramContext, WebViewActivity.class);
        localIntent.addFlags(268435456);
        Bundle localBundle = new Bundle();
        localBundle.putString("mUrlPath", paramString);
        localIntent.putExtras(localBundle);
        paramContext.startActivity(localIntent);
        return;
      }
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      paramString.addFlags(268435456);
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public String a()
  {
    if (this.b != null)
      return this.b.toString();
    return "";
  }

  protected void a(Parcel paramParcel)
  {
    if (this.g != null);
    for (String str = this.g.toString(); ; str = "{}")
    {
      paramParcel.writeString(str);
      paramParcel.writeBooleanArray(new boolean[] { this.c });
      return;
    }
  }

  public abstract void a(d paramd);

  public void a(String paramString)
  {
    this.b = paramString;
  }

  public boolean a(boolean paramBoolean, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return false;
    this.c = paramBoolean;
    this.g = paramJSONObject;
    if (this.g.has("action"))
      this.a = this.g.optString("action");
    if (this.g.has("inapp"))
      this.d = this.g.optBoolean("inapp", false);
    if (this.g.has("repeatable"))
      this.e = this.g.optBoolean("repeatable", true);
    return true;
  }

  public String b()
  {
    return this.b;
  }

  protected void b(Parcel paramParcel)
  {
    String str = paramParcel.readString();
    boolean[] arrayOfBoolean = new boolean[1];
    paramParcel.readBooleanArray(arrayOfBoolean);
    if (str != null);
    try
    {
      this.g = new JSONObject(str);
      a(arrayOfBoolean[0], this.g);
      return;
    }
    catch (JSONException paramParcel)
    {
    }
  }

  public String c()
  {
    return this.a;
  }

  public boolean d()
  {
    return this.d;
  }

  public boolean e()
  {
    return this.e;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.triggerresponse.TriggerResponse
 * JD-Core Version:    0.6.2
 */