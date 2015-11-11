package fm.qingting.qtradio.view.im;

public class ChatMode
{
  public static final int GROUP = 1;
  public static final int PRIVATE = 0;
  private static int sMode = 0;

  public static int getCurrentMode()
  {
    return sMode;
  }

  public static boolean isGroup()
  {
    return sMode == 1;
  }

  public static void setMode(int paramInt)
  {
    sMode = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.ChatMode
 * JD-Core Version:    0.6.2
 */