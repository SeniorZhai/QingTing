package master.flame.danmaku.danmaku.parser.android;

import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.util.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;

public class JSONSource
  implements IDataSource<JSONArray>
{
  private InputStream mInput;
  private JSONArray mJSONArray;

  public JSONSource(Uri paramUri)
    throws IOException, JSONException
  {
    String str = paramUri.getScheme();
    if (("http".equalsIgnoreCase(str)) || ("https".equalsIgnoreCase(str)))
      init(new URL(paramUri.getPath()).openStream());
    while (!"file".equalsIgnoreCase(str))
      return;
    init(new FileInputStream(paramUri.getPath()));
  }

  public JSONSource(File paramFile)
    throws FileNotFoundException, JSONException
  {
    init(new FileInputStream(paramFile));
  }

  public JSONSource(InputStream paramInputStream)
    throws JSONException
  {
    init(paramInputStream);
  }

  public JSONSource(String paramString)
    throws JSONException
  {
    init(paramString);
  }

  public JSONSource(URL paramURL)
    throws JSONException, IOException
  {
    this(paramURL.openStream());
  }

  private void init(InputStream paramInputStream)
    throws JSONException
  {
    if (paramInputStream == null)
      throw new NullPointerException("input stream cannot be null!");
    this.mInput = paramInputStream;
    init(IOUtils.getString(this.mInput));
  }

  private void init(String paramString)
    throws JSONException
  {
    if (!TextUtils.isEmpty(paramString))
      this.mJSONArray = new JSONArray(paramString);
  }

  public JSONArray data()
  {
    return this.mJSONArray;
  }

  public void release()
  {
    IOUtils.closeQuietly(this.mInput);
    this.mInput = null;
    this.mJSONArray = null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.parser.android.JSONSource
 * JD-Core Version:    0.6.2
 */