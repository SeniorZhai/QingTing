package fm.qingting.qtradio.view.virtualchannellist;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.SpecialTopicNode;

class HeaderView extends QtView
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 342, 720, 342, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private NetImageViewElement mImageViewElement;
  private TextViewElement mName;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(670, 40, 25, 288, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout picLayout = this.itemLayout.createChildLT(720, 278, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public HeaderView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mImageViewElement = new NetImageViewElement(paramContext);
    this.mImageViewElement.setDefaultImageRes(2130837983);
    addElement(this.mImageViewElement, hashCode());
    this.mName = new TextViewElement(paramContext);
    this.mName.setMaxLineLimit(4);
    this.mName.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    this.mName.setColor(SkinManager.getTextColorNormal_New());
    addElement(this.mName);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.picLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.mImageViewElement.measure(this.picLayout);
    this.mName.measure(this.nameLayout);
    this.mName.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    setMeasuredDimension(this.itemLayout.width, this.nameLayout.topMargin + this.mName.getHeight() + this.nameLayout.topMargin - this.picLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (SpecialTopicNode)paramObject;
      this.mImageViewElement.setImageUrl(paramString.thumb, false);
      this.mName.setText(paramString.desc);
      requestLayout();
      paramString = QTLogger.getInstance().buildSpecialTopicLog(paramString);
      if (paramString != null)
        LogModule.getInstance().send("topic_v6", paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannellist.HeaderView
 * JD-Core Version:    0.6.2
 */