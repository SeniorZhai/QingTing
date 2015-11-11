package fm.qingting.qtradio.view.im.chat;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.room.UserInfo;
import java.util.ArrayList;
import java.util.List;

class AdminsRowView extends ViewGroupViewImpl
  implements IEventHandler
{
  private final ViewLayout itemLayout = this.standardLayout.createChildLT(150, 160, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private List<UserInfo> mAdmins;
  private List<ImChatAdminCellView> mChildrens;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(500, 160, 500, 160, 0, 0, ViewLayout.FILL);

  public AdminsRowView(Context paramContext)
  {
    super(paramContext);
  }

  private void buildViews()
  {
    removeAllViews();
    if (this.mChildrens != null)
      this.mChildrens.clear();
    if (this.mAdmins == null)
      return;
    if (this.mChildrens == null)
      this.mChildrens = new ArrayList();
    int i = 0;
    while (i < this.mAdmins.size())
    {
      ImChatAdminCellView localImChatAdminCellView = new ImChatAdminCellView(getContext());
      localImChatAdminCellView.update("setData", this.mAdmins.get(i));
      this.mChildrens.add(localImChatAdminCellView);
      localImChatAdminCellView.setEventHandler(this);
      addView(localImChatAdminCellView);
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
      j += ((ImChatAdminCellView)this.mChildrens.get(i)).getMeasuredWidth();
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
        ImChatAdminCellView localImChatAdminCellView = (ImChatAdminCellView)this.mChildrens.get(i);
        k = localImChatAdminCellView.getMeasuredWidth() + j;
        localImChatAdminCellView.layout(j, 0, k, this.itemLayout.height);
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
        ((ImChatAdminCellView)this.mChildrens.get(i)).close(paramBoolean);
        i += 1;
      }
    }
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
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
        ((ImChatAdminCellView)this.mChildrens.get(i)).update("resetState", null);
        i += 1;
      }
      continue;
      if (paramString.equalsIgnoreCase("setHeadInfo"))
      {
        this.mAdmins = ((List)paramObject);
        buildViews();
        return;
      }
      if ((paramString.equalsIgnoreCase("changeFlowerState")) && (this.mChildrens != null))
      {
        i = 0;
        while (i < this.mChildrens.size())
        {
          ((ImChatAdminCellView)this.mChildrens.get(i)).update("changeFlowerState", null);
          i += 1;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.chat.AdminsRowView
 * JD-Core Version:    0.6.2
 */