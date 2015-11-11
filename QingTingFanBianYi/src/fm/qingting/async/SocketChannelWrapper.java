package fm.qingting.async;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

class SocketChannelWrapper extends ChannelWrapper
{
  SocketChannel mChannel;

  SocketChannelWrapper(SocketChannel paramSocketChannel)
    throws IOException
  {
    super(paramSocketChannel);
    this.mChannel = paramSocketChannel;
  }

  public int getLocalPort()
  {
    return this.mChannel.socket().getLocalPort();
  }

  public boolean isConnected()
  {
    return this.mChannel.isConnected();
  }

  public int read(ByteBuffer paramByteBuffer)
    throws IOException
  {
    return this.mChannel.read(paramByteBuffer);
  }

  public SelectionKey register(Selector paramSelector)
    throws ClosedChannelException
  {
    return register(paramSelector, 8);
  }

  public void shutdownInput()
  {
    try
    {
      this.mChannel.socket().shutdownInput();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void shutdownOutput()
  {
    try
    {
      this.mChannel.socket().shutdownOutput();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public int write(ByteBuffer paramByteBuffer)
    throws IOException
  {
    return this.mChannel.write(paramByteBuffer);
  }

  public int write(ByteBuffer[] paramArrayOfByteBuffer)
    throws IOException
  {
    return (int)this.mChannel.write(paramArrayOfByteBuffer);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.SocketChannelWrapper
 * JD-Core Version:    0.6.2
 */