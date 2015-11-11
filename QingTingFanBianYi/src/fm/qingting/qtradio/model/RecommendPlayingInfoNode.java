package fm.qingting.qtradio.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecommendPlayingInfoNode extends Node
{
  private int MAX_ITEMS_FRONTPAGE = 3;
  private List<RecommendPlayingItemNode> mLstPlayingItemsForAll = new ArrayList();
  private List<RecommendPlayingItemNode> mLstPlayingItemsForRest = new ArrayList();
  private List<RecommendPlayingItemNode> mLstRecommendPlaying;
  private List<RecommendPlayingItemNode> mLstRecommendPlayingForFrontPage = new ArrayList();
  private int minEndTimeForAll;
  private int minEndTimeForFrontPage;
  private int minPlayingProgramIndex = 0;

  public RecommendPlayingInfoNode()
  {
    this.nodeName = "recommendplayinginfo";
  }

  private List<RecommendPlayingItemNode> getCurrPlayingForAll()
  {
    int m = 0;
    if ((this.mLstRecommendPlaying == null) || (this.mLstRecommendPlaying.size() == 0))
      return null;
    long l = getRelativeTime(System.currentTimeMillis());
    this.mLstPlayingItemsForAll.clear();
    this.mLstPlayingItemsForRest.clear();
    int k = this.minPlayingProgramIndex;
    int i = 0;
    if (k < this.mLstRecommendPlaying.size())
    {
      RecommendPlayingItemNode localRecommendPlayingItemNode = (RecommendPlayingItemNode)this.mLstRecommendPlaying.get(k);
      int j;
      if ((l >= localRecommendPlayingItemNode.startTime()) && (l < localRecommendPlayingItemNode.endTime()))
      {
        j = i;
        if (i == 0)
        {
          j = 1;
          this.minPlayingProgramIndex = k;
        }
        this.mLstPlayingItemsForAll.add(localRecommendPlayingItemNode);
      }
      while (true)
      {
        k += 1;
        i = j;
        break;
        j = i;
        if (l < localRecommendPlayingItemNode.startTime())
        {
          this.mLstPlayingItemsForRest.add(localRecommendPlayingItemNode);
          j = i;
        }
      }
    }
    this.minEndTimeForAll = 2147483647;
    if (this.mLstPlayingItemsForAll.size() > 0)
    {
      this.minEndTimeForAll = ((RecommendPlayingItemNode)this.mLstPlayingItemsForAll.get(0)).endTime();
      i = m;
      while (i < this.mLstPlayingItemsForAll.size())
      {
        if (((RecommendPlayingItemNode)this.mLstPlayingItemsForAll.get(i)).endTime() < this.minEndTimeForAll)
          this.minEndTimeForAll = ((RecommendPlayingItemNode)this.mLstPlayingItemsForAll.get(i)).endTime();
        i += 1;
      }
      return this.mLstPlayingItemsForAll;
    }
    return null;
  }

  private int getRelativeTime(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    int i = localCalendar.get(11);
    return localCalendar.get(12) * 60 + i * 60 * 60;
  }

  public boolean checkRecommendPlayingList(long paramLong)
  {
    if ((this.mLstRecommendPlaying == null) || (this.mLstRecommendPlaying.size() == 0))
      return false;
    paramLong = getRelativeTime(1000L * paramLong);
    if (this.minEndTimeForAll < paramLong)
      return true;
    if (this.minEndTimeForFrontPage < paramLong)
      return true;
    int i = 0;
    while (true)
    {
      if ((i >= this.mLstPlayingItemsForRest.size()) || (((RecommendPlayingItemNode)this.mLstPlayingItemsForRest.get(i)).startTime() <= paramLong))
      {
        if (i == this.mLstPlayingItemsForRest.size())
          break;
        getCurrPlayingForAll();
        getCurrPlayingFrontPageNodes();
        return true;
      }
      i += 1;
    }
  }

  public List<RecommendPlayingItemNode> getCurrPlayingForShow()
  {
    return getCurrPlayingForAll();
  }

  public List<RecommendPlayingItemNode> getCurrPlayingFrontPageNodes()
  {
    int m = 0;
    if ((this.mLstRecommendPlaying == null) || (this.mLstRecommendPlaying.size() == 0))
      return null;
    long l = getRelativeTime(System.currentTimeMillis());
    this.mLstRecommendPlayingForFrontPage.clear();
    int j = 0;
    int i;
    for (int k = 0; ; k = i)
    {
      if (j < this.mLstRecommendPlaying.size())
      {
        RecommendPlayingItemNode localRecommendPlayingItemNode = (RecommendPlayingItemNode)this.mLstRecommendPlaying.get(j);
        i = k;
        if (l > localRecommendPlayingItemNode.startTime())
        {
          i = k;
          if (l < localRecommendPlayingItemNode.endTime())
          {
            i = k;
            if (k < this.MAX_ITEMS_FRONTPAGE)
            {
              this.mLstRecommendPlayingForFrontPage.add(localRecommendPlayingItemNode);
              k += 1;
              i = k;
              if (k != this.MAX_ITEMS_FRONTPAGE);
            }
          }
        }
      }
      else
      {
        this.minEndTimeForFrontPage = 2147483647;
        if (this.mLstRecommendPlayingForFrontPage.size() <= 0)
          break label263;
        this.minEndTimeForFrontPage = ((RecommendPlayingItemNode)this.mLstRecommendPlayingForFrontPage.get(0)).endTime();
        i = m;
        while (i < this.mLstRecommendPlayingForFrontPage.size())
        {
          if (((RecommendPlayingItemNode)this.mLstRecommendPlayingForFrontPage.get(i)).endTime() < this.minEndTimeForFrontPage)
            this.minEndTimeForFrontPage = ((RecommendPlayingItemNode)this.mLstRecommendPlayingForFrontPage.get(i)).endTime();
          i += 1;
        }
      }
      j += 1;
    }
    return this.mLstRecommendPlayingForFrontPage;
    label263: return null;
  }

  public List<RecommendPlayingItemNode> getPlayingItemNodes(long paramLong)
  {
    if ((this.mLstRecommendPlaying == null) || (this.mLstRecommendPlaying.size() == 0))
      return null;
    ArrayList localArrayList = new ArrayList();
    paramLong = getRelativeTime(1000L * paramLong);
    int i = 0;
    while (i < this.mLstRecommendPlaying.size())
    {
      RecommendPlayingItemNode localRecommendPlayingItemNode = (RecommendPlayingItemNode)this.mLstRecommendPlaying.get(i);
      if ((paramLong > localRecommendPlayingItemNode.startTime()) && (paramLong < localRecommendPlayingItemNode.endTime()))
        localArrayList.add(localRecommendPlayingItemNode);
      i += 1;
    }
    if (localArrayList.size() == 0)
      return null;
    return localArrayList;
  }

  public List<RecommendPlayingItemNode> getRecommendPlayingItemNodes(long paramLong)
  {
    if ((this.mLstRecommendPlaying == null) || (this.mLstRecommendPlaying.size() == 0))
      return null;
    ArrayList localArrayList = new ArrayList();
    paramLong = getRelativeTime(1000L * paramLong);
    int i = 0;
    while (i < this.mLstRecommendPlaying.size())
    {
      RecommendPlayingItemNode localRecommendPlayingItemNode = (RecommendPlayingItemNode)this.mLstRecommendPlaying.get(i);
      if ((paramLong > localRecommendPlayingItemNode.startTime()) && (paramLong < localRecommendPlayingItemNode.endTime()))
        localArrayList.add(localRecommendPlayingItemNode);
      i += 1;
    }
    if (localArrayList.size() == 0)
      return null;
    return localArrayList;
  }

  public void setRecommendList(List<RecommendPlayingItemNode> paramList)
  {
    if (paramList.size() == 0)
      return;
    this.mLstPlayingItemsForAll.clear();
    this.mLstPlayingItemsForRest.clear();
    this.mLstRecommendPlayingForFrontPage.clear();
    this.minEndTimeForFrontPage = 0;
    this.minEndTimeForAll = 0;
    this.minPlayingProgramIndex = 0;
    this.mLstRecommendPlaying = paramList;
    try
    {
      Collections.sort(this.mLstRecommendPlaying, new RecommendPlayTimeComparator());
      return;
    }
    catch (Exception paramList)
    {
    }
  }

  public void updateDB()
  {
  }

  class RecommendPlayTimeComparator
    implements Comparator<Node>
  {
    RecommendPlayTimeComparator()
    {
    }

    public int compare(Node paramNode1, Node paramNode2)
    {
      if (((RecommendPlayingItemNode)paramNode1).startTime() < ((RecommendPlayingItemNode)paramNode2).startTime())
        return -1;
      if (((RecommendPlayingItemNode)paramNode1).startTime() > ((RecommendPlayingItemNode)paramNode2).startTime())
        return 1;
      if (((RecommendPlayingItemNode)paramNode1).endTime() < ((RecommendPlayingItemNode)paramNode2).endTime())
        return -1;
      if (((RecommendPlayingItemNode)paramNode1).endTime() > ((RecommendPlayingItemNode)paramNode2).endTime())
        return 1;
      return 0;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.RecommendPlayingInfoNode
 * JD-Core Version:    0.6.2
 */