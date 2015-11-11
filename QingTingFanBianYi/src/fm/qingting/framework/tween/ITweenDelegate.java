package fm.qingting.framework.tween;

public abstract interface ITweenDelegate
{
  public abstract float getValue(Object paramObject, String paramString);

  public abstract void onCancel(Object paramObject);

  public abstract void onComplete(Object paramObject);

  public abstract void onProgress(Object paramObject);

  public abstract void onStart(Object paramObject, String paramString, float paramFloat1, float paramFloat2);

  public abstract void setValue(Object paramObject, String paramString, float paramFloat);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.tween.ITweenDelegate
 * JD-Core Version:    0.6.2
 */