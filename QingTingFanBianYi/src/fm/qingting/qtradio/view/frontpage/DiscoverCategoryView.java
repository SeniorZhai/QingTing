package fm.qingting.qtradio.view.frontpage;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.qtradio.view.frontpage.discover.DiscoverCampusView;
import fm.qingting.qtradio.view.frontpage.discover.DiscoverColumnView;
import fm.qingting.qtradio.view.frontpage.discover.DiscoverNovelView;

public class DiscoverCategoryView extends ViewGroupViewImpl
{
  private MiniPlayerView mPlayerView;
  private IView mView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public DiscoverCategoryView(Context paramContext, CategoryNode paramCategoryNode)
  {
    super(paramContext);
    if (paramCategoryNode.sectionId == 166)
      this.mView = new DiscoverCampusView(getContext());
    while (true)
    {
      this.mView.getView().setBackgroundColor(SkinManager.getBackgroundColor());
      this.mView.update("setData", paramCategoryNode);
      addView(this.mView.getView());
      this.mPlayerView = new MiniPlayerView(paramContext);
      addView(this.mPlayerView);
      return;
      if (paramCategoryNode.sectionId == 208)
        this.mView = new DiscoverNovelView(getContext());
      else
        this.mView = new DiscoverColumnView(getContext());
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mView.getView().layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    this.mPlayerView.layout(0, this.standardLayout.height - this.mPlayerView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.standardLayout.measureView(this.mPlayerView);
    this.mView.getView().measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.DiscoverCategoryView
 * JD-Core Version:    0.6.2
 */