package org.jdom.filter;

final class NegateFilter extends AbstractFilter
{
  private static final String CVS_ID = "@(#) $RCSfile: NegateFilter.java,v $ $Revision: 1.4 $ $Date: 2007/11/10 05:29:00 $";
  private Filter filter;

  public NegateFilter(Filter paramFilter)
  {
    this.filter = paramFilter;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof NegateFilter))
      return this.filter.equals(((NegateFilter)paramObject).filter);
    return false;
  }

  public int hashCode()
  {
    return this.filter.hashCode() ^ 0xFFFFFFFF;
  }

  public boolean matches(Object paramObject)
  {
    return !this.filter.matches(paramObject);
  }

  public Filter negate()
  {
    return this.filter;
  }

  public String toString()
  {
    return 64 + "[NegateFilter: " + this.filter.toString() + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.filter.NegateFilter
 * JD-Core Version:    0.6.2
 */