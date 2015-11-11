package com.taobao.munion.base.download;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.taobao.munion.base.Log;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class e
{
  private static final String a = e.class.getName();
  private static final String b = "umeng_download_task_list";
  private static final String c = "UMENG_DATA";
  private static final String d = "cp";
  private static final String e = "url";
  private static final String f = "progress";
  private static final String g = "last_modified";
  private static final String h = "extra";
  private static Context i;
  private static final String j = "yyyy-MM-dd HH:mm:ss";
  private a k = new a(i);

  public static e a(Context paramContext)
  {
    if ((i == null) && (paramContext == null))
      throw new NullPointerException();
    if (i == null)
      i = paramContext;
    return b.a;
  }

  public List<String> a(String paramString)
  {
    paramString = this.k.getReadableDatabase().query("umeng_download_task_list", new String[] { "url" }, "cp=?", new String[] { paramString }, null, null, null, "1");
    ArrayList localArrayList = new ArrayList();
    paramString.moveToFirst();
    while (!paramString.isAfterLast())
    {
      localArrayList.add(paramString.getString(0));
      paramString.moveToNext();
    }
    paramString.close();
    return localArrayList;
  }

  public void a(int paramInt)
  {
    try
    {
      Date localDate = new Date(new Date().getTime() - paramInt * 1000);
      String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(localDate);
      str = " DELETE FROM umeng_download_task_list WHERE strftime('yyyy-MM-dd HH:mm:ss', last_modified)<=strftime('yyyy-MM-dd HH:mm:ss', '" + str + "')";
      this.k.getWritableDatabase().execSQL(str);
      Log.d("clearOverdueTasks(" + paramInt + ")" + " remove all tasks before " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(localDate), new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      Log.e(a, new Object[] { localException.getMessage() });
    }
  }

  public void a(String paramString1, String paramString2, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("progress", Integer.valueOf(paramInt));
    localContentValues.put("last_modified", f.a());
    this.k.getWritableDatabase().update("umeng_download_task_list", localContentValues, "cp=? and url=?", new String[] { paramString1, paramString2 });
    Log.d("updateProgress(" + paramString1 + ", " + paramString2 + ", " + paramInt + ")", new Object[0]);
  }

  public void a(String paramString1, String paramString2, String paramString3)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("extra", paramString3);
    localContentValues.put("last_modified", f.a());
    this.k.getWritableDatabase().update("umeng_download_task_list", localContentValues, "cp=? and url=?", new String[] { paramString1, paramString2 });
    Log.d("updateExtra(" + paramString1 + ", " + paramString2 + ", " + paramString3 + ")", new Object[0]);
  }

  // ERROR //
  public boolean a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 181	android/content/ContentValues
    //   3: dup
    //   4: invokespecial 182	android/content/ContentValues:<init>	()V
    //   7: astore_3
    //   8: aload_3
    //   9: ldc 21
    //   11: aload_1
    //   12: invokevirtual 199	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   15: aload_3
    //   16: ldc 27
    //   18: iconst_0
    //   19: invokestatic 188	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   22: invokevirtual 192	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   25: aload_3
    //   26: ldc 30
    //   28: invokestatic 196	com/taobao/munion/base/download/f:a	()Ljava/lang/String;
    //   31: invokevirtual 199	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   34: aload_0
    //   35: getfield 61	com/taobao/munion/base/download/e:k	Lcom/taobao/munion/base/download/e$a;
    //   38: invokevirtual 75	com/taobao/munion/base/download/e$a:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   41: ldc 15
    //   43: iconst_1
    //   44: anewarray 77	java/lang/String
    //   47: dup
    //   48: iconst_0
    //   49: ldc 27
    //   51: aastore
    //   52: ldc 201
    //   54: iconst_2
    //   55: anewarray 77	java/lang/String
    //   58: dup
    //   59: iconst_0
    //   60: aload_1
    //   61: aastore
    //   62: dup
    //   63: iconst_1
    //   64: aload_2
    //   65: aastore
    //   66: aconst_null
    //   67: aconst_null
    //   68: aconst_null
    //   69: ldc 81
    //   71: invokevirtual 87	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   74: astore 4
    //   76: aload 4
    //   78: invokeinterface 217 1 0
    //   83: ifle +61 -> 144
    //   86: new 141	java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   93: ldc 219
    //   95: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: aload_1
    //   99: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: ldc 209
    //   104: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: aload_2
    //   108: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: ldc 221
    //   113: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: ldc 223
    //   118: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   124: iconst_0
    //   125: anewarray 4	java/lang/Object
    //   128: invokestatic 173	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   131: iconst_0
    //   132: istore 7
    //   134: aload 4
    //   136: invokeinterface 115 1 0
    //   141: iload 7
    //   143: ireturn
    //   144: aload_0
    //   145: getfield 61	com/taobao/munion/base/download/e:k	Lcom/taobao/munion/base/download/e$a;
    //   148: invokevirtual 156	com/taobao/munion/base/download/e$a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   151: ldc 15
    //   153: aconst_null
    //   154: aload_3
    //   155: invokevirtual 227	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   158: lstore 5
    //   160: lload 5
    //   162: ldc2_w 228
    //   165: lcmp
    //   166: ifne +59 -> 225
    //   169: iconst_0
    //   170: istore 7
    //   172: new 141	java/lang/StringBuilder
    //   175: dup
    //   176: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   179: ldc 219
    //   181: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: aload_1
    //   185: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: ldc 209
    //   190: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: aload_2
    //   194: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: ldc 221
    //   199: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: ldc 231
    //   204: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: lload 5
    //   209: invokevirtual 234	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   212: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   215: iconst_0
    //   216: anewarray 4	java/lang/Object
    //   219: invokestatic 173	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   222: goto -88 -> 134
    //   225: iconst_1
    //   226: istore 7
    //   228: goto -56 -> 172
    //   231: astore_3
    //   232: iconst_0
    //   233: istore 7
    //   235: new 141	java/lang/StringBuilder
    //   238: dup
    //   239: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   242: ldc 219
    //   244: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: aload_1
    //   248: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: ldc 209
    //   253: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: aload_2
    //   257: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: ldc 221
    //   262: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: aload_3
    //   266: invokevirtual 176	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   269: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   272: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   275: iconst_1
    //   276: anewarray 4	java/lang/Object
    //   279: dup
    //   280: iconst_0
    //   281: aload_3
    //   282: aastore
    //   283: invokestatic 173	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   286: iload 7
    //   288: ireturn
    //   289: astore_3
    //   290: goto -55 -> 235
    //   293: astore_3
    //   294: goto -59 -> 235
    //
    // Exception table:
    //   from	to	target	type
    //   34	131	231	java/lang/Exception
    //   144	160	231	java/lang/Exception
    //   172	222	289	java/lang/Exception
    //   134	141	293	java/lang/Exception
  }

  public int b(String paramString1, String paramString2)
  {
    paramString1 = this.k.getReadableDatabase().query("umeng_download_task_list", new String[] { "progress" }, "cp=? and url=?", new String[] { paramString1, paramString2 }, null, null, null, "1");
    if (paramString1.getCount() > 0)
      paramString1.moveToFirst();
    for (int m = paramString1.getInt(0); ; m = -1)
    {
      paramString1.close();
      return m;
    }
  }

  public String c(String paramString1, String paramString2)
  {
    Object localObject = null;
    paramString2 = this.k.getReadableDatabase().query("umeng_download_task_list", new String[] { "extra" }, "cp=? and url=?", new String[] { paramString1, paramString2 }, null, null, null, "1");
    paramString1 = localObject;
    if (paramString2.getCount() > 0)
    {
      paramString2.moveToFirst();
      paramString1 = paramString2.getString(0);
    }
    paramString2.close();
    return paramString1;
  }

  public Date d(String paramString1, String paramString2)
  {
    Object localObject2 = null;
    Cursor localCursor = this.k.getReadableDatabase().query("umeng_download_task_list", new String[] { "last_modified" }, "cp=? and url=?", new String[] { paramString1, paramString2 }, null, null, null, null);
    Object localObject1 = localObject2;
    if (localCursor.getCount() > 0)
    {
      localCursor.moveToFirst();
      localObject1 = localCursor.getString(0);
      Log.d("getLastModified(" + paramString1 + ", " + paramString2 + "): " + (String)localObject1, new Object[0]);
    }
    try
    {
      localObject1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String)localObject1);
      localCursor.close();
      return localObject1;
    }
    catch (Exception paramString1)
    {
      while (true)
      {
        Log.d(paramString1.getMessage(), new Object[0]);
        localObject1 = localObject2;
      }
    }
  }

  public void e(String paramString1, String paramString2)
  {
    this.k.getWritableDatabase().delete("umeng_download_task_list", "cp=? and url=?", new String[] { paramString1, paramString2 });
    Log.d("delete(" + paramString1 + ", " + paramString2 + ")", new Object[0]);
  }

  public void finalize()
  {
    this.k.close();
  }

  class a extends SQLiteOpenHelper
  {
    private static final int b = 2;
    private static final String c = "CREATE TABLE umeng_download_task_list (cp TEXT, url TEXT, progress INTEGER, extra TEXT, last_modified TEXT, UNIQUE (cp,url) ON CONFLICT ABORT);";

    a(Context arg2)
    {
      super("UMENG_DATA", null, 2);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      Log.d("CREATE TABLE umeng_download_task_list (cp TEXT, url TEXT, progress INTEGER, extra TEXT, last_modified TEXT, UNIQUE (cp,url) ON CONFLICT ABORT);", new Object[0]);
      paramSQLiteDatabase.execSQL("CREATE TABLE umeng_download_task_list (cp TEXT, url TEXT, progress INTEGER, extra TEXT, last_modified TEXT, UNIQUE (cp,url) ON CONFLICT ABORT);");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
    }
  }

  private static class b
  {
    public static final e a = new e(null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.download.e
 * JD-Core Version:    0.6.2
 */