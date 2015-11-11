package com.miaozhen.mzmonitor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

class b
{
  private static b b = null;
  private Context a;

  private b(Context paramContext)
  {
    this.a = paramContext;
  }

  public static b a(Context paramContext)
  {
    try
    {
      if (b == null)
        b = new b(paramContext.getApplicationContext());
      paramContext = b;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  private void a(a parama)
  {
    a locala = new a(this.a, "mzmonitor", null, 6);
    locala.getWritableDatabase().delete("mzcaches", "cacheId = ? AND url = ?", new String[] { parama.e(), parama.a() });
    locala.close();
  }

  private int b()
  {
    int j = 0;
    int i = j;
    try
    {
      a locala = new a(this.a, "mzmonitor", null, 6);
      i = j;
      Cursor localCursor = locala.getReadableDatabase().rawQuery("select count(*) from mzcaches", null);
      i = j;
      localCursor.moveToFirst();
      i = j;
      j = localCursor.getInt(0);
      i = j;
      localCursor.close();
      i = j;
      locala.close();
      return j;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return i;
  }

  private boolean b(a parama)
  {
    boolean bool = false;
    a locala = new a(this.a, "mzmonitor", null, 6);
    parama = locala.getReadableDatabase().rawQuery("SELECT * FROM mzcaches WHERE cacheId = ? and url = ?", new String[] { parama.e(), parama.a() });
    if (parama.moveToNext())
      bool = true;
    parama.close();
    locala.close();
    return bool;
  }

  public final List<a> a()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      a locala = new a(this.a, "mzmonitor", null, 6);
      Cursor localCursor = locala.getReadableDatabase().query("mzcaches", new String[] { "cacheId", "url", "timestamp", "times" }, null, null, null, null, null);
      if (localCursor.getCount() > 0);
      while (true)
      {
        if (!localCursor.moveToNext())
        {
          localCursor.close();
          locala.close();
          return localArrayList;
        }
        a locala1 = new a();
        locala1.e(localCursor.getString(localCursor.getColumnIndex("cacheId")));
        locala1.a(localCursor.getString(localCursor.getColumnIndex("url")));
        locala1.a(localCursor.getLong(localCursor.getColumnIndex("timestamp")));
        locala1.a(localCursor.getShort(localCursor.getColumnIndex("times")));
        localArrayList.add(locala1);
      }
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  // ERROR //
  public final void a(a parama, boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 7
    //   3: iconst_1
    //   4: istore 6
    //   6: aload_0
    //   7: monitorenter
    //   8: iload_2
    //   9: ifeq +19 -> 28
    //   12: aload_0
    //   13: aload_1
    //   14: invokespecial 163	com/miaozhen/mzmonitor/b:b	(Lcom/miaozhen/mzmonitor/a;)Z
    //   17: ifeq +8 -> 25
    //   20: aload_0
    //   21: aload_1
    //   22: invokespecial 165	com/miaozhen/mzmonitor/b:a	(Lcom/miaozhen/mzmonitor/a;)V
    //   25: aload_0
    //   26: monitorexit
    //   27: return
    //   28: aload_0
    //   29: aload_1
    //   30: invokespecial 163	com/miaozhen/mzmonitor/b:b	(Lcom/miaozhen/mzmonitor/a;)Z
    //   33: ifeq +155 -> 188
    //   36: aload_1
    //   37: invokevirtual 168	com/miaozhen/mzmonitor/a:h	()I
    //   40: aload_0
    //   41: getfield 21	com/miaozhen/mzmonitor/b:a	Landroid/content/Context;
    //   44: invokestatic 173	com/miaozhen/mzmonitor/j:b	(Landroid/content/Context;)I
    //   47: if_icmplt +16 -> 63
    //   50: iload 6
    //   52: ifeq +45 -> 97
    //   55: aload_0
    //   56: aload_1
    //   57: invokespecial 165	com/miaozhen/mzmonitor/b:a	(Lcom/miaozhen/mzmonitor/a;)V
    //   60: goto -35 -> 25
    //   63: aload_1
    //   64: invokevirtual 177	com/miaozhen/mzmonitor/a:g	()J
    //   67: lstore 8
    //   69: aload_0
    //   70: getfield 21	com/miaozhen/mzmonitor/b:a	Landroid/content/Context;
    //   73: invokestatic 179	com/miaozhen/mzmonitor/j:e	(Landroid/content/Context;)I
    //   76: i2l
    //   77: lstore 10
    //   79: invokestatic 183	com/miaozhen/mzmonitor/c$a:a	()J
    //   82: lload 8
    //   84: lsub
    //   85: lload 10
    //   87: lcmp
    //   88: ifgt -38 -> 50
    //   91: iconst_0
    //   92: istore 6
    //   94: goto -44 -> 50
    //   97: new 6	com/miaozhen/mzmonitor/b$a
    //   100: dup
    //   101: aload_0
    //   102: aload_0
    //   103: getfield 21	com/miaozhen/mzmonitor/b:a	Landroid/content/Context;
    //   106: ldc 33
    //   108: aconst_null
    //   109: bipush 6
    //   111: invokespecial 36	com/miaozhen/mzmonitor/b$a:<init>	(Lcom/miaozhen/mzmonitor/b;Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V
    //   114: astore_3
    //   115: aload_3
    //   116: invokevirtual 40	com/miaozhen/mzmonitor/b$a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   119: astore 4
    //   121: aload_1
    //   122: aload_1
    //   123: invokevirtual 168	com/miaozhen/mzmonitor/a:h	()I
    //   126: iconst_1
    //   127: iadd
    //   128: invokevirtual 152	com/miaozhen/mzmonitor/a:a	(I)V
    //   131: aload 4
    //   133: ldc 42
    //   135: aload_1
    //   136: invokevirtual 187	com/miaozhen/mzmonitor/a:j	()Landroid/content/ContentValues;
    //   139: ldc 44
    //   141: iconst_2
    //   142: anewarray 46	java/lang/String
    //   145: dup
    //   146: iconst_0
    //   147: new 48	java/lang/StringBuilder
    //   150: dup
    //   151: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   154: aload_1
    //   155: invokevirtual 55	com/miaozhen/mzmonitor/a:e	()Ljava/lang/String;
    //   158: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   164: aastore
    //   165: dup
    //   166: iconst_1
    //   167: aload_1
    //   168: invokevirtual 64	com/miaozhen/mzmonitor/a:a	()Ljava/lang/String;
    //   171: aastore
    //   172: invokevirtual 191	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   175: pop
    //   176: aload_3
    //   177: invokevirtual 73	com/miaozhen/mzmonitor/b$a:close	()V
    //   180: goto -155 -> 25
    //   183: astore_1
    //   184: aload_0
    //   185: monitorexit
    //   186: aload_1
    //   187: athrow
    //   188: aload_0
    //   189: invokespecial 193	com/miaozhen/mzmonitor/b:b	()I
    //   192: aload_0
    //   193: getfield 21	com/miaozhen/mzmonitor/b:a	Landroid/content/Context;
    //   196: invokestatic 195	com/miaozhen/mzmonitor/j:a	(Landroid/content/Context;)I
    //   199: if_icmplt +201 -> 400
    //   202: iload 7
    //   204: istore 6
    //   206: iload 6
    //   208: ifeq +120 -> 328
    //   211: new 6	com/miaozhen/mzmonitor/b$a
    //   214: dup
    //   215: aload_0
    //   216: aload_0
    //   217: getfield 21	com/miaozhen/mzmonitor/b:a	Landroid/content/Context;
    //   220: ldc 33
    //   222: aconst_null
    //   223: bipush 6
    //   225: invokespecial 36	com/miaozhen/mzmonitor/b$a:<init>	(Lcom/miaozhen/mzmonitor/b;Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V
    //   228: astore_3
    //   229: aload_3
    //   230: invokevirtual 79	com/miaozhen/mzmonitor/b$a:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   233: astore 4
    //   235: aload 4
    //   237: ldc 197
    //   239: aconst_null
    //   240: invokevirtual 85	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   243: astore 5
    //   245: aload 5
    //   247: invokeinterface 105 1 0
    //   252: ifeq +65 -> 317
    //   255: aload 4
    //   257: ldc 199
    //   259: iconst_2
    //   260: anewarray 46	java/lang/String
    //   263: dup
    //   264: iconst_0
    //   265: new 48	java/lang/StringBuilder
    //   268: dup
    //   269: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   272: aload 5
    //   274: aload 5
    //   276: ldc 111
    //   278: invokeinterface 129 2 0
    //   283: invokeinterface 133 2 0
    //   288: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   291: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   294: aastore
    //   295: dup
    //   296: iconst_1
    //   297: aload 5
    //   299: aload 5
    //   301: ldc 113
    //   303: invokeinterface 129 2 0
    //   308: invokeinterface 133 2 0
    //   313: aastore
    //   314: invokevirtual 203	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   317: aload 5
    //   319: invokeinterface 96 1 0
    //   324: aload_3
    //   325: invokevirtual 73	com/miaozhen/mzmonitor/b$a:close	()V
    //   328: new 6	com/miaozhen/mzmonitor/b$a
    //   331: dup
    //   332: aload_0
    //   333: aload_0
    //   334: getfield 21	com/miaozhen/mzmonitor/b:a	Landroid/content/Context;
    //   337: ldc 33
    //   339: aconst_null
    //   340: bipush 6
    //   342: invokespecial 36	com/miaozhen/mzmonitor/b$a:<init>	(Lcom/miaozhen/mzmonitor/b;Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V
    //   345: astore_3
    //   346: aload_3
    //   347: invokevirtual 40	com/miaozhen/mzmonitor/b$a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   350: astore 4
    //   352: aload_1
    //   353: aload_1
    //   354: invokevirtual 168	com/miaozhen/mzmonitor/a:h	()I
    //   357: iconst_1
    //   358: iadd
    //   359: invokevirtual 152	com/miaozhen/mzmonitor/a:a	(I)V
    //   362: aload 4
    //   364: ldc 42
    //   366: aconst_null
    //   367: aload_1
    //   368: invokevirtual 187	com/miaozhen/mzmonitor/a:j	()Landroid/content/ContentValues;
    //   371: invokevirtual 207	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   374: pop2
    //   375: ldc 209
    //   377: aload_1
    //   378: invokevirtual 210	com/miaozhen/mzmonitor/a:toString	()Ljava/lang/String;
    //   381: invokestatic 216	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   384: pop
    //   385: aload_3
    //   386: invokevirtual 73	com/miaozhen/mzmonitor/b$a:close	()V
    //   389: goto -364 -> 25
    //   392: astore_1
    //   393: aload_1
    //   394: invokevirtual 99	java/lang/Exception:printStackTrace	()V
    //   397: goto -372 -> 25
    //   400: iconst_0
    //   401: istore 6
    //   403: goto -197 -> 206
    //   406: astore_1
    //   407: goto -382 -> 25
    //
    // Exception table:
    //   from	to	target	type
    //   12	25	183	finally
    //   28	50	183	finally
    //   55	60	183	finally
    //   63	79	183	finally
    //   79	91	183	finally
    //   97	180	183	finally
    //   188	202	183	finally
    //   211	317	183	finally
    //   317	328	183	finally
    //   328	389	183	finally
    //   393	397	183	finally
    //   188	202	392	java/lang/Exception
    //   211	317	392	java/lang/Exception
    //   317	328	392	java/lang/Exception
    //   328	389	392	java/lang/Exception
    //   12	25	406	java/lang/Exception
    //   28	50	406	java/lang/Exception
    //   55	60	406	java/lang/Exception
    //   63	79	406	java/lang/Exception
    //   79	91	406	java/lang/Exception
    //   97	180	406	java/lang/Exception
    //   393	397	406	java/lang/Exception
  }

  final class a extends SQLiteOpenHelper
  {
    public a(Context paramString, String paramCursorFactory, SQLiteDatabase.CursorFactory paramInt, int arg5)
    {
      super(paramCursorFactory, null, 6);
    }

    public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS mzcaches (cacheId VARCHAR PRIMARY KEY, url VARCHAR, timestamp LONG, times INTEGER)");
    }

    public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS mzcaches");
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS mzcaches (cacheId VARCHAR PRIMARY KEY, url VARCHAR, timestamp LONG, times INTEGER)");
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.miaozhen.mzmonitor.b
 * JD-Core Version:    0.6.2
 */