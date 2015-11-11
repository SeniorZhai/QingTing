package org.jdom.adapters;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.jdom.DocType;
import org.jdom.JDOMException;
import org.w3c.dom.Document;

public abstract interface DOMAdapter
{
  public abstract Document createDocument()
    throws JDOMException;

  public abstract Document createDocument(DocType paramDocType)
    throws JDOMException;

  public abstract Document getDocument(File paramFile, boolean paramBoolean)
    throws IOException, JDOMException;

  public abstract Document getDocument(InputStream paramInputStream, boolean paramBoolean)
    throws IOException, JDOMException;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.adapters.DOMAdapter
 * JD-Core Version:    0.6.2
 */