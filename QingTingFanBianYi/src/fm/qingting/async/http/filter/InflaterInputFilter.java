package fm.qingting.async.http.filter;

import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.FilteredDataEmitter;
import fm.qingting.async.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.Inflater;

public class InflaterInputFilter extends FilteredDataEmitter
{
  private Inflater mInflater;

  static
  {
    if (!InflaterInputFilter.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public InflaterInputFilter()
  {
    this(new Inflater());
  }

  public InflaterInputFilter(Inflater paramInflater)
  {
    this.mInflater = paramInflater;
  }

  public void onDataAvailable(DataEmitter paramDataEmitter, ByteBufferList paramByteBufferList)
  {
    ByteBufferList localByteBufferList;
    while (true)
    {
      try
      {
        localByteBufferList = new ByteBufferList();
        paramDataEmitter = ByteBufferList.obtain(paramByteBufferList.remaining() * 2);
        if (paramByteBufferList.size() <= 0)
          break;
        localObject = paramByteBufferList.remove();
        if (!((ByteBuffer)localObject).hasRemaining())
          continue;
        int i = ((ByteBuffer)localObject).remaining();
        this.mInflater.setInput(((ByteBuffer)localObject).array(), ((ByteBuffer)localObject).arrayOffset() + ((ByteBuffer)localObject).position(), ((ByteBuffer)localObject).remaining());
        paramDataEmitter.position(this.mInflater.inflate(paramDataEmitter.array(), paramDataEmitter.arrayOffset() + paramDataEmitter.position(), paramDataEmitter.remaining()) + paramDataEmitter.position());
        localObject = paramDataEmitter;
        if (paramDataEmitter.hasRemaining())
          break label168;
        paramDataEmitter.limit(paramDataEmitter.position());
        paramDataEmitter.position(0);
        localByteBufferList.add(paramDataEmitter);
        if ((!$assertionsDisabled) && (i == 0))
          throw new AssertionError();
      }
      catch (Exception paramDataEmitter)
      {
        report(paramDataEmitter);
        return;
      }
      Object localObject = ByteBufferList.obtain(paramDataEmitter.capacity() * 2);
      label168: paramDataEmitter = (DataEmitter)localObject;
      if (!this.mInflater.needsInput())
      {
        paramDataEmitter = (DataEmitter)localObject;
        if (this.mInflater.finished())
          paramDataEmitter = (DataEmitter)localObject;
      }
    }
    paramDataEmitter.limit(paramDataEmitter.position());
    paramDataEmitter.position(0);
    localByteBufferList.add(paramDataEmitter);
    Util.emitAllData(this, localByteBufferList);
  }

  protected void report(Exception paramException)
  {
    Object localObject = paramException;
    if (paramException != null)
    {
      localObject = paramException;
      if (this.mInflater.getRemaining() > 0)
        localObject = new IOException("data still remaining in inflater");
    }
    super.report((Exception)localObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.filter.InflaterInputFilter
 * JD-Core Version:    0.6.2
 */