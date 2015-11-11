package fm.qingting.qtradio.view.personalcenter.playhistory;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
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
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PlayHistoryNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.ReserveNode;
import fm.qingting.utils.TimeUtil;
import java.util.ArrayList;
import java.util.List;

public class PlayHistoryItemView extends QtListItemView
  implements IMotionHandler
{
  private final ViewLayout arrowLayout = this.itemLayout.createChildLT(36, 36, 650, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout channelLayout = this.itemLayout.createChildLT(660, 45, 30, 25, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(30, 22, 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = this.itemLayout.createChildLT(48, 48, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private int hash = -2;
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(660, 45, 30, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Object mAnimator;
  private final Paint mArrowPaint = new Paint();
  private final Rect mArrowRect = new Rect();
  private final Paint mCheckBgPaint = new Paint();
  private final Rect mCheckRect = new Rect();
  private final Paint mCheckStatePaint = new Paint();
  private boolean mInTouchMode = false;
  private boolean mIsChecked = false;
  private Node mNode;
  private int mOffset = 0;
  private final Paint mPaint = new Paint();
  private int mSelectedIndex = -1;
  private MotionController motionController;
  private final Rect textBound = new Rect();

  public PlayHistoryItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.hash = paramInt;
    this.mCheckBgPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mCheckStatePaint.setColor(SkinManager.getTextColorHighlight());
    this.mCheckBgPaint.setStyle(Paint.Style.STROKE);
    this.mCheckStatePaint.setStyle(Paint.Style.FILL);
    setItemSelectedEnable();
    init();
  }

  private void drawArrow(Canvas paramCanvas, boolean paramBoolean)
  {
    paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getResources(), this.hash, 2130837697), null, this.mArrowRect, this.mArrowPaint);
  }

  private void drawBg(Canvas paramCanvas, boolean paramBoolean)
  {
    if (!paramBoolean)
      paramCanvas.drawColor(SkinManager.getCardColor());
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

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, this.mOffset, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  private boolean drawNovelSubInfo(Canvas paramCanvas)
  {
    if (!isNovel());
    int i;
    do
    {
      return false;
      i = getSequence();
    }
    while (i <= 0);
    String str = "上次收听到第" + String.valueOf(i);
    str = str + "集";
    TextPaint localTextPaint = SkinManager.getInstance().getSubTextPaint();
    localTextPaint.getTextBounds(str, 0, str.length(), this.textBound);
    paramCanvas.drawText(str, this.mOffset + this.infoLayout.getLeft() + this.itemLayout.leftMargin, this.channelLayout.topMargin + this.channelLayout.height + this.infoLayout.topMargin + (this.infoLayout.height - this.textBound.top - this.textBound.bottom) / 2, localTextPaint);
    return true;
  }

  private void drawSubInfo(Canvas paramCanvas)
  {
    if (drawNovelSubInfo(paramCanvas))
      return;
    TextPaint localTextPaint = SkinManager.getInstance().getSubTextPaint();
    localTextPaint.getTextBounds("播放时间:", 0, "播放时间:".length(), this.textBound);
    float f1 = this.mOffset + this.infoLayout.getLeft() + this.itemLayout.leftMargin;
    float f2 = this.channelLayout.topMargin + this.channelLayout.height + this.infoLayout.topMargin + (this.infoLayout.height - this.textBound.top - this.textBound.bottom) / 2;
    paramCanvas.drawText("播放时间:", f1, f2, localTextPaint);
    String str = TimeUtil.msToDate4(getTime() * 1000L);
    float f3 = this.textBound.width();
    float f4 = this.infoLayout.getLeft() / 2;
    localTextPaint.getTextBounds(str, 0, str.length(), this.textBound);
    paramCanvas.drawText(str, f1 + f3 + f4, f2, localTextPaint);
  }

  private void drawTitle(Canvas paramCanvas)
  {
    Object localObject2 = getTitle();
    Object localObject1 = localObject2;
    if (localObject2 == null)
      localObject1 = "";
    localObject2 = SkinManager.getInstance().getNormalTextPaint();
    localObject1 = TextUtils.ellipsize((CharSequence)localObject1, (TextPaint)localObject2, this.mArrowRect.left - this.channelLayout.leftMargin - this.mOffset, TextUtils.TruncateAt.END).toString();
    ((TextPaint)localObject2).getTextBounds((String)localObject1, 0, ((String)localObject1).length(), this.textBound);
    paramCanvas.drawText((String)localObject1, this.channelLayout.getLeft() + this.itemLayout.leftMargin + this.mOffset, this.channelLayout.topMargin + (this.channelLayout.height - this.textBound.top - this.textBound.bottom) / 2, (Paint)localObject2);
    drawSubInfo(paramCanvas);
  }

  private void generateRect()
  {
    this.mArrowRect.set(this.itemLayout.leftMargin + this.arrowLayout.leftMargin, (this.itemLayout.height - this.arrowLayout.height) / 2, this.itemLayout.leftMargin + this.arrowLayout.leftMargin + this.arrowLayout.width, (this.itemLayout.height + this.arrowLayout.height) / 2);
    this.mCheckRect.set((-this.checkbgLayout.width - this.checkStateLayout.width) / 2, (this.itemLayout.height - this.checkStateLayout.height) / 2, (-this.checkbgLayout.width + this.checkStateLayout.width) / 2, (this.itemLayout.height + this.checkStateLayout.height) / 2);
  }

  private int getMaxOffset()
  {
    return this.checkbgLayout.leftMargin + this.checkbgLayout.width;
  }

  private int getSelectedIndex()
  {
    if (this.mOffset > 0)
      return 1;
    return 0;
  }

  private int getSequence()
  {
    if ((this.mNode != null) && (this.mNode.nodeName.equalsIgnoreCase("playhistory")))
    {
      Node localNode = ((PlayHistoryNode)this.mNode).playNode;
      if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")))
        return ((ProgramNode)localNode).sequence;
    }
    return 0;
  }

  private long getTime()
  {
    if (this.mNode.nodeName.equalsIgnoreCase("playhistory"))
      return ((PlayHistoryNode)this.mNode).playTime;
    if (this.mNode.nodeName.equalsIgnoreCase("reserve"))
      return ((ReserveNode)this.mNode).reserveTime;
    return 0L;
  }

  private String getTitle()
  {
    if (this.mNode.nodeName.equalsIgnoreCase("playhistory"))
    {
      Node localNode = ((PlayHistoryNode)this.mNode).playNode;
      if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")))
      {
        if (((ProgramNode)localNode).channelType == 1)
        {
          if (((ProgramNode)localNode).isNovelProgram())
          {
            if (InfoManager.getInstance().isDownloadCategory(((PlayHistoryNode)this.mNode).categoryId))
              return "已下载: " + ((ProgramNode)localNode).title;
            return ((ProgramNode)localNode).title;
          }
          if (InfoManager.getInstance().isDownloadCategory(((PlayHistoryNode)this.mNode).categoryId))
            return "已下载: " + ((ProgramNode)localNode).title;
          return ((ProgramNode)localNode).title;
        }
        return ((PlayHistoryNode)this.mNode).channelName;
      }
      return ((PlayHistoryNode)this.mNode).channelName;
    }
    return "";
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
          PlayHistoryItemView.this.setPosition(((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
        }
      });
      return;
    }
    this.motionController = new MotionController(this);
  }

  private boolean isNovel()
  {
    if ((this.mNode != null) && (this.mNode.nodeName.equalsIgnoreCase("playhistory")))
    {
      Node localNode = ((PlayHistoryNode)this.mNode).playNode;
      if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")))
        return ((ProgramNode)localNode).isNovelProgram();
    }
    return false;
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
    boolean bool2 = true;
    if (this.mNode == null)
      return;
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    paramCanvas.clipRect(0, 0, this.itemLayout.width, this.itemLayout.height);
    int i;
    if (this.mOffset < 0)
    {
      i = 0;
      if ((i >= 0) && (i <= 255))
        this.mArrowPaint.setAlpha(255 - i);
      if ((!isItemPressed()) || (this.mSelectedIndex != 0))
        break label164;
      bool1 = true;
      label95: drawBg(paramCanvas, bool1);
      drawLine(paramCanvas);
      drawTitle(paramCanvas);
      drawCheckState(paramCanvas);
      if ((!isItemPressed()) || (this.mSelectedIndex != 0))
        break label169;
    }
    label164: label169: for (boolean bool1 = bool2; ; bool1 = false)
    {
      drawArrow(paramCanvas, bool1);
      paramCanvas.restore();
      return;
      i = (int)(255.0F * this.mOffset / getMaxOffset());
      break;
      bool1 = false;
      break label95;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.itemLayout.scaleToBounds(paramInt1, paramInt2);
    this.channelLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.arrowLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.checkbgLayout.scaleToBounds(this.itemLayout);
    this.checkStateLayout.scaleToBounds(this.checkbgLayout);
    this.mCheckBgPaint.setStrokeWidth(this.checkStateLayout.leftMargin);
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
    if ((!this.mInTouchMode) && (paramMotionEvent.getAction() != 0));
    do
    {
      do
      {
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
                this.mInTouchMode = true;
                this.mSelectedIndex = getSelectedIndex();
                invalidate();
                return true;
              case 2:
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
        if (this.mSelectedIndex != 0)
          break;
      }
      while (this.mNode == null);
      PlayerAgent.getInstance().addPlaySource(7);
      ControllerManager.getInstance().redirectToPlayViewByNode(this.mNode, true);
      return true;
    }
    while (this.mSelectedIndex != 1);
    dispatchActionEvent("itemSelect", null);
    return true;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
      this.mNode = ((Node)paramObject);
    do
    {
      int i;
      do
      {
        do
        {
          return;
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
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.playhistory.PlayHistoryItemView
 * JD-Core Version:    0.6.2
 */