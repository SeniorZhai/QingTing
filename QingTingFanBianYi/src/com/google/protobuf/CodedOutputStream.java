package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public final class CodedOutputStream
{
  public static final int DEFAULT_BUFFER_SIZE = 4096;
  public static final int LITTLE_ENDIAN_32_SIZE = 4;
  public static final int LITTLE_ENDIAN_64_SIZE = 8;
  private final byte[] buffer;
  private final int limit;
  private final OutputStream output;
  private int position;

  private CodedOutputStream(OutputStream paramOutputStream, byte[] paramArrayOfByte)
  {
    this.output = paramOutputStream;
    this.buffer = paramArrayOfByte;
    this.position = 0;
    this.limit = paramArrayOfByte.length;
  }

  private CodedOutputStream(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.output = null;
    this.buffer = paramArrayOfByte;
    this.position = paramInt1;
    this.limit = (paramInt1 + paramInt2);
  }

  public static int computeBoolSize(int paramInt, boolean paramBoolean)
  {
    return computeTagSize(paramInt) + computeBoolSizeNoTag(paramBoolean);
  }

  public static int computeBoolSizeNoTag(boolean paramBoolean)
  {
    return 1;
  }

  public static int computeBytesSize(int paramInt, ByteString paramByteString)
  {
    return computeTagSize(paramInt) + computeBytesSizeNoTag(paramByteString);
  }

  public static int computeBytesSizeNoTag(ByteString paramByteString)
  {
    return computeRawVarint32Size(paramByteString.size()) + paramByteString.size();
  }

  public static int computeDoubleSize(int paramInt, double paramDouble)
  {
    return computeTagSize(paramInt) + computeDoubleSizeNoTag(paramDouble);
  }

  public static int computeDoubleSizeNoTag(double paramDouble)
  {
    return 8;
  }

  public static int computeEnumSize(int paramInt1, int paramInt2)
  {
    return computeTagSize(paramInt1) + computeEnumSizeNoTag(paramInt2);
  }

  public static int computeEnumSizeNoTag(int paramInt)
  {
    return computeInt32SizeNoTag(paramInt);
  }

  public static int computeFixed32Size(int paramInt1, int paramInt2)
  {
    return computeTagSize(paramInt1) + computeFixed32SizeNoTag(paramInt2);
  }

  public static int computeFixed32SizeNoTag(int paramInt)
  {
    return 4;
  }

  public static int computeFixed64Size(int paramInt, long paramLong)
  {
    return computeTagSize(paramInt) + computeFixed64SizeNoTag(paramLong);
  }

  public static int computeFixed64SizeNoTag(long paramLong)
  {
    return 8;
  }

  public static int computeFloatSize(int paramInt, float paramFloat)
  {
    return computeTagSize(paramInt) + computeFloatSizeNoTag(paramFloat);
  }

  public static int computeFloatSizeNoTag(float paramFloat)
  {
    return 4;
  }

  public static int computeGroupSize(int paramInt, MessageLite paramMessageLite)
  {
    return computeTagSize(paramInt) * 2 + computeGroupSizeNoTag(paramMessageLite);
  }

  public static int computeGroupSizeNoTag(MessageLite paramMessageLite)
  {
    return paramMessageLite.getSerializedSize();
  }

  public static int computeInt32Size(int paramInt1, int paramInt2)
  {
    return computeTagSize(paramInt1) + computeInt32SizeNoTag(paramInt2);
  }

  public static int computeInt32SizeNoTag(int paramInt)
  {
    if (paramInt >= 0)
      return computeRawVarint32Size(paramInt);
    return 10;
  }

  public static int computeInt64Size(int paramInt, long paramLong)
  {
    return computeTagSize(paramInt) + computeInt64SizeNoTag(paramLong);
  }

  public static int computeInt64SizeNoTag(long paramLong)
  {
    return computeRawVarint64Size(paramLong);
  }

  public static int computeLazyFieldMessageSetExtensionSize(int paramInt, LazyField paramLazyField)
  {
    return computeTagSize(1) * 2 + computeUInt32Size(2, paramInt) + computeLazyFieldSize(3, paramLazyField);
  }

  public static int computeLazyFieldSize(int paramInt, LazyField paramLazyField)
  {
    return computeTagSize(paramInt) + computeLazyFieldSizeNoTag(paramLazyField);
  }

  public static int computeLazyFieldSizeNoTag(LazyField paramLazyField)
  {
    int i = paramLazyField.getSerializedSize();
    return computeRawVarint32Size(i) + i;
  }

  public static int computeMessageSetExtensionSize(int paramInt, MessageLite paramMessageLite)
  {
    return computeTagSize(1) * 2 + computeUInt32Size(2, paramInt) + computeMessageSize(3, paramMessageLite);
  }

  public static int computeMessageSize(int paramInt, MessageLite paramMessageLite)
  {
    return computeTagSize(paramInt) + computeMessageSizeNoTag(paramMessageLite);
  }

  public static int computeMessageSizeNoTag(MessageLite paramMessageLite)
  {
    int i = paramMessageLite.getSerializedSize();
    return computeRawVarint32Size(i) + i;
  }

  static int computePreferredBufferSize(int paramInt)
  {
    int i = paramInt;
    if (paramInt > 4096)
      i = 4096;
    return i;
  }

  public static int computeRawMessageSetExtensionSize(int paramInt, ByteString paramByteString)
  {
    return computeTagSize(1) * 2 + computeUInt32Size(2, paramInt) + computeBytesSize(3, paramByteString);
  }

  public static int computeRawVarint32Size(int paramInt)
  {
    if ((paramInt & 0xFFFFFF80) == 0)
      return 1;
    if ((paramInt & 0xFFFFC000) == 0)
      return 2;
    if ((0xFFE00000 & paramInt) == 0)
      return 3;
    if ((0xF0000000 & paramInt) == 0)
      return 4;
    return 5;
  }

  public static int computeRawVarint64Size(long paramLong)
  {
    if ((0xFFFFFF80 & paramLong) == 0L)
      return 1;
    if ((0xFFFFC000 & paramLong) == 0L)
      return 2;
    if ((0xFFE00000 & paramLong) == 0L)
      return 3;
    if ((0xF0000000 & paramLong) == 0L)
      return 4;
    if ((0x0 & paramLong) == 0L)
      return 5;
    if ((0x0 & paramLong) == 0L)
      return 6;
    if ((0x0 & paramLong) == 0L)
      return 7;
    if ((0x0 & paramLong) == 0L)
      return 8;
    if ((0x0 & paramLong) == 0L)
      return 9;
    return 10;
  }

  public static int computeSFixed32Size(int paramInt1, int paramInt2)
  {
    return computeTagSize(paramInt1) + computeSFixed32SizeNoTag(paramInt2);
  }

  public static int computeSFixed32SizeNoTag(int paramInt)
  {
    return 4;
  }

  public static int computeSFixed64Size(int paramInt, long paramLong)
  {
    return computeTagSize(paramInt) + computeSFixed64SizeNoTag(paramLong);
  }

  public static int computeSFixed64SizeNoTag(long paramLong)
  {
    return 8;
  }

  public static int computeSInt32Size(int paramInt1, int paramInt2)
  {
    return computeTagSize(paramInt1) + computeSInt32SizeNoTag(paramInt2);
  }

  public static int computeSInt32SizeNoTag(int paramInt)
  {
    return computeRawVarint32Size(encodeZigZag32(paramInt));
  }

  public static int computeSInt64Size(int paramInt, long paramLong)
  {
    return computeTagSize(paramInt) + computeSInt64SizeNoTag(paramLong);
  }

  public static int computeSInt64SizeNoTag(long paramLong)
  {
    return computeRawVarint64Size(encodeZigZag64(paramLong));
  }

  public static int computeStringSize(int paramInt, String paramString)
  {
    return computeTagSize(paramInt) + computeStringSizeNoTag(paramString);
  }

  public static int computeStringSizeNoTag(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      int i = computeRawVarint32Size(paramString.length);
      int j = paramString.length;
      return i + j;
    }
    catch (UnsupportedEncodingException paramString)
    {
    }
    throw new RuntimeException("UTF-8 not supported.", paramString);
  }

  public static int computeTagSize(int paramInt)
  {
    return computeRawVarint32Size(WireFormat.makeTag(paramInt, 0));
  }

  public static int computeUInt32Size(int paramInt1, int paramInt2)
  {
    return computeTagSize(paramInt1) + computeUInt32SizeNoTag(paramInt2);
  }

  public static int computeUInt32SizeNoTag(int paramInt)
  {
    return computeRawVarint32Size(paramInt);
  }

  public static int computeUInt64Size(int paramInt, long paramLong)
  {
    return computeTagSize(paramInt) + computeUInt64SizeNoTag(paramLong);
  }

  public static int computeUInt64SizeNoTag(long paramLong)
  {
    return computeRawVarint64Size(paramLong);
  }

  @Deprecated
  public static int computeUnknownGroupSize(int paramInt, MessageLite paramMessageLite)
  {
    return computeGroupSize(paramInt, paramMessageLite);
  }

  @Deprecated
  public static int computeUnknownGroupSizeNoTag(MessageLite paramMessageLite)
  {
    return computeGroupSizeNoTag(paramMessageLite);
  }

  public static int encodeZigZag32(int paramInt)
  {
    return paramInt << 1 ^ paramInt >> 31;
  }

  public static long encodeZigZag64(long paramLong)
  {
    return paramLong << 1 ^ paramLong >> 63;
  }

  public static CodedOutputStream newInstance(OutputStream paramOutputStream)
  {
    return newInstance(paramOutputStream, 4096);
  }

  public static CodedOutputStream newInstance(OutputStream paramOutputStream, int paramInt)
  {
    return new CodedOutputStream(paramOutputStream, new byte[paramInt]);
  }

  public static CodedOutputStream newInstance(byte[] paramArrayOfByte)
  {
    return newInstance(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static CodedOutputStream newInstance(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new CodedOutputStream(paramArrayOfByte, paramInt1, paramInt2);
  }

  private void refreshBuffer()
    throws IOException
  {
    if (this.output == null)
      throw new OutOfSpaceException();
    this.output.write(this.buffer, 0, this.position);
    this.position = 0;
  }

  public void checkNoSpaceLeft()
  {
    if (spaceLeft() != 0)
      throw new IllegalStateException("Did not write as much data as expected.");
  }

  public void flush()
    throws IOException
  {
    if (this.output != null)
      refreshBuffer();
  }

  public int spaceLeft()
  {
    if (this.output == null)
      return this.limit - this.position;
    throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
  }

  public void writeBool(int paramInt, boolean paramBoolean)
    throws IOException
  {
    writeTag(paramInt, 0);
    writeBoolNoTag(paramBoolean);
  }

  public void writeBoolNoTag(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean);
    for (int i = 1; ; i = 0)
    {
      writeRawByte(i);
      return;
    }
  }

  public void writeBytes(int paramInt, ByteString paramByteString)
    throws IOException
  {
    writeTag(paramInt, 2);
    writeBytesNoTag(paramByteString);
  }

  public void writeBytesNoTag(ByteString paramByteString)
    throws IOException
  {
    writeRawVarint32(paramByteString.size());
    writeRawBytes(paramByteString);
  }

  public void writeDouble(int paramInt, double paramDouble)
    throws IOException
  {
    writeTag(paramInt, 1);
    writeDoubleNoTag(paramDouble);
  }

  public void writeDoubleNoTag(double paramDouble)
    throws IOException
  {
    writeRawLittleEndian64(Double.doubleToRawLongBits(paramDouble));
  }

  public void writeEnum(int paramInt1, int paramInt2)
    throws IOException
  {
    writeTag(paramInt1, 0);
    writeEnumNoTag(paramInt2);
  }

  public void writeEnumNoTag(int paramInt)
    throws IOException
  {
    writeInt32NoTag(paramInt);
  }

  public void writeFixed32(int paramInt1, int paramInt2)
    throws IOException
  {
    writeTag(paramInt1, 5);
    writeFixed32NoTag(paramInt2);
  }

  public void writeFixed32NoTag(int paramInt)
    throws IOException
  {
    writeRawLittleEndian32(paramInt);
  }

  public void writeFixed64(int paramInt, long paramLong)
    throws IOException
  {
    writeTag(paramInt, 1);
    writeFixed64NoTag(paramLong);
  }

  public void writeFixed64NoTag(long paramLong)
    throws IOException
  {
    writeRawLittleEndian64(paramLong);
  }

  public void writeFloat(int paramInt, float paramFloat)
    throws IOException
  {
    writeTag(paramInt, 5);
    writeFloatNoTag(paramFloat);
  }

  public void writeFloatNoTag(float paramFloat)
    throws IOException
  {
    writeRawLittleEndian32(Float.floatToRawIntBits(paramFloat));
  }

  public void writeGroup(int paramInt, MessageLite paramMessageLite)
    throws IOException
  {
    writeTag(paramInt, 3);
    writeGroupNoTag(paramMessageLite);
    writeTag(paramInt, 4);
  }

  public void writeGroupNoTag(MessageLite paramMessageLite)
    throws IOException
  {
    paramMessageLite.writeTo(this);
  }

  public void writeInt32(int paramInt1, int paramInt2)
    throws IOException
  {
    writeTag(paramInt1, 0);
    writeInt32NoTag(paramInt2);
  }

  public void writeInt32NoTag(int paramInt)
    throws IOException
  {
    if (paramInt >= 0)
    {
      writeRawVarint32(paramInt);
      return;
    }
    writeRawVarint64(paramInt);
  }

  public void writeInt64(int paramInt, long paramLong)
    throws IOException
  {
    writeTag(paramInt, 0);
    writeInt64NoTag(paramLong);
  }

  public void writeInt64NoTag(long paramLong)
    throws IOException
  {
    writeRawVarint64(paramLong);
  }

  public void writeMessage(int paramInt, MessageLite paramMessageLite)
    throws IOException
  {
    writeTag(paramInt, 2);
    writeMessageNoTag(paramMessageLite);
  }

  public void writeMessageNoTag(MessageLite paramMessageLite)
    throws IOException
  {
    writeRawVarint32(paramMessageLite.getSerializedSize());
    paramMessageLite.writeTo(this);
  }

  public void writeMessageSetExtension(int paramInt, MessageLite paramMessageLite)
    throws IOException
  {
    writeTag(1, 3);
    writeUInt32(2, paramInt);
    writeMessage(3, paramMessageLite);
    writeTag(1, 4);
  }

  public void writeRawByte(byte paramByte)
    throws IOException
  {
    if (this.position == this.limit)
      refreshBuffer();
    byte[] arrayOfByte = this.buffer;
    int i = this.position;
    this.position = (i + 1);
    arrayOfByte[i] = paramByte;
  }

  public void writeRawByte(int paramInt)
    throws IOException
  {
    writeRawByte((byte)paramInt);
  }

  public void writeRawBytes(ByteString paramByteString)
    throws IOException
  {
    writeRawBytes(paramByteString, 0, paramByteString.size());
  }

  public void writeRawBytes(ByteString paramByteString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.limit - this.position >= paramInt2)
    {
      paramByteString.copyTo(this.buffer, paramInt1, this.position, paramInt2);
      this.position += paramInt2;
      return;
    }
    int j = this.limit - this.position;
    paramByteString.copyTo(this.buffer, paramInt1, this.position, j);
    int i = paramInt1 + j;
    paramInt1 = paramInt2 - j;
    this.position = this.limit;
    refreshBuffer();
    if (paramInt1 <= this.limit)
    {
      paramByteString.copyTo(this.buffer, i, 0, paramInt1);
      this.position = paramInt1;
      return;
    }
    paramByteString = paramByteString.newInput();
    if (i != paramByteString.skip(i))
      throw new IllegalStateException("Skip failed? Should never happen.");
    do
    {
      this.output.write(this.buffer, 0, i);
      paramInt1 -= i;
      if (paramInt1 <= 0)
        break;
      paramInt2 = Math.min(paramInt1, this.limit);
      i = paramByteString.read(this.buffer, 0, paramInt2);
    }
    while (i == paramInt2);
    throw new IllegalStateException("Read failed? Should never happen");
  }

  public void writeRawBytes(byte[] paramArrayOfByte)
    throws IOException
  {
    writeRawBytes(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void writeRawBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.limit - this.position >= paramInt2)
    {
      System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.position, paramInt2);
      this.position += paramInt2;
      return;
    }
    int i = this.limit - this.position;
    System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.position, i);
    paramInt1 += i;
    paramInt2 -= i;
    this.position = this.limit;
    refreshBuffer();
    if (paramInt2 <= this.limit)
    {
      System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, 0, paramInt2);
      this.position = paramInt2;
      return;
    }
    this.output.write(paramArrayOfByte, paramInt1, paramInt2);
  }

  public void writeRawLittleEndian32(int paramInt)
    throws IOException
  {
    writeRawByte(paramInt & 0xFF);
    writeRawByte(paramInt >> 8 & 0xFF);
    writeRawByte(paramInt >> 16 & 0xFF);
    writeRawByte(paramInt >> 24 & 0xFF);
  }

  public void writeRawLittleEndian64(long paramLong)
    throws IOException
  {
    writeRawByte((int)paramLong & 0xFF);
    writeRawByte((int)(paramLong >> 8) & 0xFF);
    writeRawByte((int)(paramLong >> 16) & 0xFF);
    writeRawByte((int)(paramLong >> 24) & 0xFF);
    writeRawByte((int)(paramLong >> 32) & 0xFF);
    writeRawByte((int)(paramLong >> 40) & 0xFF);
    writeRawByte((int)(paramLong >> 48) & 0xFF);
    writeRawByte((int)(paramLong >> 56) & 0xFF);
  }

  public void writeRawMessageSetExtension(int paramInt, ByteString paramByteString)
    throws IOException
  {
    writeTag(1, 3);
    writeUInt32(2, paramInt);
    writeBytes(3, paramByteString);
    writeTag(1, 4);
  }

  public void writeRawVarint32(int paramInt)
    throws IOException
  {
    while (true)
    {
      if ((paramInt & 0xFFFFFF80) == 0)
      {
        writeRawByte(paramInt);
        return;
      }
      writeRawByte(paramInt & 0x7F | 0x80);
      paramInt >>>= 7;
    }
  }

  public void writeRawVarint64(long paramLong)
    throws IOException
  {
    while (true)
    {
      if ((0xFFFFFF80 & paramLong) == 0L)
      {
        writeRawByte((int)paramLong);
        return;
      }
      writeRawByte((int)paramLong & 0x7F | 0x80);
      paramLong >>>= 7;
    }
  }

  public void writeSFixed32(int paramInt1, int paramInt2)
    throws IOException
  {
    writeTag(paramInt1, 5);
    writeSFixed32NoTag(paramInt2);
  }

  public void writeSFixed32NoTag(int paramInt)
    throws IOException
  {
    writeRawLittleEndian32(paramInt);
  }

  public void writeSFixed64(int paramInt, long paramLong)
    throws IOException
  {
    writeTag(paramInt, 1);
    writeSFixed64NoTag(paramLong);
  }

  public void writeSFixed64NoTag(long paramLong)
    throws IOException
  {
    writeRawLittleEndian64(paramLong);
  }

  public void writeSInt32(int paramInt1, int paramInt2)
    throws IOException
  {
    writeTag(paramInt1, 0);
    writeSInt32NoTag(paramInt2);
  }

  public void writeSInt32NoTag(int paramInt)
    throws IOException
  {
    writeRawVarint32(encodeZigZag32(paramInt));
  }

  public void writeSInt64(int paramInt, long paramLong)
    throws IOException
  {
    writeTag(paramInt, 0);
    writeSInt64NoTag(paramLong);
  }

  public void writeSInt64NoTag(long paramLong)
    throws IOException
  {
    writeRawVarint64(encodeZigZag64(paramLong));
  }

  public void writeString(int paramInt, String paramString)
    throws IOException
  {
    writeTag(paramInt, 2);
    writeStringNoTag(paramString);
  }

  public void writeStringNoTag(String paramString)
    throws IOException
  {
    paramString = paramString.getBytes("UTF-8");
    writeRawVarint32(paramString.length);
    writeRawBytes(paramString);
  }

  public void writeTag(int paramInt1, int paramInt2)
    throws IOException
  {
    writeRawVarint32(WireFormat.makeTag(paramInt1, paramInt2));
  }

  public void writeUInt32(int paramInt1, int paramInt2)
    throws IOException
  {
    writeTag(paramInt1, 0);
    writeUInt32NoTag(paramInt2);
  }

  public void writeUInt32NoTag(int paramInt)
    throws IOException
  {
    writeRawVarint32(paramInt);
  }

  public void writeUInt64(int paramInt, long paramLong)
    throws IOException
  {
    writeTag(paramInt, 0);
    writeUInt64NoTag(paramLong);
  }

  public void writeUInt64NoTag(long paramLong)
    throws IOException
  {
    writeRawVarint64(paramLong);
  }

  @Deprecated
  public void writeUnknownGroup(int paramInt, MessageLite paramMessageLite)
    throws IOException
  {
    writeGroup(paramInt, paramMessageLite);
  }

  @Deprecated
  public void writeUnknownGroupNoTag(MessageLite paramMessageLite)
    throws IOException
  {
    writeGroupNoTag(paramMessageLite);
  }

  public static class OutOfSpaceException extends IOException
  {
    private static final long serialVersionUID = -6947486886997889499L;

    OutOfSpaceException()
    {
      super();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.CodedOutputStream
 * JD-Core Version:    0.6.2
 */