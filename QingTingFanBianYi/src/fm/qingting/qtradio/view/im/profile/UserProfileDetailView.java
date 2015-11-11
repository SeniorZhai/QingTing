package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import fm.qingting.framework.view.LinearLayoutViewImpl;

class UserProfileDetailView extends LinearLayoutViewImpl
{
  private MutipleItemsView mGroupView;
  private PersonalProfileView mPersonalProfileView;
  private SingleItemView mWhatsupView;

  public UserProfileDetailView(Context paramContext)
  {
    super(paramContext);
    setOrientation(1);
    this.mPersonalProfileView = new PersonalProfileView(paramContext);
    addView(this.mPersonalProfileView);
    this.mWhatsupView = new SingleItemView(paramContext);
    addView(this.mWhatsupView);
    this.mWhatsupView.update("needfillline", null);
    this.mGroupView = new MutipleItemsView(paramContext);
    addView(this.mGroupView);
  }

  public void close(boolean paramBoolean)
  {
    this.mPersonalProfileView.close(paramBoolean);
    this.mWhatsupView.close(paramBoolean);
    this.mGroupView.close(paramBoolean);
    super.close(paramBoolean);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mPersonalProfileView.update(paramString, paramObject);
      this.mWhatsupView.update(paramString, paramObject);
      this.mWhatsupView.update("setType", "签名");
      this.mGroupView.update(paramString, paramObject);
      this.mGroupView.update("setType", "群组");
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.UserProfileDetailView
 * JD-Core Version:    0.6.2
 */