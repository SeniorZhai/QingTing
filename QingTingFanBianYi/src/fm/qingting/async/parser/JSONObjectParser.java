package fm.qingting.async.parser;

import fm.qingting.async.DataEmitter;
import fm.qingting.async.DataSink;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.future.Future;
import fm.qingting.async.future.TransformFuture;
import org.json.JSONObject;

public class JSONObjectParser
  implements AsyncParser<JSONObject>
{
  public Future<JSONObject> parse(DataEmitter paramDataEmitter)
  {
    return new TransformFuture()
    {
      protected void transform(String paramAnonymousString)
        throws Exception
      {
        setComplete(new JSONObject(paramAnonymousString));
      }
    }
    .from(new StringParser().parse(paramDataEmitter));
  }

  public void write(DataSink paramDataSink, JSONObject paramJSONObject, CompletedCallback paramCompletedCallback)
  {
    new StringParser().write(paramDataSink, paramJSONObject.toString(), paramCompletedCallback);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.parser.JSONObjectParser
 * JD-Core Version:    0.6.2
 */