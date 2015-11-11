package u.aly;

public class db
{
  private static int a = 2147483647;

  public static da a(byte[] paramArrayOfByte, da paramda)
  {
    Object localObject;
    if (paramArrayOfByte[0] > 16)
      localObject = new cs.a();
    do
    {
      do
      {
        return localObject;
        localObject = paramda;
      }
      while (paramArrayOfByte.length <= 1);
      localObject = paramda;
    }
    while ((paramArrayOfByte[1] & 0x80) == 0);
    return new cs.a();
  }

  public static void a(int paramInt)
  {
    a = paramInt;
  }

  public static void a(cy paramcy, byte paramByte)
    throws cf
  {
    a(paramcy, paramByte, a);
  }

  public static void a(cy paramcy, byte paramByte, int paramInt)
    throws cf
  {
    byte b2 = 0;
    byte b3 = 0;
    byte b1 = 0;
    if (paramInt <= 0)
      throw new cf("Maximum skip depth exceeded");
    switch (paramByte)
    {
    case 5:
    case 7:
    case 9:
    default:
      return;
    case 2:
      paramcy.t();
      return;
    case 3:
      paramcy.u();
      return;
    case 6:
      paramcy.v();
      return;
    case 8:
      paramcy.w();
      return;
    case 10:
      paramcy.x();
      return;
    case 4:
      paramcy.y();
      return;
    case 11:
      paramcy.A();
      return;
    case 12:
      paramcy.j();
      while (true)
      {
        localObject = paramcy.l();
        if (((ct)localObject).b == 0)
        {
          paramcy.k();
          return;
        }
        a(paramcy, ((ct)localObject).b, paramInt - 1);
        paramcy.m();
      }
    case 13:
      localObject = paramcy.n();
      paramByte = b1;
      while (paramByte < ((cv)localObject).c)
      {
        a(paramcy, ((cv)localObject).a, paramInt - 1);
        a(paramcy, ((cv)localObject).b, paramInt - 1);
        paramByte += 1;
      }
      paramcy.o();
      return;
    case 14:
      localObject = paramcy.r();
      paramByte = b2;
      while (paramByte < ((dc)localObject).b)
      {
        a(paramcy, ((dc)localObject).a, paramInt - 1);
        paramByte += 1;
      }
      paramcy.s();
      return;
    case 15:
    }
    Object localObject = paramcy.p();
    paramByte = b3;
    while (paramByte < ((cu)localObject).b)
    {
      a(paramcy, ((cu)localObject).a, paramInt - 1);
      paramByte += 1;
    }
    paramcy.q();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.db
 * JD-Core Version:    0.6.2
 */