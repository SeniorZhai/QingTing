package com.intowow.sdk.j;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class k
{
  private static k e = null;
  private String a = null;
  private String b = null;
  private String c = null;
  private String d = null;

  private k(Context paramContext)
  {
    b(paramContext);
  }

  public static long a(String paramString)
  {
    paramString = new File(paramString).getParentFile();
    if (paramString.exists())
    {
      paramString = new StatFs(paramString.getPath());
      return paramString.getBlockCount() * paramString.getBlockSize();
    }
    return -1L;
  }

  public static k a(Context paramContext)
  {
    if (e == null)
      e = new k(paramContext);
    return e;
  }

  public static long b(String paramString)
  {
    paramString = new File(paramString).getParentFile();
    if (paramString.exists())
    {
      paramString = new StatFs(paramString.getPath());
      long l = paramString.getBlockSize();
      return paramString.getAvailableBlocks() * l;
    }
    return -1L;
  }

  private void b(Context paramContext)
  {
    if (paramContext == null)
      return;
    boolean bool = e();
    this.a = (paramContext.getFilesDir() + "/Media/ASSETS/");
    this.b = (paramContext.getFilesDir() + "/Media/METADATA/");
    this.d = (paramContext.getFilesDir() + "/Creatives/");
    Object localObject = new ArrayList();
    ((ArrayList)localObject).add(this.a);
    ((ArrayList)localObject).add(this.b);
    ((ArrayList)localObject).add(this.d);
    paramContext = i.b(paramContext).substring(0, 8);
    paramContext = "/CrystalExpressCN/" + paramContext;
    ((ArrayList)localObject).add(paramContext);
    if (bool)
    {
      this.c = (Environment.getExternalStorageDirectory().getAbsolutePath() + paramContext + "/Creatives/");
      ((ArrayList)localObject).add(this.c);
    }
    paramContext = ((ArrayList)localObject).iterator();
    while (true)
    {
      if (!paramContext.hasNext())
      {
        if (!bool)
          break;
        paramContext = new File(this.c + ".nomedia");
        if (paramContext.exists())
          break;
        try
        {
          paramContext.createNewFile();
          return;
        }
        catch (Exception paramContext)
        {
          return;
        }
      }
      localObject = new File((String)paramContext.next());
      if (!((File)localObject).exists())
        ((File)localObject).mkdirs();
    }
  }

  public static long c(String paramString)
  {
    long l1 = 0L;
    paramString = new File(paramString);
    long l2 = l1;
    if (paramString != null)
    {
      if (paramString.exists())
        break label31;
      l2 = l1;
    }
    label31: 
    do
    {
      do
      {
        return l2;
        paramString = paramString.listFiles();
        l2 = l1;
      }
      while (paramString == null);
      l2 = l1;
    }
    while (paramString.length == 0);
    int j = paramString.length;
    int i = 0;
    while (true)
    {
      l2 = l1;
      if (i >= j)
        break;
      l2 = paramString[i].length();
      i += 1;
      l1 = l2 + l1;
    }
  }

  // ERROR //
  public static String d(String paramString)
  {
    // Byte code:
    //   0: new 159	java/io/FileInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 160	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   8: astore_1
    //   9: sipush 1024
    //   12: newarray byte
    //   14: astore_0
    //   15: ldc 162
    //   17: invokestatic 168	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   20: astore_2
    //   21: iconst_0
    //   22: istore 4
    //   24: iload 4
    //   26: iconst_m1
    //   27: if_icmpne +39 -> 66
    //   30: aload_2
    //   31: invokevirtual 172	java/security/MessageDigest:digest	()[B
    //   34: astore_3
    //   35: ldc 174
    //   37: astore_0
    //   38: iconst_0
    //   39: istore 4
    //   41: aload_3
    //   42: arraylength
    //   43: istore 5
    //   45: iload 4
    //   47: iload 5
    //   49: if_icmplt +67 -> 116
    //   52: aload_0
    //   53: astore_2
    //   54: aload_1
    //   55: ifnull +9 -> 64
    //   58: aload_1
    //   59: invokevirtual 179	java/io/InputStream:close	()V
    //   62: aload_0
    //   63: astore_2
    //   64: aload_2
    //   65: areturn
    //   66: aload_1
    //   67: aload_0
    //   68: invokevirtual 183	java/io/InputStream:read	([B)I
    //   71: istore 5
    //   73: iload 5
    //   75: istore 4
    //   77: iload 5
    //   79: ifle -55 -> 24
    //   82: aload_2
    //   83: aload_0
    //   84: iconst_0
    //   85: iload 5
    //   87: invokevirtual 187	java/security/MessageDigest:update	([BII)V
    //   90: iload 5
    //   92: istore 4
    //   94: goto -70 -> 24
    //   97: astore_0
    //   98: ldc 174
    //   100: astore_0
    //   101: aload_0
    //   102: astore_2
    //   103: aload_1
    //   104: ifnull -40 -> 64
    //   107: aload_1
    //   108: invokevirtual 179	java/io/InputStream:close	()V
    //   111: aload_0
    //   112: areturn
    //   113: astore_1
    //   114: aload_0
    //   115: areturn
    //   116: new 72	java/lang/StringBuilder
    //   119: dup
    //   120: aload_0
    //   121: invokestatic 127	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   124: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   127: aload_3
    //   128: iload 4
    //   130: baload
    //   131: sipush 255
    //   134: iand
    //   135: sipush 256
    //   138: iadd
    //   139: bipush 16
    //   141: invokestatic 191	java/lang/Integer:toString	(II)Ljava/lang/String;
    //   144: iconst_1
    //   145: invokevirtual 194	java/lang/String:substring	(I)Ljava/lang/String;
    //   148: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: astore_2
    //   155: iload 4
    //   157: iconst_1
    //   158: iadd
    //   159: istore 4
    //   161: aload_2
    //   162: astore_0
    //   163: goto -122 -> 41
    //   166: astore_0
    //   167: aconst_null
    //   168: astore_1
    //   169: aload_1
    //   170: ifnull +7 -> 177
    //   173: aload_1
    //   174: invokevirtual 179	java/io/InputStream:close	()V
    //   177: aload_0
    //   178: athrow
    //   179: astore_1
    //   180: goto -3 -> 177
    //   183: astore_1
    //   184: aload_0
    //   185: areturn
    //   186: astore_0
    //   187: goto -18 -> 169
    //   190: astore_0
    //   191: aconst_null
    //   192: astore_1
    //   193: ldc 174
    //   195: astore_0
    //   196: goto -95 -> 101
    //   199: astore_2
    //   200: goto -99 -> 101
    //
    // Exception table:
    //   from	to	target	type
    //   9	21	97	java/lang/Exception
    //   30	35	97	java/lang/Exception
    //   66	73	97	java/lang/Exception
    //   82	90	97	java/lang/Exception
    //   107	111	113	java/lang/Exception
    //   0	9	166	finally
    //   173	177	179	java/lang/Exception
    //   58	62	183	java/lang/Exception
    //   9	21	186	finally
    //   30	35	186	finally
    //   41	45	186	finally
    //   66	73	186	finally
    //   82	90	186	finally
    //   116	155	186	finally
    //   0	9	190	java/lang/Exception
    //   41	45	199	java/lang/Exception
    //   116	155	199	java/lang/Exception
  }

  public static boolean e()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }

  public String a()
  {
    if (e())
      return this.c;
    return this.d;
  }

  public String b()
  {
    return this.a;
  }

  public String c()
  {
    return this.b;
  }

  public long d()
  {
    return c(a());
  }

  public long f()
  {
    if (!e())
      return -1L;
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.k
 * JD-Core Version:    0.6.2
 */