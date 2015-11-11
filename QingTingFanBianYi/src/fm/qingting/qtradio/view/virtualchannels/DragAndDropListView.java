package fm.qingting.qtradio.view.virtualchannels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewLayout;

public class DragAndDropListView extends ListView
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 90, 480, 800, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private Bitmap mDragBitmap;
  private DragListener mDragListener;
  private int mDragPointX;
  private int mDragPointY;
  private int mDragPos;
  private ImageView mDragView;
  private DropListener mDropListener;
  private int mHeight;
  private int mItemHeightExpanded;
  private int mItemHeightHalf;
  private int mItemHeightNormal;
  private int mLowerBound;
  private int mSrcDragPos;
  private Rect mTempRect = new Rect();
  private final int mTouchSlop;
  private Drawable mTrashcan;
  private int mUpperBound;
  private WindowManager mWindowManager;
  private WindowManager.LayoutParams mWindowParams;
  private int mXOffset;
  private int mYOffset;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);

  public DragAndDropListView(Context paramContext)
  {
    super(paramContext);
    this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    getResources();
    this.mItemHeightNormal = 100;
    this.mItemHeightHalf = (this.mItemHeightNormal / 2);
    this.mItemHeightExpanded = 200;
  }

  private void adjustScrollBounds(int paramInt)
  {
    if (paramInt >= this.mHeight / 3)
      this.mUpperBound = (this.mHeight / 3);
    if (paramInt <= this.mHeight * 2 / 3)
      this.mLowerBound = (this.mHeight * 2 / 3);
  }

  private void doExpansion()
  {
    int i = this.mDragPos - getFirstVisiblePosition();
    int k = i;
    if (this.mDragPos > this.mSrcDragPos)
      k = i + 1;
    int i1 = getHeaderViewsCount();
    View localView1 = getChildAt(this.mSrcDragPos - getFirstVisiblePosition());
    int m = 0;
    View localView2 = getChildAt(m);
    if (localView2 == null)
      return;
    i = this.mItemHeightNormal;
    int j;
    if ((this.mDragPos < i1) && (m == i1))
      if (localView2.equals(localView1))
      {
        int n = 4;
        j = i;
        i = n;
      }
    while (true)
    {
      ViewGroup.LayoutParams localLayoutParams = localView2.getLayoutParams();
      localLayoutParams.height = j;
      localView2.setLayoutParams(localLayoutParams);
      localView2.setVisibility(i);
      m += 1;
      break;
      j = this.mItemHeightExpanded;
      i = 0;
      continue;
      if (localView2.equals(localView1))
      {
        if ((this.mDragPos == this.mSrcDragPos) || (getPositionForView(localView2) == getCount() - 1))
        {
          j = i;
          i = 4;
        }
        else
        {
          j = 1;
          i = 0;
        }
      }
      else if ((m == k) && (this.mDragPos >= i1) && (this.mDragPos < getCount() - 1))
      {
        j = this.mItemHeightExpanded;
        i = 0;
      }
      else
      {
        j = i;
        i = 0;
      }
    }
  }

  private void dragView(int paramInt1, int paramInt2)
  {
    this.mWindowParams.x = 0;
    this.mWindowParams.y = (paramInt2 - this.mDragPointY + this.mYOffset);
    this.mWindowManager.updateViewLayout(this.mDragView, this.mWindowParams);
    log("dragView:" + paramInt1 + "-" + paramInt2);
    int i;
    if (this.mTrashcan != null)
    {
      i = this.mDragView.getWidth();
      if (paramInt2 > getHeight() * 3 / 4)
        this.mTrashcan.setLevel(2);
    }
    else
    {
      return;
    }
    if ((i > 0) && (paramInt1 > i / 4))
    {
      this.mTrashcan.setLevel(1);
      return;
    }
    this.mTrashcan.setLevel(0);
  }

  private int getItemForPosition(int paramInt)
  {
    int j = paramInt - this.mDragPointY - this.mItemHeightHalf;
    int i = myPointToPosition(0, j);
    if (i >= 0)
    {
      paramInt = i;
      if (i <= this.mSrcDragPos)
        paramInt = i + 1;
    }
    do
    {
      return paramInt;
      paramInt = i;
    }
    while (j >= 0);
    return 0;
  }

  private void log(String paramString)
  {
    Log.e("draganddrop", paramString);
  }

  private int myPointToPosition(int paramInt1, int paramInt2)
  {
    if (paramInt2 < 0)
    {
      i = myPointToPosition(paramInt1, this.mItemHeightNormal + paramInt2);
      if (i > 0)
        return i - 1;
    }
    Rect localRect = this.mTempRect;
    int i = getChildCount() - 1;
    while (i >= 0)
    {
      getChildAt(i).getHitRect(localRect);
      if (localRect.contains(paramInt1, paramInt2))
        return i + getFirstVisiblePosition();
      i -= 1;
    }
    return -1;
  }

  private void startDragging(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    stopDragging();
    this.mWindowParams = new WindowManager.LayoutParams();
    this.mWindowParams.gravity = 51;
    this.mWindowParams.x = (paramInt1 - this.mDragPointX + this.mXOffset);
    this.mWindowParams.y = (paramInt2 - this.mDragPointY + this.mYOffset);
    this.mWindowParams.height = -2;
    this.mWindowParams.width = -2;
    this.mWindowParams.flags = 920;
    this.mWindowParams.format = -3;
    this.mWindowParams.windowAnimations = 0;
    Context localContext = getContext();
    ImageView localImageView = new ImageView(localContext);
    localImageView.setPadding(0, 0, 0, 0);
    localImageView.setImageBitmap(paramBitmap);
    this.mDragBitmap = paramBitmap;
    this.mWindowManager = ((WindowManager)localContext.getSystemService("window"));
    this.mWindowManager.addView(localImageView, this.mWindowParams);
    this.mDragView = localImageView;
  }

  private void stopDragging()
  {
    if (this.mDragView != null)
    {
      this.mDragView.setVisibility(8);
      ((WindowManager)getContext().getSystemService("window")).removeView(this.mDragView);
      this.mDragView.setImageDrawable(null);
      this.mDragView = null;
    }
    if (this.mDragBitmap != null)
    {
      this.mDragBitmap.recycle();
      this.mDragBitmap = null;
    }
    if (this.mTrashcan != null)
      this.mTrashcan.setLevel(0);
  }

  private void unExpandViews(boolean paramBoolean)
  {
    int i = 0;
    while (true)
    {
      Object localObject1 = getChildAt(i);
      Object localObject2 = localObject1;
      if (localObject1 == null)
        if (paramBoolean)
        {
          int j = getFirstVisiblePosition();
          int k = getChildAt(0).getTop();
          setAdapter(getAdapter());
          setSelectionFromTop(j, k);
        }
      try
      {
        layoutChildren();
        localObject2 = getChildAt(i);
        localObject1 = localObject2;
        label65: localObject2 = localObject1;
        if (localObject1 == null)
          return;
        localObject1 = ((View)localObject2).getLayoutParams();
        ((ViewGroup.LayoutParams)localObject1).height = this.mItemHeightNormal;
        ((View)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject1);
        ((View)localObject2).setVisibility(0);
        i += 1;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        break label65;
      }
    }
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((this.mDragListener != null) || (this.mDropListener != null))
      switch (paramMotionEvent.getAction())
      {
      default:
      case 0:
      }
    while (true)
    {
      return true;
      int j = (int)paramMotionEvent.getX();
      int i = (int)paramMotionEvent.getY();
      int k = pointToPosition(j, i);
      if ((k != -1) && (k >= getHeaderViewsCount()))
      {
        IView localIView = (IView)getChildAt(k - getFirstVisiblePosition());
        this.mDragPointX = (j - localIView.getView().getLeft());
        this.mDragPointY = (i - localIView.getView().getTop());
        this.mXOffset = ((int)paramMotionEvent.getRawX() - j);
        this.mYOffset = ((int)paramMotionEvent.getRawY() - i);
        if (j < 565)
        {
          localIView.getView().setDrawingCacheEnabled(true);
          startDragging(Bitmap.createBitmap(localIView.getView().getDrawingCache()), j, i);
          this.mDragPos = k;
          this.mSrcDragPos = this.mDragPos;
          this.mHeight = getHeight();
          j = this.mTouchSlop;
          this.mUpperBound = Math.min(i - j, this.mHeight / 3);
          this.mLowerBound = Math.max(j + i, this.mHeight * 2 / 3);
          return false;
        }
        stopDragging();
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.itemLayout.scaleToBounds(this.standardLayout);
    this.mItemHeightNormal = this.itemLayout.height;
    this.mItemHeightHalf = (this.mItemHeightNormal / 2);
    this.mItemHeightExpanded = (this.mItemHeightNormal * 2);
    super.onMeasure(paramInt1, paramInt2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i;
    if (((this.mDragListener != null) || (this.mDropListener != null)) && (this.mDragView != null))
    {
      i = paramMotionEvent.getAction();
      switch (i)
      {
      default:
      case 1:
      case 3:
      case 0:
      case 2:
      }
    }
    label337: 
    while (true)
    {
      return true;
      paramMotionEvent = this.mTempRect;
      this.mDragView.getDrawingRect(paramMotionEvent);
      stopDragging();
      if ((this.mDropListener != null) && (this.mDragPos >= 0) && (this.mDragPos < getCount()))
        this.mDropListener.drop(this.mSrcDragPos, this.mDragPos);
      unExpandViews(false);
      continue;
      int k = (int)paramMotionEvent.getX();
      int j = (int)paramMotionEvent.getY();
      dragView(k, j);
      k = getItemForPosition(j);
      if (k >= 0)
      {
        if ((i == 0) || (k != this.mDragPos))
        {
          if (this.mDragListener != null)
            this.mDragListener.drag(this.mDragPos, k);
          this.mDragPos = k;
          doExpansion();
        }
        adjustScrollBounds(j);
        if (j > this.mLowerBound)
          if (getLastVisiblePosition() < getCount() - 1)
            if (j > (this.mHeight + this.mLowerBound) / 2)
              i = 16;
        while (true)
        {
          if (i == 0)
            break label337;
          smoothScrollBy(i, 30);
          break;
          i = 4;
          continue;
          i = 1;
          continue;
          if (j < this.mUpperBound)
          {
            if (j < this.mUpperBound / 2);
            for (j = -16; ; j = -4)
            {
              i = j;
              if (getFirstVisiblePosition() != 0)
                break;
              i = j;
              if (getChildAt(0).getTop() < getPaddingTop())
                break;
              i = 0;
              break;
            }
            return super.onTouchEvent(paramMotionEvent);
          }
          i = 0;
        }
      }
    }
  }

  public void setDragListener(DragListener paramDragListener)
  {
    this.mDragListener = paramDragListener;
  }

  public void setDropListener(DropListener paramDropListener)
  {
    this.mDropListener = paramDropListener;
  }

  public static abstract interface DragListener
  {
    public abstract void drag(int paramInt1, int paramInt2);
  }

  public static abstract interface DropListener
  {
    public abstract void drop(int paramInt1, int paramInt2);
  }

  public static abstract interface RemoveListener
  {
    public abstract void remove(int paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.DragAndDropListView
 * JD-Core Version:    0.6.2
 */