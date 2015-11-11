package fm.qingting.framework.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import fm.qingting.framework.adapter.SortAdapter;
import java.util.List;

public class SortListView extends ListViewImpl
{
  public static final String EMPTY_DATA = "empty_data";
  private Bitmap mDragBitmap;
  private DragListener mDragListener;
  private int mDragPointX;
  private int mDragPointY;
  private int mDragPos;
  private ImageView mDragView;
  private DropListener mDropListener;
  private Object mFloatingData;
  private int mHeight;
  private int mLastEmptyPos = -1;
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

  public SortListView(Context paramContext)
  {
    super(paramContext);
    this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
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
    int j = i;
    if (this.mDragPos > this.mSrcDragPos)
      j = i + 1;
    int n = getHeaderViewsCount();
    View localView1 = getChildAt(this.mSrcDragPos - getFirstVisiblePosition());
    int k = 0;
    View localView2 = getChildAt(k);
    if (localView2 == null)
      return;
    int m = 0;
    if ((this.mDragPos < n) && (k == n))
    {
      i = m;
      if (localView2.equals(localView1))
        i = 4;
    }
    while (true)
    {
      localView2.setVisibility(i);
      k += 1;
      break;
      if (localView2.equals(localView1))
      {
        if (this.mDragPos != this.mSrcDragPos)
        {
          i = m;
          if (getPositionForView(localView2) != getCount() - 1);
        }
        else
        {
          i = 4;
        }
      }
      else
      {
        i = m;
        if (k == j)
        {
          i = m;
          if (this.mDragPos >= n)
          {
            i = m;
            if (this.mDragPos < getCount() - 1)
              i = 4;
          }
        }
      }
    }
  }

  private void dragView(int paramInt1, int paramInt2)
  {
    this.mWindowParams.x = (paramInt1 - this.mDragPointX + this.mXOffset);
    this.mWindowParams.y = (paramInt2 - this.mDragPointY + this.mYOffset);
    this.mWindowManager.updateViewLayout(this.mDragView, this.mWindowParams);
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
    int j = paramInt - this.mDragPointY;
    int i = myPointToPosition(0, j);
    paramInt = i;
    if (i < 0)
    {
      paramInt = i;
      if (j < 0)
        paramInt = 0;
    }
    return paramInt;
  }

  private void log(String paramString)
  {
    Log.e("draganddrop", paramString);
  }

  private int myPointToPosition(int paramInt1, int paramInt2)
  {
    if (paramInt2 < 0)
    {
      i = myPointToPosition(paramInt1, paramInt2 + 60);
      if (i > 0)
        return i - 1;
    }
    Rect localRect = this.mTempRect;
    int i = getChildCount() - 1;
    while (true)
    {
      if (i < 0)
        return -1;
      getChildAt(i).getHitRect(localRect);
      if (localRect.contains(paramInt1, paramInt2))
        return getFirstVisiblePosition() + i;
      i -= 1;
    }
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
        ((View)localObject2).setVisibility(0);
        i += 1;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        break label65;
      }
    }
  }

  public void exchangeData(int paramInt1, int paramInt2)
  {
    Log.e("FMtest", "old:" + paramInt1 + "   new:" + paramInt2);
    if ((paramInt1 < 0) && (paramInt2 < 0));
    SortAdapter localSortAdapter;
    List localList;
    do
    {
      do
      {
        return;
        localSortAdapter = (SortAdapter)getAdapter();
      }
      while (localSortAdapter == null);
      localList = localSortAdapter.getData();
    }
    while (localList == null);
    if (paramInt1 < 0)
      if ((localList.size() > paramInt2) && (paramInt2 >= 0))
      {
        localSortAdapter.setDragPosition(paramInt2);
        localSortAdapter.setEmptyPosition(paramInt2);
      }
    while (true)
    {
      localSortAdapter.notifyDataSetChanged();
      return;
      if (paramInt2 < 0)
      {
        if ((localList.size() > paramInt1) && (paramInt1 >= 0))
        {
          localSortAdapter.setDragPosition(-1);
          localSortAdapter.setEmptyPosition(-1);
        }
      }
      else if ((localList.size() > paramInt1) && (localList.size() > paramInt2))
        localSortAdapter.setEmptyPosition(paramInt2);
    }
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    }
    int k;
    int i;
    int j;
    do
    {
      return true;
      k = (int)paramMotionEvent.getX();
      i = (int)paramMotionEvent.getY();
      j = pointToPosition(k, i);
    }
    while ((j == -1) || (j < getHeaderViewsCount()));
    IView localIView = (IView)getChildAt(j - getFirstVisiblePosition());
    this.mDragPointX = (k - localIView.getView().getLeft());
    this.mDragPointY = (i - localIView.getView().getTop());
    this.mXOffset = ((int)paramMotionEvent.getRawX() - k);
    this.mYOffset = ((int)paramMotionEvent.getRawY() - i);
    if (k < 565)
    {
      localIView.getView().setDrawingCacheEnabled(true);
      startDragging(Bitmap.createBitmap(localIView.getView().getDrawingCache()), k, i);
      this.mDragPos = j;
      this.mSrcDragPos = this.mDragPos;
      this.mHeight = getHeight();
      k = this.mTouchSlop;
      this.mUpperBound = Math.min(i - k, this.mHeight / 3);
      this.mLowerBound = Math.max(i + k, this.mHeight * 2 / 3);
      exchangeData(-1, this.mDragPos);
      this.mLastEmptyPos = j;
      return false;
    }
    stopDragging();
    return true;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    super.onMeasure(paramInt1, paramInt2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mDragView != null)
    {
      int i = paramMotionEvent.getAction();
      switch (i)
      {
      default:
      case 1:
      case 3:
      case 0:
      case 2:
      }
      int k;
      do
      {
        while (true)
        {
          return true;
          paramMotionEvent = this.mTempRect;
          this.mDragView.getDrawingRect(paramMotionEvent);
          stopDragging();
          if ((this.mDropListener != null) && (this.mDragPos >= 0) && (this.mDragPos < getCount()))
            this.mDropListener.drop(this.mSrcDragPos, this.mDragPos);
          exchangeData(this.mLastEmptyPos, -1);
          this.mLastEmptyPos = -1;
        }
        k = (int)paramMotionEvent.getX();
        j = (int)paramMotionEvent.getY();
        dragView(k, j);
        k = getItemForPosition(j);
      }
      while (k < 0);
      if ((i == 0) || (k != this.mLastEmptyPos))
      {
        if (this.mDragListener != null)
          this.mDragListener.drag(this.mDragPos, k);
        this.mDragPos = k;
        exchangeData(this.mLastEmptyPos, k);
        this.mLastEmptyPos = k;
      }
      i = 0;
      adjustScrollBounds(j);
      if (j > this.mLowerBound)
        if (getLastVisiblePosition() < getCount() - 1)
          if (j > (this.mHeight + this.mLowerBound) / 2)
            i = 16;
      while (true)
        label257: if (i != 0)
        {
          smoothScrollBy(i, 30);
          break;
          i = 4;
          continue;
          i = 1;
          continue;
          if (j < this.mUpperBound)
            if (j >= this.mUpperBound / 2)
              break label333;
        }
      label333: for (int j = -16; ; j = -4)
      {
        i = j;
        if (getFirstVisiblePosition() != 0)
          break label257;
        i = j;
        if (getChildAt(0).getTop() < getPaddingTop())
          break label257;
        i = 0;
        break label257;
        break;
      }
    }
    return super.onTouchEvent(paramMotionEvent);
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
 * Qualified Name:     fm.qingting.framework.view.SortListView
 * JD-Core Version:    0.6.2
 */