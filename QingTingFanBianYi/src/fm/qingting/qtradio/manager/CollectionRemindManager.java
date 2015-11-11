package fm.qingting.qtradio.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class CollectionRemindManager
{
  private static final int LIMIT = 3;
  private static final String SAVE_ID = "collection_remind";
  public static final int SOURCE_DOWNLOAD = 1;
  public static final int SOURCE_TIMER = 2;
  public static final int SOURCE_UNKNOWN = 0;
  private static int mSource;
  private static String sMissionFinishedIds = "";

  public static void banRemind(Context paramContext, int paramInt)
  {
  }

  public static void banRemind(Context paramContext, String paramString)
  {
    if (paramString == null)
      return;
    paramContext.getSharedPreferences("collection_remind", 0).edit().putInt(paramString, 3).commit();
  }

  public static String getSource()
  {
    switch (mSource)
    {
    default:
      return "unknown";
    case 1:
      return "download";
    case 2:
    }
    return "timer";
  }

  private static boolean missionFinished(String paramString)
  {
    if (paramString == null);
    do
    {
      return true;
      paramString = wrapId(paramString);
    }
    while (sMissionFinishedIds.contains(paramString));
    sMissionFinishedIds += paramString;
    return false;
  }

  public static void setSource(int paramInt)
  {
    switch (paramInt)
    {
    default:
      mSource = 0;
      return;
    case 1:
    case 2:
    }
    mSource = paramInt;
  }

  public static boolean shoudShowRemind(Context paramContext, int paramInt)
  {
    return shouldShowRemind(paramContext, Integer.toString(paramInt));
  }

  public static boolean shouldShowRemind(Context paramContext, String paramString)
  {
    if (paramString == null);
    while ((paramContext.getSharedPreferences("collection_remind", 0).getInt(paramString, 0) >= 3) || (missionFinished(paramString)))
      return false;
    return true;
  }

  public static void userAccept(Context paramContext, String paramString)
  {
  }

  public static void userBan(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("collection_remind", 0).edit().putInt(paramString, 3).commit();
  }

  public static void userIgnore(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("collection_remind", 0);
    int i = paramContext.getInt(paramString, 0);
    paramContext.edit().putInt(paramString, i + 1).commit();
  }

  private static String wrapId(String paramString)
  {
    return "_" + paramString + "_";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.manager.CollectionRemindManager
 * JD-Core Version:    0.6.2
 */