package fm.qingting.qtradio.view.chatroom.chatlist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.NinePatchDrawable;
import android.text.DynamicLayout;
import android.text.Layout.Alignment;
import android.text.SpannableString;
import android.text.TextPaint;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.ExpressionUtil;
import fm.qingting.utils.ScreenConfiguration;

public class BubbleLeftViewElement extends ViewElement
{
  private static final String REGEX = "\\[[^\\]]+\\]";
  private final int BASE_BOTTOM = 20;
  private final int BASE_LEFT = 50;
  private final int BASE_RIGHT = 20;
  private final int BASE_TOP = 20;
  private final float BASE_WIDTH = 720.0F;
  private int mBubbleResource = 2130837569;
  private DynamicLayout mLayout;
  private int mPaddingBottom;
  private int mPaddingLeft;
  private int mPaddingRight;
  private int mPaddingTop;
  private SpannableString mText;
  private TextPaint mTextPaint = new TextPaint();

  public BubbleLeftViewElement(Context paramContext)
  {
    super(paramContext);
    this.mMeasureSpec = 0;
    this.mTextPaint.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mTextPaint.setColor(SkinManager.getTextColorNormal());
    recalculatePadding();
  }

  private void drawBubble(Canvas paramCanvas)
  {
    try
    {
      NinePatchDrawable localNinePatchDrawable = (NinePatchDrawable)getContext().getResources().getDrawable(this.mBubbleResource);
      int i = getTextWidth();
      localNinePatchDrawable.setBounds(getLeftMargin(), getTopMargin(), i + (getLeftMargin() + this.mPaddingLeft) + this.mPaddingRight, getTopMargin() + getHeight());
      localNinePatchDrawable.draw(paramCanvas);
      return;
    }
    catch (OutOfMemoryError paramCanvas)
    {
    }
  }

  private void drawText(Canvas paramCanvas)
  {
    if (this.mLayout == null)
      return;
    int i = paramCanvas.save();
    paramCanvas.translate(getLeftMargin() + this.mPaddingLeft, getTopMargin() + this.mPaddingTop);
    this.mLayout.draw(paramCanvas);
    paramCanvas.restoreToCount(i);
  }

  private int getTextWidth()
  {
    if (this.mLayout == null)
      return 0;
    if (this.mLayout.getLineCount() == 1)
      return (int)this.mLayout.getLineRight(0);
    return this.mLayout.getWidth();
  }

  private void recalculatePadding()
  {
    float f = ScreenConfiguration.getWidth() / 720.0F;
    this.mPaddingLeft = ((int)(50.0F * f));
    this.mPaddingRight = ((int)(20.0F * f));
    this.mPaddingTop = ((int)(20.0F * f));
    this.mPaddingBottom = ((int)(f * 20.0F));
  }

  public int getHeight()
  {
    if (this.mLayout == null)
      return getBottomMargin() - getTopMargin();
    return this.mLayout.getHeight() + this.mPaddingBottom + this.mPaddingTop;
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    paramCanvas.save();
    drawBubble(paramCanvas);
    drawText(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.mText == null)
      return;
    this.mLayout = new DynamicLayout(this.mText, this.mTextPaint, paramInt3 - paramInt1 - this.mPaddingLeft - this.mPaddingRight, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.5F, false);
  }

  public void setBubbleResource(int paramInt)
  {
    this.mBubbleResource = paramInt;
  }

  public void setText(String paramString)
  {
    if (paramString == null)
      this.mText = null;
    int i = (int)SkinManager.getInstance().getNormalTextSize();
    this.mText = ExpressionUtil.getInstance().getExpressionString(getContext(), paramString, "\\[[^\\]]+\\]", i, i);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.chatlist.BubbleLeftViewElement
 * JD-Core Version:    0.6.2
 */