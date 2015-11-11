package weibo4android.org.json;

import java.io.IOException;
import java.io.Writer;

public class JSONWriter
{
  private static final int maxdepth = 20;
  private boolean comma = false;
  protected char mode = 'i';
  private JSONObject[] stack = new JSONObject[20];
  private int top = 0;
  protected Writer writer;

  public JSONWriter(Writer paramWriter)
  {
    this.writer = paramWriter;
  }

  private JSONWriter append(String paramString)
    throws JSONException
  {
    if (paramString == null)
      throw new JSONException("Null pointer");
    if ((this.mode == 'o') || (this.mode == 'a'))
      try
      {
        if ((this.comma) && (this.mode == 'a'))
          this.writer.write(44);
        this.writer.write(paramString);
        if (this.mode == 'o')
          this.mode = 'k';
        this.comma = true;
        return this;
      }
      catch (IOException paramString)
      {
        throw new JSONException(paramString);
      }
    throw new JSONException("Value out of sequence.");
  }

  private JSONWriter end(char paramChar1, char paramChar2)
    throws JSONException
  {
    if (this.mode != paramChar1)
    {
      if (paramChar1 == 'o');
      for (String str = "Misplaced endObject."; ; str = "Misplaced endArray.")
        throw new JSONException(str);
    }
    pop(paramChar1);
    try
    {
      this.writer.write(paramChar2);
      this.comma = true;
      return this;
    }
    catch (IOException localIOException)
    {
      throw new JSONException(localIOException);
    }
  }

  private void pop(char paramChar)
    throws JSONException
  {
    char c1 = 'a';
    if (this.top <= 0)
      throw new JSONException("Nesting error.");
    if (this.stack[(this.top - 1)] == null);
    for (char c2 = 'a'; c2 != paramChar; c2 = 'k')
      throw new JSONException("Nesting error.");
    this.top -= 1;
    if (this.top == 0)
      c1 = 'd';
    while (true)
    {
      this.mode = c1;
      return;
      if (this.stack[(this.top - 1)] != null)
        c1 = 'k';
    }
  }

  private void push(JSONObject paramJSONObject)
    throws JSONException
  {
    if (this.top >= 20)
      throw new JSONException("Nesting too deep.");
    this.stack[this.top] = paramJSONObject;
    if (paramJSONObject == null);
    for (char c = 'a'; ; c = 'k')
    {
      this.mode = c;
      this.top += 1;
      return;
    }
  }

  public JSONWriter array()
    throws JSONException
  {
    if ((this.mode == 'i') || (this.mode == 'o') || (this.mode == 'a'))
    {
      push(null);
      append("[");
      this.comma = false;
      return this;
    }
    throw new JSONException("Misplaced array.");
  }

  public JSONWriter endArray()
    throws JSONException
  {
    return end('a', ']');
  }

  public JSONWriter endObject()
    throws JSONException
  {
    return end('k', '}');
  }

  public JSONWriter key(String paramString)
    throws JSONException
  {
    if (paramString == null)
      throw new JSONException("Null key.");
    if (this.mode == 'k')
      try
      {
        if (this.comma)
          this.writer.write(44);
        this.stack[(this.top - 1)].putOnce(paramString, Boolean.TRUE);
        this.writer.write(JSONObject.quote(paramString));
        this.writer.write(58);
        this.comma = false;
        this.mode = 'o';
        return this;
      }
      catch (IOException paramString)
      {
        throw new JSONException(paramString);
      }
    throw new JSONException("Misplaced key.");
  }

  public JSONWriter object()
    throws JSONException
  {
    if (this.mode == 'i')
      this.mode = 'o';
    if ((this.mode == 'o') || (this.mode == 'a'))
    {
      append("{");
      push(new JSONObject());
      this.comma = false;
      return this;
    }
    throw new JSONException("Misplaced object.");
  }

  public JSONWriter value(double paramDouble)
    throws JSONException
  {
    return value(new Double(paramDouble));
  }

  public JSONWriter value(long paramLong)
    throws JSONException
  {
    return append(Long.toString(paramLong));
  }

  public JSONWriter value(Object paramObject)
    throws JSONException
  {
    return append(JSONObject.valueToString(paramObject));
  }

  public JSONWriter value(boolean paramBoolean)
    throws JSONException
  {
    if (paramBoolean);
    for (String str = "true"; ; str = "false")
      return append(str);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.org.json.JSONWriter
 * JD-Core Version:    0.6.2
 */