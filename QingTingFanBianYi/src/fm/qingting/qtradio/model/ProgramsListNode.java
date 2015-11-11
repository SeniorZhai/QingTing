package fm.qingting.qtradio.model;

import java.util.ArrayList;
import java.util.List;

public class ProgramsListNode extends Node
{
  private boolean available;
  public int programType;
  public String programsId;
  private List<ProgramNode> programsList = new ArrayList();

  public ProgramsListNode()
  {
    this.nodeName = "programslist";
  }

  public boolean isAvailable()
  {
    return this.available;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ProgramsListNode
 * JD-Core Version:    0.6.2
 */