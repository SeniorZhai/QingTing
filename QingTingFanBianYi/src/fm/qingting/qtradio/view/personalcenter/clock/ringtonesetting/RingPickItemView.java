package fm.qingting.qtradio.view.personalcenter.clock.ringtonesetting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.Node;

public class RingPickItemView extends ViewImpl
  implements View.OnClickListener
{
  private final ViewLayout channelLayout = this.itemLayout.createChildLT(720, 45, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(30, 22, 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = this.itemLayout.createChildLT(48, 48, 50, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private DrawFilter filter;
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 112, 720, 800, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private Rect mCheckRect = new Rect();
  private Paint mCheckStatePaint = new Paint();
  private boolean mChecked = false;
  private int mHash = -66;
  private Node mNode;
  private final Paint mPaint = new Paint();
  private Rect textBound = new Rect();

  public RingPickItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mHash = paramInt;
    setBackgroundColor(SkinManager.getCardColor());
    this.filter = SkinManager.getInstance().getDrawFilter();
    this.mCheckStatePaint.setColor(SkinManager.getTextColorHighlight());
    this.mCheckStatePaint.setStyle(Paint.Style.FILL);
    setOnClickListener(this);
  }

  private void dispatchSelectEvent()
  {
    dispatchActionEvent("itemClick", this.mNode);
  }

  private void drawCheckState(Canvas paramCanvas)
  {
    if (this.mChecked)
    {
      paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2, this.mCheckStatePaint);
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getResources(), this.mHash, 2130837754), null, this.mCheckRect, this.mPaint);
    }
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, 0, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  private void drawTitle(Canvas paramCanvas)
  {
    String str = getName();
    TextPaint localTextPaint = SkinManager.getInstance().getNormalTextPaint();
    localTextPaint.getTextBounds(str, 0, str.length(), this.textBound);
    paramCanvas.drawText(str, this.channelLayout.getLeft() + this.itemLayout.leftMargin, (this.itemLayout.height - this.textBound.top - this.textBound.bottom) / 2.0F, localTextPaint);
  }

  private String getName()
  {
    if (this.mNode == null)
      return "";
    if ((this.mNode.nodeName.equalsIgnoreCase("channel")) && (((ChannelNode)this.mNode).title != null))
      return ((ChannelNode)this.mNode).title;
    return "";
  }

  public void onClick(View paramView)
  {
    dispatchSelectEvent();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.setDrawFilter(this.filter);
    drawTitle(paramCanvas);
    drawCheckState(paramCanvas);
    drawLine(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.itemLayout.scaleToBounds(paramInt1, paramInt2);
    this.channelLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.checkbgLayout.scaleToBounds(this.itemLayout);
    this.checkStateLayout.scaleToBounds(this.checkbgLayout);
    this.mCheckRect.set(this.itemLayout.width - this.checkbgLayout.width - this.checkbgLayout.leftMargin + (this.checkbgLayout.width - this.checkStateLayout.width) / 2, (this.itemLayout.height - this.checkStateLayout.height) / 2, this.itemLayout.width - this.checkbgLayout.width - this.checkbgLayout.leftMargin + (this.checkbgLayout.width + this.checkStateLayout.width) / 2, (this.itemLayout.height + this.checkStateLayout.height) / 2);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mNode = ((Node)paramObject);
      invalidate();
    }
    boolean bool;
    do
    {
      do
        return;
      while (!paramString.equalsIgnoreCase("checkState"));
      bool = ((Boolean)paramObject).booleanValue();
    }
    while (this.mChecked == bool);
    this.mChecked = bool;
    invalidate();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.clock.ringtonesetting.RingPickItemView
 * JD-Core Version:    0.6.2
 */