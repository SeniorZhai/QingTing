package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.SharedCfg;
import java.util.ArrayList;
import java.util.List;

class PanelView extends ViewGroup
  implements IEventHandler
{
  private final ViewLayout listLayout = this.standardLayout.createChildLT(720, 460, 0, 90, ViewLayout.SCALE_FLAG_SLTCW);
  private CustomizedAdapter mAdapter;
  private IAdapterIViewFactory mFactory;
  private ListView mListView;
  private TextView mTitleView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 550, 720, 550, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(720, 90, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public PanelView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mFactory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new ItemView(PanelView.this.getContext());
      }
    };
    this.mAdapter = new CustomizedAdapter(getList(), this.mFactory);
    this.mAdapter.setEventHandler(this);
    this.mTitleView = new TextView(paramContext);
    this.mTitleView.setText("我觉得蜻蜓君还可以抢救一下");
    this.mTitleView.setTextColor(SkinManager.getTextColorNormal());
    this.mTitleView.setGravity(17);
    addView(this.mTitleView);
    this.mTitleView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
      }
    });
    this.mListView = new ListView(paramContext);
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setDivider(null);
    this.mListView.setSelector(17170445);
    addView(this.mListView);
    this.mListView.setAdapter(this.mAdapter);
  }

  private List<Object> getList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("1.找不到电台/电台收听不了");
    localArrayList.add("2.节目单出错");
    localArrayList.add("3.下载节目又出状况了");
    localArrayList.add("4.无法免流量收听");
    localArrayList.add("5.提供电台直播流");
    localArrayList.add("6.新功能建议");
    localArrayList.add("7.吐槽其他问题");
    return localArrayList;
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      paramObject1 = (ItemParam)paramObject2;
      SharedCfg.getInstance().setFeedbackCategory((String)paramObject1.param);
      EventDispacthManager.getInstance().dispatchAction("openFeedback", null);
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.titleLayout.layoutView(this.mTitleView);
    this.listLayout.layoutView(this.mListView);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.listLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.measureView(this.mTitleView);
    this.listLayout.measureView(this.mListView);
    this.mTitleView.setTextSize(0, SkinManager.getInstance().getMiddleTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.PanelView
 * JD-Core Version:    0.6.2
 */