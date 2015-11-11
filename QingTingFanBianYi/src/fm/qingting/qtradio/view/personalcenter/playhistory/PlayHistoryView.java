package fm.qingting.qtradio.view.personalcenter.playhistory;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
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
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayHistoryInfoNode;
import fm.qingting.qtradio.model.PlayHistoryNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.qtradio.view.personalcenter.mydownload.EmptyTipsView;
import fm.qingting.qtradio.view.personalcenter.mydownload.GeneralManageView;
import java.util.ArrayList;
import java.util.List;

public class PlayHistoryView extends ViewGroupViewImpl
  implements IEventHandler, IMotionHandler
{
  private final ViewLayout checkbgLayout = this.standardLayout.createChildLT(48, 48, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private int mButtonOffset = 0;
  private GeneralManageView mConfirmView;
  private EmptyTipsView mEmptyView;
  private PlayHistoryListView mListView;
  private MiniPlayerView mPlayerView;
  private MotionController motionController;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public PlayHistoryView(Context paramContext)
  {
    super(paramContext);
    try
    {
      setBackgroundColor(SkinManager.getBackgroundColor());
      this.mEmptyView = new EmptyTipsView(paramContext, 1);
      addView(this.mEmptyView);
      this.mListView = new PlayHistoryListView(paramContext);
      this.mListView.setEventHandler(this);
      addView(this.mListView);
      this.mListView.setEmptyView(this.mEmptyView);
      this.mPlayerView = new MiniPlayerView(paramContext);
      addView(this.mPlayerView);
      this.mConfirmView = new GeneralManageView(paramContext);
      this.mConfirmView.setEventHandler(this);
      addView(this.mConfirmView);
      init();
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      while (true)
        localOutOfMemoryError.printStackTrace();
    }
  }

  private void deleteSelected(List<Object> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      int i = paramList.size() - 1;
      while (i >= 0)
      {
        InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.delPlayHistoryNode(((PlayHistoryNode)paramList.get(i)).playNode);
        i -= 1;
      }
    }
    this.mListView.update("resetCheckList", null);
    if (InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes().size() == 0)
      dispatchActionEvent("emptynow", null);
  }

  @TargetApi(11)
  private void hideDeleteButton()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      localObject = ObjectAnimator.ofFloat(this.mConfirmView, "translationY", new float[] { 0.0F });
      ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.mPlayerView, "alpha", new float[] { 1.0F });
      AnimatorSet localAnimatorSet = new AnimatorSet();
      localAnimatorSet.play((Animator)localObject).with(localObjectAnimator);
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
    this.mConfirmView.layout(0, this.mButtonOffset + this.standardLayout.height, this.standardLayout.width, this.mButtonOffset + this.standardLayout.height + this.mPlayerView.getHeightWithoutShadow());
    this.mPlayerView.layout(0, this.standardLayout.height - this.mPlayerView.getMeasuredHeight() - this.mButtonOffset, this.standardLayout.width, this.standardLayout.height - this.mButtonOffset);
  }

  @TargetApi(11)
  private void showDeleteButton()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      localObject = ObjectAnimator.ofFloat(this.mConfirmView, "translationY", new float[] { -this.mPlayerView.getHeightWithoutShadow() });
      ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.mPlayerView, "alpha", new float[] { 0.0F });
      AnimatorSet localAnimatorSet = new AnimatorSet();
      localAnimatorSet.play((Animator)localObject).with(localObjectAnimator);
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
    this.mEmptyView.close(paramBoolean);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    int j = 0;
    if (paramString.equalsIgnoreCase("stateChanged"))
      if (((Boolean)paramObject2).booleanValue())
        this.mConfirmView.update(paramString, Boolean.valueOf(true));
    int i;
    do
    {
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
      }
      while (!paramString.equalsIgnoreCase("delete"));
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
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    this.mEmptyView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    layoutMoveableViews();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.checkbgLayout.scaleToBounds(this.standardLayout);
    this.standardLayout.measureView(this.mConfirmView);
    this.standardLayout.measureView(this.mPlayerView);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    this.mEmptyView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
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
    this.mButtonOffset = ((int)(-this.mPlayerView.getHeightWithoutShadow() * paramFloat1));
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
    }
    while (!paramString.equalsIgnoreCase("resetCheckList"));
    this.mListView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.playhistory.PlayHistoryView
 * JD-Core Version:    0.6.2
 */