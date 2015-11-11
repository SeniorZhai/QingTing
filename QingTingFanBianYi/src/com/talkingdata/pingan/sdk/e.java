package com.talkingdata.pingan.sdk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class e
{
  protected static final String a = "pingan";
  private static final int b = 5;
  private static final String c = "10";
  private static final String d = "pingantcagent.db";
  private static SQLiteDatabase e;
  private static int f;

  // ERROR //
  static long a(long paramLong)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: getstatic 38	com/talkingdata/pingan/sdk/e:e	Landroid/database/sqlite/SQLiteDatabase;
    //   5: ldc 40
    //   7: getstatic 44	com/talkingdata/pingan/sdk/e$b:h	[Ljava/lang/String;
    //   10: ldc 46
    //   12: iconst_1
    //   13: anewarray 48	java/lang/String
    //   16: dup
    //   17: iconst_0
    //   18: lload_0
    //   19: invokestatic 52	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   22: aastore
    //   23: aconst_null
    //   24: aconst_null
    //   25: ldc 54
    //   27: invokevirtual 60	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   30: astore_2
    //   31: aload_2
    //   32: invokeinterface 66 1 0
    //   37: ifeq +33 -> 70
    //   40: aload_2
    //   41: invokeinterface 69 1 0
    //   46: ifne +24 -> 70
    //   49: aload_2
    //   50: bipush 6
    //   52: invokeinterface 73 2 0
    //   57: lstore_0
    //   58: aload_2
    //   59: ifnull +9 -> 68
    //   62: aload_2
    //   63: invokeinterface 76 1 0
    //   68: lload_0
    //   69: lreturn
    //   70: aload_2
    //   71: ifnull +9 -> 80
    //   74: aload_2
    //   75: invokeinterface 76 1 0
    //   80: lconst_0
    //   81: lreturn
    //   82: astore_2
    //   83: aconst_null
    //   84: astore_2
    //   85: aload_2
    //   86: ifnull -6 -> 80
    //   89: aload_2
    //   90: invokeinterface 76 1 0
    //   95: goto -15 -> 80
    //   98: astore_2
    //   99: aload_3
    //   100: ifnull +9 -> 109
    //   103: aload_3
    //   104: invokeinterface 76 1 0
    //   109: aload_2
    //   110: athrow
    //   111: astore 4
    //   113: aload_2
    //   114: astore_3
    //   115: aload 4
    //   117: astore_2
    //   118: goto -19 -> 99
    //   121: astore_3
    //   122: goto -37 -> 85
    //
    // Exception table:
    //   from	to	target	type
    //   2	31	82	java/lang/Exception
    //   2	31	98	finally
    //   31	58	111	finally
    //   31	58	121	java/lang/Exception
  }

  static long a(long paramLong, String paramString)
  {
    q.a(new String[] { "Save Error", "errorTime:" + paramLong, "data:" + paramString });
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("error_time", Long.valueOf(paramLong));
    try
    {
      al localal = new al();
      StringBuffer localStringBuffer = new StringBuffer("");
      paramLong = a(paramString, localal, localStringBuffer);
      if (0L == paramLong)
      {
        localContentValues.put("message", paramString.getBytes("UTF-8"));
        localContentValues.put("repeat", Integer.valueOf(1));
        localContentValues.put("shorthashcode", localStringBuffer.toString());
        return e.insert("error_report", null, localContentValues);
      }
      localContentValues.put("repeat", Integer.valueOf(localal.b + 1));
      int i = e.update("error_report", localContentValues, "_id=?", new String[] { String.valueOf(paramLong) });
      return i;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString.printStackTrace();
    }
    return 0L;
  }

  static long a(String paramString, long paramLong1, long paramLong2, int paramInt)
  {
    try
    {
      q.a(new String[] { "Save Session", "sessionId:" + paramString, "startTime:" + paramLong1 });
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("session_id", paramString);
      localContentValues.put("start_time", Long.valueOf(paramLong1));
      localContentValues.put("duration", Integer.valueOf(0));
      localContentValues.put("is_launch", Integer.valueOf(0));
      localContentValues.put("interval", Long.valueOf(paramLong2));
      localContentValues.put("is_connected", Integer.valueOf(paramInt));
      e.insert("session", null, localContentValues);
      label141: return 0L;
    }
    catch (Exception paramString)
    {
      break label141;
    }
  }

  // ERROR //
  static long a(String paramString, al paramal, StringBuffer paramStringBuffer)
  {
    // Byte code:
    //   0: getstatic 38	com/talkingdata/pingan/sdk/e:e	Landroid/database/sqlite/SQLiteDatabase;
    //   3: ldc 160
    //   5: getstatic 196	com/talkingdata/pingan/sdk/e$c:f	[Ljava/lang/String;
    //   8: aconst_null
    //   9: aconst_null
    //   10: aconst_null
    //   11: aconst_null
    //   12: ldc 54
    //   14: invokevirtual 60	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   17: astore_3
    //   18: aload_0
    //   19: ldc 198
    //   21: invokevirtual 202	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   24: astore_0
    //   25: aload_0
    //   26: arraylength
    //   27: istore 4
    //   29: iload 4
    //   31: iconst_3
    //   32: if_icmpge +27 -> 59
    //   35: lconst_0
    //   36: lstore 7
    //   38: lload 7
    //   40: lstore 5
    //   42: aload_3
    //   43: ifnull +13 -> 56
    //   46: aload_3
    //   47: invokeinterface 76 1 0
    //   52: lload 7
    //   54: lstore 5
    //   56: lload 5
    //   58: lreturn
    //   59: new 83	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   66: aload_0
    //   67: iconst_0
    //   68: aaload
    //   69: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: ldc 198
    //   74: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: aload_0
    //   78: iconst_1
    //   79: aaload
    //   80: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: ldc 198
    //   85: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload_0
    //   89: iconst_2
    //   90: aaload
    //   91: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: astore_0
    //   98: aload_2
    //   99: aload_0
    //   100: invokestatic 207	com/talkingdata/pingan/sdk/aa:b	(Ljava/lang/String;)Ljava/lang/String;
    //   103: invokevirtual 210	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   106: pop
    //   107: aload_3
    //   108: invokeinterface 66 1 0
    //   113: ifeq +183 -> 296
    //   116: aload_3
    //   117: invokeinterface 69 1 0
    //   122: ifne +174 -> 296
    //   125: aload_1
    //   126: aload_3
    //   127: iconst_1
    //   128: invokeinterface 73 2 0
    //   133: putfield 213	com/talkingdata/pingan/sdk/al:a	J
    //   136: aload_1
    //   137: aload_3
    //   138: iconst_2
    //   139: invokeinterface 217 2 0
    //   144: putfield 220	com/talkingdata/pingan/sdk/al:d	[B
    //   147: aload_1
    //   148: aload_3
    //   149: iconst_3
    //   150: invokeinterface 224 2 0
    //   155: putfield 166	com/talkingdata/pingan/sdk/al:b	I
    //   158: new 48	java/lang/String
    //   161: dup
    //   162: aload_1
    //   163: getfield 220	com/talkingdata/pingan/sdk/al:d	[B
    //   166: ldc 135
    //   168: invokespecial 227	java/lang/String:<init>	([BLjava/lang/String;)V
    //   171: astore_2
    //   172: aload_2
    //   173: invokevirtual 231	java/lang/String:length	()I
    //   176: aload_0
    //   177: invokevirtual 231	java/lang/String:length	()I
    //   180: if_icmplt -64 -> 116
    //   183: aload_2
    //   184: ldc 198
    //   186: invokevirtual 202	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   189: astore_2
    //   190: aload_2
    //   191: arraylength
    //   192: iconst_3
    //   193: if_icmplt -77 -> 116
    //   196: new 83	java/lang/StringBuilder
    //   199: dup
    //   200: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   203: aload_2
    //   204: iconst_0
    //   205: aaload
    //   206: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: ldc 198
    //   211: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: aload_2
    //   215: iconst_1
    //   216: aaload
    //   217: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: ldc 198
    //   222: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: aload_2
    //   226: iconst_2
    //   227: aaload
    //   228: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   234: aload_0
    //   235: invokevirtual 235	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   238: ifeq +29 -> 267
    //   241: aload_3
    //   242: iconst_0
    //   243: invokeinterface 73 2 0
    //   248: lstore 7
    //   250: lload 7
    //   252: lstore 5
    //   254: aload_3
    //   255: ifnull -199 -> 56
    //   258: aload_3
    //   259: invokeinterface 76 1 0
    //   264: lload 7
    //   266: lreturn
    //   267: aload_3
    //   268: invokeinterface 238 1 0
    //   273: pop
    //   274: goto -158 -> 116
    //   277: astore_1
    //   278: aload_3
    //   279: astore_0
    //   280: aload_1
    //   281: invokevirtual 239	java/lang/Exception:printStackTrace	()V
    //   284: aload_0
    //   285: ifnull +9 -> 294
    //   288: aload_0
    //   289: invokeinterface 76 1 0
    //   294: lconst_0
    //   295: lreturn
    //   296: aload_3
    //   297: ifnull -3 -> 294
    //   300: aload_3
    //   301: invokeinterface 76 1 0
    //   306: goto -12 -> 294
    //   309: astore_0
    //   310: aconst_null
    //   311: astore_3
    //   312: aload_3
    //   313: ifnull +9 -> 322
    //   316: aload_3
    //   317: invokeinterface 76 1 0
    //   322: aload_0
    //   323: athrow
    //   324: astore_0
    //   325: goto -13 -> 312
    //   328: astore_1
    //   329: aload_0
    //   330: astore_3
    //   331: aload_1
    //   332: astore_0
    //   333: goto -21 -> 312
    //   336: astore_1
    //   337: aconst_null
    //   338: astore_0
    //   339: goto -59 -> 280
    //
    // Exception table:
    //   from	to	target	type
    //   18	29	277	java/lang/Exception
    //   59	116	277	java/lang/Exception
    //   116	250	277	java/lang/Exception
    //   267	274	277	java/lang/Exception
    //   0	18	309	finally
    //   18	29	324	finally
    //   59	116	324	finally
    //   116	250	324	finally
    //   267	274	324	finally
    //   280	284	328	finally
    //   0	18	336	java/lang/Exception
  }

  static long a(String paramString1, String paramString2, long paramLong1, int paramInt, String paramString3, long paramLong2)
  {
    try
    {
      q.a(new String[] { "Save Activity", "sessionId:" + paramString1, "name:" + paramString2, "start:" + paramLong1, "duration:" + paramInt, "refer:" + paramString3 });
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("session_id", paramString1);
      localContentValues.put("name", paramString2);
      localContentValues.put("start_time", Long.valueOf(paramLong1));
      localContentValues.put("duration", Integer.valueOf(paramInt));
      localContentValues.put("refer", paramString3);
      localContentValues.put("realtime", Long.valueOf(paramLong2));
      paramLong1 = e.insert("activity", null, localContentValues);
      q.a(new String[] { "ActEvent", "Save activity: " + paramLong1 + " sessionId:" + paramString1 + " name:" + paramString2 });
      return paramLong1;
    }
    catch (Exception paramString1)
    {
    }
    return -1L;
  }

  // ERROR //
  static long a(List paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokeinterface 275 1 0
    //   6: istore_2
    //   7: iload_2
    //   8: ifne +9 -> 17
    //   11: lconst_0
    //   12: lstore 5
    //   14: lload 5
    //   16: lreturn
    //   17: iload_2
    //   18: iconst_1
    //   19: isub
    //   20: istore_2
    //   21: lconst_0
    //   22: lstore_3
    //   23: iload_2
    //   24: iflt +150 -> 174
    //   27: lload_3
    //   28: lstore 7
    //   30: new 83	java/lang/StringBuilder
    //   33: dup
    //   34: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   37: astore_1
    //   38: lload_3
    //   39: lstore 7
    //   41: aload_1
    //   42: ldc_w 277
    //   45: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: pop
    //   49: getstatic 38	com/talkingdata/pingan/sdk/e:e	Landroid/database/sqlite/SQLiteDatabase;
    //   52: aload_1
    //   53: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: iconst_1
    //   57: anewarray 48	java/lang/String
    //   60: dup
    //   61: iconst_0
    //   62: aload_0
    //   63: iload_2
    //   64: invokeinterface 281 2 0
    //   69: checkcast 283	com/talkingdata/pingan/sdk/c
    //   72: getfield 285	com/talkingdata/pingan/sdk/c:a	Ljava/lang/String;
    //   75: aastore
    //   76: invokevirtual 289	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   79: astore_1
    //   80: lload_3
    //   81: lstore 5
    //   83: aload_1
    //   84: invokeinterface 66 1 0
    //   89: ifeq +42 -> 131
    //   92: aload_1
    //   93: iconst_0
    //   94: invokeinterface 73 2 0
    //   99: lstore 5
    //   101: lload 5
    //   103: lstore_3
    //   104: lload_3
    //   105: lconst_0
    //   106: lcmp
    //   107: ifeq +21 -> 128
    //   110: lload_3
    //   111: lstore 5
    //   113: aload_1
    //   114: ifnull -100 -> 14
    //   117: aload_1
    //   118: invokeinterface 76 1 0
    //   123: lload_3
    //   124: lreturn
    //   125: astore_0
    //   126: lload_3
    //   127: lreturn
    //   128: lload_3
    //   129: lstore 5
    //   131: aload_1
    //   132: ifnull +44 -> 176
    //   135: lload 5
    //   137: lstore 7
    //   139: aload_1
    //   140: invokeinterface 76 1 0
    //   145: goto +31 -> 176
    //   148: aload_1
    //   149: ifnull +12 -> 161
    //   152: lload_3
    //   153: lstore 7
    //   155: aload_1
    //   156: invokeinterface 76 1 0
    //   161: lload_3
    //   162: lstore 7
    //   164: aload_0
    //   165: athrow
    //   166: astore_0
    //   167: lload 7
    //   169: lreturn
    //   170: astore_0
    //   171: goto -23 -> 148
    //   174: lload_3
    //   175: lreturn
    //   176: iload_2
    //   177: iconst_1
    //   178: isub
    //   179: istore_2
    //   180: lload 5
    //   182: lstore_3
    //   183: goto -160 -> 23
    //   186: astore_0
    //   187: aconst_null
    //   188: astore_1
    //   189: goto -41 -> 148
    //
    // Exception table:
    //   from	to	target	type
    //   117	123	125	java/lang/Exception
    //   30	38	166	java/lang/Exception
    //   41	49	166	java/lang/Exception
    //   139	145	166	java/lang/Exception
    //   155	161	166	java/lang/Exception
    //   164	166	166	java/lang/Exception
    //   83	101	170	finally
    //   49	80	186	finally
  }

  // ERROR //
  static List a(String paramString, long paramLong)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 292	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 293	java/util/ArrayList:<init>	()V
    //   9: astore 4
    //   11: getstatic 38	com/talkingdata/pingan/sdk/e:e	Landroid/database/sqlite/SQLiteDatabase;
    //   14: ldc 40
    //   16: getstatic 44	com/talkingdata/pingan/sdk/e$b:h	[Ljava/lang/String;
    //   19: ldc_w 295
    //   22: iconst_1
    //   23: anewarray 48	java/lang/String
    //   26: dup
    //   27: iconst_0
    //   28: aload_0
    //   29: aastore
    //   30: aconst_null
    //   31: aconst_null
    //   32: ldc 54
    //   34: invokevirtual 60	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   37: astore_0
    //   38: aload_0
    //   39: invokeinterface 66 1 0
    //   44: ifeq +97 -> 141
    //   47: aload_0
    //   48: invokeinterface 69 1 0
    //   53: ifne +88 -> 141
    //   56: new 297	com/talkingdata/pingan/sdk/am
    //   59: dup
    //   60: invokespecial 298	com/talkingdata/pingan/sdk/am:<init>	()V
    //   63: astore_3
    //   64: aload_3
    //   65: aload_0
    //   66: iconst_1
    //   67: invokeinterface 302 2 0
    //   72: putfield 303	com/talkingdata/pingan/sdk/am:a	Ljava/lang/String;
    //   75: aload_3
    //   76: aload_0
    //   77: iconst_2
    //   78: invokeinterface 73 2 0
    //   83: putfield 305	com/talkingdata/pingan/sdk/am:b	J
    //   86: aload_3
    //   87: aload_0
    //   88: iconst_3
    //   89: invokeinterface 224 2 0
    //   94: putfield 307	com/talkingdata/pingan/sdk/am:c	I
    //   97: aload_3
    //   98: aload_0
    //   99: iconst_5
    //   100: invokeinterface 302 2 0
    //   105: putfield 309	com/talkingdata/pingan/sdk/am:d	Ljava/lang/String;
    //   108: aload 4
    //   110: aload_3
    //   111: invokeinterface 312 2 0
    //   116: pop
    //   117: aload_0
    //   118: invokeinterface 238 1 0
    //   123: pop
    //   124: goto -77 -> 47
    //   127: astore_3
    //   128: aload_0
    //   129: ifnull +9 -> 138
    //   132: aload_0
    //   133: invokeinterface 76 1 0
    //   138: aload 4
    //   140: areturn
    //   141: aload_0
    //   142: ifnull -4 -> 138
    //   145: aload_0
    //   146: invokeinterface 76 1 0
    //   151: aload 4
    //   153: areturn
    //   154: astore 4
    //   156: aload_3
    //   157: astore_0
    //   158: aload 4
    //   160: astore_3
    //   161: aload_0
    //   162: ifnull +9 -> 171
    //   165: aload_0
    //   166: invokeinterface 76 1 0
    //   171: aload_3
    //   172: athrow
    //   173: astore_3
    //   174: goto -13 -> 161
    //   177: astore_0
    //   178: aconst_null
    //   179: astore_0
    //   180: goto -52 -> 128
    //
    // Exception table:
    //   from	to	target	type
    //   38	47	127	java/lang/Exception
    //   47	124	127	java/lang/Exception
    //   11	38	154	finally
    //   38	47	173	finally
    //   47	124	173	finally
    //   11	38	177	java/lang/Exception
  }

  // ERROR //
  private static Map a(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +8 -> 9
    //   4: aload_0
    //   5: arraylength
    //   6: ifne +5 -> 11
    //   9: aconst_null
    //   10: areturn
    //   11: new 315	java/util/HashMap
    //   14: dup
    //   15: invokespecial 316	java/util/HashMap:<init>	()V
    //   18: astore_3
    //   19: new 318	java/io/ByteArrayInputStream
    //   22: dup
    //   23: aload_0
    //   24: invokespecial 321	java/io/ByteArrayInputStream:<init>	([B)V
    //   27: astore_0
    //   28: new 323	java/io/DataInputStream
    //   31: dup
    //   32: aload_0
    //   33: invokespecial 326	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   36: astore_2
    //   37: aload_2
    //   38: invokevirtual 329	java/io/DataInputStream:readInt	()I
    //   41: istore 6
    //   43: iconst_0
    //   44: istore 5
    //   46: iload 5
    //   48: iload 6
    //   50: if_icmpge +74 -> 124
    //   53: aload_2
    //   54: invokevirtual 332	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   57: astore 4
    //   59: aload_2
    //   60: invokevirtual 329	java/io/DataInputStream:readInt	()I
    //   63: istore 7
    //   65: iload 7
    //   67: bipush 66
    //   69: if_icmpne +30 -> 99
    //   72: aload_2
    //   73: invokevirtual 336	java/io/DataInputStream:readDouble	()D
    //   76: invokestatic 341	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   79: astore_1
    //   80: aload_3
    //   81: aload 4
    //   83: aload_1
    //   84: invokeinterface 346 3 0
    //   89: pop
    //   90: iload 5
    //   92: iconst_1
    //   93: iadd
    //   94: istore 5
    //   96: goto -50 -> 46
    //   99: iload 7
    //   101: bipush 88
    //   103: if_icmpne +11 -> 114
    //   106: aload_2
    //   107: invokevirtual 332	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   110: astore_1
    //   111: goto -31 -> 80
    //   114: aload_0
    //   115: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   118: aload_2
    //   119: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   122: aconst_null
    //   123: areturn
    //   124: aload_0
    //   125: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   128: aload_2
    //   129: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   132: aload_3
    //   133: areturn
    //   134: astore_0
    //   135: aconst_null
    //   136: astore_0
    //   137: aconst_null
    //   138: astore_1
    //   139: aload_1
    //   140: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   143: aload_0
    //   144: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   147: aconst_null
    //   148: areturn
    //   149: astore_1
    //   150: aconst_null
    //   151: astore_2
    //   152: aconst_null
    //   153: astore_0
    //   154: aload_0
    //   155: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   158: aload_2
    //   159: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   162: aload_1
    //   163: athrow
    //   164: astore_1
    //   165: aconst_null
    //   166: astore_2
    //   167: goto -13 -> 154
    //   170: astore_1
    //   171: goto -17 -> 154
    //   174: astore_1
    //   175: aconst_null
    //   176: astore_2
    //   177: aload_0
    //   178: astore_1
    //   179: aload_2
    //   180: astore_0
    //   181: goto -42 -> 139
    //   184: astore_1
    //   185: aload_0
    //   186: astore_1
    //   187: aload_2
    //   188: astore_0
    //   189: goto -50 -> 139
    //
    // Exception table:
    //   from	to	target	type
    //   11	28	134	java/lang/Exception
    //   11	28	149	finally
    //   28	37	164	finally
    //   37	43	170	finally
    //   53	65	170	finally
    //   72	80	170	finally
    //   80	90	170	finally
    //   106	111	170	finally
    //   28	37	174	java/lang/Exception
    //   37	43	184	java/lang/Exception
    //   53	65	184	java/lang/Exception
    //   72	80	184	java/lang/Exception
    //   80	90	184	java/lang/Exception
    //   106	111	184	java/lang/Exception
  }

  static void a()
  {
    e.setVersion(5);
    a.a(e);
    b.a(e);
    d.a(e);
    c.a(e);
  }

  static void a(long paramLong1, long paramLong2)
  {
    paramLong2 = (paramLong2 - a(paramLong1)) / 1000L;
    q.a(new String[] { "Update Activity Duration", "id:" + paramLong1, "duration:" + paramLong2 });
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("duration", Long.valueOf(paramLong2));
    try
    {
      e.update("activity", localContentValues, "_id=?", new String[] { String.valueOf(paramLong1) });
      return;
    }
    catch (Exception localException)
    {
    }
  }

  static void a(Context paramContext)
  {
    while (true)
    {
      try
      {
        if (e == null)
        {
          paramContext = new File(paramContext.getFilesDir(), "pingantcagent.db");
          boolean bool = paramContext.exists();
          if (!paramContext.getParentFile().exists())
            paramContext.getParentFile().mkdirs();
          e = SQLiteDatabase.openOrCreateDatabase(paramContext, null);
          e.setLockingEnabled(true);
          e.setMaximumSize(1000000L);
          f = 1;
          if (!bool)
          {
            a();
            return;
          }
          if (5 <= e.getVersion())
            continue;
          e.execSQL("DROP TABLE IF EXISTS error_report");
          e.execSQL("DROP TABLE IF EXISTS app_event");
          e.execSQL("DROP TABLE IF EXISTS session");
          e.execSQL("DROP TABLE IF EXISTS activity");
          a();
          continue;
        }
      }
      finally
      {
      }
      f += 1;
    }
  }

  static void a(String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("is_launch", Integer.valueOf(1));
    e.update("session", localContentValues, "session_id=?", new String[] { paramString });
  }

  static void a(String paramString, int paramInt)
  {
    q.a(new String[] { "Update Session Duration", "sessionId:" + paramString, "duration:" + paramInt });
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("duration", Integer.valueOf(paramInt));
    try
    {
      e.update("session", localContentValues, "session_id=?", new String[] { paramString });
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  static boolean a(String paramString1, String paramString2, String paramString3, long paramLong, Map paramMap)
  {
    try
    {
      q.a(new String[] { "Save App Event", "sessionId:" + paramString1, "event:" + paramString2, "label:" + paramString3 });
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("event_id", paramString2);
      localContentValues.put("event_label", paramString3);
      localContentValues.put("session_id", paramString1);
      localContentValues.put("occurtime", Long.valueOf(paramLong));
      localContentValues.put("paramap", a(paramMap));
      paramLong = e.insert("app_event", null, localContentValues);
      return paramLong != -1L;
    }
    catch (Exception paramString1)
    {
    }
    return false;
  }

  // ERROR //
  private static byte[] a(Map paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +12 -> 13
    //   4: aload_0
    //   5: invokeinterface 448 1 0
    //   10: ifne +5 -> 15
    //   13: aconst_null
    //   14: areturn
    //   15: aload_0
    //   16: invokeinterface 448 1 0
    //   21: istore 5
    //   23: iload 5
    //   25: istore 4
    //   27: iload 5
    //   29: bipush 10
    //   31: if_icmple +7 -> 38
    //   34: bipush 10
    //   36: istore 4
    //   38: new 450	java/io/ByteArrayOutputStream
    //   41: dup
    //   42: invokespecial 451	java/io/ByteArrayOutputStream:<init>	()V
    //   45: astore_1
    //   46: new 453	java/io/DataOutputStream
    //   49: dup
    //   50: aload_1
    //   51: invokespecial 456	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   54: astore_2
    //   55: aload_2
    //   56: iload 4
    //   58: invokevirtual 459	java/io/DataOutputStream:writeInt	(I)V
    //   61: aload_0
    //   62: invokeinterface 463 1 0
    //   67: invokeinterface 469 1 0
    //   72: astore_0
    //   73: iconst_0
    //   74: istore 4
    //   76: aload_0
    //   77: invokeinterface 474 1 0
    //   82: ifeq +60 -> 142
    //   85: aload_0
    //   86: invokeinterface 478 1 0
    //   91: checkcast 480	java/util/Map$Entry
    //   94: astore_3
    //   95: aload_2
    //   96: aload_3
    //   97: invokeinterface 483 1 0
    //   102: checkcast 48	java/lang/String
    //   105: invokevirtual 486	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   108: aload_3
    //   109: invokeinterface 489 1 0
    //   114: astore_3
    //   115: aload_3
    //   116: instanceof 491
    //   119: ifeq +38 -> 157
    //   122: aload_2
    //   123: bipush 66
    //   125: invokevirtual 459	java/io/DataOutputStream:writeInt	(I)V
    //   128: aload_2
    //   129: aload_3
    //   130: checkcast 491	java/lang/Number
    //   133: invokevirtual 494	java/lang/Number:doubleValue	()D
    //   136: invokevirtual 498	java/io/DataOutputStream:writeDouble	(D)V
    //   139: goto +107 -> 246
    //   142: aload_1
    //   143: invokevirtual 502	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   146: astore_0
    //   147: aload_1
    //   148: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   151: aload_2
    //   152: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   155: aload_0
    //   156: areturn
    //   157: aload_3
    //   158: instanceof 48
    //   161: ifeq +33 -> 194
    //   164: aload_2
    //   165: bipush 88
    //   167: invokevirtual 459	java/io/DataOutputStream:writeInt	(I)V
    //   170: aload_2
    //   171: aload_3
    //   172: checkcast 48	java/lang/String
    //   175: invokevirtual 486	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   178: goto +68 -> 246
    //   181: astore_0
    //   182: aload_2
    //   183: astore_0
    //   184: aload_1
    //   185: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   188: aload_0
    //   189: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   192: aconst_null
    //   193: areturn
    //   194: aload_1
    //   195: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   198: aload_2
    //   199: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   202: aconst_null
    //   203: areturn
    //   204: goto -128 -> 76
    //   207: astore_0
    //   208: aconst_null
    //   209: astore_2
    //   210: aconst_null
    //   211: astore_1
    //   212: aload_1
    //   213: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   216: aload_2
    //   217: invokestatic 351	com/talkingdata/pingan/sdk/PAAgent:a	(Ljava/io/Closeable;)V
    //   220: aload_0
    //   221: athrow
    //   222: astore_0
    //   223: aconst_null
    //   224: astore_2
    //   225: goto -13 -> 212
    //   228: astore_0
    //   229: goto -17 -> 212
    //   232: astore_0
    //   233: aconst_null
    //   234: astore_0
    //   235: aconst_null
    //   236: astore_1
    //   237: goto -53 -> 184
    //   240: astore_0
    //   241: aconst_null
    //   242: astore_0
    //   243: goto -59 -> 184
    //   246: iload 4
    //   248: iconst_1
    //   249: iadd
    //   250: istore 4
    //   252: iload 4
    //   254: bipush 10
    //   256: if_icmpne -52 -> 204
    //   259: goto -117 -> 142
    //
    // Exception table:
    //   from	to	target	type
    //   55	73	181	java/lang/Exception
    //   76	139	181	java/lang/Exception
    //   142	147	181	java/lang/Exception
    //   157	178	181	java/lang/Exception
    //   38	46	207	finally
    //   46	55	222	finally
    //   55	73	228	finally
    //   76	139	228	finally
    //   142	147	228	finally
    //   157	178	228	finally
    //   38	46	232	java/lang/Exception
    //   46	55	240	java/lang/Exception
  }

  // ERROR //
  static long b(List paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokeinterface 275 1 0
    //   6: istore_3
    //   7: iload_3
    //   8: ifne +9 -> 17
    //   11: lconst_0
    //   12: lstore 4
    //   14: lload 4
    //   16: lreturn
    //   17: iload_3
    //   18: iconst_1
    //   19: isub
    //   20: istore_3
    //   21: iload_3
    //   22: iflt +99 -> 121
    //   25: new 83	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   32: astore_2
    //   33: aload_2
    //   34: ldc_w 504
    //   37: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: pop
    //   41: aconst_null
    //   42: astore_1
    //   43: getstatic 38	com/talkingdata/pingan/sdk/e:e	Landroid/database/sqlite/SQLiteDatabase;
    //   46: aload_2
    //   47: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: iconst_1
    //   51: anewarray 48	java/lang/String
    //   54: dup
    //   55: iconst_0
    //   56: aload_0
    //   57: iload_3
    //   58: invokeinterface 281 2 0
    //   63: checkcast 283	com/talkingdata/pingan/sdk/c
    //   66: getfield 285	com/talkingdata/pingan/sdk/c:a	Ljava/lang/String;
    //   69: aastore
    //   70: invokevirtual 289	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   73: astore_2
    //   74: aload_2
    //   75: invokeinterface 66 1 0
    //   80: ifeq +43 -> 123
    //   83: aload_2
    //   84: iconst_0
    //   85: invokeinterface 73 2 0
    //   90: lstore 6
    //   92: lload 6
    //   94: lconst_0
    //   95: lcmp
    //   96: ifeq +27 -> 123
    //   99: lload 6
    //   101: lstore 4
    //   103: aload_2
    //   104: ifnull -90 -> 14
    //   107: aload_2
    //   108: invokeinterface 76 1 0
    //   113: lload 6
    //   115: lreturn
    //   116: astore_0
    //   117: aload_0
    //   118: invokevirtual 239	java/lang/Exception:printStackTrace	()V
    //   121: lconst_0
    //   122: lreturn
    //   123: aload_2
    //   124: ifnull +30 -> 154
    //   127: aload_2
    //   128: invokeinterface 76 1 0
    //   133: goto +21 -> 154
    //   136: aload_1
    //   137: ifnull +9 -> 146
    //   140: aload_1
    //   141: invokeinterface 76 1 0
    //   146: aload_0
    //   147: athrow
    //   148: astore_0
    //   149: aload_2
    //   150: astore_1
    //   151: goto -15 -> 136
    //   154: iload_3
    //   155: iconst_1
    //   156: isub
    //   157: istore_3
    //   158: goto -137 -> 21
    //   161: astore_0
    //   162: goto -26 -> 136
    //
    // Exception table:
    //   from	to	target	type
    //   25	41	116	java/lang/Exception
    //   107	113	116	java/lang/Exception
    //   127	133	116	java/lang/Exception
    //   140	146	116	java/lang/Exception
    //   146	148	116	java/lang/Exception
    //   74	92	148	finally
    //   43	74	161	finally
  }

  // ERROR //
  static List b(String paramString, long paramLong)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 292	java/util/ArrayList
    //   6: dup
    //   7: invokespecial 293	java/util/ArrayList:<init>	()V
    //   10: astore 5
    //   12: aload 4
    //   14: astore_3
    //   15: new 83	java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   22: astore 6
    //   24: aload 4
    //   26: astore_3
    //   27: aload 6
    //   29: ldc_w 506
    //   32: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: pop
    //   36: aload 4
    //   38: astore_3
    //   39: getstatic 38	com/talkingdata/pingan/sdk/e:e	Landroid/database/sqlite/SQLiteDatabase;
    //   42: aload 6
    //   44: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   47: iconst_1
    //   48: anewarray 48	java/lang/String
    //   51: dup
    //   52: iconst_0
    //   53: aload_0
    //   54: aastore
    //   55: invokevirtual 289	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   58: astore_0
    //   59: aload_0
    //   60: astore_3
    //   61: aload_0
    //   62: invokeinterface 66 1 0
    //   67: ifeq +144 -> 211
    //   70: aload_0
    //   71: astore_3
    //   72: aload_0
    //   73: invokeinterface 69 1 0
    //   78: ifne +133 -> 211
    //   81: aload_0
    //   82: astore_3
    //   83: new 508	com/talkingdata/pingan/sdk/ab
    //   86: dup
    //   87: invokespecial 509	com/talkingdata/pingan/sdk/ab:<init>	()V
    //   90: astore 4
    //   92: aload_0
    //   93: astore_3
    //   94: aload 4
    //   96: aload_0
    //   97: iconst_0
    //   98: invokeinterface 224 2 0
    //   103: putfield 510	com/talkingdata/pingan/sdk/ab:c	I
    //   106: aload_0
    //   107: astore_3
    //   108: aload 4
    //   110: aload_0
    //   111: iconst_1
    //   112: invokeinterface 73 2 0
    //   117: putfield 512	com/talkingdata/pingan/sdk/ab:d	J
    //   120: aload_0
    //   121: astore_3
    //   122: aload 4
    //   124: aload_0
    //   125: iconst_2
    //   126: invokeinterface 302 2 0
    //   131: putfield 513	com/talkingdata/pingan/sdk/ab:a	Ljava/lang/String;
    //   134: aload_0
    //   135: astore_3
    //   136: aload 4
    //   138: aload_0
    //   139: iconst_3
    //   140: invokeinterface 302 2 0
    //   145: putfield 515	com/talkingdata/pingan/sdk/ab:b	Ljava/lang/String;
    //   148: aload_0
    //   149: astore_3
    //   150: aload 4
    //   152: aconst_null
    //   153: putfield 518	com/talkingdata/pingan/sdk/ab:e	Ljava/util/Map;
    //   156: aload_0
    //   157: astore_3
    //   158: aload 4
    //   160: aload_0
    //   161: iconst_4
    //   162: invokeinterface 217 2 0
    //   167: invokestatic 520	com/talkingdata/pingan/sdk/e:a	([B)Ljava/util/Map;
    //   170: putfield 518	com/talkingdata/pingan/sdk/ab:e	Ljava/util/Map;
    //   173: aload_0
    //   174: astore_3
    //   175: aload 5
    //   177: aload 4
    //   179: invokeinterface 312 2 0
    //   184: pop
    //   185: aload_0
    //   186: astore_3
    //   187: aload_0
    //   188: invokeinterface 238 1 0
    //   193: pop
    //   194: goto -124 -> 70
    //   197: astore_0
    //   198: aload_3
    //   199: ifnull +9 -> 208
    //   202: aload_3
    //   203: invokeinterface 76 1 0
    //   208: aload 5
    //   210: areturn
    //   211: aload_0
    //   212: ifnull -4 -> 208
    //   215: aload_0
    //   216: invokeinterface 76 1 0
    //   221: aload 5
    //   223: areturn
    //   224: astore_0
    //   225: aconst_null
    //   226: astore 4
    //   228: aload_0
    //   229: astore_3
    //   230: aload 4
    //   232: ifnull +10 -> 242
    //   235: aload 4
    //   237: invokeinterface 76 1 0
    //   242: aload_3
    //   243: athrow
    //   244: astore_3
    //   245: aload_0
    //   246: astore 4
    //   248: goto -18 -> 230
    //
    // Exception table:
    //   from	to	target	type
    //   15	24	197	java/lang/Exception
    //   27	36	197	java/lang/Exception
    //   39	59	197	java/lang/Exception
    //   61	70	197	java/lang/Exception
    //   72	81	197	java/lang/Exception
    //   83	92	197	java/lang/Exception
    //   94	106	197	java/lang/Exception
    //   108	120	197	java/lang/Exception
    //   122	134	197	java/lang/Exception
    //   136	148	197	java/lang/Exception
    //   150	156	197	java/lang/Exception
    //   158	173	197	java/lang/Exception
    //   175	185	197	java/lang/Exception
    //   187	194	197	java/lang/Exception
    //   15	24	224	finally
    //   27	36	224	finally
    //   39	59	224	finally
    //   61	70	244	finally
    //   72	81	244	finally
    //   83	92	244	finally
    //   94	106	244	finally
    //   108	120	244	finally
    //   122	134	244	finally
    //   136	148	244	finally
    //   150	156	244	finally
    //   158	173	244	finally
    //   175	185	244	finally
    //   187	194	244	finally
  }

  static void b()
  {
    try
    {
      f -= 1;
      f = Math.max(0, f);
      if ((f == 0) && (e != null))
      {
        e.close();
        e = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  static void b(long paramLong)
  {
    q.a(new String[] { "Delete Activity Less Than Id", "id:" + paramLong });
    e.delete("activity", "_id<=? AND duration != 0 ", new String[] { String.valueOf(paramLong) });
  }

  static void b(String paramString)
  {
    q.a(new String[] { "Delete Session By Session Id", "sessionId:" + paramString });
    e.delete("session", "session_id=?", new String[] { paramString });
  }

  static long c()
  {
    return DatabaseUtils.queryNumEntries(e, "session");
  }

  static void c(long paramLong)
  {
    q.a(new String[] { "Delete App Event Less Than Id", "id:" + paramLong });
    e.delete("app_event", "_id<=? ", new String[] { String.valueOf(paramLong) });
  }

  static void c(String paramString)
  {
    q.a(new String[] { "Delete Activity By SessionID", "sessionId:" + paramString });
    e.delete("activity", "session_id=? ", new String[] { paramString });
  }

  // ERROR //
  static List d()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: new 292	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 293	java/util/ArrayList:<init>	()V
    //   9: astore_2
    //   10: getstatic 38	com/talkingdata/pingan/sdk/e:e	Landroid/database/sqlite/SQLiteDatabase;
    //   13: ldc 194
    //   15: getstatic 555	com/talkingdata/pingan/sdk/e$a:h	[Ljava/lang/String;
    //   18: aconst_null
    //   19: aconst_null
    //   20: aconst_null
    //   21: aconst_null
    //   22: ldc 54
    //   24: ldc 23
    //   26: invokevirtual 558	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   29: astore_1
    //   30: aload_1
    //   31: astore_0
    //   32: aload_0
    //   33: invokeinterface 66 1 0
    //   38: ifeq +229 -> 267
    //   41: aload_0
    //   42: invokeinterface 69 1 0
    //   47: ifne +220 -> 267
    //   50: new 283	com/talkingdata/pingan/sdk/c
    //   53: dup
    //   54: invokespecial 559	com/talkingdata/pingan/sdk/c:<init>	()V
    //   57: astore_1
    //   58: aload_1
    //   59: aload_0
    //   60: iconst_1
    //   61: invokeinterface 302 2 0
    //   66: putfield 285	com/talkingdata/pingan/sdk/c:a	Ljava/lang/String;
    //   69: aload_1
    //   70: aload_0
    //   71: iconst_2
    //   72: invokeinterface 73 2 0
    //   77: putfield 560	com/talkingdata/pingan/sdk/c:b	J
    //   80: iconst_1
    //   81: anewarray 48	java/lang/String
    //   84: dup
    //   85: iconst_0
    //   86: new 83	java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   93: aload_1
    //   94: getfield 285	com/talkingdata/pingan/sdk/c:a	Ljava/lang/String;
    //   97: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: ldc_w 562
    //   103: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload_1
    //   107: getfield 560	com/talkingdata/pingan/sdk/c:b	J
    //   110: invokevirtual 93	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   113: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: aastore
    //   117: invokestatic 104	com/talkingdata/pingan/sdk/q:a	([Ljava/lang/String;)V
    //   120: aload_1
    //   121: aload_0
    //   122: iconst_3
    //   123: invokeinterface 224 2 0
    //   128: putfield 565	com/talkingdata/pingan/sdk/c:g	I
    //   131: aload_0
    //   132: iconst_4
    //   133: invokeinterface 224 2 0
    //   138: ifne +94 -> 232
    //   141: aload_1
    //   142: iconst_1
    //   143: putfield 566	com/talkingdata/pingan/sdk/c:c	I
    //   146: iconst_1
    //   147: aload_1
    //   148: getfield 566	com/talkingdata/pingan/sdk/c:c	I
    //   151: if_icmpne +38 -> 189
    //   154: aload_1
    //   155: aload_0
    //   156: iconst_5
    //   157: invokeinterface 224 2 0
    //   162: putfield 569	com/talkingdata/pingan/sdk/c:j	I
    //   165: aload_1
    //   166: getfield 569	com/talkingdata/pingan/sdk/c:j	I
    //   169: ifge +8 -> 177
    //   172: aload_1
    //   173: iconst_0
    //   174: putfield 569	com/talkingdata/pingan/sdk/c:j	I
    //   177: aload_1
    //   178: aload_1
    //   179: getfield 569	com/talkingdata/pingan/sdk/c:j	I
    //   182: sipush 1000
    //   185: idiv
    //   186: putfield 565	com/talkingdata/pingan/sdk/c:g	I
    //   189: aload_1
    //   190: aload_0
    //   191: bipush 6
    //   193: invokeinterface 224 2 0
    //   198: putfield 572	com/talkingdata/pingan/sdk/c:k	I
    //   201: aload_2
    //   202: aload_1
    //   203: invokeinterface 312 2 0
    //   208: pop
    //   209: aload_0
    //   210: invokeinterface 238 1 0
    //   215: pop
    //   216: goto -175 -> 41
    //   219: astore_1
    //   220: aload_0
    //   221: ifnull +9 -> 230
    //   224: aload_0
    //   225: invokeinterface 76 1 0
    //   230: aload_2
    //   231: areturn
    //   232: aload_1
    //   233: getfield 565	com/talkingdata/pingan/sdk/c:g	I
    //   236: ifeq +26 -> 262
    //   239: iconst_3
    //   240: istore_3
    //   241: aload_1
    //   242: iload_3
    //   243: putfield 566	com/talkingdata/pingan/sdk/c:c	I
    //   246: goto -100 -> 146
    //   249: astore_1
    //   250: aload_0
    //   251: ifnull +9 -> 260
    //   254: aload_0
    //   255: invokeinterface 76 1 0
    //   260: aload_1
    //   261: athrow
    //   262: iconst_2
    //   263: istore_3
    //   264: goto -23 -> 241
    //   267: aload_0
    //   268: ifnull -38 -> 230
    //   271: aload_0
    //   272: invokeinterface 76 1 0
    //   277: aload_2
    //   278: areturn
    //   279: astore_1
    //   280: goto -30 -> 250
    //   283: astore_0
    //   284: aconst_null
    //   285: astore_0
    //   286: goto -66 -> 220
    //
    // Exception table:
    //   from	to	target	type
    //   32	41	219	java/lang/Exception
    //   41	146	219	java/lang/Exception
    //   146	177	219	java/lang/Exception
    //   177	189	219	java/lang/Exception
    //   189	216	219	java/lang/Exception
    //   232	239	219	java/lang/Exception
    //   241	246	219	java/lang/Exception
    //   32	41	249	finally
    //   41	146	249	finally
    //   146	177	249	finally
    //   177	189	249	finally
    //   189	216	249	finally
    //   232	239	249	finally
    //   241	246	249	finally
    //   10	30	279	finally
    //   10	30	283	java/lang/Exception
  }

  static void d(long paramLong)
  {
    q.a(new String[] { "Delete Error Less Than Id", "id:" + paramLong });
    e.delete("error_report", "_id<=?", new String[] { String.valueOf(paramLong) });
  }

  static void d(String paramString)
  {
    q.a(new String[] { "Delete App Event By SessionId", "sessionId:" + paramString });
    e.delete("app_event", "session_id=? ", new String[] { paramString });
  }

  // ERROR //
  static long e(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 83	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   9: astore_2
    //   10: aload_2
    //   11: ldc_w 581
    //   14: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: pop
    //   18: aload_2
    //   19: aload_0
    //   20: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: getstatic 38	com/talkingdata/pingan/sdk/e:e	Landroid/database/sqlite/SQLiteDatabase;
    //   27: aload_2
    //   28: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   31: aconst_null
    //   32: invokevirtual 289	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   35: astore_0
    //   36: aload_0
    //   37: invokeinterface 66 1 0
    //   42: ifeq +32 -> 74
    //   45: aload_0
    //   46: invokeinterface 69 1 0
    //   51: ifne +23 -> 74
    //   54: aload_0
    //   55: iconst_0
    //   56: invokeinterface 73 2 0
    //   61: lstore_3
    //   62: aload_0
    //   63: ifnull +9 -> 72
    //   66: aload_0
    //   67: invokeinterface 76 1 0
    //   72: lload_3
    //   73: lreturn
    //   74: aload_0
    //   75: ifnull +9 -> 84
    //   78: aload_0
    //   79: invokeinterface 76 1 0
    //   84: lconst_0
    //   85: lreturn
    //   86: astore_0
    //   87: aload_1
    //   88: astore_0
    //   89: aload_0
    //   90: ifnull -6 -> 84
    //   93: aload_0
    //   94: invokeinterface 76 1 0
    //   99: goto -15 -> 84
    //   102: astore_0
    //   103: aconst_null
    //   104: astore_2
    //   105: aload_0
    //   106: astore_1
    //   107: aload_2
    //   108: ifnull +9 -> 117
    //   111: aload_2
    //   112: invokeinterface 76 1 0
    //   117: aload_1
    //   118: athrow
    //   119: astore_1
    //   120: aload_0
    //   121: astore_2
    //   122: goto -15 -> 107
    //   125: astore_1
    //   126: goto -37 -> 89
    //
    // Exception table:
    //   from	to	target	type
    //   2	36	86	java/lang/Exception
    //   2	36	102	finally
    //   36	62	119	finally
    //   36	62	125	java/lang/Exception
  }

  static List e(long paramLong)
  {
    ArrayList localArrayList = new ArrayList();
    String str = null;
    Cursor localCursor2 = null;
    Cursor localCursor1 = localCursor2;
    Object localObject1 = str;
    try
    {
      Object localObject3 = new StringBuilder();
      localCursor1 = localCursor2;
      localObject1 = str;
      ((StringBuilder)localObject3).append("SELECT error_time,message,repeat, shorthashcode from error_report where _id<=?");
      localCursor1 = localCursor2;
      localObject1 = str;
      localCursor2 = e.rawQuery(((StringBuilder)localObject3).toString(), new String[] { String.valueOf(paramLong) });
      localCursor1 = localCursor2;
      localObject1 = localCursor2;
      if (localCursor2.moveToFirst())
      {
        localCursor1 = localCursor2;
        localObject1 = localCursor2;
        if (PAAgent.c() != null)
        {
          localCursor1 = localCursor2;
          localObject1 = localCursor2;
          str = String.valueOf(i.c(PAAgent.c()));
          while (true)
          {
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            if (localCursor2.isAfterLast())
              break;
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            localObject3 = new ac();
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            ((ac)localObject3).a = 3;
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            al localal = new al();
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            localal.a = localCursor2.getLong(0);
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            localal.d = localCursor2.getBlob(1);
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            localal.b = localCursor2.getInt(2);
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            localal.e = localCursor2.getString(3);
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            localal.c = str;
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            ((ac)localObject3).d = localal;
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            localArrayList.add(localObject3);
            localCursor1 = localCursor2;
            localObject1 = localCursor2;
            localCursor2.moveToNext();
          }
        }
      }
    }
    catch (Exception localException)
    {
      localObject1 = localCursor1;
      localException.printStackTrace();
      if (localCursor1 != null)
        localCursor1.close();
      do
      {
        return localArrayList;
        str = "";
        break;
      }
      while (localException == null);
      localException.close();
      return localArrayList;
    }
    finally
    {
      if (localObject1 != null)
        ((Cursor)localObject1).close();
    }
  }

  static final class a
    implements BaseColumns
  {
    public static final String a = "session_id";
    public static final String b = "start_time";
    public static final String c = "duration";
    public static final String d = "is_launch";
    public static final String e = "interval";
    public static final String f = "is_connected";
    public static final String g = "session";
    public static final String[] h = { "_id", "session_id", "start_time", "duration", "is_launch", "interval", "is_connected" };

    public static final void a(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE session (_id INTEGER PRIMARY KEY autoincrement,session_id TEXT,start_time LONG,duration INTEGER,is_launch INTEGER,interval LONG, is_connected INTEGER)");
    }

    public static final void b(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS session");
    }
  }

  static final class b
    implements BaseColumns
  {
    public static final String a = "name";
    public static final String b = "start_time";
    public static final String c = "duration";
    public static final String d = "session_id";
    public static final String e = "refer";
    public static final String f = "realtime";
    public static final String g = "activity";
    public static final String[] h = { "_id", "name", "start_time", "duration", "session_id", "refer", "realtime" };

    public static final void a(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE activity (_id INTEGER PRIMARY KEY autoincrement,name TEXT,start_time LONG,duration INTEGER,session_id TEXT,refer TEXT,realtime LONG)");
    }

    public static final void b(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS activity");
    }
  }

  static final class c
    implements BaseColumns
  {
    public static final String a = "error_time";
    public static final String b = "message";
    public static final String c = "repeat";
    public static final String d = "shorthashcode";
    public static final String e = "error_report";
    public static final String[] f = { "_id", "error_time", "message", "repeat", "shorthashcode" };

    public static final void a(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE error_report (_id INTEGER PRIMARY KEY autoincrement,error_time LONG,message BLOB,repeat INTERGER,shorthashcode TEXT)");
    }

    public static final void b(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS error_report");
    }
  }

  static final class d
    implements BaseColumns
  {
    public static final String a = "event_id";
    public static final String b = "event_label";
    public static final String c = "session_id";
    public static final String d = "occurtime";
    public static final String e = "paramap";
    public static final String f = "app_event";
    public static final String[] g = { "_id", "event_id", "event_label", "session_id", "occurtime", "paramap" };

    public static final void a(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE app_event (_id INTEGER PRIMARY KEY autoincrement,event_id TEXT,event_label TEXT,session_id TEXT,occurtime LONG,paramap BLOB)");
    }

    public static final void b(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS app_event");
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.e
 * JD-Core Version:    0.6.2
 */