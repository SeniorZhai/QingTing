package fm.qingting.qtradio.model;

import fm.qingting.utils.LifeTime;
import java.util.ArrayList;

public class PersonalCenterNode extends Node
{
  public AlarmInfoNode alarmInfoNode = new AlarmInfoNode();
  public String mTitle = "个人中心";
  public CollectionNode myCollectionNode = new CollectionNode();
  public PlayHistoryInfoNode playHistoryNode = new PlayHistoryInfoNode();
  public ReserveInfoNode reserveNode = new ReserveInfoNode();

  public PersonalCenterNode()
  {
    this.nodeName = "personalcenter";
  }

  public void init()
  {
    this.myCollectionNode.init();
    if (!LifeTime.isFirstLaunchAfterInstall)
      this.playHistoryNode.init();
    this.reserveNode.init();
    this.alarmInfoNode.init();
    if ((this.alarmInfoNode != null) && (this.myCollectionNode != null) && (this.alarmInfoNode.mLstAlarms == null))
      this.alarmInfoNode.mLstAlarms = new ArrayList();
  }

  public boolean saveChildToDB()
  {
    saveFavToDB();
    saveOtherToDB();
    return true;
  }

  public void saveFavToDB()
  {
    this.myCollectionNode.WriteToDB();
  }

  public void saveOtherToDB()
  {
    this.alarmInfoNode.WriteToDB();
    this.reserveNode.WriteToDB();
    this.playHistoryNode.WriteToDB();
  }

  public void upateDB()
  {
    this.alarmInfoNode.WriteToDB();
    this.myCollectionNode.WriteToDB();
    this.reserveNode.WriteToDB();
    this.playHistoryNode.WriteToDB();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.PersonalCenterNode
 * JD-Core Version:    0.6.2
 */