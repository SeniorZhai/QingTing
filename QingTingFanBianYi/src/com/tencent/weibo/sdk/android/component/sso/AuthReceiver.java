package com.tencent.weibo.sdk.android.component.sso;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.weibo.sdk.android.component.sso.tools.Cryptor;

public class AuthReceiver extends BroadcastReceiver
{
  static final String ACTION = "com.tencent.sso.AUTH";

  // ERROR //
  private WeiboToken revert(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 21	com/tencent/weibo/sdk/android/component/sso/WeiboToken
    //   3: dup
    //   4: invokespecial 22	com/tencent/weibo/sdk/android/component/sso/WeiboToken:<init>	()V
    //   7: astore_2
    //   8: new 24	java/io/ByteArrayInputStream
    //   11: dup
    //   12: aload_1
    //   13: invokespecial 27	java/io/ByteArrayInputStream:<init>	([B)V
    //   16: astore_3
    //   17: new 29	java/io/DataInputStream
    //   20: dup
    //   21: aload_3
    //   22: invokespecial 32	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   25: astore_1
    //   26: aload_2
    //   27: aload_1
    //   28: invokevirtual 36	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   31: putfield 39	com/tencent/weibo/sdk/android/component/sso/WeiboToken:accessToken	Ljava/lang/String;
    //   34: aload_2
    //   35: aload_1
    //   36: invokevirtual 43	java/io/DataInputStream:readLong	()J
    //   39: putfield 47	com/tencent/weibo/sdk/android/component/sso/WeiboToken:expiresIn	J
    //   42: aload_2
    //   43: aload_1
    //   44: invokevirtual 36	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   47: putfield 50	com/tencent/weibo/sdk/android/component/sso/WeiboToken:refreshToken	Ljava/lang/String;
    //   50: aload_2
    //   51: aload_1
    //   52: invokevirtual 36	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   55: putfield 53	com/tencent/weibo/sdk/android/component/sso/WeiboToken:openID	Ljava/lang/String;
    //   58: aload_2
    //   59: aload_1
    //   60: invokevirtual 36	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   63: putfield 56	com/tencent/weibo/sdk/android/component/sso/WeiboToken:omasToken	Ljava/lang/String;
    //   66: aload_2
    //   67: aload_1
    //   68: invokevirtual 36	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   71: putfield 59	com/tencent/weibo/sdk/android/component/sso/WeiboToken:omasKey	Ljava/lang/String;
    //   74: aload_3
    //   75: ifnull +7 -> 82
    //   78: aload_3
    //   79: invokevirtual 62	java/io/ByteArrayInputStream:close	()V
    //   82: aload_1
    //   83: ifnull +7 -> 90
    //   86: aload_1
    //   87: invokevirtual 63	java/io/DataInputStream:close	()V
    //   90: aload_2
    //   91: areturn
    //   92: astore_2
    //   93: aload_2
    //   94: invokevirtual 66	java/lang/Exception:printStackTrace	()V
    //   97: aload_3
    //   98: ifnull +7 -> 105
    //   101: aload_3
    //   102: invokevirtual 62	java/io/ByteArrayInputStream:close	()V
    //   105: aload_1
    //   106: ifnull +7 -> 113
    //   109: aload_1
    //   110: invokevirtual 63	java/io/DataInputStream:close	()V
    //   113: aconst_null
    //   114: areturn
    //   115: astore_2
    //   116: aload_3
    //   117: ifnull +7 -> 124
    //   120: aload_3
    //   121: invokevirtual 62	java/io/ByteArrayInputStream:close	()V
    //   124: aload_1
    //   125: ifnull +7 -> 132
    //   128: aload_1
    //   129: invokevirtual 63	java/io/DataInputStream:close	()V
    //   132: aload_2
    //   133: athrow
    //   134: astore_3
    //   135: goto -53 -> 82
    //   138: astore_1
    //   139: aload_2
    //   140: areturn
    //   141: astore_2
    //   142: goto -37 -> 105
    //   145: astore_1
    //   146: goto -33 -> 113
    //   149: astore_3
    //   150: goto -26 -> 124
    //   153: astore_1
    //   154: goto -22 -> 132
    //
    // Exception table:
    //   from	to	target	type
    //   26	74	92	java/lang/Exception
    //   26	74	115	finally
    //   93	97	115	finally
    //   78	82	134	java/io/IOException
    //   86	90	138	java/io/IOException
    //   101	105	141	java/io/IOException
    //   109	113	145	java/io/IOException
    //   120	124	149	java/io/IOException
    //   128	132	153	java/io/IOException
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramIntent.getAction().equals("com.tencent.sso.AUTH")) && (paramIntent.getStringExtra("com.tencent.sso.PACKAGE_NAME").equals(paramContext.getPackageName())))
    {
      if (!paramIntent.getBooleanExtra("com.tencent.sso.AUTH_RESULT", false))
        break label92;
      paramContext = paramIntent.getStringExtra("com.tencent.sso.WEIBO_NICK");
      paramIntent = paramIntent.getByteArrayExtra("com.tencent.sso.ACCESS_TOKEN");
      paramIntent = revert(new Cryptor().decrypt(paramIntent, "&-*)Wb5_U,[^!9'+".getBytes(), 10));
      if (AuthHelper.listener != null)
        AuthHelper.listener.onAuthPassed(paramContext, paramIntent);
    }
    label92: int i;
    do
    {
      return;
      i = paramIntent.getIntExtra("com.tencent.sso.RESULT", 1);
      paramContext = paramIntent.getStringExtra("com.tencent.sso.WEIBO_NICK");
    }
    while (AuthHelper.listener == null);
    AuthHelper.listener.onAuthFail(i, paramContext);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.sso.AuthReceiver
 * JD-Core Version:    0.6.2
 */