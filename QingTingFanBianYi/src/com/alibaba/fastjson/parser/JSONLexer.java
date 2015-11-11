package com.alibaba.fastjson.parser;

import java.math.BigDecimal;
import java.util.Collection;

public abstract interface JSONLexer
{
  public static final int ARRAY = 2;
  public static final int END = 4;
  public static final byte EOI = 26;
  public static final int NOT_MATCH = -1;
  public static final int NOT_MATCH_NAME = -2;
  public static final int OBJECT = 1;
  public static final int UNKOWN = 0;
  public static final int VALUE = 3;

  public abstract byte[] bytesValue();

  public abstract void close();

  public abstract void config(Feature paramFeature, boolean paramBoolean);

  public abstract Number decimalValue(boolean paramBoolean);

  public abstract BigDecimal decimalValue();

  public abstract float floatValue();

  public abstract int getBufferPosition();

  public abstract char getCurrent();

  public abstract int intValue();

  public abstract Number integerValue();

  public abstract boolean isBlankInput();

  public abstract boolean isEnabled(Feature paramFeature);

  public abstract boolean isRef();

  public abstract long longValue();

  public abstract char next();

  public abstract void nextToken();

  public abstract void nextToken(int paramInt);

  public abstract void nextTokenWithColon();

  public abstract void nextTokenWithColon(int paramInt);

  public abstract String numberString();

  public abstract Number numberValue();

  public abstract int pos();

  public abstract void resetStringPosition();

  public abstract Enum<?> scanEnum(Class<?> paramClass, SymbolTable paramSymbolTable, char paramChar);

  public abstract int scanInt(char paramChar);

  public abstract long scanLong(char paramChar);

  public abstract void scanNumber();

  public abstract String scanString(char paramChar);

  public abstract void scanString();

  public abstract Collection<String> scanStringArray(Class<?> paramClass, char paramChar);

  public abstract String scanSymbol(SymbolTable paramSymbolTable);

  public abstract String scanSymbol(SymbolTable paramSymbolTable, char paramChar);

  public abstract String scanSymbolUnQuoted(SymbolTable paramSymbolTable);

  public abstract String scanSymbolWithSeperator(SymbolTable paramSymbolTable, char paramChar);

  public abstract void skipWhitespace();

  public abstract String stringVal();

  public abstract int token();

  public abstract String tokenName();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.JSONLexer
 * JD-Core Version:    0.6.2
 */