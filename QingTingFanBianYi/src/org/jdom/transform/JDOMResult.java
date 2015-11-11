package org.jdom.transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.transform.sax.SAXResult;
import org.jdom.DefaultJDOMFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMFactory;
import org.jdom.input.SAXHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.XMLFilterImpl;

public class JDOMResult extends SAXResult
{
  private static final String CVS_ID = "@(#) $RCSfile: JDOMResult.java,v $ $Revision: 1.24 $ $Date: 2007/11/10 05:29:02 $ $Name: jdom_1_1_1 $";
  public static final String JDOM_FEATURE = "http://org.jdom.transform.JDOMResult/feature";
  private JDOMFactory factory = null;
  private boolean queried = false;
  private Object result = null;

  public JDOMResult()
  {
    DocumentBuilder localDocumentBuilder = new DocumentBuilder();
    super.setHandler(localDocumentBuilder);
    super.setLexicalHandler(localDocumentBuilder);
  }

  private void retrieveResult()
  {
    if (this.result == null)
      setResult(((DocumentBuilder)getHandler()).getResult());
  }

  public Document getDocument()
  {
    Object localObject3 = null;
    Object localObject2 = null;
    retrieveResult();
    Object localObject1;
    if ((this.result instanceof Document))
      localObject1 = (Document)this.result;
    while (true)
    {
      this.queried = true;
      return localObject1;
      localObject1 = localObject2;
      if ((this.result instanceof List))
      {
        localObject1 = localObject2;
        if (!this.queried)
        {
          localObject1 = localObject3;
          try
          {
            JDOMFactory localJDOMFactory = getFactory();
            localObject2 = localJDOMFactory;
            if (localJDOMFactory == null)
            {
              localObject1 = localObject3;
              localObject2 = new DefaultJDOMFactory();
            }
            localObject1 = localObject3;
            localObject2 = ((JDOMFactory)localObject2).document(null);
            localObject1 = localObject2;
            ((Document)localObject2).setContent((List)this.result);
            localObject1 = localObject2;
            this.result = localObject2;
            localObject1 = localObject2;
          }
          catch (RuntimeException localRuntimeException)
          {
          }
        }
      }
    }
  }

  public JDOMFactory getFactory()
  {
    return this.factory;
  }

  public List getResult()
  {
    List localList = Collections.EMPTY_LIST;
    retrieveResult();
    Object localObject;
    if ((this.result instanceof List))
      localObject = (List)this.result;
    while (true)
    {
      this.queried = true;
      return localObject;
      localObject = localList;
      if ((this.result instanceof Document))
      {
        localObject = localList;
        if (!this.queried)
        {
          localList = ((Document)this.result).getContent();
          localObject = new ArrayList(localList.size());
          while (localList.size() != 0)
            ((List)localObject).add(localList.remove(0));
          this.result = localObject;
        }
      }
    }
  }

  public void setDocument(Document paramDocument)
  {
    this.result = paramDocument;
    this.queried = false;
  }

  public void setFactory(JDOMFactory paramJDOMFactory)
  {
    this.factory = paramJDOMFactory;
  }

  public void setHandler(ContentHandler paramContentHandler)
  {
  }

  public void setLexicalHandler(LexicalHandler paramLexicalHandler)
  {
  }

  public void setResult(List paramList)
  {
    this.result = paramList;
    this.queried = false;
  }

  private class DocumentBuilder extends XMLFilterImpl
    implements LexicalHandler
  {
    private JDOMResult.FragmentHandler saxHandler = null;
    private boolean startDocumentReceived = false;

    public DocumentBuilder()
    {
    }

    private void ensureInitialization()
      throws SAXException
    {
      if (!this.startDocumentReceived)
        startDocument();
    }

    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      ensureInitialization();
      super.characters(paramArrayOfChar, paramInt1, paramInt2);
    }

    public void comment(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      ensureInitialization();
      this.saxHandler.comment(paramArrayOfChar, paramInt1, paramInt2);
    }

    public void endCDATA()
      throws SAXException
    {
      this.saxHandler.endCDATA();
    }

    public void endDTD()
      throws SAXException
    {
      this.saxHandler.endDTD();
    }

    public void endEntity(String paramString)
      throws SAXException
    {
      this.saxHandler.endEntity(paramString);
    }

    public List getResult()
    {
      List localList = null;
      if (this.saxHandler != null)
      {
        localList = this.saxHandler.getResult();
        this.saxHandler = null;
        this.startDocumentReceived = false;
      }
      return localList;
    }

    public void ignorableWhitespace(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      ensureInitialization();
      super.ignorableWhitespace(paramArrayOfChar, paramInt1, paramInt2);
    }

    public void processingInstruction(String paramString1, String paramString2)
      throws SAXException
    {
      ensureInitialization();
      super.processingInstruction(paramString1, paramString2);
    }

    public void skippedEntity(String paramString)
      throws SAXException
    {
      ensureInitialization();
      super.skippedEntity(paramString);
    }

    public void startCDATA()
      throws SAXException
    {
      ensureInitialization();
      this.saxHandler.startCDATA();
    }

    public void startDTD(String paramString1, String paramString2, String paramString3)
      throws SAXException
    {
      ensureInitialization();
      this.saxHandler.startDTD(paramString1, paramString2, paramString3);
    }

    public void startDocument()
      throws SAXException
    {
      this.startDocumentReceived = true;
      JDOMResult.this.setResult(null);
      this.saxHandler = new JDOMResult.FragmentHandler(JDOMResult.this.getFactory());
      super.setContentHandler(this.saxHandler);
      super.startDocument();
    }

    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
      throws SAXException
    {
      ensureInitialization();
      super.startElement(paramString1, paramString2, paramString3, paramAttributes);
    }

    public void startEntity(String paramString)
      throws SAXException
    {
      ensureInitialization();
      this.saxHandler.startEntity(paramString);
    }

    public void startPrefixMapping(String paramString1, String paramString2)
      throws SAXException
    {
      ensureInitialization();
      super.startPrefixMapping(paramString1, paramString2);
    }
  }

  private static class FragmentHandler extends SAXHandler
  {
    private Element dummyRoot = new Element("root", null, null);

    public FragmentHandler(JDOMFactory paramJDOMFactory)
    {
      super();
      pushElement(this.dummyRoot);
    }

    private List getDetachedContent(Element paramElement)
    {
      paramElement = paramElement.getContent();
      ArrayList localArrayList = new ArrayList(paramElement.size());
      while (paramElement.size() != 0)
        localArrayList.add(paramElement.remove(0));
      return localArrayList;
    }

    public List getResult()
    {
      try
      {
        flushCharacters();
        label4: return getDetachedContent(this.dummyRoot);
      }
      catch (SAXException localSAXException)
      {
        break label4;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.transform.JDOMResult
 * JD-Core Version:    0.6.2
 */