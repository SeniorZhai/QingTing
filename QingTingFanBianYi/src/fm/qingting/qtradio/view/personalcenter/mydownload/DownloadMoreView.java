package fm.qingting.qtradio.view.personalcenter.mydownload;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Toast;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.utils.QTMSGManage;

public class DownloadMoreView extends QtView
{
  Bitmap icon = BitmapFactory.decodeResource(getResources(), 2130837696);
  private final ViewLayout iconLayout = this.standardLayout.createChildLT(52, 52, 30, 24, ViewLayout.SCALE_FLAG_SLTCW);
  private Paint iconPaint = new Paint();
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private ChannelNode mDownloadChannelNode;
  private boolean mInTouchMode;
  private float mLastMotionX;
  private float mLastMotionY;
  private Bitmap scaledIcon;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 100, 720, 100, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private Rect textBound = new Rect();
  final TextPaint textPaint = new TextPaint();
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(608, 50, 112, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public DownloadMoreView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
      }
    });
  }

  private void downloadMore()
  {
    QTMSGManage.getInstance().sendStatistcsMessage("downloadMoreClick");
    ChannelNode localChannelNode;
    if (this.mDownloadChannelNode != null)
    {
      localChannelNode = ChannelHelper.getInstance().getChannel(this.mDownloadChannelNode.channelId, 1);
      if (localChannelNode == null)
        Toast.makeText(getContext(), "该专辑无法下载更多", 1).show();
    }
    else
    {
      return;
    }
    if ((localChannelNode != null) && (!localChannelNode.hasEmptyProgramSchedule()))
    {
      ControllerManager.getInstance().redirectToBatchDownloadView(localChannelNode, false, true);
      return;
    }
    ControllerManager.getInstance().redirectToBatchDownloadView(localChannelNode, false, true);
  }

  public void onDraw(Canvas paramCanvas)
  {
    int i = paramCanvas.save();
    paramCanvas.clipRect(0, 0, this.standardLayout.width, this.standardLayout.height);
    paramCanvas.drawColor(SkinManager.getCardColor());
    paramCanvas.restoreToCount(i);
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, this.lineLayout.leftMargin, this.standardLayout.width, this.standardLayout.height - this.lineLayout.height, 1);
    this.scaledIcon = Bitmap.createScaledBitmap(this.icon, this.iconLayout.width, this.iconLayout.height, false);
    paramCanvas.drawBitmap(this.scaledIcon, this.iconLayout.leftMargin, (this.standardLayout.height - this.iconLayout.height) / 2, this.iconPaint);
    this.textPaint.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.textPaint.setColor(-65536);
    this.textPaint.getTextBounds("下载更多", 0, "下载更多".length(), this.textBound);
    paramCanvas.drawText("下载更多", this.titleLayout.leftMargin, (this.standardLayout.height - this.textBound.top - this.textBound.bottom) / 2, this.textPaint);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.iconLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((!this.mInTouchMode) && (paramMotionEvent.getAction() != 0));
    do
    {
      return true;
      switch (paramMotionEvent.getAction())
      {
      default:
        return true;
      case 0:
        this.mLastMotionX = paramMotionEvent.getX();
        this.mLastMotionY = paramMotionEvent.getY();
        this.mInTouchMode = true;
        invalidate();
        return true;
      case 2:
        this.mLastMotionX = paramMotionEvent.getX();
        this.mLastMotionY = paramMotionEvent.getY();
      case 3:
      case 1:
      }
    }
    while ((this.mLastMotionX >= 0.0F) && (this.mLastMotionX <= this.standardLayout.width) && (this.mLastMotionY >= 0.0F) && (this.mLastMotionY <= this.standardLayout.height));
    this.mInTouchMode = false;
    return true;
    this.mInTouchMode = false;
    return true;
    downloadMore();
    return true;
  }

  public void setChannel(ChannelNode paramChannelNode)
  {
    this.mDownloadChannelNode = paramChannelNode;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mydownload.DownloadMoreView
 * JD-Core Version:    0.6.2
 */