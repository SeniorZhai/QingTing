package fm.qingting.qtradio.view.playview_bb;

import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.manager.SkinManager;
import java.util.List;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.DanmakuFactory;

public class BBDanmakuParserNew extends BaseDanmakuParser
{
  private int index = 0;
  private BaseDanmaku item = null;
  private float mDispScaleX;
  private float mDispScaleY;

  private boolean isFemale(String paramString)
  {
    if (paramString == null);
    while (!paramString.equalsIgnoreCase("m"))
      return true;
    return false;
  }

  private Danmakus parseImmessages(List<IMMessage> paramList)
  {
    Danmakus localDanmakus = new Danmakus();
    if ((paramList == null) || (paramList.size() == 0))
      return localDanmakus;
    int i = 0;
    if (i < paramList.size())
    {
      Object localObject = (IMMessage)paramList.get(i);
      long l = ((IMMessage)localObject).publish;
      float f = SkinManager.getInstance().getNormalTextSize();
      this.item = DanmakuFactory.createDanmaku(1, this.mDisp);
      BaseDanmaku localBaseDanmaku;
      if (this.item != null)
      {
        this.item.time = (l * 1000L);
        this.item.textSize = f;
        this.item.textColor = -11908534;
        localBaseDanmaku = this.item;
        if (!isFemale(((IMMessage)localObject).mGender))
          break label218;
      }
      label218: for (int j = 2130837578; ; j = 2130837588)
      {
        localBaseDanmaku.drawableLeftResid = j;
        this.item.textShadowColor = 0;
        DanmakuFactory.fillText(this.item, ((IMMessage)localObject).mMessage);
        localObject = this.item;
        j = this.index;
        this.index = (j + 1);
        ((BaseDanmaku)localObject).index = j;
        this.item.setTimer(this.mTimer);
        localDanmakus.addItem(this.item);
        i += 1;
        break;
      }
    }
    return localDanmakus;
  }

  public void characters(String paramString)
  {
    if (this.item != null)
    {
      DanmakuFactory.fillText(this.item, paramString);
      paramString = this.item;
      int i = this.index;
      this.index = (i + 1);
      paramString.index = i;
    }
  }

  public Danmakus parse()
  {
    if (this.mDataSource != null)
      return parseImmessages(((IMMessageDataSource)this.mDataSource).data());
    return null;
  }

  public BaseDanmakuParser setDisplayer(IDisplayer paramIDisplayer)
  {
    super.setDisplayer(paramIDisplayer);
    this.mDispScaleX = (this.mDispWidth / 682.0F);
    this.mDispScaleY = (this.mDispHeight / 438.0F);
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview_bb.BBDanmakuParserNew
 * JD-Core Version:    0.6.2
 */