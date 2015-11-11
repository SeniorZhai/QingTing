package fm.qingting.qtradio.view.im.chat;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class ImChatNaviView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout backLayout = this.standardLayout.createChildLT(106, 98, 3, 8, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mBack;
  private TextViewElement mNameElement;
  private TextViewElement mOnlineElement;
  private final ViewLayout nameLayout = this.standardLayout.createChildLT(500, 100, 110, 7, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout onlineLayout = this.standardLayout.createChildLT(106, 98, 611, 8, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 114, 720, 114, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public ImChatNaviView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mBack = new ButtonViewElement(paramContext);
    this.mBack.setBackground(2130837863, 2130837862);
    addElement(this.mBack, i);
    this.mBack.setOnElementClickListener(this);
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setColor(SkinManager.getBackgroundColor());
    this.mNameElement.setText("群组", false);
    addElement(this.mNameElement);
    this.mOnlineElement = new TextViewElement(paramContext);
    this.mOnlineElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mOnlineElement.setMaxLineLimit(1);
    this.mOnlineElement.setColor(-1);
    this.mOnlineElement.setText("资料", false);
    addElement(this.mOnlineElement);
    this.mOnlineElement.setOnElementClickListener(this);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (this.mBack == paramViewElement)
      dispatchActionEvent("clickback", null);
    while (this.mOnlineElement != paramViewElement)
      return;
    dispatchActionEvent("clickright", null);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.backLayout.scaleToBounds(this.standardLayout);
    this.nameLayout.scaleToBounds(this.standardLayout);
    this.mBack.measure(this.backLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mNameElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.onlineLayout.scaleToBounds(this.standardLayout);
    this.mOnlineElement.measure(this.onlineLayout);
    this.mOnlineElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramObject = (String)paramObject;
      paramString = paramObject;
      if (paramObject == null)
        paramString = "";
      this.mNameElement.setText(paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.chat.ImChatNaviView
 * JD-Core Version:    0.6.2
 */