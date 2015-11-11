package fm.qingting.qtradio.view.playview_bb;

import android.content.Context;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.view.IView;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.LoadMoreListView;
import fm.qingting.utils.ListUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DanmakuVirtualPlaylistView extends LoadMoreListView
{
  private CustomizedAdapter mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
  private IAdapterIViewFactory mFactory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new DanmakuVirtualPlaylistItemView(DanmakuVirtualPlaylistView.this.getContext(), this.val$hash);
    }
  };

  public DanmakuVirtualPlaylistView(Context paramContext)
  {
    super(paramContext);
    setDivider(null);
    setAdapter(this.mAdapter);
  }

  public void update(String paramString, Object paramObject)
  {
    int i;
    label55: int j;
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (List)paramObject;
      if (paramString == null)
        return;
      paramObject = InfoManager.getInstance().root().getCurrentPlayingNode();
      if ((paramObject == null) || (!paramObject.nodeName.equalsIgnoreCase("program")))
        break label168;
      Iterator localIterator = paramString.iterator();
      i = 0;
      if (!localIterator.hasNext())
        break label162;
      if (((ProgramNode)localIterator.next()).uniqueId == ((ProgramNode)paramObject).uniqueId)
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
        break label55;
      }
      setSelection(0);
      return;
      if (!paramString.equalsIgnoreCase("addmore"))
        break;
      paramString = (List)paramObject;
      if (paramString == null)
        break;
      this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
      return;
      label162: j = 0;
      continue;
      label168: j = 0;
      i = 0;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview_bb.DanmakuVirtualPlaylistView
 * JD-Core Version:    0.6.2
 */