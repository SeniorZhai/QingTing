package com.mediav.ads.sdk.interfaces;

import java.util.HashSet;

public abstract interface IMvVideoAdAttributes extends IMvAdAttributes
{
  public abstract void setCast(HashSet<String> paramHashSet);

  public abstract void setCategory(int paramInt);

  public abstract void setEpisode(int paramInt);

  public abstract void setRegion(String paramString);

  public abstract void setSource(String paramString);

  public abstract void setTitle(String paramString);

  public abstract void setYear(int paramInt);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.interfaces.IMvVideoAdAttributes
 * JD-Core Version:    0.6.2
 */