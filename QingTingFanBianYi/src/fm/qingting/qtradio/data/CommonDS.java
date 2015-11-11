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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CommonDS
  implements IDataSource
{
  private static CommonDS instance;

  private String acquireCommonRecord(DataCommand paramDataCommand)
  {
    paramDataCommand = (String)paramDataCommand.getParam().get("key");
    if (paramDataCommand == null)
      return null;
    while (true)
    {
      try
      {
        Object localObject = "select value from commonRecords where key = " + "'";
        paramDataCommand = (String)localObject + paramDataCommand;
        paramDataCommand = paramDataCommand + "'";
        localObject = DBManager.getInstance().getReadableDB("commonRecords").rawQuery(paramDataCommand, null);
        if (((Cursor)localObject).moveToNext())
        {
          paramDataCommand = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("value"));
          ((Cursor)localObject).close();
          return paramDataCommand;
        }
      }
      catch (Exception paramDataCommand)
      {
        return null;
      }
      paramDataCommand = null;
    }
  }

  private boolean deleteCommonRecord(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("commonRecords").execSQL("delete from commonRecords");
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
    localDataToken.setData(new Result(true, acquireCommonRecord(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteCommonRecord(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertCommonRecord(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateCommonRecord(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateGroupCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateGroupCommonRecord(paramDataCommand))));
    return localDataToken;
  }

  public static CommonDS getInstance()
  {
    if (instance == null)
      instance = new CommonDS();
    return instance;
  }

  private boolean insertCommonRecord(DataCommand paramDataCommand)
  {
    Object localObject = paramDataCommand.getParam();
    paramDataCommand = (String)((Map)localObject).get("type");
    String str = (String)((Map)localObject).get("value");
    localObject = (String)((Map)localObject).get("key");
    if ((paramDataCommand == null) || (str == null) || (localObject == null))
      return false;
    try
    {
      DBManager.getInstance().getWritableDB("commonRecords").execSQL("insert into commonRecords(key,type,value) values(?,?,?)", new Object[] { localObject, paramDataCommand, str });
      return true;
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  private boolean updateCommonRecord(DataCommand paramDataCommand)
  {
    Object localObject1 = paramDataCommand.getParam();
    Object localObject2 = (String)((Map)localObject1).get("type");
    String str = (String)((Map)localObject1).get("value");
    localObject1 = (String)((Map)localObject1).get("key");
    if ((localObject2 == null) || (str == null) || (localObject1 == null))
      return false;
    try
    {
      localObject2 = DBManager.getInstance().getWritableDB("commonRecords");
      Object localObject3 = "select value from commonRecords where key = " + "'";
      localObject3 = (String)localObject3 + (String)localObject1;
      localObject3 = ((SQLiteDatabase)localObject2).rawQuery((String)localObject3 + "'", null);
      if ((localObject3 == null) || (!((Cursor)localObject3).moveToNext()))
      {
        insertCommonRecord(paramDataCommand);
        return true;
      }
      ((Cursor)localObject3).close();
      paramDataCommand = "update commonRecords set value = " + "'";
      paramDataCommand = paramDataCommand + str;
      paramDataCommand = paramDataCommand + "'";
      paramDataCommand = paramDataCommand + " where key = ";
      paramDataCommand = paramDataCommand + "'";
      paramDataCommand = paramDataCommand + (String)localObject1;
      ((SQLiteDatabase)localObject2).execSQL(paramDataCommand + "'");
      return true;
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  private boolean updateGroupCommonRecord(DataCommand paramDataCommand)
  {
    Object localObject1 = (Map)paramDataCommand.getParam().get("items");
    if ((localObject1 == null) || (((Map)localObject1).size() == 0))
      return false;
    try
    {
      paramDataCommand = DBManager.getInstance().getWritableDB("commonRecords");
      paramDataCommand.beginTransaction();
      localObject1 = ((Map)localObject1).entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (Map.Entry)((Iterator)localObject1).next();
        String str = (String)((Map.Entry)localObject2).getValue();
        localObject2 = (String)((Map.Entry)localObject2).getKey();
        Object localObject3 = "select value from commonRecords where key = " + "'";
        localObject3 = (String)localObject3 + (String)localObject2;
        localObject3 = paramDataCommand.rawQuery((String)localObject3 + "'", null);
        if ((localObject3 == null) || (!((Cursor)localObject3).moveToNext()))
        {
          paramDataCommand.execSQL("insert into commonRecords(key,type,value) values(?,?,?)", new Object[] { localObject2, "", str });
        }
        else
        {
          ((Cursor)localObject3).close();
          localObject3 = "update commonRecords set value = " + "'";
          str = (String)localObject3 + str;
          str = str + "'";
          str = str + "where key = ";
          str = str + "'";
          localObject2 = str + (String)localObject2;
          paramDataCommand.execSQL((String)localObject2 + "'");
        }
      }
      paramDataCommand.setTransactionSuccessful();
      paramDataCommand.endTransaction();
      return true;
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "CommonDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_common_record"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_common_record"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_common_record"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_common_record"))
      return doUpdateCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_common_grouprecord"))
      return doUpdateGroupCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.CommonDS
 * JD-Core Version:    0.6.2
 */