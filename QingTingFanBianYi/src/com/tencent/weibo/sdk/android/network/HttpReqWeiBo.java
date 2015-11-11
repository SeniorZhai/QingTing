package com.tencent.weibo.sdk.android.network;

import android.content.Context;
import android.util.Log;
import com.tencent.weibo.sdk.android.api.util.JsonUtil;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.model.ModelResult;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpReqWeiBo extends HttpReq
{
  private Context mContext;
  private Integer mResultType = Integer.valueOf(0);
  private Class<? extends BaseVO> mTargetClass;
  private Class<? extends BaseVO> mTargetClass2;

  public HttpReqWeiBo(Context paramContext, String paramString1, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, String paramString2, Integer paramInteger)
  {
    this.mContext = paramContext;
    this.mHost = "192.168.1.100";
    this.mPort = 8088;
    this.mUrl = paramString1;
    this.mCallBack = paramHttpCallback;
    this.mTargetClass = paramClass;
    this.mResultType = paramInteger;
    this.mMethod = paramString2;
  }

  protected Object processResponse(InputStream paramInputStream)
    throws Exception
  {
    ModelResult localModelResult = new ModelResult();
    if (paramInputStream != null)
    {
      paramInputStream = new InputStreamReader(paramInputStream);
      Object localObject1 = new BufferedReader(paramInputStream);
      Object localObject2 = new StringBuffer();
      while (true)
      {
        str = ((BufferedReader)localObject1).readLine();
        if (str == null)
        {
          ((BufferedReader)localObject1).close();
          paramInputStream.close();
          Log.d("relst", ((StringBuffer)localObject2).toString());
          if ((((StringBuffer)localObject2).toString().indexOf("errcode") != -1) || (((StringBuffer)localObject2).toString().indexOf("access_token") == -1))
            break;
          localModelResult.setObj(((StringBuffer)localObject2).toString());
          return localModelResult;
        }
        ((StringBuffer)localObject2).append(str);
      }
      localObject1 = new JSONObject(((StringBuffer)localObject2).toString());
      paramInputStream = null;
      if (this.mTargetClass != null)
        paramInputStream = (BaseVO)this.mTargetClass.newInstance();
      localObject2 = ((JSONObject)localObject1).getString("errcode");
      String str = ((JSONObject)localObject1).getString("msg");
      if ((localObject2 != null) && ("0".equals(localObject2)))
      {
        localModelResult.setSuccess(true);
        switch (this.mResultType.intValue())
        {
        default:
          return localModelResult;
        case 0:
          paramInputStream = JsonUtil.jsonToObject(this.mTargetClass, (JSONObject)localObject1);
          localObject1 = new ArrayList();
          ((List)localObject1).add(paramInputStream);
          localModelResult.setList((List)localObject1);
          return localModelResult;
        case 1:
          paramInputStream = paramInputStream.analyseHead((JSONObject)localObject1);
          localObject1 = (JSONArray)paramInputStream.get("array");
          localObject1 = JsonUtil.jsonToList(this.mTargetClass, (JSONArray)localObject1);
          int i;
          int j;
          if (paramInputStream.get("total") == null)
          {
            i = 0;
            if (paramInputStream.get("p") != null)
              break label415;
            j = 1;
            if (paramInputStream.get("ps") != null)
              break label434;
          }
          for (int k = 1; ; k = ((Integer)paramInputStream.get("ps")).intValue())
          {
            boolean bool = ((Boolean)paramInputStream.get("isLastPage")).booleanValue();
            localModelResult.setList((List)localObject1);
            localModelResult.setTotal(Integer.valueOf(i).intValue());
            localModelResult.setP(Integer.valueOf(j).intValue());
            localModelResult.setPs(Integer.valueOf(k).intValue());
            localModelResult.setLastPage(bool);
            return localModelResult;
            i = ((Integer)paramInputStream.get("total")).intValue();
            break;
            j = ((Integer)paramInputStream.get("p")).intValue();
            break label317;
          }
        case 2:
          localModelResult.setObj(JsonUtil.jsonToObject(this.mTargetClass, (JSONObject)localObject1));
          return localModelResult;
        case 3:
          label317: paramInputStream = JsonUtil.jsonToObject(this.mTargetClass, (JSONObject)localObject1);
          label415: label434: localObject1 = ((JSONObject)localObject1).getJSONArray("result_list");
          localObject1 = JsonUtil.jsonToList(this.mTargetClass2, (JSONArray)localObject1);
          localModelResult.setObj(paramInputStream);
          localModelResult.setList((List)localObject1);
          return localModelResult;
        case 4:
        }
        localModelResult.setObj(localObject1);
        return localModelResult;
      }
      localModelResult.setSuccess(false);
      localModelResult.setError_message(str);
      return localModelResult;
    }
    localModelResult.setSuccess(false);
    localModelResult.setError_message("请求失败");
    return localModelResult;
  }

  public void setReq(String paramString)
    throws Exception
  {
    if ("POST".equals(this.mMethod))
    {
      paramString = new HttpReq.UTF8PostMethod(this.mUrl);
      this.mParam.toString();
      paramString.setRequestEntity(new ByteArrayRequestEntity(this.mParam.toString().getBytes("utf-8")));
    }
  }

  protected void setReq(HttpMethod paramHttpMethod)
    throws Exception
  {
    if ("POST".equals(this.mMethod))
    {
      paramHttpMethod = (PostMethod)paramHttpMethod;
      this.mParam.toString();
      paramHttpMethod.addParameter("Connection", "Keep-Alive");
      paramHttpMethod.addParameter("Charset", "UTF-8");
      paramHttpMethod.setRequestEntity(new ByteArrayRequestEntity(this.mParam.toString().getBytes("utf-8")));
    }
  }

  public void setmResultType(Integer paramInteger)
  {
    this.mResultType = paramInteger;
  }

  public void setmTargetClass(Class<? extends BaseVO> paramClass)
  {
    this.mTargetClass = paramClass;
  }

  public void setmTargetClass2(Class<? extends BaseVO> paramClass)
  {
    this.mTargetClass2 = paramClass;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.network.HttpReqWeiBo
 * JD-Core Version:    0.6.2
 */