package fm.qingting.qtradio.controller.im;

import android.content.Context;
import android.text.TextUtils;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.im.IMContacts;
import fm.qingting.qtradio.im.LatestMessages;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.view.im.ImConversationsView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ImConversationsController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView mBarTopView;
  private List<Object> mResultList;
  private ImConversationsView mainView;

  public ImConversationsController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "conversations";
    this.mainView = new ImConversationsView(paramContext);
    attachView(this.mainView);
    this.mBarTopView = new NavigationBarView(paramContext);
    this.mBarTopView.setTitleItem(new NavigationBarItem("消息"));
    this.mBarTopView.setLeftItem(0);
    setNavigationBar(this.mBarTopView);
    this.mBarTopView.setBarListener(this);
    LatestMessages.resetBaseTime();
  }

  private boolean needSort()
  {
    if ((this.mResultList == null) || (this.mResultList.size() == 0));
    while (true)
    {
      return false;
      Object localObject = this.mResultList.get(0);
      if ((localObject instanceof GroupInfo))
        localObject = ((GroupInfo)localObject).groupId;
      while (localObject != null)
      {
        if (!TextUtils.equals(LatestMessages.getLatestContactId(), (CharSequence)localObject));
        for (boolean bool = true; ; bool = false)
        {
          return bool;
          if (!(localObject instanceof UserInfo))
            break label88;
          localObject = ((UserInfo)localObject).userKey;
          break;
        }
        label88: localObject = null;
      }
    }
  }

  private void sortList()
  {
    Collections.sort(this.mResultList, new Comparator()
    {
      public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        if ((paramAnonymousObject1 instanceof GroupInfo))
        {
          paramAnonymousObject1 = ((GroupInfo)paramAnonymousObject1).groupId;
          if (!(paramAnonymousObject2 instanceof GroupInfo))
            break label57;
        }
        label57: for (paramAnonymousObject2 = ((GroupInfo)paramAnonymousObject2).groupId; ; paramAnonymousObject2 = ((UserInfo)paramAnonymousObject2).userKey)
        {
          paramAnonymousObject1 = LatestMessages.getMessage(paramAnonymousObject1);
          paramAnonymousObject2 = LatestMessages.getMessage(paramAnonymousObject2);
          if (paramAnonymousObject1 != null)
            break label68;
          return 1;
          paramAnonymousObject1 = ((UserInfo)paramAnonymousObject1).userKey;
          break;
        }
        label68: if (paramAnonymousObject2 == null)
          return -1;
        if (paramAnonymousObject1.publish > paramAnonymousObject2.publish)
          return -1;
        return 1;
      }
    });
  }

  public void config(String paramString, Object paramObject)
  {
    List localList;
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mResultList = new ArrayList();
      paramObject = IMContacts.getInstance().getRecentGroupContacts();
      localList = IMContacts.getInstance().getRecentUserContacts();
      if (paramObject != null)
        this.mResultList.addAll(paramObject);
      if (localList != null)
        this.mResultList.addAll(localList);
      sortList();
      this.mainView.update(paramString, this.mResultList);
      QTMSGManage.getInstance().sendStatistcsMessage("imconversation");
      paramString = QTLogger.getInstance().buildEnterIMLog(1);
      if (paramString != null)
        LogModule.getInstance().send("IMUI", paramString);
    }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("resetList"))
      {
        if (((Boolean)paramObject).booleanValue())
          sortList();
        this.mainView.update(paramString, null);
        return;
      }
      if (paramString.equalsIgnoreCase("resetData"))
      {
        this.mResultList.clear();
        paramObject = IMContacts.getInstance().getRecentGroupContacts();
        localList = IMContacts.getInstance().getRecentUserContacts();
        if (paramObject != null)
          this.mResultList.addAll(paramObject);
        if (localList != null)
          this.mResultList.addAll(localList);
        sortList();
        this.mainView.update(paramString, this.mResultList);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("sortListIfNeed"));
    if (needSort())
      sortList();
    this.mainView.update("resetList", null);
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public void controllerDidPushed()
  {
    IMAgent.getInstance().clearNotificationMsg();
    this.mainView.close(false);
    super.controllerDidPushed();
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.im.ImConversationsController
 * JD-Core Version:    0.6.2
 */