package fm.qingting.qtradio.view.im.chat;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;

public class ImChatAdminsRowView extends ViewGroupViewImpl
  implements IEventHandler
{
  private AdminsScrollView mScrollView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(500, 160, 500, 160, 0, 0, ViewLayout.FILL);

  public ImChatAdminsRowView(Context paramContext)
  {
    super(paramContext);
    this.mScrollView = new AdminsScrollView(paramContext);
    addView(this.mScrollView);
    this.mScrollView.setEventHandler(this);
  }

  public void close(boolean paramBoolean)
  {
    this.mScrollView.close(paramBoolean);
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("flowerToPoint"))
      dispatchActionEvent(paramString, paramObject2);
    while (!paramString.equalsIgnoreCase("flowerToAdmin"))
      return;
    dispatchActionEvent(paramString, paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mScrollView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.standardLayout.measureView(this.mScrollView);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    this.mScrollView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.chat.ImChatAdminsRowView
 * JD-Core Version:    0.6.2
 */