package fm.qingting.qtradio.view.personalcenter.feedback;

import android.content.Context;
import com.umeng.fb.model.DevReply;
import com.umeng.fb.model.Reply;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ListViewImpl;
import fm.qingting.qtradio.view.chatroom.chatlist.ChatItem;
import fm.qingting.qtradio.view.chatroom.chatlist.ChatroomTimestampView;
import fm.qingting.qtradio.view.chatroom.chatlist.IChatAdapterIViewFactory;
import fm.qingting.qtradio.view.im.chat.ImChatAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FeedbackListView extends ListViewImpl
  implements IEventHandler
{
  private static final String ANOUNCE = "【公告】客服在线时间：周一至周五9:30-11:30 13:30-18:00，如果您有紧急问题，请加蜻蜓官方QQ群：171440910，谢谢您的支持！";
  private final long TIME_INTERVAL = 1800000L;
  private ImChatAdapter adapter;
  private IChatAdapterIViewFactory factory;
  private Calendar mCalendar;
  private int mDay;
  private long mLastTimestamp = 0L;

  public FeedbackListView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-1118482);
    this.factory = new IChatAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default:
          return null;
        case 16:
          return new FeedbackItemLeftView(FeedbackListView.this.getContext(), this.val$hash);
        case 0:
          return new FeedbackItemRightView(FeedbackListView.this.getContext(), this.val$hash);
        case 32:
        }
        return new ChatroomTimestampView(FeedbackListView.this.getContext());
      }
    };
    this.adapter = new ImChatAdapter(new ArrayList(), this.factory);
    this.adapter.setEventHandler(this);
    try
    {
      setDivider(null);
      setVerticalFadingEdgeEnabled(false);
      setCacheColorHint(0);
      setHeaderDividersEnabled(false);
      setSelector(17170445);
      setAdapter(this.adapter);
      resetBaseTime();
      return;
    }
    catch (OutOfMemoryError paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
  }

  private List<ChatItem> addDefaultTip(List<Reply> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    this.mLastTimestamp = 0L;
    if (1376286870000L - this.mLastTimestamp >= 1800000L)
    {
      localArrayList.add(new ChatItem(32, getTimestamp(1376286870000L)));
      this.mLastTimestamp = 1376286870000L;
    }
    localArrayList.add(new ChatItem(20, "【公告】客服在线时间：周一至周五9:30-11:30 13:30-18:00，如果您有紧急问题，请加蜻蜓官方QQ群：171440910，谢谢您的支持！"));
    if (paramList == null)
      return localArrayList;
    int i = 0;
    if (i < paramList.size())
    {
      Reply localReply = (Reply)paramList.get(i);
      long l = localReply.getDatetime().getTime();
      if (l - this.mLastTimestamp >= 1800000L)
      {
        localArrayList.add(new ChatItem(32, getTimestamp(l)));
        this.mLastTimestamp = l;
      }
      if ((localReply instanceof DevReply))
        localArrayList.add(new ChatItem(17, localReply));
      while (true)
      {
        i += 1;
        break;
        localArrayList.add(new ChatItem(1, localReply));
      }
    }
    return localArrayList;
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

  private String getTimestamp(long paramLong)
  {
    this.mCalendar.setTimeInMillis(paramLong);
    int k = this.mCalendar.get(6);
    int i = this.mCalendar.get(11);
    int j = this.mCalendar.get(12);
    if (k == this.mDay)
      return getTimeInDay(i, j);
    if (k == this.mDay - 1)
      return "昨天 " + getTimeInDay(i, j);
    k = this.mCalendar.get(2);
    int m = this.mCalendar.get(5);
    return String.format(Locale.CHINESE, "%d月%d日 %s", new Object[] { Integer.valueOf(k + 1), Integer.valueOf(m), getTimeInDay(i, j) });
  }

  private void resetBaseTime()
  {
    if (this.mCalendar == null)
      this.mCalendar = Calendar.getInstance();
    this.mCalendar.setTimeInMillis(System.currentTimeMillis());
    this.mDay = this.mCalendar.get(6);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      paramObject1 = (ItemParam)paramObject2;
      paramString = paramObject1.type;
      if (paramString.equalsIgnoreCase("copyPop"))
        dispatchActionEvent(paramString, paramObject1.param);
    }
    while (!paramString.equalsIgnoreCase("select"))
    {
      return;
      dispatchActionEvent("reply", paramObject1.param);
      return;
    }
    dispatchActionEvent(paramString, paramObject2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.adapter.setData(addDefaultTip((List)paramObject));
    while (!paramString.equalsIgnoreCase("refresh"))
      return;
    this.adapter.notifyDataSetChanged();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.feedback.FeedbackListView
 * JD-Core Version:    0.6.2
 */