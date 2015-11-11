package fm.qingting.qtradio.view.scheduleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.Download;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.utils.TimeUtil;
import java.util.Locale;

public class BatchDownloadItemView extends QtListItemView
{
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(30, 22, 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = this.itemLayout.createChildLT(48, 48, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final DrawFilter filter = SkinManager.getInstance().getDrawFilter();
  private int hash = -24;
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout labelLayout = this.itemLayout.createChildLT(100, 45, 30, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final TextPaint mChannelPaint = new TextPaint();
  private Paint mCheckBgPaint = new Paint();
  private Rect mCheckRect = new Rect();
  private Paint mCheckStatePaint = new Paint();
  private int mDownloading = 0;
  private boolean mIsChecked = false;
  private final Paint mLabelHighlightPaint = new Paint();
  private final Paint mLabelPaint = new Paint();
  private Node mNode;
  private final Paint mPaint = new Paint();
  private final ViewLayout programLayout = this.itemLayout.createChildLT(600, 45, 30, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect textBound = new Rect();
  private final ViewLayout timeLayout = this.itemLayout.createChildLT(300, 45, 300, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout updateLayout = this.itemLayout.createChildLT(300, 45, 550, 10, ViewLayout.SCALE_FLAG_SLTCW);

  public BatchDownloadItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.hash = paramInt;
    setBackgroundColor(SkinManager.getCardColor());
    this.mChannelPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mLabelPaint.setColor(SkinManager.getTextColorHighlight());
    this.mLabelHighlightPaint.setColor(SkinManager.getLiveColor());
    this.mCheckBgPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mCheckStatePaint.setColor(SkinManager.getTextColorHighlight());
    this.mCheckBgPaint.setStyle(Paint.Style.STROKE);
    this.mCheckStatePaint.setStyle(Paint.Style.FILL);
    setItemSelectedEnable();
    setOnClickListener(this);
  }

  private void drawBg(Canvas paramCanvas)
  {
  }

  private void drawCheckState(Canvas paramCanvas)
  {
    if (this.mDownloading == 0)
    {
      if (this.mIsChecked)
      {
        paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2, this.mCheckStatePaint);
        paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getResources(), this.hash, 2130837754), null, this.mCheckRect, this.mPaint);
      }
    }
    else
      return;
    paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2, this.mCheckBgPaint);
  }

  private void drawDownloadLabel(Canvas paramCanvas)
  {
    String str;
    float f1;
    float f2;
    if (this.mDownloading > 0)
    {
      if (this.mDownloading != 1)
        break label123;
      str = "正在下载";
      this.mLabelPaint.getTextBounds(str, 0, str.length(), this.textBound);
      f1 = this.labelLayout.leftMargin;
      f2 = this.programLayout.topMargin + this.programLayout.height + this.labelLayout.topMargin + (this.labelLayout.height - this.textBound.top - this.textBound.bottom) / 2;
      if (this.mDownloading != 1)
        break label130;
    }
    label130: for (Paint localPaint = this.mLabelHighlightPaint; ; localPaint = this.mLabelPaint)
    {
      paramCanvas.drawText(str, f1, f2, localPaint);
      return;
      label123: str = "已下载";
      break;
    }
  }

  private void drawFileSize(Canvas paramCanvas)
  {
    String str = "";
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
      str = SizeInfo.getFileSize(((ProgramNode)this.mNode).getDuration() * 24 * 125);
    this.mChannelPaint.getTextBounds(str, 0, str.length(), this.textBound);
    if (this.mDownloading == 0);
    for (float f = this.checkbgLayout.getRight() + this.programLayout.leftMargin; ; f = this.labelLayout.getRight() + this.programLayout.leftMargin)
    {
      paramCanvas.drawText(str, f, this.programLayout.topMargin + this.programLayout.height + this.timeLayout.topMargin + (this.timeLayout.height - this.textBound.top - this.textBound.bottom) / 2, this.mChannelPaint);
      return;
    }
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, this.lineLayout.leftMargin, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  private void drawTime(Canvas paramCanvas)
  {
    String str = "";
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
      str = "时长:" + getDurationTime(((ProgramNode)this.mNode).getDuration());
    this.mChannelPaint.getTextBounds(str, 0, str.length(), this.textBound);
    paramCanvas.drawText(str, this.timeLayout.leftMargin, this.programLayout.topMargin + this.programLayout.height + this.timeLayout.topMargin + (this.timeLayout.height - this.textBound.top - this.textBound.bottom) / 2, this.mChannelPaint);
  }

  private void drawTitle(Canvas paramCanvas)
  {
    String str = "";
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
      str = ((ProgramNode)this.mNode).title;
    TextPaint localTextPaint = SkinManager.getInstance().getNormalTextPaint();
    if (this.mDownloading == 0)
    {
      f = this.itemLayout.width - this.programLayout.leftMargin - this.checkbgLayout.getRight();
      str = TextUtils.ellipsize(str, localTextPaint, f, TextUtils.TruncateAt.END).toString();
      localTextPaint.getTextBounds(str, 0, str.length(), this.textBound);
      if (this.mDownloading != 0)
        break label187;
    }
    label187: for (float f = this.checkbgLayout.getRight() + this.programLayout.leftMargin; ; f = this.checkbgLayout.leftMargin)
    {
      paramCanvas.drawText(str, f, this.programLayout.topMargin + (this.programLayout.height - this.textBound.top - this.textBound.bottom) / 2, localTextPaint);
      return;
      f = this.itemLayout.width - this.checkbgLayout.leftMargin;
      break;
    }
  }

  private void drawUpdate(Canvas paramCanvas)
  {
    String str = "";
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
    {
      if (((ProgramNode)this.mNode).channelType == 0)
        str = TimeUtil.msToDate2(((ProgramNode)this.mNode).getAbsoluteStartTime() * 1000L);
    }
    else
    {
      if ((str != null) && (!str.equalsIgnoreCase("")))
        break label133;
      return;
    }
    if (((ProgramNode)this.mNode).isDownloadProgram())
      if (((ProgramNode)this.mNode).downloadInfo == null)
        break label218;
    label133: label218: for (long l = ((ProgramNode)this.mNode).downloadInfo.updateTime; ; l = 0L)
    {
      str = TimeUtil.msToDate2(l * 1000L);
      break;
      str = TimeUtil.msToDate2(((ProgramNode)this.mNode).getUpdateTime());
      break;
      this.mChannelPaint.getTextBounds(str, 0, str.length(), this.textBound);
      paramCanvas.drawText(str, this.updateLayout.leftMargin, this.programLayout.topMargin + this.programLayout.height + this.updateLayout.topMargin + (this.updateLayout.height - this.textBound.top - this.textBound.bottom) / 2, this.mChannelPaint);
      return;
    }
  }

  private void generateRect()
  {
    this.mCheckRect.set(this.checkbgLayout.leftMargin + (this.checkbgLayout.width - this.checkStateLayout.width) / 2, (this.itemLayout.height - this.checkStateLayout.height) / 2, this.checkbgLayout.leftMargin + (this.checkbgLayout.width + this.checkStateLayout.width) / 2, (this.itemLayout.height + this.checkStateLayout.height) / 2);
  }

  private String getDurationTime(int paramInt)
  {
    int i = paramInt / 3600;
    int j = paramInt / 60 % 60;
    paramInt %= 60;
    if (i == 0)
    {
      if (paramInt == 0)
        return String.format(Locale.CHINA, "%d分", new Object[] { Integer.valueOf(j) });
      if (j == 0)
        return String.format(Locale.CHINA, "%d秒", new Object[] { Integer.valueOf(paramInt) });
      return String.format(Locale.CHINA, "%d分%d秒", new Object[] { Integer.valueOf(j), Integer.valueOf(paramInt) });
    }
    if (j == 0)
      return String.format(Locale.CHINA, "%d小时", new Object[] { Integer.valueOf(i) });
    return String.format(Locale.CHINA, "%d小时%d分", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.mNode == null)
      return;
    paramCanvas.setDrawFilter(this.filter);
    paramCanvas.save();
    drawBg(paramCanvas);
    drawCheckState(paramCanvas);
    drawDownloadLabel(paramCanvas);
    drawTitle(paramCanvas);
    drawFileSize(paramCanvas);
    drawTime(paramCanvas);
    drawUpdate(paramCanvas);
    drawLine(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.programLayout.scaleToBounds(this.itemLayout);
    this.checkbgLayout.scaleToBounds(this.itemLayout);
    this.checkStateLayout.scaleToBounds(this.checkbgLayout);
    this.updateLayout.scaleToBounds(this.itemLayout);
    this.timeLayout.scaleToBounds(this.itemLayout);
    this.labelLayout.scaleToBounds(this.itemLayout);
    this.mChannelPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mLabelPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mLabelHighlightPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mCheckBgPaint.setStrokeWidth(this.checkStateLayout.leftMargin);
    generateRect();
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  protected void onQtItemClick(View paramView)
  {
    if (this.mDownloading == 0)
      dispatchActionEvent("itemSelect", null);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mNode = ((Node)paramObject);
      this.mDownloading = InfoManager.getInstance().root().mDownLoadInfoNode.hasDownLoad(this.mNode);
    }
    while (!paramString.equalsIgnoreCase("checkState"))
      return;
    this.mIsChecked = ((Boolean)paramObject).booleanValue();
    invalidate();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.scheduleview.BatchDownloadItemView
 * JD-Core Version:    0.6.2
 */