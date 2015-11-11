package fm.qingting.qtradio.view.personalcenter.mycollection;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
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
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PlayingProgramInfoNode;
import fm.qingting.qtradio.model.PlayingProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.utils.TimeUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MyCollectionItemView2 extends QtView
  implements IMotionHandler
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(120, 120, 16, 24, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(30, 22, 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = this.itemLayout.createChildLT(48, 48, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 168, 720, 168, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lastestProgramLayout = this.itemLayout.createChildLT(514, 40, 160, 70, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(680, 1, 16, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Object mAnimator;
  private NetImageViewElement mAvatarElement;
  private ButtonViewElement mBg;
  private ChannelNode mChannelInfo;
  private final Paint mCheckBgPaint = new Paint();
  private final Rect mCheckRect = new Rect();
  private final Paint mCheckStatePaint = new Paint();
  private int mHash;
  private boolean mIsChecked = false;
  private TextViewElement mLastestProgramElement;
  private LineElement mLineElement;
  private TextViewElement mNameElement;
  private int mOffset;
  private Paint mPaint = new Paint();
  private boolean mRemind;
  private final Paint mReminderPaint = new Paint();
  private TextViewElement mUpdateTimeElement;
  private MotionController motionController;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(514, 40, 160, 22, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout remindLayout = this.itemLayout.createChildLT(20, 20, 120, 32, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout updateTimeLayout = this.itemLayout.createChildLT(514, 40, 160, 110, ViewLayout.SCALE_FLAG_SLTCW);

  public MyCollectionItemView2(Context paramContext, int paramInt)
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
        if (MyCollectionItemView2.this.mOffset == 0)
        {
          if (MyCollectionItemView2.this.mChannelInfo != null)
          {
            PlayerAgent.getInstance().addPlaySource(4);
            if (MyCollectionItemView2.this.mChannelInfo.isNovelChannel())
            {
              ControllerManager.getInstance().setChannelSource(0);
              ControllerManager.getInstance().openChannelDetailController(MyCollectionItemView2.this.mChannelInfo);
            }
          }
          else
          {
            return;
          }
          if (MyCollectionItemView2.this.mChannelInfo.channelType == 1)
          {
            ControllerManager.getInstance().setChannelSource(0);
            ControllerManager.getInstance().openChannelDetailController(MyCollectionItemView2.this.mChannelInfo);
            return;
          }
          ControllerManager.getInstance().redirectToPlayViewByNode(MyCollectionItemView2.this.mChannelInfo, true);
          return;
        }
        MyCollectionItemView2.this.dispatchActionEvent("itemCallback", null);
      }
    });
    this.mAvatarElement = new NetImageViewElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837907);
    addElement(this.mAvatarElement, this.mHash);
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setColor(SkinManager.getTextColorNormal());
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    addElement(this.mNameElement);
    this.mLastestProgramElement = new TextViewElement(paramContext);
    this.mLastestProgramElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mLastestProgramElement.setMaxLineLimit(1);
    addElement(this.mLastestProgramElement);
    this.mUpdateTimeElement = new TextViewElement(paramContext);
    this.mUpdateTimeElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mUpdateTimeElement.setMaxLineLimit(1);
    addElement(this.mUpdateTimeElement);
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

  private String getCurrentPlayingProgram(ChannelNode paramChannelNode)
  {
    paramChannelNode = (PlayingProgramNode)InfoManager.getInstance().root().mPlayingProgramInfo.getCurrentPlayingProgram(paramChannelNode);
    if (paramChannelNode == null)
      return "";
    return "正在直播：" + paramChannelNode.programName;
  }

  private int getMaxOffset()
  {
    return this.checkbgLayout.leftMargin + this.checkbgLayout.width;
  }

  private String getUpdateTimeString(long paramLong)
  {
    Object localObject = Calendar.getInstance();
    int i = ((Calendar)localObject).get(1);
    int j = ((Calendar)localObject).get(6);
    ((Calendar)localObject).setTimeInMillis(paramLong);
    int k = ((Calendar)localObject).get(1);
    int m = ((Calendar)localObject).get(6);
    if ((i == k) && (j == m));
    for (i = 1; ; i = 0)
    {
      if (i != 0)
      {
        paramLong = System.currentTimeMillis() - paramLong;
        if (paramLong <= 600000L)
          localObject = "刚刚";
      }
      while (true)
      {
        return (String)localObject + "更新";
        i = (int)(paramLong / 1000L / 3600L);
        if (i > 0)
          String.format(Locale.CHINA, "%d小时前", new Object[] { Integer.valueOf(i) });
        i = (int)(paramLong / 1000L / 60L % 60L);
        if (i > 0)
          String.format(Locale.CHINA, "%d分钟前", new Object[] { Integer.valueOf(i) });
        localObject = "刚刚";
        continue;
        localObject = TimeUtil.msToDate5(paramLong);
      }
    }
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
          MyCollectionItemView2.this.setPosition(((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
        }
      });
      return;
    }
    this.motionController = new MotionController(this);
  }

  private void setElementValues()
  {
    if (this.mChannelInfo == null)
    {
      this.mAvatarElement.setImageUrl("");
      this.mNameElement.setText("");
      this.mLastestProgramElement.setText("");
      this.mUpdateTimeElement.setText("");
      return;
    }
    this.mAvatarElement.setImageUrl(this.mChannelInfo.getApproximativeThumb());
    this.mNameElement.setText(this.mChannelInfo.title);
    if (this.mChannelInfo.channelType == 0)
    {
      this.mLastestProgramElement.setText(getCurrentPlayingProgram(this.mChannelInfo));
      this.mUpdateTimeElement.setText("");
      return;
    }
    this.mLastestProgramElement.setText(this.mChannelInfo.latest_program);
    this.mUpdateTimeElement.setText(getUpdateTimeString(this.mChannelInfo.getUpdateTime()));
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
    this.mNameElement.setTranslationX(this.mOffset);
    this.mLastestProgramElement.setTranslationX(this.mOffset);
    this.mUpdateTimeElement.setTranslationX(this.mOffset);
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
    this.lastestProgramLayout.scaleToBounds(this.itemLayout);
    this.updateTimeLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.checkbgLayout.scaleToBounds(this.itemLayout);
    this.remindLayout.scaleToBounds(this.itemLayout);
    this.mNameElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mLastestProgramElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mUpdateTimeElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mLastestProgramElement.measure(this.lastestProgramLayout);
    this.mUpdateTimeElement.measure(this.updateTimeLayout);
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
      this.mChannelInfo = ((ChannelNode)paramObject);
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
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mycollection.MyCollectionItemView2
 * JD-Core Version:    0.6.2
 */