package u.aly;

public final class cw
{
  public final String a;
  public final byte b;
  public final int c;

  public cw()
  {
    this("", (byte)0, 0);
  }

  public cw(String paramString, byte paramByte, int paramInt)
  {
    this.a = paramString;
    this.b = paramByte;
    this.c = paramInt;
  }

  public boolean a(cw paramcw)
  {
    return (this.a.equals(paramcw.a)) && (this.b == paramcw.b) && (this.c == paramcw.c);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof cw))
      return a((cw)paramObject);
    return false;
  }

  public String toString()
  {
    return "<TMessage name:'" + this.a + "' type: " + this.b + " seqid:" + this.c + ">";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.cw
 * JD-Core Version:    0.6.2
 */