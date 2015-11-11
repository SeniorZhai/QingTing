package fm.qingting.social;

public abstract interface ISocialEventListener
{
  public abstract void onCancel(Object paramObject);

  public abstract void onComplete(Object paramObject1, Object paramObject2);

  public abstract void onException(Object paramObject);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.social.ISocialEventListener
 * JD-Core Version:    0.6.2
 */