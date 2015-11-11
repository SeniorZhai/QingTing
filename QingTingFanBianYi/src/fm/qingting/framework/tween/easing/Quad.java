package fm.qingting.framework.tween.easing;

import fm.qingting.framework.tween.IEase;

public class Quad
{
  public static class EaseIn
    implements IEase
  {
    public float ease(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float[] paramArrayOfFloat)
    {
      paramFloat1 /= paramFloat4;
      return paramFloat3 * paramFloat1 * paramFloat1 + paramFloat2;
    }
  }

  public static class EaseInOut
    implements IEase
  {
    public float ease(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float[] paramArrayOfFloat)
    {
      paramFloat1 /= paramFloat4 / 2.0F;
      if (paramFloat1 < 1.0F)
        return paramFloat3 / 2.0F * paramFloat1 * paramFloat1 + paramFloat2;
      paramFloat3 = -paramFloat3 / 2.0F;
      paramFloat1 -= 1.0F;
      return paramFloat3 * ((paramFloat1 - 2.0F) * paramFloat1 - 1.0F) + paramFloat2;
    }
  }

  public static class EaseOut
    implements IEase
  {
    public static float inverseEase(float paramFloat1, float paramFloat2, float paramFloat3)
    {
      paramFloat1 /= paramFloat3;
      return paramFloat2 / paramFloat1 * (paramFloat1 - 2.0F);
    }

    public float ease(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float[] paramArrayOfFloat)
    {
      paramFloat3 = -paramFloat3;
      paramFloat1 /= paramFloat4;
      return paramFloat3 * paramFloat1 * (paramFloat1 - 2.0F) + paramFloat2;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.tween.easing.Quad
 * JD-Core Version:    0.6.2
 */