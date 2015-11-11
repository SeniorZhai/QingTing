package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import fm.qingting.framework.view.ScrollViewImpl;

class GroupProfileScrollView extends ScrollViewImpl
{
  private GroupProfileDetailView mDetailView;

  public GroupProfileScrollView(Context paramContext)
  {
    super(paramContext);
    this.mDetailView = new GroupProfileDetailView(paramContext);
    addView(this.mDetailView);
  }

  public void close(boolean paramBoolean)
  {
    this.mDetailView.close(paramBoolean);
    super.close(paramBoolean);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    this.mDetailView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.GroupProfileScrollView
 * JD-Core Version:    0.6.2
 */