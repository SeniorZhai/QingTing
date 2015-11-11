package com.tencent.stat.event;

import android.content.Context;
import com.tencent.stat.StatConfig;
import com.tencent.stat.common.StatCommonHelper;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class AdditionEvent extends Event
{
  Map<String, ?> map = null;

  public AdditionEvent(Context paramContext, int paramInt, Map<String, ?> paramMap)
  {
    super(paramContext, paramInt);
    this.map = paramMap;
  }

  public EventType getType()
  {
    return EventType.ADDITION;
  }

  public boolean onEncode(JSONObject paramJSONObject)
    throws JSONException
  {
    StatCommonHelper.jsonPut(paramJSONObject, "qq", StatConfig.getQQ());
    if ((this.map != null) && (this.map.size() > 0))
    {
      Iterator localIterator = this.map.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        paramJSONObject.put((String)localEntry.getKey(), localEntry.getValue());
      }
    }
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.event.AdditionEvent
 * JD-Core Version:    0.6.2
 */