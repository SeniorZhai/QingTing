package com.google.gson;

public enum LongSerializationPolicy
{
  DEFAULT, STRING;

  public abstract JsonElement serialize(Long paramLong);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.LongSerializationPolicy
 * JD-Core Version:    0.6.2
 */