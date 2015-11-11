package fm.qingting.qtradio.ring;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import fm.qingting.qtradio.model.SharedCfg;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class RingManager
{
  public static void loadRings(Context paramContext)
  {
    try
    {
      loadRingsData(paramContext);
      return;
    }
    finally
    {
      paramContext = finally;
    }
    throw paramContext;
  }

  private static void loadRingsData(Context paramContext)
  {
    paramContext = paramContext.getAssets();
    try
    {
      if (!upgradeRingsDB())
        return;
      String[] arrayOfString = paramContext.list("rings");
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = arrayOfString[i];
        localObject = paramContext.open("rings/" + (String)localObject);
        ZipInputStream localZipInputStream = new ZipInputStream((InputStream)localObject);
        while (true)
        {
          ZipEntry localZipEntry = localZipInputStream.getNextEntry();
          if (localZipEntry == null)
            break;
          Log.e("RingManager", "ring: " + localZipEntry.getName());
          moveDataBase(localZipInputStream, localZipEntry.getName());
        }
        SharedCfg.getInstance().setLocalRingsVersion(SharedCfg.getInstance().getNewRingsVersion());
        ((InputStream)localObject).close();
        localZipInputStream.closeEntry();
        localZipInputStream.close();
        i += 1;
      }
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  // ERROR //
  private static boolean moveDataBase(InputStream paramInputStream, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +7 -> 8
    //   4: aload_1
    //   5: ifnonnull +5 -> 10
    //   8: iconst_0
    //   9: ireturn
    //   10: invokestatic 110	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   13: invokevirtual 114	fm/qingting/qtradio/model/InfoManager:root	()Lfm/qingting/qtradio/model/RootNode;
    //   16: getfield 120	fm/qingting/qtradio/model/RootNode:mDownLoadInfoNode	Lfm/qingting/qtradio/model/DownLoadInfoNode;
    //   19: invokevirtual 125	fm/qingting/qtradio/model/DownLoadInfoNode:getDownLoadPath	()Ljava/lang/String;
    //   22: astore_2
    //   23: new 127	java/io/File
    //   26: dup
    //   27: aload_2
    //   28: invokespecial 129	java/io/File:<init>	(Ljava/lang/String;)V
    //   31: astore_3
    //   32: aload_3
    //   33: invokevirtual 132	java/io/File:exists	()Z
    //   36: ifne +8 -> 44
    //   39: aload_3
    //   40: invokevirtual 135	java/io/File:mkdir	()Z
    //   43: pop
    //   44: new 36	java/lang/StringBuilder
    //   47: dup
    //   48: invokespecial 37	java/lang/StringBuilder:<init>	()V
    //   51: aload_2
    //   52: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: aload_1
    //   56: invokevirtual 43	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: invokevirtual 47	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: astore_1
    //   63: new 127	java/io/File
    //   66: dup
    //   67: aload_1
    //   68: invokespecial 129	java/io/File:<init>	(Ljava/lang/String;)V
    //   71: astore_2
    //   72: aload_2
    //   73: invokevirtual 132	java/io/File:exists	()Z
    //   76: ifeq +16 -> 92
    //   79: invokestatic 26	fm/qingting/qtradio/ring/RingManager:upgradeRingsDB	()Z
    //   82: ifeq -74 -> 8
    //   85: aload_2
    //   86: invokevirtual 138	java/io/File:delete	()Z
    //   89: ifeq -81 -> 8
    //   92: new 140	java/io/FileOutputStream
    //   95: dup
    //   96: aload_1
    //   97: invokespecial 141	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   100: astore_1
    //   101: sipush 4096
    //   104: newarray byte
    //   106: astore_2
    //   107: aload_0
    //   108: aload_2
    //   109: invokevirtual 145	java/io/InputStream:read	([B)I
    //   112: istore 4
    //   114: iload 4
    //   116: ifle +19 -> 135
    //   119: aload_1
    //   120: aload_2
    //   121: iconst_0
    //   122: iload 4
    //   124: invokevirtual 149	java/io/FileOutputStream:write	([BII)V
    //   127: goto -20 -> 107
    //   130: astore_0
    //   131: aload_0
    //   132: invokevirtual 152	java/io/IOException:printStackTrace	()V
    //   135: aload_1
    //   136: invokevirtual 153	java/io/FileOutputStream:close	()V
    //   139: iconst_1
    //   140: ireturn
    //   141: astore_0
    //   142: aload_0
    //   143: invokevirtual 154	java/io/FileNotFoundException:printStackTrace	()V
    //   146: iconst_0
    //   147: ireturn
    //   148: astore_0
    //   149: aload_0
    //   150: invokevirtual 152	java/io/IOException:printStackTrace	()V
    //   153: goto -14 -> 139
    //
    // Exception table:
    //   from	to	target	type
    //   107	114	130	java/io/IOException
    //   119	127	130	java/io/IOException
    //   92	101	141	java/io/FileNotFoundException
    //   135	139	148	java/io/IOException
  }

  private static boolean upgradeRingsDB()
  {
    String str1 = SharedCfg.getInstance().getLocalRingsVersion();
    String str2 = SharedCfg.getInstance().getNewRingsVersion();
    return (str1 == null) || (str2 == null) || (!str1.equalsIgnoreCase(str2));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.ring.RingManager
 * JD-Core Version:    0.6.2
 */