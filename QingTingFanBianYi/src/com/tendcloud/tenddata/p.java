package com.tendcloud.tenddata;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

public final class p
{
  public static final String a = "tdcv3.talkingdata.net";
  private static String b = "211.151.121.41";
  private static final String c = "http://tdcv3.talkingdata.net/g/d";
  private static final ad d = new ad("", "tdcv3.talkingdata.net", b, 443);

  static boolean a(an paraman)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(1024);
    GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
    new q(localGZIPOutputStream).a(paraman);
    localGZIPOutputStream.finish();
    localGZIPOutputStream.close();
    int i = d.a("http://tdcv3.talkingdata.net/g/d", localByteArrayOutputStream.toByteArray());
    if (i == 200)
      return true;
    if (TCAgent.LOG_ON)
      Log.w("TDLog", "Server response code:" + i);
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.p
 * JD-Core Version:    0.6.2
 */