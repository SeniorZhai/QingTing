package master.flame.danmaku.danmaku.model;

public class SpecialDanmaku extends BaseDanmaku
{
  public long alphaDuration;
  public int beginAlpha;
  public float beginX;
  public float beginY;
  private float[] currStateValues = new float[4];
  public int deltaAlpha;
  public float deltaX;
  public float deltaY;
  public int endAlpha;
  public float endX;
  public float endY;
  public LinePath[] linePaths;
  public float pivotX;
  public float pivotY;
  public float rotateX;
  public float rotateZ;
  public long translationDuration;
  public long translationStartDelay;

  public float getBottom()
  {
    return this.currStateValues[3];
  }

  public float getLeft()
  {
    return this.currStateValues[0];
  }

  public float[] getRectAtTime(IDisplayer paramIDisplayer, long paramLong)
  {
    if (!isMeasured())
      return null;
    paramLong -= this.time;
    float f1;
    float f3;
    long l;
    float f4;
    int i;
    if ((this.alphaDuration > 0L) && (this.deltaAlpha != 0))
    {
      if (paramLong >= this.alphaDuration)
        this.alpha = this.endAlpha;
    }
    else
    {
      f1 = this.beginX;
      f3 = this.beginY;
      l = paramLong - this.translationStartDelay;
      if ((this.translationDuration <= 0L) || (l < 0L) || (l > this.translationDuration))
        break label453;
      f4 = (float)l / (float)this.translationDuration;
      if (this.linePaths == null)
        break label388;
      LinePath[] arrayOfLinePath = this.linePaths;
      int j = arrayOfLinePath.length;
      i = 0;
      f4 = f3;
      f3 = f1;
      label135: if (i >= j)
        break label495;
      paramIDisplayer = arrayOfLinePath[i];
      if ((l < paramIDisplayer.beginTime) || (l >= paramIDisplayer.endTime))
        break label361;
    }
    while (true)
    {
      f1 = f4;
      float f2 = f3;
      float f5;
      if (paramIDisplayer != null)
      {
        f1 = paramIDisplayer.delatX;
        f5 = paramIDisplayer.deltaY;
        float f6 = (float)(paramLong - paramIDisplayer.beginTime) / (float)paramIDisplayer.duration;
        f2 = paramIDisplayer.pBegin.x;
        float f7 = paramIDisplayer.pBegin.y;
        if (f1 != 0.0F)
          f3 = f1 * f6 + f2;
        f1 = f4;
        f2 = f3;
        if (f5 != 0.0F)
        {
          f1 = f5 * f6 + f7;
          f2 = f3;
        }
      }
      while (true)
      {
        label271: this.currStateValues[0] = f2;
        this.currStateValues[1] = f1;
        this.currStateValues[2] = (f2 + this.paintWidth);
        this.currStateValues[3] = (f1 + this.paintHeight);
        if (!isOutside());
        for (boolean bool = true; ; bool = false)
        {
          setVisibility(bool);
          return this.currStateValues;
          this.alpha = ((int)((float)paramLong / (float)this.alphaDuration * this.deltaAlpha) + this.beginAlpha);
          break;
          label361: f3 = paramIDisplayer.pEnd.x;
          f4 = paramIDisplayer.pEnd.y;
          i += 1;
          break label135;
          label388: if (this.deltaX != 0.0F)
            f1 = this.deltaX * f4 + this.beginX;
          f2 = f1;
          if (this.deltaY == 0.0F)
            break label488;
          f3 = this.deltaY;
          f5 = this.beginY;
          f2 = f1;
          f1 = f3 * f4 + f5;
          break label271;
          label453: f2 = f1;
          if (l <= this.translationDuration)
            break label488;
          f2 = this.endX;
          f1 = this.endY;
          break label271;
        }
        label488: f1 = f3;
      }
      label495: paramIDisplayer = null;
    }
  }

  public float getRight()
  {
    return this.currStateValues[2];
  }

  public float getTop()
  {
    return this.currStateValues[1];
  }

  public int getType()
  {
    return 7;
  }

  public void layout(IDisplayer paramIDisplayer, float paramFloat1, float paramFloat2)
  {
    getRectAtTime(paramIDisplayer, this.mTimer.currMillisecond);
  }

  public void setAlphaData(int paramInt1, int paramInt2, long paramLong)
  {
    this.beginAlpha = paramInt1;
    this.endAlpha = paramInt2;
    this.deltaAlpha = (paramInt2 - paramInt1);
    this.alphaDuration = paramLong;
    if ((this.deltaAlpha != 0) && (paramInt1 != AlphaValue.MAX))
      this.alpha = paramInt1;
  }

  public void setLinePathData(float[][] paramArrayOfFloat)
  {
    if (paramArrayOfFloat != null)
    {
      int i = paramArrayOfFloat.length;
      this.beginX = paramArrayOfFloat[0][0];
      this.beginY = paramArrayOfFloat[0][1];
      this.endX = paramArrayOfFloat[(i - 1)][0];
      this.endY = paramArrayOfFloat[(i - 1)][1];
      if (paramArrayOfFloat.length > 1)
      {
        this.linePaths = new LinePath[paramArrayOfFloat.length - 1];
        i = 0;
        while (i < this.linePaths.length)
        {
          this.linePaths[i] = new LinePath();
          this.linePaths[i].setPoints(new Point(paramArrayOfFloat[i][0], paramArrayOfFloat[i][1]), new Point(paramArrayOfFloat[(i + 1)][0], paramArrayOfFloat[(i + 1)][1]));
          i += 1;
        }
        paramArrayOfFloat = this.linePaths;
        int j = paramArrayOfFloat.length;
        float f1 = 0.0F;
        i = 0;
        while (i < j)
        {
          float f2 = paramArrayOfFloat[i].getDistance();
          i += 1;
          f1 = f2 + f1;
        }
        paramArrayOfFloat = null;
        LinePath[] arrayOfLinePath = this.linePaths;
        j = arrayOfLinePath.length;
        i = 0;
        if (i < j)
        {
          LinePath localLinePath = arrayOfLinePath[i];
          localLinePath.duration = (()(localLinePath.getDistance() / f1 * (float)this.translationDuration));
          if (paramArrayOfFloat == null);
          for (long l = 0L; ; l = paramArrayOfFloat.endTime)
          {
            localLinePath.beginTime = l;
            localLinePath.endTime = (localLinePath.beginTime + localLinePath.duration);
            i += 1;
            paramArrayOfFloat = localLinePath;
            break;
          }
        }
      }
    }
  }

  public void setTranslationData(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong1, long paramLong2)
  {
    this.beginX = paramFloat1;
    this.beginY = paramFloat2;
    this.endX = paramFloat3;
    this.endY = paramFloat4;
    this.deltaX = (paramFloat3 - paramFloat1);
    this.deltaY = (paramFloat4 - paramFloat2);
    this.translationDuration = paramLong1;
    this.translationStartDelay = paramLong2;
  }

  public void updateData(float paramFloat)
  {
  }

  public class LinePath
  {
    public long beginTime;
    float delatX;
    float deltaY;
    public long duration;
    public long endTime;
    SpecialDanmaku.Point pBegin;
    SpecialDanmaku.Point pEnd;

    public LinePath()
    {
    }

    public float[] getBeginPoint()
    {
      return new float[] { this.pBegin.x, this.pBegin.y };
    }

    public float getDistance()
    {
      return this.pEnd.getDistance(this.pBegin);
    }

    public float[] getEndPoint()
    {
      return new float[] { this.pEnd.x, this.pEnd.y };
    }

    public void setPoints(SpecialDanmaku.Point paramPoint1, SpecialDanmaku.Point paramPoint2)
    {
      this.pBegin = paramPoint1;
      this.pEnd = paramPoint2;
      this.delatX = (paramPoint2.x - paramPoint1.x);
      this.deltaY = (paramPoint2.y - paramPoint1.y);
    }
  }

  private class Point
  {
    float x;
    float y;

    public Point(float paramFloat1, float arg3)
    {
      this.x = paramFloat1;
      Object localObject;
      this.y = localObject;
    }

    public float getDistance(Point paramPoint)
    {
      float f1 = Math.abs(this.x - paramPoint.x);
      float f2 = Math.abs(this.y - paramPoint.y);
      return (float)Math.sqrt(f1 * f1 + f2 * f2);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.SpecialDanmaku
 * JD-Core Version:    0.6.2
 */