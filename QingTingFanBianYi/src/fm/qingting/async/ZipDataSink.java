package fm.qingting.async;

import fm.qingting.async.callback.CompletedCallback;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDataSink extends FilteredDataSink
{
  ByteArrayOutputStream bout = new ByteArrayOutputStream();
  boolean first = true;
  ZipOutputStream zop = new ZipOutputStream(this.bout);

  public ZipDataSink(DataSink paramDataSink)
  {
    super(paramDataSink);
  }

  public void close()
  {
    try
    {
      this.zop.close();
      setMaxBuffer(2147483647);
      write(new ByteBufferList());
      super.close();
      return;
    }
    catch (IOException localIOException)
    {
      report(localIOException);
    }
  }

  public void closeEntry()
    throws IOException
  {
    this.zop.closeEntry();
  }

  public ByteBufferList filter(ByteBufferList paramByteBufferList)
  {
    Object localObject;
    if (paramByteBufferList != null)
      try
      {
        while (paramByteBufferList.size() > 0)
        {
          localObject = paramByteBufferList.remove();
          this.zop.write(((ByteBuffer)localObject).array(), ((ByteBuffer)localObject).arrayOffset() + ((ByteBuffer)localObject).position(), ((ByteBuffer)localObject).remaining());
        }
      }
      catch (IOException paramByteBufferList)
      {
        report(paramByteBufferList);
        localObject = null;
      }
    ByteBufferList localByteBufferList;
    do
    {
      return localObject;
      localByteBufferList = new ByteBufferList(this.bout.toByteArray());
      this.bout.reset();
      localObject = localByteBufferList;
    }
    while (paramByteBufferList == null);
    paramByteBufferList.clear();
    return localByteBufferList;
  }

  public void putNextEntry(ZipEntry paramZipEntry)
    throws IOException
  {
    this.zop.putNextEntry(paramZipEntry);
  }

  protected void report(Exception paramException)
  {
    CompletedCallback localCompletedCallback = getClosedCallback();
    if (localCompletedCallback != null)
      localCompletedCallback.onCompleted(paramException);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.ZipDataSink
 * JD-Core Version:    0.6.2
 */