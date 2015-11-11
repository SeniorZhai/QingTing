package fm.qingting.qtradio.model;

import java.util.ArrayList;
import java.util.List;

public class CheckInInfoNode extends Node
{
  private List<CheckInNode> lstCheckIn = new ArrayList();

  public CheckInInfoNode()
  {
    this.nodeName = "checkininfo";
  }

  private int isExisted(String paramString)
  {
    int j;
    if (paramString == null)
    {
      j = -1;
      return j;
    }
    int i = 0;
    while (true)
    {
      if (i >= this.lstCheckIn.size())
        break label55;
      j = i;
      if (((CheckInNode)this.lstCheckIn.get(i)).name.equalsIgnoreCase(paramString))
        break;
      i += 1;
    }
    label55: return -1;
  }

  public void addCheckInNode(CheckInNode paramCheckInNode)
  {
    if (paramCheckInNode == null);
    while (isExisted(paramCheckInNode.name) != -1)
      return;
    this.lstCheckIn.add(paramCheckInNode);
  }

  public CheckInNode getCheckInNodeByName(String paramString)
  {
    if (paramString == null);
    int i;
    do
    {
      return null;
      i = isExisted(paramString);
    }
    while (i == -1);
    return (CheckInNode)this.lstCheckIn.get(i);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.CheckInInfoNode
 * JD-Core Version:    0.6.2
 */