package org.jdom;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class AttributeList extends AbstractList
  implements List, Serializable
{
  private static final String CVS_ID = "@(#) $RCSfile: AttributeList.java,v $ $Revision: 1.24 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
  private static final int INITIAL_ARRAY_SIZE = 5;
  private Attribute[] elementData;
  private Element parent;
  private int size;

  private AttributeList()
  {
  }

  AttributeList(Element paramElement)
  {
    this.parent = paramElement;
  }

  private void ensureCapacity(int paramInt)
  {
    if (this.elementData == null)
      this.elementData = new Attribute[Math.max(paramInt, 5)];
    do
    {
      return;
      i = this.elementData.length;
    }
    while (paramInt <= i);
    Attribute[] arrayOfAttribute = this.elementData;
    int j = i * 3 / 2 + 1;
    int i = j;
    if (j < paramInt)
      i = paramInt;
    this.elementData = new Attribute[i];
    System.arraycopy(arrayOfAttribute, 0, this.elementData, 0, this.size);
  }

  private int indexOfDuplicate(Attribute paramAttribute)
  {
    return indexOf(paramAttribute.getName(), paramAttribute.getNamespace());
  }

  public void add(int paramInt, Object paramObject)
  {
    if ((paramObject instanceof Attribute))
    {
      paramObject = (Attribute)paramObject;
      if (indexOfDuplicate(paramObject) >= 0)
        throw new IllegalAddException("Cannot add duplicate attribute");
      add(paramInt, paramObject);
      this.modCount += 1;
      return;
    }
    if (paramObject == null)
      throw new IllegalAddException("Cannot add null attribute");
    throw new IllegalAddException("Class " + paramObject.getClass().getName() + " is not an attribute");
  }

  void add(int paramInt, Attribute paramAttribute)
  {
    if (paramAttribute.getParent() != null)
      throw new IllegalAddException("The attribute already has an existing parent \"" + paramAttribute.getParent().getQualifiedName() + "\"");
    Object localObject = Verifier.checkNamespaceCollision(paramAttribute, this.parent);
    if (localObject != null)
      throw new IllegalAddException(this.parent, paramAttribute, (String)localObject);
    if ((paramInt < 0) || (paramInt > this.size))
      throw new IndexOutOfBoundsException("Index: " + paramInt + " Size: " + size());
    paramAttribute.setParent(this.parent);
    ensureCapacity(this.size + 1);
    if (paramInt == this.size)
    {
      localObject = this.elementData;
      paramInt = this.size;
      this.size = (paramInt + 1);
      localObject[paramInt] = paramAttribute;
    }
    while (true)
    {
      this.modCount += 1;
      return;
      System.arraycopy(this.elementData, paramInt, this.elementData, paramInt + 1, this.size - paramInt);
      this.elementData[paramInt] = paramAttribute;
      this.size += 1;
    }
  }

  public boolean add(Object paramObject)
  {
    if ((paramObject instanceof Attribute))
    {
      paramObject = (Attribute)paramObject;
      int i = indexOfDuplicate(paramObject);
      if (i < 0)
        add(size(), paramObject);
      while (true)
      {
        return true;
        set(i, paramObject);
      }
    }
    if (paramObject == null)
      throw new IllegalAddException("Cannot add null attribute");
    throw new IllegalAddException("Class " + paramObject.getClass().getName() + " is not an attribute");
  }

  public boolean addAll(int paramInt, Collection paramCollection)
  {
    if ((paramInt < 0) || (paramInt > this.size))
      throw new IndexOutOfBoundsException("Index: " + paramInt + " Size: " + size());
    if ((paramCollection == null) || (paramCollection.size() == 0))
      return false;
    ensureCapacity(size() + paramCollection.size());
    int j = 0;
    int i = 0;
    try
    {
      paramCollection = paramCollection.iterator();
      while (true)
      {
        j = i;
        if (!paramCollection.hasNext())
          break;
        j = i;
        add(paramInt + i, paramCollection.next());
        i += 1;
      }
    }
    catch (RuntimeException paramCollection)
    {
      i = 0;
      while (i < j)
      {
        remove(paramInt);
        i += 1;
      }
      throw paramCollection;
    }
    return true;
  }

  public boolean addAll(Collection paramCollection)
  {
    return addAll(size(), paramCollection);
  }

  public void clear()
  {
    if (this.elementData != null)
    {
      int i = 0;
      while (i < this.size)
      {
        this.elementData[i].setParent(null);
        i += 1;
      }
      this.elementData = null;
      this.size = 0;
    }
    this.modCount += 1;
  }

  void clearAndSet(Collection paramCollection)
  {
    Attribute[] arrayOfAttribute = this.elementData;
    int j = this.size;
    this.elementData = null;
    this.size = 0;
    if ((paramCollection != null) && (paramCollection.size() != 0))
      ensureCapacity(paramCollection.size());
    try
    {
      addAll(0, paramCollection);
      if (arrayOfAttribute != null)
      {
        int i = 0;
        while (i < j)
        {
          arrayOfAttribute[i].setParent(null);
          i += 1;
        }
      }
    }
    catch (RuntimeException paramCollection)
    {
      this.elementData = arrayOfAttribute;
      this.size = j;
      throw paramCollection;
    }
    this.modCount += 1;
  }

  public Object get(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.size))
      throw new IndexOutOfBoundsException("Index: " + paramInt + " Size: " + size());
    return this.elementData[paramInt];
  }

  Object get(String paramString, Namespace paramNamespace)
  {
    int i = indexOf(paramString, paramNamespace);
    if (i < 0)
      return null;
    return this.elementData[i];
  }

  int indexOf(String paramString, Namespace paramNamespace)
  {
    paramNamespace = paramNamespace.getURI();
    if (this.elementData != null)
    {
      int i = 0;
      while (i < this.size)
      {
        Object localObject = this.elementData[i];
        String str = ((Attribute)localObject).getNamespaceURI();
        localObject = ((Attribute)localObject).getName();
        if ((str.equals(paramNamespace)) && (((String)localObject).equals(paramString)))
          return i;
        i += 1;
      }
    }
    return -1;
  }

  public Object remove(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.size))
      throw new IndexOutOfBoundsException("Index: " + paramInt + " Size: " + size());
    Attribute localAttribute = this.elementData[paramInt];
    localAttribute.setParent(null);
    int i = this.size - paramInt - 1;
    if (i > 0)
      System.arraycopy(this.elementData, paramInt + 1, this.elementData, paramInt, i);
    Attribute[] arrayOfAttribute = this.elementData;
    paramInt = this.size - 1;
    this.size = paramInt;
    arrayOfAttribute[paramInt] = null;
    this.modCount += 1;
    return localAttribute;
  }

  boolean remove(String paramString, Namespace paramNamespace)
  {
    int i = indexOf(paramString, paramNamespace);
    if (i < 0)
      return false;
    remove(i);
    return true;
  }

  public Object set(int paramInt, Object paramObject)
  {
    if ((paramObject instanceof Attribute))
    {
      paramObject = (Attribute)paramObject;
      int i = indexOfDuplicate(paramObject);
      if ((i >= 0) && (i != paramInt))
        throw new IllegalAddException("Cannot set duplicate attribute");
      return set(paramInt, paramObject);
    }
    if (paramObject == null)
      throw new IllegalAddException("Cannot add null attribute");
    throw new IllegalAddException("Class " + paramObject.getClass().getName() + " is not an attribute");
  }

  Object set(int paramInt, Attribute paramAttribute)
  {
    if ((paramInt < 0) || (paramInt >= this.size))
      throw new IndexOutOfBoundsException("Index: " + paramInt + " Size: " + size());
    if (paramAttribute.getParent() != null)
      throw new IllegalAddException("The attribute already has an existing parent \"" + paramAttribute.getParent().getQualifiedName() + "\"");
    Object localObject = Verifier.checkNamespaceCollision(paramAttribute, this.parent);
    if (localObject != null)
      throw new IllegalAddException(this.parent, paramAttribute, (String)localObject);
    localObject = this.elementData[paramInt];
    ((Attribute)localObject).setParent(null);
    this.elementData[paramInt] = paramAttribute;
    paramAttribute.setParent(this.parent);
    return localObject;
  }

  public int size()
  {
    return this.size;
  }

  public String toString()
  {
    return super.toString();
  }

  final void uncheckedAddAttribute(Attribute paramAttribute)
  {
    paramAttribute.parent = this.parent;
    ensureCapacity(this.size + 1);
    Attribute[] arrayOfAttribute = this.elementData;
    int i = this.size;
    this.size = (i + 1);
    arrayOfAttribute[i] = paramAttribute;
    this.modCount += 1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.AttributeList
 * JD-Core Version:    0.6.2
 */