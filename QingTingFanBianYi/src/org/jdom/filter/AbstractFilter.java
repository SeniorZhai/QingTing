package org.jdom.filter;

public abstract class AbstractFilter
  implements Filter
{
  private static final String CVS_ID = "@(#) $RCSfile: AbstractFilter.java,v $ $Revision: 1.6 $ $Date: 2007/11/10 05:29:00 $";

  public Filter and(Filter paramFilter)
  {
    return new AndFilter(this, paramFilter);
  }

  public Filter negate()
  {
    return new NegateFilter(this);
  }

  public Filter or(Filter paramFilter)
  {
    return new OrFilter(this, paramFilter);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.filter.AbstractFilter
 * JD-Core Version:    0.6.2
 */