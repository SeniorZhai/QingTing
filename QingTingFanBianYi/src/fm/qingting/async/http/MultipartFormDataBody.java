package fm.qingting.async.http;

import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.DataSink;
import fm.qingting.async.LineEmitter;
import fm.qingting.async.LineEmitter.StringCallback;
import fm.qingting.async.NullDataCallback;
import fm.qingting.async.Util;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.ContinuationCallback;
import fm.qingting.async.callback.DataCallback;
import fm.qingting.async.future.Continuation;
import fm.qingting.async.http.libcore.RawHeaders;
import fm.qingting.async.http.server.BoundaryEmitter;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class MultipartFormDataBody extends BoundaryEmitter
  implements AsyncHttpRequestBody<Multimap>
{
  public static final String CONTENT_TYPE = "multipart/form-data";
  RawHeaders formData;
  ByteBufferList last;
  String lastName;
  LineEmitter liner;
  MultipartCallback mCallback;
  private ArrayList<Part> mParts;
  int totalToWrite;
  int written;

  public MultipartFormDataBody()
  {
  }

  public MultipartFormDataBody(String paramString, String[] paramArrayOfString)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    if (i < j)
    {
      paramString = paramArrayOfString[i].split("=");
      if (paramString.length != 2);
      while (!"boundary".equals(paramString[0]))
      {
        i += 1;
        break;
      }
      setBoundary(paramString[1]);
      return;
    }
    report(new Exception("No boundary found for multipart/form-data"));
  }

  public void addFilePart(String paramString, File paramFile)
  {
    addPart(new FilePart(paramString, paramFile));
  }

  public void addPart(Part paramPart)
  {
    if (this.mParts == null)
      this.mParts = new ArrayList();
    this.mParts.add(paramPart);
  }

  public void addStringPart(String paramString1, String paramString2)
  {
    addPart(new StringPart(paramString1, paramString2));
  }

  public Multimap get()
  {
    return new Multimap(this.formData);
  }

  public String getContentType()
  {
    if (getBoundary() == null)
      setBoundary("----------------------------" + UUID.randomUUID().toString().replace("-", ""));
    return "multipart/form-data; boundary=" + getBoundary();
  }

  public String getField(String paramString)
  {
    if (this.formData == null)
      return null;
    return this.formData.get(paramString);
  }

  public MultipartCallback getMultipartCallback()
  {
    return this.mCallback;
  }

  void handleLast()
  {
    if (this.last == null)
      return;
    if (this.formData == null)
      this.formData = new RawHeaders();
    this.formData.add(this.lastName, this.last.peekString());
    this.lastName = null;
    this.last = null;
  }

  public int length()
  {
    if (getBoundary() == null)
      setBoundary("----------------------------" + UUID.randomUUID().toString().replace("-", ""));
    Iterator localIterator = this.mParts.iterator();
    Part localPart;
    int j;
    for (int i = 0; localIterator.hasNext(); i = localPart.getRawHeaders().toHeaderString().getBytes().length + j + "\r\n".length() + i)
    {
      localPart = (Part)localIterator.next();
      localPart.getRawHeaders().setStatusLine(getBoundaryStart());
      if (localPart.length() == -1)
        return -1;
      j = localPart.length();
    }
    i = (getBoundaryEnd() + "\r\n").getBytes().length + i;
    this.totalToWrite = i;
    return i;
  }

  protected void onBoundaryEnd()
  {
    super.onBoundaryEnd();
    handleLast();
  }

  protected void onBoundaryStart()
  {
    final RawHeaders localRawHeaders = new RawHeaders();
    this.liner = new LineEmitter();
    this.liner.setLineCallback(new LineEmitter.StringCallback()
    {
      public void onStringAvailable(String paramAnonymousString)
      {
        if (!"\r".equals(paramAnonymousString))
          localRawHeaders.addLine(paramAnonymousString);
        do
        {
          return;
          MultipartFormDataBody.this.handleLast();
          MultipartFormDataBody.this.liner = null;
          MultipartFormDataBody.this.setDataCallback(null);
          paramAnonymousString = new Part(localRawHeaders);
          if (MultipartFormDataBody.this.mCallback != null)
            MultipartFormDataBody.this.mCallback.onPart(paramAnonymousString);
        }
        while (MultipartFormDataBody.this.getDataCallback() != null);
        if (paramAnonymousString.isFile())
        {
          MultipartFormDataBody.this.setDataCallback(new NullDataCallback());
          return;
        }
        MultipartFormDataBody.this.lastName = paramAnonymousString.getName();
        MultipartFormDataBody.this.last = new ByteBufferList();
        MultipartFormDataBody.this.setDataCallback(new DataCallback()
        {
          public void onDataAvailable(DataEmitter paramAnonymous2DataEmitter, ByteBufferList paramAnonymous2ByteBufferList)
          {
            paramAnonymous2ByteBufferList.get(MultipartFormDataBody.this.last);
          }
        });
      }
    });
    setDataCallback(this.liner);
  }

  public void parse(DataEmitter paramDataEmitter, CompletedCallback paramCompletedCallback)
  {
    setDataEmitter(paramDataEmitter);
    setEndCallback(paramCompletedCallback);
  }

  public boolean readFullyOnRequest()
  {
    return false;
  }

  public void setMultipartCallback(MultipartCallback paramMultipartCallback)
  {
    this.mCallback = paramMultipartCallback;
  }

  public void write(AsyncHttpRequest paramAsyncHttpRequest, final DataSink paramDataSink)
  {
    if (this.mParts == null)
    {
      paramDataSink.end();
      return;
    }
    paramAsyncHttpRequest = new Continuation(new CompletedCallback()
    {
      public void onCompleted(Exception paramAnonymousException)
      {
      }
    });
    Iterator localIterator = this.mParts.iterator();
    while (localIterator.hasNext())
    {
      final Part localPart = (Part)localIterator.next();
      paramAsyncHttpRequest.add(new ContinuationCallback()
      {
        public void onContinue(Continuation paramAnonymousContinuation, CompletedCallback paramAnonymousCompletedCallback)
          throws Exception
        {
          localPart.getRawHeaders().setStatusLine(MultipartFormDataBody.this.getBoundaryStart());
          paramAnonymousContinuation = localPart.getRawHeaders().toHeaderString().getBytes();
          Util.writeAll(paramDataSink, paramAnonymousContinuation, paramAnonymousCompletedCallback);
          paramAnonymousCompletedCallback = MultipartFormDataBody.this;
          int i = paramAnonymousCompletedCallback.written;
          paramAnonymousCompletedCallback.written = (paramAnonymousContinuation.length + i);
        }
      }).add(new ContinuationCallback()
      {
        public void onContinue(Continuation paramAnonymousContinuation, CompletedCallback paramAnonymousCompletedCallback)
          throws Exception
        {
          paramAnonymousContinuation = MultipartFormDataBody.this;
          paramAnonymousContinuation.written += localPart.length();
          localPart.write(paramDataSink, paramAnonymousCompletedCallback);
        }
      }).add(new ContinuationCallback()
      {
        public void onContinue(Continuation paramAnonymousContinuation, CompletedCallback paramAnonymousCompletedCallback)
          throws Exception
        {
          paramAnonymousContinuation = "\r\n".getBytes();
          Util.writeAll(paramDataSink, paramAnonymousContinuation, paramAnonymousCompletedCallback);
          paramAnonymousCompletedCallback = MultipartFormDataBody.this;
          int i = paramAnonymousCompletedCallback.written;
          paramAnonymousCompletedCallback.written = (paramAnonymousContinuation.length + i);
        }
      });
    }
    paramAsyncHttpRequest.add(new ContinuationCallback()
    {
      static
      {
        if (!MultipartFormDataBody.class.desiredAssertionStatus());
        for (boolean bool = true; ; bool = false)
        {
          $assertionsDisabled = bool;
          return;
        }
      }

      public void onContinue(Continuation paramAnonymousContinuation, CompletedCallback paramAnonymousCompletedCallback)
        throws Exception
      {
        paramAnonymousContinuation = (MultipartFormDataBody.this.getBoundaryEnd() + "\r\n").getBytes();
        Util.writeAll(paramDataSink, paramAnonymousContinuation, paramAnonymousCompletedCallback);
        paramAnonymousCompletedCallback = MultipartFormDataBody.this;
        int i = paramAnonymousCompletedCallback.written;
        paramAnonymousCompletedCallback.written = (paramAnonymousContinuation.length + i);
        assert (MultipartFormDataBody.this.written == MultipartFormDataBody.this.totalToWrite);
      }
    });
    paramAsyncHttpRequest.start();
  }

  public static abstract interface MultipartCallback
  {
    public abstract void onPart(Part paramPart);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.MultipartFormDataBody
 * JD-Core Version:    0.6.2
 */