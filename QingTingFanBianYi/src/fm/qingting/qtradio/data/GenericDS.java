package fm.qingting.qtradio.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.gson.Gson;
import fm.qingting.framework.data.DataCommand;
import fm.qingting.framework.data.DataToken;
import fm.qingting.framework.data.IDataParser;
import fm.qingting.framework.data.IDataRecvHandler;
import fm.qingting.framework.data.IDataSource;
import fm.qingting.framework.data.IDataToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.pushcontent.LiveChannelInfoBean;
import java.util.Map;

public class GenericDS
  implements IDataSource
{
  private static GenericDS instance;

  private Object acquireGenericObj(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    String str = (String)paramDataCommand.get("type");
    paramDataCommand = (String)paramDataCommand.get("id");
    if ((str == null) || (paramDataCommand == null))
      return null;
    try
    {
      paramDataCommand = "select value from genericObjs where type = '" + str + "'" + " and id=" + "'" + paramDataCommand + "'";
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getReadableDB("genericObjs");
      Cursor localCursor = localSQLiteDatabase.rawQuery(paramDataCommand, null);
      Gson localGson = new Gson();
      if (localCursor.moveToNext())
      {
        paramDataCommand = localCursor.getString(localCursor.getColumnIndex("value"));
        boolean bool = str.equalsIgnoreCase("livechannelinfobean");
        if (!bool);
      }
      while (true)
      {
        try
        {
          paramDataCommand = (LiveChannelInfoBean)localGson.fromJson(paramDataCommand, LiveChannelInfoBean.class);
          localCursor.close();
          localSQLiteDatabase.close();
          return paramDataCommand;
        }
        catch (Exception paramDataCommand)
        {
          paramDataCommand.printStackTrace();
        }
        break;
        paramDataCommand = null;
      }
    }
    catch (Exception paramDataCommand)
    {
    }
    return null;
  }

  private boolean deleteGenericObj(DataCommand paramDataCommand)
  {
    try
    {
      paramDataCommand = DBManager.getInstance().getWritableDB("genericObjs");
      paramDataCommand.execSQL("delete from genericObjs");
      paramDataCommand.close();
      return true;
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  private DataToken doAcquireCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, acquireGenericObj(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteGenericObj(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateGenericObj(paramDataCommand))));
    return localDataToken;
  }

  public static GenericDS getInstance()
  {
    if (instance == null)
      instance = new GenericDS();
    return instance;
  }

  private boolean updateGenericObj(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    String str1 = (String)paramDataCommand.get("type");
    String str2 = (String)paramDataCommand.get("id");
    if ((str1 == null) || (str2 == null))
      return false;
    if (str1.equalsIgnoreCase("livechannelinfobean"))
    {
      paramDataCommand = (LiveChannelInfoBean)paramDataCommand.get("value");
      if (paramDataCommand == null);
    }
    for (paramDataCommand = new Gson().toJson(paramDataCommand); ; paramDataCommand = null)
    {
      if (paramDataCommand == null)
        return false;
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("genericObjs");
        String str3 = "delete from genericObjs where type = '" + str1 + "'" + " and id=" + "'" + str2 + "'";
        localSQLiteDatabase.beginTransaction();
        localSQLiteDatabase.execSQL(str3);
        localSQLiteDatabase.execSQL("insert into genericObjs(id,type,value) values(?,?,?)", new Object[] { str2, str1, paramDataCommand });
        localSQLiteDatabase.close();
        return true;
      }
      catch (Exception paramDataCommand)
      {
        return false;
      }
    }
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "GenericDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_generic_obj"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_generic_obj"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_generic_obj"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.GenericDS
 * JD-Core Version:    0.6.2
 */