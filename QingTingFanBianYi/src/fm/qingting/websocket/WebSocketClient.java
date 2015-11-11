package fm.qingting.websocket;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.BasicNameValuePair;

public class WebSocketClient
{
  private static final String TAG = "WebSocketClient";
  private static TrustManager[] sTrustManagers;
  private List<BasicNameValuePair> mExtraHeaders;
  private Handler mHandler;
  private HandlerThread mHandlerThread;
  private Listener mListener;
  private HybiParser mParser;
  private final Object mSendLock = new Object();
  private Socket mSocket;
  private Thread mThread;
  private URI mURI;

  public WebSocketClient(URI paramURI, Listener paramListener, List<BasicNameValuePair> paramList)
  {
    this.mURI = paramURI;
    this.mListener = paramListener;
    this.mExtraHeaders = paramList;
    this.mParser = new HybiParser(this);
    this.mHandlerThread = new HandlerThread("websocket-thread");
    this.mHandlerThread.start();
    this.mHandler = new Handler(this.mHandlerThread.getLooper());
  }

  private String createSecret()
  {
    byte[] arrayOfByte = new byte[16];
    int i = 0;
    while (i < 16)
    {
      arrayOfByte[i] = ((byte)(int)(Math.random() * 256.0D));
      i += 1;
    }
    return Base64.encodeToString(arrayOfByte, 0).trim();
  }

  private String createSecretValidation(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update((paramString + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes());
      paramString = Base64.encodeToString(localMessageDigest.digest(), 0).trim();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
    }
    throw new RuntimeException(paramString);
  }

  private SSLSocketFactory getSSLSocketFactory()
    throws NoSuchAlgorithmException, KeyManagementException
  {
    SSLContext localSSLContext = SSLContext.getInstance("TLS");
    localSSLContext.init(null, sTrustManagers, null);
    return localSSLContext.getSocketFactory();
  }

  private void log(String paramString)
  {
    Log.e("WebSocketClient", paramString);
  }

  private Header parseHeader(String paramString)
  {
    return BasicLineParser.parseHeader(paramString, new BasicLineParser());
  }

  private StatusLine parseStatusLine(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    return BasicLineParser.parseStatusLine(paramString, new BasicLineParser());
  }

  private String readLine(HybiParser.HappyDataInputStream paramHappyDataInputStream)
    throws IOException
  {
    int i = paramHappyDataInputStream.read();
    if (i == -1)
      return null;
    StringBuilder localStringBuilder = new StringBuilder("");
    while (i != 10)
    {
      if (i != 13)
        localStringBuilder.append((char)i);
      int j = paramHappyDataInputStream.read();
      i = j;
      if (j == -1)
        return null;
    }
    return localStringBuilder.toString();
  }

  public static void setTrustManagers(TrustManager[] paramArrayOfTrustManager)
  {
    sTrustManagers = paramArrayOfTrustManager;
  }

  public void connect()
  {
    if ((this.mThread != null) && (this.mThread.isAlive()))
      return;
    this.mThread = new Thread(new Runnable()
    {
      public void run()
      {
        String str;
        label102: Object localObject3;
        try
        {
          str = WebSocketClient.this.createSecret();
          if (WebSocketClient.this.mURI.getPort() != -1)
          {
            i = WebSocketClient.this.mURI.getPort();
            if (!TextUtils.isEmpty(WebSocketClient.this.mURI.getPath()))
              break label513;
            Object localObject1 = "/";
            if (TextUtils.isEmpty(WebSocketClient.this.mURI.getQuery()))
              break label790;
            localObject1 = (String)localObject1 + "?" + WebSocketClient.this.mURI.getQuery();
            if (!WebSocketClient.this.mURI.getScheme().equals("wss"))
              break label800;
            localObject3 = "https";
            label123: Object localObject4 = new URI((String)localObject3, "//" + WebSocketClient.this.mURI.getHost(), null);
            WebSocketClient.this.log("ready to create msocket");
            if (!WebSocketClient.this.mURI.getScheme().equals("wss"))
              break label527;
            localObject3 = WebSocketClient.this.getSSLSocketFactory();
            WebSocketClient.access$402(WebSocketClient.this, ((SocketFactory)localObject3).createSocket(WebSocketClient.this.mURI.getHost(), i));
            localObject3 = new PrintWriter(WebSocketClient.this.mSocket.getOutputStream());
            ((PrintWriter)localObject3).print("GET " + (String)localObject1 + " HTTP/1.1\r\n");
            ((PrintWriter)localObject3).print("Upgrade: websocket\r\n");
            ((PrintWriter)localObject3).print("Connection: Upgrade\r\n");
            ((PrintWriter)localObject3).print("Host: " + WebSocketClient.this.mURI.getHost() + "\r\n");
            ((PrintWriter)localObject3).print("Origin: " + ((URI)localObject4).toString() + "\r\n");
            ((PrintWriter)localObject3).print("Sec-WebSocket-Key: " + str + "\r\n");
            ((PrintWriter)localObject3).print("Sec-WebSocket-Version: 13\r\n");
            if (WebSocketClient.this.mExtraHeaders == null)
              break label534;
            localObject1 = WebSocketClient.this.mExtraHeaders.iterator();
            while (((Iterator)localObject1).hasNext())
            {
              localObject4 = (NameValuePair)((Iterator)localObject1).next();
              ((PrintWriter)localObject3).print(String.format("%s: %s\r\n", new Object[] { ((NameValuePair)localObject4).getName(), ((NameValuePair)localObject4).getValue() }));
            }
          }
        }
        catch (EOFException localEOFException)
        {
          while (true)
          {
            Log.e("WebSocketClient", "WebSocket EOF!", localEOFException);
            WebSocketClient.this.mListener.onDisconnect(0, "EOF");
            return;
            if (!WebSocketClient.this.mURI.getScheme().equals("wss"))
              break;
            i = 443;
            continue;
            localObject2 = WebSocketClient.this.mURI.getPath();
            continue;
            localObject3 = SocketFactory.getDefault();
          }
          ((PrintWriter)localObject3).print("\r\n");
          ((PrintWriter)localObject3).flush();
          Object localObject2 = new HybiParser.HappyDataInputStream(WebSocketClient.this.mSocket.getInputStream());
          localObject3 = WebSocketClient.this.parseStatusLine(WebSocketClient.access$600(WebSocketClient.this, (HybiParser.HappyDataInputStream)localObject2));
          if (localObject3 == null)
            throw new HttpException("Received no reply from server.");
        }
        catch (SSLException localSSLException)
        {
          Log.e("WebSocketClient", "Websocket SSL error!", localSSLException);
          WebSocketClient.this.mListener.onDisconnect(0, "SSL");
          return;
          if (((StatusLine)localObject3).getStatusCode() != 101)
            throw new HttpResponseException(((StatusLine)localObject3).getStatusCode(), ((StatusLine)localObject3).getReasonPhrase());
        }
        catch (Exception localException)
        {
          label513: label527: label534: WebSocketClient.this.log("mthread.run,catch an exception");
          WebSocketClient.this.mListener.onError(localException);
          return;
        }
        int i = 0;
        while (true)
        {
          localObject3 = WebSocketClient.this.readLine(localException);
          if (!TextUtils.isEmpty((CharSequence)localObject3))
          {
            localObject3 = WebSocketClient.this.parseHeader((String)localObject3);
            if (((Header)localObject3).getName().equals("Sec-WebSocket-Accept"))
              if (!WebSocketClient.this.createSecretValidation(str).equals(((Header)localObject3).getValue().trim()))
                throw new HttpException("Bad Sec-WebSocket-Accept header value.");
          }
          else
          {
            if (i == 0)
              throw new HttpException("No Sec-WebSocket-Accept header.");
            WebSocketClient.this.mListener.onConnect();
            WebSocketClient.this.mParser.start(localException);
            return;
            label790: break label102;
            i = 80;
            break;
            label800: localObject3 = "http";
            break label123;
            i = 1;
          }
        }
      }
    });
    this.mThread.start();
  }

  public void disconnect()
  {
    if (this.mSocket != null)
      this.mHandler.post(new Runnable()
      {
        public void run()
        {
          try
          {
            if (WebSocketClient.this.mSocket != null)
              WebSocketClient.this.mSocket.close();
            WebSocketClient.access$402(WebSocketClient.this, null);
            return;
          }
          catch (IOException localIOException)
          {
            Log.d("WebSocketClient", "Error while disconnecting", localIOException);
            WebSocketClient.this.mListener.onError(localIOException);
          }
        }
      });
  }

  public Listener getListener()
  {
    return this.mListener;
  }

  public void send(String paramString)
  {
    sendFrame(this.mParser.frame(paramString));
  }

  public void send(byte[] paramArrayOfByte)
  {
    sendFrame(this.mParser.frame(paramArrayOfByte));
  }

  void sendFrame(final byte[] paramArrayOfByte)
  {
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        try
        {
          synchronized (WebSocketClient.this.mSendLock)
          {
            if (WebSocketClient.this.mSocket == null)
              throw new IllegalStateException("Socket not connected");
          }
        }
        catch (Exception localException)
        {
          WebSocketClient.this.mListener.onError(localException);
          return;
        }
        OutputStream localOutputStream = WebSocketClient.this.mSocket.getOutputStream();
        localOutputStream.write(paramArrayOfByte);
        localOutputStream.flush();
      }
    });
  }

  public static abstract interface Listener
  {
    public abstract void onConnect();

    public abstract void onDisconnect(int paramInt, String paramString);

    public abstract void onError(Exception paramException);

    public abstract void onMessage(String paramString);

    public abstract void onMessage(byte[] paramArrayOfByte);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.websocket.WebSocketClient
 * JD-Core Version:    0.6.2
 */