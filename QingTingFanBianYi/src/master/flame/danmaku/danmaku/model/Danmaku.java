package master.flame.danmaku.danmaku.model;

import master.flame.danmaku.danmaku.parser.DanmakuFactory;

public class Danmaku extends BaseDanmaku
{
  public Danmaku(String paramString)
  {
    DanmakuFactory.fillText(this, paramString);
  }

  public float getBottom()
  {
    return 0.0F;
  }

  public float getLeft()
  {
    return 0.0F;
  }

  public float[] getRectAtTime(IDisplayer paramIDisplayer, long paramLong)
  {
    return null;
  }

  public float getRight()
  {
    return 0.0F;
  }

  public float getTop()
  {
    return 0.0F;
  }

  public int getType()
  {
    return 0;
  }

  public boolean isShown()
  {
    return false;
  }

  public void layout(IDisplayer paramIDisplayer, float paramFloat1, float paramFloat2)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.Danmaku
 * JD-Core Version:    0.6.2
 */