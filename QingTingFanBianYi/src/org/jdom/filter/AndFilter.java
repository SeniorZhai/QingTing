package org.jdom.filter;

final class AndFilter extends AbstractFilter
{
  private static final String CVS_ID = "@(#) $RCSfile: AndFilter.java,v $ $Revision: 1.4 $ $Date: 2007/11/10 05:29:00 $";
  private Filter left;
  private Filter right;

  public AndFilter(Filter paramFilter1, Filter paramFilter2)
  {
    if ((paramFilter1 == null) || (paramFilter2 == null))
      throw new IllegalArgumentException("null filter not allowed");
    this.left = paramFilter1;
    this.right = paramFilter2;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      return true;
      if (!(paramObject instanceof AndFilter))
        break;
      paramObject = (AndFilter)paramObject;
    }
    while (((this.left.equals(paramObject.left)) && (this.right.equals(paramObject.right))) || ((this.left.equals(paramObject.right)) && (this.right.equals(paramObject.left))));
    return false;
  }

  public int hashCode()
  {
    return this.left.hashCode() * 31 + this.right.hashCode();
  }

  public boolean matches(Object paramObject)
  {
    return (this.left.matches(paramObject)) && (this.right.matches(paramObject));
  }

  public String toString()
  {
    return 64 + "[AndFilter: " + this.left.toString() + ",\n" + "            " + this.right.toString() + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.filter.AndFilter
 * JD-Core Version:    0.6.2
 */