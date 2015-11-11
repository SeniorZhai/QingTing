package org.jdom.input;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.xml.sax.SAXParseException;

public class JDOMParseException extends JDOMException
{
  private static final String CVS_ID = "@(#) $RCSfile: JDOMParseException.java,v $ $Revision: 1.8 $ $Date: 2007/11/10 05:29:00 $ $Name: jdom_1_1_1 $";
  private final Document partialDocument;

  public JDOMParseException(String paramString, Throwable paramThrowable)
  {
    this(paramString, paramThrowable, null);
  }

  public JDOMParseException(String paramString, Throwable paramThrowable, Document paramDocument)
  {
    super(paramString, paramThrowable);
    this.partialDocument = paramDocument;
  }

  public int getColumnNumber()
  {
    if ((getCause() instanceof SAXParseException))
      return ((SAXParseException)getCause()).getColumnNumber();
    return -1;
  }

  public int getLineNumber()
  {
    if ((getCause() instanceof SAXParseException))
      return ((SAXParseException)getCause()).getLineNumber();
    return -1;
  }

  public Document getPartialDocument()
  {
    return this.partialDocument;
  }

  public String getPublicId()
  {
    if ((getCause() instanceof SAXParseException))
      return ((SAXParseException)getCause()).getPublicId();
    return null;
  }

  public String getSystemId()
  {
    if ((getCause() instanceof SAXParseException))
      return ((SAXParseException)getCause()).getSystemId();
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.input.JDOMParseException
 * JD-Core Version:    0.6.2
 */