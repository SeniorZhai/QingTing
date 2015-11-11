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

public class ScheduleListView extends ListViewImpl
{
  private CustomizedAdapter mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
  private IAdapterIViewFactory mFactory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new PlayListItemView(ScheduleListView.this.getContext(), this.val$hash);
    }
  };

  public ScheduleListView(Context paramContext)
  {
    super(paramContext);
    setDivider(null);
    setAdapter(this.mAdapter);
  }

  public void update(String paramString, Object paramObject)
  {
    int i;
    int j;
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (List)paramObject;
      paramObject = InfoManager.getInstance().root().getCurrentPlayingNode();
      if ((paramObject == null) || (!paramObject.nodeName.equalsIgnoreCase("program")))
        break label133;
      Iterator localIterator = paramString.iterator();
      i = 0;
      if (!localIterator.hasNext())
        break label127;
      if (((ProgramNode)localIterator.next()).uniqueId != ((ProgramNode)paramObject).uniqueId)
        break label112;
      j = 1;
    }
    while (true)
    {
      this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
      if ((j != 0) && (i > 0))
      {
        setSelection(i);
        return;
        label112: i += 1;
        break;
      }
      setSelection(0);
      return;
      label127: j = 0;
      continue;
      label133: j = 0;
      i = 0;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.ScheduleListView
 * JD-Core Version:    0.6.2
 */