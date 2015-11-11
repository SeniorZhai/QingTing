package com.umeng.message.proguard;

import android.os.Message;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

public class ap extends an
{
  private String[] a = { "image/jpeg", "image/png" };

  public ap()
  {
  }

  public ap(String[] paramArrayOfString)
  {
    this();
    this.a = paramArrayOfString;
  }

  void a(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      super.a(paramMessage);
      return;
    case 0:
      c((byte[])paramMessage.obj);
      return;
    case 1:
    }
    paramMessage = (Object[])paramMessage.obj;
    c((Throwable)paramMessage[0], (byte[])paramMessage[1]);
  }

  public void a(Throwable paramThrowable, byte[] paramArrayOfByte)
  {
    b(a(1, new Object[] { paramThrowable, paramArrayOfByte }));
  }

  void a(HttpResponse paramHttpResponse)
  {
    Object localObject1 = null;
    int j = 0;
    StatusLine localStatusLine = paramHttpResponse.getStatusLine();
    Object localObject2 = paramHttpResponse.getHeaders("Content-Type");
    if (localObject2.length != 1)
    {
      a(new HttpResponseException(localStatusLine.getStatusCode(), "None, or more than one, Content-Type Header found!"), null);
      return;
    }
    localObject2 = localObject2[0];
    String[] arrayOfString = this.a;
    int k = arrayOfString.length;
    int i = 0;
    while (i < k)
    {
      if (arrayOfString[i].equals(((Header)localObject2).getValue()))
        j = 1;
      i += 1;
    }
    if (j == 0)
    {
      a(new HttpResponseException(localStatusLine.getStatusCode(), "Content-Type not allowed!"), null);
      return;
    }
    while (true)
    {
      try
      {
        paramHttpResponse = paramHttpResponse.getEntity();
        if (paramHttpResponse != null)
        {
          paramHttpResponse = new BufferedHttpEntity(paramHttpResponse);
          paramHttpResponse = EntityUtils.toByteArray(paramHttpResponse);
          if (localStatusLine.getStatusCode() >= 300)
          {
            a(new HttpResponseException(localStatusLine.getStatusCode(), localStatusLine.getReasonPhrase()), paramHttpResponse);
            return;
          }
        }
      }
      catch (IOException paramHttpResponse)
      {
        a(paramHttpResponse, (byte[])null);
        paramHttpResponse = localObject1;
        continue;
        b(paramHttpResponse);
        return;
      }
      paramHttpResponse = null;
    }
  }

  public void a(byte[] paramArrayOfByte)
  {
  }

  public void b(Throwable paramThrowable, byte[] paramArrayOfByte)
  {
    onFailure(paramThrowable);
  }

  public void b(byte[] paramArrayOfByte)
  {
    b(a(0, paramArrayOfByte));
  }

  void c(Throwable paramThrowable, byte[] paramArrayOfByte)
  {
    b(paramThrowable, paramArrayOfByte);
  }

  void c(byte[] paramArrayOfByte)
  {
    a(paramArrayOfByte);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.ap
 * JD-Core Version:    0.6.2
 */