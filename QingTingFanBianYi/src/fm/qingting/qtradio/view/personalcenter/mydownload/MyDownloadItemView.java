package fm.qingting.qtradio.view.personalcenter.mydownload;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.downloadnew.DownloadService;
import fm.qingting.downloadnew.DownloadTask;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.tween.FrameTween;
import fm.qingting.framework.tween.FrameTween.SyncType;
import fm.qingting.framework.tween.IMotionHandler;
import fm.qingting.framework.tween.MotionController;
import fm.qingting.framework.tween.TweenProperty;
import fm.qingting.framework.tween.easing.Quad.EaseIn;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.QTApplication;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.Download;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.popviews.AlertParam.Builder;
import fm.qingting.qtradio.view.popviews.AlertParam.OnButtonClickListener;
import fm.qingting.qtradio.view.scheduleview.SizeInfo;
import fm.qingting.utils.TimeUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyDownloadItemView extends QtListItemView
  implements IMotionHandler
{
  private static final String DELETE = "删除";
  private static final String PAUSE = "暂停";
  private static final String RESUME = "继续";
  private static final String RETRY = "重试";
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(30, 22, 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = this.itemLayout.createChildLT(48, 48, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private DrawFilter filter = SkinManager.getInstance().getDrawFilter();
  private int hash = -25;
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private Object mAnimator;
  private Paint mCheckBgPaint = new Paint();
  private Rect mCheckRect = new Rect();
  private Paint mCheckStatePaint = new Paint();
  private boolean mInTouchMode = false;
  private Paint mInfoHighlightPaint = new Paint();
  private boolean mIsChecked = false;
  private float mLastMotionX = 0.0F;
  private float mLastMotionY = 0.0F;
  private Paint mManageHighlightPaint = new Paint();
  private Node mNode;
  private int mOffset = 0;
  private Paint mPaint = new Paint();
  private Paint mPauseHighlightPaint = new Paint();
  private Paint mPausePaint = new Paint();
  private Paint mPauseTextPaint = new Paint();
  private boolean mPaused = false;
  private Paint mProcessBgPaint = new Paint();
  private Paint mProcessingPaint = new Paint();
  private Paint mResumeHighlightPaint = new Paint();
  private Paint mResumePaint = new Paint();
  private Paint mResumeTextPaint = new Paint();
  private int mSelectedIndex = -1;
  private final ViewLayout manageLayout = this.itemLayout.createChildLT(90, 90, 608, 2, ViewLayout.SCALE_FLAG_SLTCW);
  private MotionController motionController;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(450, 45, 30, 20, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout processBgLayout = this.itemLayout.createChildLT(450, 9, 30, 15, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout sizeLayout = this.itemLayout.createChildLT(150, 45, 30, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect textBound = new Rect();
  private final ViewLayout timeLayout = this.itemLayout.createChildLT(150, 45, 30, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout updateLayout = this.itemLayout.createChildLT(150, 45, 450, 10, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);

  public MyDownloadItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.hash = paramInt;
    this.mInfoHighlightPaint.setColor(SkinManager.getTextColorHighlight());
    this.mProcessBgPaint.setColor(-3552823);
    this.mProcessingPaint.setColor(SkinManager.getTextColorHighlight());
    this.mProcessBgPaint.setStyle(Paint.Style.FILL);
    this.mProcessingPaint.setStyle(Paint.Style.FILL);
    this.mResumePaint.setColor(SkinManager.getTextColorHighlight());
    this.mResumePaint.setStyle(Paint.Style.STROKE);
    this.mResumeHighlightPaint.setColor(SkinManager.getTextColorHighlight());
    this.mResumeHighlightPaint.setStyle(Paint.Style.FILL);
    this.mManageHighlightPaint.setColor(SkinManager.getBackgroundColor());
    this.mResumeTextPaint.setColor(SkinManager.getTextColorHighlight());
    this.mPausePaint.setColor(-9408400);
    this.mPausePaint.setStyle(Paint.Style.STROKE);
    this.mPauseHighlightPaint.setColor(-9408400);
    this.mPauseHighlightPaint.setStyle(Paint.Style.FILL);
    this.mPauseTextPaint.setColor(-9408400);
    this.mCheckBgPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mCheckStatePaint.setColor(SkinManager.getTextColorHighlight());
    this.mCheckBgPaint.setStyle(Paint.Style.STROKE);
    this.mCheckStatePaint.setStyle(Paint.Style.FILL);
    setItemSelectedEnable();
    init();
  }

  private void drawBg(Canvas paramCanvas)
  {
    if ((isItemPressed()) && (this.mSelectedIndex == 0) && (this.mOffset == 0))
      return;
    int i = paramCanvas.save();
    paramCanvas.clipRect(0, 0, this.itemLayout.width, this.itemLayout.height);
    paramCanvas.drawColor(SkinManager.getCardColor());
    paramCanvas.restoreToCount(i);
  }

  private void drawCheck(Canvas paramCanvas)
  {
    if (this.mOffset > 0)
    {
      this.mCheckRect.offset(this.mOffset, 0);
      if (!this.mIsChecked)
        break label104;
      paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2, this.mCheckStatePaint);
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getResources(), this.hash, 2130837754), null, this.mCheckRect, this.mPaint);
    }
    while (true)
    {
      this.mCheckRect.offset(-this.mOffset, 0);
      return;
      label104: paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2, this.mCheckBgPaint);
    }
  }

  private void drawDeleteButton(Canvas paramCanvas, boolean paramBoolean)
  {
    TextPaint localTextPaint = SkinManager.getInstance().getSubTextPaint();
    float f1 = this.mOffset + this.manageLayout.leftMargin + this.manageLayout.width / 2;
    float f2 = this.itemLayout.height / 2;
    float f3 = this.manageLayout.width / 2;
    if (paramBoolean)
    {
      localPaint = this.mPauseHighlightPaint;
      paramCanvas.drawCircle(f1, f2, f3, localPaint);
      localTextPaint.getTextBounds("删除", 0, "删除".length(), this.textBound);
      f1 = this.mOffset + this.manageLayout.leftMargin + this.manageLayout.width / 2 - this.textBound.width() / 2;
      f2 = (this.itemLayout.height - this.textBound.top - this.textBound.bottom) / 2;
      if (!paramBoolean)
        break label187;
    }
    label187: for (Paint localPaint = this.mManageHighlightPaint; ; localPaint = this.mPauseTextPaint)
    {
      paramCanvas.drawText("删除", f1, f2, localPaint);
      return;
      localPaint = this.mPausePaint;
      break;
    }
  }

  private void drawInfo(Canvas paramCanvas, DownloadState paramDownloadState)
  {
    int i = 1;
    int k = 0;
    TextPaint localTextPaint = SkinManager.getInstance().getSubTextPaint();
    String str;
    if ((paramDownloadState == DownloadState.downloading) || (paramDownloadState == DownloadState.pausing) || (paramDownloadState == DownloadState.waiting))
    {
      str = getStateLable(paramDownloadState);
      if ((str != null) && (!str.equalsIgnoreCase("")));
    }
    label167: label358: label364: 
    do
    {
      return;
      float f1;
      int m;
      int n;
      int i1;
      int i2;
      int j;
      float f2;
      if (paramDownloadState == DownloadState.downloading)
      {
        this.mInfoHighlightPaint.getTextBounds(str, 0, str.length(), this.textBound);
        f1 = this.mOffset + this.timeLayout.leftMargin;
        m = this.nameLayout.topMargin;
        n = this.nameLayout.height;
        i1 = this.timeLayout.topMargin;
        i2 = (this.timeLayout.height - this.textBound.top - this.textBound.bottom) / 2;
        if (i == 0)
          break label358;
        j = this.timeLayout.topMargin;
        f2 = i2 + (m + n + i1) - j;
        if ((i == 0) && (paramDownloadState != DownloadState.notstarted))
          break label364;
      }
      for (Object localObject = this.mInfoHighlightPaint; ; localObject = localTextPaint)
      {
        paramCanvas.drawText(str, f1, f2, (Paint)localObject);
        paramDownloadState = getProcessInfo(paramDownloadState);
        localTextPaint.getTextBounds(paramDownloadState, 0, paramDownloadState.length(), this.textBound);
        f1 = this.mOffset + this.sizeLayout.getRight() + this.timeLayout.leftMargin;
        m = this.nameLayout.topMargin;
        n = this.nameLayout.height;
        i1 = this.timeLayout.topMargin;
        i2 = (this.timeLayout.height - this.textBound.top - this.textBound.bottom) / 2;
        j = k;
        if (i != 0)
          j = this.timeLayout.topMargin;
        paramCanvas.drawText(paramDownloadState, f1, m + n + i1 + i2 - j, localTextPaint);
        return;
        i = 0;
        break;
        j = 0;
        break label167;
      }
      if (this.mNode.nodeName.equalsIgnoreCase("program"))
      {
        paramDownloadState = getDurationTime(((ProgramNode)this.mNode).getDuration());
        localTextPaint.getTextBounds(paramDownloadState, 0, paramDownloadState.length(), this.textBound);
        paramCanvas.drawText(paramDownloadState, this.mOffset + this.timeLayout.leftMargin, this.nameLayout.topMargin + this.nameLayout.height + this.timeLayout.topMargin + (this.timeLayout.height - this.textBound.top - this.textBound.bottom) / 2, localTextPaint);
        paramDownloadState = getFileSize((ProgramNode)this.mNode);
        localTextPaint.getTextBounds(paramDownloadState, 0, paramDownloadState.length(), this.textBound);
        paramCanvas.drawText(paramDownloadState, this.mOffset + this.timeLayout.getRight() + this.sizeLayout.leftMargin, this.nameLayout.topMargin + this.nameLayout.height + this.timeLayout.topMargin + (this.timeLayout.height - this.textBound.top - this.textBound.bottom) / 2, localTextPaint);
        if (((ProgramNode)this.mNode).downloadInfo == null);
        for (long l = ((ProgramNode)this.mNode).getUpdateTime(); ; l = ((ProgramNode)this.mNode).downloadInfo.updateTime)
        {
          paramDownloadState = TimeUtil.msToDate2(l * 1000L);
          localTextPaint.getTextBounds(paramDownloadState, 0, paramDownloadState.length(), this.textBound);
          paramCanvas.drawText(paramDownloadState, this.mOffset + this.updateLayout.leftMargin, this.nameLayout.topMargin + this.nameLayout.height + this.timeLayout.topMargin + (this.updateLayout.height - this.textBound.top - this.textBound.bottom) / 2, localTextPaint);
          return;
        }
      }
    }
    while (!this.mNode.nodeName.equalsIgnoreCase("channel"));
    paramDownloadState = String.format(Locale.CHINA, "%d个文件", new Object[] { Integer.valueOf(((ChannelNode)this.mNode).programCnt) });
    localTextPaint.getTextBounds(paramDownloadState, 0, paramDownloadState.length(), this.textBound);
    paramCanvas.drawText(paramDownloadState, this.mOffset + this.timeLayout.leftMargin, this.nameLayout.topMargin + this.nameLayout.height + this.timeLayout.topMargin + (this.timeLayout.height - this.textBound.top - this.textBound.bottom) / 2, localTextPaint);
    paramDownloadState = String.valueOf(System.currentTimeMillis() / 1000L);
    localTextPaint.getTextBounds(paramDownloadState, 0, paramDownloadState.length(), this.textBound);
    paramCanvas.drawText(paramDownloadState, this.mOffset + this.updateLayout.leftMargin, this.nameLayout.topMargin + this.nameLayout.height + this.updateLayout.topMargin + (this.updateLayout.height - this.textBound.top - this.textBound.bottom) / 2, localTextPaint);
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager localSkinManager = SkinManager.getInstance();
    int i = this.mOffset;
    localSkinManager.drawHorizontalLine(paramCanvas, this.lineLayout.leftMargin + i, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  private void drawManage(Canvas paramCanvas, DownloadState paramDownloadState, boolean paramBoolean)
  {
    TextPaint localTextPaint = SkinManager.getInstance().getSubTextPaint();
    String str2 = "继续";
    int j = 1;
    int i = j;
    String str1 = str2;
    float f1;
    float f2;
    switch (3.$SwitchMap$fm$qingting$qtradio$view$personalcenter$mydownload$DownloadState[paramDownloadState.ordinal()])
    {
    default:
      str1 = str2;
      i = j;
    case 2:
    case 3:
      f1 = this.mOffset + this.manageLayout.leftMargin + this.manageLayout.width / 2;
      f2 = this.itemLayout.height / 2;
      float f3 = this.manageLayout.width / 2;
      if (i != 0)
        if (paramBoolean)
        {
          paramDownloadState = this.mResumeHighlightPaint;
          label135: paramCanvas.drawCircle(f1, f2, f3, paramDownloadState);
          localTextPaint.getTextBounds(str1, 0, str1.length(), this.textBound);
          f1 = this.mOffset + this.manageLayout.leftMargin + this.manageLayout.width / 2 - this.textBound.width() / 2;
          f2 = (this.itemLayout.height - this.textBound.top - this.textBound.bottom) / 2;
          if (!paramBoolean)
            break label306;
          paramDownloadState = this.mManageHighlightPaint;
        }
      break;
    case 4:
    case 1:
    case 5:
    }
    while (true)
    {
      paramCanvas.drawText(str1, f1, f2, paramDownloadState);
      return;
      str1 = "暂停";
      i = 0;
      break;
      str1 = "暂停";
      i = 0;
      break;
      str1 = "重试";
      i = j;
      break;
      paramDownloadState = this.mResumePaint;
      break label135;
      if (paramBoolean)
      {
        paramDownloadState = this.mPauseHighlightPaint;
        break label135;
      }
      paramDownloadState = this.mPausePaint;
      break label135;
      label306: if (i != 0)
        paramDownloadState = this.mResumeTextPaint;
      else
        paramDownloadState = this.mPauseTextPaint;
    }
  }

  private void drawProcessBar(Canvas paramCanvas, DownloadState paramDownloadState)
  {
    if (paramDownloadState != DownloadState.downloading);
    int i;
    do
    {
      return;
      paramCanvas.drawRect(this.mOffset + this.processBgLayout.leftMargin, this.itemLayout.height - this.processBgLayout.topMargin - this.processBgLayout.height, this.mOffset + this.processBgLayout.leftMargin + this.processBgLayout.width, this.itemLayout.height - this.processBgLayout.topMargin, this.mProcessBgPaint);
      i = getPercent(paramDownloadState);
    }
    while (i == 0);
    float f1 = this.mOffset + this.processBgLayout.leftMargin;
    float f2 = this.itemLayout.height - this.processBgLayout.topMargin - this.processBgLayout.height;
    int j = this.mOffset;
    int k = this.processBgLayout.leftMargin;
    paramCanvas.drawRect(f1, f2, i * this.processBgLayout.width / 100 + (j + k), this.itemLayout.height - this.processBgLayout.topMargin, this.mProcessingPaint);
  }

  private void drawTitle(Canvas paramCanvas, boolean paramBoolean)
  {
    Object localObject2 = getName();
    Object localObject1 = localObject2;
    if (localObject2 == null)
      localObject1 = "";
    localObject2 = SkinManager.getInstance().getNormalTextPaint();
    if (paramBoolean);
    for (float f = this.manageLayout.leftMargin - this.nameLayout.leftMargin; ; f = this.itemLayout.width - this.nameLayout.leftMargin)
    {
      localObject1 = TextUtils.ellipsize((CharSequence)localObject1, (TextPaint)localObject2, f, TextUtils.TruncateAt.END).toString();
      ((TextPaint)localObject2).getTextBounds((String)localObject1, 0, ((String)localObject1).length(), this.textBound);
      paramCanvas.drawText((String)localObject1, this.mOffset + this.nameLayout.leftMargin, this.nameLayout.topMargin + (this.nameLayout.height - this.textBound.top - this.textBound.bottom) / 2, (Paint)localObject2);
      return;
    }
  }

  private void generateRect()
  {
    this.mCheckRect.set((-this.checkbgLayout.width - this.checkStateLayout.width) / 2, (this.itemLayout.height - this.checkStateLayout.height) / 2, (-this.checkbgLayout.width + this.checkStateLayout.width) / 2, (this.itemLayout.height + this.checkStateLayout.height) / 2);
  }

  private String getDurationTime(int paramInt)
  {
    int i = paramInt / 3600;
    int j = paramInt / 60 % 60;
    if (i == 0)
    {
      if (j == 0)
        return String.format(Locale.CHINA, "时长%d秒", new Object[] { Integer.valueOf(paramInt % 60) });
      return String.format(Locale.CHINA, "时长%d分钟", new Object[] { Integer.valueOf(j) });
    }
    if (j == 0)
      return String.format(Locale.CHINA, "时长%d小时", new Object[] { Integer.valueOf(i) });
    return String.format(Locale.CHINA, "时长:%d小时%d分", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
  }

  private String getFileSize(ProgramNode paramProgramNode)
  {
    if (paramProgramNode.downloadInfo == null);
    for (int i = paramProgramNode.getDuration() * 24 * 125; i < 0; i = paramProgramNode.downloadInfo.fileSize)
      return "";
    if (i < 1000)
      return i + "B";
    if (i < 1000000)
      return String.format("%.1fkB", new Object[] { Float.valueOf(i / 1000.0F) });
    if (i < 1000000000)
      return String.format("%.1fMB", new Object[] { Float.valueOf(i / 1000000.0F) });
    if (i < 1000000000000L)
      return String.format("%.1fG", new Object[] { Float.valueOf(i / 1.0E+009F) });
    return "";
  }

  private int getMaxOffset()
  {
    return this.checkbgLayout.leftMargin + this.checkbgLayout.width;
  }

  private String getName()
  {
    if (this.mNode.nodeName.equalsIgnoreCase("program"))
      return ((ProgramNode)this.mNode).title;
    if (this.mNode.nodeName.equalsIgnoreCase("channel"))
      return ((ChannelNode)this.mNode).title;
    return "";
  }

  private int getPercent(DownloadState paramDownloadState)
  {
    if (this.mNode == null);
    while ((!this.mNode.nodeName.equalsIgnoreCase("program")) || (((ProgramNode)this.mNode).downloadInfo == null))
      return 0;
    int j = ((ProgramNode)this.mNode).downloadInfo.progress;
    int i = j;
    if (j > 100)
      i = 100;
    j = i;
    if (i < 0)
      j = 0;
    return j;
  }

  private String getProcessInfo(DownloadState paramDownloadState)
  {
    if (this.mNode == null)
      return "";
    if (!this.mNode.nodeName.equalsIgnoreCase("program"))
      return "";
    int j = getPercent(paramDownloadState);
    if (((ProgramNode)this.mNode).downloadInfo == null);
    for (int i = ((ProgramNode)this.mNode).getDuration() * 24 * 125; ; i = ((ProgramNode)this.mNode).downloadInfo.fileSize)
    {
      j = (int)(i * (j / 100.0D));
      return String.format(Locale.US, "%s/%s", new Object[] { SizeInfo.getFileSize(j), SizeInfo.getFileSize(i) });
    }
  }

  private int getSelectedIndex()
  {
    if ((this.mOffset == 0) && (pointInManage(this.mLastMotionX, this.mLastMotionY)))
    {
      if (this.mNode.nodeName.equalsIgnoreCase("channel"));
      while (!isDownloading())
        return 1;
      return 1;
    }
    return 0;
  }

  private DownloadState getState()
  {
    DownloadState localDownloadState = DownloadState.none;
    if ((this.mNode == null) || (!this.mNode.nodeName.equalsIgnoreCase("program")))
      return localDownloadState;
    if (((ProgramNode)this.mNode).downloadInfo == null)
      return DownloadState.notstarted;
    if (this.mPaused)
      return DownloadState.pausing;
    switch (((ProgramNode)this.mNode).downloadInfo.state)
    {
    default:
      return localDownloadState;
    case 3:
      return DownloadState.completed;
    case 1:
      return DownloadState.downloading;
    case 2:
      localDownloadState = DownloadState.pausing;
      this.mPaused = true;
      return localDownloadState;
    case 4:
      return DownloadState.error;
    case 0:
    }
    return DownloadState.waiting;
  }

  private String getStateLable(DownloadState paramDownloadState)
  {
    switch (3.$SwitchMap$fm$qingting$qtradio$view$personalcenter$mydownload$DownloadState[paramDownloadState.ordinal()])
    {
    default:
      return null;
    case 1:
      return "正在下载";
    case 2:
      return "已暂停";
    case 3:
      return "等待下载";
    case 4:
    }
    return "等待中";
  }

  @TargetApi(11)
  private void init()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      this.mAnimator = new ValueAnimator();
      ((ValueAnimator)this.mAnimator).setDuration(200L);
      ((ValueAnimator)this.mAnimator).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          MyDownloadItemView.this.setPosition(((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
        }
      });
      return;
    }
    this.motionController = new MotionController(this);
  }

  private boolean isDownloading()
  {
    DownloadState localDownloadState = getState();
    return (localDownloadState == DownloadState.downloading) || (localDownloadState == DownloadState.pausing);
  }

  private boolean pointInManage(float paramFloat1, float paramFloat2)
  {
    return (paramFloat1 > this.manageLayout.leftMargin) && (paramFloat1 < this.manageLayout.getRight()) && (paramFloat2 > (this.itemLayout.height - this.manageLayout.height) / 2) && (paramFloat2 < (this.itemLayout.height + this.manageLayout.height) / 2);
  }

  private void setPosition(float paramFloat)
  {
    this.mOffset = ((int)paramFloat);
    invalidate();
  }

  @TargetApi(11)
  private void startHideManageAnimation()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      ((ValueAnimator)this.mAnimator).setFloatValues(new float[] { getMaxOffset(), 0.0F });
      ((ValueAnimator)this.mAnimator).start();
      return;
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new TweenProperty("position", getMaxOffset(), 0.0F, 10.0F, new Quad.EaseIn()));
    FrameTween.to(this.motionController, this, localArrayList, FrameTween.SyncType.ASYNC);
  }

  @TargetApi(11)
  private void startShowManageAnimation(int paramInt)
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      ((ValueAnimator)this.mAnimator).setFloatValues(new float[] { 0.0F, paramInt });
      ((ValueAnimator)this.mAnimator).start();
      return;
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new TweenProperty("position", 0.0F, paramInt, 10.0F, new Quad.EaseIn()));
    FrameTween.to(this.motionController, this, localArrayList, FrameTween.SyncType.ASYNC);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    boolean bool3 = true;
    boolean bool2 = true;
    if (this.mNode == null)
      return;
    paramCanvas.setDrawFilter(this.filter);
    paramCanvas.save();
    drawBg(paramCanvas);
    DownloadState localDownloadState = getState();
    if ((localDownloadState == DownloadState.downloading) || (localDownloadState == DownloadState.pausing) || (localDownloadState == DownloadState.none) || (localDownloadState == DownloadState.waiting))
    {
      bool1 = true;
      drawTitle(paramCanvas, bool1);
      if ((localDownloadState != DownloadState.downloading) && (localDownloadState != DownloadState.pausing) && (localDownloadState != DownloadState.waiting))
        break label156;
      drawProcessBar(paramCanvas, localDownloadState);
      if ((!isItemPressed()) || (this.mSelectedIndex != 1))
        break label151;
      bool1 = bool2;
      drawManage(paramCanvas, localDownloadState, bool1);
    }
    label151: label156: 
    while (localDownloadState != DownloadState.none)
      while (true)
      {
        drawInfo(paramCanvas, localDownloadState);
        drawCheck(paramCanvas);
        drawLine(paramCanvas);
        paramCanvas.restore();
        return;
        bool1 = false;
        break;
        bool1 = false;
      }
    if ((isItemPressed()) && (this.mSelectedIndex == 1));
    for (boolean bool1 = bool3; ; bool1 = false)
    {
      drawDeleteButton(paramCanvas, bool1);
      break;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.updateLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.sizeLayout.scaleToBounds(this.itemLayout);
    this.timeLayout.scaleToBounds(this.itemLayout);
    this.checkbgLayout.scaleToBounds(this.itemLayout);
    this.checkStateLayout.scaleToBounds(this.checkbgLayout);
    this.processBgLayout.scaleToBounds(this.itemLayout);
    this.manageLayout.scaleToBounds(this.itemLayout);
    this.mInfoHighlightPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mCheckBgPaint.setStrokeWidth(this.checkStateLayout.leftMargin);
    generateRect();
    this.mPausePaint.setStrokeWidth(this.manageLayout.topMargin);
    this.mResumePaint.setStrokeWidth(this.manageLayout.topMargin);
    this.mPauseTextPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mResumeTextPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mManageHighlightPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void onMotionCancel(MotionController paramMotionController)
  {
  }

  public void onMotionComplete(MotionController paramMotionController)
  {
  }

  public void onMotionProgress(MotionController paramMotionController, float paramFloat1, float paramFloat2)
  {
    setPosition(paramFloat1);
  }

  public void onMotionStart(MotionController paramMotionController)
  {
  }

  public void onTargetChange(MotionController paramMotionController, float paramFloat)
  {
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
    if (this.mSelectedIndex == 0)
      if (this.mOffset > 0)
        dispatchActionEvent("itemSelect", null);
    while (true)
    {
      invalidate();
      return true;
      if (this.mNode != null)
        if (this.mNode.nodeName.equalsIgnoreCase("program"))
        {
          if ((InfoManager.getInstance().root().mDownLoadInfoNode.hasInDownLoading(this.mNode) < 0) && (InfoManager.getInstance().root().mDownLoadInfoNode.hasNodeDownloaded(this.mNode)))
          {
            PlayerAgent.getInstance().addPlaySource(6);
            ControllerManager.getInstance().redirectToPlayViewByNode(this.mNode, true);
          }
        }
        else if (this.mNode.nodeName.equalsIgnoreCase("channel"))
        {
          ControllerManager.getInstance().redirectToDownloadProgramsController((ChannelNode)this.mNode);
          continue;
          if ((this.mSelectedIndex == 1) && (this.mNode != null))
          {
            paramMotionEvent = getState();
            if (paramMotionEvent == DownloadState.pausing)
            {
              this.mPaused = false;
              InfoManager.getInstance().root().mDownLoadInfoNode.resumeDownLoad(this.mNode);
            }
            else if ((paramMotionEvent == DownloadState.downloading) || (paramMotionEvent == DownloadState.waiting))
            {
              this.mPaused = true;
              InfoManager.getInstance().root().mDownLoadInfoNode.pauseDownLoad(this.mNode, true);
            }
            else if (this.mNode.nodeName.equalsIgnoreCase("channel"))
            {
              paramMotionEvent = new AlertParam.Builder();
              paramMotionEvent.setMessage("确定删除" + ((ChannelNode)this.mNode).title + "及其中的所有节目吗？").addButton("取消").addButton("确定").setListener(new AlertParam.OnButtonClickListener()
              {
                public void onClick(int paramAnonymousInt, boolean paramAnonymousBoolean)
                {
                  if (paramAnonymousInt == 1)
                    InfoManager.getInstance().root().mDownLoadInfoNode.delDownLoad((ChannelNode)MyDownloadItemView.this.mNode, true);
                  MyDownloadItemView.this.dispatchActionEvent("cancelPop", null);
                }
              });
              paramMotionEvent = paramMotionEvent.create();
              EventDispacthManager.getInstance().dispatchAction("showAlert", paramMotionEvent);
            }
          }
        }
    }
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
      if (paramObject != null);
    do
    {
      int i;
      do
      {
        do
        {
          return;
          this.mNode = ((Node)paramObject);
          if (this.mNode.nodeName.equalsIgnoreCase("program"))
            if (((ProgramNode)this.mNode).downloadInfo != null)
            {
              paramString = ((ProgramNode)this.mNode).downloadInfo.id;
              paramString = DownloadService.getInstance(QTApplication.appContext).getTask(paramString);
              if ((paramString != null) && (paramString.getDownloadState() == fm.qingting.downloadnew.DownloadState.PAUSED))
                this.mPaused = true;
            }
          while (true)
          {
            invalidate();
            return;
            this.mPaused = false;
            continue;
            this.mPaused = false;
            continue;
            this.mPaused = false;
          }
          if (paramString.equalsIgnoreCase("checkState"))
          {
            this.mIsChecked = ((Boolean)paramObject).booleanValue();
            invalidate();
            return;
          }
          if (!paramString.equalsIgnoreCase("showManage"))
            break;
        }
        while (this.mOffset > 0);
        startShowManageAnimation(((Integer)paramObject).intValue());
        return;
        if (!paramString.equalsIgnoreCase("showManageWithoutShift"))
          break;
        i = ((Integer)paramObject).intValue();
      }
      while (this.mOffset == i);
      this.mOffset = i;
      invalidate();
      return;
    }
    while ((!paramString.equalsIgnoreCase("hideManage")) || (this.mOffset == 0));
    startHideManageAnimation();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mydownload.MyDownloadItemView
 * JD-Core Version:    0.6.2
 */