package fm.qingting.qtradio.room;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import fm.qingting.qtradio.model.InfoManager;

public class LiveRoom extends Room
  implements RoomMessageListener
{
  private Chat mChat;
  private Handler mDoActionHandler;
  private HandlerThread mDoActionThread = new HandlerThread("doActionThread");

  public LiveRoom()
  {
    init();
    this.type = 1;
  }

  private void init()
  {
    this.mDoActionThread.start();
    this.mDoActionHandler = new DoActionHandler(this.mDoActionThread.getLooper());
  }

  public void doAction(Action paramAction)
  {
    if (paramAction == null)
      return;
    this.mDoActionHandler.sendMessage(this.mDoActionHandler.obtainMessage(paramAction.getActionType(), paramAction));
  }

  public Chat getChat()
  {
    if (this.mChat == null)
    {
      this.mChat = new LiveRoomChat();
      Context localContext = InfoManager.getInstance().getContext();
      ((LiveRoomChat)this.mChat).init(localContext);
      this.mChat.registerEventListener(this);
    }
    return this.mChat;
  }

  public void onConnect()
  {
  }

  public void onConnectFailure()
  {
  }

  public void onDisconnect()
  {
  }

  public void onMessage()
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.LiveRoom
 * JD-Core Version:    0.6.2
 */