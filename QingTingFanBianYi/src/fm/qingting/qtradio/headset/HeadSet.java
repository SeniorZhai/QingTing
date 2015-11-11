package fm.qingting.qtradio.headset;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RadioChannelNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.PlayMode;
import java.util.ArrayList;
import java.util.List;

public class HeadSet
{
  static final int HEADSET_MEDIA_BUTTON = 0;
  static final int HEADSET_PLUG = 1;
  public static final int STATE_HEADSET_PLUGGED = 1;
  public static final int STATE_HEADSET_UNPLUGGED = 0;
  private static HeadSet instance;
  private MyHandler handler = new MyHandler(null);
  private int headsetPlug = 0;
  private HeadSetPlugReceiver mHeadSetPlugReceiver = new HeadSetPlugReceiver();
  private List<HeadsetListener> mlstListener = new ArrayList();

  private HeadSet(Context paramContext)
  {
    if (((AudioManager)paramContext.getSystemService("audio")).isWiredHeadsetOn());
    for (int i = 1; ; i = 0)
    {
      this.headsetPlug = i;
      return;
    }
  }

  private void dispatchPlugEvent(int paramInt)
  {
    if (this.mlstListener.size() > 0)
    {
      int i = 0;
      while (i < this.mlstListener.size())
      {
        ((HeadsetListener)this.mlstListener.get(i)).onHeadsetPlug(paramInt);
        i += 1;
      }
    }
  }

  public static HeadSet getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new HeadSet(paramContext);
    return instance;
  }

  private void playNext()
  {
    Object localObject2 = InfoManager.getInstance().root().getCurrentPlayingNode();
    if ((localObject2 != null) && (((Node)localObject2).nextSibling != null))
      if ((((Node)localObject2).nextSibling.nodeName.equalsIgnoreCase("program")) && (((ProgramNode)((Node)localObject2).nextSibling).getCurrPlayStatus() != 2));
    Object localObject1;
    do
    {
      do
      {
        do
        {
          return;
          PlayerAgent.getInstance().play(((Node)localObject2).nextSibling);
          return;
        }
        while (localObject2 == null);
        localObject1 = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      }
      while ((localObject1 == null) || (((ChannelNode)localObject1).hasEmptyProgramSchedule()) || (((ChannelNode)localObject1).getAllLstProgramNode() == null) || (!((Node)localObject2).nodeName.equalsIgnoreCase("program")));
      localObject2 = ((ChannelNode)localObject1).getProgramNodeByProgramId(((ProgramNode)localObject2).id);
      if ((localObject2 != null) && (((Node)localObject2).nextSibling != null))
      {
        PlayerAgent.getInstance().play(((Node)localObject2).nextSibling);
        return;
      }
      localObject1 = (Node)((ChannelNode)localObject1).getAllLstProgramNode().get(0);
    }
    while (localObject1 == null);
    PlayerAgent.getInstance().play((Node)localObject1);
  }

  private void playOrStop()
  {
    if (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.FMPLAY)
      if (InfoManager.getInstance().root().isOpenFm())
        PlayerAgent.getInstance().stopFM();
    Node localNode;
    do
    {
      do
      {
        do
        {
          return;
          localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
        }
        while (localNode == null);
        if (localNode.nodeName.equalsIgnoreCase("radiochannel"))
        {
          PlayerAgent.getInstance().startFM((RadioChannelNode)localNode);
          return;
        }
      }
      while (!localNode.nodeName.equalsIgnoreCase("program"));
      PlayerAgent.getInstance().play(localNode);
      return;
      if (PlayerAgent.getInstance().isPlaying())
      {
        PlayerAgent.getInstance().stop();
        return;
      }
      localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    }
    while (localNode == null);
    PlayerAgent.getInstance().play(localNode);
  }

  private void playPrev()
  {
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    if ((localNode != null) && (localNode.prevSibling != null) && (localNode.prevSibling.nodeName.equalsIgnoreCase("program")) && (((ProgramNode)localNode.prevSibling).getCurrPlayStatus() != 2))
      PlayerAgent.getInstance().play(localNode.prevSibling);
  }

  public void addMediaEventListener(HeadsetListener paramHeadsetListener)
  {
    this.mlstListener.add(paramHeadsetListener);
  }

  Handler getHandler()
  {
    return this.handler;
  }

  public int getHeadsetPlug()
  {
    return this.headsetPlug;
  }

  public void registerReceiver(Context paramContext)
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.HEADSET_PLUG");
    localIntentFilter.setPriority(100);
    try
    {
      paramContext.registerReceiver(this.mHeadSetPlugReceiver, localIntentFilter);
      ((AudioManager)paramContext.getSystemService("audio")).registerMediaButtonEventReceiver(new ComponentName(paramContext.getPackageName(), MediaButtonReceiver.class.getName()));
      return;
    }
    catch (IllegalArgumentException paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public void removeMediaEventListener(HeadsetListener paramHeadsetListener)
  {
    int i = 0;
    while (true)
    {
      if (i < this.mlstListener.size())
      {
        if (paramHeadsetListener == this.mlstListener.get(i))
          this.mlstListener.remove(i);
      }
      else
        return;
      i += 1;
    }
  }

  public void unRegisterReceiver(Context paramContext)
  {
    try
    {
      paramContext.unregisterReceiver(this.mHeadSetPlugReceiver);
      ((AudioManager)paramContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(new ComponentName(paramContext.getPackageName(), MediaButtonReceiver.class.getName()));
      return;
    }
    catch (IllegalArgumentException paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  class HeadSetPlugReceiver extends BroadcastReceiver
  {
    HeadSetPlugReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ("android.intent.action.HEADSET_PLUG".equalsIgnoreCase(paramIntent.getAction()))
      {
        paramContext = Message.obtain();
        paramContext.what = 1;
        paramContext.arg1 = paramIntent.getIntExtra("state", 0);
        HeadSet.this.handler.sendMessage(paramContext);
      }
    }
  }

  private class MyHandler extends Handler
  {
    private MyHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        super.handleMessage(paramMessage);
      case 1:
        do
        {
          return;
          HeadSet.access$102(HeadSet.this, paramMessage.arg1);
          HeadSet.this.dispatchPlugEvent(paramMessage.arg1);
        }
        while (HeadSet.this.headsetPlug != 0);
        PlayerAgent.getInstance().stop();
        return;
      case 0:
      }
      switch (paramMessage.getData().getInt("key_code"))
      {
      default:
        return;
      case 79:
      case 85:
      case 126:
      case 127:
        HeadSet.this.playOrStop();
        return;
      case 87:
        HeadSet.this.playNext();
        return;
      case 88:
      }
      HeadSet.this.playPrev();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.headset.HeadSet
 * JD-Core Version:    0.6.2
 */