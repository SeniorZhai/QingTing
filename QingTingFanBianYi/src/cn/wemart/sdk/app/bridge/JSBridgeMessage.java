package cn.wemart.sdk.app.bridge;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSBridgeMessage
{
  private static final String CALLBACK_ID_STR = "callbackId";
  private static final String DATA_STR = "data";
  private static final String HANDLER_NAME_STR = "handlerName";
  private static final String RESPONSE_DATA_STR = "responseData";
  private static final String RESPONSE_ID_STR = "responseId";
  private String callbackId;
  private String data;
  private String handlerName;
  private String responseData;
  private String responseId;

  public static List<JSBridgeMessage> toArrayList(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      JSONArray localJSONArray = new JSONArray(paramString.replaceAll("/\"", "\\\\\""));
      int i = 0;
      if (i >= localJSONArray.length())
        return localArrayList;
      JSBridgeMessage localJSBridgeMessage = new JSBridgeMessage();
      JSONObject localJSONObject = localJSONArray.getJSONObject(i);
      if (localJSONObject.has("handlerName"))
      {
        paramString = localJSONObject.getString("handlerName");
        label72: localJSBridgeMessage.setHandlerName(paramString);
        if (!localJSONObject.has("callbackId"))
          break label191;
        paramString = localJSONObject.getString("callbackId");
        label95: localJSBridgeMessage.setCallbackId(paramString);
        if (!localJSONObject.has("responseData"))
          break label196;
        paramString = localJSONObject.getString("responseData");
        label118: localJSBridgeMessage.setResponseData(paramString);
        if (!localJSONObject.has("responseId"))
          break label201;
        paramString = localJSONObject.getString("responseId");
        label141: localJSBridgeMessage.setResponseId(paramString);
        if (!localJSONObject.has("data"))
          break label206;
      }
      label191: label196: label201: label206: for (paramString = localJSONObject.getString("data"); ; paramString = null)
      {
        localJSBridgeMessage.setData(paramString);
        localArrayList.add(localJSBridgeMessage);
        i += 1;
        break;
        paramString = null;
        break label72;
        paramString = null;
        break label95;
        paramString = null;
        break label118;
        paramString = null;
        break label141;
      }
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return localArrayList;
  }

  public static JSBridgeMessage toObject(String paramString)
  {
    Object localObject = null;
    JSBridgeMessage localJSBridgeMessage = new JSBridgeMessage();
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      if (localJSONObject.has("handlerName"))
      {
        paramString = localJSONObject.getString("handlerName");
        localJSBridgeMessage.setHandlerName(paramString);
        if (!localJSONObject.has("callbackId"))
          break label133;
        paramString = localJSONObject.getString("callbackId");
        label56: localJSBridgeMessage.setCallbackId(paramString);
        if (!localJSONObject.has("responseData"))
          break label138;
        paramString = localJSONObject.getString("responseData");
        label77: localJSBridgeMessage.setResponseData(paramString);
        if (!localJSONObject.has("responseId"))
          break label143;
      }
      label133: label138: label143: for (paramString = localJSONObject.getString("responseId"); ; paramString = null)
      {
        localJSBridgeMessage.setResponseId(paramString);
        paramString = localObject;
        if (localJSONObject.has("data"))
          paramString = localJSONObject.getString("data");
        localJSBridgeMessage.setData(paramString);
        return localJSBridgeMessage;
        paramString = null;
        break;
        paramString = null;
        break label56;
        paramString = null;
        break label77;
      }
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return localJSBridgeMessage;
  }

  public String getCallbackId()
  {
    return this.callbackId;
  }

  public String getData()
  {
    return this.data;
  }

  public String getHandlerName()
  {
    return this.handlerName;
  }

  public String getResponseData()
  {
    return this.responseData;
  }

  public String getResponseId()
  {
    return this.responseId;
  }

  public void setCallbackId(String paramString)
  {
    this.callbackId = paramString;
  }

  public void setData(String paramString)
  {
    this.data = paramString;
  }

  public void setHandlerName(String paramString)
  {
    this.handlerName = paramString;
  }

  public void setResponseData(String paramString)
  {
    this.responseData = paramString;
  }

  public void setResponseId(String paramString)
  {
    this.responseId = paramString;
  }

  public String toJson()
  {
    Object localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("callbackId", getCallbackId());
      ((JSONObject)localObject).put("data", getData());
      ((JSONObject)localObject).put("handlerName", getHandlerName());
      ((JSONObject)localObject).put("responseData", getResponseData());
      ((JSONObject)localObject).put("responseId", getResponseId());
      localObject = ((JSONObject)localObject).toString();
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.wemart.sdk.app.bridge.JSBridgeMessage
 * JD-Core Version:    0.6.2
 */