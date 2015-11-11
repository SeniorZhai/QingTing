package u.aly;

public abstract class dm
{
  public abstract int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws dn;

  public void a(int paramInt)
  {
  }

  public abstract boolean a();

  public abstract void b()
    throws dn;

  public void b(byte[] paramArrayOfByte)
    throws dn
  {
    b(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public abstract void b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws dn;

  public abstract void c();

  public int d(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws dn
  {
    int i = 0;
    while (i < paramInt2)
    {
      int j = a(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
      if (j <= 0)
        throw new dn("Cannot read. Remote side has closed. Tried to read " + paramInt2 + " bytes, but only got " + i + " bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)");
      i += j;
    }
    return i;
  }

  public void d()
    throws dn
  {
  }

  public byte[] f()
  {
    return null;
  }

  public int g()
  {
    return 0;
  }

  public int h()
  {
    return -1;
  }

  public boolean i()
  {
    return a();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.dm
 * JD-Core Version:    0.6.2
 */