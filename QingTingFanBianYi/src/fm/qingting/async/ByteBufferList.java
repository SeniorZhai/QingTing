package fm.qingting.async;

import android.os.Looper;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ByteBufferList
{
  private static final int MAX_SIZE = 1048576;
  static int currentSize;
  static PriorityQueue<ByteBuffer> reclaimed;
  LinkedList<ByteBuffer> mBuffers = new LinkedList();
  ByteOrder order = ByteOrder.BIG_ENDIAN;
  private int remaining = 0;

  static
  {
    if (!ByteBufferList.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      reclaimed = new PriorityQueue();
      currentSize = 0;
      return;
    }
  }

  public ByteBufferList()
  {
  }

  public ByteBufferList(byte[] paramArrayOfByte)
  {
    add(ByteBuffer.wrap(paramArrayOfByte));
  }

  public ByteBufferList(ByteBuffer[] paramArrayOfByteBuffer)
  {
    addAll(paramArrayOfByteBuffer);
  }

  private void addRemaining(int paramInt)
  {
    if (remaining() >= 0)
      this.remaining += paramInt;
  }

  private static PriorityQueue<ByteBuffer> getReclaimed()
  {
    if (Thread.currentThread() == Looper.getMainLooper().getThread())
      return null;
    return reclaimed;
  }

  public static ByteBuffer obtain(int paramInt)
  {
    PriorityQueue localPriorityQueue;
    if (paramInt <= 8192)
    {
      assert (Thread.currentThread() != Looper.getMainLooper().getThread());
      localPriorityQueue = getReclaimed();
      if (localPriorityQueue == null);
    }
    try
    {
      while (localPriorityQueue.size() > 0)
      {
        ByteBuffer localByteBuffer = (ByteBuffer)localPriorityQueue.remove();
        currentSize -= localByteBuffer.capacity();
        if (localByteBuffer.capacity() >= paramInt)
          return localByteBuffer;
      }
      return ByteBuffer.allocate(Math.max(8192, paramInt));
    }
    finally
    {
    }
  }

  private ByteBuffer read(int paramInt)
  {
    if (remaining() < paramInt)
      throw new IllegalArgumentException("count");
    for (ByteBuffer localByteBuffer = (ByteBuffer)this.mBuffers.peek(); (localByteBuffer != null) && (localByteBuffer.position() == localByteBuffer.limit()); localByteBuffer = (ByteBuffer)this.mBuffers.peek())
      reclaim((ByteBuffer)this.mBuffers.remove());
    if (localByteBuffer == null)
      return ByteBuffer.wrap(new byte[0]).order(this.order);
    if (localByteBuffer.remaining() >= paramInt)
      return localByteBuffer.order(this.order);
    byte[] arrayOfByte = new byte[paramInt];
    localByteBuffer = null;
    int i = 0;
    while (i < paramInt)
    {
      if (localByteBuffer != null)
        reclaim(localByteBuffer);
      localByteBuffer = (ByteBuffer)this.mBuffers.remove();
      int j = Math.min(paramInt - i, localByteBuffer.remaining());
      localByteBuffer.get(arrayOfByte, i, j);
      i += j;
    }
    assert (localByteBuffer != null);
    if (localByteBuffer.position() < localByteBuffer.limit())
      this.mBuffers.add(0, localByteBuffer);
    localByteBuffer = ByteBuffer.wrap(arrayOfByte);
    this.mBuffers.add(0, localByteBuffer);
    return localByteBuffer.order(this.order);
  }

  public static void reclaim(ByteBuffer paramByteBuffer)
  {
    if ((paramByteBuffer.arrayOffset() != 0) || (paramByteBuffer.array().length != paramByteBuffer.capacity()));
    PriorityQueue localPriorityQueue;
    do
    {
      do
        return;
      while ((paramByteBuffer.capacity() < 8192) || (currentSize > 1048576));
      paramByteBuffer.position(0);
      paramByteBuffer.limit(paramByteBuffer.capacity());
      currentSize += paramByteBuffer.capacity();
      localPriorityQueue = getReclaimed();
    }
    while (localPriorityQueue == null);
    try
    {
      localPriorityQueue.add(paramByteBuffer);
      return;
    }
    finally
    {
    }
    throw paramByteBuffer;
  }

  public void add(int paramInt, ByteBuffer paramByteBuffer)
  {
    addRemaining(paramByteBuffer.remaining());
    this.mBuffers.add(paramInt, paramByteBuffer);
  }

  public void add(ByteBuffer paramByteBuffer)
  {
    if (paramByteBuffer.remaining() <= 0)
    {
      reclaim(paramByteBuffer);
      return;
    }
    addRemaining(paramByteBuffer.remaining());
    this.mBuffers.add(paramByteBuffer);
    trim();
  }

  public void addAll(ByteBuffer[] paramArrayOfByteBuffer)
  {
    int j = paramArrayOfByteBuffer.length;
    int i = 0;
    while (i < j)
    {
      add(paramArrayOfByteBuffer[i]);
      i += 1;
    }
  }

  public void clear()
  {
    this.mBuffers.clear();
    this.remaining = 0;
  }

  public byte get()
  {
    byte b = read(1).get();
    this.remaining -= 1;
    return b;
  }

  public ByteBufferList get(int paramInt)
  {
    ByteBufferList localByteBufferList = new ByteBufferList();
    get(localByteBufferList, paramInt);
    return localByteBufferList.order(this.order);
  }

  public void get(ByteBufferList paramByteBufferList)
  {
    get(paramByteBufferList, remaining());
  }

  public void get(ByteBufferList paramByteBufferList, int paramInt)
  {
    if (remaining() < paramInt)
      throw new IllegalArgumentException("length");
    int i = 0;
    while (true)
    {
      ByteBuffer localByteBuffer;
      int j;
      if (i < paramInt)
      {
        localByteBuffer = (ByteBuffer)this.mBuffers.remove();
        j = localByteBuffer.remaining();
        if (j == 0)
        {
          reclaim(localByteBuffer);
        }
        else if (i + j > paramInt)
        {
          byte[] arrayOfByte = new byte[paramInt - i];
          localByteBuffer.get(arrayOfByte);
          paramByteBufferList.add(ByteBuffer.wrap(arrayOfByte));
          this.mBuffers.add(0, localByteBuffer);
        }
      }
      else
      {
        this.remaining -= paramInt;
        return;
        paramByteBufferList.add(localByteBuffer);
        i += j;
      }
    }
  }

  public void get(byte[] paramArrayOfByte)
  {
    get(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void get(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    read(paramInt2).get(paramArrayOfByte, paramInt1, paramInt2);
    this.remaining -= paramInt2;
  }

  public ByteBuffer getAll()
  {
    read(remaining());
    return remove();
  }

  public ByteBuffer[] getAllArray()
  {
    ByteBuffer[] arrayOfByteBuffer = new ByteBuffer[this.mBuffers.size()];
    arrayOfByteBuffer = (ByteBuffer[])this.mBuffers.toArray(arrayOfByteBuffer);
    this.mBuffers.clear();
    this.remaining = 0;
    return arrayOfByteBuffer;
  }

  public byte[] getAllByteArray()
  {
    byte[] arrayOfByte = new byte[remaining()];
    get(arrayOfByte);
    return arrayOfByte;
  }

  public char getByteChar()
  {
    char c = (char)read(1).get();
    this.remaining -= 1;
    return c;
  }

  public int getInt()
  {
    int i = read(4).getInt();
    this.remaining -= 4;
    return i;
  }

  public long getLong()
  {
    long l = read(8).getLong();
    this.remaining -= 8;
    return l;
  }

  public int getShort()
  {
    int i = read(2).getShort();
    this.remaining -= 2;
    return i;
  }

  public boolean isEmpty()
  {
    return this.remaining == 0;
  }

  public ByteBufferList order(ByteOrder paramByteOrder)
  {
    this.order = paramByteOrder;
    return this;
  }

  public ByteOrder order()
  {
    return this.order;
  }

  public String peekString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.mBuffers.iterator();
    while (localIterator.hasNext())
    {
      ByteBuffer localByteBuffer = (ByteBuffer)localIterator.next();
      localStringBuilder.append(new String(localByteBuffer.array(), localByteBuffer.arrayOffset() + localByteBuffer.position(), localByteBuffer.remaining()));
    }
    return localStringBuilder.toString();
  }

  public String readString()
  {
    String str = peekString();
    clear();
    return str;
  }

  public int remaining()
  {
    return this.remaining;
  }

  public ByteBuffer remove()
  {
    ByteBuffer localByteBuffer = (ByteBuffer)this.mBuffers.remove();
    this.remaining -= localByteBuffer.remaining();
    return localByteBuffer;
  }

  public int size()
  {
    return this.mBuffers.size();
  }

  public void spewString()
  {
    System.out.println(peekString());
  }

  public void trim()
  {
    read(0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.ByteBufferList
 * JD-Core Version:    0.6.2
 */