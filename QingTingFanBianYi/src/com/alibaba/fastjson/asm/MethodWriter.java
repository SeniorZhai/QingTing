package com.alibaba.fastjson.asm;

class MethodWriter
  implements MethodVisitor
{
  static final int ACC_CONSTRUCTOR = 262144;
  static final int APPEND_FRAME = 252;
  static final int CHOP_FRAME = 248;
  static final int FULL_FRAME = 255;
  static final int RESERVED = 128;
  static final int SAME_FRAME = 0;
  static final int SAME_FRAME_EXTENDED = 251;
  static final int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
  static final int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
  private int access;
  int classReaderLength;
  private ByteVector code = new ByteVector();
  final ClassWriter cw;
  private final int desc;
  int exceptionCount;
  int[] exceptions;
  private int maxLocals;
  private int maxStack;
  private final int name;
  MethodWriter next;

  MethodWriter(ClassWriter paramClassWriter, int paramInt, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString)
  {
    if (paramClassWriter.firstMethod == null)
      paramClassWriter.firstMethod = this;
    while (true)
    {
      paramClassWriter.lastMethod = this;
      this.cw = paramClassWriter;
      this.access = paramInt;
      this.name = paramClassWriter.newUTF8(paramString1);
      this.desc = paramClassWriter.newUTF8(paramString2);
      if ((paramArrayOfString == null) || (paramArrayOfString.length <= 0))
        break;
      this.exceptionCount = paramArrayOfString.length;
      this.exceptions = new int[this.exceptionCount];
      paramInt = 0;
      while (paramInt < this.exceptionCount)
      {
        this.exceptions[paramInt] = paramClassWriter.newClass(paramArrayOfString[paramInt]);
        paramInt += 1;
      }
      paramClassWriter.lastMethod.next = this;
    }
  }

  final int getSize()
  {
    int i = 8;
    if (this.code.length > 0)
    {
      this.cw.newUTF8("Code");
      i = 8 + (this.code.length + 18 + 0);
    }
    int j = i;
    if (this.exceptionCount > 0)
    {
      this.cw.newUTF8("Exceptions");
      j = i + (this.exceptionCount * 2 + 8);
    }
    return j;
  }

  final void put(ByteVector paramByteVector)
  {
    int i = (this.access & 0x40000) / 64;
    paramByteVector.putShort(this.access & ((0x60000 | i) ^ 0xFFFFFFFF)).putShort(this.name).putShort(this.desc);
    i = 0;
    if (this.code.length > 0)
      i = 0 + 1;
    int j = i;
    if (this.exceptionCount > 0)
      j = i + 1;
    paramByteVector.putShort(j);
    if (this.code.length > 0)
    {
      i = this.code.length;
      paramByteVector.putShort(this.cw.newUTF8("Code")).putInt(i + 12 + 0);
      paramByteVector.putShort(this.maxStack).putShort(this.maxLocals);
      paramByteVector.putInt(this.code.length).putByteArray(this.code.data, 0, this.code.length);
      paramByteVector.putShort(0);
      paramByteVector.putShort(0);
    }
    if (this.exceptionCount > 0)
    {
      paramByteVector.putShort(this.cw.newUTF8("Exceptions")).putInt(this.exceptionCount * 2 + 2);
      paramByteVector.putShort(this.exceptionCount);
      i = 0;
      while (i < this.exceptionCount)
      {
        paramByteVector.putShort(this.exceptions[i]);
        i += 1;
      }
    }
  }

  public void visitEnd()
  {
  }

  public void visitFieldInsn(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    paramString1 = this.cw.newFieldItem(paramString1, paramString2, paramString3);
    this.code.put12(paramInt, paramString1.index);
  }

  public void visitIincInsn(int paramInt1, int paramInt2)
  {
    this.code.putByte(132).put11(paramInt1, paramInt2);
  }

  public void visitInsn(int paramInt)
  {
    this.code.putByte(paramInt);
  }

  public void visitIntInsn(int paramInt1, int paramInt2)
  {
    this.code.put11(paramInt1, paramInt2);
  }

  public void visitJumpInsn(int paramInt, Label paramLabel)
  {
    if (((paramLabel.status & 0x2) != 0) && (paramLabel.position - this.code.length < -32768))
      throw new UnsupportedOperationException();
    this.code.putByte(paramInt);
    paramLabel.put(this, this.code, this.code.length - 1);
  }

  public void visitLabel(Label paramLabel)
  {
    paramLabel.resolve(this, this.code.length, this.code.data);
  }

  public void visitLdcInsn(Object paramObject)
  {
    paramObject = this.cw.newConstItem(paramObject);
    int i = paramObject.index;
    if ((paramObject.type == 5) || (paramObject.type == 6))
    {
      this.code.put12(20, i);
      return;
    }
    if (i >= 256)
    {
      this.code.put12(19, i);
      return;
    }
    this.code.put11(18, i);
  }

  public void visitMaxs(int paramInt1, int paramInt2)
  {
    this.maxStack = paramInt1;
    this.maxLocals = paramInt2;
  }

  public void visitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    if (paramInt == 185);
    for (boolean bool = true; ; bool = false)
    {
      paramString1 = this.cw.newMethodItem(paramString1, paramString2, paramString3, bool);
      int i = paramString1.intVal;
      if (!bool)
        break;
      paramInt = i;
      if (i == 0)
      {
        paramInt = Type.getArgumentsAndReturnSizes(paramString3);
        paramString1.intVal = paramInt;
      }
      this.code.put12(185, paramString1.index).put11(paramInt >> 2, 0);
      return;
    }
    this.code.put12(paramInt, paramString1.index);
  }

  public void visitTypeInsn(int paramInt, String paramString)
  {
    paramString = this.cw.newClassItem(paramString);
    this.code.put12(paramInt, paramString.index);
  }

  public void visitVarInsn(int paramInt1, int paramInt2)
  {
    if ((paramInt2 < 4) && (paramInt1 != 169))
    {
      if (paramInt1 < 54);
      for (paramInt1 = (paramInt1 - 21 << 2) + 26 + paramInt2; ; paramInt1 = (paramInt1 - 54 << 2) + 59 + paramInt2)
      {
        this.code.putByte(paramInt1);
        return;
      }
    }
    if (paramInt2 >= 256)
    {
      this.code.putByte(196).put12(paramInt1, paramInt2);
      return;
    }
    this.code.put11(paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.asm.MethodWriter
 * JD-Core Version:    0.6.2
 */