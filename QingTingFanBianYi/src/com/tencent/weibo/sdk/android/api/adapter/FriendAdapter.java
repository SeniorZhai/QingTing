package com.tencent.weibo.sdk.android.api.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import com.tencent.weibo.sdk.android.model.Firend;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class FriendAdapter extends BaseExpandableListAdapter
{
  private Map<String, List<Firend>> child;
  private Context ctx;
  private List<String> group;

  public FriendAdapter(Context paramContext, List<String> paramList, Map<String, List<Firend>> paramMap)
  {
    this.ctx = paramContext;
    this.group = paramList;
    this.child = paramMap;
  }

  public Object getChild(int paramInt1, int paramInt2)
  {
    return ((List)this.child.get(getGroup(paramInt1))).get(paramInt2);
  }

  public Map<String, List<Firend>> getChild()
  {
    return this.child;
  }

  public long getChildId(int paramInt1, int paramInt2)
  {
    return paramInt2;
  }

  public View getChildView(final int paramInt1, final int paramInt2, boolean paramBoolean, final View paramView, ViewGroup paramViewGroup)
  {
    Object localObject;
    TextView localTextView1;
    if (paramView == null)
    {
      paramViewGroup = new LinearLayout(this.ctx);
      new LinearLayout.LayoutParams(-1, -2);
      paramViewGroup.setOrientation(0);
      paramViewGroup.setGravity(16);
      paramViewGroup.setPadding(15, 0, 10, 0);
      paramViewGroup.setBackgroundDrawable(BackGroudSeletor.getdrawble("childbg_", this.ctx));
      paramView = new ImageView(this.ctx);
      paramView.setId(4502);
      paramView.setLayoutParams(new LinearLayout.LayoutParams(45, 45));
      paramView.setImageDrawable(BackGroudSeletor.getdrawble("logo", this.ctx));
      localObject = new LinearLayout(this.ctx);
      ((LinearLayout)localObject).setPadding(10, 0, 0, 0);
      ((LinearLayout)localObject).setGravity(16);
      ((LinearLayout)localObject).setOrientation(1);
      localTextView1 = new TextView(this.ctx);
      TextView localTextView2 = new TextView(this.ctx);
      localTextView1.setTextSize(18.0F);
      localTextView1.setId(4500);
      localTextView1.setTextColor(Color.parseColor("#32769b"));
      localTextView2.setTextSize(12.0F);
      localTextView2.setId(4501);
      localTextView2.setTextColor(Color.parseColor("#a6c6d5"));
      localTextView1.setText(((Firend)getChild(paramInt1, paramInt2)).getNick());
      localTextView2.setText(((Firend)getChild(paramInt1, paramInt2)).getName());
      ((LinearLayout)localObject).addView(localTextView1);
      ((LinearLayout)localObject).addView(localTextView2);
      paramViewGroup.setBackgroundDrawable(BackGroudSeletor.getdrawble("childbg_", this.ctx));
      paramViewGroup.addView(paramView);
      paramViewGroup.addView((View)localObject);
      paramView = paramViewGroup;
      paramView.setTag(paramViewGroup);
    }
    while (((Firend)getChild(paramInt1, paramInt2)).getHeadurl() != null)
    {
      new AsyncTask()
      {
        protected Bitmap doInBackground(String[] paramAnonymousArrayOfString)
        {
          try
          {
            Log.d("image urel", ((Firend)FriendAdapter.this.getChild(paramInt1, paramInt2)).getHeadurl());
            paramAnonymousArrayOfString = new URL(((Firend)FriendAdapter.this.getChild(paramInt1, paramInt2)).getHeadurl());
            String str = paramAnonymousArrayOfString.openConnection().getHeaderField(0);
            if (str.indexOf("200") < 0)
              Log.d("图片文件不存在或路径错误，错误代码：", str);
            paramAnonymousArrayOfString = BitmapFactory.decodeStream(paramAnonymousArrayOfString.openStream());
            return paramAnonymousArrayOfString;
          }
          catch (MalformedURLException paramAnonymousArrayOfString)
          {
            paramAnonymousArrayOfString.printStackTrace();
            return null;
          }
          catch (IOException paramAnonymousArrayOfString)
          {
            paramAnonymousArrayOfString.printStackTrace();
          }
          return null;
        }

        protected void onPostExecute(Bitmap paramAnonymousBitmap)
        {
          ImageView localImageView = (ImageView)((LinearLayout)paramView.getTag()).findViewById(4502);
          if (paramAnonymousBitmap == null)
            localImageView.setImageDrawable(BackGroudSeletor.getdrawble("logo", FriendAdapter.this.ctx));
          while (true)
          {
            super.onPostExecute(paramAnonymousBitmap);
            return;
            localImageView.setImageBitmap(paramAnonymousBitmap);
          }
        }

        protected void onPreExecute()
        {
          super.onPreExecute();
        }
      }
      .execute(new String[] { ((Firend)getChild(paramInt1, paramInt2)).getHeadurl() });
      return paramView;
      paramViewGroup = (LinearLayout)paramView.getTag();
      localObject = (TextView)paramViewGroup.findViewById(4500);
      localTextView1 = (TextView)paramViewGroup.findViewById(4501);
      ((ImageView)((LinearLayout)paramViewGroup.getTag()).findViewById(4502)).setImageDrawable(BackGroudSeletor.getdrawble("logo", this.ctx));
      ((TextView)localObject).setText(((Firend)getChild(paramInt1, paramInt2)).getNick());
      localTextView1.setText(((Firend)getChild(paramInt1, paramInt2)).getName());
    }
    ((ImageView)((LinearLayout)paramView.getTag()).findViewById(4502)).setImageDrawable(BackGroudSeletor.getdrawble("logo", this.ctx));
    return paramView;
  }

  public int getChildrenCount(int paramInt)
  {
    return ((List)this.child.get(this.group.get(paramInt))).size();
  }

  public Object getGroup(int paramInt)
  {
    return this.group.get(paramInt);
  }

  public List<String> getGroup()
  {
    return this.group;
  }

  public int getGroupCount()
  {
    return this.group.size();
  }

  public long getGroupId(int paramInt)
  {
    return paramInt;
  }

  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = new LinearLayout(this.ctx);
      paramViewGroup = new TextView(this.ctx);
      paramView.setPadding(30, 0, 0, 0);
      paramView.setGravity(16);
      paramViewGroup.setTextSize(18.0F);
      paramViewGroup.setTextColor(-1);
      paramViewGroup.setWidth(40);
      paramViewGroup.setText(getGroup(paramInt).toString().toUpperCase());
      paramView.addView(paramViewGroup);
      paramView.setBackgroundDrawable(BackGroudSeletor.getdrawble("groupbg_", this.ctx));
      paramViewGroup.setTag(Integer.valueOf(paramInt));
      paramView.setTag(paramViewGroup);
      return paramView;
    }
    ((TextView)paramView.getTag()).setText(getGroup(paramInt).toString().toUpperCase());
    return paramView;
  }

  public boolean hasStableIds()
  {
    return true;
  }

  public boolean isChildSelectable(int paramInt1, int paramInt2)
  {
    return true;
  }

  public void setChild(Map<String, List<Firend>> paramMap)
  {
    this.child = paramMap;
  }

  public void setGroup(List<String> paramList)
  {
    this.group = paramList;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.adapter.FriendAdapter
 * JD-Core Version:    0.6.2
 */