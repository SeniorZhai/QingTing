package fm.qingting.qtradio.view.popviews;

public class CustomPopAction
{
  public static final int ALARM = 2;
  public static final int DOWNLOAD = 1;
  public static final int FEED_BACK = 6;
  private static final int[] ICONS = { 2130837902, 2130837894, 2130837893, 2130837901, 2130837899, 2130837900, 2130837895 };
  public static final int PICTURE_CAPTURE = 4;
  public static final int PICTURE_PICK = 5;
  public static final int REPLAY = 3;
  public static final int SHARE = 0;
  private static final String[] TYPES = { "分享", "下载", "设置闹钟", "回听", "拍照", "从相册选择", "意见反馈" };

  public static int getIcon(int paramInt)
  {
    return ICONS[paramInt];
  }

  public static String getName(int paramInt)
  {
    return TYPES[paramInt];
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.CustomPopAction
 * JD-Core Version:    0.6.2
 */