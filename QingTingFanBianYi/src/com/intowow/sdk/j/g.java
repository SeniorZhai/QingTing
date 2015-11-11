package com.intowow.sdk.j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class g
{
  private boolean a = false;
  private int b = 10000;
  private long c = 5000L;

  public g(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }

  public g(boolean paramBoolean, int paramInt)
  {
    this.a = paramBoolean;
    a(paramInt);
  }

  private String a(String paramString)
  {
    if (paramString == null)
      return null;
    return paramString.replaceAll("%%CACHEBUSTER%%", System.currentTimeMillis());
  }

  private String a(HttpURLConnection paramHttpURLConnection, BufferedReader paramBufferedReader)
  {
    paramBufferedReader = new StringBuilder();
    paramHttpURLConnection = new BufferedReader(new InputStreamReader(paramHttpURLConnection.getInputStream(), "utf-8"));
    while (true)
    {
      String str = paramHttpURLConnection.readLine();
      if (str == null)
      {
        paramHttpURLConnection.close();
        return paramBufferedReader.toString();
      }
      paramBufferedReader.append(str);
    }
  }

  private HttpURLConnection a(String paramString1, String paramString2)
  {
    paramString1 = (HttpURLConnection)new URL(paramString1).openConnection();
    paramString1.setConnectTimeout(5000);
    paramString1.setReadTimeout(this.b);
    paramString1.setRequestMethod(paramString2);
    paramString1.setInstanceFollowRedirects(false);
    return paramString1;
  }

  private void a(a parama, int paramInt, String paramString)
  {
    if (parama != null)
      parama.a(paramInt, paramString);
  }

  private void a(a parama, int paramInt, String paramString, Exception paramException)
  {
    if (parama != null)
      parama.a(paramInt, paramString, paramException);
  }

  private void a(BufferedReader paramBufferedReader, HttpURLConnection paramHttpURLConnection)
  {
    if (paramBufferedReader != null);
    try
    {
      paramBufferedReader.close();
      label8: c(paramHttpURLConnection);
      return;
    }
    catch (IOException paramBufferedReader)
    {
      break label8;
    }
  }

  private void a(HttpURLConnection paramHttpURLConnection)
  {
    paramHttpURLConnection.setDoOutput(true);
    paramHttpURLConnection.setDoInput(true);
  }

  private void a(HttpURLConnection paramHttpURLConnection, OutputStream paramOutputStream, JSONObject paramJSONObject)
  {
    paramHttpURLConnection = paramHttpURLConnection.getOutputStream();
    paramHttpURLConnection.write(paramJSONObject.toString().getBytes("UTF-8"));
    paramHttpURLConnection.flush();
    paramHttpURLConnection.close();
  }

  // ERROR //
  private boolean a(String paramString, JSONObject paramJSONObject, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 19	com/intowow/sdk/j/g:a	Z
    //   4: ifeq +23 -> 27
    //   7: ldc 157
    //   9: iconst_2
    //   10: anewarray 4	java/lang/Object
    //   13: dup
    //   14: iconst_0
    //   15: aload_1
    //   16: aastore
    //   17: dup
    //   18: iconst_1
    //   19: iload_3
    //   20: invokestatic 162	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   23: aastore
    //   24: invokestatic 167	com/intowow/sdk/j/h:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   27: iload_3
    //   28: bipush 10
    //   30: if_icmplt +11 -> 41
    //   33: aload_0
    //   34: aconst_null
    //   35: aconst_null
    //   36: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   39: iconst_0
    //   40: ireturn
    //   41: aload_0
    //   42: aload_1
    //   43: ldc 171
    //   45: invokespecial 173	com/intowow/sdk/j/g:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/net/HttpURLConnection;
    //   48: astore_1
    //   49: aload_0
    //   50: aload_1
    //   51: invokespecial 175	com/intowow/sdk/j/g:a	(Ljava/net/HttpURLConnection;)V
    //   54: aload_0
    //   55: aload_1
    //   56: invokespecial 177	com/intowow/sdk/j/g:b	(Ljava/net/HttpURLConnection;)V
    //   59: aload_0
    //   60: aload_1
    //   61: aconst_null
    //   62: aload_2
    //   63: invokespecial 179	com/intowow/sdk/j/g:a	(Ljava/net/HttpURLConnection;Ljava/io/OutputStream;Lorg/json/JSONObject;)V
    //   66: aload_1
    //   67: invokevirtual 183	java/net/HttpURLConnection:getResponseCode	()I
    //   70: istore 4
    //   72: aload_0
    //   73: iload 4
    //   75: invokespecial 186	com/intowow/sdk/j/g:b	(I)Z
    //   78: ifeq +45 -> 123
    //   81: aload_0
    //   82: aload_1
    //   83: ldc 188
    //   85: invokevirtual 191	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   88: invokespecial 193	com/intowow/sdk/j/g:a	(Ljava/lang/String;)Ljava/lang/String;
    //   91: ifnull +32 -> 123
    //   94: aload_0
    //   95: aload_0
    //   96: aload_1
    //   97: ldc 188
    //   99: invokevirtual 191	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   102: invokespecial 193	com/intowow/sdk/j/g:a	(Ljava/lang/String;)Ljava/lang/String;
    //   105: aload_2
    //   106: iload_3
    //   107: iconst_1
    //   108: iadd
    //   109: invokespecial 195	com/intowow/sdk/j/g:a	(Ljava/lang/String;Lorg/json/JSONObject;I)Z
    //   112: istore 5
    //   114: aload_0
    //   115: aconst_null
    //   116: aload_1
    //   117: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   120: iload 5
    //   122: ireturn
    //   123: iload 4
    //   125: sipush 200
    //   128: if_icmpne +11 -> 139
    //   131: aload_0
    //   132: aconst_null
    //   133: aload_1
    //   134: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   137: iconst_1
    //   138: ireturn
    //   139: aload_0
    //   140: aconst_null
    //   141: aload_1
    //   142: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   145: iconst_0
    //   146: ireturn
    //   147: astore_1
    //   148: aconst_null
    //   149: astore_1
    //   150: aload_0
    //   151: aconst_null
    //   152: aload_1
    //   153: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   156: iconst_0
    //   157: ireturn
    //   158: astore_2
    //   159: aconst_null
    //   160: astore_1
    //   161: aload_0
    //   162: aconst_null
    //   163: aload_1
    //   164: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   167: aload_2
    //   168: athrow
    //   169: astore_2
    //   170: goto -9 -> 161
    //   173: astore_2
    //   174: goto -24 -> 150
    //
    // Exception table:
    //   from	to	target	type
    //   0	27	147	java/lang/Exception
    //   41	49	147	java/lang/Exception
    //   0	27	158	finally
    //   41	49	158	finally
    //   49	114	169	finally
    //   49	114	173	java/lang/Exception
  }

  private HttpURLConnection b(String paramString1, String paramString2)
  {
    paramString1 = a(paramString1, paramString2);
    paramString1.connect();
    return paramString1;
  }

  private void b(HttpURLConnection paramHttpURLConnection)
  {
    paramHttpURLConnection.setRequestProperty("Content-Type", "application/json");
    paramHttpURLConnection.setRequestProperty("Accept", "application/json");
  }

  private boolean b(int paramInt)
  {
    return (paramInt != 200) && ((paramInt == 302) || (paramInt == 301) || (paramInt == 303));
  }

  private void c(HttpURLConnection paramHttpURLConnection)
  {
    if (paramHttpURLConnection != null)
      paramHttpURLConnection.disconnect();
  }

  public void a(int paramInt)
  {
    this.b = paramInt;
  }

  // ERROR //
  public boolean a(String paramString, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 193	com/intowow/sdk/j/g:a	(Ljava/lang/String;)Ljava/lang/String;
    //   5: astore_1
    //   6: aload_0
    //   7: getfield 19	com/intowow/sdk/j/g:a	Z
    //   10: ifeq +45 -> 55
    //   13: new 35	java/lang/StringBuilder
    //   16: dup
    //   17: ldc 214
    //   19: invokespecial 50	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   22: aload_1
    //   23: ldc 216
    //   25: ldc 218
    //   27: invokevirtual 58	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   30: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: ldc 220
    //   35: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: iconst_1
    //   42: anewarray 4	java/lang/Object
    //   45: dup
    //   46: iconst_0
    //   47: iload_2
    //   48: invokestatic 162	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   51: aastore
    //   52: invokestatic 167	com/intowow/sdk/j/h:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   55: iload_2
    //   56: bipush 10
    //   58: if_icmplt +11 -> 69
    //   61: aload_0
    //   62: aconst_null
    //   63: aconst_null
    //   64: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   67: iconst_0
    //   68: ireturn
    //   69: aload_0
    //   70: aload_1
    //   71: ldc 222
    //   73: invokespecial 224	com/intowow/sdk/j/g:b	(Ljava/lang/String;Ljava/lang/String;)Ljava/net/HttpURLConnection;
    //   76: astore_1
    //   77: aload_1
    //   78: invokevirtual 183	java/net/HttpURLConnection:getResponseCode	()I
    //   81: istore 4
    //   83: aload_0
    //   84: getfield 19	com/intowow/sdk/j/g:a	Z
    //   87: ifeq +32 -> 119
    //   90: new 35	java/lang/StringBuilder
    //   93: dup
    //   94: ldc 226
    //   96: invokespecial 50	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   99: iload 4
    //   101: invokevirtual 229	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   104: ldc 231
    //   106: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: iconst_0
    //   113: anewarray 4	java/lang/Object
    //   116: invokestatic 167	com/intowow/sdk/j/h:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   119: aload_0
    //   120: iload 4
    //   122: invokespecial 186	com/intowow/sdk/j/g:b	(I)Z
    //   125: ifeq +44 -> 169
    //   128: aload_0
    //   129: aload_1
    //   130: ldc 188
    //   132: invokevirtual 191	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   135: invokespecial 193	com/intowow/sdk/j/g:a	(Ljava/lang/String;)Ljava/lang/String;
    //   138: ifnull +31 -> 169
    //   141: aload_0
    //   142: aload_0
    //   143: aload_1
    //   144: ldc 188
    //   146: invokevirtual 191	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   149: invokespecial 193	com/intowow/sdk/j/g:a	(Ljava/lang/String;)Ljava/lang/String;
    //   152: iload_2
    //   153: iconst_1
    //   154: iadd
    //   155: invokevirtual 233	com/intowow/sdk/j/g:a	(Ljava/lang/String;I)Z
    //   158: istore 5
    //   160: aload_0
    //   161: aconst_null
    //   162: aload_1
    //   163: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   166: iload 5
    //   168: ireturn
    //   169: iload 4
    //   171: sipush 200
    //   174: if_icmpne +11 -> 185
    //   177: aload_0
    //   178: aconst_null
    //   179: aload_1
    //   180: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   183: iconst_1
    //   184: ireturn
    //   185: aload_0
    //   186: aconst_null
    //   187: aload_1
    //   188: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   191: iconst_0
    //   192: ireturn
    //   193: astore_1
    //   194: aconst_null
    //   195: astore_1
    //   196: aload_0
    //   197: aconst_null
    //   198: aload_1
    //   199: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   202: iconst_0
    //   203: ireturn
    //   204: astore_3
    //   205: aconst_null
    //   206: astore_1
    //   207: aload_0
    //   208: aconst_null
    //   209: aload_1
    //   210: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   213: aload_3
    //   214: athrow
    //   215: astore_3
    //   216: goto -9 -> 207
    //   219: astore_3
    //   220: goto -24 -> 196
    //
    // Exception table:
    //   from	to	target	type
    //   69	77	193	java/lang/Exception
    //   0	55	204	finally
    //   69	77	204	finally
    //   77	119	215	finally
    //   119	160	215	finally
    //   77	119	219	java/lang/Exception
    //   119	160	219	java/lang/Exception
  }

  // ERROR //
  public boolean a(String paramString, JSONObject paramJSONObject, a parama)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 19	com/intowow/sdk/j/g:a	Z
    //   4: ifeq +16 -> 20
    //   7: ldc 236
    //   9: iconst_1
    //   10: anewarray 4	java/lang/Object
    //   13: dup
    //   14: iconst_0
    //   15: aload_1
    //   16: aastore
    //   17: invokestatic 167	com/intowow/sdk/j/h:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   20: aload_0
    //   21: aload_1
    //   22: ldc 171
    //   24: invokespecial 173	com/intowow/sdk/j/g:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/net/HttpURLConnection;
    //   27: astore 4
    //   29: aload 4
    //   31: astore_1
    //   32: aload_0
    //   33: aload 4
    //   35: invokespecial 175	com/intowow/sdk/j/g:a	(Ljava/net/HttpURLConnection;)V
    //   38: aload 4
    //   40: astore_1
    //   41: aload_0
    //   42: aload 4
    //   44: invokespecial 177	com/intowow/sdk/j/g:b	(Ljava/net/HttpURLConnection;)V
    //   47: aload 4
    //   49: astore_1
    //   50: aload_0
    //   51: aload 4
    //   53: aconst_null
    //   54: aload_2
    //   55: invokespecial 179	com/intowow/sdk/j/g:a	(Ljava/net/HttpURLConnection;Ljava/io/OutputStream;Lorg/json/JSONObject;)V
    //   58: aload 4
    //   60: astore_1
    //   61: aload 4
    //   63: invokevirtual 183	java/net/HttpURLConnection:getResponseCode	()I
    //   66: istore 5
    //   68: iload 5
    //   70: sipush 200
    //   73: if_icmpne +29 -> 102
    //   76: aload 4
    //   78: astore_1
    //   79: aload_0
    //   80: aload_3
    //   81: iload 5
    //   83: aload_0
    //   84: aload 4
    //   86: aconst_null
    //   87: invokespecial 238	com/intowow/sdk/j/g:a	(Ljava/net/HttpURLConnection;Ljava/io/BufferedReader;)Ljava/lang/String;
    //   90: invokespecial 240	com/intowow/sdk/j/g:a	(Lcom/intowow/sdk/j/g$a;ILjava/lang/String;)V
    //   93: aload_0
    //   94: aconst_null
    //   95: aload 4
    //   97: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   100: iconst_1
    //   101: ireturn
    //   102: aload 4
    //   104: astore_1
    //   105: aload_0
    //   106: aload_3
    //   107: iload 5
    //   109: aload 4
    //   111: invokevirtual 243	java/net/HttpURLConnection:getResponseMessage	()Ljava/lang/String;
    //   114: aconst_null
    //   115: invokespecial 245	com/intowow/sdk/j/g:a	(Lcom/intowow/sdk/j/g$a;ILjava/lang/String;Ljava/lang/Exception;)V
    //   118: aload_0
    //   119: aconst_null
    //   120: aload 4
    //   122: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   125: iconst_0
    //   126: ireturn
    //   127: astore_2
    //   128: iconst_0
    //   129: istore 5
    //   131: aconst_null
    //   132: astore 4
    //   134: aload 4
    //   136: astore_1
    //   137: aload_0
    //   138: aload_3
    //   139: iload 5
    //   141: aload_2
    //   142: invokevirtual 248	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   145: aload_2
    //   146: invokespecial 245	com/intowow/sdk/j/g:a	(Lcom/intowow/sdk/j/g$a;ILjava/lang/String;Ljava/lang/Exception;)V
    //   149: aload_0
    //   150: aconst_null
    //   151: aload 4
    //   153: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   156: goto -31 -> 125
    //   159: astore_2
    //   160: aconst_null
    //   161: astore_1
    //   162: aload_0
    //   163: aconst_null
    //   164: aload_1
    //   165: invokespecial 169	com/intowow/sdk/j/g:a	(Ljava/io/BufferedReader;Ljava/net/HttpURLConnection;)V
    //   168: aload_2
    //   169: athrow
    //   170: astore_2
    //   171: goto -9 -> 162
    //   174: astore_2
    //   175: iconst_0
    //   176: istore 5
    //   178: goto -44 -> 134
    //   181: astore_2
    //   182: goto -48 -> 134
    //
    // Exception table:
    //   from	to	target	type
    //   0	20	127	java/lang/Exception
    //   20	29	127	java/lang/Exception
    //   0	20	159	finally
    //   20	29	159	finally
    //   32	38	170	finally
    //   41	47	170	finally
    //   50	58	170	finally
    //   61	68	170	finally
    //   79	93	170	finally
    //   105	118	170	finally
    //   137	149	170	finally
    //   32	38	174	java/lang/Exception
    //   41	47	174	java/lang/Exception
    //   50	58	174	java/lang/Exception
    //   61	68	174	java/lang/Exception
    //   79	93	181	java/lang/Exception
    //   105	118	181	java/lang/Exception
  }

  public boolean a(String paramString, JSONObject paramJSONObject, a parama, int paramInt)
  {
    boolean bool1 = false;
    while (true)
    {
      boolean bool2;
      if (paramInt >= 0)
      {
        bool2 = a(paramString, paramJSONObject, 0);
        if (bool2)
          bool1 = bool2;
      }
      else
      {
        if (bool1)
          this.c = 5000L;
        if (parama != null)
        {
          if (!bool1)
            break label123;
          parama.a(200, "{}");
        }
        return bool1;
      }
      int i = paramInt - 1;
      try
      {
        Thread.sleep(this.c);
        label74: this.c += 5000L;
        bool1 = bool2;
        paramInt = i;
        if (this.c <= 180000L)
          continue;
        this.c = 180000L;
        bool1 = bool2;
        paramInt = i;
        continue;
        label123: parama.a(-1, "", null);
        return bool1;
      }
      catch (Exception localException)
      {
        break label74;
      }
    }
  }

  public static abstract interface a
  {
    public abstract void a(int paramInt, String paramString);

    public abstract void a(int paramInt, String paramString, Exception paramException);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.g
 * JD-Core Version:    0.6.2
 */