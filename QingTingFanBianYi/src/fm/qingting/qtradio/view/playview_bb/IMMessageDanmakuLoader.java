package fm.qingting.qtradio.view.playview_bb;

import fm.qingting.qtradio.im.message.IMMessage;
import java.io.InputStream;
import java.util.List;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;

public class IMMessageDanmakuLoader
  implements ILoader
{
  private static IMMessageDanmakuLoader _instance;
  private IMMessageDataSource dataSource;

  public static IMMessageDanmakuLoader instance()
  {
    if (_instance == null)
      _instance = new IMMessageDanmakuLoader();
    return _instance;
  }

  public IMMessageDataSource getDataSource()
  {
    return this.dataSource;
  }

  public void load(InputStream paramInputStream)
    throws IllegalDataException
  {
  }

  public void load(String paramString)
    throws IllegalDataException
  {
  }

  public void load(List<IMMessage> paramList)
    throws IllegalDataException
  {
    try
    {
      this.dataSource = new IMMessageDataSource(paramList);
      return;
    }
    catch (Exception paramList)
    {
    }
    throw new IllegalDataException(paramList);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview_bb.IMMessageDanmakuLoader
 * JD-Core Version:    0.6.2
 */