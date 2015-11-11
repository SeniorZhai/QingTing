package fm.qingting.qtradio.push.data;

import android.content.Context;
import fm.qingting.qtradio.model.DownLoadInfoNode;

public class PushCommonData
{
  private static PushCommonData _ins = null;
  public DownLoadInfoNode m_info = null;

  private PushCommonData(Context paramContext)
  {
    init();
  }

  public static PushCommonData getInstance(Context paramContext)
  {
    if (_ins == null)
      _ins = new PushCommonData(paramContext);
    return _ins;
  }

  private void init()
  {
    if (this.m_info == null)
    {
      this.m_info = new DownLoadInfoNode();
      this.m_info.init();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.push.data.PushCommonData
 * JD-Core Version:    0.6.2
 */