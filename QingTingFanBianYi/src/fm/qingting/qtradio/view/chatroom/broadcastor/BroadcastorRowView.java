package fm.qingting.qtradio.view.chatroom.broadcastor;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.model.ProgramNode;
import java.util.ArrayList;
import java.util.List;

class BroadcastorRowView extends ViewGroupViewImpl
  implements IEventHandler
{
  private final ViewLayout itemLayout = this.standardLayout.createChildLT(150, 160, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private List<ChatroomBroadcastorCellView> mChildrens;
  private ProgramNode mProgramNode;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(500, 160, 500, 160, 0, 0, ViewLayout.FILL);

  public BroadcastorRowView(Context paramContext)
  {
    super(paramContext);
  }

  private void buildViews()
  {
    removeAllViews();
    if (this.mChildrens != null)
      this.mChildrens.clear();
    if (this.mProgramNode == null);
    List localList;
    do
    {
      return;
      localList = this.mProgramNode.lstBroadcaster;
    }
    while (localList == null);
    if (this.mChildrens == null)
      this.mChildrens = new ArrayList();
    int i = 0;
    while (i < localList.size())
    {
      ChatroomBroadcastorCellView localChatroomBroadcastorCellView = new ChatroomBroadcastorCellView(getContext());
      localChatroomBroadcastorCellView.update("setData", localList.get(i));
      this.mChildrens.add(localChatroomBroadcastorCellView);
      localChatroomBroadcastorCellView.setEventHandler(this);
      addView(localChatroomBroadcastorCellView);
      i += 1;
    }
    requestLayout();
  }

  private int getThisWidth()
  {
    if (this.mChildrens == null)
      return 0;
    int i = 0;
    int j = 0;
    while (i < this.mChildrens.size())
    {
      j += ((ChatroomBroadcastorCellView)this.mChildrens.get(i)).getMeasuredWidth();
      i += 1;
    }
    return j;
  }

  private void layoutChildrens()
  {
    if (this.mChildrens == null);
    while (true)
    {
      return;
      int i = 0;
      int k;
      for (int j = 0; i < this.mChildrens.size(); j = k)
      {
        ChatroomBroadcastorCellView localChatroomBroadcastorCellView = (ChatroomBroadcastorCellView)this.mChildrens.get(i);
        k = localChatroomBroadcastorCellView.getMeasuredWidth() + j;
        localChatroomBroadcastorCellView.layout(j, 0, k, this.itemLayout.height);
        i += 1;
      }
    }
  }

  private void measureChildrens()
  {
    if (this.mChildrens == null);
    while (true)
    {
      return;
      int i = 0;
      while (i < this.mChildrens.size())
      {
        this.itemLayout.measureView((View)this.mChildrens.get(i));
        i += 1;
      }
    }
  }

  public void close(boolean paramBoolean)
  {
    if (this.mChildrens != null)
    {
      int i = 0;
      while (i < this.mChildrens.size())
      {
        ((ChatroomBroadcastorCellView)this.mChildrens.get(i)).close(paramBoolean);
        i += 1;
      }
    }
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("flowerToPoint"))
      dispatchActionEvent(paramString, paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    layoutChildrens();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.itemLayout.scaleToBounds(this.standardLayout);
    measureChildrens();
    setMeasuredDimension(getThisWidth(), this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("resetState"))
      if (this.mChildrens != null);
    while (true)
    {
      return;
      int i = 0;
      while (i < this.mChildrens.size())
      {
        ((ChatroomBroadcastorCellView)this.mChildrens.get(i)).update("resetState", null);
        i += 1;
      }
      continue;
      if (paramString.equalsIgnoreCase("setHeadInfo"))
      {
        this.mProgramNode = ((ProgramNode)paramObject);
        buildViews();
        return;
      }
      if ((paramString.equalsIgnoreCase("changeFlowerState")) && (this.mChildrens != null))
      {
        i = 0;
        while (i < this.mChildrens.size())
        {
          ((ChatroomBroadcastorCellView)this.mChildrens.get(i)).update("changeFlowerState", null);
          i += 1;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.broadcastor.BroadcastorRowView
 * JD-Core Version:    0.6.2
 */