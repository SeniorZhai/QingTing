package fm.qingting.qtradio.view.personalcenter.mycollection;

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
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.qtradio.view.personalcenter.mydownload.GeneralManageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyCollectionView extends ViewGroupViewImpl
  implements IEventHandler, RootNode.IInfoUpdateEventListener, InfoManager.ISubscribeEventListener, IMotionHandler
{
  private final ViewLayout checkbgLayout = this.standardLayout.createChildLT(48, 48, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private int mButtonOffset = 0;
  private GeneralManageView mConfirmView;
  private CollectEmptyView mEmptyView;
  private MyCollectionListView mListView;
  private boolean mLogin;
  private MiniPlayerView mPlayerView;
  private MotionController motionController;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public MyCollectionView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mEmptyView = new CollectEmptyView(paramContext);
    addView(this.mEmptyView);
    this.mListView = new MyCollectionListView(paramContext, hashCode());
    this.mListView.setEventHandler(this);
    addView(this.mListView);
    MiniPlayerPlaceHolder.wrapListView(paramContext, this.mListView);
    this.mListView.setEmptyView(this.mEmptyView);
    this.mPlayerView = new MiniPlayerView(paramContext);
    addView(this.mPlayerView);
    this.mConfirmView = new GeneralManageView(paramContext);
    this.mConfirmView.setEventHandler(this);
    addView(this.mConfirmView);
    reviewLogin();
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 0);
    InfoManager.getInstance().registerSubscribeEventListener(this, "RUIU");
    InfoManager.getInstance().registerViewTime(this);
    init();
  }

  private void deleteSelected(List<Object> paramList)
  {
    List localList = (List)this.mListView.getValue("allData", null);
    if ((localList != null) && (paramList != null) && (getListRealSize(localList) == paramList.size()));
    for (int i = 1; ; i = 0)
    {
      if ((paramList != null) && (paramList.size() > 0))
      {
        int j = paramList.size() - 1;
        while (j >= 0)
        {
          InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.deleteFavNode((Node)paramList.get(j));
          j -= 1;
        }
      }
      paramList = InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.getFavouriteNodes();
      this.mListView.update("resetCheckList", paramList);
      if (i != 0)
        dispatchActionEvent("emptynow", null);
      return;
    }
  }

  private int getListRealSize(List<Object> paramList)
  {
    int i = 0;
    paramList = paramList.iterator();
    while (paramList.hasNext())
      if ((paramList.next() instanceof ChannelNode))
        i += 1;
    return i;
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

  private void reviewLogin()
  {
    this.mLogin = CloudCenter.getInstance().isLogin(false);
    if (this.mLogin)
    {
      this.mListView.setLoginVisibility(8);
      this.mEmptyView.setLoginVisibility(8);
    }
    while (true)
    {
      invalidate();
      return;
      this.mListView.setLoginVisibility(0);
      this.mEmptyView.setLoginVisibility(0);
    }
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
    InfoManager.getInstance().root().unRegisterInfoUpdateListener(0, this);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().unregisterViewTime(this);
    this.mPlayerView.destroy();
    this.mEmptyView.close(paramBoolean);
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

  public void onInfoUpdated(int paramInt)
  {
    if (paramInt == 0)
    {
      InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.sortCollectionNodeByPlayOrder();
      List localList = InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.getFavouriteNodes();
      this.mListView.update("setData", localList);
      if (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.getFavouriteNodes().size() == 0)
        dispatchActionEvent("emptynow", null);
    }
    else
    {
      return;
    }
    dispatchActionEvent("notEmpty", null);
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
    this.standardLayout.measureView(this.mPlayerView);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    this.mEmptyView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    this.checkbgLayout.scaleToBounds(this.standardLayout);
    this.standardLayout.measureView(this.mConfirmView);
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

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("viewTimeUpdated"))
      this.mListView.update("refreshList", null);
    while (!paramString.equalsIgnoreCase("RUIU"))
      return;
    reviewLogin();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
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
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mycollection.MyCollectionView
 * JD-Core Version:    0.6.2
 */