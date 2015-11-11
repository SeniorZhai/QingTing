package com.tencent.weibo.sdk.android.api.util;

import com.tencent.weibo.sdk.android.model.BaseVO;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil
{
  public static List<BaseVO> jsonToList(Class<? extends BaseVO> paramClass, JSONArray paramJSONArray)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (true)
    {
      if (i >= paramJSONArray.length())
        return localArrayList;
      localArrayList.add(jsonToObject(paramClass, paramJSONArray.getJSONObject(i)));
      i += 1;
    }
  }

  public static BaseVO jsonToObject(Class<? extends BaseVO> paramClass, JSONObject paramJSONObject)
    throws Exception
  {
    HashMap localHashMap = new HashMap();
    Object localObject1 = paramClass.getMethods();
    int j = localObject1.length;
    int i = 0;
    if (i >= j)
    {
      localObject1 = (BaseVO)paramClass.newInstance();
      paramClass = paramClass.getDeclaredFields();
      j = paramClass.length;
      i = 0;
    }
    while (true)
    {
      if (i >= j)
      {
        return localObject1;
        localObject2 = localObject1[i];
        if (((Method)localObject2).getName().startsWith("set"))
          localHashMap.put(((Method)localObject2).getName().toLowerCase(), localObject2);
        i += 1;
        break;
      }
      String str = paramClass[i];
      Object localObject2 = str.getType().getName();
      str = str.getName();
      try
      {
        if (((String)localObject2).equals("boolean"))
          ((Method)localHashMap.get("set" + str.toLowerCase())).invoke(localObject1, new Object[] { Boolean.valueOf(paramJSONObject.getBoolean(str)) });
        else if (((String)localObject2).equals(Boolean.class.getName()))
          ((Method)localHashMap.get("set" + str.toLowerCase())).invoke(localObject1, new Object[] { Boolean.valueOf(paramJSONObject.getBoolean(str)) });
        else if ((((String)localObject2).equals("int")) || (((String)localObject2).equals("byte")))
          ((Method)localHashMap.get("set" + str.toLowerCase())).invoke(localObject1, new Object[] { Integer.valueOf(paramJSONObject.getInt(str)) });
        else if ((((String)localObject2).equals(Integer.class.getName())) || (((String)localObject2).equals(Byte.class.getName())))
          ((Method)localHashMap.get("set" + str.toLowerCase())).invoke(localObject1, new Object[] { Integer.valueOf(paramJSONObject.getInt(str)) });
        else if (((String)localObject2).equals(String.class.getName()))
          ((Method)localHashMap.get("set" + str.toLowerCase())).invoke(localObject1, new Object[] { paramJSONObject.getString(str) });
        else if ((((String)localObject2).equals("double")) || (((String)localObject2).equals("float")))
          ((Method)localHashMap.get("set" + str.toLowerCase())).invoke(localObject1, new Object[] { Double.valueOf(paramJSONObject.getDouble(str)) });
        else if ((((String)localObject2).equals(Double.class.getName())) || (((String)localObject2).equals(Float.class.getName())))
          ((Method)localHashMap.get("set" + str.toLowerCase())).invoke(localObject1, new Object[] { Double.valueOf(paramJSONObject.getDouble(str)) });
        else if (((String)localObject2).equals("long"))
          ((Method)localHashMap.get("set" + str.toLowerCase())).invoke(localObject1, new Object[] { Long.valueOf(paramJSONObject.getLong(str)) });
        else if (((String)localObject2).equals(Long.class.getName()))
          ((Method)localHashMap.get("set" + str.toLowerCase())).invoke(localObject1, new Object[] { Long.valueOf(paramJSONObject.getLong(str)) });
        label746: i += 1;
      }
      catch (Exception localException)
      {
        break label746;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.util.JsonUtil
 * JD-Core Version:    0.6.2
 */