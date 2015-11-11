package master.flame.danmaku.controller;

public class UpdateThread extends Thread
{
  volatile boolean mIsQuited;

  public UpdateThread(String paramString)
  {
    super(paramString);
  }

  public boolean isQuited()
  {
    return this.mIsQuited;
  }

  public void quit()
  {
    this.mIsQuited = true;
  }

  public void run()
  {
    if (this.mIsQuited);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.controller.UpdateThread
 * JD-Core Version:    0.6.2
 */