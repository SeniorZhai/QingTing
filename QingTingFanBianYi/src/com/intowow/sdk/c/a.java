package com.intowow.sdk.c;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.intowow.sdk.model.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class a extends SQLiteOpenHelper
{
  public a(Context paramContext)
  {
    super(paramContext, "i2wapi.db", null, 4);
  }

  private List<String> a(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("pragma table_info(" + paramString + ");", null);
    while (true)
    {
      if (!paramSQLiteDatabase.moveToNext())
      {
        paramSQLiteDatabase.close();
        return localArrayList;
      }
      localArrayList.add(paramSQLiteDatabase.getString(paramSQLiteDatabase.getColumnIndex("name")));
    }
  }

  private void a(SQLiteDatabase paramSQLiteDatabase, String paramString1, String paramString2, String[] paramArrayOfString)
  {
    List localList = a(paramSQLiteDatabase, paramString2);
    localList.removeAll(Arrays.asList(paramArrayOfString));
    paramArrayOfString = TextUtils.join(",", localList);
    paramSQLiteDatabase.execSQL("ALTER TABLE " + paramString2 + " RENAME TO " + paramString2 + "_old;");
    paramSQLiteDatabase.execSQL(paramString1);
    paramSQLiteDatabase.execSQL("INSERT INTO " + paramString2 + "(" + paramArrayOfString + ") SELECT " + paramArrayOfString + " FROM " + paramString2 + "_old;");
    paramSQLiteDatabase.execSQL("DROP TABLE " + paramString2 + "_old;");
  }

  // ERROR //
  private void b(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 116	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   6: aload_1
    //   7: ldc 118
    //   9: invokevirtual 99	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   12: aload_1
    //   13: ldc 120
    //   15: invokevirtual 99	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   18: aload_1
    //   19: invokevirtual 123	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   22: aload_1
    //   23: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   26: aload_0
    //   27: monitorexit
    //   28: return
    //   29: astore_2
    //   30: aload_1
    //   31: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   34: goto -8 -> 26
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    //   42: astore_2
    //   43: aload_1
    //   44: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   47: aload_2
    //   48: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	22	29	java/lang/Exception
    //   22	26	37	finally
    //   30	34	37	finally
    //   43	49	37	finally
    //   2	22	42	finally
  }

  // ERROR //
  private void c(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 116	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   6: aload_1
    //   7: aload_0
    //   8: invokespecial 130	com/intowow/sdk/c/a:h	()Ljava/lang/String;
    //   11: invokevirtual 99	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   14: aload_1
    //   15: invokevirtual 123	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   18: aload_1
    //   19: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_2
    //   26: aload_1
    //   27: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   30: goto -8 -> 22
    //   33: astore_1
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_1
    //   37: athrow
    //   38: astore_2
    //   39: aload_1
    //   40: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   43: aload_2
    //   44: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	18	25	java/lang/Exception
    //   18	22	33	finally
    //   26	30	33	finally
    //   39	45	33	finally
    //   2	18	38	finally
  }

  private void d(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      paramSQLiteDatabase.beginTransaction();
      paramSQLiteDatabase.execSQL(i());
      localObject1 = a(paramSQLiteDatabase);
      if (((List)localObject1).size() > 0)
      {
        localObject1 = ((List)localObject1).iterator();
        if (((Iterator)localObject1).hasNext());
      }
      else
      {
        e(paramSQLiteDatabase);
      }
    }
    catch (Exception localException)
    {
    }
    finally
    {
      Object localObject1;
      c localc;
      paramSQLiteDatabase.endTransaction();
    }
  }

  private void e(SQLiteDatabase paramSQLiteDatabase)
  {
    a(paramSQLiteDatabase, g(), "ADSTATE", new String[] { "impressions" });
  }

  private String f()
  {
    return "CREATE TABLE IF NOT EXISTS PREFERENCE (_id INTEGER PRIMARY KEY , _VALUE TEXT);";
  }

  private String g()
  {
    return "CREATE TABLE IF NOT EXISTS ADSTATE (_id INTEGER PRIMARY KEY , lastView INTEGER , state INTEGER ,total_impression INTEGER, total_click INTEGER );";
  }

  private String h()
  {
    return "CREATE TABLE IF NOT EXISTS PLACEMENT_LAST_REQUEST_TIME (REQUEST_KEY TEXT , LAST_REQUEST_TIME INTEGER , PRIMARY KEY (REQUEST_KEY));";
  }

  private String i()
  {
    return "CREATE TABLE IF NOT EXISTS AD_FREQCAP_STATE (_ID INTEGER , SLIDING_WINDOW INTEGER , FIRST_VIEW INTEGER , IMPRESSIONS INTEGER , PRIMARY KEY (_ID, SLIDING_WINDOW));";
  }

  // ERROR //
  public String a(int paramInt, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore 4
    //   5: aload_0
    //   6: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: ldc 196
    //   11: iconst_1
    //   12: anewarray 176	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: iload_1
    //   18: invokestatic 199	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   21: aastore
    //   22: invokevirtual 42	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   25: astore_3
    //   26: aload_3
    //   27: astore 4
    //   29: aload_3
    //   30: invokeinterface 202 1 0
    //   35: ifle +93 -> 128
    //   38: aload_3
    //   39: astore 4
    //   41: aload_3
    //   42: invokeinterface 205 1 0
    //   47: ifeq +81 -> 128
    //   50: aload_3
    //   51: astore 4
    //   53: aload_3
    //   54: iconst_0
    //   55: invokeinterface 61 2 0
    //   60: astore 5
    //   62: aload 5
    //   64: astore_2
    //   65: aload_2
    //   66: astore 5
    //   68: aload_3
    //   69: ifnull +12 -> 81
    //   72: aload_3
    //   73: invokeinterface 51 1 0
    //   78: aload_2
    //   79: astore 5
    //   81: aload_0
    //   82: monitorexit
    //   83: aload 5
    //   85: areturn
    //   86: astore_3
    //   87: aload_2
    //   88: astore 5
    //   90: aload 4
    //   92: ifnull -11 -> 81
    //   95: aload 4
    //   97: invokeinterface 51 1 0
    //   102: aload_2
    //   103: astore 5
    //   105: goto -24 -> 81
    //   108: astore_2
    //   109: aload_0
    //   110: monitorexit
    //   111: aload_2
    //   112: athrow
    //   113: astore_2
    //   114: aconst_null
    //   115: astore_3
    //   116: aload_3
    //   117: ifnull +9 -> 126
    //   120: aload_3
    //   121: invokeinterface 51 1 0
    //   126: aload_2
    //   127: athrow
    //   128: aload_2
    //   129: astore 5
    //   131: aload_3
    //   132: ifnull -51 -> 81
    //   135: aload_3
    //   136: invokeinterface 51 1 0
    //   141: aload_2
    //   142: astore 5
    //   144: goto -63 -> 81
    //   147: astore_2
    //   148: goto -32 -> 116
    //
    // Exception table:
    //   from	to	target	type
    //   5	26	86	java/lang/Exception
    //   29	38	86	java/lang/Exception
    //   41	50	86	java/lang/Exception
    //   53	62	86	java/lang/Exception
    //   72	78	108	finally
    //   95	102	108	finally
    //   120	126	108	finally
    //   126	128	108	finally
    //   135	141	108	finally
    //   5	26	113	finally
    //   29	38	147	finally
    //   41	50	147	finally
    //   53	62	147	finally
  }

  // ERROR //
  public List<c> a(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 207	java/util/LinkedList
    //   5: dup
    //   6: invokespecial 208	java/util/LinkedList:<init>	()V
    //   9: astore_2
    //   10: aload_1
    //   11: iconst_0
    //   12: ldc 174
    //   14: aconst_null
    //   15: aconst_null
    //   16: aconst_null
    //   17: aconst_null
    //   18: aconst_null
    //   19: aconst_null
    //   20: aconst_null
    //   21: invokevirtual 212	android/database/sqlite/SQLiteDatabase:query	(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   24: astore_1
    //   25: aload_1
    //   26: invokeinterface 202 1 0
    //   31: ifle +79 -> 110
    //   34: aload_1
    //   35: invokeinterface 205 1 0
    //   40: pop
    //   41: aload_2
    //   42: new 159	com/intowow/sdk/model/c
    //   45: dup
    //   46: aload_1
    //   47: iconst_0
    //   48: invokeinterface 216 2 0
    //   53: aload_1
    //   54: iconst_1
    //   55: invokeinterface 220 2 0
    //   60: aload_1
    //   61: iconst_2
    //   62: invokeinterface 216 2 0
    //   67: aload_1
    //   68: iconst_3
    //   69: invokeinterface 216 2 0
    //   74: aload_1
    //   75: iconst_4
    //   76: invokeinterface 216 2 0
    //   81: aload_1
    //   82: iconst_5
    //   83: invokeinterface 216 2 0
    //   88: invokespecial 223	com/intowow/sdk/model/c:<init>	(IJIIII)V
    //   91: invokeinterface 67 2 0
    //   96: pop
    //   97: aload_1
    //   98: invokeinterface 48 1 0
    //   103: istore 4
    //   105: iload 4
    //   107: ifne -66 -> 41
    //   110: aload_1
    //   111: invokeinterface 51 1 0
    //   116: aload_0
    //   117: monitorexit
    //   118: aload_2
    //   119: areturn
    //   120: astore_3
    //   121: aload_1
    //   122: invokeinterface 51 1 0
    //   127: goto -11 -> 116
    //   130: astore_3
    //   131: aload_1
    //   132: invokeinterface 51 1 0
    //   137: aload_3
    //   138: athrow
    //   139: astore_1
    //   140: aload_0
    //   141: monitorexit
    //   142: aload_1
    //   143: athrow
    //   144: astore_3
    //   145: goto -48 -> 97
    //   148: astore_1
    //   149: goto -33 -> 116
    //
    // Exception table:
    //   from	to	target	type
    //   25	41	120	java/lang/Exception
    //   97	105	120	java/lang/Exception
    //   25	41	130	finally
    //   41	97	130	finally
    //   97	105	130	finally
    //   2	10	139	finally
    //   10	25	139	finally
    //   110	116	139	finally
    //   121	127	139	finally
    //   131	139	139	finally
    //   41	97	144	java/lang/Exception
    //   10	25	148	java/lang/Exception
    //   110	116	148	java/lang/Exception
    //   121	127	148	java/lang/Exception
    //   131	139	148	java/lang/Exception
  }

  // ERROR //
  public void a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore_1
    //   7: aload_1
    //   8: invokevirtual 116	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   11: aload_1
    //   12: ldc 226
    //   14: invokevirtual 99	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   17: aload_1
    //   18: invokevirtual 123	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   21: aload_1
    //   22: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   25: aload_0
    //   26: monitorexit
    //   27: return
    //   28: astore_2
    //   29: aload_1
    //   30: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   33: goto -8 -> 25
    //   36: astore_2
    //   37: aload_1
    //   38: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   41: aload_2
    //   42: athrow
    //   43: astore_1
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_1
    //   47: athrow
    //   48: astore_1
    //   49: goto -24 -> 25
    //
    // Exception table:
    //   from	to	target	type
    //   11	21	28	java/lang/Exception
    //   11	21	36	finally
    //   2	11	43	finally
    //   21	25	43	finally
    //   29	33	43	finally
    //   37	43	43	finally
    //   2	11	48	java/lang/Exception
    //   21	25	48	java/lang/Exception
    //   29	33	48	java/lang/Exception
    //   37	43	48	java/lang/Exception
  }

  // ERROR //
  public void a(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   8: astore 4
    //   10: new 229	android/content/ContentValues
    //   13: dup
    //   14: invokespecial 230	android/content/ContentValues:<init>	()V
    //   17: astore_2
    //   18: aload_2
    //   19: ldc 232
    //   21: iconst_0
    //   22: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   25: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   28: aload 4
    //   30: ldc 174
    //   32: aload_2
    //   33: new 21	java/lang/StringBuilder
    //   36: dup
    //   37: ldc 243
    //   39: invokespecial 26	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   42: iload_1
    //   43: invokestatic 199	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   46: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: aconst_null
    //   53: invokevirtual 247	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   56: pop
    //   57: aload_2
    //   58: ifnull +7 -> 65
    //   61: aload_2
    //   62: invokevirtual 250	android/content/ContentValues:clear	()V
    //   65: aload_0
    //   66: monitorexit
    //   67: return
    //   68: astore_2
    //   69: aconst_null
    //   70: astore_2
    //   71: aload_2
    //   72: ifnull -7 -> 65
    //   75: aload_2
    //   76: invokevirtual 250	android/content/ContentValues:clear	()V
    //   79: goto -14 -> 65
    //   82: astore_2
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_2
    //   86: athrow
    //   87: astore 4
    //   89: aload_3
    //   90: astore_2
    //   91: aload 4
    //   93: astore_3
    //   94: aload_2
    //   95: ifnull +7 -> 102
    //   98: aload_2
    //   99: invokevirtual 250	android/content/ContentValues:clear	()V
    //   102: aload_3
    //   103: athrow
    //   104: astore_3
    //   105: goto -11 -> 94
    //   108: astore_3
    //   109: goto -38 -> 71
    //
    // Exception table:
    //   from	to	target	type
    //   4	18	68	java/lang/Exception
    //   61	65	82	finally
    //   75	79	82	finally
    //   98	102	82	finally
    //   102	104	82	finally
    //   4	18	87	finally
    //   18	57	104	finally
    //   18	57	108	java/lang/Exception
  }

  // ERROR //
  public void a(int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore 5
    //   11: new 229	android/content/ContentValues
    //   14: dup
    //   15: invokespecial 230	android/content/ContentValues:<init>	()V
    //   18: astore_3
    //   19: aload_3
    //   20: ldc 232
    //   22: iload_2
    //   23: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   26: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   29: aload 5
    //   31: ldc 174
    //   33: aload_3
    //   34: new 21	java/lang/StringBuilder
    //   37: dup
    //   38: ldc 243
    //   40: invokespecial 26	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   43: iload_1
    //   44: invokestatic 199	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   47: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: aconst_null
    //   54: invokevirtual 247	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   57: pop
    //   58: aload_3
    //   59: ifnull +7 -> 66
    //   62: aload_3
    //   63: invokevirtual 250	android/content/ContentValues:clear	()V
    //   66: aload_0
    //   67: monitorexit
    //   68: return
    //   69: astore_3
    //   70: aconst_null
    //   71: astore_3
    //   72: aload_3
    //   73: ifnull -7 -> 66
    //   76: aload_3
    //   77: invokevirtual 250	android/content/ContentValues:clear	()V
    //   80: goto -14 -> 66
    //   83: astore_3
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_3
    //   87: athrow
    //   88: astore 5
    //   90: aload 4
    //   92: astore_3
    //   93: aload 5
    //   95: astore 4
    //   97: aload_3
    //   98: ifnull +7 -> 105
    //   101: aload_3
    //   102: invokevirtual 250	android/content/ContentValues:clear	()V
    //   105: aload 4
    //   107: athrow
    //   108: astore 4
    //   110: goto -13 -> 97
    //   113: astore 4
    //   115: goto -43 -> 72
    //
    // Exception table:
    //   from	to	target	type
    //   5	19	69	java/lang/Exception
    //   62	66	83	finally
    //   76	80	83	finally
    //   101	105	83	finally
    //   105	108	83	finally
    //   5	19	88	finally
    //   19	58	108	finally
    //   19	58	113	java/lang/Exception
  }

  // ERROR //
  public void a(int paramInt1, long paramLong, int paramInt2, List<com.intowow.sdk.model.a> paramList)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore 8
    //   11: new 229	android/content/ContentValues
    //   14: dup
    //   15: invokespecial 230	android/content/ContentValues:<init>	()V
    //   18: astore 6
    //   20: aload 6
    //   22: ldc 254
    //   24: lload_2
    //   25: invokestatic 259	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   28: invokevirtual 262	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   31: aload 6
    //   33: ldc_w 264
    //   36: iload 4
    //   38: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   41: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   44: aload 8
    //   46: ldc 174
    //   48: aload 6
    //   50: new 21	java/lang/StringBuilder
    //   53: dup
    //   54: ldc 243
    //   56: invokespecial 26	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   59: iload_1
    //   60: invokestatic 199	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   63: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   69: aconst_null
    //   70: invokevirtual 247	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   73: pop
    //   74: aload 5
    //   76: invokeinterface 145 1 0
    //   81: astore 5
    //   83: aload 5
    //   85: invokeinterface 150 1 0
    //   90: istore 9
    //   92: iload 9
    //   94: ifne +16 -> 110
    //   97: aload 6
    //   99: ifnull +8 -> 107
    //   102: aload 6
    //   104: invokevirtual 250	android/content/ContentValues:clear	()V
    //   107: aload_0
    //   108: monitorexit
    //   109: return
    //   110: aload 5
    //   112: invokeinterface 157 1 0
    //   117: checkcast 266	com/intowow/sdk/model/a
    //   120: astore 7
    //   122: aload 8
    //   124: ldc_w 268
    //   127: iconst_5
    //   128: anewarray 270	java/lang/Object
    //   131: dup
    //   132: iconst_0
    //   133: ldc_w 272
    //   136: aastore
    //   137: dup
    //   138: iconst_1
    //   139: aload 7
    //   141: invokevirtual 274	com/intowow/sdk/model/a:c	()J
    //   144: invokestatic 259	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   147: aastore
    //   148: dup
    //   149: iconst_2
    //   150: aload 7
    //   152: invokevirtual 276	com/intowow/sdk/model/a:d	()I
    //   155: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   158: aastore
    //   159: dup
    //   160: iconst_3
    //   161: iload_1
    //   162: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   165: aastore
    //   166: dup
    //   167: iconst_4
    //   168: aload 7
    //   170: invokevirtual 278	com/intowow/sdk/model/a:b	()I
    //   173: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   176: aastore
    //   177: invokestatic 282	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   180: invokevirtual 99	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   183: goto -100 -> 83
    //   186: astore 7
    //   188: goto -105 -> 83
    //   191: astore 5
    //   193: aload 7
    //   195: astore 5
    //   197: aload 5
    //   199: ifnull -92 -> 107
    //   202: aload 5
    //   204: invokevirtual 250	android/content/ContentValues:clear	()V
    //   207: goto -100 -> 107
    //   210: astore 5
    //   212: aload_0
    //   213: monitorexit
    //   214: aload 5
    //   216: athrow
    //   217: astore 5
    //   219: aconst_null
    //   220: astore 6
    //   222: aload 6
    //   224: ifnull +8 -> 232
    //   227: aload 6
    //   229: invokevirtual 250	android/content/ContentValues:clear	()V
    //   232: aload 5
    //   234: athrow
    //   235: astore 5
    //   237: goto -15 -> 222
    //   240: astore 5
    //   242: aload 6
    //   244: astore 5
    //   246: goto -49 -> 197
    //
    // Exception table:
    //   from	to	target	type
    //   122	183	186	java/lang/Exception
    //   5	20	191	java/lang/Exception
    //   102	107	210	finally
    //   202	207	210	finally
    //   227	232	210	finally
    //   232	235	210	finally
    //   5	20	217	finally
    //   20	83	235	finally
    //   83	92	235	finally
    //   110	122	235	finally
    //   122	183	235	finally
    //   20	83	240	java/lang/Exception
    //   83	92	240	java/lang/Exception
    //   110	122	240	java/lang/Exception
  }

  // ERROR //
  public void a(int paramInt, java.util.Set<Integer> paramSet)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore 6
    //   8: new 229	android/content/ContentValues
    //   11: dup
    //   12: invokespecial 230	android/content/ContentValues:<init>	()V
    //   15: astore_3
    //   16: aload_3
    //   17: astore 5
    //   19: aload_3
    //   20: astore 4
    //   22: aload_3
    //   23: ldc_w 286
    //   26: iload_1
    //   27: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   30: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   33: aload_3
    //   34: astore 5
    //   36: aload_3
    //   37: astore 4
    //   39: aload_3
    //   40: ldc 254
    //   42: iconst_0
    //   43: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   46: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   49: aload_3
    //   50: astore 5
    //   52: aload_3
    //   53: astore 4
    //   55: aload_3
    //   56: ldc_w 288
    //   59: getstatic 293	com/intowow/sdk/model/ADProfile$n:a	Lcom/intowow/sdk/model/ADProfile$n;
    //   62: invokevirtual 296	com/intowow/sdk/model/ADProfile$n:ordinal	()I
    //   65: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   68: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   71: aload_3
    //   72: astore 5
    //   74: aload_3
    //   75: astore 4
    //   77: aload_3
    //   78: ldc_w 264
    //   81: iconst_0
    //   82: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   85: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   88: aload_3
    //   89: astore 5
    //   91: aload_3
    //   92: astore 4
    //   94: aload_3
    //   95: ldc 232
    //   97: iconst_0
    //   98: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   101: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   104: aload_3
    //   105: astore 5
    //   107: aload_3
    //   108: astore 4
    //   110: aload 6
    //   112: ldc 174
    //   114: aconst_null
    //   115: aload_3
    //   116: invokevirtual 300	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   119: pop2
    //   120: aload_3
    //   121: astore 4
    //   123: aload_2
    //   124: ifnull +42 -> 166
    //   127: aload_3
    //   128: astore 5
    //   130: aload_3
    //   131: astore 4
    //   133: aload_2
    //   134: invokeinterface 303 1 0
    //   139: astore 7
    //   141: aload_3
    //   142: astore_2
    //   143: aload_2
    //   144: astore 5
    //   146: aload_2
    //   147: astore 4
    //   149: aload 7
    //   151: invokeinterface 150 1 0
    //   156: istore 9
    //   158: iload 9
    //   160: ifne +19 -> 179
    //   163: aload_2
    //   164: astore 4
    //   166: aload 4
    //   168: ifnull +8 -> 176
    //   171: aload 4
    //   173: invokevirtual 250	android/content/ContentValues:clear	()V
    //   176: aload_0
    //   177: monitorexit
    //   178: return
    //   179: aload_2
    //   180: astore 5
    //   182: aload_2
    //   183: astore 4
    //   185: aload 7
    //   187: invokeinterface 157 1 0
    //   192: checkcast 234	java/lang/Integer
    //   195: astore 8
    //   197: aload_2
    //   198: astore 4
    //   200: new 229	android/content/ContentValues
    //   203: dup
    //   204: invokespecial 230	android/content/ContentValues:<init>	()V
    //   207: astore_3
    //   208: aload_3
    //   209: ldc_w 305
    //   212: iload_1
    //   213: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   216: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   219: aload_3
    //   220: ldc_w 307
    //   223: aload 8
    //   225: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   228: aload_3
    //   229: ldc_w 309
    //   232: iconst_0
    //   233: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   236: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   239: aload_3
    //   240: ldc_w 311
    //   243: iconst_0
    //   244: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   247: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   250: aload 6
    //   252: ldc_w 272
    //   255: aconst_null
    //   256: aload_3
    //   257: invokevirtual 300	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   260: pop2
    //   261: aload_3
    //   262: astore_2
    //   263: aload_3
    //   264: ifnull -121 -> 143
    //   267: aload_3
    //   268: astore 5
    //   270: aload_3
    //   271: astore 4
    //   273: aload_3
    //   274: invokevirtual 250	android/content/ContentValues:clear	()V
    //   277: aload_3
    //   278: astore_2
    //   279: goto -136 -> 143
    //   282: astore_2
    //   283: aload 5
    //   285: ifnull -109 -> 176
    //   288: aload 5
    //   290: invokevirtual 250	android/content/ContentValues:clear	()V
    //   293: goto -117 -> 176
    //   296: astore_2
    //   297: aload_0
    //   298: monitorexit
    //   299: aload_2
    //   300: athrow
    //   301: astore_3
    //   302: aload_2
    //   303: astore_3
    //   304: goto -43 -> 261
    //   307: astore_2
    //   308: aconst_null
    //   309: astore 4
    //   311: aload 4
    //   313: ifnull +8 -> 321
    //   316: aload 4
    //   318: invokevirtual 250	android/content/ContentValues:clear	()V
    //   321: aload_2
    //   322: athrow
    //   323: astore_2
    //   324: goto -13 -> 311
    //   327: astore_2
    //   328: aload_3
    //   329: astore 4
    //   331: goto -20 -> 311
    //   334: astore_2
    //   335: aconst_null
    //   336: astore 5
    //   338: goto -55 -> 283
    //   341: astore_2
    //   342: aload_3
    //   343: astore_2
    //   344: goto -42 -> 302
    //
    // Exception table:
    //   from	to	target	type
    //   22	33	282	java/lang/Exception
    //   39	49	282	java/lang/Exception
    //   55	71	282	java/lang/Exception
    //   77	88	282	java/lang/Exception
    //   94	104	282	java/lang/Exception
    //   110	120	282	java/lang/Exception
    //   133	141	282	java/lang/Exception
    //   149	158	282	java/lang/Exception
    //   185	197	282	java/lang/Exception
    //   273	277	282	java/lang/Exception
    //   171	176	296	finally
    //   288	293	296	finally
    //   316	321	296	finally
    //   321	323	296	finally
    //   200	208	301	java/lang/Exception
    //   2	16	307	finally
    //   22	33	323	finally
    //   39	49	323	finally
    //   55	71	323	finally
    //   77	88	323	finally
    //   94	104	323	finally
    //   110	120	323	finally
    //   133	141	323	finally
    //   149	158	323	finally
    //   185	197	323	finally
    //   200	208	323	finally
    //   273	277	323	finally
    //   208	261	327	finally
    //   2	16	334	java/lang/Exception
    //   208	261	341	java/lang/Exception
  }

  public void a(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2, long paramLong, int paramInt3)
  {
    try
    {
      paramSQLiteDatabase.execSQL(String.format("insert into %s ( _ID, SLIDING_WINDOW, FIRST_VIEW, IMPRESSIONS) values(%d, %d, %d, %d);", new Object[] { "AD_FREQCAP_STATE", Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Long.valueOf(paramLong), Integer.valueOf(paramInt3) }));
      return;
    }
    catch (Exception paramSQLiteDatabase)
    {
    }
  }

  // ERROR //
  public void a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: ldc_w 316
    //   9: iconst_1
    //   10: anewarray 176	java/lang/String
    //   13: dup
    //   14: iconst_0
    //   15: aload_1
    //   16: invokestatic 319	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   19: aastore
    //   20: invokevirtual 322	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    //   31: astore_1
    //   32: goto -9 -> 23
    //
    // Exception table:
    //   from	to	target	type
    //   2	23	26	finally
    //   2	23	31	java/lang/Exception
  }

  // ERROR //
  public void a(String paramString, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore 4
    //   5: aload_0
    //   6: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore 5
    //   11: aload 5
    //   13: astore 4
    //   15: aload 5
    //   17: ldc_w 327
    //   20: iconst_2
    //   21: anewarray 176	java/lang/String
    //   24: dup
    //   25: iconst_0
    //   26: aload_1
    //   27: invokestatic 319	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   30: aastore
    //   31: dup
    //   32: iconst_1
    //   33: lload_2
    //   34: invokestatic 330	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   37: aastore
    //   38: invokevirtual 322	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: astore 5
    //   46: aload 4
    //   48: ldc_w 332
    //   51: iconst_2
    //   52: anewarray 176	java/lang/String
    //   55: dup
    //   56: iconst_0
    //   57: lload_2
    //   58: invokestatic 330	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   61: aastore
    //   62: dup
    //   63: iconst_1
    //   64: aload_1
    //   65: invokestatic 319	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   68: aastore
    //   69: invokevirtual 322	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   72: goto -31 -> 41
    //   75: astore_1
    //   76: goto -35 -> 41
    //   79: astore_1
    //   80: aload_0
    //   81: monitorexit
    //   82: aload_1
    //   83: athrow
    //   84: astore_1
    //   85: goto -44 -> 41
    //
    // Exception table:
    //   from	to	target	type
    //   5	11	44	android/database/sqlite/SQLiteConstraintException
    //   15	41	44	android/database/sqlite/SQLiteConstraintException
    //   46	72	75	java/lang/Exception
    //   5	11	79	finally
    //   15	41	79	finally
    //   46	72	79	finally
    //   5	11	84	java/lang/Exception
    //   15	41	84	java/lang/Exception
  }

  // ERROR //
  public java.util.concurrent.ConcurrentHashMap<String, Long> b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: new 335	java/util/concurrent/ConcurrentHashMap
    //   7: dup
    //   8: invokespecial 336	java/util/concurrent/ConcurrentHashMap:<init>	()V
    //   11: astore_3
    //   12: aload_0
    //   13: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   16: ldc_w 338
    //   19: aconst_null
    //   20: invokevirtual 42	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   23: astore_1
    //   24: aload_1
    //   25: astore_2
    //   26: aload_1
    //   27: invokeinterface 202 1 0
    //   32: ifle +71 -> 103
    //   35: aload_1
    //   36: astore_2
    //   37: aload_1
    //   38: invokeinterface 205 1 0
    //   43: ifeq +60 -> 103
    //   46: aload_1
    //   47: astore_2
    //   48: aload_1
    //   49: invokeinterface 202 1 0
    //   54: ifle +49 -> 103
    //   57: aload_1
    //   58: astore_2
    //   59: aload_1
    //   60: invokeinterface 205 1 0
    //   65: pop
    //   66: aload_3
    //   67: aload_1
    //   68: iconst_0
    //   69: invokeinterface 61 2 0
    //   74: aload_1
    //   75: iconst_1
    //   76: invokeinterface 220 2 0
    //   81: invokestatic 259	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   84: invokevirtual 341	java/util/concurrent/ConcurrentHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   87: pop
    //   88: aload_1
    //   89: astore_2
    //   90: aload_1
    //   91: invokeinterface 48 1 0
    //   96: istore 4
    //   98: iload 4
    //   100: ifne -34 -> 66
    //   103: aload_1
    //   104: ifnull +9 -> 113
    //   107: aload_1
    //   108: invokeinterface 51 1 0
    //   113: aload_0
    //   114: monitorexit
    //   115: aload_3
    //   116: areturn
    //   117: astore_1
    //   118: aload_2
    //   119: ifnull -6 -> 113
    //   122: aload_2
    //   123: invokeinterface 51 1 0
    //   128: goto -15 -> 113
    //   131: astore_1
    //   132: aload_0
    //   133: monitorexit
    //   134: aload_1
    //   135: athrow
    //   136: astore_1
    //   137: aconst_null
    //   138: astore_3
    //   139: aload_1
    //   140: astore_2
    //   141: aload_3
    //   142: ifnull +9 -> 151
    //   145: aload_3
    //   146: invokeinterface 51 1 0
    //   151: aload_2
    //   152: athrow
    //   153: astore_2
    //   154: aload_1
    //   155: astore_3
    //   156: goto -15 -> 141
    //   159: astore_2
    //   160: goto -72 -> 88
    //
    // Exception table:
    //   from	to	target	type
    //   12	24	117	java/lang/Exception
    //   26	35	117	java/lang/Exception
    //   37	46	117	java/lang/Exception
    //   48	57	117	java/lang/Exception
    //   59	66	117	java/lang/Exception
    //   90	98	117	java/lang/Exception
    //   4	12	131	finally
    //   107	113	131	finally
    //   122	128	131	finally
    //   145	151	131	finally
    //   151	153	131	finally
    //   12	24	136	finally
    //   26	35	153	finally
    //   37	46	153	finally
    //   48	57	153	finally
    //   59	66	153	finally
    //   66	88	153	finally
    //   90	98	153	finally
    //   66	88	159	java/lang/Exception
  }

  // ERROR //
  public void b(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_1
    //   3: anewarray 176	java/lang/String
    //   6: astore_3
    //   7: aload_3
    //   8: iconst_0
    //   9: iload_1
    //   10: invokestatic 199	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   13: aastore
    //   14: aload_0
    //   15: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   18: astore_2
    //   19: aload_2
    //   20: invokevirtual 116	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   23: aload_2
    //   24: ldc 174
    //   26: ldc_w 344
    //   29: aload_3
    //   30: invokevirtual 348	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   33: pop
    //   34: aload_2
    //   35: ldc_w 272
    //   38: ldc_w 344
    //   41: aload_3
    //   42: invokevirtual 348	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   45: pop
    //   46: aload_2
    //   47: invokevirtual 123	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   50: aload_2
    //   51: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   54: aload_0
    //   55: monitorexit
    //   56: return
    //   57: astore_3
    //   58: aload_2
    //   59: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   62: goto -8 -> 54
    //   65: astore_3
    //   66: aload_2
    //   67: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   70: aload_3
    //   71: athrow
    //   72: astore_2
    //   73: aload_0
    //   74: monitorexit
    //   75: aload_2
    //   76: athrow
    //   77: astore_2
    //   78: goto -24 -> 54
    //
    // Exception table:
    //   from	to	target	type
    //   23	50	57	java/lang/Exception
    //   23	50	65	finally
    //   2	14	72	finally
    //   14	23	72	finally
    //   50	54	72	finally
    //   58	62	72	finally
    //   66	72	72	finally
    //   14	23	77	java/lang/Exception
    //   50	54	77	java/lang/Exception
    //   58	62	77	java/lang/Exception
    //   66	72	77	java/lang/Exception
  }

  // ERROR //
  public void b(int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore 5
    //   11: new 229	android/content/ContentValues
    //   14: dup
    //   15: invokespecial 230	android/content/ContentValues:<init>	()V
    //   18: astore_3
    //   19: aload_3
    //   20: ldc_w 288
    //   23: iload_2
    //   24: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   27: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   30: aload 5
    //   32: ldc 174
    //   34: aload_3
    //   35: new 21	java/lang/StringBuilder
    //   38: dup
    //   39: ldc 243
    //   41: invokespecial 26	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   44: iload_1
    //   45: invokestatic 199	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   48: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: aconst_null
    //   55: invokevirtual 247	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   58: pop
    //   59: aload_3
    //   60: ifnull +7 -> 67
    //   63: aload_3
    //   64: invokevirtual 250	android/content/ContentValues:clear	()V
    //   67: aload_0
    //   68: monitorexit
    //   69: return
    //   70: astore_3
    //   71: aconst_null
    //   72: astore_3
    //   73: aload_3
    //   74: ifnull -7 -> 67
    //   77: aload_3
    //   78: invokevirtual 250	android/content/ContentValues:clear	()V
    //   81: goto -14 -> 67
    //   84: astore_3
    //   85: aload_0
    //   86: monitorexit
    //   87: aload_3
    //   88: athrow
    //   89: astore 5
    //   91: aload 4
    //   93: astore_3
    //   94: aload 5
    //   96: astore 4
    //   98: aload_3
    //   99: ifnull +7 -> 106
    //   102: aload_3
    //   103: invokevirtual 250	android/content/ContentValues:clear	()V
    //   106: aload 4
    //   108: athrow
    //   109: astore 4
    //   111: goto -13 -> 98
    //   114: astore 4
    //   116: goto -43 -> 73
    //
    // Exception table:
    //   from	to	target	type
    //   5	19	70	java/lang/Exception
    //   63	67	84	finally
    //   77	81	84	finally
    //   102	106	84	finally
    //   106	109	84	finally
    //   5	19	89	finally
    //   19	59	109	finally
    //   19	59	114	java/lang/Exception
  }

  // ERROR //
  public void b(int paramInt, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore 5
    //   11: new 229	android/content/ContentValues
    //   14: dup
    //   15: invokespecial 230	android/content/ContentValues:<init>	()V
    //   18: astore_3
    //   19: aload_3
    //   20: ldc_w 286
    //   23: iload_1
    //   24: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   27: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   30: aload_3
    //   31: ldc_w 351
    //   34: aload_2
    //   35: invokevirtual 354	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   38: aload 5
    //   40: ldc_w 356
    //   43: aconst_null
    //   44: aload_3
    //   45: iconst_5
    //   46: invokevirtual 360	android/database/sqlite/SQLiteDatabase:insertWithOnConflict	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J
    //   49: pop2
    //   50: aload_3
    //   51: ifnull +7 -> 58
    //   54: aload_3
    //   55: invokevirtual 250	android/content/ContentValues:clear	()V
    //   58: aload_0
    //   59: monitorexit
    //   60: return
    //   61: astore_2
    //   62: aconst_null
    //   63: astore_3
    //   64: aload_3
    //   65: ifnull -7 -> 58
    //   68: aload_3
    //   69: invokevirtual 250	android/content/ContentValues:clear	()V
    //   72: goto -14 -> 58
    //   75: astore_2
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_2
    //   79: athrow
    //   80: astore_2
    //   81: aload 4
    //   83: astore_3
    //   84: aload_3
    //   85: ifnull +7 -> 92
    //   88: aload_3
    //   89: invokevirtual 250	android/content/ContentValues:clear	()V
    //   92: aload_2
    //   93: athrow
    //   94: astore_2
    //   95: goto -11 -> 84
    //   98: astore_2
    //   99: goto -35 -> 64
    //
    // Exception table:
    //   from	to	target	type
    //   5	19	61	java/lang/Exception
    //   54	58	75	finally
    //   68	72	75	finally
    //   88	92	75	finally
    //   92	94	75	finally
    //   5	19	80	finally
    //   19	50	94	finally
    //   19	50	98	java/lang/Exception
  }

  // ERROR //
  public void b(int paramInt, java.util.Set<Integer> paramSet)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore 5
    //   11: new 229	android/content/ContentValues
    //   14: dup
    //   15: invokespecial 230	android/content/ContentValues:<init>	()V
    //   18: astore_3
    //   19: aload_3
    //   20: ldc_w 264
    //   23: iconst_0
    //   24: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   27: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   30: aload 5
    //   32: ldc 174
    //   34: aload_3
    //   35: new 21	java/lang/StringBuilder
    //   38: dup
    //   39: ldc 243
    //   41: invokespecial 26	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   44: iload_1
    //   45: invokestatic 199	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   48: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: aconst_null
    //   55: invokevirtual 247	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   58: pop
    //   59: aload_3
    //   60: ifnull +7 -> 67
    //   63: aload_3
    //   64: invokevirtual 250	android/content/ContentValues:clear	()V
    //   67: aload 5
    //   69: ldc_w 272
    //   72: ldc_w 344
    //   75: iconst_1
    //   76: anewarray 176	java/lang/String
    //   79: dup
    //   80: iconst_0
    //   81: new 21	java/lang/StringBuilder
    //   84: dup
    //   85: iload_1
    //   86: invokestatic 199	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   89: invokespecial 26	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   92: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: aastore
    //   96: invokevirtual 348	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   99: pop
    //   100: aload_2
    //   101: invokeinterface 303 1 0
    //   106: astore_2
    //   107: aload_2
    //   108: invokeinterface 150 1 0
    //   113: istore 6
    //   115: iload 6
    //   117: ifne +14 -> 131
    //   120: aload_3
    //   121: ifnull +7 -> 128
    //   124: aload_3
    //   125: invokevirtual 250	android/content/ContentValues:clear	()V
    //   128: aload_0
    //   129: monitorexit
    //   130: return
    //   131: aload_0
    //   132: aload 5
    //   134: iload_1
    //   135: aload_2
    //   136: invokeinterface 157 1 0
    //   141: checkcast 234	java/lang/Integer
    //   144: invokevirtual 363	java/lang/Integer:intValue	()I
    //   147: lconst_0
    //   148: iconst_0
    //   149: invokevirtual 169	com/intowow/sdk/c/a:a	(Landroid/database/sqlite/SQLiteDatabase;IIJI)V
    //   152: goto -45 -> 107
    //   155: astore_2
    //   156: aload_3
    //   157: ifnull -29 -> 128
    //   160: aload_3
    //   161: invokevirtual 250	android/content/ContentValues:clear	()V
    //   164: goto -36 -> 128
    //   167: astore_2
    //   168: aload_0
    //   169: monitorexit
    //   170: aload_2
    //   171: athrow
    //   172: astore_2
    //   173: aconst_null
    //   174: astore_3
    //   175: aload_3
    //   176: ifnull +7 -> 183
    //   179: aload_3
    //   180: invokevirtual 250	android/content/ContentValues:clear	()V
    //   183: aload_2
    //   184: athrow
    //   185: astore_2
    //   186: goto -11 -> 175
    //   189: astore_2
    //   190: aload 4
    //   192: astore_3
    //   193: goto -37 -> 156
    //
    // Exception table:
    //   from	to	target	type
    //   19	59	155	java/lang/Exception
    //   63	67	155	java/lang/Exception
    //   67	107	155	java/lang/Exception
    //   107	115	155	java/lang/Exception
    //   131	152	155	java/lang/Exception
    //   124	128	167	finally
    //   160	164	167	finally
    //   179	183	167	finally
    //   183	185	167	finally
    //   5	19	172	finally
    //   19	59	185	finally
    //   63	67	185	finally
    //   67	107	185	finally
    //   107	115	185	finally
    //   131	152	185	finally
    //   5	19	189	java/lang/Exception
  }

  // ERROR //
  public android.util.SparseArray<String> c()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 366	android/util/SparseArray
    //   5: dup
    //   6: invokespecial 367	android/util/SparseArray:<init>	()V
    //   9: astore_1
    //   10: aload_0
    //   11: invokevirtual 370	com/intowow/sdk/c/a:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   14: iconst_0
    //   15: ldc_w 356
    //   18: aconst_null
    //   19: aconst_null
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: aconst_null
    //   24: aconst_null
    //   25: invokevirtual 212	android/database/sqlite/SQLiteDatabase:query	(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   28: astore_2
    //   29: aload_2
    //   30: invokeinterface 202 1 0
    //   35: ifle +41 -> 76
    //   38: aload_2
    //   39: invokeinterface 205 1 0
    //   44: pop
    //   45: aload_1
    //   46: aload_2
    //   47: iconst_0
    //   48: invokeinterface 216 2 0
    //   53: aload_2
    //   54: iconst_1
    //   55: invokeinterface 61 2 0
    //   60: invokevirtual 373	android/util/SparseArray:put	(ILjava/lang/Object;)V
    //   63: aload_2
    //   64: invokeinterface 48 1 0
    //   69: istore 4
    //   71: iload 4
    //   73: ifne -28 -> 45
    //   76: aload_2
    //   77: invokeinterface 51 1 0
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_1
    //   85: areturn
    //   86: astore_3
    //   87: aload_2
    //   88: invokeinterface 51 1 0
    //   93: goto -11 -> 82
    //   96: astore_3
    //   97: aload_2
    //   98: invokeinterface 51 1 0
    //   103: aload_3
    //   104: athrow
    //   105: astore_1
    //   106: aload_0
    //   107: monitorexit
    //   108: aload_1
    //   109: athrow
    //   110: astore_3
    //   111: goto -48 -> 63
    //   114: astore_2
    //   115: goto -33 -> 82
    //
    // Exception table:
    //   from	to	target	type
    //   29	45	86	java/lang/Exception
    //   63	71	86	java/lang/Exception
    //   29	45	96	finally
    //   45	63	96	finally
    //   63	71	96	finally
    //   2	10	105	finally
    //   10	29	105	finally
    //   76	82	105	finally
    //   87	93	105	finally
    //   97	105	105	finally
    //   45	63	110	java/lang/Exception
    //   10	29	114	java/lang/Exception
    //   76	82	114	java/lang/Exception
    //   87	93	114	java/lang/Exception
    //   97	105	114	java/lang/Exception
  }

  // ERROR //
  public void c(int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: invokevirtual 194	com/intowow/sdk/c/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore 5
    //   9: new 229	android/content/ContentValues
    //   12: dup
    //   13: invokespecial 230	android/content/ContentValues:<init>	()V
    //   16: astore_3
    //   17: aload_3
    //   18: ldc_w 307
    //   21: iload_2
    //   22: invokestatic 237	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   25: invokevirtual 241	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   28: aload 5
    //   30: ldc_w 272
    //   33: aload_3
    //   34: new 21	java/lang/StringBuilder
    //   37: dup
    //   38: ldc 243
    //   40: invokespecial 26	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   43: iload_1
    //   44: invokestatic 199	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   47: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: aconst_null
    //   54: invokevirtual 247	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   57: pop
    //   58: aload_3
    //   59: ifnull +7 -> 66
    //   62: aload_3
    //   63: invokevirtual 250	android/content/ContentValues:clear	()V
    //   66: return
    //   67: astore_3
    //   68: aconst_null
    //   69: astore_3
    //   70: aload_3
    //   71: ifnull -5 -> 66
    //   74: aload_3
    //   75: invokevirtual 250	android/content/ContentValues:clear	()V
    //   78: return
    //   79: astore 5
    //   81: aload 4
    //   83: astore_3
    //   84: aload 5
    //   86: astore 4
    //   88: aload_3
    //   89: ifnull +7 -> 96
    //   92: aload_3
    //   93: invokevirtual 250	android/content/ContentValues:clear	()V
    //   96: aload 4
    //   98: athrow
    //   99: astore 4
    //   101: goto -13 -> 88
    //   104: astore 4
    //   106: goto -36 -> 70
    //
    // Exception table:
    //   from	to	target	type
    //   3	17	67	java/lang/Exception
    //   3	17	79	finally
    //   17	58	99	finally
    //   17	58	104	java/lang/Exception
  }

  // ERROR //
  public List<com.intowow.sdk.model.a> d()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 207	java/util/LinkedList
    //   5: dup
    //   6: invokespecial 208	java/util/LinkedList:<init>	()V
    //   9: astore_1
    //   10: aload_0
    //   11: invokevirtual 370	com/intowow/sdk/c/a:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   14: iconst_0
    //   15: ldc_w 272
    //   18: aconst_null
    //   19: aconst_null
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: aconst_null
    //   24: aconst_null
    //   25: invokevirtual 212	android/database/sqlite/SQLiteDatabase:query	(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   28: astore_2
    //   29: aload_2
    //   30: invokeinterface 202 1 0
    //   35: ifle +65 -> 100
    //   38: aload_2
    //   39: invokeinterface 205 1 0
    //   44: pop
    //   45: aload_1
    //   46: new 266	com/intowow/sdk/model/a
    //   49: dup
    //   50: aload_2
    //   51: iconst_0
    //   52: invokeinterface 216 2 0
    //   57: aload_2
    //   58: iconst_1
    //   59: invokeinterface 216 2 0
    //   64: aload_2
    //   65: iconst_2
    //   66: invokeinterface 220 2 0
    //   71: aload_2
    //   72: iconst_3
    //   73: invokeinterface 216 2 0
    //   78: invokespecial 378	com/intowow/sdk/model/a:<init>	(IIJI)V
    //   81: invokeinterface 67 2 0
    //   86: pop
    //   87: aload_2
    //   88: invokeinterface 48 1 0
    //   93: istore 4
    //   95: iload 4
    //   97: ifne -52 -> 45
    //   100: aload_2
    //   101: invokeinterface 51 1 0
    //   106: aload_0
    //   107: monitorexit
    //   108: aload_1
    //   109: areturn
    //   110: astore_3
    //   111: aload_2
    //   112: invokeinterface 51 1 0
    //   117: goto -11 -> 106
    //   120: astore_3
    //   121: aload_2
    //   122: invokeinterface 51 1 0
    //   127: aload_3
    //   128: athrow
    //   129: astore_1
    //   130: aload_0
    //   131: monitorexit
    //   132: aload_1
    //   133: athrow
    //   134: astore_3
    //   135: goto -48 -> 87
    //   138: astore_2
    //   139: goto -33 -> 106
    //
    // Exception table:
    //   from	to	target	type
    //   29	45	110	java/lang/Exception
    //   87	95	110	java/lang/Exception
    //   29	45	120	finally
    //   45	87	120	finally
    //   87	95	120	finally
    //   2	10	129	finally
    //   10	29	129	finally
    //   100	106	129	finally
    //   111	117	129	finally
    //   121	129	129	finally
    //   45	87	134	java/lang/Exception
    //   10	29	138	java/lang/Exception
    //   100	106	138	java/lang/Exception
    //   111	117	138	java/lang/Exception
    //   121	129	138	java/lang/Exception
  }

  // ERROR //
  public List<c> e()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 207	java/util/LinkedList
    //   5: dup
    //   6: invokespecial 208	java/util/LinkedList:<init>	()V
    //   9: astore_1
    //   10: aload_0
    //   11: invokevirtual 370	com/intowow/sdk/c/a:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   14: iconst_0
    //   15: ldc 174
    //   17: aconst_null
    //   18: aconst_null
    //   19: aconst_null
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: aconst_null
    //   24: invokevirtual 212	android/database/sqlite/SQLiteDatabase:query	(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   27: astore_2
    //   28: aload_2
    //   29: invokeinterface 202 1 0
    //   34: ifle +72 -> 106
    //   37: aload_2
    //   38: invokeinterface 205 1 0
    //   43: pop
    //   44: aload_1
    //   45: new 159	com/intowow/sdk/model/c
    //   48: dup
    //   49: aload_2
    //   50: iconst_0
    //   51: invokeinterface 216 2 0
    //   56: aload_2
    //   57: iconst_1
    //   58: invokeinterface 220 2 0
    //   63: aload_2
    //   64: iconst_2
    //   65: invokeinterface 216 2 0
    //   70: aload_2
    //   71: iconst_3
    //   72: invokeinterface 216 2 0
    //   77: aload_2
    //   78: iconst_4
    //   79: invokeinterface 216 2 0
    //   84: invokespecial 382	com/intowow/sdk/model/c:<init>	(IJIII)V
    //   87: invokeinterface 67 2 0
    //   92: pop
    //   93: aload_2
    //   94: invokeinterface 48 1 0
    //   99: istore 4
    //   101: iload 4
    //   103: ifne -59 -> 44
    //   106: aload_2
    //   107: invokeinterface 51 1 0
    //   112: aload_0
    //   113: monitorexit
    //   114: aload_1
    //   115: areturn
    //   116: astore_3
    //   117: aload_2
    //   118: invokeinterface 51 1 0
    //   123: goto -11 -> 112
    //   126: astore_3
    //   127: aload_2
    //   128: invokeinterface 51 1 0
    //   133: aload_3
    //   134: athrow
    //   135: astore_1
    //   136: aload_0
    //   137: monitorexit
    //   138: aload_1
    //   139: athrow
    //   140: astore_3
    //   141: goto -48 -> 93
    //   144: astore_2
    //   145: goto -33 -> 112
    //
    // Exception table:
    //   from	to	target	type
    //   28	44	116	java/lang/Exception
    //   93	101	116	java/lang/Exception
    //   28	44	126	finally
    //   44	93	126	finally
    //   93	101	126	finally
    //   2	10	135	finally
    //   10	28	135	finally
    //   106	112	135	finally
    //   117	123	135	finally
    //   127	135	135	finally
    //   44	93	140	java/lang/Exception
    //   10	28	144	java/lang/Exception
    //   106	112	144	java/lang/Exception
    //   117	123	144	java/lang/Exception
    //   127	135	144	java/lang/Exception
  }

  // ERROR //
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 386	com/intowow/sdk/c/a:f	()Ljava/lang/String;
    //   6: astore_2
    //   7: aload_0
    //   8: invokespecial 172	com/intowow/sdk/c/a:g	()Ljava/lang/String;
    //   11: astore_3
    //   12: aload_0
    //   13: invokespecial 130	com/intowow/sdk/c/a:h	()Ljava/lang/String;
    //   16: astore 4
    //   18: aload_0
    //   19: invokespecial 134	com/intowow/sdk/c/a:i	()Ljava/lang/String;
    //   22: astore 5
    //   24: aload_1
    //   25: invokevirtual 116	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   28: aload_1
    //   29: aload_2
    //   30: invokevirtual 99	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   33: aload_1
    //   34: aload_3
    //   35: invokevirtual 99	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   38: aload_1
    //   39: aload 4
    //   41: invokevirtual 99	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   44: aload_1
    //   45: aload 5
    //   47: invokevirtual 99	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   50: aload_1
    //   51: invokevirtual 123	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   54: aload_1
    //   55: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   58: aload_0
    //   59: monitorexit
    //   60: return
    //   61: astore_2
    //   62: aload_1
    //   63: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   66: goto -8 -> 58
    //   69: astore_2
    //   70: aload_1
    //   71: invokevirtual 126	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   74: aload_2
    //   75: athrow
    //   76: astore_1
    //   77: aload_0
    //   78: monitorexit
    //   79: aload_1
    //   80: athrow
    //   81: astore_1
    //   82: goto -24 -> 58
    //
    // Exception table:
    //   from	to	target	type
    //   28	54	61	java/lang/Exception
    //   28	54	69	finally
    //   2	24	76	finally
    //   24	28	76	finally
    //   54	58	76	finally
    //   62	66	76	finally
    //   70	76	76	finally
    //   24	28	81	java/lang/Exception
    //   54	58	81	java/lang/Exception
    //   62	66	81	java/lang/Exception
    //   70	76	81	java/lang/Exception
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    if (paramInt1 != paramInt2)
    {
      paramInt1 += 1;
      if (paramInt1 <= paramInt2);
    }
    else
    {
      return;
    }
    switch (paramInt1)
    {
    default:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      paramInt1 += 1;
      break;
      try
      {
        b(paramSQLiteDatabase);
        continue;
      }
      finally
      {
      }
      c(paramSQLiteDatabase);
      continue;
      d(paramSQLiteDatabase);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.c.a
 * JD-Core Version:    0.6.2
 */