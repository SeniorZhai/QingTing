package fm.qingting.qtradio.room;

import java.util.Comparator;

class ChatDataComparator
  implements Comparator<CustomData>
{
  public int compare(CustomData paramCustomData1, CustomData paramCustomData2)
  {
    if ((paramCustomData1.type == 1) && (paramCustomData2.type == 1))
    {
      if (((ChatData)paramCustomData1).createTime < ((ChatData)paramCustomData2).createTime)
        return -1;
      if (((ChatData)paramCustomData1).createTime > ((ChatData)paramCustomData2).createTime)
        return 1;
      return 0;
    }
    return 0;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.ChatDataComparator
 * JD-Core Version:    0.6.2
 */