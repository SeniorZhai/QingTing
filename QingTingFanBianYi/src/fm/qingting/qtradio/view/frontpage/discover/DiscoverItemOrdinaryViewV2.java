package fm.qingting.qtradio.view.frontpage.discover;

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
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.SpecialTopicNode;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.qtradio.view.virtualchannels.RatingElement;
import fm.qingting.utils.TimeUtil;

public class DiscoverItemOrdinaryViewV2 extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(120, 120, 25, 24, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout heatLayout = this.itemLayout.createChildLT(22, 22, 152, 117, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout hostLabelLayout = this.itemLayout.createChildLT(22, 22, 344, 117, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout hostTextLayout = this.itemLayout.createChildLT(130, 45, 368, 105, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout indentationLayout = this.itemLayout.createChildLT(17, 18, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(300, 45, 190, 95, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 168, 720, 168, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(670, 1, 25, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private NetImageViewElement mAvatarElement;
  private ButtonViewElement mBg;
  private ImageViewElement mHeatElement;
  private ImageViewElement mHostLabelElement;
  private TextViewElement mHostTextElement;
  private RecommendItemNode mInfo;
  private TextViewElement mInfoElement;
  private LineElement mLineElement;
  private RatingElement mRatingElement;
  private TextViewElement mSubTitleElement;
  private TextViewElement mTimeElement;
  private TextViewElement mTitleElement;
  private final ViewLayout ratingLayout = this.itemLayout.createChildLT(137, 30, 170, 118, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout subLayout = this.itemLayout.createChildLT(540, 40, 170, 71, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout timeLayout = this.itemLayout.createChildLT(120, 40, 580, 111, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(540, 40, 170, 25, ViewLayout.SCALE_FLAG_SLTCW);

  public DiscoverItemOrdinaryViewV2(Context paramContext, int paramInt)
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
    this.mTitleElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    this.mTitleElement.setMaxLineLimit(2);
    addElement(this.mTitleElement);
    this.mSubTitleElement = new TextViewElement(paramContext);
    this.mSubTitleElement.setColor(SkinManager.getTextColorRecommend());
    this.mSubTitleElement.setMaxLineLimit(1);
    addElement(this.mSubTitleElement);
    this.mHeatElement = new ImageViewElement(paramContext);
    this.mHeatElement.setImageRes(2130837750);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setColor(SkinManager.getTextColorHeat());
    this.mInfoElement.setMaxLineLimit(1);
    addElement(this.mInfoElement);
    this.mHostLabelElement = new ImageViewElement(paramContext);
    this.mHostLabelElement.setImageRes(2130837751);
    this.mHostTextElement = new TextViewElement(paramContext);
    this.mHostTextElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mHostTextElement.setMaxLineLimit(1);
    this.mRatingElement = new RatingElement(paramContext);
    addElement(this.mRatingElement);
    this.mTimeElement = new TextViewElement(paramContext);
    this.mTimeElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mTimeElement.setMaxLineLimit(1);
    this.mTimeElement.setAlignment(Layout.Alignment.ALIGN_OPPOSITE);
    addElement(this.mTimeElement);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
  }

  private String getSubTitle()
  {
    if (this.mInfo.mNode == null)
      return null;
    return this.mInfo.name;
  }

  private String getTitle()
  {
    Object localObject;
    if (this.mInfo.mNode == null)
      localObject = null;
    String str;
    do
    {
      return localObject;
      if (!this.mInfo.mNode.nodeName.equalsIgnoreCase("program"))
        break;
      str = ((ProgramNode)this.mInfo.mNode).getChannelName();
      localObject = str;
    }
    while (str != null);
    return this.mInfo.belongName;
    if (this.mInfo.mNode.nodeName.equalsIgnoreCase("channel"))
      return this.mInfo.name;
    if (this.mInfo.mNode.nodeName.equalsIgnoreCase("specialtopic"))
      return ((SpecialTopicNode)this.mInfo.mNode).title;
    if (this.mInfo.mNode.nodeName.equalsIgnoreCase("activity"))
      return ((ActivityNode)this.mInfo.mNode).name;
    return this.mInfo.name;
  }

  private String getUpdateTime()
  {
    return standardizeTime(this.mInfo.getUpdateTime());
  }

  private String standardizeTime(long paramLong)
  {
    return TimeUtil.getReadableTime(paramLong);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    int i = this.mTitleElement.getLineCnt();
    TextViewElement localTextViewElement = this.mSubTitleElement;
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
    ControllerManager.getInstance().openControllerByRecommendNode(this.mInfo);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.subLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.ratingLayout.scaleToBounds(this.itemLayout);
    this.timeLayout.scaleToBounds(this.itemLayout);
    this.heatLayout.scaleToBounds(this.itemLayout);
    this.hostLabelLayout.scaleToBounds(this.itemLayout);
    this.hostTextLayout.scaleToBounds(this.itemLayout);
    this.indentationLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mAvatarElement.setBoundLineWidth(this.lineLayout.height);
    this.mTitleElement.measure(this.titleLayout);
    this.mSubTitleElement.measure(this.subLayout);
    this.mHeatElement.measure(this.heatLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mRatingElement.measure(this.ratingLayout);
    this.mTimeElement.measure(this.timeLayout);
    this.mLineElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.lineLayout.getRight(), this.itemLayout.height);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mSubTitleElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    this.mTimeElement.setTextSize(SkinManager.getInstance().getTeenyTinyTextSize());
    this.mHostLabelElement.measure(this.hostLabelLayout);
    this.mHostTextElement.measure(this.hostTextLayout);
    this.mHostTextElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    int i = 0;
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mInfo = ((RecommendItemNode)paramObject);
      this.mAvatarElement.setImageUrl(this.mInfo.getApproximativeThumb(120, 120));
      this.mTitleElement.setText(getTitle(), false);
      this.mSubTitleElement.setText(getSubTitle(), false);
      this.mTimeElement.setText(getUpdateTime());
      if (((this.mInfo.mNode instanceof ChannelNode)) && (((ChannelNode)this.mInfo.mNode).channelType == 0))
      {
        this.mRatingElement.setRating(-1);
        this.mRatingElement.setData(this.mInfo);
      }
    }
    while (!paramString.equalsIgnoreCase("nbl"))
      while (true)
      {
        return;
        this.mRatingElement.setRating(this.mInfo.ratingStar);
      }
    boolean bool = ((Boolean)paramObject).booleanValue();
    paramString = this.mLineElement;
    if (bool);
    while (true)
    {
      paramString.setVisible(i);
      return;
      i = 4;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.DiscoverItemOrdinaryViewV2
 * JD-Core Version:    0.6.2
 */