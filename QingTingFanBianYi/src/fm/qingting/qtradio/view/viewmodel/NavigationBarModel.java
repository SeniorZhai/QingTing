package fm.qingting.qtradio.view.viewmodel;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.INavigationSetting.Mode;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.IViewEventListener;
import fm.qingting.framework.view.IViewModel;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;

public class NavigationBarModel extends ViewGroupViewImpl
  implements IViewModel
{
  private INavigationSetting.Mode mMode;
  protected IView mNavigationBar;
  private StatusBarView mStatusBar;
  private int mStatusHeight = 0;
  protected IView mainView;
  protected final ViewLayout standardlayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public NavigationBarModel(Context paramContext, INavigationSetting.Mode paramMode)
  {
    super(paramContext);
    this.mMode = paramMode;
    if ((QtApiLevelManager.isApiLevelSupported(19)) && ((paramMode == INavigationSetting.Mode.NORMAL) || (paramMode == INavigationSetting.Mode.NOTITLE) || (paramMode == INavigationSetting.Mode.OVERLAY)))
    {
      this.mStatusBar = new StatusBarView(paramContext);
      this.mStatusHeight = NaviUtil.getStatusBarHeight(getResources());
    }
  }

  private boolean supportTranslucentStatusBar()
  {
    return (this.mStatusBar != null) && (this.mStatusHeight > 0);
  }

  public void LayoutView(ViewController paramViewController)
  {
    if ((paramViewController instanceof IEventHandler))
      setEventHandler((IEventHandler)paramViewController);
    if (this.mStatusBar != null)
      addView(this.mStatusBar);
    IView localIView = paramViewController.getView();
    if (localIView != null)
    {
      this.mainView = localIView;
      if ((this.mainView instanceof IViewEventListener))
        addViewEventListener((IViewEventListener)this.mainView);
      addView(this.mainView.getView());
    }
    localIView = paramViewController.getTopBar();
    if (localIView != null)
    {
      this.mNavigationBar = localIView;
      addView(this.mNavigationBar.getView());
    }
    paramViewController.setShellView(this);
    addViewEventListener(paramViewController);
    requestLayout();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((this.mNavigationBar == null) && (this.mainView == null));
    do
    {
      return;
      if ((this.mNavigationBar != null) && (this.mainView != null))
      {
        switch (1.$SwitchMap$fm$qingting$framework$view$INavigationSetting$Mode[this.mMode.ordinal()])
        {
        default:
          paramInt1 = 0;
        case 1:
        case 2:
        }
        while (true)
        {
          if (this.mStatusBar != null)
            this.mStatusBar.layout(0, 0, this.standardlayout.width, this.mStatusBar.getMeasuredHeight());
          this.mainView.getView().layout(0, paramInt1, paramInt3, this.standardlayout.height);
          this.mNavigationBar.getView().layout(0, this.mStatusHeight, this.standardlayout.width, this.mStatusHeight + this.mNavigationBar.getView().getMeasuredHeight());
          return;
          paramInt1 = this.mStatusHeight + this.mNavigationBar.getView().getMeasuredHeight();
          continue;
          paramInt1 = 0;
        }
      }
    }
    while ((this.mNavigationBar != null) || (this.mainView == null));
    this.mainView.getView().layout(0, 0, this.standardlayout.width, this.standardlayout.height);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    int i = 0;
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardlayout.scaleToBounds(paramInt1, paramInt2);
    if (this.mNavigationBar != null)
      this.standardlayout.measureView(this.mNavigationBar.getView());
    for (paramInt1 = this.mNavigationBar.getView().getMeasuredHeight(); ; paramInt1 = 0)
    {
      if (supportTranslucentStatusBar())
        this.mStatusBar.measure(this.standardlayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(paramInt1 + this.mStatusHeight, 1073741824));
      if ((this.mNavigationBar == null) && (this.mainView == null))
      {
        setMeasuredDimension(this.standardlayout.width, this.standardlayout.height);
        return;
      }
      if ((this.mNavigationBar != null) && (this.mainView != null))
        if (this.mStatusBar == null)
        {
          paramInt1 = this.mNavigationBar.getView().getMeasuredHeight();
          switch (1.$SwitchMap$fm$qingting$framework$view$INavigationSetting$Mode[this.mMode.ordinal()])
          {
          default:
            paramInt1 = i;
            label190: this.mainView.getView().measure(this.standardlayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824));
          case 1:
          case 2:
          }
        }
      while (true)
      {
        setMeasuredDimension(this.standardlayout.width, this.standardlayout.height);
        return;
        paramInt1 = this.mStatusBar.getMeasuredHeight();
        break;
        paramInt1 = this.standardlayout.height - paramInt1;
        break label190;
        paramInt1 = this.standardlayout.height;
        break label190;
        if ((this.mNavigationBar == null) && (this.mainView != null))
          this.standardlayout.measureView(this.mainView.getView());
      }
    }
  }

  public void setActivate(boolean paramBoolean)
  {
    this.mainView.setActivate(paramBoolean);
    if (this.mNavigationBar != null)
      this.mNavigationBar.setActivate(paramBoolean);
  }

  public void update(String paramString, Object paramObject)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.viewmodel.NavigationBarModel
 * JD-Core Version:    0.6.2
 */