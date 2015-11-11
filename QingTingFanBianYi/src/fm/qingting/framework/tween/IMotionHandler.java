package fm.qingting.framework.tween;

public abstract interface IMotionHandler
{
  public abstract void onMotionCancel(MotionController paramMotionController);

  public abstract void onMotionComplete(MotionController paramMotionController);

  public abstract void onMotionProgress(MotionController paramMotionController, float paramFloat1, float paramFloat2);

  public abstract void onMotionStart(MotionController paramMotionController);

  public abstract void onTargetChange(MotionController paramMotionController, float paramFloat);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.tween.IMotionHandler
 * JD-Core Version:    0.6.2
 */