package org.jdom;

import java.util.Map;

public class DefaultJDOMFactory
  implements JDOMFactory
{
  private static final String CVS_ID = "@(#) $RCSfile: DefaultJDOMFactory.java,v $ $Revision: 1.7 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";

  public void addContent(Parent paramParent, Content paramContent)
  {
    if ((paramParent instanceof Document))
    {
      ((Document)paramParent).addContent(paramContent);
      return;
    }
    ((Element)paramParent).addContent(paramContent);
  }

  public void addNamespaceDeclaration(Element paramElement, Namespace paramNamespace)
  {
    paramElement.addNamespaceDeclaration(paramNamespace);
  }

  public Attribute attribute(String paramString1, String paramString2)
  {
    return new Attribute(paramString1, paramString2);
  }

  public Attribute attribute(String paramString1, String paramString2, int paramInt)
  {
    return new Attribute(paramString1, paramString2, paramInt);
  }

  public Attribute attribute(String paramString1, String paramString2, int paramInt, Namespace paramNamespace)
  {
    return new Attribute(paramString1, paramString2, paramInt, paramNamespace);
  }

  public Attribute attribute(String paramString1, String paramString2, Namespace paramNamespace)
  {
    return new Attribute(paramString1, paramString2, paramNamespace);
  }

  public CDATA cdata(String paramString)
  {
    return new CDATA(paramString);
  }

  public Comment comment(String paramString)
  {
    return new Comment(paramString);
  }

  public DocType docType(String paramString)
  {
    return new DocType(paramString);
  }

  public DocType docType(String paramString1, String paramString2)
  {
    return new DocType(paramString1, paramString2);
  }

  public DocType docType(String paramString1, String paramString2, String paramString3)
  {
    return new DocType(paramString1, paramString2, paramString3);
  }

  public Document document(Element paramElement)
  {
    return new Document(paramElement);
  }

  public Document document(Element paramElement, DocType paramDocType)
  {
    return new Document(paramElement, paramDocType);
  }

  public Document document(Element paramElement, DocType paramDocType, String paramString)
  {
    return new Document(paramElement, paramDocType, paramString);
  }

  public Element element(String paramString)
  {
    return new Element(paramString);
  }

  public Element element(String paramString1, String paramString2)
  {
    return new Element(paramString1, paramString2);
  }

  public Element element(String paramString1, String paramString2, String paramString3)
  {
    return new Element(paramString1, paramString2, paramString3);
  }

  public Element element(String paramString, Namespace paramNamespace)
  {
    return new Element(paramString, paramNamespace);
  }

  public EntityRef entityRef(String paramString)
  {
    return new EntityRef(paramString);
  }

  public EntityRef entityRef(String paramString1, String paramString2)
  {
    return new EntityRef(paramString1, paramString2);
  }

  public EntityRef entityRef(String paramString1, String paramString2, String paramString3)
  {
    return new EntityRef(paramString1, paramString2, paramString3);
  }

  public ProcessingInstruction processingInstruction(String paramString1, String paramString2)
  {
    return new ProcessingInstruction(paramString1, paramString2);
  }

  public ProcessingInstruction processingInstruction(String paramString, Map paramMap)
  {
    return new ProcessingInstruction(paramString, paramMap);
  }

  public void setAttribute(Element paramElement, Attribute paramAttribute)
  {
    paramElement.setAttribute(paramAttribute);
  }

  public Text text(String paramString)
  {
    return new Text(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.DefaultJDOMFactory
 * JD-Core Version:    0.6.2
 */