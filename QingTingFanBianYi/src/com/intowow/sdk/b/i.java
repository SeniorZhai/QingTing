package com.intowow.sdk.b;

import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;

public class i extends GZIPInputStream
{
  private i a;
  private i b;
  private int c;
  private boolean d;

  private i(i parami)
  {
    super(parami.in);
    this.c = -1;
    if (parami.a == null);
    while (true)
    {
      this.a = parami;
      this.a.b = this;
      return;
      parami = parami.a;
    }
  }

  private i(i parami, int paramInt)
  {
    super(parami.in, paramInt);
    this.c = paramInt;
    if (parami.a == null);
    while (true)
    {
      this.a = parami;
      this.a.b = this;
      return;
      parami = parami.a;
    }
  }

  public i(InputStream paramInputStream)
  {
    super(new PushbackInputStream(paramInputStream, 1024));
    this.c = -1;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.d)
      i = -1;
    int j;
    do
    {
      return i;
      if (this.b != null)
        return this.b.read(paramArrayOfByte, paramInt1, paramInt2);
      j = super.read(paramArrayOfByte, paramInt1, paramInt2);
      i = j;
    }
    while (j != -1);
    int i = this.inf.getRemaining() - 8;
    if (i > 0)
    {
      ((PushbackInputStream)this.in).unread(this.buf, this.len - i, i);
      if (this.c != -1)
        break label161;
    }
    label161: for (Object localObject = new i(this); ; localObject = new i(this, this.c))
    {
      return ((i)localObject).read(paramArrayOfByte, paramInt1, paramInt2);
      localObject = new byte[1];
      if (this.in.read((byte[])localObject, 0, 1) == -1)
      {
        this.d = true;
        return -1;
      }
      ((PushbackInputStream)this.in).unread((byte[])localObject, 0, 1);
      break;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.b.i
 * JD-Core Version:    0.6.2
 */