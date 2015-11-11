package fm.qingting.qtradio.remotecontrol;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.OnGetPlaybackPositionListener;
import android.media.RemoteControlClient.OnPlaybackPositionUpdateListener;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.headset.MediaButtonReceiver;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.utils.RemoteBitmapLoader;
import fm.qingting.utils.RemoteBitmapLoader.IBitmapRecvListener;

public class RemoteControl
  implements RemoteBitmapLoader.IBitmapRecvListener
{
  private static RemoteControl instance;
  private ChannelNode mChannel;
  private Context mContext;
  private String mCurThumbUrl;
  private ProgramNode mProgram;
  private RemoteControlClient mRemoteControlClient;

  public static RemoteControl getInstance()
  {
    if (instance == null)
      instance = new RemoteControl();
    return instance;
  }

  private Bitmap resizeBmp(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    return Bitmap.createScaledBitmap(paramBitmap, paramInt1, paramInt2, false);
  }

  @TargetApi(14)
  private void setTransportControlFlags()
  {
    if (QtApiLevelManager.isApiLevelSupported(14))
    {
      int i = 157;
      if (QtApiLevelManager.isApiLevelSupported(19))
        i = turnOnMediaRatingFlag(157);
      this.mRemoteControlClient.setTransportControlFlags(i);
    }
  }

  @SuppressLint({"NewApi"})
  private void setupRemoteControl()
  {
    if (QtApiLevelManager.isApiLevelSupported(18))
    {
      this.mRemoteControlClient.setOnGetPlaybackPositionListener(new RemoteControlClient.OnGetPlaybackPositionListener()
      {
        public long onGetPlaybackPosition()
        {
          return PlayerAgent.getInstance().queryPosition() * 1000;
        }
      });
      this.mRemoteControlClient.setPlaybackPositionUpdateListener(new RemoteControlClient.OnPlaybackPositionUpdateListener()
      {
        public void onPlaybackPositionUpdate(long paramAnonymousLong)
        {
          PlayerAgent.getInstance().seekPosition((int)paramAnonymousLong / 1000);
        }
      });
    }
    setTransportControlFlags();
  }

  @TargetApi(19)
  private int turnOnMediaRatingFlag(int paramInt)
  {
    return paramInt | 0x200;
  }

  // ERROR //
  @SuppressLint({"InlinedApi", "NewApi"})
  public void onRecvBitmap(String paramString, Bitmap paramBitmap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +18 -> 19
    //   4: aload_1
    //   5: aload_0
    //   6: getfield 87	fm/qingting/qtradio/remotecontrol/RemoteControl:mCurThumbUrl	Ljava/lang/String;
    //   9: invokevirtual 93	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   12: ifeq +7 -> 19
    //   15: aload_2
    //   16: ifnonnull +4 -> 20
    //   19: return
    //   20: aload_0
    //   21: getfield 56	fm/qingting/qtradio/remotecontrol/RemoteControl:mRemoteControlClient	Landroid/media/RemoteControlClient;
    //   24: iconst_1
    //   25: invokevirtual 97	android/media/RemoteControlClient:editMetadata	(Z)Landroid/media/RemoteControlClient$MetadataEditor;
    //   28: astore 4
    //   30: aload 4
    //   32: bipush 7
    //   34: aload_0
    //   35: getfield 99	fm/qingting/qtradio/remotecontrol/RemoteControl:mProgram	Lfm/qingting/qtradio/model/ProgramNode;
    //   38: getfield 104	fm/qingting/qtradio/model/ProgramNode:title	Ljava/lang/String;
    //   41: invokevirtual 110	android/media/RemoteControlClient$MetadataEditor:putString	(ILjava/lang/String;)Landroid/media/RemoteControlClient$MetadataEditor;
    //   44: pop
    //   45: aload 4
    //   47: iconst_1
    //   48: aload_0
    //   49: getfield 99	fm/qingting/qtradio/remotecontrol/RemoteControl:mProgram	Lfm/qingting/qtradio/model/ProgramNode;
    //   52: invokevirtual 114	fm/qingting/qtradio/model/ProgramNode:getChannelName	()Ljava/lang/String;
    //   55: invokevirtual 110	android/media/RemoteControlClient$MetadataEditor:putString	(ILjava/lang/String;)Landroid/media/RemoteControlClient$MetadataEditor;
    //   58: pop
    //   59: aload_0
    //   60: getfield 99	fm/qingting/qtradio/remotecontrol/RemoteControl:mProgram	Lfm/qingting/qtradio/model/ProgramNode;
    //   63: invokevirtual 117	fm/qingting/qtradio/model/ProgramNode:getBroadCasterNames	()Ljava/lang/String;
    //   66: astore_1
    //   67: aload_0
    //   68: getfield 119	fm/qingting/qtradio/remotecontrol/RemoteControl:mChannel	Lfm/qingting/qtradio/model/ChannelNode;
    //   71: ifnull +125 -> 196
    //   74: aload_0
    //   75: getfield 119	fm/qingting/qtradio/remotecontrol/RemoteControl:mChannel	Lfm/qingting/qtradio/model/ChannelNode;
    //   78: invokevirtual 124	fm/qingting/qtradio/model/ChannelNode:getAuthorNames	()Ljava/lang/String;
    //   81: astore_3
    //   82: aload_3
    //   83: ifnull +113 -> 196
    //   86: aload_3
    //   87: ldc 126
    //   89: invokevirtual 93	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   92: ifne +104 -> 196
    //   95: aload_3
    //   96: astore_1
    //   97: aload 4
    //   99: iconst_2
    //   100: aload_1
    //   101: invokevirtual 110	android/media/RemoteControlClient$MetadataEditor:putString	(ILjava/lang/String;)Landroid/media/RemoteControlClient$MetadataEditor;
    //   104: pop
    //   105: aload 4
    //   107: bipush 9
    //   109: aload_0
    //   110: getfield 99	fm/qingting/qtradio/remotecontrol/RemoteControl:mProgram	Lfm/qingting/qtradio/model/ProgramNode;
    //   113: invokevirtual 130	fm/qingting/qtradio/model/ProgramNode:getDuration	()I
    //   116: i2l
    //   117: invokevirtual 134	android/media/RemoteControlClient$MetadataEditor:putLong	(IJ)Landroid/media/RemoteControlClient$MetadataEditor;
    //   120: pop
    //   121: aload 4
    //   123: iconst_0
    //   124: aload_0
    //   125: getfield 99	fm/qingting/qtradio/remotecontrol/RemoteControl:mProgram	Lfm/qingting/qtradio/model/ProgramNode;
    //   128: getfield 138	fm/qingting/qtradio/model/ProgramNode:sequence	I
    //   131: i2l
    //   132: invokevirtual 134	android/media/RemoteControlClient$MetadataEditor:putLong	(IJ)Landroid/media/RemoteControlClient$MetadataEditor;
    //   135: pop
    //   136: aload_0
    //   137: getfield 119	fm/qingting/qtradio/remotecontrol/RemoteControl:mChannel	Lfm/qingting/qtradio/model/ChannelNode;
    //   140: invokevirtual 142	fm/qingting/qtradio/model/ChannelNode:getAllLstProgramNode	()Ljava/util/List;
    //   143: invokeinterface 147 1 0
    //   148: istore 5
    //   150: iload 5
    //   152: i2l
    //   153: lstore 6
    //   155: aload 4
    //   157: bipush 10
    //   159: lload 6
    //   161: invokevirtual 134	android/media/RemoteControlClient$MetadataEditor:putLong	(IJ)Landroid/media/RemoteControlClient$MetadataEditor;
    //   164: pop
    //   165: aload 4
    //   167: bipush 100
    //   169: aload_0
    //   170: aload_2
    //   171: sipush 630
    //   174: sipush 630
    //   177: invokespecial 149	fm/qingting/qtradio/remotecontrol/RemoteControl:resizeBmp	(Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;
    //   180: invokevirtual 153	android/media/RemoteControlClient$MetadataEditor:putBitmap	(ILandroid/graphics/Bitmap;)Landroid/media/RemoteControlClient$MetadataEditor;
    //   183: pop
    //   184: aload 4
    //   186: invokevirtual 156	android/media/RemoteControlClient$MetadataEditor:apply	()V
    //   189: return
    //   190: astore_1
    //   191: return
    //   192: astore_1
    //   193: goto -28 -> 165
    //   196: goto -99 -> 97
    //
    // Exception table:
    //   from	to	target	type
    //   20	82	190	java/lang/Exception
    //   86	95	190	java/lang/Exception
    //   97	150	190	java/lang/Exception
    //   165	189	190	java/lang/Exception
    //   155	165	192	java/lang/Exception
  }

  @SuppressLint({"NewApi"})
  public void registerRemoteControl(Context paramContext)
  {
    if (QtApiLevelManager.isApiLevelSupported(14))
    {
      ComponentName localComponentName = new ComponentName(paramContext.getPackageName(), MediaButtonReceiver.class.getName());
      Intent localIntent = new Intent("android.intent.action.MEDIA_BUTTON");
      localIntent.setComponent(localComponentName);
      this.mRemoteControlClient = new RemoteControlClient(PendingIntent.getBroadcast(paramContext.getApplicationContext(), 0, localIntent, 0));
      ((AudioManager)paramContext.getSystemService("audio")).registerRemoteControlClient(this.mRemoteControlClient);
      setupRemoteControl();
    }
  }

  @SuppressLint({"NewApi"})
  public void unregisterRemoteControl(Context paramContext)
  {
    if (QtApiLevelManager.isApiLevelSupported(14))
      ((AudioManager)paramContext.getSystemService("audio")).unregisterRemoteControlClient(this.mRemoteControlClient);
  }

  public void updateProgramInfo(Context paramContext, ChannelNode paramChannelNode, ProgramNode paramProgramNode)
  {
    if (QtApiLevelManager.isApiLevelSupported(14))
    {
      this.mProgram = paramProgramNode;
      this.mChannel = paramChannelNode;
      this.mContext = paramContext;
      if ((this.mContext != null) && (this.mChannel != null) && (this.mProgram != null))
        break label45;
    }
    label45: 
    do
    {
      return;
      this.mCurThumbUrl = this.mChannel.getApproximativeThumb(480, 480, true);
    }
    while (this.mCurThumbUrl == null);
    paramContext = new RemoteBitmapLoader(this.mCurThumbUrl);
    paramContext.setBitmapListener(this);
    paramContext.execute(new Object[0]);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.remotecontrol.RemoteControl
 * JD-Core Version:    0.6.2
 */