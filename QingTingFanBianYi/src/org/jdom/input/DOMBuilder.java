package org.jdom.input;

import org.jdom.Content;
import org.jdom.DefaultJDOMFactory;
import org.jdom.DocType;
import org.jdom.JDOMFactory;
import org.jdom.Namespace;
import org.w3c.dom.Attr;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMBuilder
{
  private static final String CVS_ID = "@(#) $RCSfile: DOMBuilder.java,v $ $Revision: 1.60 $ $Date: 2007/11/10 05:29:00 $ $Name: jdom_1_1_1 $";
  private String adapterClass;
  private JDOMFactory factory = new DefaultJDOMFactory();

  public DOMBuilder()
  {
  }

  public DOMBuilder(String paramString)
  {
    this.adapterClass = paramString;
  }

  private void buildTree(Node paramNode, org.jdom.Document paramDocument, org.jdom.Element paramElement, boolean paramBoolean)
  {
    switch (paramNode.getNodeType())
    {
    case 2:
    case 6:
    default:
    case 9:
    case 1:
      Object localObject3;
      do
      {
        while (true)
        {
          return;
          paramNode = paramNode.getChildNodes();
          i = 0;
          j = paramNode.getLength();
          while (i < j)
          {
            buildTree(paramNode.item(i), paramDocument, paramElement, true);
            i += 1;
          }
        }
        localObject1 = paramNode.getNodeName();
        localObject2 = "";
        localObject3 = localObject1;
        i = ((String)localObject1).indexOf(':');
        if (i >= 0)
        {
          localObject2 = ((String)localObject1).substring(0, i);
          localObject3 = ((String)localObject1).substring(i + 1);
        }
        localObject1 = paramNode.getNamespaceURI();
        NamedNodeMap localNamedNodeMap;
        Object localObject4;
        int k;
        if (localObject1 == null)
          if (paramElement == null)
          {
            localObject1 = Namespace.NO_NAMESPACE;
            localObject3 = this.factory.element((String)localObject3, (Namespace)localObject1);
            if (!paramBoolean)
              break label359;
            paramDocument.setRootElement((org.jdom.Element)localObject3);
            localNamedNodeMap = paramNode.getAttributes();
            j = localNamedNodeMap.getLength();
            i = 0;
            if (i >= j)
              break label390;
            localObject1 = (Attr)localNamedNodeMap.item(i);
            localObject4 = ((Attr)localObject1).getName();
            if (((String)localObject4).startsWith("xmlns"))
            {
              paramElement = "";
              k = ((String)localObject4).indexOf(':');
              if (k >= 0)
                paramElement = ((String)localObject4).substring(k + 1);
              localObject1 = Namespace.getNamespace(paramElement, ((Attr)localObject1).getValue());
              if (!((String)localObject2).equals(paramElement))
                break label374;
              ((org.jdom.Element)localObject3).setNamespace((Namespace)localObject1);
            }
          }
        while (true)
        {
          i += 1;
          break label230;
          localObject1 = paramElement.getNamespace((String)localObject2);
          break;
          localObject1 = Namespace.getNamespace((String)localObject2, (String)localObject1);
          break;
          this.factory.addContent(paramElement, (Content)localObject3);
          break label210;
          this.factory.addNamespaceDeclaration((org.jdom.Element)localObject3, (Namespace)localObject1);
        }
        i = 0;
        if (i < j)
        {
          localObject4 = (Attr)localNamedNodeMap.item(i);
          localObject2 = ((Attr)localObject4).getName();
          if (!((String)localObject2).startsWith("xmlns"))
          {
            localObject1 = "";
            paramElement = (org.jdom.Element)localObject2;
            k = ((String)localObject2).indexOf(':');
            if (k >= 0)
            {
              localObject1 = ((String)localObject2).substring(0, k);
              paramElement = ((String)localObject2).substring(k + 1);
            }
            localObject2 = ((Attr)localObject4).getValue();
            if (!"".equals(localObject1))
              break label534;
          }
          for (localObject1 = Namespace.NO_NAMESPACE; ; localObject1 = ((org.jdom.Element)localObject3).getNamespace((String)localObject1))
          {
            paramElement = this.factory.attribute(paramElement, (String)localObject2, (Namespace)localObject1);
            this.factory.setAttribute((org.jdom.Element)localObject3, paramElement);
            i += 1;
            break;
          }
        }
        paramNode = paramNode.getChildNodes();
      }
      while (paramNode == null);
      int j = paramNode.getLength();
      int i = 0;
      while (i < j)
      {
        paramElement = paramNode.item(i);
        if (paramElement != null)
          buildTree(paramElement, paramDocument, (org.jdom.Element)localObject3, false);
        i += 1;
      }
    case 3:
      paramNode = paramNode.getNodeValue();
      this.factory.addContent(paramElement, this.factory.text(paramNode));
      return;
    case 4:
      paramNode = paramNode.getNodeValue();
      this.factory.addContent(paramElement, this.factory.cdata(paramNode));
      return;
    case 7:
      if (paramBoolean)
      {
        this.factory.addContent(paramDocument, this.factory.processingInstruction(paramNode.getNodeName(), paramNode.getNodeValue()));
        return;
      }
      this.factory.addContent(paramElement, this.factory.processingInstruction(paramNode.getNodeName(), paramNode.getNodeValue()));
      return;
    case 8:
      if (paramBoolean)
      {
        this.factory.addContent(paramDocument, this.factory.comment(paramNode.getNodeValue()));
        return;
      }
      this.factory.addContent(paramElement, this.factory.comment(paramNode.getNodeValue()));
      return;
    case 5:
      label210: label230: label359: label374: paramNode = this.factory.entityRef(paramNode.getNodeName());
      label390: label534: this.factory.addContent(paramElement, paramNode);
      return;
    case 10:
    }
    Object localObject2 = (DocumentType)paramNode;
    paramNode = ((DocumentType)localObject2).getPublicId();
    paramElement = ((DocumentType)localObject2).getSystemId();
    Object localObject1 = ((DocumentType)localObject2).getInternalSubset();
    localObject2 = this.factory.docType(((DocumentType)localObject2).getName());
    ((DocType)localObject2).setPublicID(paramNode);
    ((DocType)localObject2).setSystemID(paramElement);
    ((DocType)localObject2).setInternalSubset((String)localObject1);
    this.factory.addContent(paramDocument, (Content)localObject2);
  }

  public org.jdom.Document build(org.w3c.dom.Document paramDocument)
  {
    org.jdom.Document localDocument = this.factory.document(null);
    buildTree(paramDocument, localDocument, null, true);
    return localDocument;
  }

  public org.jdom.Element build(org.w3c.dom.Element paramElement)
  {
    org.jdom.Document localDocument = this.factory.document(null);
    buildTree(paramElement, localDocument, null, true);
    return localDocument.getRootElement();
  }

  public JDOMFactory getFactory()
  {
    return this.factory;
  }

  public void setFactory(JDOMFactory paramJDOMFactory)
  {
    this.factory = paramJDOMFactory;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.input.DOMBuilder
 * JD-Core Version:    0.6.2
 */