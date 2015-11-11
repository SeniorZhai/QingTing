package fm.qingting.qtradio.view.virtualchannels;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class ProgramIntervalChooseViewGroup extends ViewGroupViewImpl
  implements IEventHandler
{
  private ProgramIntervalChooseScrollView mScrollView;
  private final ViewLayout paddingLayout = ViewLayout.createViewLayoutWithBoundsLT(10, 10, 720, 0, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public ProgramIntervalChooseViewGroup(Context paramContext)
  {
    super(paramContext);
    try
    {
      setBackgroundColor(SkinManager.getPopButtonNormalColor());
      label32: this.mScrollView = new ProgramIntervalChooseScrollView(paramContext);
      addView(this.mScrollView);
      this.mScrollView.setEventHandler(this);
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      break label32;
    }
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("jumptopoint"))
      dispatchActionEvent(paramString, paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mScrollView.layout(0, this.paddingLayout.width, getMeasuredWidth(), this.paddingLayout.width + this.mScrollView.getMeasuredHeight());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt2);
    this.paddingLayout.scaleToBounds(i, j);
    this.mScrollView.measure(paramInt1, paramInt2);
    paramInt1 = this.paddingLayout.width * 2 + this.mScrollView.getMeasuredHeight();
    if (this.mScrollView.getMeasuredHeight() == 0)
      paramInt1 = 0;
    setMeasuredDimension(i, paramInt1);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mScrollView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.ProgramIntervalChooseViewGroup
 * JD-Core Version:    0.6.2
 */