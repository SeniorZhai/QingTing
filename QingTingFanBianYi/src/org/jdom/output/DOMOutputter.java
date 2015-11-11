package org.jdom.output;

import java.util.Iterator;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Comment;
import org.jdom.DocType;
import org.jdom.EntityRef;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.ProcessingInstruction;
import org.jdom.Text;
import org.jdom.adapters.DOMAdapter;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

public class DOMOutputter
{
  private static final String CVS_ID = "@(#) $RCSfile: DOMOutputter.java,v $ $Revision: 1.43 $ $Date: 2007/11/10 05:29:01 $ $Name: jdom_1_1_1 $";
  private static final String DEFAULT_ADAPTER_CLASS = "org.jdom.adapters.XercesDOMAdapter";
  private String adapterClass;
  private boolean forceNamespaceAware;

  public DOMOutputter()
  {
  }

  public DOMOutputter(String paramString)
  {
    this.adapterClass = paramString;
  }

  private org.w3c.dom.Document createDOMDocument(DocType paramDocType)
    throws JDOMException
  {
    if (this.adapterClass != null);
    try
    {
      org.w3c.dom.Document localDocument = ((DOMAdapter)Class.forName(this.adapterClass).newInstance()).createDocument(paramDocType);
      return localDocument;
      try
      {
        localDocument = ((DOMAdapter)Class.forName("org.jdom.adapters.JAXPDOMAdapter").newInstance()).createDocument(paramDocType);
        return localDocument;
      }
      catch (InstantiationException localInstantiationException1)
      {
        try
        {
          paramDocType = ((DOMAdapter)Class.forName("org.jdom.adapters.XercesDOMAdapter").newInstance()).createDocument(paramDocType);
          return paramDocType;
        }
        catch (InstantiationException paramDocType)
        {
          throw new JDOMException("No JAXP or default parser available");
        }
        catch (IllegalAccessException paramDocType)
        {
          break label71;
        }
        catch (ClassNotFoundException paramDocType)
        {
          break label71;
        }
      }
      catch (IllegalAccessException localIllegalAccessException1)
      {
        break label50;
      }
      catch (ClassNotFoundException localClassNotFoundException1)
      {
        label50: label71: break label50;
      }
    }
    catch (InstantiationException localInstantiationException2)
    {
      break label50;
    }
    catch (IllegalAccessException localIllegalAccessException2)
    {
      break label50;
    }
    catch (ClassNotFoundException localClassNotFoundException2)
    {
      break label50;
    }
  }

  private static String getXmlnsTagFor(Namespace paramNamespace)
  {
    String str = "xmlns";
    if (!paramNamespace.getPrefix().equals(""))
    {
      str = "xmlns" + ":";
      str = str + paramNamespace.getPrefix();
    }
    return str;
  }

  private Attr output(Attribute paramAttribute, org.w3c.dom.Document paramDocument)
    throws JDOMException
  {
    try
    {
      if (paramAttribute.getNamespace() == Namespace.NO_NAMESPACE)
        if (this.forceNamespaceAware)
          paramDocument = paramDocument.createAttributeNS(null, paramAttribute.getQualifiedName());
      while (true)
      {
        paramDocument.setValue(paramAttribute.getValue());
        return paramDocument;
        paramDocument = paramDocument.createAttribute(paramAttribute.getQualifiedName());
        continue;
        paramDocument = paramDocument.createAttributeNS(paramAttribute.getNamespaceURI(), paramAttribute.getQualifiedName());
      }
    }
    catch (Exception paramDocument)
    {
    }
    throw new JDOMException("Exception outputting Attribute " + paramAttribute.getQualifiedName(), paramDocument);
  }

  private org.w3c.dom.Element output(org.jdom.Element paramElement, org.w3c.dom.Document paramDocument, NamespaceStack paramNamespaceStack)
    throws JDOMException
  {
    int i;
    org.w3c.dom.Element localElement;
    Object localObject2;
    Object localObject3;
    while (true)
    {
      try
      {
        i = paramNamespaceStack.size();
        if (paramElement.getNamespace() != Namespace.NO_NAMESPACE)
          break label244;
        if (this.forceNamespaceAware)
        {
          localElement = paramDocument.createElementNS(null, paramElement.getQualifiedName());
          localObject1 = paramElement.getNamespace();
          if ((localObject1 != Namespace.XML_NAMESPACE) && ((localObject1 != Namespace.NO_NAMESPACE) || (paramNamespaceStack.getURI("") != null)))
          {
            localObject2 = paramNamespaceStack.getURI(((Namespace)localObject1).getPrefix());
            if (!((Namespace)localObject1).getURI().equals(localObject2))
            {
              paramNamespaceStack.push((Namespace)localObject1);
              localElement.setAttribute(getXmlnsTagFor((Namespace)localObject1), ((Namespace)localObject1).getURI());
            }
          }
          localObject1 = paramElement.getAdditionalNamespaces().iterator();
          if (!((Iterator)localObject1).hasNext())
            break;
          localObject2 = (Namespace)((Iterator)localObject1).next();
          localObject3 = paramNamespaceStack.getURI(((Namespace)localObject2).getPrefix());
          if (((Namespace)localObject2).getURI().equals(localObject3))
            continue;
          localElement.setAttribute(getXmlnsTagFor((Namespace)localObject2), ((Namespace)localObject2).getURI());
          paramNamespaceStack.push((Namespace)localObject2);
          continue;
        }
      }
      catch (Exception paramDocument)
      {
        throw new JDOMException("Exception outputting Element " + paramElement.getQualifiedName(), paramDocument);
      }
      localElement = paramDocument.createElement(paramElement.getQualifiedName());
      continue;
      label244: localElement = paramDocument.createElementNS(paramElement.getNamespaceURI(), paramElement.getQualifiedName());
    }
    Object localObject1 = paramElement.getAttributes().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Attribute)((Iterator)localObject1).next();
      localElement.setAttributeNode(output((Attribute)localObject2, paramDocument));
      localObject3 = ((Attribute)localObject2).getNamespace();
      if ((localObject3 != Namespace.NO_NAMESPACE) && (localObject3 != Namespace.XML_NAMESPACE))
      {
        String str = paramNamespaceStack.getURI(((Namespace)localObject3).getPrefix());
        if (!((Namespace)localObject3).getURI().equals(str))
        {
          localElement.setAttribute(getXmlnsTagFor((Namespace)localObject3), ((Namespace)localObject3).getURI());
          paramNamespaceStack.push((Namespace)localObject3);
        }
      }
      if (((Attribute)localObject2).getNamespace() == Namespace.NO_NAMESPACE)
      {
        if (this.forceNamespaceAware)
          localElement.setAttributeNS(null, ((Attribute)localObject2).getQualifiedName(), ((Attribute)localObject2).getValue());
        else
          localElement.setAttribute(((Attribute)localObject2).getQualifiedName(), ((Attribute)localObject2).getValue());
      }
      else
        localElement.setAttributeNS(((Attribute)localObject2).getNamespaceURI(), ((Attribute)localObject2).getQualifiedName(), ((Attribute)localObject2).getValue());
    }
    localObject1 = paramElement.getContent().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = ((Iterator)localObject1).next();
      if ((localObject2 instanceof org.jdom.Element))
      {
        localElement.appendChild(output((org.jdom.Element)localObject2, paramDocument, paramNamespaceStack));
      }
      else if ((localObject2 instanceof String))
      {
        localElement.appendChild(paramDocument.createTextNode((String)localObject2));
      }
      else if ((localObject2 instanceof CDATA))
      {
        localElement.appendChild(paramDocument.createCDATASection(((CDATA)localObject2).getText()));
      }
      else if ((localObject2 instanceof Text))
      {
        localElement.appendChild(paramDocument.createTextNode(((Text)localObject2).getText()));
      }
      else if ((localObject2 instanceof Comment))
      {
        localElement.appendChild(paramDocument.createComment(((Comment)localObject2).getText()));
      }
      else if ((localObject2 instanceof ProcessingInstruction))
      {
        localObject2 = (ProcessingInstruction)localObject2;
        localElement.appendChild(paramDocument.createProcessingInstruction(((ProcessingInstruction)localObject2).getTarget(), ((ProcessingInstruction)localObject2).getData()));
      }
      else if ((localObject2 instanceof EntityRef))
      {
        localElement.appendChild(paramDocument.createEntityReference(((EntityRef)localObject2).getName()));
      }
      else
      {
        throw new JDOMException("Element contained content with type:" + localObject2.getClass().getName());
      }
    }
    while (paramNamespaceStack.size() > i)
      paramNamespaceStack.pop();
    return localElement;
  }

  public boolean getForceNamespaceAware()
  {
    return this.forceNamespaceAware;
  }

  public org.w3c.dom.Document output(org.jdom.Document paramDocument)
    throws JDOMException
  {
    NamespaceStack localNamespaceStack = new NamespaceStack();
    org.w3c.dom.Document localDocument;
    Object localObject;
    label117: 
    do
      while (true)
      {
        org.w3c.dom.Element localElement;
        try
        {
          localDocument = createDOMDocument(paramDocument.getDocType());
          paramDocument = paramDocument.getContent().iterator();
          if (!paramDocument.hasNext())
            break label233;
          localObject = paramDocument.next();
          if (!(localObject instanceof org.jdom.Element))
            break label117;
          localObject = output((org.jdom.Element)localObject, localDocument, localNamespaceStack);
          localElement = localDocument.getDocumentElement();
          if (localElement == null)
          {
            localDocument.appendChild((Node)localObject);
            continue;
          }
        }
        catch (Throwable paramDocument)
        {
          throw new JDOMException("Exception outputting Document", paramDocument);
        }
        localDocument.replaceChild((Node)localObject, localElement);
        continue;
        if ((localObject instanceof Comment))
        {
          localDocument.appendChild(localDocument.createComment(((Comment)localObject).getText()));
        }
        else
        {
          if (!(localObject instanceof ProcessingInstruction))
            break;
          localObject = (ProcessingInstruction)localObject;
          localDocument.appendChild(localDocument.createProcessingInstruction(((ProcessingInstruction)localObject).getTarget(), ((ProcessingInstruction)localObject).getData()));
        }
      }
    while ((localObject instanceof DocType));
    throw new JDOMException("Document contained top-level content with type:" + localObject.getClass().getName());
    label233: return localDocument;
  }

  public void setForceNamespaceAware(boolean paramBoolean)
  {
    this.forceNamespaceAware = paramBoolean;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.output.DOMOutputter
 * JD-Core Version:    0.6.2
 */