package fm.qingting.qtradio.wo;

import android.content.Context;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

public class WoAutoResizeTextView extends TextView
{
  public static final float MIN_TEXT_SIZE = 8.0F;
  private static final String mEllipsis = "...";
  private boolean mAddEllipsis = true;
  private float mMaxTextSize = 0.0F;
  private float mMinTextSize = 8.0F;
  private boolean mNeedsResize = false;
  private float mSpacingAdd = 0.0F;
  private float mSpacingMult = 1.0F;
  private OnTextResizeListener mTextResizeListener;
  private float mTextSize = getTextSize();

  public WoAutoResizeTextView(Context paramContext)
  {
    this(paramContext, null);
  }

  public WoAutoResizeTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public WoAutoResizeTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private int getTextHeight(CharSequence paramCharSequence, TextPaint paramTextPaint, int paramInt, float paramFloat)
  {
    paramTextPaint = new TextPaint(paramTextPaint);
    paramTextPaint.setTextSize(paramFloat);
    return new StaticLayout(paramCharSequence, paramTextPaint, paramInt, Layout.Alignment.ALIGN_NORMAL, this.mSpacingMult, this.mSpacingAdd, true).getHeight();
  }

  public boolean getAddEllipsis()
  {
    return this.mAddEllipsis;
  }

  public float getMaxTextSize()
  {
    return this.mMaxTextSize;
  }

  public float getMinTextSize()
  {
    return this.mMinTextSize;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramBoolean) || (this.mNeedsResize))
      resizeText(paramInt3 - paramInt1 - getCompoundPaddingLeft() - getCompoundPaddingRight(), paramInt4 - paramInt2 - getCompoundPaddingBottom() - getCompoundPaddingTop());
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt1 != paramInt3) || (paramInt2 != paramInt4))
      this.mNeedsResize = true;
  }

  protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mNeedsResize = true;
    resetTextSize();
  }

  public void resetTextSize()
  {
    if (this.mTextSize > 0.0F)
    {
      super.setTextSize(0, this.mTextSize);
      this.mMaxTextSize = this.mTextSize;
    }
  }

  public void resizeText()
  {
    int i = getHeight();
    int j = getPaddingBottom();
    int k = getPaddingTop();
    resizeText(getWidth() - getPaddingLeft() - getPaddingRight(), i - j - k);
  }

  public void resizeText(int paramInt1, int paramInt2)
  {
    CharSequence localCharSequence = getText();
    if ((localCharSequence == null) || (localCharSequence.length() == 0) || (paramInt2 <= 0) || (paramInt1 <= 0) || (this.mTextSize == 0.0F))
      return;
    TextPaint localTextPaint = getPaint();
    float f3 = localTextPaint.getTextSize();
    if (this.mMaxTextSize > 0.0F);
    int i;
    for (float f1 = Math.min(this.mTextSize, this.mMaxTextSize); ; f1 = this.mTextSize)
      for (i = getTextHeight(localCharSequence, localTextPaint, paramInt1, f1); (i > paramInt2) && (f1 > this.mMinTextSize); i = getTextHeight(localCharSequence, localTextPaint, paramInt1, f1))
        f1 = Math.max(f1 - 1.0F, this.mMinTextSize);
    StaticLayout localStaticLayout;
    int j;
    if ((this.mAddEllipsis) && (f1 == this.mMinTextSize) && (i > paramInt2))
    {
      localStaticLayout = new StaticLayout(localCharSequence, new TextPaint(localTextPaint), paramInt1, Layout.Alignment.ALIGN_NORMAL, this.mSpacingMult, this.mSpacingAdd, false);
      if (localStaticLayout.getLineCount() > 0)
      {
        j = localStaticLayout.getLineForVertical(paramInt2) - 1;
        if (j >= 0)
          break label262;
        setText("");
      }
    }
    while (true)
    {
      setTextSize(0, f1);
      setLineSpacing(this.mSpacingAdd, this.mSpacingMult);
      if (this.mTextResizeListener != null)
        this.mTextResizeListener.onTextResize(this, f3, f1);
      this.mNeedsResize = false;
      return;
      label262: i = localStaticLayout.getLineStart(j);
      paramInt2 = localStaticLayout.getLineEnd(j);
      float f2 = localStaticLayout.getLineWidth(j);
      float f4 = localTextPaint.measureText("...");
      while (paramInt1 < f2 + f4)
      {
        paramInt2 -= 1;
        f2 = localTextPaint.measureText(localCharSequence.subSequence(i, paramInt2 + 1).toString());
      }
      setText(localCharSequence.subSequence(0, paramInt2) + "...");
    }
  }

  public void setAddEllipsis(boolean paramBoolean)
  {
    this.mAddEllipsis = paramBoolean;
  }

  public void setLineSpacing(float paramFloat1, float paramFloat2)
  {
    super.setLineSpacing(paramFloat1, paramFloat2);
    this.mSpacingMult = paramFloat2;
    this.mSpacingAdd = paramFloat1;
  }

  public void setMaxTextSize(float paramFloat)
  {
    this.mMaxTextSize = paramFloat;
    requestLayout();
    invalidate();
  }

  public void setMinTextSize(float paramFloat)
  {
    this.mMinTextSize = paramFloat;
    requestLayout();
    invalidate();
  }

  public void setOnResizeListener(OnTextResizeListener paramOnTextResizeListener)
  {
    this.mTextResizeListener = paramOnTextResizeListener;
  }

  public void setTextSize(float paramFloat)
  {
    super.setTextSize(paramFloat);
    this.mTextSize = getTextSize();
  }

  public void setTextSize(int paramInt, float paramFloat)
  {
    super.setTextSize(paramInt, paramFloat);
    this.mTextSize = getTextSize();
  }

  public static abstract interface OnTextResizeListener
  {
    public abstract void onTextResize(TextView paramTextView, float paramFloat1, float paramFloat2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.wo.WoAutoResizeTextView
 * JD-Core Version:    0.6.2
 */