package fm.qingting.async;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

class ServerSocketChannelWrapper extends ChannelWrapper
{
  ServerSocketChannel mChannel;

  static
  {
    if (!ServerSocketChannelWrapper.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  ServerSocketChannelWrapper(ServerSocketChannel paramServerSocketChannel)
    throws IOException
  {
    super(paramServerSocketChannel);
    this.mChannel = paramServerSocketChannel;
  }

  public int getLocalPort()
  {
    return this.mChannel.socket().getLocalPort();
  }

  public boolean isConnected()
  {
    if (!$assertionsDisabled)
      throw new AssertionError();
    return false;
  }

  public int read(ByteBuffer paramByteBuffer)
    throws IOException
  {
    if (!$assertionsDisabled)
      throw new AssertionError();
    throw new IOException("Can't read ServerSocketChannel");
  }

  public SelectionKey register(Selector paramSelector)
    throws ClosedChannelException
  {
    return this.mChannel.register(paramSelector, 16);
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
    if (!$assertionsDisabled)
      throw new AssertionError();
    throw new IOException("Can't write ServerSocketChannel");
  }

  public int write(ByteBuffer[] paramArrayOfByteBuffer)
    throws IOException
  {
    if (!$assertionsDisabled)
      throw new AssertionError();
    throw new IOException("Can't write ServerSocketChannel");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.ServerSocketChannelWrapper
 * JD-Core Version:    0.6.2
 */