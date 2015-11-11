package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import android.widget.Toast;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Conversation.SyncListener;
import com.umeng.fb.model.DevReply;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.UserInfo;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.framework.utils.MobileState;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.QTLocation;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.feedback.FeedbackView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedbackController extends ViewController
  implements INavigationBarListener
{
  private FeedbackAgent agent;
  private Conversation defaultConversation;
  private Conversation.SyncListener listener;
  private FeedbackView mView;

  public FeedbackController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "feedback";
    this.mView = new FeedbackView(paramContext);
    attachView(this.mView);
    paramContext = new NavigationBarView(paramContext);
    paramContext.setLeftItem(0);
    paramContext.setTitleItem(new NavigationBarItem("问题描述"));
    paramContext.setBarListener(this);
    setNavigationBar(paramContext);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.agent = new FeedbackAgent(getContext());
      this.defaultConversation = this.agent.getDefaultConversation();
      paramString = this.defaultConversation.getReplyList();
      this.mView.update("setData", paramString);
      this.listener = new Conversation.SyncListener()
      {
        public void onReceiveDevReply(List<DevReply> paramAnonymousList)
        {
        }

        public void onSendUserReply(List<Reply> paramAnonymousList)
        {
          FeedbackController.this.mView.update("setData", FeedbackController.this.defaultConversation.getReplyList());
        }
      };
      this.defaultConversation.sync(this.listener);
    }
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
    {
      this.mView.closeIm();
      ControllerManager.getInstance().popLastController();
    }
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    Object localObject = null;
    if (paramString.equalsIgnoreCase("sendDiscuss"))
    {
      paramString = "【" + SharedCfg.getInstance().getFeedbackCategory() + "】" + (String)paramObject2;
      paramObject2 = new GroupInfo();
      paramObject2.groupId = "24124";
      paramObject2.groupName = "Android技术群";
      paramObject1 = SharedCfg.getInstance().getFeedbackContactInfo();
      if ((paramObject1 == null) || (paramObject1.length() <= 0))
        break label383;
      paramObject1 = "安卓反馈:" + paramString + " 联系信息：" + paramObject1;
    }
    while (true)
    {
      IMAgent.getInstance().sendFeedbackMessage(paramObject1, paramObject2);
      Toast.makeText(getContext(), "反馈信息发送成功，感谢您对蜻蜓的支持！", 0).show();
      this.defaultConversation.addUserReply(paramString);
      try
      {
        paramObject2 = new HashMap();
        switch (MobileState.getNetWorkType(getContext()))
        {
        case 4:
        case 1:
        case 2:
        case 3:
        case 5:
        }
        while (true)
        {
          paramObject2.put("NetState", "offline");
          label207: paramObject1 = InfoManager.getInstance().root().getCurrentPlayingNode();
          if (paramObject1 != null)
          {
            if ((!paramObject1.nodeName.equalsIgnoreCase("program")) || (paramObject1.parent == null) || (!paramObject1.parent.nodeName.equalsIgnoreCase("channel")))
              break label466;
            paramString = ((ChannelNode)paramObject1.parent).title;
            paramObject1 = ((ProgramNode)paramObject1).title;
          }
          while (true)
          {
            if (paramString != null)
              paramObject2.put("Channel", paramString);
            if (paramObject1 != null)
              paramObject2.put("Program", paramObject1);
            paramObject1 = InfoManager.getInstance().getCurrentLocation();
            if ((paramObject1 != null) && (paramObject1.city != null))
              paramObject2.put("City", paramObject1.city);
            paramString = this.agent.getUserInfo();
            paramObject1 = paramString;
            if (paramString == null)
              paramObject1 = new UserInfo();
            paramObject1.setRemark(paramObject2);
            this.agent.setUserInfo(paramObject1);
            this.defaultConversation.sync(this.listener);
            return;
            label383: paramObject1 = "安卓反馈:" + paramString;
            break;
            paramObject2.put("NetState", "Wifi");
            break label207;
            paramObject2.put("NetState", "3G");
            break label207;
            paramObject2.put("NetState", "2G");
            break label207;
            paramObject2.put("NetState", "unknown");
            break label207;
            label466: paramObject1 = null;
            paramString = localObject;
          }
        }
      }
      catch (Exception paramObject1)
      {
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.FeedbackController
 * JD-Core Version:    0.6.2
 */