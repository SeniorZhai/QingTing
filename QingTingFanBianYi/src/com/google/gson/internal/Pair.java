package com.google.gson.internal;

public final class Pair<FIRST, SECOND>
{
  public final FIRST first;
  public final SECOND second;

  public Pair(FIRST paramFIRST, SECOND paramSECOND)
  {
    this.first = paramFIRST;
    this.second = paramSECOND;
  }

  private static boolean equal(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Pair));
    do
    {
      return false;
      paramObject = (Pair)paramObject;
    }
    while ((!equal(this.first, paramObject.first)) || (!equal(this.second, paramObject.second)));
    return true;
  }

  public int hashCode()
  {
    int j = 0;
    if (this.first != null);
    for (int i = this.first.hashCode(); ; i = 0)
    {
      if (this.second != null)
        j = this.second.hashCode();
      return i * 17 + j * 17;
    }
  }

  public String toString()
  {
    return String.format("{%s,%s}", new Object[] { this.first, this.second });
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.internal.Pair
 * JD-Core Version:    0.6.2
 */