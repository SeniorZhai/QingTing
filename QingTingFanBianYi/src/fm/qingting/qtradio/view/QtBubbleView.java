package fm.qingting.qtradio.view;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.view.advertisement.AdvertisementView;
import fm.qingting.qtradio.view.chatroom.ChatUserActionPopView;
import fm.qingting.qtradio.view.personalcenter.clock.djringtone.DjAlarmPopView;
import fm.qingting.qtradio.view.popviews.AccountPopView;
import fm.qingting.qtradio.view.popviews.AlertPopView;
import fm.qingting.qtradio.view.popviews.ChannelInfoView;
import fm.qingting.qtradio.view.popviews.CollectionRemindPopView;
import fm.qingting.qtradio.view.popviews.CustomPopActionView;
import fm.qingting.qtradio.view.popviews.FeedbackPopViewNew;
import fm.qingting.qtradio.view.popviews.FlowPopView;
import fm.qingting.qtradio.view.popviews.FmScanPopView;
import fm.qingting.qtradio.view.popviews.ImBlockRemindPopView;
import fm.qingting.qtradio.view.popviews.ImBlockRemovePopView;
import fm.qingting.qtradio.view.popviews.ImMenuView;
import fm.qingting.qtradio.view.popviews.MainMenuView;
import fm.qingting.qtradio.view.popviews.OnlineUpdatePopuView;
import fm.qingting.qtradio.view.popviews.SchedulePopView;
import fm.qingting.qtradio.view.popviews.ShareMessagePopuView;
import fm.qingting.qtradio.view.popviews.SharePopView;
import fm.qingting.qtradio.view.popviews.ToastPopupView;
import fm.qingting.qtradio.view.popviews.TopHistoryPopView;
import fm.qingting.qtradio.view.popviews.VoicePopupView;
import fm.qingting.qtradio.view.popviews.categoryfilter.CategoryFilterPopView;
import fm.qingting.utils.QTMSGManage;

public class QtBubbleView extends ViewGroupViewImpl
  implements IEventHandler
{
  public static final int BUBBLE_ACCOUNT = 12;
  public static final int BUBBLE_ADVIEW = 13;
  public static final int BUBBLE_ALERT = 5;
  public static final int BUBBLE_BLOCKREMIND = 16;
  public static final int BUBBLE_BLOCK_DELETE = 17;
  public static final int BUBBLE_CHANNELINFO = 3;
  public static final int BUBBLE_CHAT = 14;
  public static final int BUBBLE_COLLECTION_REMIND = 20;
  public static final int BUBBLE_DJRINGTONELOADING = 9;
  public static final int BUBBLE_ERRORLOG = 2;
  public static final int BUBBLE_FEEDBACK = 10;
  public static final int BUBBLE_FILTER = 18;
  public static final int BUBBLE_FLOW = 25;
  public static final int BUBBLE_IMMENU = 1;
  public static final int BUBBLE_MENU = 8;
  public static final int BUBBLE_NONE = 0;
  public static final int BUBBLE_OPERATE = 6;
  public static final int BUBBLE_SCANFM = 11;
  public static final int BUBBLE_SCHEDULEPOP = 7;
  public static final int BUBBLE_SHARE = 4;
  public static final int BUBBLE_SHARE_MESSAGE = 32;
  public static final int BUBBLE_TOAST = 23;
  public static final int BUBBLE_TOPPLAYHISTORY = 24;
  public static final int BUBBLE_UPGRADE = 22;
  public static final int BUBBLE_VOICE = 15;
  private final ViewLayout collectionRemindLayout = this.standardLayout.createChildLT(720, 320, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout immenuLayout = this.standardLayout.createChildLT(720, 640, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private int mAnimationLengthDefault = 220;
  private int mAnimationLengthShort = 150;
  private Animation mHideAnimation;
  private int mLastType = 0;
  private IView mShowedView;
  private Point mTimerShowPoint = new Point();
  private final ViewLayout menuLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 400, 720, 1200, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public QtBubbleView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-1728053248);
  }

  private boolean clearUnNeededState(int paramInt)
  {
    if ((this.mLastType == paramInt) && (this.mShowedView != null))
      return false;
    if (this.mHideAnimation != null)
    {
      this.mHideAnimation.cancel();
      this.mHideAnimation = null;
    }
    if (this.mShowedView != null)
      this.mShowedView.close(false);
    removeAllViews();
    this.mShowedView = null;
    this.mLastType = paramInt;
    return true;
  }

  @TargetApi(11)
  private void dimBehindIn()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      ObjectAnimator localObjectAnimator = (ObjectAnimator)AnimatorInflater.loadAnimator(getContext(), 2131034112);
      localObjectAnimator.setTarget(getBackground());
      localObjectAnimator.start();
    }
  }

  @TargetApi(11)
  private void dimBehindOut()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      ObjectAnimator localObjectAnimator = (ObjectAnimator)AnimatorInflater.loadAnimator(getContext(), 2131034113);
      localObjectAnimator.setTarget(getBackground());
      localObjectAnimator.start();
    }
  }

  private Animation getAnimation(int paramInt)
  {
    switch (paramInt)
    {
    case 2:
    case 9:
    case 11:
    case 13:
    case 15:
    case 19:
    case 21:
    case 22:
    case 23:
    default:
      return null;
    case 4:
    case 6:
    case 8:
    case 12:
    case 16:
    case 17:
      localObject = new TranslateAnimation(0.0F, 0.0F, this.menuLayout.height, 0.0F);
      ((Animation)localObject).setDuration(this.mAnimationLengthDefault);
      ((Animation)localObject).setInterpolator(getContext(), 2131099656);
      ((Animation)localObject).setFillAfter(true);
      this.mHideAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, this.menuLayout.height);
      this.mHideAnimation.setDuration(this.mAnimationLengthDefault);
      this.mHideAnimation.setFillAfter(true);
      this.mHideAnimation.setInterpolator(getContext(), 2131099648);
      this.mHideAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          if (QtBubbleView.this.mHideAnimation != null)
            QtBubbleView.this.mHideAnimation.cancel();
          QtBubbleView.this.dispatchActionEvent("cancelPop", null);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      });
      return localObject;
    case 1:
    case 10:
      localObject = new TranslateAnimation(0.0F, 0.0F, this.immenuLayout.height, 0.0F);
      ((Animation)localObject).setDuration(this.mAnimationLengthDefault);
      ((Animation)localObject).setInterpolator(getContext(), 2131099656);
      ((Animation)localObject).setFillAfter(true);
      this.mHideAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, this.immenuLayout.height);
      this.mHideAnimation.setDuration(this.mAnimationLengthDefault);
      this.mHideAnimation.setFillAfter(true);
      this.mHideAnimation.setInterpolator(getContext(), 2131099648);
      this.mHideAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          if (QtBubbleView.this.mHideAnimation != null)
            QtBubbleView.this.mHideAnimation.cancel();
          QtBubbleView.this.dispatchActionEvent("cancelPop", null);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      });
      return localObject;
    case 14:
      localObject = new TranslateAnimation(0.0F, 0.0F, this.menuLayout.height, 0.0F);
      ((Animation)localObject).setDuration(this.mAnimationLengthDefault);
      ((Animation)localObject).setInterpolator(getContext(), 2131099656);
      ((Animation)localObject).setFillAfter(true);
      this.mHideAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, this.menuLayout.height);
      this.mHideAnimation.setDuration(this.mAnimationLengthDefault);
      this.mHideAnimation.setFillAfter(true);
      this.mHideAnimation.setInterpolator(getContext(), 2131099648);
      this.mHideAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          if (QtBubbleView.this.mHideAnimation != null)
            QtBubbleView.this.mHideAnimation.cancel();
          QtBubbleView.this.dispatchActionEvent("cancelPop", null);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      });
      return localObject;
    case 7:
    case 24:
      localObject = new TranslateAnimation(0.0F, 0.0F, this.standardLayout.height * 2 / 3, 0.0F);
      ((Animation)localObject).setDuration(this.mAnimationLengthDefault);
      ((Animation)localObject).setInterpolator(getContext(), 2131099656);
      ((Animation)localObject).setFillAfter(true);
      this.mHideAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, this.standardLayout.height * 2 / 3);
      this.mHideAnimation.setDuration(this.mAnimationLengthDefault);
      this.mHideAnimation.setFillAfter(true);
      this.mHideAnimation.setInterpolator(getContext(), 2131099648);
      this.mHideAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          if (QtBubbleView.this.mHideAnimation != null)
            QtBubbleView.this.mHideAnimation.cancel();
          QtBubbleView.this.dispatchActionEvent("cancelPop", null);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      });
      return localObject;
    case 5:
      localObject = AnimationUtils.loadAnimation(getContext(), 2130968576);
      this.mHideAnimation = AnimationUtils.loadAnimation(getContext(), 2130968577);
      this.mHideAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          if (QtBubbleView.this.mHideAnimation != null)
            QtBubbleView.this.mHideAnimation.cancel();
          QtBubbleView.this.dispatchActionEvent("cancelPop", null);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      });
      return localObject;
    case 3:
      localObject = AnimationUtils.loadAnimation(getContext(), 2130968576);
      this.mHideAnimation = AnimationUtils.loadAnimation(getContext(), 2130968577);
      this.mHideAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          if (QtBubbleView.this.mHideAnimation != null)
            QtBubbleView.this.mHideAnimation.cancel();
          QtBubbleView.this.dispatchActionEvent("cancelPop", null);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      });
      return localObject;
    case 18:
      localObject = new TranslateAnimation(0.0F, 0.0F, -this.standardLayout.height, 0.0F);
      ((Animation)localObject).setDuration(this.mAnimationLengthDefault);
      ((Animation)localObject).setInterpolator(getContext(), 2131099656);
      ((Animation)localObject).setFillAfter(true);
      this.mHideAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, -this.standardLayout.height);
      this.mHideAnimation.setDuration(this.mAnimationLengthDefault);
      this.mHideAnimation.setFillAfter(true);
      this.mHideAnimation.setInterpolator(getContext(), 2131099648);
      this.mHideAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          if (QtBubbleView.this.mHideAnimation != null)
            QtBubbleView.this.mHideAnimation.cancel();
          QtBubbleView.this.dispatchActionEvent("cancelPop", null);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      });
      return localObject;
    case 20:
    }
    Object localObject = new TranslateAnimation(0.0F, 0.0F, this.collectionRemindLayout.height, 0.0F);
    ((Animation)localObject).setDuration(this.mAnimationLengthDefault);
    ((Animation)localObject).setInterpolator(getContext(), 2131099656);
    ((Animation)localObject).setFillAfter(true);
    this.mHideAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, this.collectionRemindLayout.height);
    this.mHideAnimation.setDuration(this.mAnimationLengthDefault);
    this.mHideAnimation.setFillAfter(true);
    this.mHideAnimation.setInterpolator(getContext(), 2131099648);
    this.mHideAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        if (QtBubbleView.this.mHideAnimation != null)
          QtBubbleView.this.mHideAnimation.cancel();
        QtBubbleView.this.dispatchActionEvent("cancelPop", null);
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    return localObject;
  }

  public void clearAnimation()
  {
    this.mHideAnimation = null;
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mShowedView != null)
      return this.mShowedView.getView().dispatchTouchEvent(paramMotionEvent);
    return false;
  }

  public int getBubbleType()
  {
    return this.mLastType;
  }

  public IView getShowingView()
  {
    return this.mShowedView;
  }

  public void hide()
  {
    if ((this.mHideAnimation != null) && (this.mShowedView != null))
    {
      this.mShowedView.getView().startAnimation(this.mHideAnimation);
      this.mHideAnimation = null;
    }
  }

  public boolean isAnimationAvailable()
  {
    return this.mHideAnimation != null;
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("cancelPop"))
    {
      if (this.mHideAnimation == null)
      {
        dispatchActionEvent(paramString, paramObject2);
        return;
      }
      dispatchActionEvent("startDimBackAnimation", paramObject2);
      hide();
      return;
    }
    if (paramString.equalsIgnoreCase("cancelPopWithoutAnimation"))
    {
      this.mHideAnimation = null;
      if (this.mShowedView != null)
        this.mShowedView.getView().clearAnimation();
      dispatchActionEvent("cancelPop", paramObject2);
      return;
    }
    dispatchActionEvent(paramString, paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.mShowedView != null)
      this.mShowedView.getView().layout(paramInt1, 0, paramInt3, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.menuLayout.scaleToBounds(this.standardLayout);
    this.immenuLayout.scaleToBounds(this.standardLayout);
    this.collectionRemindLayout.scaleToBounds(this.standardLayout);
    if (this.mShowedView != null)
      this.standardLayout.measureView(this.mShowedView.getView());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void onViewHidden()
  {
    if (this.mShowedView != null)
      this.mShowedView.update("hide", null);
  }

  public void setViewByType(int paramInt, Object paramObject)
  {
    boolean bool = clearUnNeededState(paramInt);
    setBackgroundColor(-1728053248);
    switch (paramInt)
    {
    case 19:
    case 21:
    case 26:
    case 27:
    case 28:
    case 29:
    case 30:
    case 31:
    default:
    case 8:
    case 1:
    case 13:
    case 9:
    case 10:
    case 11:
    case 12:
    case 7:
    case 6:
    case 5:
    case 4:
    case 32:
    case 3:
    case 2:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 20:
    case 22:
    case 23:
    case 24:
    case 25:
    }
    while (true)
    {
      if ((bool) && (this.mShowedView != null))
        this.mShowedView.setEventHandler(this);
      if (this.mShowedView != null)
      {
        this.mShowedView.update("setBubbleData", paramObject);
        if (bool)
          addView(this.mShowedView.getView());
        requestLayout();
        paramObject = getAnimation(paramInt);
        if (paramObject != null)
          this.mShowedView.getView().startAnimation(paramObject);
      }
      return;
      if (bool)
      {
        this.mShowedView = new MainMenuView(getContext());
        continue;
        if (bool)
        {
          this.mShowedView = new ImMenuView(getContext());
          continue;
          if (bool)
          {
            this.mShowedView = new AdvertisementView(getContext());
            continue;
            if (bool)
            {
              this.mShowedView = new DjAlarmPopView(getContext());
              continue;
              if (bool)
              {
                this.mShowedView = new FeedbackPopViewNew(getContext());
                continue;
                if (bool)
                {
                  this.mShowedView = new FmScanPopView(getContext());
                  continue;
                  if (bool)
                  {
                    this.mShowedView = new AccountPopView(getContext());
                    continue;
                    if (bool)
                    {
                      this.mShowedView = new SchedulePopView(getContext());
                      continue;
                      if (bool)
                      {
                        this.mShowedView = new CustomPopActionView(getContext());
                        continue;
                        if (bool)
                        {
                          this.mShowedView = new AlertPopView(getContext());
                          continue;
                          QTMSGManage.getInstance().sendStatistcsMessage("showSharePlatform");
                          if (bool)
                          {
                            this.mShowedView = new SharePopView(getContext());
                            continue;
                            QTMSGManage.getInstance().sendStatistcsMessage("showSharePlatform");
                            if (bool)
                            {
                              this.mShowedView = new ShareMessagePopuView(getContext());
                              continue;
                              if (bool)
                              {
                                this.mShowedView = new ChannelInfoView(getContext());
                                continue;
                                if (bool)
                                {
                                  this.mShowedView = new ErrorLogPopView(getContext());
                                  continue;
                                  if (bool)
                                  {
                                    this.mShowedView = new ChatUserActionPopView(getContext());
                                    continue;
                                    if (bool)
                                    {
                                      this.mShowedView = new VoicePopupView(getContext());
                                    }
                                    else
                                    {
                                      ((VoicePopupView)this.mShowedView).startSpeech();
                                      continue;
                                      if (bool)
                                      {
                                        this.mShowedView = new ImBlockRemindPopView(getContext());
                                        continue;
                                        if (bool)
                                        {
                                          this.mShowedView = new ImBlockRemovePopView(getContext());
                                          continue;
                                          if (bool)
                                          {
                                            this.mShowedView = new CategoryFilterPopView(getContext());
                                            continue;
                                            if (bool)
                                            {
                                              this.mShowedView = new CollectionRemindPopView(getContext());
                                              continue;
                                              if (bool)
                                              {
                                                this.mShowedView = new OnlineUpdatePopuView(getContext());
                                                continue;
                                                if (bool)
                                                  this.mShowedView = new ToastPopupView(getContext());
                                                setBackgroundColor(0);
                                                continue;
                                                if (bool)
                                                  this.mShowedView = new TopHistoryPopView(getContext());
                                                setBackgroundColor(0);
                                                continue;
                                                if (bool)
                                                  this.mShowedView = new FlowPopView(getContext());
                                                setBackgroundColor(0);
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("dimin"))
      dimBehindIn();
    while (!paramString.equalsIgnoreCase("dimout"))
      return;
    dimBehindOut();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.QtBubbleView
 * JD-Core Version:    0.6.2
 */