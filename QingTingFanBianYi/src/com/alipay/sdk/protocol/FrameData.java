package com.alipay.sdk.protocol;

import com.alipay.sdk.data.Request;
import com.alipay.sdk.data.Response;
import org.json.JSONObject;

public class FrameData
{
  private Request a;
  private Response b;
  private JSONObject c;

  public FrameData(Request paramRequest, Response paramResponse)
  {
    this.a = paramRequest;
    this.b = paramResponse;
  }

  public final Request a()
  {
    return this.a;
  }

  public void a(JSONObject paramJSONObject)
  {
    this.c = paramJSONObject;
  }

  public final Response b()
  {
    return this.b;
  }

  public final JSONObject c()
  {
    return this.c;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.protocol.FrameData
 * JD-Core Version:    0.6.2
 */