package fm.qingting.qtradio.view.personalcenter.aboutQt;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;

public class AboutQtView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final long Interval = 1000L;
  private final ViewLayout buttonLayout = this.standardLayout.createChildLT(400, 90, 160, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout copyrightLayout = this.standardLayout.createChildLT(720, 40, 0, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout iconLayout = this.buttonLayout.createChildLT(32, 32, 10, 29, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(400, 1, 160, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout logoLayout = this.standardLayout.createChildLT(294, 294, 213, 105, ViewLayout.SCALE_FLAG_SLTCW);
  private int mClickCnt = 0;
  private TextViewElement mCopyright;
  private TextViewElement mEnterElement;
  private ImageViewElement mEnterIcon;
  private TextViewElement mFollowElement;
  private ImageViewElement mFollowIcon;
  private long mLastTime = 0L;
  private Paint mLinePaint = new Paint();
  private ImageViewElement mLogoElement;
  private Paint mPaint = new Paint();
  private TextViewElement mSmallLogoElement;
  private final ViewLayout smallLayout = this.standardLayout.createChildLT(720, 50, 0, 95, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public AboutQtView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mLogoElement = new ImageViewElement(paramContext);
    this.mLogoElement.setImageRes(2130837691);
    this.mLogoElement.setOnElementClickListener(this);
    addElement(this.mLogoElement, i);
    this.mSmallLogoElement = new TextViewElement(paramContext);
    this.mSmallLogoElement.setMaxLineLimit(1);
    this.mSmallLogoElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mSmallLogoElement.setColor(SkinManager.getBackgroundColor());
    this.mSmallLogoElement.setText("当前版本：" + getResources().getString(2131492874), false);
    addElement(this.mSmallLogoElement, i);
    this.mCopyright = new TextViewElement(paramContext);
    this.mCopyright.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mCopyright.setMaxLineLimit(1);
    this.mCopyright.setColor(SkinManager.getBackgroundColor());
    this.mCopyright.setText(getResources().getString(2131492867), false);
    this.mCopyright.setTextSize(SkinManager.getInstance().getSubTextSize());
    addElement(this.mCopyright);
    this.mFollowIcon = new ImageViewElement(paramContext);
    this.mFollowIcon.setImageRes(2130837690);
    addElement(this.mFollowIcon, i);
    this.mEnterIcon = new ImageViewElement(paramContext);
    this.mEnterIcon.setImageRes(2130837689);
    addElement(this.mEnterIcon, i);
    this.mFollowElement = new TextViewElement(paramContext);
    this.mFollowElement.setMaxLineLimit(1);
    this.mFollowElement.setColor(SkinManager.getBackgroundColor());
    this.mFollowElement.setText("微博关注蜻蜓君", false);
    addElement(this.mFollowElement);
    this.mEnterElement = new TextViewElement(paramContext);
    this.mEnterElement.setMaxLineLimit(1);
    this.mEnterElement.setColor(SkinManager.getBackgroundColor());
    this.mEnterElement.setText("进入蜻蜓君的首页", false);
    addElement(this.mEnterElement);
    this.mLinePaint.setColor(654311423);
    this.mFollowElement.setOnElementClickListener(this);
    this.mFollowIcon.setOnElementClickListener(this);
    this.mEnterElement.setOnElementClickListener(this);
    this.mEnterIcon.setOnElementClickListener(this);
  }

  private void drawBg(Canvas paramCanvas)
  {
    Bitmap localBitmap = BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130837688);
    paramCanvas.drawBitmap(localBitmap, SkinManager.getTrimedBitmapRectBaseTop(localBitmap, this.standardLayout.width, this.standardLayout.height), this.standardLayout.getRect(), this.mPaint);
    drawLine(paramCanvas, this.mFollowElement.getBottomMargin());
    drawLine(paramCanvas, this.mEnterElement.getBottomMargin());
  }

  private void drawLine(Canvas paramCanvas, float paramFloat)
  {
    paramCanvas.drawLine(this.lineLayout.leftMargin, paramFloat, this.lineLayout.getRight(), paramFloat, this.mLinePaint);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    drawBg(paramCanvas);
    super.onDraw(paramCanvas);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if ((paramViewElement == this.mFollowIcon) || (paramViewElement == this.mFollowElement))
    {
      dispatchActionEvent("followQt", null);
      return;
    }
    if (paramViewElement == this.mLogoElement)
    {
      long l = System.currentTimeMillis();
      if (l < this.mLastTime + 1000L)
      {
        this.mClickCnt += 1;
        if (this.mClickCnt == 3)
          ControllerManager.getInstance().openHiddenFeatureController();
      }
      for (this.mClickCnt = 0; ; this.mClickCnt = 1)
      {
        this.mLastTime = l;
        return;
      }
    }
    dispatchActionEvent("toWebQt", null);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.logoLayout.scaleToBounds(this.standardLayout);
    this.smallLayout.scaleToBounds(this.standardLayout);
    this.copyrightLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.iconLayout.scaleToBounds(this.buttonLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.mLinePaint.setStrokeWidth(this.lineLayout.height);
    this.mCopyright.measure(0, this.standardLayout.height - this.copyrightLayout.topMargin - this.copyrightLayout.height, this.standardLayout.width, this.standardLayout.height - this.copyrightLayout.topMargin);
    this.mCopyright.setTextSize(this.copyrightLayout.height * 0.54F);
    this.mSmallLogoElement.measure(this.smallLayout.leftMargin, this.standardLayout.height - this.smallLayout.getBottom(), this.smallLayout.getRight(), this.standardLayout.height - this.smallLayout.topMargin);
    this.mSmallLogoElement.setTextSize(this.smallLayout.height * 0.6F);
    paramInt1 = (this.standardLayout.height - this.logoLayout.height - this.buttonLayout.height * 2 - this.smallLayout.getBottom()) / 3;
    this.mLogoElement.measure(this.logoLayout.leftMargin, this.logoLayout.topMargin + paramInt1, this.logoLayout.getRight(), this.logoLayout.getBottom() + paramInt1);
    this.mFollowIcon.measure(this.buttonLayout.leftMargin, paramInt1 * 2 + this.logoLayout.height + this.iconLayout.topMargin, this.buttonLayout.leftMargin + this.iconLayout.width, paramInt1 * 2 + this.logoLayout.height + this.iconLayout.getBottom());
    this.mFollowElement.measure(this.buttonLayout.leftMargin + this.iconLayout.getRight(), paramInt1 * 2 + this.logoLayout.height, this.buttonLayout.getRight(), paramInt1 * 2 + this.logoLayout.height + this.buttonLayout.height);
    this.mEnterIcon.measure(this.buttonLayout.leftMargin, paramInt1 * 2 + this.logoLayout.height + this.buttonLayout.height + this.iconLayout.topMargin, this.buttonLayout.leftMargin + this.iconLayout.width, paramInt1 * 2 + this.logoLayout.height + this.buttonLayout.height + this.iconLayout.getBottom());
    this.mEnterElement.measure(this.buttonLayout.leftMargin + this.iconLayout.getRight(), paramInt1 * 2 + this.logoLayout.height + this.buttonLayout.height, this.buttonLayout.getRight(), paramInt1 * 2 + this.logoLayout.height + this.buttonLayout.height * 2);
    this.mFollowElement.setTextSize(this.buttonLayout.height * 0.45F);
    this.mEnterElement.setTextSize(this.buttonLayout.height * 0.45F);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.aboutQt.AboutQtView
 * JD-Core Version:    0.6.2
 */