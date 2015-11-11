package fm.qingting.framework.data;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataManager
  implements IDataRecvHandler
{
  private static final int DATACOMPLETE = 1;
  private static DataManager instance = null;
  private HashMap<IDataToken, ResultToken> _cbMap = new HashMap();
  private HashSet<WeakReference<IDataRecvHandler>> _dataListeners = new HashSet();
  private Handler _dispatchHandler = new DispatchHandler(Looper.getMainLooper());
  private HashMap<String, IDataSource> _dsMap = new HashMap();
  private HashMap<String, IDataSourceProxy> _proxyMap = new HashMap();
  private HashSet<WeakReference<IResultRecvHandler>> _resultListeners = new HashSet();
  private final int retryCount = 3;

  private void doCommand(IDataSource paramIDataSource, DataCommand paramDataCommand, ResultToken paramResultToken)
  {
    while (true)
    {
      try
      {
        if (paramIDataSource.isSynchronous(paramDataCommand.getType(), paramDataCommand.getParam()))
        {
          localIDataToken = paramIDataSource.doCommand(paramDataCommand, null);
          if ((localIDataToken != null) || (paramDataCommand.getNextCount() >= 3))
          {
            if (localIDataToken != null)
            {
              paramDataCommand = (Result)localIDataToken.getData();
              paramResultToken.setResult(paramDataCommand);
              sendResult(paramResultToken);
            }
          }
          else
          {
            paramDataCommand.getNextCommand();
            localIDataToken = paramIDataSource.doCommand(paramDataCommand, null);
            continue;
          }
          paramDataCommand = new Result(false, null, DataError.DATASOURCE_ERROR.getCode(), DataError.DATASOURCE_ERROR.getMessage());
          continue;
        }
        localIDataToken = paramIDataSource.doCommand(paramDataCommand, this);
        if ((localIDataToken != null) || (paramDataCommand.getNextCount() >= 3))
        {
          if (localIDataToken == null)
            break label179;
          this._cbMap.put(localIDataToken, paramResultToken);
          continue;
        }
      }
      finally
      {
      }
      paramDataCommand.getNextCommand();
      IDataToken localIDataToken = paramIDataSource.doCommand(paramDataCommand, this);
      continue;
      label179: paramResultToken.setResult(new Result(false, null, DataError.DATASOURCE_ERROR.getCode(), DataError.DATASOURCE_ERROR.getMessage()));
      sendResult(paramResultToken);
    }
  }

  public static DataManager getInstance()
  {
    try
    {
      if (instance == null)
        instance = new DataManager();
      DataManager localDataManager = instance;
      return localDataManager;
    }
    finally
    {
    }
  }

  private void sendResult(ResultToken paramResultToken)
  {
    Message localMessage = new Message();
    localMessage.what = 1;
    localMessage.obj = paramResultToken;
    this._dispatchHandler.sendMessage(localMessage);
  }

  public void addDataListener(IDataRecvHandler paramIDataRecvHandler)
  {
    IDataRecvHandler localIDataRecvHandler;
    do
      synchronized (this._dataListeners)
      {
        Iterator localIterator = this._dataListeners.iterator();
        if (!localIterator.hasNext())
        {
          this._dataListeners.add(new WeakReference(paramIDataRecvHandler));
          return;
        }
        localIDataRecvHandler = (IDataRecvHandler)((WeakReference)localIterator.next()).get();
        if (localIDataRecvHandler == null)
          localIterator.remove();
      }
    while (paramIDataRecvHandler != localIDataRecvHandler);
  }

  public void addDataSource(IDataSource paramIDataSource)
  {
    try
    {
      this._dsMap.put(paramIDataSource.dataSourceName().toLowerCase(), paramIDataSource);
      return;
    }
    finally
    {
      paramIDataSource = finally;
    }
    throw paramIDataSource;
  }

  public void addDataSourceProxy(IDataSourceProxy paramIDataSourceProxy)
  {
    try
    {
      this._proxyMap.put(paramIDataSourceProxy.dataSourceName().toLowerCase(), paramIDataSourceProxy);
      return;
    }
    finally
    {
      paramIDataSourceProxy = finally;
    }
    throw paramIDataSourceProxy;
  }

  public void addResultListener(IResultRecvHandler paramIResultRecvHandler)
  {
    IResultRecvHandler localIResultRecvHandler;
    do
      synchronized (this._resultListeners)
      {
        Iterator localIterator = this._resultListeners.iterator();
        if (!localIterator.hasNext())
        {
          this._resultListeners.add(new WeakReference(paramIResultRecvHandler));
          return;
        }
        localIResultRecvHandler = (IResultRecvHandler)((WeakReference)localIterator.next()).get();
        if (localIResultRecvHandler == null)
          localIterator.remove();
      }
    while (paramIResultRecvHandler != localIResultRecvHandler);
  }

  protected void dispatchDataEvent(boolean paramBoolean, Object paramObject1, String paramString1, String paramString2, Object paramObject2, IDataToken paramIDataToken, Object paramObject3)
  {
    Iterator localIterator = new ArrayList(this._dataListeners).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      IDataRecvHandler localIDataRecvHandler = (IDataRecvHandler)localWeakReference.get();
      if (localIDataRecvHandler == null)
        this._dataListeners.remove(localWeakReference);
      else if (paramBoolean)
        localIDataRecvHandler.onRecvData(paramObject1, paramObject2, paramIDataToken, paramObject3);
      else
        localIDataRecvHandler.onRecvError(paramString1, paramString2, paramObject2, paramIDataToken, paramObject3);
    }
  }

  protected void dispatchResultEvent(Result paramResult, Object paramObject1, IResultToken paramIResultToken, Object paramObject2)
  {
    Iterator localIterator = new ArrayList(this._resultListeners).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      IResultRecvHandler localIResultRecvHandler = (IResultRecvHandler)localWeakReference.get();
      if (localIResultRecvHandler == null)
        this._resultListeners.remove(localWeakReference);
      else
        localIResultRecvHandler.onRecvResult(paramResult, paramObject1, paramIResultToken, paramObject2);
    }
  }

  public IResultToken getData(String paramString, IResultRecvHandler paramIResultRecvHandler, Map<String, Object> paramMap)
  {
    try
    {
      paramString = getData(paramString, paramIResultRecvHandler, paramMap, true);
      return paramString;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  // ERROR //
  public IResultToken getData(String paramString, IResultRecvHandler paramIResultRecvHandler, Map<String, Object> paramMap, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 106	fm/qingting/framework/data/ResultToken
    //   5: dup
    //   6: invokespecial 234	fm/qingting/framework/data/ResultToken:<init>	()V
    //   9: astore 6
    //   11: aload 6
    //   13: aload_2
    //   14: invokevirtual 237	fm/qingting/framework/data/ResultToken:setResultRecvHandler	(Lfm/qingting/framework/data/IResultRecvHandler;)V
    //   17: aload 6
    //   19: aload_1
    //   20: invokevirtual 241	fm/qingting/framework/data/ResultToken:setType	(Ljava/lang/String;)V
    //   23: aload 6
    //   25: aload_3
    //   26: invokevirtual 244	fm/qingting/framework/data/ResultToken:setParametets	(Ljava/lang/Object;)V
    //   29: invokestatic 249	fm/qingting/framework/data/ServerConfig:getInstance	()Lfm/qingting/framework/data/ServerConfig;
    //   32: aload_1
    //   33: invokevirtual 253	fm/qingting/framework/data/ServerConfig:getRequestTrait	(Ljava/lang/String;)Lfm/qingting/framework/data/RequestTrait;
    //   36: astore 9
    //   38: aload 9
    //   40: ifnonnull +40 -> 80
    //   43: aload 6
    //   45: new 104	fm/qingting/framework/data/Result
    //   48: dup
    //   49: iconst_0
    //   50: aconst_null
    //   51: getstatic 256	fm/qingting/framework/data/DataError:REQUEST_ERROR	Lfm/qingting/framework/data/DataError;
    //   54: invokevirtual 126	fm/qingting/framework/data/DataError:getCode	()Ljava/lang/String;
    //   57: getstatic 256	fm/qingting/framework/data/DataError:REQUEST_ERROR	Lfm/qingting/framework/data/DataError;
    //   60: invokevirtual 129	fm/qingting/framework/data/DataError:getMessage	()Ljava/lang/String;
    //   63: invokespecial 132	fm/qingting/framework/data/Result:<init>	(ZLjava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    //   66: invokevirtual 110	fm/qingting/framework/data/ResultToken:setResult	(Lfm/qingting/framework/data/Result;)V
    //   69: aload_0
    //   70: aload 6
    //   72: invokespecial 114	fm/qingting/framework/data/DataManager:sendResult	(Lfm/qingting/framework/data/ResultToken;)V
    //   75: aload_0
    //   76: monitorexit
    //   77: aload 6
    //   79: areturn
    //   80: aload 6
    //   82: aload 9
    //   84: invokevirtual 261	fm/qingting/framework/data/RequestTrait:getDataType	()Ljava/lang/String;
    //   87: invokevirtual 264	fm/qingting/framework/data/ResultToken:setDataType	(Ljava/lang/String;)V
    //   90: aload 9
    //   92: getfield 268	fm/qingting/framework/data/RequestTrait:dataSource	Ljava/lang/String;
    //   95: ifnonnull +43 -> 138
    //   98: aload 6
    //   100: new 104	fm/qingting/framework/data/Result
    //   103: dup
    //   104: iconst_0
    //   105: aconst_null
    //   106: getstatic 271	fm/qingting/framework/data/DataError:CONFIG_ERROR	Lfm/qingting/framework/data/DataError;
    //   109: invokevirtual 126	fm/qingting/framework/data/DataError:getCode	()Ljava/lang/String;
    //   112: getstatic 271	fm/qingting/framework/data/DataError:CONFIG_ERROR	Lfm/qingting/framework/data/DataError;
    //   115: invokevirtual 129	fm/qingting/framework/data/DataError:getMessage	()Ljava/lang/String;
    //   118: invokespecial 132	fm/qingting/framework/data/Result:<init>	(ZLjava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    //   121: invokevirtual 110	fm/qingting/framework/data/ResultToken:setResult	(Lfm/qingting/framework/data/Result;)V
    //   124: aload_0
    //   125: aload 6
    //   127: invokespecial 114	fm/qingting/framework/data/DataManager:sendResult	(Lfm/qingting/framework/data/ResultToken;)V
    //   130: goto -55 -> 75
    //   133: astore_1
    //   134: aload_0
    //   135: monitorexit
    //   136: aload_1
    //   137: athrow
    //   138: aload_0
    //   139: getfield 49	fm/qingting/framework/data/DataManager:_dsMap	Ljava/util/HashMap;
    //   142: aload 9
    //   144: getfield 268	fm/qingting/framework/data/RequestTrait:dataSource	Ljava/lang/String;
    //   147: invokevirtual 195	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   150: invokevirtual 274	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   153: checkcast 85	fm/qingting/framework/data/IDataSource
    //   156: astore 5
    //   158: aload 5
    //   160: ifnonnull +38 -> 198
    //   163: aload 6
    //   165: new 104	fm/qingting/framework/data/Result
    //   168: dup
    //   169: iconst_0
    //   170: aconst_null
    //   171: getstatic 123	fm/qingting/framework/data/DataError:DATASOURCE_ERROR	Lfm/qingting/framework/data/DataError;
    //   174: invokevirtual 126	fm/qingting/framework/data/DataError:getCode	()Ljava/lang/String;
    //   177: getstatic 123	fm/qingting/framework/data/DataError:DATASOURCE_ERROR	Lfm/qingting/framework/data/DataError;
    //   180: invokevirtual 129	fm/qingting/framework/data/DataError:getMessage	()Ljava/lang/String;
    //   183: invokespecial 132	fm/qingting/framework/data/Result:<init>	(ZLjava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    //   186: invokevirtual 110	fm/qingting/framework/data/ResultToken:setResult	(Lfm/qingting/framework/data/Result;)V
    //   189: aload_0
    //   190: aload 6
    //   192: invokespecial 114	fm/qingting/framework/data/DataManager:sendResult	(Lfm/qingting/framework/data/ResultToken;)V
    //   195: goto -120 -> 75
    //   198: new 75	fm/qingting/framework/data/DataCommand
    //   201: dup
    //   202: aload 9
    //   204: aload_3
    //   205: invokespecial 277	fm/qingting/framework/data/DataCommand:<init>	(Lfm/qingting/framework/data/RequestTrait;Ljava/util/Map;)V
    //   208: astore 7
    //   210: aconst_null
    //   211: astore_2
    //   212: aconst_null
    //   213: astore_1
    //   214: invokestatic 249	fm/qingting/framework/data/ServerConfig:getInstance	()Lfm/qingting/framework/data/ServerConfig;
    //   217: aload 5
    //   219: invokeinterface 190 1 0
    //   224: invokevirtual 281	fm/qingting/framework/data/ServerConfig:getDefaultProxy	(Ljava/lang/String;)Ljava/lang/String;
    //   227: astore 8
    //   229: iload 4
    //   231: ifeq +67 -> 298
    //   234: aload 9
    //   236: getfield 284	fm/qingting/framework/data/RequestTrait:proxy	Ljava/lang/String;
    //   239: ifnull +150 -> 389
    //   242: aload 9
    //   244: getfield 284	fm/qingting/framework/data/RequestTrait:proxy	Ljava/lang/String;
    //   247: ldc_w 286
    //   250: invokevirtual 290	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   253: ifne +136 -> 389
    //   256: aload_0
    //   257: getfield 51	fm/qingting/framework/data/DataManager:_proxyMap	Ljava/util/HashMap;
    //   260: aload 9
    //   262: getfield 284	fm/qingting/framework/data/RequestTrait:proxy	Ljava/lang/String;
    //   265: invokevirtual 195	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   268: invokevirtual 274	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   271: checkcast 199	fm/qingting/framework/data/IDataSourceProxy
    //   274: astore_1
    //   275: aload_1
    //   276: astore_2
    //   277: aload_1
    //   278: ifnull +20 -> 298
    //   281: aload_1
    //   282: astore_2
    //   283: aload_1
    //   284: aload 7
    //   286: aload 5
    //   288: invokeinterface 294 3 0
    //   293: ifne +5 -> 298
    //   296: aconst_null
    //   297: astore_2
    //   298: aload_2
    //   299: ifnonnull +114 -> 413
    //   302: aload 5
    //   304: aload 7
    //   306: invokevirtual 79	fm/qingting/framework/data/DataCommand:getType	()Ljava/lang/String;
    //   309: aload_3
    //   310: invokeinterface 89 3 0
    //   315: istore 4
    //   317: goto +261 -> 578
    //   320: aload_1
    //   321: monitorenter
    //   322: iload 4
    //   324: ifeq +157 -> 481
    //   327: aload_2
    //   328: ifnonnull +109 -> 437
    //   331: aload_1
    //   332: astore_3
    //   333: aload 5
    //   335: aload 7
    //   337: aconst_null
    //   338: invokeinterface 92 3 0
    //   343: astore_2
    //   344: aload_2
    //   345: ifnull +109 -> 454
    //   348: aload_1
    //   349: astore_3
    //   350: aload_2
    //   351: invokeinterface 102 1 0
    //   356: checkcast 104	fm/qingting/framework/data/Result
    //   359: astore_2
    //   360: aload_1
    //   361: astore_3
    //   362: aload 6
    //   364: aload_2
    //   365: invokevirtual 110	fm/qingting/framework/data/ResultToken:setResult	(Lfm/qingting/framework/data/Result;)V
    //   368: aload_1
    //   369: astore_3
    //   370: aload_0
    //   371: aload 6
    //   373: invokespecial 114	fm/qingting/framework/data/DataManager:sendResult	(Lfm/qingting/framework/data/ResultToken;)V
    //   376: aload_1
    //   377: astore_3
    //   378: aload_1
    //   379: monitorexit
    //   380: goto -305 -> 75
    //   383: aload_1
    //   384: astore_3
    //   385: aload_1
    //   386: monitorexit
    //   387: aload_2
    //   388: athrow
    //   389: aload 8
    //   391: ifnull -116 -> 275
    //   394: aload_0
    //   395: getfield 51	fm/qingting/framework/data/DataManager:_proxyMap	Ljava/util/HashMap;
    //   398: aload 8
    //   400: invokevirtual 195	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   403: invokevirtual 274	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   406: checkcast 199	fm/qingting/framework/data/IDataSourceProxy
    //   409: astore_1
    //   410: goto -135 -> 275
    //   413: aload_2
    //   414: aload 7
    //   416: invokevirtual 79	fm/qingting/framework/data/DataCommand:getType	()Ljava/lang/String;
    //   419: aload_3
    //   420: aload 5
    //   422: invokeinterface 297 4 0
    //   427: istore 4
    //   429: goto +149 -> 578
    //   432: aload_2
    //   433: astore_1
    //   434: goto -114 -> 320
    //   437: aload_1
    //   438: astore_3
    //   439: aload_2
    //   440: aload 7
    //   442: aconst_null
    //   443: aload 5
    //   445: invokeinterface 300 4 0
    //   450: astore_2
    //   451: goto -107 -> 344
    //   454: aload_1
    //   455: astore_3
    //   456: new 104	fm/qingting/framework/data/Result
    //   459: dup
    //   460: iconst_0
    //   461: aconst_null
    //   462: getstatic 123	fm/qingting/framework/data/DataError:DATASOURCE_ERROR	Lfm/qingting/framework/data/DataError;
    //   465: invokevirtual 126	fm/qingting/framework/data/DataError:getCode	()Ljava/lang/String;
    //   468: getstatic 123	fm/qingting/framework/data/DataError:DATASOURCE_ERROR	Lfm/qingting/framework/data/DataError;
    //   471: invokevirtual 129	fm/qingting/framework/data/DataError:getMessage	()Ljava/lang/String;
    //   474: invokespecial 132	fm/qingting/framework/data/Result:<init>	(ZLjava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    //   477: astore_2
    //   478: goto -118 -> 360
    //   481: aload_2
    //   482: ifnonnull +36 -> 518
    //   485: aload_1
    //   486: astore_3
    //   487: aload 5
    //   489: aload 7
    //   491: aload_0
    //   492: invokeinterface 92 3 0
    //   497: astore_2
    //   498: aload_2
    //   499: ifnull +36 -> 535
    //   502: aload_1
    //   503: astore_3
    //   504: aload_0
    //   505: getfield 47	fm/qingting/framework/data/DataManager:_cbMap	Ljava/util/HashMap;
    //   508: aload_2
    //   509: aload 6
    //   511: invokevirtual 136	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   514: pop
    //   515: goto -139 -> 376
    //   518: aload_1
    //   519: astore_3
    //   520: aload_2
    //   521: aload 7
    //   523: aload_0
    //   524: aload 5
    //   526: invokeinterface 300 4 0
    //   531: astore_2
    //   532: goto -34 -> 498
    //   535: aload_1
    //   536: astore_3
    //   537: new 104	fm/qingting/framework/data/Result
    //   540: dup
    //   541: iconst_0
    //   542: aconst_null
    //   543: getstatic 123	fm/qingting/framework/data/DataError:DATASOURCE_ERROR	Lfm/qingting/framework/data/DataError;
    //   546: invokevirtual 126	fm/qingting/framework/data/DataError:getCode	()Ljava/lang/String;
    //   549: getstatic 123	fm/qingting/framework/data/DataError:DATASOURCE_ERROR	Lfm/qingting/framework/data/DataError;
    //   552: invokevirtual 129	fm/qingting/framework/data/DataError:getMessage	()Ljava/lang/String;
    //   555: invokespecial 132	fm/qingting/framework/data/Result:<init>	(ZLjava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    //   558: astore_2
    //   559: aload 6
    //   561: aload_2
    //   562: invokevirtual 110	fm/qingting/framework/data/ResultToken:setResult	(Lfm/qingting/framework/data/Result;)V
    //   565: aload_0
    //   566: aload 6
    //   568: invokespecial 114	fm/qingting/framework/data/DataManager:sendResult	(Lfm/qingting/framework/data/ResultToken;)V
    //   571: goto -195 -> 376
    //   574: astore_2
    //   575: goto -192 -> 383
    //   578: aload_2
    //   579: ifnonnull -147 -> 432
    //   582: aload 5
    //   584: astore_1
    //   585: goto -265 -> 320
    //   588: astore_2
    //   589: aload_3
    //   590: astore_1
    //   591: goto -208 -> 383
    //
    // Exception table:
    //   from	to	target	type
    //   2	38	133	finally
    //   43	75	133	finally
    //   80	130	133	finally
    //   138	158	133	finally
    //   163	195	133	finally
    //   198	210	133	finally
    //   214	229	133	finally
    //   234	275	133	finally
    //   283	296	133	finally
    //   302	317	133	finally
    //   320	322	133	finally
    //   387	389	133	finally
    //   394	410	133	finally
    //   413	429	133	finally
    //   559	571	574	finally
    //   333	344	588	finally
    //   350	360	588	finally
    //   362	368	588	finally
    //   370	376	588	finally
    //   378	380	588	finally
    //   385	387	588	finally
    //   439	451	588	finally
    //   456	478	588	finally
    //   487	498	588	finally
    //   504	515	588	finally
    //   520	532	588	finally
    //   537	559	588	finally
  }

  public void onRecvData(Object paramObject1, Object paramObject2, IDataToken paramIDataToken, Object paramObject3)
  {
    try
    {
      dispatchDataEvent(true, paramObject1, null, null, paramObject2, paramIDataToken, paramObject3);
      paramObject2 = (ResultToken)this._cbMap.get(paramIDataToken);
      this._cbMap.remove(paramIDataToken);
      if (paramObject2 == null);
      while (true)
      {
        return;
        paramObject2.setResult((Result)paramObject1);
        sendResult(paramObject2);
      }
    }
    finally
    {
    }
    throw paramObject1;
  }

  public void onRecvError(String paramString1, String paramString2, Object paramObject1, IDataToken paramIDataToken, Object paramObject2)
  {
    dispatchDataEvent(false, null, paramString1, paramString2, paramObject1, paramIDataToken, paramObject2);
    ResultToken localResultToken = (ResultToken)this._cbMap.get(paramIDataToken);
    this._cbMap.remove(paramIDataToken);
    if (((paramObject2 instanceof DataCommand)) && ((paramObject1 instanceof IDataSource)))
    {
      paramIDataToken = (DataCommand)paramObject2;
      paramObject1 = (IDataSource)paramObject1;
      paramIDataToken.getNextCommand();
      if (!paramIDataToken.hasRetryAllCommands())
        doCommand(paramObject1, paramIDataToken, localResultToken);
    }
    while (localResultToken == null)
      return;
    localResultToken.setResult(new Result(false, null, paramString1, paramString2));
    sendResult(localResultToken);
  }

  public void removeDataListener(IDataRecvHandler paramIDataRecvHandler)
  {
    Iterator localIterator;
    IDataRecvHandler localIDataRecvHandler;
    do
      synchronized (this._dataListeners)
      {
        localIterator = this._dataListeners.iterator();
        if (!localIterator.hasNext())
          return;
        localIDataRecvHandler = (IDataRecvHandler)((WeakReference)localIterator.next()).get();
        if (localIDataRecvHandler == null)
          localIterator.remove();
      }
    while (paramIDataRecvHandler != localIDataRecvHandler);
    localIterator.remove();
  }

  public void removeResultListener(IResultRecvHandler paramIResultRecvHandler)
  {
    Iterator localIterator;
    IResultRecvHandler localIResultRecvHandler;
    do
      synchronized (this._resultListeners)
      {
        localIterator = this._resultListeners.iterator();
        if (!localIterator.hasNext())
          return;
        localIResultRecvHandler = (IResultRecvHandler)((WeakReference)localIterator.next()).get();
        if (localIResultRecvHandler == null)
          localIterator.remove();
      }
    while (paramIResultRecvHandler != localIResultRecvHandler);
    localIterator.remove();
  }

  private class AsynchronousDispatcher
    implements Runnable
  {
    private List<ResultToken> _tasks = new ArrayList();

    public AsynchronousDispatcher()
    {
      this$1 = new Thread(null, this);
      DataManager.this.setPriority(1);
      DataManager.this.start();
    }

    public void addTask(ResultToken paramResultToken)
    {
      try
      {
        synchronized (this._tasks)
        {
          this._tasks.add(paramResultToken);
          return;
        }
      }
      finally
      {
      }
    }

    public void run()
    {
      if (Thread.currentThread().isInterrupted())
        return;
      ArrayList localArrayList = new ArrayList();
      while (true)
      {
        synchronized (this._tasks)
        {
          while (true)
          {
            localArrayList.addAll(this._tasks);
            this._tasks.clear();
            ??? = localArrayList.iterator();
            if (!((Iterator)???).hasNext())
              try
              {
                Thread.sleep(100L);
              }
              catch (InterruptedException localInterruptedException)
              {
                localInterruptedException.printStackTrace();
              }
          }
        }
        ResultToken localResultToken = (ResultToken)localInterruptedException.next();
        Message localMessage = new Message();
        localMessage.what = 1;
        localMessage.obj = localResultToken;
        DataManager.this._dispatchHandler.sendMessage(localMessage);
      }
    }
  }

  private class DispatchHandler extends Handler
  {
    public DispatchHandler(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      paramMessage = (ResultToken)paramMessage.obj;
      if (paramMessage == null)
        return;
      DataManager.this.dispatchResultEvent(paramMessage.getResult(), DataManager.this, paramMessage, paramMessage.getParametets());
      paramMessage.dispatchResultEvent(DataManager.this);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.DataManager
 * JD-Core Version:    0.6.2
 */