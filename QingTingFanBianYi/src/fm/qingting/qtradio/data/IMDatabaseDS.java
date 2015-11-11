package fm.qingting.qtradio.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import fm.qingting.framework.data.DataCommand;
import fm.qingting.framework.data.IDataParser;
import fm.qingting.framework.data.IDataRecvHandler;
import fm.qingting.framework.data.IDataSource;
import fm.qingting.framework.data.IDataToken;
import fm.qingting.qtradio.im.IMContact;
import fm.qingting.qtradio.im.message.IMMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IMDatabaseDS
  implements IDataSource
{
  private static IMDatabaseDS instance = null;

  public static IMDatabaseDS getInstance()
  {
    if (instance == null)
      instance = new IMDatabaseDS();
    return instance;
  }

  private IMContact restoreContact(Cursor paramCursor, Boolean paramBoolean)
  {
    boolean bool = true;
    try
    {
      IMContact localIMContact = new IMContact();
      localIMContact.Avatar = paramCursor.getString(paramCursor.getColumnIndex("avatar"));
      localIMContact.Gender = paramCursor.getString(paramCursor.getColumnIndex("gender"));
      localIMContact.IsGroupContact = paramBoolean;
      if (paramCursor.getInt(paramCursor.getColumnIndex("isBlocked")) == 1);
      while (true)
      {
        localIMContact.IsBlocked = Boolean.valueOf(bool);
        localIMContact.Level = paramCursor.getInt(paramCursor.getColumnIndex("level"));
        localIMContact.Name = paramCursor.getString(paramCursor.getColumnIndex("name"));
        return localIMContact;
        bool = false;
      }
    }
    catch (Exception paramCursor)
    {
      paramCursor.printStackTrace();
    }
    return null;
  }

  private IMContact restoreGroupContact(Cursor paramCursor)
  {
    return restoreContact(paramCursor, Boolean.valueOf(true));
  }

  private IMContact restoreUserContact(Cursor paramCursor)
  {
    return restoreContact(paramCursor, Boolean.valueOf(false));
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public void appendGroupMessage(IMMessage paramIMMessage, boolean paramBoolean)
  {
    if (paramIMMessage == null);
    while (true)
    {
      return;
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("imDatabase");
        localSQLiteDatabase.beginTransaction();
        localSQLiteDatabase.execSQL("INSERT INTO groupMessage(msgSeq,msgId,groupId, fromContactId, contentType, content, timestamp,avatar,name)VALUES(?, ?, ?,       ?,         ?,           ?,       ?,?,?)", new Object[] { Long.valueOf(paramIMMessage.msgSeq), "", paramIMMessage.mFromGroupId, paramIMMessage.mFromID, Integer.valueOf(0), paramIMMessage.mMessage, Long.valueOf(paramIMMessage.publish), paramIMMessage.mFromAvatar, paramIMMessage.mFromName });
        localSQLiteDatabase.setTransactionSuccessful();
        localSQLiteDatabase.endTransaction();
        if (paramBoolean)
        {
          localSQLiteDatabase.close();
          return;
        }
      }
      catch (Exception paramIMMessage)
      {
        paramIMMessage.printStackTrace();
      }
    }
  }

  public void appendListGroupMessage(List<IMMessage> paramList, boolean paramBoolean)
  {
    if (paramList == null);
    while (true)
    {
      return;
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("imDatabase");
        localSQLiteDatabase.beginTransaction();
        int i = 0;
        while (i < paramList.size())
        {
          localSQLiteDatabase.execSQL("INSERT INTO groupMessage(msgSeq, msgId,groupId, fromContactId, contentType, content, timestamp,avatar,name)VALUES(?, ?, ?,       ?,         ?,           ?,       ?,? ,?)", new Object[] { Long.valueOf(((IMMessage)paramList.get(i)).msgSeq), "", ((IMMessage)paramList.get(i)).mFromGroupId, ((IMMessage)paramList.get(i)).mFromID, Integer.valueOf(0), ((IMMessage)paramList.get(i)).mMessage, Long.valueOf(((IMMessage)paramList.get(i)).publish), ((IMMessage)paramList.get(i)).mFromAvatar, ((IMMessage)paramList.get(i)).mFromName });
          i += 1;
        }
        localSQLiteDatabase.setTransactionSuccessful();
        localSQLiteDatabase.endTransaction();
        if (paramBoolean)
        {
          localSQLiteDatabase.close();
          return;
        }
      }
      catch (Exception paramList)
      {
        paramList.printStackTrace();
      }
    }
  }

  public void appendListPrivateMessage(List<IMMessage> paramList, boolean paramBoolean)
  {
    if (paramList == null);
    while (true)
    {
      return;
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("imDatabase");
        localSQLiteDatabase.beginTransaction();
        int i = 0;
        while (i < paramList.size())
        {
          localSQLiteDatabase.execSQL("INSERT INTO userMessage(msgSeq,msgId,fromContactId, toContactId, contentType, content, timestamp,avatar,name)VALUES(?, ? ,?,             ?,           ?,           ?,  ?,?,?)", new Object[] { Long.valueOf(((IMMessage)paramList.get(i)).msgSeq), "", ((IMMessage)paramList.get(i)).mFromID, ((IMMessage)paramList.get(i)).mToUserId, Integer.valueOf(0), ((IMMessage)paramList.get(i)).mMessage, Long.valueOf(((IMMessage)paramList.get(i)).publish), ((IMMessage)paramList.get(i)).mFromAvatar, ((IMMessage)paramList.get(i)).mFromName });
          i += 1;
        }
        localSQLiteDatabase.setTransactionSuccessful();
        localSQLiteDatabase.endTransaction();
        if (paramBoolean)
        {
          localSQLiteDatabase.close();
          return;
        }
      }
      catch (Exception paramList)
      {
        paramList.printStackTrace();
      }
    }
  }

  public void appendPrivateMessage(IMMessage paramIMMessage, boolean paramBoolean)
  {
    if (paramIMMessage == null);
    while (true)
    {
      return;
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("imDatabase");
        localSQLiteDatabase.beginTransaction();
        localSQLiteDatabase.execSQL("INSERT INTO userMessage(msgSeq,msgId,fromContactId, toContactId, contentType, content, timestamp,avatar,name)VALUES(?,   ?,  ?,             ?,           ?,           ?,       ?,?,?)", new Object[] { Long.valueOf(paramIMMessage.msgSeq), "", paramIMMessage.mFromID, paramIMMessage.mToUserId, Integer.valueOf(0), paramIMMessage.mMessage, Long.valueOf(paramIMMessage.publish), paramIMMessage.mFromAvatar, paramIMMessage.mFromName });
        localSQLiteDatabase.setTransactionSuccessful();
        localSQLiteDatabase.endTransaction();
        if (paramBoolean)
        {
          localSQLiteDatabase.close();
          return;
        }
      }
      catch (Exception paramIMMessage)
      {
        paramIMMessage.printStackTrace();
      }
    }
  }

  public String dataSourceName()
  {
    return "IMDatabaseDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    return null;
  }

  public IMContact getGroupContact(String paramString)
  {
    while (true)
    {
      try
      {
        Cursor localCursor = DBManager.getInstance().getReadableDB("imDatabase").rawQuery("SELECT * FROM groupContact WHERE (id = '" + paramString + "')", null);
        if (localCursor.moveToNext())
        {
          paramString = restoreGroupContact(localCursor);
          localCursor.close();
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return null;
      }
      paramString = null;
    }
  }

  public List<IMMessage> getGroupConversation(String paramString, int paramInt)
  {
    if (paramString == null)
      return null;
    ArrayList localArrayList;
    Cursor localCursor;
    try
    {
      localArrayList = new ArrayList();
      localCursor = DBManager.getInstance().getReadableDB("imDatabase").rawQuery("SELECT * FROM groupMessage WHERE ( groupId = '" + paramString + "' ) ORDER BY msgSeq DESC LIMIT " + paramInt, null);
      while (localCursor.moveToNext())
      {
        IMMessage localIMMessage = new IMMessage();
        localIMMessage.chatType = 1;
        localIMMessage.mFromID = localCursor.getString(localCursor.getColumnIndex("fromContactId"));
        localIMMessage.mFromGroupId = paramString;
        localIMMessage.mToUserId = null;
        localIMMessage.mMessage = localCursor.getString(localCursor.getColumnIndex("content"));
        localIMMessage.publish = localCursor.getLong(localCursor.getColumnIndex("timestamp"));
        localIMMessage.msgSeq = localCursor.getInt(localCursor.getColumnIndex("msgSeq"));
        localIMMessage.mFromAvatar = localCursor.getString(localCursor.getColumnIndex("avatar"));
        localIMMessage.mFromName = localCursor.getString(localCursor.getColumnIndex("name"));
        localArrayList.add(localIMMessage);
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    localCursor.close();
    return localArrayList;
  }

  public List<IMMessage> getGroupConversationLessThan(String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null)
      return null;
    ArrayList localArrayList;
    Cursor localCursor;
    try
    {
      localArrayList = new ArrayList();
      localCursor = DBManager.getInstance().getReadableDB("imDatabase").rawQuery("SELECT * FROM groupMessage WHERE ( groupId = '" + paramString + "' AND msgSeq < '" + paramInt2 + "' ) ORDER BY msgSeq DESC LIMIT " + paramInt1, null);
      while (localCursor.moveToNext())
      {
        IMMessage localIMMessage = new IMMessage();
        localIMMessage.chatType = 1;
        localIMMessage.mFromID = localCursor.getString(localCursor.getColumnIndex("fromContactId"));
        localIMMessage.mFromGroupId = paramString;
        localIMMessage.mToUserId = null;
        localIMMessage.mMessage = localCursor.getString(localCursor.getColumnIndex("content"));
        localIMMessage.publish = localCursor.getLong(localCursor.getColumnIndex("timestamp"));
        localIMMessage.msgSeq = localCursor.getInt(localCursor.getColumnIndex("msgSeq"));
        localIMMessage.mFromAvatar = localCursor.getString(localCursor.getColumnIndex("avatar"));
        localIMMessage.mFromName = localCursor.getString(localCursor.getColumnIndex("name"));
        localArrayList.add(localIMMessage);
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    localCursor.close();
    return localArrayList;
  }

  public List<IMMessage> getPrivateConversation(String paramString1, String paramString2, int paramInt)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return null;
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList();
      paramString1 = DBManager.getInstance().getReadableDB("imDatabase").rawQuery("SELECT * FROM userMessage WHERE (fromContactId = '" + paramString1 + "' AND toContactId = '" + paramString2 + "' )" + " OR (toContactId = '" + paramString1 + "' AND fromContactId = '" + paramString2 + "')" + " ORDER BY msgSeq DESC LIMIT " + paramInt, null);
      while (paramString1.moveToNext())
      {
        paramString2 = new IMMessage();
        paramString2.chatType = 0;
        paramString2.mFromID = paramString1.getString(paramString1.getColumnIndex("fromContactId"));
        paramString2.mToUserId = paramString1.getString(paramString1.getColumnIndex("toContactId"));
        paramString2.mMessage = paramString1.getString(paramString1.getColumnIndex("content"));
        paramString2.publish = paramString1.getLong(paramString1.getColumnIndex("timestamp"));
        paramString2.mFromAvatar = paramString1.getString(paramString1.getColumnIndex("avatar"));
        paramString2.mFromName = paramString1.getString(paramString1.getColumnIndex("name"));
        paramString2.msgSeq = paramString1.getInt(paramString1.getColumnIndex("msgSeq"));
        localArrayList.add(paramString2);
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    paramString1.close();
    return localArrayList;
  }

  public List<IMMessage> getPrivateConversationLessThan(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return null;
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList();
      paramString1 = DBManager.getInstance().getReadableDB("imDatabase").rawQuery("SELECT * FROM userMessage WHERE ( (fromContactId = '" + paramString1 + "' AND toContactId = '" + paramString2 + "' ) " + " OR ( toContactId = '" + paramString1 + "' AND fromContactId = '" + paramString2 + "' ) " + ") AND msgSeq < '" + paramInt2 + "' ORDER BY msgSeq DESC LIMIT " + paramInt1, null);
      while (paramString1.moveToNext())
      {
        paramString2 = new IMMessage();
        paramString2.chatType = 0;
        paramString2.mFromID = paramString1.getString(paramString1.getColumnIndex("fromContactId"));
        paramString2.mToUserId = paramString1.getString(paramString1.getColumnIndex("toContactId"));
        paramString2.mMessage = paramString1.getString(paramString1.getColumnIndex("content"));
        paramString2.publish = paramString1.getLong(paramString1.getColumnIndex("timestamp"));
        paramString2.msgSeq = paramString1.getInt(paramString1.getColumnIndex("msgSeq"));
        paramString2.mFromAvatar = paramString1.getString(paramString1.getColumnIndex("avatar"));
        paramString2.mFromName = paramString1.getString(paramString1.getColumnIndex("name"));
        localArrayList.add(paramString2);
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    paramString1.close();
    return localArrayList;
  }

  public IMContact getUserContact(String paramString)
  {
    if (paramString == null)
      return null;
    while (true)
    {
      try
      {
        Cursor localCursor = DBManager.getInstance().getReadableDB("imDatabase").rawQuery("SELECT * FROM userContact WHERE (id = '" + paramString + "')", null);
        if (localCursor.moveToNext())
        {
          paramString = restoreUserContact(localCursor);
          localCursor.close();
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return null;
      }
      paramString = null;
    }
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return false;
  }

  public void removeGroupContacts()
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("imDatabase");
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("DELETE FROM groupContact");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void removeGroupMessageOlderThan(long paramLong)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("imDatabase");
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("DELETE FROM groupMessage where timestamp < " + paramLong);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void removePrivateMessageOlderThan(long paramLong)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("imDatabase");
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("DELETE FROM userMessage where timestamp < " + paramLong);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void removeUserContacts(List<String> paramList)
  {
    if (paramList != null)
      try
      {
        if (paramList.size() == 0)
          return;
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("imDatabase");
        localSQLiteDatabase.beginTransaction();
        paramList = TextUtils.join(", ", paramList);
        localSQLiteDatabase.execSQL("DELETE FROM userContact WHERE id in ( '" + paramList + "' )");
        localSQLiteDatabase.setTransactionSuccessful();
        localSQLiteDatabase.endTransaction();
        return;
      }
      catch (Exception paramList)
      {
        paramList.printStackTrace();
      }
  }

  public void updateGroupContact(IMContact paramIMContact)
  {
    if (paramIMContact == null)
      return;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("imDatabase");
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("DELETE FROM groupContact WHERE id = '" + paramIMContact.Id + "'");
      localSQLiteDatabase.execSQL("INSERT INTO userContact(id, avatar, name, gender, isBlocked, level)VALUES(?,  ?,      ?,    ?,      ?,         ?)", new Object[] { paramIMContact.Id, paramIMContact.Avatar, paramIMContact.Gender, paramIMContact.IsBlocked, Integer.valueOf(paramIMContact.Level) });
      localSQLiteDatabase.setTransactionSuccessful();
      localSQLiteDatabase.endTransaction();
      return;
    }
    catch (Exception paramIMContact)
    {
      paramIMContact.printStackTrace();
    }
  }

  public void updateUserContact(IMContact paramIMContact, String paramString)
  {
    if ((paramIMContact == null) || (paramString == null))
      return;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("imDatabase");
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("DELETE FROM userContact WHERE id = '" + paramIMContact.Id + "' AND myId = '" + paramString + "'");
      localSQLiteDatabase.execSQL("INSERT INTO userContact(id, avatar, name, gender, isBlocked, level, myId)VALUES(?,  ?,      ?,    ?,      ?,         ?,     ?)", new Object[] { paramIMContact.Id, paramIMContact.Avatar, paramIMContact.Gender, paramIMContact.IsBlocked, Integer.valueOf(paramIMContact.Level), paramString });
      localSQLiteDatabase.setTransactionSuccessful();
      localSQLiteDatabase.endTransaction();
      return;
    }
    catch (Exception paramIMContact)
    {
      paramIMContact.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.IMDatabaseDS
 * JD-Core Version:    0.6.2
 */