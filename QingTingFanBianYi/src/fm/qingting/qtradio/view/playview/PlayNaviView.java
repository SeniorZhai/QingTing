package fm.qingting.qtradio.view.playview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.Layout.Alignment;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View.MeasureSpec;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
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
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.popviews.CustomPopActionParam.Builder;
import fm.qingting.qtradio.wo.WoApiRequest;

public class PlayNaviView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout leftLayout = this.standardLayout.createChildLT(106, 98, 3, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mLeftElement;
  private ButtonViewElement mRightElement;
  private String mTitle;
  private TextViewElement mTitleElement;
  private ImageViewElement mUnicomElement;
  private final ViewLayout rightLayout = this.standardLayout.createChildLT(106, 98, 611, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 98, 720, 98, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(500, 64, 110, 17, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout unicomLayout = this.standardLayout.createChildLT(50, 26, 110, 37, ViewLayout.SCALE_FLAG_SLTCW);

  public PlayNaviView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mLeftElement = new ButtonViewElement(paramContext);
    this.mLeftElement.setBackground(2130837863, 2130837862);
    addElement(this.mLeftElement, i);
    this.mLeftElement.setOnElementClickListener(this);
    this.mRightElement = new ButtonViewElement(paramContext);
    this.mRightElement.setBackground(2130837869, 2130837868);
    addElement(this.mRightElement, i);
    this.mRightElement.setOnElementClickListener(this);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mTitleElement.setMaxLineLimit(1);
    this.mTitleElement.setColor(-1);
    this.mTitleElement.setText("蜻蜓FM", false);
    addElement(this.mTitleElement);
    this.mUnicomElement = new ImageViewElement(paramContext);
    this.mUnicomElement.setImageRes(2130838008);
    addElement(this.mUnicomElement);
    if (!WoApiRequest.hasOpen())
      this.mUnicomElement.setVisible(4);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  @SuppressLint({"DrawAllocation"})
  public void onDraw(Canvas paramCanvas)
  {
    if (WoApiRequest.hasOpen())
    {
      Rect localRect = new Rect();
      this.mTitle = TextUtils.ellipsize(this.mTitle, SkinManager.getInstance().getNormalTextPaint(), this.titleLayout.width, TextUtils.TruncateAt.END).toString();
      SkinManager.getInstance().getNormalTextPaint().getTextBounds(this.mTitle, 0, this.mTitle.length(), localRect);
      int i = Math.max(0, (this.titleLayout.width - localRect.width()) / 2 - 30);
      this.mUnicomElement.setTranslationX(i);
    }
    super.onDraw(paramCanvas);
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
      localBuilder.addButton(6);
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
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.unicomLayout.scaleToBounds(this.standardLayout);
    this.mLeftElement.measure(this.leftLayout);
    this.mRightElement.measure(this.rightLayout);
    this.mTitleElement.measure(this.titleLayout);
    this.mUnicomElement.measure(this.unicomLayout);
    if (WoApiRequest.hasOpen())
      this.mTitleElement.setTranslationX(this.mUnicomElement.getWidth());
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mTitleElement.setText((String)paramObject);
      this.mTitle = ((String)paramObject);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.PlayNaviView
 * JD-Core Version:    0.6.2
 */