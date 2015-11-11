package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayHistoryInfoNode;
import fm.qingting.qtradio.model.RootNode;
import java.util.ArrayList;
import java.util.List;

public class TopHistoryPopView extends ViewGroupViewImpl
{
  private CustomizedAdapter adapter = new CustomizedAdapter(new ArrayList(), this.factory);
  private IAdapterIViewFactory factory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new TopHistoryItemView(TopHistoryPopView.this.getContext());
    }
  };
  private ListView mListView;
  private int mSchedulePopViewBottom = 0;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public TopHistoryPopView(Context paramContext)
  {
    super(paramContext);
    this.mListView = new ListView(paramContext);
    this.mListView.setBackgroundColor(-538889168);
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setDivider(null);
    this.mListView.setAdapter(this.adapter);
    addView(this.mListView);
    paramContext = new ArrayList();
    List localList = InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes();
    int i;
    if (localList.size() >= 3)
      i = 2;
    while (i >= 1)
    {
      paramContext.add(localList.get(i));
      i -= 1;
      continue;
      i = localList.size() - 1;
    }
    this.adapter.setData(paramContext);
    setBackgroundColor(0);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0)
    {
      int i = this.mSchedulePopViewBottom;
      int j = this.mListView.getMeasuredHeight();
      if (paramMotionEvent.getY() < i - j)
      {
        dispatchActionEvent("cancelPop", null);
        EventDispacthManager.getInstance().dispatchAction("hideMiniplayerTrangle", null);
        return super.dispatchTouchEvent(paramMotionEvent);
      }
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mSchedulePopViewBottom = paramInt4;
    try
    {
      this.mListView.layout(0, this.mSchedulePopViewBottom - this.mListView.getMeasuredHeight(), this.standardLayout.width, this.mSchedulePopViewBottom);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height * 2 / 3, -2147483648));
    setMeasuredDimension(paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.TopHistoryPopView
 * JD-Core Version:    0.6.2
 */