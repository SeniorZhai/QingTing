package com.tencent.stat.event;

import android.content.Context;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.json.JSONException;
import org.json.JSONObject;

public class ErrorEvent extends Event
{
  private String error;
  private int errorAttr;
  private int maxErrorLength = 100;

  public ErrorEvent(Context paramContext, int paramInt1, int paramInt2, Throwable paramThrowable)
  {
    super(paramContext, paramInt1);
    paramContext = paramThrowable.getStackTrace();
    if ((paramContext != null) && (paramContext.length > this.maxErrorLength))
    {
      localObject = new StackTraceElement[this.maxErrorLength];
      paramInt1 = 0;
      while (paramInt1 < this.maxErrorLength)
      {
        localObject[paramInt1] = paramContext[paramInt1];
        paramInt1 += 1;
      }
      paramThrowable.setStackTrace((StackTraceElement[])localObject);
    }
    paramContext = new StringWriter();
    Object localObject = new PrintWriter(paramContext);
    paramThrowable.printStackTrace((PrintWriter)localObject);
    this.error = paramContext.toString();
    this.errorAttr = paramInt2;
    ((PrintWriter)localObject).close();
  }

  public ErrorEvent(Context paramContext, int paramInt, String paramString)
  {
    super(paramContext, paramInt);
    this.error = paramString;
    this.errorAttr = 0;
  }

  public EventType getType()
  {
    return EventType.ERROR;
  }

  public boolean onEncode(JSONObject paramJSONObject)
    throws JSONException
  {
    paramJSONObject.put("er", this.error);
    paramJSONObject.put("ea", this.errorAttr);
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.event.ErrorEvent
 * JD-Core Version:    0.6.2
 */