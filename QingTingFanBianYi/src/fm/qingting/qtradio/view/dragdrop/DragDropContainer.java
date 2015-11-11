package fm.qingting.qtradio.view.dragdrop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import java.util.ArrayList;
import java.util.List;

public class DragDropContainer extends ViewGroupViewImpl
  implements View.OnLongClickListener, View.OnClickListener
{
  private static final int BACKFROMINNERSPACE = 238;
  private static final int BACKFROMOUTERSPACE = 254;
  private static final int CROSS_CORNER_LB = 9;
  private static final int CROSS_EDGE_BOTTOM = 8;
  private static final int CROSS_EDGE_LEFT = 1;
  private static final int CROSS_EDGE_RIGHT = 2;
  private static final int CROSS_EDGE_TOP = 4;
  private static final int DURATION = 180;
  private static final int INBOUND = 0;
  private static final int INNERSPACE = 239;
  private static final int OUTERSPACE = 255;
  private static final int POSITION_INVALID = -1;
  private static final int RESETDURATION = 120;
  private static final String TAG = "dragdrop";
  private static final String TIP_DRAG = "拖动排序";
  private static final String TIP_PRESS = "长按排序";
  private DragDropAdapter mAdapter;
  private final Rect mBound = new Rect();
  private ArrayList<IView> mChildren;
  private int mCorrectionOffset = 0;
  private Bitmap mDragBitmap;
  private int mDragIndex = -1;
  private int mDragOffsetX;
  private int mDragOffsetY;
  private ImageView mDragView;
  private boolean mInDragMode = false;
  private ArrayList<Integer> mIndexs = new ArrayList();
  private float mInitialMotionX;
  private float mInitialMotionY;
  private int mItemHeight;
  private int mItemWidth;
  private DragDropListener mListener;
  private int mOuterSpaceBoundX;
  private int mOuterSpaceBoundY_left;
  private int mOuterSpaceBoundY_right;
  private RotateAnimation mRotateAnimation;
  private int mStartDragIndex = -1;
  private boolean mTravelingInnerSpace = false;
  private boolean mTravelingOuterSpace = false;
  private Vibrator mVibrator;
  private WindowManager mWindowManager;
  private WindowManager.LayoutParams mWindowParams;
  private final ViewLayout tipLayout = ViewLayout.createViewLayoutWithBoundsLT(200, 50, 720, 1200, 500, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public DragDropContainer(Context paramContext)
  {
    super(paramContext);
    this.mVibrator = ((Vibrator)paramContext.getSystemService("vibrator"));
    this.mRotateAnimation = new RotateAnimation(-3.0F, 3.0F, 1, 0.5F, 1, 0.5F);
    this.mRotateAnimation.setRepeatMode(2);
    this.mRotateAnimation.setRepeatCount(-1);
    this.mRotateAnimation.setDuration(80L);
    this.mRotateAnimation.setFillAfter(false);
    this.mRotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
  }

  private void adjustBound(int paramInt1, int paramInt2)
  {
    int i = getItemWidth();
    int j = getItemHeight();
    this.mBound.set(paramInt1, paramInt2, i + paramInt1, j + paramInt2);
  }

  private void changeDragIndex(int paramInt1, int paramInt2)
  {
    if ((paramInt2 < 0) || (isItemFixed(paramInt2)));
    int i;
    do
    {
      return;
      i = paramInt2;
      if (paramInt2 >= this.mChildren.size())
        i = this.mChildren.size() - 1;
    }
    while (paramInt1 == i);
    this.mDragIndex = i;
    Object localObject = (IView)this.mChildren.remove(paramInt1);
    this.mChildren.add(i, localObject);
    localObject = (Integer)this.mIndexs.remove(paramInt1);
    this.mIndexs.add(i, localObject);
    adjustBound(getItemLeft(i), getItemTop(i));
    layoutItems();
    doTranslateAnimations(paramInt1, i);
  }

  private void doTranslateAnimations(int paramInt1, int paramInt2)
  {
    int i = getRow(paramInt1);
    int j = getRow(paramInt2);
    int k = this.mAdapter.getColumnCount();
    if (i == j)
      translateViewsHorizonallyByRange(paramInt1, paramInt2, true);
    do
    {
      return;
      if (i > j)
      {
        if (paramInt1 % k != 0)
          translateViewsHorizonallyByRange(paramInt1, i * k, false);
        translateViewAcrossRow(getAcrossRowView(i, j), i, j, true);
        translateViewsHorizonallyByRange((j + 1) * k - 1, paramInt2, false);
        return;
      }
      translateViewsHorizonallyByRange(paramInt1, (i + 1) * k - 1, false);
      translateViewAcrossRow(getAcrossRowView(i, j), i, j, true);
    }
    while (paramInt2 % k == 0);
    translateViewsHorizonallyByRange(j * k, paramInt2, false);
  }

  private void drag(int paramInt1, int paramInt2)
  {
    this.mWindowParams.x = paramInt1;
    this.mWindowParams.y = (getCorrectionOffset() + paramInt2);
    this.mWindowManager.updateViewLayout(this.mDragView, this.mWindowParams);
    paramInt1 = getCrossEdgeState(getItemWidth() / 2 + paramInt1, getItemHeight() / 2 + paramInt2);
    paramInt2 = this.mDragIndex;
    int i = this.mAdapter.getColumnCount();
    switch (paramInt1)
    {
    default:
    case 1:
    case 4:
    case 2:
    case 8:
    case 255:
    case 239:
    case 9:
    }
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
              do
              {
                do
                  return;
                while (paramInt2 % i <= 0);
                changeDragIndex(paramInt2, paramInt2 - 1);
                return;
              }
              while (paramInt2 / i <= 0);
              changeDragIndex(paramInt2, paramInt2 - i);
              return;
            }
            while ((paramInt2 % i == i - 1) || (paramInt2 == this.mChildren.size() - 1));
            changeDragIndex(paramInt2, paramInt2 + 1);
            return;
          }
          while (paramInt2 / i == this.mChildren.size() / i);
          changeDragIndex(paramInt2, paramInt2 + i);
          return;
        }
        while (this.mTravelingOuterSpace);
        this.mTravelingOuterSpace = true;
        changeDragIndex(paramInt2, this.mChildren.size() - 1);
        return;
      }
      while (this.mTravelingInnerSpace);
      this.mTravelingInnerSpace = true;
      return;
    }
    while (paramInt2 % i <= 0);
    changeDragIndex(paramInt2, paramInt2 + i - 1);
  }

  private void endDragging()
  {
    if (this.mDragView != null)
    {
      this.mDragView.setVisibility(8);
      if (this.mWindowManager != null)
        this.mWindowManager.removeView(this.mDragView);
      this.mDragView.setImageDrawable(null);
      this.mDragView = null;
    }
    if (this.mDragBitmap != null)
    {
      this.mDragBitmap.recycle();
      this.mDragBitmap = null;
    }
    if (this.mDragIndex != -1)
    {
      if (this.mWindowParams != null)
        resetDraggingView(this.mWindowParams.x, this.mWindowParams.y - getCorrectionOffset());
      if (this.mListener != null)
        this.mListener.onDrop(this.mStartDragIndex, this.mDragIndex);
    }
    this.mDragIndex = -1;
  }

  private int findIndex(View paramView)
  {
    ArrayList localArrayList = this.mChildren;
    int j = localArrayList.size();
    int i = 0;
    while (i < j)
    {
      if (paramView.equals(((IView)localArrayList.get(i)).getView()))
        return i;
      i += 1;
    }
    return -1;
  }

  private int findPosition(float paramFloat1, float paramFloat2)
  {
    ArrayList localArrayList = this.mChildren;
    int j = localArrayList.size();
    int i = 0;
    while (i < j)
    {
      if (!isItemFixed(i))
      {
        View localView = ((IView)localArrayList.get(i)).getView();
        if ((paramFloat1 > localView.getLeft()) && (paramFloat1 < localView.getRight()) && (paramFloat2 > localView.getTop()) && (paramFloat2 < localView.getBottom()))
          return i;
      }
      i += 1;
    }
    return -1;
  }

  private int findWhereToLand(int paramInt1, int paramInt2)
  {
    int i = 0;
    while (i < this.mChildren.size())
    {
      int j = getItemLeft(i);
      int k = getItemTop(i);
      int m = getItemWidth();
      int n = getItemHeight();
      if ((paramInt1 >= j) && (paramInt1 < m + j) && (paramInt2 >= k) && (paramInt2 < n + k))
        return i;
      i += 1;
    }
    return -1;
  }

  private View getAcrossRowView(int paramInt1, int paramInt2)
  {
    int i = this.mAdapter.getColumnCount();
    if (paramInt1 < paramInt2)
      return ((IView)this.mChildren.get(i + paramInt1 * i - 1)).getView();
    if (paramInt1 > paramInt2)
      return ((IView)this.mChildren.get(i * paramInt1)).getView();
    return null;
  }

  private final int getCorrectionOffset()
  {
    return this.mCorrectionOffset;
  }

  private final int getCrossEdgeState(int paramInt1, int paramInt2)
  {
    int j = 0;
    int i;
    if (isInnerSpace(paramInt1, paramInt2))
      i = 239;
    Rect localRect;
    do
    {
      do
      {
        return i;
        if (inOuterSpace(paramInt1, paramInt2))
          return 255;
        if (this.mTravelingOuterSpace)
        {
          i = findWhereToLand(paramInt1, paramInt2);
          if (i != -1)
          {
            changeDragIndex(this.mDragIndex, i);
            this.mTravelingOuterSpace = false;
            return 254;
          }
        }
        if (this.mTravelingInnerSpace)
        {
          i = findWhereToLand(paramInt1, paramInt2);
          if (i != -1)
          {
            changeDragIndex(this.mDragIndex, i);
            this.mTravelingInnerSpace = false;
            return 238;
          }
        }
        localRect = this.mBound;
        i = j;
      }
      while (localRect.contains(paramInt1, paramInt2));
      if (paramInt1 >= localRect.right)
        return 2;
      if (paramInt2 < localRect.top)
        return 4;
      if ((paramInt1 < localRect.left) && (paramInt2 >= localRect.bottom))
        return 9;
      if (paramInt1 < localRect.left)
        return 1;
      i = j;
    }
    while (paramInt2 < localRect.bottom);
    return 8;
  }

  private int getItemHeight()
  {
    return this.mItemHeight;
  }

  private int getItemLeft(int paramInt)
  {
    return this.mItemWidth * (paramInt % this.mAdapter.getColumnCount());
  }

  private int getItemTop(int paramInt)
  {
    return this.mItemHeight * (paramInt / this.mAdapter.getColumnCount());
  }

  private int getItemWidth()
  {
    return this.mItemWidth;
  }

  private final int getRow(int paramInt)
  {
    return paramInt / this.mAdapter.getColumnCount();
  }

  private boolean inOuterSpace(int paramInt1, int paramInt2)
  {
    return ((paramInt1 < this.mOuterSpaceBoundX) && (paramInt2 > this.mOuterSpaceBoundY_left)) || ((paramInt1 > this.mOuterSpaceBoundX) && (paramInt2 > this.mOuterSpaceBoundY_right));
  }

  private final boolean isDragging()
  {
    return this.mDragIndex != -1;
  }

  private final boolean isInDragMode()
  {
    return this.mInDragMode;
  }

  private boolean isInnerSpace(int paramInt1, int paramInt2)
  {
    return paramInt2 < 0;
  }

  private final boolean isItemFixed(int paramInt)
  {
    return this.mAdapter.isFixed(paramInt);
  }

  private void isReadyToGo(View paramView)
  {
    int i = findIndex(paramView);
    if (isItemFixed(i))
      return;
    this.mVibrator.vibrate(50L);
    if (this.mListener != null)
      this.mListener.onEnterDragMode(i);
    if (i != -1)
    {
      this.mDragIndex = i;
      this.mStartDragIndex = i;
      startDragging(paramView, getItemLeft(i), getItemTop(i));
    }
    paramView.setVisibility(4);
    enableDragDrop();
  }

  private void layoutItems()
  {
    if ((this.mAdapter == null) || (this.mChildren == null))
      throw new IllegalStateException("u should call setDragDropAdapter right after constructor");
    int i = 0;
    while (i < this.mChildren.size())
    {
      View localView = ((IView)this.mChildren.get(i)).getView();
      int j = getItemLeft(i);
      int k = getItemTop(i);
      localView.layout(j, k, this.mItemWidth + j, this.mItemHeight + k);
      if (i == this.mChildren.size() - 1)
      {
        this.mOuterSpaceBoundX = (this.mItemWidth + j);
        this.mOuterSpaceBoundY_right = k;
        this.mOuterSpaceBoundY_left = (this.mItemHeight + k);
      }
      i += 1;
    }
  }

  private void letsRock()
  {
    int i = 0;
    while (i < this.mChildren.size())
    {
      if ((i != this.mDragIndex) && (!isItemFixed(i)))
      {
        View localView = ((IView)this.mChildren.get(i)).getView();
        if (localView.getAnimation() != this.mRotateAnimation)
          localView.startAnimation(this.mRotateAnimation);
      }
      i += 1;
    }
  }

  private void onItemClick(View paramView)
  {
    if (this.mListener == null);
    int i;
    do
    {
      return;
      i = findIndex(paramView);
    }
    while (i == -1);
    this.mListener.onItemClick(i);
  }

  private void onTouchDown(MotionEvent paramMotionEvent)
  {
    this.mInitialMotionX = paramMotionEvent.getX();
    this.mInitialMotionY = paramMotionEvent.getY();
    if (!isInDragMode());
    int i;
    do
    {
      return;
      i = findPosition(this.mInitialMotionX, this.mInitialMotionY);
    }
    while (i == -1);
    this.mDragIndex = i;
    this.mStartDragIndex = i;
    paramMotionEvent = ((IView)this.mChildren.get(i)).getView();
    paramMotionEvent.clearAnimation();
    startDragging(paramMotionEvent, getItemLeft(i), getItemTop(i));
    paramMotionEvent.setVisibility(4);
  }

  private void onTouchMove(MotionEvent paramMotionEvent)
  {
    if (!isDragging())
      return;
    drag((int)(paramMotionEvent.getX() - this.mDragOffsetX), (int)(paramMotionEvent.getY() - this.mDragOffsetY));
  }

  private void onTouchUp(MotionEvent paramMotionEvent)
  {
    endDragging();
  }

  private void resetDraggingView(int paramInt1, int paramInt2)
  {
    if (!isDragging())
      return;
    View localView = ((IView)this.mChildren.get(this.mDragIndex)).getView();
    localView.setVisibility(0);
    if (paramInt2 < 0)
    {
      localView.startAnimation(this.mRotateAnimation);
      return;
    }
    int i = getItemLeft(this.mDragIndex);
    int j = getItemTop(this.mDragIndex);
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(paramInt1 - i, 0.0F, paramInt2 - j, 0.0F);
    localTranslateAnimation.setDuration(120L);
    localTranslateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
    localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        DragDropContainer.this.letsRock();
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    localView.clearAnimation();
    localView.startAnimation(localTranslateAnimation);
  }

  private void startDragging(View paramView, int paramInt1, int paramInt2)
  {
    if (this.mWindowParams == null)
    {
      this.mWindowParams = new WindowManager.LayoutParams();
      this.mWindowParams.gravity = 51;
      this.mWindowParams.height = -2;
      this.mWindowParams.width = -2;
      this.mWindowParams.flags = 664;
      this.mWindowParams.format = -3;
      this.mWindowParams.windowAnimations = 0;
    }
    this.mWindowParams.x = paramInt1;
    this.mWindowParams.y = (getCorrectionOffset() + paramInt2);
    adjustBound(paramInt1, paramInt2);
    this.mDragOffsetX = ((int)(this.mInitialMotionX - paramInt1));
    this.mDragOffsetY = ((int)(this.mInitialMotionY - paramInt2));
    ImageView localImageView = new ImageView(getContext());
    localImageView.setPadding(0, 0, 0, 0);
    paramView.setDrawingCacheEnabled(true);
    Bitmap localBitmap = Bitmap.createBitmap(paramView.getDrawingCache());
    localImageView.setImageBitmap(localBitmap);
    this.mDragBitmap = localBitmap;
    paramView.destroyDrawingCache();
    if (this.mWindowManager == null)
      this.mWindowManager = ((WindowManager)getContext().getSystemService("window"));
    this.mWindowManager.addView(localImageView, this.mWindowParams);
    this.mDragView = localImageView;
    if (this.mListener != null)
      this.mListener.onDragStart(this.mDragIndex);
  }

  private void stopRocking()
  {
    int i = 0;
    while (i < this.mChildren.size())
    {
      if ((i != this.mDragIndex) && (!isItemFixed(i)))
        ((IView)this.mChildren.get(i)).getView().clearAnimation();
      i += 1;
    }
  }

  private void translateViewAcrossRow(View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramInt1 == paramInt2)
      return;
    int i = this.mAdapter.getColumnCount();
    i = (i - 1) * getWidth() / i;
    int j = getItemHeight();
    if (paramInt1 > paramInt2);
    for (float f = i; ; f = -i)
    {
      TranslateAnimation localTranslateAnimation = new TranslateAnimation(f, 0.0F, (paramInt2 - paramInt1) * j, 0.0F);
      localTranslateAnimation.setDuration(180L);
      localTranslateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
      if (paramBoolean)
        localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymousAnimation)
          {
            DragDropContainer.this.letsRock();
          }

          public void onAnimationRepeat(Animation paramAnonymousAnimation)
          {
          }

          public void onAnimationStart(Animation paramAnonymousAnimation)
          {
          }
        });
      paramView.clearAnimation();
      paramView.startAnimation(localTranslateAnimation);
      return;
    }
  }

  private void translateViewsHorizonallyByRange(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramInt1 == paramInt2)
      return;
    float f;
    label19: TranslateAnimation localTranslateAnimation;
    if (paramInt1 > paramInt2)
    {
      f = -getItemWidth();
      localTranslateAnimation = new TranslateAnimation(f, 0.0F, 0.0F, 0.0F);
      localTranslateAnimation.setDuration(180L);
      localTranslateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
      if (paramInt1 <= paramInt2)
        break label139;
      int i = paramInt2 + 1;
      paramInt2 = paramInt1 + 1;
      paramInt1 = i;
    }
    label139: 
    while (true)
    {
      if (paramBoolean)
        localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymousAnimation)
          {
            DragDropContainer.this.letsRock();
          }

          public void onAnimationRepeat(Animation paramAnonymousAnimation)
          {
          }

          public void onAnimationStart(Animation paramAnonymousAnimation)
          {
          }
        });
      while (paramInt1 < paramInt2)
      {
        View localView = ((IView)this.mChildren.get(paramInt1)).getView();
        localView.clearAnimation();
        localView.startAnimation(localTranslateAnimation);
        paramInt1 += 1;
      }
      break;
      f = getItemWidth();
      break label19;
    }
  }

  public void disableDragDrop()
  {
    this.mInDragMode = false;
    stopRocking();
  }

  public void enableDragDrop()
  {
    this.mInDragMode = true;
    letsRock();
  }

  public void ensureWindowRemoved()
  {
    endDragging();
  }

  public DragDropAdapter getAdapter()
  {
    return this.mAdapter;
  }

  public ArrayList<Integer> getIndexsList()
  {
    return this.mIndexs;
  }

  public List<IView> getItems()
  {
    return this.mChildren;
  }

  public int getSelectedIndex()
  {
    int j;
    if (this.mChildren == null)
    {
      j = -1;
      return j;
    }
    int i = 0;
    while (true)
    {
      if (i >= this.mChildren.size())
        break label55;
      j = i;
      if (((IView)this.mChildren.get(i)).getView().isSelected())
        break;
      i += 1;
    }
    label55: return -1;
  }

  final void log(String paramString)
  {
    Log.e("dragdrop", paramString);
  }

  public void onClick(View paramView)
  {
    if (!isInDragMode())
      onItemClick(paramView);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if ((i != 1) && (isInDragMode()))
      return true;
    switch (i & 0xFF)
    {
    case 2:
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      return super.onInterceptTouchEvent(paramMotionEvent);
      onTouchDown(paramMotionEvent);
      continue;
      onTouchUp(paramMotionEvent);
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = this.mChildren.size();
    getItemTop(paramInt1 - 1);
    if (paramInt1 % this.mAdapter.getColumnCount() == 0)
      paramInt1 = this.mItemHeight;
    layoutItems();
  }

  public boolean onLongClick(View paramView)
  {
    if (!this.mInDragMode)
      isReadyToGo(paramView);
    return true;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if ((this.mAdapter == null) || (this.mChildren == null))
      throw new IllegalStateException("u should call setDragDropAdapter right after constructor");
    this.tipLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    int i = this.mAdapter.getColumnCount();
    i = View.MeasureSpec.getSize(paramInt1) / i;
    this.mItemWidth = i;
    int j = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
    int k = this.mChildren.size();
    i = 0;
    while (i < k)
    {
      ((IView)this.mChildren.get(i)).getView().measure(j, paramInt2);
      if (i == 0)
        this.mItemHeight = ((IView)this.mChildren.get(i)).getView().getMeasuredHeight();
      i += 1;
    }
    super.onMeasure(paramInt1, paramInt2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction() & 0xFF)
    {
    default:
    case 0:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      return true;
      onTouchDown(paramMotionEvent);
      continue;
      onTouchMove(paramMotionEvent);
      continue;
      onTouchUp(paramMotionEvent);
    }
  }

  public void setCorrectionOffset(int paramInt)
  {
    this.mCorrectionOffset = paramInt;
  }

  public void setDragDropAdapter(DragDropAdapter paramDragDropAdapter)
  {
    if (this.mAdapter != null)
      throw new IllegalStateException("adapter already set, don't set it twice");
    this.mAdapter = paramDragDropAdapter;
    int j = this.mAdapter.getCount();
    if (this.mChildren == null)
      this.mChildren = new ArrayList();
    while (true)
    {
      int i = 0;
      while (i < j)
      {
        paramDragDropAdapter = this.mAdapter.instantiateItem(i);
        if (paramDragDropAdapter != null)
        {
          View localView = paramDragDropAdapter.getView();
          localView.setOnLongClickListener(this);
          localView.setOnClickListener(this);
          addView(localView);
          this.mChildren.add(paramDragDropAdapter);
        }
        i += 1;
      }
      this.mChildren.clear();
      removeAllViews();
    }
  }

  public void setDragDropListener(DragDropListener paramDragDropListener)
  {
    this.mListener = paramDragDropListener;
  }

  public void setIndexsList(ArrayList<Integer> paramArrayList)
  {
    this.mIndexs.clear();
    int i = 0;
    while (i < paramArrayList.size())
    {
      this.mIndexs.add(paramArrayList.get(i));
      i += 1;
    }
  }

  public boolean toggleDragDrop()
  {
    if (this.mInDragMode)
      disableDragDrop();
    while (true)
    {
      return this.mInDragMode;
      enableDragDrop();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.dragdrop.DragDropContainer
 * JD-Core Version:    0.6.2
 */