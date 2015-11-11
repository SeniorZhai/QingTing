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
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.FrontPageNode;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import java.util.List;
import java.util.Map;

public class FrontPageNodeDS
  implements IDataSource
{
  private static FrontPageNodeDS instance;

  private FrontPageNode acquireFrontPage(DataCommand paramDataCommand)
  {
    try
    {
      FrontPageNode localFrontPageNode = new FrontPageNode();
      Cursor localCursor = DBManager.getInstance().getReadableDB("frontpageNodes").rawQuery("select * from frontpageNodes", null);
      Gson localGson = new Gson();
      DataCommand localDataCommand = null;
      while (localCursor.moveToNext())
      {
        int i = localCursor.getColumnIndex("RecommendItemNode");
        int j = localCursor.getColumnIndex("nodeName");
        int k = localCursor.getColumnIndex("type");
        String str1 = localCursor.getString(localCursor.getColumnIndex("node"));
        paramDataCommand = localCursor.getString(i);
        String str2 = localCursor.getString(j);
        i = localCursor.getInt(k);
        RecommendItemNode localRecommendItemNode = (RecommendItemNode)getNode(localGson, paramDataCommand, RecommendItemNode.class);
        if (str2.equalsIgnoreCase("channel"))
          paramDataCommand = getNode(localGson, str1, ChannelNode.class);
        while (true)
        {
          localDataCommand = paramDataCommand;
          if (paramDataCommand == null)
            break;
          localRecommendItemNode.setDetail(paramDataCommand);
          if (i != 0)
            break label277;
          localFrontPageNode.addToRecommendList(localRecommendItemNode);
          localDataCommand = paramDataCommand;
          break;
          if (str2.equalsIgnoreCase("category"))
          {
            paramDataCommand = getNode(localGson, str1, CategoryNode.class);
          }
          else
          {
            paramDataCommand = localDataCommand;
            if (!str2.equalsIgnoreCase("subcategory"))
              if (str2.equalsIgnoreCase("program"))
              {
                paramDataCommand = getNode(localGson, str1, ProgramNode.class);
              }
              else
              {
                paramDataCommand = localDataCommand;
                if (str2.equalsIgnoreCase("activity"))
                  paramDataCommand = getNode(localGson, str1, ActivityNode.class);
              }
          }
        }
        label277: localDataCommand = paramDataCommand;
        if (i == 1)
        {
          localFrontPageNode.addToShowList(localRecommendItemNode);
          localDataCommand = paramDataCommand;
        }
      }
      localCursor.close();
      return localFrontPageNode;
    }
    catch (Exception paramDataCommand)
    {
    }
    return null;
  }

  private boolean delInsertFrontPage(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    Object localObject1 = (List)paramDataCommand.get("banner");
    paramDataCommand = (List)paramDataCommand.get("showlist");
    while (true)
    {
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("frontpageNodes");
        localSQLiteDatabase.beginTransaction();
        localSQLiteDatabase.execSQL("delete from frontpageNodes");
        Gson localGson = new Gson();
        i = 0;
        if ((localObject1 != null) && (i < ((List)localObject1).size()))
        {
          Object localObject2 = (RecommendItemNode)((List)localObject1).get(i);
          String str1 = localGson.toJson(localObject2);
          String str2 = localGson.toJson(((RecommendItemNode)localObject2).mNode);
          if (((RecommendItemNode)localObject2).mNode != null)
          {
            localSQLiteDatabase.execSQL("insert into frontpageNodes(RecommendItemNode,node,nodeName,type) values(?,?,?,?)", new Object[] { str1, str2, ((RecommendItemNode)localObject2).mNode.nodeName, Integer.valueOf(0) });
            continue;
            if ((paramDataCommand != null) && (i < paramDataCommand.size()))
            {
              localObject1 = (RecommendItemNode)paramDataCommand.get(i);
              localObject2 = localGson.toJson(localObject1);
              if (((RecommendItemNode)localObject1).mNode == null)
                break label285;
              localSQLiteDatabase.execSQL("insert into frontpageNodes(RecommendItemNode,node,nodeName,type) values(?,?,?,?)", new Object[] { localObject2, localGson.toJson(((RecommendItemNode)localObject1).mNode), ((RecommendItemNode)localObject1).mNode.nodeName, Integer.valueOf(1) });
              break label285;
            }
            localSQLiteDatabase.setTransactionSuccessful();
            localSQLiteDatabase.endTransaction();
            return true;
          }
          i += 1;
          continue;
        }
      }
      catch (Exception paramDataCommand)
      {
        return false;
      }
      int i = 0;
      continue;
      label285: i += 1;
    }
  }

  private boolean deleteFrontPage(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("frontpageNodes").execSQL("delete from frontpageNodes");
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
    localDataToken.setData(new Result(true, acquireFrontPage(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDelInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(delInsertFrontPage(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteFrontPage(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertFrontPage(paramDataCommand))));
    return localDataToken;
  }

  public static FrontPageNodeDS getInstance()
  {
    if (instance == null)
      instance = new FrontPageNodeDS();
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

  private boolean insertFrontPage(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    Object localObject1 = (List)paramDataCommand.get("banner");
    paramDataCommand = (List)paramDataCommand.get("showlist");
    while (true)
    {
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("frontpageNodes");
        localSQLiteDatabase.beginTransaction();
        Gson localGson = new Gson();
        i = 0;
        if ((localObject1 != null) && (i < ((List)localObject1).size()))
        {
          Object localObject2 = (RecommendItemNode)((List)localObject1).get(i);
          String str = localGson.toJson(localObject2);
          if (((RecommendItemNode)localObject2).mNode != null)
          {
            localSQLiteDatabase.execSQL("insert into frontpageNodes(RecommendItemNode,node,nodeName,type) values(?,?,?,?)", new Object[] { str, localGson.toJson(((RecommendItemNode)localObject2).mNode), ((RecommendItemNode)localObject2).mNode.nodeName, Integer.valueOf(0) });
            continue;
            if ((paramDataCommand != null) && (i < paramDataCommand.size()))
            {
              localObject1 = (RecommendItemNode)paramDataCommand.get(i);
              localObject2 = localGson.toJson(localObject1);
              if (((RecommendItemNode)localObject1).mNode == null)
                break label275;
              localSQLiteDatabase.execSQL("insert into frontpageNodes(RecommendItemNode,node,nodeName,type) values(?,?,?,?)", new Object[] { localObject2, localGson.toJson(((RecommendItemNode)localObject1).mNode), ((RecommendItemNode)localObject1).mNode.nodeName, Integer.valueOf(1) });
              break label275;
            }
            localSQLiteDatabase.setTransactionSuccessful();
            localSQLiteDatabase.endTransaction();
            return true;
          }
          i += 1;
          continue;
        }
      }
      catch (Exception paramDataCommand)
      {
        return false;
      }
      int i = 0;
      continue;
      label275: i += 1;
    }
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "FrontPageNodeDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_frontpage_node"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_frontpage_node"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_frontpage_node"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("delinsertdb_frontpage_node"))
      return doDelInsertCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.FrontPageNodeDS
 * JD-Core Version:    0.6.2
 */