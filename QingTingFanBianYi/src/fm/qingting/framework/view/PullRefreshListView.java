package fm.qingting.framework.view;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import fm.qingting.framework.tween.FrameTween;
import fm.qingting.framework.tween.IMotionHandler;
import fm.qingting.framework.tween.MotionController;
import fm.qingting.framework.tween.easing.Quart.EaseOut;
import java.util.Calendar;

public class PullRefreshListView extends ListViewImpl
  implements AbsListView.OnScrollListener, IMotionHandler
{
  public static final int ON_REFRESH = 1;
  private static final int RATIO = 2;
  public static final int READY_FOR_REFRESH = 2;
  public static final int REFRESH_COMPLETE = 0;
  public static final int REFRESH_UNAVAILABLE = -1;
  private int firstItemIndex = 0;
  private RefreshHeader headerView;
  private boolean isRecorded = false;
  private boolean onScroll = false;
  private AbsListView.OnScrollListener onScrollListener;
  private int refreshState = 2;
  private boolean resetFlag = false;
  private MotionController rollMotion = new MotionController(-1.0F, this);
  private int scrollCount = 0;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);
  private int startY = 0;
  private long touchDownTime = 0L;
  private Point touchPoint = new Point(0, 0);

  public PullRefreshListView(Context paramContext)
  {
    super(paramContext);
    this.headerView = new RefreshHeader(paramContext);
    addHeaderView(this.headerView);
    setOnScrollListener(this);
  }

  private void cancelRollHeader()
  {
    FrameTween.cancel(this.rollMotion);
  }

  private void rollHeader(float paramFloat)
  {
    cancelRollHeader();
    int j = (int)(Math.abs(Math.abs(paramFloat) - Math.abs(this.rollMotion.getPosition())) * 15);
    int i = j;
    if (j <= 5)
      i = 5;
    j = i;
    if (i > 15)
      j = 15;
    FrameTween.to(this.rollMotion, this.rollMotion, MotionController.buildTweenProperties(paramFloat, j, new Quart.EaseOut()));
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    this.standardLayout.scaleToBounds(getMeasuredWidth(), getMeasuredHeight());
  }

  public void onMotionCancel(MotionController paramMotionController)
  {
  }

  public void onMotionComplete(MotionController paramMotionController)
  {
    if ((this.headerView.getPosition() == -1.0F) && (this.refreshState == 0))
      this.headerView.setOnRefreshing(false);
  }

  public void onMotionProgress(MotionController paramMotionController, float paramFloat1, float paramFloat2)
  {
    this.headerView.setPosition(paramFloat1);
  }

  public void onMotionStart(MotionController paramMotionController)
  {
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    this.firstItemIndex = paramInt1;
    this.scrollCount += 1;
    if (this.onScrollListener != null)
      this.onScrollListener.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (this.onScrollListener != null)
      this.onScrollListener.onScrollStateChanged(paramAbsListView, paramInt);
  }

  public void onTargetChange(MotionController paramMotionController, float paramFloat)
  {
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int j = this.headerView.getContentHeight();
    int i = (int)paramMotionEvent.getX();
    int k = (int)paramMotionEvent.getY();
    if ((this.firstItemIndex == 0) && (!this.isRecorded))
    {
      this.isRecorded = true;
      this.startY = ((int)(k - (this.headerView.getPosition() + 1.0F) * j * 2.0F));
    }
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
      float f2;
      do
      {
        while (true)
        {
          return super.onTouchEvent(paramMotionEvent);
          if (this.refreshState == 0)
          {
            this.refreshState = 2;
            this.headerView.setOnRefreshing(false);
          }
          this.touchPoint.x = i;
          this.touchPoint.y = k;
          this.onScroll = false;
          this.touchDownTime = Calendar.getInstance().getTimeInMillis();
          this.scrollCount = 0;
          this.headerView.setUpdateTime();
          cancelRollHeader();
        }
        if (this.scrollCount >= 2)
          this.onScroll = true;
        if (!this.onScroll)
        {
          long l1 = Calendar.getInstance().getTimeInMillis();
          f1 = Math.abs(k - this.touchPoint.y);
          f2 = this.standardLayout.width / 96.0F;
          float f3 = this.standardLayout.width / 48.0F;
          long l2 = this.touchDownTime;
          if (((f1 > f2) && (l1 - l2 > 300L)) || (f1 > f3))
            this.onScroll = true;
        }
      }
      while ((!this.isRecorded) || (this.firstItemIndex != 0) || (!this.onScroll));
      if (this.resetFlag)
      {
        i = 0;
        label309: f1 = -1.0F;
        if (k >= this.startY)
          break label373;
        this.resetFlag = true;
      }
      while (true)
      {
        f2 = f1;
        if (this.refreshState == 1)
        {
          f2 = f1;
          if (f1 < 0.0F)
            f2 = 0.0F;
        }
        this.rollMotion.setPosition(f2);
        if (i == 0)
          break;
        setSelection(0);
        break;
        i = 1;
        break label309;
        label373: this.resetFlag = false;
        f1 = ((k - this.startY) / 2 - j) / j;
      }
    case 1:
    }
    float f1 = this.headerView.getPosition();
    if (f1 > -1.0F)
    {
      if (this.refreshState != 2)
        break label465;
      if (f1 < 0.0F)
        break label459;
      i = 0;
      label431: rollHeader(i);
      if (f1 >= 0.0F)
        dispatchActionEvent("refresh", null);
    }
    while (true)
    {
      this.isRecorded = false;
      break;
      label459: i = -1;
      break label431;
      label465: rollHeader(0.0F);
    }
  }

  public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    if (paramOnScrollListener == this)
    {
      super.setOnScrollListener(paramOnScrollListener);
      return;
    }
    this.onScrollListener = paramOnScrollListener;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("updateTime"))
      this.headerView.setUpdateTime(((Long)paramObject).longValue());
    do
    {
      return;
      if (paramString.equalsIgnoreCase("refreshState"))
      {
        int i = ((Integer)paramObject).intValue();
        switch (i)
        {
        default:
          return;
        case 0:
          this.refreshState = i;
          rollHeader(-1.0F);
          return;
        case 1:
          this.refreshState = i;
          rollHeader(0.0F);
          this.headerView.setOnRefreshing(true);
          return;
        case 2:
        }
        this.refreshState = i;
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("topPadding"));
    this.headerView.setTopExtendPadding(((Integer)paramObject).intValue());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.PullRefreshListView
 * JD-Core Version:    0.6.2
 */