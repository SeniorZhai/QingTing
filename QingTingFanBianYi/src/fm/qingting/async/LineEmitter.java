package fm.qingting.async;

import fm.qingting.async.callback.DataCallback;

public class LineEmitter
  implements DataCallback
{
  StringBuilder data = new StringBuilder();
  StringCallback mLineCallback;

  static
  {
    if (!LineEmitter.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public StringCallback getLineCallback()
  {
    return this.mLineCallback;
  }

  public void onDataAvailable(DataEmitter paramDataEmitter, ByteBufferList paramByteBufferList)
  {
    while (true)
    {
      int i;
      if (paramByteBufferList.remaining() > 0)
      {
        i = paramByteBufferList.get();
        if (i == 10)
        {
          assert (this.mLineCallback != null);
          this.mLineCallback.onStringAvailable(this.data.toString());
          this.data = new StringBuilder();
        }
      }
      else
      {
        return;
      }
      this.data.append((char)i);
    }
  }

  public void setLineCallback(StringCallback paramStringCallback)
  {
    this.mLineCallback = paramStringCallback;
  }

  public static abstract interface StringCallback
  {
    public abstract void onStringAvailable(String paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.LineEmitter
 * JD-Core Version:    0.6.2
 */