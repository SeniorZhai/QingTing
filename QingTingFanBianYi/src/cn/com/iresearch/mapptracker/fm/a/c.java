package cn.com.iresearch.mapptracker.fm.a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

final class c extends SQLiteOpenHelper
{
  public c(Context paramContext, String paramString, int paramInt)
  {
    super(paramContext, paramString, null, paramInt);
  }

  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
  }

  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    Cursor localCursor = paramSQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type ='table'", null);
    if (localCursor != null);
    while (true)
    {
      if (!localCursor.moveToNext())
      {
        if (localCursor != null)
          localCursor.close();
        return;
      }
      paramSQLiteDatabase.execSQL("DROP TABLE " + localCursor.getString(0));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.a.c
 * JD-Core Version:    0.6.2
 */