package org.android.agoo.net.mtop;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.message.proguard.aA;
import com.umeng.message.proguard.ag;
import com.umeng.message.proguard.aq;
import com.umeng.message.proguard.az;
import org.android.agoo.helper.PhoneHelper;

public class MtopHttpChunked extends az
{
  private volatile String l;
  private volatile String m;
  private volatile String n;

  private String a(Context paramContext)
  {
    Object localObject = new ag(paramContext);
    String str = ((ag)localObject).c();
    paramContext = new StringBuffer();
    if (!TextUtils.isEmpty(str))
      paramContext.append("nt=" + str);
    localObject = ((ag)localObject).b();
    if (!TextUtils.isEmpty((CharSequence)localObject))
      paramContext.append("&apn=" + (String)localObject);
    return paramContext.toString();
  }

  private String a(String paramString, aq paramaq)
  {
    String str = paramString;
    if (paramaq != null)
    {
      paramaq = paramaq.c();
      str = paramString + "?" + paramaq;
    }
    return str;
  }

  // ERROR //
  private String b(Context paramContext)
  {
    // Byte code:
    //   0: iconst_m1
    //   1: aload_1
    //   2: invokevirtual 68	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   5: ldc 70
    //   7: aload_1
    //   8: invokevirtual 73	android/content/Context:getPackageName	()Ljava/lang/String;
    //   11: invokevirtual 79	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   14: if_icmpne +5 -> 19
    //   17: aconst_null
    //   18: areturn
    //   19: aload_1
    //   20: ldc 81
    //   22: invokevirtual 85	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   25: checkcast 87	android/telephony/TelephonyManager
    //   28: astore_2
    //   29: aload_2
    //   30: invokevirtual 91	android/telephony/TelephonyManager:getCellLocation	()Landroid/telephony/CellLocation;
    //   33: checkcast 93	android/telephony/gsm/GsmCellLocation
    //   36: astore_1
    //   37: aload_1
    //   38: ifnull +129 -> 167
    //   41: aload_1
    //   42: invokevirtual 97	android/telephony/gsm/GsmCellLocation:getLac	()I
    //   45: istore_3
    //   46: aload_1
    //   47: invokevirtual 100	android/telephony/gsm/GsmCellLocation:getCid	()I
    //   50: istore 4
    //   52: iload_3
    //   53: iconst_m1
    //   54: if_icmpgt +9 -> 63
    //   57: iload 4
    //   59: iconst_m1
    //   60: if_icmple +107 -> 167
    //   63: new 35	java/lang/StringBuilder
    //   66: dup
    //   67: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   70: ldc 102
    //   72: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: iload_3
    //   76: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   79: ldc 107
    //   81: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: iload 4
    //   86: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   89: invokevirtual 45	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: astore_1
    //   93: aload_2
    //   94: invokevirtual 91	android/telephony/TelephonyManager:getCellLocation	()Landroid/telephony/CellLocation;
    //   97: checkcast 109	android/telephony/cdma/CdmaCellLocation
    //   100: astore_2
    //   101: aload_2
    //   102: ifnull +63 -> 165
    //   105: aload_2
    //   106: invokevirtual 112	android/telephony/cdma/CdmaCellLocation:getNetworkId	()I
    //   109: istore_3
    //   110: aload_2
    //   111: invokevirtual 115	android/telephony/cdma/CdmaCellLocation:getBaseStationId	()I
    //   114: istore 4
    //   116: iload_3
    //   117: iconst_m1
    //   118: if_icmpgt +9 -> 127
    //   121: iload 4
    //   123: iconst_m1
    //   124: if_icmple +41 -> 165
    //   127: new 35	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   134: ldc 102
    //   136: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: iload_3
    //   140: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   143: ldc 107
    //   145: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: iload 4
    //   150: invokevirtual 105	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   153: invokevirtual 45	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   156: astore_2
    //   157: aload_2
    //   158: areturn
    //   159: astore_1
    //   160: aconst_null
    //   161: areturn
    //   162: astore_2
    //   163: aload_1
    //   164: areturn
    //   165: aload_1
    //   166: areturn
    //   167: aconst_null
    //   168: astore_1
    //   169: goto -76 -> 93
    //
    // Exception table:
    //   from	to	target	type
    //   0	17	159	java/lang/Throwable
    //   19	37	159	java/lang/Throwable
    //   41	52	159	java/lang/Throwable
    //   63	93	159	java/lang/Throwable
    //   93	101	162	java/lang/Throwable
    //   105	116	162	java/lang/Throwable
    //   127	157	162	java/lang/Throwable
  }

  public final void connect(Context paramContext, MtopRequest paramMtopRequest, long paramLong, aA paramaA)
  {
    try
    {
      paramMtopRequest.putParams("c0", Build.BRAND);
      paramMtopRequest.putParams("c1", Build.MODEL);
      paramMtopRequest.putParams("c2", PhoneHelper.getOriginalImei(paramContext));
      paramMtopRequest.putParams("c3", PhoneHelper.getOriginalImsi(paramContext));
      paramMtopRequest.putParams("c4", PhoneHelper.getLocalMacAddress(paramContext));
      paramMtopRequest.putParams("c5", PhoneHelper.getSerialNum());
      paramMtopRequest.putParams("c6", PhoneHelper.getAndroidId(paramContext));
      MtopRequestHelper.checkAppKeyAndAppSecret(paramMtopRequest, this.l, this.m);
      paramMtopRequest = MtopRequestHelper.getUrlWithRequestParams(paramContext, paramMtopRequest);
      Object localObject = a(this.n, paramMtopRequest);
      String str = a(paramContext);
      paramMtopRequest = (MtopRequest)localObject;
      if (!TextUtils.isEmpty(str))
        paramMtopRequest = (String)localObject + "&" + str;
      str = b(paramContext);
      localObject = paramMtopRequest;
      if (!TextUtils.isEmpty(str))
        localObject = paramMtopRequest + "&" + str;
      super.connect(paramContext, (String)localObject, paramLong, paramaA);
      return;
    }
    catch (Throwable paramContext)
    {
      while (hasCallError());
      callError(true);
      paramaA.a(504, null, paramContext);
    }
  }

  public void setBaseUrl(String paramString)
  {
    this.n = paramString;
  }

  public void setDefaultAppSecret(String paramString)
  {
    this.m = paramString;
  }

  public void setDefaultAppkey(String paramString)
  {
    this.l = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.MtopHttpChunked
 * JD-Core Version:    0.6.2
 */