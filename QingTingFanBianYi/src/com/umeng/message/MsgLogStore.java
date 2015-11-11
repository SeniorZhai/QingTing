package com.umeng.message;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONObject;

public class MsgLogStore
{
  public static final String ActionType = "ActionType";
  public static final String MsgId = "MsgId";
  public static final String Time = "Time";
  private static MsgLogStore a;
  private static final String e = " And ";
  private static final String f = " Asc ";
  private static final String g = " Desc ";
  private static final String h = "MsgLogStore.db";
  private static final int i = 1;
  private static final String j = "MsgLogStore";
  private SQLiteDatabase b;
  private MsgLogStoreHelper c;
  private Context d;

  private MsgLogStore(Context paramContext)
  {
    this.d = paramContext.getApplicationContext();
    this.c = new MsgLogStoreHelper(paramContext);
    this.b = this.c.getWritableDatabase();
  }

  private void a()
  {
    if (MessageSharedPrefs.getInstance(this.d).h())
      return;
    File[] arrayOfFile = this.d.getCacheDir().listFiles(new MsgLogStore.1(this));
    if (arrayOfFile != null)
    {
      int m = arrayOfFile.length;
      int k = 0;
      while (k < m)
      {
        File localFile = arrayOfFile[k];
        a(localFile);
        localFile.delete();
        k += 1;
      }
    }
    MessageSharedPrefs.getInstance(this.d).i();
  }

  private void a(File paramFile)
  {
    try
    {
      paramFile = new JSONObject(b(paramFile));
      addLog(paramFile.optString("msg_id"), paramFile.optInt("action_type"), paramFile.optLong("ts"));
      return;
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
    }
  }

  // ERROR //
  private String b(File paramFile)
    throws java.io.IOException
  {
    // Byte code:
    //   0: new 139	java/io/BufferedReader
    //   3: dup
    //   4: new 141	java/io/FileReader
    //   7: dup
    //   8: aload_1
    //   9: invokespecial 143	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   12: invokespecial 146	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   15: astore_2
    //   16: new 148	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   23: astore_1
    //   24: aload_2
    //   25: invokevirtual 153	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnull +23 -> 53
    //   33: aload_1
    //   34: aload_3
    //   35: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: goto -15 -> 24
    //   42: astore_1
    //   43: aload_2
    //   44: ifnull +7 -> 51
    //   47: aload_2
    //   48: invokevirtual 160	java/io/BufferedReader:close	()V
    //   51: aload_1
    //   52: athrow
    //   53: aload_1
    //   54: invokevirtual 163	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: astore_1
    //   58: aload_2
    //   59: ifnull +7 -> 66
    //   62: aload_2
    //   63: invokevirtual 160	java/io/BufferedReader:close	()V
    //   66: aload_1
    //   67: areturn
    //   68: astore_2
    //   69: aload_2
    //   70: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   73: aload_1
    //   74: areturn
    //   75: astore_2
    //   76: aload_2
    //   77: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   80: goto -29 -> 51
    //   83: astore_1
    //   84: aconst_null
    //   85: astore_2
    //   86: goto -43 -> 43
    //
    // Exception table:
    //   from	to	target	type
    //   16	24	42	finally
    //   24	29	42	finally
    //   33	39	42	finally
    //   53	58	42	finally
    //   62	66	68	java/io/IOException
    //   47	51	75	java/io/IOException
    //   0	16	83	finally
  }

  public static MsgLogStore getInstance(Context paramContext)
  {
    if (a == null)
    {
      a = new MsgLogStore(paramContext);
      a.a();
    }
    return a;
  }

  public boolean addLog(String paramString, int paramInt, long paramLong)
  {
    if (TextUtils.isEmpty(paramString))
      return false;
    paramString = new MsgLog(paramString, paramInt, paramLong);
    if (this.b.insert("MsgLogStore", null, paramString.getContentValues()) != -1L);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public ArrayList<MsgLog> getMsgLogs(int paramInt)
  {
    if (paramInt < 1)
      return null;
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = this.b.query("MsgLogStore", null, null, null, null, null, "Time Asc ", paramInt + "");
    for (boolean bool = localCursor.moveToFirst(); bool; bool = localCursor.moveToNext())
      localArrayList.add(new MsgLog(localCursor));
    localCursor.close();
    return localArrayList;
  }

  public boolean removeLog(String paramString, int paramInt)
  {
    boolean bool = true;
    if (TextUtils.isEmpty(paramString))
      return false;
    String str = paramInt + "";
    if (this.b.delete("MsgLogStore", "MsgId=? And ActionType=?", new String[] { paramString, str }) == 1);
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public class MsgLog
  {
    public int actionType;
    public String msgId;
    public long time;

    public MsgLog(Cursor arg2)
    {
      Object localObject;
      this.msgId = localObject.getString(localObject.getColumnIndex("MsgId"));
      this.time = localObject.getLong(localObject.getColumnIndex("Time"));
      this.actionType = localObject.getInt(localObject.getColumnIndex("ActionType"));
    }

    public MsgLog(String paramInt, int paramLong, long arg4)
    {
      this.msgId = paramInt;
      this.actionType = paramLong;
      Object localObject;
      this.time = localObject;
    }

    public ContentValues getContentValues()
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("MsgId", this.msgId);
      localContentValues.put("Time", Long.valueOf(this.time));
      localContentValues.put("ActionType", Integer.valueOf(this.actionType));
      return localContentValues;
    }
  }

  private class MsgLogStoreHelper extends SQLiteOpenHelper
  {
    public MsgLogStoreHelper(Context arg2)
    {
      super("MsgLogStore.db", null, 1);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("create table if not exists MsgLogStore ( MsgId varchar, ActionType Integer, Time long, PRIMARY KEY(MsgId, ActionType))");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.MsgLogStore
 * JD-Core Version:    0.6.2
 */