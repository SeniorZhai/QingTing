package fm.qingting.qtradio.push;

import fm.qingting.qtradio.push.bean.PushBean;
import java.util.List;

public abstract interface IOwner
{
  public abstract void informComplete(List<PushBean> paramList);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.push.IOwner
 * JD-Core Version:    0.6.2
 */