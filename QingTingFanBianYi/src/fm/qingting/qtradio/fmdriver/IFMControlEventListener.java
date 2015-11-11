package fm.qingting.qtradio.fmdriver;

public abstract interface IFMControlEventListener
{
  public abstract void onHeadsetPlugged();

  public abstract void onHeadsetUnplugged();

  public abstract void onMobilesState(boolean paramBoolean);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.fmdriver.IFMControlEventListener
 * JD-Core Version:    0.6.2
 */