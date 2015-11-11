package com.alipay.sdk.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.File;
import java.lang.ref.WeakReference;
import javax.net.ssl.SSLException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public final class FileDownloader
{
  public boolean a;
  private String b;
  private String c;
  private String d;
  private IDownloadProgress e;
  private FileFetch f;

  public FileDownloader()
  {
    this.a = false;
  }

  private FileDownloader(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }

  private void a(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }

  private static HttpEntity c(String paramString)
    throws Exception
  {
    try
    {
      paramString = new HttpGet(paramString);
      paramString = new DefaultHttpClient().execute(paramString);
      int i = paramString.getStatusLine().getStatusCode();
      if (i == 200)
        return paramString.getEntity();
      throw new Exception("net work exception,ErrorCode :" + i);
    }
    catch (SSLException paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    catch (Exception paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
  }

  private long e()
  {
    try
    {
      long l = c(this.b).getContentLength();
      return l;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -1L;
  }

  private void f()
  {
    File localFile = new File(this.c);
    if (localFile.exists())
      localFile.delete();
    localFile = new File(this.d);
    if (localFile.exists())
      localFile.delete();
  }

  // ERROR //
  private void g()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 118	java/io/FileInputStream
    //   5: dup
    //   6: aload_0
    //   7: getfield 107	com/alipay/sdk/util/FileDownloader:d	Ljava/lang/String;
    //   10: invokespecial 119	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   13: astore_3
    //   14: new 121	java/io/ObjectInputStream
    //   17: dup
    //   18: aload_3
    //   19: invokespecial 124	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   22: astore_1
    //   23: aload_0
    //   24: getfield 31	com/alipay/sdk/util/FileDownloader:f	Lcom/alipay/sdk/util/FileFetch;
    //   27: aload_1
    //   28: invokevirtual 127	java/io/ObjectInputStream:readLong	()J
    //   31: invokevirtual 132	com/alipay/sdk/util/FileFetch:a	(J)V
    //   34: aload_0
    //   35: getfield 31	com/alipay/sdk/util/FileDownloader:f	Lcom/alipay/sdk/util/FileFetch;
    //   38: aload_1
    //   39: invokevirtual 127	java/io/ObjectInputStream:readLong	()J
    //   42: invokevirtual 134	com/alipay/sdk/util/FileFetch:b	(J)V
    //   45: aload_3
    //   46: invokevirtual 137	java/io/FileInputStream:close	()V
    //   49: aload_1
    //   50: invokevirtual 138	java/io/ObjectInputStream:close	()V
    //   53: return
    //   54: astore_3
    //   55: aconst_null
    //   56: astore_1
    //   57: aload_3
    //   58: invokevirtual 93	java/lang/Exception:printStackTrace	()V
    //   61: aload_2
    //   62: invokevirtual 137	java/io/FileInputStream:close	()V
    //   65: aload_1
    //   66: invokevirtual 138	java/io/ObjectInputStream:close	()V
    //   69: return
    //   70: astore_1
    //   71: return
    //   72: astore_2
    //   73: aconst_null
    //   74: astore_1
    //   75: aconst_null
    //   76: astore_3
    //   77: aload_3
    //   78: invokevirtual 137	java/io/FileInputStream:close	()V
    //   81: aload_1
    //   82: invokevirtual 138	java/io/ObjectInputStream:close	()V
    //   85: aload_2
    //   86: athrow
    //   87: astore_2
    //   88: goto -39 -> 49
    //   91: astore_1
    //   92: return
    //   93: astore_2
    //   94: goto -29 -> 65
    //   97: astore_3
    //   98: goto -17 -> 81
    //   101: astore_1
    //   102: goto -17 -> 85
    //   105: astore_2
    //   106: aconst_null
    //   107: astore_1
    //   108: goto -31 -> 77
    //   111: astore_2
    //   112: goto -35 -> 77
    //   115: astore 4
    //   117: aload_2
    //   118: astore_3
    //   119: aload 4
    //   121: astore_2
    //   122: goto -45 -> 77
    //   125: astore 4
    //   127: aconst_null
    //   128: astore_1
    //   129: aload_3
    //   130: astore_2
    //   131: aload 4
    //   133: astore_3
    //   134: goto -77 -> 57
    //   137: astore 4
    //   139: aload_3
    //   140: astore_2
    //   141: aload 4
    //   143: astore_3
    //   144: goto -87 -> 57
    //
    // Exception table:
    //   from	to	target	type
    //   2	14	54	java/lang/Exception
    //   65	69	70	java/lang/Exception
    //   2	14	72	finally
    //   45	49	87	java/lang/Exception
    //   49	53	91	java/lang/Exception
    //   61	65	93	java/lang/Exception
    //   77	81	97	java/lang/Exception
    //   81	85	101	java/lang/Exception
    //   14	23	105	finally
    //   23	45	111	finally
    //   57	61	115	finally
    //   14	23	125	java/lang/Exception
    //   23	45	137	java/lang/Exception
  }

  public final void a(IDownloadProgress paramIDownloadProgress)
  {
    if (paramIDownloadProgress != null)
      this.e = paramIDownloadProgress;
  }

  public final void a(String paramString)
  {
    this.b = paramString;
  }

  protected final boolean a()
  {
    return this.a;
  }

  public final void b()
  {
    new Thread(new FileDownloader.1(this, new ProgressOutput(Looper.getMainLooper(), this, (byte)0))).start();
  }

  public final void b(String paramString)
  {
    this.c = paramString;
    this.d = (paramString + ".tmp");
  }

  public final void c()
  {
    this.f.d();
  }

  // ERROR //
  protected final void d()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 176	java/io/FileOutputStream
    //   5: dup
    //   6: aload_0
    //   7: getfield 107	com/alipay/sdk/util/FileDownloader:d	Ljava/lang/String;
    //   10: invokespecial 177	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   13: astore_3
    //   14: new 179	java/io/ObjectOutputStream
    //   17: dup
    //   18: aload_3
    //   19: invokespecial 182	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   22: astore_1
    //   23: aload_1
    //   24: aload_0
    //   25: getfield 31	com/alipay/sdk/util/FileDownloader:f	Lcom/alipay/sdk/util/FileFetch;
    //   28: invokevirtual 184	com/alipay/sdk/util/FileFetch:a	()J
    //   31: invokevirtual 187	java/io/ObjectOutputStream:writeLong	(J)V
    //   34: aload_1
    //   35: aload_0
    //   36: getfield 31	com/alipay/sdk/util/FileDownloader:f	Lcom/alipay/sdk/util/FileFetch;
    //   39: invokevirtual 189	com/alipay/sdk/util/FileFetch:b	()J
    //   42: invokevirtual 187	java/io/ObjectOutputStream:writeLong	(J)V
    //   45: aload_1
    //   46: invokevirtual 192	java/io/ObjectOutputStream:flush	()V
    //   49: aload_3
    //   50: invokevirtual 193	java/io/FileOutputStream:close	()V
    //   53: aload_1
    //   54: invokevirtual 194	java/io/ObjectOutputStream:close	()V
    //   57: return
    //   58: astore_3
    //   59: aconst_null
    //   60: astore_1
    //   61: aload_3
    //   62: invokevirtual 93	java/lang/Exception:printStackTrace	()V
    //   65: aload_2
    //   66: invokevirtual 193	java/io/FileOutputStream:close	()V
    //   69: aload_1
    //   70: invokevirtual 194	java/io/ObjectOutputStream:close	()V
    //   73: return
    //   74: astore_1
    //   75: return
    //   76: astore_2
    //   77: aconst_null
    //   78: astore_1
    //   79: aconst_null
    //   80: astore_3
    //   81: aload_3
    //   82: invokevirtual 193	java/io/FileOutputStream:close	()V
    //   85: aload_1
    //   86: invokevirtual 194	java/io/ObjectOutputStream:close	()V
    //   89: aload_2
    //   90: athrow
    //   91: astore_2
    //   92: goto -39 -> 53
    //   95: astore_1
    //   96: return
    //   97: astore_2
    //   98: goto -29 -> 69
    //   101: astore_3
    //   102: goto -17 -> 85
    //   105: astore_1
    //   106: goto -17 -> 89
    //   109: astore_2
    //   110: aconst_null
    //   111: astore_1
    //   112: goto -31 -> 81
    //   115: astore_2
    //   116: goto -35 -> 81
    //   119: astore 4
    //   121: aload_2
    //   122: astore_3
    //   123: aload 4
    //   125: astore_2
    //   126: goto -45 -> 81
    //   129: astore 4
    //   131: aconst_null
    //   132: astore_1
    //   133: aload_3
    //   134: astore_2
    //   135: aload 4
    //   137: astore_3
    //   138: goto -77 -> 61
    //   141: astore 4
    //   143: aload_3
    //   144: astore_2
    //   145: aload 4
    //   147: astore_3
    //   148: goto -87 -> 61
    //
    // Exception table:
    //   from	to	target	type
    //   2	14	58	java/lang/Exception
    //   69	73	74	java/lang/Exception
    //   2	14	76	finally
    //   49	53	91	java/lang/Exception
    //   53	57	95	java/lang/Exception
    //   65	69	97	java/lang/Exception
    //   81	85	101	java/lang/Exception
    //   85	89	105	java/lang/Exception
    //   14	23	109	finally
    //   23	49	115	finally
    //   61	65	119	finally
    //   14	23	129	java/lang/Exception
    //   23	49	141	java/lang/Exception
  }

  public static abstract interface IDownloadProgress
  {
    public abstract void a();

    public abstract void b();

    public abstract void c();
  }

  private static class ProgressOutput extends Handler
  {
    WeakReference a;
    private boolean b = false;

    private ProgressOutput(Looper paramLooper, FileDownloader paramFileDownloader)
    {
      super();
      this.a = new WeakReference(paramFileDownloader);
    }

    public void handleMessage(Message paramMessage)
    {
      if (FileDownloader.g((FileDownloader)this.a.get()) == null);
      do
      {
        return;
        float f = 50.0F;
        while (true)
        {
          try
          {
            if (((FileDownloader)this.a.get()).a)
            {
              f = (float)(100L * FileDownloader.f((FileDownloader)this.a.get()).a() / FileDownloader.f((FileDownloader)this.a.get()).b());
              if (!FileDownloader.f((FileDownloader)this.a.get()).c())
                break label242;
              if ((f != 100.0F) || (this.b))
                break;
              FileDownloader.g((FileDownloader)this.a.get()).a();
              this.b = true;
              return;
            }
          }
          catch (Exception paramMessage)
          {
            FileDownloader.g((FileDownloader)this.a.get()).c();
            return;
          }
          if (FileDownloader.f((FileDownloader)this.a.get()).c())
            f = 100.0F;
        }
        if (f > 100.0F)
        {
          FileDownloader.d((FileDownloader)this.a.get());
          FileDownloader.g((FileDownloader)this.a.get()).c();
          return;
        }
      }
      while (this.b);
      FileDownloader.g((FileDownloader)this.a.get()).c();
      return;
      label242: FileDownloader.g((FileDownloader)this.a.get()).b();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.util.FileDownloader
 * JD-Core Version:    0.6.2
 */