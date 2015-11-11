package fm.qingting.async.http.server;

import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.FilteredDataEmitter;
import java.nio.ByteBuffer;

public class BoundaryEmitter extends FilteredDataEmitter
{
  private byte[] boundary;
  int state = 2;

  static
  {
    if (!BoundaryEmitter.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public String getBoundary()
  {
    if (this.boundary == null)
      return null;
    return new String(this.boundary, 4, this.boundary.length - 4);
  }

  public String getBoundaryEnd()
  {
    assert (this.boundary != null);
    return getBoundaryStart() + "--\r\n";
  }

  public String getBoundaryStart()
  {
    assert (this.boundary != null);
    return new String(this.boundary, 2, this.boundary.length - 2);
  }

  protected void onBoundaryEnd()
  {
  }

  protected void onBoundaryStart()
  {
  }

  public void onDataAvailable(DataEmitter paramDataEmitter, ByteBufferList paramByteBufferList)
  {
    if (this.state > 0)
    {
      paramByteBufferList.add(0, ByteBuffer.wrap(this.boundary, 0, this.state).duplicate());
      this.state = 0;
    }
    paramDataEmitter = new byte[paramByteBufferList.remaining()];
    paramByteBufferList.get(paramDataEmitter);
    int i = 0;
    int j = 0;
    if (i < paramDataEmitter.length)
    {
      if (this.state >= 0)
        if (paramDataEmitter[i] == this.boundary[this.state])
        {
          this.state += 1;
          k = i;
          m = j;
          if (this.state == this.boundary.length)
          {
            this.state = -1;
            m = j;
            k = i;
          }
        }
      while (true)
      {
        i = k + 1;
        j = m;
        break;
        k = i;
        m = j;
        if (this.state > 0)
        {
          k = i - this.state;
          this.state = 0;
          m = j;
          continue;
          if (this.state != -1)
            break label301;
          if (paramDataEmitter[i] == 13)
          {
            this.state = -4;
            k = i - j - this.boundary.length;
            if ((j != 0) || (k != 0))
            {
              paramByteBufferList = ByteBuffer.wrap(paramDataEmitter, j, k);
              localByteBufferList = new ByteBufferList();
              localByteBufferList.add(paramByteBufferList);
              super.onDataAvailable(this, localByteBufferList);
            }
            onBoundaryStart();
            k = i;
            m = j;
          }
          else
          {
            if (paramDataEmitter[i] != 45)
              break label287;
            this.state = -2;
            k = i;
            m = j;
          }
        }
      }
      label287: report(new Exception("Invalid multipart/form-data. Expected \r or -"));
    }
    label301: 
    while (j >= paramDataEmitter.length)
      while (true)
      {
        int k;
        int m;
        ByteBufferList localByteBufferList;
        return;
        if (this.state == -2)
        {
          if (paramDataEmitter[i] == 45)
          {
            this.state = -3;
            k = i;
            m = j;
          }
          else
          {
            report(new Exception("Invalid multipart/form-data. Expected -"));
          }
        }
        else if (this.state == -3)
        {
          if (paramDataEmitter[i] == 13)
          {
            this.state = -4;
            paramByteBufferList = ByteBuffer.wrap(paramDataEmitter, j, i - j - this.boundary.length - 2);
            localByteBufferList = new ByteBufferList();
            localByteBufferList.add(paramByteBufferList);
            super.onDataAvailable(this, localByteBufferList);
            onBoundaryEnd();
            k = i;
            m = j;
          }
          else
          {
            report(new Exception("Invalid multipart/form-data. Expected \r"));
          }
        }
        else if (this.state == -4)
        {
          if (paramDataEmitter[i] == 10)
          {
            m = i + 1;
            this.state = 0;
            k = i;
          }
          else
          {
            report(new Exception("Invalid multipart/form-data. Expected \n"));
            k = i;
            m = j;
          }
        }
        else
        {
          if (!$assertionsDisabled)
            throw new AssertionError();
          report(new Exception("Invalid multipart/form-data. Unknown state?"));
          k = i;
          m = j;
        }
      }
    i = Math.max(this.state, 0);
    paramDataEmitter = ByteBuffer.wrap(paramDataEmitter, j, paramDataEmitter.length - j - i);
    paramByteBufferList = new ByteBufferList();
    paramByteBufferList.add(paramDataEmitter);
    super.onDataAvailable(this, paramByteBufferList);
  }

  public void setBoundary(String paramString)
  {
    this.boundary = ("\r\n--" + paramString).getBytes();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.server.BoundaryEmitter
 * JD-Core Version:    0.6.2
 */