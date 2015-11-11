package u.aly;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class az extends cj<az, a>
{
  public static final Map<a, cl> a;
  private static final dd d = new dd("PropertyValue");
  private static final ct e = new ct("string_value", (byte)11, (short)1);
  private static final ct f = new ct("long_value", (byte)10, (short)2);

  static
  {
    EnumMap localEnumMap = new EnumMap(a.class);
    localEnumMap.put(a.a, new cl("string_value", (byte)3, new cm((byte)11)));
    localEnumMap.put(a.b, new cl("long_value", (byte)3, new cm((byte)10)));
    a = Collections.unmodifiableMap(localEnumMap);
    cl.a(az.class, a);
  }

  public az()
  {
  }

  public az(a parama, Object paramObject)
  {
    super(parama, paramObject);
  }

  public az(az paramaz)
  {
    super(paramaz);
  }

  public static az a(long paramLong)
  {
    az localaz = new az();
    localaz.b(paramLong);
    return localaz;
  }

  public static az a(String paramString)
  {
    az localaz = new az();
    localaz.b(paramString);
    return localaz;
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      a(new cs(new dk(paramObjectInputStream)));
      return;
    }
    catch (cf paramObjectInputStream)
    {
    }
    throw new IOException(paramObjectInputStream.getMessage());
  }

  private void a(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    try
    {
      b(new cs(new dk(paramObjectOutputStream)));
      return;
    }
    catch (cf paramObjectOutputStream)
    {
    }
    throw new IOException(paramObjectOutputStream.getMessage());
  }

  protected Object a(cy paramcy, ct paramct)
    throws cf
  {
    String str = null;
    a locala = a.a(paramct.c);
    if (locala != null);
    switch (1.a[locala.ordinal()])
    {
    default:
      throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
    case 1:
      if (paramct.b == e.b)
      {
        str = paramcy.z();
        return str;
      }
      db.a(paramcy, paramct.b);
      return null;
    case 2:
    }
    if (paramct.b == f.b)
      return Long.valueOf(paramcy.x());
    db.a(paramcy, paramct.b);
    return null;
  }

  protected Object a(cy paramcy, short paramShort)
    throws cf
  {
    a locala = a.a(paramShort);
    if (locala != null)
    {
      switch (1.a[locala.ordinal()])
      {
      default:
        throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      case 1:
        return paramcy.z();
      case 2:
      }
      return Long.valueOf(paramcy.x());
    }
    throw new cz("Couldn't find a field with field id " + paramShort);
  }

  public a a(int paramInt)
  {
    return a.a(paramInt);
  }

  protected a a(short paramShort)
  {
    return a.b(paramShort);
  }

  public az a()
  {
    return new az(this);
  }

  protected ct a(a parama)
  {
    switch (1.a[parama.ordinal()])
    {
    default:
      throw new IllegalArgumentException("Unknown field id " + parama);
    case 1:
      return e;
    case 2:
    }
    return f;
  }

  protected void a(a parama, Object paramObject)
    throws ClassCastException
  {
    switch (1.a[parama.ordinal()])
    {
    default:
      throw new IllegalArgumentException("Unknown field id " + parama);
    case 1:
      if (!(paramObject instanceof String))
        break;
    case 2:
    }
    do
    {
      return;
      throw new ClassCastException("Was expecting value of type String for field 'string_value', but got " + paramObject.getClass().getSimpleName());
    }
    while ((paramObject instanceof Long));
    throw new ClassCastException("Was expecting value of type Long for field 'long_value', but got " + paramObject.getClass().getSimpleName());
  }

  public boolean a(az paramaz)
  {
    return (paramaz != null) && (i() == paramaz.i()) && (j().equals(paramaz.j()));
  }

  public int b(az paramaz)
  {
    int j = ca.a((Comparable)i(), (Comparable)paramaz.i());
    int i = j;
    if (j == 0)
      i = ca.a(j(), paramaz.j());
    return i;
  }

  public void b(long paramLong)
  {
    this.c = a.b;
    this.b = Long.valueOf(paramLong);
  }

  public void b(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException();
    this.c = a.a;
    this.b = paramString;
  }

  protected dd c()
  {
    return d;
  }

  protected void c(cy paramcy)
    throws cf
  {
    switch (1.a[((a)this.c).ordinal()])
    {
    default:
      throw new IllegalStateException("Cannot write union with unknown field " + this.c);
    case 1:
      paramcy.a((String)this.b);
      return;
    case 2:
    }
    paramcy.a(((Long)this.b).longValue());
  }

  public String d()
  {
    if (i() == a.a)
      return (String)j();
    throw new RuntimeException("Cannot get field 'string_value' because union is currently set to " + a((a)i()).a);
  }

  protected void d(cy paramcy)
    throws cf
  {
    switch (1.a[((a)this.c).ordinal()])
    {
    default:
      throw new IllegalStateException("Cannot write union with unknown field " + this.c);
    case 1:
      paramcy.a((String)this.b);
      return;
    case 2:
    }
    paramcy.a(((Long)this.b).longValue());
  }

  public long e()
  {
    if (i() == a.b)
      return ((Long)j()).longValue();
    throw new RuntimeException("Cannot get field 'long_value' because union is currently set to " + a((a)i()).a);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof az))
      return a((az)paramObject);
    return false;
  }

  public boolean f()
  {
    return this.c == a.a;
  }

  public boolean h()
  {
    return this.c == a.b;
  }

  public int hashCode()
  {
    return 0;
  }

  public static enum a
    implements cg
  {
    private static final Map<String, a> c;
    private final short d;
    private final String e;

    static
    {
      c = new HashMap();
      Iterator localIterator = EnumSet.allOf(a.class).iterator();
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        c.put(locala.b(), locala);
      }
    }

    private a(short paramShort, String paramString)
    {
      this.d = paramShort;
      this.e = paramString;
    }

    public static a a(int paramInt)
    {
      switch (paramInt)
      {
      default:
        return null;
      case 1:
        return a;
      case 2:
      }
      return b;
    }

    public static a a(String paramString)
    {
      return (a)c.get(paramString);
    }

    public static a b(int paramInt)
    {
      a locala = a(paramInt);
      if (locala == null)
        throw new IllegalArgumentException("Field " + paramInt + " doesn't exist!");
      return locala;
    }

    public short a()
    {
      return this.d;
    }

    public String b()
    {
      return this.e;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.az
 * JD-Core Version:    0.6.2
 */