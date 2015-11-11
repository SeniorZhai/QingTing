package fm.qingting.qtradio.view.personalcenter.mypodcaster;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View.MeasureSpec;
import fm.qingting.framework.tween.FrameTween;
import fm.qingting.framework.tween.FrameTween.SyncType;
import fm.qingting.framework.tween.IMotionHandler;
import fm.qingting.framework.tween.MotionController;
import fm.qingting.framework.tween.TweenProperty;
import fm.qingting.framework.tween.easing.Quad.EaseIn;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.helper.PodcasterHelper;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.utils.FansUtils;
import java.util.ArrayList;
import java.util.List;

public class MyPodcasterItemView extends QtView
  implements IMotionHandler, InfoManager.ISubscribeEventListener
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(120, 120, 16, 24, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(30, 22, 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = this.itemLayout.createChildLT(48, 48, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout descLayout = this.itemLayout.createChildLT(514, 40, 160, 70, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout fansCountLayout = this.itemLayout.createChildLT(200, 40, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 168, 720, 168, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(670, 2, 150, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Object mAnimator;
  private RoundAvatarElement mAvatarElement;
  private ButtonViewElement mBg;
  private final Paint mCheckBgPaint = new Paint();
  private final Rect mCheckRect = new Rect();
  private final Paint mCheckStatePaint = new Paint();
  private TextViewElement mDescElement;
  private TextViewElement mFansCountElement;
  private int mHash;
  private boolean mIsChecked = false;
  private LineElement mLineElement;
  private TextViewElement mNameElement;
  private int mOffset;
  private Paint mPaint = new Paint();
  private UserInfo mPodcasterInfo;
  private TextViewElement mProgramElement;
  private boolean mRemind;
  private final Paint mReminderPaint = new Paint();
  private MotionController motionController;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(514, 40, 160, 22, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout programLayout = this.itemLayout.createChildLT(514, 40, 160, 110, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout remindLayout = this.itemLayout.createChildLT(20, 20, 120, 32, ViewLayout.SCALE_FLAG_SLTCW);

  public MyPodcasterItemView(Context paramContext, int paramInt)
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
        if (MyPodcasterItemView.this.mOffset == 0)
        {
          if (MyPodcasterItemView.this.mPodcasterInfo != null)
            ControllerManager.getInstance().openPodcasterInfoController(MyPodcasterItemView.this.mPodcasterInfo);
          return;
        }
        MyPodcasterItemView.this.dispatchActionEvent("itemSelect", null);
      }
    });
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130838032);
    addElement(this.mAvatarElement, this.mHash);
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setColor(SkinManager.getTextColorNormal());
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    addElement(this.mNameElement);
    this.mFansCountElement = new TextViewElement(paramContext);
    this.mFansCountElement.setColor(SkinManager.getTextColorSubInfo());
    this.mFansCountElement.setMaxLineLimit(1);
    this.mFansCountElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    this.mDescElement = new TextViewElement(paramContext);
    this.mDescElement.setColor(SkinManager.getTextColorNormal());
    this.mDescElement.setMaxLineLimit(1);
    addElement(this.mDescElement);
    this.mProgramElement = new TextViewElement(paramContext);
    this.mProgramElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mProgramElement.setMaxLineLimit(1);
    addElement(this.mProgramElement);
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

  private void drawReminder(Canvas paramCanvas, float paramFloat1, float paramFloat2)
  {
    if (this.mRemind)
      paramCanvas.drawCircle(this.remindLayout.width / 2 + paramFloat1, this.remindLayout.height / 2 + paramFloat2, this.remindLayout.width / 2, this.mReminderPaint);
  }

  private int getMaxOffset()
  {
    return this.checkbgLayout.leftMargin + this.checkbgLayout.width;
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
          MyPodcasterItemView.this.setPosition(((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
        }
      });
      return;
    }
    this.motionController = new MotionController(this);
  }

  private void setDescText()
  {
    if (!TextUtils.isEmpty(this.mPodcasterInfo.snsInfo.desc))
    {
      this.mDescElement.setText(this.mPodcasterInfo.snsInfo.desc);
      return;
    }
    if (!TextUtils.isEmpty(this.mPodcasterInfo.snsInfo.signature))
    {
      this.mDescElement.setText(this.mPodcasterInfo.snsInfo.signature);
      return;
    }
    if (this.mPodcasterInfo.getChannelNodes() == null)
    {
      InfoManager.getInstance().loadPodcasterChannels(this.mPodcasterInfo.podcasterId, this);
      this.mDescElement.setText("正在加载...");
      return;
    }
    if (this.mPodcasterInfo.getChannelNodes().size() == 0)
    {
      this.mDescElement.setText("暂无专辑");
      return;
    }
    Object localObject = (ChannelNode)this.mPodcasterInfo.getChannelNodes().get(0);
    TextViewElement localTextViewElement = this.mDescElement;
    if (localObject == null);
    for (localObject = "暂无专辑"; ; localObject = ((ChannelNode)localObject).title)
    {
      localTextViewElement.setText((String)localObject);
      return;
    }
  }

  private void setElementValues()
  {
    if ((this.mPodcasterInfo == null) || (this.mPodcasterInfo.snsInfo == null))
      return;
    this.mAvatarElement.setImageUrl(this.mPodcasterInfo.snsInfo.sns_avatar);
    this.mNameElement.setText(this.mPodcasterInfo.podcasterName);
    this.mFansCountElement.setText(FansUtils.toString(this.mPodcasterInfo.fansNumber));
    setDescText();
    setProgramText();
  }

  private void setPosition(float paramFloat)
  {
    this.mOffset = ((int)paramFloat);
    invalidate();
  }

  private void setProgramText()
  {
    if (this.mPodcasterInfo.getProgramNodes() == null)
    {
      InfoManager.getInstance().loadPodcasterLatestInfo(this.mPodcasterInfo.podcasterId, this);
      this.mProgramElement.setText("正在加载...");
      return;
    }
    if (this.mPodcasterInfo.getProgramNodes().size() == 0)
    {
      this.mProgramElement.setText("暂无节目");
      return;
    }
    ProgramNode localProgramNode = (ProgramNode)this.mPodcasterInfo.getProgramNodes().get(0);
    TextViewElement localTextViewElement = this.mProgramElement;
    if (localProgramNode == null);
    for (String str = "暂无节目"; ; str = localProgramNode.title)
    {
      localTextViewElement.setText(str);
      if (this.mPodcasterInfo.lastestUpdateTime != 0L)
        break;
      this.mRemind = false;
      invalidate();
      return;
    }
    if (localProgramNode.getUpdateTime() > this.mPodcasterInfo.lastestUpdateTime);
    for (boolean bool = true; ; bool = false)
    {
      this.mRemind = bool;
      break;
    }
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
    this.mNameElement.setTranslationX(this.mOffset);
    this.mDescElement.setTranslationX(this.mOffset);
    this.mProgramElement.setTranslationX(this.mOffset);
    this.mLineElement.setTranslationX(this.mOffset);
    super.onDraw(paramCanvas);
    drawReminder(paramCanvas, this.mOffset + this.remindLayout.getLeft(), this.remindLayout.getTop());
    drawCheckState(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.fansCountLayout.scaleToBounds(this.itemLayout);
    this.descLayout.scaleToBounds(this.itemLayout);
    this.programLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.checkbgLayout.scaleToBounds(this.itemLayout);
    this.remindLayout.scaleToBounds(this.itemLayout);
    this.mNameElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mDescElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mProgramElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    this.mFansCountElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mNameElement.getWidth();
    this.mDescElement.measure(this.descLayout);
    this.mProgramElement.measure(this.programLayout);
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

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RECV_PODCASTER_CHANNELS"))
    {
      this.mPodcasterInfo = PodcasterHelper.getInstance().getPodcaster(this.mPodcasterInfo.podcasterId);
      setDescText();
    }
    while (!paramString.equalsIgnoreCase("RECV_PODCASTER_LATEST"))
      return;
    this.mPodcasterInfo = PodcasterHelper.getInstance().getPodcaster(this.mPodcasterInfo.podcasterId);
    setProgramText();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void onTargetChange(MotionController paramMotionController, float paramFloat)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mPodcasterInfo = ((UserInfo)paramObject);
      setElementValues();
    }
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
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mypodcaster.MyPodcasterItemView
 * JD-Core Version:    0.6.2
 */