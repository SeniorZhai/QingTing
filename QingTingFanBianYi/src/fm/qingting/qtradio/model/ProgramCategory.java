package fm.qingting.qtradio.model;

import java.util.ArrayList;

public class ProgramCategory
{
  public String cid;
  public boolean hasChild;
  public String name;
  public ArrayList<ProgramCategory> subCategories;
  public String type;

  public ProgramCategory()
  {
  }

  public ProgramCategory(String paramString1, String paramString2, String paramString3)
  {
    this.cid = paramString1;
    this.name = paramString2;
    this.type = paramString3;
  }

  public ProgramCategory(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    this.cid = paramString1;
    this.name = paramString2;
    this.type = paramString3;
    this.hasChild = paramBoolean;
  }

  public ProgramCategory clone()
  {
    ProgramCategory localProgramCategory = new ProgramCategory();
    localProgramCategory.cid = this.cid;
    localProgramCategory.name = this.name;
    localProgramCategory.type = this.type;
    localProgramCategory.hasChild = this.hasChild;
    localProgramCategory.subCategories = this.subCategories;
    return localProgramCategory;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    label27: label36: label50: label59: label188: label191: 
    while (true)
    {
      return false;
      if ((paramObject instanceof ProgramCategory))
      {
        paramObject = (ProgramCategory)paramObject;
        int i;
        if (this.cid == null)
        {
          i = 1;
          if (paramObject.cid != null)
            break label168;
          j = 1;
          if (i != j)
            break label171;
          if (this.name != null)
            break label173;
          i = 1;
          if (paramObject.name != null)
            break label178;
          j = 1;
          if (i != j)
            break label181;
          if (this.type != null)
            break label183;
          i = 1;
          label73: if (paramObject.type != null)
            break label188;
        }
        for (int j = 1; ; j = 0)
        {
          if ((i != j) || (this.hasChild != paramObject.hasChild) || ((this.cid != null) && (!this.cid.equalsIgnoreCase(paramObject.cid))) || ((this.name != null) && (!this.name.equalsIgnoreCase(paramObject.name))) || ((this.type != null) && (!this.type.equalsIgnoreCase(paramObject.type))))
            break label191;
          return true;
          i = 0;
          break label27;
          j = 0;
          break label36;
          break;
          i = 0;
          break label50;
          j = 0;
          break label59;
          break;
          i = 0;
          break label73;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ProgramCategory
 * JD-Core Version:    0.6.2
 */