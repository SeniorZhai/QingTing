package fm.qingting.qtradio.view.floaticon;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import java.lang.reflect.Field;

public class FloatWindowSmallView extends View
{
  private final int TOUCHSLOP = 20;
  private final DrawFilter filter = new PaintFlagsDrawFilter(0, 67);
  private final ViewLayout floatLayout = ViewLayout.createViewLayoutWithBoundsLT(70, 70, 70, 70, 0, 0, ViewLayout.FILL);
  private boolean mIsBeingDragged = false;
  private final Paint mPaint = new Paint();
  private WindowManager.LayoutParams mParams;
  private final Rect mRect = new Rect();
  private boolean mRight = true;
  private int statusBarHeight;
  private final ViewLayout v2Layout = ViewLayout.createViewLayoutWithBoundsLT(128, 130, 128, 130, 0, 0, ViewLayout.FILL);
  private WindowManager windowManager;
  private float xDownInScreen;
  private float xInScreen;
  private float xInView;
  private float yDownInScreen;
  private float yInScreen;
  private float yInView;

  public FloatWindowSmallView(Context paramContext)
  {
    super(paramContext);
    this.windowManager = ((WindowManager)paramContext.getSystemService("window"));
  }

  private void dockToSide()
  {
    int j = this.windowManager.getDefaultDisplay().getWidth();
    int i;
    if (this.xInScreen > j / 2)
    {
      WindowManager.LayoutParams localLayoutParams = this.mParams;
      if (FloatViewManager.INSTANCE.isV2())
      {
        i = this.v2Layout.width;
        localLayoutParams.x = (j - i);
      }
    }
    for (this.mRight = true; ; this.mRight = false)
    {
      invalidate();
      this.mParams.y = ((int)(this.yInScreen - this.yInView));
      this.windowManager.updateViewLayout(this, this.mParams);
      return;
      i = this.floatLayout.width;
      break;
      this.mParams.x = 0;
    }
  }

  private void drawIcon(Canvas paramCanvas)
  {
    if (FloatViewManager.INSTANCE.isV2())
    {
      localObject = BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130837738);
      this.mRect.set(0, 0, this.v2Layout.width, this.v2Layout.height);
      if (!this.mIsBeingDragged)
      {
        if (!this.mRight)
          break label99;
        this.mRect.offset((int)(this.v2Layout.width * FloatViewManager.INSTANCE.getOffset()), 0);
      }
      while (true)
      {
        paramCanvas.drawBitmap((Bitmap)localObject, null, this.mRect, this.mPaint);
        return;
        label99: this.mRect.offset((int)(-this.v2Layout.width * FloatViewManager.INSTANCE.getOffset()), 0);
      }
    }
    if (this.mIsBeingDragged)
    {
      localObject = BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130837737);
      this.mRect.set(this.floatLayout.leftMargin, 0, this.floatLayout.getRight(), this.floatLayout.height);
      paramCanvas.drawBitmap((Bitmap)localObject, null, this.mRect, this.mPaint);
      return;
    }
    Object localObject = BitmapResourceCache.getInstance();
    Resources localResources = getResources();
    if (this.mRight);
    for (int i = 2130837732; ; i = 2130837731)
    {
      localObject = ((BitmapResourceCache)localObject).getResourceCache(localResources, this, i);
      this.mRect.set(0, 0, this.floatLayout.width, this.floatLayout.height);
      paramCanvas.drawBitmap((Bitmap)localObject, null, this.mRect, this.mPaint);
      return;
    }
  }

  private int getStatusBarHeight()
  {
    if (this.statusBarHeight == 0);
    try
    {
      Class localClass = Class.forName("com.android.internal.R$dimen");
      Object localObject = localClass.newInstance();
      int i = ((Integer)localClass.getField("status_bar_height").get(localObject)).intValue();
      this.statusBarHeight = getResources().getDimensionPixelSize(i);
      return this.statusBarHeight;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private void openBigWindow()
  {
    QTLogger localQTLogger = QTLogger.getInstance();
    if (FloatViewManager.INSTANCE.isV2());
    for (String str = "2"; ; str = "1")
    {
      str = localQTLogger.buildCommonLog(str, null, null);
      LogModule.getInstance().send("FloatClick", str);
      FloatViewManager.INSTANCE.createBigWindow(getContext());
      FloatViewManager.INSTANCE.removeSmallWindow(getContext());
      return;
    }
  }

  private void updateViewPosition()
  {
    this.mParams.x = ((int)(this.xInScreen - this.xInView));
    this.mParams.y = ((int)(this.yInScreen - this.yInView));
    this.windowManager.updateViewLayout(this, this.mParams);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(this.filter);
    paramCanvas.save();
    drawIcon(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.floatLayout.scaleToBounds(paramInt1, paramInt2);
    this.v2Layout.scaleToBounds(paramInt1, paramInt2);
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
      do
      {
        return true;
        this.mIsBeingDragged = false;
        this.xInView = paramMotionEvent.getX();
        this.yInView = paramMotionEvent.getY();
        this.xDownInScreen = paramMotionEvent.getRawX();
        this.yDownInScreen = (paramMotionEvent.getRawY() - getStatusBarHeight());
        this.xInScreen = paramMotionEvent.getRawX();
        this.yInScreen = (paramMotionEvent.getRawY() - getStatusBarHeight());
        return true;
        this.xInScreen = paramMotionEvent.getRawX();
        this.yInScreen = (paramMotionEvent.getRawY() - getStatusBarHeight());
        if (this.mIsBeingDragged)
        {
          updateViewPosition();
          return true;
        }
      }
      while ((Math.abs(this.xInScreen - this.xDownInScreen) <= 20.0F) && (Math.abs(this.yInScreen - this.yDownInScreen) <= 20.0F));
      this.mIsBeingDragged = true;
      invalidate();
      updateViewPosition();
      return true;
    case 1:
    }
    if (!this.mIsBeingDragged)
    {
      openBigWindow();
      return true;
    }
    this.mIsBeingDragged = false;
    dockToSide();
    return true;
  }

  public void setParams(WindowManager.LayoutParams paramLayoutParams)
  {
    this.mParams = paramLayoutParams;
    if (this.mParams.x == 0)
    {
      this.mRight = false;
      return;
    }
    this.mRight = true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.floaticon.FloatWindowSmallView
 * JD-Core Version:    0.6.2
 */