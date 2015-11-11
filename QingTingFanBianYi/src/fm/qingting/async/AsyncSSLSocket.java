package fm.qingting.async;

import java.security.cert.X509Certificate;

public abstract interface AsyncSSLSocket extends AsyncSocket
{
  public abstract X509Certificate[] getPeerCertificates();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.AsyncSSLSocket
 * JD-Core Version:    0.6.2
 */