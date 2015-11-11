package fm.qingting.qtradio.offline;

import android.content.Context;
import android.content.res.AssetManager;
import fm.qingting.qtradio.model.SharedCfg;
import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class OfflineManager
{
  private static final String DefDataBasePath = "/data/data/fm.qingting.qtradio/databases/";

  public static void loadOfflineData(Context paramContext)
  {
    int i = 0;
    if (paramContext == null);
    while (true)
    {
      return;
      int j;
      while (true)
      {
        Object localObject;
        ZipInputStream localZipInputStream;
        try
        {
          while (true)
          {
            AssetManager localAssetManager = paramContext.getAssets();
            try
            {
              if (!upgradeOfflineDB(paramContext))
                break;
              log("loadOfflineData");
              paramContext = localAssetManager.list("offline");
              File localFile = new File("/data/data/fm.qingting.qtradio/databases/");
              if (!localFile.exists())
                localFile.mkdir();
              int k = paramContext.length;
              j = 0;
              if (i >= k)
                break label224;
              localObject = paramContext[i];
              localObject = localAssetManager.open("offline/" + (String)localObject);
              localZipInputStream = new ZipInputStream((InputStream)localObject);
              while (true)
              {
                ZipEntry localZipEntry = localZipInputStream.getNextEntry();
                if (localZipEntry == null)
                  break;
                log("ze: " + localZipEntry.getName());
                moveDataBase(localZipInputStream, localZipEntry.getName(), localFile);
              }
            }
            catch (Exception paramContext)
            {
              log("exception:" + paramContext);
            }
          }
          break;
        }
        finally
        {
        }
        j = 1;
        ((InputStream)localObject).close();
        localZipInputStream.closeEntry();
        localZipInputStream.close();
        i += 1;
      }
      label224: if (j != 0)
        SharedCfg.getInstance().setOfflineDBVersion(SharedCfg.getInstance().getNewOfflienDBVersion());
    }
  }

  private static void log(String paramString)
  {
  }

  // ERROR //
  private static boolean moveDataBase(InputStream paramInputStream, String paramString, File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +11 -> 12
    //   4: aload_1
    //   5: ifnull +7 -> 12
    //   8: aload_2
    //   9: ifnonnull +5 -> 14
    //   12: iconst_0
    //   13: ireturn
    //   14: new 53	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   21: ldc 8
    //   23: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: aload_1
    //   27: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   33: astore_1
    //   34: new 42	java/io/File
    //   37: dup
    //   38: aload_1
    //   39: invokespecial 44	java/io/File:<init>	(Ljava/lang/String;)V
    //   42: invokevirtual 48	java/io/File:exists	()Z
    //   45: ifeq +25 -> 70
    //   48: new 53	java/lang/StringBuilder
    //   51: dup
    //   52: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   55: aload_1
    //   56: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: ldc 120
    //   61: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   67: invokestatic 32	fm/qingting/qtradio/offline/OfflineManager:log	(Ljava/lang/String;)V
    //   70: new 122	java/io/FileOutputStream
    //   73: dup
    //   74: aload_1
    //   75: invokespecial 123	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   78: astore_1
    //   79: sipush 4096
    //   82: newarray byte
    //   84: astore_2
    //   85: aload_0
    //   86: aload_2
    //   87: invokevirtual 127	java/io/InputStream:read	([B)I
    //   90: istore_3
    //   91: iload_3
    //   92: ifle +18 -> 110
    //   95: aload_1
    //   96: aload_2
    //   97: iconst_0
    //   98: iload_3
    //   99: invokevirtual 131	java/io/FileOutputStream:write	([BII)V
    //   102: goto -17 -> 85
    //   105: astore_0
    //   106: aload_0
    //   107: invokevirtual 134	java/io/IOException:printStackTrace	()V
    //   110: aload_1
    //   111: invokevirtual 135	java/io/FileOutputStream:close	()V
    //   114: iconst_1
    //   115: ireturn
    //   116: astore_0
    //   117: aload_0
    //   118: invokevirtual 136	java/io/FileNotFoundException:printStackTrace	()V
    //   121: iconst_0
    //   122: ireturn
    //   123: astore_0
    //   124: aload_0
    //   125: invokevirtual 134	java/io/IOException:printStackTrace	()V
    //   128: goto -14 -> 114
    //
    // Exception table:
    //   from	to	target	type
    //   85	91	105	java/io/IOException
    //   95	102	105	java/io/IOException
    //   70	79	116	java/io/FileNotFoundException
    //   110	114	123	java/io/IOException
  }

  private static boolean upgradeOfflineDB(Context paramContext)
  {
    String str;
    if (paramContext != null)
    {
      paramContext = SharedCfg.getInstance().getOfflineDBVersion();
      str = SharedCfg.getInstance().getNewOfflienDBVersion();
    }
    return (paramContext == null) || (str == null) || (!paramContext.equalsIgnoreCase(str));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.offline.OfflineManager
 * JD-Core Version:    0.6.2
 */