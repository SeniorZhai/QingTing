package fm.qingting.async.http.server;

import android.content.Context;
import fm.qingting.async.AsyncSSLSocketWrapper;
import fm.qingting.async.AsyncServer;
import fm.qingting.async.AsyncServerSocket;
import fm.qingting.async.AsyncSocket;
import fm.qingting.async.Util;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.ListenCallback;
import fm.qingting.async.http.AsyncHttpRequestBody;
import fm.qingting.async.http.Multimap;
import fm.qingting.async.http.WebSocket;
import fm.qingting.async.http.WebSocketImpl;
import fm.qingting.async.http.libcore.RawHeaders;
import fm.qingting.async.http.libcore.RequestHeaders;
import fm.qingting.async.http.libcore.ResponseHeaders;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.net.ssl.SSLContext;

public class AsyncHttpServer
{
  private static Hashtable<Integer, String> mCodes;
  static Hashtable<String, String> mContentTypes;
  Hashtable<String, ArrayList<Pair>> mActions = new Hashtable();
  CompletedCallback mCompletedCallback;
  ListenCallback mListenCallback = new ListenCallback()
  {
    public void onAccepted(final AsyncSocket paramAnonymousAsyncSocket)
    {
      new AsyncHttpServerRequestImpl()
      {
        String fullPath;
        boolean hasContinued;
        AsyncHttpServer.Pair match;
        String path;
        boolean requestComplete;
        AsyncHttpServerResponseImpl res;
        boolean responseComplete;

        private void handleOnCompleted()
        {
          if ((this.requestComplete) && (this.responseComplete))
            AsyncHttpServer.1.this.onAccepted(paramAnonymousAsyncSocket);
        }

        public String getPath()
        {
          return this.path;
        }

        public Multimap getQuery()
        {
          String[] arrayOfString = this.fullPath.split("\\?", 2);
          if (arrayOfString.length < 2)
            return new Multimap();
          return Multimap.parseQuery(arrayOfString[1]);
        }

        public void onCompleted(Exception paramAnonymous2Exception)
        {
          if (this.res.getHeaders().getHeaders().getResponseCode() == 101);
          do
          {
            return;
            this.requestComplete = true;
            super.onCompleted(paramAnonymous2Exception);
            this.mSocket.setDataCallback(null);
            this.mSocket.pause();
            handleOnCompleted();
          }
          while (!getBody().readFullyOnRequest());
          this.match.callback.onRequest(this, this.res);
        }

        protected void onHeadersReceived()
        {
          ??? = getRawHeaders();
          if ((!this.hasContinued) && ("100-continue".equals(((RawHeaders)???).get("Expect"))))
          {
            pause();
            Util.writeAll(this.mSocket, "HTTP/1.1 100 Continue\r\n".getBytes(), new CompletedCallback()
            {
              public void onCompleted(Exception paramAnonymous3Exception)
              {
                AsyncHttpServer.1.1.this.resume();
                if (paramAnonymous3Exception != null)
                {
                  AsyncHttpServer.1.1.this.report(paramAnonymous3Exception);
                  return;
                }
                AsyncHttpServer.1.1.this.hasContinued = true;
                AsyncHttpServer.1.1.this.onHeadersReceived();
              }
            });
          }
          do
          {
            return;
            ??? = ((RawHeaders)???).getStatusLine().split(" ");
            this.fullPath = ???[1];
            this.path = this.fullPath.split("\\?")[0];
            this.method = ???[0];
            synchronized (AsyncHttpServer.this.mActions)
            {
              Object localObject2 = (ArrayList)AsyncHttpServer.this.mActions.get(this.method);
              if (localObject2 != null)
              {
                localObject2 = ((ArrayList)localObject2).iterator();
                while (((Iterator)localObject2).hasNext())
                {
                  AsyncHttpServer.Pair localPair = (AsyncHttpServer.Pair)((Iterator)localObject2).next();
                  Matcher localMatcher = localPair.regex.matcher(this.path);
                  if (localMatcher.matches())
                  {
                    this.mMatcher = localMatcher;
                    this.match = localPair;
                  }
                }
              }
              this.res = new AsyncHttpServerResponseImpl(paramAnonymousAsyncSocket, this)
              {
                protected void onEnd()
                {
                  this.mSocket.setEndCallback(null);
                  AsyncHttpServer.1.1.this.responseComplete = true;
                  AsyncHttpServer.1.1.this.handleOnCompleted();
                }
              };
              AsyncHttpServer.this.onRequest(this, this.res);
              if (this.match == null)
              {
                this.res.responseCode(404);
                this.res.end();
                return;
              }
            }
            if (!getBody().readFullyOnRequest())
            {
              this.match.callback.onRequest(this, this.res);
              return;
            }
          }
          while (!this.requestComplete);
          this.match.callback.onRequest(this, this.res);
        }
      }
      .setSocket(paramAnonymousAsyncSocket);
      paramAnonymousAsyncSocket.resume();
    }

    public void onCompleted(Exception paramAnonymousException)
    {
      AsyncHttpServer.this.report(paramAnonymousException);
    }

    public void onListening(AsyncServerSocket paramAnonymousAsyncServerSocket)
    {
      AsyncHttpServer.this.mListeners.add(paramAnonymousAsyncServerSocket);
    }
  };
  ArrayList<AsyncServerSocket> mListeners = new ArrayList();

  static
  {
    if (!AsyncHttpServer.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      mContentTypes = new Hashtable();
      mCodes = new Hashtable();
      mCodes.put(Integer.valueOf(200), "OK");
      mCodes.put(Integer.valueOf(101), "Switching Protocols");
      mCodes.put(Integer.valueOf(404), "Not Found");
      return;
    }
  }

  public AsyncHttpServer()
  {
    mContentTypes.put("js", "application/javascript");
    mContentTypes.put("json", "application/json");
    mContentTypes.put("png", "image/png");
    mContentTypes.put("jpg", "image/jpeg");
    mContentTypes.put("html", "text/html");
    mContentTypes.put("css", "text/css");
    mContentTypes.put("mp4", "video/mp4");
  }

  public static InputStream getAssetStream(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageResourcePath();
    paramContext = "assets/" + paramString;
    try
    {
      paramString = new ZipFile((String)localObject);
      localObject = paramString.entries();
      while (((Enumeration)localObject).hasMoreElements())
      {
        ZipEntry localZipEntry = (ZipEntry)((Enumeration)localObject).nextElement();
        if (localZipEntry.getName().equals(paramContext))
        {
          paramContext = paramString.getInputStream(localZipEntry);
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
    }
    return null;
  }

  public static String getContentType(String paramString)
  {
    int i = paramString.lastIndexOf(".");
    if (i != -1)
    {
      paramString = paramString.substring(i + 1);
      paramString = (String)mContentTypes.get(paramString);
      if (paramString != null)
        return paramString;
    }
    return "text/plain";
  }

  public static String getResponseCodeDescription(int paramInt)
  {
    String str2 = (String)mCodes.get(Integer.valueOf(paramInt));
    String str1 = str2;
    if (str2 == null)
      str1 = "Unknown";
    return str1;
  }

  private void report(Exception paramException)
  {
    if (this.mCompletedCallback != null)
      this.mCompletedCallback.onCompleted(paramException);
  }

  public void addAction(String paramString1, String paramString2, HttpServerRequestCallback paramHttpServerRequestCallback)
  {
    Pair localPair = new Pair(null);
    localPair.regex = Pattern.compile("^" + paramString2);
    localPair.callback = paramHttpServerRequestCallback;
    synchronized (this.mActions)
    {
      paramHttpServerRequestCallback = (ArrayList)this.mActions.get(paramString1);
      paramString2 = paramHttpServerRequestCallback;
      if (paramHttpServerRequestCallback == null)
      {
        paramString2 = new ArrayList();
        this.mActions.put(paramString1, paramString2);
      }
      paramString2.add(localPair);
      return;
    }
  }

  public void directory(Context paramContext, String paramString1, final String paramString2)
  {
    addAction("GET", paramString1, new HttpServerRequestCallback()
    {
      public void onRequest(AsyncHttpServerRequest paramAnonymousAsyncHttpServerRequest, final AsyncHttpServerResponse paramAnonymousAsyncHttpServerResponse)
      {
        paramAnonymousAsyncHttpServerRequest = paramAnonymousAsyncHttpServerRequest.getMatcher().replaceAll("");
        InputStream localInputStream = AsyncHttpServer.getAssetStream(this.val$context, paramString2 + paramAnonymousAsyncHttpServerRequest);
        if (localInputStream == null)
        {
          paramAnonymousAsyncHttpServerResponse.responseCode(404);
          paramAnonymousAsyncHttpServerResponse.end();
          return;
        }
        paramAnonymousAsyncHttpServerResponse.responseCode(200);
        paramAnonymousAsyncHttpServerResponse.getHeaders().getHeaders().add("Content-Type", AsyncHttpServer.getContentType(paramAnonymousAsyncHttpServerRequest));
        Util.pump(localInputStream, paramAnonymousAsyncHttpServerResponse, new CompletedCallback()
        {
          public void onCompleted(Exception paramAnonymous2Exception)
          {
            paramAnonymousAsyncHttpServerResponse.end();
          }
        });
      }
    });
  }

  public void directory(String paramString, File paramFile)
  {
    directory(paramString, paramFile, false);
  }

  public void directory(String paramString, final File paramFile, final boolean paramBoolean)
  {
    assert (paramFile.isDirectory());
    addAction("GET", paramString, new HttpServerRequestCallback()
    {
      public void onRequest(AsyncHttpServerRequest paramAnonymousAsyncHttpServerRequest, final AsyncHttpServerResponse paramAnonymousAsyncHttpServerResponse)
      {
        paramAnonymousAsyncHttpServerRequest = paramAnonymousAsyncHttpServerRequest.getMatcher().replaceAll("");
        Object localObject1 = new File(paramFile, paramAnonymousAsyncHttpServerRequest);
        if ((((File)localObject1).isDirectory()) && (paramBoolean))
        {
          paramAnonymousAsyncHttpServerRequest = new ArrayList();
          paramAnonymousAsyncHttpServerResponse = new ArrayList();
          localObject1 = ((File)localObject1).listFiles();
          int j = localObject1.length;
          int i = 0;
          if (i < j)
          {
            Object localObject2 = localObject1[i];
            if (localObject2.isDirectory())
              paramAnonymousAsyncHttpServerRequest.add(localObject2);
            while (true)
            {
              i += 1;
              break;
              paramAnonymousAsyncHttpServerResponse.add(localObject2);
            }
          }
          localObject1 = new Comparator()
          {
            public int compare(File paramAnonymous2File1, File paramAnonymous2File2)
            {
              return paramAnonymous2File1.getName().compareTo(paramAnonymous2File2.getName());
            }
          };
          Collections.sort(paramAnonymousAsyncHttpServerRequest, (Comparator)localObject1);
          Collections.sort(paramAnonymousAsyncHttpServerResponse, (Comparator)localObject1);
          paramAnonymousAsyncHttpServerResponse.addAll(0, paramAnonymousAsyncHttpServerRequest);
          return;
        }
        if (!((File)localObject1).isFile())
        {
          paramAnonymousAsyncHttpServerResponse.responseCode(404);
          paramAnonymousAsyncHttpServerResponse.end();
          return;
        }
        try
        {
          paramAnonymousAsyncHttpServerRequest = new FileInputStream((File)localObject1);
          paramAnonymousAsyncHttpServerResponse.responseCode(200);
          Util.pump(paramAnonymousAsyncHttpServerRequest, paramAnonymousAsyncHttpServerResponse, new CompletedCallback()
          {
            public void onCompleted(Exception paramAnonymous2Exception)
            {
              paramAnonymousAsyncHttpServerResponse.end();
            }
          });
          return;
        }
        catch (Exception paramAnonymousAsyncHttpServerRequest)
        {
          paramAnonymousAsyncHttpServerResponse.responseCode(404);
          paramAnonymousAsyncHttpServerResponse.end();
        }
      }
    });
  }

  public void get(String paramString, HttpServerRequestCallback paramHttpServerRequestCallback)
  {
    addAction("GET", paramString, paramHttpServerRequestCallback);
  }

  public CompletedCallback getErrorCallback()
  {
    return this.mCompletedCallback;
  }

  public ListenCallback getListenCallback()
  {
    return this.mListenCallback;
  }

  public void listen(int paramInt)
  {
    listen(AsyncServer.getDefault(), paramInt);
  }

  public void listen(AsyncServer paramAsyncServer, int paramInt)
  {
    paramAsyncServer.listen(null, paramInt, this.mListenCallback);
  }

  public void listenSecure(final int paramInt, final SSLContext paramSSLContext)
  {
    AsyncServer.getDefault().listen(null, paramInt, new ListenCallback()
    {
      public void onAccepted(AsyncSocket paramAnonymousAsyncSocket)
      {
        paramAnonymousAsyncSocket = new AsyncSSLSocketWrapper(paramAnonymousAsyncSocket, null, paramInt, paramSSLContext, null, false);
        AsyncHttpServer.this.mListenCallback.onAccepted(paramAnonymousAsyncSocket);
      }

      public void onCompleted(Exception paramAnonymousException)
      {
        AsyncHttpServer.this.mListenCallback.onCompleted(paramAnonymousException);
      }

      public void onListening(AsyncServerSocket paramAnonymousAsyncServerSocket)
      {
        AsyncHttpServer.this.mListenCallback.onListening(paramAnonymousAsyncServerSocket);
      }
    });
  }

  protected void onRequest(AsyncHttpServerRequest paramAsyncHttpServerRequest, AsyncHttpServerResponse paramAsyncHttpServerResponse)
  {
  }

  public void post(String paramString, HttpServerRequestCallback paramHttpServerRequestCallback)
  {
    addAction("POST", paramString, paramHttpServerRequestCallback);
  }

  public void setErrorCallback(CompletedCallback paramCompletedCallback)
  {
    this.mCompletedCallback = paramCompletedCallback;
  }

  public void stop()
  {
    if (this.mListeners != null)
    {
      Iterator localIterator = this.mListeners.iterator();
      while (localIterator.hasNext())
        ((AsyncServerSocket)localIterator.next()).stop();
    }
  }

  public void websocket(String paramString, final WebSocketRequestCallback paramWebSocketRequestCallback)
  {
    get(paramString, new HttpServerRequestCallback()
    {
      public void onRequest(AsyncHttpServerRequest paramAnonymousAsyncHttpServerRequest, AsyncHttpServerResponse paramAnonymousAsyncHttpServerResponse)
      {
        int k = 0;
        Object localObject = paramAnonymousAsyncHttpServerRequest.getHeaders().getHeaders().get("Connection");
        int j = k;
        int m;
        int i;
        if (localObject != null)
        {
          localObject = ((String)localObject).split(",");
          m = localObject.length;
          i = 0;
        }
        while (true)
        {
          j = k;
          if (i < m)
          {
            if ("Upgrade".equalsIgnoreCase(localObject[i].trim()))
              j = 1;
          }
          else
          {
            if (("websocket".equals(paramAnonymousAsyncHttpServerRequest.getHeaders().getHeaders().get("Upgrade"))) && (j != 0))
              break;
            paramAnonymousAsyncHttpServerResponse.responseCode(404);
            paramAnonymousAsyncHttpServerResponse.end();
            return;
          }
          i += 1;
        }
        paramWebSocketRequestCallback.onConnected(new WebSocketImpl(paramAnonymousAsyncHttpServerRequest, paramAnonymousAsyncHttpServerResponse), paramAnonymousAsyncHttpServerRequest.getHeaders());
      }
    });
  }

  private static class Pair
  {
    HttpServerRequestCallback callback;
    Pattern regex;
  }

  public static abstract interface WebSocketRequestCallback
  {
    public abstract void onConnected(WebSocket paramWebSocket, RequestHeaders paramRequestHeaders);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.server.AsyncHttpServer
 * JD-Core Version:    0.6.2
 */