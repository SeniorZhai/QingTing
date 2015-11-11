package fm.qingting.qtradio.data;

import android.database.sqlite.SQLiteDatabase;

class SQLDbCache
{
  private SQLiteDatabase mDb;
  private long mLastUsedTime;
  private boolean mWritable;

  public SQLDbCache(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean, long paramLong)
  {
    this.mDb = paramSQLiteDatabase;
    this.mWritable = paramBoolean;
    this.mLastUsedTime = paramLong;
  }

  public SQLiteDatabase getDb()
  {
    return this.mDb;
  }

  public long getTimeInterval(long paramLong)
  {
    return paramLong - this.mLastUsedTime;
  }

  public boolean isWritable()
  {
    return this.mWritable;
  }

  public void setTime(long paramLong)
  {
    this.mLastUsedTime = paramLong;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.SQLDbCache
 * JD-Core Version:    0.6.2
 */