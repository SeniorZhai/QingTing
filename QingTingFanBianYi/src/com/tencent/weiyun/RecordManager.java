package com.tencent.weiyun;

import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.Util;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecordManager extends BaseApi
{
  public RecordManager(Context paramContext, QQAuth paramQQAuth, QQToken paramQQToken)
  {
    super(paramContext, paramQQAuth, paramQQToken);
  }

  public RecordManager(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
  }

  public void checkRecord(String paramString, final IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    paramIUiListener = new BaseApi.TempRequestListener(this, new IUiListener()
    {
      public void onCancel()
      {
        paramIUiListener.onCancel();
      }

      public void onComplete(Object paramAnonymousObject)
      {
        paramAnonymousObject = (JSONObject)paramAnonymousObject;
        try
        {
          if (paramAnonymousObject.getInt("ret") == 0)
          {
            paramIUiListener.onComplete(Boolean.TRUE);
            return;
          }
          paramIUiListener.onComplete(Boolean.FALSE);
          return;
        }
        catch (JSONException paramAnonymousObject)
        {
          paramIUiListener.onError(new UiError(-4, paramAnonymousObject.getMessage(), null));
        }
      }

      public void onError(UiError paramAnonymousUiError)
      {
        paramIUiListener.onError(paramAnonymousUiError);
      }
    });
    localBundle.putString("key", Util.toHexString(paramString));
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://graph.qq.com/weiyun/check_record", localBundle, "GET", paramIUiListener);
  }

  public void createRecord(String paramString1, String paramString2, final IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    paramIUiListener = new BaseApi.TempRequestListener(this, new IUiListener()
    {
      public void onCancel()
      {
        paramIUiListener.onCancel();
      }

      public void onComplete(Object paramAnonymousObject)
      {
        paramAnonymousObject = (JSONObject)paramAnonymousObject;
        try
        {
          if (paramAnonymousObject.getInt("ret") == 0)
          {
            paramIUiListener.onComplete("");
            return;
          }
          paramIUiListener.onError(new UiError(-4, paramAnonymousObject.toString(), null));
          return;
        }
        catch (JSONException paramAnonymousObject)
        {
          paramIUiListener.onError(new UiError(-4, paramAnonymousObject.getMessage(), null));
        }
      }

      public void onError(UiError paramAnonymousUiError)
      {
        paramIUiListener.onError(paramAnonymousUiError);
      }
    });
    localBundle.putString("key", Util.toHexString(paramString1));
    localBundle.putByteArray("value", Util.toHexString(paramString2).getBytes());
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://graph.qq.com/weiyun/create_record", localBundle, "POST", paramIUiListener);
  }

  public void deleteRecord(String paramString, final IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    paramIUiListener = new BaseApi.TempRequestListener(this, new IUiListener()
    {
      public void onCancel()
      {
        paramIUiListener.onCancel();
      }

      public void onComplete(Object paramAnonymousObject)
      {
        paramAnonymousObject = (JSONObject)paramAnonymousObject;
        try
        {
          if (paramAnonymousObject.getInt("ret") == 0)
          {
            paramIUiListener.onComplete("");
            return;
          }
          paramIUiListener.onError(new UiError(-4, paramAnonymousObject.toString(), null));
          return;
        }
        catch (JSONException paramAnonymousObject)
        {
          paramIUiListener.onError(new UiError(-4, paramAnonymousObject.getMessage(), null));
        }
      }

      public void onError(UiError paramAnonymousUiError)
      {
        paramIUiListener.onError(paramAnonymousUiError);
      }
    });
    localBundle.putString("key", Util.toHexString(paramString));
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://graph.qq.com/weiyun/delete_record", localBundle, "GET", paramIUiListener);
  }

  public void getRecord(String paramString, final IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    paramIUiListener = new BaseApi.TempRequestListener(this, new IUiListener()
    {
      public void onCancel()
      {
        paramIUiListener.onCancel();
      }

      public void onComplete(Object paramAnonymousObject)
      {
        paramAnonymousObject = (JSONObject)paramAnonymousObject;
        try
        {
          if (paramAnonymousObject.getInt("ret") == 0)
          {
            paramAnonymousObject = paramAnonymousObject.getJSONObject("data").getString("value");
            paramIUiListener.onComplete(Util.hexToString(paramAnonymousObject));
            return;
          }
          paramIUiListener.onError(new UiError(-4, paramAnonymousObject.toString(), null));
          return;
        }
        catch (JSONException paramAnonymousObject)
        {
          paramIUiListener.onError(new UiError(-4, paramAnonymousObject.getMessage(), null));
        }
      }

      public void onError(UiError paramAnonymousUiError)
      {
        paramIUiListener.onError(paramAnonymousUiError);
      }
    });
    localBundle.putString("key", Util.toHexString(paramString));
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://graph.qq.com/weiyun/get_record", localBundle, "GET", paramIUiListener);
  }

  public void modifyRecord(String paramString1, String paramString2, final IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    paramIUiListener = new BaseApi.TempRequestListener(this, new IUiListener()
    {
      public void onCancel()
      {
        paramIUiListener.onCancel();
      }

      public void onComplete(Object paramAnonymousObject)
      {
        paramAnonymousObject = (JSONObject)paramAnonymousObject;
        try
        {
          if (paramAnonymousObject.getInt("ret") == 0)
          {
            paramIUiListener.onComplete("");
            return;
          }
          paramIUiListener.onError(new UiError(-4, paramAnonymousObject.toString(), null));
          return;
        }
        catch (JSONException paramAnonymousObject)
        {
          paramIUiListener.onError(new UiError(-4, paramAnonymousObject.getMessage(), null));
        }
      }

      public void onError(UiError paramAnonymousUiError)
      {
        paramIUiListener.onError(paramAnonymousUiError);
      }
    });
    localBundle.putString("key", Util.toHexString(paramString1));
    localBundle.putByteArray("value", Util.toHexString(paramString2).getBytes());
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://graph.qq.com/weiyun/modify_record", localBundle, "POST", paramIUiListener);
  }

  public void queryAllRecord(final IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    paramIUiListener = new BaseApi.TempRequestListener(this, new IUiListener()
    {
      public void onCancel()
      {
        paramIUiListener.onCancel();
      }

      public void onComplete(Object paramAnonymousObject)
      {
        Object localObject = (JSONObject)paramAnonymousObject;
        try
        {
          if (((JSONObject)localObject).getInt("ret") == 0)
          {
            paramAnonymousObject = new ArrayList();
            localObject = ((JSONObject)localObject).getJSONObject("data");
            if (!((JSONObject)localObject).isNull("keys"))
            {
              localObject = ((JSONObject)localObject).getJSONArray("keys");
              int i = 0;
              while (i < ((JSONArray)localObject).length())
              {
                paramAnonymousObject.add(Util.hexToString(((JSONArray)localObject).getJSONObject(i).getString("key")));
                i += 1;
              }
            }
            paramIUiListener.onComplete(paramAnonymousObject);
            return;
          }
          paramIUiListener.onError(new UiError(-4, ((JSONObject)localObject).toString(), null));
          return;
        }
        catch (JSONException paramAnonymousObject)
        {
          paramIUiListener.onError(new UiError(-4, paramAnonymousObject.getMessage(), null));
        }
      }

      public void onError(UiError paramAnonymousUiError)
      {
        paramIUiListener.onError(paramAnonymousUiError);
      }
    });
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://graph.qq.com/weiyun/query_all_record", localBundle, "GET", paramIUiListener);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weiyun.RecordManager
 * JD-Core Version:    0.6.2
 */