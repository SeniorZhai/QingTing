package u.aly;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class ci
{
  private final ByteArrayOutputStream a = new ByteArrayOutputStream();
  private final dk b = new dk(this.a);
  private cy c;

  public ci()
  {
    this(new cs.a());
  }

  public ci(da paramda)
  {
    this.c = paramda.a(this.b);
  }

  public String a(bz parambz, String paramString)
    throws cf
  {
    try
    {
      parambz = new String(a(parambz), paramString);
      return parambz;
    }
    catch (UnsupportedEncodingException parambz)
    {
    }
    throw new cf("JVM DOES NOT SUPPORT ENCODING: " + paramString);
  }

  public byte[] a(bz parambz)
    throws cf
  {
    this.a.reset();
    parambz.b(this.c);
    return this.a.toByteArray();
  }

  public String b(bz parambz)
    throws cf
  {
    return new String(a(parambz));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.ci
 * JD-Core Version:    0.6.2
 */