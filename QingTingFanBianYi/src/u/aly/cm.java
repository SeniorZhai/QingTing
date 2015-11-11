package u.aly;

import java.io.Serializable;

public class cm
  implements Serializable
{
  private final boolean a;
  public final byte b;
  private final String c;
  private final boolean d;

  public cm(byte paramByte)
  {
    this(paramByte, false);
  }

  public cm(byte paramByte, String paramString)
  {
    this.b = paramByte;
    this.a = true;
    this.c = paramString;
    this.d = false;
  }

  public cm(byte paramByte, boolean paramBoolean)
  {
    this.b = paramByte;
    this.a = false;
    this.c = null;
    this.d = paramBoolean;
  }

  public boolean a()
  {
    return this.a;
  }

  public String b()
  {
    return this.c;
  }

  public boolean c()
  {
    return this.b == 12;
  }

  public boolean d()
  {
    return (this.b == 15) || (this.b == 13) || (this.b == 14);
  }

  public boolean e()
  {
    return this.d;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.cm
 * JD-Core Version:    0.6.2
 */