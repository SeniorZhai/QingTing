package fm.qingting.qtradio.model;

import java.util.Comparator;

class DownloadProgramComparator
  implements Comparator<Node>
{
  public int compare(Node paramNode1, Node paramNode2)
  {
    int i = ((ProgramNode)paramNode1).downloadInfo.updateTime;
    int j = ((ProgramNode)paramNode2).downloadInfo.updateTime;
    if (i > j)
      return -1;
    if (i < j)
      return 1;
    return 0;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.DownloadProgramComparator
 * JD-Core Version:    0.6.2
 */