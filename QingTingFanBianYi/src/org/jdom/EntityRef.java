package org.jdom;

public class EntityRef extends Content
{
  private static final String CVS_ID = "@(#) $RCSfile: EntityRef.java,v $ $Revision: 1.22 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
  protected String name;
  protected String publicID;
  protected String systemID;

  protected EntityRef()
  {
  }

  public EntityRef(String paramString)
  {
    this(paramString, null, null);
  }

  public EntityRef(String paramString1, String paramString2)
  {
    this(paramString1, null, paramString2);
  }

  public EntityRef(String paramString1, String paramString2, String paramString3)
  {
    setName(paramString1);
    setPublicID(paramString2);
    setSystemID(paramString3);
  }

  public String getName()
  {
    return this.name;
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

  public EntityRef setName(String paramString)
  {
    String str = Verifier.checkXMLName(paramString);
    if (str != null)
      throw new IllegalNameException(paramString, "EntityRef", str);
    this.name = paramString;
    return this;
  }

  public EntityRef setPublicID(String paramString)
  {
    String str = Verifier.checkPublicID(paramString);
    if (str != null)
      throw new IllegalDataException(paramString, "EntityRef", str);
    this.publicID = paramString;
    return this;
  }

  public EntityRef setSystemID(String paramString)
  {
    String str = Verifier.checkSystemLiteral(paramString);
    if (str != null)
      throw new IllegalDataException(paramString, "EntityRef", str);
    this.systemID = paramString;
    return this;
  }

  public String toString()
  {
    return "[EntityRef: " + "&" + this.name + ";" + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.EntityRef
 * JD-Core Version:    0.6.2
 */