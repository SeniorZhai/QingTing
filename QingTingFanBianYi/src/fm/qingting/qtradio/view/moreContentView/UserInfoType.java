package fm.qingting.qtradio.view.moreContentView;

public class UserInfoType
{
  public static final int ALARM = 6;
  public static final int CHINAUNICOM_ZONE = 9;
  public static final int COLLECTION = 0;
  public static final int CONTACTS = 3;
  public static final int FLOW = 8;
  public static final int GAME = 11;
  public static final int HISTORY = 1;
  public static final int MALL = 12;
  public static final int MESSAGE = 4;
  public static final int MORE = 7;
  public static final int PODCASTER = 10;
  public static final int RESERVE = 2;
  public static final int TIMER = 5;

  public static int getRes(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return 0;
    case 0:
      return 2130838016;
    case 1:
      return 2130838022;
    case 2:
      return 2130838026;
    case 3:
      return 2130838017;
    case 4:
      return 2130838018;
    case 5:
      return 2130838028;
    case 6:
      return 2130838010;
    case 7:
      return 2130838023;
    case 8:
      return 2130838020;
    case 9:
      return 2130838015;
    case 10:
      return 2130838024;
    case 11:
      return 2130838021;
    case 12:
    }
    return 2130838027;
  }

  public static String getTitle(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "";
    case 0:
      return "我的收藏";
    case 1:
      return "最近收听";
    case 2:
      return "我的预约";
    case 3:
      return "联系人";
    case 4:
      return "消息";
    case 5:
      return "定时关闭";
    case 6:
      return "闹钟";
    case 7:
      return "更多设置";
    case 8:
      return "免流量畅听";
    case 9:
      return "联通专区";
    case 10:
      return "我的主播";
    case 11:
      return "蜻蜓游乐场";
    case 12:
    }
    return "蜻蜓商城";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.moreContentView.UserInfoType
 * JD-Core Version:    0.6.2
 */