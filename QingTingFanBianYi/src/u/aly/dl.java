package u.aly;

public final class dl extends dm
{
  private byte[] a;
  private int b;
  private int c;

  public dl()
  {
  }

  public dl(byte[] paramArrayOfByte)
  {
    a(paramArrayOfByte);
  }

  public dl(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    c(paramArrayOfByte, paramInt1, paramInt2);
  }

  public int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws dn
  {
    int j = h();
    int i = paramInt2;
    if (paramInt2 > j)
      i = j;
    if (i > 0)
    {
      System.arraycopy(this.a, this.b, paramArrayOfByte, paramInt1, i);
      a(i);
    }
    return i;
  }

  public void a(int paramInt)
  {
    this.b += paramInt;
  }

  public void a(byte[] paramArrayOfByte)
  {
    c(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public boolean a()
  {
    return true;
  }

  public void b()
    throws dn
  {
  }

  public void b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws dn
  {
    throw new UnsupportedOperationException("No writing allowed!");
  }

  public void c()
  {
  }

  public void c(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.a = paramArrayOfByte;
    this.b = paramInt1;
    this.c = (paramInt1 + paramInt2);
  }

  public void e()
  {
    this.a = null;
  }

  public byte[] f()
  {
    return this.a;
  }

  public int g()
  {
    return this.b;
  }

  public int h()
  {
    return this.c - this.b;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.dl
 * JD-Core Version:    0.6.2
 */