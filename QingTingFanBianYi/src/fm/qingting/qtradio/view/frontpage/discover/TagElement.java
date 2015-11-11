package fm.qingting.qtradio.view.frontpage.discover;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import fm.qingting.framework.view.ViewElement;

class TagElement extends ViewElement
{
  private final Paint mGradientPaint = new Paint();
  private String mName;
  private int mOffset = 0;
  private final Rect mTextBound = new Rect();
  private final TextPaint mTextPaint = new TextPaint();

  public TagElement(Context paramContext)
  {
    super(paramContext);
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    paramCanvas.drawRect(getLeftMargin(), getTopMargin(), getRightMargin(), getBottomMargin(), this.mGradientPaint);
    if ((this.mName == null) || (this.mName.length() == 0))
      return;
    String str = TextUtils.ellipsize(this.mName, this.mTextPaint, getWidth() - this.mOffset, TextUtils.TruncateAt.END).toString();
    this.mTextPaint.getTextBounds(str, 0, str.length(), this.mTextBound);
    paramCanvas.drawText(str, getLeftMargin() + this.mOffset, getTopMargin() + (getHeight() - this.mTextBound.top - this.mTextBound.bottom) / 2 + getHeight() / 18, this.mTextPaint);
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.mGradientPaint.getShader() == null)
    {
      LinearGradient localLinearGradient = new LinearGradient(getLeftMargin(), getTopMargin(), getLeftMargin(), getBottomMargin(), 0, -872415232, Shader.TileMode.CLAMP);
      this.mGradientPaint.setShader(localLinearGradient);
    }
  }

  void setText(String paramString)
  {
    this.mName = paramString;
  }

  void setTextColor(int paramInt)
  {
    this.mTextPaint.setColor(paramInt);
  }

  void setTextOffset(int paramInt)
  {
    this.mOffset = paramInt;
  }

  void setTextSize(float paramFloat)
  {
    this.mTextPaint.setTextSize(paramFloat);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.TagElement
 * JD-Core Version:    0.6.2
 */