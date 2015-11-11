package u.aly;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class i extends a
{
  private static final String a = "utdid";
  private static final String b = "android.permission.WRITE_EXTERNAL_STORAGE";
  private static final Pattern c = Pattern.compile("UTDID\">([^<]+)");
  private Context d;

  public i(Context paramContext)
  {
    super("utdid");
    this.d = paramContext;
  }

  private String b(String paramString)
  {
    if (paramString == null);
    do
    {
      return null;
      paramString = c.matcher(paramString);
    }
    while (!paramString.find());
    return paramString.group(1);
  }

  // ERROR //
  private String g()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 58	u/aly/i:h	()Ljava/io/File;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnull +10 -> 16
    //   9: aload_1
    //   10: invokevirtual 63	java/io/File:exists	()Z
    //   13: ifne +5 -> 18
    //   16: aconst_null
    //   17: areturn
    //   18: new 65	java/io/FileInputStream
    //   21: dup
    //   22: aload_1
    //   23: invokespecial 68	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   26: astore_1
    //   27: aload_0
    //   28: aload_1
    //   29: invokestatic 73	u/aly/bv:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   32: invokespecial 75	u/aly/i:b	(Ljava/lang/String;)Ljava/lang/String;
    //   35: astore_2
    //   36: aload_1
    //   37: invokestatic 78	u/aly/bv:c	(Ljava/io/InputStream;)V
    //   40: aload_2
    //   41: areturn
    //   42: astore_1
    //   43: aload_1
    //   44: invokevirtual 81	java/lang/Exception:printStackTrace	()V
    //   47: aconst_null
    //   48: areturn
    //   49: astore_2
    //   50: aload_1
    //   51: invokestatic 78	u/aly/bv:c	(Ljava/io/InputStream;)V
    //   54: aload_2
    //   55: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   18	27	42	java/lang/Exception
    //   36	40	42	java/lang/Exception
    //   50	56	42	java/lang/Exception
    //   27	36	49	finally
  }

  private File h()
  {
    if (!bi.a(this.d, "android.permission.WRITE_EXTERNAL_STORAGE"));
    while (!Environment.getExternalStorageState().equals("mounted"))
      return null;
    File localFile = Environment.getExternalStorageDirectory();
    try
    {
      localFile = new File(localFile.getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
      return localFile;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public String f()
  {
    return g();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.i
 * JD-Core Version:    0.6.2
 */