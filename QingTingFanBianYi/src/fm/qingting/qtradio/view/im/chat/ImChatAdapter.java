package fm.qingting.qtradio.view.im.chat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fm.qingting.framework.adapter.ILayoutParamsBuilder;
import fm.qingting.framework.adapter.IReusableCollection;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import fm.qingting.qtradio.view.chatroom.chatlist.ChatItem;
import fm.qingting.qtradio.view.chatroom.chatlist.IChatAdapterIViewFactory;
import java.util.List;

public class ImChatAdapter extends BaseAdapter
{
  protected ILayoutParamsBuilder builder;
  protected List<ChatItem> data;
  protected IEventHandler eventHandler;
  protected IChatAdapterIViewFactory factory;
  protected String idKey;

  public ImChatAdapter(List<ChatItem> paramList, IChatAdapterIViewFactory paramIChatAdapterIViewFactory)
  {
    this.data = paramList;
    this.factory = paramIChatAdapterIViewFactory;
  }

  public void build(List<ChatItem> paramList, IChatAdapterIViewFactory paramIChatAdapterIViewFactory)
  {
    this.factory = paramIChatAdapterIViewFactory;
    setData(paramList);
  }

  public void clear()
  {
    this.data.clear();
    notifyDataSetChanged();
  }

  public int getCount()
  {
    if (this.data == null)
      return 0;
    try
    {
      int i = this.data.size();
      return i;
    }
    catch (Exception localException)
    {
    }
    return 1;
  }

  public List<ChatItem> getData()
  {
    return this.data;
  }

  public ChatItem getItem(int paramInt)
  {
    if (this.data == null);
    while (this.data.size() <= paramInt)
      return null;
    return (ChatItem)this.data.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    ChatItem localChatItem = getItem(paramInt);
    if (localChatItem == null)
      return 32;
    return (localChatItem.type & 0xF0) >> 4;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ChatItem localChatItem = getItem(paramInt);
    if (paramView == null)
      if ((paramViewGroup == null) || (!(paramViewGroup instanceof IReusableCollection)))
        break label142;
    label142: for (paramViewGroup = (IView)((IReusableCollection)paramViewGroup).getReusableItem(null); ; paramViewGroup = null)
    {
      paramView = paramViewGroup;
      if (paramViewGroup == null)
        paramView = this.factory.createView(localChatItem.type & 0xF0);
      paramViewGroup = paramView.getView();
      paramViewGroup.setTag(paramView);
      Object localObject = paramView;
      while (true)
      {
        ((IView)localObject).setEventHandler(null);
        if (localChatItem != null)
          ((IView)localObject).update("content", localChatItem);
        if (this.builder != null)
        {
          paramView = this.builder.getLayoutParams();
          if (paramView != null)
            paramViewGroup.setLayoutParams(paramView);
        }
        return paramViewGroup;
        localObject = (IView)paramView.getTag();
        paramViewGroup = paramView;
      }
    }
  }

  public int getViewTypeCount()
  {
    return 3;
  }

  public void setData(List<ChatItem> paramList)
  {
    this.data = paramList;
    notifyDataSetChanged();
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.eventHandler = paramIEventHandler;
  }

  public void setFactory(IChatAdapterIViewFactory paramIChatAdapterIViewFactory)
  {
    this.factory = paramIChatAdapterIViewFactory;
  }

  public void setIDKey(String paramString)
  {
    this.idKey = paramString;
  }

  public void setLayoutParamsBuilder(ILayoutParamsBuilder paramILayoutParamsBuilder)
  {
    this.builder = paramILayoutParamsBuilder;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.chat.ImChatAdapter
 * JD-Core Version:    0.6.2
 */