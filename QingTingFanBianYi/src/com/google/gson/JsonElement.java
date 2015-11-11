package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class JsonElement
{
  public BigDecimal getAsBigDecimal()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public BigInteger getAsBigInteger()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public boolean getAsBoolean()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  Boolean getAsBooleanWrapper()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public byte getAsByte()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public char getAsCharacter()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public double getAsDouble()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public float getAsFloat()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public int getAsInt()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public JsonArray getAsJsonArray()
  {
    if (isJsonArray())
      return (JsonArray)this;
    throw new IllegalStateException("This is not a JSON Array.");
  }

  public JsonNull getAsJsonNull()
  {
    if (isJsonNull())
      return (JsonNull)this;
    throw new IllegalStateException("This is not a JSON Null.");
  }

  public JsonObject getAsJsonObject()
  {
    if (isJsonObject())
      return (JsonObject)this;
    throw new IllegalStateException("Not a JSON Object: " + this);
  }

  public JsonPrimitive getAsJsonPrimitive()
  {
    if (isJsonPrimitive())
      return (JsonPrimitive)this;
    throw new IllegalStateException("This is not a JSON Primitive.");
  }

  public long getAsLong()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public Number getAsNumber()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public short getAsShort()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public String getAsString()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public boolean isJsonArray()
  {
    return this instanceof JsonArray;
  }

  public boolean isJsonNull()
  {
    return this instanceof JsonNull;
  }

  public boolean isJsonObject()
  {
    return this instanceof JsonObject;
  }

  public boolean isJsonPrimitive()
  {
    return this instanceof JsonPrimitive;
  }

  public String toString()
  {
    try
    {
      Object localObject = new StringWriter();
      JsonWriter localJsonWriter = new JsonWriter((Writer)localObject);
      localJsonWriter.setLenient(true);
      Streams.write(this, localJsonWriter);
      localObject = ((StringWriter)localObject).toString();
      return localObject;
    }
    catch (IOException localIOException)
    {
      throw new AssertionError(localIOException);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.JsonElement
 * JD-Core Version:    0.6.2
 */