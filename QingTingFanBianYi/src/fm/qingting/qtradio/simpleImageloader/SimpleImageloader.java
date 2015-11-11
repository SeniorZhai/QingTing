package fm.qingting.qtradio.simpleImageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.net.URL;

public class SimpleImageloader extends AsyncTask
{
  private static final int EndLoadingimage = 2;
  private static final int Init = 0;
  private static final int StartLoadimage = 1;
  private boolean isLoading = false;
  private String mAdUrl = null;
  private IRecvBitmapEventListener mBitmapListener;
  private Bitmap mImage;
  private String mImageUrl;

  private void log(String paramString)
  {
  }

  private boolean onHandleRecvBitmap(Bitmap paramBitmap)
  {
    if ((paramBitmap != null) && (this.mBitmapListener != null))
      this.mBitmapListener.onRecvBitmap(paramBitmap);
    return false;
  }

  // ERROR //
  private void restoreAdvertisementPic(Bitmap paramBitmap, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +134 -> 135
    //   4: aload_2
    //   5: ifnull +130 -> 135
    //   8: aload_0
    //   9: getfield 31	fm/qingting/qtradio/simpleImageloader/SimpleImageloader:mAdUrl	Ljava/lang/String;
    //   12: ifnull +123 -> 135
    //   15: aload_0
    //   16: getfield 31	fm/qingting/qtradio/simpleImageloader/SimpleImageloader:mAdUrl	Ljava/lang/String;
    //   19: aload_2
    //   20: invokevirtual 55	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   23: ifeq +112 -> 135
    //   26: invokestatic 61	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   29: invokevirtual 65	fm/qingting/qtradio/model/InfoManager:getContext	()Landroid/content/Context;
    //   32: invokevirtual 70	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   35: invokevirtual 74	android/content/Context:getFilesDir	()Ljava/io/File;
    //   38: invokevirtual 80	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   41: astore_2
    //   42: new 82	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 83	java/lang/StringBuilder:<init>	()V
    //   49: aload_2
    //   50: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: ldc 89
    //   55: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   61: astore_2
    //   62: new 76	java/io/File
    //   65: dup
    //   66: aload_2
    //   67: invokespecial 94	java/io/File:<init>	(Ljava/lang/String;)V
    //   70: astore_3
    //   71: aload_3
    //   72: invokevirtual 98	java/io/File:createNewFile	()Z
    //   75: pop
    //   76: new 100	java/io/FileOutputStream
    //   79: dup
    //   80: aload_3
    //   81: invokespecial 103	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   84: astore_3
    //   85: aload_1
    //   86: getstatic 109	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   89: bipush 50
    //   91: aload_3
    //   92: invokevirtual 115	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   95: pop
    //   96: aload_3
    //   97: invokevirtual 118	java/io/FileOutputStream:flush	()V
    //   100: aload_3
    //   101: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   104: invokestatic 126	fm/qingting/qtradio/model/SharedCfg:getInstance	()Lfm/qingting/qtradio/model/SharedCfg;
    //   107: aload_2
    //   108: invokevirtual 129	fm/qingting/qtradio/model/SharedCfg:setLocalAdvertisementPic	(Ljava/lang/String;)V
    //   111: return
    //   112: astore_1
    //   113: aload_1
    //   114: invokevirtual 132	java/io/IOException:printStackTrace	()V
    //   117: return
    //   118: astore_1
    //   119: aload_1
    //   120: invokevirtual 133	java/io/FileNotFoundException:printStackTrace	()V
    //   123: return
    //   124: astore_1
    //   125: aload_1
    //   126: invokevirtual 132	java/io/IOException:printStackTrace	()V
    //   129: return
    //   130: astore_1
    //   131: aload_1
    //   132: invokevirtual 132	java/io/IOException:printStackTrace	()V
    //   135: return
    //   136: astore_1
    //   137: return
    //
    // Exception table:
    //   from	to	target	type
    //   71	76	112	java/io/IOException
    //   76	85	118	java/io/FileNotFoundException
    //   96	100	124	java/io/IOException
    //   100	104	130	java/io/IOException
    //   62	71	136	java/lang/Exception
    //   71	76	136	java/lang/Exception
    //   76	85	136	java/lang/Exception
    //   85	96	136	java/lang/Exception
    //   96	100	136	java/lang/Exception
    //   100	104	136	java/lang/Exception
    //   104	111	136	java/lang/Exception
    //   113	117	136	java/lang/Exception
    //   119	123	136	java/lang/Exception
    //   125	129	136	java/lang/Exception
    //   131	135	136	java/lang/Exception
  }

  protected Object doInBackground(Object[] paramArrayOfObject)
  {
    paramArrayOfObject = (String)paramArrayOfObject[0];
    if ((paramArrayOfObject == null) || (paramArrayOfObject.equalsIgnoreCase("")))
      return null;
    try
    {
      this.mImageUrl = paramArrayOfObject;
      this.mImage = BitmapFactory.decodeStream(new URL(paramArrayOfObject).openStream());
      restoreAdvertisementPic(this.mImage, paramArrayOfObject);
      return this.mImage;
    }
    catch (Exception paramArrayOfObject)
    {
      while (true)
      {
        paramArrayOfObject.printStackTrace();
        log("catch an exception");
      }
    }
  }

  public boolean isLastImageUrl(String paramString)
  {
    return (paramString != null) && (this.mImageUrl != null) && (this.mImageUrl.equalsIgnoreCase(paramString));
  }

  public boolean isLoading()
  {
    return this.isLoading;
  }

  protected void onPostExecute(Object paramObject)
  {
    if (paramObject != null)
    {
      log("onpostexecute,handle result");
      this.isLoading = false;
      onHandleRecvBitmap((Bitmap)paramObject);
    }
  }

  public void ready(boolean paramBoolean)
  {
    this.isLoading = paramBoolean;
  }

  public void setBitmapEventListener(IRecvBitmapEventListener paramIRecvBitmapEventListener)
  {
    this.mBitmapListener = paramIRecvBitmapEventListener;
  }

  public void setNetAdvertisementPic(String paramString)
  {
    this.mAdUrl = paramString;
  }

  public static abstract interface IRecvBitmapEventListener
  {
    public static final int RECV_BITMAP = 0;

    public abstract boolean onRecvBitmap(Bitmap paramBitmap);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.simpleImageloader.SimpleImageloader
 * JD-Core Version:    0.6.2
 */