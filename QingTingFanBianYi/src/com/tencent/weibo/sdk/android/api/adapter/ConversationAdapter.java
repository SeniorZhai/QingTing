package com.tencent.weibo.sdk.android.api.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import java.util.List;

public class ConversationAdapter extends BaseAdapter
{
  private Context ct;
  private List<String> cvlist;

  public ConversationAdapter(Context paramContext, List<String> paramList)
  {
    this.ct = paramContext;
    this.cvlist = paramList;
  }

  public int getCount()
  {
    return this.cvlist.size();
  }

  public List<String> getCvlist()
  {
    return this.cvlist;
  }

  public Object getItem(int paramInt)
  {
    return this.cvlist.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = new LinearLayout(this.ct);
      new LinearLayout.LayoutParams(-1, -2);
      paramView.setBackgroundDrawable(BackGroudSeletor.getdrawble("topic_", this.ct));
      paramView.setGravity(16);
      paramView.setPadding(10, 0, 10, 0);
      paramViewGroup = new TextView(this.ct);
      paramViewGroup.setTextColor(Color.parseColor("#108ece"));
      paramViewGroup.setText(getItem(paramInt).toString());
      paramViewGroup.setGravity(16);
      paramViewGroup.setTextSize(18.0F);
      paramView.addView(paramViewGroup);
      paramView.setTag(paramViewGroup);
      return paramView;
    }
    ((TextView)paramView.getTag()).setText(getItem(paramInt).toString());
    return paramView;
  }

  public void setCvlist(List<String> paramList)
  {
    this.cvlist = paramList;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.adapter.ConversationAdapter
 * JD-Core Version:    0.6.2
 */