package fm.qingting.qtradio.view.playview_bb;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.popviews.CustomPopActionParam.Builder;

public class DanmakuPlayNaviView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout leftLayout = this.standardLayout.createChildLT(106, 98, 3, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mLeftElement;
  private TextViewElement mMainTitleElement;
  private ButtonViewElement mRightElement;
  private TextViewElement mSubTitleElement;
  private final ViewLayout mainTitleLayout = this.standardLayout.createChildLT(500, 50, 110, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout rightLayout = this.standardLayout.createChildLT(106, 98, 611, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 98, 720, 98, 0, 0, ViewLayout.FILL);
  private final ViewLayout subTitleLayout = this.standardLayout.createChildLT(500, 28, 110, 60, ViewLayout.SCALE_FLAG_SLTCW);

  public DanmakuPlayNaviView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-394759);
    int i = hashCode();
    this.mLeftElement = new ButtonViewElement(paramContext);
    this.mLeftElement.setBackground(2130837863, 2130837524);
    addElement(this.mLeftElement, i);
    this.mLeftElement.setOnElementClickListener(this);
    this.mRightElement = new ButtonViewElement(paramContext);
    this.mRightElement.setBackground(2130837869, 2130837525);
    addElement(this.mRightElement, i);
    this.mRightElement.setOnElementClickListener(this);
    this.mMainTitleElement = new TextViewElement(paramContext);
    this.mMainTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mMainTitleElement.setMaxLineLimit(1);
    this.mMainTitleElement.setColor(SkinManager.getTextColorRecommend());
    this.mMainTitleElement.setText("蜻蜓FM", false);
    addElement(this.mMainTitleElement);
    this.mSubTitleElement = new TextViewElement(paramContext);
    this.mSubTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mSubTitleElement.setMaxLineLimit(1);
    this.mSubTitleElement.setColor(SkinManager.getTextColorRecommend());
    this.mSubTitleElement.setText("蜻蜓FM", false);
    addElement(this.mSubTitleElement);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement == this.mLeftElement)
      ControllerManager.getInstance().popLastController();
    do
    {
      do
        return;
      while (paramViewElement != this.mRightElement);
      paramViewElement = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    }
    while (paramViewElement == null);
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    CustomPopActionParam.Builder localBuilder = new CustomPopActionParam.Builder();
    if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")) && (paramViewElement.channelType == 1))
      localBuilder.setTitle("更多").addNode(localNode);
    while (true)
    {
      localBuilder.addButton(2);
      if (paramViewElement.channelType == 0)
        localBuilder.addButton(3);
      paramViewElement = localBuilder.create();
      EventDispacthManager.getInstance().dispatchAction("showoperatepop", paramViewElement);
      return;
      localBuilder.setTitle("更多").addNode(paramViewElement);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.leftLayout.scaleToBounds(this.standardLayout);
    this.rightLayout.scaleToBounds(this.standardLayout);
    this.mainTitleLayout.scaleToBounds(this.standardLayout);
    this.subTitleLayout.scaleToBounds(this.standardLayout);
    this.mLeftElement.measure(this.leftLayout);
    this.mRightElement.measure(this.rightLayout);
    this.mMainTitleElement.measure(this.mainTitleLayout);
    this.mSubTitleElement.measure(this.subTitleLayout);
    this.mMainTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mSubTitleElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mSubTitleElement.setText((String)paramObject);
    while (!paramString.equalsIgnoreCase("setSubData"))
      return;
    paramString = (ProgramNode)paramObject;
    this.mMainTitleElement.setText(paramString.title);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview_bb.DanmakuPlayNaviView
 * JD-Core Version:    0.6.2
 */