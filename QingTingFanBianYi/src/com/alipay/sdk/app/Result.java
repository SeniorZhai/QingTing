package com.alipay.sdk.app;

public class Result
{
  private static String a;

  public static String a()
  {
    return a;
  }

  public static String a(int paramInt, String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("resultStatus={").append(paramInt).append("};memo={").append(paramString1).append("};result={").append(paramString2).append("}");
    return localStringBuilder.toString();
  }

  public static void a(String paramString)
  {
    a = paramString;
  }

  public static String b()
  {
    ResultStatus localResultStatus = ResultStatus.a(ResultStatus.c.a());
    return a(localResultStatus.a(), localResultStatus.b(), "");
  }

  public static String c()
  {
    ResultStatus localResultStatus = ResultStatus.a(ResultStatus.e.a());
    return a(localResultStatus.a(), localResultStatus.b(), "");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.Result
 * JD-Core Version:    0.6.2
 */