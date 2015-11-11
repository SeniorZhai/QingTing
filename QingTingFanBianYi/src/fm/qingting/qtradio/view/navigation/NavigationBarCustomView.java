package fm.qingting.qtradio.view.navigation;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.navigation.items.TopButtonView;
import fm.qingting.qtradio.view.navigation.items.TopCheckView;
import fm.qingting.qtradio.view.navigation.items.TopTextView;

public class NavigationBarCustomView extends ViewGroupViewImpl
  implements IEventHandler
{
  private INavigationBarListener barListener;
  private LinearLayout leftContainer;
  private final ViewLayout leftLayout = this.standardLayout.createChildLT(702, 114, 8, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private LinearLayout rightContainer;
  private final ViewLayout rightLayout = this.standardLayout.createChildLT(702, 114, 10, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 114, 720, 114, 0, 0, ViewLayout.FILL);
  private LinearLayout titleContainer;
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(480, 98, 120, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);

  public NavigationBarCustomView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getNaviBgColor());
    this.titleContainer = new LinearLayout(paramContext);
    this.titleContainer.setGravity(17);
    this.titleContainer.setVisibility(4);
    addView(this.titleContainer);
    this.rightContainer = new LinearLayout(paramContext);
    this.rightContainer.setGravity(5);
    addView(this.rightContainer);
    this.leftContainer = new LinearLayout(paramContext);
    this.leftContainer.setGravity(3);
    addView(this.leftContainer);
  }

  private void addRightItemInternal(int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
    TopButtonView localTopButtonView = new TopButtonView(getContext(), paramInt);
    localTopButtonView.setEventHandler(this);
    localTopButtonView.setItemType(3);
    this.rightContainer.setVisibility(0);
    this.rightContainer.addView(localTopButtonView, localLayoutParams);
  }

  public void addLeftItem(int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
    TopButtonView localTopButtonView = new TopButtonView(getContext(), paramInt);
    localTopButtonView.setEventHandler(this);
    localTopButtonView.setItemType(2);
    this.leftContainer.setVisibility(0);
    this.leftContainer.addView(localTopButtonView, localLayoutParams);
  }

  public void addRightItem(int paramInt)
  {
    switch (paramInt)
    {
    default:
      addRightItemInternal(paramInt);
      return;
    case 3:
    }
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
    TopCheckView localTopCheckView = new TopCheckView(getContext());
    localTopCheckView.setEventHandler(this);
    localTopCheckView.setItemType(4);
    this.rightContainer.setVisibility(0);
    this.rightContainer.addView(localTopCheckView, localLayoutParams);
  }

  public void close(boolean paramBoolean)
  {
    this.barListener = null;
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    return null;
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if ((paramString.equalsIgnoreCase("click")) && (paramObject2 != null) && (this.barListener != null))
      this.barListener.onItemClick(((Integer)paramObject2).intValue());
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt2 = paramInt4 - paramInt2;
    this.standardLayout.scaleToBounds(paramInt3 - paramInt1, paramInt2);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.rightLayout.scaleToBounds(this.standardLayout);
    this.leftLayout.scaleToBounds(this.standardLayout);
    if (paramInt2 > this.standardLayout.height)
    {
      paramInt1 = (paramInt2 - this.standardLayout.height) / 2;
      this.titleContainer.layout(this.titleLayout.getLeft(), this.titleLayout.getTop() + paramInt1, this.titleLayout.getLeft() + this.titleLayout.width, this.titleLayout.getTop() + this.titleLayout.height + paramInt1);
      this.rightContainer.layout(this.rightLayout.getLeft(), this.rightLayout.getTop() + paramInt1, this.rightLayout.getLeft() + this.rightLayout.width, this.rightLayout.getTop() + this.rightLayout.height + paramInt1);
      this.leftContainer.layout(this.leftLayout.getLeft(), this.leftLayout.getTop() + paramInt1, this.leftLayout.getLeft() + this.leftLayout.width, paramInt1 + (this.leftLayout.getTop() + this.leftLayout.height));
      return;
    }
    this.titleLayout.layoutView(this.titleContainer);
    this.rightLayout.layoutView(this.rightContainer);
    this.leftLayout.layoutView(this.leftContainer);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.rightLayout.scaleToBounds(this.standardLayout);
    this.leftLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.measureView(this.titleContainer);
    this.titleContainer.measure(View.MeasureSpec.makeMeasureSpec(this.titleLayout.getWidthMeasureSpec(), 0), View.MeasureSpec.makeMeasureSpec(this.titleLayout.getHeightMeasureSpec(), 0));
    this.rightLayout.measureView(this.rightContainer);
    this.leftLayout.measureView(this.leftContainer);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void resetCheckStateIfNeed()
  {
    int j = this.rightContainer.getChildCount();
    int i = 0;
    while (i < j)
    {
      IView localIView = (IView)this.rightContainer.getChildAt(i);
      if ((localIView instanceof TopCheckView))
        localIView.update("resetState", null);
      i += 1;
    }
  }

  public void setBarListener(INavigationBarListener paramINavigationBarListener)
  {
    this.barListener = paramINavigationBarListener;
  }

  public void setTitleItem(NavigationBarItem paramNavigationBarItem)
  {
    if (paramNavigationBarItem == null)
      return;
    LinearLayout.LayoutParams localLayoutParams;
    if (paramNavigationBarItem.getCustomeView() != null)
    {
      localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
      this.titleContainer.removeAllViews();
      this.titleContainer.addView(paramNavigationBarItem.getCustomeView(), localLayoutParams);
      this.titleContainer.setVisibility(0);
      return;
    }
    if (paramNavigationBarItem.getTitle() != null)
    {
      localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
      TopTextView localTopTextView = new TopTextView(getContext());
      localTopTextView.setTitle(paramNavigationBarItem.getTitle());
      localTopTextView.setItemType(1);
      localTopTextView.setEventHandler(this);
      this.titleContainer.removeAllViews();
      this.titleContainer.addView(localTopTextView, localLayoutParams);
      this.titleContainer.setVisibility(0);
      return;
    }
    this.titleContainer.setVisibility(4);
  }

  public void setTitleItem(NavigationBarItem paramNavigationBarItem, boolean paramBoolean)
  {
    if (paramNavigationBarItem == null)
      return;
    if (paramNavigationBarItem.getCustomeView() != null)
    {
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
      this.titleContainer.addView(paramNavigationBarItem.getCustomeView(), localLayoutParams);
      this.titleContainer.setVisibility(0);
      return;
    }
    if (paramBoolean)
    {
      this.titleContainer.setVisibility(4);
      return;
    }
    this.titleContainer.setVisibility(4);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.navigation.NavigationBarCustomView
 * JD-Core Version:    0.6.2
 */