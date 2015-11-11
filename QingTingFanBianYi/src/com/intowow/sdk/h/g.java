package com.intowow.sdk.h;

import com.intowow.sdk.a.e;
import com.intowow.sdk.j.g.a;
import com.intowow.sdk.j.k;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g
  implements b
{
  private File a = null;
  private File b = null;
  private String c = null;
  private List<String> d = null;
  private com.intowow.sdk.j.g e = null;

  public g(File paramFile, String paramString)
  {
    this.a = paramFile;
    this.c = paramString;
    this.e = new com.intowow.sdk.j.g(e.a, 60000);
    c();
  }

  private File a(File paramFile)
  {
    if ((paramFile == null) || (!paramFile.exists()));
    do
    {
      return null;
      paramFile = paramFile.listFiles(new FilenameFilter()
      {
        public boolean accept(File paramAnonymousFile, String paramAnonymousString)
        {
          return paramAnonymousString.indexOf("processdone") != -1;
        }
      });
    }
    while ((paramFile == null) || (paramFile.length <= 0));
    return paramFile[0];
  }

  private void a(long paramLong, List<JSONObject> paramList)
  {
    String str = this.b.getAbsolutePath() + "/" + paramLong + "." + "undone" + "log";
    b(str, paramList);
    paramList = new File(str.replaceAll("undone", "processdone"));
    new File(str).renameTo(paramList);
  }

  private boolean a(String paramString, List<JSONObject> paramList)
  {
    try
    {
      Object localObject = (JSONObject)paramList.get(0);
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.put(c.a(c.j), ((JSONObject)localObject).getString(c.a(c.j)));
      localJSONObject1.put(c.a(c.e), ((JSONObject)localObject).getString(c.a(c.e)));
      localObject = new JSONArray();
      paramList = paramList.iterator();
      while (true)
      {
        if (!paramList.hasNext())
        {
          paramList = new JSONObject();
          paramList.put(f.a(f.C), localObject);
          localJSONObject1.put(c.a(c.h), paramList);
          return a(paramString, localJSONObject1);
        }
        JSONObject localJSONObject2 = (JSONObject)paramList.next();
        localJSONObject2.remove(c.a(c.j));
        localJSONObject2.remove(c.a(c.e));
        ((JSONArray)localObject).put(localJSONObject2);
      }
    }
    catch (Exception paramString)
    {
    }
    return false;
  }

  private boolean a(final String paramString, JSONObject paramJSONObject)
  {
    if (this.c == null)
      return false;
    return this.e.a(this.c, paramJSONObject, new g.a()
    {
      public void a(int paramAnonymousInt, String paramAnonymousString)
      {
        g.a(g.this, paramString);
      }

      public void a(int paramAnonymousInt, String paramAnonymousString, Exception paramAnonymousException)
      {
      }
    }
    , 2);
  }

  private void b(String paramString)
  {
    synchronized (this.d)
    {
      if (this.d.contains(paramString))
        return;
      this.d.add(paramString);
      return;
    }
  }

  // ERROR //
  private void b(String paramString, List<JSONObject> paramList)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 212	java/io/BufferedWriter
    //   5: dup
    //   6: new 214	java/io/FileWriter
    //   9: dup
    //   10: aload_1
    //   11: iconst_0
    //   12: invokespecial 217	java/io/FileWriter:<init>	(Ljava/lang/String;Z)V
    //   15: invokespecial 220	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   18: astore_1
    //   19: aload_2
    //   20: invokeinterface 164 1 0
    //   25: astore_2
    //   26: aload_2
    //   27: invokeinterface 169 1 0
    //   32: istore 4
    //   34: iload 4
    //   36: ifne +16 -> 52
    //   39: aload_1
    //   40: ifnull +11 -> 51
    //   43: aload_1
    //   44: invokevirtual 223	java/io/BufferedWriter:flush	()V
    //   47: aload_1
    //   48: invokevirtual 226	java/io/BufferedWriter:close	()V
    //   51: return
    //   52: aload_1
    //   53: new 76	java/lang/StringBuilder
    //   56: dup
    //   57: aload_2
    //   58: invokeinterface 188 1 0
    //   63: checkcast 137	org/json/JSONObject
    //   66: invokevirtual 227	org/json/JSONObject:toString	()Ljava/lang/String;
    //   69: invokestatic 86	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   72: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   75: ldc 229
    //   77: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: invokevirtual 232	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   86: goto -60 -> 26
    //   89: astore_2
    //   90: aload_1
    //   91: ifnull -40 -> 51
    //   94: aload_1
    //   95: invokevirtual 223	java/io/BufferedWriter:flush	()V
    //   98: aload_1
    //   99: invokevirtual 226	java/io/BufferedWriter:close	()V
    //   102: return
    //   103: astore_1
    //   104: return
    //   105: astore_2
    //   106: aconst_null
    //   107: astore_1
    //   108: aload_1
    //   109: ifnull +11 -> 120
    //   112: aload_1
    //   113: invokevirtual 223	java/io/BufferedWriter:flush	()V
    //   116: aload_1
    //   117: invokevirtual 226	java/io/BufferedWriter:close	()V
    //   120: aload_2
    //   121: athrow
    //   122: astore_1
    //   123: return
    //   124: astore_1
    //   125: goto -5 -> 120
    //   128: astore_2
    //   129: goto -21 -> 108
    //   132: astore_1
    //   133: aload_3
    //   134: astore_1
    //   135: goto -45 -> 90
    //
    // Exception table:
    //   from	to	target	type
    //   19	26	89	java/lang/Exception
    //   26	34	89	java/lang/Exception
    //   52	86	89	java/lang/Exception
    //   94	102	103	java/io/IOException
    //   2	19	105	finally
    //   43	51	122	java/io/IOException
    //   112	120	124	java/io/IOException
    //   19	26	128	finally
    //   26	34	128	finally
    //   52	86	128	finally
    //   2	19	132	java/lang/Exception
  }

  // ERROR //
  private String c(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 76	java/lang/StringBuilder
    //   6: dup
    //   7: invokestatic 239	java/lang/System:currentTimeMillis	()J
    //   10: invokestatic 242	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   13: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   16: ldc 244
    //   18: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   24: astore_2
    //   25: new 212	java/io/BufferedWriter
    //   28: dup
    //   29: new 214	java/io/FileWriter
    //   32: dup
    //   33: aload_0
    //   34: aload_2
    //   35: invokespecial 247	com/intowow/sdk/h/g:g	(Ljava/lang/String;)Ljava/lang/String;
    //   38: iconst_1
    //   39: invokespecial 217	java/io/FileWriter:<init>	(Ljava/lang/String;Z)V
    //   42: invokespecial 220	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   45: astore_3
    //   46: aload_3
    //   47: new 76	java/lang/StringBuilder
    //   50: dup
    //   51: aload_1
    //   52: invokestatic 86	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   55: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   58: ldc 229
    //   60: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: invokevirtual 232	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   69: aload_2
    //   70: astore 4
    //   72: aload_3
    //   73: ifnull +14 -> 87
    //   76: aload_3
    //   77: invokevirtual 223	java/io/BufferedWriter:flush	()V
    //   80: aload_3
    //   81: invokevirtual 226	java/io/BufferedWriter:close	()V
    //   84: aload_2
    //   85: astore 4
    //   87: aload 4
    //   89: areturn
    //   90: astore_1
    //   91: aconst_null
    //   92: astore_1
    //   93: aconst_null
    //   94: astore_2
    //   95: aload_2
    //   96: astore 4
    //   98: aload_1
    //   99: ifnull -12 -> 87
    //   102: aload_1
    //   103: invokevirtual 223	java/io/BufferedWriter:flush	()V
    //   106: aload_1
    //   107: invokevirtual 226	java/io/BufferedWriter:close	()V
    //   110: aload_2
    //   111: areturn
    //   112: astore_1
    //   113: aload_2
    //   114: areturn
    //   115: astore_1
    //   116: aload 4
    //   118: astore_2
    //   119: aload_2
    //   120: ifnull +11 -> 131
    //   123: aload_2
    //   124: invokevirtual 223	java/io/BufferedWriter:flush	()V
    //   127: aload_2
    //   128: invokevirtual 226	java/io/BufferedWriter:close	()V
    //   131: aload_1
    //   132: athrow
    //   133: astore_1
    //   134: aload_2
    //   135: areturn
    //   136: astore_2
    //   137: goto -6 -> 131
    //   140: astore_1
    //   141: aload_3
    //   142: astore_2
    //   143: goto -24 -> 119
    //   146: astore_1
    //   147: aconst_null
    //   148: astore_1
    //   149: goto -54 -> 95
    //   152: astore_1
    //   153: aload_3
    //   154: astore_1
    //   155: goto -60 -> 95
    //
    // Exception table:
    //   from	to	target	type
    //   3	25	90	java/lang/Exception
    //   102	110	112	java/io/IOException
    //   3	25	115	finally
    //   25	46	115	finally
    //   76	84	133	java/io/IOException
    //   123	131	136	java/io/IOException
    //   46	69	140	finally
    //   25	46	146	java/lang/Exception
    //   46	69	152	java/lang/Exception
  }

  private void c()
  {
    if (!this.a.exists())
      this.a.mkdirs();
    while (true)
    {
      return;
      this.b = new File(this.a.getAbsolutePath() + "/tmp");
      if (!this.b.exists())
        this.b.mkdirs();
      this.d = new LinkedList();
      File[] arrayOfFile = this.a.listFiles(new FilenameFilter()
      {
        public boolean accept(File paramAnonymousFile, String paramAnonymousString)
        {
          return paramAnonymousString.indexOf("log") != -1;
        }
      });
      if ((arrayOfFile != null) && (arrayOfFile.length != 0))
      {
        Arrays.sort(arrayOfFile, new Comparator()
        {
          public int a(File paramAnonymousFile1, File paramAnonymousFile2)
          {
            return paramAnonymousFile1.getName().compareTo(paramAnonymousFile2.getName());
          }
        });
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          b(arrayOfFile[i].getName());
          i += 1;
        }
      }
    }
  }

  private File d()
  {
    File localFile = a(this.b);
    if (localFile == null)
      return null;
    final String str1 = localFile.getName();
    this.a.listFiles(new FilenameFilter()
    {
      public boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        if (paramAnonymousString.equals("tmp"));
        while (paramAnonymousString.compareTo(str1) >= 0)
          return false;
        g.a(g.this, paramAnonymousString);
        return false;
      }
    });
    String str2 = this.a.getAbsolutePath() + "/" + str1;
    localFile.renameTo(new File(str2));
    b(str1);
    this.b.listFiles(new FileFilter()
    {
      public boolean accept(File paramAnonymousFile)
      {
        paramAnonymousFile.delete();
        return false;
      }
    });
    return new File(str2);
  }

  private void d(String paramString)
  {
    new File(g(paramString)).delete();
    synchronized (this.d)
    {
      this.d.remove(paramString);
      return;
    }
  }

  private List<String> e()
  {
    LinkedList localLinkedList = new LinkedList();
    synchronized (this.d)
    {
      Collections.sort(this.d, new Comparator()
      {
        public int a(String paramAnonymousString1, String paramAnonymousString2)
        {
          return paramAnonymousString1.compareTo(paramAnonymousString2);
        }
      });
      Iterator localIterator = this.d.iterator();
      if (!localIterator.hasNext())
        return localLinkedList;
      localLinkedList.add(new String((String)localIterator.next()));
    }
  }

  // ERROR //
  private List<JSONObject> e(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_2
    //   5: new 254	java/util/LinkedList
    //   8: dup
    //   9: invokespecial 255	java/util/LinkedList:<init>	()V
    //   12: astore 5
    //   14: aload_0
    //   15: aload_1
    //   16: invokespecial 247	com/intowow/sdk/h/g:g	(Ljava/lang/String;)Ljava/lang/String;
    //   19: astore_1
    //   20: new 62	java/io/File
    //   23: dup
    //   24: aload_1
    //   25: invokespecial 117	java/io/File:<init>	(Ljava/lang/String;)V
    //   28: invokevirtual 66	java/io/File:exists	()Z
    //   31: istore 6
    //   33: iload 6
    //   35: ifne +30 -> 65
    //   38: iconst_0
    //   39: ifeq +11 -> 50
    //   42: new 293	java/lang/NullPointerException
    //   45: dup
    //   46: invokespecial 294	java/lang/NullPointerException:<init>	()V
    //   49: athrow
    //   50: iconst_0
    //   51: ifeq +11 -> 62
    //   54: new 293	java/lang/NullPointerException
    //   57: dup
    //   58: invokespecial 294	java/lang/NullPointerException:<init>	()V
    //   61: athrow
    //   62: aload 5
    //   64: areturn
    //   65: new 296	java/io/InputStreamReader
    //   68: dup
    //   69: new 298	java/io/FileInputStream
    //   72: dup
    //   73: aload_1
    //   74: invokespecial 299	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   77: ldc_w 301
    //   80: invokespecial 304	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   83: astore_1
    //   84: new 306	java/io/BufferedReader
    //   87: dup
    //   88: aload_1
    //   89: invokespecial 309	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   92: astore_3
    //   93: aload_3
    //   94: invokevirtual 312	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   97: astore_2
    //   98: aload_2
    //   99: ifnonnull +26 -> 125
    //   102: aload_1
    //   103: ifnull +7 -> 110
    //   106: aload_1
    //   107: invokevirtual 313	java/io/InputStreamReader:close	()V
    //   110: aload_3
    //   111: ifnull -49 -> 62
    //   114: aload_3
    //   115: invokevirtual 314	java/io/BufferedReader:close	()V
    //   118: aload 5
    //   120: areturn
    //   121: astore_1
    //   122: aload 5
    //   124: areturn
    //   125: aload_0
    //   126: aload_2
    //   127: invokespecial 318	com/intowow/sdk/h/g:f	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   130: astore_2
    //   131: aload_2
    //   132: ifnull -39 -> 93
    //   135: aload 5
    //   137: aload_2
    //   138: invokeinterface 208 2 0
    //   143: pop
    //   144: goto -51 -> 93
    //   147: astore_2
    //   148: aload_1
    //   149: astore_2
    //   150: aload_3
    //   151: astore_1
    //   152: aload_2
    //   153: ifnull +7 -> 160
    //   156: aload_2
    //   157: invokevirtual 313	java/io/InputStreamReader:close	()V
    //   160: aload_1
    //   161: ifnull -99 -> 62
    //   164: aload_1
    //   165: invokevirtual 314	java/io/BufferedReader:close	()V
    //   168: aload 5
    //   170: areturn
    //   171: astore_1
    //   172: aload 5
    //   174: areturn
    //   175: astore_2
    //   176: aconst_null
    //   177: astore_1
    //   178: aload 4
    //   180: astore_3
    //   181: aload_1
    //   182: ifnull +7 -> 189
    //   185: aload_1
    //   186: invokevirtual 313	java/io/InputStreamReader:close	()V
    //   189: aload_3
    //   190: ifnull +7 -> 197
    //   193: aload_3
    //   194: invokevirtual 314	java/io/BufferedReader:close	()V
    //   197: aload_2
    //   198: athrow
    //   199: astore_1
    //   200: goto -3 -> 197
    //   203: astore_2
    //   204: aload 4
    //   206: astore_3
    //   207: goto -26 -> 181
    //   210: astore_2
    //   211: goto -30 -> 181
    //   214: astore_1
    //   215: aconst_null
    //   216: astore_1
    //   217: goto -65 -> 152
    //   220: astore_2
    //   221: aconst_null
    //   222: astore_3
    //   223: aload_1
    //   224: astore_2
    //   225: aload_3
    //   226: astore_1
    //   227: goto -75 -> 152
    //   230: astore_1
    //   231: aload 5
    //   233: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   106	110	121	java/lang/Exception
    //   114	118	121	java/lang/Exception
    //   93	98	147	java/lang/Exception
    //   125	131	147	java/lang/Exception
    //   135	144	147	java/lang/Exception
    //   156	160	171	java/lang/Exception
    //   164	168	171	java/lang/Exception
    //   14	33	175	finally
    //   65	84	175	finally
    //   185	189	199	java/lang/Exception
    //   193	197	199	java/lang/Exception
    //   84	93	203	finally
    //   93	98	210	finally
    //   125	131	210	finally
    //   135	144	210	finally
    //   14	33	214	java/lang/Exception
    //   65	84	214	java/lang/Exception
    //   84	93	220	java/lang/Exception
    //   42	50	230	java/lang/Exception
    //   54	62	230	java/lang/Exception
  }

  private JSONObject f(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      return paramString;
    }
    catch (JSONException paramString)
    {
    }
    return null;
  }

  private String g(String paramString)
  {
    return this.a.getAbsolutePath() + "/" + paramString;
  }

  private long h(String paramString)
  {
    return Long.parseLong(paramString.substring(0, paramString.indexOf('.')));
  }

  public void a()
  {
    d();
    try
    {
      Object localObject1 = e();
      if (((List)localObject1).size() == 0)
        return;
      LinkedList localLinkedList = new LinkedList();
      Iterator localIterator = ((List)localObject1).iterator();
      localObject1 = null;
      int i;
      if (!localIterator.hasNext())
      {
        i = 0;
        label55: if (i != 0)
          break label154;
      }
      label154: for (long l = System.currentTimeMillis(); ; l = h((String)localObject1))
      {
        a(l + 1L, localLinkedList);
        localObject1 = d();
        if ((localObject1 == null) || (!((File)localObject1).exists()))
          return;
        a(((File)localObject1).getName(), localLinkedList);
        localLinkedList.clear();
        return;
        String str = (String)localIterator.next();
        if (localLinkedList.size() > 100)
        {
          i = 1;
          break label55;
        }
        localLinkedList.addAll(e(str));
        localObject1 = str;
        break;
      }
    }
    finally
    {
    }
  }

  public void a(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("You must pass a non-null tracking data");
    if (b() + paramString.getBytes().length > 5242880L);
    while (f(paramString) == null)
      return;
    try
    {
      paramString = c(paramString);
      if (paramString != null)
        b(paramString);
      return;
    }
    finally
    {
    }
    throw paramString;
  }

  public long b()
  {
    return k.c(this.a.getAbsolutePath());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.h.g
 * JD-Core Version:    0.6.2
 */