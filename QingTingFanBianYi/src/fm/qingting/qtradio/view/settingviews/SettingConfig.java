package fm.qingting.qtradio.view.settingviews;

import android.os.Environment;
import android.text.TextUtils;
import fm.qingting.downloadnew.DownloadUtils;
import fm.qingting.qtradio.fm.PlayCacheAgent;
import fm.qingting.qtradio.helper.OnlineUpdateHelper;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.scheduleview.SizeInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SettingConfig
{
  public static List<String> getAllExterSdcardPath()
  {
    ArrayList localArrayList = new ArrayList();
    String str = Environment.getExternalStorageDirectory().getPath();
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("mount").getInputStream()));
      while (true)
      {
        Object localObject1 = localBufferedReader.readLine();
        if (localObject1 == null)
          break;
        if ((!((String)localObject1).contains("secure")) && (!((String)localObject1).contains("asec")) && (!((String)localObject1).contains("media")) && (!((String)localObject1).contains("system")) && (!((String)localObject1).contains("cache")) && (!((String)localObject1).contains("sys")) && (!((String)localObject1).contains("data")) && (!((String)localObject1).contains("tmpfs")) && (!((String)localObject1).contains("shell")) && (!((String)localObject1).contains("root")) && (!((String)localObject1).contains("acct")) && (!((String)localObject1).contains("proc")) && (!((String)localObject1).contains("misc")) && (!((String)localObject1).contains("obb")) && ((((String)localObject1).contains("fat")) || (((String)localObject1).contains("fuse")) || (((String)localObject1).contains("ntfs"))))
        {
          localObject1 = ((String)localObject1).split(" ");
          if ((localObject1 != null) && (localObject1.length > 1))
          {
            Object localObject2 = localObject1[1];
            if ((localObject2 != null) && (!localArrayList.contains(localObject2)) && (localObject2.toLowerCase(Locale.US).contains("sd")))
              localArrayList.add(localObject1[1]);
          }
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      if (!localArrayList.contains(str))
        localArrayList.add(str);
    }
    return localArrayList;
  }

  private static String getDownloadDirSettingSubInfo()
  {
    String str2 = InfoManager.getInstance().root().mDownLoadInfoNode.getDownLoadPath();
    if (TextUtils.equals(str2, Environment.getExternalStorageDirectory().getPath() + File.separator + "QTDownloadRadio"));
    for (String str1 = "存储卡1"; ; str1 = "存储卡2")
    {
      String str3 = SizeInfo.getFileSize(DownloadUtils.getAvailableExternalMemorySize(str2));
      str2 = SizeInfo.getFileSize(DownloadUtils.getTotalExternalMemorySize(str2));
      return "当前位置:" + str1 + "," + str3 + "可用," + "共" + str2;
    }
  }

  public static List<Object> getSettingList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new SettingItem("清除缓存", SettingItem.SettingType.select, "delcache", "已缓存" + PlayCacheAgent.getInstance().getCacheSize() / 1048576L + "MB"));
    localArrayList.add(new SettingItem("打开应用后自动播放", SettingItem.SettingType.switcher, "autoplay"));
    localArrayList.add(new SettingItem("音质设置", SettingItem.SettingType.select, "audioquality"));
    if (needShowDownloadDirSetting())
      localArrayList.add(new SettingItem("选择下载位置", SettingItem.SettingType.select, "selectdir", getDownloadDirSettingSubInfo()));
    localArrayList.add(new SettingItem("消息推送", SettingItem.SettingType.select, "pushmessage"));
    localArrayList.add(new SettingItem("常见问题", SettingItem.SettingType.select, "faq"));
    localArrayList.add(new SettingItem("意见反馈", SettingItem.SettingType.select, "help"));
    if (OnlineUpdateHelper.getInstance().hasUpdate());
    for (String str = OnlineUpdateHelper.getInstance().getLatestVersion(); ; str = "已是最新")
    {
      localArrayList.add(new SettingItem("检查更新", SettingItem.SettingType.select, "checkupgrade", str));
      localArrayList.add(new SettingItem("关于蜻蜓fm", SettingItem.SettingType.select, "aboutus"));
      localArrayList.add(new SettingItem("主播入驻", SettingItem.SettingType.select, "podcasterenroll"));
      return localArrayList;
    }
  }

  // ERROR //
  public static List<String> getStorageList()
  {
    // Byte code:
    //   0: new 15	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 16	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: invokestatic 22	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   11: invokevirtual 28	java/io/File:getPath	()Ljava/lang/String;
    //   14: astore_0
    //   15: invokestatic 304	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   18: astore_1
    //   19: aload_1
    //   20: ldc_w 306
    //   23: invokevirtual 308	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   26: ifne +13 -> 39
    //   29: aload_1
    //   30: ldc_w 310
    //   33: invokevirtual 308	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   36: ifeq +292 -> 328
    //   39: iconst_1
    //   40: istore 7
    //   42: invokestatic 304	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   45: ldc_w 310
    //   48: invokevirtual 308	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   51: pop
    //   52: new 312	java/util/HashSet
    //   55: dup
    //   56: invokespecial 313	java/util/HashSet:<init>	()V
    //   59: astore_2
    //   60: iload 7
    //   62: ifeq +17 -> 79
    //   65: aload_2
    //   66: aload_0
    //   67: invokevirtual 314	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   70: pop
    //   71: aload_3
    //   72: aload_0
    //   73: invokeinterface 125 2 0
    //   78: pop
    //   79: new 30	java/io/BufferedReader
    //   82: dup
    //   83: new 316	java/io/FileReader
    //   86: dup
    //   87: ldc_w 318
    //   90: invokespecial 321	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   93: invokespecial 56	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   96: astore_1
    //   97: aload_1
    //   98: astore_0
    //   99: aload_1
    //   100: invokevirtual 59	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   103: astore 4
    //   105: aload 4
    //   107: ifnull +227 -> 334
    //   110: aload_1
    //   111: astore_0
    //   112: aload 4
    //   114: ldc_w 323
    //   117: invokevirtual 67	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   120: ifne +16 -> 136
    //   123: aload_1
    //   124: astore_0
    //   125: aload 4
    //   127: ldc_w 325
    //   130: invokevirtual 67	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   133: ifeq -36 -> 97
    //   136: aload_1
    //   137: astore_0
    //   138: new 327	java/util/StringTokenizer
    //   141: dup
    //   142: aload 4
    //   144: ldc 101
    //   146: invokespecial 330	java/util/StringTokenizer:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   149: astore 5
    //   151: aload_1
    //   152: astore_0
    //   153: aload 5
    //   155: invokevirtual 333	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   158: pop
    //   159: aload_1
    //   160: astore_0
    //   161: aload 5
    //   163: invokevirtual 333	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   166: astore 6
    //   168: aload_1
    //   169: astore_0
    //   170: aload_2
    //   171: aload 6
    //   173: invokevirtual 334	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   176: ifne -79 -> 97
    //   179: aload_1
    //   180: astore_0
    //   181: aload 5
    //   183: invokevirtual 333	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   186: pop
    //   187: aload_1
    //   188: astore_0
    //   189: aload 5
    //   191: invokevirtual 333	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   194: ldc 194
    //   196: invokevirtual 105	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   199: invokestatic 340	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   202: ldc_w 342
    //   205: invokeinterface 110 2 0
    //   210: pop
    //   211: aload_1
    //   212: astore_0
    //   213: aload 4
    //   215: ldc_w 344
    //   218: invokevirtual 67	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   221: ifeq -124 -> 97
    //   224: aload_1
    //   225: astore_0
    //   226: aload 4
    //   228: ldc_w 346
    //   231: invokevirtual 67	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   234: ifne -137 -> 97
    //   237: aload_1
    //   238: astore_0
    //   239: aload 4
    //   241: ldc_w 348
    //   244: invokevirtual 67	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   247: ifne -150 -> 97
    //   250: aload_1
    //   251: astore_0
    //   252: aload 4
    //   254: ldc_w 350
    //   257: invokevirtual 67	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   260: ifne -163 -> 97
    //   263: aload_1
    //   264: astore_0
    //   265: aload 4
    //   267: ldc_w 352
    //   270: invokevirtual 67	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   273: ifne -176 -> 97
    //   276: aload_1
    //   277: astore_0
    //   278: aload 4
    //   280: ldc 81
    //   282: invokevirtual 67	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   285: ifne -188 -> 97
    //   288: aload_1
    //   289: astore_0
    //   290: aload_2
    //   291: aload 6
    //   293: invokevirtual 314	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   296: pop
    //   297: aload_1
    //   298: astore_0
    //   299: aload_3
    //   300: aload 6
    //   302: invokeinterface 125 2 0
    //   307: pop
    //   308: goto -211 -> 97
    //   311: astore_2
    //   312: aload_1
    //   313: astore_0
    //   314: aload_2
    //   315: invokevirtual 353	java/io/FileNotFoundException:printStackTrace	()V
    //   318: aload_1
    //   319: ifnull +7 -> 326
    //   322: aload_1
    //   323: invokevirtual 356	java/io/BufferedReader:close	()V
    //   326: aload_3
    //   327: areturn
    //   328: iconst_0
    //   329: istore 7
    //   331: goto -289 -> 42
    //   334: aload_1
    //   335: ifnull -9 -> 326
    //   338: aload_1
    //   339: invokevirtual 356	java/io/BufferedReader:close	()V
    //   342: aload_3
    //   343: areturn
    //   344: astore_0
    //   345: aload_3
    //   346: areturn
    //   347: astore_2
    //   348: aconst_null
    //   349: astore_1
    //   350: aload_1
    //   351: astore_0
    //   352: aload_2
    //   353: invokevirtual 357	java/io/IOException:printStackTrace	()V
    //   356: aload_1
    //   357: ifnull -31 -> 326
    //   360: aload_1
    //   361: invokevirtual 356	java/io/BufferedReader:close	()V
    //   364: aload_3
    //   365: areturn
    //   366: astore_0
    //   367: aload_3
    //   368: areturn
    //   369: astore_1
    //   370: aconst_null
    //   371: astore_0
    //   372: aload_0
    //   373: ifnull +7 -> 380
    //   376: aload_0
    //   377: invokevirtual 356	java/io/BufferedReader:close	()V
    //   380: aload_1
    //   381: athrow
    //   382: astore_0
    //   383: aload_3
    //   384: areturn
    //   385: astore_0
    //   386: goto -6 -> 380
    //   389: astore_1
    //   390: goto -18 -> 372
    //   393: astore_2
    //   394: goto -44 -> 350
    //   397: astore_2
    //   398: aconst_null
    //   399: astore_1
    //   400: goto -88 -> 312
    //
    // Exception table:
    //   from	to	target	type
    //   99	105	311	java/io/FileNotFoundException
    //   112	123	311	java/io/FileNotFoundException
    //   125	136	311	java/io/FileNotFoundException
    //   138	151	311	java/io/FileNotFoundException
    //   153	159	311	java/io/FileNotFoundException
    //   161	168	311	java/io/FileNotFoundException
    //   170	179	311	java/io/FileNotFoundException
    //   181	187	311	java/io/FileNotFoundException
    //   189	211	311	java/io/FileNotFoundException
    //   213	224	311	java/io/FileNotFoundException
    //   226	237	311	java/io/FileNotFoundException
    //   239	250	311	java/io/FileNotFoundException
    //   252	263	311	java/io/FileNotFoundException
    //   265	276	311	java/io/FileNotFoundException
    //   278	288	311	java/io/FileNotFoundException
    //   290	297	311	java/io/FileNotFoundException
    //   299	308	311	java/io/FileNotFoundException
    //   338	342	344	java/io/IOException
    //   79	97	347	java/io/IOException
    //   360	364	366	java/io/IOException
    //   79	97	369	finally
    //   322	326	382	java/io/IOException
    //   376	380	385	java/io/IOException
    //   99	105	389	finally
    //   112	123	389	finally
    //   125	136	389	finally
    //   138	151	389	finally
    //   153	159	389	finally
    //   161	168	389	finally
    //   170	179	389	finally
    //   181	187	389	finally
    //   189	211	389	finally
    //   213	224	389	finally
    //   226	237	389	finally
    //   239	250	389	finally
    //   252	263	389	finally
    //   265	276	389	finally
    //   278	288	389	finally
    //   290	297	389	finally
    //   299	308	389	finally
    //   314	318	389	finally
    //   352	356	389	finally
    //   99	105	393	java/io/IOException
    //   112	123	393	java/io/IOException
    //   125	136	393	java/io/IOException
    //   138	151	393	java/io/IOException
    //   153	159	393	java/io/IOException
    //   161	168	393	java/io/IOException
    //   170	179	393	java/io/IOException
    //   181	187	393	java/io/IOException
    //   189	211	393	java/io/IOException
    //   213	224	393	java/io/IOException
    //   226	237	393	java/io/IOException
    //   239	250	393	java/io/IOException
    //   252	263	393	java/io/IOException
    //   265	276	393	java/io/IOException
    //   278	288	393	java/io/IOException
    //   290	297	393	java/io/IOException
    //   299	308	393	java/io/IOException
    //   79	97	397	java/io/FileNotFoundException
  }

  public static boolean needShowDownloadDirSetting()
  {
    return getStorageList().size() > 1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.settingviews.SettingConfig
 * JD-Core Version:    0.6.2
 */