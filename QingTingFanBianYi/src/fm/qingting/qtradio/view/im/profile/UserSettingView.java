package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.manager.SkinManager;
import java.util.ArrayList;

public class UserSettingView extends ViewGroupViewImpl
  implements IEventHandler
{
  private CancelActionView mActionView;
  private CustomizedAdapter mAdapter;
  private IAdapterIViewFactory mFactory;
  private ListView mListView;

  public UserSettingView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mFactory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new ImSettingItemView(UserSettingView.this.getContext(), this.val$hash);
      }
    };
    this.mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
    this.mListView = new ListView(paramContext);
    this.mListView.setDivider(null);
    this.mListView.setAdapter(this.mAdapter);
    addView(this.mListView);
    this.mActionView = new CancelActionView(paramContext);
    this.mActionView.setEventHandler(this);
    this.mActionView.update("setData", "取消关注");
    addView(this.mActionView);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("useraction"))
      dispatchActionEvent("unfollow", paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.mActionView.layout(0, paramInt4 - paramInt2 - this.mActionView.getMeasuredHeight(), paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mListView.measure(paramInt1, paramInt2);
    this.mActionView.measure(paramInt1, paramInt2);
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.UserSettingView
 * JD-Core Version:    0.6.2
 */