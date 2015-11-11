package fm.qingting.qtradio.view.personalcenter.mycollection;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
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
import fm.qingting.framework.tween.FrameTween;
import fm.qingting.framework.tween.FrameTween.SyncType;
import fm.qingting.framework.tween.IMotionHandler;
import fm.qingting.framework.tween.MotionController;
import fm.qingting.framework.tween.TweenProperty;
import fm.qingting.framework.tween.easing.Quad.EaseIn;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PlayingProgramInfoNode;
import fm.qingting.qtradio.model.PlayingProgramNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;
import java.util.List;

public class MyCollectionItemView extends QtListItemView
  implements IMotionHandler
{
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(30, 22, 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = this.itemLayout.createChildLT(48, 48, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout downloadLayout = this.itemLayout.createChildLT(80, 80, 600, 28, ViewLayout.SCALE_FLAG_SLTCW);
  private DrawFilter filter = SkinManager.getInstance().getDrawFilter();
  private int hash = -25;
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(600, 45, 30, 10, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private Object mAnimator;
  private boolean mCanDownload = false;
  private final Paint mCheckBgPaint = new Paint();
  private final Rect mCheckRect = new Rect();
  private final Paint mCheckStatePaint = new Paint();
  private final Rect mDownloadRect = new Rect();
  private boolean mInTouchMode = false;
  private boolean mIsChecked = false;
  private float mLastMotionX;
  private float mLastMotionY;
  private Node mNode;
  private int mOffset = 0;
  private final Paint mPaint = new Paint();
  private final Paint mReminderPaint = new Paint();
  private int mSelectedIndex = -1;
  private MotionController motionController;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(600, 45, 30, 20, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout remindLayout = this.itemLayout.createChildLT(22, 22, 15, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final Rect textBound = new Rect();

  public MyCollectionItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.hash = paramInt;
    this.mReminderPaint.setColor(-59877);
    this.mReminderPaint.setStyle(Paint.Style.FILL);
    this.mCheckBgPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mCheckStatePaint.setColor(SkinManager.getTextColorHighlight());
    this.mCheckBgPaint.setStyle(Paint.Style.STROKE);
    this.mCheckStatePaint.setStyle(Paint.Style.FILL);
    setItemSelectedEnable();
    init();
  }

  private void drawBg(Canvas paramCanvas)
  {
    if ((isItemPressed()) && (this.mSelectedIndex == 0))
      return;
    int i = paramCanvas.save();
    paramCanvas.clipRect(0, 0, this.itemLayout.width, this.itemLayout.height);
    paramCanvas.drawColor(SkinManager.getCardColor());
    paramCanvas.restoreToCount(i);
  }

  private void drawCheckState(Canvas paramCanvas)
  {
    if (this.mOffset > 0)
    {
      this.mCheckRect.offset(this.mOffset, 0);
      if (!this.mIsChecked)
        break label103;
      paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2, this.mCheckStatePaint);
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getResources(), this.hash, 2130837754), null, this.mCheckRect, this.mPaint);
    }
    while (true)
    {
      this.mCheckRect.offset(-this.mOffset, 0);
      return;
      label103: paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2, this.mCheckBgPaint);
    }
  }

  private void drawInfo(Canvas paramCanvas)
  {
    Object localObject2 = getName();
    if ((this.mNode.nodeName.equalsIgnoreCase("channel")) && (((ChannelNode)this.mNode).channelType == 1))
      this.mCanDownload = false;
    float f1;
    Object localObject1;
    float f2;
    label153: int j;
    if ((this.mOffset <= 0) || (this.mCanDownload))
    {
      f1 = this.mDownloadRect.left + this.mOffset;
      if (localObject2 != null)
      {
        if (0 != 0)
          drawReminder(paramCanvas, this.mOffset + this.nameLayout.leftMargin, this.nameLayout.topMargin + this.nameLayout.height / 2);
        localObject1 = SkinManager.getInstance().getNormalTextPaint();
        if (0 == 0)
          break label538;
        f2 = f1 - this.remindLayout.width - this.remindLayout.leftMargin * 2 - this.mOffset;
        localObject2 = TextUtils.ellipsize((CharSequence)localObject2, (TextPaint)localObject1, f2, TextUtils.TruncateAt.END).toString();
        ((TextPaint)localObject1).getTextBounds((String)localObject2, 0, ((String)localObject2).length(), this.textBound);
        j = this.mOffset;
        int k = this.nameLayout.leftMargin;
        if (0 == 0)
          break label558;
        i = this.remindLayout.leftMargin + this.remindLayout.width;
        label224: paramCanvas.drawText((String)localObject2, i + (k + j), this.nameLayout.topMargin + (this.nameLayout.height - this.textBound.top - this.textBound.bottom) / 2, (Paint)localObject1);
      }
      localObject2 = getInfo();
      if (localObject2 != null)
      {
        localObject1 = SkinManager.getInstance().getSubTextPaint();
        localObject2 = TextUtils.ellipsize((CharSequence)localObject2, (TextPaint)localObject1, f1 - this.infoLayout.leftMargin - this.remindLayout.leftMargin - this.mOffset, TextUtils.TruncateAt.END).toString();
        ((TextPaint)localObject1).getTextBounds((String)localObject2, 0, ((String)localObject2).length(), this.textBound);
        paramCanvas.drawText((String)localObject2, this.mOffset + this.infoLayout.leftMargin, this.nameLayout.topMargin + this.nameLayout.height + this.infoLayout.topMargin + (this.infoLayout.height - this.textBound.top - this.textBound.bottom) / 2, (Paint)localObject1);
      }
      if (this.mCanDownload)
      {
        this.mDownloadRect.offset(this.mOffset, 0);
        localObject1 = BitmapResourceCache.getInstance();
        localObject2 = getResources();
        j = this.hash;
        if ((this.mSelectedIndex != 2) || (!isItemPressed()))
          break label564;
      }
    }
    label538: label558: label564: for (int i = 2130837727; ; i = 2130837726)
    {
      paramCanvas.drawBitmap(((BitmapResourceCache)localObject1).getResourceCacheByParent((Resources)localObject2, j, i), null, this.mDownloadRect, this.mPaint);
      this.mDownloadRect.offset(-this.mOffset, 0);
      return;
      f1 = this.itemLayout.width - this.nameLayout.leftMargin;
      break;
      f2 = f1 - this.remindLayout.leftMargin - this.mOffset;
      break label153;
      i = 0;
      break label224;
    }
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager localSkinManager = SkinManager.getInstance();
    int i = this.mOffset;
    localSkinManager.drawHorizontalLine(paramCanvas, this.lineLayout.leftMargin + i, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  private void drawReminder(Canvas paramCanvas, float paramFloat1, float paramFloat2)
  {
    paramCanvas.drawCircle(this.remindLayout.width / 2 + paramFloat1, paramFloat2, this.remindLayout.width / 2, this.mReminderPaint);
  }

  private void generateRect()
  {
    this.mCheckRect.set((-this.checkbgLayout.width - this.checkStateLayout.width) / 2, (this.itemLayout.height - this.checkStateLayout.height) / 2, (-this.checkbgLayout.width + this.checkStateLayout.width) / 2, (this.itemLayout.height + this.checkStateLayout.height) / 2);
    this.mDownloadRect.set(this.downloadLayout.leftMargin, this.downloadLayout.topMargin, this.downloadLayout.getRight(), this.downloadLayout.getBottom());
  }

  private String getInfo()
  {
    Object localObject;
    if (this.mNode.nodeName.equalsIgnoreCase("channel"))
    {
      if (((ChannelNode)this.mNode).isNovelChannel())
      {
        if (((ChannelNode)this.mNode).desc == null)
          return "";
        return ((ChannelNode)this.mNode).desc;
      }
      ChannelNode localChannelNode = (ChannelNode)this.mNode;
      if (localChannelNode.isLiveChannel())
      {
        localObject = localChannelNode.getProgramNodeByTime(System.currentTimeMillis());
        if (localObject != null)
          localObject = "正在直播:" + ((ProgramNode)localObject).title;
      }
      while ((localObject == null) || (((String)localObject).equalsIgnoreCase("")))
      {
        return localChannelNode.title;
        localObject = InfoManager.getInstance().root().mPlayingProgramInfo.getCurrentPlayingProgram(this.mNode);
        if ((localObject != null) && (((Node)localObject).nodeName.equalsIgnoreCase("playingprogram")))
          return "正在直播 ：" + ((PlayingProgramNode)localObject).programName;
        InfoManager.getInstance().loadCurrentPlayingPrograms(String.valueOf(((ChannelNode)this.mNode).channelId), null);
        localObject = null;
        continue;
        if ((localChannelNode.latest_program != null) && (!localChannelNode.latest_program.equalsIgnoreCase("")));
        localObject = "最新节目: " + localChannelNode.latest_program;
      }
    }
    return "";
    return localObject;
  }

  private int getMaxOffset()
  {
    return this.checkbgLayout.leftMargin + this.checkbgLayout.width;
  }

  private String getName()
  {
    if (this.mNode.nodeName.equalsIgnoreCase("channel"))
      return ((ChannelNode)this.mNode).title;
    return "";
  }

  private int getSelectedIndex()
  {
    if (this.mOffset > 0)
      return 1;
    if ((this.mCanDownload) && (this.mDownloadRect.contains((int)this.mLastMotionX, (int)this.mLastMotionY)))
      return 2;
    return 0;
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
          MyCollectionItemView.this.setPosition(((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
        }
      });
      return;
    }
    this.motionController = new MotionController(this);
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
    if (this.mNode == null)
      return;
    paramCanvas.setDrawFilter(this.filter);
    paramCanvas.save();
    drawBg(paramCanvas);
    drawInfo(paramCanvas);
    drawCheckState(paramCanvas);
    drawLine(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.remindLayout.scaleToBounds(this.itemLayout);
    this.checkbgLayout.scaleToBounds(this.itemLayout);
    this.checkStateLayout.scaleToBounds(this.checkbgLayout);
    this.mCheckBgPaint.setStrokeWidth(this.checkStateLayout.leftMargin);
    this.downloadLayout.scaleToBounds(this.itemLayout);
    generateRect();
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
    if ((paramMotionEvent.getAction() != 0) && (!this.mInTouchMode));
    do
    {
      int i;
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
            this.mInTouchMode = true;
            this.mLastMotionX = paramMotionEvent.getX();
            this.mLastMotionY = paramMotionEvent.getY();
            this.mSelectedIndex = getSelectedIndex();
          case 2:
          case 1:
          case 3:
          }
        }
        while ((this.mSelectedIndex <= -1) || (!isItemPressed()));
        invalidate();
        return true;
        this.mLastMotionX = paramMotionEvent.getX();
        this.mLastMotionY = paramMotionEvent.getY();
        i = getSelectedIndex();
      }
      while ((this.mSelectedIndex <= -1) || (this.mSelectedIndex == i));
      this.mSelectedIndex = -1;
      this.mInTouchMode = false;
      invalidate();
      return true;
      if (this.mSelectedIndex > -1)
        switch (this.mSelectedIndex)
        {
        default:
        case 0:
        case 1:
        case 2:
        }
      while (true)
      {
        this.mInTouchMode = false;
        this.mSelectedIndex = -1;
        return true;
        if ((this.mNode != null) && (this.mNode.nodeName.equalsIgnoreCase("channel")))
        {
          PlayerAgent.getInstance().addPlaySource(4);
          if (((ChannelNode)this.mNode).isNovelChannel())
          {
            ControllerManager.getInstance().setChannelSource(0);
            ControllerManager.getInstance().openChannelDetailController(this.mNode);
          }
          else if (((ChannelNode)this.mNode).channelType == 1)
          {
            ControllerManager.getInstance().setChannelSource(0);
            ControllerManager.getInstance().openChannelDetailController(this.mNode);
          }
          else
          {
            ControllerManager.getInstance().redirectToPlayViewByNode(this.mNode, true);
            continue;
            dispatchActionEvent("deleteItem", null);
            continue;
            QTMSGManage.getInstance().sendStatistcsMessage("clickdownloadincollection");
            ControllerManager.getInstance().redirectToBatchDownloadView(this.mNode, true, false);
          }
        }
      }
      this.mInTouchMode = false;
      this.mSelectedIndex = -1;
    }
    while (isItemPressed());
    invalidate();
    return true;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mNode = ((Node)paramObject);
      invalidate();
    }
    do
    {
      do
      {
        int i;
        do
        {
          do
          {
            return;
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
        if (!paramString.equalsIgnoreCase("hideManage"))
          break;
      }
      while (this.mOffset == 0);
      startHideManageAnimation();
      return;
    }
    while (!paramString.equalsIgnoreCase("checkState"));
    this.mIsChecked = ((Boolean)paramObject).booleanValue();
    invalidate();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mycollection.MyCollectionItemView
 * JD-Core Version:    0.6.2
 */