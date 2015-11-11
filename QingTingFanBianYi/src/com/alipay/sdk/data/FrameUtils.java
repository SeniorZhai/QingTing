package com.alipay.sdk.data;

import android.text.TextUtils;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.exception.UnZipException;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.tid.TidInfo;
import com.alipay.sdk.util.DeviceInfo;
import com.alipay.sdk.util.JsonUtils;
import com.alipay.sdk.util.Utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

public class FrameUtils
{
  private static final String a = "Msp-Param";

  public static Request a()
  {
    Envelope localEnvelope = new Envelope();
    localEnvelope.b(GlobalConstants.b);
    localEnvelope.c("com.alipay.mobilecashier");
    localEnvelope.d("/device/findAccount");
    localEnvelope.e("3.0.0");
    GlobalContext localGlobalContext = GlobalContext.a();
    TidInfo localTidInfo = TidInfo.c();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (!TextUtils.isEmpty(localTidInfo.a()))
        localJSONObject.put("tid", localTidInfo.a());
      while (true)
      {
        localJSONObject.put("utdid", localGlobalContext.g());
        localJSONObject.put("app_key", "2014052600006128");
        localJSONObject.put("new_client_key", localTidInfo.b());
        localJSONObject.put("imei", DeviceInfo.a(localGlobalContext.b()).b());
        localJSONObject.put("imsi", DeviceInfo.a(localGlobalContext.b()).a());
        label135: return new Request(localEnvelope, localJSONObject);
        localTidInfo.b(localTidInfo.b());
      }
    }
    catch (JSONException localJSONException)
    {
      break label135;
    }
  }

  public static Request a(InteractionData paramInteractionData, String paramString, JSONObject paramJSONObject)
  {
    Object localObject1 = GlobalContext.a();
    Object localObject2 = TidInfo.c();
    paramJSONObject = JsonUtils.a(null, paramJSONObject);
    try
    {
      paramJSONObject.put("tid", ((TidInfo)localObject2).a());
      paramJSONObject.put("user_agent", ((GlobalContext)localObject1).c().a((TidInfo)localObject2));
      paramJSONObject.put("has_alipay", Utils.b(((GlobalContext)localObject1).b()));
      paramJSONObject.put("has_msp_app", Utils.a(((GlobalContext)localObject1).b()));
      paramJSONObject.put("external_info", paramString);
      paramJSONObject.put("app_key", "2014052600006128");
      paramJSONObject.put("utdid", ((GlobalContext)localObject1).g());
      paramJSONObject.put("new_client_key", ((TidInfo)localObject2).b());
      localObject1 = new Envelope();
      ((Envelope)localObject1).b(GlobalConstants.b);
      ((Envelope)localObject1).c("com.alipay.mobilecashier");
      ((Envelope)localObject1).d("/cashier/main");
      ((Envelope)localObject1).e("4.0.2");
      if (paramJSONObject != null)
      {
        localRequest = new Request((Envelope)localObject1, paramJSONObject);
        localRequest.a(true);
        if ((localRequest != null) && (!TextUtils.isEmpty(paramString)))
        {
          String[] arrayOfString = paramString.split("&");
          if (arrayOfString.length != 0)
          {
            int j = arrayOfString.length;
            int i = 0;
            Object localObject3 = null;
            localObject2 = null;
            Object localObject4 = null;
            Object localObject5 = null;
            if (i < j)
            {
              String str = arrayOfString[i];
              paramString = (String)localObject5;
              if (TextUtils.isEmpty((CharSequence)localObject5))
              {
                if (!str.contains("biz_type"))
                  paramString = null;
              }
              else
              {
                label248: paramJSONObject = (JSONObject)localObject4;
                if (TextUtils.isEmpty((CharSequence)localObject4))
                {
                  if (str.contains("biz_no"))
                    break label360;
                  paramJSONObject = null;
                }
                label271: localObject1 = localObject2;
                if (TextUtils.isEmpty((CharSequence)localObject2))
                {
                  if ((str.contains("trade_no")) && (!str.startsWith("out_trade_no")))
                    break label369;
                  localObject1 = null;
                }
                label304: localObject2 = localObject3;
                if (TextUtils.isEmpty((CharSequence)localObject3))
                  if (str.contains("app_userid"))
                    break label378;
              }
              label360: label369: label378: for (localObject2 = null; ; localObject2 = d(str))
              {
                i += 1;
                localObject3 = localObject2;
                localObject5 = paramString;
                localObject4 = paramJSONObject;
                localObject2 = localObject1;
                break;
                paramString = d(str);
                break label248;
                paramJSONObject = d(str);
                break label271;
                localObject1 = d(str);
                break label304;
              }
            }
            paramString = new StringBuilder();
            if (!TextUtils.isEmpty((CharSequence)localObject5))
              paramString.append("biz_type=" + (String)localObject5 + ";");
            if (!TextUtils.isEmpty((CharSequence)localObject4))
              paramString.append("biz_no=" + (String)localObject4 + ";");
            if (!TextUtils.isEmpty((CharSequence)localObject2))
              paramString.append("trade_no=" + (String)localObject2 + ";");
            if (!TextUtils.isEmpty((CharSequence)localObject3))
              paramString.append("app_userid=" + (String)localObject3 + ";");
            if (paramString.length() != 0)
            {
              paramJSONObject = paramString.toString();
              paramString = paramJSONObject;
              if (paramJSONObject.endsWith(";"))
                paramString = paramJSONObject.substring(0, paramJSONObject.length() - 1);
              paramInteractionData.a(new Header[] { new BasicHeader("Msp-Param", paramString) });
              localRequest.a(paramInteractionData);
            }
          }
        }
        return localRequest;
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        continue;
        Request localRequest = null;
      }
    }
  }

  private static Request a(JSONObject paramJSONObject, boolean paramBoolean)
  {
    Envelope localEnvelope = new Envelope();
    localEnvelope.b(GlobalConstants.b);
    localEnvelope.c("com.alipay.mobilecashier");
    localEnvelope.d("/cashier/main");
    localEnvelope.e("4.0.2");
    Request localRequest = null;
    if (paramJSONObject != null)
    {
      localRequest = new Request(localEnvelope, paramJSONObject);
      localRequest.a(paramBoolean);
    }
    return localRequest;
  }

  private static String a(String paramString)
  {
    if (!paramString.contains("biz_type"))
      return null;
    return d(paramString);
  }

  private static void a(InteractionData paramInteractionData, Request paramRequest, String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    label79: label102: 
    do
    {
      String[] arrayOfString;
      do
      {
        return;
        arrayOfString = paramString.split("&");
      }
      while (arrayOfString.length == 0);
      int j = arrayOfString.length;
      int i = 0;
      Object localObject4 = null;
      Object localObject3 = null;
      Object localObject5 = null;
      Object localObject6 = null;
      if (i < j)
      {
        String str = arrayOfString[i];
        paramString = (String)localObject6;
        Object localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject6))
        {
          if (!str.contains("biz_type"))
            paramString = null;
        }
        else
        {
          localObject1 = localObject5;
          if (TextUtils.isEmpty((CharSequence)localObject5))
          {
            if (str.contains("biz_no"))
              break label194;
            localObject1 = null;
          }
          localObject2 = localObject3;
          if (TextUtils.isEmpty((CharSequence)localObject3))
          {
            if ((str.contains("trade_no")) && (!str.startsWith("out_trade_no")))
              break label203;
            localObject2 = null;
          }
          localObject3 = localObject4;
          if (TextUtils.isEmpty((CharSequence)localObject4))
            if (str.contains("app_userid"))
              break label213;
        }
        for (localObject3 = null; ; localObject3 = d(str))
        {
          i += 1;
          localObject4 = localObject3;
          localObject6 = paramString;
          localObject5 = localObject1;
          localObject3 = localObject2;
          break;
          paramString = d(str);
          break label79;
          localObject1 = d(str);
          break label102;
          localObject2 = d(str);
          break label137;
        }
      }
      paramString = new StringBuilder();
      if (!TextUtils.isEmpty((CharSequence)localObject6))
        paramString.append("biz_type=" + (String)localObject6 + ";");
      if (!TextUtils.isEmpty((CharSequence)localObject5))
        paramString.append("biz_no=" + (String)localObject5 + ";");
      if (!TextUtils.isEmpty((CharSequence)localObject3))
        paramString.append("trade_no=" + (String)localObject3 + ";");
      if (!TextUtils.isEmpty((CharSequence)localObject4))
        paramString.append("app_userid=" + (String)localObject4 + ";");
    }
    while (paramString.length() == 0);
    label137: label194: label203: label213: Object localObject1 = paramString.toString();
    paramString = (String)localObject1;
    if (((String)localObject1).endsWith(";"))
      paramString = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
    paramInteractionData.a(new Header[] { new BasicHeader("Msp-Param", paramString) });
    paramRequest.a(paramInteractionData);
  }

  public static void a(InteractionData paramInteractionData, HttpResponse paramHttpResponse)
  {
    paramHttpResponse = paramHttpResponse.getHeaders("Msp-Param");
    if ((paramInteractionData != null) && (paramHttpResponse.length > 0))
      paramInteractionData.a(paramHttpResponse);
  }

  public static byte[] a(byte[] paramArrayOfByte)
    throws UnZipException
  {
    try
    {
      paramArrayOfByte = new ByteArrayInputStream(paramArrayOfByte);
      localGZIPInputStream = new GZIPInputStream(paramArrayOfByte);
      arrayOfByte = new byte[4096];
      localByteArrayOutputStream = new ByteArrayOutputStream();
      while (true)
      {
        int i = localGZIPInputStream.read(arrayOfByte, 0, arrayOfByte.length);
        if (i == -1)
          break;
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
      GZIPInputStream localGZIPInputStream;
      ByteArrayOutputStream localByteArrayOutputStream;
      paramArrayOfByte.printStackTrace();
      throw new UnZipException("UnsupportedEncodingException");
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      localByteArrayOutputStream.flush();
      localByteArrayOutputStream.close();
      localGZIPInputStream.close();
      paramArrayOfByte.close();
      return arrayOfByte;
    }
    catch (IOException paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    throw new UnZipException("IOException");
  }

  private static String b(String paramString)
  {
    if (!paramString.contains("biz_no"))
      return null;
    return d(paramString);
  }

  private static String c(String paramString)
  {
    if ((!paramString.contains("trade_no")) || (paramString.startsWith("out_trade_no")))
      return null;
    return d(paramString);
  }

  private static String d(String paramString)
  {
    Object localObject = paramString.split("=");
    paramString = null;
    if (localObject.length > 1)
    {
      localObject = localObject[1];
      paramString = (String)localObject;
      if (((String)localObject).contains("\""))
        paramString = ((String)localObject).replaceAll("\"", "");
    }
    return paramString;
  }

  private static String e(String paramString)
  {
    if (!paramString.contains("app_userid"))
      return null;
    return d(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.data.FrameUtils
 * JD-Core Version:    0.6.2
 */