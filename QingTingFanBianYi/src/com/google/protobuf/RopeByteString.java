package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

class RopeByteString extends ByteString
{
  private static final int[] minLengthByDepth;
  private int hash = 0;
  private final ByteString left;
  private final int leftLength;
  private final ByteString right;
  private final int totalLength;
  private final int treeDepth;

  static
  {
    ArrayList localArrayList = new ArrayList();
    int j = 1;
    int k;
    for (int i = 1; ; i = k + i)
    {
      k = j;
      if (i <= 0)
        break;
      localArrayList.add(Integer.valueOf(i));
      j = i;
    }
    localArrayList.add(Integer.valueOf(2147483647));
    minLengthByDepth = new int[localArrayList.size()];
    i = 0;
    while (i < minLengthByDepth.length)
    {
      minLengthByDepth[i] = ((Integer)localArrayList.get(i)).intValue();
      i += 1;
    }
  }

  private RopeByteString(ByteString paramByteString1, ByteString paramByteString2)
  {
    this.left = paramByteString1;
    this.right = paramByteString2;
    this.leftLength = paramByteString1.size();
    this.totalLength = (this.leftLength + paramByteString2.size());
    this.treeDepth = (Math.max(paramByteString1.getTreeDepth(), paramByteString2.getTreeDepth()) + 1);
  }

  static ByteString concatenate(ByteString paramByteString1, ByteString paramByteString2)
  {
    if ((paramByteString1 instanceof RopeByteString));
    for (RopeByteString localRopeByteString = (RopeByteString)paramByteString1; paramByteString2.size() == 0; localRopeByteString = null)
      return paramByteString1;
    if (paramByteString1.size() == 0)
      return paramByteString2;
    int i = paramByteString1.size() + paramByteString2.size();
    if (i < 128)
      return concatenateBytes(paramByteString1, paramByteString2);
    if ((localRopeByteString != null) && (localRopeByteString.right.size() + paramByteString2.size() < 128))
    {
      paramByteString1 = concatenateBytes(localRopeByteString.right, paramByteString2);
      return new RopeByteString(localRopeByteString.left, paramByteString1);
    }
    if ((localRopeByteString != null) && (localRopeByteString.left.getTreeDepth() > localRopeByteString.right.getTreeDepth()) && (localRopeByteString.getTreeDepth() > paramByteString2.getTreeDepth()))
    {
      paramByteString1 = new RopeByteString(localRopeByteString.right, paramByteString2);
      return new RopeByteString(localRopeByteString.left, paramByteString1);
    }
    int j = Math.max(paramByteString1.getTreeDepth(), paramByteString2.getTreeDepth());
    if (i >= minLengthByDepth[(j + 1)])
      return new RopeByteString(paramByteString1, paramByteString2);
    return new Balancer(null).balance(paramByteString1, paramByteString2);
  }

  private static LiteralByteString concatenateBytes(ByteString paramByteString1, ByteString paramByteString2)
  {
    int i = paramByteString1.size();
    int j = paramByteString2.size();
    byte[] arrayOfByte = new byte[i + j];
    paramByteString1.copyTo(arrayOfByte, 0, 0, i);
    paramByteString2.copyTo(arrayOfByte, 0, i, j);
    return new LiteralByteString(arrayOfByte);
  }

  private boolean equalsFragments(ByteString paramByteString)
  {
    int j = 0;
    PieceIterator localPieceIterator1 = new PieceIterator(this, null);
    LiteralByteString localLiteralByteString = (LiteralByteString)localPieceIterator1.next();
    int i = 0;
    PieceIterator localPieceIterator2 = new PieceIterator(paramByteString, null);
    paramByteString = (LiteralByteString)localPieceIterator2.next();
    int k = 0;
    while (true)
    {
      int i1 = localLiteralByteString.size() - j;
      int m = paramByteString.size() - i;
      int n = Math.min(i1, m);
      if (j == 0);
      for (boolean bool = localLiteralByteString.equalsRange(paramByteString, i, n); !bool; bool = paramByteString.equalsRange(localLiteralByteString, j, n))
        return false;
      k += n;
      if (k >= this.totalLength)
      {
        if (k == this.totalLength)
          return true;
        throw new IllegalStateException();
      }
      if (n == i1)
      {
        j = 0;
        localLiteralByteString = (LiteralByteString)localPieceIterator1.next();
      }
      while (true)
      {
        if (n != m)
          break label204;
        i = 0;
        paramByteString = (LiteralByteString)localPieceIterator2.next();
        break;
        j += n;
      }
      label204: i += n;
    }
  }

  static RopeByteString newInstanceForTest(ByteString paramByteString1, ByteString paramByteString2)
  {
    return new RopeByteString(paramByteString1, paramByteString2);
  }

  public ByteBuffer asReadOnlyByteBuffer()
  {
    return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
  }

  public List<ByteBuffer> asReadOnlyByteBufferList()
  {
    ArrayList localArrayList = new ArrayList();
    PieceIterator localPieceIterator = new PieceIterator(this, null);
    while (localPieceIterator.hasNext())
      localArrayList.add(localPieceIterator.next().asReadOnlyByteBuffer());
    return localArrayList;
  }

  public byte byteAt(int paramInt)
  {
    if (paramInt < 0)
      throw new ArrayIndexOutOfBoundsException("Index < 0: " + paramInt);
    if (paramInt > this.totalLength)
      throw new ArrayIndexOutOfBoundsException("Index > length: " + paramInt + ", " + this.totalLength);
    if (paramInt < this.leftLength)
      return this.left.byteAt(paramInt);
    return this.right.byteAt(paramInt - this.leftLength);
  }

  public void copyTo(ByteBuffer paramByteBuffer)
  {
    this.left.copyTo(paramByteBuffer);
    this.right.copyTo(paramByteBuffer);
  }

  protected void copyToInternal(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 + paramInt3 <= this.leftLength)
    {
      this.left.copyToInternal(paramArrayOfByte, paramInt1, paramInt2, paramInt3);
      return;
    }
    if (paramInt1 >= this.leftLength)
    {
      this.right.copyToInternal(paramArrayOfByte, paramInt1 - this.leftLength, paramInt2, paramInt3);
      return;
    }
    int i = this.leftLength - paramInt1;
    this.left.copyToInternal(paramArrayOfByte, paramInt1, paramInt2, i);
    this.right.copyToInternal(paramArrayOfByte, 0, paramInt2 + i, paramInt3 - i);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this);
    do
    {
      return true;
      if (!(paramObject instanceof ByteString))
        return false;
      paramObject = (ByteString)paramObject;
      if (this.totalLength != paramObject.size())
        return false;
    }
    while (this.totalLength == 0);
    if (this.hash != 0)
    {
      int i = paramObject.peekCachedHashCode();
      if ((i != 0) && (this.hash != i))
        return false;
    }
    return equalsFragments(paramObject);
  }

  protected int getTreeDepth()
  {
    return this.treeDepth;
  }

  public int hashCode()
  {
    int j = this.hash;
    int i = j;
    if (j == 0)
    {
      j = partialHash(this.totalLength, 0, this.totalLength);
      i = j;
      if (j == 0)
        i = 1;
      this.hash = i;
    }
    return i;
  }

  protected boolean isBalanced()
  {
    return this.totalLength >= minLengthByDepth[this.treeDepth];
  }

  public boolean isValidUtf8()
  {
    boolean bool = false;
    int i = this.left.partialIsValidUtf8(0, 0, this.leftLength);
    if (this.right.partialIsValidUtf8(i, 0, this.right.size()) == 0)
      bool = true;
    return bool;
  }

  public ByteString.ByteIterator iterator()
  {
    return new RopeByteIterator(null);
  }

  public CodedInputStream newCodedInput()
  {
    return CodedInputStream.newInstance(new RopeInputStream());
  }

  public InputStream newInput()
  {
    return new RopeInputStream();
  }

  protected int partialHash(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 + paramInt3 <= this.leftLength)
      return this.left.partialHash(paramInt1, paramInt2, paramInt3);
    if (paramInt2 >= this.leftLength)
      return this.right.partialHash(paramInt1, paramInt2 - this.leftLength, paramInt3);
    int i = this.leftLength - paramInt2;
    paramInt1 = this.left.partialHash(paramInt1, paramInt2, i);
    return this.right.partialHash(paramInt1, 0, paramInt3 - i);
  }

  protected int partialIsValidUtf8(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 + paramInt3 <= this.leftLength)
      return this.left.partialIsValidUtf8(paramInt1, paramInt2, paramInt3);
    if (paramInt2 >= this.leftLength)
      return this.right.partialIsValidUtf8(paramInt1, paramInt2 - this.leftLength, paramInt3);
    int i = this.leftLength - paramInt2;
    paramInt1 = this.left.partialIsValidUtf8(paramInt1, paramInt2, i);
    return this.right.partialIsValidUtf8(paramInt1, 0, paramInt3 - i);
  }

  protected int peekCachedHashCode()
  {
    return this.hash;
  }

  public int size()
  {
    return this.totalLength;
  }

  public ByteString substring(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IndexOutOfBoundsException("Beginning index: " + paramInt1 + " < 0");
    if (paramInt2 > this.totalLength)
      throw new IndexOutOfBoundsException("End index: " + paramInt2 + " > " + this.totalLength);
    int i = paramInt2 - paramInt1;
    if (i < 0)
      throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + paramInt1 + ", " + paramInt2);
    if (i == 0)
      return ByteString.EMPTY;
    if (i == this.totalLength)
      return this;
    if (paramInt2 <= this.leftLength)
      return this.left.substring(paramInt1, paramInt2);
    if (paramInt1 >= this.leftLength)
      return this.right.substring(paramInt1 - this.leftLength, paramInt2 - this.leftLength);
    return new RopeByteString(this.left.substring(paramInt1), this.right.substring(0, paramInt2 - this.leftLength));
  }

  public String toString(String paramString)
    throws UnsupportedEncodingException
  {
    return new String(toByteArray(), paramString);
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    this.left.writeTo(paramOutputStream);
    this.right.writeTo(paramOutputStream);
  }

  private static class Balancer
  {
    private final Stack<ByteString> prefixesStack = new Stack();

    private ByteString balance(ByteString paramByteString1, ByteString paramByteString2)
    {
      doBalance(paramByteString1);
      doBalance(paramByteString2);
      for (paramByteString1 = (ByteString)this.prefixesStack.pop(); !this.prefixesStack.isEmpty(); paramByteString1 = new RopeByteString((ByteString)this.prefixesStack.pop(), paramByteString1, null));
      return paramByteString1;
    }

    private void doBalance(ByteString paramByteString)
    {
      if (paramByteString.isBalanced())
      {
        insert(paramByteString);
        return;
      }
      if ((paramByteString instanceof RopeByteString))
      {
        paramByteString = (RopeByteString)paramByteString;
        doBalance(paramByteString.left);
        doBalance(paramByteString.right);
        return;
      }
      throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + paramByteString.getClass());
    }

    private int getDepthBinForLength(int paramInt)
    {
      int i = Arrays.binarySearch(RopeByteString.minLengthByDepth, paramInt);
      paramInt = i;
      if (i < 0)
        paramInt = -(i + 1) - 1;
      return paramInt;
    }

    private void insert(ByteString paramByteString)
    {
      int i = getDepthBinForLength(paramByteString.size());
      int j = RopeByteString.minLengthByDepth[(i + 1)];
      if ((this.prefixesStack.isEmpty()) || (((ByteString)this.prefixesStack.peek()).size() >= j))
      {
        this.prefixesStack.push(paramByteString);
        return;
      }
      i = RopeByteString.minLengthByDepth[i];
      for (Object localObject = (ByteString)this.prefixesStack.pop(); (!this.prefixesStack.isEmpty()) && (((ByteString)this.prefixesStack.peek()).size() < i); localObject = new RopeByteString((ByteString)this.prefixesStack.pop(), (ByteString)localObject, null));
      for (paramByteString = new RopeByteString((ByteString)localObject, paramByteString, null); !this.prefixesStack.isEmpty(); paramByteString = new RopeByteString((ByteString)this.prefixesStack.pop(), paramByteString, null))
      {
        i = getDepthBinForLength(paramByteString.size());
        i = RopeByteString.minLengthByDepth[(i + 1)];
        if (((ByteString)this.prefixesStack.peek()).size() >= i)
          break;
      }
      this.prefixesStack.push(paramByteString);
    }
  }

  private static class PieceIterator
    implements Iterator<LiteralByteString>
  {
    private final Stack<RopeByteString> breadCrumbs = new Stack();
    private LiteralByteString next = getLeafByLeft(paramByteString);

    private PieceIterator(ByteString paramByteString)
    {
    }

    private LiteralByteString getLeafByLeft(ByteString paramByteString)
    {
      while ((paramByteString instanceof RopeByteString))
      {
        paramByteString = (RopeByteString)paramByteString;
        this.breadCrumbs.push(paramByteString);
        paramByteString = paramByteString.left;
      }
      return (LiteralByteString)paramByteString;
    }

    private LiteralByteString getNextNonEmptyLeaf()
    {
      LiteralByteString localLiteralByteString;
      do
      {
        if (this.breadCrumbs.isEmpty())
          return null;
        localLiteralByteString = getLeafByLeft(((RopeByteString)this.breadCrumbs.pop()).right);
      }
      while (localLiteralByteString.isEmpty());
      return localLiteralByteString;
    }

    public boolean hasNext()
    {
      return this.next != null;
    }

    public LiteralByteString next()
    {
      if (this.next == null)
        throw new NoSuchElementException();
      LiteralByteString localLiteralByteString = this.next;
      this.next = getNextNonEmptyLeaf();
      return localLiteralByteString;
    }

    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }

  private class RopeByteIterator
    implements ByteString.ByteIterator
  {
    private ByteString.ByteIterator bytes = this.pieces.next().iterator();
    int bytesRemaining = RopeByteString.this.size();
    private final RopeByteString.PieceIterator pieces = new RopeByteString.PieceIterator(RopeByteString.this, null);

    private RopeByteIterator()
    {
    }

    public boolean hasNext()
    {
      return this.bytesRemaining > 0;
    }

    public Byte next()
    {
      return Byte.valueOf(nextByte());
    }

    public byte nextByte()
    {
      if (!this.bytes.hasNext())
        this.bytes = this.pieces.next().iterator();
      this.bytesRemaining -= 1;
      return this.bytes.nextByte();
    }

    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }

  private class RopeInputStream extends InputStream
  {
    private LiteralByteString currentPiece;
    private int currentPieceIndex;
    private int currentPieceOffsetInRope;
    private int currentPieceSize;
    private int mark;
    private RopeByteString.PieceIterator pieceIterator;

    public RopeInputStream()
    {
      initialize();
    }

    private void advanceIfCurrentPieceFullyRead()
    {
      if ((this.currentPiece != null) && (this.currentPieceIndex == this.currentPieceSize))
      {
        this.currentPieceOffsetInRope += this.currentPieceSize;
        this.currentPieceIndex = 0;
        if (this.pieceIterator.hasNext())
        {
          this.currentPiece = this.pieceIterator.next();
          this.currentPieceSize = this.currentPiece.size();
        }
      }
      else
      {
        return;
      }
      this.currentPiece = null;
      this.currentPieceSize = 0;
    }

    private void initialize()
    {
      this.pieceIterator = new RopeByteString.PieceIterator(RopeByteString.this, null);
      this.currentPiece = this.pieceIterator.next();
      this.currentPieceSize = this.currentPiece.size();
      this.currentPieceIndex = 0;
      this.currentPieceOffsetInRope = 0;
    }

    private int readSkipInternal(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      int j = paramInt2;
      int i = paramInt1;
      paramInt1 = j;
      while (paramInt1 > 0)
      {
        advanceIfCurrentPieceFullyRead();
        if (this.currentPiece == null)
        {
          if (paramInt1 != paramInt2)
            break;
          return -1;
        }
        int k = Math.min(this.currentPieceSize - this.currentPieceIndex, paramInt1);
        j = i;
        if (paramArrayOfByte != null)
        {
          this.currentPiece.copyTo(paramArrayOfByte, this.currentPieceIndex, i, k);
          j = i + k;
        }
        this.currentPieceIndex += k;
        paramInt1 -= k;
        i = j;
      }
      return paramInt2 - paramInt1;
    }

    public int available()
      throws IOException
    {
      int i = this.currentPieceOffsetInRope;
      int j = this.currentPieceIndex;
      return RopeByteString.this.size() - (i + j);
    }

    public void mark(int paramInt)
    {
      this.mark = (this.currentPieceOffsetInRope + this.currentPieceIndex);
    }

    public boolean markSupported()
    {
      return true;
    }

    public int read()
      throws IOException
    {
      advanceIfCurrentPieceFullyRead();
      if (this.currentPiece == null)
        return -1;
      LiteralByteString localLiteralByteString = this.currentPiece;
      int i = this.currentPieceIndex;
      this.currentPieceIndex = (i + 1);
      return localLiteralByteString.byteAt(i) & 0xFF;
    }

    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      if (paramArrayOfByte == null)
        throw new NullPointerException();
      if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt2 > paramArrayOfByte.length - paramInt1))
        throw new IndexOutOfBoundsException();
      return readSkipInternal(paramArrayOfByte, paramInt1, paramInt2);
    }

    public void reset()
    {
      try
      {
        initialize();
        readSkipInternal(null, 0, this.mark);
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public long skip(long paramLong)
    {
      if (paramLong < 0L)
        throw new IndexOutOfBoundsException();
      long l = paramLong;
      if (paramLong > 2147483647L)
        l = 2147483647L;
      return readSkipInternal(null, 0, (int)l);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.RopeByteString
 * JD-Core Version:    0.6.2
 */