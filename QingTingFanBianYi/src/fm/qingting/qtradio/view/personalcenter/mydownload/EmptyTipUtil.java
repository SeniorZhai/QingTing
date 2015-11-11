package fm.qingting.qtradio.view.personalcenter.mydownload;

public class EmptyTipUtil
{
  public static final int BLACKLIST = 10;
  public static final int COLLECTION = 0;
  public static final int CONVERSATION = 7;
  public static final int DOWNLOAD = 3;
  public static final int FANS = 6;
  public static final int FOLLOWING = 5;
  public static final int GROUP = 4;
  public static final int HISTORY = 1;
  public static final int NOFILTERRESULT = 11;
  public static final int NONET = 9;
  public static final int PLAY_GAMES = 14;
  public static final int PODCASTER = 12;
  public static final int RESERVE = 2;
  public static final int SEARCH = 8;
  public static final int SEARCH_ALL = 13;

  public static String getContent(int paramInt)
  {
    switch (paramInt)
    {
    case 9:
    default:
      return "";
    case 0:
      return "听到喜欢的内容就收藏吧更新及时告诉你";
    case 1:
      return "快去收听喜欢的节目吧";
    case 2:
      return "预约直播内容，到点准时通知您";
    case 3:
      return "要离线收听，不耗流量？快把想听\n的内容下载下来吧！";
    case 4:
      return "您还没有加入任何群组";
    case 5:
      return "您还没有关注其他联系人";
    case 6:
      return "无粉丝";
    case 7:
      return "您目前没有收到聊天消息";
    case 8:
      return "左右滑试试？或者换个关键词！";
    case 13:
      return "换个关键词试试?";
    case 10:
      return "如果您觉得受到了其他用户的骚扰，\n点Ta头像可以拉黑哦！";
    case 11:
      return "请尝试使用其他筛选条件";
    case 12:
    }
    return "关注喜爱的主播，更新及时告诉您";
  }

  public static int getRes(int paramInt)
  {
    switch (paramInt)
    {
    case 9:
    default:
      return 0;
    case 0:
      return 2130837601;
    case 1:
      return 2130837608;
    case 2:
      return 2130837611;
    case 3:
      return 2130837603;
    case 4:
      return 2130837607;
    case 5:
      return 2130837606;
    case 6:
      return 2130837604;
    case 7:
      return 2130837602;
    case 8:
    case 13:
      return 2130837612;
    case 10:
      return 2130837600;
    case 11:
      return 2130837605;
    case 12:
      return 2130837610;
    case 14:
    }
    return 2130837609;
  }

  public static String getTitle(int paramInt)
  {
    switch (paramInt)
    {
    case 9:
    default:
      return "";
    case 0:
      return "暂无收藏内容";
    case 1:
      return "暂无收听记录";
    case 2:
      return "暂无预约内容";
    case 3:
      return "暂无下载内容";
    case 4:
      return "无群组";
    case 5:
      return "无关注";
    case 6:
      return "无粉丝";
    case 7:
      return "无聊天消息";
    case 8:
      return "此分类无结果";
    case 13:
      return "无结果";
    case 10:
      return "空的黑名单";
    case 11:
      return "无筛选结果";
    case 12:
      return "无关注的主播";
    case 14:
    }
    return "游乐场空啦";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mydownload.EmptyTipUtil
 * JD-Core Version:    0.6.2
 */