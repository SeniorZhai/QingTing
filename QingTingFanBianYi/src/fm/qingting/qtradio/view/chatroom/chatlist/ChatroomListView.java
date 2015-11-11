package fm.qingting.qtradio.view.chatroom.chatlist;

import android.content.Context;
import android.widget.ListAdapter;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ListViewImpl;
import java.util.ArrayList;
import java.util.List;

public class ChatroomListView extends ListViewImpl
  implements IEventHandler
{
  private ChatAdapter mAdapter;
  private IChatAdapterIViewFactory mFactory;
  private boolean mFirst = true;

  public ChatroomListView(Context paramContext, final boolean paramBoolean)
  {
    super(paramContext);
    setBackgroundColor(-1118482);
    this.mFactory = new IChatAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default:
          return null;
        case 16:
          return new ChatroomItemLeftView(ChatroomListView.this.getContext(), this.val$hash, paramBoolean);
        case 0:
          return new ChatroomItemRightView(ChatroomListView.this.getContext(), this.val$hash);
        case 32:
        }
        return new ChatroomTimestampView(ChatroomListView.this.getContext());
      }
    };
    this.mAdapter = new ChatAdapter(new ArrayList(), this.mFactory);
    this.mAdapter.setEventHandler(this);
    setVerticalScrollBarEnabled(false);
    setVerticalFadingEdgeEnabled(false);
    setCacheColorHint(0);
    setSelector(17170445);
    setDivider(null);
    setAdapter(this.mAdapter);
  }

  private boolean lastIsMine(List<ChatItem> paramList)
  {
    if (paramList == null);
    while (paramList.size() == 0)
      return false;
    paramList = (ChatItem)paramList.get(paramList.size() - 1);
    if ((paramList.type == 3) || (paramList.type == 2) || (paramList.type == 4) || (paramList.type == 1));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      paramObject1 = (ItemParam)paramObject2;
      paramString = paramObject1.type;
      if (paramString.equalsIgnoreCase("copyPop"))
        dispatchActionEvent(paramString, paramObject1.param);
    }
    while (!paramString.equalsIgnoreCase("select"))
    {
      do
      {
        return;
        if (paramString.equalsIgnoreCase("select"))
        {
          dispatchActionEvent("reply", paramObject1);
          return;
        }
      }
      while (!paramString.equalsIgnoreCase("selectBlank"));
      dispatchActionEvent("selectBlack", null);
      return;
    }
    dispatchActionEvent(paramString, paramObject2);
  }

  public void scrollToPosition(int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt1 >= 0)
    {
      i = paramInt1;
      if (paramInt1 <= getAdapter().getCount() - 1);
    }
    else
    {
      i = getAdapter().getCount() - 1;
    }
    setSelectionFromTop(i, paramInt2);
    try
    {
      layoutChildren();
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      localIllegalStateException.printStackTrace();
    }
  }

  public void update(String paramString, Object paramObject)
  {
    int i;
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (List)paramObject;
      if (getLastVisiblePosition() == getCount() - 1)
      {
        i = 1;
        boolean bool = lastIsMine(paramString);
        this.mAdapter.setData(paramString);
        if ((this.mFirst) || (i != 0) || (bool))
        {
          this.mFirst = false;
          setSelection(getAdapter().getCount() - 1);
        }
      }
    }
    do
    {
      do
      {
        return;
        i = 0;
        break;
        if (paramString.equalsIgnoreCase("scroll"))
        {
          setSelection(getAdapter().getCount() - 1);
          return;
        }
        if (paramString.equalsIgnoreCase("scrollToBottom"))
        {
          int j = ((Integer)paramObject).intValue();
          if (j >= 0)
          {
            i = j;
            if (j <= getAdapter().getCount() - 2);
          }
          else
          {
            i = getAdapter().getCount() - 2;
          }
          setSelection(i);
          layoutChildren();
          return;
        }
      }
      while (!paramString.equalsIgnoreCase("scrollToFirstIndexAtMe"));
      i = ((Integer)paramObject).intValue();
    }
    while ((i < 0) || (i >= this.mAdapter.getCount()));
    setSelection(i);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.chatlist.ChatroomListView
 * JD-Core Version:    0.6.2
 */