package fm.qingting.qtradio.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fm.qingting.framework.data.DataCommand;
import fm.qingting.framework.data.DataToken;
import fm.qingting.framework.data.IDataParser;
import fm.qingting.framework.data.IDataRecvHandler;
import fm.qingting.framework.data.IDataSource;
import fm.qingting.framework.data.IDataToken;
import fm.qingting.framework.data.Result;
import java.util.Map;

public class ProfileDS
  implements IDataSource
{
  private static ProfileDS instance;

  private DataToken doGetClientIDCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    paramDataCommand = getClientID();
    if (paramDataCommand != null);
    for (paramDataCommand = new Result(true, paramDataCommand); ; paramDataCommand = new Result(false, null))
    {
      localDataToken.setData(paramDataCommand);
      return localDataToken;
    }
  }

  private DataToken doGetValueCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    paramDataCommand = getValue((String)paramDataCommand.getParam().get("key"));
    if (paramDataCommand != null);
    for (paramDataCommand = new Result(true, paramDataCommand); ; paramDataCommand = new Result(false, null, "", ""))
    {
      localDataToken.setData(paramDataCommand);
      return localDataToken;
    }
  }

  private DataToken doUpdateClientIDCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    updateClientID((String)paramDataCommand.getParam().get("clientid"));
    localDataToken.setData(new Result(true, null));
    return localDataToken;
  }

  private DataToken doUpdateValueCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    paramDataCommand = paramDataCommand.getParam();
    updateValue((String)paramDataCommand.get("key"), (String)paramDataCommand.get("value"));
    localDataToken.setData(new Result(true, (String)paramDataCommand.get("value")));
    return localDataToken;
  }

  private String getClientID()
  {
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject2 = localObject4;
    try
    {
      Cursor localCursor = DBManager.getInstance().getReadableDB("profile").rawQuery("select key, value from profile", null);
      do
      {
        localObject1 = localObject3;
        localObject2 = localObject4;
        if (!localCursor.moveToNext())
          break;
        localObject2 = localObject4;
      }
      while (!localCursor.getString(0).equalsIgnoreCase("clientid"));
      localObject2 = localObject4;
      Object localObject1 = localCursor.getString(1);
      localObject2 = localObject1;
      localCursor.close();
      return localObject1;
    }
    catch (Exception localException)
    {
    }
    return localObject2;
  }

  public static ProfileDS getInstance()
  {
    if (instance == null)
      instance = new ProfileDS();
    return instance;
  }

  private String getValue(String paramString)
  {
    try
    {
      Object localObject = String.format("select key, value from profile where key='%s'", new Object[] { paramString.replace("'", "''") });
      paramString = DBManager.getInstance().getReadableDB("profile");
      localObject = paramString.rawQuery((String)localObject, null);
      if (((Cursor)localObject).isAfterLast())
      {
        ((Cursor)localObject).close();
        paramString.close();
        return null;
      }
      ((Cursor)localObject).moveToNext();
      paramString = ((Cursor)localObject).getString(1);
      ((Cursor)localObject).close();
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  private void updateClientID(String paramString)
  {
    for (int i = 1; ; i = 0)
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("profile");
        Cursor localCursor = localSQLiteDatabase.rawQuery("select key, value from profile where key='clientid'", null);
        if (!localCursor.isAfterLast())
        {
          localCursor.close();
          if (i != 0);
          for (paramString = String.format("update profile set value = '%s' where key = 'clientid'", new Object[] { paramString }); ; paramString = String.format("insert into profile(key, value) values('clientid', '%s')", new Object[] { paramString }))
          {
            localSQLiteDatabase.execSQL(paramString);
            return;
          }
        }
      }
      catch (Exception paramString)
      {
        return;
      }
  }

  private void updateValue(String paramString1, String paramString2)
  {
    int i = 1;
    if (paramString1 == null)
      return;
    String str = paramString2;
    if (paramString2 == null)
      str = "";
    while (true)
    {
      try
      {
        Object localObject = String.format("select key, value from profile where key='%s'", new Object[] { paramString1.replace("'", "''") });
        paramString2 = DBManager.getInstance().getWritableDB("profile");
        localObject = paramString2.rawQuery((String)localObject, null);
        if (!((Cursor)localObject).isAfterLast())
        {
          ((Cursor)localObject).close();
          if (i != 0)
          {
            paramString1 = String.format("update profile set value = '%s' where key = '%s'", new Object[] { str.replace("'", "''"), paramString1.replace("'", "''") });
            paramString2.execSQL(paramString1);
            return;
          }
          paramString1 = String.format("insert into profile(key, value) values('%s', '%s')", new Object[] { paramString1.replace("'", "''"), str.replace("'", "''") });
          continue;
        }
      }
      catch (Exception paramString1)
      {
        return;
      }
      i = 0;
    }
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "Profile";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("getClientID"))
      return doGetClientIDCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updateClientID"))
      return doUpdateClientIDCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getValue"))
      return doGetValueCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updateValue"))
      return doUpdateValueCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.ProfileDS
 * JD-Core Version:    0.6.2
 */