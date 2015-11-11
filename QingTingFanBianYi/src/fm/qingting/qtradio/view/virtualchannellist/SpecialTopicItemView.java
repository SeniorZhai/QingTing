package fm.qingting.qtradio.view.virtualchannellist;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.qtradio.view.virtualchannels.RatingElement;
import fm.qingting.utils.TimeUtil;

public class SpecialTopicItemView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(146, 146, 20, 22, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout collectionLayout = this.itemLayout.createChildLT(90, 90, 600, 45, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout heatLabelLayout = this.itemLayout.createChildLT(22, 22, 186, 143, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout heatTextLayout = this.itemLayout.createChildLT(100, 40, 210, 134, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout hostLabelLayout = this.itemLayout.createChildLT(22, 22, 364, 143, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout hostTextLayout = this.itemLayout.createChildLT(130, 40, 388, 134, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(400, 45, 186, 75, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 190, 720, 190, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private NetImageViewElement mAvatarElement;
  private ButtonViewElement mBg;
  private ImageViewElement mCollectionIe;
  private ImageViewElement mHeatLabelElement;
  private TextViewElement mHeatTextElement;
  private ImageViewElement mHostLabelElement;
  private TextViewElement mHostTextElement;
  private TextViewElement mInfoElement;
  private LineElement mLineElement;
  private ChannelNode mNode;
  private RatingElement mRatingElement;
  private TextViewElement mTimeElement;
  private TextViewElement mTitleElement;
  private final ViewLayout ratingLayout = this.itemLayout.createChildLT(136, 30, 186, 134, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout timeLayout = this.itemLayout.createChildLT(180, 40, 520, 134, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(400, 45, 186, 18, ViewLayout.SCALE_FLAG_SLTCW);

  public SpecialTopicItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(0, SkinManager.getCardColor());
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(this);
    this.mAvatarElement = new NetImageViewElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837907);
    this.mAvatarElement.setBoundColor(SkinManager.getDividerColor());
    addElement(this.mAvatarElement, paramInt);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setColor(SkinManager.getTextColorNormal());
    this.mTitleElement.setMaxLineLimit(1);
    addElement(this.mTitleElement);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setColor(SkinManager.getTextColorSecondLevel());
    this.mInfoElement.setMaxLineLimit(1);
    addElement(this.mInfoElement);
    this.mRatingElement = new RatingElement(paramContext);
    addElement(this.mRatingElement);
    this.mTimeElement = new TextViewElement(paramContext);
    this.mTimeElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mTimeElement.setMaxLineLimit(1);
    this.mTimeElement.setAlignment(Layout.Alignment.ALIGN_OPPOSITE);
    addElement(this.mTimeElement);
    this.mHeatLabelElement = new ImageViewElement(paramContext);
    this.mHeatLabelElement.setImageRes(2130837750);
    this.mHeatTextElement = new TextViewElement(paramContext);
    this.mHeatTextElement.setColor(SkinManager.getTextColorHeat());
    this.mHeatTextElement.setMaxLineLimit(1);
    this.mHostLabelElement = new ImageViewElement(paramContext);
    this.mHostLabelElement.setImageRes(2130837751);
    this.mHostTextElement = new TextViewElement(paramContext);
    this.mHostTextElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mHostTextElement.setMaxLineLimit(1);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
    this.mCollectionIe = new ImageViewElement(paramContext);
    this.mCollectionIe.setImageRes(2130837560);
    this.mCollectionIe.setOnElementClickListener(this);
    addElement(this.mCollectionIe);
  }

  private String getSubInfo()
  {
    if (this.mNode.isNovelChannel())
      return this.mNode.desc;
    if ((this.mNode.latest_program != null) && (this.mNode.latest_program.length() > 0))
      return this.mNode.latest_program;
    return this.mNode.desc;
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement == this.mCollectionIe)
    {
      if (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(this.mNode))
      {
        InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.deleteFavNode(this.mNode);
        this.mCollectionIe.setImageRes(2130837560);
        MobclickAgent.onEvent(getContext(), "deleteTopicCollection");
        TCAgent.onEvent(getContext(), "deleteTopicCollection");
        return;
      }
      InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.addFavNode(this.mNode);
      this.mCollectionIe.setImageRes(2130837559);
      MobclickAgent.onEvent(getContext(), "addTopicCollection");
      TCAgent.onEvent(getContext(), "addTopicCollection");
      return;
    }
    ControllerManager.getInstance().setChannelSource(1);
    if ((this.mNode.nodeName.equalsIgnoreCase("channel")) && (this.mNode.channelType == 0))
    {
      PlayerAgent.getInstance().addPlaySource(41);
      paramViewElement = QTLogger.getInstance().buildSpecialTopicClickLog(this.mNode.channelId, this.mNode.channelType);
      if (paramViewElement != null)
        LogModule.getInstance().send("topic_click_v6", paramViewElement);
      ControllerManager.getInstance().openPlayController(this.mNode.categoryId, this.mNode.channelId, 0, 0, this.mNode.title, true);
      return;
    }
    if (PlayerAgent.getInstance().isPlaying())
      ControllerManager.getInstance().openChannelDetailController(this.mNode, false, false);
    while (true)
    {
      PlayerAgent.getInstance().addPlaySource(41);
      paramViewElement = QTLogger.getInstance().buildSpecialTopicClickLog(this.mNode.channelId, this.mNode.channelType);
      if (paramViewElement == null)
        break;
      LogModule.getInstance().send("topic_click_v6", paramViewElement);
      return;
      ControllerManager.getInstance().openChannelDetailController(this.mNode, true, false);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.ratingLayout.scaleToBounds(this.itemLayout);
    this.timeLayout.scaleToBounds(this.itemLayout);
    this.heatLabelLayout.scaleToBounds(this.itemLayout);
    this.heatTextLayout.scaleToBounds(this.itemLayout);
    this.hostLabelLayout.scaleToBounds(this.itemLayout);
    this.hostTextLayout.scaleToBounds(this.itemLayout);
    this.collectionLayout.scaleToBounds(this.itemLayout);
    this.mCollectionIe.measure(this.collectionLayout);
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mAvatarElement.setBoundLineWidth(this.lineLayout.height);
    this.mTitleElement.measure(this.titleLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mRatingElement.measure(this.ratingLayout);
    this.mTimeElement.measure(this.timeLayout);
    this.mLineElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.lineLayout.getRight(), this.itemLayout.height);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mTimeElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    this.mHeatLabelElement.measure(this.heatLabelLayout);
    this.mHeatTextElement.measure(this.heatTextLayout);
    this.mHostLabelElement.measure(this.hostLabelLayout);
    this.mHostTextElement.measure(this.hostTextLayout);
    this.mHeatTextElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    this.mHostTextElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mNode = ((ChannelNode)paramObject);
      this.mAvatarElement.setImageUrl(this.mNode.getApproximativeThumb(146, 146, true));
      this.mTitleElement.setText(this.mNode.title, false);
      this.mInfoElement.setText(getSubInfo(), false);
      this.mRatingElement.setRating(this.mNode.ratingStar);
      this.mRatingElement.setData(this.mNode);
      this.mTimeElement.setText(TimeUtil.getReadableTime(this.mNode.getUpdateTime()), false);
      if (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(this.mNode.channelId))
        this.mCollectionIe.setImageRes(2130837559);
    }
    else
    {
      return;
    }
    this.mCollectionIe.setImageRes(2130837560);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannellist.SpecialTopicItemView
 * JD-Core Version:    0.6.2
 */