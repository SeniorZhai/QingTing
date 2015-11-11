package fm.qingting.qtradio.view.personalcenter.clock;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.tween.FrameTween;
import fm.qingting.framework.tween.FrameTween.SyncType;
import fm.qingting.framework.tween.IMotionHandler;
import fm.qingting.framework.tween.MotionController;
import fm.qingting.framework.tween.TweenProperty;
import fm.qingting.framework.tween.easing.Quad.EaseIn;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.AlarmInfo;
import fm.qingting.qtradio.model.AlarmInfoNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.personalcenter.mydownload.GeneralManageView;
import java.util.ArrayList;
import java.util.List;

public class AlarmView extends ViewGroupViewImpl
  implements IEventHandler, IMotionHandler
{
  private final ViewLayout bottomLayout = this.standardLayout.createChildLT(720, 92, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = this.standardLayout.createChildLT(48, 48, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private int mButtonOffset = 0;
  private GeneralManageView mConfirmView;
  private AlarmListView mListView;
  private MotionController motionController;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public AlarmView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mListView = new AlarmListView(paramContext);
    this.mListView.setEventHandler(this);
    addView(this.mListView);
    this.mConfirmView = new GeneralManageView(paramContext);
    this.mConfirmView.setEventHandler(this);
    addView(this.mConfirmView);
    init();
  }

  private void deleteSelected(List<Object> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      int i = paramList.size() - 1;
      while (i >= 0)
      {
        InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.removeAlarm((AlarmInfo)paramList.get(i));
        i -= 1;
      }
    }
    this.mListView.update("refreshlist", null);
  }

  @TargetApi(11)
  private void hideDeleteButton()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      localObject = ObjectAnimator.ofFloat(this.mConfirmView, "translationY", new float[] { 0.0F });
      AnimatorSet localAnimatorSet = new AnimatorSet();
      localAnimatorSet.play((Animator)localObject);
      localAnimatorSet.setDuration(200L);
      localAnimatorSet.start();
      return;
    }
    Object localObject = new ArrayList();
    ((List)localObject).add(new TweenProperty("position", 1.0F, 0.0F, 10.0F, new Quad.EaseIn()));
    FrameTween.to(this.motionController, this, (List)localObject, FrameTween.SyncType.ASYNC);
  }

  @TargetApi(11)
  private void init()
  {
    if (!QtApiLevelManager.isApiLevelSupported(11))
      this.motionController = new MotionController(this);
  }

  private void layoutMoveableViews()
  {
    this.mConfirmView.layout(0, this.mButtonOffset + this.standardLayout.height, this.standardLayout.width, this.mButtonOffset + this.standardLayout.height + this.bottomLayout.height);
  }

  @TargetApi(11)
  private void showDeleteButton()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      localObject = ObjectAnimator.ofFloat(this.mConfirmView, "translationY", new float[] { -this.bottomLayout.height });
      AnimatorSet localAnimatorSet = new AnimatorSet();
      localAnimatorSet.play((Animator)localObject);
      localAnimatorSet.setDuration(200L);
      localAnimatorSet.start();
      return;
    }
    Object localObject = new ArrayList();
    ((List)localObject).add(new TweenProperty("position", 0.0F, 1.0F, 10.0F, new Quad.EaseIn()));
    FrameTween.to(this.motionController, this, (List)localObject, FrameTween.SyncType.ASYNC);
  }

  public void close(boolean paramBoolean)
  {
    this.mListView.close(paramBoolean);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    int j = 0;
    if (paramString.equalsIgnoreCase("stateChanged"))
      if (((Boolean)paramObject2).booleanValue())
        this.mConfirmView.update(paramString, Boolean.valueOf(true));
    do
    {
      int i;
      do
      {
        do
          return;
        while (((Boolean)this.mListView.getValue("hasCheckedIndexs", null)).booleanValue());
        this.mConfirmView.update(paramString, Boolean.valueOf(false));
        return;
        if (paramString.equalsIgnoreCase("selectAll"))
        {
          this.mListView.update(paramString, paramObject2);
          return;
        }
        if (!paramString.equalsIgnoreCase("delete"))
          break;
        paramObject1 = (List)this.mListView.getValue("deletelist", null);
        i = j;
        if (paramObject1 != null)
        {
          i = j;
          if (paramObject1.size() > 0)
            i = 0 + paramObject1.size();
        }
      }
      while (i == 0);
      deleteSelected(paramObject1);
      return;
      if (paramString.equalsIgnoreCase("onclick"))
      {
        dispatchActionEvent(paramString, paramObject2);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("select"));
    dispatchActionEvent(paramString, paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, this.standardLayout.width, this.standardLayout.height - this.bottomLayout.height);
    layoutMoveableViews();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.bottomLayout.scaleToBounds(this.standardLayout);
    this.checkbgLayout.scaleToBounds(this.standardLayout);
    this.bottomLayout.measureView(this.mConfirmView);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.bottomLayout.height, 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void onMotionCancel(MotionController paramMotionController)
  {
  }

  public void onMotionComplete(MotionController paramMotionController)
  {
  }

  public void onMotionProgress(MotionController paramMotionController, float paramFloat1, float paramFloat2)
  {
    this.mButtonOffset = ((int)(-this.bottomLayout.height * paramFloat1));
    layoutMoveableViews();
  }

  public void onMotionStart(MotionController paramMotionController)
  {
  }

  public void onTargetChange(MotionController paramMotionController, float paramFloat)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("showManage"))
    {
      this.mListView.update(paramString, Integer.valueOf(this.checkbgLayout.leftMargin + this.checkbgLayout.width));
      showDeleteButton();
    }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("hideManage"))
      {
        this.mListView.update(paramString, paramObject);
        hideDeleteButton();
        return;
      }
      if (paramString.equalsIgnoreCase("setData"))
      {
        this.mListView.update(paramString, paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("resetCheckList"))
      {
        this.mListView.update(paramString, paramObject);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("refreshList"));
    this.mListView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.clock.AlarmView
 * JD-Core Version:    0.6.2
 */