package fm.qingting.qtradio.room;

public class InitState extends State
{
  public InitState()
  {
    this.type = 0;
  }

  public void execute(Action paramAction)
  {
    if (paramAction == null);
    do
    {
      return;
      if (paramAction.actionType == 2)
      {
        RoomDataCenter.getInstance().enterRoom(((JoinAction)paramAction).getRoomType(), ((JoinAction)paramAction).getRoomId());
        RoomManager.getInstance().getRoomByType(((JoinAction)paramAction).getRoomType()).getChat().join(((JoinAction)paramAction).getConnectUrl(), ((JoinAction)paramAction).getRoomId(), ((JoinAction)paramAction).getProgramTopic(), ((JoinAction)paramAction).getRecordCount());
        return;
      }
      if (paramAction.actionType == 9)
      {
        RoomManager.getInstance().getRoomByType(((GetHistoryAction)paramAction).getRoomType()).getChat().getHistory(((GetHistoryAction)paramAction).getConnectUrl(), ((GetHistoryAction)paramAction).getRoomId(), ((GetHistoryAction)paramAction).getRecordCount());
        return;
      }
      if (paramAction.actionType == 11)
      {
        RoomManager.getInstance().getRoomByType(((GetOnlineUsersAction)paramAction).getRoomType()).getChat().getOnlineFriends(((GetOnlineUsersAction)paramAction).getRoomId());
        return;
      }
      if (paramAction.actionType == 3)
      {
        RoomManager.getInstance().getRoomByType(((LoginAction)paramAction).getRoomType()).getChat().login(((LoginAction)paramAction).getUserInfo());
        return;
      }
      if (paramAction.actionType == 1)
      {
        RoomManager.getInstance().getRoomByType(((SendAction)paramAction).getRoomType()).getChat().send(((SendAction)paramAction).getData());
        return;
      }
      if (paramAction.actionType == 5)
      {
        RoomManager.getInstance().getRoomByType(((ExitAction)paramAction).getRoomType()).getChat().exit();
        return;
      }
      if (paramAction.actionType == 12)
      {
        RoomManager.getInstance().getRoomByType(((GetTopicAction)paramAction).getRoomType()).getChat().getTopic(((GetTopicAction)paramAction).getRoomId());
        return;
      }
      if (paramAction.actionType == 13)
      {
        RoomManager.getInstance().getRoomByType(((AskSongAction)paramAction).getRoomType()).getChat().askSongName(((AskSongAction)paramAction).getRoomId());
        return;
      }
      if (paramAction.actionType == 15)
      {
        RoomManager.getInstance().getRoomByType(((AskSongTogetherAction)paramAction).getRoomType()).getChat().askSongNameTogether(((AskSongTogetherAction)paramAction).getRoomId());
        return;
      }
      if (paramAction.actionType == 14)
      {
        RoomManager.getInstance().getRoomByType(((TellSongAction)paramAction).getRoomType()).getChat().tellSongName(((TellSongAction)paramAction).getRoomId(), ((TellSongAction)paramAction).getUser(), ((TellSongAction)paramAction).getMessage());
        return;
      }
      if (paramAction.actionType == 10)
      {
        RoomManager.getInstance().getRoomByType(((LeaveAction)paramAction).getRoomType()).getChat().leave();
        return;
      }
      if (paramAction.actionType == 6)
      {
        RoomManager.getInstance().getRoomByType(((CheckInAction)paramAction).getRoomType()).getChat().checkIn(((CheckInAction)paramAction).getPlatformType());
        return;
      }
      if (paramAction.actionType == 7)
      {
        RoomManager.getInstance().getRoomByType(((FlowerAction)paramAction).getRoomType()).getChat().flower(((FlowerAction)paramAction).getPlatformType(), ((FlowerAction)paramAction).getFlowerUser());
        return;
      }
    }
    while (paramAction.actionType != 8);
    RoomManager.getInstance().getRoomByType(((SpeakToAction)paramAction).getRoomType()).getChat().speakTo(((SpeakToAction)paramAction).getPlatformType(), ((SpeakToAction)paramAction).getUserInfo(), ((SpeakToAction)paramAction).getMessage());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.InitState
 * JD-Core Version:    0.6.2
 */