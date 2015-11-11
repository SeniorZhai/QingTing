package fm.qingting.qtradio.log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.gson.Gson;
import fm.qingting.framework.data.DBHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LogDB
{
  private static final String Content = "content";
  private static final String ID = "id";
  private static final String Meta = "meta";
  private static String TableName = "qtlogDB";
  private static final String Time = "time";
  private static final String Type = "type";
  private Context _context;
  private DBHelper _logHelper;

  public LogDB(Context paramContext)
  {
    this._context = paramContext;
    createDB();
  }

  private void createDB()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "INTEGER PRIMARY KEY AUTOINCREMENT");
    localHashMap2.put("time", "VARCHAR(20)");
    localHashMap2.put("type", "VARCHAR(100)");
    localHashMap2.put("content", "VARCHAR(500)");
    localHashMap2.put("meta", "VARCHAR(200)");
    localHashMap1.put(TableName, localHashMap2);
    this._logHelper = new DBHelper(localHashMap1, null, this._context, TableName, null, 1, null);
  }

  private static void log(String paramString)
  {
  }

  public List<LogBean> fetch(int paramInt)
  {
    Object localObject1 = this._logHelper.getReadableDatabase();
    LinkedList localLinkedList = new LinkedList();
    try
    {
      localObject1 = ((SQLiteDatabase)localObject1).rawQuery("select * from " + TableName + " order by " + "id" + " desc limit 0," + paramInt, null);
      Gson localGson = new Gson();
      while (((Cursor)localObject1).moveToNext())
      {
        Object localObject2 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("meta"));
        localObject2 = new LogBean(((Cursor)localObject1).getLong(((Cursor)localObject1).getColumnIndex("time")), ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("type")), ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("content")), (LogMeta)localGson.fromJson((String)localObject2, LogMeta.class));
        ((LogBean)localObject2).setId(((Cursor)localObject1).getLong(((Cursor)localObject1).getColumnIndex("id")));
        localLinkedList.add(localObject2);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return localLinkedList;
    }
    localException.close();
    return localLinkedList;
  }

  public List<Long> remove(List<Long> paramList)
  {
    if ((paramList == null) || (paramList.size() <= 0))
      return null;
    SQLiteDatabase localSQLiteDatabase = this._logHelper.getWritableDatabase();
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Long localLong = (Long)paramList.next();
      if (localSQLiteDatabase.delete(TableName, "ID = ? ", new String[] { String.valueOf(localLong) }) != 1)
        localArrayList.add(localLong);
    }
    return localArrayList;
  }

  public long store(LogBean paramLogBean)
  {
    if (paramLogBean == null)
    {
      log("logbean==null.Don't write log");
      return -1L;
    }
    try
    {
      SQLiteDatabase localSQLiteDatabase = this._logHelper.getWritableDatabase();
      Gson localGson = new Gson();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("time", Long.valueOf(paramLogBean.getTime()));
      localContentValues.put("type", paramLogBean.getType());
      localContentValues.put("content", paramLogBean.getContent());
      if (paramLogBean.getMeta() != null)
        localContentValues.put("meta", localGson.toJson(paramLogBean.getMeta()));
      long l = localSQLiteDatabase.insert(TableName, "id", localContentValues);
      log("[store   done][rowid:" + l + "]" + paramLogBean);
      return l;
    }
    catch (Exception localException)
    {
      log("[store failed]" + paramLogBean);
      localException.printStackTrace();
    }
    return -1L;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.log.LogDB
 * JD-Core Version:    0.6.2
 */