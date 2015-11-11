package org.jdom.filter;

import org.jdom.CDATA;
import org.jdom.Comment;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.EntityRef;
import org.jdom.ProcessingInstruction;
import org.jdom.Text;

public class ContentFilter extends AbstractFilter
{
  public static final int CDATA = 2;
  public static final int COMMENT = 8;
  private static final String CVS_ID = "@(#) $RCSfile: ContentFilter.java,v $ $Revision: 1.15 $ $Date: 2007/11/10 05:29:00 $ $Name: jdom_1_1_1 $";
  public static final int DOCTYPE = 128;
  public static final int DOCUMENT = 64;
  public static final int ELEMENT = 1;
  public static final int ENTITYREF = 32;
  public static final int PI = 16;
  public static final int TEXT = 4;
  private int filterMask;

  public ContentFilter()
  {
    setDefaultMask();
  }

  public ContentFilter(int paramInt)
  {
    setFilterMask(paramInt);
  }

  public ContentFilter(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      setDefaultMask();
      return;
    }
    this.filterMask &= (this.filterMask ^ 0xFFFFFFFF);
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      return true;
      if (!(paramObject instanceof ContentFilter))
        return false;
      paramObject = (ContentFilter)paramObject;
    }
    while (this.filterMask == paramObject.filterMask);
    return false;
  }

  public int getFilterMask()
  {
    return this.filterMask;
  }

  public int hashCode()
  {
    return this.filterMask;
  }

  public boolean matches(Object paramObject)
  {
    if ((paramObject instanceof Element))
      if ((this.filterMask & 0x1) == 0);
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return true;
                  return false;
                  if (!(paramObject instanceof CDATA))
                    break;
                }
                while ((this.filterMask & 0x2) != 0);
                return false;
                if (!(paramObject instanceof Text))
                  break;
              }
              while ((this.filterMask & 0x4) != 0);
              return false;
              if (!(paramObject instanceof Comment))
                break;
            }
            while ((this.filterMask & 0x8) != 0);
            return false;
            if (!(paramObject instanceof ProcessingInstruction))
              break;
          }
          while ((this.filterMask & 0x10) != 0);
          return false;
          if (!(paramObject instanceof EntityRef))
            break;
        }
        while ((this.filterMask & 0x20) != 0);
        return false;
        if (!(paramObject instanceof Document))
          break;
      }
      while ((this.filterMask & 0x40) != 0);
      return false;
      if (!(paramObject instanceof DocType))
        break;
    }
    while ((this.filterMask & 0x80) != 0);
    return false;
    return false;
  }

  public void setCDATAVisible(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.filterMask |= 2;
      return;
    }
    this.filterMask &= -3;
  }

  public void setCommentVisible(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.filterMask |= 8;
      return;
    }
    this.filterMask &= -9;
  }

  public void setDefaultMask()
  {
    this.filterMask = 255;
  }

  public void setDocTypeVisible(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.filterMask |= 128;
      return;
    }
    this.filterMask &= -129;
  }

  public void setDocumentContent()
  {
    this.filterMask = 153;
  }

  public void setElementContent()
  {
    this.filterMask = 63;
  }

  public void setElementVisible(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.filterMask |= 1;
      return;
    }
    this.filterMask &= -2;
  }

  public void setEntityRefVisible(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.filterMask |= 32;
      return;
    }
    this.filterMask &= -33;
  }

  public void setFilterMask(int paramInt)
  {
    setDefaultMask();
    this.filterMask &= paramInt;
  }

  public void setPIVisible(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.filterMask |= 16;
      return;
    }
    this.filterMask &= -17;
  }

  public void setTextVisible(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.filterMask |= 4;
      return;
    }
    this.filterMask &= -5;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.filter.ContentFilter
 * JD-Core Version:    0.6.2
 */