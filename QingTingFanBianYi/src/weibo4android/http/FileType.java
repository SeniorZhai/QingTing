package weibo4android.http;

import java.io.File;

public class FileType
{
  private final String[][] MIME_MapTable;

  public FileType()
  {
    String[] arrayOfString1 = { ".h", "text/plain" };
    String[] arrayOfString2 = { ".m3u", "audio/x-mpegurl" };
    String[] arrayOfString3 = { ".m4p", "audio/mp4a-latm" };
    String[] arrayOfString4 = { ".mov", "video/quicktime" };
    String[] arrayOfString5 = { ".mpc", "application/vnd.mpohun.certificate" };
    String[] arrayOfString6 = { ".prop", "text/plain" };
    String[] arrayOfString7 = { ".txt", "text/plain" };
    String[] arrayOfString8 = { ".xml", "text/xml" };
    String[] arrayOfString9 = { ".xml", "text/plain" };
    String[] arrayOfString10 = { ".zip", "application/zip" };
    this.MIME_MapTable = new String[][] { { ".3gp", "video/3gpp" }, { ".apk", "application/vnd.android.package-archive" }, { ".asf", "video/x-ms-asf" }, { ".avi", "video/x-msvideo" }, { ".bin", "application/octet-stream" }, { ".bmp", "image/bmp" }, { ".c", "text/plain" }, { ".class", "application/octet-stream" }, { ".conf", "text/plain" }, { ".cpp", "text/plain" }, { ".doc", "application/msword" }, { ".exe", "application/octet-stream" }, { ".gif", "image/gif" }, { ".gtar", "application/x-gtar" }, { ".gz", "application/x-gzip" }, arrayOfString1, { ".htm", "text/html" }, { ".html", "text/html" }, { ".jar", "application/java-archive" }, { ".java", "text/plain" }, { ".jpeg", "image/jpeg" }, { ".jpg", "image/jpeg" }, { ".js", "application/x-javascript" }, { ".log", "text/plain" }, arrayOfString2, { ".m4a", "audio/mp4a-latm" }, { ".m4b", "audio/mp4a-latm" }, arrayOfString3, { ".m4u", "video/vnd.mpegurl" }, { ".m4v", "video/x-m4v" }, arrayOfString4, { ".mp2", "audio/x-mpeg" }, { ".mp3", "audio/x-mpeg" }, { ".mp4", "video/mp4" }, arrayOfString5, { ".mpe", "video/mpeg" }, { ".mpeg", "video/mpeg" }, { ".mpg", "video/mpeg" }, { ".mpg4", "video/mp4" }, { ".mpga", "audio/mpeg" }, { ".msg", "application/vnd.ms-outlook" }, { ".ogg", "audio/ogg" }, { ".pdf", "application/pdf" }, { ".png", "image/png" }, { ".pps", "application/vnd.ms-powerpoint" }, { ".ppt", "application/vnd.ms-powerpoint" }, arrayOfString6, { ".rar", "application/x-rar-compressed" }, { ".rc", "text/plain" }, { ".rmvb", "audio/x-pn-realaudio" }, { ".rtf", "application/rtf" }, { ".sh", "text/plain" }, { ".tar", "application/x-tar" }, { ".tgz", "application/x-compressed" }, arrayOfString7, { ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" }, { ".wmv", "audio/x-ms-wmv" }, { ".wps", "application/vnd.ms-works" }, arrayOfString8, arrayOfString9, { ".z", "application/x-compress" }, arrayOfString10, { "", "*/*" } };
  }

  public String getMIMEType(File paramFile)
  {
    String str1 = "*/*";
    paramFile = paramFile.getName();
    int i = paramFile.lastIndexOf(".");
    Object localObject;
    if (i < 0)
      localObject = str1;
    String str2;
    do
    {
      return localObject;
      str2 = paramFile.substring(i, paramFile.length()).toLowerCase();
      localObject = str1;
    }
    while (str2 == "");
    i = 0;
    paramFile = str1;
    while (true)
    {
      localObject = paramFile;
      if (i >= this.MIME_MapTable.length)
        break;
      if (str2.equals(this.MIME_MapTable[i][0]))
        paramFile = this.MIME_MapTable[i][1];
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.http.FileType
 * JD-Core Version:    0.6.2
 */