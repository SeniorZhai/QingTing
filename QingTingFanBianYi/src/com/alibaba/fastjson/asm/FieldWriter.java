package com.alibaba.fastjson.asm;

final class FieldWriter
  implements FieldVisitor
{
  private final int access;
  private final int desc;
  private final int name;
  FieldWriter next;

  FieldWriter(ClassWriter paramClassWriter, int paramInt, String paramString1, String paramString2)
  {
    if (paramClassWriter.firstField == null)
      paramClassWriter.firstField = this;
    while (true)
    {
      paramClassWriter.lastField = this;
      this.access = paramInt;
      this.name = paramClassWriter.newUTF8(paramString1);
      this.desc = paramClassWriter.newUTF8(paramString2);
      return;
      paramClassWriter.lastField.next = this;
    }
  }

  int getSize()
  {
    return 8;
  }

  void put(ByteVector paramByteVector)
  {
    int i = (this.access & 0x40000) / 64;
    paramByteVector.putShort(this.access & ((0x60000 | i) ^ 0xFFFFFFFF)).putShort(this.name).putShort(this.desc);
    paramByteVector.putShort(0);
  }

  public void visitEnd()
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.asm.FieldWriter
 * JD-Core Version:    0.6.2
 */