package master.flame.danmaku.danmaku.parser.android;

import android.net.Uri;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.util.IOUtils;

public class AndroidFileSource
  implements IDataSource<InputStream>
{
  private InputStream inStream;

  public AndroidFileSource(Uri paramUri)
  {
    fillStreamFromUri(paramUri);
  }

  public AndroidFileSource(File paramFile)
  {
    fillStreamFromFile(paramFile);
  }

  public AndroidFileSource(InputStream paramInputStream)
  {
    this.inStream = paramInputStream;
  }

  public AndroidFileSource(String paramString)
  {
    fillStreamFromFile(new File(paramString));
  }

  public InputStream data()
  {
    return this.inStream;
  }

  public void fillStreamFromFile(File paramFile)
  {
    try
    {
      this.inStream = new BufferedInputStream(new FileInputStream(paramFile));
      return;
    }
    catch (FileNotFoundException paramFile)
    {
      paramFile.printStackTrace();
    }
  }

  public void fillStreamFromHttpFile(Uri paramUri)
  {
    try
    {
      paramUri = new URL(paramUri.getPath());
      paramUri.openConnection();
      this.inStream = new BufferedInputStream(paramUri.openStream());
      return;
    }
    catch (MalformedURLException paramUri)
    {
      paramUri.printStackTrace();
      return;
    }
    catch (IOException paramUri)
    {
      paramUri.printStackTrace();
    }
  }

  public void fillStreamFromUri(Uri paramUri)
  {
    String str = paramUri.getScheme();
    if (("http".equalsIgnoreCase(str)) || ("https".equalsIgnoreCase(str)))
      fillStreamFromHttpFile(paramUri);
    while (!"file".equalsIgnoreCase(str))
      return;
    fillStreamFromFile(new File(paramUri.getPath()));
  }

  public void release()
  {
    IOUtils.closeQuietly(this.inStream);
    this.inStream = null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.parser.android.AndroidFileSource
 * JD-Core Version:    0.6.2
 */