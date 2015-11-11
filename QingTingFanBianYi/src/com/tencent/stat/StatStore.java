package com.tencent.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.tencent.stat.common.StatCommonHelper;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.User;
import com.tencent.stat.event.Event;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class StatStore
{
  private static StatStore instance = null;
  private static StatLogger logger = StatCommonHelper.getLogger();
  Handler handler = null;
  private StatStoreHelper helper;
  private HashMap<String, String> kvMap = new HashMap();
  volatile int numStoredEvents = 0;
  User user = null;

  private StatStore(Context paramContext)
  {
    try
    {
      HandlerThread localHandlerThread = new HandlerThread("StatStore");
      localHandlerThread.start();
      logger.w("Launch store thread:" + localHandlerThread);
      this.handler = new Handler(localHandlerThread.getLooper());
      paramContext = paramContext.getApplicationContext();
      this.helper = new StatStoreHelper(paramContext);
      this.helper.getWritableDatabase();
      this.helper.getReadableDatabase();
      getUser(paramContext);
      loadConfig();
      loadKeyValues();
      this.handler.post(new Runnable()
      {
        public void run()
        {
          StatStore.this.loadUnsentEventCount();
        }
      });
      return;
    }
    catch (Throwable paramContext)
    {
      logger.e(paramContext);
    }
  }

  private void directDeleteEvents(List<StoredEvent> paramList)
  {
    logger.i("Delete " + paramList.size() + " sent events in thread:" + Thread.currentThread());
    try
    {
      this.helper.getWritableDatabase().beginTransaction();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        StoredEvent localStoredEvent = (StoredEvent)paramList.next();
        this.numStoredEvents -= this.helper.getWritableDatabase().delete("events", "event_id = ?", new String[] { Long.toString(localStoredEvent.id) });
      }
    }
    catch (SQLiteException paramList)
    {
      paramList = paramList;
      logger.e(paramList);
      try
      {
        this.helper.getWritableDatabase().endTransaction();
        return;
        this.helper.getWritableDatabase().setTransactionSuccessful();
        this.numStoredEvents = ((int)DatabaseUtils.queryNumEntries(this.helper.getReadableDatabase(), "events"));
        try
        {
          this.helper.getWritableDatabase().endTransaction();
          return;
        }
        catch (SQLiteException paramList)
        {
          logger.e(paramList);
          return;
        }
      }
      catch (SQLiteException paramList)
      {
        logger.e(paramList);
        return;
      }
    }
    finally
    {
    }
    try
    {
      this.helper.getWritableDatabase().endTransaction();
      throw paramList;
    }
    catch (SQLiteException localSQLiteException)
    {
      while (true)
        logger.e(localSQLiteException);
    }
  }

  // ERROR //
  private void directUpdateEvents(List<StoredEvent> paramList, int paramInt)
  {
    // Byte code:
    //   0: getstatic 51	com/tencent/stat/StatStore:logger	Lcom/tencent/stat/common/StatLogger;
    //   3: new 83	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   10: ldc 253
    //   12: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: aload_1
    //   16: invokeinterface 182 1 0
    //   21: invokevirtual 185	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   24: ldc 255
    //   26: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: iload_2
    //   30: invokevirtual 185	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   33: ldc_w 257
    //   36: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: invokestatic 193	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   42: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   45: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokevirtual 196	com/tencent/stat/common/StatLogger:i	(Ljava/lang/Object;)V
    //   51: new 259	android/content/ContentValues
    //   54: dup
    //   55: invokespecial 260	android/content/ContentValues:<init>	()V
    //   58: astore_3
    //   59: aload_3
    //   60: ldc_w 262
    //   63: iload_2
    //   64: invokestatic 267	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   67: invokevirtual 271	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   70: aload_0
    //   71: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   74: invokevirtual 126	com/tencent/stat/StatStore$StatStoreHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   77: invokevirtual 201	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   80: aload_1
    //   81: invokeinterface 205 1 0
    //   86: astore_1
    //   87: aload_1
    //   88: invokeinterface 211 1 0
    //   93: ifeq +226 -> 319
    //   96: aload_1
    //   97: invokeinterface 215 1 0
    //   102: checkcast 25	com/tencent/stat/StatStore$StoredEvent
    //   105: astore 4
    //   107: aload 4
    //   109: getfield 274	com/tencent/stat/StatStore$StoredEvent:send_count	I
    //   112: iconst_1
    //   113: iadd
    //   114: invokestatic 279	com/tencent/stat/StatConfig:getMaxSendRetryCount	()I
    //   117: if_icmple +64 -> 181
    //   120: aload_0
    //   121: aload_0
    //   122: getfield 64	com/tencent/stat/StatStore:numStoredEvents	I
    //   125: aload_0
    //   126: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   129: invokevirtual 126	com/tencent/stat/StatStore$StatStoreHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   132: ldc 217
    //   134: ldc_w 281
    //   137: iconst_1
    //   138: anewarray 221	java/lang/String
    //   141: dup
    //   142: iconst_0
    //   143: aload 4
    //   145: getfield 225	com/tencent/stat/StatStore$StoredEvent:id	J
    //   148: invokestatic 230	java/lang/Long:toString	(J)Ljava/lang/String;
    //   151: aastore
    //   152: invokevirtual 234	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   155: isub
    //   156: putfield 64	com/tencent/stat/StatStore:numStoredEvents	I
    //   159: goto -72 -> 87
    //   162: astore_1
    //   163: getstatic 51	com/tencent/stat/StatStore:logger	Lcom/tencent/stat/common/StatLogger;
    //   166: aload_1
    //   167: invokevirtual 237	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
    //   170: aload_0
    //   171: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   174: invokevirtual 126	com/tencent/stat/StatStore$StatStoreHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   177: invokevirtual 240	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   180: return
    //   181: aload_3
    //   182: ldc_w 282
    //   185: aload 4
    //   187: getfield 274	com/tencent/stat/StatStore$StoredEvent:send_count	I
    //   190: iconst_1
    //   191: iadd
    //   192: invokestatic 286	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   195: invokevirtual 289	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   198: getstatic 51	com/tencent/stat/StatStore:logger	Lcom/tencent/stat/common/StatLogger;
    //   201: new 83	java/lang/StringBuilder
    //   204: dup
    //   205: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   208: ldc_w 291
    //   211: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: aload 4
    //   216: getfield 225	com/tencent/stat/StatStore$StoredEvent:id	J
    //   219: invokevirtual 294	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   222: ldc_w 296
    //   225: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: aload_3
    //   229: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   232: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   235: invokevirtual 196	com/tencent/stat/common/StatLogger:i	(Ljava/lang/Object;)V
    //   238: aload_0
    //   239: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   242: invokevirtual 126	com/tencent/stat/StatStore$StatStoreHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   245: ldc 217
    //   247: aload_3
    //   248: ldc_w 281
    //   251: iconst_1
    //   252: anewarray 221	java/lang/String
    //   255: dup
    //   256: iconst_0
    //   257: aload 4
    //   259: getfield 225	com/tencent/stat/StatStore$StoredEvent:id	J
    //   262: invokestatic 230	java/lang/Long:toString	(J)Ljava/lang/String;
    //   265: aastore
    //   266: invokevirtual 300	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   269: istore_2
    //   270: iload_2
    //   271: ifgt -184 -> 87
    //   274: getstatic 51	com/tencent/stat/StatStore:logger	Lcom/tencent/stat/common/StatLogger;
    //   277: new 83	java/lang/StringBuilder
    //   280: dup
    //   281: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   284: ldc_w 302
    //   287: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: iload_2
    //   291: invokestatic 267	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   294: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   300: invokevirtual 149	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   303: goto -216 -> 87
    //   306: astore_1
    //   307: aload_0
    //   308: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   311: invokevirtual 126	com/tencent/stat/StatStore$StatStoreHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   314: invokevirtual 240	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   317: aload_1
    //   318: athrow
    //   319: aload_0
    //   320: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   323: invokevirtual 126	com/tencent/stat/StatStore$StatStoreHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   326: invokevirtual 243	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   329: aload_0
    //   330: aload_0
    //   331: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   334: invokevirtual 129	com/tencent/stat/StatStore$StatStoreHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   337: ldc 217
    //   339: invokestatic 249	android/database/DatabaseUtils:queryNumEntries	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)J
    //   342: l2i
    //   343: putfield 64	com/tencent/stat/StatStore:numStoredEvents	I
    //   346: aload_0
    //   347: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   350: invokevirtual 126	com/tencent/stat/StatStore$StatStoreHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   353: invokevirtual 240	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   356: return
    //   357: astore_1
    //   358: getstatic 51	com/tencent/stat/StatStore:logger	Lcom/tencent/stat/common/StatLogger;
    //   361: aload_1
    //   362: invokevirtual 237	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
    //   365: return
    //   366: astore_1
    //   367: getstatic 51	com/tencent/stat/StatStore:logger	Lcom/tencent/stat/common/StatLogger;
    //   370: aload_1
    //   371: invokevirtual 237	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
    //   374: return
    //   375: astore_3
    //   376: getstatic 51	com/tencent/stat/StatStore:logger	Lcom/tencent/stat/common/StatLogger;
    //   379: aload_3
    //   380: invokevirtual 237	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
    //   383: goto -66 -> 317
    //
    // Exception table:
    //   from	to	target	type
    //   51	87	162	android/database/sqlite/SQLiteException
    //   87	159	162	android/database/sqlite/SQLiteException
    //   181	270	162	android/database/sqlite/SQLiteException
    //   274	303	162	android/database/sqlite/SQLiteException
    //   319	346	162	android/database/sqlite/SQLiteException
    //   51	87	306	finally
    //   87	159	306	finally
    //   163	170	306	finally
    //   181	270	306	finally
    //   274	303	306	finally
    //   319	346	306	finally
    //   346	356	357	android/database/sqlite/SQLiteException
    //   170	180	366	android/database/sqlite/SQLiteException
    //   307	317	375	android/database/sqlite/SQLiteException
  }

  public static StatStore getInstance()
  {
    return instance;
  }

  public static StatStore getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new StatStore(paramContext);
    return instance;
  }

  // ERROR //
  private void loadKeyValues()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   4: invokevirtual 129	com/tencent/stat/StatStore$StatStoreHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: ldc_w 309
    //   10: aconst_null
    //   11: aconst_null
    //   12: aconst_null
    //   13: aconst_null
    //   14: aconst_null
    //   15: aconst_null
    //   16: invokevirtual 313	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   19: astore_2
    //   20: aload_2
    //   21: astore_1
    //   22: aload_2
    //   23: invokeinterface 318 1 0
    //   28: ifeq +51 -> 79
    //   31: aload_2
    //   32: astore_1
    //   33: aload_0
    //   34: getfield 71	com/tencent/stat/StatStore:kvMap	Ljava/util/HashMap;
    //   37: aload_2
    //   38: iconst_0
    //   39: invokeinterface 321 2 0
    //   44: aload_2
    //   45: iconst_1
    //   46: invokeinterface 321 2 0
    //   51: invokevirtual 324	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   54: pop
    //   55: goto -35 -> 20
    //   58: astore_3
    //   59: aload_2
    //   60: astore_1
    //   61: getstatic 51	com/tencent/stat/StatStore:logger	Lcom/tencent/stat/common/StatLogger;
    //   64: aload_3
    //   65: invokevirtual 237	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
    //   68: aload_2
    //   69: ifnull +9 -> 78
    //   72: aload_2
    //   73: invokeinterface 327 1 0
    //   78: return
    //   79: aload_2
    //   80: ifnull -2 -> 78
    //   83: aload_2
    //   84: invokeinterface 327 1 0
    //   89: return
    //   90: astore_2
    //   91: aconst_null
    //   92: astore_1
    //   93: aload_1
    //   94: ifnull +9 -> 103
    //   97: aload_1
    //   98: invokeinterface 327 1 0
    //   103: aload_2
    //   104: athrow
    //   105: astore_2
    //   106: goto -13 -> 93
    //   109: astore_3
    //   110: aconst_null
    //   111: astore_2
    //   112: goto -53 -> 59
    //
    // Exception table:
    //   from	to	target	type
    //   22	31	58	android/database/sqlite/SQLiteException
    //   33	55	58	android/database/sqlite/SQLiteException
    //   0	20	90	finally
    //   22	31	105	finally
    //   33	55	105	finally
    //   61	68	105	finally
    //   0	20	109	android/database/sqlite/SQLiteException
  }

  private void loadUnsentEventCount()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("status", Integer.valueOf(1));
    this.helper.getWritableDatabase().update("events", localContentValues, "status=?", new String[] { Long.toString(2L) });
    this.numStoredEvents = ((int)DatabaseUtils.queryNumEntries(this.helper.getReadableDatabase(), "events"));
    logger.i("Total " + this.numStoredEvents + " unsent events.");
  }

  // ERROR //
  private void peekEvents(List<StoredEvent> paramList, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   7: invokevirtual 129	com/tencent/stat/StatStore$StatStoreHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   10: astore_3
    //   11: iconst_1
    //   12: invokestatic 267	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   15: astore 5
    //   17: iload_2
    //   18: invokestatic 267	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   21: astore 6
    //   23: aload_3
    //   24: ldc 217
    //   26: aconst_null
    //   27: ldc_w 329
    //   30: iconst_1
    //   31: anewarray 221	java/lang/String
    //   34: dup
    //   35: iconst_0
    //   36: aload 5
    //   38: aastore
    //   39: aconst_null
    //   40: aconst_null
    //   41: ldc_w 337
    //   44: aload 6
    //   46: invokevirtual 340	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   49: astore_3
    //   50: aload_3
    //   51: invokeinterface 318 1 0
    //   56: ifeq +76 -> 132
    //   59: aload_1
    //   60: new 25	com/tencent/stat/StatStore$StoredEvent
    //   63: dup
    //   64: aload_3
    //   65: iconst_0
    //   66: invokeinterface 344 2 0
    //   71: aload_3
    //   72: iconst_1
    //   73: invokeinterface 321 2 0
    //   78: invokestatic 348	com/tencent/stat/common/StatCommonHelper:decode	(Ljava/lang/String;)Ljava/lang/String;
    //   81: aload_3
    //   82: iconst_2
    //   83: invokeinterface 352 2 0
    //   88: aload_3
    //   89: iconst_3
    //   90: invokeinterface 352 2 0
    //   95: invokespecial 355	com/tencent/stat/StatStore$StoredEvent:<init>	(JLjava/lang/String;II)V
    //   98: invokeinterface 359 2 0
    //   103: pop
    //   104: goto -54 -> 50
    //   107: astore 4
    //   109: aload_3
    //   110: astore_1
    //   111: aload 4
    //   113: astore_3
    //   114: getstatic 51	com/tencent/stat/StatStore:logger	Lcom/tencent/stat/common/StatLogger;
    //   117: aload_3
    //   118: invokevirtual 237	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
    //   121: aload_1
    //   122: ifnull +9 -> 131
    //   125: aload_1
    //   126: invokeinterface 327 1 0
    //   131: return
    //   132: aload_3
    //   133: ifnull -2 -> 131
    //   136: aload_3
    //   137: invokeinterface 327 1 0
    //   142: return
    //   143: astore_1
    //   144: aload 4
    //   146: astore_3
    //   147: aload_3
    //   148: ifnull +9 -> 157
    //   151: aload_3
    //   152: invokeinterface 327 1 0
    //   157: aload_1
    //   158: athrow
    //   159: astore_1
    //   160: goto -13 -> 147
    //   163: astore 4
    //   165: aload_1
    //   166: astore_3
    //   167: aload 4
    //   169: astore_1
    //   170: goto -23 -> 147
    //   173: astore_3
    //   174: aconst_null
    //   175: astore_1
    //   176: goto -62 -> 114
    //
    // Exception table:
    //   from	to	target	type
    //   50	104	107	android/database/sqlite/SQLiteException
    //   3	50	143	finally
    //   50	104	159	finally
    //   114	121	163	finally
    //   3	50	173	android/database/sqlite/SQLiteException
  }

  void deleteEvents(final List<StoredEvent> paramList)
  {
    try
    {
      if (Thread.currentThread().getId() == this.handler.getLooper().getThread().getId())
      {
        directDeleteEvents(paramList);
        return;
      }
      this.handler.post(new Runnable()
      {
        public void run()
        {
          StatStore.this.directDeleteEvents(paramList);
        }
      });
      return;
    }
    catch (SQLiteException paramList)
    {
      logger.e(paramList);
    }
  }

  void directStoreEvent(Event paramEvent, StatDispatchCallback paramStatDispatchCallback)
  {
    if (StatConfig.getMaxStoreEventCount() <= 0);
    do
    {
      return;
      if (this.numStoredEvents > StatConfig.getMaxStoreEventCount())
      {
        logger.warn("Too many events stored in db.");
        this.numStoredEvents -= this.helper.getWritableDatabase().delete("events", "event_id in (select event_id from events where timestamp in (select min(timestamp) from events) limit 1)", null);
      }
      ContentValues localContentValues = new ContentValues();
      String str = StatCommonHelper.encode(paramEvent.toJsonString());
      localContentValues.put("content", str);
      localContentValues.put("send_count", "0");
      localContentValues.put("status", Integer.toString(1));
      localContentValues.put("timestamp", Long.valueOf(paramEvent.getTimestamp()));
      if (this.helper.getWritableDatabase().insert("events", null, localContentValues) == -1L)
      {
        logger.error("Failed to store event:" + str);
        return;
      }
      this.numStoredEvents += 1;
    }
    while (paramStatDispatchCallback == null);
    paramStatDispatchCallback.onDispatchSuccess();
  }

  public int getNumStoredEvents()
  {
    return this.numStoredEvents;
  }

  // ERROR //
  public User getUser(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 66	com/tencent/stat/StatStore:user	Lcom/tencent/stat/common/User;
    //   4: ifnull +8 -> 12
    //   7: aload_0
    //   8: getfield 66	com/tencent/stat/StatStore:user	Lcom/tencent/stat/common/User;
    //   11: areturn
    //   12: aload_0
    //   13: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   16: invokevirtual 129	com/tencent/stat/StatStore$StatStoreHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   19: ldc_w 425
    //   22: aconst_null
    //   23: aconst_null
    //   24: aconst_null
    //   25: aconst_null
    //   26: aconst_null
    //   27: aconst_null
    //   28: aconst_null
    //   29: invokevirtual 340	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   32: astore_3
    //   33: iconst_0
    //   34: istore 9
    //   36: aload_3
    //   37: invokeinterface 318 1 0
    //   42: ifeq +353 -> 395
    //   45: aload_3
    //   46: iconst_0
    //   47: invokeinterface 321 2 0
    //   52: astore 8
    //   54: aload 8
    //   56: invokestatic 348	com/tencent/stat/common/StatCommonHelper:decode	(Ljava/lang/String;)Ljava/lang/String;
    //   59: astore 4
    //   61: aload_3
    //   62: iconst_1
    //   63: invokeinterface 352 2 0
    //   68: istore 12
    //   70: aload_3
    //   71: iconst_2
    //   72: invokeinterface 321 2 0
    //   77: astore_2
    //   78: aload_3
    //   79: iconst_3
    //   80: invokeinterface 344 2 0
    //   85: lstore 13
    //   87: invokestatic 430	java/lang/System:currentTimeMillis	()J
    //   90: ldc2_w 431
    //   93: ldiv
    //   94: lstore 15
    //   96: iload 12
    //   98: iconst_1
    //   99: if_icmpeq +660 -> 759
    //   102: lload 13
    //   104: ldc2_w 431
    //   107: lmul
    //   108: invokestatic 435	com/tencent/stat/common/StatCommonHelper:getDateFormat	(J)Ljava/lang/String;
    //   111: ldc2_w 431
    //   114: lload 15
    //   116: lmul
    //   117: invokestatic 435	com/tencent/stat/common/StatCommonHelper:getDateFormat	(J)Ljava/lang/String;
    //   120: invokevirtual 438	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   123: ifne +636 -> 759
    //   126: iconst_1
    //   127: istore 9
    //   129: aload_2
    //   130: aload_1
    //   131: invokestatic 442	com/tencent/stat/common/StatCommonHelper:getAppVersion	(Landroid/content/Context;)Ljava/lang/String;
    //   134: invokevirtual 438	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: ifne +615 -> 752
    //   140: iload 9
    //   142: iconst_2
    //   143: ior
    //   144: istore 10
    //   146: aload 4
    //   148: ldc_w 444
    //   151: invokevirtual 448	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   154: astore 6
    //   156: aload 6
    //   158: ifnull +411 -> 569
    //   161: aload 6
    //   163: arraylength
    //   164: ifle +405 -> 569
    //   167: aload 6
    //   169: iconst_0
    //   170: aaload
    //   171: astore_2
    //   172: aload_2
    //   173: ifnull +12 -> 185
    //   176: aload_2
    //   177: invokevirtual 451	java/lang/String:length	()I
    //   180: bipush 11
    //   182: if_icmpge +554 -> 736
    //   185: aload_1
    //   186: invokestatic 454	com/tencent/stat/common/StatCommonHelper:getDeviceID	(Landroid/content/Context;)Ljava/lang/String;
    //   189: astore 5
    //   191: aload 5
    //   193: ifnull +537 -> 730
    //   196: aload 5
    //   198: invokevirtual 451	java/lang/String:length	()I
    //   201: bipush 10
    //   203: if_icmple +527 -> 730
    //   206: iconst_1
    //   207: istore 9
    //   209: aload 5
    //   211: astore_2
    //   212: goto +554 -> 766
    //   215: aload 6
    //   217: ifnull +366 -> 583
    //   220: aload 6
    //   222: arraylength
    //   223: iconst_2
    //   224: if_icmplt +359 -> 583
    //   227: aload 6
    //   229: iconst_1
    //   230: aaload
    //   231: astore 6
    //   233: new 83	java/lang/StringBuilder
    //   236: dup
    //   237: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   240: aload 4
    //   242: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: ldc_w 444
    //   248: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: aload 6
    //   253: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   259: astore 5
    //   261: iload 9
    //   263: istore 11
    //   265: aload_0
    //   266: new 456	com/tencent/stat/common/User
    //   269: dup
    //   270: aload 4
    //   272: aload 6
    //   274: iload 10
    //   276: invokespecial 459	com/tencent/stat/common/User:<init>	(Ljava/lang/String;Ljava/lang/String;I)V
    //   279: putfield 66	com/tencent/stat/StatStore:user	Lcom/tencent/stat/common/User;
    //   282: new 259	android/content/ContentValues
    //   285: dup
    //   286: invokespecial 260	android/content/ContentValues:<init>	()V
    //   289: astore_2
    //   290: aload_2
    //   291: ldc_w 461
    //   294: aload 5
    //   296: invokestatic 392	com/tencent/stat/common/StatCommonHelper:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   299: invokevirtual 271	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   302: aload_2
    //   303: ldc_w 463
    //   306: iload 10
    //   308: invokestatic 286	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   311: invokevirtual 289	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   314: aload_2
    //   315: ldc_w 465
    //   318: aload_1
    //   319: invokestatic 442	com/tencent/stat/common/StatCommonHelper:getAppVersion	(Landroid/content/Context;)Ljava/lang/String;
    //   322: invokevirtual 271	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   325: aload_2
    //   326: ldc_w 467
    //   329: lload 15
    //   331: invokestatic 404	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   334: invokevirtual 407	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   337: iload 11
    //   339: ifeq +30 -> 369
    //   342: aload_0
    //   343: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   346: invokevirtual 126	com/tencent/stat/StatStore$StatStoreHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   349: ldc_w 425
    //   352: aload_2
    //   353: ldc_w 469
    //   356: iconst_1
    //   357: anewarray 221	java/lang/String
    //   360: dup
    //   361: iconst_0
    //   362: aload 8
    //   364: aastore
    //   365: invokevirtual 300	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   368: pop
    //   369: iload 10
    //   371: iload 12
    //   373: if_icmpeq +351 -> 724
    //   376: aload_0
    //   377: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   380: invokevirtual 126	com/tencent/stat/StatStore$StatStoreHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   383: ldc_w 425
    //   386: aconst_null
    //   387: aload_2
    //   388: invokevirtual 472	android/database/sqlite/SQLiteDatabase:replace	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   391: pop2
    //   392: iconst_1
    //   393: istore 9
    //   395: iload 9
    //   397: ifne +157 -> 554
    //   400: aload_1
    //   401: invokestatic 475	com/tencent/stat/common/StatCommonHelper:getUserID	(Landroid/content/Context;)Ljava/lang/String;
    //   404: astore 4
    //   406: aload_1
    //   407: invokestatic 478	com/tencent/stat/common/StatCommonHelper:getMacId	(Landroid/content/Context;)Ljava/lang/String;
    //   410: astore 5
    //   412: aload 5
    //   414: ifnull +304 -> 718
    //   417: aload 5
    //   419: invokevirtual 451	java/lang/String:length	()I
    //   422: ifle +296 -> 718
    //   425: new 83	java/lang/StringBuilder
    //   428: dup
    //   429: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   432: aload 4
    //   434: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   437: ldc_w 444
    //   440: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   443: aload 5
    //   445: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   451: astore_2
    //   452: invokestatic 430	java/lang/System:currentTimeMillis	()J
    //   455: ldc2_w 431
    //   458: ldiv
    //   459: lstore 13
    //   461: aload_1
    //   462: invokestatic 442	com/tencent/stat/common/StatCommonHelper:getAppVersion	(Landroid/content/Context;)Ljava/lang/String;
    //   465: astore_1
    //   466: new 259	android/content/ContentValues
    //   469: dup
    //   470: invokespecial 260	android/content/ContentValues:<init>	()V
    //   473: astore 6
    //   475: aload 6
    //   477: ldc_w 461
    //   480: aload_2
    //   481: invokestatic 392	com/tencent/stat/common/StatCommonHelper:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   484: invokevirtual 271	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   487: aload 6
    //   489: ldc_w 463
    //   492: iconst_0
    //   493: invokestatic 286	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   496: invokevirtual 289	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   499: aload 6
    //   501: ldc_w 465
    //   504: aload_1
    //   505: invokevirtual 271	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   508: aload 6
    //   510: ldc_w 467
    //   513: lload 13
    //   515: invokestatic 404	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   518: invokevirtual 407	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   521: aload_0
    //   522: getfield 122	com/tencent/stat/StatStore:helper	Lcom/tencent/stat/StatStore$StatStoreHelper;
    //   525: invokevirtual 126	com/tencent/stat/StatStore$StatStoreHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   528: ldc_w 425
    //   531: aconst_null
    //   532: aload 6
    //   534: invokevirtual 411	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   537: pop2
    //   538: aload_0
    //   539: new 456	com/tencent/stat/common/User
    //   542: dup
    //   543: aload 4
    //   545: aload 5
    //   547: iconst_0
    //   548: invokespecial 459	com/tencent/stat/common/User:<init>	(Ljava/lang/String;Ljava/lang/String;I)V
    //   551: putfield 66	com/tencent/stat/StatStore:user	Lcom/tencent/stat/common/User;
    //   554: aload_3
    //   555: ifnull +9 -> 564
    //   558: aload_3
    //   559: invokeinterface 327 1 0
    //   564: aload_0
    //   565: getfield 66	com/tencent/stat/StatStore:user	Lcom/tencent/stat/common/User;
    //   568: areturn
    //   569: aload_1
    //   570: invokestatic 475	com/tencent/stat/common/StatCommonHelper:getUserID	(Landroid/content/Context;)Ljava/lang/String;
    //   573: astore_2
    //   574: aload_2
    //   575: astore 4
    //   577: iconst_1
    //   578: istore 9
    //   580: goto -365 -> 215
    //   583: aload_1
    //   584: invokestatic 478	com/tencent/stat/common/StatCommonHelper:getMacId	(Landroid/content/Context;)Ljava/lang/String;
    //   587: astore 7
    //   589: aload 7
    //   591: astore 6
    //   593: iload 9
    //   595: istore 11
    //   597: aload_2
    //   598: astore 5
    //   600: aload 7
    //   602: ifnull -337 -> 265
    //   605: aload 7
    //   607: astore 6
    //   609: iload 9
    //   611: istore 11
    //   613: aload_2
    //   614: astore 5
    //   616: aload 7
    //   618: invokevirtual 451	java/lang/String:length	()I
    //   621: ifle -356 -> 265
    //   624: new 83	java/lang/StringBuilder
    //   627: dup
    //   628: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   631: aload 4
    //   633: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   636: ldc_w 444
    //   639: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   642: aload 7
    //   644: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   647: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   650: astore 5
    //   652: iconst_1
    //   653: istore 11
    //   655: aload 7
    //   657: astore 6
    //   659: goto -394 -> 265
    //   662: astore_2
    //   663: aconst_null
    //   664: astore_1
    //   665: getstatic 51	com/tencent/stat/StatStore:logger	Lcom/tencent/stat/common/StatLogger;
    //   668: aload_2
    //   669: invokevirtual 149	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Object;)V
    //   672: aload_1
    //   673: ifnull -109 -> 564
    //   676: aload_1
    //   677: invokeinterface 327 1 0
    //   682: goto -118 -> 564
    //   685: astore_1
    //   686: aconst_null
    //   687: astore_3
    //   688: aload_3
    //   689: ifnull +9 -> 698
    //   692: aload_3
    //   693: invokeinterface 327 1 0
    //   698: aload_1
    //   699: athrow
    //   700: astore_1
    //   701: goto -13 -> 688
    //   704: astore_2
    //   705: aload_1
    //   706: astore_3
    //   707: aload_2
    //   708: astore_1
    //   709: goto -21 -> 688
    //   712: astore_2
    //   713: aload_3
    //   714: astore_1
    //   715: goto -50 -> 665
    //   718: aload 4
    //   720: astore_2
    //   721: goto -269 -> 452
    //   724: iconst_1
    //   725: istore 9
    //   727: goto -332 -> 395
    //   730: iconst_0
    //   731: istore 9
    //   733: goto +33 -> 766
    //   736: iconst_0
    //   737: istore 9
    //   739: aload_2
    //   740: astore 5
    //   742: aload 4
    //   744: astore_2
    //   745: aload 5
    //   747: astore 4
    //   749: goto -534 -> 215
    //   752: iload 9
    //   754: istore 10
    //   756: goto -610 -> 146
    //   759: iload 12
    //   761: istore 9
    //   763: goto -634 -> 129
    //   766: aload_2
    //   767: astore 5
    //   769: aload 4
    //   771: astore_2
    //   772: aload 5
    //   774: astore 4
    //   776: goto -561 -> 215
    //
    // Exception table:
    //   from	to	target	type
    //   12	33	662	java/lang/Throwable
    //   12	33	685	finally
    //   36	96	700	finally
    //   102	126	700	finally
    //   129	140	700	finally
    //   146	156	700	finally
    //   161	167	700	finally
    //   176	185	700	finally
    //   185	191	700	finally
    //   196	206	700	finally
    //   220	227	700	finally
    //   233	261	700	finally
    //   265	337	700	finally
    //   342	369	700	finally
    //   376	392	700	finally
    //   400	412	700	finally
    //   417	452	700	finally
    //   452	554	700	finally
    //   569	574	700	finally
    //   583	589	700	finally
    //   616	652	700	finally
    //   665	672	704	finally
    //   36	96	712	java/lang/Throwable
    //   102	126	712	java/lang/Throwable
    //   129	140	712	java/lang/Throwable
    //   146	156	712	java/lang/Throwable
    //   161	167	712	java/lang/Throwable
    //   176	185	712	java/lang/Throwable
    //   185	191	712	java/lang/Throwable
    //   196	206	712	java/lang/Throwable
    //   220	227	712	java/lang/Throwable
    //   233	261	712	java/lang/Throwable
    //   265	337	712	java/lang/Throwable
    //   342	369	712	java/lang/Throwable
    //   376	392	712	java/lang/Throwable
    //   400	412	712	java/lang/Throwable
    //   417	452	712	java/lang/Throwable
    //   452	554	712	java/lang/Throwable
    //   569	574	712	java/lang/Throwable
    //   583	589	712	java/lang/Throwable
    //   616	652	712	java/lang/Throwable
  }

  void loadConfig()
  {
    this.handler.post(new Runnable()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 17	com/tencent/stat/StatStore$6:this$0	Lcom/tencent/stat/StatStore;
        //   4: invokestatic 27	com/tencent/stat/StatStore:access$300	(Lcom/tencent/stat/StatStore;)Lcom/tencent/stat/StatStore$StatStoreHelper;
        //   7: invokevirtual 33	com/tencent/stat/StatStore$StatStoreHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
        //   10: ldc 35
        //   12: aconst_null
        //   13: aconst_null
        //   14: aconst_null
        //   15: aconst_null
        //   16: aconst_null
        //   17: aconst_null
        //   18: invokevirtual 41	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   21: astore_2
        //   22: aload_2
        //   23: astore_1
        //   24: aload_2
        //   25: invokeinterface 47 1 0
        //   30: ifeq +132 -> 162
        //   33: aload_2
        //   34: astore_1
        //   35: aload_2
        //   36: iconst_0
        //   37: invokeinterface 51 2 0
        //   42: istore 6
        //   44: aload_2
        //   45: astore_1
        //   46: aload_2
        //   47: iconst_1
        //   48: invokeinterface 55 2 0
        //   53: astore_3
        //   54: aload_2
        //   55: astore_1
        //   56: aload_2
        //   57: iconst_2
        //   58: invokeinterface 55 2 0
        //   63: astore 4
        //   65: aload_2
        //   66: astore_1
        //   67: aload_2
        //   68: iconst_3
        //   69: invokeinterface 51 2 0
        //   74: istore 7
        //   76: aload_2
        //   77: astore_1
        //   78: new 57	com/tencent/stat/StatConfig$OnlineConfig
        //   81: dup
        //   82: iload 6
        //   84: invokespecial 60	com/tencent/stat/StatConfig$OnlineConfig:<init>	(I)V
        //   87: astore 5
        //   89: aload_2
        //   90: astore_1
        //   91: aload 5
        //   93: iload 6
        //   95: putfield 64	com/tencent/stat/StatConfig$OnlineConfig:type	I
        //   98: aload_2
        //   99: astore_1
        //   100: aload 5
        //   102: new 66	org/json/JSONObject
        //   105: dup
        //   106: aload_3
        //   107: invokespecial 69	org/json/JSONObject:<init>	(Ljava/lang/String;)V
        //   110: putfield 73	com/tencent/stat/StatConfig$OnlineConfig:props	Lorg/json/JSONObject;
        //   113: aload_2
        //   114: astore_1
        //   115: aload 5
        //   117: aload 4
        //   119: putfield 77	com/tencent/stat/StatConfig$OnlineConfig:md5sum	Ljava/lang/String;
        //   122: aload_2
        //   123: astore_1
        //   124: aload 5
        //   126: iload 7
        //   128: putfield 80	com/tencent/stat/StatConfig$OnlineConfig:version	I
        //   131: aload_2
        //   132: astore_1
        //   133: aload 5
        //   135: invokestatic 86	com/tencent/stat/StatConfig:setConfig	(Lcom/tencent/stat/StatConfig$OnlineConfig;)V
        //   138: goto -116 -> 22
        //   141: astore_3
        //   142: aload_2
        //   143: astore_1
        //   144: invokestatic 90	com/tencent/stat/StatStore:access$400	()Lcom/tencent/stat/common/StatLogger;
        //   147: aload_3
        //   148: invokevirtual 96	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
        //   151: aload_2
        //   152: ifnull +9 -> 161
        //   155: aload_2
        //   156: invokeinterface 99 1 0
        //   161: return
        //   162: aload_2
        //   163: ifnull -2 -> 161
        //   166: aload_2
        //   167: invokeinterface 99 1 0
        //   172: return
        //   173: astore_2
        //   174: aconst_null
        //   175: astore_1
        //   176: aload_1
        //   177: ifnull +9 -> 186
        //   180: aload_1
        //   181: invokeinterface 99 1 0
        //   186: aload_2
        //   187: athrow
        //   188: astore_2
        //   189: goto -13 -> 176
        //   192: astore_3
        //   193: aconst_null
        //   194: astore_2
        //   195: goto -53 -> 142
        //
        // Exception table:
        //   from	to	target	type
        //   24	33	141	java/lang/Exception
        //   35	44	141	java/lang/Exception
        //   46	54	141	java/lang/Exception
        //   56	65	141	java/lang/Exception
        //   67	76	141	java/lang/Exception
        //   78	89	141	java/lang/Exception
        //   91	98	141	java/lang/Exception
        //   100	113	141	java/lang/Exception
        //   115	122	141	java/lang/Exception
        //   124	131	141	java/lang/Exception
        //   133	138	141	java/lang/Exception
        //   0	22	173	finally
        //   24	33	188	finally
        //   35	44	188	finally
        //   46	54	188	finally
        //   56	65	188	finally
        //   67	76	188	finally
        //   78	89	188	finally
        //   91	98	188	finally
        //   100	113	188	finally
        //   115	122	188	finally
        //   124	131	188	finally
        //   133	138	188	finally
        //   144	151	188	finally
        //   0	22	192	java/lang/Exception
      }
    });
  }

  void loadEvents(final int paramInt)
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        if (StatStore.this.numStoredEvents == 0)
          return;
        StatStore.logger.i("Load " + Integer.toString(StatStore.this.numStoredEvents) + " unsent events");
        ArrayList localArrayList1 = new ArrayList();
        final ArrayList localArrayList2 = new ArrayList();
        final int i = paramInt;
        if ((i == -1) || (i > StatConfig.getMaxLoadEventCount()))
          i = StatConfig.getMaxLoadEventCount();
        while (true)
        {
          Object localObject = StatStore.this;
          ((StatStore)localObject).numStoredEvents -= i;
          StatStore.this.peekEvents(localArrayList2, i);
          StatStore.logger.i("Peek " + Integer.toString(localArrayList2.size()) + " unsent events.");
          if (localArrayList2.isEmpty())
            break;
          StatStore.this.directUpdateEvents(localArrayList2, 2);
          localObject = localArrayList2.iterator();
          while (((Iterator)localObject).hasNext())
            localArrayList1.add(((StatStore.StoredEvent)((Iterator)localObject).next()).content);
          StatDispatcher.getInstance().send(localArrayList1, new StatDispatchCallback()
          {
            public void onDispatchFailure()
            {
              StatStore localStatStore = StatStore.this;
              localStatStore.numStoredEvents += i;
              StatStore.this.updateEvents(localArrayList2, 1);
            }

            public void onDispatchSuccess()
            {
              StatStore.this.deleteEvents(localArrayList2);
              if (StatStore.7.this.val$maxNumber == -1);
              for (int i = StatStore.7.this.val$maxNumber; ; i = StatStore.7.this.val$maxNumber - localArrayList2.size())
              {
                if ((i == -1) || (i > 0))
                  StatStore.this.loadEvents(i);
                return;
              }
            }
          });
          return;
        }
      }
    });
  }

  void storeCfg(final StatConfig.OnlineConfig paramOnlineConfig)
  {
    if (paramOnlineConfig == null)
      return;
    try
    {
      this.handler.post(new Runnable()
      {
        // ERROR //
        public void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 21	com/tencent/stat/StatStore$5:val$cfg	Lcom/tencent/stat/StatConfig$OnlineConfig;
          //   4: invokevirtual 34	com/tencent/stat/StatConfig$OnlineConfig:toJsonString	()Ljava/lang/String;
          //   7: astore 4
          //   9: aload 4
          //   11: invokestatic 40	com/tencent/stat/common/StatCommonHelper:md5sum	(Ljava/lang/String;)Ljava/lang/String;
          //   14: astore_1
          //   15: aload_1
          //   16: aload_0
          //   17: getfield 21	com/tencent/stat/StatStore$5:val$cfg	Lcom/tencent/stat/StatConfig$OnlineConfig;
          //   20: getfield 43	com/tencent/stat/StatConfig$OnlineConfig:md5sum	Ljava/lang/String;
          //   23: invokevirtual 49	java/lang/String:equals	(Ljava/lang/Object;)Z
          //   26: ifne +225 -> 251
          //   29: new 51	android/content/ContentValues
          //   32: dup
          //   33: invokespecial 52	android/content/ContentValues:<init>	()V
          //   36: astore 5
          //   38: aload 5
          //   40: ldc 54
          //   42: aload_0
          //   43: getfield 21	com/tencent/stat/StatStore$5:val$cfg	Lcom/tencent/stat/StatConfig$OnlineConfig;
          //   46: getfield 58	com/tencent/stat/StatConfig$OnlineConfig:props	Lorg/json/JSONObject;
          //   49: invokevirtual 63	org/json/JSONObject:toString	()Ljava/lang/String;
          //   52: invokevirtual 67	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
          //   55: aload 5
          //   57: ldc 68
          //   59: aload_1
          //   60: invokevirtual 67	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
          //   63: aload_0
          //   64: getfield 21	com/tencent/stat/StatStore$5:val$cfg	Lcom/tencent/stat/StatConfig$OnlineConfig;
          //   67: aload_1
          //   68: putfield 43	com/tencent/stat/StatConfig$OnlineConfig:md5sum	Ljava/lang/String;
          //   71: aload 5
          //   73: ldc 70
          //   75: aload_0
          //   76: getfield 21	com/tencent/stat/StatStore$5:val$cfg	Lcom/tencent/stat/StatConfig$OnlineConfig;
          //   79: getfield 73	com/tencent/stat/StatConfig$OnlineConfig:version	I
          //   82: invokestatic 79	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
          //   85: invokevirtual 82	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
          //   88: aload_0
          //   89: getfield 19	com/tencent/stat/StatStore$5:this$0	Lcom/tencent/stat/StatStore;
          //   92: invokestatic 86	com/tencent/stat/StatStore:access$300	(Lcom/tencent/stat/StatStore;)Lcom/tencent/stat/StatStore$StatStoreHelper;
          //   95: invokevirtual 92	com/tencent/stat/StatStore$StatStoreHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
          //   98: ldc 94
          //   100: aconst_null
          //   101: aconst_null
          //   102: aconst_null
          //   103: aconst_null
          //   104: aconst_null
          //   105: aconst_null
          //   106: invokevirtual 100	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
          //   109: astore_2
          //   110: aload_2
          //   111: astore_1
          //   112: aload_2
          //   113: invokeinterface 106 1 0
          //   118: ifeq +258 -> 376
          //   121: aload_2
          //   122: astore_1
          //   123: aload_2
          //   124: iconst_0
          //   125: invokeinterface 110 2 0
          //   130: istore 6
          //   132: aload_2
          //   133: astore_1
          //   134: aload_0
          //   135: getfield 21	com/tencent/stat/StatStore$5:val$cfg	Lcom/tencent/stat/StatConfig$OnlineConfig;
          //   138: getfield 113	com/tencent/stat/StatConfig$OnlineConfig:type	I
          //   141: istore 7
          //   143: iload 6
          //   145: iload 7
          //   147: if_icmpne -37 -> 110
          //   150: iconst_1
          //   151: istore 6
          //   153: iload 6
          //   155: istore 7
          //   157: aload_2
          //   158: ifnull +13 -> 171
          //   161: aload_2
          //   162: invokeinterface 116 1 0
          //   167: iload 6
          //   169: istore 7
          //   171: iconst_1
          //   172: iload 7
          //   174: if_icmpne +121 -> 295
          //   177: aload_0
          //   178: getfield 19	com/tencent/stat/StatStore$5:this$0	Lcom/tencent/stat/StatStore;
          //   181: invokestatic 86	com/tencent/stat/StatStore:access$300	(Lcom/tencent/stat/StatStore;)Lcom/tencent/stat/StatStore$StatStoreHelper;
          //   184: invokevirtual 119	com/tencent/stat/StatStore$StatStoreHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
          //   187: ldc 94
          //   189: aload 5
          //   191: ldc 121
          //   193: iconst_1
          //   194: anewarray 45	java/lang/String
          //   197: dup
          //   198: iconst_0
          //   199: aload_0
          //   200: getfield 21	com/tencent/stat/StatStore$5:val$cfg	Lcom/tencent/stat/StatConfig$OnlineConfig;
          //   203: getfield 113	com/tencent/stat/StatConfig$OnlineConfig:type	I
          //   206: invokestatic 124	java/lang/Integer:toString	(I)Ljava/lang/String;
          //   209: aastore
          //   210: invokevirtual 128	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
          //   213: i2l
          //   214: lstore 8
          //   216: lload 8
          //   218: ldc2_w 129
          //   221: lcmp
          //   222: ifne +113 -> 335
          //   225: invokestatic 134	com/tencent/stat/StatStore:access$400	()Lcom/tencent/stat/common/StatLogger;
          //   228: new 136	java/lang/StringBuilder
          //   231: dup
          //   232: invokespecial 137	java/lang/StringBuilder:<init>	()V
          //   235: ldc 139
          //   237: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   240: aload 4
          //   242: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   245: invokevirtual 144	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   248: invokevirtual 150	com/tencent/stat/common/StatLogger:error	(Ljava/lang/Object;)V
          //   251: return
          //   252: astore_3
          //   253: aconst_null
          //   254: astore_2
          //   255: aload_2
          //   256: astore_1
          //   257: invokestatic 134	com/tencent/stat/StatStore:access$400	()Lcom/tencent/stat/common/StatLogger;
          //   260: aload_3
          //   261: invokevirtual 154	com/tencent/stat/common/StatLogger:e	(Ljava/lang/Exception;)V
          //   264: aload_2
          //   265: ifnull +105 -> 370
          //   268: aload_2
          //   269: invokeinterface 116 1 0
          //   274: iconst_0
          //   275: istore 7
          //   277: goto -106 -> 171
          //   280: astore_2
          //   281: aconst_null
          //   282: astore_1
          //   283: aload_1
          //   284: ifnull +9 -> 293
          //   287: aload_1
          //   288: invokeinterface 116 1 0
          //   293: aload_2
          //   294: athrow
          //   295: aload 5
          //   297: ldc 155
          //   299: aload_0
          //   300: getfield 21	com/tencent/stat/StatStore$5:val$cfg	Lcom/tencent/stat/StatConfig$OnlineConfig;
          //   303: getfield 113	com/tencent/stat/StatConfig$OnlineConfig:type	I
          //   306: invokestatic 79	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
          //   309: invokevirtual 82	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
          //   312: aload_0
          //   313: getfield 19	com/tencent/stat/StatStore$5:this$0	Lcom/tencent/stat/StatStore;
          //   316: invokestatic 86	com/tencent/stat/StatStore:access$300	(Lcom/tencent/stat/StatStore;)Lcom/tencent/stat/StatStore$StatStoreHelper;
          //   319: invokevirtual 119	com/tencent/stat/StatStore$StatStoreHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
          //   322: ldc 94
          //   324: aconst_null
          //   325: aload 5
          //   327: invokevirtual 159	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
          //   330: lstore 8
          //   332: goto -116 -> 216
          //   335: invokestatic 134	com/tencent/stat/StatStore:access$400	()Lcom/tencent/stat/common/StatLogger;
          //   338: new 136	java/lang/StringBuilder
          //   341: dup
          //   342: invokespecial 137	java/lang/StringBuilder:<init>	()V
          //   345: ldc 161
          //   347: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   350: aload 4
          //   352: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   355: invokevirtual 144	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   358: invokevirtual 164	com/tencent/stat/common/StatLogger:i	(Ljava/lang/Object;)V
          //   361: return
          //   362: astore_2
          //   363: goto -80 -> 283
          //   366: astore_3
          //   367: goto -112 -> 255
          //   370: iconst_0
          //   371: istore 7
          //   373: goto -202 -> 171
          //   376: iconst_0
          //   377: istore 6
          //   379: goto -226 -> 153
          //
          // Exception table:
          //   from	to	target	type
          //   88	110	252	java/lang/Exception
          //   88	110	280	finally
          //   112	121	362	finally
          //   123	132	362	finally
          //   134	143	362	finally
          //   257	264	362	finally
          //   112	121	366	java/lang/Exception
          //   123	132	366	java/lang/Exception
          //   134	143	366	java/lang/Exception
        }
      });
      return;
    }
    catch (Exception paramOnlineConfig)
    {
      logger.e(paramOnlineConfig);
    }
  }

  void storeEvent(final Event paramEvent, final StatDispatchCallback paramStatDispatchCallback)
  {
    if (!StatConfig.isEnableStatService())
      return;
    try
    {
      if (Thread.currentThread().getId() == this.handler.getLooper().getThread().getId())
      {
        directStoreEvent(paramEvent, paramStatDispatchCallback);
        return;
      }
    }
    catch (SQLiteException paramEvent)
    {
      logger.e(paramEvent);
      return;
    }
    this.handler.post(new Runnable()
    {
      public void run()
      {
        StatStore.this.directStoreEvent(paramEvent, paramStatDispatchCallback);
      }
    });
  }

  void updateEvents(final List<StoredEvent> paramList, final int paramInt)
  {
    try
    {
      if (Thread.currentThread().getId() == this.handler.getLooper().getThread().getId())
      {
        directUpdateEvents(paramList, paramInt);
        return;
      }
      this.handler.post(new Runnable()
      {
        public void run()
        {
          StatStore.this.directUpdateEvents(paramList, paramInt);
        }
      });
      return;
    }
    catch (SQLiteException paramList)
    {
      logger.e(paramList);
    }
  }

  static class StatStoreHelper extends SQLiteOpenHelper
  {
    private static String DATABASE_NAME = "tencent_analysis.db";
    private static int DATABASE_VERSION = 3;

    public StatStoreHelper(Context paramContext)
    {
      super(DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void upgradeEventsToVer3(SQLiteDatabase paramSQLiteDatabase)
    {
      Object localObject1 = paramSQLiteDatabase.query("events", null, null, null, null, null, null);
      Object localObject2 = new ArrayList();
      while (((Cursor)localObject1).moveToNext())
        ((List)localObject2).add(new StatStore.StoredEvent(((Cursor)localObject1).getLong(0), ((Cursor)localObject1).getString(1), ((Cursor)localObject1).getInt(2), ((Cursor)localObject1).getInt(3)));
      localObject1 = new ContentValues();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        StatStore.StoredEvent localStoredEvent = (StatStore.StoredEvent)((Iterator)localObject2).next();
        ((ContentValues)localObject1).put("content", StatCommonHelper.encode(localStoredEvent.content));
        paramSQLiteDatabase.update("events", (ContentValues)localObject1, "event_id=?", new String[] { Long.toString(localStoredEvent.id) });
      }
    }

    private void upgradeUserToVer3(SQLiteDatabase paramSQLiteDatabase)
    {
      String str = null;
      Cursor localCursor = paramSQLiteDatabase.query("user", null, null, null, null, null, null);
      ContentValues localContentValues = new ContentValues();
      if (localCursor.moveToNext())
      {
        str = localCursor.getString(0);
        localCursor.getInt(1);
        localCursor.getString(2);
        localCursor.getLong(3);
        localContentValues.put("uid", StatCommonHelper.encode(str));
      }
      if (str != null)
        paramSQLiteDatabase.update("user", localContentValues, "uid=?", new String[] { str });
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("create table if not exists events(event_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, content TEXT, status INTEGER, send_count INTEGER, timestamp LONG)");
      paramSQLiteDatabase.execSQL("create table if not exists user(uid TEXT PRIMARY KEY, user_type INTEGER, app_ver TEXT, ts INTEGER)");
      paramSQLiteDatabase.execSQL("create table if not exists config(type INTEGER PRIMARY KEY NOT NULL, content TEXT, md5sum TEXT, version INTEGER)");
      paramSQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      StatStore.logger.debug("upgrade DB from oldVersion " + paramInt1 + " to newVersion " + paramInt2);
      if (paramInt1 == 1)
      {
        paramSQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
        upgradeUserToVer3(paramSQLiteDatabase);
        upgradeEventsToVer3(paramSQLiteDatabase);
      }
      if (paramInt1 == 2)
      {
        upgradeUserToVer3(paramSQLiteDatabase);
        upgradeEventsToVer3(paramSQLiteDatabase);
      }
    }
  }

  static class StoredEvent
  {
    String content;
    long id;
    int send_count;
    int status;

    public StoredEvent(long paramLong, String paramString, int paramInt1, int paramInt2)
    {
      this.id = paramLong;
      this.content = paramString;
      this.status = paramInt1;
      this.send_count = paramInt2;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.StatStore
 * JD-Core Version:    0.6.2
 */