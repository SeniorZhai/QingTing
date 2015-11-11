package org.jdom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jdom.output.XMLOutputter;

public class ProcessingInstruction extends Content
{
  private static final String CVS_ID = "@(#) $RCSfile: ProcessingInstruction.java,v $ $Revision: 1.47 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
  protected Map mapData;
  protected String rawData;
  protected String target;

  protected ProcessingInstruction()
  {
  }

  public ProcessingInstruction(String paramString1, String paramString2)
  {
    setTarget(paramString1);
    setData(paramString2);
  }

  public ProcessingInstruction(String paramString, Map paramMap)
  {
    setTarget(paramString);
    setData(paramMap);
  }

  private static int[] extractQuotedString(String paramString)
  {
    int i1 = 0;
    int m = 34;
    int j = 0;
    int i = 0;
    if (i < paramString.length())
    {
      int i3 = paramString.charAt(i);
      int i2;
      int n;
      int k;
      if (i3 != 34)
      {
        i2 = i1;
        n = m;
        k = j;
        if (i3 != 39);
      }
      else
      {
        if (i1 != 0)
          break label83;
        n = i3;
        i2 = 1;
        k = i + 1;
      }
      label83: 
      do
      {
        i += 1;
        i1 = i2;
        m = n;
        j = k;
        break;
        i2 = i1;
        n = m;
        k = j;
      }
      while (m != i3);
      return new int[] { j, i };
    }
    return null;
  }

  private Map parseData(String paramString)
  {
    HashMap localHashMap = new HashMap();
    paramString = paramString.trim();
    Object localObject1 = localHashMap;
    Object localObject3;
    String str;
    int j;
    char c1;
    int i;
    if (!paramString.trim().equals(""))
    {
      localObject3 = "";
      str = "";
      j = 0;
      c1 = paramString.charAt(0);
      i = 1;
    }
    while (true)
    {
      Object localObject2 = localObject3;
      int k = i;
      localObject1 = str;
      char c2;
      if (i < paramString.length())
      {
        c2 = paramString.charAt(i);
        if (c2 == '=')
        {
          localObject2 = paramString.substring(j, i).trim();
          localObject3 = extractQuotedString(paramString.substring(i + 1));
          if (localObject3 == null)
          {
            localObject1 = new HashMap();
            return localObject1;
          }
          localObject1 = paramString.substring(localObject3[0] + i + 1, localObject3[1] + i + 1);
          k = i + (localObject3[1] + 1);
        }
      }
      else
      {
        localObject3 = paramString.substring(k);
        paramString = (String)localObject3;
        if (((String)localObject2).length() <= 0)
          break;
        paramString = (String)localObject3;
        if (localObject1 == null)
          break;
        localHashMap.put(localObject2, localObject1);
        paramString = (String)localObject3;
        break;
      }
      k = j;
      if (Character.isWhitespace(c1))
      {
        k = j;
        if (!Character.isWhitespace(c2))
          k = i;
      }
      c1 = c2;
      i += 1;
      j = k;
    }
  }

  private String toString(Map paramMap)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      String str2 = (String)paramMap.get(str1);
      localStringBuffer.append(str1).append("=\"").append(str2).append("\" ");
    }
    if (localStringBuffer.length() > 0)
      localStringBuffer.setLength(localStringBuffer.length() - 1);
    return localStringBuffer.toString();
  }

  public Object clone()
  {
    ProcessingInstruction localProcessingInstruction = (ProcessingInstruction)super.clone();
    if (this.mapData != null)
      localProcessingInstruction.mapData = parseData(this.rawData);
    return localProcessingInstruction;
  }

  public String getData()
  {
    return this.rawData;
  }

  public List getPseudoAttributeNames()
  {
    Object localObject = this.mapData.entrySet();
    ArrayList localArrayList = new ArrayList();
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = ((Iterator)localObject).next().toString();
      localArrayList.add(str.substring(0, str.indexOf("=")));
    }
    return localArrayList;
  }

  public String getPseudoAttributeValue(String paramString)
  {
    return (String)this.mapData.get(paramString);
  }

  public String getTarget()
  {
    return this.target;
  }

  public String getValue()
  {
    return this.rawData;
  }

  public boolean removePseudoAttribute(String paramString)
  {
    if (this.mapData.remove(paramString) != null)
    {
      this.rawData = toString(this.mapData);
      return true;
    }
    return false;
  }

  public ProcessingInstruction setData(String paramString)
  {
    String str = Verifier.checkProcessingInstructionData(paramString);
    if (str != null)
      throw new IllegalDataException(paramString, str);
    this.rawData = paramString;
    this.mapData = parseData(paramString);
    return this;
  }

  public ProcessingInstruction setData(Map paramMap)
  {
    String str1 = toString(paramMap);
    String str2 = Verifier.checkProcessingInstructionData(str1);
    if (str2 != null)
      throw new IllegalDataException(str1, str2);
    this.rawData = str1;
    this.mapData = paramMap;
    return this;
  }

  public ProcessingInstruction setPseudoAttribute(String paramString1, String paramString2)
  {
    String str = Verifier.checkProcessingInstructionData(paramString1);
    if (str != null)
      throw new IllegalDataException(paramString1, str);
    str = Verifier.checkProcessingInstructionData(paramString2);
    if (str != null)
      throw new IllegalDataException(paramString2, str);
    this.mapData.put(paramString1, paramString2);
    this.rawData = toString(this.mapData);
    return this;
  }

  public ProcessingInstruction setTarget(String paramString)
  {
    String str = Verifier.checkProcessingInstructionTarget(paramString);
    if (str != null)
      throw new IllegalTargetException(paramString, str);
    this.target = paramString;
    return this;
  }

  public String toString()
  {
    return "[ProcessingInstruction: " + new XMLOutputter().outputString(this) + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.ProcessingInstruction
 * JD-Core Version:    0.6.2
 */