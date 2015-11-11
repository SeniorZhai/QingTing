package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.text.Layout.Alignment;
import android.text.TextUtils;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PlayingProgramInfoNode;
import fm.qingting.qtradio.model.PlayingProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.search.SearchItemNode;
import fm.qingting.qtradio.search.SearchNode;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.utils.InputMethodUtil;
import fm.qingting.utils.QTMSGManage;

public class SearchItemChannelView extends QtView
{
  public static final String PLAYING = "正在播放:";
  private final ViewLayout audienceLayout = this.itemLayout.createChildLT(300, 50, 208, 106, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout descLayout = this.itemLayout.createChildLT(480, 50, 208, 52, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout freqLayout = this.itemLayout.createChildLT(200, 50, 480, 106, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 170, 720, 1200, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(640, 1, 40, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private TextViewElement mAudience;
  private ButtonViewElement mBg;
  private TextViewElement mFreq;
  private TextViewElement mInfo;
  private LineElement mLineElement;
  private TextViewElement mName;
  private SearchItemNode mNode;
  private NetImageViewElement mPic;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(480, 50, 208, 12, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout picLayout = this.itemLayout.createChildLT(136, 136, 40, 17, ViewLayout.SCALE_FLAG_SLTCW);

  public SearchItemChannelView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getItemHighlightMaskColor(), SkinManager.getCardColor());
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        if (InfoManager.getInstance().root().mSearchNode.getVoiceSearch())
        {
          InfoManager.getInstance().root().mSearchNode.setVoiceSearch(false);
          QTMSGManage.getInstance().sendStatistcsMessage("VoiceRecognition", "voice_search_click");
        }
        InputMethodUtil.hideSoftInput(SearchItemChannelView.this);
        ControllerManager.getInstance().redirectToViewBySearchNode(SearchItemChannelView.this.mNode);
      }
    });
    this.mPic = new NetImageViewElement(paramContext);
    this.mPic.setDefaultImageRes(2130837934);
    this.mPic.setBoundColor(SkinManager.getDividerColor());
    addElement(this.mPic, paramInt);
    this.mName = new TextViewElement(paramContext);
    this.mName.setMaxLineLimit(1);
    this.mName.setColor(SkinManager.getTextColorNormal());
    addElement(this.mName);
    this.mInfo = new TextViewElement(paramContext);
    this.mInfo.setMaxLineLimit(1);
    this.mInfo.setColor(SkinManager.getTextColorSubInfo());
    addElement(this.mInfo);
    this.mFreq = new TextViewElement(paramContext);
    this.mFreq.setMaxLineLimit(1);
    this.mFreq.setColor(SkinManager.getTextColorThirdLevel());
    this.mFreq.setAlignment(Layout.Alignment.ALIGN_OPPOSITE);
    addElement(this.mFreq);
    this.mAudience = new TextViewElement(paramContext);
    this.mAudience.setMaxLineLimit(1);
    this.mAudience.setColor(SkinManager.getTextColorThirdLevel());
    addElement(this.mAudience);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    this.mLineElement.setOrientation(1);
    addElement(this.mLineElement);
  }

  private String getAudienceCount()
  {
    int i = this.mNode.audience_count;
    if (i > 0)
    {
      if (i > 10000)
      {
        float f = i / 1000 * 1000 / 10000.0F;
        return String.valueOf(f) + "万";
      }
      return String.valueOf(i);
    }
    return null;
  }

  private String getCurrentPlayingProgram()
  {
    PlayingProgramNode localPlayingProgramNode = (PlayingProgramNode)InfoManager.getInstance().root().mPlayingProgramInfo.getCurrentPlayingProgram(this.mNode.channelId, this.mNode.name);
    if (localPlayingProgramNode == null)
      return null;
    return "正在播放:" + localPlayingProgramNode.programName;
  }

  private String getFreqText()
  {
    if (!TextUtils.isEmpty(this.mNode.freqs))
      return "FM" + this.mNode.freqs;
    return null;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.picLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.descLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.freqLayout.scaleToBounds(this.itemLayout);
    this.audienceLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mPic.measure(this.picLayout);
    this.mPic.setBoundLineWidth(this.lineLayout.height);
    this.mName.measure(this.nameLayout);
    this.mInfo.measure(this.descLayout);
    this.mFreq.measure(this.freqLayout);
    this.mAudience.measure(this.audienceLayout);
    this.mLineElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.lineLayout.getRight(), this.itemLayout.height);
    this.mName.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mInfo.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mFreq.setTextSize(SkinManager.getInstance().getTinyTextSize());
    this.mAudience.setTextSize(SkinManager.getInstance().getTinyTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mNode = ((SearchItemNode)paramObject);
      this.mPic.setImageUrl(this.mNode.cover);
      this.mName.setText(this.mNode.name);
      this.mInfo.setText(getCurrentPlayingProgram());
      this.mFreq.setText(getFreqText());
      paramString = getAudienceCount();
      if (paramString != null)
        this.mAudience.setText("听众" + paramString);
    }
    do
    {
      return;
      this.mAudience.setText(paramString);
      return;
      if (paramString.equalsIgnoreCase("nbl"))
      {
        boolean bool = ((Boolean)paramObject).booleanValue();
        paramString = this.mLineElement;
        if (bool);
        for (int i = 0; ; i = 4)
        {
          paramString.setVisible(i);
          return;
        }
      }
    }
    while (!paramString.equalsIgnoreCase("ip"));
    this.mInfo.setText(getCurrentPlayingProgram());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchItemChannelView
 * JD-Core Version:    0.6.2
 */