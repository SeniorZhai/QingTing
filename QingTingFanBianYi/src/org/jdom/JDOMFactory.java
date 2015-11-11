package org.jdom;

import java.util.Map;

public abstract interface JDOMFactory
{
  public abstract void addContent(Parent paramParent, Content paramContent);

  public abstract void addNamespaceDeclaration(Element paramElement, Namespace paramNamespace);

  public abstract Attribute attribute(String paramString1, String paramString2);

  public abstract Attribute attribute(String paramString1, String paramString2, int paramInt);

  public abstract Attribute attribute(String paramString1, String paramString2, int paramInt, Namespace paramNamespace);

  public abstract Attribute attribute(String paramString1, String paramString2, Namespace paramNamespace);

  public abstract CDATA cdata(String paramString);

  public abstract Comment comment(String paramString);

  public abstract DocType docType(String paramString);

  public abstract DocType docType(String paramString1, String paramString2);

  public abstract DocType docType(String paramString1, String paramString2, String paramString3);

  public abstract Document document(Element paramElement);

  public abstract Document document(Element paramElement, DocType paramDocType);

  public abstract Document document(Element paramElement, DocType paramDocType, String paramString);

  public abstract Element element(String paramString);

  public abstract Element element(String paramString1, String paramString2);

  public abstract Element element(String paramString1, String paramString2, String paramString3);

  public abstract Element element(String paramString, Namespace paramNamespace);

  public abstract EntityRef entityRef(String paramString);

  public abstract EntityRef entityRef(String paramString1, String paramString2);

  public abstract EntityRef entityRef(String paramString1, String paramString2, String paramString3);

  public abstract ProcessingInstruction processingInstruction(String paramString1, String paramString2);

  public abstract ProcessingInstruction processingInstruction(String paramString, Map paramMap);

  public abstract void setAttribute(Element paramElement, Attribute paramAttribute);

  public abstract Text text(String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.JDOMFactory
 * JD-Core Version:    0.6.2
 */