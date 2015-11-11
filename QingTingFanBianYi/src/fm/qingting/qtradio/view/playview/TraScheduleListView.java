package fm.qingting.qtradio.view.playview;

import android.content.Context;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ListViewImpl;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.utils.ListUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TraScheduleListView extends ListViewImpl
{
  private CustomizedAdapter mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
  private IAdapterIViewFactory mFactory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new TraScheduleItemView(TraScheduleListView.this.getContext(), this.val$hash);
    }
  };

  public TraScheduleListView(Context paramContext)
  {
    super(paramContext);
    setDivider(null);
    setAdapter(this.mAdapter);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (List)paramObject;
      if (paramString != null);
    }
    else
    {
      return;
    }
    paramObject = InfoManager.getInstance().root().getCurrentPlayingNode();
    int i;
    int j;
    if ((paramObject != null) && (paramObject.nodeName.equalsIgnoreCase("program")))
    {
      Iterator localIterator = paramString.iterator();
      i = 0;
      if (localIterator.hasNext())
        if (((ProgramNode)localIterator.next()).id == ((ProgramNode)paramObject).id)
          j = 1;
    }
    while (true)
    {
      this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
      if ((j != 0) && (i > 0))
      {
        setSelection(i);
        return;
        i += 1;
        break;
      }
      setSelection(0);
      return;
      j = 0;
      continue;
      j = 0;
      i = 0;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.TraScheduleListView
 * JD-Core Version:    0.6.2
 */