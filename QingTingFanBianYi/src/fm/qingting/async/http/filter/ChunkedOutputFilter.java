package fm.qingting.async.http.filter;

import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataSink;
import fm.qingting.async.FilteredDataSink;
import java.nio.ByteBuffer;

public class ChunkedOutputFilter extends FilteredDataSink
{
  public ChunkedOutputFilter(DataSink paramDataSink)
  {
    super(paramDataSink);
  }

  public ByteBufferList filter(ByteBufferList paramByteBufferList)
  {
    paramByteBufferList.add(0, ByteBuffer.wrap((Integer.toString(paramByteBufferList.remaining(), 16) + "\r\n").getBytes()));
    paramByteBufferList.add(ByteBuffer.wrap("\r\n".getBytes()));
    return paramByteBufferList;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.filter.ChunkedOutputFilter
 * JD-Core Version:    0.6.2
 */