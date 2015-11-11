package com.taobao.newxp.view.audio;

import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class b
{
  private String a = "";
  private int b = 4096;

  public b()
  {
    if (Environment.getExternalStorageState().equals("mounted"))
      this.a = (Environment.getExternalStorageDirectory() + "/");
  }

  public File a(String paramString)
    throws IOException
  {
    paramString = new File(this.a + paramString);
    paramString.createNewFile();
    return paramString;
  }

  // ERROR //
  public File a(String paramString1, String paramString2, java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_0
    //   4: aload_1
    //   5: invokevirtual 70	com/taobao/newxp/view/audio/b:b	(Ljava/lang/String;)Ljava/io/File;
    //   8: pop
    //   9: aload_0
    //   10: new 34	java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial 35	java/lang/StringBuilder:<init>	()V
    //   17: aload_1
    //   18: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: aload_2
    //   22: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: invokevirtual 51	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   28: invokevirtual 72	com/taobao/newxp/view/audio/b:a	(Ljava/lang/String;)Ljava/io/File;
    //   31: astore 4
    //   33: new 74	java/io/FileOutputStream
    //   36: dup
    //   37: aload 4
    //   39: invokespecial 77	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   42: astore_1
    //   43: aload_1
    //   44: astore_2
    //   45: aload_0
    //   46: getfield 18	com/taobao/newxp/view/audio/b:b	I
    //   49: newarray byte
    //   51: astore 5
    //   53: aload_1
    //   54: astore_2
    //   55: aload_3
    //   56: aload 5
    //   58: invokevirtual 83	java/io/InputStream:read	([B)I
    //   61: istore 6
    //   63: iload 6
    //   65: ifle +31 -> 96
    //   68: aload_1
    //   69: astore_2
    //   70: aload_1
    //   71: aload 5
    //   73: iconst_0
    //   74: iload 6
    //   76: invokevirtual 89	java/io/OutputStream:write	([BII)V
    //   79: goto -26 -> 53
    //   82: astore_3
    //   83: aload_1
    //   84: astore_2
    //   85: aload_3
    //   86: invokevirtual 92	java/lang/Exception:printStackTrace	()V
    //   89: aload_1
    //   90: invokevirtual 95	java/io/OutputStream:close	()V
    //   93: aload 4
    //   95: areturn
    //   96: aload_1
    //   97: astore_2
    //   98: aload_1
    //   99: invokevirtual 98	java/io/OutputStream:flush	()V
    //   102: aload_1
    //   103: invokevirtual 95	java/io/OutputStream:close	()V
    //   106: aload 4
    //   108: areturn
    //   109: astore_1
    //   110: aload_1
    //   111: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   114: aload 4
    //   116: areturn
    //   117: astore_1
    //   118: aload_1
    //   119: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   122: aload 4
    //   124: areturn
    //   125: astore_1
    //   126: aload 5
    //   128: astore_2
    //   129: aload_2
    //   130: invokevirtual 95	java/io/OutputStream:close	()V
    //   133: aload_1
    //   134: athrow
    //   135: astore_2
    //   136: aload_2
    //   137: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   140: goto -7 -> 133
    //   143: astore_1
    //   144: goto -15 -> 129
    //   147: astore_3
    //   148: aconst_null
    //   149: astore_1
    //   150: aconst_null
    //   151: astore 4
    //   153: goto -70 -> 83
    //   156: astore_3
    //   157: aconst_null
    //   158: astore_1
    //   159: goto -76 -> 83
    //
    // Exception table:
    //   from	to	target	type
    //   45	53	82	java/lang/Exception
    //   55	63	82	java/lang/Exception
    //   70	79	82	java/lang/Exception
    //   98	102	82	java/lang/Exception
    //   102	106	109	java/io/IOException
    //   89	93	117	java/io/IOException
    //   3	33	125	finally
    //   33	43	125	finally
    //   129	133	135	java/io/IOException
    //   45	53	143	finally
    //   55	63	143	finally
    //   70	79	143	finally
    //   85	89	143	finally
    //   98	102	143	finally
    //   3	33	147	java/lang/Exception
    //   33	43	156	java/lang/Exception
  }

  public String a()
  {
    return this.a;
  }

  public File b(String paramString)
  {
    paramString = new File(this.a + paramString);
    paramString.mkdir();
    return paramString;
  }

  public boolean c(String paramString)
  {
    File localFile = new File(this.a + paramString);
    System.out.println(this.a + paramString);
    return localFile.exists();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.audio.b
 * JD-Core Version:    0.6.2
 */