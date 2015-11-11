package com.alipay.sdk.data;

import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.util.JsonUtils;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

public class Request
{
  private Envelope a;
  private JSONObject b;
  private JSONObject c;
  private long d;
  private WeakReference e = null;
  private boolean f = true;
  private boolean g = true;

  public Request(Envelope paramEnvelope, JSONObject paramJSONObject)
  {
    this(paramEnvelope, paramJSONObject, (byte)0);
  }

  private Request(Envelope paramEnvelope, JSONObject paramJSONObject, byte paramByte)
  {
    this.a = paramEnvelope;
    this.b = paramJSONObject;
    this.c = null;
    this.e = new WeakReference(null);
  }

  private void a(long paramLong)
  {
    this.d = paramLong;
  }

  private void a(JSONObject paramJSONObject)
  {
    this.c = paramJSONObject;
  }

  private void b(boolean paramBoolean)
  {
    this.f = paramBoolean;
  }

  private boolean e()
  {
    return this.g;
  }

  private long f()
  {
    return this.d;
  }

  public final String a()
  {
    return this.a.b();
  }

  public final JSONObject a(String paramString)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      localJSONObject2 = new JSONObject();
      localJSONObject2.put("device", Build.MODEL);
      localJSONObject3 = new JSONObject();
      localJSONObject2 = JsonUtils.a(localJSONObject2, this.c);
      localJSONObject2.put("namespace", this.a.c());
      localJSONObject2.put("api_name", this.a.a());
      localJSONObject2.put("api_version", this.a.e());
      if (this.b == null)
        this.b = new JSONObject();
      this.b.put("action", localJSONObject3);
      localObject = this.a.d();
      boolean bool = TextUtils.isEmpty((CharSequence)localObject);
      if (bool);
    }
    catch (Exception paramString)
    {
      try
      {
        JSONObject localJSONObject2;
        JSONObject localJSONObject3;
        Object localObject = ((String)localObject).split("/");
        localJSONObject3.put("type", localObject[1]);
        if (localObject.length > 1)
          localJSONObject3.put("method", localObject[2]);
        label177: this.b.put("gzip", this.g);
        if (this.f)
        {
          localJSONObject3 = new JSONObject();
          new StringBuilder("requestData before: ").append(this.b.toString()).toString();
          localJSONObject3.put("req_data", JsonUtils.a(paramString, this.b.toString()));
          localJSONObject2.put("params", localJSONObject3);
          localJSONObject1.put("data", localJSONObject2);
        }
        while (true)
        {
          new StringBuilder("requestData : ").append(localJSONObject1.toString()).toString();
          return localJSONObject1;
          localJSONObject2.put("params", this.b);
          break;
          paramString = paramString;
        }
      }
      catch (Exception localException)
      {
        break label177;
      }
    }
  }

  public final void a(InteractionData paramInteractionData)
  {
    this.e = new WeakReference(paramInteractionData);
  }

  public final void a(boolean paramBoolean)
  {
    this.g = paramBoolean;
  }

  public final InteractionData b()
  {
    return (InteractionData)this.e.get();
  }

  public final boolean c()
  {
    return this.f;
  }

  public final Envelope d()
  {
    return this.a;
  }

  public String toString()
  {
    return this.a.toString() + ", requestData = " + JsonUtils.a(this.b, this.c) + ", timeStamp = " + this.d;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.data.Request
 * JD-Core Version:    0.6.2
 */