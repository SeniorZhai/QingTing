package com.google.protobuf;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextFormat
{
  private static final int BUFFER_SIZE = 4096;
  private static final Printer DEFAULT_PRINTER = new Printer(null);
  private static final Printer SINGLE_LINE_PRINTER = new Printer(null).setSingleLineMode(true);
  private static final Printer UNICODE_PRINTER = new Printer(null).setEscapeNonAscii(false);

  private static int digitValue(byte paramByte)
  {
    if ((48 <= paramByte) && (paramByte <= 57))
      return paramByte - 48;
    if ((97 <= paramByte) && (paramByte <= 122))
      return paramByte - 97 + 10;
    return paramByte - 65 + 10;
  }

  static String escapeBytes(ByteString paramByteString)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramByteString.size());
    int i = 0;
    if (i < paramByteString.size())
    {
      int j = paramByteString.byteAt(i);
      switch (j)
      {
      default:
        if (j >= 32)
          localStringBuilder.append((char)j);
        break;
      case 7:
      case 8:
      case 12:
      case 10:
      case 13:
      case 9:
      case 11:
      case 92:
      case 39:
      case 34:
      }
      while (true)
      {
        i += 1;
        break;
        localStringBuilder.append("\\a");
        continue;
        localStringBuilder.append("\\b");
        continue;
        localStringBuilder.append("\\f");
        continue;
        localStringBuilder.append("\\n");
        continue;
        localStringBuilder.append("\\r");
        continue;
        localStringBuilder.append("\\t");
        continue;
        localStringBuilder.append("\\v");
        continue;
        localStringBuilder.append("\\\\");
        continue;
        localStringBuilder.append("\\'");
        continue;
        localStringBuilder.append("\\\"");
        continue;
        localStringBuilder.append('\\');
        localStringBuilder.append((char)((j >>> 6 & 0x3) + 48));
        localStringBuilder.append((char)((j >>> 3 & 0x7) + 48));
        localStringBuilder.append((char)((j & 0x7) + 48));
      }
    }
    return localStringBuilder.toString();
  }

  static String escapeText(String paramString)
  {
    return escapeBytes(ByteString.copyFromUtf8(paramString));
  }

  private static boolean isHex(byte paramByte)
  {
    return ((48 <= paramByte) && (paramByte <= 57)) || ((97 <= paramByte) && (paramByte <= 102)) || ((65 <= paramByte) && (paramByte <= 70));
  }

  private static boolean isOctal(byte paramByte)
  {
    return (48 <= paramByte) && (paramByte <= 55);
  }

  public static void merge(CharSequence paramCharSequence, ExtensionRegistry paramExtensionRegistry, Message.Builder paramBuilder)
    throws TextFormat.ParseException
  {
    paramCharSequence = new Tokenizer(paramCharSequence, null);
    while (!paramCharSequence.atEnd())
      mergeField(paramCharSequence, paramExtensionRegistry, paramBuilder);
  }

  public static void merge(CharSequence paramCharSequence, Message.Builder paramBuilder)
    throws TextFormat.ParseException
  {
    merge(paramCharSequence, ExtensionRegistry.getEmptyRegistry(), paramBuilder);
  }

  public static void merge(Readable paramReadable, ExtensionRegistry paramExtensionRegistry, Message.Builder paramBuilder)
    throws IOException
  {
    merge(toStringBuilder(paramReadable), paramExtensionRegistry, paramBuilder);
  }

  public static void merge(Readable paramReadable, Message.Builder paramBuilder)
    throws IOException
  {
    merge(paramReadable, ExtensionRegistry.getEmptyRegistry(), paramBuilder);
  }

  private static void mergeField(Tokenizer paramTokenizer, ExtensionRegistry paramExtensionRegistry, Message.Builder paramBuilder)
    throws TextFormat.ParseException
  {
    Object localObject4 = paramBuilder.getDescriptorForType();
    Object localObject3 = null;
    Object localObject1;
    Object localObject2;
    if (paramTokenizer.tryConsume("["))
    {
      localObject1 = new StringBuilder(paramTokenizer.consumeIdentifier());
      while (paramTokenizer.tryConsume("."))
      {
        ((StringBuilder)localObject1).append('.');
        ((StringBuilder)localObject1).append(paramTokenizer.consumeIdentifier());
      }
      localObject3 = paramExtensionRegistry.findExtensionByName(((StringBuilder)localObject1).toString());
      if (localObject3 == null)
        throw paramTokenizer.parseExceptionPreviousToken("Extension \"" + localObject1 + "\" not found in the ExtensionRegistry.");
      if (((ExtensionRegistry.ExtensionInfo)localObject3).descriptor.getContainingType() != localObject4)
        throw paramTokenizer.parseExceptionPreviousToken("Extension \"" + localObject1 + "\" does not extend message type \"" + ((Descriptors.Descriptor)localObject4).getFullName() + "\".");
      paramTokenizer.consume("]");
      localObject1 = ((ExtensionRegistry.ExtensionInfo)localObject3).descriptor;
      localObject2 = null;
      if (((Descriptors.FieldDescriptor)localObject1).getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE)
        break label485;
      paramTokenizer.tryConsume(":");
      if (!paramTokenizer.tryConsume("<"))
        break label420;
      localObject2 = ">";
      label204: if (localObject3 != null)
        break label435;
      localObject3 = paramBuilder.newBuilderForField((Descriptors.FieldDescriptor)localObject1);
    }
    while (true)
    {
      if (paramTokenizer.tryConsume((String)localObject2))
        break label460;
      if (paramTokenizer.atEnd())
      {
        throw paramTokenizer.parseException("Expected \"" + (String)localObject2 + "\".");
        String str = paramTokenizer.consumeIdentifier();
        localObject2 = ((Descriptors.Descriptor)localObject4).findFieldByName(str);
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject2 = ((Descriptors.Descriptor)localObject4).findFieldByName(str.toLowerCase(Locale.US));
          localObject1 = localObject2;
          if (localObject2 != null)
          {
            localObject1 = localObject2;
            if (((Descriptors.FieldDescriptor)localObject2).getType() != Descriptors.FieldDescriptor.Type.GROUP)
              localObject1 = null;
          }
        }
        localObject2 = localObject1;
        if (localObject1 != null)
        {
          localObject2 = localObject1;
          if (((Descriptors.FieldDescriptor)localObject1).getType() == Descriptors.FieldDescriptor.Type.GROUP)
          {
            localObject2 = localObject1;
            if (!((Descriptors.FieldDescriptor)localObject1).getMessageType().getName().equals(str))
              localObject2 = null;
          }
        }
        localObject1 = localObject2;
        if (localObject2 != null)
          break;
        throw paramTokenizer.parseExceptionPreviousToken("Message type \"" + ((Descriptors.Descriptor)localObject4).getFullName() + "\" has no field named \"" + str + "\".");
        label420: paramTokenizer.consume("{");
        localObject2 = "}";
        break label204;
        label435: localObject3 = ((ExtensionRegistry.ExtensionInfo)localObject3).defaultInstance.newBuilderForType();
        continue;
      }
      mergeField(paramTokenizer, paramExtensionRegistry, (Message.Builder)localObject3);
    }
    label460: paramExtensionRegistry = ((Message.Builder)localObject3).buildPartial();
    while (((Descriptors.FieldDescriptor)localObject1).isRepeated())
    {
      paramBuilder.addRepeatedField((Descriptors.FieldDescriptor)localObject1, paramExtensionRegistry);
      return;
      label485: paramTokenizer.consume(":");
      switch (1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[localObject1.getType().ordinal()])
      {
      default:
        paramExtensionRegistry = (ExtensionRegistry)localObject2;
        break;
      case 1:
      case 2:
      case 3:
        paramExtensionRegistry = Integer.valueOf(paramTokenizer.consumeInt32());
        break;
      case 4:
      case 5:
      case 6:
        paramExtensionRegistry = Long.valueOf(paramTokenizer.consumeInt64());
        break;
      case 10:
      case 11:
        paramExtensionRegistry = Integer.valueOf(paramTokenizer.consumeUInt32());
        break;
      case 12:
      case 13:
        paramExtensionRegistry = Long.valueOf(paramTokenizer.consumeUInt64());
        break;
      case 8:
        paramExtensionRegistry = Float.valueOf(paramTokenizer.consumeFloat());
        break;
      case 9:
        paramExtensionRegistry = Double.valueOf(paramTokenizer.consumeDouble());
        break;
      case 7:
        paramExtensionRegistry = Boolean.valueOf(paramTokenizer.consumeBoolean());
        break;
      case 14:
        paramExtensionRegistry = paramTokenizer.consumeString();
        break;
      case 15:
        paramExtensionRegistry = paramTokenizer.consumeByteString();
        break;
      case 16:
        localObject3 = ((Descriptors.FieldDescriptor)localObject1).getEnumType();
        if (paramTokenizer.lookingAtInteger())
        {
          int i = paramTokenizer.consumeInt32();
          localObject2 = ((Descriptors.EnumDescriptor)localObject3).findValueByNumber(i);
          paramExtensionRegistry = (ExtensionRegistry)localObject2;
          if (localObject2 == null)
            throw paramTokenizer.parseExceptionPreviousToken("Enum type \"" + ((Descriptors.EnumDescriptor)localObject3).getFullName() + "\" has no value with number " + i + '.');
        }
        else
        {
          localObject4 = paramTokenizer.consumeIdentifier();
          localObject2 = ((Descriptors.EnumDescriptor)localObject3).findValueByName((String)localObject4);
          paramExtensionRegistry = (ExtensionRegistry)localObject2;
          if (localObject2 == null)
            throw paramTokenizer.parseExceptionPreviousToken("Enum type \"" + ((Descriptors.EnumDescriptor)localObject3).getFullName() + "\" has no value named \"" + (String)localObject4 + "\".");
        }
        break;
      case 17:
      case 18:
        throw new RuntimeException("Can't get here.");
      }
    }
    paramBuilder.setField((Descriptors.FieldDescriptor)localObject1, paramExtensionRegistry);
  }

  static int parseInt32(String paramString)
    throws NumberFormatException
  {
    return (int)parseInteger(paramString, true, false);
  }

  static long parseInt64(String paramString)
    throws NumberFormatException
  {
    return parseInteger(paramString, true, true);
  }

  private static long parseInteger(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    throws NumberFormatException
  {
    int i = 0;
    int k = 0;
    if (paramString.startsWith("-", 0))
    {
      if (!paramBoolean1)
        throw new NumberFormatException("Number must be positive: " + paramString);
      i = 0 + 1;
      k = 1;
    }
    int j = 10;
    int m;
    if (paramString.startsWith("0x", i))
    {
      m = i + 2;
      j = 16;
    }
    Object localObject;
    long l2;
    long l1;
    while (true)
    {
      localObject = paramString.substring(m);
      if (((String)localObject).length() >= 16)
        break label258;
      l2 = Long.parseLong((String)localObject, j);
      l1 = l2;
      if (k != 0)
        l1 = -l2;
      l2 = l1;
      if (paramBoolean2)
        break label450;
      if (!paramBoolean1)
        break;
      if (l1 <= 2147483647L)
      {
        l2 = l1;
        if (l1 >= -2147483648L)
          break label450;
      }
      throw new NumberFormatException("Number out of range for 32-bit signed integer: " + paramString);
      m = i;
      if (paramString.startsWith("0", i))
      {
        j = 8;
        m = i;
      }
    }
    if (l1 < 4294967296L)
    {
      l2 = l1;
      if (l1 >= 0L);
    }
    else
    {
      throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + paramString);
      label258: BigInteger localBigInteger = new BigInteger((String)localObject, j);
      localObject = localBigInteger;
      if (k != 0)
        localObject = localBigInteger.negate();
      if (!paramBoolean2)
      {
        if (paramBoolean1)
        {
          if (((BigInteger)localObject).bitLength() > 31)
            throw new NumberFormatException("Number out of range for 32-bit signed integer: " + paramString);
        }
        else if (((BigInteger)localObject).bitLength() > 32)
          throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + paramString);
      }
      else if (paramBoolean1)
      {
        if (((BigInteger)localObject).bitLength() > 63)
          throw new NumberFormatException("Number out of range for 64-bit signed integer: " + paramString);
      }
      else if (((BigInteger)localObject).bitLength() > 64)
        throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + paramString);
      l2 = ((BigInteger)localObject).longValue();
    }
    label450: return l2;
  }

  static int parseUInt32(String paramString)
    throws NumberFormatException
  {
    return (int)parseInteger(paramString, false, false);
  }

  static long parseUInt64(String paramString)
    throws NumberFormatException
  {
    return parseInteger(paramString, false, true);
  }

  public static void print(MessageOrBuilder paramMessageOrBuilder, Appendable paramAppendable)
    throws IOException
  {
    DEFAULT_PRINTER.print(paramMessageOrBuilder, new TextGenerator(paramAppendable, null));
  }

  public static void print(UnknownFieldSet paramUnknownFieldSet, Appendable paramAppendable)
    throws IOException
  {
    DEFAULT_PRINTER.printUnknownFields(paramUnknownFieldSet, new TextGenerator(paramAppendable, null));
  }

  public static void printField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, Appendable paramAppendable)
    throws IOException
  {
    DEFAULT_PRINTER.printField(paramFieldDescriptor, paramObject, new TextGenerator(paramAppendable, null));
  }

  public static String printFieldToString(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      printField(paramFieldDescriptor, paramObject, localStringBuilder);
      paramFieldDescriptor = localStringBuilder.toString();
      return paramFieldDescriptor;
    }
    catch (IOException paramFieldDescriptor)
    {
    }
    throw new IllegalStateException(paramFieldDescriptor);
  }

  public static void printFieldValue(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, Appendable paramAppendable)
    throws IOException
  {
    DEFAULT_PRINTER.printFieldValue(paramFieldDescriptor, paramObject, new TextGenerator(paramAppendable, null));
  }

  public static String printToString(MessageOrBuilder paramMessageOrBuilder)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      print(paramMessageOrBuilder, localStringBuilder);
      paramMessageOrBuilder = localStringBuilder.toString();
      return paramMessageOrBuilder;
    }
    catch (IOException paramMessageOrBuilder)
    {
    }
    throw new IllegalStateException(paramMessageOrBuilder);
  }

  public static String printToString(UnknownFieldSet paramUnknownFieldSet)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      print(paramUnknownFieldSet, localStringBuilder);
      paramUnknownFieldSet = localStringBuilder.toString();
      return paramUnknownFieldSet;
    }
    catch (IOException paramUnknownFieldSet)
    {
    }
    throw new IllegalStateException(paramUnknownFieldSet);
  }

  public static String printToUnicodeString(MessageOrBuilder paramMessageOrBuilder)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      UNICODE_PRINTER.print(paramMessageOrBuilder, new TextGenerator(localStringBuilder, null));
      paramMessageOrBuilder = localStringBuilder.toString();
      return paramMessageOrBuilder;
    }
    catch (IOException paramMessageOrBuilder)
    {
    }
    throw new IllegalStateException(paramMessageOrBuilder);
  }

  public static String printToUnicodeString(UnknownFieldSet paramUnknownFieldSet)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      UNICODE_PRINTER.printUnknownFields(paramUnknownFieldSet, new TextGenerator(localStringBuilder, null));
      paramUnknownFieldSet = localStringBuilder.toString();
      return paramUnknownFieldSet;
    }
    catch (IOException paramUnknownFieldSet)
    {
    }
    throw new IllegalStateException(paramUnknownFieldSet);
  }

  private static void printUnknownFieldValue(int paramInt, Object paramObject, TextGenerator paramTextGenerator)
    throws IOException
  {
    switch (WireFormat.getTagWireType(paramInt))
    {
    case 4:
    default:
      throw new IllegalArgumentException("Bad tag: " + paramInt);
    case 0:
      paramTextGenerator.print(unsignedToString(((Long)paramObject).longValue()));
      return;
    case 5:
      paramTextGenerator.print(String.format((Locale)null, "0x%08x", new Object[] { (Integer)paramObject }));
      return;
    case 1:
      paramTextGenerator.print(String.format((Locale)null, "0x%016x", new Object[] { (Long)paramObject }));
      return;
    case 2:
      paramTextGenerator.print("\"");
      paramTextGenerator.print(escapeBytes((ByteString)paramObject));
      paramTextGenerator.print("\"");
      return;
    case 3:
    }
    DEFAULT_PRINTER.printUnknownFields((UnknownFieldSet)paramObject, paramTextGenerator);
  }

  public static void printUnknownFieldValue(int paramInt, Object paramObject, Appendable paramAppendable)
    throws IOException
  {
    printUnknownFieldValue(paramInt, paramObject, new TextGenerator(paramAppendable, null));
  }

  public static String shortDebugString(MessageOrBuilder paramMessageOrBuilder)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      SINGLE_LINE_PRINTER.print(paramMessageOrBuilder, new TextGenerator(localStringBuilder, null));
      paramMessageOrBuilder = localStringBuilder.toString().trim();
      return paramMessageOrBuilder;
    }
    catch (IOException paramMessageOrBuilder)
    {
    }
    throw new IllegalStateException(paramMessageOrBuilder);
  }

  public static String shortDebugString(UnknownFieldSet paramUnknownFieldSet)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      SINGLE_LINE_PRINTER.printUnknownFields(paramUnknownFieldSet, new TextGenerator(localStringBuilder, null));
      paramUnknownFieldSet = localStringBuilder.toString().trim();
      return paramUnknownFieldSet;
    }
    catch (IOException paramUnknownFieldSet)
    {
    }
    throw new IllegalStateException(paramUnknownFieldSet);
  }

  private static StringBuilder toStringBuilder(Readable paramReadable)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    CharBuffer localCharBuffer = CharBuffer.allocate(4096);
    while (true)
    {
      int i = paramReadable.read(localCharBuffer);
      if (i == -1)
        return localStringBuilder;
      localCharBuffer.flip();
      localStringBuilder.append(localCharBuffer, 0, i);
    }
  }

  static ByteString unescapeBytes(CharSequence paramCharSequence)
    throws TextFormat.InvalidEscapeSequenceException
  {
    paramCharSequence = ByteString.copyFromUtf8(paramCharSequence.toString());
    byte[] arrayOfByte = new byte[paramCharSequence.size()];
    int m = 0;
    int j = 0;
    if (j < paramCharSequence.size())
    {
      byte b = paramCharSequence.byteAt(j);
      int k;
      int n;
      int i;
      if (b == 92)
        if (j + 1 < paramCharSequence.size())
        {
          k = j + 1;
          b = paramCharSequence.byteAt(k);
          if (isOctal(b))
          {
            n = digitValue(b);
            i = n;
            j = k;
            if (k + 1 < paramCharSequence.size())
            {
              i = n;
              j = k;
              if (isOctal(paramCharSequence.byteAt(k + 1)))
              {
                j = k + 1;
                i = n * 8 + digitValue(paramCharSequence.byteAt(j));
              }
            }
            n = i;
            k = j;
            if (j + 1 < paramCharSequence.size())
            {
              n = i;
              k = j;
              if (isOctal(paramCharSequence.byteAt(j + 1)))
              {
                k = j + 1;
                n = i * 8 + digitValue(paramCharSequence.byteAt(k));
              }
            }
            arrayOfByte[m] = ((byte)n);
            i = m + 1;
            j = k;
          }
        }
      while (true)
      {
        j += 1;
        m = i;
        break;
        switch (b)
        {
        default:
          throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + (char)b + '\'');
        case 97:
          arrayOfByte[m] = 7;
          i = m + 1;
          j = k;
          break;
        case 98:
          arrayOfByte[m] = 8;
          i = m + 1;
          j = k;
          break;
        case 102:
          arrayOfByte[m] = 12;
          i = m + 1;
          j = k;
          break;
        case 110:
          arrayOfByte[m] = 10;
          i = m + 1;
          j = k;
          break;
        case 114:
          arrayOfByte[m] = 13;
          i = m + 1;
          j = k;
          break;
        case 116:
          arrayOfByte[m] = 9;
          i = m + 1;
          j = k;
          break;
        case 118:
          arrayOfByte[m] = 11;
          i = m + 1;
          j = k;
          break;
        case 92:
          arrayOfByte[m] = 92;
          i = m + 1;
          j = k;
          break;
        case 39:
          arrayOfByte[m] = 39;
          i = m + 1;
          j = k;
          break;
        case 34:
          arrayOfByte[m] = 34;
          i = m + 1;
          j = k;
          break;
        case 120:
          if ((k + 1 < paramCharSequence.size()) && (isHex(paramCharSequence.byteAt(k + 1))))
          {
            n = k + 1;
            k = digitValue(paramCharSequence.byteAt(n));
            i = k;
            j = n;
            if (n + 1 < paramCharSequence.size())
            {
              i = k;
              j = n;
              if (isHex(paramCharSequence.byteAt(n + 1)))
              {
                j = n + 1;
                i = k * 16 + digitValue(paramCharSequence.byteAt(j));
              }
            }
            arrayOfByte[m] = ((byte)i);
            i = m + 1;
          }
          else
          {
            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
            arrayOfByte[m] = b;
            i = m + 1;
          }
          break;
        }
      }
    }
    return ByteString.copyFrom(arrayOfByte, 0, m);
  }

  static String unescapeText(String paramString)
    throws TextFormat.InvalidEscapeSequenceException
  {
    return unescapeBytes(paramString).toStringUtf8();
  }

  private static String unsignedToString(int paramInt)
  {
    if (paramInt >= 0)
      return Integer.toString(paramInt);
    return Long.toString(paramInt & 0xFFFFFFFF);
  }

  private static String unsignedToString(long paramLong)
  {
    if (paramLong >= 0L)
      return Long.toString(paramLong);
    return BigInteger.valueOf(0xFFFFFFFF & paramLong).setBit(63).toString();
  }

  static class InvalidEscapeSequenceException extends IOException
  {
    private static final long serialVersionUID = -8164033650142593304L;

    InvalidEscapeSequenceException(String paramString)
    {
      super();
    }
  }

  public static class ParseException extends IOException
  {
    private static final long serialVersionUID = 3196188060225107702L;
    private final int column;
    private final int line;

    public ParseException(int paramInt1, int paramInt2, String paramString)
    {
      super();
      this.line = paramInt1;
      this.column = paramInt2;
    }

    public ParseException(String paramString)
    {
      this(-1, -1, paramString);
    }

    public int getColumn()
    {
      return this.column;
    }

    public int getLine()
    {
      return this.line;
    }
  }

  private static final class Printer
  {
    boolean escapeNonAscii = true;
    boolean singleLineMode = false;

    private void print(MessageOrBuilder paramMessageOrBuilder, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      Iterator localIterator = paramMessageOrBuilder.getAllFields().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        printField((Descriptors.FieldDescriptor)localEntry.getKey(), localEntry.getValue(), paramTextGenerator);
      }
      printUnknownFields(paramMessageOrBuilder.getUnknownFields(), paramTextGenerator);
    }

    private void printField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      if (paramFieldDescriptor.isRepeated())
      {
        paramObject = ((List)paramObject).iterator();
        while (paramObject.hasNext())
          printSingleField(paramFieldDescriptor, paramObject.next(), paramTextGenerator);
      }
      printSingleField(paramFieldDescriptor, paramObject, paramTextGenerator);
    }

    private void printFieldValue(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      switch (TextFormat.1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[paramFieldDescriptor.getType().ordinal()])
      {
      default:
        return;
      case 1:
      case 2:
      case 3:
        paramTextGenerator.print(((Integer)paramObject).toString());
        return;
      case 4:
      case 5:
      case 6:
        paramTextGenerator.print(((Long)paramObject).toString());
        return;
      case 7:
        paramTextGenerator.print(((Boolean)paramObject).toString());
        return;
      case 8:
        paramTextGenerator.print(((Float)paramObject).toString());
        return;
      case 9:
        paramTextGenerator.print(((Double)paramObject).toString());
        return;
      case 10:
      case 11:
        paramTextGenerator.print(TextFormat.unsignedToString(((Integer)paramObject).intValue()));
        return;
      case 12:
      case 13:
        paramTextGenerator.print(TextFormat.unsignedToString(((Long)paramObject).longValue()));
        return;
      case 14:
        paramTextGenerator.print("\"");
        if (this.escapeNonAscii);
        for (paramFieldDescriptor = TextFormat.escapeText((String)paramObject); ; paramFieldDescriptor = (String)paramObject)
        {
          paramTextGenerator.print(paramFieldDescriptor);
          paramTextGenerator.print("\"");
          return;
        }
      case 15:
        paramTextGenerator.print("\"");
        paramTextGenerator.print(TextFormat.escapeBytes((ByteString)paramObject));
        paramTextGenerator.print("\"");
        return;
      case 16:
        paramTextGenerator.print(((Descriptors.EnumValueDescriptor)paramObject).getName());
        return;
      case 17:
      case 18:
      }
      print((Message)paramObject, paramTextGenerator);
    }

    private void printSingleField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      if (paramFieldDescriptor.isExtension())
      {
        paramTextGenerator.print("[");
        if ((paramFieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat()) && (paramFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE) && (paramFieldDescriptor.isOptional()) && (paramFieldDescriptor.getExtensionScope() == paramFieldDescriptor.getMessageType()))
        {
          paramTextGenerator.print(paramFieldDescriptor.getMessageType().getFullName());
          paramTextGenerator.print("]");
          label71: if (paramFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE)
            break label184;
          if (!this.singleLineMode)
            break label171;
          paramTextGenerator.print(" { ");
        }
      }
      while (true)
      {
        printFieldValue(paramFieldDescriptor, paramObject, paramTextGenerator);
        if (paramFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE)
          break label204;
        if (!this.singleLineMode)
          break label193;
        paramTextGenerator.print("} ");
        return;
        paramTextGenerator.print(paramFieldDescriptor.getFullName());
        break;
        if (paramFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP)
        {
          paramTextGenerator.print(paramFieldDescriptor.getMessageType().getName());
          break label71;
        }
        paramTextGenerator.print(paramFieldDescriptor.getName());
        break label71;
        label171: paramTextGenerator.print(" {\n");
        paramTextGenerator.indent();
        continue;
        label184: paramTextGenerator.print(": ");
      }
      label193: paramTextGenerator.outdent();
      paramTextGenerator.print("}\n");
      return;
      label204: if (this.singleLineMode)
      {
        paramTextGenerator.print(" ");
        return;
      }
      paramTextGenerator.print("\n");
    }

    private void printUnknownField(int paramInt1, int paramInt2, List<?> paramList, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      Iterator localIterator = paramList.iterator();
      if (localIterator.hasNext())
      {
        paramList = localIterator.next();
        paramTextGenerator.print(String.valueOf(paramInt1));
        paramTextGenerator.print(": ");
        TextFormat.printUnknownFieldValue(paramInt2, paramList, paramTextGenerator);
        if (this.singleLineMode);
        for (paramList = " "; ; paramList = "\n")
        {
          paramTextGenerator.print(paramList);
          break;
        }
      }
    }

    private void printUnknownFields(UnknownFieldSet paramUnknownFieldSet, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      paramUnknownFieldSet = paramUnknownFieldSet.asMap().entrySet().iterator();
      while (paramUnknownFieldSet.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramUnknownFieldSet.next();
        int i = ((Integer)localEntry.getKey()).intValue();
        Object localObject = (UnknownFieldSet.Field)localEntry.getValue();
        printUnknownField(i, 0, ((UnknownFieldSet.Field)localObject).getVarintList(), paramTextGenerator);
        printUnknownField(i, 5, ((UnknownFieldSet.Field)localObject).getFixed32List(), paramTextGenerator);
        printUnknownField(i, 1, ((UnknownFieldSet.Field)localObject).getFixed64List(), paramTextGenerator);
        printUnknownField(i, 2, ((UnknownFieldSet.Field)localObject).getLengthDelimitedList(), paramTextGenerator);
        localObject = ((UnknownFieldSet.Field)localObject).getGroupList().iterator();
        while (((Iterator)localObject).hasNext())
        {
          UnknownFieldSet localUnknownFieldSet = (UnknownFieldSet)((Iterator)localObject).next();
          paramTextGenerator.print(((Integer)localEntry.getKey()).toString());
          if (this.singleLineMode)
            paramTextGenerator.print(" { ");
          while (true)
          {
            printUnknownFields(localUnknownFieldSet, paramTextGenerator);
            if (!this.singleLineMode)
              break label210;
            paramTextGenerator.print("} ");
            break;
            paramTextGenerator.print(" {\n");
            paramTextGenerator.indent();
          }
          label210: paramTextGenerator.outdent();
          paramTextGenerator.print("}\n");
        }
      }
    }

    private Printer setEscapeNonAscii(boolean paramBoolean)
    {
      this.escapeNonAscii = paramBoolean;
      return this;
    }

    private Printer setSingleLineMode(boolean paramBoolean)
    {
      this.singleLineMode = paramBoolean;
      return this;
    }
  }

  private static final class TextGenerator
  {
    private boolean atStartOfLine = true;
    private final StringBuilder indent = new StringBuilder();
    private final Appendable output;

    private TextGenerator(Appendable paramAppendable)
    {
      this.output = paramAppendable;
    }

    private void write(CharSequence paramCharSequence, int paramInt)
      throws IOException
    {
      if (paramInt == 0)
        return;
      if (this.atStartOfLine)
      {
        this.atStartOfLine = false;
        this.output.append(this.indent);
      }
      this.output.append(paramCharSequence);
    }

    public void indent()
    {
      this.indent.append("  ");
    }

    public void outdent()
    {
      int i = this.indent.length();
      if (i == 0)
        throw new IllegalArgumentException(" Outdent() without matching Indent().");
      this.indent.delete(i - 2, i);
    }

    public void print(CharSequence paramCharSequence)
      throws IOException
    {
      int m = paramCharSequence.length();
      int j = 0;
      int i = 0;
      while (i < m)
      {
        int k = j;
        if (paramCharSequence.charAt(i) == '\n')
        {
          write(paramCharSequence.subSequence(j, m), i - j + 1);
          k = i + 1;
          this.atStartOfLine = true;
        }
        i += 1;
        j = k;
      }
      write(paramCharSequence.subSequence(j, m), m - j);
    }
  }

  private static final class Tokenizer
  {
    private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
    private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
    private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);
    private static final Pattern TOKEN;
    private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
    private int column = 0;
    private String currentToken;
    private int line = 0;
    private final Matcher matcher;
    private int pos = 0;
    private int previousColumn = 0;
    private int previousLine = 0;
    private final CharSequence text;

    static
    {
      TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
    }

    private Tokenizer(CharSequence paramCharSequence)
    {
      this.text = paramCharSequence;
      this.matcher = WHITESPACE.matcher(paramCharSequence);
      skipWhitespace();
      nextToken();
    }

    private void consumeByteString(List<ByteString> paramList)
      throws TextFormat.ParseException
    {
      int i = 0;
      if (this.currentToken.length() > 0)
        i = this.currentToken.charAt(0);
      if ((i != 34) && (i != 39))
        throw parseException("Expected string.");
      if ((this.currentToken.length() < 2) || (this.currentToken.charAt(this.currentToken.length() - 1) != i))
        throw parseException("String missing ending quote.");
      try
      {
        ByteString localByteString = TextFormat.unescapeBytes(this.currentToken.substring(1, this.currentToken.length() - 1));
        nextToken();
        paramList.add(localByteString);
        return;
      }
      catch (TextFormat.InvalidEscapeSequenceException paramList)
      {
      }
      throw parseException(paramList.getMessage());
    }

    private TextFormat.ParseException floatParseException(NumberFormatException paramNumberFormatException)
    {
      return parseException("Couldn't parse number: " + paramNumberFormatException.getMessage());
    }

    private TextFormat.ParseException integerParseException(NumberFormatException paramNumberFormatException)
    {
      return parseException("Couldn't parse integer: " + paramNumberFormatException.getMessage());
    }

    private void skipWhitespace()
    {
      this.matcher.usePattern(WHITESPACE);
      if (this.matcher.lookingAt())
        this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
    }

    public boolean atEnd()
    {
      return this.currentToken.length() == 0;
    }

    public void consume(String paramString)
      throws TextFormat.ParseException
    {
      if (!tryConsume(paramString))
        throw parseException("Expected \"" + paramString + "\".");
    }

    public boolean consumeBoolean()
      throws TextFormat.ParseException
    {
      if ((this.currentToken.equals("true")) || (this.currentToken.equals("t")) || (this.currentToken.equals("1")))
      {
        nextToken();
        return true;
      }
      if ((this.currentToken.equals("false")) || (this.currentToken.equals("f")) || (this.currentToken.equals("0")))
      {
        nextToken();
        return false;
      }
      throw parseException("Expected \"true\" or \"false\".");
    }

    public ByteString consumeByteString()
      throws TextFormat.ParseException
    {
      ArrayList localArrayList = new ArrayList();
      consumeByteString(localArrayList);
      while ((this.currentToken.startsWith("'")) || (this.currentToken.startsWith("\"")))
        consumeByteString(localArrayList);
      return ByteString.copyFrom(localArrayList);
    }

    public double consumeDouble()
      throws TextFormat.ParseException
    {
      if (DOUBLE_INFINITY.matcher(this.currentToken).matches())
      {
        boolean bool = this.currentToken.startsWith("-");
        nextToken();
        if (bool)
          return (-1.0D / 0.0D);
        return (1.0D / 0.0D);
      }
      if (this.currentToken.equalsIgnoreCase("nan"))
      {
        nextToken();
        return (0.0D / 0.0D);
      }
      try
      {
        double d = Double.parseDouble(this.currentToken);
        nextToken();
        return d;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw floatParseException(localNumberFormatException);
      }
    }

    public float consumeFloat()
      throws TextFormat.ParseException
    {
      if (FLOAT_INFINITY.matcher(this.currentToken).matches())
      {
        boolean bool = this.currentToken.startsWith("-");
        nextToken();
        if (bool)
          return (1.0F / -1.0F);
        return (1.0F / 1.0F);
      }
      if (FLOAT_NAN.matcher(this.currentToken).matches())
      {
        nextToken();
        return (0.0F / 0.0F);
      }
      try
      {
        float f = Float.parseFloat(this.currentToken);
        nextToken();
        return f;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw floatParseException(localNumberFormatException);
      }
    }

    public String consumeIdentifier()
      throws TextFormat.ParseException
    {
      int i = 0;
      while (i < this.currentToken.length())
      {
        int j = this.currentToken.charAt(i);
        if (((97 <= j) && (j <= 122)) || ((65 <= j) && (j <= 90)) || ((48 <= j) && (j <= 57)) || (j == 95) || (j == 46))
          i += 1;
        else
          throw parseException("Expected identifier.");
      }
      String str = this.currentToken;
      nextToken();
      return str;
    }

    public int consumeInt32()
      throws TextFormat.ParseException
    {
      try
      {
        int i = TextFormat.parseInt32(this.currentToken);
        nextToken();
        return i;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw integerParseException(localNumberFormatException);
      }
    }

    public long consumeInt64()
      throws TextFormat.ParseException
    {
      try
      {
        long l = TextFormat.parseInt64(this.currentToken);
        nextToken();
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw integerParseException(localNumberFormatException);
      }
    }

    public String consumeString()
      throws TextFormat.ParseException
    {
      return consumeByteString().toStringUtf8();
    }

    public int consumeUInt32()
      throws TextFormat.ParseException
    {
      try
      {
        int i = TextFormat.parseUInt32(this.currentToken);
        nextToken();
        return i;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw integerParseException(localNumberFormatException);
      }
    }

    public long consumeUInt64()
      throws TextFormat.ParseException
    {
      try
      {
        long l = TextFormat.parseUInt64(this.currentToken);
        nextToken();
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw integerParseException(localNumberFormatException);
      }
    }

    public boolean lookingAtInteger()
    {
      if (this.currentToken.length() == 0);
      int i;
      do
      {
        return false;
        i = this.currentToken.charAt(0);
      }
      while (((48 > i) || (i > 57)) && (i != 45) && (i != 43));
      return true;
    }

    public void nextToken()
    {
      this.previousLine = this.line;
      this.previousColumn = this.column;
      if (this.pos < this.matcher.regionStart())
      {
        if (this.text.charAt(this.pos) == '\n')
          this.line += 1;
        for (this.column = 0; ; this.column += 1)
        {
          this.pos += 1;
          break;
        }
      }
      if (this.matcher.regionStart() == this.matcher.regionEnd())
      {
        this.currentToken = "";
        return;
      }
      this.matcher.usePattern(TOKEN);
      if (this.matcher.lookingAt())
      {
        this.currentToken = this.matcher.group();
        this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
      }
      while (true)
      {
        skipWhitespace();
        return;
        this.currentToken = String.valueOf(this.text.charAt(this.pos));
        this.matcher.region(this.pos + 1, this.matcher.regionEnd());
      }
    }

    public TextFormat.ParseException parseException(String paramString)
    {
      return new TextFormat.ParseException(this.line + 1, this.column + 1, paramString);
    }

    public TextFormat.ParseException parseExceptionPreviousToken(String paramString)
    {
      return new TextFormat.ParseException(this.previousLine + 1, this.previousColumn + 1, paramString);
    }

    public boolean tryConsume(String paramString)
    {
      if (this.currentToken.equals(paramString))
      {
        nextToken();
        return true;
      }
      return false;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.TextFormat
 * JD-Core Version:    0.6.2
 */