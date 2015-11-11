package fm.qingting.qtradio.view.pinnedsection;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

public class PinnedSectionListView extends ListView
{
  private final DataSetObserver mDataSetObserver = new DataSetObserver()
  {
    public void onChanged()
    {
      PinnedSectionListView.this.recreatePinnedShadow();
    }

    public void onInvalidated()
    {
      PinnedSectionListView.this.recreatePinnedShadow();
    }
  };
  AbsListView.OnScrollListener mDelegateOnScrollListener;
  private MotionEvent mDownEvent;
  private final AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener()
  {
    public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      if (PinnedSectionListView.this.mDelegateOnScrollListener != null)
        PinnedSectionListView.this.mDelegateOnScrollListener.onScroll(paramAnonymousAbsListView, paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
      paramAnonymousAbsListView = PinnedSectionListView.this.getAdapter();
      if ((paramAnonymousAbsListView == null) || (paramAnonymousInt2 == 0))
        return;
      if (PinnedSectionListView.isItemViewTypePinned(paramAnonymousAbsListView, paramAnonymousAbsListView.getItemViewType(paramAnonymousInt1)))
      {
        if (PinnedSectionListView.this.getChildAt(0).getTop() == PinnedSectionListView.this.getPaddingTop())
        {
          PinnedSectionListView.this.destroyPinnedShadow();
          return;
        }
        PinnedSectionListView.this.ensureShadowForPosition(paramAnonymousInt1, paramAnonymousInt1, paramAnonymousInt2);
        return;
      }
      paramAnonymousInt3 = PinnedSectionListView.this.findCurrentSectionPosition(paramAnonymousInt1);
      if (paramAnonymousInt3 > -1)
      {
        PinnedSectionListView.this.ensureShadowForPosition(paramAnonymousInt3, paramAnonymousInt1, paramAnonymousInt2);
        return;
      }
      PinnedSectionListView.this.destroyPinnedShadow();
    }

    public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
    {
      if (PinnedSectionListView.this.mDelegateOnScrollListener != null)
        PinnedSectionListView.this.mDelegateOnScrollListener.onScrollStateChanged(paramAnonymousAbsListView, paramAnonymousInt);
    }
  };
  PinnedSection mPinnedSection;
  PinnedSection mRecycleSection;
  private int mSectionsDistanceY;
  private GradientDrawable mShadowDrawable;
  private int mShadowHeight;
  private final PointF mTouchPoint = new PointF();
  private final Rect mTouchRect = new Rect();
  private int mTouchSlop;
  private View mTouchTarget;
  int mTranslateY;

  public PinnedSectionListView(Context paramContext)
  {
    super(paramContext);
    initView();
  }

  private void clearTouchTarget()
  {
    this.mTouchTarget = null;
    if (this.mDownEvent != null)
    {
      this.mDownEvent.recycle();
      this.mDownEvent = null;
    }
  }

  private void initView()
  {
    setOnScrollListener(this.mOnScrollListener);
    this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    initShadow(true);
  }

  public static boolean isItemViewTypePinned(ListAdapter paramListAdapter, int paramInt)
  {
    if ((paramListAdapter instanceof HeaderViewListAdapter))
      paramListAdapter = ((HeaderViewListAdapter)paramListAdapter).getWrappedAdapter();
    while (true)
      return ((PinnedSectionListAdapter)paramListAdapter).isItemViewTypePinned(paramInt);
  }

  private boolean isPinnedViewTouched(View paramView, float paramFloat1, float paramFloat2)
  {
    paramView.getHitRect(this.mTouchRect);
    paramView = this.mTouchRect;
    paramView.top += this.mTranslateY;
    paramView = this.mTouchRect;
    paramView.bottom += this.mTranslateY + getPaddingTop();
    paramView = this.mTouchRect;
    paramView.left += getPaddingLeft();
    paramView = this.mTouchRect;
    paramView.right -= getPaddingRight();
    return this.mTouchRect.contains((int)paramFloat1, (int)paramFloat2);
  }

  private boolean performPinnedItemClick()
  {
    if (this.mPinnedSection == null)
      return false;
    AdapterView.OnItemClickListener localOnItemClickListener = getOnItemClickListener();
    if (localOnItemClickListener != null)
    {
      View localView = this.mPinnedSection.view;
      playSoundEffect(0);
      if (localView != null)
        localView.sendAccessibilityEvent(1);
      localOnItemClickListener.onItemClick(this, localView, this.mPinnedSection.position, this.mPinnedSection.id);
      return true;
    }
    return false;
  }

  void createPinnedShadow(int paramInt)
  {
    PinnedSection localPinnedSection = this.mRecycleSection;
    this.mRecycleSection = null;
    if (localPinnedSection == null)
      localPinnedSection = new PinnedSection();
    while (true)
    {
      View localView = getAdapter().getView(paramInt, localPinnedSection.view, this);
      AbsListView.LayoutParams localLayoutParams2 = (AbsListView.LayoutParams)localView.getLayoutParams();
      AbsListView.LayoutParams localLayoutParams1 = localLayoutParams2;
      if (localLayoutParams2 == null)
        localLayoutParams1 = new AbsListView.LayoutParams(-1, -2);
      int i = View.MeasureSpec.getMode(localLayoutParams1.height);
      int j = View.MeasureSpec.getSize(localLayoutParams1.height);
      if (i == 0)
        i = 1073741824;
      while (true)
      {
        int k = getHeight() - getListPaddingTop() - getListPaddingBottom();
        if (j > k)
          j = k;
        while (true)
        {
          localView.measure(View.MeasureSpec.makeMeasureSpec(getWidth() - getListPaddingLeft() - getListPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec(j, i));
          localView.layout(0, 0, localView.getMeasuredWidth(), localView.getMeasuredHeight());
          this.mTranslateY = 0;
          localPinnedSection.view = localView;
          localPinnedSection.position = paramInt;
          localPinnedSection.id = getAdapter().getItemId(paramInt);
          this.mPinnedSection = localPinnedSection;
          return;
        }
      }
    }
  }

  void destroyPinnedShadow()
  {
    if (this.mPinnedSection != null)
    {
      this.mRecycleSection = this.mPinnedSection;
      this.mPinnedSection = null;
    }
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    int j;
    int k;
    View localView;
    int m;
    if (this.mPinnedSection != null)
    {
      j = getListPaddingLeft();
      k = getListPaddingTop();
      localView = this.mPinnedSection.view;
      paramCanvas.save();
      m = localView.getHeight();
      if (this.mShadowDrawable != null)
        break label186;
    }
    label186: for (int i = 0; ; i = Math.min(this.mShadowHeight, this.mSectionsDistanceY))
    {
      paramCanvas.clipRect(j, k, localView.getWidth() + j, i + m + k);
      paramCanvas.translate(j, this.mTranslateY + k);
      drawChild(paramCanvas, this.mPinnedSection.view, getDrawingTime());
      if ((this.mShadowDrawable != null) && (this.mSectionsDistanceY > 0))
      {
        this.mShadowDrawable.setBounds(this.mPinnedSection.view.getLeft(), this.mPinnedSection.view.getBottom(), this.mPinnedSection.view.getRight(), this.mPinnedSection.view.getBottom() + this.mShadowHeight);
        this.mShadowDrawable.draw(paramCanvas);
      }
      paramCanvas.restore();
      return;
    }
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    int i = paramMotionEvent.getAction();
    if ((i == 0) && (this.mTouchTarget == null) && (this.mPinnedSection != null) && (isPinnedViewTouched(this.mPinnedSection.view, f1, f2)))
    {
      this.mTouchTarget = this.mPinnedSection.view;
      this.mTouchPoint.x = f1;
      this.mTouchPoint.y = f2;
      this.mDownEvent = MotionEvent.obtain(paramMotionEvent);
    }
    if (this.mTouchTarget != null)
    {
      if (isPinnedViewTouched(this.mTouchTarget, f1, f2))
        this.mTouchTarget.dispatchTouchEvent(paramMotionEvent);
      if (i == 1)
      {
        super.dispatchTouchEvent(paramMotionEvent);
        performPinnedItemClick();
        clearTouchTarget();
      }
      do
      {
        return true;
        if (i == 3)
        {
          clearTouchTarget();
          return true;
        }
      }
      while ((i != 2) || (Math.abs(f2 - this.mTouchPoint.y) <= this.mTouchSlop));
      MotionEvent localMotionEvent = MotionEvent.obtain(paramMotionEvent);
      localMotionEvent.setAction(3);
      this.mTouchTarget.dispatchTouchEvent(localMotionEvent);
      localMotionEvent.recycle();
      super.dispatchTouchEvent(this.mDownEvent);
      super.dispatchTouchEvent(paramMotionEvent);
      clearTouchTarget();
      return true;
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  void ensureShadowForPosition(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt3 < 2)
      destroyPinnedShadow();
    do
    {
      return;
      if ((this.mPinnedSection != null) && (this.mPinnedSection.position != paramInt1))
        destroyPinnedShadow();
      if (this.mPinnedSection == null)
        createPinnedShadow(paramInt1);
      paramInt1 += 1;
    }
    while (paramInt1 >= getCount());
    paramInt1 = findFirstVisibleSectionPosition(paramInt1, paramInt3 - (paramInt1 - paramInt2));
    if (paramInt1 > -1)
    {
      View localView = getChildAt(paramInt1 - paramInt2);
      paramInt1 = this.mPinnedSection.view.getBottom();
      paramInt2 = getPaddingTop();
      this.mSectionsDistanceY = (localView.getTop() - (paramInt1 + paramInt2));
      if (this.mSectionsDistanceY < 0)
      {
        this.mTranslateY = this.mSectionsDistanceY;
        return;
      }
      this.mTranslateY = 0;
      return;
    }
    this.mTranslateY = 0;
    this.mSectionsDistanceY = 2147483647;
  }

  int findCurrentSectionPosition(int paramInt)
  {
    ListAdapter localListAdapter = getAdapter();
    int i;
    if ((localListAdapter instanceof SectionIndexer))
    {
      SectionIndexer localSectionIndexer = (SectionIndexer)localListAdapter;
      i = localSectionIndexer.getPositionForSection(localSectionIndexer.getSectionForPosition(paramInt));
      if (isItemViewTypePinned(localListAdapter, localListAdapter.getItemViewType(i)))
        return i;
    }
    while (true)
    {
      if (paramInt < 0)
        break label78;
      i = paramInt;
      if (isItemViewTypePinned(localListAdapter, localListAdapter.getItemViewType(paramInt)))
        break;
      paramInt -= 1;
    }
    label78: return -1;
  }

  int findFirstVisibleSectionPosition(int paramInt1, int paramInt2)
  {
    ListAdapter localListAdapter = getAdapter();
    int i = 0;
    while (i < paramInt2)
    {
      int j = paramInt1 + i;
      if (isItemViewTypePinned(localListAdapter, localListAdapter.getItemViewType(j)))
        return j;
      i += 1;
    }
    return -1;
  }

  public void initShadow(boolean paramBoolean)
  {
    if (paramBoolean)
      if (this.mShadowDrawable == null)
      {
        this.mShadowDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] { Color.parseColor("#ffa0a0a0"), Color.parseColor("#50a0a0a0"), Color.parseColor("#00a0a0a0") });
        this.mShadowHeight = ((int)(8.0F * getResources().getDisplayMetrics().density));
      }
    while (this.mShadowDrawable == null)
      return;
    this.mShadowDrawable = null;
    this.mShadowHeight = 0;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if ((this.mPinnedSection != null) && (paramInt3 - paramInt1 - getPaddingLeft() - getPaddingRight() != this.mPinnedSection.view.getWidth()))
      recreatePinnedShadow();
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    super.onRestoreInstanceState(paramParcelable);
    post(new Runnable()
    {
      public void run()
      {
        PinnedSectionListView.this.recreatePinnedShadow();
      }
    });
  }

  void recreatePinnedShadow()
  {
    destroyPinnedShadow();
    ListAdapter localListAdapter = getAdapter();
    int i;
    int j;
    if ((localListAdapter != null) && (localListAdapter.getCount() > 0))
    {
      i = getFirstVisiblePosition();
      j = findCurrentSectionPosition(i);
      if (j != -1);
    }
    else
    {
      return;
    }
    ensureShadowForPosition(j, i, getLastVisiblePosition() - i);
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (paramListAdapter != null)
    {
      if (!(paramListAdapter instanceof PinnedSectionListAdapter))
        throw new IllegalArgumentException("Does your adapter implement PinnedSectionListAdapter?");
      if (paramListAdapter.getViewTypeCount() < 2)
        throw new IllegalArgumentException("Does your adapter handle at least two types of views in getViewTypeCount() method: items and sections?");
    }
    ListAdapter localListAdapter = getAdapter();
    if (localListAdapter != null)
      localListAdapter.unregisterDataSetObserver(this.mDataSetObserver);
    if (paramListAdapter != null)
      paramListAdapter.registerDataSetObserver(this.mDataSetObserver);
    if (localListAdapter != paramListAdapter)
      destroyPinnedShadow();
    super.setAdapter(paramListAdapter);
  }

  public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    if (paramOnScrollListener == this.mOnScrollListener)
    {
      super.setOnScrollListener(paramOnScrollListener);
      return;
    }
    this.mDelegateOnScrollListener = paramOnScrollListener;
  }

  public void setShadowVisible(boolean paramBoolean)
  {
    initShadow(paramBoolean);
    if (this.mPinnedSection != null)
    {
      View localView = this.mPinnedSection.view;
      invalidate(localView.getLeft(), localView.getTop(), localView.getRight(), localView.getBottom() + this.mShadowHeight);
    }
  }

  static class PinnedSection
  {
    public long id;
    public int position;
    public View view;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.pinnedsection.PinnedSectionListView
 * JD-Core Version:    0.6.2
 */