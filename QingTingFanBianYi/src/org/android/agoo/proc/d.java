package org.android.agoo.proc;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.umeng.message.proguard.aN;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;

public class d
{
  public static String a;
  public static final String b = "android";
  public static final String c = "libcockroach.so";
  private static final boolean d = false;
  private static final String e = "SEProtect-";
  private static final String f = "test-";
  private static final String g = "1.3.3";
  private static String h = "SEProtect-1.3.3";
  private static final String i = "cockroach";
  private static final String[] j;
  private static final String[] k;
  private static final String[] l;
  private static HashMap<String, String[]> m;
  private Context n = null;
  private File o = null;

  static
  {
    a = null;
    j = new String[] { "cc7adaf0aaeb510ee4a3d62fd4b2b12c99a9474bb52d879024482bff6c8e2e52" };
    k = new String[] { "2a10e0cdfb83ed0358321c09d0da1ce9f1d524060d8a19671580a599bcb9bcba" };
    l = new String[] { "8e5677fa96dc5dde287126c92487f6b29160799d5d4acfe4d7337adbf80e9f7f" };
    m = new HashMap();
    a = "lib" + h + ".so";
    m.put(a, j);
    m.put("android", k);
    m.put("libcockroach.so", l);
  }

  private d(Context paramContext)
  {
    this.n = paramContext;
    try
    {
      this.o = this.n.getFilesDir();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  private static String a(Build paramBuild, String paramString)
  {
    try
    {
      paramBuild = Build.class.getField(paramString).get(paramBuild).toString();
      return paramBuild;
    }
    catch (Throwable paramBuild)
    {
    }
    return "Unknown";
  }

  private a a(String paramString)
  {
    a locala = new a();
    paramString = c(paramString);
    if ((paramString != null) && (paramString.exists()));
    try
    {
      System.load(paramString.getAbsolutePath());
      Log.i("SOManager", "Call System.load() by SOManager");
      locala.b(true);
      locala.a(true);
      return locala;
    }
    catch (Throwable paramString)
    {
    }
    return locala;
  }

  public static d a(Context paramContext)
  {
    if (paramContext != null)
      return new d(paramContext.getApplicationContext());
    return null;
  }

  // ERROR //
  private boolean a(String paramString, java.io.FileInputStream paramFileInputStream)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +9 -> 10
    //   4: iconst_0
    //   5: istore 5
    //   7: iload 5
    //   9: ireturn
    //   10: getstatic 70	org/android/agoo/proc/d:m	Ljava/util/HashMap;
    //   13: aload_1
    //   14: invokevirtual 165	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   17: checkcast 166	[Ljava/lang/String;
    //   20: astore_1
    //   21: aload_2
    //   22: ifnull +92 -> 114
    //   25: aload_2
    //   26: invokevirtual 172	java/io/FileInputStream:available	()I
    //   29: ifle +85 -> 114
    //   32: aload_2
    //   33: invokestatic 177	com/umeng/message/proguard/aN:c	(Ljava/io/InputStream;)Ljava/lang/String;
    //   36: astore_3
    //   37: aload_3
    //   38: ifnull +16 -> 54
    //   41: ldc 179
    //   43: aload_3
    //   44: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   47: istore 5
    //   49: iload 5
    //   51: ifeq +13 -> 64
    //   54: aload_2
    //   55: ifnull +7 -> 62
    //   58: aload_2
    //   59: invokevirtual 186	java/io/FileInputStream:close	()V
    //   62: iconst_0
    //   63: ireturn
    //   64: iconst_0
    //   65: istore 4
    //   67: iload 4
    //   69: aload_1
    //   70: arraylength
    //   71: if_icmpge +43 -> 114
    //   74: aload_1
    //   75: iload 4
    //   77: aaload
    //   78: aload_3
    //   79: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   82: istore 5
    //   84: iload 5
    //   86: ifeq +19 -> 105
    //   89: iconst_1
    //   90: istore 5
    //   92: aload_2
    //   93: ifnull -86 -> 7
    //   96: aload_2
    //   97: invokevirtual 186	java/io/FileInputStream:close	()V
    //   100: iconst_1
    //   101: ireturn
    //   102: astore_1
    //   103: iconst_1
    //   104: ireturn
    //   105: iload 4
    //   107: iconst_1
    //   108: iadd
    //   109: istore 4
    //   111: goto -44 -> 67
    //   114: aload_2
    //   115: ifnull +7 -> 122
    //   118: aload_2
    //   119: invokevirtual 186	java/io/FileInputStream:close	()V
    //   122: iconst_0
    //   123: ireturn
    //   124: astore_1
    //   125: aload_2
    //   126: ifnull -4 -> 122
    //   129: aload_2
    //   130: invokevirtual 186	java/io/FileInputStream:close	()V
    //   133: goto -11 -> 122
    //   136: astore_1
    //   137: goto -15 -> 122
    //   140: astore_1
    //   141: aload_2
    //   142: ifnull +7 -> 149
    //   145: aload_2
    //   146: invokevirtual 186	java/io/FileInputStream:close	()V
    //   149: aload_1
    //   150: athrow
    //   151: astore_1
    //   152: goto -90 -> 62
    //   155: astore_1
    //   156: goto -34 -> 122
    //   159: astore_2
    //   160: goto -11 -> 149
    //
    // Exception table:
    //   from	to	target	type
    //   96	100	102	java/lang/Throwable
    //   10	21	124	java/lang/Throwable
    //   25	37	124	java/lang/Throwable
    //   41	49	124	java/lang/Throwable
    //   67	84	124	java/lang/Throwable
    //   129	133	136	java/lang/Throwable
    //   10	21	140	finally
    //   25	37	140	finally
    //   41	49	140	finally
    //   67	84	140	finally
    //   58	62	151	java/lang/Throwable
    //   118	122	155	java/lang/Throwable
    //   145	149	159	java/lang/Throwable
  }

  private boolean a(String paramString, byte[] paramArrayOfByte)
  {
    if (paramString == null);
    while (true)
    {
      return false;
      paramString = (String[])m.get(paramString);
      if ((paramArrayOfByte != null) && (paramString != null))
      {
        paramArrayOfByte = aN.a(paramArrayOfByte);
        if ((paramArrayOfByte != null) && (!"".equals(paramArrayOfByte)))
        {
          int i1 = 0;
          while (i1 < paramString.length)
          {
            if (paramString[i1].equals(paramArrayOfByte))
              return true;
            i1 += 1;
          }
        }
      }
    }
  }

  private void b(String paramString)
  {
    c(paramString);
  }

  // ERROR //
  private File c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 98	org/android/agoo/proc/d:o	Ljava/io/File;
    //   4: ifnonnull +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: aload_0
    //   10: getfield 98	org/android/agoo/proc/d:o	Ljava/io/File;
    //   13: invokevirtual 133	java/io/File:exists	()Z
    //   16: ifne +11 -> 27
    //   19: aload_0
    //   20: getfield 98	org/android/agoo/proc/d:o	Ljava/io/File;
    //   23: invokevirtual 195	java/io/File:mkdir	()Z
    //   26: pop
    //   27: aload_0
    //   28: getfield 98	org/android/agoo/proc/d:o	Ljava/io/File;
    //   31: invokevirtual 133	java/io/File:exists	()Z
    //   34: ifeq -27 -> 7
    //   37: new 129	java/io/File
    //   40: dup
    //   41: aload_0
    //   42: getfield 98	org/android/agoo/proc/d:o	Ljava/io/File;
    //   45: aload_1
    //   46: invokespecial 198	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   49: astore_2
    //   50: aload_2
    //   51: invokevirtual 133	java/io/File:exists	()Z
    //   54: ifeq +29 -> 83
    //   57: aload_0
    //   58: aload_1
    //   59: new 168	java/io/FileInputStream
    //   62: dup
    //   63: aload_2
    //   64: invokespecial 201	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   67: invokespecial 203	org/android/agoo/proc/d:a	(Ljava/lang/String;Ljava/io/FileInputStream;)Z
    //   70: ifeq +13 -> 83
    //   73: ldc 205
    //   75: ldc 207
    //   77: invokestatic 151	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   80: pop
    //   81: aload_2
    //   82: areturn
    //   83: ldc 205
    //   85: ldc 209
    //   87: invokestatic 151	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   90: pop
    //   91: aload_1
    //   92: new 107	android/os/Build
    //   95: dup
    //   96: invokespecial 210	android/os/Build:<init>	()V
    //   99: ldc 212
    //   101: invokestatic 214	org/android/agoo/proc/d:a	(Landroid/os/Build;Ljava/lang/String;)Ljava/lang/String;
    //   104: invokestatic 219	org/android/agoo/proc/c:a	(Ljava/lang/String;Ljava/lang/String;)[B
    //   107: astore 4
    //   109: aload 4
    //   111: ifnull +177 -> 288
    //   114: aload_0
    //   115: aload_1
    //   116: aload 4
    //   118: invokespecial 221	org/android/agoo/proc/d:a	(Ljava/lang/String;[B)Z
    //   121: ifne +13 -> 134
    //   124: ldc 205
    //   126: ldc 223
    //   128: invokestatic 151	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   131: pop
    //   132: aconst_null
    //   133: areturn
    //   134: new 129	java/io/File
    //   137: dup
    //   138: aload_0
    //   139: getfield 98	org/android/agoo/proc/d:o	Ljava/io/File;
    //   142: aload_1
    //   143: invokespecial 198	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   146: astore_3
    //   147: aload_3
    //   148: ifnull -141 -> 7
    //   151: aload_3
    //   152: invokevirtual 226	java/io/File:getParentFile	()Ljava/io/File;
    //   155: invokevirtual 229	java/io/File:canWrite	()Z
    //   158: ifeq -151 -> 7
    //   161: aload_3
    //   162: invokevirtual 133	java/io/File:exists	()Z
    //   165: ifeq +8 -> 173
    //   168: aload_3
    //   169: invokevirtual 232	java/io/File:delete	()Z
    //   172: pop
    //   173: new 234	java/io/FileOutputStream
    //   176: dup
    //   177: aload_3
    //   178: invokespecial 235	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   181: astore_2
    //   182: aload_2
    //   183: aload 4
    //   185: iconst_0
    //   186: aload 4
    //   188: arraylength
    //   189: invokevirtual 239	java/io/FileOutputStream:write	([BII)V
    //   192: aload_0
    //   193: aload_1
    //   194: new 168	java/io/FileInputStream
    //   197: dup
    //   198: aload_3
    //   199: invokespecial 201	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   202: invokespecial 203	org/android/agoo/proc/d:a	(Ljava/lang/String;Ljava/io/FileInputStream;)Z
    //   205: ifeq +21 -> 226
    //   208: ldc 205
    //   210: ldc 207
    //   212: invokestatic 151	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   215: pop
    //   216: aload_2
    //   217: ifnull +7 -> 224
    //   220: aload_2
    //   221: invokevirtual 240	java/io/FileOutputStream:close	()V
    //   224: aload_3
    //   225: areturn
    //   226: aload_3
    //   227: invokevirtual 133	java/io/File:exists	()Z
    //   230: ifeq +16 -> 246
    //   233: aload_3
    //   234: invokevirtual 232	java/io/File:delete	()Z
    //   237: pop
    //   238: ldc 205
    //   240: ldc 242
    //   242: invokestatic 151	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   245: pop
    //   246: aload_2
    //   247: ifnull -240 -> 7
    //   250: aload_2
    //   251: invokevirtual 240	java/io/FileOutputStream:close	()V
    //   254: aconst_null
    //   255: areturn
    //   256: astore_1
    //   257: aconst_null
    //   258: areturn
    //   259: astore_1
    //   260: aconst_null
    //   261: astore_1
    //   262: aload_1
    //   263: ifnull -256 -> 7
    //   266: aload_1
    //   267: invokevirtual 240	java/io/FileOutputStream:close	()V
    //   270: aconst_null
    //   271: areturn
    //   272: astore_1
    //   273: aconst_null
    //   274: areturn
    //   275: astore_1
    //   276: aconst_null
    //   277: astore_2
    //   278: aload_2
    //   279: ifnull +7 -> 286
    //   282: aload_2
    //   283: invokevirtual 240	java/io/FileOutputStream:close	()V
    //   286: aload_1
    //   287: athrow
    //   288: ldc 244
    //   290: ldc 246
    //   292: invokestatic 151	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   295: pop
    //   296: aconst_null
    //   297: areturn
    //   298: astore_1
    //   299: goto -75 -> 224
    //   302: astore_2
    //   303: goto -17 -> 286
    //   306: astore_1
    //   307: goto -29 -> 278
    //   310: astore_1
    //   311: aload_2
    //   312: astore_1
    //   313: goto -51 -> 262
    //   316: astore_2
    //   317: goto -226 -> 91
    //
    // Exception table:
    //   from	to	target	type
    //   250	254	256	java/lang/Throwable
    //   173	182	259	java/lang/Throwable
    //   266	270	272	java/lang/Throwable
    //   173	182	275	finally
    //   220	224	298	java/lang/Throwable
    //   282	286	302	java/lang/Throwable
    //   182	216	306	finally
    //   226	246	306	finally
    //   182	216	310	java/lang/Throwable
    //   226	246	310	java/lang/Throwable
    //   50	81	316	java/io/FileNotFoundException
    //   83	91	316	java/io/FileNotFoundException
  }

  public a a()
  {
    return a(a);
  }

  public a b()
  {
    return a("libcockroach.so");
  }

  public void c()
  {
    b("android");
  }

  public static class a
  {
    private boolean a = false;
    private boolean b = false;

    public void a(boolean paramBoolean)
    {
      this.a = paramBoolean;
    }

    public boolean a()
    {
      return this.a;
    }

    public void b(boolean paramBoolean)
    {
      this.b = paramBoolean;
    }

    public boolean b()
    {
      return this.b;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.proc.d
 * JD-Core Version:    0.6.2
 */