package com.mediav.ads.sdk.adcore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpCacher
{
  private static final int MAX_COUNT = 2147483647;
  private static final int MAX_SIZE = 50000000;
  public static final int TIME_DAY = 86400;
  public static final int TIME_HOUR = 3600;
  private static Map<String, HttpCacher> mInstanceMap = new HashMap();
  private ACacheManager mCache;

  private HttpCacher(File paramFile, long paramLong, int paramInt)
    throws Exception
  {
    if ((!paramFile.exists()) && (!paramFile.mkdirs()))
      throw new RuntimeException("can't make dirs in " + paramFile.getAbsolutePath());
    this.mCache = new ACacheManager(paramFile, paramLong, paramInt, null);
  }

  public static HttpCacher get(Context paramContext)
    throws Exception
  {
    return get(paramContext, "ACache");
  }

  public static HttpCacher get(Context paramContext, long paramLong, int paramInt)
    throws Exception
  {
    return get(new File(paramContext.getCacheDir(), "ACache"), paramLong, paramInt);
  }

  public static HttpCacher get(Context paramContext, String paramString)
    throws Exception
  {
    return get(new File(paramContext.getCacheDir(), paramString), 50000000L, 2147483647);
  }

  public static HttpCacher get(File paramFile)
    throws Exception
  {
    return get(paramFile, 50000000L, 2147483647);
  }

  public static HttpCacher get(File paramFile, long paramLong, int paramInt)
    throws Exception
  {
    HttpCacher localHttpCacher2 = (HttpCacher)mInstanceMap.get(paramFile.getAbsoluteFile() + myPid());
    HttpCacher localHttpCacher1 = localHttpCacher2;
    if (localHttpCacher2 == null)
    {
      localHttpCacher1 = new HttpCacher(paramFile, paramLong, paramInt);
      mInstanceMap.put(paramFile.getAbsolutePath() + myPid(), localHttpCacher1);
    }
    return localHttpCacher1;
  }

  private static String myPid()
  {
    return "_" + Process.myPid();
  }

  public void clear()
  {
    this.mCache.clear();
  }

  public File file(String paramString)
  {
    paramString = this.mCache.newFile(paramString);
    if (paramString.exists())
      return paramString;
    return null;
  }

  public InputStream get(String paramString)
    throws FileNotFoundException
  {
    paramString = this.mCache.get(paramString);
    if (!paramString.exists())
      return null;
    return new FileInputStream(paramString);
  }

  // ERROR //
  public byte[] getAsBinary(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 5
    //   5: aload_3
    //   6: astore_2
    //   7: aload_0
    //   8: getfield 80	com/mediav/ads/sdk/adcore/HttpCacher:mCache	Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;
    //   11: aload_1
    //   12: invokestatic 153	com/mediav/ads/sdk/adcore/HttpCacher$ACacheManager:access$400	(Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;Ljava/lang/String;)Ljava/io/File;
    //   15: astore 4
    //   17: aload_3
    //   18: astore_2
    //   19: aload 4
    //   21: invokevirtual 51	java/io/File:exists	()Z
    //   24: istore 6
    //   26: iload 6
    //   28: ifne +35 -> 63
    //   31: iconst_0
    //   32: ifeq +11 -> 43
    //   35: new 164	java/lang/NullPointerException
    //   38: dup
    //   39: invokespecial 165	java/lang/NullPointerException:<init>	()V
    //   42: athrow
    //   43: iconst_0
    //   44: ifeq +9 -> 53
    //   47: aload_0
    //   48: aload_1
    //   49: invokevirtual 169	com/mediav/ads/sdk/adcore/HttpCacher:remove	(Ljava/lang/String;)Z
    //   52: pop
    //   53: aconst_null
    //   54: areturn
    //   55: astore_2
    //   56: aload_2
    //   57: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   60: goto -17 -> 43
    //   63: aload_3
    //   64: astore_2
    //   65: new 174	java/io/RandomAccessFile
    //   68: dup
    //   69: aload 4
    //   71: ldc 176
    //   73: invokespecial 177	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   76: astore_3
    //   77: aload_3
    //   78: invokevirtual 181	java/io/RandomAccessFile:length	()J
    //   81: l2i
    //   82: newarray byte
    //   84: astore_2
    //   85: aload_3
    //   86: aload_2
    //   87: invokevirtual 185	java/io/RandomAccessFile:read	([B)I
    //   90: pop
    //   91: aload_2
    //   92: invokestatic 189	com/mediav/ads/sdk/adcore/HttpCacher$Utils:access$800	([B)Z
    //   95: ifne +36 -> 131
    //   98: aload_2
    //   99: invokestatic 193	com/mediav/ads/sdk/adcore/HttpCacher$Utils:access$900	([B)[B
    //   102: astore_2
    //   103: aload_3
    //   104: ifnull +7 -> 111
    //   107: aload_3
    //   108: invokevirtual 196	java/io/RandomAccessFile:close	()V
    //   111: iconst_0
    //   112: ifeq +9 -> 121
    //   115: aload_0
    //   116: aload_1
    //   117: invokevirtual 169	com/mediav/ads/sdk/adcore/HttpCacher:remove	(Ljava/lang/String;)Z
    //   120: pop
    //   121: aload_2
    //   122: areturn
    //   123: astore_3
    //   124: aload_3
    //   125: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   128: goto -17 -> 111
    //   131: aload_3
    //   132: ifnull +7 -> 139
    //   135: aload_3
    //   136: invokevirtual 196	java/io/RandomAccessFile:close	()V
    //   139: iconst_1
    //   140: ifeq +9 -> 149
    //   143: aload_0
    //   144: aload_1
    //   145: invokevirtual 169	com/mediav/ads/sdk/adcore/HttpCacher:remove	(Ljava/lang/String;)Z
    //   148: pop
    //   149: aconst_null
    //   150: areturn
    //   151: astore_2
    //   152: aload_2
    //   153: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   156: goto -17 -> 139
    //   159: astore 4
    //   161: aload 5
    //   163: astore_3
    //   164: aload_3
    //   165: astore_2
    //   166: aload 4
    //   168: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   171: aload_3
    //   172: ifnull +7 -> 179
    //   175: aload_3
    //   176: invokevirtual 196	java/io/RandomAccessFile:close	()V
    //   179: iconst_0
    //   180: ifeq -127 -> 53
    //   183: aload_0
    //   184: aload_1
    //   185: invokevirtual 169	com/mediav/ads/sdk/adcore/HttpCacher:remove	(Ljava/lang/String;)Z
    //   188: pop
    //   189: aconst_null
    //   190: areturn
    //   191: astore_2
    //   192: aload_2
    //   193: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   196: goto -17 -> 179
    //   199: astore_3
    //   200: aload_2
    //   201: ifnull +7 -> 208
    //   204: aload_2
    //   205: invokevirtual 196	java/io/RandomAccessFile:close	()V
    //   208: iconst_0
    //   209: ifeq +9 -> 218
    //   212: aload_0
    //   213: aload_1
    //   214: invokevirtual 169	com/mediav/ads/sdk/adcore/HttpCacher:remove	(Ljava/lang/String;)Z
    //   217: pop
    //   218: aload_3
    //   219: athrow
    //   220: astore_2
    //   221: aload_2
    //   222: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   225: goto -17 -> 208
    //   228: astore 4
    //   230: aload_3
    //   231: astore_2
    //   232: aload 4
    //   234: astore_3
    //   235: goto -35 -> 200
    //   238: astore 4
    //   240: goto -76 -> 164
    //
    // Exception table:
    //   from	to	target	type
    //   35	43	55	java/io/IOException
    //   107	111	123	java/io/IOException
    //   135	139	151	java/io/IOException
    //   7	17	159	java/lang/Exception
    //   19	26	159	java/lang/Exception
    //   65	77	159	java/lang/Exception
    //   175	179	191	java/io/IOException
    //   7	17	199	finally
    //   19	26	199	finally
    //   65	77	199	finally
    //   166	171	199	finally
    //   204	208	220	java/io/IOException
    //   77	103	228	finally
    //   77	103	238	java/lang/Exception
  }

  public Bitmap getAsBitmap(String paramString)
  {
    if (getAsBinary(paramString) == null)
      return null;
    return Utils.Bytes2Bimap(getAsBinary(paramString));
  }

  public Drawable getAsDrawable(String paramString)
  {
    if (getAsBinary(paramString) == null)
      return null;
    return Utils.bitmap2Drawable(Utils.access$1100(getAsBinary(paramString)));
  }

  public JSONArray getAsJSONArray(String paramString)
  {
    paramString = getAsString(paramString);
    try
    {
      paramString = new JSONArray(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public JSONObject getAsJSONObject(String paramString)
  {
    paramString = getAsString(paramString);
    try
    {
      paramString = new JSONObject(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  // ERROR //
  public Object getAsObject(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aload_0
    //   4: aload_1
    //   5: invokevirtual 201	com/mediav/ads/sdk/adcore/HttpCacher:getAsBinary	(Ljava/lang/String;)[B
    //   8: astore 8
    //   10: aload 6
    //   12: astore_1
    //   13: aload 8
    //   15: ifnull +60 -> 75
    //   18: aconst_null
    //   19: astore_2
    //   20: aconst_null
    //   21: astore 7
    //   23: aconst_null
    //   24: astore_3
    //   25: aconst_null
    //   26: astore 5
    //   28: aconst_null
    //   29: astore 4
    //   31: new 229	java/io/ByteArrayInputStream
    //   34: dup
    //   35: aload 8
    //   37: invokespecial 232	java/io/ByteArrayInputStream:<init>	([B)V
    //   40: astore_1
    //   41: new 234	java/io/ObjectInputStream
    //   44: dup
    //   45: aload_1
    //   46: invokespecial 237	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   49: astore_2
    //   50: aload_2
    //   51: invokevirtual 241	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   54: astore_3
    //   55: aload_1
    //   56: ifnull +7 -> 63
    //   59: aload_1
    //   60: invokevirtual 242	java/io/ByteArrayInputStream:close	()V
    //   63: aload_3
    //   64: astore_1
    //   65: aload_2
    //   66: ifnull +9 -> 75
    //   69: aload_2
    //   70: invokevirtual 243	java/io/ObjectInputStream:close	()V
    //   73: aload_3
    //   74: astore_1
    //   75: aload_1
    //   76: areturn
    //   77: astore_1
    //   78: aload_1
    //   79: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   82: goto -19 -> 63
    //   85: astore_1
    //   86: aload_1
    //   87: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   90: aload_3
    //   91: areturn
    //   92: astore 5
    //   94: aload 7
    //   96: astore_1
    //   97: aload_1
    //   98: astore_2
    //   99: aload 4
    //   101: astore_3
    //   102: aload 5
    //   104: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   107: aload_1
    //   108: ifnull +7 -> 115
    //   111: aload_1
    //   112: invokevirtual 242	java/io/ByteArrayInputStream:close	()V
    //   115: aload 6
    //   117: astore_1
    //   118: aload 4
    //   120: ifnull -45 -> 75
    //   123: aload 4
    //   125: invokevirtual 243	java/io/ObjectInputStream:close	()V
    //   128: aconst_null
    //   129: areturn
    //   130: astore_1
    //   131: aload_1
    //   132: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   135: aconst_null
    //   136: areturn
    //   137: astore_1
    //   138: aload_1
    //   139: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   142: goto -27 -> 115
    //   145: astore_1
    //   146: aload_2
    //   147: ifnull +7 -> 154
    //   150: aload_2
    //   151: invokevirtual 242	java/io/ByteArrayInputStream:close	()V
    //   154: aload_3
    //   155: ifnull +7 -> 162
    //   158: aload_3
    //   159: invokevirtual 243	java/io/ObjectInputStream:close	()V
    //   162: aload_1
    //   163: athrow
    //   164: astore_2
    //   165: aload_2
    //   166: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   169: goto -15 -> 154
    //   172: astore_2
    //   173: aload_2
    //   174: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   177: goto -15 -> 162
    //   180: astore 4
    //   182: aload_1
    //   183: astore_2
    //   184: aload 5
    //   186: astore_3
    //   187: aload 4
    //   189: astore_1
    //   190: goto -44 -> 146
    //   193: astore 4
    //   195: aload_2
    //   196: astore_3
    //   197: aload_1
    //   198: astore_2
    //   199: aload 4
    //   201: astore_1
    //   202: goto -56 -> 146
    //   205: astore 5
    //   207: goto -110 -> 97
    //   210: astore 5
    //   212: aload_2
    //   213: astore 4
    //   215: goto -118 -> 97
    //
    // Exception table:
    //   from	to	target	type
    //   59	63	77	java/io/IOException
    //   69	73	85	java/io/IOException
    //   31	41	92	java/lang/Exception
    //   123	128	130	java/io/IOException
    //   111	115	137	java/io/IOException
    //   31	41	145	finally
    //   102	107	145	finally
    //   150	154	164	java/io/IOException
    //   158	162	172	java/io/IOException
    //   41	50	180	finally
    //   50	55	193	finally
    //   41	50	205	java/lang/Exception
    //   50	55	210	java/lang/Exception
  }

  // ERROR //
  public String getAsString(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_0
    //   4: getfield 80	com/mediav/ads/sdk/adcore/HttpCacher:mCache	Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;
    //   7: aload_1
    //   8: invokestatic 153	com/mediav/ads/sdk/adcore/HttpCacher$ACacheManager:access$400	(Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;Ljava/lang/String;)Ljava/io/File;
    //   11: astore_3
    //   12: aload_3
    //   13: invokevirtual 51	java/io/File:exists	()Z
    //   16: ifne +8 -> 24
    //   19: aload 5
    //   21: astore_3
    //   22: aload_3
    //   23: areturn
    //   24: aconst_null
    //   25: astore_2
    //   26: aconst_null
    //   27: astore 4
    //   29: new 245	java/io/BufferedReader
    //   32: dup
    //   33: new 247	java/io/FileReader
    //   36: dup
    //   37: aload_3
    //   38: invokespecial 248	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   41: invokespecial 251	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   44: astore_3
    //   45: ldc 253
    //   47: astore_2
    //   48: aload_3
    //   49: invokevirtual 256	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   52: astore 4
    //   54: aload 4
    //   56: ifnull +26 -> 82
    //   59: new 58	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 59	java/lang/StringBuilder:<init>	()V
    //   66: aload_2
    //   67: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload 4
    //   72: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   78: astore_2
    //   79: goto -31 -> 48
    //   82: aload_2
    //   83: invokestatic 259	com/mediav/ads/sdk/adcore/HttpCacher$Utils:access$500	(Ljava/lang/String;)Z
    //   86: ifne +38 -> 124
    //   89: aload_2
    //   90: invokestatic 262	com/mediav/ads/sdk/adcore/HttpCacher$Utils:access$600	(Ljava/lang/String;)Ljava/lang/String;
    //   93: astore_2
    //   94: aload_3
    //   95: ifnull +7 -> 102
    //   98: aload_3
    //   99: invokevirtual 263	java/io/BufferedReader:close	()V
    //   102: aload_2
    //   103: astore_3
    //   104: iconst_0
    //   105: ifeq -83 -> 22
    //   108: aload_0
    //   109: aload_1
    //   110: invokevirtual 169	com/mediav/ads/sdk/adcore/HttpCacher:remove	(Ljava/lang/String;)Z
    //   113: pop
    //   114: aload_2
    //   115: areturn
    //   116: astore_3
    //   117: aload_3
    //   118: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   121: goto -19 -> 102
    //   124: aload_3
    //   125: ifnull +7 -> 132
    //   128: aload_3
    //   129: invokevirtual 263	java/io/BufferedReader:close	()V
    //   132: aload 5
    //   134: astore_3
    //   135: iconst_1
    //   136: ifeq -114 -> 22
    //   139: aload_0
    //   140: aload_1
    //   141: invokevirtual 169	com/mediav/ads/sdk/adcore/HttpCacher:remove	(Ljava/lang/String;)Z
    //   144: pop
    //   145: aconst_null
    //   146: areturn
    //   147: astore_2
    //   148: aload_2
    //   149: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   152: goto -20 -> 132
    //   155: astore_2
    //   156: aload 4
    //   158: astore_3
    //   159: aload_2
    //   160: astore 4
    //   162: aload_3
    //   163: astore_2
    //   164: aload 4
    //   166: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   169: aload_3
    //   170: ifnull +7 -> 177
    //   173: aload_3
    //   174: invokevirtual 263	java/io/BufferedReader:close	()V
    //   177: aload 5
    //   179: astore_3
    //   180: iconst_0
    //   181: ifeq -159 -> 22
    //   184: aload_0
    //   185: aload_1
    //   186: invokevirtual 169	com/mediav/ads/sdk/adcore/HttpCacher:remove	(Ljava/lang/String;)Z
    //   189: pop
    //   190: aconst_null
    //   191: areturn
    //   192: astore_2
    //   193: aload_2
    //   194: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   197: goto -20 -> 177
    //   200: astore_3
    //   201: aload_2
    //   202: ifnull +7 -> 209
    //   205: aload_2
    //   206: invokevirtual 263	java/io/BufferedReader:close	()V
    //   209: iconst_0
    //   210: ifeq +9 -> 219
    //   213: aload_0
    //   214: aload_1
    //   215: invokevirtual 169	com/mediav/ads/sdk/adcore/HttpCacher:remove	(Ljava/lang/String;)Z
    //   218: pop
    //   219: aload_3
    //   220: athrow
    //   221: astore_2
    //   222: aload_2
    //   223: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   226: goto -17 -> 209
    //   229: astore 4
    //   231: aload_3
    //   232: astore_2
    //   233: aload 4
    //   235: astore_3
    //   236: goto -35 -> 201
    //   239: astore 4
    //   241: goto -79 -> 162
    //
    // Exception table:
    //   from	to	target	type
    //   98	102	116	java/io/IOException
    //   128	132	147	java/io/IOException
    //   29	45	155	java/lang/Exception
    //   173	177	192	java/io/IOException
    //   29	45	200	finally
    //   164	169	200	finally
    //   205	209	221	java/io/IOException
    //   48	54	229	finally
    //   59	79	229	finally
    //   82	94	229	finally
    //   48	54	239	java/lang/Exception
    //   59	79	239	java/lang/Exception
    //   82	94	239	java/lang/Exception
  }

  public OutputStream put(String paramString)
    throws FileNotFoundException
  {
    return new xFileOutputStream(this.mCache.newFile(paramString));
  }

  public void put(String paramString, Bitmap paramBitmap)
  {
    put(paramString, Utils.Bitmap2Bytes(paramBitmap));
  }

  public void put(String paramString, Bitmap paramBitmap, int paramInt)
  {
    put(paramString, Utils.Bitmap2Bytes(paramBitmap), paramInt);
  }

  public void put(String paramString, Drawable paramDrawable)
  {
    put(paramString, Utils.drawable2Bitmap(paramDrawable));
  }

  public void put(String paramString, Drawable paramDrawable, int paramInt)
  {
    put(paramString, Utils.drawable2Bitmap(paramDrawable), paramInt);
  }

  public void put(String paramString, Serializable paramSerializable)
  {
    put(paramString, paramSerializable, -1);
  }

  // ERROR //
  public void put(String paramString, Serializable paramSerializable, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 5
    //   9: new 295	java/io/ByteArrayOutputStream
    //   12: dup
    //   13: invokespecial 296	java/io/ByteArrayOutputStream:<init>	()V
    //   16: astore 7
    //   18: new 298	java/io/ObjectOutputStream
    //   21: dup
    //   22: aload 7
    //   24: invokespecial 301	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   27: astore 4
    //   29: aload 4
    //   31: aload_2
    //   32: invokevirtual 305	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   35: aload 7
    //   37: invokevirtual 309	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   40: astore_2
    //   41: iload_3
    //   42: iconst_m1
    //   43: if_icmpeq +21 -> 64
    //   46: aload_0
    //   47: aload_1
    //   48: aload_2
    //   49: iload_3
    //   50: invokevirtual 279	com/mediav/ads/sdk/adcore/HttpCacher:put	(Ljava/lang/String;[BI)V
    //   53: aload 4
    //   55: ifnull +8 -> 63
    //   58: aload 4
    //   60: invokevirtual 310	java/io/ObjectOutputStream:close	()V
    //   63: return
    //   64: aload_0
    //   65: aload_1
    //   66: aload_2
    //   67: invokevirtual 275	com/mediav/ads/sdk/adcore/HttpCacher:put	(Ljava/lang/String;[B)V
    //   70: goto -17 -> 53
    //   73: astore_1
    //   74: aload 4
    //   76: astore_2
    //   77: aload_2
    //   78: astore 4
    //   80: aload_1
    //   81: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   84: aload_2
    //   85: ifnull -22 -> 63
    //   88: aload_2
    //   89: invokevirtual 310	java/io/ObjectOutputStream:close	()V
    //   92: return
    //   93: astore_1
    //   94: aload_1
    //   95: invokevirtual 313	java/io/IOException:getMessage	()Ljava/lang/String;
    //   98: invokestatic 318	com/mediav/ads/sdk/log/MVLog:e	(Ljava/lang/String;)V
    //   101: return
    //   102: astore_1
    //   103: aload_1
    //   104: invokevirtual 313	java/io/IOException:getMessage	()Ljava/lang/String;
    //   107: invokestatic 318	com/mediav/ads/sdk/log/MVLog:e	(Ljava/lang/String;)V
    //   110: return
    //   111: astore_1
    //   112: aload 4
    //   114: ifnull +8 -> 122
    //   117: aload 4
    //   119: invokevirtual 310	java/io/ObjectOutputStream:close	()V
    //   122: aload_1
    //   123: athrow
    //   124: astore_2
    //   125: aload_2
    //   126: invokevirtual 313	java/io/IOException:getMessage	()Ljava/lang/String;
    //   129: invokestatic 318	com/mediav/ads/sdk/log/MVLog:e	(Ljava/lang/String;)V
    //   132: goto -10 -> 122
    //   135: astore_1
    //   136: aload 6
    //   138: astore 4
    //   140: goto -28 -> 112
    //   143: astore_1
    //   144: goto -32 -> 112
    //   147: astore_1
    //   148: aload 5
    //   150: astore_2
    //   151: goto -74 -> 77
    //   154: astore_1
    //   155: aload 5
    //   157: astore_2
    //   158: goto -81 -> 77
    //
    // Exception table:
    //   from	to	target	type
    //   29	41	73	java/lang/Exception
    //   46	53	73	java/lang/Exception
    //   64	70	73	java/lang/Exception
    //   88	92	93	java/io/IOException
    //   58	63	102	java/io/IOException
    //   9	18	111	finally
    //   80	84	111	finally
    //   117	122	124	java/io/IOException
    //   18	29	135	finally
    //   29	41	143	finally
    //   46	53	143	finally
    //   64	70	143	finally
    //   9	18	147	java/lang/Exception
    //   18	29	154	java/lang/Exception
  }

  // ERROR //
  public void put(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 80	com/mediav/ads/sdk/adcore/HttpCacher:mCache	Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;
    //   4: aload_1
    //   5: invokestatic 147	com/mediav/ads/sdk/adcore/HttpCacher$ACacheManager:access$100	(Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;Ljava/lang/String;)Ljava/io/File;
    //   8: astore 5
    //   10: aconst_null
    //   11: astore_1
    //   12: aconst_null
    //   13: astore 4
    //   15: new 321	java/io/BufferedWriter
    //   18: dup
    //   19: new 323	java/io/FileWriter
    //   22: dup
    //   23: aload 5
    //   25: invokespecial 324	java/io/FileWriter:<init>	(Ljava/io/File;)V
    //   28: sipush 1024
    //   31: invokespecial 327	java/io/BufferedWriter:<init>	(Ljava/io/Writer;I)V
    //   34: astore_3
    //   35: aload_3
    //   36: aload_2
    //   37: invokevirtual 330	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   40: aload_3
    //   41: ifnull +11 -> 52
    //   44: aload_3
    //   45: invokevirtual 333	java/io/BufferedWriter:flush	()V
    //   48: aload_3
    //   49: invokevirtual 334	java/io/BufferedWriter:close	()V
    //   52: aload_0
    //   53: getfield 80	com/mediav/ads/sdk/adcore/HttpCacher:mCache	Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;
    //   56: aload 5
    //   58: invokestatic 338	com/mediav/ads/sdk/adcore/HttpCacher$ACacheManager:access$200	(Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;Ljava/io/File;)V
    //   61: return
    //   62: astore_1
    //   63: aload_1
    //   64: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   67: goto -15 -> 52
    //   70: astore_3
    //   71: aload 4
    //   73: astore_2
    //   74: aload_2
    //   75: astore_1
    //   76: aload_3
    //   77: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   80: aload_2
    //   81: ifnull +11 -> 92
    //   84: aload_2
    //   85: invokevirtual 333	java/io/BufferedWriter:flush	()V
    //   88: aload_2
    //   89: invokevirtual 334	java/io/BufferedWriter:close	()V
    //   92: aload_0
    //   93: getfield 80	com/mediav/ads/sdk/adcore/HttpCacher:mCache	Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;
    //   96: aload 5
    //   98: invokestatic 338	com/mediav/ads/sdk/adcore/HttpCacher$ACacheManager:access$200	(Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;Ljava/io/File;)V
    //   101: return
    //   102: astore_1
    //   103: aload_1
    //   104: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   107: goto -15 -> 92
    //   110: astore_2
    //   111: aload_1
    //   112: ifnull +11 -> 123
    //   115: aload_1
    //   116: invokevirtual 333	java/io/BufferedWriter:flush	()V
    //   119: aload_1
    //   120: invokevirtual 334	java/io/BufferedWriter:close	()V
    //   123: aload_0
    //   124: getfield 80	com/mediav/ads/sdk/adcore/HttpCacher:mCache	Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;
    //   127: aload 5
    //   129: invokestatic 338	com/mediav/ads/sdk/adcore/HttpCacher$ACacheManager:access$200	(Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;Ljava/io/File;)V
    //   132: aload_2
    //   133: athrow
    //   134: astore_1
    //   135: aload_1
    //   136: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   139: goto -16 -> 123
    //   142: astore_2
    //   143: aload_3
    //   144: astore_1
    //   145: goto -34 -> 111
    //   148: astore_1
    //   149: aload_3
    //   150: astore_2
    //   151: aload_1
    //   152: astore_3
    //   153: goto -79 -> 74
    //
    // Exception table:
    //   from	to	target	type
    //   44	52	62	java/io/IOException
    //   15	35	70	java/io/IOException
    //   84	92	102	java/io/IOException
    //   15	35	110	finally
    //   76	80	110	finally
    //   115	123	134	java/io/IOException
    //   35	40	142	finally
    //   35	40	148	java/io/IOException
  }

  public void put(String paramString1, String paramString2, int paramInt)
  {
    put(paramString1, Utils.newStringWithDateInfo(paramInt, paramString2));
  }

  public void put(String paramString, JSONArray paramJSONArray)
  {
    put(paramString, paramJSONArray.toString());
  }

  public void put(String paramString, JSONArray paramJSONArray, int paramInt)
  {
    put(paramString, paramJSONArray.toString(), paramInt);
  }

  public void put(String paramString, JSONObject paramJSONObject)
  {
    put(paramString, paramJSONObject.toString());
  }

  public void put(String paramString, JSONObject paramJSONObject, int paramInt)
  {
    put(paramString, paramJSONObject.toString(), paramInt);
  }

  // ERROR //
  public void put(String paramString, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 80	com/mediav/ads/sdk/adcore/HttpCacher:mCache	Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;
    //   4: aload_1
    //   5: invokestatic 147	com/mediav/ads/sdk/adcore/HttpCacher$ACacheManager:access$100	(Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;Ljava/lang/String;)Ljava/io/File;
    //   8: astore 5
    //   10: aconst_null
    //   11: astore_1
    //   12: aconst_null
    //   13: astore 4
    //   15: new 355	java/io/FileOutputStream
    //   18: dup
    //   19: aload 5
    //   21: invokespecial 356	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   24: astore_3
    //   25: aload_3
    //   26: aload_2
    //   27: invokevirtual 358	java/io/FileOutputStream:write	([B)V
    //   30: aload_3
    //   31: ifnull +11 -> 42
    //   34: aload_3
    //   35: invokevirtual 359	java/io/FileOutputStream:flush	()V
    //   38: aload_3
    //   39: invokevirtual 360	java/io/FileOutputStream:close	()V
    //   42: aload_0
    //   43: getfield 80	com/mediav/ads/sdk/adcore/HttpCacher:mCache	Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;
    //   46: aload 5
    //   48: invokestatic 338	com/mediav/ads/sdk/adcore/HttpCacher$ACacheManager:access$200	(Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;Ljava/io/File;)V
    //   51: return
    //   52: astore_1
    //   53: aload_1
    //   54: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   57: goto -15 -> 42
    //   60: astore_3
    //   61: aload 4
    //   63: astore_2
    //   64: aload_2
    //   65: astore_1
    //   66: aload_3
    //   67: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   70: aload_2
    //   71: ifnull +11 -> 82
    //   74: aload_2
    //   75: invokevirtual 359	java/io/FileOutputStream:flush	()V
    //   78: aload_2
    //   79: invokevirtual 360	java/io/FileOutputStream:close	()V
    //   82: aload_0
    //   83: getfield 80	com/mediav/ads/sdk/adcore/HttpCacher:mCache	Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;
    //   86: aload 5
    //   88: invokestatic 338	com/mediav/ads/sdk/adcore/HttpCacher$ACacheManager:access$200	(Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;Ljava/io/File;)V
    //   91: return
    //   92: astore_1
    //   93: aload_1
    //   94: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   97: goto -15 -> 82
    //   100: astore_2
    //   101: aload_1
    //   102: ifnull +11 -> 113
    //   105: aload_1
    //   106: invokevirtual 359	java/io/FileOutputStream:flush	()V
    //   109: aload_1
    //   110: invokevirtual 360	java/io/FileOutputStream:close	()V
    //   113: aload_0
    //   114: getfield 80	com/mediav/ads/sdk/adcore/HttpCacher:mCache	Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;
    //   117: aload 5
    //   119: invokestatic 338	com/mediav/ads/sdk/adcore/HttpCacher$ACacheManager:access$200	(Lcom/mediav/ads/sdk/adcore/HttpCacher$ACacheManager;Ljava/io/File;)V
    //   122: aload_2
    //   123: athrow
    //   124: astore_1
    //   125: aload_1
    //   126: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   129: goto -16 -> 113
    //   132: astore_2
    //   133: aload_3
    //   134: astore_1
    //   135: goto -34 -> 101
    //   138: astore_1
    //   139: aload_3
    //   140: astore_2
    //   141: aload_1
    //   142: astore_3
    //   143: goto -79 -> 64
    //
    // Exception table:
    //   from	to	target	type
    //   34	42	52	java/io/IOException
    //   15	25	60	java/lang/Exception
    //   74	82	92	java/io/IOException
    //   15	25	100	finally
    //   66	70	100	finally
    //   105	113	124	java/io/IOException
    //   25	30	132	finally
    //   25	30	138	java/lang/Exception
  }

  public void put(String paramString, byte[] paramArrayOfByte, int paramInt)
  {
    put(paramString, Utils.newByteArrayWithDateInfo(paramInt, paramArrayOfByte));
  }

  public boolean remove(String paramString)
  {
    return this.mCache.remove(paramString);
  }

  public class ACacheManager
  {
    private final AtomicInteger cacheCount;
    protected File cacheDir;
    private final AtomicLong cacheSize;
    private final int countLimit;
    private final Map<File, Long> lastUsageDates = Collections.synchronizedMap(new HashMap());
    private final long sizeLimit;

    private ACacheManager(File paramLong, long arg3, int arg5)
    {
      this.cacheDir = paramLong;
      this.sizeLimit = ???;
      int i;
      this.countLimit = i;
      this.cacheSize = new AtomicLong();
      this.cacheCount = new AtomicInteger();
      calculateCacheSizeAndCacheCount();
    }

    private void calculateCacheSizeAndCacheCount()
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          int k = 0;
          int j = 0;
          File[] arrayOfFile = HttpCacher.ACacheManager.this.cacheDir.listFiles();
          if (arrayOfFile != null)
          {
            int m = arrayOfFile.length;
            int i = 0;
            while (i < m)
            {
              File localFile = arrayOfFile[i];
              k = (int)(k + HttpCacher.ACacheManager.this.calculateSize(localFile));
              j += 1;
              HttpCacher.ACacheManager.this.lastUsageDates.put(localFile, Long.valueOf(localFile.lastModified()));
              i += 1;
            }
            HttpCacher.ACacheManager.this.cacheSize.set(k);
            HttpCacher.ACacheManager.this.cacheCount.set(j);
          }
        }
      }).start();
    }

    private long calculateSize(File paramFile)
    {
      return paramFile.length();
    }

    private void clear()
    {
      this.lastUsageDates.clear();
      this.cacheSize.set(0L);
      File[] arrayOfFile = this.cacheDir.listFiles();
      if (arrayOfFile != null)
      {
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          arrayOfFile[i].delete();
          i += 1;
        }
      }
    }

    private File get(String paramString)
    {
      paramString = newFile(paramString);
      Long localLong = Long.valueOf(System.currentTimeMillis());
      paramString.setLastModified(localLong.longValue());
      this.lastUsageDates.put(paramString, localLong);
      return paramString;
    }

    private File newFile(String paramString)
    {
      return new File(this.cacheDir, paramString.hashCode() + "");
    }

    private void put(File paramFile)
    {
      for (int i = this.cacheCount.get(); i + 1 > this.countLimit; i = this.cacheCount.addAndGet(-1))
      {
        l1 = removeNext();
        this.cacheSize.addAndGet(-l1);
      }
      this.cacheCount.addAndGet(1);
      long l2 = calculateSize(paramFile);
      for (long l1 = this.cacheSize.get(); l1 + l2 > this.sizeLimit; l1 = this.cacheSize.addAndGet(-l1))
        l1 = removeNext();
      this.cacheSize.addAndGet(l2);
      Long localLong = Long.valueOf(System.currentTimeMillis());
      paramFile.setLastModified(localLong.longValue());
      this.lastUsageDates.put(paramFile, localLong);
    }

    private boolean remove(String paramString)
    {
      return get(paramString).delete();
    }

    private long removeNext()
    {
      long l1 = 0L;
      if (this.lastUsageDates.isEmpty());
      while (true)
      {
        return l1;
        Object localObject2 = null;
        File localFile = null;
        Object localObject3 = this.lastUsageDates.entrySet();
        synchronized (this.lastUsageDates)
        {
          Iterator localIterator = ((Set)localObject3).iterator();
          while (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            if (localFile == null)
            {
              localFile = (File)localEntry.getKey();
              localObject2 = (Long)localEntry.getValue();
            }
            else
            {
              localObject3 = (Long)localEntry.getValue();
              if (((Long)localObject3).longValue() < ((Long)localObject2).longValue())
              {
                localObject2 = localObject3;
                localFile = (File)localEntry.getKey();
              }
            }
          }
          if (localFile == null)
            continue;
          long l2 = calculateSize(localFile);
          l1 = l2;
          if (!localFile.delete())
            continue;
          this.lastUsageDates.remove(localFile);
          return l2;
        }
      }
    }
  }

  private static class Utils
  {
    private static final char mSeparator = ' ';

    private static byte[] Bitmap2Bytes(Bitmap paramBitmap)
    {
      if (paramBitmap == null)
        return null;
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
      return localByteArrayOutputStream.toByteArray();
    }

    private static Bitmap Bytes2Bimap(byte[] paramArrayOfByte)
    {
      if (paramArrayOfByte.length == 0)
        return null;
      return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
    }

    private static Drawable bitmap2Drawable(Bitmap paramBitmap)
    {
      if (paramBitmap == null)
        return null;
      new BitmapDrawable(paramBitmap).setTargetDensity(paramBitmap.getDensity());
      return new BitmapDrawable(paramBitmap);
    }

    private static String clearDateInfo(String paramString)
    {
      String str = paramString;
      if (paramString != null)
      {
        str = paramString;
        if (hasDateInfo(paramString.getBytes()))
          str = paramString.substring(paramString.indexOf(' ') + 1, paramString.length());
      }
      return str;
    }

    private static byte[] clearDateInfo(byte[] paramArrayOfByte)
      throws Exception
    {
      byte[] arrayOfByte = paramArrayOfByte;
      if (hasDateInfo(paramArrayOfByte))
        arrayOfByte = copyOfRange(paramArrayOfByte, indexOf(paramArrayOfByte, ' ') + 1, paramArrayOfByte.length);
      return arrayOfByte;
    }

    private static byte[] copyOfRange(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws Exception
    {
      int i = paramInt2 - paramInt1;
      if (i < 0)
        throw new IllegalArgumentException(paramInt1 + " > " + paramInt2);
      byte[] arrayOfByte = new byte[i];
      System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, Math.min(paramArrayOfByte.length - paramInt1, i));
      return arrayOfByte;
    }

    private static String createDateInfo(int paramInt)
    {
      for (String str = System.currentTimeMillis() + ""; str.length() < 13; str = "0" + str);
      return str + "-" + paramInt + ' ';
    }

    private static Bitmap drawable2Bitmap(Drawable paramDrawable)
    {
      if (paramDrawable == null)
        return null;
      int i = paramDrawable.getIntrinsicWidth();
      int j = paramDrawable.getIntrinsicHeight();
      if (paramDrawable.getOpacity() != -1);
      for (Object localObject = Bitmap.Config.ARGB_8888; ; localObject = Bitmap.Config.RGB_565)
      {
        localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
        Canvas localCanvas = new Canvas((Bitmap)localObject);
        paramDrawable.setBounds(0, 0, i, j);
        paramDrawable.draw(localCanvas);
        return localObject;
      }
    }

    private static String[] getDateInfoFromDate(byte[] paramArrayOfByte)
      throws Exception
    {
      if (hasDateInfo(paramArrayOfByte))
        return new String[] { new String(copyOfRange(paramArrayOfByte, 0, 13)), new String(copyOfRange(paramArrayOfByte, 14, indexOf(paramArrayOfByte, ' '))) };
      return null;
    }

    private static boolean hasDateInfo(byte[] paramArrayOfByte)
    {
      return (paramArrayOfByte != null) && (paramArrayOfByte.length > 15) && (paramArrayOfByte[13] == 45) && (indexOf(paramArrayOfByte, ' ') > 14);
    }

    private static int indexOf(byte[] paramArrayOfByte, char paramChar)
    {
      int i = 0;
      while (i < paramArrayOfByte.length)
      {
        if (paramArrayOfByte[i] == paramChar)
          return i;
        i += 1;
      }
      return -1;
    }

    private static boolean isDue(String paramString)
      throws Exception
    {
      return isDue(paramString.getBytes());
    }

    private static boolean isDue(byte[] paramArrayOfByte)
      throws Exception
    {
      String[] arrayOfString = getDateInfoFromDate(paramArrayOfByte);
      if ((arrayOfString != null) && (arrayOfString.length == 2))
      {
        for (paramArrayOfByte = arrayOfString[0]; paramArrayOfByte.startsWith("0"); paramArrayOfByte = paramArrayOfByte.substring(1, paramArrayOfByte.length()));
        long l1 = Long.valueOf(paramArrayOfByte).longValue();
        long l2 = Long.valueOf(arrayOfString[1]).longValue();
        if (System.currentTimeMillis() > 1000L * l2 + l1)
          return true;
      }
      return false;
    }

    private static byte[] newByteArrayWithDateInfo(int paramInt, byte[] paramArrayOfByte)
    {
      byte[] arrayOfByte1 = createDateInfo(paramInt).getBytes();
      byte[] arrayOfByte2 = new byte[arrayOfByte1.length + paramArrayOfByte.length];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
      System.arraycopy(paramArrayOfByte, 0, arrayOfByte2, arrayOfByte1.length, paramArrayOfByte.length);
      return arrayOfByte2;
    }

    private static String newStringWithDateInfo(int paramInt, String paramString)
    {
      return createDateInfo(paramInt) + paramString;
    }
  }

  class xFileOutputStream extends FileOutputStream
  {
    File file;

    public xFileOutputStream(File arg2)
      throws FileNotFoundException
    {
      super();
      this.file = localFile;
    }

    public void close()
      throws IOException
    {
      super.close();
      HttpCacher.this.mCache.put(this.file);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.adcore.HttpCacher
 * JD-Core Version:    0.6.2
 */