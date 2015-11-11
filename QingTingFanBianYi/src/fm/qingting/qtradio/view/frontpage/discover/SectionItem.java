package fm.qingting.qtradio.view.frontpage.discover;

class SectionItem
{
  static final int TYPE_3RD_PARTY_AD_ITEM = 8;
  static final int TYPE_AD_ITEM = 7;
  static final int TYPE_ALL = 1;
  static final int TYPE_COLLECTION = 4;
  static final int TYPE_COUNT = 9;
  static final int TYPE_ITEM = 3;
  static final int TYPE_JD_ITEM = 6;
  static final int TYPE_JD_TAG = 5;
  static final int TYPE_SECTION = 0;
  static final int TYPE_TAG = 2;
  public Object data;
  public final int type;

  public SectionItem(int paramInt, Object paramObject)
  {
    this.type = paramInt;
    this.data = paramObject;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.SectionItem
 * JD-Core Version:    0.6.2
 */