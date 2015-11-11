package fm.qingting.qtradio.view.frontpage.discover;

import android.content.Context;
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
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RecommendPlayingItemNode;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.utils.QTMSGManage;

public class DiscoverItemRecommendPlayingView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(116, 116, 25, 26, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout heatLayout = this.itemLayout.createChildLT(22, 22, 166, 117, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout hostLabelLayout = this.itemLayout.createChildLT(22, 22, 344, 117, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout hostTextLayout = this.itemLayout.createChildLT(130, 45, 368, 105, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout indentationLayout = this.itemLayout.createChildLT(17, 18, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(300, 45, 190, 105, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 168, 720, 168, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(670, 1, 25, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private NetImageViewElement mAvatarElement;
  private ButtonViewElement mBg;
  private ImageViewElement mHeatElement;
  private ImageViewElement mHostLabelElement;
  private TextViewElement mHostTextElement;
  private RecommendPlayingItemNode mInfo;
  private TextViewElement mInfoElement;
  private LineElement mLineElement;
  private TextViewElement mTimeElement;
  private TextViewElement mTitleElement;
  private final ViewLayout timeLayout = this.itemLayout.createChildLT(480, 45, 166, 105, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(540, 40, 166, 18, ViewLayout.SCALE_FLAG_SLTCW);

  public DiscoverItemRecommendPlayingView(Context paramContext, int paramInt)
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
    this.mHeatElement = new ImageViewElement(paramContext);
    this.mHeatElement.setImageRes(2130837750);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setColor(SkinManager.getTextColorHeat());
    this.mInfoElement.setMaxLineLimit(1);
    this.mHostLabelElement = new ImageViewElement(paramContext);
    this.mHostLabelElement.setImageRes(2130837751);
    this.mHostTextElement = new TextViewElement(paramContext);
    this.mHostTextElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mHostTextElement.setMaxLineLimit(1);
    addElement(this.mHostTextElement);
    this.mTimeElement = new TextViewElement(paramContext);
    this.mTimeElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mTimeElement.setMaxLineLimit(1);
    addElement(this.mTimeElement);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
  }

  private String getName()
  {
    if (this.mInfo == null)
      return null;
    return "【" + this.mInfo.channelName + "】" + this.mInfo.programName;
  }

  private String getUpdateTime()
  {
    if (this.mInfo == null)
      return null;
    String str1;
    if (this.mInfo.startTime == null)
    {
      str1 = "00:00";
      if (this.mInfo.endTime != null)
        break label79;
    }
    label79: for (String str2 = "00:00"; ; str2 = trimTime(this.mInfo.endTime))
    {
      return "直播时间:" + str1 + "-" + str2;
      str1 = trimTime(this.mInfo.startTime);
      break;
    }
  }

  private String trimTime(String paramString)
  {
    return paramString.substring(0, paramString.lastIndexOf(":"));
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (this.mInfo != null)
    {
      PlayerAgent.getInstance().addPlaySource(37);
      paramViewElement = InfoManager.getInstance().h5Channel(this.mInfo.channelId);
      if ((paramViewElement == null) || (paramViewElement.equalsIgnoreCase("")))
        break label95;
      String str = this.mInfo.channelName;
      ControllerManager.getInstance().redirectToActiviyByUrl(paramViewElement, str, false);
      paramViewElement = ChannelHelper.getInstance().getChannel(this.mInfo.channelId, 0);
      if (paramViewElement != null)
        PlayerAgent.getInstance().play(paramViewElement);
    }
    while (true)
    {
      QTMSGManage.getInstance().sendStatistcsMessage("v6_live_recommendplaying");
      return;
      label95: ControllerManager.getInstance().openPlayController(0, this.mInfo.channelId, 0, true, this.mInfo.channelName);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.timeLayout.scaleToBounds(this.itemLayout);
    this.heatLayout.scaleToBounds(this.itemLayout);
    this.hostLabelLayout.scaleToBounds(this.itemLayout);
    this.hostTextLayout.scaleToBounds(this.itemLayout);
    this.indentationLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mAvatarElement.setBoundLineWidth(this.lineLayout.height);
    this.mTitleElement.measure(this.titleLayout);
    this.mHeatElement.measure(this.heatLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mTimeElement.measure(this.timeLayout);
    this.mLineElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.lineLayout.getRight(), this.itemLayout.height);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mTitleElement.setExactFirstIndentation(-this.indentationLayout.width);
    this.mInfoElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    this.mTimeElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
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
      this.mInfo = ((RecommendPlayingItemNode)paramObject);
      this.mAvatarElement.setImageUrl(this.mInfo.thumb);
      this.mTitleElement.setText(getName(), false);
      this.mTimeElement.setText(getUpdateTime());
    }
    while (!paramString.equalsIgnoreCase("nbl"))
      return;
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
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.DiscoverItemRecommendPlayingView
 * JD-Core Version:    0.6.2
 */