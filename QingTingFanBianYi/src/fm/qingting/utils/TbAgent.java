package fm.qingting.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import com.taobao.munion.base.a;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.view.audio.MunionAudio;
import com.taobao.newxp.view.audio.MunionAudio.OnAudioADClientCallBackListener;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.PlayMode;
import java.lang.reflect.Field;

public class TbAgent
{
  private static final String AD_IDENTITY = "62831";
  private static TbAgent instance;
  private boolean hasLoadedAudio = false;
  private MunionAudio mAudio;
  private Context mContext;
  private MediaPlayer mPlayer;

  private void _playAD()
  {
    if (this.mAudio != null)
    {
      InfoManager.getInstance().setPlayAdvertisementTime();
      mute();
      this.mAudio.playAD();
      QTMSGManage.getInstance().sendStatistcsMessage("taobao_ad", "play");
    }
  }

  private void change()
  {
    AlimmContext.getAliContext().init(this.mContext);
    log(AlimmContext.getAliContext().getAppUtils().r());
    Class localClass = AlimmContext.getAliContext().getAppUtils().getClass();
    try
    {
      Field[] arrayOfField = localClass.getDeclaredFields();
      int i = 0;
      while (i < arrayOfField.length)
      {
        Object localObject = arrayOfField[i].getType();
        log("fa:" + localObject + "____" + arrayOfField[i].getName());
        if ((!arrayOfField[i].getName().equalsIgnoreCase("a")) && (!arrayOfField[i].getName().equalsIgnoreCase("u")))
        {
          localObject = localClass.getDeclaredField(arrayOfField[i].getName());
          ((Field)localObject).setAccessible(true);
          localObject = String.valueOf(((Field)localObject).get(AlimmContext.getAliContext().getAppUtils()));
          log(arrayOfField[i].getName() + " =" + (String)localObject);
        }
        i += 1;
      }
    }
    catch (Exception localException)
    {
    }
  }

  public static TbAgent getInstance()
  {
    try
    {
      if (instance == null)
        instance = new TbAgent();
      TbAgent localTbAgent = instance;
      return localTbAgent;
    }
    finally
    {
    }
  }

  private void log(String paramString)
  {
    Log.e("tbagent", paramString);
  }

  private void mute()
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
          if (this.mPlayer != null)
            this.mPlayer.setVolume(0.0F, 0.0F);
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

  public void init(Context paramContext)
  {
    this.mContext = paramContext;
    this.mAudio = new MunionAudio(paramContext);
    change();
    mute();
    this.mAudio.setOnAudioADClientCallBackListener(new MunionAudio.OnAudioADClientCallBackListener()
    {
      public void onDidPause()
      {
        TbAgent.this.log("暂停广告播放");
      }

      public void onDidStart()
      {
        TbAgent.this.log("开始广告播放");
        InfoManager.getInstance().root().setPlayMode(RootNode.PlayMode.PLAY_FRONT_ADVERTISEMENT);
      }

      public void onDidStop()
      {
        TbAgent.this.log("停止广告播放");
        TbAgent.this.onHandlePlayFinished();
      }

      public void onPlayDidFinished()
      {
        TbAgent.this.log("播放广告完成");
        TbAgent.this.onHandlePlayFinished();
        QTMSGManage.getInstance().sendStatistcsMessage("taobao_ad", "playFinished");
      }

      public void onPlayFailed(String paramAnonymousString)
      {
        TbAgent.this.log("播放广告失败");
        TbAgent.this.onHandlePlayFinished();
        QTMSGManage.getInstance().sendStatistcsMessage("taobao_ad", "playFailed");
      }

      public void onRequestFailed(String paramAnonymousString)
      {
        TbAgent.this.log("广告请求失败");
        QTMSGManage.getInstance().sendStatistcsMessage("taobao_ad", "requestADFailed");
      }

      public void onRequestFinished()
      {
        TbAgent.this.log("广告请求成功");
        TbAgent.access$102(TbAgent.this, true);
        QTMSGManage.getInstance().sendStatistcsMessage("taobao_ad", "requestADSucc");
      }
    });
  }

  public void loadAd()
  {
    if (this.mAudio != null)
    {
      this.hasLoadedAudio = false;
      this.mAudio.requestAD("62831");
      QTMSGManage.getInstance().sendStatistcsMessage("taobao_ad", "load");
    }
  }

  public boolean playAD()
  {
    if (this.hasLoadedAudio)
    {
      PlayerAgent.getInstance().stop();
      _playAD();
      return true;
    }
    loadAd();
    return false;
  }

  public void stopAD()
  {
    if (this.mAudio != null)
      this.mAudio.stopAD();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.TbAgent
 * JD-Core Version:    0.6.2
 */