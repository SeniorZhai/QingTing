package fm.qingting.qtradio.view.switchview;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.view.ViewConfigurationCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.ImageView;
import fm.qingting.framework.tween.FrameTween;
import fm.qingting.framework.tween.FrameTween.SyncType;
import fm.qingting.framework.tween.IMotionHandler;
import fm.qingting.framework.tween.MotionController;
import fm.qingting.framework.tween.TweenProperty;
import fm.qingting.framework.tween.easing.Linear.EaseIn;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.utils.ImageLoader;
import fm.qingting.framework.utils.ImageLoaderHandler;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RecommendItemNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class SwitchView extends ViewImpl
  implements IMotionHandler, ImageLoaderHandler
{
  private static final int LEFT = 1;
  private static final int NONE = 0;
  private static final int RIGHT = 2;
  private List<IPageChangedListener> changedListeners;
  private int currentItem = 0;
  private int direction = 0;
  private final ViewLayout dotsLayout = this.itemLayout.createChildLT(720, 4, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final DrawFilter filter;
  private boolean hasMoved = false;
  private boolean indexChanged = false;
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 342, 720, 342, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Object mAnimator;
  private boolean mForbidSwitch = true;
  private float mInitialMotionX;
  private float mInitialMotionY;
  private boolean mIsBeingDragged;
  private float mLastMotionX = 0.0F;
  private long mLastMovementTime = 0L;
  private float mLastTwoMoveEventDistance = 0.0F;
  private long mLastTwoMoveEventInterval = 0L;
  private List<Node> mList = new ArrayList();
  private final HashSet<String> mLoadingUrls = new HashSet();
  private float mMinimumFlingVelocity;
  private final TextPaint mNamePaint = new TextPaint();
  private final Paint mPaint = new Paint();
  private final Rect mPicRect = new Rect();
  private final Rect mTextBound = new Rect();
  private int mTouchSlop;
  private final HashMap<String, String> mTrimedTexts = new HashMap();
  private long mUpEventMoveEventInterval = 0L;
  private boolean motionCompleted = false;
  private MotionController motionController;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(720, 64, 25, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout picLayout = this.itemLayout.createChildLT(720, 278, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Runnable run = new Runnable()
  {
    public void run()
    {
      if (SwitchView.this.isShown())
        SwitchView.this.switchToNext();
      SwitchView.this.startHandler(false);
    }
  };
  private Handler switchHandler = new Handler();
  private boolean touchedDown = false;
  private float xStartPosition = 0.0F;
  private float xoffset = 0.0F;

  public SwitchView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mNamePaint.setColor(SkinManager.getTextColorNormal_New());
    this.filter = SkinManager.getInstance().getDrawFilter();
    this.changedListeners = new ArrayList();
    paramContext = ViewConfiguration.get(paramContext);
    this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(paramContext);
    this.mMinimumFlingVelocity = paramContext.getScaledMinimumFlingVelocity();
    init();
  }

  private void dispatchPageChangedEvent(int paramInt1, int paramInt2)
  {
    if ((this.changedListeners == null) || (this.changedListeners.size() <= 0));
    while (true)
    {
      return;
      Iterator localIterator = this.changedListeners.iterator();
      while (localIterator.hasNext())
        ((IPageChangedListener)localIterator.next()).onPageIndexChanged(paramInt2, paramInt1);
    }
  }

  private void dispathEvent(int paramInt)
  {
    if ((this.mList == null) || (this.mList.size() <= 0))
      return;
    int i = paramInt % this.mList.size();
    paramInt = i;
    if (i < 0)
      paramInt = i + this.mList.size();
    ControllerManager.getInstance().openControllerByRecommendNode((Node)this.mList.get(paramInt));
  }

  private void drawDefaultBg(Canvas paramCanvas, Rect paramRect)
  {
    paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130837983), null, paramRect, this.mPaint);
  }

  private void drawDots(Canvas paramCanvas)
  {
    if (this.mForbidSwitch)
      return;
    int k = this.mList.size();
    int j = this.currentItem % k;
    int i = j;
    if (j < 0)
      i = j + k;
    j = this.itemLayout.width / k;
    float f = this.xoffset / this.itemLayout.width;
    k = (int)(j * f);
    if ((i == 0) && (f > 0.0F))
    {
      drawSlide(paramCanvas, 0, j - k);
      drawSlide(paramCanvas, this.itemLayout.width - k, this.itemLayout.width);
      return;
    }
    if ((i == this.mList.size() - 1) && (f < 0.0F))
    {
      drawSlide(paramCanvas, 0, -k);
      drawSlide(paramCanvas, this.itemLayout.width - j - k, this.itemLayout.width);
      return;
    }
    i = j * i - k;
    drawSlide(paramCanvas, i, j + i);
  }

  private void drawName(Canvas paramCanvas, String paramString, float paramFloat)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    paramString = trimTextIfNeed(paramString);
    Rect localRect = this.mTextBound;
    this.mNamePaint.getTextBounds(paramString, 0, paramString.length(), localRect);
    float f = this.nameLayout.leftMargin;
    int i = this.picLayout.height;
    paramCanvas.drawText(paramString, f + paramFloat, (this.nameLayout.height - localRect.top - localRect.bottom) / 2 + i, this.mNamePaint);
  }

  private void drawPics(Canvas paramCanvas)
  {
    List localList = this.mList;
    int n;
    int k;
    int m;
    if ((localList != null) && (localList.size() != 0))
    {
      n = localList.size();
      if (this.xoffset > 0.0F)
      {
        k = 1;
        m = this.currentItem % n;
        if (m >= 0)
          break label189;
        m += n;
      }
    }
    label168: label189: 
    while (true)
    {
      int j;
      if (k != 0)
      {
        j = m - 1;
        label70: i = j;
        if (j < 0)
          i = j + n;
        j = i;
        if (i >= n)
          j = i - n;
        drawPicture(paramCanvas, m, this.xoffset);
        if (k == 0)
          break label168;
      }
      for (int i = -this.itemLayout.width; ; i = this.itemLayout.width)
      {
        drawPicture(paramCanvas, j, i + this.xoffset);
        drawDots(paramCanvas);
        this.xoffset = 0.0F;
        refreshItem();
        return;
        k = 0;
        break;
        j = m + 1;
        break label70;
      }
      drawDefaultBg(paramCanvas, this.mPicRect);
      return;
    }
  }

  private void drawPicture(Canvas paramCanvas, int paramInt, float paramFloat)
  {
    RecommendItemNode localRecommendItemNode = (RecommendItemNode)this.mList.get(paramInt);
    String str1 = localRecommendItemNode.name;
    String str2 = localRecommendItemNode.getSmallThumb();
    if ((localRecommendItemNode.mNode != null) && (localRecommendItemNode.mNode.nodeName.equalsIgnoreCase("channel")) && ((str1 == null) || (str1.equalsIgnoreCase(""))))
      str1 = ((ChannelNode)localRecommendItemNode.mNode).title;
    while (true)
    {
      drawThumb(paramCanvas, str2, (int)paramFloat);
      drawName(paramCanvas, str1, paramFloat);
      return;
    }
  }

  private void drawSlide(Canvas paramCanvas, int paramInt1, int paramInt2)
  {
    int i = paramCanvas.save();
    paramCanvas.clipRect(paramInt1, this.itemLayout.height - this.dotsLayout.height, paramInt2, this.itemLayout.height);
    paramCanvas.drawColor(SkinManager.getTextColorHighlight());
    paramCanvas.restoreToCount(i);
  }

  private void drawThumb(Canvas paramCanvas, String paramString, int paramInt)
  {
    Rect localRect = this.mPicRect;
    localRect.offset(paramInt, 0);
    Bitmap localBitmap;
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
    {
      localBitmap = ImageLoader.getInstance(getContext()).getImage(paramString, this.picLayout.width, this.picLayout.height);
      if (localBitmap == null)
      {
        loadImageIfNeed(paramString);
        drawDefaultBg(paramCanvas, localRect);
      }
    }
    while (true)
    {
      localRect.offset(-paramInt, 0);
      return;
      paramCanvas.drawBitmap(localBitmap, null, localRect, this.mPaint);
      continue;
      drawDefaultBg(paramCanvas, localRect);
    }
  }

  private void generateRect()
  {
    this.mPicRect.set(0, 0, this.picLayout.width, this.picLayout.height);
  }

  private void getDirection()
  {
    if (this.mUpEventMoveEventInterval > ViewConfiguration.getTapTimeout())
      this.direction = 0;
    float f;
    do
    {
      return;
      if (this.mLastTwoMoveEventInterval <= 0L)
      {
        this.direction = 0;
        return;
      }
      f = this.mLastTwoMoveEventDistance * 1000.0F / (float)this.mLastTwoMoveEventInterval;
    }
    while (Math.abs(f) <= this.mMinimumFlingVelocity);
    if (f > 0.0F)
    {
      this.direction = 2;
      return;
    }
    this.direction = 1;
  }

  @TargetApi(11)
  private void init()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      this.mAnimator = new ValueAnimator();
      ((ValueAnimator)this.mAnimator).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          SwitchView.this.setPosition(((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
        }
      });
      ((ValueAnimator)this.mAnimator).addListener(new Animator.AnimatorListener()
      {
        public void onAnimationCancel(Animator paramAnonymousAnimator)
        {
        }

        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          SwitchView.access$202(SwitchView.this, true);
        }

        public void onAnimationRepeat(Animator paramAnonymousAnimator)
        {
        }

        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
        }
      });
      return;
    }
    this.motionController = new MotionController(this);
  }

  @TargetApi(11)
  private boolean isAnimationRunning()
  {
    if (isHighApiAvailable())
      return ((ValueAnimator)this.mAnimator).isRunning();
    return this.motionController.isRunning();
  }

  private boolean isHighApiAvailable()
  {
    return (QtApiLevelManager.isApiLevelSupported(11)) && (this.mAnimator != null);
  }

  private void loadImageIfNeed(String paramString)
  {
    if (this.mLoadingUrls.contains(paramString))
      return;
    this.mLoadingUrls.add(paramString);
    ImageLoader.getInstance(getContext()).loadImage(paramString, null, this, this.picLayout.width, this.picLayout.height, this);
  }

  private void preLoad()
  {
  }

  private void refreshItem()
  {
    if (this.motionCompleted)
    {
      this.motionCompleted = false;
      if (this.indexChanged);
    }
    else
    {
      return;
    }
    switch (this.direction)
    {
    default:
      return;
    case 1:
      i = this.currentItem;
      j = this.currentItem + 1;
      this.currentItem = j;
      dispatchPageChangedEvent(i, j);
      return;
    case 2:
    }
    int i = this.currentItem;
    int j = this.currentItem - 1;
    this.currentItem = j;
    dispatchPageChangedEvent(i, j);
  }

  @TargetApi(11)
  private void selfMotion(boolean paramBoolean)
  {
    if (paramBoolean);
    for (float f = -1.0F; isHighApiAvailable(); f = 1.0F)
    {
      ((ValueAnimator)this.mAnimator).setDuration(350L);
      ((ValueAnimator)this.mAnimator).setFloatValues(new float[] { 0.0F, f });
      ((ValueAnimator)this.mAnimator).start();
      return;
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new TweenProperty("position", 0.0F, f, 20.0F, new Linear.EaseIn()));
    FrameTween.to(this.motionController, this.motionController, localArrayList, FrameTween.SyncType.ASYNC);
  }

  private void setPosition(float paramFloat)
  {
    this.xoffset = (this.itemLayout.width * paramFloat);
    invalidate();
  }

  @TargetApi(11)
  private void startAnimation(boolean paramBoolean)
  {
    float f1 = 0.0F;
    float f2 = this.xoffset / this.itemLayout.width;
    switch (this.direction)
    {
    default:
      if (f2 > 0.5F)
      {
        this.indexChanged = true;
        this.direction = 2;
        f1 = 1.0F;
      }
      break;
    case 1:
    case 2:
    }
    while (isHighApiAvailable())
    {
      long l = Math.abs(()((f1 - f2) * 350.0F));
      ((ValueAnimator)this.mAnimator).setDuration(l);
      ((ValueAnimator)this.mAnimator).setFloatValues(new float[] { f2, f1 });
      ((ValueAnimator)this.mAnimator).start();
      return;
      if (f2 > 0.0F)
      {
        this.indexChanged = false;
      }
      else
      {
        this.indexChanged = true;
        f1 = -1.0F;
        continue;
        if (f2 > 0.0F)
        {
          this.indexChanged = true;
          f1 = 1.0F;
        }
        else
        {
          this.indexChanged = false;
          continue;
          if (f2 < -0.5F)
          {
            this.indexChanged = true;
            this.direction = 1;
            f1 = -1.0F;
          }
          else
          {
            this.indexChanged = false;
          }
        }
      }
    }
    int i = Math.abs((int)((f1 - f2) * 20.0F));
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new TweenProperty("position", f2, f1, i, new Linear.EaseIn()));
    FrameTween.to(this.motionController, this.motionController, localArrayList, FrameTween.SyncType.ASYNC);
  }

  private void startHandler(boolean paramBoolean)
  {
    if ((this.mForbidSwitch) || (this.mList == null) || (this.mList.size() <= 0))
      return;
    if (this.switchHandler == null)
      this.switchHandler = new Handler();
    int i = this.currentItem % this.mList.size();
    if (i < 0)
      this.mList.size();
    this.switchHandler.removeCallbacks(this.run);
    Handler localHandler = this.switchHandler;
    Runnable localRunnable = this.run;
    if (paramBoolean);
    for (long l = 5000L; ; l = 3000L)
    {
      localHandler.postDelayed(localRunnable, l);
      return;
    }
  }

  private String trimTextIfNeed(String paramString)
  {
    if (this.mTrimedTexts.containsKey(paramString))
      return (String)this.mTrimedTexts.get(paramString);
    String str = TextUtils.ellipsize(paramString, this.mNamePaint, this.itemLayout.width - this.nameLayout.leftMargin * 2, TextUtils.TruncateAt.END).toString();
    this.mTrimedTexts.put(paramString, str);
    return str;
  }

  public void addPageChangedListener(IPageChangedListener paramIPageChangedListener)
  {
    this.changedListeners.add(paramIPageChangedListener);
  }

  public void close(boolean paramBoolean)
  {
    FrameTween.cancel(this.motionController);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction() & 0xFF;
    if (i == 3)
    {
      this.mIsBeingDragged = false;
      getParent().requestDisallowInterceptTouchEvent(false);
      startHandler(false);
      return false;
    }
    if (i == 1)
    {
      this.mIsBeingDragged = false;
      getParent().requestDisallowInterceptTouchEvent(false);
      return super.dispatchTouchEvent(paramMotionEvent);
    }
    if ((i != 0) && (this.mIsBeingDragged))
      return super.dispatchTouchEvent(paramMotionEvent);
    switch (i)
    {
    case 1:
    default:
    case 2:
    case 0:
    }
    while (true)
    {
      return super.dispatchTouchEvent(paramMotionEvent);
      float f1 = paramMotionEvent.getX() - this.mLastMotionX;
      float f2 = Math.abs(f1);
      float f3 = Math.abs(paramMotionEvent.getY() - this.mInitialMotionY);
      if (f2 > this.mTouchSlop)
      {
        this.mIsBeingDragged = true;
        if (f1 > 0.0F);
        for (f1 = this.mInitialMotionX + this.mTouchSlop; ; f1 = this.mInitialMotionX - this.mTouchSlop)
        {
          this.mLastMotionX = f1;
          break;
        }
      }
      if (f3 > this.mTouchSlop)
      {
        continue;
        f1 = paramMotionEvent.getX();
        this.mInitialMotionX = f1;
        this.mLastMotionX = f1;
        getParent().requestDisallowInterceptTouchEvent(true);
        this.mIsBeingDragged = false;
      }
    }
  }

  public int getCurrentItem()
  {
    return this.currentItem;
  }

  public void loadImageFinish(boolean paramBoolean, String paramString, Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (paramBoolean)
    {
      this.mLoadingUrls.remove(paramString);
      invalidate();
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(this.filter);
    paramCanvas.save();
    drawPics(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.itemLayout.scaleToBounds(paramInt1, paramInt2);
    this.picLayout.scaleToBounds(this.itemLayout);
    this.dotsLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.mNamePaint.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    generateRect();
    if ((this.mList == null) || (this.mList.size() == 0))
    {
      setMeasuredDimension(this.itemLayout.width, 0);
      return;
    }
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void onMotionCancel(MotionController paramMotionController)
  {
  }

  public void onMotionComplete(MotionController paramMotionController)
  {
    this.motionCompleted = true;
  }

  public void onMotionProgress(MotionController paramMotionController, float paramFloat1, float paramFloat2)
  {
    setPosition(paramFloat1);
  }

  public void onMotionStart(MotionController paramMotionController)
  {
  }

  public void onTargetChange(MotionController paramMotionController, float paramFloat)
  {
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (isAnimationRunning());
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
              return true;
              if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getEdgeFlags() != 0))
                return false;
              switch (paramMotionEvent.getAction())
              {
              default:
                return true;
              case 0:
                this.touchedDown = true;
                this.hasMoved = false;
                this.direction = 0;
                this.xStartPosition = paramMotionEvent.getX();
                this.mLastMovementTime = paramMotionEvent.getEventTime();
                this.mLastMotionX = paramMotionEvent.getX();
              case 2:
              case 1:
              case 3:
              case 4:
              }
            }
            while (this.switchHandler == null);
            this.switchHandler.removeCallbacks(this.run);
            return true;
          }
          while ((!this.mIsBeingDragged) || (!this.touchedDown));
          if ((!this.hasMoved) && (Math.abs(paramMotionEvent.getX() - this.xStartPosition) > this.itemLayout.width / 20))
            this.hasMoved = true;
        }
        while (this.mForbidSwitch);
        long l = paramMotionEvent.getEventTime();
        this.mLastTwoMoveEventInterval = (l - this.mLastMovementTime);
        this.mLastMovementTime = l;
        float f = paramMotionEvent.getX();
        this.xoffset = (f - this.xStartPosition);
        this.mLastTwoMoveEventDistance = (f - this.mLastMotionX);
        this.mLastMotionX = f;
        invalidate();
        return true;
        if (!this.hasMoved)
        {
          startHandler(false);
          dispathEvent(this.currentItem);
          return true;
        }
      }
      while (this.mForbidSwitch);
      this.mUpEventMoveEventInterval = (paramMotionEvent.getEventTime() - this.mLastMovementTime);
      getDirection();
      startHandler(false);
      this.xoffset = (paramMotionEvent.getX() - this.xStartPosition);
      startAnimation(this.hasMoved);
      return true;
    }
    while (this.mForbidSwitch);
    startHandler(false);
    this.xoffset = (paramMotionEvent.getX() - this.xStartPosition);
    this.direction = 0;
    startAnimation(this.hasMoved);
    return false;
  }

  public void setCurrentItem(int paramInt)
  {
    this.currentItem = paramInt;
  }

  public void switchToNext()
  {
    this.indexChanged = true;
    this.direction = 1;
    selfMotion(true);
  }

  public void switchToPre()
  {
    this.indexChanged = true;
    this.direction = 2;
    selfMotion(false);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mList = ((List)paramObject);
      if ((this.mList != null) && (this.mList.size() > 1))
      {
        this.mForbidSwitch = false;
        requestLayout();
        preLoad();
        if (!this.mForbidSwitch)
          break label85;
        if (this.switchHandler != null)
          this.switchHandler.removeCallbacks(this.run);
      }
    }
    label85: label120: 
    do
    {
      do
      {
        return;
        this.mForbidSwitch = true;
        break;
        startHandler(true);
        return;
        if (!paramString.equalsIgnoreCase("stop"))
          break label120;
      }
      while (this.switchHandler == null);
      this.switchHandler.removeCallbacks(this.run);
      return;
    }
    while (!paramString.equalsIgnoreCase("start"));
    startHandler(false);
  }

  public void updateImageViewFinish(boolean paramBoolean, ImageView paramImageView, String paramString, Bitmap paramBitmap)
  {
  }

  public static abstract interface IPageChangedListener
  {
    public abstract void onPageIndexChanged(int paramInt1, int paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.switchview.SwitchView
 * JD-Core Version:    0.6.2
 */