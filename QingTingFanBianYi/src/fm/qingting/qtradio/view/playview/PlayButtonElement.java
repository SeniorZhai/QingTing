package fm.qingting.qtradio.view.playview;

import android.content.Context;

public class PlayButtonElement extends AbsPlayButtonElement
{
  public PlayButtonElement(Context paramContext)
  {
    super(paramContext);
  }

  protected int getBufferResource(boolean paramBoolean)
  {
    return 2130837763;
  }

  protected int getErrorResource(boolean paramBoolean)
  {
    if (paramBoolean)
      return 2130837764;
    return 2130837763;
  }

  protected int getPauseResource(boolean paramBoolean)
  {
    if (paramBoolean)
      return 2130837782;
    return 2130837781;
  }

  protected int getPlayResource(boolean paramBoolean)
  {
    if (paramBoolean)
      return 2130837784;
    return 2130837783;
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.PlayButtonElement
 * JD-Core Version:    0.6.2
 */