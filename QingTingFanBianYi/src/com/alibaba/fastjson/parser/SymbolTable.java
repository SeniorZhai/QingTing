package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;

public class SymbolTable
{
  public static final int DEFAULT_TABLE_SIZE = 256;
  public static final int MAX_BUCKET_LENTH = 8;
  public static final int MAX_SIZE = 2048;
  private final Entry[] buckets;
  private final int indexMask;
  private int size = 0;
  private final String[] symbols;
  private final char[][] symbols_char;

  public SymbolTable()
  {
    this(256);
    addSymbol("$ref", 0, 4, "$ref".hashCode());
    addSymbol(JSON.DEFAULT_TYPE_KEY, 0, 4, JSON.DEFAULT_TYPE_KEY.hashCode());
  }

  public SymbolTable(int paramInt)
  {
    this.indexMask = (paramInt - 1);
    this.buckets = new Entry[paramInt];
    this.symbols = new String[paramInt];
    this.symbols_char = new char[paramInt][];
  }

  public static final int hash(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i = 0;
    while (i < paramInt2)
    {
      j = j * 31 + paramArrayOfChar[paramInt1];
      i += 1;
      paramInt1 += 1;
    }
    return j;
  }

  public String addSymbol(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    int i1 = paramInt3 & this.indexMask;
    Object localObject = this.symbols[i1];
    int i = 1;
    int k = 1;
    char[] arrayOfChar;
    if (localObject != null)
    {
      if (((String)localObject).length() == paramInt2)
      {
        arrayOfChar = this.symbols_char[i1];
        i = 0;
        while (true)
        {
          j = k;
          if (i < paramInt2)
          {
            if (paramString.charAt(paramInt1 + i) != arrayOfChar[i])
              j = 0;
          }
          else
          {
            i = j;
            if (j == 0)
              break;
            return localObject;
          }
          i += 1;
        }
      }
      i = 0;
    }
    int j = 0;
    localObject = this.buckets[i1];
    if (localObject != null)
    {
      arrayOfChar = ((Entry)localObject).characters;
      k = j;
      int n;
      if (paramInt2 == arrayOfChar.length)
      {
        k = j;
        if (paramInt3 == ((Entry)localObject).hashCode)
        {
          n = 1;
          k = 0;
        }
      }
      while (true)
      {
        int m = n;
        if (k < paramInt2)
        {
          if (paramString.charAt(paramInt1 + k) != arrayOfChar[k])
            m = 0;
        }
        else
        {
          if (m != 0)
            break label221;
          k = j + 1;
          localObject = ((Entry)localObject).next;
          j = k;
          break;
        }
        k += 1;
      }
      label221: return ((Entry)localObject).symbol;
    }
    if (j >= 8)
      return paramString.substring(paramInt1, paramInt1 + paramInt2);
    if (this.size >= 2048)
      return paramString.substring(paramInt1, paramInt1 + paramInt2);
    paramString = new Entry(paramString, paramInt1, paramInt2, paramInt3, this.buckets[i1]);
    this.buckets[i1] = paramString;
    if (i != 0)
    {
      this.symbols[i1] = paramString.symbol;
      this.symbols_char[i1] = paramString.characters;
    }
    this.size += 1;
    return paramString.symbol;
  }

  public String addSymbol(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    return addSymbol(paramArrayOfChar, paramInt1, paramInt2, hash(paramArrayOfChar, paramInt1, paramInt2));
  }

  public String addSymbol(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
  {
    int i1 = paramInt3 & this.indexMask;
    Object localObject = this.symbols[i1];
    int i = 1;
    int k = 1;
    char[] arrayOfChar;
    if (localObject != null)
    {
      if (((String)localObject).length() == paramInt2)
      {
        arrayOfChar = this.symbols_char[i1];
        i = 0;
        while (true)
        {
          j = k;
          if (i < paramInt2)
          {
            if (paramArrayOfChar[(paramInt1 + i)] != arrayOfChar[i])
              j = 0;
          }
          else
          {
            i = j;
            if (j == 0)
              break;
            return localObject;
          }
          i += 1;
        }
      }
      i = 0;
    }
    int j = 0;
    localObject = this.buckets[i1];
    if (localObject != null)
    {
      arrayOfChar = ((Entry)localObject).characters;
      k = j;
      int n;
      if (paramInt2 == arrayOfChar.length)
      {
        k = j;
        if (paramInt3 == ((Entry)localObject).hashCode)
        {
          n = 1;
          k = 0;
        }
      }
      while (true)
      {
        int m = n;
        if (k < paramInt2)
        {
          if (paramArrayOfChar[(paramInt1 + k)] != arrayOfChar[k])
            m = 0;
        }
        else
        {
          if (m != 0)
            break label217;
          k = j + 1;
          localObject = ((Entry)localObject).next;
          j = k;
          break;
        }
        k += 1;
      }
      label217: return ((Entry)localObject).symbol;
    }
    if (j >= 8)
      return new String(paramArrayOfChar, paramInt1, paramInt2);
    if (this.size >= 2048)
      return new String(paramArrayOfChar, paramInt1, paramInt2);
    paramArrayOfChar = new Entry(paramArrayOfChar, paramInt1, paramInt2, paramInt3, this.buckets[i1]);
    this.buckets[i1] = paramArrayOfChar;
    if (i != 0)
    {
      this.symbols[i1] = paramArrayOfChar.symbol;
      this.symbols_char[i1] = paramArrayOfChar.characters;
    }
    this.size += 1;
    return paramArrayOfChar.symbol;
  }

  public int size()
  {
    return this.size;
  }

  protected static final class Entry
  {
    public final byte[] bytes;
    public final char[] characters;
    public final int hashCode;
    public Entry next;
    public final String symbol;

    public Entry(String paramString, int paramInt1, int paramInt2, int paramInt3, Entry paramEntry)
    {
      this.symbol = paramString.substring(paramInt1, paramInt1 + paramInt2).intern();
      this.characters = this.symbol.toCharArray();
      this.next = paramEntry;
      this.hashCode = paramInt3;
      this.bytes = null;
    }

    public Entry(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3, Entry paramEntry)
    {
      this.characters = new char[paramInt2];
      System.arraycopy(paramArrayOfChar, paramInt1, this.characters, 0, paramInt2);
      this.symbol = new String(this.characters).intern();
      this.next = paramEntry;
      this.hashCode = paramInt3;
      this.bytes = null;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.SymbolTable
 * JD-Core Version:    0.6.2
 */