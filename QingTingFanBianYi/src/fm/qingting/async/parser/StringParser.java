package fm.qingting.async.parser;

import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.DataSink;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.future.Future;
import fm.qingting.async.future.TransformFuture;

public class StringParser
  implements AsyncParser<String>
{
  public Future<String> parse(DataEmitter paramDataEmitter)
  {
    return new TransformFuture()
    {
      protected void transform(ByteBufferList paramAnonymousByteBufferList)
        throws Exception
      {
        setComplete(paramAnonymousByteBufferList.readString());
      }
    }
    .from(new ByteBufferListParser().parse(paramDataEmitter));
  }

  public void write(DataSink paramDataSink, String paramString, CompletedCallback paramCompletedCallback)
  {
    new ByteBufferListParser().write(paramDataSink, new ByteBufferList(paramString.getBytes()), paramCompletedCallback);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.parser.StringParser
 * JD-Core Version:    0.6.2
 */