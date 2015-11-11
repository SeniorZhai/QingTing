package org.apache.commons.httpclient.protocol;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract interface SecureProtocolSocketFactory extends ProtocolSocketFactory
{
  public abstract Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory
 * JD-Core Version:    0.6.2
 */