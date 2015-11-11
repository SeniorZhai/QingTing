package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import android.text.TextUtils;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.Result;

public class PayHelper
{
  public static final String a = "failed";
  private Activity b;
  private IAlixPay c;
  private Object d = IAlixPay.class;
  private boolean e = false;
  private ServiceConnection f = new PayHelper.1(this);
  private IRemoteServiceCallback g = new PayHelper.2(this);

  public PayHelper(Activity paramActivity)
  {
    this.b = paramActivity;
  }

  // ERROR //
  private String a(String paramString, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 31	com/alipay/sdk/util/PayHelper:e	Z
    //   4: ifeq +6 -> 10
    //   7: ldc 55
    //   9: areturn
    //   10: aload_0
    //   11: iconst_1
    //   12: putfield 31	com/alipay/sdk/util/PayHelper:e	Z
    //   15: aload_0
    //   16: getfield 49	com/alipay/sdk/util/PayHelper:c	Lcom/alipay/android/app/IAlixPay;
    //   19: ifnonnull +20 -> 39
    //   22: aload_0
    //   23: getfield 45	com/alipay/sdk/util/PayHelper:b	Landroid/app/Activity;
    //   26: invokevirtual 61	android/app/Activity:getApplicationContext	()Landroid/content/Context;
    //   29: aload_2
    //   30: aload_0
    //   31: getfield 38	com/alipay/sdk/util/PayHelper:f	Landroid/content/ServiceConnection;
    //   34: iconst_1
    //   35: invokevirtual 67	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   38: pop
    //   39: aload_0
    //   40: getfield 29	com/alipay/sdk/util/PayHelper:d	Ljava/lang/Object;
    //   43: astore_2
    //   44: aload_2
    //   45: monitorenter
    //   46: aload_0
    //   47: getfield 49	com/alipay/sdk/util/PayHelper:c	Lcom/alipay/android/app/IAlixPay;
    //   50: ifnonnull +13 -> 63
    //   53: aload_0
    //   54: getfield 29	com/alipay/sdk/util/PayHelper:d	Ljava/lang/Object;
    //   57: ldc2_w 68
    //   60: invokevirtual 73	java/lang/Object:wait	(J)V
    //   63: aload_2
    //   64: monitorexit
    //   65: aload_0
    //   66: getfield 49	com/alipay/sdk/util/PayHelper:c	Lcom/alipay/android/app/IAlixPay;
    //   69: ifnonnull +57 -> 126
    //   72: aload_0
    //   73: getfield 45	com/alipay/sdk/util/PayHelper:b	Landroid/app/Activity;
    //   76: aload_0
    //   77: getfield 38	com/alipay/sdk/util/PayHelper:f	Landroid/content/ServiceConnection;
    //   80: invokevirtual 77	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   83: aload_0
    //   84: iconst_0
    //   85: putfield 31	com/alipay/sdk/util/PayHelper:e	Z
    //   88: ldc 8
    //   90: areturn
    //   91: astore_1
    //   92: aload_2
    //   93: monitorexit
    //   94: aload_1
    //   95: athrow
    //   96: astore_1
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_0
    //   100: getfield 45	com/alipay/sdk/util/PayHelper:b	Landroid/app/Activity;
    //   103: aload_0
    //   104: getfield 38	com/alipay/sdk/util/PayHelper:f	Landroid/content/ServiceConnection;
    //   107: invokevirtual 77	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   110: aload_0
    //   111: iconst_0
    //   112: putfield 31	com/alipay/sdk/util/PayHelper:e	Z
    //   115: aload_1
    //   116: areturn
    //   117: astore_1
    //   118: aload_0
    //   119: aconst_null
    //   120: putfield 49	com/alipay/sdk/util/PayHelper:c	Lcom/alipay/android/app/IAlixPay;
    //   123: goto -40 -> 83
    //   126: aload_0
    //   127: getfield 49	com/alipay/sdk/util/PayHelper:c	Lcom/alipay/android/app/IAlixPay;
    //   130: aload_0
    //   131: getfield 43	com/alipay/sdk/util/PayHelper:g	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   134: invokeinterface 81 2 0
    //   139: aload_0
    //   140: getfield 49	com/alipay/sdk/util/PayHelper:c	Lcom/alipay/android/app/IAlixPay;
    //   143: aload_1
    //   144: invokeinterface 85 2 0
    //   149: astore_1
    //   150: aload_0
    //   151: getfield 49	com/alipay/sdk/util/PayHelper:c	Lcom/alipay/android/app/IAlixPay;
    //   154: aload_0
    //   155: getfield 43	com/alipay/sdk/util/PayHelper:g	Lcom/alipay/android/app/IRemoteServiceCallback;
    //   158: invokeinterface 88 2 0
    //   163: aload_0
    //   164: aconst_null
    //   165: putfield 49	com/alipay/sdk/util/PayHelper:c	Lcom/alipay/android/app/IAlixPay;
    //   168: aload_0
    //   169: getfield 45	com/alipay/sdk/util/PayHelper:b	Landroid/app/Activity;
    //   172: aload_0
    //   173: getfield 38	com/alipay/sdk/util/PayHelper:f	Landroid/content/ServiceConnection;
    //   176: invokevirtual 77	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   179: aload_0
    //   180: iconst_0
    //   181: putfield 31	com/alipay/sdk/util/PayHelper:e	Z
    //   184: aload_1
    //   185: areturn
    //   186: astore_2
    //   187: aload_0
    //   188: aconst_null
    //   189: putfield 49	com/alipay/sdk/util/PayHelper:c	Lcom/alipay/android/app/IAlixPay;
    //   192: goto -13 -> 179
    //   195: astore_2
    //   196: aload_0
    //   197: aconst_null
    //   198: putfield 49	com/alipay/sdk/util/PayHelper:c	Lcom/alipay/android/app/IAlixPay;
    //   201: goto -91 -> 110
    //   204: astore_1
    //   205: aload_0
    //   206: getfield 45	com/alipay/sdk/util/PayHelper:b	Landroid/app/Activity;
    //   209: aload_0
    //   210: getfield 38	com/alipay/sdk/util/PayHelper:f	Landroid/content/ServiceConnection;
    //   213: invokevirtual 77	android/app/Activity:unbindService	(Landroid/content/ServiceConnection;)V
    //   216: aload_0
    //   217: iconst_0
    //   218: putfield 31	com/alipay/sdk/util/PayHelper:e	Z
    //   221: aload_1
    //   222: athrow
    //   223: astore_2
    //   224: aload_0
    //   225: aconst_null
    //   226: putfield 49	com/alipay/sdk/util/PayHelper:c	Lcom/alipay/android/app/IAlixPay;
    //   229: goto -13 -> 216
    //   232: astore_2
    //   233: goto -134 -> 99
    //
    // Exception table:
    //   from	to	target	type
    //   46	63	91	finally
    //   63	65	91	finally
    //   39	46	96	java/lang/Exception
    //   65	72	96	java/lang/Exception
    //   92	96	96	java/lang/Exception
    //   126	150	96	java/lang/Exception
    //   72	83	117	java/lang/Exception
    //   168	179	186	java/lang/Exception
    //   99	110	195	java/lang/Exception
    //   39	46	204	finally
    //   65	72	204	finally
    //   92	96	204	finally
    //   126	150	204	finally
    //   150	168	204	finally
    //   205	216	223	java/lang/Exception
    //   150	168	232	java/lang/Exception
  }

  public final String a(String paramString)
  {
    Object localObject = Utils.a(Utils.a(this.b, "com.eg.android.AlipayGphone"));
    if ((localObject != null) && (!TextUtils.equals((CharSequence)localObject, "b6cbad6cbd5ed0d209afc69ad3b7a617efaae9b3c47eabe0be42d924936fa78c8001b1fd74b079e5ff9690061dacfa4768e981a526b9ca77156ca36251cf2f906d105481374998a7e6e6e18f75ca98b8ed2eaf86ff402c874cca0a263053f22237858206867d210020daa38c48b20cc9dfd82b44a51aeb5db459b22794e2d649")))
      return Result.c();
    localObject = new Intent();
    ((Intent)localObject).setClassName("com.eg.android.AlipayGphone", "com.alipay.android.app.MspService");
    ((Intent)localObject).setAction("com.eg.android.AlipayGphone.IAlixPay");
    return a(paramString, (Intent)localObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.util.PayHelper
 * JD-Core Version:    0.6.2
 */