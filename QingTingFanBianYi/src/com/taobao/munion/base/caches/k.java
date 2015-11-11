package com.taobao.munion.base.caches;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;

public class k
{
  private static final String a = "FileManager";

  public static File a(Context paramContext, String paramString)
  {
    String str = paramContext.getFilesDir().getAbsolutePath();
    paramContext = str;
    if (!TextUtils.isEmpty(paramString))
      paramContext = str + File.separator + paramString;
    paramContext = new File(paramContext);
    if (!paramContext.exists())
      paramContext.mkdirs();
    return paramContext;
  }

  public static String a(Context paramContext, String paramString1, String paramString2)
  {
    String str = "";
    if (paramContext.getFilesDir() != null)
    {
      paramContext = new StringBuilder().append(paramContext.getFilesDir().getAbsolutePath());
      if (!TextUtils.isEmpty(paramString1))
        paramContext.append(File.separator).append(paramString1);
      str = File.separator + paramString2;
    }
    return str;
  }

  public static String a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      StringBuilder localStringBuilder = new StringBuilder().append(Environment.getExternalStorageDirectory().toString()).append(File.separator);
      if (TextUtils.isEmpty(paramString1))
        localStringBuilder.append(paramContext.getPackageName());
      while (true)
      {
        return File.separator + paramString2;
        localStringBuilder.append(paramString1);
      }
    }
    return a(paramContext, paramString1, paramString2);
  }

  // ERROR //
  public static void a(java.io.InputStream paramInputStream, File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +7 -> 8
    //   4: aload_1
    //   5: ifnonnull +4 -> 9
    //   8: return
    //   9: aconst_null
    //   10: astore_2
    //   11: aload_1
    //   12: invokevirtual 78	java/io/File:createNewFile	()Z
    //   15: pop
    //   16: new 80	java/io/FileOutputStream
    //   19: dup
    //   20: aload_1
    //   21: invokespecial 83	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   24: astore_1
    //   25: sipush 2048
    //   28: newarray byte
    //   30: astore_2
    //   31: aload_0
    //   32: aload_2
    //   33: iconst_0
    //   34: sipush 2048
    //   37: invokevirtual 89	java/io/InputStream:read	([BII)I
    //   40: istore_3
    //   41: iload_3
    //   42: iconst_m1
    //   43: if_icmpeq +29 -> 72
    //   46: aload_1
    //   47: aload_2
    //   48: iconst_0
    //   49: iload_3
    //   50: invokevirtual 93	java/io/FileOutputStream:write	([BII)V
    //   53: goto -22 -> 31
    //   56: astore_0
    //   57: aload_1
    //   58: ifnull -50 -> 8
    //   61: aload_1
    //   62: invokevirtual 96	java/io/FileOutputStream:close	()V
    //   65: return
    //   66: astore_0
    //   67: aload_0
    //   68: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   71: return
    //   72: aload_1
    //   73: ifnull -65 -> 8
    //   76: aload_1
    //   77: invokevirtual 96	java/io/FileOutputStream:close	()V
    //   80: return
    //   81: astore_0
    //   82: aload_0
    //   83: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   86: return
    //   87: astore_0
    //   88: aconst_null
    //   89: astore_1
    //   90: aload_1
    //   91: ifnull -83 -> 8
    //   94: aload_1
    //   95: invokevirtual 96	java/io/FileOutputStream:close	()V
    //   98: return
    //   99: astore_0
    //   100: aload_0
    //   101: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   104: return
    //   105: astore_0
    //   106: aload_2
    //   107: astore_1
    //   108: aload_1
    //   109: ifnull +7 -> 116
    //   112: aload_1
    //   113: invokevirtual 96	java/io/FileOutputStream:close	()V
    //   116: aload_0
    //   117: athrow
    //   118: astore_1
    //   119: aload_1
    //   120: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   123: goto -7 -> 116
    //   126: astore_0
    //   127: goto -19 -> 108
    //   130: astore_0
    //   131: goto -41 -> 90
    //   134: astore_0
    //   135: aconst_null
    //   136: astore_1
    //   137: goto -80 -> 57
    //
    // Exception table:
    //   from	to	target	type
    //   25	31	56	java/io/FileNotFoundException
    //   31	41	56	java/io/FileNotFoundException
    //   46	53	56	java/io/FileNotFoundException
    //   61	65	66	java/io/IOException
    //   76	80	81	java/io/IOException
    //   11	25	87	java/io/IOException
    //   94	98	99	java/io/IOException
    //   11	25	105	finally
    //   112	116	118	java/io/IOException
    //   25	31	126	finally
    //   31	41	126	finally
    //   46	53	126	finally
    //   25	31	130	java/io/IOException
    //   31	41	130	java/io/IOException
    //   46	53	130	java/io/IOException
    //   11	25	134	java/io/FileNotFoundException
  }

  // ERROR //
  public static void a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore_2
    //   5: aconst_null
    //   6: astore 4
    //   8: aload_0
    //   9: ifnull +7 -> 16
    //   12: aload_1
    //   13: ifnonnull +4 -> 17
    //   16: return
    //   17: aload_1
    //   18: astore_3
    //   19: aload_1
    //   20: ldc 102
    //   22: invokevirtual 108	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   25: ifne +23 -> 48
    //   28: new 34	java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial 35	java/lang/StringBuilder:<init>	()V
    //   35: aload_1
    //   36: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: ldc 102
    //   41: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: invokevirtual 45	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   47: astore_3
    //   48: new 22	java/io/File
    //   51: dup
    //   52: aload_0
    //   53: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
    //   56: astore_0
    //   57: aload_0
    //   58: invokevirtual 52	java/io/File:exists	()Z
    //   61: ifeq -45 -> 16
    //   64: new 110	java/util/zip/ZipFile
    //   67: dup
    //   68: aload_0
    //   69: invokespecial 111	java/util/zip/ZipFile:<init>	(Ljava/io/File;)V
    //   72: astore 6
    //   74: aload 6
    //   76: invokevirtual 115	java/util/zip/ZipFile:entries	()Ljava/util/Enumeration;
    //   79: astore 7
    //   81: aconst_null
    //   82: astore_1
    //   83: aload 4
    //   85: astore_0
    //   86: aload 7
    //   88: invokeinterface 120 1 0
    //   93: ifeq +185 -> 278
    //   96: aload 7
    //   98: invokeinterface 124 1 0
    //   103: checkcast 126	java/util/zip/ZipEntry
    //   106: astore 4
    //   108: aload 4
    //   110: ifnonnull +26 -> 136
    //   113: aload_0
    //   114: ifnull +7 -> 121
    //   117: aload_0
    //   118: invokevirtual 127	java/io/InputStream:close	()V
    //   121: aload_1
    //   122: ifnull -106 -> 16
    //   125: aload_1
    //   126: invokevirtual 96	java/io/FileOutputStream:close	()V
    //   129: return
    //   130: astore_0
    //   131: aload_0
    //   132: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   135: return
    //   136: new 22	java/io/File
    //   139: dup
    //   140: new 34	java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial 35	java/lang/StringBuilder:<init>	()V
    //   147: aload_3
    //   148: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload 4
    //   153: invokevirtual 130	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   156: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: invokevirtual 45	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   162: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
    //   165: astore_2
    //   166: aload 4
    //   168: invokevirtual 133	java/util/zip/ZipEntry:isDirectory	()Z
    //   171: ifeq +11 -> 182
    //   174: aload_2
    //   175: invokevirtual 55	java/io/File:mkdirs	()Z
    //   178: pop
    //   179: goto +201 -> 380
    //   182: aload_2
    //   183: invokevirtual 136	java/io/File:getParentFile	()Ljava/io/File;
    //   186: invokevirtual 52	java/io/File:exists	()Z
    //   189: ifne +11 -> 200
    //   192: aload_2
    //   193: invokevirtual 136	java/io/File:getParentFile	()Ljava/io/File;
    //   196: invokevirtual 55	java/io/File:mkdirs	()Z
    //   199: pop
    //   200: new 80	java/io/FileOutputStream
    //   203: dup
    //   204: aload_2
    //   205: invokespecial 83	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   208: astore_2
    //   209: aload 6
    //   211: aload 4
    //   213: invokevirtual 140	java/util/zip/ZipFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   216: astore_1
    //   217: aload_1
    //   218: astore_0
    //   219: sipush 1024
    //   222: newarray byte
    //   224: astore_1
    //   225: aload_0
    //   226: aload_1
    //   227: iconst_0
    //   228: sipush 1024
    //   231: invokevirtual 89	java/io/InputStream:read	([BII)I
    //   234: istore 8
    //   236: iload 8
    //   238: ifle +140 -> 378
    //   241: aload_2
    //   242: aload_1
    //   243: iconst_0
    //   244: iload 8
    //   246: invokevirtual 93	java/io/FileOutputStream:write	([BII)V
    //   249: goto -24 -> 225
    //   252: astore_1
    //   253: aload_2
    //   254: astore_1
    //   255: aload_0
    //   256: ifnull +7 -> 263
    //   259: aload_0
    //   260: invokevirtual 127	java/io/InputStream:close	()V
    //   263: aload_1
    //   264: ifnull -248 -> 16
    //   267: aload_1
    //   268: invokevirtual 96	java/io/FileOutputStream:close	()V
    //   271: return
    //   272: astore_0
    //   273: aload_0
    //   274: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   277: return
    //   278: aload 6
    //   280: invokevirtual 141	java/util/zip/ZipFile:close	()V
    //   283: aload_0
    //   284: ifnull +7 -> 291
    //   287: aload_0
    //   288: invokevirtual 127	java/io/InputStream:close	()V
    //   291: aload_1
    //   292: ifnull -276 -> 16
    //   295: aload_1
    //   296: invokevirtual 96	java/io/FileOutputStream:close	()V
    //   299: return
    //   300: astore_0
    //   301: aload_0
    //   302: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   305: return
    //   306: astore_3
    //   307: aconst_null
    //   308: astore_1
    //   309: aload_2
    //   310: astore_0
    //   311: aload_3
    //   312: astore_2
    //   313: aload_0
    //   314: ifnull +7 -> 321
    //   317: aload_0
    //   318: invokevirtual 127	java/io/InputStream:close	()V
    //   321: aload_1
    //   322: ifnull +7 -> 329
    //   325: aload_1
    //   326: invokevirtual 96	java/io/FileOutputStream:close	()V
    //   329: aload_2
    //   330: athrow
    //   331: astore_0
    //   332: aload_0
    //   333: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   336: goto -7 -> 329
    //   339: astore_3
    //   340: aload_2
    //   341: astore_1
    //   342: aload_3
    //   343: astore_2
    //   344: goto -31 -> 313
    //   347: astore_2
    //   348: goto -35 -> 313
    //   351: astore_3
    //   352: aload_2
    //   353: astore_1
    //   354: aload_3
    //   355: astore_2
    //   356: goto -43 -> 313
    //   359: astore_0
    //   360: aconst_null
    //   361: astore_1
    //   362: aload 5
    //   364: astore_0
    //   365: goto -110 -> 255
    //   368: astore_2
    //   369: goto -114 -> 255
    //   372: astore_1
    //   373: aload_2
    //   374: astore_1
    //   375: goto -120 -> 255
    //   378: aload_2
    //   379: astore_1
    //   380: goto -294 -> 86
    //
    // Exception table:
    //   from	to	target	type
    //   117	121	130	java/io/IOException
    //   125	129	130	java/io/IOException
    //   219	225	252	java/io/IOException
    //   225	236	252	java/io/IOException
    //   241	249	252	java/io/IOException
    //   259	263	272	java/io/IOException
    //   267	271	272	java/io/IOException
    //   287	291	300	java/io/IOException
    //   295	299	300	java/io/IOException
    //   64	81	306	finally
    //   317	321	331	java/io/IOException
    //   325	329	331	java/io/IOException
    //   219	225	339	finally
    //   225	236	339	finally
    //   241	249	339	finally
    //   86	108	347	finally
    //   136	179	347	finally
    //   182	200	347	finally
    //   200	209	347	finally
    //   278	283	347	finally
    //   209	217	351	finally
    //   64	81	359	java/io/IOException
    //   86	108	368	java/io/IOException
    //   136	179	368	java/io/IOException
    //   182	200	368	java/io/IOException
    //   200	209	368	java/io/IOException
    //   278	283	368	java/io/IOException
    //   209	217	372	java/io/IOException
  }

  // ERROR //
  public static boolean a(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: iconst_0
    //   6: istore 6
    //   8: aload_0
    //   9: invokevirtual 52	java/io/File:exists	()Z
    //   12: istore 7
    //   14: iload 7
    //   16: ifne +45 -> 61
    //   19: iconst_0
    //   20: ifeq +11 -> 31
    //   23: new 146	java/lang/NullPointerException
    //   26: dup
    //   27: invokespecial 147	java/lang/NullPointerException:<init>	()V
    //   30: athrow
    //   31: iconst_0
    //   32: ifeq +11 -> 43
    //   35: new 146	java/lang/NullPointerException
    //   38: dup
    //   39: invokespecial 147	java/lang/NullPointerException:<init>	()V
    //   42: athrow
    //   43: iload 6
    //   45: ireturn
    //   46: astore_0
    //   47: aload_0
    //   48: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   51: goto -20 -> 31
    //   54: astore_0
    //   55: aload_0
    //   56: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   59: iconst_0
    //   60: ireturn
    //   61: aload_1
    //   62: invokevirtual 52	java/io/File:exists	()Z
    //   65: ifne +8 -> 73
    //   68: aload_1
    //   69: invokevirtual 78	java/io/File:createNewFile	()Z
    //   72: pop
    //   73: new 149	java/io/FileInputStream
    //   76: dup
    //   77: aload_0
    //   78: invokespecial 150	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   81: astore_2
    //   82: new 80	java/io/FileOutputStream
    //   85: dup
    //   86: aload_1
    //   87: invokespecial 83	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   90: astore_0
    //   91: sipush 2048
    //   94: newarray byte
    //   96: astore_1
    //   97: aload_2
    //   98: aload_1
    //   99: invokevirtual 153	java/io/FileInputStream:read	([B)I
    //   102: istore 5
    //   104: iload 5
    //   106: iconst_m1
    //   107: if_icmpeq +50 -> 157
    //   110: aload_0
    //   111: aload_1
    //   112: iconst_0
    //   113: iload 5
    //   115: invokevirtual 93	java/io/FileOutputStream:write	([BII)V
    //   118: goto -21 -> 97
    //   121: astore_3
    //   122: aload_0
    //   123: astore_1
    //   124: aload_2
    //   125: astore_0
    //   126: aload_3
    //   127: astore_2
    //   128: aload_2
    //   129: invokevirtual 154	java/lang/Exception:printStackTrace	()V
    //   132: aload_0
    //   133: ifnull +7 -> 140
    //   136: aload_0
    //   137: invokevirtual 155	java/io/FileInputStream:close	()V
    //   140: aload_1
    //   141: ifnull -98 -> 43
    //   144: aload_1
    //   145: invokevirtual 96	java/io/FileOutputStream:close	()V
    //   148: iconst_0
    //   149: ireturn
    //   150: astore_0
    //   151: aload_0
    //   152: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   155: iconst_0
    //   156: ireturn
    //   157: aload_0
    //   158: invokevirtual 158	java/io/FileOutputStream:flush	()V
    //   161: iconst_1
    //   162: istore 6
    //   164: aload_2
    //   165: ifnull +7 -> 172
    //   168: aload_2
    //   169: invokevirtual 155	java/io/FileInputStream:close	()V
    //   172: aload_0
    //   173: ifnull -130 -> 43
    //   176: aload_0
    //   177: invokevirtual 96	java/io/FileOutputStream:close	()V
    //   180: iconst_1
    //   181: ireturn
    //   182: astore_0
    //   183: aload_0
    //   184: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   187: iconst_1
    //   188: ireturn
    //   189: astore_1
    //   190: aload_1
    //   191: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   194: goto -22 -> 172
    //   197: astore_0
    //   198: aload_0
    //   199: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   202: goto -62 -> 140
    //   205: astore_0
    //   206: aconst_null
    //   207: astore_2
    //   208: aload 4
    //   210: astore_1
    //   211: aload_2
    //   212: ifnull +7 -> 219
    //   215: aload_2
    //   216: invokevirtual 155	java/io/FileInputStream:close	()V
    //   219: aload_1
    //   220: ifnull +7 -> 227
    //   223: aload_1
    //   224: invokevirtual 96	java/io/FileOutputStream:close	()V
    //   227: aload_0
    //   228: athrow
    //   229: astore_2
    //   230: aload_2
    //   231: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   234: goto -15 -> 219
    //   237: astore_1
    //   238: aload_1
    //   239: invokevirtual 99	java/io/IOException:printStackTrace	()V
    //   242: goto -15 -> 227
    //   245: astore_0
    //   246: aload 4
    //   248: astore_1
    //   249: goto -38 -> 211
    //   252: astore_3
    //   253: aload_0
    //   254: astore_1
    //   255: aload_3
    //   256: astore_0
    //   257: goto -46 -> 211
    //   260: astore_3
    //   261: aload_0
    //   262: astore_2
    //   263: aload_3
    //   264: astore_0
    //   265: goto -54 -> 211
    //   268: astore_2
    //   269: aconst_null
    //   270: astore_0
    //   271: aload_3
    //   272: astore_1
    //   273: goto -145 -> 128
    //   276: astore_1
    //   277: aload_2
    //   278: astore_0
    //   279: aload_1
    //   280: astore_2
    //   281: aload_3
    //   282: astore_1
    //   283: goto -155 -> 128
    //
    // Exception table:
    //   from	to	target	type
    //   23	31	46	java/io/IOException
    //   35	43	54	java/io/IOException
    //   91	97	121	java/lang/Exception
    //   97	104	121	java/lang/Exception
    //   110	118	121	java/lang/Exception
    //   157	161	121	java/lang/Exception
    //   144	148	150	java/io/IOException
    //   176	180	182	java/io/IOException
    //   168	172	189	java/io/IOException
    //   136	140	197	java/io/IOException
    //   8	14	205	finally
    //   61	73	205	finally
    //   73	82	205	finally
    //   215	219	229	java/io/IOException
    //   223	227	237	java/io/IOException
    //   82	91	245	finally
    //   91	97	252	finally
    //   97	104	252	finally
    //   110	118	252	finally
    //   157	161	252	finally
    //   128	132	260	finally
    //   8	14	268	java/lang/Exception
    //   61	73	268	java/lang/Exception
    //   73	82	268	java/lang/Exception
    //   82	91	276	java/lang/Exception
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.k
 * JD-Core Version:    0.6.2
 */