package fm.qingting.qtradio.model;

public class Program
{
  public boolean available = true;
  public String description = "";
  public int duration;
  public int index = -1;
  public String name;
  public String programId;
  public String source;

  public Program clone()
  {
    Program localProgram = new Program();
    localProgram.name = this.name;
    localProgram.description = this.description;
    localProgram.duration = this.duration;
    localProgram.source = this.source;
    localProgram.programId = this.programId;
    localProgram.index = this.index;
    return localProgram;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (this == paramObject)
      bool1 = true;
    int i;
    label47: label56: label74: label83: label101: label110: 
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            }
            while (paramObject == null);
            bool1 = bool2;
          }
          while (!(paramObject instanceof Program));
          paramObject = (Program)paramObject;
          if (this.name != null)
            break;
          i = 1;
          if (paramObject.name != null)
            break label283;
          j = 1;
          bool1 = bool2;
        }
        while (i != j);
        if (this.description != null)
          break label288;
        i = 1;
        if (paramObject.description != null)
          break label293;
        j = 1;
        bool1 = bool2;
      }
      while (i != j);
      if (this.source != null)
        break label298;
      i = 1;
      if (paramObject.source != null)
        break label303;
      j = 1;
      bool1 = bool2;
    }
    while (i != j);
    if (this.programId == null)
    {
      i = 1;
      label128: if (paramObject.programId != null)
        break label313;
    }
    label283: label288: label293: label298: label303: label313: for (int j = 1; ; j = 0)
    {
      bool1 = bool2;
      if (i != j)
        break;
      if (this.name != null)
      {
        bool1 = bool2;
        if (!this.name.equalsIgnoreCase(paramObject.name))
          break;
      }
      if (this.description != null)
      {
        bool1 = bool2;
        if (!this.description.equalsIgnoreCase(paramObject.description))
          break;
      }
      if (this.source != null)
      {
        bool1 = bool2;
        if (!this.source.equalsIgnoreCase(paramObject.source))
          break;
      }
      if (this.programId != null)
      {
        bool1 = bool2;
        if (!this.programId.equalsIgnoreCase(paramObject.programId))
          break;
      }
      bool1 = bool2;
      if (this.duration != paramObject.duration)
        break;
      bool1 = bool2;
      if (this.available != paramObject.available)
        break;
      return true;
      i = 0;
      break label47;
      j = 0;
      break label56;
      i = 0;
      break label74;
      j = 0;
      break label83;
      i = 0;
      break label101;
      j = 0;
      break label110;
      i = 0;
      break label128;
    }
  }

  public void setProgram(Program paramProgram)
  {
    this.name = paramProgram.name;
    this.description = paramProgram.description;
    this.duration = paramProgram.duration;
    this.source = paramProgram.source;
    this.programId = paramProgram.programId;
    this.index = paramProgram.index;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.Program
 * JD-Core Version:    0.6.2
 */