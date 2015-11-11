package cn.wemart.sdk.app.pay;

import android.text.TextUtils;

public class PayResult
{
  private String memo;
  private String result;
  private String resultStatus;

  public PayResult(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return;
      paramString = paramString.split(";");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        String str = paramString[i];
        if (str.startsWith("resultStatus"))
          this.resultStatus = gatValue(str, "resultStatus");
        if (str.startsWith("result"))
          this.result = gatValue(str, "result");
        if (str.startsWith("memo"))
          this.memo = gatValue(str, "memo");
        i += 1;
      }
    }
  }

  private String gatValue(String paramString1, String paramString2)
  {
    paramString2 = paramString2 + "={";
    return paramString1.substring(paramString1.indexOf(paramString2) + paramString2.length(), paramString1.lastIndexOf("}"));
  }

  public String getMemo()
  {
    return this.memo;
  }

  public String getResult()
  {
    return this.result;
  }

  public String getResultStatus()
  {
    return this.resultStatus;
  }

  public String toString()
  {
    return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + "}";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.wemart.sdk.app.pay.PayResult
 * JD-Core Version:    0.6.2
 */