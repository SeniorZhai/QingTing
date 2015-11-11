package com.google.gson.stream;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JsonWriter
  implements Closeable
{
  private String deferredName;
  private boolean htmlSafe;
  private String indent;
  private boolean lenient;
  private final Writer out;
  private String separator;
  private boolean serializeNulls;
  private final List<JsonScope> stack = new ArrayList();

  public JsonWriter(Writer paramWriter)
  {
    this.stack.add(JsonScope.EMPTY_DOCUMENT);
    this.separator = ":";
    this.serializeNulls = true;
    if (paramWriter == null)
      throw new NullPointerException("out == null");
    this.out = paramWriter;
  }

  private void beforeName()
    throws IOException
  {
    JsonScope localJsonScope = peek();
    if (localJsonScope == JsonScope.NONEMPTY_OBJECT)
      this.out.write(44);
    while (localJsonScope == JsonScope.EMPTY_OBJECT)
    {
      newline();
      replaceTop(JsonScope.DANGLING_NAME);
      return;
    }
    throw new IllegalStateException("Nesting problem: " + this.stack);
  }

  private void beforeValue(boolean paramBoolean)
    throws IOException
  {
    switch (1.$SwitchMap$com$google$gson$stream$JsonScope[peek().ordinal()])
    {
    default:
      throw new IllegalStateException("Nesting problem: " + this.stack);
    case 1:
      if ((!this.lenient) && (!paramBoolean))
        throw new IllegalStateException("JSON must start with an array or an object.");
      replaceTop(JsonScope.NONEMPTY_DOCUMENT);
      return;
    case 2:
      replaceTop(JsonScope.NONEMPTY_ARRAY);
      newline();
      return;
    case 3:
      this.out.append(',');
      newline();
      return;
    case 4:
      this.out.append(this.separator);
      replaceTop(JsonScope.NONEMPTY_OBJECT);
      return;
    case 5:
    }
    throw new IllegalStateException("JSON must have only one top-level value.");
  }

  private JsonWriter close(JsonScope paramJsonScope1, JsonScope paramJsonScope2, String paramString)
    throws IOException
  {
    JsonScope localJsonScope = peek();
    if ((localJsonScope != paramJsonScope2) && (localJsonScope != paramJsonScope1))
      throw new IllegalStateException("Nesting problem: " + this.stack);
    if (this.deferredName != null)
      throw new IllegalStateException("Dangling name: " + this.deferredName);
    this.stack.remove(this.stack.size() - 1);
    if (localJsonScope == paramJsonScope2)
      newline();
    this.out.write(paramString);
    return this;
  }

  private void newline()
    throws IOException
  {
    if (this.indent == null);
    while (true)
    {
      return;
      this.out.write("\n");
      int i = 1;
      while (i < this.stack.size())
      {
        this.out.write(this.indent);
        i += 1;
      }
    }
  }

  private JsonWriter open(JsonScope paramJsonScope, String paramString)
    throws IOException
  {
    beforeValue(true);
    this.stack.add(paramJsonScope);
    this.out.write(paramString);
    return this;
  }

  private JsonScope peek()
  {
    return (JsonScope)this.stack.get(this.stack.size() - 1);
  }

  private void replaceTop(JsonScope paramJsonScope)
  {
    this.stack.set(this.stack.size() - 1, paramJsonScope);
  }

  private void string(String paramString)
    throws IOException
  {
    this.out.write("\"");
    int i = 0;
    int j = paramString.length();
    if (i < j)
    {
      int k = paramString.charAt(i);
      switch (k)
      {
      default:
        if (k <= 31)
          this.out.write(String.format("\\u%04x", new Object[] { Integer.valueOf(k) }));
        break;
      case 34:
      case 92:
      case 9:
      case 8:
      case 10:
      case 13:
      case 12:
      case 38:
      case 39:
      case 60:
      case 61:
      case 62:
      case 8232:
      case 8233:
      }
      while (true)
      {
        i += 1;
        break;
        this.out.write(92);
        this.out.write(k);
        continue;
        this.out.write("\\t");
        continue;
        this.out.write("\\b");
        continue;
        this.out.write("\\n");
        continue;
        this.out.write("\\r");
        continue;
        this.out.write("\\f");
        continue;
        if (this.htmlSafe)
        {
          this.out.write(String.format("\\u%04x", new Object[] { Integer.valueOf(k) }));
        }
        else
        {
          this.out.write(k);
          continue;
          this.out.write(String.format("\\u%04x", new Object[] { Integer.valueOf(k) }));
          continue;
          this.out.write(k);
        }
      }
    }
    this.out.write("\"");
  }

  private void writeDeferredName()
    throws IOException
  {
    if (this.deferredName != null)
    {
      beforeName();
      string(this.deferredName);
      this.deferredName = null;
    }
  }

  public JsonWriter beginArray()
    throws IOException
  {
    writeDeferredName();
    return open(JsonScope.EMPTY_ARRAY, "[");
  }

  public JsonWriter beginObject()
    throws IOException
  {
    writeDeferredName();
    return open(JsonScope.EMPTY_OBJECT, "{");
  }

  public void close()
    throws IOException
  {
    this.out.close();
    if (peek() != JsonScope.NONEMPTY_DOCUMENT)
      throw new IOException("Incomplete document");
  }

  public JsonWriter endArray()
    throws IOException
  {
    return close(JsonScope.EMPTY_ARRAY, JsonScope.NONEMPTY_ARRAY, "]");
  }

  public JsonWriter endObject()
    throws IOException
  {
    return close(JsonScope.EMPTY_OBJECT, JsonScope.NONEMPTY_OBJECT, "}");
  }

  public void flush()
    throws IOException
  {
    this.out.flush();
  }

  public final boolean getSerializeNulls()
  {
    return this.serializeNulls;
  }

  public final boolean isHtmlSafe()
  {
    return this.htmlSafe;
  }

  public boolean isLenient()
  {
    return this.lenient;
  }

  public JsonWriter name(String paramString)
    throws IOException
  {
    if (paramString == null)
      throw new NullPointerException("name == null");
    if (this.deferredName != null)
      throw new IllegalStateException();
    this.deferredName = paramString;
    return this;
  }

  public JsonWriter nullValue()
    throws IOException
  {
    if (this.deferredName != null)
    {
      if (this.serializeNulls)
        writeDeferredName();
    }
    else
    {
      beforeValue(false);
      this.out.write("null");
      return this;
    }
    this.deferredName = null;
    return this;
  }

  public final void setHtmlSafe(boolean paramBoolean)
  {
    this.htmlSafe = paramBoolean;
  }

  public final void setIndent(String paramString)
  {
    if (paramString.length() == 0)
    {
      this.indent = null;
      this.separator = ":";
      return;
    }
    this.indent = paramString;
    this.separator = ": ";
  }

  public final void setLenient(boolean paramBoolean)
  {
    this.lenient = paramBoolean;
  }

  public final void setSerializeNulls(boolean paramBoolean)
  {
    this.serializeNulls = paramBoolean;
  }

  public JsonWriter value(double paramDouble)
    throws IOException
  {
    if ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble)))
      throw new IllegalArgumentException("Numeric values must be finite, but was " + paramDouble);
    writeDeferredName();
    beforeValue(false);
    this.out.append(Double.toString(paramDouble));
    return this;
  }

  public JsonWriter value(long paramLong)
    throws IOException
  {
    writeDeferredName();
    beforeValue(false);
    this.out.write(Long.toString(paramLong));
    return this;
  }

  public JsonWriter value(Number paramNumber)
    throws IOException
  {
    if (paramNumber == null)
      return nullValue();
    writeDeferredName();
    String str = paramNumber.toString();
    if ((!this.lenient) && ((str.equals("-Infinity")) || (str.equals("Infinity")) || (str.equals("NaN"))))
      throw new IllegalArgumentException("Numeric values must be finite, but was " + paramNumber);
    beforeValue(false);
    this.out.append(str);
    return this;
  }

  public JsonWriter value(String paramString)
    throws IOException
  {
    if (paramString == null)
      return nullValue();
    writeDeferredName();
    beforeValue(false);
    string(paramString);
    return this;
  }

  public JsonWriter value(boolean paramBoolean)
    throws IOException
  {
    writeDeferredName();
    beforeValue(false);
    Writer localWriter = this.out;
    if (paramBoolean);
    for (String str = "true"; ; str = "false")
    {
      localWriter.write(str);
      return this;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.stream.JsonWriter
 * JD-Core Version:    0.6.2
 */