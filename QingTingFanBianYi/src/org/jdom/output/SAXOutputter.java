package org.jdom.output;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Comment;
import org.jdom.Content;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.EntityRef;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.ProcessingInstruction;
import org.jdom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXOutputter
{
  private static final String CVS_ID = "@(#) $RCSfile: SAXOutputter.java,v $ $Revision: 1.40 $ $Date: 2007/11/10 05:29:01 $ $Name: jdom_1_1_1 $";
  private static final String DECL_HANDLER_ALT_PROPERTY = "http://xml.org/sax/handlers/DeclHandler";
  private static final String DECL_HANDLER_SAX_PROPERTY = "http://xml.org/sax/properties/declaration-handler";
  private static final String LEXICAL_HANDLER_ALT_PROPERTY = "http://xml.org/sax/handlers/LexicalHandler";
  private static final String LEXICAL_HANDLER_SAX_PROPERTY = "http://xml.org/sax/properties/lexical-handler";
  private static final String NAMESPACES_SAX_FEATURE = "http://xml.org/sax/features/namespaces";
  private static final String NS_PREFIXES_SAX_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
  private static final String VALIDATION_SAX_FEATURE = "http://xml.org/sax/features/validation";
  private static final String[] attrTypeToNameMap = { "CDATA", "CDATA", "ID", "IDREF", "IDREFS", "ENTITY", "ENTITIES", "NMTOKEN", "NMTOKENS", "NOTATION", "NMTOKEN" };
  private ContentHandler contentHandler;
  private DeclHandler declHandler;
  private boolean declareNamespaces = false;
  private DTDHandler dtdHandler;
  private EntityResolver entityResolver;
  private ErrorHandler errorHandler;
  private LexicalHandler lexicalHandler;
  private JDOMLocator locator = null;
  private boolean reportDtdEvents = true;

  public SAXOutputter()
  {
  }

  public SAXOutputter(ContentHandler paramContentHandler)
  {
    this(paramContentHandler, null, null, null, null);
  }

  public SAXOutputter(ContentHandler paramContentHandler, ErrorHandler paramErrorHandler, DTDHandler paramDTDHandler, EntityResolver paramEntityResolver)
  {
    this(paramContentHandler, paramErrorHandler, paramDTDHandler, paramEntityResolver, null);
  }

  public SAXOutputter(ContentHandler paramContentHandler, ErrorHandler paramErrorHandler, DTDHandler paramDTDHandler, EntityResolver paramEntityResolver, LexicalHandler paramLexicalHandler)
  {
    this.contentHandler = paramContentHandler;
    this.errorHandler = paramErrorHandler;
    this.dtdHandler = paramDTDHandler;
    this.entityResolver = paramEntityResolver;
    this.lexicalHandler = paramLexicalHandler;
  }

  private AttributesImpl addNsAttribute(AttributesImpl paramAttributesImpl, Namespace paramNamespace)
  {
    AttributesImpl localAttributesImpl = paramAttributesImpl;
    if (this.declareNamespaces)
    {
      localAttributesImpl = paramAttributesImpl;
      if (paramAttributesImpl == null)
        localAttributesImpl = new AttributesImpl();
      if (paramNamespace.getPrefix().equals(""))
        localAttributesImpl.addAttribute("", "", "xmlns", "CDATA", paramNamespace.getURI());
    }
    else
    {
      return localAttributesImpl;
    }
    localAttributesImpl.addAttribute("", "", "xmlns:" + paramNamespace.getPrefix(), "CDATA", paramNamespace.getURI());
    return localAttributesImpl;
  }

  private void cdata(String paramString)
    throws JDOMException
  {
    try
    {
      if (this.lexicalHandler != null)
      {
        this.lexicalHandler.startCDATA();
        characters(paramString);
        this.lexicalHandler.endCDATA();
        return;
      }
      characters(paramString);
      return;
    }
    catch (SAXException paramString)
    {
    }
    throw new JDOMException("Exception in CDATA", paramString);
  }

  private void characters(String paramString)
    throws JDOMException
  {
    paramString = paramString.toCharArray();
    try
    {
      this.contentHandler.characters(paramString, 0, paramString.length);
      return;
    }
    catch (SAXException paramString)
    {
    }
    throw new JDOMException("Exception in characters", paramString);
  }

  private void comment(String paramString)
    throws JDOMException
  {
    if (this.lexicalHandler != null)
      paramString = paramString.toCharArray();
    try
    {
      this.lexicalHandler.comment(paramString, 0, paramString.length);
      return;
    }
    catch (SAXException paramString)
    {
    }
    throw new JDOMException("Exception in comment", paramString);
  }

  // ERROR //
  private XMLReader createDTDParser()
    throws JDOMException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 181	org/jdom/output/SAXOutputter:createParser	()Lorg/xml/sax/XMLReader;
    //   4: astore_1
    //   5: aload_0
    //   6: invokevirtual 185	org/jdom/output/SAXOutputter:getDTDHandler	()Lorg/xml/sax/DTDHandler;
    //   9: ifnull +13 -> 22
    //   12: aload_1
    //   13: aload_0
    //   14: invokevirtual 185	org/jdom/output/SAXOutputter:getDTDHandler	()Lorg/xml/sax/DTDHandler;
    //   17: invokeinterface 191 2 0
    //   22: aload_0
    //   23: invokevirtual 195	org/jdom/output/SAXOutputter:getEntityResolver	()Lorg/xml/sax/EntityResolver;
    //   26: ifnull +13 -> 39
    //   29: aload_1
    //   30: aload_0
    //   31: invokevirtual 195	org/jdom/output/SAXOutputter:getEntityResolver	()Lorg/xml/sax/EntityResolver;
    //   34: invokeinterface 199 2 0
    //   39: aload_0
    //   40: invokevirtual 203	org/jdom/output/SAXOutputter:getLexicalHandler	()Lorg/xml/sax/ext/LexicalHandler;
    //   43: ifnull +15 -> 58
    //   46: aload_1
    //   47: ldc 20
    //   49: aload_0
    //   50: invokevirtual 203	org/jdom/output/SAXOutputter:getLexicalHandler	()Lorg/xml/sax/ext/LexicalHandler;
    //   53: invokeinterface 207 3 0
    //   58: aload_0
    //   59: invokevirtual 211	org/jdom/output/SAXOutputter:getDeclHandler	()Lorg/xml/sax/ext/DeclHandler;
    //   62: ifnull +15 -> 77
    //   65: aload_1
    //   66: ldc 14
    //   68: aload_0
    //   69: invokevirtual 211	org/jdom/output/SAXOutputter:getDeclHandler	()Lorg/xml/sax/ext/DeclHandler;
    //   72: invokeinterface 207 3 0
    //   77: aload_1
    //   78: new 213	org/xml/sax/helpers/DefaultHandler
    //   81: dup
    //   82: invokespecial 214	org/xml/sax/helpers/DefaultHandler:<init>	()V
    //   85: invokeinterface 218 2 0
    //   90: aload_1
    //   91: areturn
    //   92: astore_1
    //   93: new 139	org/jdom/JDOMException
    //   96: dup
    //   97: ldc 220
    //   99: aload_1
    //   100: invokespecial 157	org/jdom/JDOMException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   103: athrow
    //   104: astore_2
    //   105: aload_1
    //   106: ldc 17
    //   108: aload_0
    //   109: invokevirtual 203	org/jdom/output/SAXOutputter:getLexicalHandler	()Lorg/xml/sax/ext/LexicalHandler;
    //   112: invokeinterface 207 3 0
    //   117: goto -59 -> 58
    //   120: astore_2
    //   121: goto -63 -> 58
    //   124: astore_2
    //   125: aload_1
    //   126: ldc 11
    //   128: aload_0
    //   129: invokevirtual 211	org/jdom/output/SAXOutputter:getDeclHandler	()Lorg/xml/sax/ext/DeclHandler;
    //   132: invokeinterface 207 3 0
    //   137: goto -60 -> 77
    //   140: astore_2
    //   141: goto -64 -> 77
    //
    // Exception table:
    //   from	to	target	type
    //   0	5	92	java/lang/Exception
    //   46	58	104	org/xml/sax/SAXException
    //   105	117	120	org/xml/sax/SAXException
    //   65	77	124	org/xml/sax/SAXException
    //   125	137	140	org/xml/sax/SAXException
  }

  private void documentLocator(Document paramDocument)
  {
    this.locator = new JDOMLocator();
    Object localObject3 = null;
    Object localObject4 = null;
    Object localObject2 = localObject3;
    Object localObject1 = localObject4;
    if (paramDocument != null)
    {
      paramDocument = paramDocument.getDocType();
      localObject2 = localObject3;
      localObject1 = localObject4;
      if (paramDocument != null)
      {
        localObject2 = paramDocument.getPublicID();
        localObject1 = paramDocument.getSystemID();
      }
    }
    this.locator.setPublicId((String)localObject2);
    this.locator.setSystemId((String)localObject1);
    this.locator.setLineNumber(-1);
    this.locator.setColumnNumber(-1);
    this.contentHandler.setDocumentLocator(this.locator);
  }

  private void dtdEvents(Document paramDocument)
    throws JDOMException
  {
    paramDocument = paramDocument.getDocType();
    if ((paramDocument != null) && ((this.dtdHandler != null) || (this.declHandler != null)))
      paramDocument = new XMLOutputter().outputString(paramDocument);
    try
    {
      createDTDParser().parse(new InputSource(new StringReader(paramDocument)));
      return;
    }
    catch (SAXException paramDocument)
    {
      throw new JDOMException("DTD parsing error", paramDocument);
    }
    catch (IOException paramDocument)
    {
      throw new JDOMException("DTD parsing error", paramDocument);
    }
    catch (SAXParseException paramDocument)
    {
    }
  }

  private void element(Element paramElement, NamespaceStack paramNamespaceStack)
    throws JDOMException
  {
    int i = paramNamespaceStack.size();
    startElement(paramElement, startPrefixMapping(paramElement, paramNamespaceStack));
    elementContent(paramElement.getContent(), paramNamespaceStack);
    if (this.locator != null)
      this.locator.setNode(paramElement);
    endElement(paramElement);
    endPrefixMapping(paramNamespaceStack, i);
  }

  private void elementContent(List paramList, NamespaceStack paramNamespaceStack)
    throws JDOMException
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = paramList.next();
      if ((localObject instanceof Content))
        elementContent((Content)localObject, paramNamespaceStack);
      else
        handleError(new JDOMException("Invalid element content: " + localObject));
    }
  }

  private void elementContent(Content paramContent, NamespaceStack paramNamespaceStack)
    throws JDOMException
  {
    if (this.locator != null)
      this.locator.setNode(paramContent);
    if ((paramContent instanceof Element))
    {
      element((Element)paramContent, paramNamespaceStack);
      return;
    }
    if ((paramContent instanceof CDATA))
    {
      cdata(((CDATA)paramContent).getText());
      return;
    }
    if ((paramContent instanceof Text))
    {
      characters(((Text)paramContent).getText());
      return;
    }
    if ((paramContent instanceof ProcessingInstruction))
    {
      processingInstruction((ProcessingInstruction)paramContent);
      return;
    }
    if ((paramContent instanceof Comment))
    {
      comment(((Comment)paramContent).getText());
      return;
    }
    if ((paramContent instanceof EntityRef))
    {
      entityRef((EntityRef)paramContent);
      return;
    }
    handleError(new JDOMException("Invalid element content: " + paramContent));
  }

  private void endDocument()
    throws JDOMException
  {
    try
    {
      this.contentHandler.endDocument();
      this.locator = null;
      return;
    }
    catch (SAXException localSAXException)
    {
      throw new JDOMException("Exception in endDocument", localSAXException);
    }
  }

  private void endElement(Element paramElement)
    throws JDOMException
  {
    String str1 = paramElement.getNamespaceURI();
    String str2 = paramElement.getName();
    paramElement = paramElement.getQualifiedName();
    try
    {
      this.contentHandler.endElement(str1, str2, paramElement);
      return;
    }
    catch (SAXException paramElement)
    {
    }
    throw new JDOMException("Exception in endElement", paramElement);
  }

  private void endPrefixMapping(NamespaceStack paramNamespaceStack, int paramInt)
    throws JDOMException
  {
    while (paramNamespaceStack.size() > paramInt)
    {
      String str = paramNamespaceStack.pop();
      try
      {
        this.contentHandler.endPrefixMapping(str);
      }
      catch (SAXException paramNamespaceStack)
      {
        throw new JDOMException("Exception in endPrefixMapping", paramNamespaceStack);
      }
    }
  }

  private void entityRef(EntityRef paramEntityRef)
    throws JDOMException
  {
    if (paramEntityRef != null);
    try
    {
      this.contentHandler.skippedEntity(paramEntityRef.getName());
      return;
    }
    catch (SAXException paramEntityRef)
    {
    }
    throw new JDOMException("Exception in entityRef", paramEntityRef);
  }

  private static String getAttributeTypeName(int paramInt)
  {
    int i;
    if (paramInt >= 0)
    {
      i = paramInt;
      if (paramInt < attrTypeToNameMap.length);
    }
    else
    {
      i = 0;
    }
    return attrTypeToNameMap[i];
  }

  private void handleError(JDOMException paramJDOMException)
    throws JDOMException
  {
    if (this.errorHandler != null)
      try
      {
        this.errorHandler.error(new SAXParseException(paramJDOMException.getMessage(), null, paramJDOMException));
        return;
      }
      catch (SAXException paramJDOMException)
      {
        if ((paramJDOMException.getException() instanceof JDOMException))
          throw ((JDOMException)paramJDOMException.getException());
        throw new JDOMException(paramJDOMException.getMessage(), paramJDOMException);
      }
    throw paramJDOMException;
  }

  private void processingInstruction(ProcessingInstruction paramProcessingInstruction)
    throws JDOMException
  {
    String str;
    if (paramProcessingInstruction != null)
    {
      str = paramProcessingInstruction.getTarget();
      paramProcessingInstruction = paramProcessingInstruction.getData();
    }
    try
    {
      this.contentHandler.processingInstruction(str, paramProcessingInstruction);
      return;
    }
    catch (SAXException paramProcessingInstruction)
    {
    }
    throw new JDOMException("Exception in processingInstruction", paramProcessingInstruction);
  }

  private void startDocument()
    throws JDOMException
  {
    try
    {
      this.contentHandler.startDocument();
      return;
    }
    catch (SAXException localSAXException)
    {
      throw new JDOMException("Exception in startDocument", localSAXException);
    }
  }

  private void startElement(Element paramElement, Attributes paramAttributes)
    throws JDOMException
  {
    String str1 = paramElement.getNamespaceURI();
    String str2 = paramElement.getName();
    String str3 = paramElement.getQualifiedName();
    if (paramAttributes != null);
    for (paramAttributes = new AttributesImpl(paramAttributes); ; paramAttributes = new AttributesImpl())
    {
      paramElement = paramElement.getAttributes().iterator();
      while (paramElement.hasNext())
      {
        Attribute localAttribute = (Attribute)paramElement.next();
        paramAttributes.addAttribute(localAttribute.getNamespaceURI(), localAttribute.getName(), localAttribute.getQualifiedName(), getAttributeTypeName(localAttribute.getAttributeType()), localAttribute.getValue());
      }
    }
    try
    {
      this.contentHandler.startElement(str1, str2, str3, paramAttributes);
      return;
    }
    catch (SAXException paramElement)
    {
    }
    throw new JDOMException("Exception in startElement", paramElement);
  }

  private Attributes startPrefixMapping(Element paramElement, NamespaceStack paramNamespaceStack)
    throws JDOMException
  {
    Object localObject2 = null;
    Object localObject3 = paramElement.getNamespace();
    Object localObject1 = localObject2;
    String str1;
    if (localObject3 != Namespace.XML_NAMESPACE)
    {
      str1 = ((Namespace)localObject3).getPrefix();
      String str2 = paramNamespaceStack.getURI(str1);
      localObject1 = localObject2;
      if (!((Namespace)localObject3).getURI().equals(str2))
      {
        paramNamespaceStack.push((Namespace)localObject3);
        localObject1 = addNsAttribute(null, (Namespace)localObject3);
      }
    }
    try
    {
      this.contentHandler.startPrefixMapping(str1, ((Namespace)localObject3).getURI());
      localObject2 = paramElement.getAdditionalNamespaces();
      paramElement = (Element)localObject1;
      if (localObject2 != null)
      {
        localObject2 = ((List)localObject2).iterator();
        while (true)
        {
          paramElement = (Element)localObject1;
          if (((Iterator)localObject2).hasNext())
          {
            paramElement = (Namespace)((Iterator)localObject2).next();
            localObject3 = paramElement.getPrefix();
            str1 = paramNamespaceStack.getURI((String)localObject3);
            if (paramElement.getURI().equals(str1))
              continue;
            paramNamespaceStack.push(paramElement);
            localObject1 = addNsAttribute((AttributesImpl)localObject1, paramElement);
            try
            {
              this.contentHandler.startPrefixMapping((String)localObject3, paramElement.getURI());
            }
            catch (SAXException paramElement)
            {
              throw new JDOMException("Exception in startPrefixMapping", paramElement);
            }
          }
        }
      }
    }
    catch (SAXException paramElement)
    {
      throw new JDOMException("Exception in startPrefixMapping", paramElement);
    }
    return paramElement;
  }

  protected XMLReader createParser()
    throws Exception
  {
    Object localObject1 = null;
    try
    {
      Object localObject2 = Class.forName("javax.xml.parsers.SAXParserFactory");
      Object localObject3 = ((Class)localObject2).getMethod("newInstance", null).invoke(null, null);
      localObject2 = ((Class)localObject2).getMethod("newSAXParser", null).invoke(localObject3, null);
      localObject2 = (XMLReader)localObject2.getClass().getMethod("getXMLReader", null).invoke(localObject2, null);
      localObject1 = localObject2;
      label59: localObject2 = localObject1;
      if (localObject1 == null)
        localObject2 = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
      return localObject2;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      break label59;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      break label59;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      break label59;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      break label59;
    }
  }

  public ContentHandler getContentHandler()
  {
    return this.contentHandler;
  }

  public DTDHandler getDTDHandler()
  {
    return this.dtdHandler;
  }

  public DeclHandler getDeclHandler()
  {
    return this.declHandler;
  }

  public EntityResolver getEntityResolver()
  {
    return this.entityResolver;
  }

  public ErrorHandler getErrorHandler()
  {
    return this.errorHandler;
  }

  public boolean getFeature(String paramString)
    throws SAXNotRecognizedException, SAXNotSupportedException
  {
    if ("http://xml.org/sax/features/namespace-prefixes".equals(paramString))
      return this.declareNamespaces;
    if ("http://xml.org/sax/features/namespaces".equals(paramString))
      return true;
    if ("http://xml.org/sax/features/validation".equals(paramString))
      return this.reportDtdEvents;
    throw new SAXNotRecognizedException(paramString);
  }

  public LexicalHandler getLexicalHandler()
  {
    return this.lexicalHandler;
  }

  public JDOMLocator getLocator()
  {
    if (this.locator != null)
      return new JDOMLocator(this.locator);
    return null;
  }

  public Object getProperty(String paramString)
    throws SAXNotRecognizedException, SAXNotSupportedException
  {
    if (("http://xml.org/sax/properties/lexical-handler".equals(paramString)) || ("http://xml.org/sax/handlers/LexicalHandler".equals(paramString)))
      return getLexicalHandler();
    if (("http://xml.org/sax/properties/declaration-handler".equals(paramString)) || ("http://xml.org/sax/handlers/DeclHandler".equals(paramString)))
      return getDeclHandler();
    throw new SAXNotRecognizedException(paramString);
  }

  public boolean getReportDTDEvents()
  {
    return this.reportDtdEvents;
  }

  public boolean getReportNamespaceDeclarations()
  {
    return this.declareNamespaces;
  }

  public void output(List paramList)
    throws JDOMException
  {
    if ((paramList == null) || (paramList.size() == 0))
      return;
    documentLocator(null);
    startDocument();
    elementContent(paramList, new NamespaceStack());
    endDocument();
  }

  public void output(Document paramDocument)
    throws JDOMException
  {
    if (paramDocument == null)
      return;
    documentLocator(paramDocument);
    startDocument();
    if (this.reportDtdEvents)
      dtdEvents(paramDocument);
    Iterator localIterator = paramDocument.getContent().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      this.locator.setNode(localObject);
      if ((localObject instanceof Element))
        element(paramDocument.getRootElement(), new NamespaceStack());
      else if ((localObject instanceof ProcessingInstruction))
        processingInstruction((ProcessingInstruction)localObject);
      else if ((localObject instanceof Comment))
        comment(((Comment)localObject).getText());
    }
    endDocument();
  }

  public void output(Element paramElement)
    throws JDOMException
  {
    if (paramElement == null)
      return;
    documentLocator(null);
    startDocument();
    elementContent(paramElement, new NamespaceStack());
    endDocument();
  }

  public void outputFragment(List paramList)
    throws JDOMException
  {
    if ((paramList == null) || (paramList.size() == 0))
      return;
    elementContent(paramList, new NamespaceStack());
  }

  public void outputFragment(Content paramContent)
    throws JDOMException
  {
    if (paramContent == null)
      return;
    elementContent(paramContent, new NamespaceStack());
  }

  public void setContentHandler(ContentHandler paramContentHandler)
  {
    this.contentHandler = paramContentHandler;
  }

  public void setDTDHandler(DTDHandler paramDTDHandler)
  {
    this.dtdHandler = paramDTDHandler;
  }

  public void setDeclHandler(DeclHandler paramDeclHandler)
  {
    this.declHandler = paramDeclHandler;
  }

  public void setEntityResolver(EntityResolver paramEntityResolver)
  {
    this.entityResolver = paramEntityResolver;
  }

  public void setErrorHandler(ErrorHandler paramErrorHandler)
  {
    this.errorHandler = paramErrorHandler;
  }

  public void setFeature(String paramString, boolean paramBoolean)
    throws SAXNotRecognizedException, SAXNotSupportedException
  {
    if ("http://xml.org/sax/features/namespace-prefixes".equals(paramString))
      setReportNamespaceDeclarations(paramBoolean);
    do
    {
      return;
      if (!"http://xml.org/sax/features/namespaces".equals(paramString))
        break;
    }
    while (paramBoolean == true);
    throw new SAXNotSupportedException(paramString);
    if ("http://xml.org/sax/features/validation".equals(paramString))
    {
      setReportDTDEvents(paramBoolean);
      return;
    }
    throw new SAXNotRecognizedException(paramString);
  }

  public void setLexicalHandler(LexicalHandler paramLexicalHandler)
  {
    this.lexicalHandler = paramLexicalHandler;
  }

  public void setProperty(String paramString, Object paramObject)
    throws SAXNotRecognizedException, SAXNotSupportedException
  {
    if (("http://xml.org/sax/properties/lexical-handler".equals(paramString)) || ("http://xml.org/sax/handlers/LexicalHandler".equals(paramString)))
    {
      setLexicalHandler((LexicalHandler)paramObject);
      return;
    }
    if (("http://xml.org/sax/properties/declaration-handler".equals(paramString)) || ("http://xml.org/sax/handlers/DeclHandler".equals(paramString)))
    {
      setDeclHandler((DeclHandler)paramObject);
      return;
    }
    throw new SAXNotRecognizedException(paramString);
  }

  public void setReportDTDEvents(boolean paramBoolean)
  {
    this.reportDtdEvents = paramBoolean;
  }

  public void setReportNamespaceDeclarations(boolean paramBoolean)
  {
    this.declareNamespaces = paramBoolean;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.output.SAXOutputter
 * JD-Core Version:    0.6.2
 */