package fm.qingting.qtradio.view;

import android.content.Context;
import fm.qingting.qtradio.view.playview.AbsPlayButtonElement;

class PlayButtonElement extends AbsPlayButtonElement
{
  public PlayButtonElement(Context paramContext)
  {
    super(paramContext);
  }

  protected int getBufferResource(boolean paramBoolean)
  {
    return 2130837839;
  }

  protected int getErrorResource(boolean paramBoolean)
  {
    if (paramBoolean)
      return 2130837840;
    return 2130837839;
  }

  protected int getPauseResource(boolean paramBoolean)
  {
    if (paramBoolean)
      return 2130837846;
    return 2130837845;
  }

  protected int getPlayResource(boolean paramBoolean)
  {
    if (paramBoolean)
      return 2130837848;
    return 2130837847;
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.PlayButtonElement
 * JD-Core Version:    0.6.2
 */