package com.taobao.munion.base.volley;

import java.util.Collections;
import java.util.Map;

public class i
{
  public final int a;
  public final byte[] b;
  public final Map<String, String> c;
  public final boolean d;

  public i(int paramInt, byte[] paramArrayOfByte, Map<String, String> paramMap, boolean paramBoolean)
  {
    this.a = paramInt;
    this.b = paramArrayOfByte;
    this.c = paramMap;
    this.d = paramBoolean;
  }

  public i(byte[] paramArrayOfByte)
  {
    this(200, paramArrayOfByte, Collections.emptyMap(), false);
  }

  public i(byte[] paramArrayOfByte, Map<String, String> paramMap)
  {
    this(200, paramArrayOfByte, paramMap, false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.i
 * JD-Core Version:    0.6.2
 */