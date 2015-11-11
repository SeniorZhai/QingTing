package fm.qingting.qtradio.view.frontpage.discover;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View.MeasureSpec;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
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
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.utils.TimeUtil;

public class DiscoverCollectionItemView extends QtView
  implements ViewElement.OnElementClickListener, InfoManager.ISubscribeEventListener
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
  private ChannelNode mChannelNode;
  private ImageViewElement mHeatElement;
  private ImageViewElement mHostLabelElement;
  private TextViewElement mHostTextElement;
  private TextViewElement mInfoElement;
  private LineElement mLineElement;
  private TextViewElement mSubTitleElement;
  private TextViewElement mTimeElement;
  private TextViewElement mTitleElement;
  private boolean shouldReminder;
  private final ViewLayout subLayout = this.itemLayout.createChildLT(540, 40, 170, 71, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout timeLayout = this.itemLayout.createChildLT(280, 40, 170, 111, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(540, 40, 170, 25, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout updateLayout = this.itemLayout.createChildLT(20, 20, 170, 40, ViewLayout.SCALE_FLAG_SLTCW);

  public DiscoverCollectionItemView(Context paramContext, int paramInt)
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
    this.mTimeElement = new TextViewElement(paramContext);
    this.mTimeElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mTimeElement.setMaxLineLimit(1);
    addElement(this.mTimeElement);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
    this.shouldReminder = false;
  }

  private void checkLiveChannel()
  {
    if (this.mChannelNode.channelType == 1)
      return;
    ProgramNode localProgramNode = this.mChannelNode.getProgramNodeByTime(System.currentTimeMillis());
    if (localProgramNode != null)
    {
      this.mSubTitleElement.setText("正在直播：" + localProgramNode.title, false);
      this.mTimeElement.setText("播放时间：" + TimeUtil.msToDate3(localProgramNode.getAbsoluteStartTime() * 1000L) + " - " + TimeUtil.msToDate3(localProgramNode.getAbsoluteEndTime() * 1000L));
      return;
    }
    InfoManager.getInstance().loadProgramsScheduleNode(this.mChannelNode, this);
    this.mSubTitleElement.setText("正在加载...");
    this.mTimeElement.setText("播放时间: 00:00 - 24:00");
  }

  private String getSubTitle()
  {
    if (this.mChannelNode.channelType == 1)
      return this.mChannelNode.latest_program;
    return "";
  }

  private String getTitle()
  {
    if (this.mChannelNode == null)
      return null;
    return this.mChannelNode.title;
  }

  private String getUpdateTime()
  {
    if (this.mChannelNode.channelType == 1)
      return standardizeTime(this.mChannelNode.getUpdateTime());
    return "";
  }

  private String standardizeTime(long paramLong)
  {
    return TimeUtil.getReadableTime(paramLong);
  }

  @SuppressLint({"DrawAllocation"})
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i = this.mTitleElement.getLineCnt();
    Object localObject = this.mSubTitleElement;
    if (i > 1);
    for (i = 4; ; i = 0)
    {
      ((TextViewElement)localObject).setVisible(i);
      if (this.shouldReminder)
      {
        localObject = new Paint();
        ((Paint)localObject).setColor(-65536);
        paramCanvas.drawCircle(this.updateLayout.leftMargin + this.updateLayout.width / 2, this.updateLayout.topMargin + this.updateLayout.width / 2, this.updateLayout.width / 2, (Paint)localObject);
      }
      return;
    }
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (this.mChannelNode == null)
      return;
    this.mChannelNode.viewTime = System.currentTimeMillis();
    PlayerAgent.getInstance().addPlaySource(4);
    if (this.mChannelNode.isNovelChannel())
    {
      ControllerManager.getInstance().setChannelSource(0);
      ControllerManager.getInstance().openChannelDetailController(this.mChannelNode);
    }
    while (true)
    {
      MobclickAgent.onEvent(InfoManager.getInstance().getContext(), "frontCollectionChannel");
      TCAgent.onEvent(InfoManager.getInstance().getContext(), "frontCollectionChannel");
      return;
      if (this.mChannelNode.channelType == 1)
      {
        ControllerManager.getInstance().setChannelSource(0);
        ControllerManager.getInstance().openChannelDetailController(this.mChannelNode);
      }
      else
      {
        ControllerManager.getInstance().redirectToPlayViewByNode(this.mChannelNode, true);
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.updateLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.subLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.timeLayout.scaleToBounds(this.itemLayout);
    this.heatLayout.scaleToBounds(this.itemLayout);
    this.hostLabelLayout.scaleToBounds(this.itemLayout);
    this.hostTextLayout.scaleToBounds(this.itemLayout);
    this.indentationLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mTitleElement.measure(this.titleLayout);
    this.mSubTitleElement.measure(this.subLayout);
    this.mHeatElement.measure(this.heatLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mTimeElement.measure(this.timeLayout);
    this.mLineElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.lineLayout.getRight(), this.itemLayout.height);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mSubTitleElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    this.mTimeElement.setTextSize(SkinManager.getInstance().getTeenyTinyTextSize());
    this.mHostLabelElement.measure(this.hostLabelLayout);
    this.mHostTextElement.measure(this.hostTextLayout);
    this.mHostTextElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    this.mAvatarElement.setBoundLineWidth(this.lineLayout.height);
    if (this.shouldReminder)
      this.mTitleElement.setTranslationX(this.updateLayout.width);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void onNotification(String paramString)
  {
    if ((!paramString.equalsIgnoreCase("RPS")) || (this.mChannelNode.channelType == 1));
    do
    {
      return;
      paramString = this.mChannelNode.getProgramNodeByTime(System.currentTimeMillis());
    }
    while (paramString == null);
    this.mSubTitleElement.setText("正在直播：" + paramString.title, false);
    this.mTimeElement.setText("播放时间：" + TimeUtil.msToDate3(paramString.getAbsoluteStartTime() * 1000L) + " - " + TimeUtil.msToDate3(paramString.getAbsoluteEndTime() * 1000L));
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    boolean bool = true;
    int i = 0;
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mChannelNode = ((ChannelNode)paramObject);
      this.mAvatarElement.setImageUrl(this.mChannelNode.getApproximativeThumb(120, 120, true));
      this.mTitleElement.setText(getTitle(), false);
      this.mSubTitleElement.setText(getSubTitle(), false);
      this.mTimeElement.setText(getUpdateTime());
      if ((this.mChannelNode.getUpdateTime() > SharedCfg.getInstance().getLatestBootstrapTime()) && (this.mChannelNode.channelType == 1) && (SharedCfg.getInstance().getLatestBootstrapTime() > this.mChannelNode.viewTime))
      {
        this.shouldReminder = bool;
        checkLiveChannel();
      }
    }
    while (!paramString.equalsIgnoreCase("nbl"))
      while (true)
      {
        return;
        bool = false;
      }
    bool = ((Boolean)paramObject).booleanValue();
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
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.DiscoverCollectionItemView
 * JD-Core Version:    0.6.2
 */