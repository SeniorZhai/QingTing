package fm.qingting.async.http.filter;

import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.FilteredDataEmitter;

public class ContentLengthFilter extends FilteredDataEmitter
{
  int contentLength;
  int totalRead;

  static
  {
    if (!ContentLengthFilter.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public ContentLengthFilter(int paramInt)
  {
    this.contentLength = paramInt;
  }

  public void onDataAvailable(DataEmitter paramDataEmitter, ByteBufferList paramByteBufferList)
  {
    assert (this.totalRead < this.contentLength);
    int i = paramByteBufferList.remaining();
    int j = Math.min(this.contentLength - this.totalRead, i);
    if (j == i);
    while (true)
    {
      this.totalRead += paramByteBufferList.remaining();
      super.onDataAvailable(paramDataEmitter, paramByteBufferList);
      if (this.totalRead == this.contentLength)
        report(null);
      return;
      paramByteBufferList = paramByteBufferList.get(j);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.filter.ContentLengthFilter
 * JD-Core Version:    0.6.2
 */