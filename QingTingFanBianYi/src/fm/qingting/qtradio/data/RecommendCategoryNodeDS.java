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
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RecommendCategoryNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import java.util.List;
import java.util.Map;

public class RecommendCategoryNodeDS
  implements IDataSource
{
  private static RecommendCategoryNodeDS instance;

  private RecommendCategoryNode acquireRecCategory(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    int i = ((Integer)paramDataCommand.get("id")).intValue();
    Object localObject = (String)paramDataCommand.get("type");
    try
    {
      RecommendCategoryNode localRecommendCategoryNode = new RecommendCategoryNode();
      paramDataCommand = "select * from recCategoryNodes where catid = '" + i + "'";
      if (localObject != null)
        paramDataCommand = paramDataCommand + " and type = '" + (String)localObject + "'";
      while (true)
      {
        Cursor localCursor = DBManager.getInstance().getReadableDB("recCategoryNodes").rawQuery(paramDataCommand, null);
        Gson localGson = new Gson();
        localObject = null;
        if (localCursor.moveToNext())
        {
          int j = localCursor.getColumnIndex("RecommendItemNode");
          int k = localCursor.getColumnIndex("nodeName");
          int m = localCursor.getColumnIndex("type");
          String str1 = localCursor.getString(localCursor.getColumnIndex("node"));
          paramDataCommand = localCursor.getString(j);
          String str2 = localCursor.getString(k);
          j = localCursor.getInt(m);
          RecommendItemNode localRecommendItemNode = (RecommendItemNode)getNode(localGson, paramDataCommand, RecommendItemNode.class);
          if (str2.equalsIgnoreCase("channel"))
            paramDataCommand = getNode(localGson, str1, ChannelNode.class);
          while (true)
          {
            if (localRecommendCategoryNode.sectionId == -1)
              localRecommendCategoryNode.sectionId = Integer.valueOf(i).intValue();
            localObject = paramDataCommand;
            if (paramDataCommand == null)
              break;
            localRecommendItemNode.setDetail(paramDataCommand);
            localRecommendCategoryNode.insertRecCategory(localRecommendItemNode, j);
            localObject = paramDataCommand;
            break;
            if (str2.equalsIgnoreCase("category"))
            {
              paramDataCommand = getNode(localGson, str1, CategoryNode.class);
            }
            else
            {
              paramDataCommand = (DataCommand)localObject;
              if (!str2.equalsIgnoreCase("subcategory"))
                if (str2.equalsIgnoreCase("program"))
                {
                  paramDataCommand = getNode(localGson, str1, ProgramNode.class);
                }
                else
                {
                  paramDataCommand = (DataCommand)localObject;
                  if (str2.equalsIgnoreCase("activity"))
                    paramDataCommand = getNode(localGson, str1, ActivityNode.class);
                }
            }
          }
        }
        localCursor.close();
        return localRecommendCategoryNode;
      }
    }
    catch (Exception paramDataCommand)
    {
    }
    return null;
  }

  private boolean deleteRecCategory(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("recCategoryNodes").execSQL("delete from recCategoryNodes");
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
    localDataToken.setData(new Result(true, acquireRecCategory(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteRecCategory(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertRecCategory(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateRecCategory(paramDataCommand))));
    return localDataToken;
  }

  public static RecommendCategoryNodeDS getInstance()
  {
    if (instance == null)
      instance = new RecommendCategoryNodeDS();
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

  private boolean insertRecCategory(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    Object localObject1 = (String)paramDataCommand.get("type");
    if (localObject1 == null)
      return false;
    int k = ((Integer)paramDataCommand.get("id")).intValue();
    if (((String)localObject1).equalsIgnoreCase("l"))
    {
      localObject1 = (List)paramDataCommand.get("nodes");
      paramDataCommand = null;
    }
    while (true)
    {
      int i;
      int j;
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("recCategoryNodes");
        localSQLiteDatabase.beginTransaction();
        Gson localGson = new Gson();
        Object localObject2;
        String str1;
        if ((localObject1 != null) && (((List)localObject1).size() > 0))
        {
          i = 0;
          if ((localObject1 != null) && (i < ((List)localObject1).size()))
          {
            localObject2 = (RecommendItemNode)((List)localObject1).get(i);
            str1 = localGson.toJson(localObject2);
            String str2 = localGson.toJson(((RecommendItemNode)localObject2).mNode);
            Node localNode = ((RecommendItemNode)localObject2).mNode;
            if (localNode == null)
            {
              i += 1;
              continue;
              if (!((String)localObject1).equalsIgnoreCase("ll"))
                continue;
              paramDataCommand = (List)paramDataCommand.get("nodes");
              localObject1 = null;
              continue;
            }
            localSQLiteDatabase.execSQL("insert into recCategoryNodes(RecommendItemNode,node,nodeName,catid,type) values(?,?,?,?,?)", new Object[] { str1, str2, ((RecommendItemNode)localObject2).mNode.nodeName, Integer.valueOf(k), Integer.valueOf(0) });
            continue;
          }
        }
        if ((paramDataCommand != null) && (paramDataCommand.size() > 0))
        {
          i = 0;
          if (i < paramDataCommand.size())
          {
            if (paramDataCommand.get(i) == null)
              break label435;
            j = 0;
            if (j >= ((List)paramDataCommand.get(i)).size())
              break label435;
            localObject1 = (RecommendItemNode)((List)paramDataCommand.get(i)).get(j);
            localObject2 = localGson.toJson(localObject1);
            str1 = localGson.toJson(((RecommendItemNode)localObject1).mNode);
            if (((RecommendItemNode)localObject1).mNode == null)
              break label426;
            localSQLiteDatabase.execSQL("insert into recCategoryNodes(RecommendItemNode,node,nodeName,catid,type) values(?,?,?,?,?)", new Object[] { localObject2, str1, ((RecommendItemNode)localObject1).mNode.nodeName, Integer.valueOf(k), Integer.valueOf(1) });
            break label426;
          }
        }
        localSQLiteDatabase.setTransactionSuccessful();
        localSQLiteDatabase.endTransaction();
        return true;
        localObject1 = null;
        paramDataCommand = null;
        continue;
      }
      catch (Exception paramDataCommand)
      {
        return false;
      }
      label426: j += 1;
      continue;
      label435: i += 1;
    }
  }

  private boolean updateRecCategory(DataCommand paramDataCommand)
  {
    Object localObject1 = paramDataCommand.getParam();
    int k = ((Integer)((Map)localObject1).get("id")).intValue();
    Object localObject2 = (List)((Map)localObject1).get("banner");
    List localList;
    if ((localObject2 != null) && (((List)localObject2).size() > 0))
    {
      paramDataCommand = "0";
      localList = (List)((Map)localObject1).get("main");
      localObject1 = paramDataCommand;
      if (localList != null)
      {
        localObject1 = paramDataCommand;
        if (localList.size() > 0)
          localObject1 = "1";
      }
    }
    while (true)
    {
      int i;
      int j;
      try
      {
        paramDataCommand = DBManager.getInstance().getWritableDB("recCategoryNodes");
        paramDataCommand.beginTransaction();
        paramDataCommand.execSQL("delete from recCategoryNodes where catid = '" + k + "'" + " and type = " + "'" + (String)localObject1 + "'");
        localObject1 = new Gson();
        Object localObject3;
        String str1;
        if ((localObject2 != null) && (((List)localObject2).size() > 0))
        {
          i = 0;
          if ((localObject2 != null) && (i < ((List)localObject2).size()))
          {
            localObject3 = (RecommendItemNode)((List)localObject2).get(i);
            str1 = ((Gson)localObject1).toJson(localObject3);
            String str2 = ((Gson)localObject1).toJson(((RecommendItemNode)localObject3).mNode);
            if (((RecommendItemNode)localObject3).mNode == null)
              continue;
            paramDataCommand.execSQL("insert into recCategoryNodes(RecommendItemNode,node,nodeName,catid,type) values(?,?,?,?,?)", new Object[] { str1, str2, ((RecommendItemNode)localObject3).mNode.nodeName, Integer.valueOf(k), Integer.valueOf(0) });
            continue;
          }
        }
        if ((localList != null) && (localList.size() > 0))
        {
          i = 0;
          if (i < localList.size())
          {
            if (localList.get(i) == null)
              break label482;
            j = 0;
            if (j >= ((List)localList.get(i)).size())
              break label482;
            localObject2 = (RecommendItemNode)((List)localList.get(i)).get(j);
            localObject3 = ((Gson)localObject1).toJson(localObject2);
            str1 = ((Gson)localObject1).toJson(((RecommendItemNode)localObject2).mNode);
            if (((RecommendItemNode)localObject2).mNode == null)
              break label473;
            paramDataCommand.execSQL("insert into recCategoryNodes(RecommendItemNode,node,nodeName,catid,type) values(?,?,?,?,?)", new Object[] { localObject3, str1, ((RecommendItemNode)localObject2).mNode.nodeName, Integer.valueOf(k), Integer.valueOf(1) });
            break label473;
          }
        }
        paramDataCommand.setTransactionSuccessful();
        paramDataCommand.endTransaction();
        return true;
        paramDataCommand = null;
        break;
        i += 1;
        continue;
      }
      catch (Exception paramDataCommand)
      {
        return false;
      }
      label473: j += 1;
      continue;
      label482: i += 1;
    }
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "RecommendCategoryNodeDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_reccategory_node"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_reccategory_node"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_reccategory_node"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_reccategory_node"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.RecommendCategoryNodeDS
 * JD-Core Version:    0.6.2
 */