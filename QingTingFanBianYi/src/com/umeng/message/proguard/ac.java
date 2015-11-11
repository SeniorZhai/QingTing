package com.umeng.message.proguard;

import java.util.Map;

class ac
  implements aA
{
  private long b = -1L;

  ac(aa paramaa)
  {
  }

  public void a()
  {
    Q.c("MessagePush", "connect[" + aa.d(this.a) + "]--->[onClose()]");
    U.a(this.a.a, this.b, "onClose");
    aa.f(this.a);
  }

  public void a(int paramInt, Map<String, String> paramMap)
  {
    aa.a(this.a, paramInt);
    Q.c("MessagePush", "connect[" + aa.d(this.a) + "]--->[onOpen()]");
    aa.e(this.a);
    aa.a(this.a, System.currentTimeMillis());
    this.b = System.currentTimeMillis();
    aa.b(this.a, this.b, (String)paramMap.get("Hb"));
    aa.a(this.a, (String)paramMap.get("X-AT"));
    aa.b(this.a, (String)paramMap.get("X-COMMAND"));
    aa.c(this.a, this.b, (String)paramMap.get("Dye"));
  }

  public void a(int paramInt, Map<String, String> paramMap, Throwable paramThrowable)
  {
    aa.a(this.a, -1L);
    Q.c("MessagePush", "connect[" + aa.d(this.a) + "]--->httpStatusCode[" + paramInt + "]:Throwable[" + paramThrowable.getMessage() + "]");
    U.a(this.a.a, this.b, "onError", "" + paramInt);
    aa.f(this.a);
    aa.a(this.a, paramInt, paramMap);
  }

  public void a(String paramString)
  {
    if (!aa.g(this.a))
      return;
    aa.a(this.a, System.currentTimeMillis());
    Q.c("MessagePush", "connect[" + aa.d(this.a) + "]--->[" + paramString + "][lastHeartTime:" + aa.h(this.a) + "]");
    this.a.f(paramString);
  }

  public void a(char[] paramArrayOfChar)
  {
    if (!aa.g(this.a))
      return;
    aa.a(this.a, paramArrayOfChar, this.b);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.ac
 * JD-Core Version:    0.6.2
 */