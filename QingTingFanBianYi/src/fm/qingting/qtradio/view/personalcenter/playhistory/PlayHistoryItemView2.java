package fm.qingting.qtradio.view.personalcenter.playhistory;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.tween.FrameTween;
import fm.qingting.framework.tween.FrameTween.SyncType;
import fm.qingting.framework.tween.IMotionHandler;
import fm.qingting.framework.tween.MotionController;
import fm.qingting.framework.tween.TweenProperty;
import fm.qingting.framework.tween.easing.Quad.EaseIn;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PlayHistoryNode;
import fm.qingting.qtradio.model.PlayedMetaData;
import fm.qingting.qtradio.model.PlayedMetaInfo;
import fm.qingting.qtradio.model.PlayingProgramInfoNode;
import fm.qingting.qtradio.model.PlayingProgramNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.playview.LineElement;
import java.util.ArrayList;
import java.util.List;

public class PlayHistoryItemView2 extends QtView
  implements IMotionHandler
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(120, 120, 16, 24, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout channelTitleLayout = this.itemLayout.createChildLT(514, 40, 160, 22, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(30, 22, 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = this.itemLayout.createChildLT(48, 48, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 168, 720, 168, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(680, 1, 20, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Object mAnimator;
  private NetImageViewElement mAvatarElement;
  private ButtonViewElement mBg;
  private TextViewElement mChannelTitleElement;
  private final Paint mCheckBgPaint = new Paint();
  private final Rect mCheckRect = new Rect();
  private final Paint mCheckStatePaint = new Paint();
  private int mHash;
  private PlayHistoryNode mHistoryNode;
  private boolean mIsChecked = false;
  private LineElement mLineElement;
  private PlayedMetaData mMetaData;
  private int mOffset;
  private Paint mPaint = new Paint();
  private TextViewElement mPlayPercentElement;
  private TextViewElement mPlayTimeElement;
  private ProgramNode mProgramNode;
  private TextViewElement mProgramTitleElement;
  private final Paint mReminderPaint = new Paint();
  private MotionController motionController;
  private final ViewLayout playPercentLayout = this.itemLayout.createChildLT(200, 40, 500, 110, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout playTimeLayout = this.itemLayout.createChildLT(400, 40, 160, 110, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout programTitleLayout = this.itemLayout.createChildLT(514, 40, 160, 70, ViewLayout.SCALE_FLAG_SLTCW);

  public PlayHistoryItemView2(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mHash = paramInt;
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(0, SkinManager.getCardColor());
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        if (PlayHistoryItemView2.this.mOffset == 0)
        {
          try
          {
            PlayerAgent.getInstance().addPlaySource(7);
            if (PlayHistoryItemView2.this.mHistoryNode.playContent == 0L)
            {
              ControllerManager.getInstance().redirectToPlayViewByNode(PlayHistoryItemView2.this.mHistoryNode, true);
              return;
            }
            if (!PlayHistoryItemView2.this.mProgramNode.available)
              return;
            PlayerAgent.getInstance().play(PlayHistoryItemView2.this.mProgramNode);
            paramAnonymousViewElement = ChannelHelper.getInstance().getChannel(PlayHistoryItemView2.this.mProgramNode);
            if (paramAnonymousViewElement == null)
              return;
            if (paramAnonymousViewElement.isDownloadChannel())
            {
              ControllerManager.getInstance().redirectToDownloadProgramsController(paramAnonymousViewElement);
              return;
            }
          }
          catch (Exception paramAnonymousViewElement)
          {
            paramAnonymousViewElement.printStackTrace();
            return;
          }
          ControllerManager.getInstance().openChannelDetailController(paramAnonymousViewElement);
        }
        else
        {
          PlayHistoryItemView2.this.dispatchActionEvent("itemCallback", null);
        }
      }
    });
    this.mAvatarElement = new NetImageViewElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837907);
    addElement(this.mAvatarElement, this.mHash);
    this.mChannelTitleElement = new TextViewElement(paramContext);
    this.mChannelTitleElement.setColor(SkinManager.getTextColorNormal());
    this.mChannelTitleElement.setMaxLineLimit(1);
    this.mChannelTitleElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    addElement(this.mChannelTitleElement);
    this.mProgramTitleElement = new TextViewElement(paramContext);
    this.mProgramTitleElement.setColor(-9934744);
    this.mProgramTitleElement.setMaxLineLimit(1);
    addElement(this.mProgramTitleElement);
    this.mPlayTimeElement = new TextViewElement(paramContext);
    this.mPlayTimeElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mPlayTimeElement.setMaxLineLimit(1);
    addElement(this.mPlayTimeElement);
    this.mPlayPercentElement = new TextViewElement(paramContext);
    this.mPlayPercentElement.setAlignment(Layout.Alignment.ALIGN_OPPOSITE);
    this.mPlayPercentElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mPlayPercentElement.setMaxLineLimit(1);
    addElement(this.mPlayPercentElement);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
    this.mReminderPaint.setColor(-59877);
    this.mReminderPaint.setStyle(Paint.Style.FILL);
    this.mCheckBgPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mCheckStatePaint.setColor(SkinManager.getTextColorHighlight());
    this.mCheckBgPaint.setStyle(Paint.Style.STROKE);
    this.mCheckStatePaint.setStyle(Paint.Style.FILL);
    init();
  }

  private void drawCheckState(Canvas paramCanvas)
  {
    if (this.mOffset > 0)
    {
      this.mCheckRect.offset(this.mOffset, 0);
      if (!this.mIsChecked)
        break label104;
      paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2, this.mCheckStatePaint);
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getResources(), this.mHash, 2130837754), null, this.mCheckRect, this.mPaint);
    }
    while (true)
    {
      this.mCheckRect.offset(-this.mOffset, 0);
      return;
      label104: paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2, this.mCheckBgPaint);
    }
  }

  private int getMaxOffset()
  {
    return this.checkbgLayout.leftMargin + this.checkbgLayout.width;
  }

  private String getPlayTime(int paramInt)
  {
    int i = paramInt / 3600;
    int j = paramInt % 3600;
    paramInt = j / 60;
    j %= 60;
    if (i > 0)
      return String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt), Integer.valueOf(j) });
    return String.format("%02d:%02d", new Object[] { Integer.valueOf(paramInt), Integer.valueOf(j) });
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
          PlayHistoryItemView2.this.setPosition(((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
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

  public void close(boolean paramBoolean)
  {
    super.close(paramBoolean);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    this.mAvatarElement.setTranslationX(this.mOffset);
    this.mChannelTitleElement.setTranslationX(this.mOffset);
    this.mProgramTitleElement.setTranslationX(this.mOffset);
    this.mPlayTimeElement.setTranslationX(this.mOffset);
    this.mLineElement.setTranslationX(this.mOffset);
    super.onDraw(paramCanvas);
    drawCheckState(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.channelTitleLayout.scaleToBounds(this.itemLayout);
    this.programTitleLayout.scaleToBounds(this.itemLayout);
    this.playTimeLayout.scaleToBounds(this.itemLayout);
    this.playPercentLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.checkbgLayout.scaleToBounds(this.itemLayout);
    this.mChannelTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mProgramTitleElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mPlayTimeElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    this.mPlayPercentElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mChannelTitleElement.measure(this.channelTitleLayout);
    this.mProgramTitleElement.measure(this.programTitleLayout);
    this.mPlayTimeElement.measure(this.playTimeLayout);
    this.mPlayPercentElement.measure(this.playPercentLayout);
    this.mLineElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.lineLayout.getRight(), this.itemLayout.height);
    this.mCheckBgPaint.setStrokeWidth(this.checkStateLayout.leftMargin);
    this.mCheckRect.set((-this.checkbgLayout.width - this.checkStateLayout.width) / 2, (this.itemLayout.height - this.checkStateLayout.height) / 2, (-this.checkbgLayout.width + this.checkStateLayout.width) / 2, (this.itemLayout.height + this.checkStateLayout.height) / 2);
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

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mHistoryNode = ((PlayHistoryNode)paramObject);
      if (paramObject != null);
    }
    do
    {
      int i;
      do
      {
        do
        {
          do
          {
            return;
            this.mHistoryNode = ((PlayHistoryNode)paramObject);
            this.mProgramNode = ((ProgramNode)this.mHistoryNode.playNode);
          }
          while (this.mProgramNode == null);
          this.mMetaData = PlayedMetaInfo.getInstance().getPlayedMeta(this.mProgramNode.id);
          paramObject = this.mHistoryNode.channelThumb;
          paramString = paramObject;
          if (paramObject == null)
          {
            ChannelNode localChannelNode = ChannelHelper.getInstance().getChannel(this.mProgramNode);
            paramString = paramObject;
            if (localChannelNode != null)
            {
              paramString = localChannelNode.getApproximativeThumb();
              this.mHistoryNode.channelThumb = paramString;
            }
          }
          this.mAvatarElement.setImageUrl(paramString);
          if (this.mHistoryNode.playContent == 1L)
          {
            this.mChannelTitleElement.setText(this.mProgramNode.title);
            this.mProgramTitleElement.setText(this.mHistoryNode.channelName);
            this.mProgramTitleElement.setVisible(0);
            if (this.mMetaData != null)
            {
              this.mPlayTimeElement.setText("收听至" + getPlayTime(this.mMetaData.position));
              this.mPlayPercentElement.setText("已经收听" + this.mMetaData.position * 100 / this.mMetaData.duration + "%");
            }
            while (true)
            {
              this.mChannelTitleElement.setTranslationY(0);
              this.mPlayTimeElement.setTranslationY(0);
              return;
              this.mPlayTimeElement.setText("收听至" + getPlayTime(1));
              this.mPlayPercentElement.setText("已经收听0%");
            }
          }
          paramString = (PlayingProgramNode)InfoManager.getInstance().root().mPlayingProgramInfo.getCurrentPlayingProgram(this.mHistoryNode.channelId, this.mHistoryNode.channelName);
          if (paramString == null);
          for (paramString = "上次收听: " + this.mProgramNode.title; ; paramString = "正在直播: " + paramString.programName)
          {
            this.mChannelTitleElement.setText(this.mHistoryNode.channelName);
            this.mPlayTimeElement.setText(paramString);
            this.mChannelTitleElement.setTranslationY(20);
            this.mPlayTimeElement.setTranslationY(-20);
            this.mPlayTimeElement.setVisible(0);
            this.mProgramTitleElement.setVisible(4);
            this.mPlayPercentElement.setVisible(4);
            return;
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
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.playhistory.PlayHistoryItemView2
 * JD-Core Version:    0.6.2
 */