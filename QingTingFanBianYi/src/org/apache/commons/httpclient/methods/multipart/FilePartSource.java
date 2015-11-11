package org.apache.commons.httpclient.methods.multipart;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FilePartSource
  implements PartSource
{
  private File file = null;
  private String fileName = null;

  public FilePartSource(File paramFile)
    throws FileNotFoundException
  {
    this.file = paramFile;
    if (paramFile != null)
    {
      if (!paramFile.isFile())
        throw new FileNotFoundException("File is not a normal file.");
      if (!paramFile.canRead())
        throw new FileNotFoundException("File is not readable.");
      this.fileName = paramFile.getName();
    }
  }

  public FilePartSource(String paramString, File paramFile)
    throws FileNotFoundException
  {
    this(paramFile);
    if (paramString != null)
      this.fileName = paramString;
  }

  public InputStream createInputStream()
    throws IOException
  {
    if (this.file != null)
      return new FileInputStream(this.file);
    return new ByteArrayInputStream(new byte[0]);
  }

  public String getFileName()
  {
    if (this.fileName == null)
      return "noname";
    return this.fileName;
  }

  public long getLength()
  {
    if (this.file != null)
      return this.file.length();
    return 0L;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.multipart.FilePartSource
 * JD-Core Version:    0.6.2
 */