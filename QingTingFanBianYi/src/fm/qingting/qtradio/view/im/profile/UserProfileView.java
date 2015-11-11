package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import android.text.TextUtils;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.im.IMContacts;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.social.UserProfile;

public class UserProfileView extends ViewGroupViewImpl
  implements IEventHandler
{
  private UserActionView mBottomView;
  private UserDoubleActionView mDoubleActionView;
  private UserProfileScrollView mScrollView;

  public UserProfileView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mScrollView = new UserProfileScrollView(paramContext);
    addView(this.mScrollView);
    this.mBottomView = new UserActionView(paramContext);
    this.mBottomView.update("setData", "发送消息");
    this.mBottomView.setEventHandler(this);
    addView(this.mBottomView);
    this.mDoubleActionView = new UserDoubleActionView(paramContext);
    this.mDoubleActionView.update("setDataLeft", "发送消息");
    this.mDoubleActionView.update("setDataRight", "关注TA");
    this.mDoubleActionView.setEventHandler(this);
    addView(this.mDoubleActionView);
    this.mDoubleActionView.setVisibility(4);
  }

  public void close(boolean paramBoolean)
  {
    this.mScrollView.close(paramBoolean);
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, final String paramString, Object paramObject2)
  {
    if (CloudCenter.getInstance().isLogin(false))
    {
      if (paramString.equalsIgnoreCase("useraction"))
        dispatchActionEvent("sendMessage", null);
      do
      {
        return;
        if (paramString.equalsIgnoreCase("useractionleft"))
        {
          dispatchActionEvent("sendMessage", null);
          return;
        }
      }
      while (!paramString.equalsIgnoreCase("useractionright"));
      dispatchActionEvent("followuser", null);
      this.mBottomView.setVisibility(0);
      this.mDoubleActionView.setVisibility(4);
      return;
    }
    paramObject1 = new CloudCenter.OnLoginEventListerner()
    {
      public void onLoginFailed(int paramAnonymousInt)
      {
      }

      public void onLoginSuccessed(int paramAnonymousInt)
      {
        if (paramString.equalsIgnoreCase("useraction"))
          UserProfileView.this.dispatchActionEvent("sendMessage", null);
        do
        {
          return;
          if (paramString.equalsIgnoreCase("useractionleft"))
          {
            UserProfileView.this.dispatchActionEvent("sendMessage", null);
            return;
          }
        }
        while (!paramString.equalsIgnoreCase("useractionright"));
        UserProfileView.this.dispatchActionEvent("followuser", null);
        UserProfileView.this.mBottomView.setVisibility(0);
        UserProfileView.this.mDoubleActionView.setVisibility(4);
      }
    };
    EventDispacthManager.getInstance().dispatchAction("showLogin", paramObject1);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mScrollView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2 - this.mBottomView.getMeasuredHeight());
    this.mBottomView.layout(0, paramInt4 - paramInt2 - this.mBottomView.getMeasuredHeight(), paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.mDoubleActionView.layout(0, paramInt4 - paramInt2 - this.mBottomView.getMeasuredHeight(), paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mScrollView.measure(paramInt1, paramInt2);
    this.mBottomView.measure(paramInt1, paramInt2);
    this.mDoubleActionView.measure(paramInt1, paramInt2);
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
  }

  public void update(String paramString, Object paramObject)
  {
    int j = 4;
    if (paramString.equalsIgnoreCase("setData"))
    {
      if (paramObject == null)
      {
        this.mBottomView.setVisibility(4);
        this.mDoubleActionView.setVisibility(4);
      }
    }
    else
      return;
    this.mScrollView.update(paramString, paramObject);
    paramString = (UserProfile)paramObject;
    if (TextUtils.equals(paramString.getUserKey(), InfoManager.getInstance().getUserProfile().getUserKey()))
    {
      this.mBottomView.setVisibility(4);
      this.mDoubleActionView.setVisibility(4);
      return;
    }
    boolean bool = IMContacts.getInstance().hasWatchedUser(paramString.getUserKey());
    paramString = this.mBottomView;
    if (bool)
    {
      i = 0;
      paramString.setVisibility(i);
      paramString = this.mDoubleActionView;
      if (!bool)
        break label136;
    }
    label136: for (int i = j; ; i = 0)
    {
      paramString.setVisibility(i);
      return;
      i = 4;
      break;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.UserProfileView
 * JD-Core Version:    0.6.2
 */