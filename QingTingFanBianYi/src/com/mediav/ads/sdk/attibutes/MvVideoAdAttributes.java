package com.mediav.ads.sdk.attibutes;

import android.text.TextUtils;
import com.mediav.ads.sdk.interfaces.IMvVideoAdAttributes;
import java.util.HashMap;
import java.util.HashSet;

public class MvVideoAdAttributes
  implements IMvVideoAdAttributes
{
  private HashMap<String, String> map = new HashMap();
  private HashSet<String> tags = new HashSet();

  public HashMap<String, String> getAttributes()
  {
    this.map.put("qhtag", TextUtils.join("_", this.tags));
    return this.map;
  }

  public void setCast(HashSet<String> paramHashSet)
  {
    this.tags.addAll(paramHashSet);
  }

  public void setCategory(int paramInt)
  {
    this.map.put("qhchannel", String.valueOf(paramInt));
  }

  public void setEpisode(int paramInt)
  {
    this.map.put("qhepisode", String.valueOf(paramInt));
  }

  public void setRegion(String paramString)
  {
    this.tags.add(paramString);
  }

  public void setSource(String paramString)
  {
    this.map.put("qhsource", paramString);
  }

  public void setTitle(String paramString)
  {
    this.map.put("qhname", paramString);
  }

  public void setYear(int paramInt)
  {
    this.tags.add(String.valueOf(paramInt));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.attibutes.MvVideoAdAttributes
 * JD-Core Version:    0.6.2
 */