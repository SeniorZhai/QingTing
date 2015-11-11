package fm.qingting.async;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

class DatagramChannelWrapper extends ChannelWrapper
{
  InetSocketAddress address;
  DatagramChannel mChannel;

  DatagramChannelWrapper(DatagramChannel paramDatagramChannel)
    throws IOException
  {
    super(paramDatagramChannel);
    this.mChannel = paramDatagramChannel;
  }

  public void disconnect()
    throws IOException
  {
    this.mChannel.disconnect();
  }

  public int getLocalPort()
  {
    return this.mChannel.socket().getLocalPort();
  }

  public InetSocketAddress getRemoteAddress()
  {
    return this.address;
  }

  public boolean isChunked()
  {
    return true;
  }

  public boolean isConnected()
  {
    return this.mChannel.isConnected();
  }

  public int read(ByteBuffer paramByteBuffer)
    throws IOException
  {
    if (!isConnected())
    {
      int i = paramByteBuffer.position();
      this.address = ((InetSocketAddress)this.mChannel.receive(paramByteBuffer));
      if (this.address == null)
        return -1;
      return paramByteBuffer.position() - i;
    }
    this.address = null;
    return this.mChannel.read(paramByteBuffer);
  }

  public SelectionKey register(Selector paramSelector)
    throws ClosedChannelException
  {
    return register(paramSelector, 1);
  }

  public SelectionKey register(Selector paramSelector, int paramInt)
    throws ClosedChannelException
  {
    return this.mChannel.register(paramSelector, paramInt);
  }

  public void shutdownInput()
  {
  }

  public void shutdownOutput()
  {
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
 * Qualified Name:     fm.qingting.async.DatagramChannelWrapper
 * JD-Core Version:    0.6.2
 */