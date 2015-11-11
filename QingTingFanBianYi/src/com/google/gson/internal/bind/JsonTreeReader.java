package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class JsonTreeReader extends JsonReader
{
  private static final Object SENTINEL_CLOSED = new Object();
  private static final Reader UNREADABLE_READER = new Reader()
  {
    public void close()
      throws IOException
    {
      throw new AssertionError();
    }

    public int read(char[] paramAnonymousArrayOfChar, int paramAnonymousInt1, int paramAnonymousInt2)
      throws IOException
    {
      throw new AssertionError();
    }
  };
  private final List<Object> stack = new ArrayList();

  public JsonTreeReader(JsonElement paramJsonElement)
  {
    super(UNREADABLE_READER);
    this.stack.add(paramJsonElement);
  }

  private void expect(JsonToken paramJsonToken)
    throws IOException
  {
    if (peek() != paramJsonToken)
      throw new IllegalStateException("Expected " + paramJsonToken + " but was " + peek());
  }

  private Object peekStack()
  {
    return this.stack.get(this.stack.size() - 1);
  }

  private Object popStack()
  {
    return this.stack.remove(this.stack.size() - 1);
  }

  public void beginArray()
    throws IOException
  {
    expect(JsonToken.BEGIN_ARRAY);
    JsonArray localJsonArray = (JsonArray)peekStack();
    this.stack.add(localJsonArray.iterator());
  }

  public void beginObject()
    throws IOException
  {
    expect(JsonToken.BEGIN_OBJECT);
    JsonObject localJsonObject = (JsonObject)peekStack();
    this.stack.add(localJsonObject.entrySet().iterator());
  }

  public void close()
    throws IOException
  {
    this.stack.clear();
    this.stack.add(SENTINEL_CLOSED);
  }

  public void endArray()
    throws IOException
  {
    expect(JsonToken.END_ARRAY);
    popStack();
    popStack();
  }

  public void endObject()
    throws IOException
  {
    expect(JsonToken.END_OBJECT);
    popStack();
    popStack();
  }

  public boolean hasNext()
    throws IOException
  {
    JsonToken localJsonToken = peek();
    return (localJsonToken != JsonToken.END_OBJECT) && (localJsonToken != JsonToken.END_ARRAY);
  }

  public boolean nextBoolean()
    throws IOException
  {
    expect(JsonToken.BOOLEAN);
    return ((JsonPrimitive)popStack()).getAsBoolean();
  }

  public double nextDouble()
    throws IOException
  {
    JsonToken localJsonToken = peek();
    if ((localJsonToken != JsonToken.NUMBER) && (localJsonToken != JsonToken.STRING))
      throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + localJsonToken);
    double d = ((JsonPrimitive)peekStack()).getAsDouble();
    if ((!isLenient()) && ((Double.isNaN(d)) || (Double.isInfinite(d))))
      throw new NumberFormatException("JSON forbids NaN and infinities: " + d);
    popStack();
    return d;
  }

  public int nextInt()
    throws IOException
  {
    JsonToken localJsonToken = peek();
    if ((localJsonToken != JsonToken.NUMBER) && (localJsonToken != JsonToken.STRING))
      throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + localJsonToken);
    int i = ((JsonPrimitive)peekStack()).getAsInt();
    popStack();
    return i;
  }

  public long nextLong()
    throws IOException
  {
    JsonToken localJsonToken = peek();
    if ((localJsonToken != JsonToken.NUMBER) && (localJsonToken != JsonToken.STRING))
      throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + localJsonToken);
    long l = ((JsonPrimitive)peekStack()).getAsLong();
    popStack();
    return l;
  }

  public String nextName()
    throws IOException
  {
    expect(JsonToken.NAME);
    Map.Entry localEntry = (Map.Entry)((Iterator)peekStack()).next();
    this.stack.add(localEntry.getValue());
    return (String)localEntry.getKey();
  }

  public void nextNull()
    throws IOException
  {
    expect(JsonToken.NULL);
    popStack();
  }

  public String nextString()
    throws IOException
  {
    JsonToken localJsonToken = peek();
    if ((localJsonToken != JsonToken.STRING) && (localJsonToken != JsonToken.NUMBER))
      throw new IllegalStateException("Expected " + JsonToken.STRING + " but was " + localJsonToken);
    return ((JsonPrimitive)popStack()).getAsString();
  }

  public JsonToken peek()
    throws IOException
  {
    if (this.stack.isEmpty())
      return JsonToken.END_DOCUMENT;
    Object localObject = peekStack();
    if ((localObject instanceof Iterator))
    {
      boolean bool = this.stack.get(this.stack.size() - 2) instanceof JsonObject;
      localObject = (Iterator)localObject;
      if (((Iterator)localObject).hasNext())
      {
        if (bool)
          return JsonToken.NAME;
        this.stack.add(((Iterator)localObject).next());
        return peek();
      }
      if (bool)
        return JsonToken.END_OBJECT;
      return JsonToken.END_ARRAY;
    }
    if ((localObject instanceof JsonObject))
      return JsonToken.BEGIN_OBJECT;
    if ((localObject instanceof JsonArray))
      return JsonToken.BEGIN_ARRAY;
    if ((localObject instanceof JsonPrimitive))
    {
      localObject = (JsonPrimitive)localObject;
      if (((JsonPrimitive)localObject).isString())
        return JsonToken.STRING;
      if (((JsonPrimitive)localObject).isBoolean())
        return JsonToken.BOOLEAN;
      if (((JsonPrimitive)localObject).isNumber())
        return JsonToken.NUMBER;
      throw new AssertionError();
    }
    if ((localObject instanceof JsonNull))
      return JsonToken.NULL;
    if (localObject == SENTINEL_CLOSED)
      throw new IllegalStateException("JsonReader is closed");
    throw new AssertionError();
  }

  public void promoteNameToValue()
    throws IOException
  {
    expect(JsonToken.NAME);
    Map.Entry localEntry = (Map.Entry)((Iterator)peekStack()).next();
    this.stack.add(localEntry.getValue());
    this.stack.add(new JsonPrimitive((String)localEntry.getKey()));
  }

  public void skipValue()
    throws IOException
  {
    if (peek() == JsonToken.NAME)
    {
      nextName();
      return;
    }
    popStack();
  }

  public String toString()
  {
    return getClass().getSimpleName();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.internal.bind.JsonTreeReader
 * JD-Core Version:    0.6.2
 */