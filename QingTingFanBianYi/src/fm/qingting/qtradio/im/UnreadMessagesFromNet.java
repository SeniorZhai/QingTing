package fm.qingting.qtradio.im;

import fm.qingting.qtradio.im.message.IMMessage;

public class UnreadMessagesFromNet
{
  private int mCount = 0;
  private IMMessage mMessage;

  public UnreadMessagesFromNet(int paramInt, IMMessage paramIMMessage)
  {
    this.mCount = paramInt;
    this.mMessage = paramIMMessage;
  }

  public int getCount()
  {
    return this.mCount;
  }

  public IMMessage getMessage()
  {
    return this.mMessage;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.im.UnreadMessagesFromNet
 * JD-Core Version:    0.6.2
 */