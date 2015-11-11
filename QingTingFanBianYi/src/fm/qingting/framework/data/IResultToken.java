package fm.qingting.framework.data;

public abstract interface IResultToken
{
  public abstract void cancel();

  public abstract String getDataType();

  public abstract String getEventM();

  public abstract int getID();

  public abstract Object getParametets();

  public abstract String getPosM();

  public abstract Result getResult();

  public abstract String getSourceM();

  public abstract String getType();

  public abstract void setMobParam(String paramString1, String paramString2, String paramString3);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.IResultToken
 * JD-Core Version:    0.6.2
 */