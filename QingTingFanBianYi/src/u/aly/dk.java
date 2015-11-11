package u.aly;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class dk extends dm
{
  protected InputStream a = null;
  protected OutputStream b = null;

  protected dk()
  {
  }

  public dk(InputStream paramInputStream)
  {
    this.a = paramInputStream;
  }

  public dk(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    this.a = paramInputStream;
    this.b = paramOutputStream;
  }

  public dk(OutputStream paramOutputStream)
  {
    this.b = paramOutputStream;
  }

  public int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws dn
  {
    if (this.a == null)
      throw new dn(1, "Cannot read from null inputStream");
    try
    {
      paramInt1 = this.a.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt1 < 0)
        throw new dn(4);
    }
    catch (IOException paramArrayOfByte)
    {
      throw new dn(0, paramArrayOfByte);
    }
    return paramInt1;
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
    if (this.b == null)
      throw new dn(1, "Cannot write to null outputStream");
    try
    {
      this.b.write(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    catch (IOException paramArrayOfByte)
    {
    }
    throw new dn(0, paramArrayOfByte);
  }

  public void c()
  {
    if (this.a != null);
    try
    {
      this.a.close();
      this.a = null;
      if (this.b == null);
    }
    catch (IOException localIOException1)
    {
      try
      {
        this.b.close();
        this.b = null;
        return;
        localIOException1 = localIOException1;
        localIOException1.printStackTrace();
      }
      catch (IOException localIOException2)
      {
        while (true)
          localIOException2.printStackTrace();
      }
    }
  }

  public void d()
    throws dn
  {
    if (this.b == null)
      throw new dn(1, "Cannot flush null outputStream");
    try
    {
      this.b.flush();
      return;
    }
    catch (IOException localIOException)
    {
      throw new dn(0, localIOException);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.dk
 * JD-Core Version:    0.6.2
 */