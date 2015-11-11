package com.google.gson;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class JsonArray extends JsonElement
  implements Iterable<JsonElement>
{
  private final List<JsonElement> elements = new ArrayList();

  public void add(JsonElement paramJsonElement)
  {
    Object localObject = paramJsonElement;
    if (paramJsonElement == null)
      localObject = JsonNull.INSTANCE;
    this.elements.add(localObject);
  }

  public void addAll(JsonArray paramJsonArray)
  {
    this.elements.addAll(paramJsonArray.elements);
  }

  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof JsonArray)) && (((JsonArray)paramObject).elements.equals(this.elements)));
  }

  public JsonElement get(int paramInt)
  {
    return (JsonElement)this.elements.get(paramInt);
  }

  public BigDecimal getAsBigDecimal()
  {
    if (this.elements.size() == 1)
      return ((JsonElement)this.elements.get(0)).getAsBigDecimal();
    throw new IllegalStateException();
  }

  public BigInteger getAsBigInteger()
  {
    if (this.elements.size() == 1)
      return ((JsonElement)this.elements.get(0)).getAsBigInteger();
    throw new IllegalStateException();
  }

  public boolean getAsBoolean()
  {
    if (this.elements.size() == 1)
      return ((JsonElement)this.elements.get(0)).getAsBoolean();
    throw new IllegalStateException();
  }

  public byte getAsByte()
  {
    if (this.elements.size() == 1)
      return ((JsonElement)this.elements.get(0)).getAsByte();
    throw new IllegalStateException();
  }

  public char getAsCharacter()
  {
    if (this.elements.size() == 1)
      return ((JsonElement)this.elements.get(0)).getAsCharacter();
    throw new IllegalStateException();
  }

  public double getAsDouble()
  {
    if (this.elements.size() == 1)
      return ((JsonElement)this.elements.get(0)).getAsDouble();
    throw new IllegalStateException();
  }

  public float getAsFloat()
  {
    if (this.elements.size() == 1)
      return ((JsonElement)this.elements.get(0)).getAsFloat();
    throw new IllegalStateException();
  }

  public int getAsInt()
  {
    if (this.elements.size() == 1)
      return ((JsonElement)this.elements.get(0)).getAsInt();
    throw new IllegalStateException();
  }

  public long getAsLong()
  {
    if (this.elements.size() == 1)
      return ((JsonElement)this.elements.get(0)).getAsLong();
    throw new IllegalStateException();
  }

  public Number getAsNumber()
  {
    if (this.elements.size() == 1)
      return ((JsonElement)this.elements.get(0)).getAsNumber();
    throw new IllegalStateException();
  }

  public short getAsShort()
  {
    if (this.elements.size() == 1)
      return ((JsonElement)this.elements.get(0)).getAsShort();
    throw new IllegalStateException();
  }

  public String getAsString()
  {
    if (this.elements.size() == 1)
      return ((JsonElement)this.elements.get(0)).getAsString();
    throw new IllegalStateException();
  }

  public int hashCode()
  {
    return this.elements.hashCode();
  }

  public Iterator<JsonElement> iterator()
  {
    return this.elements.iterator();
  }

  public int size()
  {
    return this.elements.size();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.JsonArray
 * JD-Core Version:    0.6.2
 */