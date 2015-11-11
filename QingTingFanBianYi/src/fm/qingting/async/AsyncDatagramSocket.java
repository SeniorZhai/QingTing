package fm.qingting.async;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class AsyncDatagramSocket extends AsyncNetworkSocket
{
  public void connect(InetSocketAddress paramInetSocketAddress)
    throws IOException
  {
    this.socketAddress = paramInetSocketAddress;
    ((DatagramChannelWrapper)getChannel()).mChannel.connect(paramInetSocketAddress);
  }

  public void disconnect()
    throws IOException
  {
    this.socketAddress = null;
    ((DatagramChannelWrapper)getChannel()).disconnect();
  }

  public InetSocketAddress getRemoteAddress()
  {
    if (isOpen())
      return super.getRemoteAddress();
    return ((DatagramChannelWrapper)getChannel()).getRemoteAddress();
  }

  public void send(final String paramString, final int paramInt, final ByteBuffer paramByteBuffer)
  {
    if (getServer().getAffinity() != Thread.currentThread())
    {
      getServer().run(new Runnable()
      {
        public void run()
        {
          AsyncDatagramSocket.this.send(paramString, paramInt, paramByteBuffer);
        }
      });
      return;
    }
    try
    {
      ((DatagramChannelWrapper)getChannel()).mChannel.send(paramByteBuffer, new InetSocketAddress(paramString, paramInt));
      return;
    }
    catch (IOException paramString)
    {
    }
  }

  public void send(final InetSocketAddress paramInetSocketAddress, final ByteBuffer paramByteBuffer)
  {
    if (getServer().getAffinity() != Thread.currentThread())
    {
      getServer().run(new Runnable()
      {
        public void run()
        {
          AsyncDatagramSocket.this.send(paramInetSocketAddress, paramByteBuffer);
        }
      });
      return;
    }
    try
    {
      ((DatagramChannelWrapper)getChannel()).mChannel.send(paramByteBuffer, new InetSocketAddress(paramInetSocketAddress.getHostName(), paramInetSocketAddress.getPort()));
      return;
    }
    catch (IOException paramInetSocketAddress)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.AsyncDatagramSocket
 * JD-Core Version:    0.6.2
 */