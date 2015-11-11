package com.alipay.sdk.data;

import java.util.ArrayList;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class InteractionData
{
  public static final String a = "application/octet-stream;binary/octet-stream";
  private Header[] b = null;
  private String c = null;
  private String d = null;

  private void a(String paramString)
  {
    this.c = paramString;
  }

  private void b(String paramString)
  {
    this.d = paramString;
  }

  private Header[] d()
  {
    return this.b;
  }

  private void e()
  {
    this.d = null;
    this.c = null;
  }

  public final ArrayList a()
  {
    Object localObject;
    if ((this.b == null) || (this.b.length == 0))
    {
      localObject = null;
      return localObject;
    }
    ArrayList localArrayList = new ArrayList();
    Header[] arrayOfHeader = this.b;
    int j = arrayOfHeader.length;
    int i = 0;
    while (true)
    {
      localObject = localArrayList;
      if (i >= j)
        break;
      localObject = arrayOfHeader[i];
      localArrayList.add(new BasicHeader(((Header)localObject).getName(), ((Header)localObject).getValue()));
      i += 1;
    }
  }

  public final void a(Header[] paramArrayOfHeader)
  {
    this.b = paramArrayOfHeader;
  }

  public final String b()
  {
    return this.c;
  }

  public final String c()
  {
    return this.d;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.data.InteractionData
 * JD-Core Version:    0.6.2
 */