package com.tencent.open.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public class b
{
  private Context a;
  private SQLiteDatabase b;
  private a c;
  private int d;

  public b(Context paramContext)
  {
    this.a = paramContext;
    this.c = new a(paramContext, "sdk_cgi_report.db", null, 2);
    this.d = c().size();
  }

  public int a(ArrayList<d> paramArrayList)
  {
    int i;
    try
    {
      Log.i("cgi_report_debug", "ReportDataModal backupOldItems count = " + paramArrayList.size());
      paramArrayList = paramArrayList.iterator();
      i = 0;
      while (true)
        if (paramArrayList.hasNext())
        {
          d locald = (d)paramArrayList.next();
          ContentValues localContentValues = new ContentValues();
          localContentValues.put("apn", locald.a());
          localContentValues.put("frequency", locald.b());
          localContentValues.put("commandid", locald.c());
          localContentValues.put("resultcode", locald.d());
          localContentValues.put("tmcost", locald.e());
          localContentValues.put("reqsize", locald.f());
          localContentValues.put("rspsize", locald.g());
          localContentValues.put("deviceinfo", locald.i());
          localContentValues.put("detail", locald.h());
          try
          {
            this.b = this.c.getWritableDatabase();
            this.b.insertOrThrow("olddata", null, localContentValues);
            this.b.close();
            i += 1;
          }
          catch (Exception localException)
          {
            while (true)
              localException.printStackTrace();
          }
        }
    }
    finally
    {
    }
    Log.i("cgi_report_debug", "ReportDataModal backupOldItems succ_count = " + i);
    return i;
  }

  // ERROR //
  public boolean a()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: ldc 45
    //   6: ldc 156
    //   8: invokestatic 67	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   11: pop
    //   12: aload_0
    //   13: aload_0
    //   14: getfield 28	com/tencent/open/a/b:c	Lcom/tencent/open/a/b$a;
    //   17: invokevirtual 134	com/tencent/open/a/b$a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   20: putfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   23: aload_0
    //   24: getfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   27: ldc 158
    //   29: invokevirtual 162	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   32: aload_0
    //   33: getfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   36: invokevirtual 147	android/database/sqlite/SQLiteDatabase:close	()V
    //   39: ldc 45
    //   41: ldc 164
    //   43: invokestatic 67	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   46: pop
    //   47: iconst_1
    //   48: istore_2
    //   49: aload_0
    //   50: monitorexit
    //   51: iload_2
    //   52: ireturn
    //   53: astore_1
    //   54: aload_1
    //   55: invokevirtual 150	java/lang/Exception:printStackTrace	()V
    //   58: goto -9 -> 49
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    //   66: astore_1
    //   67: aload_1
    //   68: invokevirtual 150	java/lang/Exception:printStackTrace	()V
    //   71: aload_0
    //   72: getfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   75: invokevirtual 147	android/database/sqlite/SQLiteDatabase:close	()V
    //   78: goto -29 -> 49
    //
    // Exception table:
    //   from	to	target	type
    //   12	23	53	java/lang/Exception
    //   4	12	61	finally
    //   12	23	61	finally
    //   23	32	61	finally
    //   32	47	61	finally
    //   54	58	61	finally
    //   67	78	61	finally
    //   23	32	66	java/lang/Exception
  }

  public boolean a(String paramString1, String paramString2, String paramString3, int paramInt, long paramLong1, long paramLong2, long paramLong3, String paramString4)
  {
    String str = paramString3;
    try
    {
      if (paramString3.contains("?"))
        str = paramString3.split("\\?")[0];
      Log.i("cgi_report_debug", "ReportDataModal addNewItem apn=" + paramString1 + ",frequency=" + paramString2 + ",commandid=" + str + ",resultCode=" + paramInt + ",costTime=" + paramLong1 + ",reqSzie=" + paramLong2 + ",rspSize=" + paramLong3);
      paramString3 = new ContentValues();
      paramString3.put("apn", paramString1 + "");
      paramString3.put("frequency", paramString2 + "");
      paramString3.put("commandid", str + "");
      paramString3.put("resultcode", paramInt + "");
      paramString3.put("tmcost", paramLong1 + "");
      paramString3.put("reqsize", paramLong2 + "");
      paramString3.put("rspsize", paramLong3 + "");
      paramString2 = new StringBuilder();
      paramString2.append("network=").append(paramString1).append('&');
      paramString1 = paramString2.append("sdcard=");
      if (Environment.getExternalStorageState().equals("mounted"))
        paramInt = 1;
      while (true)
      {
        paramString1.append(paramInt).append('&');
        paramString2.append("wifi=").append(a.c(this.a));
        paramString3.put("deviceinfo", paramString2.toString());
        paramString3.put("detail", paramString4);
        try
        {
          this.b = this.c.getWritableDatabase();
          this.b.insertOrThrow("newdata", null, paramString3);
          this.b.close();
          Log.i("cgi_report_debug", "ReportDataModal addNewItem success");
          this.d += 1;
          bool = true;
          return bool;
          paramInt = 0;
        }
        catch (Exception paramString1)
        {
          while (true)
          {
            Log.e("cgi_report_debug", "ReportDataModal addNewItem failed");
            paramString1.printStackTrace();
            boolean bool = false;
          }
        }
      }
    }
    finally
    {
    }
    throw paramString1;
  }

  // ERROR //
  public boolean b()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: ldc 45
    //   6: ldc 233
    //   8: invokestatic 67	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   11: pop
    //   12: aload_0
    //   13: aload_0
    //   14: getfield 28	com/tencent/open/a/b:c	Lcom/tencent/open/a/b$a;
    //   17: invokevirtual 134	com/tencent/open/a/b$a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   20: putfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   23: aload_0
    //   24: getfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   27: ldc 235
    //   29: invokevirtual 162	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   32: aload_0
    //   33: iconst_0
    //   34: putfield 39	com/tencent/open/a/b:d	I
    //   37: aload_0
    //   38: getfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   41: invokevirtual 147	android/database/sqlite/SQLiteDatabase:close	()V
    //   44: ldc 45
    //   46: ldc 233
    //   48: invokestatic 67	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   51: pop
    //   52: iconst_1
    //   53: istore_2
    //   54: aload_0
    //   55: monitorexit
    //   56: iload_2
    //   57: ireturn
    //   58: astore_1
    //   59: aload_1
    //   60: invokevirtual 150	java/lang/Exception:printStackTrace	()V
    //   63: goto -9 -> 54
    //   66: astore_1
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_1
    //   70: athrow
    //   71: astore_1
    //   72: aload_1
    //   73: invokevirtual 150	java/lang/Exception:printStackTrace	()V
    //   76: aload_0
    //   77: getfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   80: invokevirtual 147	android/database/sqlite/SQLiteDatabase:close	()V
    //   83: goto -29 -> 54
    //
    // Exception table:
    //   from	to	target	type
    //   12	23	58	java/lang/Exception
    //   4	12	66	finally
    //   12	23	66	finally
    //   23	32	66	finally
    //   32	52	66	finally
    //   59	63	66	finally
    //   72	83	66	finally
    //   23	32	71	java/lang/Exception
  }

  // ERROR //
  public ArrayList<d> c()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 45
    //   4: ldc 237
    //   6: invokestatic 67	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   9: pop
    //   10: new 33	java/util/ArrayList
    //   13: dup
    //   14: invokespecial 238	java/util/ArrayList:<init>	()V
    //   17: astore_1
    //   18: aload_0
    //   19: aload_0
    //   20: getfield 28	com/tencent/open/a/b:c	Lcom/tencent/open/a/b$a;
    //   23: invokevirtual 241	com/tencent/open/a/b$a:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   26: putfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   29: aload_0
    //   30: getfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   33: ldc 243
    //   35: iconst_0
    //   36: anewarray 169	java/lang/String
    //   39: invokevirtual 247	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   42: astore_2
    //   43: aload_2
    //   44: invokeinterface 252 1 0
    //   49: pop
    //   50: aload_2
    //   51: invokeinterface 255 1 0
    //   56: ifne +195 -> 251
    //   59: aload_2
    //   60: aload_2
    //   61: ldc 88
    //   63: invokeinterface 259 2 0
    //   68: invokeinterface 263 2 0
    //   73: astore_3
    //   74: aload_2
    //   75: aload_2
    //   76: ldc 96
    //   78: invokeinterface 259 2 0
    //   83: invokeinterface 263 2 0
    //   88: astore 4
    //   90: aload_2
    //   91: aload_2
    //   92: ldc 100
    //   94: invokeinterface 259 2 0
    //   99: invokeinterface 263 2 0
    //   104: astore 5
    //   106: aload_2
    //   107: aload_2
    //   108: ldc 104
    //   110: invokeinterface 259 2 0
    //   115: invokeinterface 263 2 0
    //   120: astore 6
    //   122: aload_2
    //   123: aload_2
    //   124: ldc 108
    //   126: invokeinterface 259 2 0
    //   131: invokeinterface 263 2 0
    //   136: astore 7
    //   138: aload_2
    //   139: aload_2
    //   140: ldc 113
    //   142: invokeinterface 259 2 0
    //   147: invokeinterface 263 2 0
    //   152: astore 8
    //   154: aload_2
    //   155: aload_2
    //   156: ldc 118
    //   158: invokeinterface 259 2 0
    //   163: invokeinterface 263 2 0
    //   168: astore 9
    //   170: aload_2
    //   171: aload_2
    //   172: ldc 127
    //   174: invokeinterface 259 2 0
    //   179: invokeinterface 263 2 0
    //   184: astore 10
    //   186: aload_1
    //   187: new 83	com/tencent/open/a/d
    //   190: dup
    //   191: aload_3
    //   192: aload 4
    //   194: aload 5
    //   196: aload 6
    //   198: aload 7
    //   200: aload 8
    //   202: aload 9
    //   204: aload_2
    //   205: aload_2
    //   206: ldc 123
    //   208: invokeinterface 259 2 0
    //   213: invokeinterface 263 2 0
    //   218: aload 10
    //   220: invokespecial 266	com/tencent/open/a/d:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   223: invokevirtual 269	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   226: pop
    //   227: aload_2
    //   228: invokeinterface 272 1 0
    //   233: pop
    //   234: goto -184 -> 50
    //   237: astore_1
    //   238: aload_0
    //   239: monitorexit
    //   240: aload_1
    //   241: athrow
    //   242: astore_2
    //   243: aload_2
    //   244: invokevirtual 150	java/lang/Exception:printStackTrace	()V
    //   247: aload_0
    //   248: monitorexit
    //   249: aload_1
    //   250: areturn
    //   251: aload_2
    //   252: invokeinterface 273 1 0
    //   257: aload_0
    //   258: getfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   261: invokevirtual 147	android/database/sqlite/SQLiteDatabase:close	()V
    //   264: ldc 45
    //   266: new 47	java/lang/StringBuilder
    //   269: dup
    //   270: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   273: ldc_w 275
    //   276: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   279: aload_1
    //   280: invokevirtual 37	java/util/ArrayList:size	()I
    //   283: invokevirtual 57	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   286: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   289: invokestatic 67	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   292: pop
    //   293: goto -46 -> 247
    //
    // Exception table:
    //   from	to	target	type
    //   2	18	237	finally
    //   18	29	237	finally
    //   29	50	237	finally
    //   50	234	237	finally
    //   243	247	237	finally
    //   251	293	237	finally
    //   18	29	242	java/lang/Exception
  }

  // ERROR //
  public ArrayList<d> d()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 45
    //   4: ldc_w 278
    //   7: invokestatic 67	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   10: pop
    //   11: new 33	java/util/ArrayList
    //   14: dup
    //   15: invokespecial 238	java/util/ArrayList:<init>	()V
    //   18: astore_1
    //   19: aload_0
    //   20: aload_0
    //   21: getfield 28	com/tencent/open/a/b:c	Lcom/tencent/open/a/b$a;
    //   24: invokevirtual 241	com/tencent/open/a/b$a:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   27: putfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   30: aload_0
    //   31: getfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   34: ldc_w 280
    //   37: iconst_0
    //   38: anewarray 169	java/lang/String
    //   41: invokevirtual 247	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   44: astore_2
    //   45: aload_2
    //   46: invokeinterface 252 1 0
    //   51: pop
    //   52: aload_2
    //   53: invokeinterface 255 1 0
    //   58: ifne +195 -> 253
    //   61: aload_2
    //   62: aload_2
    //   63: ldc 88
    //   65: invokeinterface 259 2 0
    //   70: invokeinterface 263 2 0
    //   75: astore_3
    //   76: aload_2
    //   77: aload_2
    //   78: ldc 96
    //   80: invokeinterface 259 2 0
    //   85: invokeinterface 263 2 0
    //   90: astore 4
    //   92: aload_2
    //   93: aload_2
    //   94: ldc 100
    //   96: invokeinterface 259 2 0
    //   101: invokeinterface 263 2 0
    //   106: astore 5
    //   108: aload_2
    //   109: aload_2
    //   110: ldc 104
    //   112: invokeinterface 259 2 0
    //   117: invokeinterface 263 2 0
    //   122: astore 6
    //   124: aload_2
    //   125: aload_2
    //   126: ldc 108
    //   128: invokeinterface 259 2 0
    //   133: invokeinterface 263 2 0
    //   138: astore 7
    //   140: aload_2
    //   141: aload_2
    //   142: ldc 113
    //   144: invokeinterface 259 2 0
    //   149: invokeinterface 263 2 0
    //   154: astore 8
    //   156: aload_2
    //   157: aload_2
    //   158: ldc 118
    //   160: invokeinterface 259 2 0
    //   165: invokeinterface 263 2 0
    //   170: astore 9
    //   172: aload_2
    //   173: aload_2
    //   174: ldc 127
    //   176: invokeinterface 259 2 0
    //   181: invokeinterface 263 2 0
    //   186: astore 10
    //   188: aload_1
    //   189: new 83	com/tencent/open/a/d
    //   192: dup
    //   193: aload_3
    //   194: aload 4
    //   196: aload 5
    //   198: aload 6
    //   200: aload 7
    //   202: aload 8
    //   204: aload 9
    //   206: aload_2
    //   207: aload_2
    //   208: ldc 123
    //   210: invokeinterface 259 2 0
    //   215: invokeinterface 263 2 0
    //   220: aload 10
    //   222: invokespecial 266	com/tencent/open/a/d:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   225: invokevirtual 269	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   228: pop
    //   229: aload_2
    //   230: invokeinterface 272 1 0
    //   235: pop
    //   236: goto -184 -> 52
    //   239: astore_1
    //   240: aload_0
    //   241: monitorexit
    //   242: aload_1
    //   243: athrow
    //   244: astore_2
    //   245: aload_2
    //   246: invokevirtual 150	java/lang/Exception:printStackTrace	()V
    //   249: aload_0
    //   250: monitorexit
    //   251: aload_1
    //   252: areturn
    //   253: aload_2
    //   254: invokeinterface 273 1 0
    //   259: aload_0
    //   260: getfield 136	com/tencent/open/a/b:b	Landroid/database/sqlite/SQLiteDatabase;
    //   263: invokevirtual 147	android/database/sqlite/SQLiteDatabase:close	()V
    //   266: ldc 45
    //   268: new 47	java/lang/StringBuilder
    //   271: dup
    //   272: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   275: ldc_w 282
    //   278: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   281: aload_1
    //   282: invokevirtual 37	java/util/ArrayList:size	()I
    //   285: invokevirtual 57	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   288: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   291: invokestatic 67	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   294: pop
    //   295: goto -46 -> 249
    //
    // Exception table:
    //   from	to	target	type
    //   2	19	239	finally
    //   19	30	239	finally
    //   30	52	239	finally
    //   52	236	239	finally
    //   245	249	239	finally
    //   253	295	239	finally
    //   19	30	244	java/lang/Exception
  }

  public int e()
  {
    Log.i("cgi_report_debug", "ReportDataModal getTotalCount count = " + this.d);
    return this.d;
  }

  private class a extends SQLiteOpenHelper
  {
    public a(Context paramString, String paramCursorFactory, SQLiteDatabase.CursorFactory paramInt, int arg5)
    {
      super(paramCursorFactory, paramInt, i);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      try
      {
        Log.i("cgi_report_debug", "ReportDataModal onCreate sql1 = create table if not exists newdata(id integer primary key,apn text,frequency text,commandid text,resultcode text,tmcost text,reqsize text,rspsize text,deviceinfo text,detail text)");
        paramSQLiteDatabase.execSQL("create table if not exists newdata(id integer primary key,apn text,frequency text,commandid text,resultcode text,tmcost text,reqsize text,rspsize text,deviceinfo text,detail text)");
        Log.i("cgi_report_debug", "ReportDataModal onCreate sql2 = create table if not exists olddata(id integer primary key,apn text,frequency text,commandid text,resultcode text,tmcost text,reqsize text,rspsize text,deviceinfo text,detail text)");
        paramSQLiteDatabase.execSQL("create table if not exists olddata(id integer primary key,apn text,frequency text,commandid text,resultcode text,tmcost text,reqsize text,rspsize text,deviceinfo text,detail text)");
        return;
      }
      catch (Exception paramSQLiteDatabase)
      {
        Log.e("cgi_report_debug", "ReportDataModal onCreate failed");
        paramSQLiteDatabase.printStackTrace();
      }
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      Log.i("cgi_report_debug", "ReportDataModal onUpgrade oldVersion=" + paramInt1 + "  newVersion=" + paramInt2 + "");
      if (paramInt1 != paramInt2);
      try
      {
        paramSQLiteDatabase.execSQL("drop table if exists newdata");
        paramSQLiteDatabase.execSQL("drop table if exists olddata");
        onCreate(paramSQLiteDatabase);
        Log.i("cgi_report_debug", "ReportDataModal onUpgrade success");
        return;
      }
      catch (Exception paramSQLiteDatabase)
      {
        Log.e("cgi_report_debug", "ReportDataModal onUpgrade failed");
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.open.a.b
 * JD-Core Version:    0.6.2
 */