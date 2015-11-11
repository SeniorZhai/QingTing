package fm.qingting.qtradio.view.frontpage;

import android.content.Context;
import android.graphics.Point;
import android.view.KeyEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.EducationManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.qtradio.view.moreContentView.UserInfoView;
import fm.qingting.qtradio.view.personalcenter.mydownload.MyDownloadView;
import fm.qingting.utils.QTMSGManage;

public class FrontPageView extends ViewGroupViewImpl
  implements IEventHandler
{
  private DiscoverView mDiscoverView;
  private MyDownloadView mDownloadView;
  private int mLastType = 1;
  private MiniPlayerView mPlayerView;
  private MainTabView mTabView;
  private UserInfoView mUserInfoView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public FrontPageView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mDiscoverView = new DiscoverView(paramContext);
    addView(this.mDiscoverView);
    this.mPlayerView = new MiniPlayerView(paramContext);
    addView(this.mPlayerView);
  }

  private void changeShowingView(int paramInt)
  {
    if (this.mLastType == paramInt)
      return;
    removeCurrentView(this.mLastType);
    switch (paramInt)
    {
    default:
    case 1:
      while (true)
      {
        this.mLastType = paramInt;
        return;
        this.mDiscoverView.setVisibility(0);
        QTMSGManage.getInstance().sendStatistcsMessage("newnavi_maintabclick", "DISCOVERY");
      }
    case 0:
      if (this.mUserInfoView == null)
      {
        this.mUserInfoView = new UserInfoView(getContext());
        addView(this.mUserInfoView, getChildCount() - 1);
        this.mUserInfoView.update("setData", null);
      }
      while (true)
      {
        QTMSGManage.getInstance().sendStatistcsMessage("newnavi_maintabclick", "PERSONAL");
        if (!EducationManager.getInstance().isShown())
          break;
        EducationManager.getInstance().cancelTip();
        break;
        this.mUserInfoView.setVisibility(0);
      }
    case 2:
    }
    if (this.mDownloadView == null)
    {
      this.mDownloadView = new MyDownloadView(getContext());
      addView(this.mDownloadView, getChildCount() - 1);
      this.mDownloadView.update("setData", null);
    }
    while (true)
    {
      QTMSGManage.getInstance().sendStatistcsMessage("newnavi_maintabclick", "DOWNLOAD");
      if (!EducationManager.getInstance().isShown())
        break;
      EducationManager.getInstance().cancelTip();
      break;
      this.mDownloadView.update("setData", null);
      this.mDownloadView.setVisibility(0);
    }
  }

  private void removeCurrentView(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 1:
      this.mDiscoverView.setVisibility(8);
      return;
    case 0:
      this.mUserInfoView.setVisibility(8);
      return;
    case 2:
    }
    this.mDownloadView.setVisibility(8);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (this.mLastType == 1)
      return this.mDiscoverView.dispatchKeyEvent(paramKeyEvent);
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    boolean bool = true;
    if (paramString.equalsIgnoreCase("currentIndex"))
    {
      if (this.mLastType == 1)
        return this.mDiscoverView.getValue(paramString, paramObject);
    }
    else if (paramString.equalsIgnoreCase("divide"))
    {
      if (this.mLastType == 1)
      {
        int i = this.mTabView.getMeasuredHeight();
        return new Point(i, ((Integer)this.mDiscoverView.getValue("tabHeight", null)).intValue() + i);
      }
    }
    else if (paramString.equalsIgnoreCase("isPersonal"))
    {
      if (this.mLastType == 0);
      while (true)
      {
        return Boolean.valueOf(bool);
        bool = false;
      }
    }
    return super.getValue(paramString, paramObject);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("selectTab"))
      changeShowingView(((Integer)paramObject2).intValue());
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mDiscoverView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    if (this.mUserInfoView != null)
      this.mUserInfoView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    if (this.mDownloadView != null)
      this.mDownloadView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    this.mPlayerView.layout(0, this.standardLayout.height - this.mPlayerView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.standardLayout.measureView(this.mPlayerView);
    paramInt1 = this.standardLayout.getWidthMeasureSpec();
    paramInt2 = View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824);
    this.mDiscoverView.measure(paramInt1, paramInt2);
    if (this.mUserInfoView != null)
      this.mUserInfoView.measure(paramInt1, paramInt2);
    if (this.mDownloadView != null)
      this.mDownloadView.measure(paramInt1, paramInt2);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void setTabView(MainTabView paramMainTabView)
  {
    this.mTabView = paramMainTabView;
    this.mTabView.setEventHandler(this);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mDiscoverView.update(paramString, paramObject);
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return;
              if (!paramString.equalsIgnoreCase("loginSuccess"))
                break;
            }
            while (this.mDownloadView == null);
            this.mDownloadView.update(paramString, paramObject);
            return;
          }
          while (paramString.equalsIgnoreCase("showSettingView"));
          if (!paramString.equalsIgnoreCase("updateWoState"))
            break;
        }
        while (this.mDownloadView == null);
        this.mDownloadView.update("setData", null);
        return;
        if (!paramString.equalsIgnoreCase("refreshView"))
          break;
      }
      while ((this.mLastType != 0) || (this.mUserInfoView == null));
      this.mUserInfoView.update(paramString, paramObject);
      return;
    }
    while ((!paramString.equalsIgnoreCase("resortCategoryList")) || (this.mDiscoverView == null));
    this.mDiscoverView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.FrontPageView
 * JD-Core Version:    0.6.2
 */