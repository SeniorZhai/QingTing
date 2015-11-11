package fm.qingting.downloadnew;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DownloadDBHelper extends SQLiteOpenHelper
{
  static final String COL_CUR_SIZE = "download_size";
  static final String COL_EXTRA = "extra_info";
  static final String COL_FILE = "file";
  static final String COL_FINISH_TIME = "finish_time";
  static final String COL_ID = "_id";
  static final String COL_STATE = "state";
  static final String COL_TASK_ID = "task_id";
  static final String COL_TOTAL_SIZE = "total_size";
  static final String COL_URL = "url";
  private static final String DATABASE_NAME = "download.db";
  static final int DATABASE_VERSION = 1;
  static final String TABLE_NAME = "download_task";
  private static DownloadDBHelper helper;

  private DownloadDBHelper(Context paramContext)
  {
    super(paramContext, "download.db", null, 1);
  }

  public static DownloadDBHelper getInstance(Context paramContext)
  {
    try
    {
      if (helper == null)
        helper = new DownloadDBHelper(paramContext.getApplicationContext());
      paramContext = helper;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE download_task (_id INTEGER PRIMARY KEY, task_id TEXT, url TEXT, file TEXT, state INTEGER, total_size INTEGER, download_size INTEGER, finish_time INTEGER, extra_info TEXT  )");
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("drop table if exists download_task");
    onCreate(paramSQLiteDatabase);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.downloadnew.DownloadDBHelper
 * JD-Core Version:    0.6.2
 */