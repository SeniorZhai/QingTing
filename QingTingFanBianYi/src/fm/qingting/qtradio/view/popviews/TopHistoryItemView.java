package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.PlayHistoryNode;
import fm.qingting.qtradio.model.PlayedMetaData;
import fm.qingting.qtradio.model.PlayedMetaInfo;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.utils.QTMSGManage;

class TopHistoryItemView extends QtView
{
  private final ViewLayout arrowLayout = this.itemLayout.createChildLT(9, 14, 660, 32, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout bottomLayout = this.itemLayout.createChildLT(680, 1, 20, 78, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout continueLayout = this.itemLayout.createChildLT(80, 79, 20, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 79, 720, 79, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private ImageViewElement mArrowIe;
  private ButtonViewElement mBg;
  private LineElement mBottomLe;
  private TextViewElement mContinueTe;
  private PlayHistoryNode mHistoryNode;
  private PlayedMetaData mMetaData;
  private TextViewElement mPlayPercentTe;
  private PlayedMetaInfo mPlayedMetaInfo = PlayedMetaInfo.getInstance();
  private ProgramNode mProgramNode;
  private TextViewElement mProgramTitleTe;
  private final ViewLayout playPercentLayout = this.itemLayout.createChildLT(100, 79, 560, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout programLayout = this.itemLayout.createChildLT(400, 79, 110, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public TopHistoryItemView(Context paramContext)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        PlayerAgent.getInstance().addPlaySource(13);
        if (TopHistoryItemView.this.mHistoryNode.playContent == 0L)
        {
          paramAnonymousViewElement = ChannelHelper.getInstance().getChannel(TopHistoryItemView.this.mProgramNode);
          PlayerAgent.getInstance().play(paramAnonymousViewElement);
        }
        while (true)
        {
          EventDispacthManager.getInstance().dispatchAction("cancelPop", null);
          QTMSGManage.getInstance().sendStatistcsMessage("miniplayerclick", "topplayhistory");
          return;
          try
          {
            PlayerAgent.getInstance().play(TopHistoryItemView.this.mProgramNode);
          }
          catch (Exception paramAnonymousViewElement)
          {
            paramAnonymousViewElement.printStackTrace();
          }
        }
      }
    });
    this.mContinueTe = new TextViewElement(paramContext);
    this.mContinueTe.setMaxLineLimit(1);
    this.mContinueTe.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mContinueTe.setText("续听");
    this.mContinueTe.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mContinueTe.setColor(2147483647);
    addElement(this.mContinueTe);
    this.mProgramTitleTe = new TextViewElement(paramContext);
    this.mProgramTitleTe.setMaxLineLimit(1);
    this.mProgramTitleTe.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mProgramTitleTe.setColor(-1);
    addElement(this.mProgramTitleTe);
    this.mPlayPercentTe = new TextViewElement(paramContext);
    this.mPlayPercentTe.setMaxLineLimit(1);
    this.mPlayPercentTe.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mPlayPercentTe.setColor(-1);
    addElement(this.mPlayPercentTe);
    this.mArrowIe = new ImageViewElement(paramContext);
    this.mArrowIe.setImageRes(2130837996);
    addElement(this.mArrowIe);
    this.mBottomLe = new LineElement(paramContext);
    this.mBottomLe.setOrientation(1);
    this.mBottomLe.setColor(2145246685);
    addElement(this.mBottomLe);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.continueLayout.scaleToBounds(this.itemLayout);
    this.programLayout.scaleToBounds(this.itemLayout);
    this.playPercentLayout.scaleToBounds(this.itemLayout);
    this.arrowLayout.scaleToBounds(this.itemLayout);
    this.bottomLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mContinueTe.measure(this.continueLayout);
    this.mProgramTitleTe.measure(this.programLayout);
    this.mPlayPercentTe.measure(this.playPercentLayout);
    this.mArrowIe.measure(this.arrowLayout);
    this.mBottomLe.measure(this.bottomLayout);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      try
      {
        this.mHistoryNode = ((PlayHistoryNode)paramObject);
        this.mProgramNode = ((ProgramNode)this.mHistoryNode.playNode);
        this.mMetaData = this.mPlayedMetaInfo.getPlayedMeta(this.mProgramNode.uniqueId);
        if (this.mHistoryNode.playContent == 1L)
          this.mProgramTitleTe.setText(this.mProgramNode.title);
        while (this.mMetaData != null)
        {
          this.mPlayPercentTe.setText(this.mMetaData.position * 100 / this.mMetaData.duration + "%");
          return;
          this.mProgramTitleTe.setText(this.mHistoryNode.channelName);
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      this.mPlayPercentTe.setText("1%");
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.TopHistoryItemView
 * JD-Core Version:    0.6.2
 */