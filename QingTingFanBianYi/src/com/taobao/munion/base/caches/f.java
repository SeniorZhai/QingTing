package com.taobao.munion.base.caches;

import java.io.File;
import java.nio.ByteBuffer;

public class f
{
  // ERROR //
  public static boolean a(File paramFile, ByteBuffer paramByteBuffer)
    throws p
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore_3
    //   5: aconst_null
    //   6: astore 4
    //   8: aconst_null
    //   9: astore_2
    //   10: aload_0
    //   11: invokevirtual 23	java/io/File:exists	()Z
    //   14: ifne +16 -> 30
    //   17: aload_0
    //   18: invokevirtual 27	java/io/File:getParentFile	()Ljava/io/File;
    //   21: invokevirtual 30	java/io/File:mkdirs	()Z
    //   24: pop
    //   25: aload_0
    //   26: invokevirtual 33	java/io/File:createNewFile	()Z
    //   29: pop
    //   30: new 35	java/io/FileOutputStream
    //   33: dup
    //   34: aload_0
    //   35: invokespecial 38	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   38: astore 5
    //   40: aload 6
    //   42: astore_2
    //   43: aload 5
    //   45: astore_3
    //   46: aload 5
    //   48: invokevirtual 42	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   51: astore 6
    //   53: aload 6
    //   55: astore_2
    //   56: aload 5
    //   58: astore_3
    //   59: aload 6
    //   61: astore 4
    //   63: aload_1
    //   64: iconst_0
    //   65: invokevirtual 48	java/nio/ByteBuffer:position	(I)Ljava/nio/Buffer;
    //   68: pop
    //   69: aload 6
    //   71: astore_2
    //   72: aload 5
    //   74: astore_3
    //   75: aload 6
    //   77: astore 4
    //   79: aload 6
    //   81: aload_1
    //   82: invokevirtual 54	java/nio/channels/FileChannel:write	(Ljava/nio/ByteBuffer;)I
    //   85: pop
    //   86: aload 6
    //   88: astore_2
    //   89: aload 5
    //   91: astore_3
    //   92: aload 6
    //   94: astore 4
    //   96: aload 6
    //   98: iconst_1
    //   99: invokevirtual 58	java/nio/channels/FileChannel:force	(Z)V
    //   102: aload 5
    //   104: ifnull +8 -> 112
    //   107: aload 5
    //   109: invokevirtual 61	java/io/FileOutputStream:close	()V
    //   112: aload 6
    //   114: ifnull +8 -> 122
    //   117: aload 6
    //   119: invokevirtual 62	java/nio/channels/FileChannel:close	()V
    //   122: iconst_1
    //   123: ireturn
    //   124: astore_0
    //   125: aload_0
    //   126: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   129: goto -17 -> 112
    //   132: astore_0
    //   133: aload_0
    //   134: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   137: iconst_1
    //   138: ireturn
    //   139: astore 6
    //   141: aconst_null
    //   142: astore 4
    //   144: aload_2
    //   145: astore_1
    //   146: aload_1
    //   147: astore_2
    //   148: aload 4
    //   150: astore_3
    //   151: aload 6
    //   153: invokevirtual 69	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   156: astore 5
    //   158: aload 5
    //   160: ifnull +52 -> 212
    //   163: aload_1
    //   164: astore_2
    //   165: aload 4
    //   167: astore_3
    //   168: aload 5
    //   170: ldc 71
    //   172: invokevirtual 77	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   175: ifeq +37 -> 212
    //   178: aload_1
    //   179: astore_2
    //   180: aload 4
    //   182: astore_3
    //   183: new 13	com/taobao/munion/base/caches/p
    //   186: dup
    //   187: ldc 79
    //   189: invokespecial 82	com/taobao/munion/base/caches/p:<init>	(Ljava/lang/String;)V
    //   192: athrow
    //   193: astore_0
    //   194: aload_3
    //   195: ifnull +7 -> 202
    //   198: aload_3
    //   199: invokevirtual 61	java/io/FileOutputStream:close	()V
    //   202: aload_2
    //   203: ifnull +7 -> 210
    //   206: aload_2
    //   207: invokevirtual 62	java/nio/channels/FileChannel:close	()V
    //   210: aload_0
    //   211: athrow
    //   212: aload_0
    //   213: ifnull +13 -> 226
    //   216: aload_1
    //   217: astore_2
    //   218: aload 4
    //   220: astore_3
    //   221: aload_0
    //   222: invokevirtual 85	java/io/File:delete	()Z
    //   225: pop
    //   226: aload_1
    //   227: astore_2
    //   228: aload 4
    //   230: astore_3
    //   231: aload 6
    //   233: invokevirtual 86	java/lang/Exception:printStackTrace	()V
    //   236: aload 4
    //   238: ifnull +8 -> 246
    //   241: aload 4
    //   243: invokevirtual 61	java/io/FileOutputStream:close	()V
    //   246: aload_1
    //   247: ifnull +7 -> 254
    //   250: aload_1
    //   251: invokevirtual 62	java/nio/channels/FileChannel:close	()V
    //   254: iconst_0
    //   255: ireturn
    //   256: astore_0
    //   257: aload_0
    //   258: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   261: goto -15 -> 246
    //   264: astore_0
    //   265: aload_0
    //   266: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   269: goto -15 -> 254
    //   272: astore_1
    //   273: aload_1
    //   274: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   277: goto -75 -> 202
    //   280: astore_1
    //   281: aload_1
    //   282: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   285: goto -75 -> 210
    //   288: astore_0
    //   289: aconst_null
    //   290: astore_1
    //   291: aload_3
    //   292: astore_2
    //   293: aload_1
    //   294: astore_3
    //   295: goto -101 -> 194
    //   298: astore 6
    //   300: aload 4
    //   302: astore_1
    //   303: aload 5
    //   305: astore 4
    //   307: goto -161 -> 146
    //
    // Exception table:
    //   from	to	target	type
    //   107	112	124	java/io/IOException
    //   117	122	132	java/io/IOException
    //   10	30	139	java/lang/Exception
    //   30	40	139	java/lang/Exception
    //   46	53	193	finally
    //   63	69	193	finally
    //   79	86	193	finally
    //   96	102	193	finally
    //   151	158	193	finally
    //   168	178	193	finally
    //   183	193	193	finally
    //   221	226	193	finally
    //   231	236	193	finally
    //   241	246	256	java/io/IOException
    //   250	254	264	java/io/IOException
    //   198	202	272	java/io/IOException
    //   206	210	280	java/io/IOException
    //   10	30	288	finally
    //   30	40	288	finally
    //   46	53	298	java/lang/Exception
    //   63	69	298	java/lang/Exception
    //   79	86	298	java/lang/Exception
    //   96	102	298	java/lang/Exception
  }

  public static boolean a(String paramString)
  {
    if (paramString == null)
      return false;
    return new File(paramString).exists();
  }

  public static boolean a(String paramString, ByteBuffer paramByteBuffer)
    throws p
  {
    if (paramString == null)
      return false;
    return a(new File(paramString), paramByteBuffer);
  }

  // ERROR //
  public static byte[] a(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: new 95	java/io/FileInputStream
    //   6: dup
    //   7: aload_0
    //   8: invokespecial 96	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   11: astore_1
    //   12: aload_1
    //   13: invokevirtual 97	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   16: astore_0
    //   17: aload_0
    //   18: astore_3
    //   19: aload_1
    //   20: astore_2
    //   21: aload_0
    //   22: invokevirtual 101	java/nio/channels/FileChannel:size	()J
    //   25: l2i
    //   26: invokestatic 105	java/nio/ByteBuffer:allocate	(I)Ljava/nio/ByteBuffer;
    //   29: astore 4
    //   31: aload_0
    //   32: astore_3
    //   33: aload_1
    //   34: astore_2
    //   35: aload_0
    //   36: aload 4
    //   38: invokevirtual 108	java/nio/channels/FileChannel:read	(Ljava/nio/ByteBuffer;)I
    //   41: pop
    //   42: aload_0
    //   43: astore_3
    //   44: aload_1
    //   45: astore_2
    //   46: aload 4
    //   48: invokevirtual 112	java/nio/ByteBuffer:array	()[B
    //   51: astore 4
    //   53: aload 4
    //   55: astore_2
    //   56: aload_1
    //   57: ifnull +7 -> 64
    //   60: aload_1
    //   61: invokevirtual 113	java/io/FileInputStream:close	()V
    //   64: aload_2
    //   65: astore_1
    //   66: aload_0
    //   67: ifnull +9 -> 76
    //   70: aload_0
    //   71: invokevirtual 62	java/nio/channels/FileChannel:close	()V
    //   74: aload_2
    //   75: astore_1
    //   76: aload_1
    //   77: areturn
    //   78: astore_1
    //   79: aload_1
    //   80: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   83: goto -19 -> 64
    //   86: astore_0
    //   87: aload_0
    //   88: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   91: aload_2
    //   92: areturn
    //   93: astore 4
    //   95: aconst_null
    //   96: astore_0
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_0
    //   100: astore_3
    //   101: aload_1
    //   102: astore_2
    //   103: aload 4
    //   105: invokevirtual 86	java/lang/Exception:printStackTrace	()V
    //   108: aload_1
    //   109: ifnull +7 -> 116
    //   112: aload_1
    //   113: invokevirtual 113	java/io/FileInputStream:close	()V
    //   116: aload 5
    //   118: astore_1
    //   119: aload_0
    //   120: ifnull -44 -> 76
    //   123: aload_0
    //   124: invokevirtual 62	java/nio/channels/FileChannel:close	()V
    //   127: aconst_null
    //   128: areturn
    //   129: astore_0
    //   130: aload_0
    //   131: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   134: aconst_null
    //   135: areturn
    //   136: astore_1
    //   137: aload_1
    //   138: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   141: goto -25 -> 116
    //   144: astore_0
    //   145: aconst_null
    //   146: astore_3
    //   147: aconst_null
    //   148: astore_1
    //   149: aload_1
    //   150: ifnull +7 -> 157
    //   153: aload_1
    //   154: invokevirtual 113	java/io/FileInputStream:close	()V
    //   157: aload_3
    //   158: ifnull +7 -> 165
    //   161: aload_3
    //   162: invokevirtual 62	java/nio/channels/FileChannel:close	()V
    //   165: aload_0
    //   166: athrow
    //   167: astore_1
    //   168: aload_1
    //   169: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   172: goto -15 -> 157
    //   175: astore_1
    //   176: aload_1
    //   177: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   180: goto -15 -> 165
    //   183: astore_0
    //   184: aconst_null
    //   185: astore_3
    //   186: goto -37 -> 149
    //   189: astore_0
    //   190: aload_2
    //   191: astore_1
    //   192: goto -43 -> 149
    //   195: astore 4
    //   197: aconst_null
    //   198: astore_0
    //   199: goto -100 -> 99
    //   202: astore 4
    //   204: goto -105 -> 99
    //
    // Exception table:
    //   from	to	target	type
    //   60	64	78	java/io/IOException
    //   70	74	86	java/io/IOException
    //   3	12	93	java/lang/Exception
    //   123	127	129	java/io/IOException
    //   112	116	136	java/io/IOException
    //   3	12	144	finally
    //   153	157	167	java/io/IOException
    //   161	165	175	java/io/IOException
    //   12	17	183	finally
    //   21	31	189	finally
    //   35	42	189	finally
    //   46	53	189	finally
    //   103	108	189	finally
    //   12	17	195	java/lang/Exception
    //   21	31	202	java/lang/Exception
    //   35	42	202	java/lang/Exception
    //   46	53	202	java/lang/Exception
  }

  public static boolean b(File paramFile)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramFile != null)
    {
      bool1 = bool2;
      if (paramFile.exists())
        if (paramFile.isDirectory())
        {
          File[] arrayOfFile = paramFile.listFiles();
          int j = arrayOfFile.length;
          int i = 0;
          if (i < j)
          {
            File localFile = arrayOfFile[i];
            if (localFile.isDirectory())
              b(localFile);
            while (true)
            {
              i += 1;
              break;
              try
              {
                localFile.delete();
              }
              catch (Exception localException)
              {
                localException.printStackTrace();
              }
            }
          }
        }
    }
    try
    {
      bool1 = paramFile.delete();
      return bool1;
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
    }
    return false;
  }

  public static byte[] b(String paramString)
  {
    if (paramString == null)
      return null;
    return a(new File(paramString));
  }

  public static boolean c(String paramString)
  {
    if (paramString == null)
      return false;
    return b(new File(paramString));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.f
 * JD-Core Version:    0.6.2
 */