package fm.qingting.qtradio.tips;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import fm.qingting.qtradio.model.SharedCfg;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TipsManager
{
  private static final String DefDataBasePath = "/mnt/sdcard/QTRadioTips/";
  private static final String TIPS_SOURCE = "file:///mnt/sdcard/QTRadioTips/1";
  private static final String TIPS_VERSION = "1.0";
  private static TipsManager _instance;
  private Context mContext;

  public static TipsManager getInstance()
  {
    if (_instance == null)
      _instance = new TipsManager();
    return _instance;
  }

  private void loadTipsData()
  {
    AssetManager localAssetManager = this.mContext.getAssets();
    try
    {
      if (!upgradeTipsDB())
        return;
      String[] arrayOfString = localAssetManager.list("tips");
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject1 = arrayOfString[i];
        localObject1 = localAssetManager.open("tips/" + (String)localObject1);
        ZipInputStream localZipInputStream = new ZipInputStream((InputStream)localObject1);
        Object localObject2 = new byte[4096];
        while (true)
        {
          localObject2 = localZipInputStream.getNextEntry();
          if (localObject2 == null)
            break;
          Log.e("TipManager", "tips: " + ((ZipEntry)localObject2).getName());
          moveDataBase(localZipInputStream, ((ZipEntry)localObject2).getName());
        }
        SharedCfg.getInstance().setTipsVersion("1.0");
        localZipInputStream.closeEntry();
        ((InputStream)localObject1).close();
        localZipInputStream.closeEntry();
        localZipInputStream.close();
        i += 1;
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private boolean upgradeTipsDB()
  {
    String str;
    if (this.mContext != null)
      str = SharedCfg.getInstance().getTipsVersion();
    return (str == null) || (!str.equalsIgnoreCase("1.0"));
  }

  public String getTipsSource()
  {
    return "file:///mnt/sdcard/QTRadioTips/1";
  }

  public void loadTips(Context paramContext)
  {
    if (paramContext == null);
    while (true)
    {
      return;
      try
      {
        this.mContext = paramContext;
        loadTipsData();
      }
      finally
      {
      }
    }
  }

  // ERROR //
  public boolean moveDataBase(InputStream paramInputStream, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +7 -> 8
    //   4: aload_2
    //   5: ifnonnull +5 -> 10
    //   8: iconst_0
    //   9: ireturn
    //   10: new 134	java/io/File
    //   13: dup
    //   14: ldc 8
    //   16: invokespecial 136	java/io/File:<init>	(Ljava/lang/String;)V
    //   19: astore_3
    //   20: aload_3
    //   21: invokevirtual 139	java/io/File:exists	()Z
    //   24: ifne +8 -> 32
    //   27: aload_3
    //   28: invokevirtual 142	java/io/File:mkdir	()Z
    //   31: pop
    //   32: new 53	java/lang/StringBuilder
    //   35: dup
    //   36: invokespecial 54	java/lang/StringBuilder:<init>	()V
    //   39: ldc 8
    //   41: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: aload_2
    //   45: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: astore_2
    //   52: new 134	java/io/File
    //   55: dup
    //   56: aload_2
    //   57: invokespecial 136	java/io/File:<init>	(Ljava/lang/String;)V
    //   60: astore_3
    //   61: aload_3
    //   62: invokevirtual 139	java/io/File:exists	()Z
    //   65: ifeq +17 -> 82
    //   68: aload_0
    //   69: invokespecial 43	fm/qingting/qtradio/tips/TipsManager:upgradeTipsDB	()Z
    //   72: ifeq -64 -> 8
    //   75: aload_3
    //   76: invokevirtual 145	java/io/File:delete	()Z
    //   79: ifeq -71 -> 8
    //   82: new 147	java/io/FileOutputStream
    //   85: dup
    //   86: aload_2
    //   87: invokespecial 148	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   90: astore_2
    //   91: sipush 4096
    //   94: newarray byte
    //   96: astore_3
    //   97: aload_1
    //   98: aload_3
    //   99: invokevirtual 152	java/io/InputStream:read	([B)I
    //   102: istore 4
    //   104: iload 4
    //   106: ifle +19 -> 125
    //   109: aload_2
    //   110: aload_3
    //   111: iconst_0
    //   112: iload 4
    //   114: invokevirtual 156	java/io/FileOutputStream:write	([BII)V
    //   117: goto -20 -> 97
    //   120: astore_1
    //   121: aload_1
    //   122: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   125: aload_2
    //   126: invokevirtual 160	java/io/FileOutputStream:close	()V
    //   129: iconst_1
    //   130: ireturn
    //   131: astore_1
    //   132: aload_1
    //   133: invokevirtual 161	java/io/FileNotFoundException:printStackTrace	()V
    //   136: iconst_0
    //   137: ireturn
    //   138: astore_1
    //   139: aload_1
    //   140: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   143: goto -14 -> 129
    //
    // Exception table:
    //   from	to	target	type
    //   97	104	120	java/io/IOException
    //   109	117	120	java/io/IOException
    //   82	91	131	java/io/FileNotFoundException
    //   125	129	138	java/io/IOException
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.tips.TipsManager
 * JD-Core Version:    0.6.2
 */