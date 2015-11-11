package org.jdom.input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.jdom.DefaultJDOMFactory;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.JDOMFactory;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

public class SAXBuilder
{
  private static final String CVS_ID = "@(#) $RCSfile: SAXBuilder.java,v $ $Revision: 1.93 $ $Date: 2009/07/23 06:26:26 $ $Name: jdom_1_1_1 $";
  private static final String DEFAULT_SAX_DRIVER = "org.apache.xerces.parsers.SAXParser";
  static Class class$java$util$Map;
  private boolean expand = true;
  private JDOMFactory factory = new DefaultJDOMFactory();
  private boolean fastReconfigure = false;
  private HashMap features = new HashMap(5);
  private boolean ignoringBoundaryWhite = false;
  private boolean ignoringWhite = false;
  private HashMap properties = new HashMap(5);
  private boolean reuseParser = true;
  private DTDHandler saxDTDHandler = null;
  private String saxDriverClass;
  private EntityResolver saxEntityResolver = null;
  private ErrorHandler saxErrorHandler = null;
  private XMLReader saxParser = null;
  private XMLFilter saxXMLFilter = null;
  private boolean skipNextEntityExpandConfig = false;
  private boolean skipNextLexicalReportingConfig = false;
  private boolean validate;

  public SAXBuilder()
  {
    this(false);
  }

  public SAXBuilder(String paramString)
  {
    this(paramString, false);
  }

  public SAXBuilder(String paramString, boolean paramBoolean)
  {
    this.saxDriverClass = paramString;
    this.validate = paramBoolean;
  }

  public SAXBuilder(boolean paramBoolean)
  {
    this.validate = paramBoolean;
  }

  static Class class$(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
    }
    throw new NoClassDefFoundError(paramString.getMessage());
  }

  private static URL fileToURL(File paramFile)
    throws MalformedURLException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    String str2 = paramFile.getAbsolutePath();
    String str1 = str2;
    if (File.separatorChar != '/')
      str1 = str2.replace(File.separatorChar, '/');
    if (!str1.startsWith("/"))
      localStringBuffer.append('/');
    int j = str1.length();
    int i = 0;
    if (i < j)
    {
      char c = str1.charAt(i);
      if (c == ' ')
        localStringBuffer.append("%20");
      while (true)
      {
        i += 1;
        break;
        if (c == '#')
          localStringBuffer.append("%23");
        else if (c == '%')
          localStringBuffer.append("%25");
        else if (c == '&')
          localStringBuffer.append("%26");
        else if (c == ';')
          localStringBuffer.append("%3B");
        else if (c == '<')
          localStringBuffer.append("%3C");
        else if (c == '=')
          localStringBuffer.append("%3D");
        else if (c == '>')
          localStringBuffer.append("%3E");
        else if (c == '?')
          localStringBuffer.append("%3F");
        else if (c == '~')
          localStringBuffer.append("%7E");
        else
          localStringBuffer.append(c);
      }
    }
    if ((!str1.endsWith("/")) && (paramFile.isDirectory()))
      localStringBuffer.append('/');
    return new URL("file", "", localStringBuffer.toString());
  }

  private void internalSetFeature(XMLReader paramXMLReader, String paramString1, boolean paramBoolean, String paramString2)
    throws JDOMException
  {
    try
    {
      paramXMLReader.setFeature(paramString1, paramBoolean);
      return;
    }
    catch (SAXNotSupportedException paramString1)
    {
      throw new JDOMException(paramString2 + " feature not supported for SAX driver " + paramXMLReader.getClass().getName());
    }
    catch (SAXNotRecognizedException paramString1)
    {
    }
    throw new JDOMException(paramString2 + " feature not recognized for SAX driver " + paramXMLReader.getClass().getName());
  }

  private void internalSetProperty(XMLReader paramXMLReader, String paramString1, Object paramObject, String paramString2)
    throws JDOMException
  {
    try
    {
      paramXMLReader.setProperty(paramString1, paramObject);
      return;
    }
    catch (SAXNotSupportedException paramString1)
    {
      throw new JDOMException(paramString2 + " property not supported for SAX driver " + paramXMLReader.getClass().getName());
    }
    catch (SAXNotRecognizedException paramString1)
    {
    }
    throw new JDOMException(paramString2 + " property not recognized for SAX driver " + paramXMLReader.getClass().getName());
  }

  // ERROR //
  private void setFeaturesAndProperties(XMLReader paramXMLReader, boolean paramBoolean)
    throws JDOMException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 76	org/jdom/input/SAXBuilder:features	Ljava/util/HashMap;
    //   4: invokevirtual 233	java/util/HashMap:keySet	()Ljava/util/Set;
    //   7: invokeinterface 239 1 0
    //   12: astore_3
    //   13: aload_3
    //   14: invokeinterface 244 1 0
    //   19: ifeq +41 -> 60
    //   22: aload_3
    //   23: invokeinterface 248 1 0
    //   28: checkcast 127	java/lang/String
    //   31: astore 4
    //   33: aload_0
    //   34: aload_1
    //   35: aload 4
    //   37: aload_0
    //   38: getfield 76	org/jdom/input/SAXBuilder:features	Ljava/util/HashMap;
    //   41: aload 4
    //   43: invokevirtual 252	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   46: checkcast 254	java/lang/Boolean
    //   49: invokevirtual 257	java/lang/Boolean:booleanValue	()Z
    //   52: aload 4
    //   54: invokespecial 259	org/jdom/input/SAXBuilder:internalSetFeature	(Lorg/xml/sax/XMLReader;Ljava/lang/String;ZLjava/lang/String;)V
    //   57: goto -44 -> 13
    //   60: aload_0
    //   61: getfield 78	org/jdom/input/SAXBuilder:properties	Ljava/util/HashMap;
    //   64: invokevirtual 233	java/util/HashMap:keySet	()Ljava/util/Set;
    //   67: invokeinterface 239 1 0
    //   72: astore_3
    //   73: aload_3
    //   74: invokeinterface 244 1 0
    //   79: ifeq +35 -> 114
    //   82: aload_3
    //   83: invokeinterface 248 1 0
    //   88: checkcast 127	java/lang/String
    //   91: astore 4
    //   93: aload_0
    //   94: aload_1
    //   95: aload 4
    //   97: aload_0
    //   98: getfield 78	org/jdom/input/SAXBuilder:properties	Ljava/util/HashMap;
    //   101: aload 4
    //   103: invokevirtual 252	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   106: aload 4
    //   108: invokespecial 261	org/jdom/input/SAXBuilder:internalSetProperty	(Lorg/xml/sax/XMLReader;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)V
    //   111: goto -38 -> 73
    //   114: iload_2
    //   115: ifeq +42 -> 157
    //   118: aload_0
    //   119: aload_1
    //   120: ldc_w 263
    //   123: aload_0
    //   124: getfield 92	org/jdom/input/SAXBuilder:validate	Z
    //   127: ldc_w 265
    //   130: invokespecial 259	org/jdom/input/SAXBuilder:internalSetFeature	(Lorg/xml/sax/XMLReader;Ljava/lang/String;ZLjava/lang/String;)V
    //   133: aload_0
    //   134: aload_1
    //   135: ldc_w 267
    //   138: iconst_1
    //   139: ldc_w 269
    //   142: invokespecial 259	org/jdom/input/SAXBuilder:internalSetFeature	(Lorg/xml/sax/XMLReader;Ljava/lang/String;ZLjava/lang/String;)V
    //   145: aload_0
    //   146: aload_1
    //   147: ldc_w 271
    //   150: iconst_1
    //   151: ldc_w 273
    //   154: invokespecial 259	org/jdom/input/SAXBuilder:internalSetFeature	(Lorg/xml/sax/XMLReader;Ljava/lang/String;ZLjava/lang/String;)V
    //   157: aload_1
    //   158: ldc_w 275
    //   161: invokeinterface 278 2 0
    //   166: aload_0
    //   167: getfield 52	org/jdom/input/SAXBuilder:expand	Z
    //   170: if_icmpeq +16 -> 186
    //   173: aload_1
    //   174: ldc_w 275
    //   177: aload_0
    //   178: getfield 52	org/jdom/input/SAXBuilder:expand	Z
    //   181: invokeinterface 205 3 0
    //   186: return
    //   187: astore_3
    //   188: aload_0
    //   189: getfield 92	org/jdom/input/SAXBuilder:validate	Z
    //   192: ifeq -59 -> 133
    //   195: aload_3
    //   196: athrow
    //   197: astore_1
    //   198: return
    //   199: astore_1
    //   200: return
    //
    // Exception table:
    //   from	to	target	type
    //   118	133	187	org/jdom/JDOMException
    //   157	186	197	org/xml/sax/SAXNotSupportedException
    //   157	186	199	org/xml/sax/SAXNotRecognizedException
  }

  public Document build(File paramFile)
    throws JDOMException, IOException
  {
    try
    {
      paramFile = build(fileToURL(paramFile));
      return paramFile;
    }
    catch (MalformedURLException paramFile)
    {
    }
    throw new JDOMException("Error in building", paramFile);
  }

  public Document build(InputStream paramInputStream)
    throws JDOMException, IOException
  {
    return build(new InputSource(paramInputStream));
  }

  public Document build(InputStream paramInputStream, String paramString)
    throws JDOMException, IOException
  {
    paramInputStream = new InputSource(paramInputStream);
    paramInputStream.setSystemId(paramString);
    return build(paramInputStream);
  }

  public Document build(Reader paramReader)
    throws JDOMException, IOException
  {
    return build(new InputSource(paramReader));
  }

  public Document build(Reader paramReader, String paramString)
    throws JDOMException, IOException
  {
    paramReader = new InputSource(paramReader);
    paramReader.setSystemId(paramString);
    return build(paramReader);
  }

  public Document build(String paramString)
    throws JDOMException, IOException
  {
    return build(new InputSource(paramString));
  }

  public Document build(URL paramURL)
    throws JDOMException, IOException
  {
    return build(new InputSource(paramURL.toExternalForm()));
  }

  // ERROR //
  public Document build(InputSource paramInputSource)
    throws JDOMException, IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aload_0
    //   6: invokevirtual 323	org/jdom/input/SAXBuilder:createContentHandler	()Lorg/jdom/input/SAXHandler;
    //   9: astore 5
    //   11: aload 5
    //   13: astore_3
    //   14: aload 5
    //   16: astore 4
    //   18: aload_0
    //   19: aload 5
    //   21: invokevirtual 327	org/jdom/input/SAXBuilder:configureContentHandler	(Lorg/jdom/input/SAXHandler;)V
    //   24: aload 5
    //   26: astore_3
    //   27: aload 5
    //   29: astore 4
    //   31: aload_0
    //   32: getfield 88	org/jdom/input/SAXBuilder:saxParser	Lorg/xml/sax/XMLReader;
    //   35: astore 6
    //   37: aload 6
    //   39: ifnonnull +185 -> 224
    //   42: aload 5
    //   44: astore_3
    //   45: aload 5
    //   47: astore 4
    //   49: aload_0
    //   50: invokevirtual 331	org/jdom/input/SAXBuilder:createParser	()Lorg/xml/sax/XMLReader;
    //   53: astore 6
    //   55: aload 6
    //   57: astore_2
    //   58: aload 5
    //   60: astore_3
    //   61: aload 5
    //   63: astore 4
    //   65: aload_0
    //   66: getfield 60	org/jdom/input/SAXBuilder:saxXMLFilter	Lorg/xml/sax/XMLFilter;
    //   69: ifnull +81 -> 150
    //   72: aload 5
    //   74: astore_3
    //   75: aload 5
    //   77: astore 4
    //   79: aload_0
    //   80: getfield 60	org/jdom/input/SAXBuilder:saxXMLFilter	Lorg/xml/sax/XMLFilter;
    //   83: astore_2
    //   84: aload 5
    //   86: astore_3
    //   87: aload 5
    //   89: astore 4
    //   91: aload_2
    //   92: invokeinterface 336 1 0
    //   97: instanceof 333
    //   100: ifeq +23 -> 123
    //   103: aload 5
    //   105: astore_3
    //   106: aload 5
    //   108: astore 4
    //   110: aload_2
    //   111: invokeinterface 336 1 0
    //   116: checkcast 333	org/xml/sax/XMLFilter
    //   119: astore_2
    //   120: goto -36 -> 84
    //   123: aload 5
    //   125: astore_3
    //   126: aload 5
    //   128: astore 4
    //   130: aload_2
    //   131: aload 6
    //   133: invokeinterface 340 2 0
    //   138: aload 5
    //   140: astore_3
    //   141: aload 5
    //   143: astore 4
    //   145: aload_0
    //   146: getfield 60	org/jdom/input/SAXBuilder:saxXMLFilter	Lorg/xml/sax/XMLFilter;
    //   149: astore_2
    //   150: aload 5
    //   152: astore_3
    //   153: aload 5
    //   155: astore 4
    //   157: aload_0
    //   158: aload_2
    //   159: aload 5
    //   161: invokevirtual 344	org/jdom/input/SAXBuilder:configureParser	(Lorg/xml/sax/XMLReader;Lorg/jdom/input/SAXHandler;)V
    //   164: aload_2
    //   165: astore 6
    //   167: aload 5
    //   169: astore_3
    //   170: aload 5
    //   172: astore 4
    //   174: aload_0
    //   175: getfield 86	org/jdom/input/SAXBuilder:reuseParser	Z
    //   178: ifeq +18 -> 196
    //   181: aload 5
    //   183: astore_3
    //   184: aload 5
    //   186: astore 4
    //   188: aload_0
    //   189: aload_2
    //   190: putfield 88	org/jdom/input/SAXBuilder:saxParser	Lorg/xml/sax/XMLReader;
    //   193: aload_2
    //   194: astore 6
    //   196: aload 5
    //   198: astore_3
    //   199: aload 5
    //   201: astore 4
    //   203: aload 6
    //   205: aload_1
    //   206: invokeinterface 348 2 0
    //   211: aload 5
    //   213: astore_3
    //   214: aload 5
    //   216: astore 4
    //   218: aload 5
    //   220: invokevirtual 354	org/jdom/input/SAXHandler:getDocument	()Lorg/jdom/Document;
    //   223: areturn
    //   224: aload 5
    //   226: astore_3
    //   227: aload 5
    //   229: astore 4
    //   231: aload_0
    //   232: aload 6
    //   234: aload 5
    //   236: invokevirtual 344	org/jdom/input/SAXBuilder:configureParser	(Lorg/xml/sax/XMLReader;Lorg/jdom/input/SAXHandler;)V
    //   239: goto -43 -> 196
    //   242: astore 4
    //   244: aload_3
    //   245: invokevirtual 354	org/jdom/input/SAXHandler:getDocument	()Lorg/jdom/Document;
    //   248: astore_2
    //   249: aload_2
    //   250: astore_1
    //   251: aload_2
    //   252: invokevirtual 359	org/jdom/Document:hasRootElement	()Z
    //   255: ifne +5 -> 260
    //   258: aconst_null
    //   259: astore_1
    //   260: aload 4
    //   262: invokevirtual 362	org/xml/sax/SAXParseException:getSystemId	()Ljava/lang/String;
    //   265: astore_2
    //   266: aload_2
    //   267: ifnull +51 -> 318
    //   270: new 364	org/jdom/input/JDOMParseException
    //   273: dup
    //   274: new 115	java/lang/StringBuffer
    //   277: dup
    //   278: invokespecial 116	java/lang/StringBuffer:<init>	()V
    //   281: ldc_w 366
    //   284: invokevirtual 154	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   287: aload 4
    //   289: invokevirtual 369	org/xml/sax/SAXParseException:getLineNumber	()I
    //   292: invokevirtual 372	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   295: ldc_w 374
    //   298: invokevirtual 154	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   301: aload_2
    //   302: invokevirtual 154	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   305: invokevirtual 188	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   308: aload 4
    //   310: aload_1
    //   311: invokespecial 377	org/jdom/input/JDOMParseException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;Lorg/jdom/Document;)V
    //   314: athrow
    //   315: astore_1
    //   316: aload_1
    //   317: athrow
    //   318: new 364	org/jdom/input/JDOMParseException
    //   321: dup
    //   322: new 115	java/lang/StringBuffer
    //   325: dup
    //   326: invokespecial 116	java/lang/StringBuffer:<init>	()V
    //   329: ldc_w 366
    //   332: invokevirtual 154	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   335: aload 4
    //   337: invokevirtual 369	org/xml/sax/SAXParseException:getLineNumber	()I
    //   340: invokevirtual 372	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   343: invokevirtual 188	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   346: aload 4
    //   348: aload_1
    //   349: invokespecial 377	org/jdom/input/JDOMParseException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;Lorg/jdom/Document;)V
    //   352: athrow
    //   353: astore_1
    //   354: new 364	org/jdom/input/JDOMParseException
    //   357: dup
    //   358: new 115	java/lang/StringBuffer
    //   361: dup
    //   362: invokespecial 116	java/lang/StringBuffer:<init>	()V
    //   365: ldc_w 379
    //   368: invokevirtual 154	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   371: aload_1
    //   372: invokevirtual 380	org/xml/sax/SAXException:getMessage	()Ljava/lang/String;
    //   375: invokevirtual 154	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   378: invokevirtual 188	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   381: aload_1
    //   382: aload 4
    //   384: invokevirtual 354	org/jdom/input/SAXHandler:getDocument	()Lorg/jdom/Document;
    //   387: invokespecial 377	org/jdom/input/JDOMParseException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;Lorg/jdom/Document;)V
    //   390: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   5	11	242	org/xml/sax/SAXParseException
    //   18	24	242	org/xml/sax/SAXParseException
    //   31	37	242	org/xml/sax/SAXParseException
    //   49	55	242	org/xml/sax/SAXParseException
    //   65	72	242	org/xml/sax/SAXParseException
    //   79	84	242	org/xml/sax/SAXParseException
    //   91	103	242	org/xml/sax/SAXParseException
    //   110	120	242	org/xml/sax/SAXParseException
    //   130	138	242	org/xml/sax/SAXParseException
    //   145	150	242	org/xml/sax/SAXParseException
    //   157	164	242	org/xml/sax/SAXParseException
    //   174	181	242	org/xml/sax/SAXParseException
    //   188	193	242	org/xml/sax/SAXParseException
    //   203	211	242	org/xml/sax/SAXParseException
    //   218	224	242	org/xml/sax/SAXParseException
    //   231	239	242	org/xml/sax/SAXParseException
    //   5	11	315	finally
    //   18	24	315	finally
    //   31	37	315	finally
    //   49	55	315	finally
    //   65	72	315	finally
    //   79	84	315	finally
    //   91	103	315	finally
    //   110	120	315	finally
    //   130	138	315	finally
    //   145	150	315	finally
    //   157	164	315	finally
    //   174	181	315	finally
    //   188	193	315	finally
    //   203	211	315	finally
    //   218	224	315	finally
    //   231	239	315	finally
    //   244	249	315	finally
    //   251	258	315	finally
    //   260	266	315	finally
    //   270	315	315	finally
    //   318	353	315	finally
    //   354	391	315	finally
    //   5	11	353	org/xml/sax/SAXException
    //   18	24	353	org/xml/sax/SAXException
    //   31	37	353	org/xml/sax/SAXException
    //   49	55	353	org/xml/sax/SAXException
    //   65	72	353	org/xml/sax/SAXException
    //   79	84	353	org/xml/sax/SAXException
    //   91	103	353	org/xml/sax/SAXException
    //   110	120	353	org/xml/sax/SAXException
    //   130	138	353	org/xml/sax/SAXException
    //   145	150	353	org/xml/sax/SAXException
    //   157	164	353	org/xml/sax/SAXException
    //   174	181	353	org/xml/sax/SAXException
    //   188	193	353	org/xml/sax/SAXException
    //   203	211	353	org/xml/sax/SAXException
    //   218	224	353	org/xml/sax/SAXException
    //   231	239	353	org/xml/sax/SAXException
  }

  protected void configureContentHandler(SAXHandler paramSAXHandler)
  {
    paramSAXHandler.setExpandEntities(this.expand);
    paramSAXHandler.setIgnoringElementContentWhitespace(this.ignoringWhite);
    paramSAXHandler.setIgnoringBoundaryWhitespace(this.ignoringBoundaryWhite);
  }

  protected void configureParser(XMLReader paramXMLReader, SAXHandler paramSAXHandler)
    throws JDOMException
  {
    paramXMLReader.setContentHandler(paramSAXHandler);
    if (this.saxEntityResolver != null)
      paramXMLReader.setEntityResolver(this.saxEntityResolver);
    if (this.saxDTDHandler != null)
      paramXMLReader.setDTDHandler(this.saxDTDHandler);
    while (true)
    {
      int i;
      if (this.saxErrorHandler != null)
      {
        paramXMLReader.setErrorHandler(this.saxErrorHandler);
        if (!this.skipNextLexicalReportingConfig)
          i = 0;
      }
      try
      {
        paramXMLReader.setProperty("http://xml.org/sax/handlers/LexicalHandler", paramSAXHandler);
        i = 1;
        label81: int j = i;
        if (i == 0);
        try
        {
          paramXMLReader.setProperty("http://xml.org/sax/properties/lexical-handler", paramSAXHandler);
          j = 1;
          if ((j == 0) && (this.fastReconfigure))
            this.skipNextLexicalReportingConfig = true;
          if (!this.skipNextEntityExpandConfig)
          {
            j = 0;
            i = j;
            if (this.expand);
          }
          try
          {
            paramXMLReader.setProperty("http://xml.org/sax/properties/declaration-handler", paramSAXHandler);
            i = 1;
            if ((i == 0) && (this.fastReconfigure))
              this.skipNextEntityExpandConfig = true;
            return;
            paramXMLReader.setDTDHandler(paramSAXHandler);
            continue;
            paramXMLReader.setErrorHandler(new BuilderErrorHandler());
          }
          catch (SAXNotRecognizedException paramXMLReader)
          {
            while (true)
              i = j;
          }
          catch (SAXNotSupportedException paramXMLReader)
          {
            while (true)
              i = j;
          }
        }
        catch (SAXNotRecognizedException localSAXNotRecognizedException1)
        {
          while (true)
            j = i;
        }
        catch (SAXNotSupportedException localSAXNotSupportedException1)
        {
          while (true)
            j = i;
        }
      }
      catch (SAXNotRecognizedException localSAXNotRecognizedException2)
      {
        break label81;
      }
      catch (SAXNotSupportedException localSAXNotSupportedException2)
      {
        break label81;
      }
    }
  }

  protected SAXHandler createContentHandler()
  {
    return new SAXHandler(this.factory);
  }

  // ERROR //
  protected XMLReader createParser()
    throws JDOMException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_0
    //   7: getfield 90	org/jdom/input/SAXBuilder:saxDriverClass	Ljava/lang/String;
    //   10: ifnull +81 -> 91
    //   13: aload_0
    //   14: getfield 90	org/jdom/input/SAXBuilder:saxDriverClass	Ljava/lang/String;
    //   17: invokestatic 425	org/xml/sax/helpers/XMLReaderFactory:createXMLReader	(Ljava/lang/String;)Lorg/xml/sax/XMLReader;
    //   20: astore_1
    //   21: aload_0
    //   22: aload_1
    //   23: iconst_1
    //   24: invokespecial 427	org/jdom/input/SAXBuilder:setFeaturesAndProperties	(Lorg/xml/sax/XMLReader;Z)V
    //   27: aload_1
    //   28: astore_2
    //   29: aload_1
    //   30: ifnonnull +26 -> 56
    //   33: ldc 11
    //   35: invokestatic 425	org/xml/sax/helpers/XMLReaderFactory:createXMLReader	(Ljava/lang/String;)Lorg/xml/sax/XMLReader;
    //   38: astore_2
    //   39: aload_0
    //   40: aload_2
    //   41: invokevirtual 211	java/lang/Object:getClass	()Ljava/lang/Class;
    //   44: invokevirtual 214	java/lang/Class:getName	()Ljava/lang/String;
    //   47: putfield 90	org/jdom/input/SAXBuilder:saxDriverClass	Ljava/lang/String;
    //   50: aload_0
    //   51: aload_2
    //   52: iconst_1
    //   53: invokespecial 427	org/jdom/input/SAXBuilder:setFeaturesAndProperties	(Lorg/xml/sax/XMLReader;Z)V
    //   56: aload_2
    //   57: areturn
    //   58: astore_1
    //   59: new 196	org/jdom/JDOMException
    //   62: dup
    //   63: new 115	java/lang/StringBuffer
    //   66: dup
    //   67: invokespecial 116	java/lang/StringBuffer:<init>	()V
    //   70: ldc_w 429
    //   73: invokevirtual 154	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   76: aload_0
    //   77: getfield 90	org/jdom/input/SAXBuilder:saxDriverClass	Ljava/lang/String;
    //   80: invokevirtual 154	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   83: invokevirtual 188	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   86: aload_1
    //   87: invokespecial 292	org/jdom/JDOMException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   90: athrow
    //   91: aload 5
    //   93: astore_1
    //   94: aload 6
    //   96: astore_2
    //   97: ldc_w 431
    //   100: invokestatic 101	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   103: astore 7
    //   105: aload 5
    //   107: astore_1
    //   108: aload 6
    //   110: astore_2
    //   111: getstatic 434	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   114: astore 8
    //   116: aload 5
    //   118: astore_1
    //   119: aload 6
    //   121: astore_2
    //   122: getstatic 436	org/jdom/input/SAXBuilder:class$java$util$Map	Ljava/lang/Class;
    //   125: ifnonnull +176 -> 301
    //   128: aload 5
    //   130: astore_1
    //   131: aload 6
    //   133: astore_2
    //   134: ldc_w 438
    //   137: invokestatic 440	org/jdom/input/SAXBuilder:class$	(Ljava/lang/String;)Ljava/lang/Class;
    //   140: astore_3
    //   141: aload 5
    //   143: astore_1
    //   144: aload 6
    //   146: astore_2
    //   147: aload_3
    //   148: putstatic 436	org/jdom/input/SAXBuilder:class$java$util$Map	Ljava/lang/Class;
    //   151: aload 5
    //   153: astore_1
    //   154: aload 6
    //   156: astore_2
    //   157: getstatic 436	org/jdom/input/SAXBuilder:class$java$util$Map	Ljava/lang/Class;
    //   160: ifnonnull +154 -> 314
    //   163: aload 5
    //   165: astore_1
    //   166: aload 6
    //   168: astore_2
    //   169: ldc_w 438
    //   172: invokestatic 440	org/jdom/input/SAXBuilder:class$	(Ljava/lang/String;)Ljava/lang/Class;
    //   175: astore 4
    //   177: aload 5
    //   179: astore_1
    //   180: aload 6
    //   182: astore_2
    //   183: aload 4
    //   185: putstatic 436	org/jdom/input/SAXBuilder:class$java$util$Map	Ljava/lang/Class;
    //   188: aload 5
    //   190: astore_1
    //   191: aload 6
    //   193: astore_2
    //   194: aload 7
    //   196: ldc_w 441
    //   199: iconst_3
    //   200: anewarray 98	java/lang/Class
    //   203: dup
    //   204: iconst_0
    //   205: aload 8
    //   207: aastore
    //   208: dup
    //   209: iconst_1
    //   210: aload_3
    //   211: aastore
    //   212: dup
    //   213: iconst_2
    //   214: aload 4
    //   216: aastore
    //   217: invokevirtual 445	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   220: astore 4
    //   222: aload 5
    //   224: astore_1
    //   225: aload 6
    //   227: astore_2
    //   228: aload_0
    //   229: getfield 92	org/jdom/input/SAXBuilder:validate	Z
    //   232: ifeq +96 -> 328
    //   235: aload 5
    //   237: astore_1
    //   238: aload 6
    //   240: astore_2
    //   241: getstatic 449	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   244: astore_3
    //   245: aload 5
    //   247: astore_1
    //   248: aload 6
    //   250: astore_2
    //   251: aload 4
    //   253: aconst_null
    //   254: iconst_3
    //   255: anewarray 4	java/lang/Object
    //   258: dup
    //   259: iconst_0
    //   260: aload_3
    //   261: aastore
    //   262: dup
    //   263: iconst_1
    //   264: aload_0
    //   265: getfield 76	org/jdom/input/SAXBuilder:features	Ljava/util/HashMap;
    //   268: aastore
    //   269: dup
    //   270: iconst_2
    //   271: aload_0
    //   272: getfield 78	org/jdom/input/SAXBuilder:properties	Ljava/util/HashMap;
    //   275: aastore
    //   276: invokevirtual 455	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   279: checkcast 202	org/xml/sax/XMLReader
    //   282: astore_3
    //   283: aload_3
    //   284: astore_1
    //   285: aload_3
    //   286: astore_2
    //   287: aload_0
    //   288: aload_3
    //   289: iconst_0
    //   290: invokespecial 427	org/jdom/input/SAXBuilder:setFeaturesAndProperties	(Lorg/xml/sax/XMLReader;Z)V
    //   293: aload_3
    //   294: astore_1
    //   295: goto -268 -> 27
    //   298: astore_1
    //   299: aload_1
    //   300: athrow
    //   301: aload 5
    //   303: astore_1
    //   304: aload 6
    //   306: astore_2
    //   307: getstatic 436	org/jdom/input/SAXBuilder:class$java$util$Map	Ljava/lang/Class;
    //   310: astore_3
    //   311: goto -160 -> 151
    //   314: aload 5
    //   316: astore_1
    //   317: aload 6
    //   319: astore_2
    //   320: getstatic 436	org/jdom/input/SAXBuilder:class$java$util$Map	Ljava/lang/Class;
    //   323: astore 4
    //   325: goto -137 -> 188
    //   328: aload 5
    //   330: astore_1
    //   331: aload 6
    //   333: astore_2
    //   334: getstatic 458	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   337: astore_3
    //   338: goto -93 -> 245
    //   341: astore_1
    //   342: new 196	org/jdom/JDOMException
    //   345: dup
    //   346: ldc_w 460
    //   349: aload_1
    //   350: invokespecial 292	org/jdom/JDOMException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   353: athrow
    //   354: astore_2
    //   355: goto -328 -> 27
    //   358: astore_1
    //   359: aload_2
    //   360: astore_1
    //   361: goto -334 -> 27
    //
    // Exception table:
    //   from	to	target	type
    //   13	27	58	org/xml/sax/SAXException
    //   97	105	298	org/jdom/JDOMException
    //   111	116	298	org/jdom/JDOMException
    //   122	128	298	org/jdom/JDOMException
    //   134	141	298	org/jdom/JDOMException
    //   147	151	298	org/jdom/JDOMException
    //   157	163	298	org/jdom/JDOMException
    //   169	177	298	org/jdom/JDOMException
    //   183	188	298	org/jdom/JDOMException
    //   194	222	298	org/jdom/JDOMException
    //   228	235	298	org/jdom/JDOMException
    //   241	245	298	org/jdom/JDOMException
    //   251	283	298	org/jdom/JDOMException
    //   287	293	298	org/jdom/JDOMException
    //   307	311	298	org/jdom/JDOMException
    //   320	325	298	org/jdom/JDOMException
    //   334	338	298	org/jdom/JDOMException
    //   33	56	341	org/xml/sax/SAXException
    //   97	105	354	java/lang/Exception
    //   111	116	354	java/lang/Exception
    //   122	128	354	java/lang/Exception
    //   134	141	354	java/lang/Exception
    //   147	151	354	java/lang/Exception
    //   157	163	354	java/lang/Exception
    //   169	177	354	java/lang/Exception
    //   183	188	354	java/lang/Exception
    //   194	222	354	java/lang/Exception
    //   228	235	354	java/lang/Exception
    //   241	245	354	java/lang/Exception
    //   251	283	354	java/lang/Exception
    //   287	293	354	java/lang/Exception
    //   307	311	354	java/lang/Exception
    //   320	325	354	java/lang/Exception
    //   334	338	354	java/lang/Exception
    //   97	105	358	java/lang/NoClassDefFoundError
    //   111	116	358	java/lang/NoClassDefFoundError
    //   122	128	358	java/lang/NoClassDefFoundError
    //   134	141	358	java/lang/NoClassDefFoundError
    //   147	151	358	java/lang/NoClassDefFoundError
    //   157	163	358	java/lang/NoClassDefFoundError
    //   169	177	358	java/lang/NoClassDefFoundError
    //   183	188	358	java/lang/NoClassDefFoundError
    //   194	222	358	java/lang/NoClassDefFoundError
    //   228	235	358	java/lang/NoClassDefFoundError
    //   241	245	358	java/lang/NoClassDefFoundError
    //   251	283	358	java/lang/NoClassDefFoundError
    //   287	293	358	java/lang/NoClassDefFoundError
    //   307	311	358	java/lang/NoClassDefFoundError
    //   320	325	358	java/lang/NoClassDefFoundError
    //   334	338	358	java/lang/NoClassDefFoundError
  }

  public DTDHandler getDTDHandler()
  {
    return this.saxDTDHandler;
  }

  public String getDriverClass()
  {
    return this.saxDriverClass;
  }

  public EntityResolver getEntityResolver()
  {
    return this.saxEntityResolver;
  }

  public ErrorHandler getErrorHandler()
  {
    return this.saxErrorHandler;
  }

  public boolean getExpandEntities()
  {
    return this.expand;
  }

  public JDOMFactory getFactory()
  {
    return this.factory;
  }

  public boolean getIgnoringBoundaryWhitespace()
  {
    return this.ignoringBoundaryWhite;
  }

  public boolean getIgnoringElementContentWhitespace()
  {
    return this.ignoringWhite;
  }

  public boolean getReuseParser()
  {
    return this.reuseParser;
  }

  public boolean getValidation()
  {
    return this.validate;
  }

  public XMLFilter getXMLFilter()
  {
    return this.saxXMLFilter;
  }

  public void setDTDHandler(DTDHandler paramDTDHandler)
  {
    this.saxDTDHandler = paramDTDHandler;
  }

  public void setEntityResolver(EntityResolver paramEntityResolver)
  {
    this.saxEntityResolver = paramEntityResolver;
  }

  public void setErrorHandler(ErrorHandler paramErrorHandler)
  {
    this.saxErrorHandler = paramErrorHandler;
  }

  public void setExpandEntities(boolean paramBoolean)
  {
    this.expand = paramBoolean;
  }

  public void setFactory(JDOMFactory paramJDOMFactory)
  {
    this.factory = paramJDOMFactory;
  }

  public void setFastReconfigure(boolean paramBoolean)
  {
    if (this.reuseParser)
      this.fastReconfigure = paramBoolean;
  }

  public void setFeature(String paramString, boolean paramBoolean)
  {
    HashMap localHashMap = this.features;
    if (paramBoolean);
    for (Boolean localBoolean = Boolean.TRUE; ; localBoolean = Boolean.FALSE)
    {
      localHashMap.put(paramString, localBoolean);
      return;
    }
  }

  public void setIgnoringBoundaryWhitespace(boolean paramBoolean)
  {
    this.ignoringBoundaryWhite = paramBoolean;
  }

  public void setIgnoringElementContentWhitespace(boolean paramBoolean)
  {
    this.ignoringWhite = paramBoolean;
  }

  public void setProperty(String paramString, Object paramObject)
  {
    this.properties.put(paramString, paramObject);
  }

  public void setReuseParser(boolean paramBoolean)
  {
    this.reuseParser = paramBoolean;
    this.saxParser = null;
  }

  public void setValidation(boolean paramBoolean)
  {
    this.validate = paramBoolean;
  }

  public void setXMLFilter(XMLFilter paramXMLFilter)
  {
    this.saxXMLFilter = paramXMLFilter;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.input.SAXBuilder
 * JD-Core Version:    0.6.2
 */