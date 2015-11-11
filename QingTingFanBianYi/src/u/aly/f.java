package u.aly;

import android.content.Context;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class f
{
  private static final String a = ".imprint";
  private static final byte[] b = "pbl0".getBytes();
  private at c = null;
  private Context d;

  public f(Context paramContext)
  {
    this.d = paramContext;
  }

  private at a(at paramat1, at paramat2)
  {
    if (paramat2 == null)
      return paramat1;
    Map localMap = paramat1.d();
    Iterator localIterator = paramat2.d().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (((au)localEntry.getValue()).e())
        localMap.put(localEntry.getKey(), localEntry.getValue());
      else
        localMap.remove(localEntry.getKey());
    }
    paramat1.a(paramat2.h());
    paramat1.a(a(paramat1));
    return paramat1;
  }

  private boolean c(at paramat)
  {
    if (!paramat.k().equals(a(paramat)))
      return false;
    paramat = paramat.d().values().iterator();
    while (paramat.hasNext())
    {
      Object localObject = (au)paramat.next();
      byte[] arrayOfByte = c.b(((au)localObject).j());
      localObject = a((au)localObject);
      int i = 0;
      while (i < 4)
      {
        if (arrayOfByte[i] != localObject[i])
          return false;
        i += 1;
      }
    }
    return true;
  }

  public String a(at paramat)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = new TreeMap(paramat.d()).entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append(((au)localEntry.getValue()).c());
      localStringBuilder.append(((au)localEntry.getValue()).f());
      localStringBuilder.append(((au)localEntry.getValue()).j());
    }
    localStringBuilder.append(paramat.b);
    return bv.a(localStringBuilder.toString()).toLowerCase(Locale.US);
  }

  public at a()
  {
    try
    {
      at localat = this.c;
      return localat;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public byte[] a(au paramau)
  {
    Object localObject = ByteBuffer.allocate(8);
    ((ByteBuffer)localObject).order(null);
    ((ByteBuffer)localObject).putLong(paramau.f());
    paramau = ((ByteBuffer)localObject).array();
    localObject = b;
    byte[] arrayOfByte = new byte[4];
    int i = 0;
    while (i < 4)
    {
      arrayOfByte[i] = ((byte)(paramau[i] ^ localObject[i]));
      i += 1;
    }
    return arrayOfByte;
  }

  // ERROR //
  public void b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: new 191	java/io/File
    //   8: dup
    //   9: aload_0
    //   10: getfield 35	u/aly/f:d	Landroid/content/Context;
    //   13: invokevirtual 197	android/content/Context:getFilesDir	()Ljava/io/File;
    //   16: ldc 8
    //   18: invokespecial 200	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   21: invokevirtual 203	java/io/File:exists	()Z
    //   24: ifne +4 -> 28
    //   27: return
    //   28: aload_0
    //   29: getfield 35	u/aly/f:d	Landroid/content/Context;
    //   32: ldc 8
    //   34: invokevirtual 207	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   37: astore_1
    //   38: aload_1
    //   39: astore_2
    //   40: aload_1
    //   41: invokestatic 210	u/aly/bv:b	(Ljava/io/InputStream;)[B
    //   44: astore_3
    //   45: aload_3
    //   46: astore_2
    //   47: aload_1
    //   48: invokestatic 213	u/aly/bv:c	(Ljava/io/InputStream;)V
    //   51: aload_2
    //   52: ifnull -25 -> 27
    //   55: new 38	u/aly/at
    //   58: dup
    //   59: invokespecial 214	u/aly/at:<init>	()V
    //   62: astore_1
    //   63: new 216	u/aly/cc
    //   66: dup
    //   67: invokespecial 217	u/aly/cc:<init>	()V
    //   70: aload_1
    //   71: aload_2
    //   72: invokevirtual 220	u/aly/cc:a	(Lu/aly/bz;[B)V
    //   75: aload_0
    //   76: aload_1
    //   77: putfield 33	u/aly/f:c	Lu/aly/at;
    //   80: return
    //   81: astore_1
    //   82: aload_1
    //   83: invokevirtual 223	java/lang/Exception:printStackTrace	()V
    //   86: return
    //   87: astore_3
    //   88: aconst_null
    //   89: astore_1
    //   90: aload_1
    //   91: astore_2
    //   92: aload_3
    //   93: invokevirtual 223	java/lang/Exception:printStackTrace	()V
    //   96: aload_1
    //   97: invokestatic 213	u/aly/bv:c	(Ljava/io/InputStream;)V
    //   100: aload 4
    //   102: astore_2
    //   103: goto -52 -> 51
    //   106: astore_1
    //   107: aload_2
    //   108: invokestatic 213	u/aly/bv:c	(Ljava/io/InputStream;)V
    //   111: aload_1
    //   112: athrow
    //   113: astore_1
    //   114: goto -7 -> 107
    //   117: astore_3
    //   118: goto -28 -> 90
    //
    // Exception table:
    //   from	to	target	type
    //   55	80	81	java/lang/Exception
    //   28	38	87	java/lang/Exception
    //   28	38	106	finally
    //   40	45	113	finally
    //   92	96	113	finally
    //   40	45	117	java/lang/Exception
  }

  public void b(at paramat)
  {
    if (paramat == null);
    while (!c(paramat))
      return;
    while (true)
    {
      at localat;
      try
      {
        localat = this.c;
        if (localat == null)
        {
          this.c = paramat;
          return;
        }
      }
      finally
      {
      }
      paramat = a(localat, paramat);
    }
  }

  public void c()
  {
    if (this.c == null)
      return;
    try
    {
      byte[] arrayOfByte = new ci().a(this.c);
      bv.a(new File(this.d.getFilesDir(), ".imprint"), arrayOfByte);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public boolean d()
  {
    return new File(this.d.getFilesDir(), ".imprint").delete();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.f
 * JD-Core Version:    0.6.2
 */