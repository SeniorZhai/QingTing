package fm.qingting.qtradio.model;

import java.util.Map;

public class Node
  implements InfoManager.INodeEventListener
{
  public transient Node child = null;
  public transient Node nextSibling = null;
  public String nodeName = "node";
  public transient Node parent = null;
  public transient Node prevSibling = null;

  public Node getChild()
  {
    return this.child;
  }

  public Node getNextSibling()
  {
    return this.nextSibling;
  }

  public Node getParent()
  {
    return this.parent;
  }

  public Node getPrevSibling()
  {
    return this.prevSibling;
  }

  public boolean insertChildAtEnd(Node paramNode)
  {
    if (paramNode == null)
      return false;
    paramNode.parent = this;
    if (this.child != null)
      this.child.insertSiblingAtEnd(paramNode);
    while (true)
    {
      return true;
      this.child = paramNode;
    }
  }

  public boolean insertNodeAtEndByParentName(Node paramNode, String paramString)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramNode == null) || (paramString == null))
    {
      bool1 = false;
      return bool1;
    }
    if (this.nodeName.equalsIgnoreCase(paramString))
      return insertChildAtEnd(paramNode);
    for (Node localNode = this.nextSibling; ; localNode = localNode.nextSibling)
    {
      if (localNode == null)
        break label64;
      bool1 = bool2;
      if (localNode.insertNodeAtEndByParentName(paramNode, paramString))
        break;
    }
    label64: for (localNode = this.child; ; localNode = localNode.child)
    {
      if (localNode == null)
        break label94;
      bool1 = bool2;
      if (localNode.insertNodeAtEndByParentName(paramNode, paramString))
        break;
    }
    label94: return false;
  }

  public boolean insertSiblingAtEnd(Node paramNode)
  {
    Node localNode;
    if (paramNode != null)
    {
      localNode = this;
      if (this != null);
    }
    else
    {
      return false;
    }
    while (localNode.nextSibling != null)
      localNode = localNode.nextSibling;
    paramNode.prevSibling = localNode;
    paramNode.parent = localNode.parent;
    localNode.nextSibling = paramNode;
    return true;
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
  }

  public void onNodeUpdated(Object paramObject, Map<String, String> paramMap, String paramString)
  {
  }

  public boolean restoreChildFromDB()
  {
    return false;
  }

  public boolean saveChildToDB()
  {
    return false;
  }

  public void setParent(Node paramNode)
  {
    this.parent = paramNode;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.Node
 * JD-Core Version:    0.6.2
 */