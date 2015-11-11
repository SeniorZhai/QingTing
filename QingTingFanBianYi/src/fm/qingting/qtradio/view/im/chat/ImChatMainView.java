package fm.qingting.qtradio.view.im.chat;

import android.content.Context;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.im.BaseUserInfoPool;
import fm.qingting.qtradio.im.BaseUserInfoPool.AvatarAndGender;
import fm.qingting.qtradio.im.BaseUserInfoPool.OnBaseUserinfoPutListener;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.im.IMContacts;
import fm.qingting.qtradio.im.UserProfileManager;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.room.AdminInfo;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.share.ShareUtil;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.chatroom.ChatActionsView;
import fm.qingting.qtradio.view.chatroom.ChatActionsView.ChatActionType;
import fm.qingting.qtradio.view.chatroom.chatlist.ChatItem;
import fm.qingting.qtradio.view.chatroom.chatlist.ChatroomTimestampView;
import fm.qingting.qtradio.view.chatroom.chatlist.IChatAdapterIViewFactory;
import fm.qingting.qtradio.view.chatroom.expression.ExpressionView;
import fm.qingting.qtradio.view.im.ChatMode;
import fm.qingting.qtradio.view.im.ImChatInviteView;
import fm.qingting.qtradio.view.im.profile.UserActionView;
import fm.qingting.utils.InputMethodUtil;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ScreenConfiguration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class ImChatMainView extends ViewGroupViewImpl
  implements IEventHandler, RootNode.IInfoUpdateEventListener, BaseUserInfoPool.OnBaseUserinfoPutListener
{
  public static final String ADD_HISTORY = "addhistory";
  public static final String ADD_MESSAGE = "addmessage";
  public static final String ADD_MESSAGES = "addMessages";
  private static final boolean API19 = QtApiLevelManager.isApiLevelSupported(19);
  private static final String[] CONTENTS = { "虽然这里有上千个主播，百万的节目，一天二十四小时不断更新，但我还是觉得@%s 的节目最有趣，献朵小花支持你！你又支持谁？下载链接：http://qingting.fm/app/download_xiaoyuan", "分享一个@蜻蜓FM 的群组%s,边听节目边聊天真的好欢乐~蜻蜓FM，倾听世界的声音。下载链接：http://qingting.fm/app/download_zazhi", "报告大家！发现一个新功能，只要使用@蜻蜓FM 收听【%s】就可以在群组里和%s全国各地的听众聊天啦！赶紧来【%s】群里和大家一起贫吧！下载请戳：http://qingting.fm/app/download_xiaoyuan2014", "用了@蜻蜓FM 才知道原来声音的世界也那么精彩~小说，音乐，娱乐八卦甚至还有全国的校园电台，一天二十四小时听不完的节目！和全国听友边听边聊还能和电台主播们实时互动，新时代的电台就是这么拽！下载链接：http://qingting.fm/app/download_quange" };
  private static final int NORMAL = 0;
  private static final int SHOWINGEXPRESSION = 1;
  private static final int SHOWINGINVITE = 2;
  private static final int SHOWINGMORE = 3;
  private final long TIME_INTERVAL = 1800L;
  private final ViewLayout bottomLayout = this.standardLayout.createChildLT(720, 106, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout chatMemberLayout = this.standardLayout.createChildLT(720, 380, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkinLayout = this.standardLayout.createChildLT(156, 74, 564, 130, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout headerLayout = this.standardLayout.createChildLT(720, 256, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ChatActionsView mActionsView;
  private ImChatAdapter mAdapter;
  private HashSet<String> mAdmins = new HashSet();
  private Calendar mCalendar;
  private Button mCheckinButton;
  private List<ChatItem> mDatas = new ArrayList();
  private int mDay;
  private ExpressionView mExpressionView;
  private IChatAdapterIViewFactory mFactory;
  private ImChatHeadView mHeadView;
  private ImChatInputView mInputView;
  private ImChatInviteView mInviteView;
  private boolean mIsInputShowing = false;
  private UserActionView mJoinView;
  private long mLastTimestamp = 0L;
  private LinearLayout mListContainer;
  private PullToRefreshListView mListView;
  private boolean mLoading = false;
  private int mMaxHeight = 0;
  private IMMessage mMessage;
  private long mOldestMsgSeq = 0L;
  private long mOldestTimeStamp = 0L;
  private Rect mOutRect = new Rect();
  private boolean mTalkingBlocked = false;
  private GroupInfo mTalkingGroupInfo;
  private String mTalkingId;
  private UserInfo mTalkingUserInfo;
  private int mViewState = 0;
  private int normalLayoutHeight = 0;
  private int specialLayoutHeight = -1;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public ImChatMainView(Context paramContext)
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
          return new ImChatItemLeftView(ImChatMainView.this.getContext(), this.val$hash, true);
        case 0:
          return new ImChatItemRightView(ImChatMainView.this.getContext(), this.val$hash);
        case 32:
        }
        return new ChatroomTimestampView(ImChatMainView.this.getContext());
      }
    };
    this.mAdapter = new ImChatAdapter(this.mDatas, this.mFactory);
    this.mListContainer = ((LinearLayout)LayoutInflater.from(paramContext).inflate(2130903040, null));
    this.mListView = ((PullToRefreshListView)this.mListContainer.findViewById(2131230731));
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setSelector(17170445);
    addView(this.mListContainer);
    this.mListView.setAdapter(this.mAdapter);
    this.mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener()
    {
      public void onRefresh(PullToRefreshBase<ListView> paramAnonymousPullToRefreshBase)
      {
        String str = DateUtils.formatDateTime(ImChatMainView.this.getContext(), System.currentTimeMillis(), 524305);
        paramAnonymousPullToRefreshBase.getLoadingLayoutProxy().setLastUpdatedLabel(str);
        new ImChatMainView.GetDataTask(ImChatMainView.this, null).execute(new Void[0]);
      }
    });
    this.mHeadView = new ImChatHeadView(paramContext);
    addView(this.mHeadView);
    this.mHeadView.setEventHandler(this);
    this.mInputView = new ImChatInputView(paramContext);
    addView(this.mInputView);
    this.mInputView.setEventHandler(this);
    this.mActionsView = new ChatActionsView(paramContext);
    this.mActionsView.setEventHandler(this);
    addView(this.mActionsView);
    this.mExpressionView = new ExpressionView(paramContext);
    this.mExpressionView.setEventHandler(this);
    addView(this.mExpressionView);
    this.mInviteView = new ImChatInviteView(paramContext);
    this.mInviteView.setEventHandler(this);
    addView(this.mInviteView);
    this.mCheckinButton = new Button(paramContext);
    this.mCheckinButton.setBackgroundResource(2130837575);
    this.mCheckinButton.setText("签到");
    addView(this.mCheckinButton);
    this.mCheckinButton.setTextColor(-1);
    this.mCheckinButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ImChatMainView.this.checkIn();
      }
    });
    this.mJoinView = new UserActionView(paramContext);
    this.mJoinView.update("setData", "加入群聊");
    addView(this.mJoinView);
    this.mJoinView.setEventHandler(this);
    this.mJoinView.setVisibility(8);
    resetBaseTime();
    BaseUserInfoPool.addListener(this);
    if (API19)
      getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
      {
        public void onGlobalLayout()
        {
          ImChatMainView.this.getWindowVisibleDisplayFrame(ImChatMainView.this.mOutRect);
          int i = ImChatMainView.this.mOutRect.height();
          if (ImChatMainView.this.mMaxHeight < i)
            ImChatMainView.access$302(ImChatMainView.this, i);
          if ((ImChatMainView.this.isInputShowingApi19()) && (!ImChatMainView.this.mIsInputShowing))
          {
            ImChatMainView.this.mListView.setTranscriptMode(2);
            ImChatMainView.this.requestLayout();
          }
          while ((ImChatMainView.this.isInputShowingApi19()) || (!ImChatMainView.this.mIsInputShowing))
            return;
          ImChatMainView.this.mListView.setTranscriptMode(2);
          ImChatMainView.this.requestLayout();
        }
      });
    this.mListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        if (paramAnonymousInt == 1)
          if (ImChatMainView.this.mIsInputShowing)
          {
            ImChatMainView.access$702(ImChatMainView.this, 0);
            InputMethodUtil.hideSoftInput(paramAnonymousAbsListView);
          }
        while (ImChatMainView.this.mViewState == 0)
          return;
        ImChatMainView.access$702(ImChatMainView.this, 0);
        ImChatMainView.this.requestLayout();
      }
    });
  }

  private void actualCheckin()
  {
    if (this.mTalkingGroupInfo == null)
      return;
    if (!IMContacts.getInstance().hasWatchedGroup(this.mTalkingId))
      InfoManager.getInstance().getUserProfile().followGroup(this.mTalkingId);
    String str2 = "";
    List localList = this.mTalkingGroupInfo.lstAdmins;
    String str1 = str2;
    int i;
    UserInfo localUserInfo;
    if (localList != null)
    {
      str1 = str2;
      if (localList.size() > 0)
      {
        i = 0;
        str2 = "";
        if (i < localList.size())
        {
          localUserInfo = (UserInfo)localList.get(i);
          if ((localUserInfo instanceof AdminInfo))
          {
            str1 = ((AdminInfo)localUserInfo).weiboName;
            if ((str1 != null) && (str1.length() != 0))
              break label270;
            str1 = localUserInfo.snsInfo.sns_name;
          }
        }
      }
    }
    label270: 
    while (true)
    {
      str2 = str2 + "@" + str1 + " ";
      i += 1;
      break;
      str1 = localUserInfo.snsInfo.sns_name;
      continue;
      str1 = str2 + "还有";
      str1 = String.format(Locale.CHINESE, CONTENTS[2], new Object[] { this.mTalkingGroupInfo.groupName, str1, this.mTalkingGroupInfo.groupName });
      sendMessage("签个到，大家好呀~~", 1);
      QTMSGManage.getInstance().sendStatistcsMessage("im_checkin");
      ShareUtil.shareToPlatform(str1, 8, 9);
      return;
    }
  }

  private void actualFlowerToUser(UserInfo paramUserInfo)
  {
    if (!IMContacts.getInstance().hasWatchedGroup(this.mTalkingId))
      InfoManager.getInstance().getUserProfile().followGroup(this.mTalkingId);
    if ((paramUserInfo instanceof AdminInfo))
    {
      String str2 = ((AdminInfo)paramUserInfo).weiboName;
      if (str2 != null)
      {
        str1 = str2;
        if (str2.length() != 0)
          break label62;
      }
    }
    for (String str1 = paramUserInfo.snsInfo.sns_name; ; str1 = paramUserInfo.snsInfo.sns_name)
    {
      label62: paramUserInfo = String.format(Locale.CHINESE, CONTENTS[0], new Object[] { str1 });
      QTMSGManage.getInstance().sendStatistcsMessage("im_flower");
      ShareUtil.shareToPlatform(paramUserInfo, 10, 11);
      return;
    }
  }

  private void actualSendMessage(String paramString, int paramInt)
  {
    int i = 4;
    if (this.mLoading)
    {
      this.mDatas.clear();
      this.mLoading = false;
    }
    String str = InfoManager.getInstance().getUserProfile().getUserKey();
    if (ChatMode.isGroup())
    {
      localObject = paramString;
      if (IMAgent.getInstance().isCheckin(paramInt))
      {
        localObject = paramString;
        if (this.mTalkingGroupInfo != null)
        {
          localObject = paramString;
          if (!IMAgent.getInstance().hasCheckIn(this.mTalkingGroupInfo.groupId))
            localObject = IMAgent.getInstance().getCheckinText();
        }
      }
      IMAgent.getInstance().sendGroupMsg((String)localObject, this.mTalkingGroupInfo, paramInt);
      paramString = new IMMessage();
      paramString.mMessage = ((String)localObject);
      paramString.chatType = 1;
      paramString.publish = (System.currentTimeMillis() / 1000L);
      l = paramString.publish;
      if (l - this.mLastTimestamp >= 1800L)
      {
        this.mDatas.add(new ChatItem(32, getTimestampBySecond(l)));
        this.mLastTimestamp = l;
      }
      localObject = this.mDatas;
      if (isAdmin(str));
      while (true)
      {
        ((List)localObject).add(new ChatItem(i, paramString));
        IMContacts.getInstance().addRecentContacts(this.mTalkingGroupInfo);
        this.mAdapter.notifyDataSetChanged();
        return;
        i = 1;
      }
    }
    if (this.mTalkingBlocked)
    {
      Toast.makeText(getContext(), "该账号已经被举报,无法接收消息", 1).show();
      return;
    }
    IMAgent.getInstance().sendUserMsg(paramString, this.mTalkingUserInfo, paramInt);
    Object localObject = new IMMessage();
    ((IMMessage)localObject).mMessage = paramString;
    ((IMMessage)localObject).chatType = 0;
    ((IMMessage)localObject).publish = (System.currentTimeMillis() / 1000L);
    long l = ((IMMessage)localObject).publish;
    if (l - this.mLastTimestamp >= 1800L)
    {
      this.mDatas.add(new ChatItem(32, getTimestampBySecond(l)));
      this.mLastTimestamp = l;
    }
    paramString = this.mDatas;
    if (isAdmin(str));
    while (true)
    {
      paramString.add(new ChatItem(i, localObject));
      IMContacts.getInstance().addRecentContacts(this.mTalkingUserInfo);
      break;
      i = 1;
    }
  }

  private final void actualShareGroup()
  {
    if (this.mTalkingGroupInfo == null)
      return;
    String str = String.format(Locale.CHINESE, CONTENTS[1], new Object[] { this.mTalkingGroupInfo.groupName });
    QTMSGManage.getInstance().sendStatistcsMessage("im_share");
    ShareUtil.shareToPlatform(str, 1, 2);
  }

  private void addHistoryMessages(List<IMMessage> paramList)
  {
    String str = InfoManager.getInstance().getUserProfile().getUserKey();
    int k;
    int n;
    int j;
    long l1;
    int m;
    label42: IMMessage localIMMessage;
    boolean bool2;
    int i;
    if (this.mDatas.size() == 0)
    {
      k = 1;
      n = paramList.size();
      j = 0;
      l1 = 0L;
      m = 0;
      if (m >= n)
        break label270;
      localIMMessage = (IMMessage)paramList.get(m);
      if (m == 0)
      {
        this.mOldestMsgSeq = localIMMessage.msgSeq;
        l2 = localIMMessage.publish;
        if ((k == 0) && (l2 > this.mOldestTimeStamp - 1800L))
          this.mDatas.remove(0);
        this.mOldestTimeStamp = l2;
      }
      boolean bool1 = TextUtils.equals(str, localIMMessage.mFromID);
      bool2 = isAdmin(localIMMessage.mFromID);
      if (!bool1)
        break label251;
      if (!bool2)
        break label245;
      i = 4;
      label149: long l2 = localIMMessage.publish;
      if (l2 - l1 < 1800L)
        break label333;
      this.mDatas.add(m + j, new ChatItem(32, getTimestampBySecond(l2)));
      j += 1;
      l1 = l2;
    }
    label270: label333: 
    while (true)
    {
      this.mDatas.add(m + j, new ChatItem(i, localIMMessage));
      m += 1;
      break label42;
      k = 0;
      break;
      label245: i = 1;
      break label149;
      label251: if (bool2)
      {
        i = 20;
        break label149;
      }
      i = 17;
      break label149;
      this.mAdapter.notifyDataSetChanged();
      this.mListView.onRefreshComplete();
      if (k != 0)
      {
        if (this.mLastTimestamp < l1)
          this.mLastTimestamp = l1;
        this.mListView.setSelection(this.mAdapter.getCount());
        return;
      }
      this.mListView.setSelection(n + j);
      return;
    }
  }

  private void changeChatMode()
  {
    switch (ChatMode.getCurrentMode())
    {
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      this.mInputView.update("changeMode", null);
      return;
      this.mCheckinButton.setVisibility(8);
      this.mJoinView.setVisibility(8);
      continue;
      this.mCheckinButton.setVisibility(0);
      this.mJoinView.setVisibility(8);
    }
  }

  private void checkIn()
  {
    if (!CloudCenter.getInstance().isLogin(true))
    {
      CloudCenter.OnLoginEventListerner local9 = new CloudCenter.OnLoginEventListerner()
      {
        public void onLoginFailed(int paramAnonymousInt)
        {
        }

        public void onLoginSuccessed(int paramAnonymousInt)
        {
          ImChatMainView.this.actualCheckin();
        }
      };
      EventDispacthManager.getInstance().dispatchAction("showLogin", local9);
      return;
    }
    actualCheckin();
  }

  private void flowerToUser(final UserInfo paramUserInfo)
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
          ImChatMainView.this.actualFlowerToUser(paramUserInfo);
          ImChatMainView.this.mJoinView.setVisibility(8);
        }
      };
      EventDispacthManager.getInstance().dispatchAction("showLogin", paramUserInfo);
      return;
    }
    actualFlowerToUser(paramUserInfo);
  }

  private int getActionsOffset()
  {
    if (this.mViewState == 3)
      return this.mActionsView.getMeasuredHeight();
    return 0;
  }

  private int getExpressionOffset()
  {
    if (this.mViewState == 1)
      return this.mExpressionView.getMeasuredHeight();
    return 0;
  }

  private int getInputOffset()
  {
    if (this.mIsInputShowing)
      return 0;
    switch (this.mViewState)
    {
    default:
      return 0;
    case 1:
      return this.mExpressionView.getMeasuredHeight();
    case 3:
      return this.mActionsView.getMeasuredHeight();
    case 2:
    }
    return this.mInviteView.getMeasuredHeight();
  }

  private int getInviteOffset()
  {
    if (this.mViewState == 2)
      return this.mInviteView.getMeasuredHeight();
    return 0;
  }

  private String getTimeInDay(int paramInt1, int paramInt2)
  {
    String str;
    if (paramInt1 < 6)
      str = "凌晨";
    while (true)
    {
      return String.format(Locale.CHINESE, "%s%02d:%02d", new Object[] { str, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
      if (paramInt1 < 12)
        str = "早上";
      else if (paramInt1 < 13)
        str = "中午";
      else if (paramInt1 < 18)
        str = "下午";
      else
        str = "晚上";
    }
  }

  private String getTimestampBySecond(long paramLong)
  {
    int i = 11;
    this.mCalendar.setTimeInMillis(1000L * paramLong);
    int j = this.mCalendar.get(6);
    int k = this.mCalendar.get(11);
    int m = this.mCalendar.get(12);
    if (j == this.mDay)
      return getTimeInDay(k, m);
    if (j == this.mDay - 1)
      return "昨天 " + getTimeInDay(k, m);
    int n = this.mCalendar.get(2);
    j = this.mCalendar.get(5);
    if (j == 12);
    while (true)
    {
      return String.format(Locale.CHINESE, "%d月%d日 %s", new Object[] { Integer.valueOf(n + 1), Integer.valueOf(i), getTimeInDay(k, m) });
      i = j;
    }
  }

  private void invalidateAvatar(String paramString)
  {
    int j = this.mListView.getListChildCnt();
    int i = 0;
    while (i < j)
    {
      View localView = this.mListView.getListChildAt(i);
      if ((localView != null) && ((localView instanceof IView)))
        ((IView)localView).update("invalidateAvatar", paramString);
      i += 1;
    }
  }

  private void invite(int paramInt)
  {
    QTMSGManage.getInstance().sendStatistcsMessage("im_invite", String.valueOf(paramInt));
    ShareUtil.inviteByPlatformIm(getContext(), paramInt, CONTENTS[3]);
  }

  private boolean isAdmin(String paramString)
  {
    return this.mAdmins.contains(paramString);
  }

  private boolean isInputShowingApi19()
  {
    return (API19) && (this.mOutRect.height() < this.mMaxHeight);
  }

  private void layoutViews(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt3 = this.mHeadView.getMeasuredHeight();
    paramInt4 = ScreenConfiguration.getNaviHeight() / 2;
    if (isInputShowingApi19())
      paramInt1 = this.mOutRect.height();
    int i;
    while (true)
    {
      i = this.mInputView.getMeasuredHeight();
      if (API19)
      {
        paramInt2 = this.mOutRect.top;
        if (!this.mIsInputShowing);
      }
      else
      {
        try
        {
          this.mListContainer.layout(0, paramInt3, this.standardLayout.width, paramInt2 + paramInt1 - i);
          this.mHeadView.layout(0, 0, this.standardLayout.width, paramInt3);
          this.mInputView.layout(0, paramInt2 + paramInt1 - i, this.standardLayout.width, paramInt2 + paramInt1);
          this.mJoinView.layout(0, paramInt2 + paramInt1 - i, this.standardLayout.width, paramInt2 + paramInt1);
          this.mExpressionView.layout(0, paramInt2 + paramInt1, this.standardLayout.width, paramInt2 + paramInt1 + this.chatMemberLayout.height);
          this.mActionsView.layout(0, paramInt2 + paramInt1, this.standardLayout.width, paramInt2 + paramInt1 + this.mActionsView.getMeasuredHeight());
          this.mInviteView.layout(0, paramInt2 + paramInt1, this.standardLayout.width, paramInt1 + paramInt2 + this.mInviteView.getMeasuredHeight());
          this.mCheckinButton.layout(this.checkinLayout.leftMargin, paramInt3 + paramInt4, this.checkinLayout.getRight(), paramInt3 + paramInt4 + this.checkinLayout.height);
          return;
          paramInt1 = getMeasuredHeight();
          continue;
          paramInt2 = 0;
        }
        catch (IllegalStateException localIllegalStateException1)
        {
          while (true)
            localIllegalStateException1.printStackTrace();
        }
      }
    }
    paramInt2 = getInputOffset();
    try
    {
      this.mListContainer.layout(0, paramInt3, this.standardLayout.width, paramInt1 - i - paramInt2);
      this.mHeadView.layout(0, 0, this.standardLayout.width, paramInt3);
      this.mInputView.layout(0, paramInt1 - i - paramInt2, this.standardLayout.width, paramInt1 - paramInt2);
      this.mJoinView.layout(0, paramInt1 - i - paramInt2, this.standardLayout.width, paramInt1 - paramInt2);
      paramInt2 = getExpressionOffset();
      this.mExpressionView.layout(0, paramInt1 - paramInt2, this.standardLayout.width, this.mExpressionView.getMeasuredHeight() + paramInt1 - paramInt2);
      paramInt2 = getActionsOffset();
      this.mActionsView.layout(0, paramInt1 - paramInt2, this.standardLayout.width, this.mActionsView.getMeasuredHeight() + paramInt1 - paramInt2);
      paramInt2 = getInviteOffset();
      this.mInviteView.layout(0, paramInt1 - paramInt2, this.standardLayout.width, paramInt1 + this.mInviteView.getMeasuredHeight() - paramInt2);
      this.mCheckinButton.layout(this.checkinLayout.leftMargin, paramInt3 + paramInt4, this.checkinLayout.getRight(), paramInt3 + paramInt4 + this.checkinLayout.height);
      return;
    }
    catch (IllegalStateException localIllegalStateException2)
    {
      while (true)
        localIllegalStateException2.printStackTrace();
    }
  }

  private void loadMoreHistory()
  {
    dispatchActionEvent("loadMore", Long.valueOf(this.mOldestMsgSeq));
  }

  private void resetBaseTime()
  {
    if (this.mCalendar == null)
      this.mCalendar = Calendar.getInstance();
    this.mCalendar.setTimeInMillis(System.currentTimeMillis());
    this.mDay = this.mCalendar.get(6);
  }

  private void sendMessage(String paramString, int paramInt)
  {
    if ((ChatMode.isGroup()) && (!IMContacts.getInstance().hasWatchedGroup(this.mTalkingId)))
      InfoManager.getInstance().getUserProfile().followGroup(this.mTalkingId);
    actualSendMessage(paramString, paramInt);
  }

  private void shareGroup()
  {
    if (!CloudCenter.getInstance().isLogin(false))
    {
      CloudCenter.OnLoginEventListerner local8 = new CloudCenter.OnLoginEventListerner()
      {
        public void onLoginFailed(int paramAnonymousInt)
        {
        }

        public void onLoginSuccessed(int paramAnonymousInt)
        {
          ImChatMainView.this.actualShareGroup();
        }
      };
      EventDispacthManager.getInstance().dispatchAction("showLogin", local8);
      return;
    }
    actualShareGroup();
  }

  public void close(boolean paramBoolean)
  {
    this.mInputView.close(paramBoolean);
    this.mHeadView.close(paramBoolean);
    this.mActionsView.close(paramBoolean);
    this.mInviteView.close(paramBoolean);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    BaseUserInfoPool.removeListener(this);
    super.close(paramBoolean);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((!this.mIsInputShowing) && (this.mViewState == 1))
    {
      if ((paramKeyEvent.getKeyCode() == 4) && (paramKeyEvent.getAction() == 1))
      {
        this.mViewState = 0;
        requestLayout();
        return true;
      }
    }
    else if ((!this.mIsInputShowing) && (this.mViewState == 2))
    {
      if ((paramKeyEvent.getKeyCode() == 4) && (paramKeyEvent.getAction() == 1))
      {
        this.mViewState = 0;
        requestLayout();
        return true;
      }
    }
    else if ((this.mIsInputShowing) && (paramKeyEvent.getKeyCode() == 4) && (paramKeyEvent.getAction() == 1))
    {
      InputMethodUtil.hideSoftInput(this.mInputView);
      return true;
    }
    return false;
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("keyboardState"))
      return Boolean.valueOf(this.mIsInputShowing);
    return super.getValue(paramString, paramObject);
  }

  public void onBaseInfoPut(String paramString, BaseUserInfoPool.AvatarAndGender paramAvatarAndGender)
  {
    if (paramString == null)
      return;
    invalidateAvatar(paramString);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("sendDiscuss"))
      sendMessage((String)paramObject2, 0);
    do
    {
      return;
      if (paramString.equalsIgnoreCase("expression"))
      {
        if (this.mIsInputShowing)
        {
          this.mViewState = 1;
          InputMethodUtil.hideSoftInput(this.mInputView);
          return;
        }
        if (this.mViewState == 1)
        {
          this.mViewState = 0;
          requestLayout();
          return;
        }
        if (!CloudCenter.getInstance().isLogin(false))
        {
          paramObject1 = new CloudCenter.OnLoginEventListerner()
          {
            public void onLoginFailed(int paramAnonymousInt)
            {
            }

            public void onLoginSuccessed(int paramAnonymousInt)
            {
              QTMSGManage.getInstance().sendStatistcsMessage("chat_functionclick", "expression");
              ImChatMainView.access$702(ImChatMainView.this, 1);
              InputMethodUtil.hideSoftInput(ImChatMainView.this.mInputView);
              ImChatMainView.this.requestLayout();
            }
          };
          EventDispacthManager.getInstance().dispatchAction("showLogin", paramObject1);
          return;
        }
        QTMSGManage.getInstance().sendStatistcsMessage("chat_functionclick", "expression");
        this.mViewState = 1;
        InputMethodUtil.hideSoftInput(this.mInputView);
        requestLayout();
        return;
      }
      if (paramString.equalsIgnoreCase("expand"))
      {
        if (this.mIsInputShowing)
        {
          this.mViewState = 3;
          InputMethodUtil.hideSoftInput(this.mInputView);
          return;
        }
        if (this.mViewState == 3)
        {
          this.mViewState = 0;
          requestLayout();
          return;
        }
        this.mViewState = 3;
        requestLayout();
        return;
      }
      if (paramString.equalsIgnoreCase("inviteFriends"))
      {
        if (this.mIsInputShowing)
        {
          this.mViewState = 2;
          InputMethodUtil.hideSoftInput(this.mInputView);
          return;
        }
        if (this.mViewState != 2)
        {
          this.mViewState = 2;
          requestLayout();
          return;
        }
        this.mViewState = 0;
        requestLayout();
        return;
      }
      if (paramString.equalsIgnoreCase("selectExpression"))
      {
        this.mInputView.update("addExpression", paramObject2);
        return;
      }
      if (paramString.equalsIgnoreCase("deleteExpression"))
      {
        this.mInputView.update(paramString, paramObject2);
        return;
      }
      if (paramString.equalsIgnoreCase("scrollToFirstIndexAtMe"))
      {
        dispatchActionEvent(paramString, paramObject2);
        return;
      }
      if (paramString.equalsIgnoreCase("useraction"))
      {
        if (!CloudCenter.getInstance().isLogin(false))
        {
          paramObject1 = new CloudCenter.OnLoginEventListerner()
          {
            public void onLoginFailed(int paramAnonymousInt)
            {
            }

            public void onLoginSuccessed(int paramAnonymousInt)
            {
              InfoManager.getInstance().getUserProfile().followGroup(ImChatMainView.this.mTalkingId);
              ImChatMainView.this.mJoinView.setVisibility(8);
            }
          };
          EventDispacthManager.getInstance().dispatchAction("showLogin", paramObject1);
          return;
        }
        InfoManager.getInstance().getUserProfile().followGroup(this.mTalkingId);
        this.mJoinView.setVisibility(8);
        return;
      }
      if (paramString.equalsIgnoreCase("flowerToAdmin"))
      {
        flowerToUser((UserInfo)paramObject2);
        return;
      }
      if (!paramString.equalsIgnoreCase("chatActionType"))
        break;
      paramObject1 = (ChatActionsView.ChatActionType)paramObject2;
      if (paramObject1 == ChatActionsView.ChatActionType.SHARE)
      {
        shareGroup();
        return;
      }
      if (paramObject1 == ChatActionsView.ChatActionType.ASKNAME)
      {
        sendMessage("现在播的什么歌？", 0);
        return;
      }
    }
    while ((paramObject1 == ChatActionsView.ChatActionType.ANSWERNAME) || (paramObject1 != ChatActionsView.ChatActionType.COLLECTION));
    return;
    if (paramString.equalsIgnoreCase("shareToPlatform"))
    {
      invite(((Integer)paramObject2).intValue());
      return;
    }
    dispatchActionEvent(paramString, paramObject2);
  }

  public void onInfoUpdated(int paramInt)
  {
    Object localObject;
    if (paramInt == 3)
      if (this.mMessage != null)
      {
        localObject = UserProfileManager.getInstance().getUserProfile(this.mTalkingId);
        if (localObject != null)
          this.mTalkingUserInfo = ((UserProfile)localObject).getUserInfo();
      }
    do
    {
      do
        return;
      while ((paramInt != 6) || (!ChatMode.isGroup()) || ((this.mAdmins != null) && (this.mAdmins.size() != 0)));
      localObject = IMAgent.getInstance().getGroupInfo(this.mTalkingId);
    }
    while (localObject == null);
    if ((((GroupInfo)localObject).lstAdmins != null) && (((GroupInfo)localObject).lstAdmins.size() > 0))
    {
      paramInt = 0;
      while (paramInt < ((GroupInfo)localObject).lstAdmins.size())
      {
        this.mAdmins.add(((UserInfo)((GroupInfo)localObject).lstAdmins.get(paramInt)).userKey);
        paramInt += 1;
      }
    }
    this.mHeadView.update("setData", localObject);
    this.mTalkingUserInfo = null;
    this.mTalkingGroupInfo = ((GroupInfo)localObject);
    this.mTalkingBlocked = false;
    this.mMessage = null;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    layoutViews(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mListView.setTranscriptMode(1);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.bottomLayout.scaleToBounds(this.standardLayout);
    this.checkinLayout.scaleToBounds(this.standardLayout);
    this.headerLayout.scaleToBounds(this.standardLayout);
    this.headerLayout.measureView(this.mHeadView);
    this.bottomLayout.measureView(this.mInputView);
    this.bottomLayout.measureView(this.mJoinView);
    if (isInputShowingApi19())
    {
      paramInt1 = this.mOutRect.height();
      if (paramInt1 > this.normalLayoutHeight)
      {
        this.specialLayoutHeight = paramInt1;
        if (this.normalLayoutHeight == 0)
          this.normalLayoutHeight = paramInt1;
      }
      if (paramInt1 >= this.normalLayoutHeight)
        break label298;
    }
    label298: for (this.mIsInputShowing = true; ; this.mIsInputShowing = false)
    {
      this.chatMemberLayout.scaleToBounds(this.standardLayout);
      this.chatMemberLayout.measureView(this.mExpressionView);
      this.chatMemberLayout.measureView(this.mActionsView);
      this.chatMemberLayout.measureView(this.mInviteView);
      paramInt2 = getInputOffset();
      this.mListContainer.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(paramInt1 - this.mHeadView.getMeasuredHeight() - this.mInputView.getMeasuredHeight() - paramInt2, 1073741824));
      this.checkinLayout.measureView(this.mCheckinButton);
      this.mCheckinButton.setPadding((int)(this.checkinLayout.width * 0.3F), 0, 0, 0);
      this.mCheckinButton.setTextSize(0, SkinManager.getInstance().getSubTextSize());
      setMeasuredDimension(this.standardLayout.width, paramInt1);
      return;
      paramInt1 = this.standardLayout.height;
      break;
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.setTranscriptMode(2);
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setActivate(boolean paramBoolean)
  {
    if (paramBoolean)
      super.setActivate(paramBoolean);
  }

  public void update(String paramString, Object paramObject)
  {
    int i;
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mAdmins.clear();
      if ((paramObject instanceof GroupInfo))
      {
        paramString = (GroupInfo)paramObject;
        this.mTalkingId = paramString.groupId;
        ChatMode.setMode(1);
        if ((paramString.lstAdmins != null) && (paramString.lstAdmins.size() > 0))
          i = 0;
        while (i < paramString.lstAdmins.size())
        {
          this.mAdmins.add(((UserInfo)paramString.lstAdmins.get(i)).userKey);
          i += 1;
          continue;
          paramObject = IMAgent.getInstance().getGroupInfo(this.mTalkingId);
          if (paramObject != null)
            break label251;
          IMAgent.getInstance().loadGroupInfo(this.mTalkingId, this);
        }
        this.mTalkingUserInfo = null;
        this.mTalkingGroupInfo = paramString;
        this.mTalkingBlocked = false;
        this.mMessage = null;
        QTMSGManage.getInstance().sendStatistcsMessage("groupChat", "enter");
        paramObject = QTLogger.getInstance().buildEnterIMLog(7);
        if (paramObject != null)
          LogModule.getInstance().send("IMUI", paramObject);
        this.mHeadView.update("setData", paramString);
        label198: this.mDatas.clear();
        this.mLastTimestamp = 0L;
        this.mLoading = true;
        this.mDatas.add(new ChatItem(32, "正在加载聊天消息"));
        this.mAdapter.notifyDataSetChanged();
        changeChatMode();
      }
    }
    label251: 
    do
    {
      return;
      paramString.update(paramObject);
      if ((paramString.lstAdmins == null) || (paramString.lstAdmins.size() <= 0))
        break;
      i = 0;
      while (i < paramString.lstAdmins.size())
      {
        this.mAdmins.add(((UserInfo)paramString.lstAdmins.get(i)).userKey);
        i += 1;
      }
      break;
      if ((paramObject instanceof UserInfo))
      {
        paramString = (UserInfo)paramObject;
        this.mTalkingId = paramString.userKey;
        ChatMode.setMode(0);
        this.mTalkingUserInfo = paramString;
        this.mTalkingGroupInfo = null;
        this.mTalkingBlocked = paramString.isBlocked;
        this.mMessage = null;
        QTMSGManage.getInstance().sendStatistcsMessage("userChat", "enter");
        paramObject = QTLogger.getInstance().buildEnterIMLog(8);
        if (paramObject != null)
          LogModule.getInstance().send("IMUI", paramObject);
        this.mHeadView.update("setData", paramString);
        break label198;
      }
      if ((paramObject instanceof IMMessage))
      {
        paramString = (IMMessage)paramObject;
        this.mTalkingId = paramString.mFromID;
        ChatMode.setMode(0);
        paramObject = UserProfileManager.getInstance().getUserProfile(this.mTalkingId);
        if (paramObject == null)
          UserProfileManager.getInstance().loadUserInfo(this.mTalkingId, this);
        while (true)
        {
          this.mTalkingGroupInfo = null;
          this.mMessage = paramString;
          QTMSGManage.getInstance().sendStatistcsMessage("userChat", "enter");
          paramObject = QTLogger.getInstance().buildEnterIMLog(8);
          if (paramObject != null)
            LogModule.getInstance().send("IMUI", paramObject);
          this.mHeadView.update("setData", paramString);
          break;
          this.mTalkingUserInfo = paramObject.getUserInfo();
        }
      }
      if (!(paramObject instanceof String))
        break label198;
      this.mTalkingId = ((String)paramObject);
      ChatMode.setMode(1);
      paramString = IMAgent.getInstance().getGroupInfo(this.mTalkingId);
      if (paramString == null)
        IMAgent.getInstance().loadGroupInfo(this.mTalkingId, this);
      while (true)
      {
        this.mTalkingUserInfo = null;
        this.mTalkingGroupInfo = paramString;
        this.mTalkingBlocked = false;
        this.mMessage = null;
        QTMSGManage.getInstance().sendStatistcsMessage("groupChat", "enter");
        paramObject = QTLogger.getInstance().buildEnterIMLog(7);
        if (paramObject != null)
          LogModule.getInstance().send("IMUI", paramObject);
        this.mHeadView.update("setData", paramString);
        break;
        if ((paramString.lstAdmins != null) && (paramString.lstAdmins.size() > 0))
        {
          i = 0;
          while (i < paramString.lstAdmins.size())
          {
            this.mAdmins.add(((UserInfo)paramString.lstAdmins.get(i)).userKey);
            i += 1;
          }
        }
      }
      if (paramString.equalsIgnoreCase("addmessage"))
      {
        if (this.mLoading)
        {
          this.mDatas.clear();
          this.mLoading = false;
        }
        paramString = (IMMessage)paramObject;
        long l = paramString.publish;
        if (l - this.mLastTimestamp >= 1800L)
        {
          this.mDatas.add(new ChatItem(32, getTimestampBySecond(l)));
          this.mLastTimestamp = l;
        }
        paramObject = this.mDatas;
        if (isAdmin(paramString.mFromID));
        for (i = 20; ; i = 17)
        {
          paramObject.add(new ChatItem(i, paramString));
          this.mAdapter.notifyDataSetChanged();
          return;
        }
      }
      if (paramString.equalsIgnoreCase("addhistory"))
      {
        if (paramObject == null)
        {
          if (this.mLoading)
          {
            this.mDatas.clear();
            this.mLoading = false;
            this.mOldestMsgSeq = 0L;
            this.mOldestTimeStamp = 0L;
            this.mAdapter.notifyDataSetChanged();
          }
          this.mAdapter.notifyDataSetChanged();
          this.mListView.onRefreshComplete();
          return;
        }
        if (this.mLoading)
        {
          this.mDatas.clear();
          this.mLoading = false;
        }
        addHistoryMessages((List)paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("closeKeyboard"))
      {
        InputMethodUtil.hideSoftInput(this.mInputView);
        return;
      }
      if (paramString.equalsIgnoreCase("needAccount"))
      {
        onEvent(this, paramString, paramObject);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("atTa"));
    if (this.mJoinView.getVisibility() == 0)
    {
      Toast.makeText(getContext(), "请先加入群聊", 0).show();
      return;
    }
    this.mJoinView.setVisibility(8);
    this.mInputView.update(paramString, paramObject);
  }

  private class GetDataTask extends AsyncTask<Void, Void, String[]>
  {
    private GetDataTask()
    {
    }

    protected String[] doInBackground(Void[] paramArrayOfVoid)
    {
      try
      {
        Thread.sleep(300L);
        label6: return null;
      }
      catch (InterruptedException paramArrayOfVoid)
      {
        break label6;
      }
    }

    protected void onPostExecute(String[] paramArrayOfString)
    {
      ImChatMainView.this.loadMoreHistory();
      super.onPostExecute(paramArrayOfString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.chat.ImChatMainView
 * JD-Core Version:    0.6.2
 */