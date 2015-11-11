package fm.qingting.qtradio.room;

import android.content.Context;
import android.util.Base64;
import android.widget.Toast;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.http.AsyncHttpClient;
import fm.qingting.async.http.SocketIOClient;
import fm.qingting.async.http.SocketIOClient.EventCallback;
import fm.qingting.async.http.SocketIOClient.JSONCallback;
import fm.qingting.async.http.SocketIOClient.SocketIOConnectCallback;
import fm.qingting.async.http.SocketIOClient.SocketIORequest;
import fm.qingting.async.http.SocketIOClient.StringCallback;
import fm.qingting.framework.utils.MobileState;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.QTLocation;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.utils.AppInfo;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QTChat extends Chat
{
  public static QTChat _instance = null;
  private long MAX_ASK_SONG_INTERVAL = 60L;
  private SocketIOClient _socketClient;
  private boolean autoGet = false;
  private boolean autoJoin = false;
  private boolean autoLogin = false;
  private int connectRetry = 3;
  private String connectUrl = "http://chat.qingting.fm/";
  private boolean connected = false;
  private boolean connecting = false;
  private long enterLiveRoom = 0L;
  private long lastAskSongNameTime = 0L;
  private String lastGetHistoryRoomId = null;
  private boolean loginSuccess = false;
  private List<String> lstCheckIn = new ArrayList();
  private List<CustomData> lstFilteredData = new ArrayList();
  private int mAskForSongNameCnt = 0;
  private UserInfo mAskForSongNameUser;
  private CompletedCallback mCompletedCallback = new CompletedCallback()
  {
    public void onCompleted(Exception paramAnonymousException)
    {
      QTChat.this.onDisconnect();
    }
  };
  private Context mContext;
  private SocketIOClient.EventCallback mEventCallback = new SocketIOClient.EventCallback()
  {
    public void onEvent(String paramAnonymousString, JSONArray paramAnonymousJSONArray)
    {
      if ((paramAnonymousJSONArray == null) || (paramAnonymousString == null))
        return;
      try
      {
        if ((paramAnonymousString.equalsIgnoreCase("join")) || (paramAnonymousString.equalsIgnoreCase("get")))
        {
          QTChat.this.on(paramAnonymousString, (JSONArray)paramAnonymousJSONArray.get(0));
          return;
        }
      }
      catch (Exception paramAnonymousString)
      {
        paramAnonymousString.printStackTrace();
        return;
      }
      if (paramAnonymousString.equalsIgnoreCase("zget"))
      {
        QTChat.this.on(paramAnonymousString, (String)paramAnonymousJSONArray.get(0));
        return;
      }
      QTChat.this.on(paramAnonymousString, (JSONObject)paramAnonymousJSONArray.get(0));
    }
  };
  private SocketIOClient.JSONCallback mJsonCallback = new SocketIOClient.JSONCallback()
  {
    public void onJSON(JSONObject paramAnonymousJSONObject)
    {
    }
  };
  private SocketIOClient.StringCallback mStringCallback = new SocketIOClient.StringCallback()
  {
    public void onString(String paramAnonymousString)
    {
    }
  };
  private UserInfo mUser;
  private int maxHistoryRecords = 30;
  private int maxOnlineUsers = 1000;
  private int recordCnt = 0;
  private String roomId;

  private QTChat()
  {
    init();
  }

  // ERROR //
  private CustomData _parseMessageObject(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_1
    //   7: ldc 133
    //   9: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   12: astore_2
    //   13: aload_2
    //   14: ldc 141
    //   16: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   19: ifeq +398 -> 417
    //   22: new 149	fm/qingting/qtradio/room/ChatData
    //   25: dup
    //   26: invokespecial 150	fm/qingting/qtradio/room/ChatData:<init>	()V
    //   29: astore_2
    //   30: new 135	org/json/JSONObject
    //   33: dup
    //   34: aload_1
    //   35: ldc 152
    //   37: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   40: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   43: astore_3
    //   44: aload_2
    //   45: aload_3
    //   46: ldc 157
    //   48: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   51: putfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   54: invokestatic 166	fm/qingting/qtradio/model/SharedCfg:getInstance	()Lfm/qingting/qtradio/model/SharedCfg;
    //   57: aload_2
    //   58: getfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   61: invokevirtual 169	fm/qingting/qtradio/model/SharedCfg:hitFilter	(Ljava/lang/String;)Z
    //   64: ifne -60 -> 4
    //   67: aload_2
    //   68: aload_1
    //   69: ldc 171
    //   71: invokevirtual 175	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   74: putfield 178	fm/qingting/qtradio/room/ChatData:createTime	J
    //   77: aload_2
    //   78: aload_1
    //   79: ldc 180
    //   81: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   84: putfield 182	fm/qingting/qtradio/room/ChatData:roomId	Ljava/lang/String;
    //   87: aload_2
    //   88: aload_0
    //   89: new 135	org/json/JSONObject
    //   92: dup
    //   93: aload_1
    //   94: ldc 184
    //   96: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   99: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   102: invokespecial 188	fm/qingting/qtradio/room/QTChat:_parseUserObject	(Lorg/json/JSONObject;)Lfm/qingting/qtradio/room/UserInfo;
    //   105: putfield 191	fm/qingting/qtradio/room/ChatData:user	Lfm/qingting/qtradio/room/UserInfo;
    //   108: aload_3
    //   109: ldc 133
    //   111: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   114: astore 4
    //   116: aload 4
    //   118: ifnull +18 -> 136
    //   121: aload 4
    //   123: ldc 193
    //   125: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   128: ifeq +205 -> 333
    //   131: aload_2
    //   132: iconst_2
    //   133: putfield 196	fm/qingting/qtradio/room/ChatData:conentType	I
    //   136: iconst_0
    //   137: istore 5
    //   139: aload_2
    //   140: aload_0
    //   141: new 135	org/json/JSONObject
    //   144: dup
    //   145: aload_1
    //   146: ldc 198
    //   148: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   151: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   154: invokespecial 188	fm/qingting/qtradio/room/QTChat:_parseUserObject	(Lorg/json/JSONObject;)Lfm/qingting/qtradio/room/UserInfo;
    //   157: putfield 201	fm/qingting/qtradio/room/ChatData:toUser	Lfm/qingting/qtradio/room/UserInfo;
    //   160: iload 5
    //   162: ifeq +24 -> 186
    //   165: aload_2
    //   166: aload_0
    //   167: new 135	org/json/JSONObject
    //   170: dup
    //   171: aload_3
    //   172: ldc 198
    //   174: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   177: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   180: invokespecial 188	fm/qingting/qtradio/room/QTChat:_parseUserObject	(Lorg/json/JSONObject;)Lfm/qingting/qtradio/room/UserInfo;
    //   183: putfield 201	fm/qingting/qtradio/room/ChatData:toUser	Lfm/qingting/qtradio/room/UserInfo;
    //   186: aload_0
    //   187: aload_3
    //   188: ldc 203
    //   190: invokevirtual 207	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   193: aload_2
    //   194: invokespecial 211	fm/qingting/qtradio/room/QTChat:_parseMetaObject	(Lorg/json/JSONObject;Lfm/qingting/qtradio/room/ChatData;)V
    //   197: aload_2
    //   198: aload_1
    //   199: ldc 213
    //   201: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   204: putfield 216	fm/qingting/qtradio/room/ChatData:msgId	Ljava/lang/String;
    //   207: aload_2
    //   208: ifnull +123 -> 331
    //   211: aload_2
    //   212: getfield 196	fm/qingting/qtradio/room/ChatData:conentType	I
    //   215: ifne +116 -> 331
    //   218: aload_2
    //   219: getfield 201	fm/qingting/qtradio/room/ChatData:toUser	Lfm/qingting/qtradio/room/UserInfo;
    //   222: ifnull +109 -> 331
    //   225: aload_2
    //   226: getfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   229: ldc 218
    //   231: invokevirtual 221	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   234: ifne +97 -> 331
    //   237: new 223	java/lang/StringBuilder
    //   240: dup
    //   241: invokespecial 224	java/lang/StringBuilder:<init>	()V
    //   244: ldc 226
    //   246: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   249: ldc 232
    //   251: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: invokevirtual 236	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   257: astore_1
    //   258: new 223	java/lang/StringBuilder
    //   261: dup
    //   262: invokespecial 224	java/lang/StringBuilder:<init>	()V
    //   265: aload_1
    //   266: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: aload_2
    //   270: getfield 201	fm/qingting/qtradio/room/ChatData:toUser	Lfm/qingting/qtradio/room/UserInfo;
    //   273: getfield 242	fm/qingting/qtradio/room/UserInfo:snsInfo	Lfm/qingting/qtradio/room/SnsInfo;
    //   276: getfield 247	fm/qingting/qtradio/room/SnsInfo:sns_name	Ljava/lang/String;
    //   279: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: invokevirtual 236	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   285: astore_1
    //   286: new 223	java/lang/StringBuilder
    //   289: dup
    //   290: invokespecial 224	java/lang/StringBuilder:<init>	()V
    //   293: aload_1
    //   294: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: ldc 249
    //   299: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: invokevirtual 236	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   305: astore_1
    //   306: aload_2
    //   307: new 223	java/lang/StringBuilder
    //   310: dup
    //   311: invokespecial 224	java/lang/StringBuilder:<init>	()V
    //   314: aload_1
    //   315: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: aload_2
    //   319: getfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   322: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   325: invokevirtual 236	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   328: putfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   331: aload_2
    //   332: areturn
    //   333: aload 4
    //   335: ldc 251
    //   337: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   340: ifeq +29 -> 369
    //   343: aload_2
    //   344: iconst_1
    //   345: putfield 196	fm/qingting/qtradio/room/ChatData:conentType	I
    //   348: aload_2
    //   349: getfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   352: ifnull -348 -> 4
    //   355: aload_2
    //   356: getfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   359: ldc 253
    //   361: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   364: ifeq -228 -> 136
    //   367: aconst_null
    //   368: areturn
    //   369: aload 4
    //   371: ldc 255
    //   373: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   376: ifeq -240 -> 136
    //   379: aload_2
    //   380: iconst_3
    //   381: putfield 196	fm/qingting/qtradio/room/ChatData:conentType	I
    //   384: aload_2
    //   385: getfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   388: ifnull -384 -> 4
    //   391: aload_2
    //   392: getfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   395: ldc 253
    //   397: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   400: istore 6
    //   402: iload 6
    //   404: ifeq -268 -> 136
    //   407: aconst_null
    //   408: areturn
    //   409: astore 4
    //   411: iconst_1
    //   412: istore 5
    //   414: goto -254 -> 160
    //   417: aload_2
    //   418: ldc_w 257
    //   421: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   424: ifeq +74 -> 498
    //   427: new 259	fm/qingting/qtradio/room/EnterRoomData
    //   430: dup
    //   431: invokespecial 260	fm/qingting/qtradio/room/EnterRoomData:<init>	()V
    //   434: astore_2
    //   435: new 135	org/json/JSONObject
    //   438: dup
    //   439: aload_1
    //   440: ldc 152
    //   442: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   445: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   448: pop
    //   449: aload_0
    //   450: new 135	org/json/JSONObject
    //   453: dup
    //   454: aload_1
    //   455: ldc 184
    //   457: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   460: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   463: invokespecial 188	fm/qingting/qtradio/room/QTChat:_parseUserObject	(Lorg/json/JSONObject;)Lfm/qingting/qtradio/room/UserInfo;
    //   466: astore_3
    //   467: aload_3
    //   468: ifnull +8 -> 476
    //   471: aload_2
    //   472: aload_3
    //   473: putfield 261	fm/qingting/qtradio/room/EnterRoomData:user	Lfm/qingting/qtradio/room/UserInfo;
    //   476: aload_2
    //   477: aload_1
    //   478: ldc 180
    //   480: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   483: putfield 262	fm/qingting/qtradio/room/EnterRoomData:roomId	Ljava/lang/String;
    //   486: aload_2
    //   487: aload_1
    //   488: ldc 213
    //   490: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   493: putfield 263	fm/qingting/qtradio/room/EnterRoomData:msgId	Ljava/lang/String;
    //   496: aload_2
    //   497: areturn
    //   498: aload_2
    //   499: ldc_w 265
    //   502: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   505: ifeq +124 -> 629
    //   508: new 149	fm/qingting/qtradio/room/ChatData
    //   511: dup
    //   512: invokespecial 150	fm/qingting/qtradio/room/ChatData:<init>	()V
    //   515: astore_2
    //   516: new 135	org/json/JSONObject
    //   519: dup
    //   520: aload_1
    //   521: ldc 152
    //   523: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   526: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   529: astore_3
    //   530: aload_2
    //   531: aload_3
    //   532: ldc 157
    //   534: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   537: putfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   540: aload_2
    //   541: aload_0
    //   542: aload_3
    //   543: ldc 198
    //   545: invokevirtual 207	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   548: invokespecial 188	fm/qingting/qtradio/room/QTChat:_parseUserObject	(Lorg/json/JSONObject;)Lfm/qingting/qtradio/room/UserInfo;
    //   551: putfield 201	fm/qingting/qtradio/room/ChatData:toUser	Lfm/qingting/qtradio/room/UserInfo;
    //   554: aload_2
    //   555: aload_3
    //   556: ldc_w 267
    //   559: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   562: putfield 270	fm/qingting/qtradio/room/ChatData:replyedContent	Ljava/lang/String;
    //   565: aload_0
    //   566: aload_3
    //   567: ldc 203
    //   569: invokevirtual 207	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   572: aload_2
    //   573: invokespecial 211	fm/qingting/qtradio/room/QTChat:_parseMetaObject	(Lorg/json/JSONObject;Lfm/qingting/qtradio/room/ChatData;)V
    //   576: aload_2
    //   577: aload_1
    //   578: ldc 171
    //   580: invokevirtual 175	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   583: putfield 178	fm/qingting/qtradio/room/ChatData:createTime	J
    //   586: aload_2
    //   587: aload_1
    //   588: ldc 180
    //   590: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   593: putfield 182	fm/qingting/qtradio/room/ChatData:roomId	Ljava/lang/String;
    //   596: aload_2
    //   597: aload_0
    //   598: new 135	org/json/JSONObject
    //   601: dup
    //   602: aload_1
    //   603: ldc 184
    //   605: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   608: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   611: invokespecial 188	fm/qingting/qtradio/room/QTChat:_parseUserObject	(Lorg/json/JSONObject;)Lfm/qingting/qtradio/room/UserInfo;
    //   614: putfield 191	fm/qingting/qtradio/room/ChatData:user	Lfm/qingting/qtradio/room/UserInfo;
    //   617: aload_2
    //   618: aload_1
    //   619: ldc 213
    //   621: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   624: putfield 216	fm/qingting/qtradio/room/ChatData:msgId	Ljava/lang/String;
    //   627: aload_2
    //   628: areturn
    //   629: aload_2
    //   630: ldc_w 272
    //   633: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   636: ifeq +86 -> 722
    //   639: new 149	fm/qingting/qtradio/room/ChatData
    //   642: dup
    //   643: invokespecial 150	fm/qingting/qtradio/room/ChatData:<init>	()V
    //   646: astore_2
    //   647: aload_2
    //   648: new 135	org/json/JSONObject
    //   651: dup
    //   652: aload_1
    //   653: ldc 152
    //   655: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   658: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   661: ldc 157
    //   663: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   666: putfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   669: aload_2
    //   670: aload_1
    //   671: ldc 171
    //   673: invokevirtual 175	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   676: putfield 178	fm/qingting/qtradio/room/ChatData:createTime	J
    //   679: aload_2
    //   680: aload_1
    //   681: ldc 180
    //   683: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   686: putfield 182	fm/qingting/qtradio/room/ChatData:roomId	Ljava/lang/String;
    //   689: aload_2
    //   690: aload_0
    //   691: new 135	org/json/JSONObject
    //   694: dup
    //   695: aload_1
    //   696: ldc 184
    //   698: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   701: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   704: invokespecial 188	fm/qingting/qtradio/room/QTChat:_parseUserObject	(Lorg/json/JSONObject;)Lfm/qingting/qtradio/room/UserInfo;
    //   707: putfield 191	fm/qingting/qtradio/room/ChatData:user	Lfm/qingting/qtradio/room/UserInfo;
    //   710: aload_2
    //   711: aload_1
    //   712: ldc 213
    //   714: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   717: putfield 216	fm/qingting/qtradio/room/ChatData:msgId	Ljava/lang/String;
    //   720: aload_2
    //   721: areturn
    //   722: aload_2
    //   723: ldc_w 274
    //   726: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   729: ifeq +86 -> 815
    //   732: new 149	fm/qingting/qtradio/room/ChatData
    //   735: dup
    //   736: invokespecial 150	fm/qingting/qtradio/room/ChatData:<init>	()V
    //   739: astore_2
    //   740: aload_2
    //   741: new 135	org/json/JSONObject
    //   744: dup
    //   745: aload_1
    //   746: ldc 152
    //   748: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   751: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   754: ldc 157
    //   756: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   759: putfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   762: aload_2
    //   763: aload_1
    //   764: ldc 171
    //   766: invokevirtual 175	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   769: putfield 178	fm/qingting/qtradio/room/ChatData:createTime	J
    //   772: aload_2
    //   773: aload_1
    //   774: ldc 180
    //   776: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   779: putfield 182	fm/qingting/qtradio/room/ChatData:roomId	Ljava/lang/String;
    //   782: aload_2
    //   783: aload_0
    //   784: new 135	org/json/JSONObject
    //   787: dup
    //   788: aload_1
    //   789: ldc 184
    //   791: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   794: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   797: invokespecial 188	fm/qingting/qtradio/room/QTChat:_parseUserObject	(Lorg/json/JSONObject;)Lfm/qingting/qtradio/room/UserInfo;
    //   800: putfield 191	fm/qingting/qtradio/room/ChatData:user	Lfm/qingting/qtradio/room/UserInfo;
    //   803: aload_2
    //   804: aload_1
    //   805: ldc 213
    //   807: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   810: putfield 216	fm/qingting/qtradio/room/ChatData:msgId	Ljava/lang/String;
    //   813: aload_2
    //   814: areturn
    //   815: aload_2
    //   816: ldc_w 276
    //   819: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   822: ifeq +74 -> 896
    //   825: new 278	fm/qingting/qtradio/room/TopicData
    //   828: dup
    //   829: invokespecial 279	fm/qingting/qtradio/room/TopicData:<init>	()V
    //   832: astore_2
    //   833: aload_2
    //   834: aload_1
    //   835: ldc 180
    //   837: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   840: putfield 280	fm/qingting/qtradio/room/TopicData:roomId	Ljava/lang/String;
    //   843: aload_2
    //   844: new 135	org/json/JSONObject
    //   847: dup
    //   848: aload_1
    //   849: ldc_w 281
    //   852: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   855: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   858: ldc_w 283
    //   861: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   864: putfield 285	fm/qingting/qtradio/room/TopicData:topic	Ljava/lang/String;
    //   867: aload_2
    //   868: getfield 285	fm/qingting/qtradio/room/TopicData:topic	Ljava/lang/String;
    //   871: ifnull +15 -> 886
    //   874: aload_2
    //   875: getfield 285	fm/qingting/qtradio/room/TopicData:topic	Ljava/lang/String;
    //   878: ldc 253
    //   880: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   883: ifeq +169 -> 1052
    //   886: aload_2
    //   887: ldc_w 287
    //   890: putfield 285	fm/qingting/qtradio/room/TopicData:topic	Ljava/lang/String;
    //   893: goto +159 -> 1052
    //   896: new 149	fm/qingting/qtradio/room/ChatData
    //   899: dup
    //   900: invokespecial 150	fm/qingting/qtradio/room/ChatData:<init>	()V
    //   903: astore_2
    //   904: aload_2
    //   905: new 135	org/json/JSONObject
    //   908: dup
    //   909: aload_1
    //   910: ldc 152
    //   912: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   915: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   918: ldc 157
    //   920: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   923: putfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   926: invokestatic 166	fm/qingting/qtradio/model/SharedCfg:getInstance	()Lfm/qingting/qtradio/model/SharedCfg;
    //   929: aload_2
    //   930: getfield 160	fm/qingting/qtradio/room/ChatData:content	Ljava/lang/String;
    //   933: invokevirtual 169	fm/qingting/qtradio/model/SharedCfg:hitFilter	(Ljava/lang/String;)Z
    //   936: ifne -932 -> 4
    //   939: aload_2
    //   940: aload_1
    //   941: ldc 171
    //   943: invokevirtual 175	org/json/JSONObject:getLong	(Ljava/lang/String;)J
    //   946: putfield 178	fm/qingting/qtradio/room/ChatData:createTime	J
    //   949: aload_2
    //   950: aload_1
    //   951: ldc 180
    //   953: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   956: putfield 182	fm/qingting/qtradio/room/ChatData:roomId	Ljava/lang/String;
    //   959: aload_2
    //   960: aload_0
    //   961: new 135	org/json/JSONObject
    //   964: dup
    //   965: aload_1
    //   966: ldc 184
    //   968: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   971: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   974: invokespecial 188	fm/qingting/qtradio/room/QTChat:_parseUserObject	(Lorg/json/JSONObject;)Lfm/qingting/qtradio/room/UserInfo;
    //   977: putfield 191	fm/qingting/qtradio/room/ChatData:user	Lfm/qingting/qtradio/room/UserInfo;
    //   980: aload_2
    //   981: aload_1
    //   982: ldc 213
    //   984: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   987: putfield 216	fm/qingting/qtradio/room/ChatData:msgId	Ljava/lang/String;
    //   990: aconst_null
    //   991: areturn
    //   992: astore_1
    //   993: aconst_null
    //   994: areturn
    //   995: astore_1
    //   996: aload_1
    //   997: invokevirtual 290	org/json/JSONException:printStackTrace	()V
    //   1000: aconst_null
    //   1001: areturn
    //   1002: astore_1
    //   1003: aconst_null
    //   1004: areturn
    //   1005: astore_1
    //   1006: goto -139 -> 867
    //   1009: astore_1
    //   1010: goto -197 -> 813
    //   1013: astore_1
    //   1014: goto -294 -> 720
    //   1017: astore_1
    //   1018: goto -391 -> 627
    //   1021: astore_3
    //   1022: goto -446 -> 576
    //   1025: astore 4
    //   1027: goto -462 -> 565
    //   1030: astore_1
    //   1031: goto -535 -> 496
    //   1034: astore_1
    //   1035: goto -828 -> 207
    //   1038: astore_3
    //   1039: goto -842 -> 197
    //   1042: astore 4
    //   1044: goto -858 -> 186
    //   1047: astore 4
    //   1049: goto -913 -> 136
    //   1052: aload_2
    //   1053: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   139	160	409	java/lang/Exception
    //   980	990	992	java/lang/Exception
    //   6	108	995	org/json/JSONException
    //   108	116	995	org/json/JSONException
    //   121	136	995	org/json/JSONException
    //   139	160	995	org/json/JSONException
    //   165	186	995	org/json/JSONException
    //   186	197	995	org/json/JSONException
    //   197	207	995	org/json/JSONException
    //   211	331	995	org/json/JSONException
    //   333	367	995	org/json/JSONException
    //   369	402	995	org/json/JSONException
    //   417	467	995	org/json/JSONException
    //   471	476	995	org/json/JSONException
    //   476	486	995	org/json/JSONException
    //   486	496	995	org/json/JSONException
    //   498	554	995	org/json/JSONException
    //   554	565	995	org/json/JSONException
    //   565	576	995	org/json/JSONException
    //   576	617	995	org/json/JSONException
    //   617	627	995	org/json/JSONException
    //   629	710	995	org/json/JSONException
    //   710	720	995	org/json/JSONException
    //   722	803	995	org/json/JSONException
    //   803	813	995	org/json/JSONException
    //   815	833	995	org/json/JSONException
    //   833	867	995	org/json/JSONException
    //   867	886	995	org/json/JSONException
    //   886	893	995	org/json/JSONException
    //   896	980	995	org/json/JSONException
    //   980	990	995	org/json/JSONException
    //   6	108	1002	java/lang/Exception
    //   211	331	1002	java/lang/Exception
    //   417	467	1002	java/lang/Exception
    //   471	476	1002	java/lang/Exception
    //   476	486	1002	java/lang/Exception
    //   498	554	1002	java/lang/Exception
    //   576	617	1002	java/lang/Exception
    //   629	710	1002	java/lang/Exception
    //   722	803	1002	java/lang/Exception
    //   815	833	1002	java/lang/Exception
    //   867	886	1002	java/lang/Exception
    //   886	893	1002	java/lang/Exception
    //   896	980	1002	java/lang/Exception
    //   833	867	1005	java/lang/Exception
    //   803	813	1009	java/lang/Exception
    //   710	720	1013	java/lang/Exception
    //   617	627	1017	java/lang/Exception
    //   565	576	1021	java/lang/Exception
    //   554	565	1025	java/lang/Exception
    //   486	496	1030	java/lang/Exception
    //   197	207	1034	java/lang/Exception
    //   186	197	1038	java/lang/Exception
    //   165	186	1042	java/lang/Exception
    //   108	116	1047	java/lang/Exception
    //   121	136	1047	java/lang/Exception
    //   333	367	1047	java/lang/Exception
    //   369	402	1047	java/lang/Exception
  }

  private void _parseMetaObject(JSONObject paramJSONObject, ChatData paramChatData)
  {
    if ((paramJSONObject == null) || (paramChatData == null))
      return;
    try
    {
      paramChatData.id = paramJSONObject.getString("id");
      paramChatData.commentid = paramJSONObject.getString("cid");
      return;
    }
    catch (Exception paramJSONObject)
    {
    }
  }

  private UserInfo _parseUserObject(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return null;
    UserInfo localUserInfo = new UserInfo();
    try
    {
      localUserInfo.userId = paramJSONObject.getString("user_id");
      localUserInfo.snsInfo.sns_id = paramJSONObject.getString("sns_id");
      localUserInfo.snsInfo.sns_avatar = paramJSONObject.getString("sns_avatar");
      localUserInfo.snsInfo.sns_name = paramJSONObject.getString("sns_name");
      if (localUserInfo.snsInfo.sns_name == null)
        localUserInfo.snsInfo.sns_name = "蜻蜓用户";
      localUserInfo.snsInfo.sns_site = paramJSONObject.getString("sns_site");
      localUserInfo.snsInfo.sns_gender = paramJSONObject.getString("sns_gender");
      localUserInfo.userKey = paramJSONObject.getString("qt_id");
      return localUserInfo;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
    return localUserInfo;
  }

  private void addToFilteredChatData(CustomData paramCustomData)
  {
    if (paramCustomData == null);
    while (!qualify((ChatData)paramCustomData))
      return;
    this.lstFilteredData.add(paramCustomData);
  }

  private void addToFilteredChatData(List<CustomData> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0));
    while (true)
    {
      return;
      int i = 0;
      while (i < paramList.size())
      {
        if (qualify((ChatData)paramList.get(i)))
          this.lstFilteredData.add(paramList.get(i));
        i += 1;
      }
    }
  }

  private boolean allowCheckIn(String paramString)
  {
    if (paramString == null)
      return false;
    int i = 0;
    while (true)
    {
      if (i >= this.lstCheckIn.size())
        break label48;
      if (((String)this.lstCheckIn.get(i)).equalsIgnoreCase(paramString))
        break;
      i += 1;
    }
    label48: return true;
  }

  private void connect(String paramString1, String paramString2)
  {
    setUrl(paramString1, paramString2);
    run();
  }

  private void connectedChatRoom()
  {
    if (!this.connected)
      return;
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("source", "android");
      localJSONObject.put("userId", InfoManager.getInstance().getDeviceId());
      localJSONObject.put("version", AppInfo.getCurrentInternalVersion(this.mContext));
      if ((this.mUser != null) && (this.mUser.snsInfo.sns_id != null) && (!this.mUser.snsInfo.sns_id.equalsIgnoreCase("")))
      {
        localObject = new JSONObject();
        ((JSONObject)localObject).putOpt("user_id", this.mUser.userId);
        ((JSONObject)localObject).putOpt("sns_id", this.mUser.snsInfo.sns_id);
        ((JSONObject)localObject).putOpt("sns_name", this.mUser.snsInfo.sns_name);
        ((JSONObject)localObject).putOpt("sns_avatar", this.mUser.snsInfo.sns_avatar);
        ((JSONObject)localObject).putOpt("sns_site", this.mUser.snsInfo.sns_site);
        ((JSONObject)localObject).putOpt("sns_gender", this.mUser.snsInfo.sns_gender);
        ((JSONObject)localObject).putOpt("qt_id", this.mUser.userKey);
        ((JSONObject)localObject).putOpt("source", this.mUser.snsInfo.source);
        localJSONObject.put("user", localObject);
      }
      Object localObject = new JSONArray();
      ((JSONArray)localObject).put(localJSONObject);
      this._socketClient.emit("init", (JSONArray)localObject);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void enterLiveRoom()
  {
    if (this.mUser == null)
      return;
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      Object localObject = new JSONObject();
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.putOpt("user_id", this.mUser.userId);
      localJSONObject2.putOpt("sns_id", this.mUser.snsInfo.sns_id);
      localJSONObject2.putOpt("sns_name", this.mUser.snsInfo.sns_name);
      localJSONObject2.putOpt("sns_avatar", this.mUser.snsInfo.sns_avatar);
      localJSONObject2.putOpt("sns_site", this.mUser.snsInfo.sns_site);
      localJSONObject2.putOpt("sns_gender", this.mUser.snsInfo.sns_gender);
      localJSONObject2.putOpt("qt_id", this.mUser.userKey);
      localJSONObject2.putOpt("source", this.mUser.snsInfo.source);
      ((JSONObject)localObject).put("from", localJSONObject2);
      localJSONObject1.put("type", "enter");
      localJSONObject1.put("data", localObject);
      localJSONObject1.put("room", String.valueOf(this.roomId));
      localObject = new JSONArray();
      ((JSONArray)localObject).put(localJSONObject1);
      this._socketClient.emit("send", (JSONArray)localObject);
      sendLiveRoomLog(3);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private String getCurrentBroadcasters()
  {
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")))
      return ((ProgramNode)localNode).getBroadCasterNamesForAt();
    return null;
  }

  private String getCurrentCity()
  {
    Object localObject = InfoManager.getInstance().getCurrentLocation();
    String str = null;
    if (localObject != null)
      str = ((QTLocation)localObject).city;
    localObject = str;
    if (str == null)
      localObject = "火星";
    return localObject;
  }

  public static QTChat getInstance()
  {
    if (_instance == null)
      _instance = new QTChat();
    return _instance;
  }

  private void init()
  {
    Context localContext = InfoManager.getInstance().getContext();
    int i;
    if (localContext != null)
    {
      i = MobileState.getNetWorkType(localContext);
      if (i != 1)
        break label33;
      this.maxHistoryRecords = 100;
    }
    while (true)
    {
      this.mContext = localContext;
      return;
      label33: if (i == 2)
        this.maxHistoryRecords = 50;
    }
  }

  private boolean parseJoinEvent(JSONArray paramJSONArray)
  {
    if (paramJSONArray == null)
      return false;
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (true)
    {
      if (i < paramJSONArray.length())
      {
        try
        {
          CustomData localCustomData = _parseMessageObject(new JSONObject((String)paramJSONArray.get(i)));
          if ((localCustomData == null) || (localCustomData.type != 1) || ((((ChatData)localCustomData).content != null) && (((ChatData)localCustomData).content.length() > InfoManager.getInstance().getMaxWordsInLiveRoom())) || ((((ChatData)localCustomData).content != null) && (((ChatData)localCustomData).content.startsWith("求歌名"))))
            break label329;
          if (((ChatData)localCustomData).conentType == 1)
          {
            String str2 = localCustomData.roomId;
            RoomDataCenter.getInstance().setSongList(1, localCustomData, str2);
          }
          if ((RoomDataCenter.getInstance().hasRoomData(1, localCustomData, localCustomData.roomId)) || (RoomDataCenter.getInstance().isWelMessageButInValid(localCustomData)) || (RoomDataCenter.getInstance().isPrivateMessageButInvalid(localCustomData)))
            break label329;
          localArrayList.add(localCustomData);
        }
        catch (Exception localException)
        {
        }
      }
      else
      {
        String str1;
        if (localArrayList.size() != 0)
        {
          str1 = ((CustomData)localArrayList.get(0)).roomId;
          Collections.sort(localArrayList, new ChatDataComparator());
          addToFilteredChatData(localArrayList);
          i = this.lstFilteredData.size();
          if (i <= 0)
            break label308;
          paramJSONArray = (CustomData)this.lstFilteredData.get(i - 1);
          this.lstFilteredData.remove(i - 1);
        }
        while (true)
        {
          RoomDataCenter.getInstance().setQualifyData(paramJSONArray);
          RoomDataCenter.getInstance().recvRoomData(1, localArrayList, str1);
          RoomDataCenter.getInstance().recvRoomEvent(1, "RLRJ");
          return true;
          label308: paramJSONArray = (CustomData)localArrayList.get(localArrayList.size() - 1);
        }
      }
      label329: i += 1;
    }
  }

  private boolean parseLoginEvent(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return false;
    try
    {
      RoomDataCenter.getInstance().recvRoomEvent(1, "RLRL");
      return true;
    }
    catch (Exception paramJSONObject)
    {
    }
    return false;
  }

  private boolean parseLogoutEvent(JSONObject paramJSONObject)
  {
    return true;
  }

  // ERROR //
  private boolean parseOnlineEvent(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 7
    //   3: aload_1
    //   4: ifnonnull +5 -> 9
    //   7: iconst_0
    //   8: ireturn
    //   9: new 109	java/util/ArrayList
    //   12: dup
    //   13: invokespecial 110	java/util/ArrayList:<init>	()V
    //   16: astore 4
    //   18: aconst_null
    //   19: astore_3
    //   20: aload_1
    //   21: ldc 180
    //   23: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   26: astore_2
    //   27: aload_2
    //   28: astore_3
    //   29: aload_1
    //   30: ldc_w 562
    //   33: invokevirtual 566	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   36: istore 6
    //   38: aload_1
    //   39: ldc_w 568
    //   42: invokevirtual 572	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   45: astore_3
    //   46: aload_3
    //   47: invokevirtual 493	org/json/JSONArray:length	()I
    //   50: istore 9
    //   52: iload 6
    //   54: istore 8
    //   56: aload_2
    //   57: astore_1
    //   58: iload 7
    //   60: iload 9
    //   62: if_icmpge +77 -> 139
    //   65: aload_0
    //   66: aload_3
    //   67: iload 7
    //   69: invokevirtual 575	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   72: invokespecial 188	fm/qingting/qtradio/room/QTChat:_parseUserObject	(Lorg/json/JSONObject;)Lfm/qingting/qtradio/room/UserInfo;
    //   75: astore_1
    //   76: aload_1
    //   77: ifnull +37 -> 114
    //   80: invokestatic 395	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   83: invokevirtual 398	fm/qingting/qtradio/model/InfoManager:getDeviceId	()Ljava/lang/String;
    //   86: astore 5
    //   88: aload 5
    //   90: ifnull +24 -> 114
    //   93: aload 5
    //   95: aload_1
    //   96: getfield 303	fm/qingting/qtradio/room/UserInfo:userId	Ljava/lang/String;
    //   99: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   102: ifne +12 -> 114
    //   105: aload 4
    //   107: aload_1
    //   108: invokeinterface 359 2 0
    //   113: pop
    //   114: iload 7
    //   116: iconst_1
    //   117: iadd
    //   118: istore 7
    //   120: goto -74 -> 46
    //   123: astore_1
    //   124: iconst_0
    //   125: istore 6
    //   127: aload_3
    //   128: astore_2
    //   129: aload_1
    //   130: invokevirtual 441	java/lang/Exception:printStackTrace	()V
    //   133: aload_2
    //   134: astore_1
    //   135: iload 6
    //   137: istore 8
    //   139: aload 4
    //   141: invokeinterface 364 1 0
    //   146: ifeq +45 -> 191
    //   149: aload_1
    //   150: ifnull +41 -> 191
    //   153: aload_1
    //   154: ldc 253
    //   156: invokevirtual 147	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   159: ifne +32 -> 191
    //   162: invokestatic 512	fm/qingting/qtradio/room/RoomDataCenter:getInstance	()Lfm/qingting/qtradio/room/RoomDataCenter;
    //   165: aload_1
    //   166: iload 8
    //   168: invokevirtual 579	fm/qingting/qtradio/room/RoomDataCenter:setOnlineUsersCnt	(Ljava/lang/String;I)V
    //   171: invokestatic 512	fm/qingting/qtradio/room/RoomDataCenter:getInstance	()Lfm/qingting/qtradio/room/RoomDataCenter;
    //   174: iconst_1
    //   175: aload 4
    //   177: aload_1
    //   178: invokevirtual 582	fm/qingting/qtradio/room/RoomDataCenter:recvOnlineUsers	(ILjava/util/List;Ljava/lang/String;)V
    //   181: invokestatic 512	fm/qingting/qtradio/room/RoomDataCenter:getInstance	()Lfm/qingting/qtradio/room/RoomDataCenter;
    //   184: iconst_1
    //   185: ldc_w 550
    //   188: invokevirtual 554	fm/qingting/qtradio/room/RoomDataCenter:recvRoomEvent	(ILjava/lang/String;)V
    //   191: iconst_1
    //   192: ireturn
    //   193: astore_1
    //   194: goto -65 -> 129
    //   197: astore_1
    //   198: goto -84 -> 114
    //
    // Exception table:
    //   from	to	target	type
    //   20	27	123	java/lang/Exception
    //   29	38	123	java/lang/Exception
    //   38	46	193	java/lang/Exception
    //   46	52	193	java/lang/Exception
    //   65	76	197	java/lang/Exception
    //   80	88	197	java/lang/Exception
    //   93	114	197	java/lang/Exception
  }

  private boolean parseRecvEvent(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return false;
    try
    {
      CustomData localCustomData = _parseMessageObject(paramJSONObject);
      int i;
      if ((localCustomData != null) && (localCustomData.type == 1))
      {
        if ((((ChatData)localCustomData).content != null) && (((ChatData)localCustomData).content.length() > InfoManager.getInstance().getMaxWordsInLiveRoom()))
          return false;
        i = ((ChatData)localCustomData).conentType;
        if (i != 2)
          if (i == 1)
          {
            paramJSONObject = localCustomData.roomId;
            RoomDataCenter.getInstance().setSongList(1, localCustomData, paramJSONObject);
            addToFilteredChatData(localCustomData);
            i = this.lstFilteredData.size();
            if (i <= 0)
              break label399;
            paramJSONObject = (CustomData)this.lstFilteredData.get(i - 1);
            this.lstFilteredData.remove(i - 1);
          }
      }
      while (true)
      {
        RoomDataCenter.getInstance().setQualifyData(paramJSONObject);
        paramJSONObject = localCustomData.roomId;
        RoomDataCenter.getInstance().recvRoomData(1, localCustomData, paramJSONObject);
        break label397;
        if ((i != 3) || (!RoomDataCenter.getInstance().isPrivateMessageButInvalid(localCustomData)))
          break;
        return false;
        if (i == 2)
        {
          if (((ChatData)localCustomData).toUser != null)
          {
            if ((((ChatData)localCustomData).user != null) && (this.mUser != null) && (((ChatData)localCustomData).user.userId.equalsIgnoreCase(this.mUser.userId)))
              return false;
            if ((this.mAskForSongNameUser != null) && (this.mAskForSongNameUser.userId.equalsIgnoreCase(((ChatData)localCustomData).toUser.userId)))
            {
              this.mAskForSongNameCnt += 1;
              ((ChatData)localCustomData).askForSongCnt = this.mAskForSongNameCnt;
            }
            this.mAskForSongNameUser = ((ChatData)localCustomData).toUser;
          }
          while (true)
          {
            paramJSONObject = localCustomData.roomId;
            RoomDataCenter.getInstance().recvAskForSong(1, localCustomData, paramJSONObject);
            break;
            this.mAskForSongNameUser = ((ChatData)localCustomData).user;
            this.mAskForSongNameCnt = 1;
            ((ChatData)localCustomData).askForSongCnt = this.mAskForSongNameCnt;
          }
          if ((localCustomData != null) && (localCustomData.type == 3))
          {
            paramJSONObject = localCustomData.roomId;
            RoomDataCenter.getInstance().recvUserEnter(1, localCustomData, paramJSONObject);
          }
          else if ((localCustomData != null) && (localCustomData.type == 4))
          {
            paramJSONObject = localCustomData.roomId;
            RoomDataCenter.getInstance().recvRoomTopic(1, localCustomData, paramJSONObject);
          }
        }
        label397: return true;
        label399: paramJSONObject = localCustomData;
      }
    }
    catch (Exception paramJSONObject)
    {
      break label397;
    }
  }

  private boolean parseZGetEvent(String paramString)
  {
    if (paramString == null)
      return false;
    try
    {
      byte[] arrayOfByte = Base64.decode(paramString, 0);
      paramString = new Inflater();
      paramString.setInput(arrayOfByte, 0, arrayOfByte.length);
      arrayOfByte = new byte[arrayOfByte.length];
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      while (true)
      {
        int i;
        if (!paramString.finished())
        {
          i = paramString.inflate(arrayOfByte);
          if (i > 0);
        }
        else
        {
          paramString.end();
          return parseJoinEvent(new JSONArray(new String(localByteArrayOutputStream.toByteArray())));
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return false;
    }
    catch (OutOfMemoryError paramString)
    {
    }
    return false;
  }

  private boolean qualify(ChatData paramChatData)
  {
    if (paramChatData == null);
    while ((paramChatData.content.contains("优听")) || (paramChatData.content.contains("youting")) || (paramChatData.content.contains("签到")) || (paramChatData.content.contains("献花")) || (paramChatData.content.contains("我听故我在")) || (paramChatData.content.contains("节目好精彩")) || (paramChatData.content.contains("我正在收听")) || (paramChatData.content.contains("兼职")) || (paramChatData.content.contains("顶一个")) || (paramChatData.content.contains("赞一个")) || (paramChatData.user.snsInfo.sns_avatar == null) || (paramChatData.user.snsInfo.sns_avatar.equalsIgnoreCase("")) || (paramChatData.content.length() > 15) || (paramChatData.content.length() < 10))
      return false;
    return true;
  }

  private void reset()
  {
    this.connected = false;
    this.connecting = false;
    this.autoJoin = false;
    this.autoLogin = false;
    this.autoGet = false;
    this.loginSuccess = false;
    this.recordCnt = 0;
    this.connectRetry = 3;
  }

  private void resetSocket()
  {
    if (this._socketClient != null)
    {
      this._socketClient.setClosedCallback(null);
      this._socketClient.setStringCallback(null);
      this._socketClient.setEventCallback(null);
      this._socketClient.setJSONCallback(null);
    }
  }

  private void run()
  {
    if ((this.connectUrl == null) || (this.connectUrl.equalsIgnoreCase("")))
      this.connectUrl = InfoManager.getInstance().chatServer;
    if (this.connecting)
      if (this.mContext == null);
    while ((this.connected) && (this._socketClient != null))
      return;
    try
    {
      this.connecting = true;
      SocketIOClient.SocketIORequest localSocketIORequest = new SocketIOClient.SocketIORequest(this.connectUrl);
      localSocketIORequest.setLogging("Socket.IO", 6);
      SocketIOClient.connect(AsyncHttpClient.getDefaultInstance(), localSocketIORequest, new SocketIOClient.SocketIOConnectCallback()
      {
        public void onConnectCompleted(Exception paramAnonymousException, SocketIOClient paramAnonymousSocketIOClient)
        {
          QTChat.access$002(QTChat.this, false);
          if (paramAnonymousSocketIOClient != null)
          {
            QTChat.this.resetSocket();
            QTChat.access$202(QTChat.this, paramAnonymousSocketIOClient);
            QTChat.this.onConnect();
            QTChat.this._socketClient.setClosedCallback(QTChat.this.mCompletedCallback);
            QTChat.this._socketClient.setStringCallback(QTChat.this.mStringCallback);
            QTChat.this._socketClient.setEventCallback(QTChat.this.mEventCallback);
            QTChat.this._socketClient.setJSONCallback(QTChat.this.mJsonCallback);
            return;
          }
          QTChat.access$202(QTChat.this, null);
          QTChat.this.onConnectFailure();
        }
      });
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void sendLiveRoomLog(int paramInt)
  {
  }

  private void setUrl(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return;
    this.connectUrl = paramString1;
    this.roomId = paramString2;
  }

  public boolean allowAskForSong()
  {
    long l = System.currentTimeMillis();
    if ((l - this.lastAskSongNameTime) / 1000L > this.MAX_ASK_SONG_INTERVAL)
    {
      this.lastAskSongNameTime = l;
      return true;
    }
    return false;
  }

  public void askForSongName(String paramString)
  {
    if (!this.connected)
    {
      connect(this.connectUrl, this.roomId);
      return;
    }
    try
    {
      if (!allowAskForSong())
      {
        Toast.makeText(this.mContext, "您求歌名的频率过快，请稍侯重试.", 1).show();
        return;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return;
    }
    paramString = new JSONObject();
    Object localObject = new JSONObject();
    paramString.put("type", "chat");
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.putOpt("user_id", this.mUser.userId);
    localJSONObject.putOpt("sns_id", this.mUser.snsInfo.sns_id);
    localJSONObject.putOpt("sns_name", this.mUser.snsInfo.sns_name);
    localJSONObject.putOpt("sns_avatar", this.mUser.snsInfo.sns_avatar);
    localJSONObject.putOpt("sns_site", this.mUser.snsInfo.sns_site);
    localJSONObject.putOpt("sns_gender", this.mUser.snsInfo.sns_gender);
    localJSONObject.putOpt("qt_id", this.mUser.userKey);
    localJSONObject.putOpt("source", this.mUser.snsInfo.source);
    paramString.put("from", localJSONObject);
    ((JSONObject)localObject).put("message", "求歌名.");
    ((JSONObject)localObject).put("type", "unknownsong");
    paramString.put("data", localObject);
    paramString.put("room", String.valueOf(this.roomId));
    localObject = new JSONArray();
    ((JSONArray)localObject).put(paramString);
    this._socketClient.emit("send", (JSONArray)localObject);
  }

  public void askForSongNameTogether(String paramString)
  {
    if (!this.connected)
    {
      connect(this.connectUrl, this.roomId);
      return;
    }
    try
    {
      paramString = new JSONObject();
      Object localObject = new JSONObject();
      paramString.put("type", "chat");
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.putOpt("user_id", this.mUser.userId);
      localJSONObject.putOpt("sns_id", this.mUser.snsInfo.sns_id);
      localJSONObject.putOpt("sns_name", this.mUser.snsInfo.sns_name);
      localJSONObject.putOpt("sns_avatar", this.mUser.snsInfo.sns_avatar);
      localJSONObject.putOpt("sns_site", this.mUser.snsInfo.sns_site);
      localJSONObject.putOpt("sns_gender", this.mUser.snsInfo.sns_gender);
      localJSONObject.putOpt("qt_id", this.mUser.userKey);
      localJSONObject.putOpt("source", this.mUser.snsInfo.source);
      paramString.put("from", localJSONObject);
      if (this.mAskForSongNameUser != null)
      {
        localJSONObject = new JSONObject();
        localJSONObject.putOpt("user_id", this.mAskForSongNameUser.userId);
        localJSONObject.putOpt("sns_id", this.mAskForSongNameUser.snsInfo.sns_id);
        localJSONObject.putOpt("sns_name", this.mAskForSongNameUser.snsInfo.sns_name);
        localJSONObject.putOpt("sns_avatar", this.mAskForSongNameUser.snsInfo.sns_avatar);
        localJSONObject.putOpt("sns_site", this.mAskForSongNameUser.snsInfo.sns_site);
        localJSONObject.putOpt("sns_gender", this.mAskForSongNameUser.snsInfo.sns_gender);
        localJSONObject.putOpt("source", this.mAskForSongNameUser.snsInfo.source);
        ((JSONObject)localObject).put("to", localJSONObject);
      }
      ((JSONObject)localObject).put("message", "同求歌名.");
      ((JSONObject)localObject).put("type", "unknownsong");
      paramString.put("data", localObject);
      paramString.put("room", String.valueOf(this.roomId));
      localObject = new JSONArray();
      ((JSONArray)localObject).put(paramString);
      this._socketClient.emit("send", (JSONArray)localObject);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }

  public void checkIn(int paramInt, String paramString)
  {
    String str1 = paramString;
    if (paramString == null)
      str1 = "";
    if (!this.connected)
      connect(this.connectUrl, this.roomId);
    while (true)
    {
      return;
      try
      {
        if (allowCheckIn(this.roomId))
        {
          this.lstCheckIn.add(this.roomId);
          JSONObject localJSONObject1 = new JSONObject();
          JSONObject localJSONObject2 = new JSONObject();
          localJSONObject1.put("type", "checkin");
          paramString = new JSONObject();
          paramString.putOpt("user_id", this.mUser.userId);
          paramString.putOpt("sns_id", this.mUser.snsInfo.sns_id);
          paramString.putOpt("sns_name", this.mUser.snsInfo.sns_name);
          paramString.putOpt("sns_avatar", this.mUser.snsInfo.sns_avatar);
          paramString.putOpt("sns_site", this.mUser.snsInfo.sns_site);
          paramString.putOpt("sns_gender", this.mUser.snsInfo.sns_gender);
          paramString.putOpt("qt_id", this.mUser.userKey);
          paramString.putOpt("source", this.mUser.snsInfo.source);
          localJSONObject1.put("from", paramString);
          paramString = "我听故我在，我正在" + getCurrentCity();
          String str2 = paramString + "收听 ";
          String str3 = getCurrentBroadcasters();
          paramString = str2;
          if (str3 != null)
          {
            paramString = str2;
            if (!str3.equalsIgnoreCase(""))
            {
              paramString = str2 + str3;
              paramString = paramString + " 主持的";
            }
          }
          localJSONObject2.put("message", paramString + "\"" + str1 + "\"");
          localJSONObject1.put("data", localJSONObject2);
          localJSONObject1.put("room", String.valueOf(this.roomId));
          paramString = new JSONArray();
          paramString.put(localJSONObject1);
          this._socketClient.emit("send", paramString);
          sendLiveRoomLog(1);
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
  }

  public void exit()
  {
    if (this._socketClient != null);
    try
    {
      leave();
      this._socketClient.disconnect();
      reset();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void flower(int paramInt, UserInfo paramUserInfo)
  {
    if (!this.connected)
      connect(this.connectUrl, this.roomId);
    while (true)
    {
      return;
      if (paramUserInfo != null)
        try
        {
          if (paramUserInfo.snsInfo.sns_name != null)
          {
            JSONObject localJSONObject1 = new JSONObject();
            JSONObject localJSONObject2 = new JSONObject();
            localJSONObject2.putOpt("user_id", this.mUser.userId);
            localJSONObject2.putOpt("sns_id", this.mUser.snsInfo.sns_id);
            localJSONObject2.putOpt("sns_name", this.mUser.snsInfo.sns_name);
            localJSONObject2.putOpt("sns_avatar", this.mUser.snsInfo.sns_avatar);
            localJSONObject2.putOpt("sns_site", this.mUser.snsInfo.sns_site);
            localJSONObject2.putOpt("sns_gender", this.mUser.snsInfo.sns_gender);
            localJSONObject2.putOpt("source", this.mUser.snsInfo.source);
            localJSONObject2.putOpt("qt_id", this.mUser.userKey);
            localJSONObject1.put("from", localJSONObject2);
            localJSONObject2 = new JSONObject();
            localJSONObject1.put("type", "flower");
            String str = "节目好精彩，我不远万里在" + getCurrentCity();
            localJSONObject2.put("message", str + "向 @" + paramUserInfo.snsInfo.sns_name + " 献了一朵花");
            localJSONObject1.put("data", localJSONObject2);
            localJSONObject1.put("room", String.valueOf(this.roomId));
            paramUserInfo = new JSONArray();
            paramUserInfo.put(localJSONObject1);
            this._socketClient.emit("send", paramUserInfo);
            sendLiveRoomLog(2);
            return;
          }
        }
        catch (Exception paramUserInfo)
        {
          paramUserInfo.printStackTrace();
        }
    }
  }

  public void getHistory(String paramString1, String paramString2, int paramInt)
  {
    if ((paramString2 == null) || (paramString1 == null))
      return;
    if ((this.roomId != null) && (!this.roomId.equalsIgnoreCase(paramString2)))
    {
      this.lstFilteredData.clear();
      RoomDataCenter.getInstance().clearRoomTopic();
    }
    this.roomId = paramString2;
    this.connectUrl = paramString1;
    if (!this.connected)
    {
      connect(paramString1, paramString2);
      this.autoGet = true;
      return;
    }
    int i = paramInt;
    if (paramInt <= 0);
    try
    {
      i = this.maxHistoryRecords;
      paramString1 = new JSONObject();
      paramString1.put("room", String.valueOf(paramString2));
      paramString1.put("history", i);
      paramString2 = new JSONArray();
      paramString2.put(paramString1);
      this._socketClient.emit("zget", paramString2);
      return;
    }
    catch (Exception paramString1)
    {
    }
  }

  public void getOnlineFriends(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    this.roomId = paramString;
    if (!this.connected)
    {
      connect(this.connectUrl, paramString);
      return;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("room", String.valueOf(paramString));
      localJSONObject.put("count", String.valueOf(this.maxOnlineUsers));
      paramString = new JSONArray();
      paramString.put(localJSONObject);
      this._socketClient.emit("onlineList", paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }

  public void getTopic(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    this.roomId = paramString;
    if (!this.connected)
    {
      connect(this.connectUrl, paramString);
      return;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("room", String.valueOf(paramString));
      localJSONObject.put("type", "topic");
      paramString = new JSONArray();
      paramString.put(localJSONObject);
      this._socketClient.emit("notify", paramString);
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  public void join(String paramString1, String paramString2, int paramInt)
  {
    if ((paramString2 == null) || (paramString1 == null))
      return;
    this.connectUrl = paramString1;
    if ((this.roomId != null) && (!this.roomId.equalsIgnoreCase(paramString2)))
      this.lstFilteredData.clear();
    this.roomId = paramString2;
    this.recordCnt = 0;
    if (!this.connected)
    {
      connect(paramString1, paramString2);
      this.autoJoin = true;
      return;
    }
    while (true)
    {
      try
      {
        this.enterLiveRoom = System.currentTimeMillis();
        paramString1 = new JSONObject();
        paramString1.put("room", String.valueOf(paramString2));
        if ((this.recordCnt == -1) || (this.recordCnt > this.maxHistoryRecords))
        {
          paramString1.put("history", this.maxHistoryRecords);
          paramString2 = new JSONArray();
          paramString2.put(paramString1);
          this._socketClient.emit("join", paramString2);
          sendLiveRoomLog(0);
          if (!this.loginSuccess)
            break;
          enterLiveRoom();
          return;
        }
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        return;
      }
      paramString1.put("history", this.recordCnt);
    }
  }

  public void leave()
  {
    try
    {
      if (this.enterLiveRoom == 0L)
        return;
      this.autoJoin = false;
      long l = (System.currentTimeMillis() - this.enterLiveRoom) / 1000L;
      this.enterLiveRoom = 0L;
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("room", String.valueOf(this.roomId));
      localJSONObject.put("duration", String.valueOf(l));
      JSONArray localJSONArray = new JSONArray();
      localJSONArray.put(localJSONObject);
      this._socketClient.emit("leave", localJSONArray);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void login(UserInfo paramUserInfo)
  {
    if (paramUserInfo == null)
      break label4;
    while (true)
    {
      label4: return;
      if ((!this.loginSuccess) || (!paramUserInfo.snsInfo.sns_id.equalsIgnoreCase(this.mUser.snsInfo.sns_id)) || (!paramUserInfo.snsInfo.sns_name.equalsIgnoreCase(this.mUser.snsInfo.sns_name)))
      {
        this.loginSuccess = false;
        this.autoLogin = true;
        this.mUser = paramUserInfo;
        RoomDataCenter.getInstance().setLoginUser(paramUserInfo);
        if (!this.connected)
        {
          connect(this.connectUrl, this.roomId);
          return;
        }
        if (paramUserInfo == null)
          break;
        try
        {
          if (paramUserInfo.userId != null)
          {
            JSONObject localJSONObject = new JSONObject();
            localJSONObject.putOpt("user_id", paramUserInfo.userId);
            localJSONObject.putOpt("sns_id", paramUserInfo.snsInfo.sns_id);
            localJSONObject.putOpt("sns_name", paramUserInfo.snsInfo.sns_name);
            localJSONObject.putOpt("sns_avatar", paramUserInfo.snsInfo.sns_avatar);
            localJSONObject.putOpt("sns_site", paramUserInfo.snsInfo.sns_site);
            localJSONObject.putOpt("sns_gender", paramUserInfo.snsInfo.sns_gender);
            localJSONObject.putOpt("qt_id", paramUserInfo.userKey);
            localJSONObject.putOpt("source", paramUserInfo.snsInfo.source);
            paramUserInfo = new JSONArray();
            paramUserInfo.put(localJSONObject);
            this._socketClient.emit("login", paramUserInfo);
            enterLiveRoom();
            return;
          }
        }
        catch (Exception paramUserInfo)
        {
          paramUserInfo.printStackTrace();
        }
      }
    }
  }

  public void logout(UserInfo paramUserInfo)
  {
    if (paramUserInfo == null)
      return;
    if (!this.connected)
    {
      connect(this.connectUrl, this.roomId);
      return;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.putOpt("user_id", paramUserInfo.userId);
      localJSONObject.putOpt("sns_id", paramUserInfo.snsInfo.sns_id);
      localJSONObject.putOpt("sns_name", paramUserInfo.snsInfo.sns_name);
      localJSONObject.putOpt("sns_avatar", paramUserInfo.snsInfo.sns_avatar);
      localJSONObject.putOpt("sns_site", paramUserInfo.snsInfo.sns_site);
      paramUserInfo = new JSONArray();
      paramUserInfo.put(localJSONObject);
      this._socketClient.emit("logout", paramUserInfo);
      return;
    }
    catch (Exception paramUserInfo)
    {
      paramUserInfo.printStackTrace();
    }
  }

  public void on(String paramString1, String paramString2)
  {
    if (paramString2 == null);
    while (!paramString1.equalsIgnoreCase("zget"))
      return;
    parseZGetEvent(paramString2);
  }

  public void on(String paramString, JSONArray paramJSONArray)
  {
    if (paramJSONArray == null);
    do
    {
      return;
      if (paramString.equalsIgnoreCase("join"))
      {
        parseJoinEvent(paramJSONArray);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("get"));
    parseJoinEvent(paramJSONArray);
  }

  public void on(String paramString, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null);
    do
    {
      return;
      if (paramString.equalsIgnoreCase("login"))
      {
        this.loginSuccess = true;
        parseLoginEvent(paramJSONObject);
        return;
      }
      if (paramString.equalsIgnoreCase("logout"))
      {
        parseLogoutEvent(paramJSONObject);
        return;
      }
      if (paramString.equalsIgnoreCase("receive"))
      {
        parseRecvEvent(paramJSONObject);
        return;
      }
      if (paramString.equalsIgnoreCase("onlinelist"))
      {
        parseOnlineEvent(paramJSONObject);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("notify"));
    parseRecvEvent(paramJSONObject);
  }

  public void onConnect()
  {
    this.connected = true;
    this.connecting = false;
    connectedChatRoom();
    if (this.autoJoin)
      join(this.connectUrl, this.roomId, this.recordCnt);
    if (this.autoGet)
    {
      getHistory(this.connectUrl, this.roomId, this.recordCnt);
      this.autoGet = false;
    }
    if (!this.loginSuccess)
    {
      login(this.mUser);
      this.autoLogin = false;
    }
  }

  public void onConnectFailure()
  {
    this.connected = false;
    this.connecting = false;
    if (this._socketClient != null)
      this._socketClient.disconnect();
    if (this.connectRetry > 0)
      connect(this.connectUrl, this.roomId);
    while (true)
    {
      this.connectRetry -= 1;
      return;
      if (this.mContext == null);
    }
  }

  public void onDisconnect()
  {
    this.connected = false;
    this.loginSuccess = false;
    this.connecting = false;
  }

  public void onMessage(String paramString)
  {
  }

  public void onMessage(JSONObject paramJSONObject)
  {
  }

  public void send(String paramString1, UserInfo paramUserInfo, String paramString2, int paramInt)
  {
    if (paramString1 == null)
      return;
    if (!this.connected)
    {
      connect(this.connectUrl, this.roomId);
      return;
    }
    while (true)
    {
      JSONObject localJSONObject1;
      try
      {
        paramString2 = new JSONObject();
        localJSONObject1 = new JSONObject();
        localJSONObject2 = new JSONObject();
        localJSONObject2.putOpt("user_id", this.mUser.userId);
        localJSONObject2.putOpt("sns_id", this.mUser.snsInfo.sns_id);
        localJSONObject2.putOpt("sns_name", this.mUser.snsInfo.sns_name);
        localJSONObject2.putOpt("sns_avatar", this.mUser.snsInfo.sns_avatar);
        localJSONObject2.putOpt("sns_site", this.mUser.snsInfo.sns_site);
        localJSONObject2.putOpt("sns_gender", this.mUser.snsInfo.sns_gender);
        localJSONObject2.putOpt("qt_id", this.mUser.userKey);
        localJSONObject2.putOpt("source", this.mUser.snsInfo.source);
        paramString2.put("from", localJSONObject2);
        if (paramUserInfo == null)
        {
          paramString2.put("type", "chat");
          localJSONObject1.put("message", paramString1);
          if (paramInt == 3)
            localJSONObject1.put("type", "private");
          paramString2.put("data", localJSONObject1);
          paramString2.put("room", String.valueOf(this.roomId));
          paramUserInfo = new JSONArray();
          paramUserInfo.put(paramString2);
          this._socketClient.emit("send", paramUserInfo);
          paramUserInfo = InfoManager.getInstance().getWeiboIdByGroupId(this.roomId);
          if (paramUserInfo == null)
            break;
          WeiboChat.getInstance().comment(paramUserInfo, paramString1);
          return;
        }
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        return;
      }
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.putOpt("user_id", paramUserInfo.userId);
      localJSONObject2.putOpt("sns_id", paramUserInfo.snsInfo.sns_id);
      localJSONObject2.putOpt("sns_name", paramUserInfo.snsInfo.sns_name);
      localJSONObject2.putOpt("sns_avatar", paramUserInfo.snsInfo.sns_avatar);
      localJSONObject2.putOpt("sns_site", paramUserInfo.snsInfo.sns_site);
      localJSONObject2.putOpt("source", paramUserInfo.snsInfo.source);
      localJSONObject2.putOpt("qt_id", paramUserInfo.userKey);
      paramString2.put("type", "chat");
      localJSONObject1.put("to", localJSONObject2);
      localJSONObject1.put("message", "" + paramString1);
    }
  }

  public void speakTo(UserInfo paramUserInfo, String paramString)
  {
    if (paramUserInfo == null)
      return;
    if (!this.connected)
    {
      connect(this.connectUrl, this.roomId);
      return;
    }
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject1.put("type", "chat");
      localJSONObject2.put("message", "@" + paramUserInfo.snsInfo.sns_name + "," + paramString);
      localJSONObject1.put("data", localJSONObject2);
      localJSONObject1.put("room", String.valueOf(this.roomId));
      paramUserInfo = new JSONArray();
      paramUserInfo.put(localJSONObject1);
      this._socketClient.emit("send", paramUserInfo);
      return;
    }
    catch (Exception paramUserInfo)
    {
      paramUserInfo.printStackTrace();
    }
  }

  public void tellSongName(UserInfo paramUserInfo, String paramString)
  {
    if (!this.connected)
      connect(this.connectUrl, this.roomId);
    while ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject1.put("type", "chat");
      JSONObject localJSONObject3 = new JSONObject();
      localJSONObject3.putOpt("user_id", this.mUser.userId);
      localJSONObject3.putOpt("sns_id", this.mUser.snsInfo.sns_id);
      localJSONObject3.putOpt("sns_name", this.mUser.snsInfo.sns_name);
      localJSONObject3.putOpt("sns_avatar", this.mUser.snsInfo.sns_avatar);
      localJSONObject3.putOpt("sns_site", this.mUser.snsInfo.sns_site);
      localJSONObject3.putOpt("sns_gender", this.mUser.snsInfo.sns_gender);
      localJSONObject3.putOpt("qt_id", this.mUser.userKey);
      localJSONObject3.putOpt("source", this.mUser.snsInfo.source);
      localJSONObject1.put("from", localJSONObject3);
      if ((paramUserInfo != null) && (paramUserInfo.userId != null))
      {
        localJSONObject3 = new JSONObject();
        localJSONObject3.putOpt("user_id", paramUserInfo.userId);
        localJSONObject3.putOpt("sns_id", paramUserInfo.snsInfo.sns_id);
        localJSONObject3.putOpt("sns_name", paramUserInfo.snsInfo.sns_name);
        localJSONObject3.putOpt("sns_avatar", paramUserInfo.snsInfo.sns_avatar);
        localJSONObject3.putOpt("sns_site", paramUserInfo.snsInfo.sns_site);
        localJSONObject3.putOpt("qt_id", paramUserInfo.userKey);
        localJSONObject3.putOpt("source", paramUserInfo.snsInfo.source);
        localJSONObject2.put("to", localJSONObject3);
      }
      localJSONObject2.put("type", "songname");
      localJSONObject2.put("message", paramString);
      localJSONObject1.put("data", localJSONObject2);
      localJSONObject1.put("room", String.valueOf(this.roomId));
      paramUserInfo = new JSONArray();
      paramUserInfo.put(localJSONObject1);
      this._socketClient.emit("send", paramUserInfo);
      return;
    }
    catch (Exception paramUserInfo)
    {
      paramUserInfo.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.QTChat
 * JD-Core Version:    0.6.2
 */