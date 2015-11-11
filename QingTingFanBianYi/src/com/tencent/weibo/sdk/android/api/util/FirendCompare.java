package com.tencent.weibo.sdk.android.api.util;

import com.tencent.weibo.sdk.android.model.Firend;
import java.util.Comparator;

public class FirendCompare
  implements Comparator<Firend>
{
  public int compare(Firend paramFirend1, Firend paramFirend2)
  {
    return HypyUtil.cn2py(paramFirend1.getName()).substring(0, 1).toUpperCase().compareTo(HypyUtil.cn2py(paramFirend2.getName()).substring(0, 1).toUpperCase());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.util.FirendCompare
 * JD-Core Version:    0.6.2
 */