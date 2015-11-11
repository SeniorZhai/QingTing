package org.jdom;

import org.jdom.output.XMLOutputter;

public class DocType extends Content
{
  private static final String CVS_ID = "@(#) $RCSfile: DocType.java,v $ $Revision: 1.32 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
  protected String elementName;
  protected String internalSubset;
  protected String publicID;
  protected String systemID;

  protected DocType()
  {
  }

  public DocType(String paramString)
  {
    this(paramString, null, null);
  }

  public DocType(String paramString1, String paramString2)
  {
    this(paramString1, null, paramString2);
  }

  public DocType(String paramString1, String paramString2, String paramString3)
  {
    setElementName(paramString1);
    setPublicID(paramString2);
    setSystemID(paramString3);
  }

  public String getElementName()
  {
    return this.elementName;
  }

  public String getInternalSubset()
  {
    return this.internalSubset;
  }

  public String getPublicID()
  {
    return this.publicID;
  }

  public String getSystemID()
  {
    return this.systemID;
  }

  public String getValue()
  {
    return "";
  }

  public DocType setElementName(String paramString)
  {
    String str = Verifier.checkXMLName(paramString);
    if (str != null)
      throw new IllegalNameException(paramString, "DocType", str);
    this.elementName = paramString;
    return this;
  }

  public void setInternalSubset(String paramString)
  {
    this.internalSubset = paramString;
  }

  public DocType setPublicID(String paramString)
  {
    String str = Verifier.checkPublicID(paramString);
    if (str != null)
      throw new IllegalDataException(paramString, "DocType", str);
    this.publicID = paramString;
    return this;
  }

  public DocType setSystemID(String paramString)
  {
    String str = Verifier.checkSystemLiteral(paramString);
    if (str != null)
      throw new IllegalDataException(paramString, "DocType", str);
    this.systemID = paramString;
    return this;
  }

  public String toString()
  {
    return "[DocType: " + new XMLOutputter().outputString(this) + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.DocType
 * JD-Core Version:    0.6.2
 */