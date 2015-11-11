package org.jdom;

import org.jdom.output.XMLOutputter;

public class Comment extends Content
{
  private static final String CVS_ID = "@(#) $RCSfile: Comment.java,v $ $Revision: 1.33 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
  protected String text;

  protected Comment()
  {
  }

  public Comment(String paramString)
  {
    setText(paramString);
  }

  public String getText()
  {
    return this.text;
  }

  public String getValue()
  {
    return this.text;
  }

  public Comment setText(String paramString)
  {
    String str = Verifier.checkCommentData(paramString);
    if (str != null)
      throw new IllegalDataException(paramString, "comment", str);
    this.text = paramString;
    return this;
  }

  public String toString()
  {
    return "[Comment: " + new XMLOutputter().outputString(this) + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.Comment
 * JD-Core Version:    0.6.2
 */