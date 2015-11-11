package fm.qingting.async;

import java.nio.ByteBuffer;

public class FilteredDataSink extends BufferedDataSink
{
  static
  {
    if (!FilteredDataSink.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public FilteredDataSink(DataSink paramDataSink)
  {
    super(paramDataSink);
    setMaxBuffer(0);
  }

  public ByteBufferList filter(ByteBufferList paramByteBufferList)
  {
    return paramByteBufferList;
  }

  public final void write(ByteBufferList paramByteBufferList)
  {
    if ((isBuffering()) && (getMaxBuffer() != 2147483647));
    do
    {
      return;
      ByteBufferList localByteBufferList = filter(paramByteBufferList);
      assert ((paramByteBufferList == null) || (localByteBufferList == paramByteBufferList) || (paramByteBufferList.isEmpty()));
      super.write(localByteBufferList, true);
    }
    while (paramByteBufferList == null);
    paramByteBufferList.clear();
  }

  public final void write(ByteBuffer paramByteBuffer)
  {
    if ((isBuffering()) && (getMaxBuffer() != 2147483647))
      return;
    ByteBufferList localByteBufferList = new ByteBufferList();
    byte[] arrayOfByte = new byte[paramByteBuffer.remaining()];
    paramByteBuffer.get(arrayOfByte);
    assert (paramByteBuffer.remaining() == 0);
    localByteBufferList.add(ByteBuffer.wrap(arrayOfByte));
    super.write(filter(localByteBufferList), true);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.FilteredDataSink
 * JD-Core Version:    0.6.2
 */