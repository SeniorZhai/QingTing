package fm.qingting.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class JsonUtil
{
  private static Gson gson = null;

  static
  {
    if (gson == null)
      gson = new Gson();
  }

  public static Object getJsonValue(String paramString1, String paramString2)
  {
    Object localObject = null;
    Map localMap = jsonToMap(paramString1);
    paramString1 = localObject;
    if (localMap != null)
    {
      paramString1 = localObject;
      if (localMap.size() > 0)
        paramString1 = localMap.get(paramString2);
    }
    return paramString1;
  }

  public static Object jsonToBean(String paramString, Class<?> paramClass)
  {
    Object localObject = null;
    if (gson != null)
      localObject = gson.fromJson(paramString, paramClass);
    return localObject;
  }

  public static <T> T jsonToBeanDateSerializer(String paramString1, Class<T> paramClass, String paramString2)
  {
    Object localObject = null;
    gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer()
    {
      public Date deserialize(JsonElement paramAnonymousJsonElement, Type paramAnonymousType, JsonDeserializationContext paramAnonymousJsonDeserializationContext)
        throws JsonParseException
      {
        paramAnonymousType = new SimpleDateFormat(this.val$pattern);
        paramAnonymousJsonElement = paramAnonymousJsonElement.getAsString();
        try
        {
          paramAnonymousJsonElement = (Date)paramAnonymousType.parse(paramAnonymousJsonElement);
          return paramAnonymousJsonElement;
        }
        catch (java.text.ParseException paramAnonymousJsonElement)
        {
          paramAnonymousJsonElement.printStackTrace();
          return null;
        }
        catch (android.net.ParseException paramAnonymousJsonElement)
        {
          while (true)
            paramAnonymousJsonElement.printStackTrace();
        }
      }
    }).setDateFormat(paramString2).create();
    paramString2 = localObject;
    if (gson != null)
      paramString2 = gson.fromJson(paramString1, paramClass);
    return paramString2;
  }

  public static List<?> jsonToList(String paramString)
  {
    Object localObject = null;
    if (gson != null)
    {
      localObject = new TypeToken()
      {
      }
      .getType();
      localObject = (List)gson.fromJson(paramString, (Type)localObject);
    }
    return localObject;
  }

  public static List<?> jsonToList(String paramString, Type paramType)
  {
    List localList = null;
    if (gson != null)
      localList = (List)gson.fromJson(paramString, paramType);
    return localList;
  }

  public static Map<?, ?> jsonToMap(String paramString)
  {
    Object localObject = null;
    if (gson != null)
    {
      localObject = new TypeToken()
      {
      }
      .getType();
      localObject = (Map)gson.fromJson(paramString, (Type)localObject);
    }
    return localObject;
  }

  public static String objectToJson(Object paramObject)
  {
    String str = null;
    if (gson != null)
      str = gson.toJson(paramObject);
    return str;
  }

  public static String objectToJsonDateSerializer(Object paramObject, String paramString)
  {
    Object localObject = null;
    gson = new GsonBuilder().registerTypeHierarchyAdapter(Date.class, new JsonSerializer()
    {
      public JsonElement serialize(Date paramAnonymousDate, Type paramAnonymousType, JsonSerializationContext paramAnonymousJsonSerializationContext)
      {
        return new JsonPrimitive(new SimpleDateFormat(this.val$dateformat).format(paramAnonymousDate));
      }
    }).setDateFormat(paramString).create();
    paramString = localObject;
    if (gson != null)
      paramString = gson.toJson(paramObject);
    return paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.JsonUtil
 * JD-Core Version:    0.6.2
 */