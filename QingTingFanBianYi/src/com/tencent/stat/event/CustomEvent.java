package com.tencent.stat.event;

import android.content.Context;
import java.util.Arrays;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomEvent extends Event
{
  private long duration = -1L;
  protected Key key = new Key();

  public CustomEvent(Context paramContext, int paramInt, String paramString)
  {
    super(paramContext, paramInt);
    this.key.id = paramString;
  }

  public Key getKey()
  {
    return this.key;
  }

  public EventType getType()
  {
    return EventType.CUSTOM;
  }

  public boolean onEncode(JSONObject paramJSONObject)
    throws JSONException
  {
    paramJSONObject.put("ei", this.key.id);
    if (this.duration > 0L)
      paramJSONObject.put("du", this.duration);
    if (this.key.args != null)
    {
      JSONArray localJSONArray = new JSONArray();
      String[] arrayOfString = this.key.args;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        localJSONArray.put(arrayOfString[i]);
        i += 1;
      }
      paramJSONObject.put("ar", localJSONArray);
    }
    if (this.key.prop != null)
      paramJSONObject.put("kv", new JSONObject(this.key.prop));
    return true;
  }

  public void setArgs(String[] paramArrayOfString)
  {
    this.key.args = paramArrayOfString;
  }

  public void setDuration(long paramLong)
  {
    this.duration = paramLong;
  }

  public void setProperties(Properties paramProperties)
  {
    this.key.prop = paramProperties;
  }

  public static class Key
  {
    String[] args;
    String id;
    Properties prop = null;

    public boolean equals(Object paramObject)
    {
      if (this == paramObject);
      int i;
      label81: 
      do
      {
        return true;
        if (!(paramObject instanceof Key))
          break;
        paramObject = (Key)paramObject;
        if ((this.id.equals(paramObject.id)) && (Arrays.equals(this.args, paramObject.args)));
        for (i = 1; ; i = 0)
        {
          if (this.prop == null)
            break label81;
          if ((i != 0) && (this.prop.equals(paramObject.prop)))
            break;
          return false;
        }
      }
      while ((i != 0) && (paramObject.prop == null));
      return false;
      return false;
    }

    public int hashCode()
    {
      int j = 0;
      if (this.id != null)
        j = this.id.hashCode();
      int i = j;
      if (this.args != null)
        i = j ^ Arrays.hashCode(this.args);
      j = i;
      if (this.prop != null)
        j = i ^ this.prop.hashCode();
      return j;
    }

    public String toString()
    {
      String str3 = this.id;
      String str1 = "";
      if (this.args != null)
      {
        str1 = this.args[0];
        int i = 1;
        while (i < this.args.length)
        {
          str1 = str1 + "," + this.args[i];
          i += 1;
        }
        str1 = "[" + str1 + "]";
      }
      String str2 = str1;
      if (this.prop != null)
        str2 = str1 + this.prop.toString();
      return str3 + str2;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.event.CustomEvent
 * JD-Core Version:    0.6.2
 */