package org.json;

import java.io.StringWriter;

public class JSONStringer extends JSONWriter
{
  public JSONStringer()
  {
    super(new StringWriter());
  }

  public String toString()
  {
    if (this.mode == 'd')
      return this.writer.toString();
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.json.JSONStringer
 * JD-Core Version:    0.6.2
 */