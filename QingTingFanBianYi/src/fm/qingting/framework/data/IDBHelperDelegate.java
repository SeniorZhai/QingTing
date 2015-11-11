package fm.qingting.framework.data;

import android.database.sqlite.SQLiteDatabase;

public abstract interface IDBHelperDelegate
{
  public abstract boolean onCreate(SQLiteDatabase paramSQLiteDatabase, String paramString);

  public abstract void onCreateComplete(SQLiteDatabase paramSQLiteDatabase, String paramString);

  public abstract boolean onUpgrade(SQLiteDatabase paramSQLiteDatabase, String paramString, int paramInt1, int paramInt2);

  public abstract void onUpgradeComplete(SQLiteDatabase paramSQLiteDatabase, String paramString, int paramInt1, int paramInt2);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.IDBHelperDelegate
 * JD-Core Version:    0.6.2
 */