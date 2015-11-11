package com.umeng.message;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.common.message.Log;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.android.agoo.client.AgooSettings;
import org.android.agoo.client.BaseRegistrar;

public class UmengRegistrar extends BaseRegistrar
{
  private static final String a = "android@umeng";
  private static final String b = UmengRegistrar.class.getName();

  static void a(Context paramContext, boolean paramBoolean)
  {
    if (paramContext == null)
    {
      Log.b(b, "setRegisteredToUmeng: null context");
      return;
    }
    String str1 = getRegistrationId(paramContext);
    if (TextUtils.isEmpty(str1))
    {
      Log.b(b, "setRegisteredToUmeng: empty registration id");
      return;
    }
    Object localObject = paramContext.getSharedPreferences("umeng_message_state", 0);
    paramContext = ((SharedPreferences)localObject).edit();
    localObject = ((SharedPreferences)localObject).getAll();
    if ((localObject != null) && (((Map)localObject).size() > 0))
    {
      localObject = ((Map)localObject).entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str2 = (String)((Map.Entry)((Iterator)localObject).next()).getKey();
        if (str2.startsWith("KEY_REGISTERED_TO_UMENG_"))
          paramContext.remove(str2);
      }
    }
    paramContext.putBoolean("KEY_REGISTERED_TO_UMENG_" + str1, paramBoolean).commit();
  }

  public static final void checkManifest(Context paramContext)
  {
  }

  public static void checkRegisteredToUmeng(Context paramContext)
  {
    if (!isRegisteredToUmeng(paramContext))
      registerToUmeng(paramContext);
  }

  public static boolean isRegisteredToUmeng(Context paramContext)
  {
    if (paramContext == null)
    {
      Log.b(b, "isRegisteredToUmeng: null context");
      return false;
    }
    String str = getRegistrationId(paramContext);
    if (TextUtils.isEmpty(str))
    {
      Log.b(b, "isRegisteredToUmeng: empty registration id");
      return false;
    }
    return MessageSharedPrefs.getInstance(paramContext).a(str);
  }

  public static void register(Context paramContext, String paramString1, String paramString2)
  {
    if (Build.VERSION.SDK_INT < 8)
    {
      Log.b(b, "Push SDK does not work for Android Verion < 8");
      return;
    }
    if (TextUtils.isEmpty(paramString1))
      throw new NullPointerException("appKey==null");
    if (TextUtils.isEmpty(paramString2))
      throw new NullPointerException("appSecret==null");
    AgooSettings.setUTVersion(paramContext, UmengUT.class.getName());
    a(paramContext, "umeng:" + paramString1, paramString2, "android@umeng");
  }

  public static void registerToUmeng(Context paramContext)
  {
    UTrack.getInstance(paramContext).trackRegister();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengRegistrar
 * JD-Core Version:    0.6.2
 */