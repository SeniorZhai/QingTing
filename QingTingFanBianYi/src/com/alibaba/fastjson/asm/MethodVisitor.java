package com.alibaba.fastjson.asm;

public abstract interface MethodVisitor
{
  public abstract void visitEnd();

  public abstract void visitFieldInsn(int paramInt, String paramString1, String paramString2, String paramString3);

  public abstract void visitIincInsn(int paramInt1, int paramInt2);

  public abstract void visitInsn(int paramInt);

  public abstract void visitIntInsn(int paramInt1, int paramInt2);

  public abstract void visitJumpInsn(int paramInt, Label paramLabel);

  public abstract void visitLabel(Label paramLabel);

  public abstract void visitLdcInsn(Object paramObject);

  public abstract void visitMaxs(int paramInt1, int paramInt2);

  public abstract void visitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3);

  public abstract void visitTypeInsn(int paramInt, String paramString);

  public abstract void visitVarInsn(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.asm.MethodVisitor
 * JD-Core Version:    0.6.2
 */