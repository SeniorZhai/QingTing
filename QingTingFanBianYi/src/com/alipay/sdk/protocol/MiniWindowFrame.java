package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.sdk.data.Request;
import com.alipay.sdk.data.Response;
import org.json.JSONObject;

public class MiniWindowFrame extends WindowData
{
  private int i;
  private boolean j = false;

  protected MiniWindowFrame(Request paramRequest, Response paramResponse)
  {
    super(paramRequest, paramResponse);
  }

  private boolean g()
  {
    return this.j;
  }

  public final void a(JSONObject paramJSONObject)
  {
    int k = 0;
    super.a(paramJSONObject);
    if (paramJSONObject.has("form"))
    {
      paramJSONObject = paramJSONObject.optJSONObject("form");
      String str = paramJSONObject.optString("type");
      this.h = Boolean.parseBoolean(paramJSONObject.optString("oneTime"));
      if (TextUtils.equals("page", str))
      {
        this.j = true;
        this.i = 9;
      }
      do
        while (true)
        {
          return;
          if (TextUtils.equals("dialog", str))
          {
            this.i = 7;
            this.j = false;
            return;
          }
          if (!TextUtils.equals("toast", str))
            break;
          paramJSONObject = ElementAction.a(paramJSONObject, "onload");
          this.i = 6;
          if (paramJSONObject != null)
          {
            paramJSONObject = ActionType.a(paramJSONObject);
            int m = paramJSONObject.length;
            while (k < m)
            {
              str = paramJSONObject[k];
              if ((str == ActionType.d) || (str == ActionType.e))
                this.i = 10;
              k += 1;
            }
          }
        }
      while (TextUtils.equals("confirm", str));
      this.j = TextUtils.equals(str, "fullscreen");
      this.i = 4;
      return;
    }
    if (MiniStatus.a(paramJSONObject.optString("status")) == MiniStatus.c)
    {
      this.i = -10;
      return;
    }
    this.i = 8;
  }

  public final boolean d()
  {
    return (this.i == 4) || (this.i == 9);
  }

  public final int e()
  {
    return this.i;
  }

  public final String f()
  {
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.protocol.MiniWindowFrame
 * JD-Core Version:    0.6.2
 */