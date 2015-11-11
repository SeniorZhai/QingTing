package fm.qingting.qtradio.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fm.qingting.framework.data.DBHelper;
import java.util.HashMap;
import java.util.Map;

public class ListenedProgramDB
{
  private static final String ChannelID = "channel_id";
  private static final String ProgramID = "program_id";
  private static final String TableName = "listened_program";
  private static final String Time = "play_time";
  private Context _context;
  private DBHelper helper;

  public ListenedProgramDB(Context paramContext)
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
    localHashMap2.put("channel_id", "varchar(30)");
    localHashMap2.put("program_id", "varchar(30)");
    localHashMap2.put("play_time", "integer");
    localHashMap1.put("listened_program", localHashMap2);
    this.helper = new DBHelper(localHashMap1, null, this._context, "listened_program", null, 1, null);
  }

  private static void log(String paramString)
  {
    Log.i("ListenedProgramDB", paramString);
  }

  public HashMap<String, Long> getPlayedPrograms(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = this.helper.getReadableDatabase();
    HashMap localHashMap = new HashMap();
    String str;
    long l;
    try
    {
      paramString = localSQLiteDatabase.rawQuery("select * from listened_program where " + "channel_id ='" + paramString + "'", null);
      while (true)
      {
        if (!paramString.moveToNext())
          break label178;
        str = paramString.getString(paramString.getColumnIndex("program_id"));
        l = paramString.getLong(paramString.getColumnIndex("play_time"));
        if (localHashMap.containsKey(str))
          break;
        localHashMap.put(str, Long.valueOf(l));
      }
    }
    catch (Exception paramString)
    {
      log("[getPlayRecords error]" + paramString);
    }
    while (true)
    {
      localSQLiteDatabase.close();
      return localHashMap;
      if (((Long)localHashMap.get(str)).longValue() >= l)
        break;
      localHashMap.put(str, Long.valueOf(l));
      break;
      label178: paramString.close();
    }
  }

  public long set(String paramString1, String paramString2, long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
    try
    {
      localSQLiteDatabase.delete("listened_program", "channel_id = ?  and program_id = ? ", new String[] { paramString1, paramString2 });
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("channel_id", paramString1);
      localContentValues.put("program_id", paramString2);
      localContentValues.put("play_time", Long.valueOf(paramLong));
      paramLong = localSQLiteDatabase.insertOrThrow("listened_program", null, localContentValues);
      log("[save] " + paramString1 + "," + paramString2);
      return paramLong;
    }
    catch (Exception paramString1)
    {
      log("[save failed]:" + paramString1.getLocalizedMessage());
    }
    return 0L;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.stat.ListenedProgramDB
 * JD-Core Version:    0.6.2
 */