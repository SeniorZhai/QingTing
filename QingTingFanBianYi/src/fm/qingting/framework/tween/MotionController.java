package fm.qingting.framework.tween;

import java.util.ArrayList;
import java.util.List;

public class MotionController
  implements ITweenDelegate
{
  public static final String MOTION_PROGRESS = "motion_progress";
  public static final String TARGET_CHANGE = "target_change";
  private boolean complete;
  private IMotionHandler handler;
  private float position;
  private float target;

  public MotionController(float paramFloat, IMotionHandler paramIMotionHandler)
  {
    this.position = paramFloat;
    this.complete = true;
    this.handler = paramIMotionHandler;
  }

  public MotionController(IMotionHandler paramIMotionHandler)
  {
    this(0.0F, paramIMotionHandler);
  }

  public static List<TweenProperty> buildTweenProperties(float paramFloat1, float paramFloat2, float paramFloat3, IEase paramIEase)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new TweenProperty("position", paramFloat1, paramFloat2, paramFloat3, paramIEase));
    return localArrayList;
  }

  public static List<TweenProperty> buildTweenProperties(float paramFloat1, float paramFloat2, IEase paramIEase)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new TweenProperty("position", paramFloat1, paramFloat2, paramIEase));
    return localArrayList;
  }

  public boolean getComplete()
  {
    return this.complete;
  }

  public float getPosition()
  {
    return this.position;
  }

  public float getTarget()
  {
    return this.target;
  }

  public float getValue(Object paramObject, String paramString)
  {
    return getPosition();
  }

  public boolean isRunning()
  {
    return !this.complete;
  }

  public void onCancel(Object paramObject)
  {
    this.complete = true;
    this.handler.onMotionCancel(this);
  }

  public void onComplete(Object paramObject)
  {
    this.complete = true;
    this.handler.onMotionComplete(this);
  }

  public void onProgress(Object paramObject)
  {
    this.complete = false;
  }

  public void onStart(Object paramObject, String paramString, float paramFloat1, float paramFloat2)
  {
    setTarget(paramFloat2);
    this.complete = false;
    if (this.handler != null)
      this.handler.onMotionStart(this);
  }

  public void resetPosition(float paramFloat)
  {
    this.position = paramFloat;
  }

  public void resetTarget(float paramFloat)
  {
    this.target = paramFloat;
  }

  protected void setComplete(boolean paramBoolean)
  {
    this.complete = paramBoolean;
  }

  public void setPosition(float paramFloat)
  {
    this.position = paramFloat;
    if (this.handler != null)
      this.handler.onMotionProgress(this, this.position, this.target);
  }

  public void setTarget(float paramFloat)
  {
    if (this.target == paramFloat);
    for (int i = 1; ; i = 0)
    {
      this.target = paramFloat;
      if ((this.handler != null) && (i != 0))
        this.handler.onTargetChange(this, this.target);
      return;
    }
  }

  public void setValue(Object paramObject, String paramString, float paramFloat)
  {
    setPosition(paramFloat);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.tween.MotionController
 * JD-Core Version:    0.6.2
 */