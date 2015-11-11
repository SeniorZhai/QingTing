package weibo4android.http;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

public class PostParameter
  implements Serializable, Comparable
{
  private static final String GIF = "image/gif";
  private static final String JPEG = "image/jpeg";
  private static final String OCTET = "application/octet-stream";
  private static final String PNG = "image/png";
  private static final long serialVersionUID = -8708108746980739212L;
  private File file = null;
  String name;
  String value;

  public PostParameter(String paramString, double paramDouble)
  {
    this.name = paramString;
    this.value = String.valueOf(paramDouble);
  }

  public PostParameter(String paramString, int paramInt)
  {
    this.name = paramString;
    this.value = String.valueOf(paramInt);
  }

  public PostParameter(String paramString, File paramFile)
  {
    this.name = paramString;
    this.file = paramFile;
  }

  public PostParameter(String paramString1, String paramString2)
  {
    this.name = paramString1;
    this.value = paramString2;
  }

  static boolean containsFile(List<PostParameter> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
      if (((PostParameter)paramList.next()).isFile())
        return true;
    return false;
  }

  public static boolean containsFile(PostParameter[] paramArrayOfPostParameter)
  {
    if (paramArrayOfPostParameter == null);
    while (true)
    {
      return false;
      int j = paramArrayOfPostParameter.length;
      int i = 0;
      while (i < j)
      {
        if (paramArrayOfPostParameter[i].isFile())
          return true;
        i += 1;
      }
    }
  }

  public static String encodeParameters(PostParameter[] paramArrayOfPostParameter)
  {
    if (paramArrayOfPostParameter == null)
      return "";
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (true)
    {
      if (i < paramArrayOfPostParameter.length)
      {
        if (paramArrayOfPostParameter[i].isFile())
          throw new IllegalArgumentException("parameter [" + paramArrayOfPostParameter[i].name + "]should be text");
        if (i != 0)
          localStringBuffer.append("&");
      }
      try
      {
        localStringBuffer.append(URLEncoder.encode(paramArrayOfPostParameter[i].name, "UTF-8")).append("=").append(URLEncoder.encode(paramArrayOfPostParameter[i].value, "UTF-8"));
        label115: i += 1;
        continue;
        return localStringBuffer.toString();
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        break label115;
      }
    }
  }

  public static PostParameter[] getParameterArray(String paramString, int paramInt)
  {
    return getParameterArray(paramString, String.valueOf(paramInt));
  }

  public static PostParameter[] getParameterArray(String paramString1, int paramInt1, String paramString2, int paramInt2)
  {
    return getParameterArray(paramString1, String.valueOf(paramInt1), paramString2, String.valueOf(paramInt2));
  }

  public static PostParameter[] getParameterArray(String paramString1, String paramString2)
  {
    return new PostParameter[] { new PostParameter(paramString1, paramString2) };
  }

  public static PostParameter[] getParameterArray(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    return new PostParameter[] { new PostParameter(paramString1, paramString2), new PostParameter(paramString3, paramString4) };
  }

  public int compareTo(Object paramObject)
  {
    paramObject = (PostParameter)paramObject;
    int j = this.name.compareTo(paramObject.name);
    int i = j;
    if (j == 0)
      i = this.value.compareTo(paramObject.value);
    return i;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == null);
    do
    {
      do
      {
        return false;
        if (this == paramObject)
          return true;
      }
      while (!(paramObject instanceof PostParameter));
      paramObject = (PostParameter)paramObject;
      if (this.file == null)
        break;
    }
    while (!this.file.equals(paramObject.file));
    if ((this.name.equals(paramObject.name)) && (this.value.equals(paramObject.value)));
    while (true)
    {
      return bool;
      if (paramObject.file == null)
        break;
      return false;
      bool = false;
    }
  }

  public String getContentType()
  {
    if (!isFile())
      throw new IllegalStateException("not a file");
    String str = this.file.getName();
    if (-1 == str.lastIndexOf("."))
      return "application/octet-stream";
    str = str.substring(str.lastIndexOf(".") + 1).toLowerCase();
    if (str.length() == 3)
    {
      if ("gif".equals(str))
        return "image/gif";
      if ("png".equals(str))
        return "image/png";
      if ("jpg".equals(str))
        return "image/jpeg";
      return "application/octet-stream";
    }
    if (str.length() == 4)
    {
      if ("jpeg".equals(str))
        return "image/jpeg";
      return "application/octet-stream";
    }
    return "application/octet-stream";
  }

  public File getFile()
  {
    return this.file;
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    int j = this.name.hashCode();
    int k = this.value.hashCode();
    if (this.file != null);
    for (int i = this.file.hashCode(); ; i = 0)
      return i + (j * 31 + k) * 31;
  }

  public boolean isFile()
  {
    return this.file != null;
  }

  public String toString()
  {
    return "PostParameter{name='" + this.name + '\'' + ", value='" + this.value + '\'' + ", file=" + this.file + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.http.PostParameter
 * JD-Core Version:    0.6.2
 */