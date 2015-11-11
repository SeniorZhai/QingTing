package org.jdom.adapters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.jdom.DocType;
import org.jdom.JDOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

public abstract class AbstractDOMAdapter
  implements DOMAdapter
{
  private static final String CVS_ID = "@(#) $RCSfile: AbstractDOMAdapter.java,v $ $Revision: 1.21 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
  static Class class$java$lang$String;

  static Class class$(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
    }
    throw new NoClassDefFoundError(paramString.getMessage());
  }

  public abstract Document createDocument()
    throws JDOMException;

  public Document createDocument(DocType paramDocType)
    throws JDOMException
  {
    if (paramDocType == null)
      return createDocument();
    DOMImplementation localDOMImplementation = createDocument().getImplementation();
    DocumentType localDocumentType = localDOMImplementation.createDocumentType(paramDocType.getElementName(), paramDocType.getPublicID(), paramDocType.getSystemID());
    setInternalSubset(localDocumentType, paramDocType.getInternalSubset());
    return localDOMImplementation.createDocument("http://temporary", paramDocType.getElementName(), localDocumentType);
  }

  public Document getDocument(File paramFile, boolean paramBoolean)
    throws IOException, JDOMException
  {
    return getDocument(new FileInputStream(paramFile), paramBoolean);
  }

  public abstract Document getDocument(InputStream paramInputStream, boolean paramBoolean)
    throws IOException, JDOMException;

  protected void setInternalSubset(DocumentType paramDocumentType, String paramString)
  {
    if ((paramDocumentType == null) || (paramString == null))
      return;
    try
    {
      Class localClass2 = paramDocumentType.getClass();
      Class localClass1;
      if (class$java$lang$String == null)
      {
        localClass1 = class$("java.lang.String");
        class$java$lang$String = localClass1;
      }
      while (true)
      {
        localClass2.getMethod("setInternalSubset", new Class[] { localClass1 }).invoke(paramDocumentType, new Object[] { paramString });
        return;
        localClass1 = class$java$lang$String;
      }
    }
    catch (Exception paramDocumentType)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.adapters.AbstractDOMAdapter
 * JD-Core Version:    0.6.2
 */