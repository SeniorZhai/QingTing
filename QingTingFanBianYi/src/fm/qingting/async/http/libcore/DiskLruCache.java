package fm.qingting.async.http.libcore;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DiskLruCache
  implements Closeable
{
  static final long ANY_SEQUENCE_NUMBER = -1L;
  private static final String CLEAN = "CLEAN";
  private static final String DIRTY = "DIRTY";
  static final String JOURNAL_FILE = "journal";
  static final String JOURNAL_FILE_BKP = "journal.bkp";
  static final String JOURNAL_FILE_TMP = "journal.tmp";
  static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,64}");
  static final String MAGIC = "libcore.io.DiskLruCache";
  private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream()
  {
    public void write(int paramAnonymousInt)
      throws IOException
    {
    }
  };
  private static final String READ = "READ";
  private static final String REMOVE = "REMOVE";
  static final String VERSION_1 = "1";
  private final int appVersion;
  private final Callable<Void> cleanupCallable = new Callable()
  {
    public Void call()
      throws Exception
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.this.journalWriter == null)
          return null;
        DiskLruCache.this.trimToSize();
        if (DiskLruCache.this.journalRebuildRequired())
        {
          DiskLruCache.this.rebuildJournal();
          DiskLruCache.access$402(DiskLruCache.this, 0);
        }
        return null;
      }
    }
  };
  private final File directory;
  final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  private final File journalFile;
  private final File journalFileBkp;
  private final File journalFileTmp;
  private Writer journalWriter;
  private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75F, true);
  private long maxSize;
  private long nextSequenceNumber = 0L;
  private int redundantOpCount;
  private long size = 0L;
  private final int valueCount;

  private DiskLruCache(File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    this.directory = paramFile;
    this.appVersion = paramInt1;
    this.journalFile = new File(paramFile, "journal");
    this.journalFileTmp = new File(paramFile, "journal.tmp");
    this.journalFileBkp = new File(paramFile, "journal.bkp");
    this.valueCount = paramInt2;
    this.maxSize = paramLong;
  }

  private void checkNotClosed()
  {
    if (this.journalWriter == null)
      throw new IllegalStateException("cache is closed");
  }

  private void completeEdit(Editor paramEditor, boolean paramBoolean)
    throws IOException
  {
    int k = 0;
    Entry localEntry;
    try
    {
      localEntry = paramEditor.entry;
      if (localEntry.currentEditor != paramEditor)
        throw new IllegalStateException();
    }
    finally
    {
    }
    int j = k;
    if (paramBoolean)
    {
      j = k;
      if (!localEntry.readable)
      {
        int i = 0;
        while (true)
        {
          j = k;
          if (i >= this.valueCount)
            break;
          if (paramEditor.written[i] == 0)
          {
            paramEditor.abort();
            throw new IllegalStateException("Newly created entry didn't create value for index " + i);
          }
          if (!localEntry.getDirtyFile(i).exists())
          {
            paramEditor.abort();
            return;
          }
          i += 1;
        }
      }
    }
    while (true)
    {
      long l1;
      if (j < this.valueCount)
      {
        paramEditor = localEntry.getDirtyFile(j);
        if (paramBoolean)
        {
          if (paramEditor.exists())
          {
            File localFile = localEntry.getCleanFile(j);
            paramEditor.renameTo(localFile);
            l1 = localEntry.lengths[j];
            long l2 = localFile.length();
            localEntry.lengths[j] = l2;
            this.size = (this.size - l1 + l2);
          }
        }
        else
          deleteIfExists(paramEditor);
      }
      else
      {
        this.redundantOpCount += 1;
        Entry.access$702(localEntry, null);
        if ((localEntry.readable | paramBoolean))
        {
          Entry.access$602(localEntry, true);
          this.journalWriter.write("CLEAN " + localEntry.key + localEntry.getLengths() + '\n');
          if (paramBoolean)
          {
            l1 = this.nextSequenceNumber;
            this.nextSequenceNumber = (1L + l1);
            Entry.access$1202(localEntry, l1);
          }
        }
        while (true)
        {
          if ((this.size <= this.maxSize) && (!journalRebuildRequired()))
            break label407;
          this.executorService.submit(this.cleanupCallable);
          break;
          this.lruEntries.remove(localEntry.key);
          this.journalWriter.write("REMOVE " + localEntry.key + '\n');
        }
        label407: break;
      }
      j += 1;
    }
  }

  private static void deleteIfExists(File paramFile)
    throws IOException
  {
    if ((paramFile.exists()) && (!paramFile.delete()))
      throw new IOException();
  }

  private Editor edit(String paramString, long paramLong)
    throws IOException
  {
    while (true)
    {
      Entry localEntry;
      try
      {
        checkNotClosed();
        validateKey(paramString);
        localEntry = (Entry)this.lruEntries.get(paramString);
        if (paramLong != -1L)
          if (localEntry != null)
          {
            long l = localEntry.sequenceNumber;
            if (l == paramLong);
          }
          else
          {
            paramString = null;
            return paramString;
          }
        if (localEntry == null)
        {
          localEntry = new Entry(paramString, null);
          this.lruEntries.put(paramString, localEntry);
          localEditor = new Editor(localEntry, null);
          Entry.access$702(localEntry, localEditor);
          this.journalWriter.write("DIRTY " + paramString + '\n');
          this.journalWriter.flush();
          paramString = localEditor;
          continue;
        }
      }
      finally
      {
      }
      Editor localEditor = localEntry.currentEditor;
      if (localEditor != null)
        paramString = null;
    }
  }

  private static String inputStreamToString(InputStream paramInputStream)
    throws IOException
  {
    return Streams.readFully(new InputStreamReader(paramInputStream, Charsets.UTF_8));
  }

  private boolean journalRebuildRequired()
  {
    return (this.redundantOpCount >= 2000) && (this.redundantOpCount >= this.lruEntries.size());
  }

  public static DiskLruCache open(File paramFile, int paramInt1, int paramInt2, long paramLong)
    throws IOException
  {
    if (paramLong <= 0L)
      throw new IllegalArgumentException("maxSize <= 0");
    if (paramInt2 <= 0)
      throw new IllegalArgumentException("valueCount <= 0");
    Object localObject = new File(paramFile, "journal.bkp");
    File localFile;
    if (((File)localObject).exists())
    {
      localFile = new File(paramFile, "journal");
      if (!localFile.exists())
        break label150;
      ((File)localObject).delete();
    }
    while (true)
    {
      localObject = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong);
      if (((DiskLruCache)localObject).journalFile.exists())
        try
        {
          ((DiskLruCache)localObject).readJournal();
          ((DiskLruCache)localObject).processJournal();
          ((DiskLruCache)localObject).journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(((DiskLruCache)localObject).journalFile, true), Charsets.US_ASCII));
          return localObject;
          label150: renameTo((File)localObject, localFile, false);
        }
        catch (IOException localIOException)
        {
          System.out.println("DiskLruCache " + paramFile + " is corrupt: " + localIOException.getMessage() + ", removing");
          ((DiskLruCache)localObject).delete();
        }
    }
    paramFile.mkdirs();
    paramFile = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong);
    paramFile.rebuildJournal();
    return paramFile;
  }

  private void processJournal()
    throws IOException
  {
    deleteIfExists(this.journalFileTmp);
    Iterator localIterator = this.lruEntries.values().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      int i;
      if (localEntry.currentEditor == null)
      {
        i = 0;
        while (i < this.valueCount)
        {
          this.size += localEntry.lengths[i];
          i += 1;
        }
      }
      else
      {
        Entry.access$702(localEntry, null);
        i = 0;
        while (i < this.valueCount)
        {
          deleteIfExists(localEntry.getCleanFile(i));
          deleteIfExists(localEntry.getDirtyFile(i));
          i += 1;
        }
        localIterator.remove();
      }
    }
  }

  // ERROR //
  private void readJournal()
    throws IOException
  {
    // Byte code:
    //   0: new 449	fm/qingting/async/http/libcore/StrictLineReader
    //   3: dup
    //   4: new 451	java/io/FileInputStream
    //   7: dup
    //   8: aload_0
    //   9: getfield 142	fm/qingting/async/http/libcore/DiskLruCache:journalFile	Ljava/io/File;
    //   12: invokespecial 453	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   15: getstatic 387	fm/qingting/async/http/libcore/Charsets:US_ASCII	Ljava/nio/charset/Charset;
    //   18: invokespecial 454	fm/qingting/async/http/libcore/StrictLineReader:<init>	(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
    //   21: astore_1
    //   22: aload_1
    //   23: invokevirtual 457	fm/qingting/async/http/libcore/StrictLineReader:readLine	()Ljava/lang/String;
    //   26: astore_2
    //   27: aload_1
    //   28: invokevirtual 457	fm/qingting/async/http/libcore/StrictLineReader:readLine	()Ljava/lang/String;
    //   31: astore_3
    //   32: aload_1
    //   33: invokevirtual 457	fm/qingting/async/http/libcore/StrictLineReader:readLine	()Ljava/lang/String;
    //   36: astore 4
    //   38: aload_1
    //   39: invokevirtual 457	fm/qingting/async/http/libcore/StrictLineReader:readLine	()Ljava/lang/String;
    //   42: astore 5
    //   44: aload_1
    //   45: invokevirtual 457	fm/qingting/async/http/libcore/StrictLineReader:readLine	()Ljava/lang/String;
    //   48: astore 6
    //   50: ldc 45
    //   52: aload_2
    //   53: invokevirtual 463	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   56: ifeq +53 -> 109
    //   59: ldc 54
    //   61: aload_3
    //   62: invokevirtual 463	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   65: ifeq +44 -> 109
    //   68: aload_0
    //   69: getfield 135	fm/qingting/async/http/libcore/DiskLruCache:appVersion	I
    //   72: invokestatic 468	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   75: aload 4
    //   77: invokevirtual 463	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   80: ifeq +29 -> 109
    //   83: aload_0
    //   84: getfield 148	fm/qingting/async/http/libcore/DiskLruCache:valueCount	I
    //   87: invokestatic 468	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   90: aload 5
    //   92: invokevirtual 463	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   95: ifeq +14 -> 109
    //   98: ldc_w 470
    //   101: aload 6
    //   103: invokevirtual 463	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   106: ifne +76 -> 182
    //   109: new 157	java/io/IOException
    //   112: dup
    //   113: new 228	java/lang/StringBuilder
    //   116: dup
    //   117: invokespecial 229	java/lang/StringBuilder:<init>	()V
    //   120: ldc_w 472
    //   123: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: aload_2
    //   127: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: ldc_w 474
    //   133: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: aload_3
    //   137: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: ldc_w 474
    //   143: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: aload 5
    //   148: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: ldc_w 474
    //   154: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: aload 6
    //   159: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: ldc_w 476
    //   165: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   171: invokespecial 477	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   174: athrow
    //   175: astore_2
    //   176: aload_1
    //   177: invokestatic 483	fm/qingting/async/http/libcore/IoUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   180: aload_2
    //   181: athrow
    //   182: iconst_0
    //   183: istore 7
    //   185: aload_0
    //   186: aload_1
    //   187: invokevirtual 457	fm/qingting/async/http/libcore/StrictLineReader:readLine	()Ljava/lang/String;
    //   190: invokespecial 486	fm/qingting/async/http/libcore/DiskLruCache:readJournalLine	(Ljava/lang/String;)V
    //   193: iload 7
    //   195: iconst_1
    //   196: iadd
    //   197: istore 7
    //   199: goto -14 -> 185
    //   202: astore_2
    //   203: aload_0
    //   204: iload 7
    //   206: aload_0
    //   207: getfield 106	fm/qingting/async/http/libcore/DiskLruCache:lruEntries	Ljava/util/LinkedHashMap;
    //   210: invokevirtual 358	java/util/LinkedHashMap:size	()I
    //   213: isub
    //   214: putfield 198	fm/qingting/async/http/libcore/DiskLruCache:redundantOpCount	I
    //   217: aload_1
    //   218: invokestatic 483	fm/qingting/async/http/libcore/IoUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   221: return
    //
    // Exception table:
    //   from	to	target	type
    //   22	109	175	finally
    //   109	175	175	finally
    //   185	193	175	finally
    //   203	217	175	finally
    //   185	193	202	java/io/EOFException
  }

  private void readJournalLine(String paramString)
    throws IOException
  {
    int i = paramString.indexOf(' ');
    if (i == -1)
      throw new IOException("unexpected journal line: " + paramString);
    int j = i + 1;
    int k = paramString.indexOf(' ', j);
    String str;
    if (k == -1)
    {
      str = paramString.substring(j);
      if ((i == "REMOVE".length()) && (paramString.startsWith("REMOVE")))
        this.lruEntries.remove(str);
    }
    else
    {
      str = paramString.substring(j, k);
    }
    while (true)
    {
      Entry localEntry2 = (Entry)this.lruEntries.get(str);
      Entry localEntry1 = localEntry2;
      if (localEntry2 == null)
      {
        localEntry1 = new Entry(str, null);
        this.lruEntries.put(str, localEntry1);
      }
      if ((k != -1) && (i == "CLEAN".length()) && (paramString.startsWith("CLEAN")))
      {
        paramString = paramString.substring(k + 1).split(" ");
        Entry.access$602(localEntry1, true);
        Entry.access$702(localEntry1, null);
        localEntry1.setLengths(paramString);
        return;
      }
      if ((k == -1) && (i == "DIRTY".length()) && (paramString.startsWith("DIRTY")))
      {
        Entry.access$702(localEntry1, new Editor(localEntry1, null));
        return;
      }
      if ((k == -1) && (i == "READ".length()) && (paramString.startsWith("READ")))
        break;
      throw new IOException("unexpected journal line: " + paramString);
    }
  }

  private void rebuildJournal()
    throws IOException
  {
    while (true)
    {
      Entry localEntry;
      try
      {
        if (this.journalWriter != null)
          this.journalWriter.close();
        BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFileTmp), Charsets.US_ASCII));
        try
        {
          localBufferedWriter.write("libcore.io.DiskLruCache");
          localBufferedWriter.write("\n");
          localBufferedWriter.write("1");
          localBufferedWriter.write("\n");
          localBufferedWriter.write(Integer.toString(this.appVersion));
          localBufferedWriter.write("\n");
          localBufferedWriter.write(Integer.toString(this.valueCount));
          localBufferedWriter.write("\n");
          localBufferedWriter.write("\n");
          Iterator localIterator = this.lruEntries.values().iterator();
          if (!localIterator.hasNext())
            break;
          localEntry = (Entry)localIterator.next();
          if (localEntry.currentEditor != null)
          {
            localBufferedWriter.write("DIRTY " + localEntry.key + '\n');
            continue;
          }
        }
        finally
        {
          localBufferedWriter.close();
        }
      }
      finally
      {
      }
      localObject1.write("CLEAN " + localEntry.key + localEntry.getLengths() + '\n');
    }
    localObject1.close();
    if (this.journalFile.exists())
      renameTo(this.journalFile, this.journalFileBkp, true);
    renameTo(this.journalFileTmp, this.journalFile, false);
    this.journalFileBkp.delete();
    this.journalWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.journalFile, true), Charsets.US_ASCII));
  }

  private static void renameTo(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
      deleteIfExists(paramFile2);
    if (!paramFile1.renameTo(paramFile2))
      throw new IOException();
  }

  private void trimToSize()
    throws IOException
  {
    while (this.size > this.maxSize)
      remove((String)((Map.Entry)this.lruEntries.entrySet().iterator().next()).getKey());
  }

  private void validateKey(String paramString)
  {
    if (!LEGAL_KEY_PATTERN.matcher(paramString).matches())
      throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + paramString + "\"");
  }

  public void close()
    throws IOException
  {
    while (true)
    {
      try
      {
        Object localObject1 = this.journalWriter;
        if (localObject1 == null)
          return;
        localObject1 = new ArrayList(this.lruEntries.values()).iterator();
        if (((Iterator)localObject1).hasNext())
        {
          Entry localEntry = (Entry)((Iterator)localObject1).next();
          if (localEntry.currentEditor == null)
            continue;
          localEntry.currentEditor.abort();
          continue;
        }
      }
      finally
      {
      }
      trimToSize();
      this.journalWriter.close();
      this.journalWriter = null;
    }
  }

  public void delete()
    throws IOException
  {
    close();
    IoUtils.deleteContents(this.directory);
  }

  public Editor edit(String paramString)
    throws IOException
  {
    return edit(paramString, -1L);
  }

  public void flush()
    throws IOException
  {
    try
    {
      checkNotClosed();
      trimToSize();
      this.journalWriter.flush();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Snapshot get(String paramString)
    throws IOException
  {
    while (true)
    {
      try
      {
        checkNotClosed();
        validateKey(paramString);
        Entry localEntry = (Entry)this.lruEntries.get(paramString);
        if (localEntry == null)
        {
          paramString = null;
          return paramString;
        }
        if (!localEntry.readable)
        {
          paramString = null;
          continue;
        }
        InputStream[] arrayOfInputStream = new InputStream[this.valueCount];
        int i = 0;
        try
        {
          if (i >= this.valueCount)
            continue;
          arrayOfInputStream[i] = new FileInputStream(localEntry.getCleanFile(i));
          i += 1;
          continue;
        }
        catch (FileNotFoundException paramString)
        {
          i = 0;
          if (i >= this.valueCount)
            break label217;
        }
        if (arrayOfInputStream[i] != null)
        {
          IoUtils.closeQuietly(arrayOfInputStream[i]);
          i += 1;
          continue;
          this.redundantOpCount += 1;
          this.journalWriter.append("READ " + paramString + '\n');
          if (journalRebuildRequired())
            this.executorService.submit(this.cleanupCallable);
          paramString = new Snapshot(paramString, localEntry.sequenceNumber, arrayOfInputStream, localEntry.lengths, null);
          continue;
        }
      }
      finally
      {
      }
      label217: paramString = null;
    }
  }

  public File getDirectory()
  {
    return this.directory;
  }

  public long getMaxSize()
  {
    return this.maxSize;
  }

  public boolean isClosed()
  {
    return this.journalWriter == null;
  }

  public boolean remove(String paramString)
    throws IOException
  {
    int i = 0;
    while (true)
    {
      try
      {
        checkNotClosed();
        validateKey(paramString);
        Entry localEntry = (Entry)this.lruEntries.get(paramString);
        Object localObject;
        if (localEntry != null)
        {
          localObject = localEntry.currentEditor;
          if (localObject == null);
        }
        else
        {
          bool = false;
          return bool;
          this.size -= localEntry.lengths[i];
          localEntry.lengths[i] = 0L;
          i += 1;
        }
        if (i < this.valueCount)
        {
          localObject = localEntry.getCleanFile(i);
          if (((File)localObject).delete())
            continue;
          throw new IOException("failed to delete " + localObject);
        }
      }
      finally
      {
      }
      this.redundantOpCount += 1;
      this.journalWriter.append("REMOVE " + paramString + '\n');
      this.lruEntries.remove(paramString);
      if (journalRebuildRequired())
        this.executorService.submit(this.cleanupCallable);
      boolean bool = true;
    }
  }

  public void setMaxSize(long paramLong)
  {
    try
    {
      this.maxSize = paramLong;
      this.executorService.submit(this.cleanupCallable);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long size()
  {
    try
    {
      long l = this.size;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final class Editor
  {
    private boolean committed;
    private final DiskLruCache.Entry entry;
    private boolean hasErrors;
    private final boolean[] written;

    private Editor(DiskLruCache.Entry arg2)
    {
      DiskLruCache.Entry localEntry;
      this.entry = localEntry;
      if (DiskLruCache.Entry.access$600(localEntry));
      for (this$1 = null; ; this$1 = new boolean[DiskLruCache.this.valueCount])
      {
        this.written = DiskLruCache.this;
        return;
      }
    }

    public void abort()
      throws IOException
    {
      DiskLruCache.this.completeEdit(this, false);
    }

    public void abortUnlessCommitted()
    {
      if (!this.committed);
      try
      {
        abort();
        return;
      }
      catch (IOException localIOException)
      {
      }
    }

    public void commit()
      throws IOException
    {
      if (this.hasErrors)
      {
        DiskLruCache.this.completeEdit(this, false);
        DiskLruCache.this.remove(DiskLruCache.Entry.access$1100(this.entry));
      }
      while (true)
      {
        this.committed = true;
        return;
        DiskLruCache.this.completeEdit(this, true);
      }
    }

    public String getString(int paramInt)
      throws IOException
    {
      InputStream localInputStream = newInputStream(paramInt);
      if (localInputStream != null)
        return DiskLruCache.inputStreamToString(localInputStream);
      return null;
    }

    public InputStream newInputStream(int paramInt)
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.Entry.access$700(this.entry) != this)
          throw new IllegalStateException();
      }
      if (!DiskLruCache.Entry.access$600(this.entry))
        return null;
      try
      {
        FileInputStream localFileInputStream = new FileInputStream(this.entry.getCleanFile(paramInt));
        return localFileInputStream;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
      }
      return null;
    }

    public OutputStream newOutputStream(int paramInt)
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.Entry.access$700(this.entry) != this)
          throw new IllegalStateException();
      }
      if (!DiskLruCache.Entry.access$600(this.entry))
        this.written[paramInt] = true;
      File localFile = this.entry.getDirtyFile(paramInt);
      try
      {
        Object localObject2 = new FileOutputStream(localFile);
        localObject2 = new FaultHidingOutputStream((OutputStream)localObject2, null);
        return localObject2;
      }
      catch (FileNotFoundException localFileNotFoundException1)
      {
        while (true)
        {
          DiskLruCache.this.directory.mkdirs();
          try
          {
            FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
          }
          catch (FileNotFoundException localFileNotFoundException2)
          {
            OutputStream localOutputStream = DiskLruCache.NULL_OUTPUT_STREAM;
            return localOutputStream;
          }
        }
      }
    }

    // ERROR //
    public void set(int paramInt, String paramString)
      throws IOException
    {
      // Byte code:
      //   0: new 131	java/io/OutputStreamWriter
      //   3: dup
      //   4: aload_0
      //   5: iload_1
      //   6: invokevirtual 133	fm/qingting/async/http/libcore/DiskLruCache$Editor:newOutputStream	(I)Ljava/io/OutputStream;
      //   9: getstatic 139	fm/qingting/async/http/libcore/Charsets:UTF_8	Ljava/nio/charset/Charset;
      //   12: invokespecial 142	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/nio/charset/Charset;)V
      //   15: astore_3
      //   16: aload_3
      //   17: aload_2
      //   18: invokevirtual 148	java/io/Writer:write	(Ljava/lang/String;)V
      //   21: aload_3
      //   22: invokestatic 154	fm/qingting/async/http/libcore/IoUtils:closeQuietly	(Ljava/io/Closeable;)V
      //   25: return
      //   26: astore_2
      //   27: aconst_null
      //   28: astore_3
      //   29: aload_3
      //   30: invokestatic 154	fm/qingting/async/http/libcore/IoUtils:closeQuietly	(Ljava/io/Closeable;)V
      //   33: aload_2
      //   34: athrow
      //   35: astore_2
      //   36: goto -7 -> 29
      //
      // Exception table:
      //   from	to	target	type
      //   0	16	26	finally
      //   16	21	35	finally
    }

    private class FaultHidingOutputStream extends FilterOutputStream
    {
      private FaultHidingOutputStream(OutputStream arg2)
      {
        super();
      }

      public void close()
      {
        try
        {
          this.out.close();
          return;
        }
        catch (IOException localIOException)
        {
          DiskLruCache.Editor.access$2302(DiskLruCache.Editor.this, true);
        }
      }

      public void flush()
      {
        try
        {
          this.out.flush();
          return;
        }
        catch (IOException localIOException)
        {
          DiskLruCache.Editor.access$2302(DiskLruCache.Editor.this, true);
        }
      }

      public void write(int paramInt)
      {
        try
        {
          this.out.write(paramInt);
          return;
        }
        catch (IOException localIOException)
        {
          DiskLruCache.Editor.access$2302(DiskLruCache.Editor.this, true);
        }
      }

      public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      {
        try
        {
          this.out.write(paramArrayOfByte, paramInt1, paramInt2);
          return;
        }
        catch (IOException paramArrayOfByte)
        {
          DiskLruCache.Editor.access$2302(DiskLruCache.Editor.this, true);
        }
      }
    }
  }

  private final class Entry
  {
    private DiskLruCache.Editor currentEditor;
    private final String key;
    private final long[] lengths;
    private boolean readable;
    private long sequenceNumber;

    private Entry(String arg2)
    {
      Object localObject;
      this.key = localObject;
      this.lengths = new long[DiskLruCache.this.valueCount];
    }

    private IOException invalidLengths(String[] paramArrayOfString)
      throws IOException
    {
      throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
    }

    private void setLengths(String[] paramArrayOfString)
      throws IOException
    {
      if (paramArrayOfString.length != DiskLruCache.this.valueCount)
        throw invalidLengths(paramArrayOfString);
      int i = 0;
      try
      {
        while (i < paramArrayOfString.length)
        {
          this.lengths[i] = Long.parseLong(paramArrayOfString[i]);
          i += 1;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw invalidLengths(paramArrayOfString);
      }
    }

    public File getCleanFile(int paramInt)
    {
      return new File(DiskLruCache.this.directory, this.key + "." + paramInt);
    }

    public File getDirtyFile(int paramInt)
    {
      return new File(DiskLruCache.this.directory, this.key + "." + paramInt + ".tmp");
    }

    public String getLengths()
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      long[] arrayOfLong = this.lengths;
      int j = arrayOfLong.length;
      int i = 0;
      while (i < j)
      {
        long l = arrayOfLong[i];
        localStringBuilder.append(' ').append(l);
        i += 1;
      }
      return localStringBuilder.toString();
    }
  }

  public final class Snapshot
    implements Closeable
  {
    private final InputStream[] ins;
    private final String key;
    private final long[] lengths;
    private final long sequenceNumber;

    private Snapshot(String paramLong, long arg3, InputStream[] paramArrayOfLong, long[] arg6)
    {
      this.key = paramLong;
      this.sequenceNumber = ???;
      this.ins = paramArrayOfLong;
      Object localObject;
      this.lengths = localObject;
    }

    public void close()
    {
      InputStream[] arrayOfInputStream = this.ins;
      int j = arrayOfInputStream.length;
      int i = 0;
      while (i < j)
      {
        IoUtils.closeQuietly(arrayOfInputStream[i]);
        i += 1;
      }
    }

    public DiskLruCache.Editor edit()
      throws IOException
    {
      return DiskLruCache.this.edit(this.key, this.sequenceNumber);
    }

    public InputStream getInputStream(int paramInt)
    {
      return this.ins[paramInt];
    }

    public long getLength(int paramInt)
    {
      return this.lengths[paramInt];
    }

    public String getString(int paramInt)
      throws IOException
    {
      return DiskLruCache.inputStreamToString(getInputStream(paramInt));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.libcore.DiskLruCache
 * JD-Core Version:    0.6.2
 */