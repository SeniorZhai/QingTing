package org.jdom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UncheckedJDOMFactory
  implements JDOMFactory
{
  public void addContent(Parent paramParent, Content paramContent)
  {
    if ((paramParent instanceof Element))
    {
      ((Element)paramParent).content.uncheckedAddContent(paramContent);
      return;
    }
    ((Document)paramParent).content.uncheckedAddContent(paramContent);
  }

  public void addNamespaceDeclaration(Element paramElement, Namespace paramNamespace)
  {
    if (paramElement.additionalNamespaces == null)
      paramElement.additionalNamespaces = new ArrayList(5);
    paramElement.additionalNamespaces.add(paramNamespace);
  }

  public Attribute attribute(String paramString1, String paramString2)
  {
    Attribute localAttribute = new Attribute();
    localAttribute.name = paramString1;
    localAttribute.value = paramString2;
    localAttribute.namespace = Namespace.NO_NAMESPACE;
    return localAttribute;
  }

  public Attribute attribute(String paramString1, String paramString2, int paramInt)
  {
    Attribute localAttribute = new Attribute();
    localAttribute.name = paramString1;
    localAttribute.type = paramInt;
    localAttribute.value = paramString2;
    localAttribute.namespace = Namespace.NO_NAMESPACE;
    return localAttribute;
  }

  public Attribute attribute(String paramString1, String paramString2, int paramInt, Namespace paramNamespace)
  {
    Attribute localAttribute = new Attribute();
    localAttribute.name = paramString1;
    localAttribute.type = paramInt;
    localAttribute.value = paramString2;
    paramString1 = paramNamespace;
    if (paramNamespace == null)
      paramString1 = Namespace.NO_NAMESPACE;
    localAttribute.namespace = paramString1;
    return localAttribute;
  }

  public Attribute attribute(String paramString1, String paramString2, Namespace paramNamespace)
  {
    Attribute localAttribute = new Attribute();
    localAttribute.name = paramString1;
    localAttribute.value = paramString2;
    paramString1 = paramNamespace;
    if (paramNamespace == null)
      paramString1 = Namespace.NO_NAMESPACE;
    localAttribute.namespace = paramString1;
    return localAttribute;
  }

  public CDATA cdata(String paramString)
  {
    CDATA localCDATA = new CDATA();
    localCDATA.value = paramString;
    return localCDATA;
  }

  public Comment comment(String paramString)
  {
    Comment localComment = new Comment();
    localComment.text = paramString;
    return localComment;
  }

  public DocType docType(String paramString)
  {
    return docType(paramString, null, null);
  }

  public DocType docType(String paramString1, String paramString2)
  {
    return docType(paramString1, null, paramString2);
  }

  public DocType docType(String paramString1, String paramString2, String paramString3)
  {
    DocType localDocType = new DocType();
    localDocType.elementName = paramString1;
    localDocType.publicID = paramString2;
    localDocType.systemID = paramString3;
    return localDocType;
  }

  public Document document(Element paramElement)
  {
    return document(paramElement, null, null);
  }

  public Document document(Element paramElement, DocType paramDocType)
  {
    return document(paramElement, paramDocType, null);
  }

  public Document document(Element paramElement, DocType paramDocType, String paramString)
  {
    Document localDocument = new Document();
    if (paramDocType != null)
      addContent(localDocument, paramDocType);
    if (paramElement != null)
      addContent(localDocument, paramElement);
    if (paramString != null)
      localDocument.baseURI = paramString;
    return localDocument;
  }

  public Element element(String paramString)
  {
    Element localElement = new Element();
    localElement.name = paramString;
    localElement.namespace = Namespace.NO_NAMESPACE;
    return localElement;
  }

  public Element element(String paramString1, String paramString2)
  {
    return element(paramString1, Namespace.getNamespace("", paramString2));
  }

  public Element element(String paramString1, String paramString2, String paramString3)
  {
    return element(paramString1, Namespace.getNamespace(paramString2, paramString3));
  }

  public Element element(String paramString, Namespace paramNamespace)
  {
    Element localElement = new Element();
    localElement.name = paramString;
    paramString = paramNamespace;
    if (paramNamespace == null)
      paramString = Namespace.NO_NAMESPACE;
    localElement.namespace = paramString;
    return localElement;
  }

  public EntityRef entityRef(String paramString)
  {
    EntityRef localEntityRef = new EntityRef();
    localEntityRef.name = paramString;
    return localEntityRef;
  }

  public EntityRef entityRef(String paramString1, String paramString2)
  {
    EntityRef localEntityRef = new EntityRef();
    localEntityRef.name = paramString1;
    localEntityRef.systemID = paramString2;
    return localEntityRef;
  }

  public EntityRef entityRef(String paramString1, String paramString2, String paramString3)
  {
    EntityRef localEntityRef = new EntityRef();
    localEntityRef.name = paramString1;
    localEntityRef.publicID = paramString2;
    localEntityRef.systemID = paramString3;
    return localEntityRef;
  }

  public ProcessingInstruction processingInstruction(String paramString1, String paramString2)
  {
    ProcessingInstruction localProcessingInstruction = new ProcessingInstruction();
    localProcessingInstruction.target = paramString1;
    localProcessingInstruction.setData(paramString2);
    return localProcessingInstruction;
  }

  public ProcessingInstruction processingInstruction(String paramString, Map paramMap)
  {
    ProcessingInstruction localProcessingInstruction = new ProcessingInstruction();
    localProcessingInstruction.target = paramString;
    localProcessingInstruction.setData(paramMap);
    return localProcessingInstruction;
  }

  public void setAttribute(Element paramElement, Attribute paramAttribute)
  {
    paramElement.attributes.uncheckedAddAttribute(paramAttribute);
  }

  public Text text(String paramString)
  {
    Text localText = new Text();
    localText.value = paramString;
    return localText;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.UncheckedJDOMFactory
 * JD-Core Version:    0.6.2
 */