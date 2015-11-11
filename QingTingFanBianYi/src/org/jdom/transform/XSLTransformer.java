package org.jdom.transform;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import org.jdom.Document;
import org.jdom.JDOMFactory;
import org.xml.sax.EntityResolver;

public class XSLTransformer
{
  private static final String CVS_ID = "@(#) $RCSfile: XSLTransformer.java,v $ $Revision: 1.5 $ $Date: 2007/11/14 04:36:54 $ $Name: jdom_1_1_1 $";
  private JDOMFactory factory = null;
  private Templates templates;

  public XSLTransformer(File paramFile)
    throws XSLTransformException
  {
    this(new StreamSource(paramFile));
  }

  public XSLTransformer(InputStream paramInputStream)
    throws XSLTransformException
  {
    this(new StreamSource(paramInputStream));
  }

  public XSLTransformer(Reader paramReader)
    throws XSLTransformException
  {
    this(new StreamSource(paramReader));
  }

  public XSLTransformer(String paramString)
    throws XSLTransformException
  {
    this(new StreamSource(paramString));
  }

  private XSLTransformer(Source paramSource)
    throws XSLTransformException
  {
    try
    {
      this.templates = TransformerFactory.newInstance().newTemplates(paramSource);
      return;
    }
    catch (TransformerException paramSource)
    {
    }
    throw new XSLTransformException("Could not construct XSLTransformer", paramSource);
  }

  public XSLTransformer(Document paramDocument)
    throws XSLTransformException
  {
    this(new JDOMSource(paramDocument));
  }

  public JDOMFactory getFactory()
  {
    return this.factory;
  }

  public void setFactory(JDOMFactory paramJDOMFactory)
  {
    this.factory = paramJDOMFactory;
  }

  public List transform(List paramList)
    throws XSLTransformException
  {
    paramList = new JDOMSource(paramList);
    JDOMResult localJDOMResult = new JDOMResult();
    localJDOMResult.setFactory(this.factory);
    try
    {
      this.templates.newTransformer().transform(paramList, localJDOMResult);
      paramList = localJDOMResult.getResult();
      return paramList;
    }
    catch (TransformerException paramList)
    {
    }
    throw new XSLTransformException("Could not perform transformation", paramList);
  }

  public Document transform(Document paramDocument)
    throws XSLTransformException
  {
    return transform(paramDocument, null);
  }

  public Document transform(Document paramDocument, EntityResolver paramEntityResolver)
    throws XSLTransformException
  {
    paramDocument = new JDOMSource(paramDocument, paramEntityResolver);
    paramEntityResolver = new JDOMResult();
    paramEntityResolver.setFactory(this.factory);
    try
    {
      this.templates.newTransformer().transform(paramDocument, paramEntityResolver);
      paramDocument = paramEntityResolver.getDocument();
      return paramDocument;
    }
    catch (TransformerException paramDocument)
    {
    }
    throw new XSLTransformException("Could not perform transformation", paramDocument);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.transform.XSLTransformer
 * JD-Core Version:    0.6.2
 */