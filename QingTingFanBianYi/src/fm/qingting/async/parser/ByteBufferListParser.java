package fm.qingting.async.parser;

import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.DataSink;
import fm.qingting.async.Util;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;
import fm.qingting.async.future.Future;
import fm.qingting.async.future.SimpleFuture;

public class ByteBufferListParser
  implements AsyncParser<ByteBufferList>
{
  public Future<ByteBufferList> parse(final DataEmitter paramDataEmitter)
  {
    final ByteBufferList localByteBufferList = new ByteBufferList();
    final SimpleFuture local1 = new SimpleFuture()
    {
      protected void cancelCleanup()
      {
        paramDataEmitter.close();
      }
    };
    paramDataEmitter.setDataCallback(new DataCallback()
    {
      public void onDataAvailable(DataEmitter paramAnonymousDataEmitter, ByteBufferList paramAnonymousByteBufferList)
      {
        paramAnonymousByteBufferList.get(localByteBufferList);
      }
    });
    paramDataEmitter.setEndCallback(new CompletedCallback()
    {
      public void onCompleted(Exception paramAnonymousException)
      {
        if (paramAnonymousException != null)
        {
          local1.setComplete(paramAnonymousException);
          return;
        }
        try
        {
          local1.setComplete(localByteBufferList);
          return;
        }
        catch (Exception paramAnonymousException)
        {
          local1.setComplete(paramAnonymousException);
        }
      }
    });
    return local1;
  }

  public void write(DataSink paramDataSink, ByteBufferList paramByteBufferList, CompletedCallback paramCompletedCallback)
  {
    Util.writeAll(paramDataSink, paramByteBufferList, paramCompletedCallback);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.parser.ByteBufferListParser
 * JD-Core Version:    0.6.2
 */