package fm.qingting.qtradio.simpleImageloader;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class NotificationImageLoader
{
  private static final TaskHandler sHandler = new TaskHandler(sThread.getLooper());
  private static IRecvBitmapEventListener sListener;
  private static final HandlerThread sThread = new HandlerThread("notification_image_loader");

  static
  {
    sThread.start();
  }

  public static void loadImage(IRecvBitmapEventListener paramIRecvBitmapEventListener, String paramString)
  {
    sListener = paramIRecvBitmapEventListener;
    paramIRecvBitmapEventListener = sHandler.obtainMessage();
    paramIRecvBitmapEventListener.obj = paramString;
    sHandler.sendMessage(paramIRecvBitmapEventListener);
  }

  public static abstract interface IRecvBitmapEventListener
  {
    public static final int RECV_BITMAP = 0;

    public abstract boolean onRecvBitmap(String paramString, Bitmap paramBitmap);
  }

  static class TaskHandler extends Handler
  {
    public TaskHandler(Looper paramLooper)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      int i = 1;
      if ((paramMessage != null) && (paramMessage.obj != null))
        paramMessage = (String)paramMessage.obj;
      try
      {
        localInputStream = new URL(paramMessage).openStream();
        localByteArrayOutputStream = new ByteArrayOutputStream();
        localObject = new byte[16384];
        while (true)
        {
          j = localInputStream.read((byte[])localObject, 0, localObject.length);
          if (j == -1)
            break;
          localByteArrayOutputStream.write((byte[])localObject, 0, j);
        }
      }
      catch (MalformedURLException paramMessage)
      {
        InputStream localInputStream;
        ByteArrayOutputStream localByteArrayOutputStream;
        paramMessage.printStackTrace();
        return;
        localByteArrayOutputStream.flush();
        Object localObject = localByteArrayOutputStream.toByteArray();
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray((byte[])localObject, 0, localObject.length, localOptions);
        int j = localOptions.outHeight;
        int k = localOptions.outWidth;
        localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        if (j > 200)
          i = Math.round(j / 200.0F);
        j = i;
        if (k / i > 200)
          j = Math.round(k / 200.0F);
        localOptions.inSampleSize = j;
        localOptions.inJustDecodeBounds = false;
        localObject = BitmapFactory.decodeByteArray((byte[])localObject, 0, localObject.length, localOptions);
        if ((NotificationImageLoader.sListener != null) && (localObject != null))
          NotificationImageLoader.sListener.onRecvBitmap(paramMessage, (Bitmap)localObject);
        localByteArrayOutputStream.close();
        localInputStream.close();
        NotificationImageLoader.access$002(null);
        return;
      }
      catch (IOException paramMessage)
      {
        paramMessage.printStackTrace();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.simpleImageloader.NotificationImageLoader
 * JD-Core Version:    0.6.2
 */