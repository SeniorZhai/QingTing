package fm.qingting.qtradio.view.playview;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.view.viewmodel.NaviUtil;

public class TraScheduleView extends ViewGroupViewImpl
{
  private final ViewLayout headLayout = this.standardLayout.createChildLT(720, 98, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ScheduleHeadView mHeadView;
  private int mStatusHeight = 0;
  private TraScheduleViewPager mViewPager;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public TraScheduleView(Context paramContext)
  {
    super(paramContext);
    this.mHeadView = new ScheduleHeadView(paramContext);
    addView(this.mHeadView);
    this.mViewPager = new TraScheduleViewPager(paramContext);
    addView(this.mViewPager);
    if (QtApiLevelManager.isApiLevelSupported(19))
      this.mStatusHeight = NaviUtil.getStatusBarHeight(getResources());
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mHeadView.layout(0, this.mStatusHeight, this.headLayout.width, this.mStatusHeight + this.headLayout.height);
    this.mViewPager.layout(0, this.mStatusHeight + this.headLayout.height, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.headLayout.scaleToBounds(this.standardLayout);
    this.headLayout.measureView(this.mHeadView);
    this.mViewPager.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.headLayout.height - this.mStatusHeight, 1073741824));
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (ChannelNode)paramObject;
      if (paramString != null);
    }
    while (!paramString.equalsIgnoreCase("initState"))
    {
      return;
      this.mHeadView.update("setData", paramString.title);
      this.mViewPager.setNode(paramString);
      return;
    }
    int i = ((Integer)paramObject).intValue();
    this.mViewPager.initCurrentItem(i);
    this.mViewPager.setCurrentItem(i, false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.TraScheduleView
 * JD-Core Version:    0.6.2
 */