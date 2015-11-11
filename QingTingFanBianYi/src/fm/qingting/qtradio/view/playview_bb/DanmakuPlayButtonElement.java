package fm.qingting.qtradio.view.playview_bb;

import android.content.Context;
import fm.qingting.qtradio.view.playview.AbsPlayButtonElement;

public class DanmakuPlayButtonElement extends AbsPlayButtonElement
{
  public DanmakuPlayButtonElement(Context paramContext)
  {
    super(paramContext);
  }

  protected int getBufferResource(boolean paramBoolean)
  {
    return 2130837512;
  }

  protected int getErrorResource(boolean paramBoolean)
  {
    if (paramBoolean)
      return 2130837513;
    return 2130837512;
  }

  protected int getPauseResource(boolean paramBoolean)
  {
    if (paramBoolean)
      return 2130837518;
    return 2130837517;
  }

  protected int getPlayResource(boolean paramBoolean)
  {
    if (paramBoolean)
      return 2130837520;
    return 2130837519;
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview_bb.DanmakuPlayButtonElement
 * JD-Core Version:    0.6.2
 */