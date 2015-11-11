package com.android.volley;

import java.util.Collections;
import java.util.Map;

public class NetworkResponse
{
  public final byte[] data;
  public final Map<String, String> headers;
  public final boolean notModified;
  public final int statusCode;

  public NetworkResponse(int paramInt, byte[] paramArrayOfByte, Map<String, String> paramMap, boolean paramBoolean)
  {
    this.statusCode = paramInt;
    this.data = paramArrayOfByte;
    this.headers = paramMap;
    this.notModified = paramBoolean;
  }

  public NetworkResponse(byte[] paramArrayOfByte)
  {
    this(200, paramArrayOfByte, Collections.emptyMap(), false);
  }

  public NetworkResponse(byte[] paramArrayOfByte, Map<String, String> paramMap)
  {
    this(200, paramArrayOfByte, paramMap, false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.NetworkResponse
 * JD-Core Version:    0.6.2
 */