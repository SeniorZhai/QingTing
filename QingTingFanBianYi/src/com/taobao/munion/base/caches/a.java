package com.taobao.munion.base.caches;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class a extends FileInputStream
{
  private String a;

  public a(File paramFile)
    throws FileNotFoundException
  {
    super(paramFile);
    this.a = paramFile.getName();
  }

  public a(FileDescriptor paramFileDescriptor)
  {
    super(paramFileDescriptor);
  }

  public void close()
  {
    try
    {
      super.close();
      label4: this.a = null;
      return;
    }
    catch (IOException localIOException)
    {
      break label4;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.a
 * JD-Core Version:    0.6.2
 */