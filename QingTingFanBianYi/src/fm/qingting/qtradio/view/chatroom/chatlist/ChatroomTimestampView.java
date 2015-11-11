package fm.qingting.qtradio.view.chatroom.chatlist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class ChatroomTimestampView extends ViewImpl
{
  private final ViewLayout contentLayout = this.itemLayout.createChildLT(720, 40, 15, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 60, 720, 60, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final Paint mBgPaint = new Paint();
  private final RectF mBgRectF = new RectF();
  private String mContent;
  private final Rect mTextBound = new Rect();
  private final Paint mTextPaint = new Paint();
  private final ViewLayout roundLayout = this.itemLayout.createChildLT(6, 6, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public ChatroomTimestampView(Context paramContext)
  {
    super(paramContext);
    this.mBgPaint.setColor(-3355444);
    this.mTextPaint.setColor(-1);
  }

  private void drawContent(Canvas paramCanvas)
  {
    if ((this.mContent == null) || (this.mContent.equalsIgnoreCase("")))
      return;
    this.mTextPaint.getTextBounds(this.mContent, 0, this.mContent.length(), this.mTextBound);
    float f = (this.itemLayout.width - this.mTextBound.left - this.mTextBound.right) / 2;
    this.mBgRectF.set(f - this.contentLayout.leftMargin, this.contentLayout.topMargin, (this.itemLayout.width + this.mTextBound.left + this.mTextBound.right) / 2 + this.contentLayout.leftMargin, this.contentLayout.getBottom());
    paramCanvas.drawRoundRect(this.mBgRectF, this.roundLayout.width, this.roundLayout.height, this.mBgPaint);
    paramCanvas.drawText(this.mContent, f, (this.itemLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, this.mTextPaint);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawContent(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.contentLayout.scaleToBounds(this.itemLayout);
    this.roundLayout.scaleToBounds(this.itemLayout);
    this.mTextPaint.setTextSize(SkinManager.getInstance().getTinyTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mContent = ((String)((ChatItem)paramObject).data);
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.chatlist.ChatroomTimestampView
 * JD-Core Version:    0.6.2
 */