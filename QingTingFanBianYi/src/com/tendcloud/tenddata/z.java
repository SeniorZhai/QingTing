package com.tendcloud.tenddata;

import java.io.IOException;

public class z
  implements g
{
  public int a = -1;
  public c b;
  public n c;
  public ah d;

  public void a(q paramq)
  {
    paramq.b(2);
    paramq.a(this.a);
    switch (this.a)
    {
    default:
      throw new IOException("unknown TMessageType");
    case 1:
      paramq.a(this.c);
      return;
    case 2:
      paramq.a(this.b);
      return;
    case 3:
    }
    paramq.a(this.d);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.z
 * JD-Core Version:    0.6.2
 */