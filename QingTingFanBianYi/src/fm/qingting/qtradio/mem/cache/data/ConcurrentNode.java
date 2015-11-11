package fm.qingting.qtradio.mem.cache.data;

public class ConcurrentNode<E>
  implements DoubleLinkedNode<E>
{
  private ConcurrentNode<E> next;
  private ConcurrentNode<E> prev;
  private E value;

  public ConcurrentNode(E paramE)
  {
    this.value = paramE;
  }

  private boolean isRemoved()
  {
    if ((getNext() != null) && (getNext().getPrev() != this));
    while ((getPrev() != null) && (getPrev().getNext() != this))
      return true;
    return false;
  }

  private void setNext(ConcurrentNode<E> paramConcurrentNode)
  {
    try
    {
      this.next = paramConcurrentNode;
      return;
    }
    finally
    {
      paramConcurrentNode = finally;
    }
    throw paramConcurrentNode;
  }

  private void setPrev(ConcurrentNode<E> paramConcurrentNode)
  {
    try
    {
      this.prev = paramConcurrentNode;
      return;
    }
    finally
    {
      paramConcurrentNode = finally;
    }
    throw paramConcurrentNode;
  }

  public DoubleLinkedNode<E> getNext()
  {
    return this.next;
  }

  public DoubleLinkedNode<E> getPrev()
  {
    return this.prev;
  }

  public E getValue()
  {
    return this.value;
  }

  public boolean insertAfter(E arg1)
  {
    if (isRemoved())
    {
      if (getNext() != null)
      {
        getNext().insertBefore(???);
        return true;
      }
      if (getPrev() != null)
      {
        getPrev().insertAfter(???);
        return true;
      }
      return false;
    }
    while (true)
    {
      ConcurrentNode localConcurrentNode;
      try
      {
        localConcurrentNode = new ConcurrentNode(???);
        if (this.next == null)
        {
          localConcurrentNode.setNext(null);
          localConcurrentNode.setPrev(this);
          setNext(localConcurrentNode);
          return true;
        }
      }
      finally
      {
      }
      synchronized (this.next)
      {
        this.next.setPrev(localConcurrentNode);
        localConcurrentNode.setNext(this.next);
        localConcurrentNode.setPrev(this);
        setNext(localConcurrentNode);
      }
    }
  }

  public boolean insertBefore(E arg1)
  {
    if (isRemoved())
    {
      if (getPrev() != null)
        return getPrev().insertAfter(???);
      if (getNext() != null)
        return getNext().insertBefore(???);
      return false;
    }
    try
    {
      ConcurrentNode localConcurrentNode = new ConcurrentNode(???);
      if (this.prev == null)
      {
        localConcurrentNode.setNext(this);
        localConcurrentNode.setPrev(null);
        setPrev(localConcurrentNode);
      }
      while (true)
      {
        return true;
        synchronized (this.prev)
        {
          this.prev.setNext(localConcurrentNode);
          localConcurrentNode.setPrev(localConcurrentNode);
          localConcurrentNode.setNext(this);
          setPrev(localConcurrentNode);
        }
      }
    }
    finally
    {
    }
    throw ???;
  }

  public void removeNext()
  {
    if (this.next != null)
      this.next.removeSelf();
  }

  public void removePrev()
  {
    if (this.prev != null)
      this.prev.removeSelf();
  }

  // ERROR //
  public void removeSelf()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 39	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:next	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   4: ifnonnull +25 -> 29
    //   7: aload_0
    //   8: getfield 43	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:prev	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   11: ifnonnull +18 -> 29
    //   14: aload_0
    //   15: monitorenter
    //   16: aload_0
    //   17: aconst_null
    //   18: putfield 21	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:value	Ljava/lang/Object;
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: astore_1
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_1
    //   28: athrow
    //   29: aload_0
    //   30: getfield 39	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:next	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   33: ifnonnull +58 -> 91
    //   36: aload_0
    //   37: getfield 43	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:prev	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   40: ifnull +51 -> 91
    //   43: aload_0
    //   44: getfield 43	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:prev	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   47: astore_1
    //   48: aload_1
    //   49: monitorenter
    //   50: aload_0
    //   51: monitorenter
    //   52: aload_0
    //   53: getfield 43	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:prev	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   56: invokevirtual 30	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:getNext	()Lfm/qingting/qtradio/mem/cache/data/DoubleLinkedNode;
    //   59: aload_0
    //   60: if_acmpne +16 -> 76
    //   63: aload_0
    //   64: getfield 43	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:prev	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   67: aconst_null
    //   68: invokespecial 60	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:setNext	(Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;)V
    //   71: aload_0
    //   72: aconst_null
    //   73: putfield 21	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:value	Ljava/lang/Object;
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_1
    //   79: monitorexit
    //   80: return
    //   81: astore_2
    //   82: aload_1
    //   83: monitorexit
    //   84: aload_2
    //   85: athrow
    //   86: astore_2
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_2
    //   90: athrow
    //   91: aload_0
    //   92: getfield 39	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:next	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   95: ifnull +58 -> 153
    //   98: aload_0
    //   99: getfield 43	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:prev	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   102: ifnonnull +51 -> 153
    //   105: aload_0
    //   106: monitorenter
    //   107: aload_0
    //   108: getfield 39	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:next	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   111: astore_1
    //   112: aload_1
    //   113: monitorenter
    //   114: aload_0
    //   115: getfield 39	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:next	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   118: invokevirtual 34	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:getPrev	()Lfm/qingting/qtradio/mem/cache/data/DoubleLinkedNode;
    //   121: aload_0
    //   122: if_acmpne +16 -> 138
    //   125: aload_0
    //   126: getfield 39	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:next	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   129: aconst_null
    //   130: invokespecial 62	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:setPrev	(Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;)V
    //   133: aload_0
    //   134: aconst_null
    //   135: putfield 21	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:value	Ljava/lang/Object;
    //   138: aload_1
    //   139: monitorexit
    //   140: aload_0
    //   141: monitorexit
    //   142: return
    //   143: astore_1
    //   144: aload_0
    //   145: monitorexit
    //   146: aload_1
    //   147: athrow
    //   148: astore_2
    //   149: aload_1
    //   150: monitorexit
    //   151: aload_2
    //   152: athrow
    //   153: aload_0
    //   154: getfield 39	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:next	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   157: ifnull +97 -> 254
    //   160: aload_0
    //   161: getfield 43	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:prev	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   164: ifnull +90 -> 254
    //   167: aload_0
    //   168: getfield 43	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:prev	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   171: astore_1
    //   172: aload_1
    //   173: monitorenter
    //   174: aload_0
    //   175: monitorenter
    //   176: aload_0
    //   177: getfield 39	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:next	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   180: astore_2
    //   181: aload_2
    //   182: monitorenter
    //   183: aload_0
    //   184: getfield 39	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:next	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   187: invokevirtual 34	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:getPrev	()Lfm/qingting/qtradio/mem/cache/data/DoubleLinkedNode;
    //   190: aload_0
    //   191: if_acmpne +41 -> 232
    //   194: aload_0
    //   195: getfield 43	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:prev	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   198: invokevirtual 30	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:getNext	()Lfm/qingting/qtradio/mem/cache/data/DoubleLinkedNode;
    //   201: aload_0
    //   202: if_acmpne +30 -> 232
    //   205: aload_0
    //   206: getfield 43	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:prev	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   209: aload_0
    //   210: getfield 39	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:next	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   213: invokespecial 60	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:setNext	(Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;)V
    //   216: aload_0
    //   217: getfield 39	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:next	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   220: aload_0
    //   221: getfield 43	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:prev	Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;
    //   224: invokespecial 62	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:setPrev	(Lfm/qingting/qtradio/mem/cache/data/ConcurrentNode;)V
    //   227: aload_0
    //   228: aconst_null
    //   229: putfield 21	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:value	Ljava/lang/Object;
    //   232: aload_2
    //   233: monitorexit
    //   234: aload_0
    //   235: monitorexit
    //   236: aload_1
    //   237: monitorexit
    //   238: return
    //   239: astore_2
    //   240: aload_1
    //   241: monitorexit
    //   242: aload_2
    //   243: athrow
    //   244: astore_3
    //   245: aload_2
    //   246: monitorexit
    //   247: aload_3
    //   248: athrow
    //   249: astore_2
    //   250: aload_0
    //   251: monitorexit
    //   252: aload_2
    //   253: athrow
    //   254: aload_0
    //   255: monitorenter
    //   256: aload_0
    //   257: aconst_null
    //   258: putfield 21	fm/qingting/qtradio/mem/cache/data/ConcurrentNode:value	Ljava/lang/Object;
    //   261: aload_0
    //   262: monitorexit
    //   263: return
    //   264: astore_1
    //   265: aload_0
    //   266: monitorexit
    //   267: aload_1
    //   268: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   16	23	24	finally
    //   25	27	24	finally
    //   50	52	81	finally
    //   78	80	81	finally
    //   82	84	81	finally
    //   89	91	81	finally
    //   52	76	86	finally
    //   76	78	86	finally
    //   87	89	86	finally
    //   107	114	143	finally
    //   140	142	143	finally
    //   144	146	143	finally
    //   151	153	143	finally
    //   114	138	148	finally
    //   138	140	148	finally
    //   149	151	148	finally
    //   174	176	239	finally
    //   236	238	239	finally
    //   240	242	239	finally
    //   252	254	239	finally
    //   183	232	244	finally
    //   232	234	244	finally
    //   245	247	244	finally
    //   176	183	249	finally
    //   234	236	249	finally
    //   247	249	249	finally
    //   250	252	249	finally
    //   256	263	264	finally
    //   265	267	264	finally
  }

  public void setValue(E paramE)
  {
    try
    {
      this.value = paramE;
      return;
    }
    finally
    {
      paramE = finally;
    }
    throw paramE;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.mem.cache.data.ConcurrentNode
 * JD-Core Version:    0.6.2
 */