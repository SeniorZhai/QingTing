package fm.qingting.qtradio.view.chatroom.expression;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.R.drawable;
import fm.qingting.qtradio.manager.SkinManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExpressionGridView extends QtListItemView
{
  private final int HORIZON_ITEM_COUNT = 6;
  private final int VERTICAL_ITEM_COUNT = 3;
  private final ViewLayout cellLayout = this.itemLayout.createChildLT(59, 59, 23, 20, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final DrawFilter filter = SkinManager.getInstance().getDrawFilter();
  private List<ExpressionItem> functions = new ArrayList();
  private final Paint iconPaint = new Paint();
  private final ViewLayout itemLayout = this.standardLayout.createChildLT(105, 100, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private boolean mInTouchMode = false;
  private float mLastMotionX;
  private float mLastMotionY;
  private int mSelectIndex = -1;
  private float scrollPosition = 0.0F;
  private int selectedIndex = -1;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 400, 720, 400, 0, 0, ViewLayout.FILL);

  public ExpressionGridView(Context paramContext)
  {
    super(paramContext);
    setItemSelectedEnable();
  }

  private void drawAvatar(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3)
  {
    Bitmap localBitmap = BitmapResourceCache.getInstance().getResourceCache(getResources(), this, paramInt3);
    if (localBitmap != null)
      paramCanvas.drawBitmap(localBitmap, null, new Rect(this.cellLayout.leftMargin + paramInt1, this.cellLayout.topMargin + paramInt2, this.cellLayout.leftMargin + paramInt1 + this.cellLayout.width, this.cellLayout.topMargin + paramInt2 + this.cellLayout.height), this.iconPaint);
  }

  private void drawDelete(Canvas paramCanvas)
  {
    int i = (this.standardLayout.width - this.itemLayout.width * 6) / 2 + this.itemLayout.width * 5 + this.cellLayout.leftMargin;
    int j = (this.standardLayout.height - this.itemLayout.height * 3) / 2 + this.itemLayout.height * 2 + this.cellLayout.topMargin;
    paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130837686), null, new Rect(i, j, this.cellLayout.width + i, this.cellLayout.height + j), this.iconPaint);
  }

  private void drawFunctionTitle(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = (this.standardLayout.width - this.itemLayout.width * 6) / 2;
    paramInt3 = 0;
    while (true)
      if ((paramInt1 * 6 + paramInt3 < this.functions.size()) && (paramInt3 < 6))
      {
        ExpressionItem localExpressionItem = (ExpressionItem)this.functions.get(paramInt1 * 6 + paramInt3);
        try
        {
          int j = Integer.parseInt(R.drawable.class.getDeclaredField(localExpressionItem.getExpressionIcon()).get(null).toString());
          if (j != 0)
            drawAvatar(paramCanvas, this.itemLayout.width * paramInt3 + i, paramInt2, j);
          paramInt3 += 1;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          while (true)
            localNumberFormatException.printStackTrace();
        }
        catch (SecurityException localSecurityException)
        {
          while (true)
            localSecurityException.printStackTrace();
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          while (true)
            localIllegalArgumentException.printStackTrace();
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          while (true)
            localNoSuchFieldException.printStackTrace();
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          while (true)
            localIllegalAccessException.printStackTrace();
        }
      }
  }

  private int getSelectIndex()
  {
    int j = (this.standardLayout.width - this.itemLayout.width * 6) / 2;
    int i = (this.standardLayout.height - this.itemLayout.height * 3) / 2;
    if ((this.mLastMotionX < j) || (this.mLastMotionY < i) || (this.mLastMotionX > this.standardLayout.width - j) || (this.mLastMotionY > this.standardLayout.height - i))
      i = -1;
    do
    {
      do
      {
        return i;
        j = (int)((this.mLastMotionX - j) / this.itemLayout.width);
        i = (int)((this.mLastMotionY - i) / this.itemLayout.height);
        if ((j >= 6) || (i >= 3))
          return -1;
        j += i * 6;
        i = j;
      }
      while (j < this.functions.size());
      i = j;
    }
    while (j == 17);
    return -1;
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("scrollPosition"))
      return Float.valueOf(this.scrollPosition);
    if (paramString.equalsIgnoreCase("selectedIndex"))
      return Integer.valueOf(this.selectedIndex);
    if (paramString.equalsIgnoreCase("downloadlist"));
    return null;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.functions == null)
      return;
    paramCanvas.save();
    paramCanvas.setDrawFilter(this.filter);
    int j = (this.standardLayout.height - this.itemLayout.height * 3) / 2;
    int i = 0;
    while (i < 3)
    {
      drawFunctionTitle(paramCanvas, i, this.itemLayout.height * i + j, (i + 1) * this.itemLayout.height + j);
      i += 1;
    }
    drawDelete(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.itemLayout.scaleToBounds(this.standardLayout);
    this.cellLayout.scaleToBounds(this.itemLayout);
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((!this.mInTouchMode) && (paramMotionEvent.getAction() != 0));
    do
    {
      do
      {
        return true;
        switch (paramMotionEvent.getAction())
        {
        default:
          return true;
        case 0:
          this.mInTouchMode = true;
          this.mLastMotionX = paramMotionEvent.getX();
          this.mLastMotionY = paramMotionEvent.getY();
          this.mSelectIndex = getSelectIndex();
          if (this.mSelectIndex > -1)
          {
            this.mInTouchMode = true;
            return true;
          }
          this.mInTouchMode = false;
          return true;
        case 2:
        case 3:
        case 1:
        }
      }
      while (getSelectIndex() == this.mSelectIndex);
      this.mSelectIndex = -1;
      this.mInTouchMode = false;
      return true;
      this.mSelectIndex = -1;
      this.mInTouchMode = false;
      invalidate();
      return true;
      if ((this.mSelectIndex < this.functions.size()) && (this.mSelectIndex > -1))
      {
        dispatchActionEvent("selectExpression", this.functions.get(this.mSelectIndex));
        return true;
      }
    }
    while (this.mSelectIndex != 17);
    dispatchActionEvent("deleteExpression", null);
    return true;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.functions = ((List)paramObject);
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.expression.ExpressionGridView
 * JD-Core Version:    0.6.2
 */