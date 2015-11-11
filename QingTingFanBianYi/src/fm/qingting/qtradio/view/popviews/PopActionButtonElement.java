package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.Layout.Alignment;
import android.view.MotionEvent;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class PopActionButtonElement extends ViewElement
{
  public static final int STYLE_ALPHA = 0;
  public static final int STYLE_MASK = 1;
  private final ViewLayout iconLayout = this.itemLayout.createChildLT(136, 136, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(136, 220, 136, 220, 0, 0, ViewLayout.FILL);
  private ImageViewElement mIcon;
  private final Paint mMaskPaint = new Paint();
  private TextViewElement mNameElement;
  private int mStyle = 0;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(136, 50, 0, 140, ViewLayout.SCALE_FLAG_SLTCW);

  public PopActionButtonElement(Context paramContext)
  {
    super(paramContext);
    this.mIcon = new ImageViewElement(paramContext);
    this.mIcon.setBelonging(this);
    this.mIcon.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
      }
    });
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mNameElement.setColor(SkinManager.getNewPopTextColor());
    this.mNameElement.setBelonging(this);
    this.mMaskPaint.setStyle(Paint.Style.FILL);
    this.mMaskPaint.setColor(SkinManager.getItemHighlightMaskColor());
  }

  private void drawMask(Canvas paramCanvas)
  {
    float f = this.mIcon.getWidth() / 2.0F;
    paramCanvas.drawCircle((getLeftMargin() + getRightMargin()) / 2.0F, (this.mIcon.getTopMargin() + this.mIcon.getBottomMargin()) / 2.0F, f, this.mMaskPaint);
  }

  public boolean handleTouchEvent(MotionEvent paramMotionEvent)
  {
    this.mIcon.handleTouchEvent(paramMotionEvent);
    return super.handleTouchEvent(paramMotionEvent);
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    boolean bool = isHighlighted();
    if (bool)
    {
      if (this.mStyle == 0)
        this.mIcon.setAlpha(144);
      this.mNameElement.setAlpha(144);
    }
    while (true)
    {
      int i = getLeftMargin();
      int j = getTopMargin();
      this.mIcon.setTranslationX(i);
      this.mIcon.setTranslationY(j);
      this.mNameElement.setTranslationX(i);
      this.mNameElement.setTranslationY(j);
      this.mIcon.draw(paramCanvas);
      this.mNameElement.draw(paramCanvas);
      if ((bool) && (this.mStyle == 1))
        drawMask(paramCanvas);
      return;
      if (this.mStyle == 0)
        this.mIcon.setAlpha(255);
      this.mNameElement.setAlpha(255);
    }
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.itemLayout.scaleToBounds(paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.iconLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.mIcon.measure(this.iconLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mNameElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
  }

  void setAction(String paramString, int paramInt)
  {
    this.mIcon.setImageRes(paramInt);
    this.mNameElement.setText(paramString);
  }

  void setStyle(int paramInt)
  {
    this.mStyle = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.PopActionButtonElement
 * JD-Core Version:    0.6.2
 */