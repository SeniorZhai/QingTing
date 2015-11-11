package u.aly;

public class ct
{
  public final String a;
  public final byte b;
  public final short c;

  public ct()
  {
    this("", (byte)0, (short)0);
  }

  public ct(String paramString, byte paramByte, short paramShort)
  {
    this.a = paramString;
    this.b = paramByte;
    this.c = paramShort;
  }

  public boolean a(ct paramct)
  {
    return (this.b == paramct.b) && (this.c == paramct.c);
  }

  public String toString()
  {
    return "<TField name:'" + this.a + "' type:" + this.b + " field-id:" + this.c + ">";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.ct
 * JD-Core Version:    0.6.2
 */