package fm.qingting.qtradio.view.chatroom.broadcastor;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.HorizontalScrollViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;

class BroadcastorScrollView extends HorizontalScrollViewImpl
  implements IEventHandler
{
  private BroadcastorRowView mRowView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(500, 160, 500, 160, 0, 0, ViewLayout.FILL);

  @TargetApi(9)
  public BroadcastorScrollView(Context paramContext)
  {
    super(paramContext);
    setHorizontalScrollBarEnabled(false);
    if (QtApiLevelManager.isApiLevelSupported(9))
      setOverScrollMode(2);
    this.mRowView = new BroadcastorRowView(paramContext);
    addView(this.mRowView);
    this.mRowView.setEventHandler(this);
  }

  public void close(boolean paramBoolean)
  {
    this.mRowView.close(paramBoolean);
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("flowerToPoint"))
    {
      paramObject1 = (Point)paramObject2;
      paramObject1.offset(-getScrollX(), 0);
      dispatchActionEvent(paramString, paramObject1);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.standardLayout.measureView(this.mRowView);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    this.mRowView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.broadcastor.BroadcastorScrollView
 * JD-Core Version:    0.6.2
 */