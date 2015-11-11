package com.taobao.newxp.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.taobao.newxp.UFPResType;
import com.taobao.newxp.b;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Category
  implements Parcelable
{
  public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator()
  {
    public Category a(Parcel paramAnonymousParcel)
    {
      return new Category(paramAnonymousParcel, null);
    }

    public Category[] a(int paramAnonymousInt)
    {
      return new Category[paramAnonymousInt];
    }
  };
  private Set<a> a = new HashSet();
  public int index;
  public String keyword;
  public String name;
  public UFPResType resType;
  public String tabId;
  public b template;

  private Category(Parcel paramParcel)
  {
    this.keyword = paramParcel.readString();
    this.name = paramParcel.readString();
    this.index = paramParcel.readInt();
    this.resType = UFPResType.fromString(paramParcel.readString());
    this.template = b.a(paramParcel.readString(), null);
    int j = paramParcel.readInt();
    int i = 0;
    while (i < j)
    {
      this.a.add(a.a(paramParcel.readString()));
      i += 1;
    }
  }

  public Category(String paramString1, String paramString2, String paramString3, UFPResType paramUFPResType, b paramb)
  {
    this.keyword = paramString1;
    this.name = paramString2;
    paramString1 = paramString3;
    if (TextUtils.isEmpty(paramString3))
      paramString1 = "um_0";
    this.tabId = paramString1;
    this.resType = paramUFPResType;
    this.template = paramb;
    if (this.resType == null)
      this.resType = UFPResType.APP;
    if (this.template == null)
      this.template = b.a;
  }

  public Category(String paramString1, String paramString2, String paramString3, UFPResType paramUFPResType, b paramb, Set<a> paramSet)
  {
    this.keyword = paramString1;
    this.name = paramString2;
    paramString1 = paramString3;
    if (TextUtils.isEmpty(paramString3))
      paramString1 = "um_0";
    this.tabId = paramString1;
    this.resType = paramUFPResType;
    this.template = paramb;
    if (this.resType == null)
      this.resType = UFPResType.APP;
    if (this.template == null)
      this.template = b.a;
    this.a = paramSet;
  }

  public void addAttribute(a parama)
  {
    this.a.add(parama);
  }

  public void addAttributes(Collection<a> paramCollection)
  {
    this.a.addAll(paramCollection);
  }

  public String attrToString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.a != null)
    {
      Iterator localIterator = this.a.iterator();
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        localStringBuilder.append(locala.toString() + ".");
      }
      if (localStringBuilder.length() > 0)
        localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
    }
    return localStringBuilder.toString();
  }

  public boolean containsAttr(a parama)
  {
    return this.a.contains(parama);
  }

  public int describeContents()
  {
    return 0;
  }

  public Set<a> getAttrs()
  {
    return this.a;
  }

  public void setAttrs(Set<a> paramSet)
  {
    this.a = paramSet;
  }

  public String toString()
  {
    return "Category [keyword=" + this.keyword + ", name=" + this.name + ", index=" + this.index + ", resType=" + this.resType + ", template=" + this.template + ", attrs=" + this.a + "]";
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.keyword);
    paramParcel.writeString(this.name);
    paramParcel.writeInt(this.index);
    paramParcel.writeString(this.resType.toString());
    paramParcel.writeString(this.template.toString());
    paramParcel.writeInt(this.a.size());
    Iterator localIterator = this.a.iterator();
    while (localIterator.hasNext())
      paramParcel.writeString(((a)localIterator.next()).toString());
  }

  public static enum a
  {
    public static a a(String paramString)
    {
      a[] arrayOfa = values();
      int j = arrayOfa.length;
      int i = 0;
      while (i < j)
      {
        a locala = arrayOfa[i];
        if (locala.toString().equals(paramString))
          return locala;
        i += 1;
      }
      return null;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.Category
 * JD-Core Version:    0.6.2
 */