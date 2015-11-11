package org.jdom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jdom.filter.Filter;

class FilterIterator
  implements Iterator
{
  private static final String CVS_ID = "@(#) $RCSfile: FilterIterator.java,v $ $Revision: 1.6 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
  private Filter filter;
  private Iterator iterator;
  private Object nextObject;

  public FilterIterator(Iterator paramIterator, Filter paramFilter)
  {
    if ((paramIterator == null) || (paramFilter == null))
      throw new IllegalArgumentException("null parameter");
    this.iterator = paramIterator;
    this.filter = paramFilter;
  }

  public boolean hasNext()
  {
    if (this.nextObject != null)
      return true;
    while (this.iterator.hasNext())
    {
      Object localObject = this.iterator.next();
      if (this.filter.matches(localObject))
      {
        this.nextObject = localObject;
        return true;
      }
    }
    return false;
  }

  public Object next()
  {
    if (!hasNext())
      throw new NoSuchElementException();
    Object localObject = this.nextObject;
    this.nextObject = null;
    return localObject;
  }

  public void remove()
  {
    this.iterator.remove();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.FilterIterator
 * JD-Core Version:    0.6.2
 */