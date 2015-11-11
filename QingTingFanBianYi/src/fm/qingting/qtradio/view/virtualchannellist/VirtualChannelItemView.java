package fm.qingting.qtradio.view.virtualchannellist;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.qtradio.view.virtualchannels.RatingElement;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.TimeUtil;

public class VirtualChannelItemView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(150, 150, 20, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout heatLabelLayout = this.itemLayout.createChildLT(22, 22, 196, 143, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout heatTextLayout = this.itemLayout.createChildLT(100, 40, 220, 134, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout hostLabelLayout = this.itemLayout.createChildLT(22, 22, 394, 143, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout hostTextLayout = this.itemLayout.createChildLT(130, 40, 398, 134, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(514, 40, 196, 84, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 190, 720, 190, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private NetImageViewElement mAvatarElement;
  private boolean mBelongToPodcasterInfo = false;
  private ButtonViewElement mBg;
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
  private final ViewLayout ratingLayout = this.itemLayout.createChildLT(137, 30, 196, 140, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout timeLayout = this.itemLayout.createChildLT(180, 40, 520, 129, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(514, 40, 196, 29, ViewLayout.SCALE_FLAG_SLTCW);

  public VirtualChannelItemView(Context paramContext, int paramInt)
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
    this.mTitleElement.setMaxLineLimit(2);
    this.mTitleElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    addElement(this.mTitleElement);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setColor(SkinManager.getTextColorThirdLevel());
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
  }

  private String getSubInfo()
  {
    if (this.mNode.isNovelChannel())
      return this.mNode.desc;
    if ((this.mNode.latest_program != null) && (this.mNode.latest_program.length() > 0))
      return this.mNode.latest_program;
    return this.mNode.desc;
  }

  private String getUpdateTime()
  {
    return TimeUtil.getReadableTime(this.mNode.getUpdateTime());
  }

  protected void onDraw(Canvas paramCanvas)
  {
    int i = this.mTitleElement.getLineCnt();
    TextViewElement localTextViewElement = this.mInfoElement;
    if (i > 1);
    for (i = 4; ; i = 0)
    {
      localTextViewElement.setVisible(i);
      super.onDraw(paramCanvas);
      return;
    }
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (this.mNode.channelType == 0)
    {
      PlayerAgent.getInstance().addPlaySource(38);
      ControllerManager.getInstance().redirectToPlayViewByNode(this.mNode, true);
    }
    while (true)
    {
      if (this.mBelongToPodcasterInfo)
        QTMSGManage.getInstance().sendStatistcsMessage("PodcasterInfo", "进入专辑");
      return;
      ControllerManager.getInstance().setChannelSource(0);
      ControllerManager.getInstance().openChannelDetailController(this.mNode);
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
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mAvatarElement.setBoundLineWidth(this.lineLayout.height);
    this.mTitleElement.measure(this.titleLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mTimeElement.measure(this.timeLayout);
    this.mLineElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.lineLayout.getRight(), this.itemLayout.height);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mRatingElement.measure(this.ratingLayout);
    this.mTimeElement.setTextSize(SkinManager.getInstance().getTeenyTinyTextSize());
    this.mHeatLabelElement.measure(this.heatLabelLayout);
    this.mHeatTextElement.measure(this.heatTextLayout);
    this.mHostLabelElement.measure(this.hostLabelLayout);
    this.mHostTextElement.measure(this.hostTextLayout);
    this.mHeatTextElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    this.mHostTextElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void setBelongToPodcasterInfo(boolean paramBoolean)
  {
    this.mBelongToPodcasterInfo = paramBoolean;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mNode = ((ChannelNode)paramObject);
      this.mAvatarElement.setImageUrl(this.mNode.getApproximativeThumb(150, 150, true));
      this.mTitleElement.setText(this.mNode.title, false);
      this.mInfoElement.setText(getSubInfo(), false);
      this.mRatingElement.setRating(this.mNode.ratingStar);
      this.mTimeElement.setText(getUpdateTime(), false);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannellist.VirtualChannelItemView
 * JD-Core Version:    0.6.2
 */