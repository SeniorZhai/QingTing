package fm.qingting.framework.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Layout.Alignment;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;

public class QtButton extends ViewImpl
{
  private final DrawFilter filter = new PaintFlagsDrawFilter(0, 67);
  private Layout.Alignment mAlignment = Layout.Alignment.ALIGN_CENTER;
  private TextUtils.TruncateAt mEllipsizeWhere = TextUtils.TruncateAt.END;
  private int mLeft = 0;
  private TextPaint mPaint = new TextPaint();
  private int mRight = 0;
  private String mText;
  protected ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);

  public QtButton(Context paramContext)
  {
    super(paramContext);
  }

  private void drawTitle(Canvas paramCanvas)
  {
    if ((this.mText == null) || (this.mText.equalsIgnoreCase("")))
      return;
    String str = TextUtils.ellipsize(this.mText, this.mPaint, this.standardLayout.width - this.mLeft - this.mRight, this.mEllipsizeWhere).toString();
    Rect localRect = new Rect();
    this.mPaint.getTextBounds(str, 0, str.length(), localRect);
    float f2 = (this.standardLayout.height - localRect.bottom - localRect.top) / 2.0F;
    float f1 = 0.0F;
    switch ($SWITCH_TABLE$android$text$Layout$Alignment()[this.mAlignment.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      paramCanvas.drawText(str, f1, f2, this.mPaint);
      return;
      f1 = (this.standardLayout.width + this.mLeft - this.mRight - localRect.width()) / 2.0F;
      continue;
      f1 = this.mLeft;
      continue;
      f1 = this.mRight - localRect.width();
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(this.filter);
    paramCanvas.save();
    drawTitle(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void setAlignment(Layout.Alignment paramAlignment)
  {
    this.mAlignment = paramAlignment;
  }

  public void setEllipsizeWhere(TextUtils.TruncateAt paramTruncateAt)
  {
    this.mEllipsizeWhere = paramTruncateAt;
  }

  public void setHorizonOffset(int paramInt1, int paramInt2)
  {
    this.mLeft = paramInt1;
    this.mRight = paramInt2;
  }

  public void setQtBackground(int paramInt)
  {
    setBackgroundResource(paramInt);
  }

  public void setQtBackground(int paramInt1, int paramInt2)
  {
    StateListDrawable localStateListDrawable = new StateListDrawable();
    try
    {
      Drawable localDrawable1 = getResources().getDrawable(paramInt2);
      Drawable localDrawable2 = getResources().getDrawable(paramInt1);
      localStateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, localDrawable1);
      localStateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, localDrawable1);
      localStateListDrawable.addState(View.ENABLED_STATE_SET, localDrawable2);
      localStateListDrawable.addState(View.FOCUSED_STATE_SET, localDrawable1);
      localStateListDrawable.addState(View.EMPTY_STATE_SET, localDrawable2);
      localStateListDrawable.addState(View.SELECTED_STATE_SET, localDrawable1);
      label82: setBackgroundDrawable(localStateListDrawable);
      return;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      while (true)
        localNotFoundException.printStackTrace();
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      break label82;
    }
  }

  public void setText(String paramString)
  {
    this.mText = paramString;
    invalidate();
  }

  public void setTextColor(int paramInt)
  {
    this.mPaint.setColor(paramInt);
  }

  public void setTextSize(float paramFloat)
  {
    this.mPaint.setTextSize(paramFloat);
    invalidate();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.QtButton
 * JD-Core Version:    0.6.2
 */