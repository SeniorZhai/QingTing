package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.sdk.cons.GlobalConstants;
import org.json.JSONObject;

public class ElementAction
{
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private boolean f;
  private boolean g = true;
  private boolean h = true;
  private String i;
  private String j;
  private String k;
  private JSONObject l;

  private ElementAction(String paramString)
  {
    this.a = paramString;
  }

  public static ElementAction a(String paramString, ActionType paramActionType)
  {
    return a(paramString, paramActionType.f(), paramActionType.l(), paramActionType.g(), paramActionType.h(), paramActionType.i(), paramActionType.j(), paramActionType.k(), paramActionType.c(), paramActionType.d(), paramActionType.b(), paramActionType.a());
  }

  private static ElementAction a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString6, String paramString7, String paramString8, JSONObject paramJSONObject)
  {
    Object localObject = null;
    if (TextUtils.isEmpty(paramString1))
      return null;
    ElementAction localElementAction = new ElementAction(paramString1);
    localElementAction.a = paramString1;
    if (TextUtils.isEmpty(paramString2));
    for (paramString1 = localObject; ; paramString1 = paramString2.trim())
    {
      localElementAction.b = paramString1;
      localElementAction.c = paramString3;
      localElementAction.d = paramString4;
      localElementAction.e = paramString5;
      localElementAction.f = paramBoolean1;
      localElementAction.g = paramBoolean2;
      localElementAction.h = paramBoolean3;
      localElementAction.i = paramString6;
      localElementAction.j = paramString7;
      localElementAction.k = paramString8;
      localElementAction.l = paramJSONObject;
      return localElementAction;
    }
  }

  private static ElementAction a(JSONObject paramJSONObject)
  {
    Object localObject2 = null;
    boolean bool4 = true;
    if ((paramJSONObject != null) && (paramJSONObject.has("name")));
    for (String str1 = paramJSONObject.optString("name"); ; str1 = null)
    {
      if ((paramJSONObject != null) && (paramJSONObject.has("host")));
      for (String str2 = paramJSONObject.optString("host"); ; str2 = null)
      {
        if ((paramJSONObject != null) && (paramJSONObject.has("params")));
        for (String str3 = paramJSONObject.optString("params"); ; str3 = null)
        {
          if ((paramJSONObject != null) && (paramJSONObject.has("enctype")));
          for (String str4 = paramJSONObject.optString("enctype"); ; str4 = null)
          {
            Object localObject1 = localObject2;
            if (paramJSONObject != null)
            {
              localObject1 = localObject2;
              if (paramJSONObject.has("request_param"))
                localObject1 = paramJSONObject.optString("request_param");
            }
            if ((paramJSONObject != null) && (paramJSONObject.has("validate")));
            for (boolean bool2 = paramJSONObject.optBoolean("validate", true); ; bool2 = true)
            {
              boolean bool1;
              if ((paramJSONObject != null) && (paramJSONObject.has("https")))
                if (!paramJSONObject.optBoolean("https"))
                  bool1 = true;
              while (true)
              {
                boolean bool3 = bool4;
                if (paramJSONObject != null)
                {
                  bool3 = bool4;
                  if (paramJSONObject.has("formSubmit"))
                    bool3 = paramJSONObject.optBoolean("formSubmit");
                }
                Object localObject3 = "";
                localObject2 = localObject3;
                if (paramJSONObject != null)
                {
                  localObject2 = localObject3;
                  if (paramJSONObject.has("namespace"))
                    localObject2 = paramJSONObject.optString("namespace");
                }
                Object localObject4 = "";
                localObject3 = localObject4;
                if (paramJSONObject != null)
                {
                  localObject3 = localObject4;
                  if (paramJSONObject.has("apiVersion"))
                    localObject3 = paramJSONObject.optString("apiVersion");
                }
                String str5 = "";
                localObject4 = str5;
                if (paramJSONObject != null)
                {
                  localObject4 = str5;
                  if (paramJSONObject.has("apiName"))
                    localObject4 = paramJSONObject.optString("apiName");
                }
                return a(str1, str2, str3, str4, (String)localObject1, bool2, bool1, bool3, (String)localObject2, (String)localObject3, (String)localObject4, paramJSONObject);
                bool1 = false;
                continue;
                bool1 = true;
              }
            }
          }
        }
      }
    }
  }

  public static ElementAction a(JSONObject paramJSONObject, String paramString)
  {
    Object localObject2 = null;
    boolean bool4 = true;
    JSONObject localJSONObject = paramJSONObject.optJSONObject(paramString);
    if ((localJSONObject != null) && (localJSONObject.has("name")));
    for (paramJSONObject = localJSONObject.optString("name"); ; paramJSONObject = null)
    {
      if ((localJSONObject != null) && (localJSONObject.has("host")));
      for (paramString = localJSONObject.optString("host"); ; paramString = null)
      {
        if ((localJSONObject != null) && (localJSONObject.has("params")));
        for (String str1 = localJSONObject.optString("params"); ; str1 = null)
        {
          if ((localJSONObject != null) && (localJSONObject.has("enctype")));
          for (String str2 = localJSONObject.optString("enctype"); ; str2 = null)
          {
            Object localObject1 = localObject2;
            if (localJSONObject != null)
            {
              localObject1 = localObject2;
              if (localJSONObject.has("request_param"))
                localObject1 = localJSONObject.optString("request_param");
            }
            if ((localJSONObject != null) && (localJSONObject.has("validate")));
            for (boolean bool2 = localJSONObject.optBoolean("validate", true); ; bool2 = true)
            {
              boolean bool1;
              if ((localJSONObject != null) && (localJSONObject.has("https")))
                if (!localJSONObject.optBoolean("https"))
                  bool1 = true;
              while (true)
              {
                boolean bool3 = bool4;
                if (localJSONObject != null)
                {
                  bool3 = bool4;
                  if (localJSONObject.has("formSubmit"))
                    bool3 = localJSONObject.optBoolean("formSubmit");
                }
                Object localObject3 = "";
                localObject2 = localObject3;
                if (localJSONObject != null)
                {
                  localObject2 = localObject3;
                  if (localJSONObject.has("namespace"))
                    localObject2 = localJSONObject.optString("namespace");
                }
                Object localObject4 = "";
                localObject3 = localObject4;
                if (localJSONObject != null)
                {
                  localObject3 = localObject4;
                  if (localJSONObject.has("apiVersion"))
                    localObject3 = localJSONObject.optString("apiVersion");
                }
                String str3 = "";
                localObject4 = str3;
                if (localJSONObject != null)
                {
                  localObject4 = str3;
                  if (localJSONObject.has("apiName"))
                    localObject4 = localJSONObject.optString("apiName");
                }
                return a(paramJSONObject, paramString, str1, str2, (String)localObject1, bool2, bool1, bool3, (String)localObject2, (String)localObject3, (String)localObject4, localJSONObject);
                bool1 = false;
                continue;
                bool1 = true;
              }
            }
          }
        }
      }
    }
  }

  public final JSONObject a()
  {
    return this.l;
  }

  public final String b()
  {
    return this.k;
  }

  public final String c()
  {
    return this.i;
  }

  public final String d()
  {
    return this.j;
  }

  public final String e()
  {
    return this.a;
  }

  public final String f()
  {
    if (TextUtils.isEmpty(this.b))
      this.b = GlobalConstants.b;
    return this.b;
  }

  public final String g()
  {
    return this.c;
  }

  public final JSONObject h()
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(this.c);
      return localJSONObject;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public final String i()
  {
    return this.d;
  }

  public final String j()
  {
    return this.e;
  }

  public final boolean k()
  {
    return this.f;
  }

  public final boolean l()
  {
    return this.g;
  }

  public final boolean m()
  {
    return this.h;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.protocol.ElementAction
 * JD-Core Version:    0.6.2
 */