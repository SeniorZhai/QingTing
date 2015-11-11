package fm.qingting.qtradio.view.chatroom;

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
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;

public class ChatroomNaviView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout backLayout = this.standardLayout.createChildLT(106, 112, 3, 1, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mBack;
  private TextViewElement mNameElement;
  private final ViewLayout nameLayout = this.standardLayout.createChildLT(500, 100, 110, 7, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout onlineLayout = this.standardLayout.createChildLT(106, 112, 611, 1, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 114, 720, 114, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public ChatroomNaviView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getNaviBgColor());
    int i = hashCode();
    this.mBack = new ButtonViewElement(paramContext);
    this.mBack.setBackground(2130837863, 2130837862);
    addElement(this.mBack, i);
    this.mBack.setOnElementClickListener(this);
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setColor(SkinManager.getBackgroundColor());
    this.mNameElement.setText("直播间", false);
    addElement(this.mNameElement);
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
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = ((ProgramNode)paramObject).parent;
      if (paramString == null)
        break label48;
      if (paramString.nodeName.equalsIgnoreCase("channel"))
        this.mNameElement.setText(((ChannelNode)paramString).title);
    }
    return;
    label48: this.mNameElement.setText("直播间");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.ChatroomNaviView
 * JD-Core Version:    0.6.2
 */