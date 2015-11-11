package com.alipay.sdk.tid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.alipay.sdk.encrypt.Des;
import com.alipay.sdk.util.DeviceInfo;
import java.lang.ref.WeakReference;

final class TidDbHelper extends SQLiteOpenHelper
{
  private static final String a = "msp.db";
  private static final int b = 1;
  private WeakReference c;

  public TidDbHelper(Context paramContext)
  {
    super(paramContext, "msp.db", null, 1);
    this.c = new WeakReference(paramContext);
  }

  // ERROR //
  private java.util.List a()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 31	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 34	java/util/ArrayList:<init>	()V
    //   9: astore 4
    //   11: aload_0
    //   12: invokevirtual 38	com/alipay/sdk/tid/TidDbHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   15: astore_1
    //   16: aload_1
    //   17: ldc 40
    //   19: aconst_null
    //   20: invokevirtual 46	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   23: astore_3
    //   24: aload_3
    //   25: astore_2
    //   26: aload_2
    //   27: invokeinterface 52 1 0
    //   32: ifeq +75 -> 107
    //   35: aload_2
    //   36: iconst_0
    //   37: invokeinterface 56 2 0
    //   42: astore_3
    //   43: aload_3
    //   44: invokestatic 62	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   47: ifne -21 -> 26
    //   50: aload 4
    //   52: aload_3
    //   53: aload_0
    //   54: getfield 25	com/alipay/sdk/tid/TidDbHelper:c	Ljava/lang/ref/WeakReference;
    //   57: invokevirtual 66	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   60: checkcast 68	android/content/Context
    //   63: invokestatic 73	com/alipay/sdk/util/DeviceInfo:c	(Landroid/content/Context;)Ljava/lang/String;
    //   66: invokestatic 78	com/alipay/sdk/encrypt/Des:b	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   69: invokeinterface 84 2 0
    //   74: pop
    //   75: goto -49 -> 26
    //   78: astore_3
    //   79: aload_2
    //   80: ifnull +9 -> 89
    //   83: aload_2
    //   84: invokeinterface 87 1 0
    //   89: aload_1
    //   90: ifnull +14 -> 104
    //   93: aload_1
    //   94: invokevirtual 90	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   97: ifeq +7 -> 104
    //   100: aload_1
    //   101: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   104: aload 4
    //   106: areturn
    //   107: aload_2
    //   108: ifnull +9 -> 117
    //   111: aload_2
    //   112: invokeinterface 87 1 0
    //   117: aload_1
    //   118: ifnull -14 -> 104
    //   121: aload_1
    //   122: invokevirtual 90	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   125: ifeq -21 -> 104
    //   128: aload_1
    //   129: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   132: aload 4
    //   134: areturn
    //   135: astore_3
    //   136: aconst_null
    //   137: astore_1
    //   138: aconst_null
    //   139: astore_2
    //   140: aload_2
    //   141: ifnull +9 -> 150
    //   144: aload_2
    //   145: invokeinterface 87 1 0
    //   150: aload_1
    //   151: ifnull +14 -> 165
    //   154: aload_1
    //   155: invokevirtual 90	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   158: ifeq +7 -> 165
    //   161: aload_1
    //   162: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   165: aload_3
    //   166: athrow
    //   167: astore_3
    //   168: aconst_null
    //   169: astore_2
    //   170: goto -30 -> 140
    //   173: astore_3
    //   174: goto -34 -> 140
    //   177: astore_1
    //   178: aconst_null
    //   179: astore_1
    //   180: goto -101 -> 79
    //   183: astore_3
    //   184: goto -105 -> 79
    //
    // Exception table:
    //   from	to	target	type
    //   26	75	78	java/lang/Exception
    //   11	16	135	finally
    //   16	24	167	finally
    //   26	75	173	finally
    //   11	16	177	java/lang/Exception
    //   16	24	183	java/lang/Exception
  }

  private static void a(SQLiteDatabase paramSQLiteDatabase)
  {
    int j = 0;
    Cursor localCursor = paramSQLiteDatabase.rawQuery("select name from tb_tid where tid!='' order by dt asc", null);
    if (localCursor.getCount() <= 14)
      localCursor.close();
    while (true)
    {
      return;
      int m = localCursor.getCount() - 14;
      String[] arrayOfString = new String[m];
      if (localCursor.moveToFirst())
      {
        i = 0;
        int k;
        do
        {
          arrayOfString[i] = localCursor.getString(0);
          k = i + 1;
          if (!localCursor.moveToNext())
            break;
          i = k;
        }
        while (m > k);
      }
      localCursor.close();
      int i = j;
      while (i < arrayOfString.length)
      {
        if (!TextUtils.isEmpty(arrayOfString[i]))
          a(paramSQLiteDatabase, arrayOfString[i]);
        i += 1;
      }
    }
  }

  private static void a(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    try
    {
      paramSQLiteDatabase.delete("tb_tid", "name=?", new String[] { paramString });
      return;
    }
    catch (Exception paramSQLiteDatabase)
    {
    }
  }

  private void a(SQLiteDatabase paramSQLiteDatabase, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    int j = 0;
    paramString3 = Des.a(paramString3, DeviceInfo.c((Context)this.c.get()));
    paramSQLiteDatabase.execSQL("insert into tb_tid (name, tid, key_tid, dt) values (?, ?, ?, datetime('now', 'localtime'))", new Object[] { e(paramString1, paramString2), paramString3, paramString4 });
    paramString1 = paramSQLiteDatabase.rawQuery("select name from tb_tid where tid!='' order by dt asc", null);
    if (paramString1.getCount() <= 14)
      paramString1.close();
    while (true)
    {
      return;
      int m = paramString1.getCount() - 14;
      paramString2 = new String[m];
      if (paramString1.moveToFirst())
      {
        i = 0;
        int k;
        do
        {
          paramString2[i] = paramString1.getString(0);
          k = i + 1;
          if (!paramString1.moveToNext())
            break;
          i = k;
        }
        while (m > k);
      }
      paramString1.close();
      int i = j;
      while (i < paramString2.length)
      {
        if (!TextUtils.isEmpty(paramString2[i]))
          a(paramSQLiteDatabase, paramString2[i]);
        i += 1;
      }
    }
  }

  private static boolean a(SQLiteDatabase paramSQLiteDatabase, String paramString1, String paramString2)
  {
    SQLiteDatabase localSQLiteDatabase2 = null;
    SQLiteDatabase localSQLiteDatabase1 = null;
    while (true)
    {
      try
      {
        paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("select count(*) from tb_tid where name=?", new String[] { e(paramString1, paramString2) });
        localSQLiteDatabase1 = paramSQLiteDatabase;
        localSQLiteDatabase2 = paramSQLiteDatabase;
        if (!paramSQLiteDatabase.moveToFirst())
          break label112;
        localSQLiteDatabase1 = paramSQLiteDatabase;
        localSQLiteDatabase2 = paramSQLiteDatabase;
        i = paramSQLiteDatabase.getInt(0);
        if (paramSQLiteDatabase == null)
          break label109;
        paramSQLiteDatabase.close();
        if (i > 0)
          return true;
      }
      catch (Exception paramSQLiteDatabase)
      {
        if (localSQLiteDatabase1 == null)
          break label103;
        localSQLiteDatabase1.close();
        i = 0;
        continue;
      }
      finally
      {
        if (localSQLiteDatabase2 != null)
          localSQLiteDatabase2.close();
      }
      return false;
      label103: int i = 0;
      continue;
      label109: continue;
      label112: i = 0;
    }
  }

  private void b(SQLiteDatabase paramSQLiteDatabase, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    paramSQLiteDatabase.execSQL("update tb_tid set tid=?, key_tid=?, dt=datetime('now', 'localtime') where name=?", new Object[] { Des.a(paramString3, DeviceInfo.c((Context)this.c.get())), paramString4, e(paramString1, paramString2) });
  }

  // ERROR //
  private long d(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 7
    //   6: aconst_null
    //   7: astore 6
    //   9: aconst_null
    //   10: astore 5
    //   12: lconst_0
    //   13: lstore 10
    //   15: aload_0
    //   16: invokevirtual 38	com/alipay/sdk/tid/TidDbHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   19: astore_3
    //   20: aload 7
    //   22: astore 4
    //   24: aload 6
    //   26: astore 5
    //   28: aload_3
    //   29: ldc 141
    //   31: iconst_1
    //   32: anewarray 100	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: aload_1
    //   38: aload_2
    //   39: invokestatic 124	com/alipay/sdk/tid/TidDbHelper:e	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   42: aastore
    //   43: invokevirtual 46	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   46: astore_1
    //   47: lload 10
    //   49: lstore 8
    //   51: aload_1
    //   52: astore 4
    //   54: aload_1
    //   55: astore 5
    //   57: aload_1
    //   58: invokeinterface 103 1 0
    //   63: ifeq +36 -> 99
    //   66: aload_1
    //   67: astore 4
    //   69: aload_1
    //   70: astore 5
    //   72: new 143	java/text/SimpleDateFormat
    //   75: dup
    //   76: ldc 145
    //   78: invokestatic 151	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   81: invokespecial 154	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   84: aload_1
    //   85: iconst_0
    //   86: invokeinterface 56 2 0
    //   91: invokevirtual 158	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   94: invokevirtual 164	java/util/Date:getTime	()J
    //   97: lstore 8
    //   99: aload_1
    //   100: ifnull +9 -> 109
    //   103: aload_1
    //   104: invokeinterface 87 1 0
    //   109: lload 8
    //   111: lstore 12
    //   113: aload_3
    //   114: ifnull +22 -> 136
    //   117: lload 8
    //   119: lstore 12
    //   121: aload_3
    //   122: invokevirtual 90	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   125: ifeq +11 -> 136
    //   128: aload_3
    //   129: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   132: lload 8
    //   134: lstore 12
    //   136: lload 12
    //   138: lreturn
    //   139: astore_1
    //   140: aconst_null
    //   141: astore_3
    //   142: aload 5
    //   144: ifnull +10 -> 154
    //   147: aload 5
    //   149: invokeinterface 87 1 0
    //   154: lload 10
    //   156: lstore 12
    //   158: aload_3
    //   159: ifnull -23 -> 136
    //   162: lload 10
    //   164: lstore 12
    //   166: aload_3
    //   167: invokevirtual 90	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   170: ifeq -34 -> 136
    //   173: aload_3
    //   174: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   177: lconst_0
    //   178: lreturn
    //   179: astore_1
    //   180: aconst_null
    //   181: astore_3
    //   182: aload 4
    //   184: ifnull +10 -> 194
    //   187: aload 4
    //   189: invokeinterface 87 1 0
    //   194: aload_3
    //   195: ifnull +14 -> 209
    //   198: aload_3
    //   199: invokevirtual 90	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   202: ifeq +7 -> 209
    //   205: aload_3
    //   206: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   209: aload_1
    //   210: athrow
    //   211: astore_1
    //   212: goto -30 -> 182
    //   215: astore_1
    //   216: goto -74 -> 142
    //
    // Exception table:
    //   from	to	target	type
    //   15	20	139	java/lang/Exception
    //   15	20	179	finally
    //   28	47	211	finally
    //   57	66	211	finally
    //   72	99	211	finally
    //   28	47	215	java/lang/Exception
    //   57	66	215	java/lang/Exception
    //   72	99	215	java/lang/Exception
  }

  private static String e(String paramString1, String paramString2)
  {
    return paramString1 + paramString2;
  }

  public final void a(String paramString1, String paramString2)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      b(localSQLiteDatabase, paramString1, paramString2, "", "");
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      a(localSQLiteDatabase, e(paramString1, paramString2));
      return;
    }
    catch (Exception paramString1)
    {
      return;
    }
    finally
    {
      if ((localObject2 != null) && (localObject2.isOpen()))
        localObject2.close();
    }
    throw paramString1;
  }

  public final void a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      localSQLiteDatabase = getWritableDatabase();
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      if (a(localSQLiteDatabase, paramString1, paramString2))
      {
        localObject1 = localSQLiteDatabase;
        localObject2 = localSQLiteDatabase;
        b(localSQLiteDatabase, paramString1, paramString2, paramString3, paramString4);
      }
      while (true)
      {
        return;
        localObject1 = localSQLiteDatabase;
        localObject2 = localSQLiteDatabase;
        paramString3 = Des.a(paramString3, DeviceInfo.c((Context)this.c.get()));
        localObject1 = localSQLiteDatabase;
        localObject2 = localSQLiteDatabase;
        localSQLiteDatabase.execSQL("insert into tb_tid (name, tid, key_tid, dt) values (?, ?, ?, datetime('now', 'localtime'))", new Object[] { e(paramString1, paramString2), paramString3, paramString4 });
        localObject1 = localSQLiteDatabase;
        localObject2 = localSQLiteDatabase;
        paramString1 = localSQLiteDatabase.rawQuery("select name from tb_tid where tid!='' order by dt asc", null);
        localObject1 = localSQLiteDatabase;
        localObject2 = localSQLiteDatabase;
        if (paramString1.getCount() > 14)
          break;
        localObject1 = localSQLiteDatabase;
        localObject2 = localSQLiteDatabase;
        paramString1.close();
      }
    }
    catch (Exception paramString1)
    {
      SQLiteDatabase localSQLiteDatabase;
      return;
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      int k = paramString1.getCount() - 14;
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      paramString2 = new String[k];
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      if (paramString1.moveToFirst())
      {
        i = 0;
        int j;
        do
        {
          localObject1 = localSQLiteDatabase;
          localObject2 = localSQLiteDatabase;
          paramString2[i] = paramString1.getString(0);
          j = i + 1;
          localObject1 = localSQLiteDatabase;
          localObject2 = localSQLiteDatabase;
          if (!paramString1.moveToNext())
            break;
          i = j;
        }
        while (k > j);
      }
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      paramString1.close();
      int i = 0;
      while (true)
      {
        localObject1 = localSQLiteDatabase;
        localObject2 = localSQLiteDatabase;
        if (i >= paramString2.length)
          break;
        localObject1 = localSQLiteDatabase;
        localObject2 = localSQLiteDatabase;
        if (!TextUtils.isEmpty(paramString2[i]))
        {
          localObject1 = localSQLiteDatabase;
          localObject2 = localSQLiteDatabase;
          a(localSQLiteDatabase, paramString2[i]);
        }
        i += 1;
      }
    }
    finally
    {
      if ((localObject2 != null) && (localObject2.isOpen()))
        localObject2.close();
    }
    throw paramString1;
  }

  // ERROR //
  public final String b(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: invokevirtual 38	com/alipay/sdk/tid/TidDbHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_3
    //   8: aload_3
    //   9: ldc 188
    //   11: iconst_1
    //   12: anewarray 100	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: aload_1
    //   18: aload_2
    //   19: invokestatic 124	com/alipay/sdk/tid/TidDbHelper:e	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   22: aastore
    //   23: invokevirtual 46	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   26: astore_2
    //   27: aload 4
    //   29: astore_1
    //   30: aload_2
    //   31: invokeinterface 103 1 0
    //   36: ifeq +11 -> 47
    //   39: aload_2
    //   40: iconst_0
    //   41: invokeinterface 56 2 0
    //   46: astore_1
    //   47: aload_2
    //   48: ifnull +9 -> 57
    //   51: aload_2
    //   52: invokeinterface 87 1 0
    //   57: aload_3
    //   58: ifnull +135 -> 193
    //   61: aload_3
    //   62: invokevirtual 90	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   65: ifeq +128 -> 193
    //   68: aload_3
    //   69: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   72: aload_1
    //   73: astore_2
    //   74: aload_1
    //   75: invokestatic 62	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   78: ifne +21 -> 99
    //   81: aload_1
    //   82: aload_0
    //   83: getfield 25	com/alipay/sdk/tid/TidDbHelper:c	Ljava/lang/ref/WeakReference;
    //   86: invokevirtual 66	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   89: checkcast 68	android/content/Context
    //   92: invokestatic 73	com/alipay/sdk/util/DeviceInfo:c	(Landroid/content/Context;)Ljava/lang/String;
    //   95: invokestatic 78	com/alipay/sdk/encrypt/Des:b	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   98: astore_2
    //   99: aload_2
    //   100: areturn
    //   101: astore_1
    //   102: aconst_null
    //   103: astore_2
    //   104: aconst_null
    //   105: astore_3
    //   106: aload_2
    //   107: ifnull +9 -> 116
    //   110: aload_2
    //   111: invokeinterface 87 1 0
    //   116: aload_3
    //   117: ifnull +71 -> 188
    //   120: aload_3
    //   121: invokevirtual 90	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   124: ifeq +64 -> 188
    //   127: aload_3
    //   128: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   131: aconst_null
    //   132: astore_1
    //   133: goto -61 -> 72
    //   136: astore_1
    //   137: aconst_null
    //   138: astore_3
    //   139: aconst_null
    //   140: astore_2
    //   141: aload_2
    //   142: ifnull +9 -> 151
    //   145: aload_2
    //   146: invokeinterface 87 1 0
    //   151: aload_3
    //   152: ifnull +14 -> 166
    //   155: aload_3
    //   156: invokevirtual 90	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   159: ifeq +7 -> 166
    //   162: aload_3
    //   163: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   166: aload_1
    //   167: athrow
    //   168: astore_1
    //   169: aconst_null
    //   170: astore_2
    //   171: goto -30 -> 141
    //   174: astore_1
    //   175: goto -34 -> 141
    //   178: astore_1
    //   179: aconst_null
    //   180: astore_2
    //   181: goto -75 -> 106
    //   184: astore_1
    //   185: goto -79 -> 106
    //   188: aconst_null
    //   189: astore_1
    //   190: goto -118 -> 72
    //   193: goto -121 -> 72
    //
    // Exception table:
    //   from	to	target	type
    //   3	8	101	java/lang/Exception
    //   3	8	136	finally
    //   8	27	168	finally
    //   30	47	174	finally
    //   8	27	178	java/lang/Exception
    //   30	47	184	java/lang/Exception
  }

  // ERROR //
  public final String c(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_0
    //   7: invokevirtual 38	com/alipay/sdk/tid/TidDbHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   10: astore_3
    //   11: aload_3
    //   12: ldc 190
    //   14: iconst_1
    //   15: anewarray 100	java/lang/String
    //   18: dup
    //   19: iconst_0
    //   20: aload_1
    //   21: aload_2
    //   22: invokestatic 124	com/alipay/sdk/tid/TidDbHelper:e	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   25: aastore
    //   26: invokevirtual 46	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   29: astore_1
    //   30: aload 5
    //   32: astore_2
    //   33: aload_1
    //   34: invokeinterface 103 1 0
    //   39: ifeq +11 -> 50
    //   42: aload_1
    //   43: iconst_0
    //   44: invokeinterface 56 2 0
    //   49: astore_2
    //   50: aload_1
    //   51: ifnull +9 -> 60
    //   54: aload_1
    //   55: invokeinterface 87 1 0
    //   60: aload_2
    //   61: astore_1
    //   62: aload_3
    //   63: ifnull +18 -> 81
    //   66: aload_2
    //   67: astore_1
    //   68: aload_3
    //   69: invokevirtual 90	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   72: ifeq +9 -> 81
    //   75: aload_3
    //   76: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   79: aload_2
    //   80: astore_1
    //   81: aload_1
    //   82: areturn
    //   83: astore_1
    //   84: aconst_null
    //   85: astore_1
    //   86: aconst_null
    //   87: astore_3
    //   88: aload_1
    //   89: ifnull +9 -> 98
    //   92: aload_1
    //   93: invokeinterface 87 1 0
    //   98: aload 4
    //   100: astore_1
    //   101: aload_3
    //   102: ifnull -21 -> 81
    //   105: aload 4
    //   107: astore_1
    //   108: aload_3
    //   109: invokevirtual 90	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   112: ifeq -31 -> 81
    //   115: aload_3
    //   116: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   119: aconst_null
    //   120: areturn
    //   121: astore_2
    //   122: aconst_null
    //   123: astore_3
    //   124: aconst_null
    //   125: astore_1
    //   126: aload_1
    //   127: ifnull +9 -> 136
    //   130: aload_1
    //   131: invokeinterface 87 1 0
    //   136: aload_3
    //   137: ifnull +14 -> 151
    //   140: aload_3
    //   141: invokevirtual 90	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   144: ifeq +7 -> 151
    //   147: aload_3
    //   148: invokevirtual 91	android/database/sqlite/SQLiteDatabase:close	()V
    //   151: aload_2
    //   152: athrow
    //   153: astore_2
    //   154: aconst_null
    //   155: astore_1
    //   156: goto -30 -> 126
    //   159: astore_2
    //   160: goto -34 -> 126
    //   163: astore_1
    //   164: aconst_null
    //   165: astore_1
    //   166: goto -78 -> 88
    //   169: astore_2
    //   170: goto -82 -> 88
    //
    // Exception table:
    //   from	to	target	type
    //   6	11	83	java/lang/Exception
    //   6	11	121	finally
    //   11	30	153	finally
    //   33	50	159	finally
    //   11	30	163	java/lang/Exception
    //   33	50	169	java/lang/Exception
  }

  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
  }

  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("drop table if exists tb_tid");
    paramSQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.tid.TidDbHelper
 * JD-Core Version:    0.6.2
 */