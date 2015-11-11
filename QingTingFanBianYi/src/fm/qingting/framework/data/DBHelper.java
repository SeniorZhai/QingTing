package fm.qingting.framework.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DBHelper extends SQLiteOpenHelper
{
  protected Context context;
  private IDBHelperDelegate delegate;
  protected SQLiteDatabase.CursorFactory factory;
  Map<String, String> indexes;
  protected String name;
  Map<String, Map<String, String>> tables;
  protected int version;

  public DBHelper(Map<String, Map<String, String>> paramMap, Map<String, String> paramMap1, Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt, IDBHelperDelegate paramIDBHelperDelegate)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
    this.delegate = paramIDBHelperDelegate;
    this.context = paramContext;
    this.name = paramString;
    this.factory = paramCursorFactory;
    this.version = paramInt;
    this.tables = paramMap;
  }

  private void createDatabase(SQLiteDatabase paramSQLiteDatabase)
  {
    Iterator localIterator;
    if (this.tables != null)
    {
      localIterator = this.tables.keySet().iterator();
      if (localIterator.hasNext());
    }
    else if (this.indexes != null)
    {
      localIterator = this.indexes.keySet().iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return;
        str = (String)localIterator.next();
        paramSQLiteDatabase.execSQL(createTableSQL(str, (Map)this.tables.get(str)));
        break;
      }
      String str = (String)localIterator.next();
      paramSQLiteDatabase.execSQL(createIndexSQL(str, (String)this.indexes.get(str)));
    }
  }

  private String createIndexSQL(String paramString1, String paramString2)
  {
    return "create index " + paramString1 + " on " + paramString2;
  }

  private String createTableSQL(String paramString, Map<String, String> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("CREATE TABLE IF NOT EXISTS " + paramString + "(");
    paramString = paramMap.keySet().iterator();
    int i = 1;
    while (true)
    {
      if (!paramString.hasNext())
      {
        localStringBuilder.append(")");
        return localStringBuilder.toString();
      }
      if (i == 0)
        localStringBuilder.append(", ");
      i = 0;
      String str = (String)paramString.next();
      localStringBuilder.append(str);
      localStringBuilder.append(" ");
      localStringBuilder.append((String)paramMap.get(str));
    }
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    if ((this.delegate == null) || (!this.delegate.onCreate(paramSQLiteDatabase, this.name)))
      createDatabase(paramSQLiteDatabase);
    if (this.delegate != null)
      this.delegate.onCreateComplete(paramSQLiteDatabase, this.name);
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    if ((this.delegate == null) || (!this.delegate.onUpgrade(paramSQLiteDatabase, this.name, paramInt1, paramInt2)))
      updateDatabase(paramSQLiteDatabase, paramInt1, paramInt2);
    if (this.delegate != null)
      this.delegate.onUpgradeComplete(paramSQLiteDatabase, this.name, paramInt1, paramInt2);
  }

  public void setDelegate(IDBHelperDelegate paramIDBHelperDelegate)
  {
    this.delegate = paramIDBHelperDelegate;
  }

  protected void updateDatabase(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    Iterator localIterator = this.tables.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        createDatabase(paramSQLiteDatabase);
        return;
      }
      String str = (String)localIterator.next();
      paramSQLiteDatabase.execSQL("drop table if exists " + str);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.DBHelper
 * JD-Core Version:    0.6.2
 */