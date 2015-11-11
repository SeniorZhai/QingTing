package fm.qingting.qtradio.view.chatroom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.widget.Toast;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.BroadcasterNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.room.ChatData;
import fm.qingting.qtradio.room.CustomData;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.room.WeiboData;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.weiboAgent.WeiboAgent;
import fm.qingting.social.SocialEventListener;
import fm.qingting.social.api.SinaWeiboApi;
import java.util.List;

public class ChatUserActionPopView extends QtListItemView
{
  private static Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
        Toast.makeText(InfoManager.getInstance().getContext(), "关注成功！", 0).show();
        return;
      case 2:
        Toast.makeText(InfoManager.getInstance().getContext(), "关注失败", 0).show();
        return;
      case 3:
      }
      Toast.makeText(InfoManager.getInstance().getContext(), "取消关注", 0).show();
    }
  };
  private final ViewLayout buttonLayout = this.itemLayout.createChildLT(630, 98, 45, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = this.standardLayout.createChildLT(720, 120, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final Paint mBgPaint = new Paint();
  private RectF mBgRect = new RectF();
  private String mBroadcasterName;
  private RectF mButtonRectF = new RectF();
  private Object mData;
  private Paint mHighlightButtonPaint = new Paint();
  private boolean mInTouchMode = false;
  private float mLastMotionY = 0.0F;
  private List<UserActionType> mLstTypes;
  private final Paint mNameHighlightPaint = new Paint();
  private final Paint mNamePaint = new Paint();
  private Paint mNormalButtonPaint = new Paint();
  private int mSelectedIndex = -1;
  private Rect mTextBound = new Rect();
  private String mTitle;
  private final Paint mTitlePaint = new Paint();
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(600, 45, 20, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout roundLayout = this.itemLayout.createChildLT(10, 10, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 7200, 720, 7200, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(720, 60, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public ChatUserActionPopView(Context paramContext)
  {
    super(paramContext);
    this.mBgPaint.setColor(SkinManager.getPopBgColor());
    this.mBgPaint.setStyle(Paint.Style.FILL);
    this.mNamePaint.setColor(SkinManager.getPopTextColor());
    this.mNamePaint.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mNameHighlightPaint.setColor(SkinManager.getBackgroundColor());
    this.mNameHighlightPaint.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mNormalButtonPaint.setColor(SkinManager.getPopButtonNormalColor());
    this.mHighlightButtonPaint.setColor(SkinManager.getPopButtonHighlightColor());
    this.mNormalButtonPaint.setStyle(Paint.Style.FILL);
    this.mHighlightButtonPaint.setStyle(Paint.Style.FILL);
    this.mTitlePaint.setColor(SkinManager.getTextColorNormal());
    setItemSelectedEnable();
  }

  private void checkProfile()
  {
    if (this.mData == null);
    while (!(this.mData instanceof ChatData))
      return;
    openUserProfile(((ChatData)this.mData).user);
  }

  private void drawButton(Canvas paramCanvas, int paramInt, boolean paramBoolean)
  {
    this.mButtonRectF.offset(0.0F, paramInt);
    RectF localRectF = this.mButtonRectF;
    float f1 = this.roundLayout.width;
    float f2 = this.roundLayout.height;
    if (paramBoolean);
    for (Paint localPaint = this.mHighlightButtonPaint; ; localPaint = this.mNormalButtonPaint)
    {
      paramCanvas.drawRoundRect(localRectF, f1, f2, localPaint);
      this.mButtonRectF.offset(0.0F, -paramInt);
      return;
    }
  }

  private void drawItem(Canvas paramCanvas, UserActionType paramUserActionType, int paramInt, boolean paramBoolean)
  {
    drawButton(paramCanvas, paramInt, paramBoolean);
    String str = paramUserActionType.getName();
    this.mNamePaint.getTextBounds(str, 0, str.length(), this.mTextBound);
    float f1 = (this.standardLayout.width - this.mTextBound.width()) / 2;
    float f2 = this.itemLayout.height + paramInt + (-this.buttonLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2;
    if (paramBoolean);
    for (paramUserActionType = this.mNameHighlightPaint; ; paramUserActionType = this.mNamePaint)
    {
      paramCanvas.drawText(str, f1, f2, paramUserActionType);
      return;
    }
  }

  private void drawItems(Canvas paramCanvas)
  {
    this.mBgRect.set(0.0F, this.standardLayout.height - this.mLstTypes.size() * this.itemLayout.height - this.itemLayout.height + this.buttonLayout.height - this.titleLayout.height, this.standardLayout.width, this.standardLayout.height + this.roundLayout.height);
    paramCanvas.drawRoundRect(this.mBgRect, this.roundLayout.width, this.roundLayout.height, this.mBgPaint);
    drawTitle(paramCanvas);
    int i = 0;
    if (i < this.mLstTypes.size())
    {
      UserActionType localUserActionType = (UserActionType)this.mLstTypes.get(i);
      int j = (int)(this.mBgRect.top + this.titleLayout.height + this.itemLayout.height * i);
      if (this.mSelectedIndex == i);
      for (boolean bool = true; ; bool = false)
      {
        drawItem(paramCanvas, localUserActionType, j, bool);
        i += 1;
        break;
      }
    }
  }

  private void drawTitle(Canvas paramCanvas)
  {
    if (this.mTitle == null)
      return;
    this.mTitlePaint.getTextBounds(this.mTitle, 0, this.mTitle.length(), this.mTextBound);
    paramCanvas.drawText(this.mTitle, (this.standardLayout.width - this.mTextBound.left - this.mTextBound.right) / 2, this.mBgRect.top + (this.titleLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, this.mTitlePaint);
  }

  private void follow()
  {
    if ((this.mBroadcasterName == null) || (this.mBroadcasterName.equalsIgnoreCase("")))
      return;
    if (!WeiboAgent.getInstance().isSessionValid().booleanValue())
    {
      Toast.makeText(InfoManager.getInstance().getContext(), "请先登录新浪微博", 0).show();
      return;
    }
    String str = this.mBroadcasterName;
    SinaWeiboApi.follow(getContext(), str, new SocialEventListener()
    {
      public void onCancel(Object paramAnonymousObject)
      {
        ChatUserActionPopView.mHandler.sendEmptyMessage(3);
      }

      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        ChatUserActionPopView.mHandler.sendEmptyMessage(1);
      }

      public void onException(Object paramAnonymousObject)
      {
        ChatUserActionPopView.mHandler.sendEmptyMessage(2);
      }
    });
  }

  private void generateRect()
  {
    this.mButtonRectF.set(this.buttonLayout.leftMargin, this.itemLayout.height - this.buttonLayout.height, this.buttonLayout.leftMargin + this.buttonLayout.width, this.itemLayout.height);
  }

  private int getSelectedIndex()
  {
    if (this.mLstTypes == null);
    do
    {
      do
        return -1;
      while (this.mLastMotionY < this.mBgRect.top);
      if (this.mLastMotionY < this.mBgRect.top + this.titleLayout.height)
        return 100;
    }
    while (this.mLastMotionY > this.standardLayout.height - this.itemLayout.height + this.buttonLayout.height);
    return (int)((this.mLastMotionY - this.mBgRect.top - this.titleLayout.height) / this.itemLayout.height);
  }

  private void handleAction(UserActionType paramUserActionType)
  {
    switch (4.$SwitchMap$fm$qingting$qtradio$view$chatroom$UserActionType[paramUserActionType.ordinal()])
    {
    default:
      return;
    case 1:
      dispatchActionEvent("cancelPopWithoutAnimation", null);
      sayToIt();
      return;
    case 2:
      dispatchActionEvent("cancelPopWithoutAnimation", null);
      checkProfile();
      return;
    case 3:
      dispatchActionEvent("cancelPopWithoutAnimation", null);
      showHistory();
      return;
    case 4:
      dispatchActionEvent("cancelPopWithoutAnimation", null);
      follow();
      return;
    case 5:
    }
    dispatchActionEvent("cancelPop", null);
  }

  private boolean isSupportProfile(ChatData paramChatData)
  {
    if (paramChatData == null);
    while ((paramChatData.user == null) || (paramChatData.user.userKey == null) || (paramChatData.user.userKey.equalsIgnoreCase("")))
      return false;
    return true;
  }

  private void openUserProfile(final UserInfo paramUserInfo)
  {
    if (!CloudCenter.getInstance().isLogin(false))
    {
      paramUserInfo = new CloudCenter.OnLoginEventListerner()
      {
        public void onLoginFailed(int paramAnonymousInt)
        {
        }

        public void onLoginSuccessed(int paramAnonymousInt)
        {
          ControllerManager.getInstance().redirectToUsersProfileView(paramUserInfo);
        }
      };
      EventDispacthManager.getInstance().dispatchAction("showLogin", paramUserInfo);
      return;
    }
    ControllerManager.getInstance().redirectToUsersProfileView(paramUserInfo);
  }

  private void sayToIt()
  {
    ViewController localViewController = ControllerManager.getInstance().getLastViewController();
    if ((localViewController != null) && (localViewController.controllerName.equalsIgnoreCase("chatroom")))
      localViewController.config("sayToIt", this.mData);
  }

  private void showHistory()
  {
    ViewController localViewController = ControllerManager.getInstance().getLastViewController();
    if ((localViewController != null) && (localViewController.controllerName.equalsIgnoreCase("chatroom")))
      localViewController.config("showchathistory", this.mData);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.mLstTypes == null)
      return;
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawItems(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.itemLayout.scaleToBounds(this.standardLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.roundLayout.scaleToBounds(this.itemLayout);
    this.buttonLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.mTitlePaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    generateRect();
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((!this.mInTouchMode) && (paramMotionEvent.getAction() != 0));
    do
    {
      do
      {
        do
        {
          return true;
          switch (paramMotionEvent.getAction())
          {
          default:
            return true;
          case 0:
            this.mLastMotionY = paramMotionEvent.getY();
            this.mInTouchMode = true;
            this.mSelectedIndex = getSelectedIndex();
            if (this.mSelectedIndex == -1)
            {
              this.mInTouchMode = false;
              dispatchActionEvent("cancelPop", null);
              return true;
            }
            invalidate();
            return true;
          case 2:
            this.mLastMotionY = paramMotionEvent.getY();
          case 3:
          case 1:
          }
        }
        while (getSelectedIndex() == this.mSelectedIndex);
        this.mSelectedIndex = -1;
        this.mInTouchMode = false;
      }
      while (!isItemPressed());
      invalidate();
      return true;
      this.mSelectedIndex = -1;
    }
    while (!isItemPressed());
    invalidate();
    return true;
    if ((this.mSelectedIndex > -1) && (this.mSelectedIndex < this.mLstTypes.size()))
      handleAction((UserActionType)this.mLstTypes.get(this.mSelectedIndex));
    this.mSelectedIndex = -1;
    invalidate();
    return true;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setBubbleData"))
    {
      this.mData = paramObject;
      if (!(paramObject instanceof CustomData))
        break label143;
      paramString = (CustomData)paramObject;
      if (paramString.type != 1)
        break label108;
      paramString = (ChatData)paramString;
      this.mTitle = paramString.user.snsInfo.sns_name;
      if (!isSupportProfile(paramString))
        break label98;
      this.mLstTypes = UserActionType.getUserActionTypes();
      if ((this.mTitle == null) || (this.mTitle.length() == 0))
        this.mTitle = "请选择操作";
      invalidate();
    }
    label98: label108: 
    while (!(paramObject instanceof BroadcasterNode))
      while (true)
      {
        return;
        this.mLstTypes = UserActionType.getSimpleUserActionTypes();
        continue;
        if (paramString.type == 2)
        {
          this.mTitle = ((WeiboData)paramString).user.snsInfo.sns_name;
          this.mLstTypes = UserActionType.getSimpleUserActionTypes();
        }
      }
    label143: paramString = (BroadcasterNode)paramObject;
    this.mTitle = paramString.nick;
    this.mBroadcasterName = paramString.weiboName;
    this.mLstTypes = UserActionType.getDjActionTypes();
    if ((this.mTitle == null) || (this.mTitle.length() == 0))
      this.mTitle = "请选择操作";
    invalidate();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.ChatUserActionPopView
 * JD-Core Version:    0.6.2
 */