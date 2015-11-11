package fm.qingting.qtradio.voice;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class VoiceHelper
{
  public static void Recognize(Context paramContext)
  {
    ByteArrayOutputStream localByteArrayOutputStream;
    try
    {
      paramContext = paramContext.getAssets().open("test.pcm");
      localByteArrayOutputStream = new ByteArrayOutputStream();
      byte[] arrayOfByte = new byte[1024];
      while (true)
      {
        int i = paramContext.read(arrayOfByte, 0, arrayOfByte.length);
        if (i == -1)
          break;
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    new String(localByteArrayOutputStream.toByteArray(), "UTF-8");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.voice.VoiceHelper
 * JD-Core Version:    0.6.2
 */