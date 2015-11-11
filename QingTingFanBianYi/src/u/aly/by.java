package u.aly;

public class by extends cf
{
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  public static final int d = 3;
  public static final int e = 4;
  public static final int f = 5;
  public static final int g = 6;
  public static final int h = 7;
  private static final dd j = new dd("TApplicationException");
  private static final ct k = new ct("message", (byte)11, (short)1);
  private static final ct l = new ct("type", (byte)8, (short)2);
  private static final long m = 1L;
  protected int i = 0;

  public by()
  {
  }

  public by(int paramInt)
  {
    this.i = paramInt;
  }

  public by(int paramInt, String paramString)
  {
    super(paramString);
    this.i = paramInt;
  }

  public by(String paramString)
  {
    super(paramString);
  }

  public static by a(cy paramcy)
    throws cf
  {
    paramcy.j();
    String str = null;
    int n = 0;
    ct localct = paramcy.l();
    if (localct.b == 0)
    {
      paramcy.k();
      return new by(n, str);
    }
    switch (localct.c)
    {
    default:
      db.a(paramcy, localct.b);
    case 1:
    case 2:
    }
    while (true)
    {
      paramcy.m();
      break;
      if (localct.b == 11)
      {
        str = paramcy.z();
      }
      else
      {
        db.a(paramcy, localct.b);
        continue;
        if (localct.b == 8)
          n = paramcy.w();
        else
          db.a(paramcy, localct.b);
      }
    }
  }

  public int a()
  {
    return this.i;
  }

  public void b(cy paramcy)
    throws cf
  {
    paramcy.a(j);
    if (getMessage() != null)
    {
      paramcy.a(k);
      paramcy.a(getMessage());
      paramcy.c();
    }
    paramcy.a(l);
    paramcy.a(this.i);
    paramcy.c();
    paramcy.d();
    paramcy.b();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.by
 * JD-Core Version:    0.6.2
 */