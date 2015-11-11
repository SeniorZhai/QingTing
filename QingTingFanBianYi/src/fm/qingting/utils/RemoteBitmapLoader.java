package fm.qingting.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class RemoteBitmapLoader extends AsyncTask
{
  private IBitmapRecvListener mListener;
  private String mUrl;

  public RemoteBitmapLoader(String paramString)
  {
    this.mUrl = paramString;
  }

  private void onHandleRecvBitmap(Bitmap paramBitmap)
  {
    if ((paramBitmap != null) && (this.mListener != null))
      this.mListener.onRecvBitmap(this.mUrl, paramBitmap);
  }

  // ERROR //
  protected Object doInBackground(Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload 4
    //   5: astore_2
    //   6: aload_0
    //   7: getfield 18	fm/qingting/utils/RemoteBitmapLoader:mUrl	Ljava/lang/String;
    //   10: ifnull +52 -> 62
    //   13: aload 4
    //   15: astore_2
    //   16: aload_0
    //   17: getfield 18	fm/qingting/utils/RemoteBitmapLoader:mUrl	Ljava/lang/String;
    //   20: ldc 37
    //   22: invokevirtual 43	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   25: ifne +37 -> 62
    //   28: new 45	java/net/URL
    //   31: dup
    //   32: aload_0
    //   33: getfield 18	fm/qingting/utils/RemoteBitmapLoader:mUrl	Ljava/lang/String;
    //   36: invokespecial 47	java/net/URL:<init>	(Ljava/lang/String;)V
    //   39: invokevirtual 51	java/net/URL:openStream	()Ljava/io/InputStream;
    //   42: astore_1
    //   43: aload_1
    //   44: astore_2
    //   45: aload_1
    //   46: invokestatic 57	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   49: astore_3
    //   50: aload_3
    //   51: astore_2
    //   52: aload_1
    //   53: ifnull +9 -> 62
    //   56: aload_1
    //   57: invokevirtual 62	java/io/InputStream:close	()V
    //   60: aload_3
    //   61: astore_2
    //   62: aload_2
    //   63: areturn
    //   64: astore_1
    //   65: aload_1
    //   66: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   69: aload_3
    //   70: areturn
    //   71: astore_3
    //   72: aconst_null
    //   73: astore_1
    //   74: aload_1
    //   75: astore_2
    //   76: aload_3
    //   77: invokevirtual 66	java/lang/Exception:printStackTrace	()V
    //   80: aload 4
    //   82: astore_2
    //   83: aload_1
    //   84: ifnull -22 -> 62
    //   87: aload_1
    //   88: invokevirtual 62	java/io/InputStream:close	()V
    //   91: aconst_null
    //   92: areturn
    //   93: astore_1
    //   94: aload_1
    //   95: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   98: aconst_null
    //   99: areturn
    //   100: astore_1
    //   101: aconst_null
    //   102: astore_1
    //   103: aload 4
    //   105: astore_2
    //   106: aload_1
    //   107: ifnull -45 -> 62
    //   110: aload_1
    //   111: invokevirtual 62	java/io/InputStream:close	()V
    //   114: aconst_null
    //   115: areturn
    //   116: astore_1
    //   117: aload_1
    //   118: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   121: aconst_null
    //   122: areturn
    //   123: astore_1
    //   124: aconst_null
    //   125: astore_2
    //   126: aload_2
    //   127: ifnull +7 -> 134
    //   130: aload_2
    //   131: invokevirtual 62	java/io/InputStream:close	()V
    //   134: aload_1
    //   135: athrow
    //   136: astore_2
    //   137: aload_2
    //   138: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   141: goto -7 -> 134
    //   144: astore_1
    //   145: goto -19 -> 126
    //   148: astore_2
    //   149: goto -46 -> 103
    //   152: astore_3
    //   153: goto -79 -> 74
    //
    // Exception table:
    //   from	to	target	type
    //   56	60	64	java/io/IOException
    //   28	43	71	java/lang/Exception
    //   87	91	93	java/io/IOException
    //   28	43	100	java/lang/Error
    //   110	114	116	java/io/IOException
    //   28	43	123	finally
    //   130	134	136	java/io/IOException
    //   45	50	144	finally
    //   76	80	144	finally
    //   45	50	148	java/lang/Error
    //   45	50	152	java/lang/Exception
  }

  protected void onPostExecute(Object paramObject)
  {
    if (paramObject != null)
      onHandleRecvBitmap((Bitmap)paramObject);
  }

  public void setBitmapListener(IBitmapRecvListener paramIBitmapRecvListener)
  {
    this.mListener = paramIBitmapRecvListener;
  }

  public static abstract interface IBitmapRecvListener
  {
    public abstract void onRecvBitmap(String paramString, Bitmap paramBitmap);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.RemoteBitmapLoader
 * JD-Core Version:    0.6.2
 */