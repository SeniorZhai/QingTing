package com.umeng.fb.net;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.fb.model.DevReply;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.Store;
import com.umeng.fb.model.UserInfo;
import com.umeng.fb.model.UserReply;
import com.umeng.fb.model.UserTitleReply;
import com.umeng.fb.util.Helper;
import com.umeng.fb.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FbClient
{
  public static final String FEEDBACK_BASE_URL = "http://feedback.umeng.com/feedback";
  public static final String FEEDBACK_Dev_Reply_URL = "http://feedback.umeng.com/feedback/reply";
  public static final String FEEDBACK_NewFeedback_URL = "http://feedback.umeng.com/feedback/feedbacks";
  public static final String FEEDBACK_UER_REPLY_URL = "http://feedback.umeng.com/feedback/reply";
  private static final int REGISTRATION_TIMEOUT_MS = 30000;
  private static final String TAG = FbClient.class.getName();
  private Context mContext;

  public FbClient(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private void addRequestHeader(JSONObject paramJSONObject)
  {
    try
    {
      JSONObject localJSONObject = Helper.getMessageHeader(this.mContext);
      Log.d(TAG, "addRequestHeader: " + localJSONObject.toString());
      Iterator localIterator = localJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        paramJSONObject.put(str, localJSONObject.get(str));
      }
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }

  private void addUserInfoIfNotSynced(JSONObject paramJSONObject)
  {
    try
    {
      long l1 = Store.getInstance(this.mContext).getUserInfoLastSyncAt();
      long l2 = Store.getInstance(this.mContext).getUserInfoLastUpdateAt();
      Log.d(TAG, "addUserInfoIfNotSynced: last_sync_at=" + l1 + " last_update_at=" + l2);
      if (l1 < l2)
        paramJSONObject.put("userinfo", Store.getInstance(this.mContext).getUserInfo().toJson());
      return;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }

  private boolean sendUserReply(UserReply paramUserReply)
  {
    try
    {
      paramUserReply = paramUserReply.toJson();
      addRequestHeader(paramUserReply);
      addUserInfoIfNotSynced(paramUserReply);
      paramUserReply = execute(new FbRequest("reply", paramUserReply, "http://feedback.umeng.com/feedback/reply"));
      if (paramUserReply != null)
      {
        boolean bool = "ok".equalsIgnoreCase(paramUserReply.getJson().get("state").toString());
        if (bool)
          return true;
      }
    }
    catch (Exception paramUserReply)
    {
      paramUserReply.printStackTrace();
    }
    return false;
  }

  private boolean sendUserTitleReply(UserTitleReply paramUserTitleReply)
  {
    try
    {
      paramUserTitleReply = paramUserTitleReply.toJson();
      addRequestHeader(paramUserTitleReply);
      addUserInfoIfNotSynced(paramUserTitleReply);
      paramUserTitleReply = execute(new FbRequest("feedback", paramUserTitleReply, "http://feedback.umeng.com/feedback/feedbacks"));
      if (paramUserTitleReply != null)
      {
        boolean bool = "ok".equalsIgnoreCase(paramUserTitleReply.getJson().get("state").toString());
        if (bool)
          return true;
      }
    }
    catch (Exception paramUserTitleReply)
    {
      paramUserTitleReply.printStackTrace();
    }
    return false;
  }

  // ERROR //
  public FbResponse execute(FbRequest paramFbRequest)
  {
    // Byte code:
    //   0: new 179	java/util/Random
    //   3: dup
    //   4: invokespecial 180	java/util/Random:<init>	()V
    //   7: sipush 1000
    //   10: invokevirtual 184	java/util/Random:nextInt	(I)I
    //   13: istore 5
    //   15: aload_1
    //   16: getfield 187	com/umeng/fb/net/FbRequest:mReportContent	Ljava/lang/String;
    //   19: astore_2
    //   20: aload_1
    //   21: getfield 190	com/umeng/fb/net/FbRequest:mKey	Ljava/lang/String;
    //   24: astore_3
    //   25: aload_1
    //   26: getfield 194	com/umeng/fb/net/FbRequest:mValue	Lorg/json/JSONObject;
    //   29: astore 4
    //   31: aload_1
    //   32: instanceof 142
    //   35: ifne +13 -> 48
    //   38: getstatic 31	com/umeng/fb/net/FbClient:TAG	Ljava/lang/String;
    //   41: ldc 196
    //   43: invokestatic 199	com/umeng/fb/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   46: aconst_null
    //   47: areturn
    //   48: aload_2
    //   49: invokevirtual 203	java/lang/String:length	()I
    //   52: iconst_1
    //   53: if_icmpgt +31 -> 84
    //   56: getstatic 31	com/umeng/fb/net/FbClient:TAG	Ljava/lang/String;
    //   59: new 50	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   66: iload 5
    //   68: invokevirtual 206	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   71: ldc 208
    //   73: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: invokestatic 199	com/umeng/fb/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   82: aconst_null
    //   83: areturn
    //   84: aload_3
    //   85: ifnull +282 -> 367
    //   88: getstatic 31	com/umeng/fb/net/FbClient:TAG	Ljava/lang/String;
    //   91: new 50	java/lang/StringBuilder
    //   94: dup
    //   95: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   98: iload 5
    //   100: invokevirtual 206	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   103: ldc 210
    //   105: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: aload_2
    //   109: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: ldc 212
    //   114: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: aload 4
    //   119: invokevirtual 62	org/json/JSONObject:toString	()Ljava/lang/String;
    //   122: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: invokestatic 215	com/umeng/fb/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   131: new 217	java/util/ArrayList
    //   134: dup
    //   135: iconst_1
    //   136: invokespecial 220	java/util/ArrayList:<init>	(I)V
    //   139: astore_1
    //   140: aload_1
    //   141: new 222	org/apache/http/message/BasicNameValuePair
    //   144: dup
    //   145: aload_3
    //   146: aload 4
    //   148: invokevirtual 62	org/json/JSONObject:toString	()Ljava/lang/String;
    //   151: invokespecial 224	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   154: invokeinterface 230 2 0
    //   159: pop
    //   160: new 232	org/apache/http/client/entity/UrlEncodedFormEntity
    //   163: dup
    //   164: aload_1
    //   165: ldc 234
    //   167: invokespecial 237	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   170: astore_3
    //   171: new 239	org/apache/http/client/methods/HttpPost
    //   174: dup
    //   175: aload_2
    //   176: invokespecial 242	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   179: astore_1
    //   180: aload_1
    //   181: aload_3
    //   182: invokeinterface 248 1 0
    //   187: invokeinterface 254 2 0
    //   192: aload_1
    //   193: checkcast 239	org/apache/http/client/methods/HttpPost
    //   196: aload_3
    //   197: invokevirtual 258	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   200: new 260	org/apache/http/impl/client/DefaultHttpClient
    //   203: dup
    //   204: invokespecial 261	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   207: astore_3
    //   208: aload_3
    //   209: invokeinterface 267 1 0
    //   214: astore 4
    //   216: aload 4
    //   218: sipush 30000
    //   221: invokestatic 273	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   224: aload 4
    //   226: sipush 30000
    //   229: invokestatic 276	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   232: aload 4
    //   234: ldc2_w 277
    //   237: invokestatic 284	org/apache/http/conn/params/ConnManagerParams:setTimeout	(Lorg/apache/http/params/HttpParams;J)V
    //   240: aload_3
    //   241: aload_1
    //   242: checkcast 286	org/apache/http/client/methods/HttpUriRequest
    //   245: invokeinterface 289 2 0
    //   250: astore_1
    //   251: aload_1
    //   252: invokeinterface 295 1 0
    //   257: invokeinterface 300 1 0
    //   262: sipush 200
    //   265: if_icmpne +180 -> 445
    //   268: aload_1
    //   269: invokeinterface 304 1 0
    //   274: invokestatic 309	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;)Ljava/lang/String;
    //   277: astore_1
    //   278: getstatic 31	com/umeng/fb/net/FbClient:TAG	Ljava/lang/String;
    //   281: new 50	java/lang/StringBuilder
    //   284: dup
    //   285: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   288: ldc_w 311
    //   291: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: aload_1
    //   295: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   301: invokestatic 215	com/umeng/fb/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   304: new 155	com/umeng/fb/net/FbResponse
    //   307: dup
    //   308: new 59	org/json/JSONObject
    //   311: dup
    //   312: aload_1
    //   313: invokespecial 312	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   316: invokespecial 314	com/umeng/fb/net/FbResponse:<init>	(Lorg/json/JSONObject;)V
    //   319: astore_1
    //   320: aload_1
    //   321: areturn
    //   322: astore_1
    //   323: getstatic 31	com/umeng/fb/net/FbClient:TAG	Ljava/lang/String;
    //   326: new 50	java/lang/StringBuilder
    //   329: dup
    //   330: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   333: iload 5
    //   335: invokevirtual 206	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   338: ldc_w 316
    //   341: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: aload_2
    //   345: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   351: aload_1
    //   352: invokestatic 319	com/umeng/fb/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   355: aconst_null
    //   356: areturn
    //   357: astore_1
    //   358: new 321	java/lang/AssertionError
    //   361: dup
    //   362: aload_1
    //   363: invokespecial 324	java/lang/AssertionError:<init>	(Ljava/lang/Object;)V
    //   366: athrow
    //   367: getstatic 31	com/umeng/fb/net/FbClient:TAG	Ljava/lang/String;
    //   370: new 50	java/lang/StringBuilder
    //   373: dup
    //   374: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   377: iload 5
    //   379: invokevirtual 206	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   382: ldc_w 326
    //   385: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   388: aload_2
    //   389: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   395: invokestatic 215	com/umeng/fb/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   398: new 328	org/apache/http/client/methods/HttpGet
    //   401: dup
    //   402: aload_2
    //   403: invokespecial 329	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   406: astore_1
    //   407: goto -207 -> 200
    //   410: astore_1
    //   411: getstatic 31	com/umeng/fb/net/FbClient:TAG	Ljava/lang/String;
    //   414: new 50	java/lang/StringBuilder
    //   417: dup
    //   418: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   421: iload 5
    //   423: invokevirtual 206	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   426: ldc_w 331
    //   429: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   432: aload_2
    //   433: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   439: aload_1
    //   440: invokestatic 319	com/umeng/fb/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   443: aconst_null
    //   444: areturn
    //   445: aconst_null
    //   446: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   240	320	322	org/apache/http/client/ClientProtocolException
    //   160	171	357	java/io/UnsupportedEncodingException
    //   240	320	410	java/lang/Exception
  }

  public List<DevReply> getDevReply(List<String> paramList, String paramString1, String paramString2)
  {
    if ((paramList == null) || (paramList.size() == 0) || (TextUtils.isEmpty(paramString2)))
      paramList = null;
    while (true)
    {
      return paramList;
      StringBuilder localStringBuilder = new StringBuilder();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        String str = (String)paramList.next();
        if (!TextUtils.isEmpty(str))
        {
          localStringBuilder.append(str);
          localStringBuilder.append(",");
        }
      }
      if (localStringBuilder.length() > 1)
        localStringBuilder.replace(localStringBuilder.length() - 1, localStringBuilder.length(), "");
      paramList = new StringBuilder("http://feedback.umeng.com/feedback/reply");
      paramList.append("?appkey=" + paramString2);
      paramList.append("&feedback_id=" + localStringBuilder);
      if (!TextUtils.isEmpty(paramString1))
        paramList.append("&startkey=" + paramString1);
      Log.d(TAG, "getDevReply url: " + paramList);
      paramString1 = new DefaultHttpClient();
      paramString2 = paramString1.getParams();
      HttpConnectionParams.setConnectionTimeout(paramString2, 30000);
      HttpConnectionParams.setSoTimeout(paramString2, 30000);
      ConnManagerParams.setTimeout(paramString2, 30000L);
      paramList = new HttpGet(paramList.toString());
      try
      {
        paramList = paramString1.execute((HttpUriRequest)paramList);
        if (paramList.getStatusLine().getStatusCode() == 200)
        {
          paramList = EntityUtils.toString(paramList.getEntity());
          Log.d(TAG, "getDevReply resp: " + paramList);
          paramString2 = new JSONArray(paramList);
          paramString1 = new ArrayList();
          int i = 0;
          while (true)
          {
            int j = paramString2.length();
            paramList = paramString1;
            if (i >= j)
              break;
            try
            {
              paramList = paramString2.getJSONArray(i);
              j = 0;
              while (true)
              {
                int k = paramList.length();
                if (j < k)
                  try
                  {
                    paramString1.add(new DevReply(paramList.getJSONObject(j)));
                    j += 1;
                  }
                  catch (JSONException localJSONException)
                  {
                    while (true)
                      localJSONException.printStackTrace();
                  }
              }
            }
            catch (JSONException paramList)
            {
              paramList.printStackTrace();
              i += 1;
            }
          }
        }
      }
      catch (Exception paramList)
      {
        paramList.printStackTrace();
      }
    }
    return null;
  }

  public boolean sendReply(Reply paramReply)
    throws IllegalArgumentException
  {
    if (paramReply == null)
      return true;
    if ((paramReply instanceof UserReply))
      return sendUserReply((UserReply)paramReply);
    if ((paramReply instanceof UserTitleReply))
      return sendUserTitleReply((UserTitleReply)paramReply);
    throw new IllegalArgumentException("Illegal argument: " + paramReply.getClass().getName() + ". reply must be " + UserReply.class.getName() + " or " + UserTitleReply.class.getName() + ".");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.net.FbClient
 * JD-Core Version:    0.6.2
 */