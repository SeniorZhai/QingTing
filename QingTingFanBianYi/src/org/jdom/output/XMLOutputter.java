package org.jdom.output;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Comment;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.EntityRef;
import org.jdom.IllegalDataException;
import org.jdom.Namespace;
import org.jdom.ProcessingInstruction;
import org.jdom.Text;
import org.jdom.Verifier;

public class XMLOutputter
  implements Cloneable
{
  private static final String CVS_ID = "@(#) $RCSfile: XMLOutputter.java,v $ $Revision: 1.117 $ $Date: 2009/07/23 05:54:23 $ $Name: jdom_1_1_1 $";
  protected static final Format preserveFormat = Format.getRawFormat();
  protected Format currentFormat = this.userFormat;
  private boolean escapeOutput = true;
  private Format userFormat = Format.getRawFormat();

  public XMLOutputter()
  {
  }

  public XMLOutputter(Format paramFormat)
  {
    this.userFormat = ((Format)paramFormat.clone());
    this.currentFormat = this.userFormat;
  }

  public XMLOutputter(XMLOutputter paramXMLOutputter)
  {
    this.userFormat = ((Format)paramXMLOutputter.userFormat.clone());
    this.currentFormat = this.userFormat;
  }

  private NamespaceStack createNamespaceStack()
  {
    return new NamespaceStack();
  }

  private boolean endsWithWhite(String paramString)
  {
    return (paramString != null) && (paramString.length() > 0) && (Verifier.isXMLWhitespace(paramString.charAt(paramString.length() - 1)));
  }

  private void indent(Writer paramWriter, int paramInt)
    throws IOException
  {
    if ((this.currentFormat.indent == null) || (this.currentFormat.indent.equals("")));
    while (true)
    {
      return;
      int i = 0;
      while (i < paramInt)
      {
        paramWriter.write(this.currentFormat.indent);
        i += 1;
      }
    }
  }

  private boolean isAllWhitespace(Object paramObject)
  {
    int i;
    if ((paramObject instanceof String))
    {
      paramObject = (String)paramObject;
      i = 0;
    }
    while (true)
    {
      if (i >= paramObject.length())
        break label69;
      if (!Verifier.isXMLWhitespace(paramObject.charAt(i)))
      {
        do
        {
          return false;
          if ((paramObject instanceof Text))
          {
            paramObject = ((Text)paramObject).getText();
            break;
          }
        }
        while (!(paramObject instanceof EntityRef));
        return false;
      }
      i += 1;
    }
    label69: return true;
  }

  private Writer makeWriter(OutputStream paramOutputStream)
    throws UnsupportedEncodingException
  {
    return makeWriter(paramOutputStream, this.userFormat.encoding);
  }

  private static Writer makeWriter(OutputStream paramOutputStream, String paramString)
    throws UnsupportedEncodingException
  {
    String str = paramString;
    if ("UTF-8".equals(paramString))
      str = "UTF8";
    return new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(paramOutputStream), str));
  }

  private void newline(Writer paramWriter)
    throws IOException
  {
    if (this.currentFormat.indent != null)
      paramWriter.write(this.currentFormat.lineSeparator);
  }

  private static int nextNonText(List paramList, int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0)
      i = 0;
    paramInt = paramList.size();
    while (i < paramInt)
    {
      Object localObject = paramList.get(i);
      if ((!(localObject instanceof Text)) && (!(localObject instanceof EntityRef)))
        return i;
      i += 1;
    }
    return paramInt;
  }

  private void printAdditionalNamespaces(Writer paramWriter, Element paramElement, NamespaceStack paramNamespaceStack)
    throws IOException
  {
    paramElement = paramElement.getAdditionalNamespaces();
    if (paramElement != null)
    {
      int i = 0;
      while (i < paramElement.size())
      {
        printNamespace(paramWriter, (Namespace)paramElement.get(i), paramNamespaceStack);
        i += 1;
      }
    }
  }

  private void printContentRange(Writer paramWriter, List paramList, int paramInt1, int paramInt2, int paramInt3, NamespaceStack paramNamespaceStack)
    throws IOException
  {
    int i = paramInt1;
    if (i < paramInt2)
    {
      if (i == paramInt1);
      Object localObject;
      for (int j = 1; ; j = 0)
      {
        localObject = paramList.get(i);
        if ((!(localObject instanceof Text)) && (!(localObject instanceof EntityRef)))
          break label113;
        int m = skipLeadingWhite(paramList, i);
        int k = nextNonText(paramList, m);
        i = k;
        if (m >= k)
          break;
        if (j == 0)
          newline(paramWriter);
        indent(paramWriter, paramInt3);
        printTextRange(paramWriter, paramList, m, k);
        i = k;
        break;
      }
      label113: if (j == 0)
        newline(paramWriter);
      indent(paramWriter, paramInt3);
      if ((localObject instanceof Comment))
        printComment(paramWriter, (Comment)localObject);
      while (true)
      {
        i += 1;
        break;
        if ((localObject instanceof Element))
          printElement(paramWriter, (Element)localObject, paramInt3, paramNamespaceStack);
        else if ((localObject instanceof ProcessingInstruction))
          printProcessingInstruction(paramWriter, (ProcessingInstruction)localObject);
      }
    }
  }

  private void printElementNamespace(Writer paramWriter, Element paramElement, NamespaceStack paramNamespaceStack)
    throws IOException
  {
    paramElement = paramElement.getNamespace();
    if (paramElement == Namespace.XML_NAMESPACE);
    while ((paramElement == Namespace.NO_NAMESPACE) && (paramNamespaceStack.getURI("") == null))
      return;
    printNamespace(paramWriter, paramElement, paramNamespaceStack);
  }

  private void printNamespace(Writer paramWriter, Namespace paramNamespace, NamespaceStack paramNamespaceStack)
    throws IOException
  {
    String str1 = paramNamespace.getPrefix();
    String str2 = paramNamespace.getURI();
    if (str2.equals(paramNamespaceStack.getURI(str1)))
      return;
    paramWriter.write(" xmlns");
    if (!str1.equals(""))
    {
      paramWriter.write(":");
      paramWriter.write(str1);
    }
    paramWriter.write("=\"");
    paramWriter.write(escapeAttributeEntities(str2));
    paramWriter.write("\"");
    paramNamespaceStack.push(paramNamespace);
  }

  private void printQualifiedName(Writer paramWriter, Attribute paramAttribute)
    throws IOException
  {
    String str = paramAttribute.getNamespace().getPrefix();
    if ((str != null) && (!str.equals("")))
    {
      paramWriter.write(str);
      paramWriter.write(58);
      paramWriter.write(paramAttribute.getName());
      return;
    }
    paramWriter.write(paramAttribute.getName());
  }

  private void printQualifiedName(Writer paramWriter, Element paramElement)
    throws IOException
  {
    if (paramElement.getNamespace().getPrefix().length() == 0)
    {
      paramWriter.write(paramElement.getName());
      return;
    }
    paramWriter.write(paramElement.getNamespace().getPrefix());
    paramWriter.write(58);
    paramWriter.write(paramElement.getName());
  }

  private void printString(Writer paramWriter, String paramString)
    throws IOException
  {
    String str;
    if (this.currentFormat.mode == Format.TextMode.NORMALIZE)
      str = Text.normalizeString(paramString);
    while (true)
    {
      paramWriter.write(escapeElementEntities(str));
      return;
      str = paramString;
      if (this.currentFormat.mode == Format.TextMode.TRIM)
        str = paramString.trim();
    }
  }

  private void printTextRange(Writer paramWriter, List paramList, int paramInt1, int paramInt2)
    throws IOException
  {
    Object localObject1 = null;
    paramInt1 = skipLeadingWhite(paramList, paramInt1);
    if (paramInt1 < paramList.size())
    {
      paramInt2 = skipTrailingWhite(paramList, paramInt2);
      if (paramInt1 < paramInt2)
      {
        Object localObject3 = paramList.get(paramInt1);
        if ((localObject3 instanceof Text));
        Object localObject2;
        for (String str = ((Text)localObject3).getText(); ; str = "&" + ((EntityRef)localObject3).getValue() + ";")
        {
          localObject2 = localObject1;
          if (str != null)
          {
            if (!"".equals(str))
              break label153;
            localObject2 = localObject1;
          }
          paramInt1 += 1;
          localObject1 = localObject2;
          break;
          if (!(localObject3 instanceof EntityRef))
            break label142;
        }
        label142: throw new IllegalStateException("Should see only CDATA, Text, or EntityRef");
        label153: if ((localObject1 != null) && ((this.currentFormat.mode == Format.TextMode.NORMALIZE) || (this.currentFormat.mode == Format.TextMode.TRIM)) && ((endsWithWhite(localObject1)) || (startsWithWhite(str))))
          paramWriter.write(" ");
        if ((localObject3 instanceof CDATA))
          printCDATA(paramWriter, (CDATA)localObject3);
        while (true)
        {
          localObject2 = str;
          break;
          if ((localObject3 instanceof EntityRef))
            printEntityRef(paramWriter, (EntityRef)localObject3);
          else
            printString(paramWriter, str);
        }
      }
    }
  }

  private int skipLeadingWhite(List paramList, int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0)
      i = 0;
    paramInt = i;
    int k = paramList.size();
    i = paramInt;
    int j;
    if (this.currentFormat.mode != Format.TextMode.TRIM_FULL_WHITE)
    {
      i = paramInt;
      if (this.currentFormat.mode != Format.TextMode.NORMALIZE)
      {
        j = paramInt;
        if (this.currentFormat.mode != Format.TextMode.TRIM)
          break label98;
        i = paramInt;
      }
    }
    while (true)
    {
      j = i;
      if (i >= k)
        break;
      if (!isAllWhitespace(paramList.get(i)))
        return i;
      i += 1;
    }
    label98: return j;
  }

  private int skipTrailingWhite(List paramList, int paramInt)
  {
    int j = paramList.size();
    int i = paramInt;
    if (paramInt > j)
      i = j;
    paramInt = i;
    if (this.currentFormat.mode != Format.TextMode.TRIM_FULL_WHITE)
    {
      paramInt = i;
      if (this.currentFormat.mode != Format.TextMode.NORMALIZE)
      {
        j = i;
        if (this.currentFormat.mode != Format.TextMode.TRIM)
          break label93;
        paramInt = i;
      }
    }
    while (true)
    {
      j = paramInt;
      if (paramInt >= 0)
      {
        if (!isAllWhitespace(paramList.get(paramInt - 1)))
          j = paramInt;
      }
      else
        label93: return j;
      paramInt -= 1;
    }
  }

  private boolean startsWithWhite(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString != null)
    {
      bool1 = bool2;
      if (paramString.length() > 0)
      {
        bool1 = bool2;
        if (Verifier.isXMLWhitespace(paramString.charAt(0)))
          bool1 = true;
      }
    }
    return bool1;
  }

  public Object clone()
  {
    try
    {
      Object localObject = super.clone();
      return localObject;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException(localCloneNotSupportedException.toString());
    }
  }

  public String escapeAttributeEntities(String paramString)
  {
    EscapeStrategy localEscapeStrategy = this.currentFormat.escapeStrategy;
    StringBuffer localStringBuffer = null;
    int i = 0;
    if (i < paramString.length())
    {
      int m = paramString.charAt(i);
      int j;
      int k;
      char c;
      String str;
      switch (m)
      {
      default:
        if (localEscapeStrategy.shouldEscape((char)m))
        {
          j = m;
          k = i;
          if (Verifier.isHighSurrogate((char)m))
          {
            k = i + 1;
            if (k >= paramString.length())
              break label383;
            c = paramString.charAt(k);
            if (!Verifier.isLowSurrogate(c))
              throw new IllegalDataException("Could not decode surrogate pair 0x" + Integer.toHexString(m) + " / 0x" + Integer.toHexString(c));
          }
        }
        break;
      case 60:
        str = "&lt;";
        j = i;
        label212: if (localStringBuffer == null)
          if (str != null)
          {
            localStringBuffer = new StringBuffer(paramString.length() + 20);
            localStringBuffer.append(paramString.substring(0, i));
            localStringBuffer.append(str);
          }
        break;
      case 62:
      case 34:
      case 38:
      case 13:
      case 9:
      case 10:
      }
      while (true)
      {
        i = j + 1;
        break;
        str = "&gt;";
        j = i;
        break label212;
        str = "&quot;";
        j = i;
        break label212;
        str = "&amp;";
        j = i;
        break label212;
        str = "&#xD;";
        j = i;
        break label212;
        str = "&#x9;";
        j = i;
        break label212;
        str = "&#xA;";
        j = i;
        break label212;
        j = Verifier.decodeSurrogatePair((char)m, c);
        str = "&#x" + Integer.toHexString(j) + ";";
        m = j;
        j = k;
        break label212;
        label383: throw new IllegalDataException("Surrogate pair 0x" + Integer.toHexString(m) + " truncated");
        str = null;
        j = i;
        break label212;
        if (str == null)
          localStringBuffer.append((char)m);
        else
          localStringBuffer.append(str);
      }
    }
    if (localStringBuffer == null)
      return paramString;
    return localStringBuffer.toString();
  }

  public String escapeElementEntities(String paramString)
  {
    if (!this.escapeOutput);
    StringBuffer localStringBuffer;
    label204: label357: 
    do
    {
      return paramString;
      EscapeStrategy localEscapeStrategy = this.currentFormat.escapeStrategy;
      localStringBuffer = null;
      int i = 0;
      if (i < paramString.length())
      {
        int m = paramString.charAt(i);
        int j;
        int k;
        char c;
        String str;
        switch (m)
        {
        default:
          if (localEscapeStrategy.shouldEscape((char)m))
          {
            j = m;
            k = i;
            if (Verifier.isHighSurrogate((char)m))
            {
              k = i + 1;
              if (k >= paramString.length())
                break label357;
              c = paramString.charAt(k);
              if (!Verifier.isLowSurrogate(c))
                throw new IllegalDataException("Could not decode surrogate pair 0x" + Integer.toHexString(m) + " / 0x" + Integer.toHexString(c));
            }
          }
          break;
        case 60:
          str = "&lt;";
          j = i;
          if (localStringBuffer == null)
            if (str != null)
            {
              localStringBuffer = new StringBuffer(paramString.length() + 20);
              localStringBuffer.append(paramString.substring(0, i));
              localStringBuffer.append(str);
            }
          break;
        case 62:
        case 38:
        case 13:
        case 10:
        }
        while (true)
        {
          i = j + 1;
          break;
          str = "&gt;";
          j = i;
          break label204;
          str = "&amp;";
          j = i;
          break label204;
          str = "&#xD;";
          j = i;
          break label204;
          str = this.currentFormat.lineSeparator;
          j = i;
          break label204;
          j = Verifier.decodeSurrogatePair((char)m, c);
          str = "&#x" + Integer.toHexString(j) + ";";
          m = j;
          j = k;
          break label204;
          throw new IllegalDataException("Surrogate pair 0x" + Integer.toHexString(m) + " truncated");
          str = null;
          j = i;
          break label204;
          if (str == null)
            localStringBuffer.append((char)m);
          else
            localStringBuffer.append(str);
        }
      }
    }
    while (localStringBuffer == null);
    return localStringBuffer.toString();
  }

  public Format getFormat()
  {
    return (Format)this.userFormat.clone();
  }

  public void output(List paramList, OutputStream paramOutputStream)
    throws IOException
  {
    output(paramList, makeWriter(paramOutputStream));
  }

  public void output(List paramList, Writer paramWriter)
    throws IOException
  {
    printContentRange(paramWriter, paramList, 0, paramList.size(), 0, createNamespaceStack());
    paramWriter.flush();
  }

  public void output(CDATA paramCDATA, OutputStream paramOutputStream)
    throws IOException
  {
    output(paramCDATA, makeWriter(paramOutputStream));
  }

  public void output(CDATA paramCDATA, Writer paramWriter)
    throws IOException
  {
    printCDATA(paramWriter, paramCDATA);
    paramWriter.flush();
  }

  public void output(Comment paramComment, OutputStream paramOutputStream)
    throws IOException
  {
    output(paramComment, makeWriter(paramOutputStream));
  }

  public void output(Comment paramComment, Writer paramWriter)
    throws IOException
  {
    printComment(paramWriter, paramComment);
    paramWriter.flush();
  }

  public void output(DocType paramDocType, OutputStream paramOutputStream)
    throws IOException
  {
    output(paramDocType, makeWriter(paramOutputStream));
  }

  public void output(DocType paramDocType, Writer paramWriter)
    throws IOException
  {
    printDocType(paramWriter, paramDocType);
    paramWriter.flush();
  }

  public void output(Document paramDocument, OutputStream paramOutputStream)
    throws IOException
  {
    output(paramDocument, makeWriter(paramOutputStream));
  }

  public void output(Document paramDocument, Writer paramWriter)
    throws IOException
  {
    printDeclaration(paramWriter, paramDocument, this.userFormat.encoding);
    List localList = paramDocument.getContent();
    int j = localList.size();
    int i = 0;
    if (i < j)
    {
      Object localObject = localList.get(i);
      if ((localObject instanceof Element))
        printElement(paramWriter, paramDocument.getRootElement(), 0, createNamespaceStack());
      while (true)
      {
        newline(paramWriter);
        indent(paramWriter, 0);
        i += 1;
        break;
        if ((localObject instanceof Comment))
        {
          printComment(paramWriter, (Comment)localObject);
        }
        else if ((localObject instanceof ProcessingInstruction))
        {
          printProcessingInstruction(paramWriter, (ProcessingInstruction)localObject);
        }
        else if ((localObject instanceof DocType))
        {
          printDocType(paramWriter, paramDocument.getDocType());
          paramWriter.write(this.currentFormat.lineSeparator);
        }
      }
    }
    paramWriter.write(this.currentFormat.lineSeparator);
    paramWriter.flush();
  }

  public void output(Element paramElement, OutputStream paramOutputStream)
    throws IOException
  {
    output(paramElement, makeWriter(paramOutputStream));
  }

  public void output(Element paramElement, Writer paramWriter)
    throws IOException
  {
    printElement(paramWriter, paramElement, 0, createNamespaceStack());
    paramWriter.flush();
  }

  public void output(EntityRef paramEntityRef, OutputStream paramOutputStream)
    throws IOException
  {
    output(paramEntityRef, makeWriter(paramOutputStream));
  }

  public void output(EntityRef paramEntityRef, Writer paramWriter)
    throws IOException
  {
    printEntityRef(paramWriter, paramEntityRef);
    paramWriter.flush();
  }

  public void output(ProcessingInstruction paramProcessingInstruction, OutputStream paramOutputStream)
    throws IOException
  {
    output(paramProcessingInstruction, makeWriter(paramOutputStream));
  }

  public void output(ProcessingInstruction paramProcessingInstruction, Writer paramWriter)
    throws IOException
  {
    boolean bool = this.currentFormat.ignoreTrAXEscapingPIs;
    this.currentFormat.setIgnoreTrAXEscapingPIs(true);
    printProcessingInstruction(paramWriter, paramProcessingInstruction);
    this.currentFormat.setIgnoreTrAXEscapingPIs(bool);
    paramWriter.flush();
  }

  public void output(Text paramText, OutputStream paramOutputStream)
    throws IOException
  {
    output(paramText, makeWriter(paramOutputStream));
  }

  public void output(Text paramText, Writer paramWriter)
    throws IOException
  {
    printText(paramWriter, paramText);
    paramWriter.flush();
  }

  public void outputElementContent(Element paramElement, OutputStream paramOutputStream)
    throws IOException
  {
    outputElementContent(paramElement, makeWriter(paramOutputStream));
  }

  public void outputElementContent(Element paramElement, Writer paramWriter)
    throws IOException
  {
    paramElement = paramElement.getContent();
    printContentRange(paramWriter, paramElement, 0, paramElement.size(), 0, createNamespaceStack());
    paramWriter.flush();
  }

  public String outputString(List paramList)
  {
    StringWriter localStringWriter = new StringWriter();
    try
    {
      output(paramList, localStringWriter);
      label14: return localStringWriter.toString();
    }
    catch (IOException paramList)
    {
      break label14;
    }
  }

  public String outputString(CDATA paramCDATA)
  {
    StringWriter localStringWriter = new StringWriter();
    try
    {
      output(paramCDATA, localStringWriter);
      label14: return localStringWriter.toString();
    }
    catch (IOException paramCDATA)
    {
      break label14;
    }
  }

  public String outputString(Comment paramComment)
  {
    StringWriter localStringWriter = new StringWriter();
    try
    {
      output(paramComment, localStringWriter);
      label14: return localStringWriter.toString();
    }
    catch (IOException paramComment)
    {
      break label14;
    }
  }

  public String outputString(DocType paramDocType)
  {
    StringWriter localStringWriter = new StringWriter();
    try
    {
      output(paramDocType, localStringWriter);
      label14: return localStringWriter.toString();
    }
    catch (IOException paramDocType)
    {
      break label14;
    }
  }

  public String outputString(Document paramDocument)
  {
    StringWriter localStringWriter = new StringWriter();
    try
    {
      output(paramDocument, localStringWriter);
      label14: return localStringWriter.toString();
    }
    catch (IOException paramDocument)
    {
      break label14;
    }
  }

  public String outputString(Element paramElement)
  {
    StringWriter localStringWriter = new StringWriter();
    try
    {
      output(paramElement, localStringWriter);
      label14: return localStringWriter.toString();
    }
    catch (IOException paramElement)
    {
      break label14;
    }
  }

  public String outputString(EntityRef paramEntityRef)
  {
    StringWriter localStringWriter = new StringWriter();
    try
    {
      output(paramEntityRef, localStringWriter);
      label14: return localStringWriter.toString();
    }
    catch (IOException paramEntityRef)
    {
      break label14;
    }
  }

  public String outputString(ProcessingInstruction paramProcessingInstruction)
  {
    StringWriter localStringWriter = new StringWriter();
    try
    {
      output(paramProcessingInstruction, localStringWriter);
      label14: return localStringWriter.toString();
    }
    catch (IOException paramProcessingInstruction)
    {
      break label14;
    }
  }

  public String outputString(Text paramText)
  {
    StringWriter localStringWriter = new StringWriter();
    try
    {
      output(paramText, localStringWriter);
      label14: return localStringWriter.toString();
    }
    catch (IOException paramText)
    {
      break label14;
    }
  }

  protected void printAttributes(Writer paramWriter, List paramList, Element paramElement, NamespaceStack paramNamespaceStack)
    throws IOException
  {
    int i = 0;
    while (i < paramList.size())
    {
      paramElement = (Attribute)paramList.get(i);
      Namespace localNamespace = paramElement.getNamespace();
      if ((localNamespace != Namespace.NO_NAMESPACE) && (localNamespace != Namespace.XML_NAMESPACE))
        printNamespace(paramWriter, localNamespace, paramNamespaceStack);
      paramWriter.write(" ");
      printQualifiedName(paramWriter, paramElement);
      paramWriter.write("=");
      paramWriter.write("\"");
      paramWriter.write(escapeAttributeEntities(paramElement.getValue()));
      paramWriter.write("\"");
      i += 1;
    }
  }

  protected void printCDATA(Writer paramWriter, CDATA paramCDATA)
    throws IOException
  {
    if (this.currentFormat.mode == Format.TextMode.NORMALIZE)
      paramCDATA = paramCDATA.getTextNormalize();
    while (true)
    {
      paramWriter.write("<![CDATA[");
      paramWriter.write(paramCDATA);
      paramWriter.write("]]>");
      return;
      if (this.currentFormat.mode == Format.TextMode.TRIM)
        paramCDATA = paramCDATA.getText().trim();
      else
        paramCDATA = paramCDATA.getText();
    }
  }

  protected void printComment(Writer paramWriter, Comment paramComment)
    throws IOException
  {
    paramWriter.write("<!--");
    paramWriter.write(paramComment.getText());
    paramWriter.write("-->");
  }

  protected void printDeclaration(Writer paramWriter, Document paramDocument, String paramString)
    throws IOException
  {
    if (!this.userFormat.omitDeclaration)
    {
      paramWriter.write("<?xml version=\"1.0\"");
      if (!this.userFormat.omitEncoding)
        paramWriter.write(" encoding=\"" + paramString + "\"");
      paramWriter.write("?>");
      paramWriter.write(this.currentFormat.lineSeparator);
    }
  }

  protected void printDocType(Writer paramWriter, DocType paramDocType)
    throws IOException
  {
    String str1 = paramDocType.getPublicID();
    String str2 = paramDocType.getSystemID();
    String str3 = paramDocType.getInternalSubset();
    int i = 0;
    paramWriter.write("<!DOCTYPE ");
    paramWriter.write(paramDocType.getElementName());
    if (str1 != null)
    {
      paramWriter.write(" PUBLIC \"");
      paramWriter.write(str1);
      paramWriter.write("\"");
      i = 1;
    }
    if (str2 != null)
    {
      if (i == 0)
        paramWriter.write(" SYSTEM");
      paramWriter.write(" \"");
      paramWriter.write(str2);
      paramWriter.write("\"");
    }
    if ((str3 != null) && (!str3.equals("")))
    {
      paramWriter.write(" [");
      paramWriter.write(this.currentFormat.lineSeparator);
      paramWriter.write(paramDocType.getInternalSubset());
      paramWriter.write("]");
    }
    paramWriter.write(">");
  }

  protected void printElement(Writer paramWriter, Element paramElement, int paramInt, NamespaceStack paramNamespaceStack)
    throws IOException
  {
    List localList1 = paramElement.getAttributes();
    List localList2 = paramElement.getContent();
    String str = null;
    if (localList1 != null)
      str = paramElement.getAttributeValue("space", Namespace.XML_NAMESPACE);
    Format localFormat = this.currentFormat;
    int i;
    int j;
    int k;
    if ("default".equals(str))
    {
      this.currentFormat = this.userFormat;
      paramWriter.write("<");
      printQualifiedName(paramWriter, paramElement);
      i = paramNamespaceStack.size();
      printElementNamespace(paramWriter, paramElement, paramNamespaceStack);
      printAdditionalNamespaces(paramWriter, paramElement, paramNamespaceStack);
      if (localList1 != null)
        printAttributes(paramWriter, localList1, paramElement, paramNamespaceStack);
      j = skipLeadingWhite(localList2, 0);
      k = localList2.size();
      if (j < k)
        break label213;
      if (!this.currentFormat.expandEmptyElements)
        break label203;
      paramWriter.write("></");
      printQualifiedName(paramWriter, paramElement);
      paramWriter.write(">");
    }
    while (true)
    {
      if (paramNamespaceStack.size() <= i)
        break label301;
      paramNamespaceStack.pop();
      continue;
      if (!"preserve".equals(str))
        break;
      this.currentFormat = preserveFormat;
      break;
      label203: paramWriter.write(" />");
    }
    label213: paramWriter.write(">");
    if (nextNonText(localList2, j) < k)
    {
      newline(paramWriter);
      printContentRange(paramWriter, localList2, j, k, paramInt + 1, paramNamespaceStack);
      newline(paramWriter);
      indent(paramWriter, paramInt);
    }
    while (true)
    {
      paramWriter.write("</");
      printQualifiedName(paramWriter, paramElement);
      paramWriter.write(">");
      break;
      printTextRange(paramWriter, localList2, j, k);
    }
    label301: this.currentFormat = localFormat;
  }

  protected void printEntityRef(Writer paramWriter, EntityRef paramEntityRef)
    throws IOException
  {
    paramWriter.write("&");
    paramWriter.write(paramEntityRef.getName());
    paramWriter.write(";");
  }

  protected void printProcessingInstruction(Writer paramWriter, ProcessingInstruction paramProcessingInstruction)
    throws IOException
  {
    String str = paramProcessingInstruction.getTarget();
    int j = 0;
    int i = j;
    if (!this.currentFormat.ignoreTrAXEscapingPIs)
    {
      if (!str.equals("javax.xml.transform.disable-output-escaping"))
        break label91;
      this.escapeOutput = false;
      i = 1;
    }
    while (true)
    {
      if (i == 0)
      {
        paramProcessingInstruction = paramProcessingInstruction.getData();
        if ("".equals(paramProcessingInstruction))
          break;
        paramWriter.write("<?");
        paramWriter.write(str);
        paramWriter.write(" ");
        paramWriter.write(paramProcessingInstruction);
        paramWriter.write("?>");
      }
      return;
      label91: i = j;
      if (str.equals("javax.xml.transform.enable-output-escaping"))
      {
        this.escapeOutput = true;
        i = 1;
      }
    }
    paramWriter.write("<?");
    paramWriter.write(str);
    paramWriter.write("?>");
  }

  protected void printText(Writer paramWriter, Text paramText)
    throws IOException
  {
    if (this.currentFormat.mode == Format.TextMode.NORMALIZE)
      paramText = paramText.getTextNormalize();
    while (true)
    {
      paramWriter.write(escapeElementEntities(paramText));
      return;
      if (this.currentFormat.mode == Format.TextMode.TRIM)
        paramText = paramText.getText().trim();
      else
        paramText = paramText.getText();
    }
  }

  public void setFormat(Format paramFormat)
  {
    this.userFormat = ((Format)paramFormat.clone());
    this.currentFormat = this.userFormat;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i < this.userFormat.lineSeparator.length())
    {
      int j = this.userFormat.lineSeparator.charAt(i);
      switch (j)
      {
      case 11:
      case 12:
      default:
        localStringBuffer.append("[" + j + "]");
      case 13:
      case 10:
      case 9:
      }
      while (true)
      {
        i += 1;
        break;
        localStringBuffer.append("\\r");
        continue;
        localStringBuffer.append("\\n");
        continue;
        localStringBuffer.append("\\t");
      }
    }
    return "XMLOutputter[omitDeclaration = " + this.userFormat.omitDeclaration + ", " + "encoding = " + this.userFormat.encoding + ", " + "omitEncoding = " + this.userFormat.omitEncoding + ", " + "indent = '" + this.userFormat.indent + "'" + ", " + "expandEmptyElements = " + this.userFormat.expandEmptyElements + ", " + "lineSeparator = '" + localStringBuffer.toString() + "', " + "textMode = " + this.userFormat.mode + "]";
  }

  protected class NamespaceStack extends NamespaceStack
  {
    protected NamespaceStack()
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.output.XMLOutputter
 * JD-Core Version:    0.6.2
 */