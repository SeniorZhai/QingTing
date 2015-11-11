package fm.qingting.qtradio.model;

import java.util.ArrayList;
import java.util.List;

public class RingToneInfoNode extends Node
{
  private String mCurrAvailableRingId;
  private List<BroadcasterNode> mLstBroadcaster;
  public List<Node> mLstRingToneNodes;
  private int mRingCatId;
  private int mRingChannelId;
  private int mRingChannelType;
  private int mRingParentId;
  private int mRingProgramId;

  public RingToneInfoNode()
  {
    this.nodeName = "ringtoneinfo";
  }

  public void addToRingTone(Node paramNode)
  {
    if ((paramNode == null) || (!paramNode.nodeName.equalsIgnoreCase("ringtone")))
      return;
    int i;
    if (((RingToneNode)paramNode).ringToneId.equalsIgnoreCase("2"))
    {
      ((RingToneNode)paramNode).ringDesc = "女仆驾到哦";
      i = 0;
    }
    while (true)
    {
      if (i >= this.mLstRingToneNodes.size())
        break label190;
      if (((RingToneNode)this.mLstRingToneNodes.get(i)).ringToneId.equalsIgnoreCase(((RingToneNode)paramNode).ringToneId))
      {
        ((RingToneNode)this.mLstRingToneNodes.get(i)).updateRingTone((RingToneNode)paramNode);
        ((RingToneNode)this.mLstRingToneNodes.get(i)).downloadInfo = ((RingToneNode)paramNode).downloadInfo;
        return;
        if (((RingToneNode)paramNode).ringToneId.equalsIgnoreCase("1"))
        {
          ((RingToneNode)paramNode).ringDesc = "李季的起床派对";
          break;
        }
        if (!((RingToneNode)paramNode).ringToneId.equalsIgnoreCase("3"))
          break;
        ((RingToneNode)paramNode).ringDesc = "怪蜀黍叫你起床啦";
        break;
      }
      i += 1;
    }
    label190: this.mLstRingToneNodes.add(paramNode);
  }

  public String getAvailableRingId()
  {
    return this.mCurrAvailableRingId;
  }

  public BroadcasterNode getBroadcasterByRoneId(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return null;
    int i = 0;
    while (i < this.mLstBroadcaster.size())
    {
      if (paramString.equalsIgnoreCase(String.valueOf(((BroadcasterNode)this.mLstBroadcaster.get(i)).ringToneId)))
        return (BroadcasterNode)this.mLstBroadcaster.get(i);
      i += 1;
    }
    return null;
  }

  public int getRingCatId()
  {
    return this.mRingCatId;
  }

  public int getRingChannelId()
  {
    return this.mRingChannelId;
  }

  public int getRingChannelType()
  {
    return this.mRingChannelType;
  }

  public RingToneNode getRingNodeById(String paramString)
  {
    if (paramString == null)
      return null;
    int i = 0;
    while (i < this.mLstRingToneNodes.size())
    {
      if (((RingToneNode)this.mLstRingToneNodes.get(i)).ringToneId.equalsIgnoreCase(paramString))
        return (RingToneNode)this.mLstRingToneNodes.get(i);
      i += 1;
    }
    return null;
  }

  public int getRingParentId()
  {
    return this.mRingParentId;
  }

  public int getRingProgramId()
  {
    return this.mRingProgramId;
  }

  public RingToneNode getRingToneByUserId(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return null;
    if (this.mLstRingToneNodes == null)
      return null;
    int i = 0;
    while (i < this.mLstRingToneNodes.size())
    {
      if (((RingToneNode)this.mLstRingToneNodes.get(i)).belongToBroadcaster(paramString))
        return (RingToneNode)this.mLstRingToneNodes.get(i);
      i += 1;
    }
    return null;
  }

  public boolean hasRingTone(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    while (true)
    {
      return false;
      if (this.mLstRingToneNodes != null)
      {
        int i = 0;
        while (i < this.mLstRingToneNodes.size())
        {
          if (((RingToneNode)this.mLstRingToneNodes.get(i)).belongToBroadcaster(paramString))
            return true;
          i += 1;
        }
      }
    }
  }

  public void init()
  {
    if (this.mLstRingToneNodes == null)
      this.mLstRingToneNodes = new ArrayList();
    if (this.mLstBroadcaster == null)
    {
      this.mLstBroadcaster = new ArrayList();
      BroadcasterNode localBroadcasterNode = new BroadcasterNode();
      localBroadcasterNode.id = 3684;
      localBroadcasterNode.avatar = "http://tp1.sinaimg.cn/1812508720/180/22841453705/1";
      localBroadcasterNode.weiboName = "李季先生";
      localBroadcasterNode.weiboId = "1812508720";
      localBroadcasterNode.nick = "李季先生";
      localBroadcasterNode.ringToneId = 1;
      this.mLstBroadcaster.add(localBroadcasterNode);
      localBroadcasterNode = new BroadcasterNode();
      localBroadcasterNode.id = 2031;
      localBroadcasterNode.avatar = "http://tp1.sinaimg.cn/1743620792/180/40008201366/0";
      localBroadcasterNode.weiboName = "1058小昕";
      localBroadcasterNode.weiboId = "1743620792";
      localBroadcasterNode.nick = "1058小昕";
      localBroadcasterNode.ringToneId = 2;
      this.mLstBroadcaster.add(localBroadcasterNode);
      localBroadcasterNode = new BroadcasterNode();
      localBroadcasterNode.id = 2029;
      localBroadcasterNode.avatar = "http://tp2.sinaimg.cn/1747314141/180/40023309355/1";
      localBroadcasterNode.weiboName = "1058小K";
      localBroadcasterNode.weiboId = "1747314141";
      localBroadcasterNode.nick = "1058小K";
      localBroadcasterNode.ringToneId = 3;
      this.mLstBroadcaster.add(localBroadcasterNode);
      localBroadcasterNode = new BroadcasterNode();
      localBroadcasterNode.id = 1;
      localBroadcasterNode.avatar = "";
      localBroadcasterNode.weiboName = "蜻蜓FM主播";
      localBroadcasterNode.weiboId = "2673619603";
      localBroadcasterNode.nick = "蜻蜓FM主播";
      localBroadcasterNode.ringToneId = 0;
      this.mLstBroadcaster.add(localBroadcasterNode);
    }
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
    paramObject = (List)paramObject;
    if ((paramObject == null) || (paramString == null) || (paramObject.size() == 0));
    while (!paramString.equalsIgnoreCase("ARTNL"))
      return;
    setRingToneList(paramObject);
  }

  public void setAvaliableRingId(String paramString)
  {
    this.mCurrAvailableRingId = paramString;
  }

  public void setRingCatId(int paramInt)
  {
    this.mRingCatId = paramInt;
  }

  public void setRingChannelId(int paramInt)
  {
    this.mRingChannelId = paramInt;
  }

  public void setRingChannelType(int paramInt)
  {
    this.mRingChannelType = paramInt;
  }

  public void setRingParentId(int paramInt)
  {
    this.mRingParentId = paramInt;
  }

  public void setRingProgramId(int paramInt)
  {
    this.mRingProgramId = paramInt;
  }

  public void setRingToneList(List<Node> paramList)
  {
    if ((this.mLstRingToneNodes == null) || (this.mLstRingToneNodes.size() == 0))
    {
      this.mLstRingToneNodes = paramList;
      return;
    }
    int k = this.mLstRingToneNodes.size();
    int i = 0;
    if (i < paramList.size())
    {
      RingToneNode localRingToneNode1 = (RingToneNode)paramList.get(i);
      int j = 0;
      while (true)
      {
        if (j < k)
        {
          RingToneNode localRingToneNode2 = (RingToneNode)this.mLstRingToneNodes.get(j);
          if (localRingToneNode2.ringToneId.equalsIgnoreCase(localRingToneNode1.ringToneId))
            localRingToneNode1.updateRingTone(localRingToneNode2);
        }
        else
        {
          i += 1;
          break;
        }
        j += 1;
      }
    }
    this.mLstRingToneNodes = paramList;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.RingToneInfoNode
 * JD-Core Version:    0.6.2
 */