package fm.qingting.qtradio.view.personalcenter.faq;

import android.content.Context;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ListViewImpl;
import fm.qingting.qtradio.manager.SkinManager;
import java.util.ArrayList;
import java.util.List;

public class FaqListView extends ListViewImpl
  implements IEventHandler
{
  private CustomizedAdapter adapter;
  private LargeButtonView askView;
  private IAdapterIViewFactory factory;

  public FaqListView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.factory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new FaqItemView(FaqListView.this.getContext());
      }
    };
    setVerticalFadingEdgeEnabled(false);
    setCacheColorHint(0);
    setVerticalScrollBarEnabled(false);
    setHeaderDividersEnabled(false);
    setSelector(17170445);
    setDivider(null);
    this.adapter = new CustomizedAdapter(new ArrayList(), this.factory);
    this.askView = new LargeButtonView(paramContext, "我要提问");
    this.askView.setEventHandler(this);
    addFooterView(this.askView);
    setAdapter(this.adapter);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("onclick"))
      dispatchActionEvent(paramString, paramObject2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.adapter.setData((List)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.faq.FaqListView
 * JD-Core Version:    0.6.2
 */