package org.jdom;

public class IllegalAddException extends IllegalArgumentException
{
  private static final String CVS_ID = "@(#) $RCSfile: IllegalAddException.java,v $ $Revision: 1.26 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";

  public IllegalAddException(String paramString)
  {
    super(paramString);
  }

  IllegalAddException(Comment paramComment, String paramString)
  {
    super("The comment \"" + paramComment.getText() + "\" could not be added to the top level of the document: " + paramString);
  }

  IllegalAddException(DocType paramDocType, String paramString)
  {
    super("The DOCTYPE " + paramDocType.toString() + " could not be added to the document: " + paramString);
  }

  IllegalAddException(Element paramElement, String paramString)
  {
    super("The element \"" + paramElement.getQualifiedName() + "\" could not be added as the root of the document: " + paramString);
  }

  IllegalAddException(Element paramElement, Attribute paramAttribute, String paramString)
  {
    super("The attribute \"" + paramAttribute.getQualifiedName() + "\" could not be added to the element \"" + paramElement.getQualifiedName() + "\": " + paramString);
  }

  IllegalAddException(Element paramElement, CDATA paramCDATA, String paramString)
  {
    super("The CDATA \"" + paramCDATA.getText() + "\" could not be added as content to \"" + paramElement.getQualifiedName() + "\": " + paramString);
  }

  IllegalAddException(Element paramElement, Comment paramComment, String paramString)
  {
    super("The comment \"" + paramComment.getText() + "\" could not be added as content to \"" + paramElement.getQualifiedName() + "\": " + paramString);
  }

  IllegalAddException(Element paramElement1, Element paramElement2, String paramString)
  {
    super("The element \"" + paramElement2.getQualifiedName() + "\" could not be added as a child of \"" + paramElement1.getQualifiedName() + "\": " + paramString);
  }

  IllegalAddException(Element paramElement, EntityRef paramEntityRef, String paramString)
  {
    super("The entity reference\"" + paramEntityRef.getName() + "\" could not be added as content to \"" + paramElement.getQualifiedName() + "\": " + paramString);
  }

  IllegalAddException(Element paramElement, Namespace paramNamespace, String paramString)
  {
  }

  IllegalAddException(Element paramElement, ProcessingInstruction paramProcessingInstruction, String paramString)
  {
    super("The PI \"" + paramProcessingInstruction.getTarget() + "\" could not be added as content to \"" + paramElement.getQualifiedName() + "\": " + paramString);
  }

  IllegalAddException(Element paramElement, Text paramText, String paramString)
  {
    super("The Text \"" + paramText.getText() + "\" could not be added as content to \"" + paramElement.getQualifiedName() + "\": " + paramString);
  }

  IllegalAddException(ProcessingInstruction paramProcessingInstruction, String paramString)
  {
    super("The PI \"" + paramProcessingInstruction.getTarget() + "\" could not be added to the top level of the document: " + paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.IllegalAddException
 * JD-Core Version:    0.6.2
 */