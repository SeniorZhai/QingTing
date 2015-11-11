package fm.qingting.qtradio.view.channelcategoryview;

import android.content.Context;
import android.view.View.MeasureSpec;
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
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PlayingProgramInfoNode;
import fm.qingting.qtradio.model.PlayingProgramNode;
import fm.qingting.qtradio.model.RadioChannelNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.playview.LineElement;

public class RadioChannelItemView extends QtView
  implements ViewElement.OnElementClickListener
{
  public static final String INVALIDATE_PROGRAM = "ip";
  public static final String LOADING = "正在加载...";
  public static final String PLAYING = "正在播放:";
  private final ViewLayout audienceLabelLayout = this.itemLayout.createChildLT(26, 23, 190, 145, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout audienceTextLayout = this.itemLayout.createChildLT(300, 40, 220, 137, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(150, 150, 20, 25, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(500, 45, 190, 78, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 200, 720, 200, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(705, 1, 15, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ImageViewElement mAudienceLabelElement;
  private TextViewElement mAudienceTextElement;
  private NetImageViewElement mAvatarElement;
  private ButtonViewElement mBg;
  private TextViewElement mInfoElement;
  private LineElement mLineElement;
  private RadioChannelNode mNode;
  private TextViewElement mTitleElement;
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(500, 45, 190, 21, ViewLayout.SCALE_FLAG_SLTCW);

  public RadioChannelItemView(Context paramContext, int paramInt)
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
    this.mInfoElement.setColor(SkinManager.getTextColorRecommend());
    this.mInfoElement.setMaxLineLimit(1);
    addElement(this.mInfoElement);
    this.mAudienceLabelElement = new ImageViewElement(paramContext);
    this.mAudienceLabelElement.setImageRes(2130837700);
    addElement(this.mAudienceLabelElement, paramInt);
    this.mAudienceTextElement = new TextViewElement(paramContext);
    this.mAudienceTextElement.setColor(SkinManager.getTextColorThirdLevel());
    this.mAudienceTextElement.setMaxLineLimit(1);
    addElement(this.mAudienceTextElement);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
  }

  private String getAudienceCount(ChannelNode paramChannelNode)
  {
    if (paramChannelNode == null);
    int i;
    do
    {
      return null;
      i = paramChannelNode.audienceCnt;
    }
    while (i <= 0);
    if (i > 10000)
    {
      float f = i / 1000 * 1000 / 10000.0F;
      return String.valueOf(f) + "万";
    }
    return String.valueOf(i);
  }

  private String getCurrentPlayingProgram()
  {
    PlayingProgramNode localPlayingProgramNode = (PlayingProgramNode)InfoManager.getInstance().root().mPlayingProgramInfo.getCurrentPlayingProgram(this.mNode);
    if (localPlayingProgramNode == null)
      return "正在加载...";
    return "正在播放:" + localPlayingProgramNode.programName;
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    PlayerAgent.getInstance().addPlaySource(9);
    ControllerManager.getInstance().redirectToPlayViewByNode(this.mNode, true);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.audienceLabelLayout.scaleToBounds(this.itemLayout);
    this.audienceTextLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mAvatarElement.setBoundLineWidth(this.lineLayout.height);
    this.mTitleElement.measure(this.titleLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mLineElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.lineLayout.getRight(), this.itemLayout.height);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mAudienceLabelElement.measure(this.audienceLabelLayout);
    this.mAudienceTextElement.measure(this.audienceTextLayout);
    this.mAudienceTextElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mNode = ((RadioChannelNode)paramObject);
      paramString = this.mNode.convertToChannelNode();
      if (paramString != null)
      {
        this.mAvatarElement.setImageUrl(paramString.getApproximativeThumb());
        this.mTitleElement.setText(paramString.title, false);
        this.mInfoElement.setText(getCurrentPlayingProgram(), false);
        paramString = getAudienceCount(paramString);
        if (paramString != null)
          break label120;
        this.mAudienceLabelElement.setVisible(4);
        this.mAudienceTextElement.setText(paramString, false);
      }
    }
    label120: 
    while (!paramString.equalsIgnoreCase("ip"))
      while (true)
      {
        return;
        this.mAvatarElement.setImageUrl(null);
        this.mTitleElement.setText(this.mNode.channelName, false);
        continue;
        this.mAudienceLabelElement.setVisible(0);
      }
    this.mInfoElement.setText(getCurrentPlayingProgram());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.channelcategoryview.RadioChannelItemView
 * JD-Core Version:    0.6.2
 */