package fm.qingting.qtradio.view.popviews;

import fm.qingting.qtradio.model.Node;
import java.util.ArrayList;
import java.util.List;

public class CustomPopActionParam
{
  private List<Integer> mButtons;
  private OnCustomButtonClickListener mClickListener;
  private Node mNode;
  private String mTitle;

  public int getButton(int paramInt)
  {
    if ((this.mButtons != null) && (this.mButtons.size() > paramInt))
      return ((Integer)this.mButtons.get(paramInt)).intValue();
    return 0;
  }

  public List<Integer> getButtons()
  {
    return this.mButtons;
  }

  public OnCustomButtonClickListener getListener()
  {
    return this.mClickListener;
  }

  public Node getNode()
  {
    return this.mNode;
  }

  public String getTitle()
  {
    return this.mTitle;
  }

  public void setButtons(List<Integer> paramList)
  {
    this.mButtons = paramList;
  }

  public void setNode(Node paramNode)
  {
    this.mNode = paramNode;
  }

  public void setOnCustomButtonClickListener(OnCustomButtonClickListener paramOnCustomButtonClickListener)
  {
    this.mClickListener = paramOnCustomButtonClickListener;
  }

  public void setTitle(String paramString)
  {
    this.mTitle = paramString;
  }

  public static class Builder
  {
    private List<Integer> mButtons;
    private CustomPopActionParam.OnCustomButtonClickListener mListener;
    private Node mNode;
    private String mTitle;

    public Builder addButton(int paramInt)
    {
      if (this.mButtons == null)
        this.mButtons = new ArrayList();
      this.mButtons.add(Integer.valueOf(paramInt));
      return this;
    }

    public Builder addNode(Node paramNode)
    {
      this.mNode = paramNode;
      return this;
    }

    public CustomPopActionParam create()
    {
      CustomPopActionParam localCustomPopActionParam = new CustomPopActionParam();
      localCustomPopActionParam.setTitle(this.mTitle);
      localCustomPopActionParam.setButtons(this.mButtons);
      localCustomPopActionParam.setOnCustomButtonClickListener(this.mListener);
      localCustomPopActionParam.setNode(this.mNode);
      return localCustomPopActionParam;
    }

    public Builder setButtons(List<Integer> paramList)
    {
      this.mButtons = paramList;
      return this;
    }

    public Builder setListener(CustomPopActionParam.OnCustomButtonClickListener paramOnCustomButtonClickListener)
    {
      this.mListener = paramOnCustomButtonClickListener;
      return this;
    }

    public Builder setTitle(String paramString)
    {
      this.mTitle = paramString;
      return this;
    }
  }

  public static abstract interface OnCustomButtonClickListener
  {
    public abstract void onClick(CustomPopAction paramCustomPopAction);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.CustomPopActionParam
 * JD-Core Version:    0.6.2
 */