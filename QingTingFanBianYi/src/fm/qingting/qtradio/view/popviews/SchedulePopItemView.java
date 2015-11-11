package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Toast;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.fmdriver.FMManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RadioChannelNode;
import fm.qingting.qtradio.model.ReserveInfoNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.PlayMode;
import java.util.Locale;

public class SchedulePopItemView extends QtListItemView
{
  private final ViewLayout arrowLayout = this.itemLayout.createChildLT(36, 36, 650, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout channelLayout = this.itemLayout.createChildLT(720, 45, 30, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private DrawFilter filter;
  private int hash = -2;
  private Paint iconPaint = new Paint();
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(720, 45, 30, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 800, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(670, 1, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Paint livePaint = new Paint();
  private final ViewLayout livingLayout = this.itemLayout.createChildLT(20, 26, 10, 29, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect mArrowRect = new Rect();
  private Rect mLivingRect = new Rect();
  private Node mNode;
  private Rect textBound = new Rect();

  public SchedulePopItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.hash = paramInt;
    this.livePaint.setColor(SkinManager.getLiveColor());
    setOnClickListener(this);
    setItemSelectedEnable();
    this.filter = SkinManager.getInstance().getDrawFilter();
  }

  private void drawArrow(Canvas paramCanvas)
  {
    paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getResources(), this.hash, 2130837697), null, this.mArrowRect, this.iconPaint);
  }

  private void drawBg(Canvas paramCanvas)
  {
    if (isItemPressed())
      paramCanvas.drawColor(SkinManager.getItemHighlightMaskColor());
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, this.lineLayout.leftMargin, this.lineLayout.getRight(), this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  private void drawLivingLabel(Canvas paramCanvas)
  {
    paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130837925), null, this.mLivingRect, this.iconPaint);
  }

  private void drawSubInfo(Canvas paramCanvas)
  {
    Object localObject2 = null;
    int i;
    Object localObject1;
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
    {
      i = ((ProgramNode)this.mNode).getCurrPlayStatus();
      if (i == 1)
      {
        this.livePaint.setColor(SkinManager.getLiveColor());
        localObject2 = "直播";
        localObject1 = ((ProgramNode)this.mNode).startTime;
        localObject1 = (String)localObject1 + "-";
        localObject1 = (String)localObject1 + ((ProgramNode)this.mNode).endTime;
        i = 0;
      }
    }
    while (true)
    {
      Object localObject3 = SkinManager.getInstance().getSubTextPaint();
      if ((localObject2 != null) && (localObject1 != null))
      {
        this.livePaint.getTextBounds((String)localObject2, 0, ((String)localObject2).length(), this.textBound);
        f1 = this.infoLayout.getLeft();
        f2 = this.channelLayout.topMargin + this.channelLayout.height + this.infoLayout.topMargin + (this.infoLayout.height - this.textBound.top - this.textBound.bottom) / 2;
        paramCanvas.drawText((String)localObject2, f1, f2, this.livePaint);
        f3 = this.textBound.width();
        float f4 = this.infoLayout.getLeft() / 2;
        ((TextPaint)localObject3).getTextBounds((String)localObject1, 0, ((String)localObject1).length(), this.textBound);
        paramCanvas.drawText((String)localObject1, f1 + f3 + f4, f2, (Paint)localObject3);
      }
      do
      {
        return;
        if (i == 2)
        {
          if (InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.isExisted(this.mNode));
          for (localObject1 = "己预约"; ; localObject1 = "预约")
          {
            this.livePaint.setColor(SkinManager.getTextColorHighlight());
            localObject2 = ((ProgramNode)this.mNode).startTime;
            localObject2 = (String)localObject2 + "-";
            localObject3 = (String)localObject2 + ((ProgramNode)this.mNode).endTime;
            localObject2 = localObject1;
            localObject1 = localObject3;
            i = 0;
            break;
          }
        }
        if (((ProgramNode)this.mNode).channelType == 0)
        {
          localObject1 = ((ProgramNode)this.mNode).startTime;
          localObject1 = (String)localObject1 + "-";
          localObject1 = (String)localObject1 + ((ProgramNode)this.mNode).endTime;
          i = 1;
          break;
        }
        localObject1 = getDurationTime(((ProgramNode)this.mNode).getDuration());
        i = 0;
        break;
        if (i == 0)
          break label728;
        ((TextPaint)localObject3).getTextBounds("回听:", 0, "回听:".length(), this.textBound);
        f1 = this.infoLayout.getLeft();
        paramCanvas.drawText("回听:", f1, this.channelLayout.topMargin + this.channelLayout.height + this.infoLayout.topMargin + (this.infoLayout.height - this.textBound.top - this.textBound.bottom) / 2, (Paint)localObject3);
      }
      while ((localObject1 == null) || (((String)localObject1).equalsIgnoreCase("")));
      float f2 = this.textBound.width();
      float f3 = this.infoLayout.getLeft() / 2;
      ((TextPaint)localObject3).getTextBounds((String)localObject1, 0, ((String)localObject1).length(), this.textBound);
      paramCanvas.drawText((String)localObject1, f2 + f1 + f3, this.channelLayout.topMargin + this.channelLayout.height + this.infoLayout.topMargin + (this.infoLayout.height - this.textBound.top - this.textBound.bottom) / 2, (Paint)localObject3);
      return;
      label728: ((TextPaint)localObject3).getTextBounds("时长:", 0, "时长:".length(), this.textBound);
      float f1 = this.infoLayout.getLeft();
      paramCanvas.drawText("时长:", f1, this.channelLayout.topMargin + this.channelLayout.height + this.infoLayout.topMargin + (this.infoLayout.height - this.textBound.top - this.textBound.bottom) / 2, (Paint)localObject3);
      f2 = this.textBound.width();
      f3 = this.infoLayout.getLeft() / 2;
      ((TextPaint)localObject3).getTextBounds((String)localObject1, 0, ((String)localObject1).length(), this.textBound);
      paramCanvas.drawText((String)localObject1, f2 + f1 + f3, this.channelLayout.topMargin + this.channelLayout.height + this.infoLayout.topMargin + (this.infoLayout.height - this.textBound.top - this.textBound.bottom) / 2, (Paint)localObject3);
      return;
      i = 0;
      localObject1 = null;
    }
  }

  private void drawTitle(Canvas paramCanvas)
  {
    boolean bool = isPlaying();
    if (bool)
      drawLivingLabel(paramCanvas);
    TextPaint localTextPaint = SkinManager.getInstance().getNormalTextPaint();
    String str = getTitle();
    int j = this.mArrowRect.left;
    int i;
    float f1;
    label123: float f2;
    if (bool)
    {
      i = this.mLivingRect.right + this.channelLayout.leftMargin;
      str = TextUtils.ellipsize(str, localTextPaint, j - i, TextUtils.TruncateAt.END).toString();
      localTextPaint.getTextBounds(str, 0, str.length(), this.textBound);
      if (!bool)
        break label202;
      f1 = this.mLivingRect.right + this.livingLayout.leftMargin;
      f2 = this.channelLayout.topMargin + (this.channelLayout.height - this.textBound.top - this.textBound.bottom) / 2;
      if ((!bool) && (!isItemPressed()))
        break label214;
      paramCanvas.drawText(str, f1, f2, SkinManager.getInstance().getHighlightTextPaint());
    }
    while (true)
    {
      drawSubInfo(paramCanvas);
      return;
      i = this.channelLayout.leftMargin;
      break;
      label202: f1 = this.channelLayout.leftMargin;
      break label123;
      label214: paramCanvas.drawText(str, f1, f2, localTextPaint);
    }
  }

  private void generateRect()
  {
    int i = this.arrowLayout.leftMargin;
    int j = (this.itemLayout.height - this.arrowLayout.height) / 2;
    this.mArrowRect.set(i, j, this.arrowLayout.width + i, this.arrowLayout.height + j);
    this.mLivingRect.set(this.channelLayout.leftMargin, this.livingLayout.topMargin, this.channelLayout.leftMargin + this.livingLayout.width, this.livingLayout.getBottom());
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
    return String.format(Locale.CHINA, "%d小时%d分", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
  }

  private String getTitle()
  {
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
      return ((ProgramNode)this.mNode).title;
    return "";
  }

  private boolean isPlaying()
  {
    if (this.mNode == null)
      return false;
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    if (localNode == null)
      return false;
    if (!this.mNode.nodeName.equalsIgnoreCase(localNode.nodeName))
      return false;
    return (this.mNode.nodeName.equalsIgnoreCase("program")) && (((ProgramNode)this.mNode).id == ((ProgramNode)localNode).id);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.mNode == null)
      return;
    paramCanvas.save();
    paramCanvas.setDrawFilter(this.filter);
    drawBg(paramCanvas);
    drawLine(paramCanvas);
    drawTitle(paramCanvas);
    drawArrow(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.itemLayout.scaleToBounds(paramInt1, paramInt2);
    this.channelLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.arrowLayout.scaleToBounds(this.itemLayout);
    this.livingLayout.scaleToBounds(this.itemLayout);
    this.livePaint.setTextSize(this.channelLayout.height * 0.5F);
    generateRect();
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  protected void onQtItemClick(View paramView)
  {
    if (this.mNode == null)
      return;
    if ((this.mNode.nodeName.equalsIgnoreCase("program")) && (((ProgramNode)this.mNode).getCurrPlayStatus() == 2))
    {
      if (InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.isExisted(this.mNode))
        InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.cancelReserve(((ProgramNode)this.mNode).id);
      while (true)
      {
        invalidate();
        return;
        InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.addReserveNode((ProgramNode)this.mNode);
      }
    }
    int j;
    int i;
    if (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.FMPLAY)
    {
      Toast.makeText(getContext(), "亲，无法使用系统收音机回听节目，只能播放当前直播节目", 1).show();
      j = 0;
      if (this.mNode.nodeName.equalsIgnoreCase("program"))
      {
        i = j;
        if (this.mNode.parent != null)
        {
          i = j;
          if (this.mNode.parent.nodeName.equalsIgnoreCase("radiochannel"))
            i = Integer.valueOf(((RadioChannelNode)this.mNode.parent).freq).intValue();
        }
        if (i != 0)
          FMManager.getInstance().tune(i);
        PlayerAgent.getInstance().dispatchPlayStateInFM(4096);
        InfoManager.getInstance().root().tuneFM(true);
      }
    }
    while (true)
    {
      invalidate();
      return;
      i = j;
      if (!this.mNode.nodeName.equalsIgnoreCase("radiochannel"))
        break;
      i = Integer.valueOf(((RadioChannelNode)this.mNode).freq).intValue();
      break;
      dispatchActionEvent("refresh", this.mNode);
      PlayerAgent.getInstance().play(this.mNode);
    }
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mNode = ((Node)paramObject);
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.SchedulePopItemView
 * JD-Core Version:    0.6.2
 */