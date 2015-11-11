package fm.qingting.qtradio.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fm.qingting.framework.data.DBHelper;
import java.util.HashMap;
import java.util.Map;

public class ChannelLastPushDB
{
  private static final String ChannelID = "channel_id";
  private static final String TableName = "channel_last_push";
  private static final String Time = "last_push_time";
  private Context _context;
  private DBHelper helper;

  public ChannelLastPushDB(Context paramContext)
  {
    this._context = paramContext;
    create();
  }

  private void create()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("last_push_time", "integer");
    localHashMap2.put("channel_id", "varchar(30)");
    localHashMap1.put("channel_last_push", localHashMap2);
    this.helper = new DBHelper(localHashMap1, null, this._context, "channel_last_push", null, 1, null);
  }

  private static void log(String paramString)
  {
    Log.i("ChannelLastPushDB", paramString);
  }

  public long get(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = this.helper.getReadableDatabase();
    try
    {
      paramString = localSQLiteDatabase.rawQuery("select * from channel_last_push where " + "channel_id ='" + paramString + "'", null);
      if (paramString.moveToNext())
      {
        long l = paramString.getLong(paramString.getColumnIndex("last_push_time"));
        paramString.close();
        return l;
      }
      paramString.close();
      localSQLiteDatabase.close();
      return 0L;
    }
    catch (Exception paramString)
    {
      while (true)
        log("[getPlayRecords error]" + paramString);
    }
  }

  public long set(String paramString, long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
    try
    {
      int i = localSQLiteDatabase.delete("channel_last_push", "channel_id = ?", new String[] { paramString });
      log("remove " + i);
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("last_push_time", Long.valueOf(paramLong));
      localContentValues.put("channel_id", paramString);
      long l = localSQLiteDatabase.insertOrThrow("channel_last_push", null, localContentValues);
      log("[save] " + paramString + "," + paramLong);
      return l;
    }
    catch (Exception paramString)
    {
      log("[save failed]:" + paramString.getLocalizedMessage());
    }
    return -1L;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.stat.ChannelLastPushDB
 * JD-Core Version:    0.6.2
 */