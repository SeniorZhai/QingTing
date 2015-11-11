package com.mediav.ads.sdk.attibutes;

import com.mediav.ads.sdk.interfaces.IMvProductAdAttributes;
import java.util.HashMap;

public class MvProductAdAttributes
  implements IMvProductAdAttributes
{
  private HashMap<String, String> productInfo = new HashMap();

  public HashMap<String, String> getAttributes()
  {
    return this.productInfo;
  }

  public void setCategory(String paramString, int paramInt)
  {
    this.productInfo.put("qhcn", paramString);
    this.productInfo.put("qhtid", paramInt + "");
  }

  public void setPrice(double paramDouble)
  {
    this.productInfo.put("price", paramDouble + "");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.attibutes.MvProductAdAttributes
 * JD-Core Version:    0.6.2
 */