package fm.qingting.async;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelectableChannel;

abstract class ChannelWrapper
  implements ReadableByteChannel
{
  private AbstractSelectableChannel mChannel;

  ChannelWrapper(AbstractSelectableChannel paramAbstractSelectableChannel)
    throws IOException
  {
    paramAbstractSelectableChannel.configureBlocking(false);
    this.mChannel = paramAbstractSelectableChannel;
  }

  public void close()
    throws IOException
  {
    this.mChannel.close();
  }

  public abstract int getLocalPort();

  public boolean isChunked()
  {
    return false;
  }

  public abstract boolean isConnected();

  public boolean isOpen()
  {
    return this.mChannel.isOpen();
  }

  public abstract SelectionKey register(Selector paramSelector)
    throws ClosedChannelException;

  public SelectionKey register(Selector paramSelector, int paramInt)
    throws ClosedChannelException
  {
    return this.mChannel.register(paramSelector, paramInt);
  }

  public abstract void shutdownInput();

  public abstract void shutdownOutput();

  public abstract int write(ByteBuffer paramByteBuffer)
    throws IOException;

  public abstract int write(ByteBuffer[] paramArrayOfByteBuffer)
    throws IOException;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.ChannelWrapper
 * JD-Core Version:    0.6.2
 */