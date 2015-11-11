package fm.qingting.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import fm.qingting.framework.event.IEventHandler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class FrameLayoutViewImpl extends FrameLayout
  implements IView
{
  protected boolean activate = false;
  private int alpha = 255;
  protected Animation.AnimationListener animationListener = new Animation.AnimationListener()
  {
    public void onAnimationEnd(Animation paramAnonymousAnimation)
    {
      if (paramAnonymousAnimation == FrameLayoutViewImpl.this.openAnimation)
      {
        FrameLayoutViewImpl.this.isOpened = true;
        FrameLayoutViewImpl.this.onViewDidOpen();
      }
      while (paramAnonymousAnimation != FrameLayoutViewImpl.this.closeAnimation)
        return;
      FrameLayoutViewImpl.this.isOpened = false;
      FrameLayoutViewImpl.this.onViewDidClose();
    }

    public void onAnimationRepeat(Animation paramAnonymousAnimation)
    {
    }

    public void onAnimationStart(Animation paramAnonymousAnimation)
    {
      if (paramAnonymousAnimation == FrameLayoutViewImpl.this.openAnimation)
      {
        FrameLayoutViewImpl.this.onViewWillOpen();
        FrameLayoutViewImpl.this.isOpened = false;
      }
      while (paramAnonymousAnimation != FrameLayoutViewImpl.this.closeAnimation)
        return;
      FrameLayoutViewImpl.this.onViewWillClose();
      FrameLayoutViewImpl.this.isOpened = false;
    }
  };
  protected int animationRetain = 0;
  protected Point centerPoint = new Point(0, 0);
  protected Animation closeAnimation;
  protected IEventHandler eventHandler;
  private boolean hasAlpha = false;
  private boolean hasScaled = false;
  protected boolean isOpened = true;
  protected Animation openAnimation;
  private float scale = 1.0F;
  private Set<WeakReference<IViewEventListener>> viewEventListeners = new HashSet();

  public FrameLayoutViewImpl(Context paramContext)
  {
    super(paramContext);
  }

  public FrameLayoutViewImpl(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public FrameLayoutViewImpl(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public void addViewEventListener(IViewEventListener paramIViewEventListener)
  {
    removeViewEventListener(paramIViewEventListener);
    this.viewEventListeners.add(new WeakReference(paramIViewEventListener));
  }

  public void close(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.closeAnimation != null))
    {
      this.closeAnimation.setAnimationListener(this.animationListener);
      startAnimation(this.closeAnimation);
      return;
    }
    if (this.closeAnimation != null)
      this.closeAnimation.setAnimationListener(null);
    clearAnimation();
    onViewWillClose();
    this.isOpened = false;
    onViewDidClose();
  }

  protected void dispatchActionEvent(String paramString, Object paramObject)
  {
    if (this.eventHandler != null)
      this.eventHandler.onEvent(this, paramString, paramObject);
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    if ((!this.hasAlpha) && (this.alpha != 255))
      paramCanvas.saveLayerAlpha(new RectF(paramCanvas.getClipBounds()), this.alpha, 31);
    while (true)
    {
      if ((!this.hasScaled) && (this.scale != 1.0F))
        paramCanvas.scale(this.scale, this.scale, this.centerPoint.x, this.centerPoint.y);
      super.dispatchDraw(paramCanvas);
      paramCanvas.restore();
      return;
      paramCanvas.save();
    }
  }

  protected void dispatchViewEvent(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = new ArrayList();
    ((ArrayList)localObject).addAll(this.viewEventListeners);
    localObject = ((ArrayList)localObject).iterator();
    while (true)
    {
      if (!((Iterator)localObject).hasNext())
      {
        this.viewEventListeners.removeAll(localArrayList);
        return;
      }
      WeakReference localWeakReference = (WeakReference)((Iterator)localObject).next();
      IViewEventListener localIViewEventListener = (IViewEventListener)localWeakReference.get();
      if (localIViewEventListener == null)
        localArrayList.add(localWeakReference);
      else
        switch (paramInt)
        {
        default:
          break;
        case 0:
          localIViewEventListener.viewWillOpen(this);
          break;
        case 1:
          localIViewEventListener.viewDidOpened(this);
          break;
        case 2:
          localIViewEventListener.viewWillClose(this);
          break;
        case 3:
          localIViewEventListener.viewDidClosed(this);
        }
    }
  }

  public void draw(Canvas paramCanvas)
  {
    this.hasScaled = false;
    this.hasAlpha = false;
    if (this.alpha != 255)
    {
      this.hasAlpha = true;
      paramCanvas.saveLayerAlpha(new RectF(paramCanvas.getClipBounds()), this.alpha, 31);
    }
    while (true)
    {
      if (this.scale != 1.0F)
      {
        this.hasScaled = true;
        paramCanvas.scale(this.scale, this.scale, this.centerPoint.x, this.centerPoint.y);
      }
      super.draw(paramCanvas);
      paramCanvas.restore();
      this.hasScaled = false;
      return;
      paramCanvas.save();
    }
  }

  public boolean getActivate()
  {
    return this.activate;
  }

  public Point getCenterPoint()
  {
    return new Point(this.centerPoint.x, this.centerPoint.y);
  }

  public int getQtAlpha()
  {
    return this.alpha;
  }

  public float getScale()
  {
    return this.scale;
  }

  public Object getValue(String paramString, Object paramObject)
  {
    return null;
  }

  public View getView()
  {
    return this;
  }

  public boolean isAnimating()
  {
    return (this.animationRetain > 0) || ((getAnimation() != null) && (!getAnimation().hasEnded()));
  }

  public boolean isOpened()
  {
    return this.isOpened;
  }

  protected void onViewDidClose()
  {
    dispatchViewEvent(3);
  }

  protected void onViewDidOpen()
  {
    dispatchViewEvent(1);
  }

  protected void onViewWillClose()
  {
    dispatchViewEvent(2);
  }

  protected void onViewWillOpen()
  {
    dispatchViewEvent(0);
  }

  public void open(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.openAnimation != null))
    {
      this.openAnimation.setAnimationListener(this.animationListener);
      startAnimation(this.openAnimation);
      return;
    }
    if (this.openAnimation != null)
      this.openAnimation.setAnimationListener(null);
    clearAnimation();
    onViewWillOpen();
    this.isOpened = true;
    onViewDidOpen();
  }

  public void removeViewEventListener(IViewEventListener paramIViewEventListener)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.viewEventListeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        this.viewEventListeners.removeAll(localArrayList);
        return;
      }
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      IViewEventListener localIViewEventListener = (IViewEventListener)localWeakReference.get();
      if ((localIViewEventListener == null) || (localIViewEventListener == paramIViewEventListener))
        localArrayList.add(localWeakReference);
    }
  }

  public void setActivate(boolean paramBoolean)
  {
    if (this.activate == paramBoolean)
      return;
    this.activate = paramBoolean;
    setFocusable(paramBoolean);
    setFocusableInTouchMode(paramBoolean);
    setEnabled(paramBoolean);
    if (!paramBoolean)
    {
      clearFocus();
      return;
    }
    requestFocus();
  }

  public void setCloseAnimation(Animation paramAnimation)
  {
    this.closeAnimation = paramAnimation;
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.eventHandler = paramIEventHandler;
  }

  public void setOpenAnimation(Animation paramAnimation)
  {
    this.openAnimation = paramAnimation;
  }

  public void setQtAlpha(int paramInt)
  {
    if (this.alpha != paramInt)
    {
      this.alpha = paramInt;
      invalidate();
    }
  }

  public void setScale(float paramFloat)
  {
    if (this.scale != paramFloat)
    {
      this.scale = paramFloat;
      invalidate();
    }
  }

  public void update(String paramString, Object paramObject)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.FrameLayoutViewImpl
 * JD-Core Version:    0.6.2
 */