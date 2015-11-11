package com.tencent.weibo.sdk.android.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseVO
  implements Serializable
{
  public static final int TYPE_BEAN = 0;
  public static final int TYPE_BEAN_LIST = 3;
  public static final int TYPE_JSON = 4;
  public static final int TYPE_LIST = 1;
  public static final int TYPE_OBJECT = 2;
  private static final long serialVersionUID = 8175948521471886407L;

  public Object analyseBody(JSONArray paramJSONArray)
    throws JSONException
  {
    return null;
  }

  public Object analyseBody(JSONObject paramJSONObject)
    throws JSONException
  {
    return null;
  }

  public Map<String, Object> analyseHead(JSONObject paramJSONObject)
    throws JSONException
  {
    HashMap localHashMap = new HashMap();
    JSONArray localJSONArray = paramJSONObject.getJSONArray("result_list");
    int i = paramJSONObject.getInt("total");
    int j = paramJSONObject.getInt("p");
    int k = paramJSONObject.getInt("ps");
    boolean bool = paramJSONObject.getBoolean("is_last_list");
    localHashMap.put("array", localJSONArray);
    localHashMap.put("total", Integer.valueOf(i));
    localHashMap.put("p", Integer.valueOf(j));
    localHashMap.put("ps", Integer.valueOf(k));
    localHashMap.put("isLastPage", Boolean.valueOf(bool));
    return localHashMap;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.model.BaseVO
 * JD-Core Version:    0.6.2
 */