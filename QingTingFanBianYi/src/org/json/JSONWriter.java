package org.json;

import java.io.IOException;
import java.io.Writer;

public class JSONWriter
{
  private static final int maxdepth = 20;
  private boolean comma = false;
  protected char mode = 'i';
  private char[] stack = new char[20];
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
    if ((this.top <= 0) || (this.stack[(this.top - 1)] != paramChar))
      throw new JSONException("Nesting error.");
    this.top -= 1;
    if (this.top == 0);
    for (char c = 'd'; ; c = this.stack[(this.top - 1)])
    {
      this.mode = c;
      return;
    }
  }

  private void push(char paramChar)
    throws JSONException
  {
    if (this.top >= 20)
      throw new JSONException("Nesting too deep.");
    this.stack[this.top] = paramChar;
    this.mode = paramChar;
    this.top += 1;
  }

  public JSONWriter array()
    throws JSONException
  {
    if ((this.mode == 'i') || (this.mode == 'o') || (this.mode == 'a'))
    {
      push('a');
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
      push('k');
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
 * Qualified Name:     org.json.JSONWriter
 * JD-Core Version:    0.6.2
 */