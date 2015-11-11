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
import fm.qingting.qtradio.model.BillboardItemNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BillboardNodeDS
  implements IDataSource
{
  private static BillboardNodeDS instance;

  private List<BillboardItemNode> acquireBillboard(DataCommand paramDataCommand)
  {
    paramDataCommand = (String)paramDataCommand.getParam().get("type");
    if ((paramDataCommand == null) || (paramDataCommand.equalsIgnoreCase("")))
      return null;
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramDataCommand = "select * from billboardNodes where type = '" + paramDataCommand + "'";
      Cursor localCursor = DBManager.getInstance().getReadableDB("billboardNodes").rawQuery(paramDataCommand, null);
      Gson localGson = new Gson();
      DataCommand localDataCommand = null;
      if (localCursor.moveToNext())
      {
        int i = localCursor.getColumnIndex("BillboardItemNode");
        int j = localCursor.getColumnIndex("nodeName");
        String str1 = localCursor.getString(localCursor.getColumnIndex("node"));
        paramDataCommand = localCursor.getString(i);
        String str2 = localCursor.getString(j);
        BillboardItemNode localBillboardItemNode = (BillboardItemNode)getNode(localGson, paramDataCommand, BillboardItemNode.class);
        if (str2.equalsIgnoreCase("channel"))
          paramDataCommand = getNode(localGson, str1, ChannelNode.class);
        while (true)
        {
          localDataCommand = paramDataCommand;
          if (paramDataCommand == null)
            break;
          paramDataCommand.parent = localBillboardItemNode;
          localBillboardItemNode.setDetail(paramDataCommand);
          localArrayList.add(localBillboardItemNode);
          localDataCommand = paramDataCommand;
          break;
          paramDataCommand = localDataCommand;
          if (str2.equalsIgnoreCase("program"))
            paramDataCommand = getNode(localGson, str1, ProgramNode.class);
        }
      }
      localCursor.close();
      return localArrayList;
    }
    catch (Exception paramDataCommand)
    {
    }
    return null;
  }

  private boolean deleteBillboard(DataCommand paramDataCommand)
  {
    paramDataCommand = (String)paramDataCommand.getParam().get("type");
    if ((paramDataCommand == null) || (paramDataCommand.equalsIgnoreCase("")))
      return false;
    try
    {
      paramDataCommand = "delete from billboardNodes where type = '" + paramDataCommand + "'";
      DBManager.getInstance().getWritableDB("billboardNodes").execSQL(paramDataCommand);
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
    localDataToken.setData(new Result(true, acquireBillboard(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteBillboard(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertBillboard(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateBillboard(paramDataCommand))));
    return localDataToken;
  }

  public static BillboardNodeDS getInstance()
  {
    if (instance == null)
      instance = new BillboardNodeDS();
    return instance;
  }

  private <T> Node getNode(Gson paramGson, String paramString, Class<T> paramClass)
  {
    if (paramString == null)
      return null;
    try
    {
      paramGson = (Node)paramGson.fromJson(paramString, paramClass);
      return paramGson;
    }
    catch (Exception paramGson)
    {
    }
    return null;
  }

  private boolean insertBillboard(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    int j = ((Integer)paramDataCommand.get("type")).intValue();
    paramDataCommand = (List)paramDataCommand.get("nodes");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("billboardNodes");
      localSQLiteDatabase.beginTransaction();
      Gson localGson = new Gson();
      int i = 0;
      while (true)
      {
        if (i < paramDataCommand.size())
        {
          BillboardItemNode localBillboardItemNode = (BillboardItemNode)paramDataCommand.get(i);
          String str1 = localGson.toJson(localBillboardItemNode);
          String str2 = localGson.toJson(localBillboardItemNode.mNode);
          if (localBillboardItemNode.mNode != null)
            localSQLiteDatabase.execSQL("insert into billboardNodes(BillboardItemNode,node,nodeName,type) values(?,?,?,?)", new Object[] { str1, str2, localBillboardItemNode.mNode.nodeName, Integer.valueOf(j) });
        }
        else
        {
          localSQLiteDatabase.setTransactionSuccessful();
          localSQLiteDatabase.endTransaction();
          return true;
        }
        i += 1;
      }
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  private boolean updateBillboard(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    int j = ((Integer)paramDataCommand.get("type")).intValue();
    paramDataCommand = (List)paramDataCommand.get("nodes");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("billboardNodes");
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("delete from billboardNodes where type = '" + j + "'");
      Gson localGson = new Gson();
      int i = 0;
      while (true)
      {
        if (i < paramDataCommand.size())
        {
          BillboardItemNode localBillboardItemNode = (BillboardItemNode)paramDataCommand.get(i);
          String str1 = localGson.toJson(localBillboardItemNode);
          String str2 = localGson.toJson(localBillboardItemNode.mNode);
          if (localBillboardItemNode.mNode != null)
            localSQLiteDatabase.execSQL("insert into billboardNodes(BillboardItemNode,node,nodeName,type) values(?,?,?,?)", new Object[] { str1, str2, localBillboardItemNode.mNode.nodeName, Integer.valueOf(j) });
        }
        else
        {
          localSQLiteDatabase.setTransactionSuccessful();
          localSQLiteDatabase.endTransaction();
          return true;
        }
        i += 1;
      }
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
    return "BillboardNodeDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_billboarditem"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_billboarditem"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_billboarditem"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_billboarditem"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.BillboardNodeDS
 * JD-Core Version:    0.6.2
 */