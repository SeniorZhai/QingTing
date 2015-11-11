package fm.qingting.qtradio.view.virtualchannels;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import android.view.View.MeasureSpec;
import android.widget.Toast;
import fm.qingting.framework.manager.EventDispacthManager;
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
import fm.qingting.qtradio.helper.PodcasterHelper;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.AdvertisementInfoNode;
import fm.qingting.qtradio.model.AdvertisementItemNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;
import fm.qingting.utils.QTMSGManage;
import java.util.List;

public class ChannelDetailInfoView extends QtView
  implements ViewElement.OnElementClickListener, InfoManager.ISubscribeEventListener, RootNode.IInfoUpdateEventListener
{
  static final String CHANGE_ALPHA = "ca";
  private static final float FRICTION = 1.3F;
  private final int RATING_STAR_HEIGHT = 30;
  private final int RATING_STAR_WIDTH = 137;
  private final ViewLayout descLayout = this.standardLayout.createChildLT(383, 160, 305, 40, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout helpLayout = this.standardLayout.createChildLT(55, 55, 216, 206, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect mBgRect = new Rect();
  private TextViewElement mDesc;
  private ButtonViewElement mHelp;
  private boolean mIsFollow = false;
  private ChannelNode mNode;
  private NetImageViewElement mPic;
  private RoundAvatarElement mPodcasterAvaImg;
  private ButtonViewElement mPodcasterFollowBtn;
  private int mPodcasterId;
  private UserInfo mPodcasterInfo;
  private TextViewElement mPodcasterNameTxt;
  private RatingElement mRatingStar;
  private TextViewElement mRatingTitle;
  private final ViewLayout picLayout = this.standardLayout.createChildLT(250, 250, 30, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout podcasterAvaLayout = this.standardLayout.createChildLT(80, 80, 310, 195, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout podcasterFollowLayout = this.standardLayout.createChildLT(128, 60, 565, 205, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout podcasterNameLayout = this.standardLayout.createChildLT(140, 40, 400, 220, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout ratingStarLayout = this.standardLayout.createChildLT(137, 30, 115, 293, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout ratingTitleLayout = this.standardLayout.createChildLT(80, 30, 30, 280, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 325, 720, 325, 0, 0, ViewLayout.FILL);

  public ChannelDetailInfoView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mPic = new NetImageViewElement(paramContext);
    this.mPic.setDefaultImageRes(2130837907);
    addElement(this.mPic, i);
    this.mPic.setOnElementClickListener(this);
    this.mDesc = new TextViewElement(paramContext);
    this.mDesc.setMaxLineLimit(3);
    this.mDesc.setColor(SkinManager.getTextColorNormal());
    this.mDesc.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    addElement(this.mDesc);
    this.mDesc.setOnElementClickListener(this);
    this.mHelp = new ButtonViewElement(paramContext);
    this.mHelp.setBackground(2130838031, 2130838030);
    addElement(this.mHelp);
    this.mHelp.setOnElementClickListener(this);
    this.mPodcasterAvaImg = new RoundAvatarElement(paramContext);
    this.mPodcasterAvaImg.setDefaultImageRes(2130838032);
    this.mPodcasterAvaImg.setOnElementClickListener(this);
    addElement(this.mPodcasterAvaImg, i);
    this.mPodcasterAvaImg.setVisible(4);
    this.mPodcasterNameTxt = new TextViewElement(paramContext);
    this.mPodcasterNameTxt.setMaxLineLimit(1);
    this.mPodcasterNameTxt.setColor(SkinManager.getTextColorNormal());
    this.mPodcasterNameTxt.setOnElementClickListener(this);
    addElement(this.mPodcasterNameTxt);
    this.mPodcasterNameTxt.setVisible(4);
    this.mPodcasterFollowBtn = new ButtonViewElement(paramContext);
    this.mPodcasterFollowBtn.setOnElementClickListener(this);
    addElement(this.mPodcasterFollowBtn);
    this.mPodcasterFollowBtn.setVisible(4);
    this.mRatingTitle = new TextViewElement(paramContext);
    this.mRatingTitle.setText("评分：");
    this.mRatingTitle.setColor(SkinManager.getTextColorSubInfo());
    addElement(this.mRatingTitle);
    this.mRatingStar = new RatingElement(paramContext);
    addElement(this.mRatingStar);
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
          Toast.makeText(ChannelDetailInfoView.this.getContext(), "请再次点击关注按钮", 0).show();
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

  private String getAdUrl()
  {
    if (this.mNode != null)
    {
      AdvertisementItemNode localAdvertisementItemNode = InfoManager.getInstance().root().mAdvertisementInfoNode.getAdvertisement(this.mNode.channelId);
      if (localAdvertisementItemNode != null)
        return localAdvertisementItemNode.landing;
    }
    return null;
  }

  private void setFollowBtn(UserInfo paramUserInfo)
  {
    this.mIsFollow = false;
    if ((paramUserInfo != null) && (CloudCenter.getInstance().isLogin(false)))
    {
      UserProfile localUserProfile = InfoManager.getInstance().getUserProfile();
      if ((localUserProfile.getUserInfo() != null) && (!TextUtils.isEmpty(localUserProfile.getUserInfo().snsInfo.sns_id)))
        this.mIsFollow = PodcasterHelper.getInstance().isMyPodcaster(paramUserInfo.podcasterId, localUserProfile.getUserInfo().snsInfo.sns_id);
    }
    if (this.mIsFollow)
      this.mPodcasterFollowBtn.setBackground(2130838036, 2130838035);
    while (true)
    {
      invalidate();
      return;
      this.mPodcasterFollowBtn.setBackground(2130838034, 2130838033);
    }
  }

  private void setPodcasterInfo(UserInfo paramUserInfo)
  {
    if ((paramUserInfo != null) && (paramUserInfo.snsInfo != null))
    {
      this.mPodcasterAvaImg.setImageUrl(paramUserInfo.snsInfo.sns_avatar);
      this.mPodcasterNameTxt.setText(paramUserInfo.podcasterName);
      setFollowBtn(paramUserInfo);
      this.mPodcasterAvaImg.setVisible(0);
      this.mPodcasterNameTxt.setVisible(0);
      this.mPodcasterFollowBtn.setVisible(0);
      return;
    }
    this.mPodcasterAvaImg.setVisible(4);
    this.mPodcasterNameTxt.setVisible(4);
    this.mPodcasterFollowBtn.setVisible(4);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().root().unRegisterInfoUpdateListener(10, this);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RECV_PODCASTER_BASEINFO", "RECV_PODCASTER_LATEST" });
    super.close(paramBoolean);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.mPodcasterAvaImg.getVisiblity() == 4)
      this.mDesc.setTranslationY((this.picLayout.height - this.mDesc.getHeight()) / 2);
    while (true)
    {
      super.onDraw(paramCanvas);
      return;
      this.mDesc.setTranslationY(0);
    }
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if ((paramViewElement == this.mPodcasterAvaImg) || (paramViewElement == this.mPodcasterNameTxt))
      if (this.mPodcasterInfo != null)
      {
        ControllerManager.getInstance().openPodcasterInfoController(this.mPodcasterInfo);
        QTMSGManage.getInstance().sendStatistcsMessage("scheduleassembleclick", "打开主播个人页");
      }
    do
    {
      return;
      if (paramViewElement != this.mPodcasterFollowBtn)
        break;
    }
    while (this.mPodcasterInfo == null);
    if (this.mIsFollow)
    {
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
      QTMSGManage.getInstance().sendStatistcsMessage("scheduleassembleclick", "关注/取消关注主播");
      return;
      doFollow();
    }
    if (paramViewElement == this.mDesc)
      QTMSGManage.getInstance().sendStatistcsMessage("channelinfo_click", "desc");
    while (true)
    {
      paramViewElement = getAdUrl();
      if ((paramViewElement == null) || (paramViewElement.equalsIgnoreCase("")))
        break;
      ControllerManager.getInstance().redirectToActiviyByUrl(paramViewElement, null, true);
      return;
      QTMSGManage.getInstance().sendStatistcsMessage("channelinfo_click", "thumb");
    }
    EventDispacthManager.getInstance().dispatchAction("showChannelInfo", this.mNode);
  }

  public void onInfoUpdated(int paramInt)
  {
    if (paramInt == 10)
      setFollowBtn(this.mPodcasterInfo);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.picLayout.scaleToBounds(this.standardLayout);
    this.descLayout.scaleToBounds(this.standardLayout);
    this.helpLayout.scaleToBounds(this.standardLayout);
    this.podcasterAvaLayout.scaleToBounds(this.standardLayout);
    this.podcasterNameLayout.scaleToBounds(this.standardLayout);
    this.podcasterFollowLayout.scaleToBounds(this.standardLayout);
    this.ratingTitleLayout.scaleToBounds(this.standardLayout);
    this.ratingStarLayout.scaleToBounds(this.standardLayout);
    this.mPic.measure(this.picLayout);
    this.mDesc.measure(this.descLayout);
    this.mHelp.measure(this.helpLayout);
    this.mPodcasterAvaImg.measure(this.podcasterAvaLayout);
    this.mPodcasterNameTxt.measure(this.podcasterNameLayout);
    this.mPodcasterFollowBtn.measure(this.podcasterFollowLayout);
    this.mDesc.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mPodcasterNameTxt.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mBgRect.set(0, 0, this.standardLayout.width, this.standardLayout.height);
    this.mRatingTitle.measure(this.ratingTitleLayout);
    this.mRatingTitle.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mRatingStar.measure(this.ratingStarLayout);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RECV_PODCASTER_BASEINFO"))
    {
      this.mPodcasterInfo = PodcasterHelper.getInstance().getPodcaster(this.mPodcasterId);
      setPodcasterInfo(this.mPodcasterInfo);
    }
    long l;
    do
    {
      do
      {
        do
          return;
        while ((!paramString.equalsIgnoreCase("RECV_PODCASTER_LATEST")) || (!CloudCenter.getInstance().isLogin(false)));
        this.mPodcasterInfo = PodcasterHelper.getInstance().getPodcaster(this.mPodcasterInfo.podcasterId);
      }
      while ((this.mPodcasterInfo == null) || (this.mPodcasterInfo.getProgramNodes() == null) || (this.mPodcasterInfo.getProgramNodes().size() <= 0));
      l = ((ProgramNode)this.mPodcasterInfo.getProgramNodes().get(0)).getUpdateTime();
      paramString = InfoManager.getInstance().getUserProfile();
    }
    while ((paramString == null) || (paramString.getUserInfo() == null) || (TextUtils.isEmpty(paramString.getUserInfo().snsInfo.sns_id)));
    PodcasterHelper.getInstance().updateMyPodcasterLastestProgramId(this.mPodcasterInfo.podcasterId, paramString.getUserInfo().snsInfo.sns_id, l);
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
    Log.e("ChannelDtailInfoView", "无法获取到主播数据");
  }

  @TargetApi(11)
  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("ca"))
      if (QtApiLevelManager.isApiLevelSupported(11))
        setAlpha(1.0F - ((Float)paramObject).floatValue() * 1.3F);
    label158: label188: label255: 
    do
    {
      do
      {
        return;
        if (!paramString.equalsIgnoreCase("setData"))
          break;
      }
      while (paramObject == null);
      this.mNode = ((ChannelNode)paramObject);
      paramString = this.mNode.getApproximativeThumb(250, 250, true);
      if (TextUtils.isEmpty(paramString))
        if ((this.mNode.parent != null) && (this.mNode.parent.nodeName.equalsIgnoreCase("recommenditem")))
        {
          this.mPic.setImageUrl(((RecommendItemNode)this.mNode.parent).getApproximativeThumb(250, 250));
          if (!this.mNode.isNovelChannel())
            break label255;
          this.mDesc.setText(this.mNode.desc, false);
          if ((this.mNode.lstPodcasters != null) && (this.mNode.lstPodcasters.size() != 0))
            break label272;
          setPodcasterInfo(null);
          this.mRatingStar.setRating(this.mNode.ratingStar);
          if (this.mNode.ratingStar < 0)
            break label331;
        }
      for (int i = 0; ; i = 4)
      {
        this.mRatingStar.setVisible(i);
        this.mRatingTitle.setVisible(i);
        return;
        this.mPic.setImageUrl(paramString);
        break;
        this.mPic.setImageUrl(paramString);
        break;
        this.mDesc.setText(this.mNode.desc);
        break label158;
        this.mPodcasterId = ((UserInfo)this.mNode.lstPodcasters.get(0)).podcasterId;
        this.mPodcasterInfo = PodcasterHelper.getInstance().getPodcaster(this.mPodcasterId);
        setPodcasterInfo(this.mPodcasterInfo);
        InfoManager.getInstance().loadPodcasterBaseInfo(this.mPodcasterId, this);
        break label188;
      }
    }
    while (!paramString.equalsIgnoreCase("setpodcasterinfo"));
    label272: label331: setPodcasterInfo((UserInfo)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.ChannelDetailInfoView
 * JD-Core Version:    0.6.2
 */