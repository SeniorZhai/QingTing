package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.sdk.data.Envelope;
import com.alipay.sdk.data.Request;
import com.alipay.sdk.data.Response;
import com.alipay.sdk.exception.AppErrorException;
import com.alipay.sdk.exception.FailOperatingException;
import com.alipay.sdk.exception.NetErrorException;
import com.alipay.sdk.tid.TidInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.json.JSONException;
import org.json.JSONObject;

public class MiniFrameFactory
{
  public static MiniWindowFrame a(FrameData paramFrameData)
    throws NetErrorException, FailOperatingException, AppErrorException
  {
    Request localRequest = paramFrameData.a();
    Response localResponse = paramFrameData.b();
    JSONObject localJSONObject = paramFrameData.c();
    Object localObject = "程序发生错误";
    if (localJSONObject.has("form"))
    {
      localObject = new MiniWindowFrame(localRequest, localResponse);
      ((MiniWindowFrame)localObject).a(paramFrameData.c());
      return localObject;
    }
    if (localJSONObject.has("status"))
    {
      paramFrameData = MiniStatus.a(localJSONObject.optString("status"));
      switch (1.a[paramFrameData.ordinal()])
      {
      default:
        paramFrameData = localJSONObject.optString("msg");
        if (TextUtils.isEmpty(paramFrameData))
          paramFrameData = (FrameData)localObject;
        break;
      case 1:
      case 2:
      case 3:
      case 4:
      }
      while (true)
      {
        throw new FailOperatingException(paramFrameData);
        paramFrameData = new MiniWindowFrame(localRequest, localResponse);
        paramFrameData.a(localJSONObject);
        return paramFrameData;
        TidInfo.d();
        return null;
      }
    }
    throw new FailOperatingException("程序发生错误");
  }

  public final void b(FrameData paramFrameData)
    throws NetErrorException, FailOperatingException, AppErrorException
  {
    Response localResponse = paramFrameData.b();
    JSONObject localJSONObject = paramFrameData.c();
    Object localObject = paramFrameData.a().d();
    Envelope localEnvelope = paramFrameData.b().a();
    if (TextUtils.isEmpty(localEnvelope.d()))
      localEnvelope.d(((Envelope)localObject).d());
    if (TextUtils.isEmpty(localEnvelope.e()))
      localEnvelope.e(((Envelope)localObject).e());
    if (TextUtils.isEmpty(localEnvelope.c()))
      localEnvelope.c(((Envelope)localObject).c());
    if (TextUtils.isEmpty(localEnvelope.b()))
      localEnvelope.b(((Envelope)localObject).b());
    localObject = localJSONObject.optJSONObject("reflected_data");
    if (localObject != null)
    {
      new StringBuilder("session = ").append(((JSONObject)localObject).optString("session", "")).toString();
      paramFrameData.b().a((JSONObject)localObject);
    }
    while (true)
    {
      localResponse.b(localJSONObject.optString("end_code", "0"));
      localResponse.e(localJSONObject.optString("user_id", ""));
      paramFrameData = localJSONObject.optString("result");
      try
      {
        localObject = URLDecoder.decode(localJSONObject.optString("result"), "UTF-8");
        paramFrameData = (FrameData)localObject;
        label201: localResponse.c(paramFrameData);
        localResponse.d(localJSONObject.optString("memo", ""));
        return;
        if (!localJSONObject.has("session"))
          continue;
        paramFrameData = new JSONObject();
        try
        {
          paramFrameData.put("session", localJSONObject.optString("session"));
          localObject = TidInfo.c().a();
          if (!TextUtils.isEmpty((CharSequence)localObject))
            paramFrameData.put("tid", localObject);
          localResponse.a(paramFrameData);
        }
        catch (JSONException paramFrameData)
        {
          throw new AppErrorException(getClass(), "can not put reflected values");
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        break label201;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.protocol.MiniFrameFactory
 * JD-Core Version:    0.6.2
 */