package u.aly;

import java.io.ByteArrayOutputStream;

public class cb extends ByteArrayOutputStream
{
  public cb()
  {
  }

  public cb(int paramInt)
  {
    super(paramInt);
  }

  public byte[] a()
  {
    return this.buf;
  }

  public int b()
  {
    return this.count;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.cb
 * JD-Core Version:    0.6.2
 */