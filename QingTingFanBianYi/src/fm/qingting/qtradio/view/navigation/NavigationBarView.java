package fm.qingting.qtradio.view.navigation;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.navigation.items.TopAutoScalePlainView;
import fm.qingting.qtradio.view.navigation.items.TopAutoScaleView;
import fm.qingting.qtradio.view.navigation.items.TopButtonView;
import fm.qingting.qtradio.view.navigation.items.TopCustomButton;
import fm.qingting.qtradio.view.navigation.items.TopPlainButton;
import fm.qingting.qtradio.view.navigation.items.TopRecordView;
import fm.qingting.qtradio.view.viewmodel.NaviUtil;

public class NavigationBarView extends ViewGroupViewImpl
  implements IEventHandler
{
  private INavigationBarListener barListener;
  private LinearLayout leftContainer;
  private final ViewLayout leftLayout = this.standardLayout.createChildLT(702, 86, 8, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private LinearLayout rightContainer;
  private final ViewLayout rightLayout = this.standardLayout.createChildLT(702, 86, 10, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 86, 720, 86, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private LinearLayout titleContainer;
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(480, 86, 120, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);

  public NavigationBarView(Context paramContext)
  {
    super(paramContext);
    if ((!QtApiLevelManager.isApiLevelSupported(19)) || (NaviUtil.getStatusBarHeight(getResources()) <= 0))
      setBackgroundColor(SkinManager.getNaviBgColor());
    this.titleContainer = new LinearLayout(paramContext);
    this.titleContainer.setGravity(17);
    this.titleContainer.setVisibility(8);
    addView(this.titleContainer);
    this.rightContainer = new LinearLayout(paramContext);
    this.rightContainer.setGravity(5);
    addView(this.rightContainer);
    this.leftContainer = new LinearLayout(paramContext);
    this.leftContainer.setGravity(3);
    addView(this.leftContainer);
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
    this.rightLayout.measureView(this.rightContainer);
    this.leftLayout.measureView(this.leftContainer);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void setBarListener(INavigationBarListener paramINavigationBarListener)
  {
    this.barListener = paramINavigationBarListener;
  }

  public void setLeftItem(int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
    TopButtonView localTopButtonView = new TopButtonView(getContext(), paramInt);
    localTopButtonView.setEventHandler(this);
    localTopButtonView.setItemType(2);
    this.leftContainer.removeAllViews();
    this.leftContainer.setVisibility(0);
    this.leftContainer.addView(localTopButtonView, localLayoutParams);
  }

  public void setLeftItem(String paramString, int paramInt1, int paramInt2)
  {
    if (this.leftContainer.getChildCount() == 1)
    {
      localObject = (IView)this.leftContainer.getChildAt(0);
      if ((localObject instanceof TopAutoScalePlainView))
      {
        ((TopAutoScalePlainView)localObject).setTitle(paramString);
        this.leftContainer.setVisibility(0);
        return;
      }
    }
    Object localObject = new LinearLayout.LayoutParams(-2, -1);
    TopAutoScalePlainView localTopAutoScalePlainView = new TopAutoScalePlainView(getContext(), paramInt1, paramInt2);
    localTopAutoScalePlainView.setItemType(2);
    localTopAutoScalePlainView.setEventHandler(this);
    localTopAutoScalePlainView.setTitle(paramString);
    this.leftContainer.removeAllViews();
    this.leftContainer.setVisibility(0);
    this.leftContainer.addView(localTopAutoScalePlainView, (ViewGroup.LayoutParams)localObject);
  }

  public void setRecordItem(String paramString)
  {
    if (this.rightContainer.getChildCount() == 1)
    {
      localObject = (IView)this.rightContainer.getChildAt(0);
      if ((localObject instanceof TopRecordView))
      {
        ((TopRecordView)localObject).setTitle(paramString);
        this.rightContainer.setVisibility(0);
        return;
      }
    }
    Object localObject = new LinearLayout.LayoutParams(-2, -1);
    TopRecordView localTopRecordView = new TopRecordView(getContext());
    localTopRecordView.setItemType(3);
    localTopRecordView.setEventHandler(this);
    localTopRecordView.setTitle(paramString);
    this.rightContainer.removeAllViews();
    this.rightContainer.setVisibility(0);
    this.rightContainer.addView(localTopRecordView, (ViewGroup.LayoutParams)localObject);
  }

  public void setRightItem(int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
    TopButtonView localTopButtonView = new TopButtonView(getContext(), paramInt);
    localTopButtonView.setEventHandler(this);
    localTopButtonView.setItemType(3);
    this.rightContainer.removeAllViews();
    this.rightContainer.setVisibility(0);
    this.rightContainer.addView(localTopButtonView, localLayoutParams);
  }

  public void setRightItem(String paramString)
  {
    if (this.rightContainer.getChildCount() == 1)
    {
      localObject = (IView)this.rightContainer.getChildAt(0);
      if ((localObject instanceof TopCustomButton))
      {
        ((TopCustomButton)localObject).setTitle(paramString);
        this.rightContainer.setVisibility(0);
        return;
      }
    }
    Object localObject = new LinearLayout.LayoutParams(-2, -1);
    TopCustomButton localTopCustomButton = new TopCustomButton(getContext());
    localTopCustomButton.setItemType(3);
    localTopCustomButton.setEventHandler(this);
    localTopCustomButton.setTitle(paramString);
    this.rightContainer.removeAllViews();
    this.rightContainer.setVisibility(0);
    this.rightContainer.addView(localTopCustomButton, (ViewGroup.LayoutParams)localObject);
  }

  public void setRightItem(String paramString, int paramInt1, int paramInt2)
  {
    if (this.rightContainer.getChildCount() == 1)
    {
      localObject = (IView)this.rightContainer.getChildAt(0);
      if ((localObject instanceof TopAutoScalePlainView))
      {
        ((TopAutoScalePlainView)localObject).setTitle(paramString);
        this.rightContainer.setVisibility(0);
        return;
      }
    }
    Object localObject = new LinearLayout.LayoutParams(-2, -1);
    TopAutoScalePlainView localTopAutoScalePlainView = new TopAutoScalePlainView(getContext(), paramInt1, paramInt2);
    localTopAutoScalePlainView.setItemType(3);
    localTopAutoScalePlainView.setEventHandler(this);
    localTopAutoScalePlainView.setTitle(paramString);
    this.rightContainer.removeAllViews();
    this.rightContainer.setVisibility(0);
    this.rightContainer.addView(localTopAutoScalePlainView, (ViewGroup.LayoutParams)localObject);
  }

  public void setRightItem(String paramString, boolean paramBoolean)
  {
    if (this.rightContainer.getChildCount() == 1)
    {
      localObject = (IView)this.rightContainer.getChildAt(0);
      if ((localObject instanceof TopPlainButton))
      {
        ((TopPlainButton)localObject).setTitle(paramString);
        this.rightContainer.setVisibility(0);
        return;
      }
    }
    Object localObject = new LinearLayout.LayoutParams(-2, -1);
    TopPlainButton localTopPlainButton = new TopPlainButton(getContext());
    localTopPlainButton.setItemType(3);
    localTopPlainButton.setEventHandler(this);
    localTopPlainButton.setTitle(paramString);
    this.rightContainer.removeAllViews();
    this.rightContainer.setVisibility(0);
    this.rightContainer.addView(localTopPlainButton, (ViewGroup.LayoutParams)localObject);
  }

  public void setRightItemVisibility(int paramInt)
  {
    this.rightContainer.setVisibility(paramInt);
  }

  public void setRightTip(String paramString)
  {
    if (this.rightContainer.getChildCount() == 1)
    {
      IView localIView = (IView)this.rightContainer.getChildAt(0);
      if ((localIView instanceof TopAutoScaleView))
        ((TopAutoScaleView)localIView).setTip(paramString);
    }
  }

  public void setTitle(String paramString)
  {
    setTitleItem(new NavigationBarItem(paramString));
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
      localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
      TextView localTextView = new TextView(getContext());
      localTextView.setText(paramNavigationBarItem.getTitle());
      if (paramNavigationBarItem.getTextColor() != 0)
        localTextView.setTextColor(paramNavigationBarItem.getTextColor());
      while (true)
      {
        localTextView.setGravity(17);
        localTextView.setSingleLine(true);
        localTextView.setEllipsize(TextUtils.TruncateAt.END);
        localTextView.setTextSize(0, SkinManager.getInstance().getNormalTextSize());
        this.titleContainer.removeAllViews();
        this.titleContainer.addView(localTextView, localLayoutParams);
        this.titleContainer.setVisibility(0);
        return;
        localTextView.setTextColor(-1);
      }
    }
    this.titleContainer.setVisibility(8);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.navigation.NavigationBarView
 * JD-Core Version:    0.6.2
 */