package fm.qingting.qtradio.view.virtualchannels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayHistoryNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.ReserveInfoNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.popviews.CustomPopActionParam.Builder;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.TimeUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class ChannelDetailItemView extends QtListItemView
{
  private static final String DOWNLOADED = "已下载";
  private static final String DOWNLOADING = "正在下载";
  private static final int INDEX_CLOSE = 2;
  private static final int INDEX_ITEM = 0;
  private static final int INDEX_TRIANGLE = 1;
  private static final int INDEX_UNKNOWN = -1;
  private final long TEN_MINUTE = 600000L;
  private final ViewLayout channelnameLayout = this.itemLayout.createChildLT(720, 35, 30, 16, ViewLayout.SCALE_FLAG_SLTCW);
  private final DrawFilter filter = SkinManager.getInstance().getDrawFilter();
  private int hash = -24;
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 126, 720, 126, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout livingLayout = this.itemLayout.createChildLT(10, 90, 0, 18, ViewLayout.SCALE_FLAG_SLTCW);
  boolean mBelongToPodcasterInfo = false;
  private final Paint mChannelNamePaint = new TextPaint();
  private int mDownloadState;
  private final TextPaint mDownloadStatePaint = new TextPaint();
  private boolean mHasChannelName = false;
  private final Paint mHighlightPaint = new Paint();
  private boolean mInTouchMode = false;
  private boolean mIsLiving = false;
  private int mItemHeight = 100;
  private float mLastMotionX = 0.0F;
  private float mLastMotionY = 0.0F;
  private final Paint mLinePaint = new Paint();
  private final Rect mLivingRect = new Rect();
  private Node mNode;
  private final Paint mPaint = new Paint();
  private boolean mRemind = false;
  private boolean mSameDay = false;
  private int mSelectedIndex = -1;
  private final Paint mStatePaint = new TextPaint();
  private final TextPaint mTimePaint = new TextPaint();
  private int mTitleHeight = 30;
  private StaticLayout mTitleLayout;
  private final Rect mTriangularRect = new Rect();
  private final TextPaint mUpdateTimePaint = new TextPaint();
  private final ViewLayout otherInfoLayout = this.itemLayout.createChildLT(720, 24, 30, 14, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout programLayout = this.itemLayout.createChildLT(720, 45, 30, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout recentCloseButtonLayout = this.itemLayout.createChildLT(20, 26, 20, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout recentIndicatorLayout = this.itemLayout.createChildLT(20, 26, 32, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout recentItemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 86, 720, 50, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout recentLineLayout = this.itemLayout.createChildLT(720, 1, 30, 1, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout recentTitleLayout = this.itemLayout.createChildLT(570, 50, 80, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout remindLayout = this.itemLayout.createChildLT(22, 22, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final Rect textBound = new Rect();
  private final ViewLayout triangularLayout = this.itemLayout.createChildLT(22, 22, 20, 20, ViewLayout.SCALE_FLAG_SLTCW);

  public ChannelDetailItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.hash = paramInt;
    this.mHighlightPaint.setColor(SkinManager.getTextColorHighlight2());
    this.mHighlightPaint.setStyle(Paint.Style.FILL);
    this.mDownloadStatePaint.setColor(SkinManager.getTextColorHighlight2());
    this.mChannelNamePaint.setColor(SkinManager.getTextColorSubInfo());
    this.mTimePaint.setColor(SkinManager.getTextColorThirdLevel());
    this.mUpdateTimePaint.setColor(SkinManager.getTextColorThirdLevel());
    this.mLinePaint.setColor(SkinManager.getDividerColor_new());
    setOnClickListener(this);
    setItemSelectedEnable();
  }

  private void doClickEvent()
  {
    if ((this.mNode == null) || (this.mSelectedIndex == -1))
      return;
    if (this.mSelectedIndex == 2)
    {
      QTMSGManage.getInstance().sendStatistcsMessage("resumerecent_close");
      EventDispacthManager.getInstance().dispatchAction("closerecentplay", null);
      return;
    }
    Object localObject;
    if (this.mSelectedIndex == 1)
    {
      localObject = new CustomPopActionParam.Builder();
      ((CustomPopActionParam.Builder)localObject).setTitle(getTitle()).addNode(this.mNode);
      if (this.mDownloadState == 0)
        ((CustomPopActionParam.Builder)localObject).addButton(1);
      ((CustomPopActionParam.Builder)localObject).addButton(0);
      localObject = ((CustomPopActionParam.Builder)localObject).create();
      EventDispacthManager.getInstance().dispatchAction("showoperatepop", localObject);
      return;
    }
    int i;
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
    {
      i = ((ProgramNode)this.mNode).getCurrPlayStatus();
      localObject = null;
    }
    while (true)
    {
      if (i == 2)
      {
        if (InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.isExisted(this.mNode))
          InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.cancelReserve(((ProgramNode)this.mNode).id);
        while (true)
        {
          invalidate();
          label194: if (!this.mBelongToPodcasterInfo)
            break label319;
          QTMSGManage.getInstance().sendStatistcsMessage("PodcasterInfo", "收听");
          return;
          if (this.mNode.nodeName.equalsIgnoreCase("ondemandprogram"))
          {
            localObject = null;
            i = 3;
            break;
          }
          if (!this.mNode.nodeName.equalsIgnoreCase("playhistory"))
            break label329;
          QTMSGManage.getInstance().sendStatistcsMessage("resumerecent_play");
          localObject = ((PlayHistoryNode)this.mNode).playNode;
          i = 1;
          break;
          InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.addReserveNode((ProgramNode)this.mNode);
        }
      }
      ControllerManager localControllerManager = ControllerManager.getInstance();
      if (localObject != null);
      while (true)
      {
        localControllerManager.redirectToPlayViewByNode((Node)localObject, true);
        break label194;
        label319: break;
        localObject = this.mNode;
      }
      label329: localObject = null;
      i = 1;
    }
  }

  private void drawBg(Canvas paramCanvas)
  {
    if ((isItemPressed()) && (this.mSelectedIndex == 0))
    {
      int i = paramCanvas.save();
      paramCanvas.clipRect(0, 0, this.itemLayout.width, this.mItemHeight);
      paramCanvas.drawColor(SkinManager.getItemHighlightMaskColor_new());
      paramCanvas.restoreToCount(i);
    }
  }

  private int drawChannelName(Canvas paramCanvas, int paramInt)
  {
    int i = paramInt;
    if (this.mHasChannelName)
    {
      String str = ((ProgramNode)this.mNode).getChannelName();
      this.mChannelNamePaint.getTextBounds(str, 0, str.length(), this.textBound);
      paramCanvas.drawText(str, this.channelnameLayout.leftMargin, this.channelnameLayout.topMargin + paramInt + (this.channelnameLayout.height - this.textBound.height()), this.mChannelNamePaint);
      i = paramInt + (this.channelnameLayout.topMargin + this.channelnameLayout.height);
    }
    return i;
  }

  private int drawDownloadState(Canvas paramCanvas, int paramInt)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    switch (this.mDownloadState)
    {
    default:
      localObject1 = localObject2;
    case 0:
    case 2:
    case 1:
    case 3:
    }
    while (localObject1 == null)
    {
      return this.mTriangularRect.left;
      localObject1 = "正在下载";
      continue;
      localObject1 = "已下载";
    }
    this.mDownloadStatePaint.getTextBounds((String)localObject1, 0, ((String)localObject1).length(), this.textBound);
    paramCanvas.drawText((String)localObject1, this.mTriangularRect.left - this.textBound.width() - this.otherInfoLayout.leftMargin, this.otherInfoLayout.topMargin + paramInt + (this.otherInfoLayout.height - this.textBound.height()), this.mDownloadStatePaint);
    return this.mTriangularRect.left - this.textBound.width() - this.otherInfoLayout.leftMargin;
  }

  private float drawDurationTime(Canvas paramCanvas, int paramInt)
  {
    if (this.mNode.nodeName.equalsIgnoreCase("program"));
    String str;
    switch (((ProgramNode)this.mNode).getCurrPlayStatus())
    {
    default:
      str = "";
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      int i = drawDownloadState(paramCanvas, paramInt);
      this.mTimePaint.getTextBounds(str, 0, str.length(), this.textBound);
      paramCanvas.drawText(str, i - this.textBound.width() - this.otherInfoLayout.leftMargin, this.otherInfoLayout.topMargin + paramInt + (this.otherInfoLayout.height - this.textBound.height()) / 2, this.mTimePaint);
      return i - this.textBound.width() - this.otherInfoLayout.leftMargin;
      str = TimeUtil.msToDate3(((ProgramNode)this.mNode).getAbsoluteStartTime() * 1000L) + "-" + TimeUtil.msToDate3(((ProgramNode)this.mNode).getAbsoluteEndTime() * 1000L);
      continue;
      str = getDurationTime(((ProgramNode)this.mNode).getDuration());
    }
  }

  private void drawLine(Canvas paramCanvas)
  {
    paramCanvas.drawLine(this.lineLayout.leftMargin, this.mItemHeight - this.lineLayout.height, this.itemLayout.width, this.mItemHeight - this.lineLayout.height, this.mLinePaint);
  }

  private void drawLivingLabel(Canvas paramCanvas)
  {
    paramCanvas.drawRect(this.mLivingRect, this.mHighlightPaint);
  }

  private void drawNormalItem(Canvas paramCanvas)
  {
    drawBg(paramCanvas);
    drawStateInfo(paramCanvas, drawChannelName(paramCanvas, drawTitle(paramCanvas)));
    drawLine(paramCanvas);
  }

  private void drawRecentBg(Canvas paramCanvas)
  {
    Paint localPaint = new Paint();
    localPaint.setStyle(Paint.Style.FILL);
    localPaint.setColor(-1513237);
    paramCanvas.drawRect(0.0F, 0.0F, this.recentItemLayout.width, this.recentItemLayout.height, localPaint);
  }

  private void drawRecentCloseButton(Canvas paramCanvas)
  {
    paramCanvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), 2130837725), this.recentCloseButtonLayout.width, this.recentCloseButtonLayout.height, false), this.recentItemLayout.width - this.recentCloseButtonLayout.width - this.recentCloseButtonLayout.leftMargin, (this.recentItemLayout.height - this.recentCloseButtonLayout.height) / 2, new Paint());
  }

  private void drawRecentIndicator(Canvas paramCanvas)
  {
    paramCanvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), 2130837759), this.recentIndicatorLayout.width, this.recentIndicatorLayout.height, false), this.recentIndicatorLayout.leftMargin, (this.recentItemLayout.height - this.recentIndicatorLayout.height) / 2, new Paint());
  }

  private void drawRecentItem(Canvas paramCanvas)
  {
    drawRecentBg(paramCanvas);
    drawRecentIndicator(paramCanvas);
    drawRecentTitle(paramCanvas);
    drawRecentCloseButton(paramCanvas);
    drawRecentLine(paramCanvas);
  }

  private void drawRecentLine(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, this.recentLineLayout.leftMargin, this.recentItemLayout.width, this.recentItemLayout.height - this.recentLineLayout.height, this.recentLineLayout.height);
  }

  private void drawRecentTitle(Canvas paramCanvas)
  {
    Object localObject2 = "继续收听  “";
    Object localObject1 = localObject2;
    if (this.mNode != null)
    {
      localObject1 = localObject2;
      if (this.mNode.nodeName != null)
      {
        localObject1 = localObject2;
        if (this.mNode.nodeName.equalsIgnoreCase("playhistory"))
          localObject1 = "继续收听  “" + ((ProgramNode)((PlayHistoryNode)this.mNode).playNode).title + "”";
      }
    }
    localObject2 = SkinManager.getInstance().getSubTextPaint();
    localObject1 = TextUtils.ellipsize((CharSequence)localObject1, (TextPaint)localObject2, this.recentTitleLayout.width, TextUtils.TruncateAt.END).toString();
    Rect localRect = new Rect();
    ((TextPaint)localObject2).getTextBounds((String)localObject1, 0, ((String)localObject1).length(), localRect);
    float f = this.recentTitleLayout.leftMargin;
    int i = this.recentTitleLayout.topMargin;
    int j = localRect.top;
    paramCanvas.drawText((String)localObject1, f, (this.recentItemLayout.height - localRect.height()) / 2 + (i - j), (Paint)localObject2);
  }

  private void drawReminder(Canvas paramCanvas, float paramFloat1, float paramFloat2)
  {
    paramCanvas.drawCircle(this.remindLayout.width / 2 + paramFloat1, paramFloat2, this.remindLayout.width / 2, this.mHighlightPaint);
  }

  private void drawStateInfo(Canvas paramCanvas, int paramInt)
  {
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
    {
      drawUpdateTime(paramCanvas, drawStateLabel(paramCanvas, paramInt), drawDurationTime(paramCanvas, paramInt), paramInt);
      drawTriangular(paramCanvas);
    }
  }

  private float drawStateLabel(Canvas paramCanvas, int paramInt)
  {
    int i;
    String str;
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
    {
      i = ((ProgramNode)this.mNode).getCurrPlayStatus();
      switch (i)
      {
      default:
        str = "";
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
      if ((str == null) || (str.equalsIgnoreCase("")))
      {
        return this.itemLayout.leftMargin;
        i = 3;
        break;
        str = "直播";
        this.mStatePaint.setColor(SkinManager.getTextColorHighlight2());
        continue;
        if (InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.isExisted(this.mNode));
        for (str = "已预约"; ; str = "预约")
        {
          this.mStatePaint.setColor(SkinManager.getTextColorHighlight2());
          break;
        }
        return this.itemLayout.leftMargin;
      }
    this.mStatePaint.getTextBounds(str, 0, str.length(), this.textBound);
    paramCanvas.drawText(str, this.itemLayout.leftMargin + this.otherInfoLayout.leftMargin, this.otherInfoLayout.topMargin + paramInt + (this.otherInfoLayout.height - this.textBound.height()), this.mStatePaint);
    return this.itemLayout.leftMargin + this.otherInfoLayout.leftMargin + this.textBound.width();
  }

  private int drawTitle(Canvas paramCanvas)
  {
    if (this.mIsLiving)
      drawLivingLabel(paramCanvas);
    if (this.mRemind)
      drawReminder(paramCanvas, this.itemLayout.leftMargin + this.remindLayout.leftMargin, this.programLayout.topMargin + this.programLayout.height / 2);
    int i = paramCanvas.save();
    paramCanvas.translate(this.programLayout.leftMargin, this.programLayout.topMargin);
    this.mTitleLayout.draw(paramCanvas);
    paramCanvas.restoreToCount(i);
    return this.programLayout.topMargin + this.mTitleHeight;
  }

  private void drawTriangular(Canvas paramCanvas)
  {
    paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getResources(), this.hash, 2130838038), null, this.mTriangularRect, this.mPaint);
  }

  private void drawUpdateTime(Canvas paramCanvas, float paramFloat1, float paramFloat2, int paramInt)
  {
    String str;
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
      if (((ProgramNode)this.mNode).channelType == 1)
        str = getUpdateTimeString((ProgramNode)this.mNode);
    while (true)
      if (str == null)
      {
        return;
        if (((ProgramNode)this.mNode).getCurrPlayStatus() == 3)
          str = TimeUtil.msToDate5(((ProgramNode)this.mNode).getAbsoluteStartTime() * 1000L);
      }
      else
      {
        str = TextUtils.ellipsize(str, this.mUpdateTimePaint, paramFloat2 - paramFloat1, TextUtils.TruncateAt.END).toString();
        this.mUpdateTimePaint.getTextBounds(str, 0, str.length(), this.textBound);
        paramCanvas.drawText(str, this.otherInfoLayout.leftMargin + paramFloat1, this.otherInfoLayout.topMargin + paramInt + (this.otherInfoLayout.height - this.textBound.height()), this.mUpdateTimePaint);
        return;
        str = null;
      }
  }

  private void generateRect()
  {
    this.mTriangularRect.set(this.itemLayout.leftMargin + this.itemLayout.width - this.triangularLayout.leftMargin - this.triangularLayout.width, this.mItemHeight - this.triangularLayout.topMargin - this.triangularLayout.height, this.itemLayout.leftMargin + this.itemLayout.width - this.triangularLayout.leftMargin, this.mItemHeight - this.triangularLayout.topMargin);
    int i = (this.mItemHeight - this.livingLayout.height) / 2;
    this.mLivingRect.set(this.livingLayout.leftMargin, i, this.livingLayout.leftMargin + this.livingLayout.width, this.livingLayout.height + i);
  }

  private String getDurationTime(int paramInt)
  {
    int i = paramInt / 60;
    String str = "" + i + ":";
    paramInt %= 60;
    if (paramInt < 10)
      return str + "0" + paramInt;
    return str + paramInt;
  }

  private int getSelectedIndex()
  {
    if ((this.mNode != null) && (this.mNode.nodeName != null) && (this.mNode.nodeName.equalsIgnoreCase("playhistory")) && (this.mLastMotionX > this.recentItemLayout.width - this.recentCloseButtonLayout.leftMargin - this.recentCloseButtonLayout.width * 2) && (this.mLastMotionY > 0.0F) && (this.mLastMotionY < this.recentItemLayout.height))
      return 2;
    if ((this.mLastMotionX > this.itemLayout.width - this.triangularLayout.leftMargin - this.triangularLayout.width * 2) && (this.mLastMotionY > this.mItemHeight - this.triangularLayout.topMargin - this.triangularLayout.height * 2))
      return 1;
    return 0;
  }

  private int getThisHeight()
  {
    int i;
    if (this.mNode == null)
    {
      i = this.itemLayout.height;
      return i;
    }
    this.mIsLiving = isPlaying();
    this.mDownloadState = InfoManager.getInstance().root().mDownLoadInfoNode.hasDownLoad(this.mNode);
    String str = "";
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
    {
      if (this.mRemind)
        str = "    " + ((ProgramNode)this.mNode).title;
    }
    else
      label102: if (!this.mIsLiving)
        break label238;
    label238: for (TextPaint localTextPaint = SkinManager.getInstance().getmHighlightTextPaint2(); ; localTextPaint = SkinManager.getInstance().getNormalTextPaint())
    {
      this.mTitleLayout = new StaticLayout(str, localTextPaint, this.itemLayout.width - this.programLayout.leftMargin, Layout.Alignment.ALIGN_NORMAL, 1.2F, 0.5F, false);
      this.mTitleHeight = this.mTitleLayout.getHeight();
      int j = this.programLayout.topMargin + this.mTitleHeight + this.otherInfoLayout.topMargin + this.otherInfoLayout.height;
      i = j;
      if (!this.mHasChannelName)
        break;
      return j + (this.channelnameLayout.height + this.channelnameLayout.topMargin);
      str = ((ProgramNode)this.mNode).title;
      break label102;
    }
  }

  private String getTitle()
  {
    if (this.mNode == null)
      return "";
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
      return ((ProgramNode)this.mNode).title;
    if (this.mNode.nodeName.equalsIgnoreCase("channel"))
      return ((ChannelNode)this.mNode).title;
    return "";
  }

  private String getUpdateTimeString(ProgramNode paramProgramNode)
  {
    long l = paramProgramNode.getUpdateTime();
    if (this.mSameDay)
    {
      l = System.currentTimeMillis() - l;
      if (l <= 600000L)
        return "刚刚更新";
      int i = (int)(l / 1000L / 3600L);
      if (i > 0)
        return String.format(Locale.CHINA, "%d小时前", new Object[] { Integer.valueOf(i) });
      i = (int)(l / 1000L / 60L % 60L);
      if (i > 0)
        return String.format(Locale.CHINA, "%d分钟前", new Object[] { Integer.valueOf(i) });
      return "刚刚更新";
    }
    return TimeUtil.msToDate5(l);
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

  private boolean isSameDay()
  {
    if (this.mNode == null)
      return false;
    if ((this.mNode.nodeName.equalsIgnoreCase("program")) && (((ProgramNode)this.mNode).channelType == 1))
    {
      long l = ((ProgramNode)this.mNode).getUpdateTime();
      Calendar localCalendar = Calendar.getInstance();
      int i = localCalendar.get(1);
      int j = localCalendar.get(6);
      localCalendar.setTimeInMillis(l);
      int k = localCalendar.get(1);
      int m = localCalendar.get(6);
      if ((i == k) && (j == m))
        return true;
    }
    return false;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.mNode == null)
      return;
    paramCanvas.setDrawFilter(this.filter);
    paramCanvas.save();
    if (this.mNode.nodeName.equalsIgnoreCase("playhistory"))
      drawRecentItem(paramCanvas);
    while (true)
    {
      paramCanvas.restore();
      return;
      drawNormalItem(paramCanvas);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.programLayout.scaleToBounds(this.itemLayout);
    this.channelnameLayout.scaleToBounds(this.itemLayout);
    this.otherInfoLayout.scaleToBounds(this.itemLayout);
    this.triangularLayout.scaleToBounds(this.itemLayout);
    this.livingLayout.scaleToBounds(this.itemLayout);
    this.remindLayout.scaleToBounds(this.itemLayout);
    this.mChannelNamePaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mStatePaint.setTextSize(SkinManager.getInstance().getTeenyTinyTextSize());
    this.mDownloadStatePaint.setTextSize(SkinManager.getInstance().getTeenyTinyTextSize());
    this.mUpdateTimePaint.setTextSize(SkinManager.getInstance().getTeenyTinyTextSize());
    this.mTimePaint.setTextSize(SkinManager.getInstance().getTeenyTinyTextSize());
    this.mItemHeight = getThisHeight();
    generateRect();
    this.recentItemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.recentIndicatorLayout.scaleToBounds(this.recentItemLayout);
    this.recentTitleLayout.scaleToBounds(this.recentItemLayout);
    this.recentCloseButtonLayout.scaleToBounds(this.recentItemLayout);
    this.recentLineLayout.scaleToBounds(this.recentItemLayout);
    if ((this.mNode != null) && (this.mNode.nodeName.equalsIgnoreCase("playhistory")))
    {
      setMeasuredDimension(this.recentItemLayout.width, this.recentItemLayout.height);
      return;
    }
    setMeasuredDimension(this.itemLayout.width, this.mItemHeight);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((!this.mInTouchMode) && (paramMotionEvent.getAction() != 0));
    do
    {
      do
      {
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
            this.mSelectedIndex = getSelectedIndex();
            invalidate();
            return true;
          case 2:
            this.mLastMotionX = paramMotionEvent.getX();
            this.mLastMotionY = paramMotionEvent.getY();
          case 3:
          case 1:
          }
        }
        while (getSelectedIndex() == this.mSelectedIndex);
        this.mSelectedIndex = -1;
        this.mInTouchMode = false;
      }
      while (!isItemPressed());
      invalidate();
      return true;
      this.mSelectedIndex = -1;
    }
    while (!isItemPressed());
    invalidate();
    return true;
    doClickEvent();
    return true;
  }

  public void setBelongToPodcasterInfo(boolean paramBoolean)
  {
    this.mBelongToPodcasterInfo = paramBoolean;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      if (!(paramObject instanceof Node))
        break label43;
      this.mNode = ((Node)paramObject);
      this.mSameDay = isSameDay();
      this.mHasChannelName = false;
    }
    label43: 
    while (!(paramObject instanceof HashMap))
    {
      requestLayout();
      return;
    }
    paramString = (HashMap)paramObject;
    this.mNode = ((Node)paramString.get("node"));
    if (((this.mNode instanceof ProgramNode)) && (!TextUtils.isEmpty(((ProgramNode)this.mNode).getChannelName())));
    for (this.mHasChannelName = true; ; this.mHasChannelName = false)
    {
      this.mSameDay = isSameDay();
      this.mRemind = ((Boolean)paramString.get("remind")).booleanValue();
      break;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.ChannelDetailItemView
 * JD-Core Version:    0.6.2
 */