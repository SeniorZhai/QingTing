package fm.qingting.qtradio.fm;

import android.content.Context;
import android.media.MediaPlayer;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.view.audio.MunionAudio;
import com.taobao.newxp.view.audio.MunionAudio.OnAudioADClientCallBackListener;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.PlayMode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.utils.QTMSGManage;
import java.lang.reflect.Field;

public class TaobaoAgent
{
  private static TaobaoAgent instance;
  private String AD_IDENTITY = "62831";
  private boolean hasLoadedAudio = false;
  private long hasPlayAdv = 0L;
  private MunionAudio mAudio;
  private Context mContext;
  private MediaPlayer mPlayer;

  private void _playAD(boolean paramBoolean)
  {
    if (this.mAudio != null)
    {
      if (!paramBoolean)
        InfoManager.getInstance().setPlayAdvertisementTime();
      if (paramBoolean)
        mute();
      this.mAudio.playAD();
      QTMSGManage.getInstance().sendStatistcsMessage("taobao_ad", "play");
    }
  }

  private void change()
  {
    AlimmContext.getAliContext().init(this.mContext);
    Class localClass = AlimmContext.getAliContext().getAppUtils().getClass();
    while (true)
    {
      int i;
      Object localObject3;
      try
      {
        Field[] arrayOfField = localClass.getDeclaredFields();
        i = 0;
        if (i < arrayOfField.length)
        {
          Object localObject1;
          if (arrayOfField[i].getName().equalsIgnoreCase("l"))
          {
            localObject1 = localClass.getDeclaredField(arrayOfField[i].getName());
            ((Field)localObject1).setAccessible(true);
            localObject1 = String.valueOf(((Field)localObject1).get(AlimmContext.getAliContext().getAppUtils()));
            j = SharedCfg.getInstance().getTaoBaoChange();
            if (j == 0)
              return;
            j = SharedCfg.getInstance().getBootstrapCnt() % j;
            if (((String)localObject1).length() > j)
            {
              char c1 = ((String)localObject1).charAt(0);
              char c2 = ((String)localObject1).charAt(j);
              changeStr((String)localObject1, String.valueOf(c2) + String.valueOf(c1) + ((String)localObject1).substring(2));
            }
          }
          else if (arrayOfField[i].getName().equalsIgnoreCase("n"))
          {
            localObject1 = localClass.getDeclaredField(arrayOfField[i].getName());
            ((Field)localObject1).setAccessible(true);
            String str = String.valueOf(((Field)localObject1).get(AlimmContext.getAliContext().getAppUtils()));
            j = SharedCfg.getInstance().getTaoBaoChange();
            if (j != 0)
            {
              int k = SharedCfg.getInstance().getBootstrapCnt() % j;
              if (str.length() <= k)
                break label383;
              String[] arrayOfString = str.split(":");
              if (arrayOfString != null)
              {
                localObject1 = "";
                if (k >= arrayOfString.length)
                  break label392;
                localObject1 = arrayOfString[k];
                break label392;
                if (j < arrayOfString.length)
                {
                  localObject3 = localObject1;
                  if (j == k)
                    break label398;
                  localObject1 = (String)localObject1 + ":";
                  localObject3 = (String)localObject1 + arrayOfString[j];
                  break label398;
                }
                if ((localObject1 == null) || (((String)localObject1).equalsIgnoreCase("")))
                  break label383;
                changeStr(str, (String)localObject1);
              }
            }
          }
        }
      }
      catch (Exception localException)
      {
      }
      return;
      label383: i += 1;
      continue;
      label392: int j = 0;
      continue;
      label398: j += 1;
      Object localObject2 = localObject3;
    }
  }

  private void changeStr(String paramString1, String paramString2)
  {
    Object localObject = paramString1.getClass();
    try
    {
      Field localField = ((Class)localObject).getDeclaredField("value");
      localObject = ((Class)localObject).getDeclaredField("count");
      ((Field)localObject).setAccessible(true);
      localField.setAccessible(true);
      paramString2 = paramString2.toCharArray();
      ((Field)localObject).set(paramString1, Integer.valueOf(paramString2.length));
      localField.set(paramString1, paramString2);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }

  public static TaobaoAgent getInstance()
  {
    try
    {
      if (instance == null)
        instance = new TaobaoAgent();
      TaobaoAgent localTaobaoAgent = instance;
      return localTaobaoAgent;
    }
    finally
    {
    }
  }

  private void getPlayer()
  {
    Class localClass = this.mAudio.getClass();
    try
    {
      Field[] arrayOfField = localClass.getDeclaredFields();
      int i = 0;
      while (true)
      {
        if (i < arrayOfField.length)
        {
          Object localObject = localClass.getDeclaredField(arrayOfField[i].getName());
          ((Field)localObject).setAccessible(true);
          localObject = ((Field)localObject).get(this.mAudio);
          if ((localObject instanceof MediaPlayer))
            this.mPlayer = ((MediaPlayer)localObject);
        }
        else
        {
          return;
        }
        i += 1;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void log(String paramString)
  {
  }

  private void mute()
  {
    try
    {
      getPlayer();
      if (this.mPlayer != null)
        this.mPlayer.setVolume(0.0F, 0.0F);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void onHandlePlayFinished()
  {
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    InfoManager.getInstance().root().setPlayMode();
    PlayerAgent.getInstance().play(localNode, false);
    loadAd();
  }

  public boolean hasLoadedAdv()
  {
    return this.hasLoadedAudio;
  }

  public void init(Context paramContext, boolean paramBoolean)
  {
    this.mContext = paramContext;
    this.mAudio = new MunionAudio(paramContext);
    if (paramBoolean)
      change();
    this.mAudio.setOnAudioADClientCallBackListener(new MunionAudio.OnAudioADClientCallBackListener()
    {
      public void onDidPause()
      {
        TaobaoAgent.this.log("暂停广告播放");
      }

      public void onDidStart()
      {
        TaobaoAgent.this.log("开始广告播放");
        InfoManager.getInstance().root().setPlayMode(RootNode.PlayMode.PLAY_FRONT_ADVERTISEMENT);
      }

      public void onDidStop()
      {
        TaobaoAgent.this.log("停止广告播放");
        TaobaoAgent.this.onHandlePlayFinished();
      }

      public void onPlayDidFinished()
      {
        TaobaoAgent.this.log("播放广告完成");
        TaobaoAgent.this.onHandlePlayFinished();
        QTMSGManage.getInstance().sendStatistcsMessage("taobao_ad", "playFinished");
      }

      public void onPlayFailed(String paramAnonymousString)
      {
        TaobaoAgent.this.log("播放广告失败");
        TaobaoAgent.this.onHandlePlayFinished();
        QTMSGManage.getInstance().sendStatistcsMessage("taobao_ad", "playFailed");
      }

      public void onRequestFailed(String paramAnonymousString)
      {
        TaobaoAgent.this.log("广告请求失败");
        QTMSGManage.getInstance().sendStatistcsMessage("taobao_ad", "requestADFailed");
      }

      public void onRequestFinished()
      {
        TaobaoAgent.this.log("广告请求成功");
        TaobaoAgent.access$102(TaobaoAgent.this, true);
        QTMSGManage.getInstance().sendStatistcsMessage("taobao_ad", "requestADSucc");
      }
    });
  }

  public void loadAd()
  {
    if (this.mAudio != null)
    {
      this.hasLoadedAudio = false;
      this.mAudio.requestAD(this.AD_IDENTITY);
      QTMSGManage.getInstance().sendStatistcsMessage("taobao_ad", "load");
    }
  }

  public boolean playAD(boolean paramBoolean)
  {
    if ((paramBoolean) && (System.currentTimeMillis() / 1000L - this.hasPlayAdv < 300L))
      return false;
    if (this.hasLoadedAudio)
    {
      if (!paramBoolean)
        PlayerAgent.getInstance().stop();
      _playAD(paramBoolean);
      this.hasPlayAdv = (System.currentTimeMillis() / 1000L);
      return true;
    }
    loadAd();
    return false;
  }

  public void setADId(String paramString)
  {
    this.AD_IDENTITY = paramString;
  }

  public void stopAD()
  {
    if (this.mAudio != null)
      this.mAudio.stopAD();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.fm.TaobaoAgent
 * JD-Core Version:    0.6.2
 */