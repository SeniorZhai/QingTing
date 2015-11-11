package fm.qingting.qtradio.view.podcaster;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View.MeasureSpec;
import android.widget.Toast;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.helper.PodcasterHelper;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.utils.QTMSGManage;
import java.util.List;

public class PodcasterInfoFollowBtn extends QtView
  implements ViewElement.OnElementClickListener, RootNode.IInfoUpdateEventListener, InfoManager.ISubscribeEventListener
{
  private final ViewLayout followLayout = this.standardLayout.createChildLT(160, 60, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mFollowBtn;
  private boolean mIsFollow = false;
  private UserInfo mPodcasterInfo;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 60, 720, 60, 0, 0, ViewLayout.FILL);

  public PodcasterInfoFollowBtn(Context paramContext)
  {
    super(paramContext);
    this.mFollowBtn = new ButtonViewElement(paramContext);
    this.mFollowBtn.setBackground(2130837887, 2130837886);
    addElement(this.mFollowBtn);
    this.mFollowBtn.setOnElementClickListener(this);
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 10);
  }

  private void doFollow()
  {
    Object localObject;
    if (!CloudCenter.getInstance().isLogin(false))
    {
      localObject = new CloudCenter.OnLoginEventListerner()
      {
        public void onLoginFailed(int paramAnonymousInt)
        {
        }

        public void onLoginSuccessed(int paramAnonymousInt)
        {
          Toast.makeText(PodcasterInfoFollowBtn.this.getContext(), "请再次点击关注按钮", 0).show();
        }
      };
      EventDispacthManager.getInstance().dispatchAction("showLogin", localObject);
    }
    do
    {
      return;
      localObject = InfoManager.getInstance().getUserProfile();
    }
    while ((((UserProfile)localObject).getUserInfo() == null) || (TextUtils.isEmpty(((UserProfile)localObject).getUserInfo().snsInfo.sns_id)));
    long l = 0L;
    if ((this.mPodcasterInfo.getProgramNodes() != null) && (this.mPodcasterInfo.getProgramNodes().size() > 0))
      l = ((ProgramNode)this.mPodcasterInfo.getProgramNodes().get(0)).getUpdateTime();
    while (true)
    {
      PodcasterHelper.getInstance().addMyPodcaster(this.mPodcasterInfo.podcasterId, ((UserProfile)localObject).getUserInfo().snsInfo.sns_id, l);
      InfoManager.getInstance().getUserProfile().followUser(this.mPodcasterInfo);
      Toast.makeText(getContext(), "关注成功", 0).show();
      localObject = this.mPodcasterInfo;
      ((UserInfo)localObject).fansNumber += 1L;
      return;
      InfoManager.getInstance().loadPodcasterLatestInfo(this.mPodcasterInfo.podcasterId, this);
    }
  }

  private void setFollowBtn()
  {
    this.mIsFollow = false;
    if ((this.mPodcasterInfo != null) && (CloudCenter.getInstance().isLogin(false)))
    {
      UserProfile localUserProfile = InfoManager.getInstance().getUserProfile();
      if ((localUserProfile.getUserInfo() != null) && (!TextUtils.isEmpty(localUserProfile.getUserInfo().snsInfo.sns_id)))
        this.mIsFollow = PodcasterHelper.getInstance().isMyPodcaster(this.mPodcasterInfo.podcasterId, localUserProfile.getUserInfo().snsInfo.sns_id);
    }
    Log.d("PodcasterInfoHeaderView", "follow = " + this.mIsFollow);
    if (this.mIsFollow)
      this.mFollowBtn.setBackground(2130837889, 2130837888);
    while (true)
    {
      invalidate();
      return;
      this.mFollowBtn.setBackground(2130837887, 2130837886);
    }
  }

  public void close(boolean paramBoolean)
  {
    InfoManager.getInstance().root().unRegisterInfoUpdateListener(10, this);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RECV_PODCASTER_LATEST" });
    super.close(paramBoolean);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (this.mPodcasterInfo != null)
    {
      if (!this.mIsFollow)
        break label137;
      paramViewElement = InfoManager.getInstance().getUserProfile();
      if ((paramViewElement.getUserInfo() != null) && (!TextUtils.isEmpty(paramViewElement.getUserInfo().snsInfo.sns_id)))
      {
        PodcasterHelper.getInstance().removeMyPodcaster(this.mPodcasterInfo.podcasterId, paramViewElement.getUserInfo().snsInfo.sns_id);
        InfoManager.getInstance().getUserProfile().unfollowUser(this.mPodcasterInfo.userKey);
        Toast.makeText(getContext(), "取消关注成功", 0).show();
        paramViewElement = this.mPodcasterInfo;
        paramViewElement.fansNumber -= 1L;
      }
    }
    while (true)
    {
      InfoManager.getInstance().root().setInfoUpdate(10);
      QTMSGManage.getInstance().sendStatistcsMessage("PodcasterInfo", "关注/取消关注主播");
      return;
      label137: doFollow();
    }
  }

  public void onInfoUpdated(int paramInt)
  {
    if (paramInt == 10)
      setFollowBtn();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.followLayout.scaleToBounds(this.standardLayout);
    int i = (this.standardLayout.width - this.followLayout.width) / 2;
    this.mFollowBtn.measure(i, 0, this.followLayout.width + i, this.followLayout.height);
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    if ((paramString.equalsIgnoreCase("RECV_PODCASTER_LATEST")) && (CloudCenter.getInstance().isLogin(false)))
    {
      this.mPodcasterInfo = PodcasterHelper.getInstance().getPodcaster(this.mPodcasterInfo.podcasterId);
      if ((this.mPodcasterInfo != null) && (this.mPodcasterInfo.getProgramNodes() != null) && (this.mPodcasterInfo.getProgramNodes().size() > 0))
      {
        long l = ((ProgramNode)this.mPodcasterInfo.getProgramNodes().get(0)).getUpdateTime();
        paramString = InfoManager.getInstance().getUserProfile();
        if ((paramString != null) && (paramString.getUserInfo() != null) && (!TextUtils.isEmpty(paramString.getUserInfo().snsInfo.sns_id)))
          PodcasterHelper.getInstance().updateMyPodcasterLastestProgramId(this.mPodcasterInfo.podcasterId, paramString.getUserInfo().snsInfo.sns_id, l);
      }
    }
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mPodcasterInfo = ((UserInfo)paramObject);
      setFollowBtn();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.podcaster.PodcasterInfoFollowBtn
 * JD-Core Version:    0.6.2
 */