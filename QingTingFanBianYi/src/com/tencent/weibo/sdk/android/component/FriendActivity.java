package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.weibo.sdk.android.api.PublishWeiBoAPI;
import com.tencent.weibo.sdk.android.api.adapter.FriendAdapter;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import com.tencent.weibo.sdk.android.api.util.FirendCompare;
import com.tencent.weibo.sdk.android.api.util.HypyUtil;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.Firend;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FriendActivity extends Activity
  implements LetterListView.OnTouchingLetterChangedListener, HttpCallback
{
  private FriendAdapter adapter;
  private Map<String, List<Firend>> child = new HashMap();
  private ProgressDialog dialog;
  private EditText editText;
  private List<String> group = new ArrayList();
  private List<String> groups = new ArrayList();
  private LetterListView letterListView;
  private ExpandableListView listView;
  private Map<String, List<Firend>> newchild = new HashMap();
  private List<String> newgourp = new ArrayList();
  private int[] nums;
  private TextView textView;

  private void ex(final List<String> paramList, final Map<String, List<Firend>> paramMap)
  {
    int i = 0;
    while (true)
    {
      if (i >= paramList.size())
      {
        this.listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
        {
          public void onGroupExpand(int paramAnonymousInt)
          {
          }
        });
        this.listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
          public boolean onGroupClick(ExpandableListView paramAnonymousExpandableListView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            return true;
          }
        });
        this.listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
          public boolean onChildClick(ExpandableListView paramAnonymousExpandableListView, View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, long paramAnonymousLong)
          {
            paramAnonymousExpandableListView = new Intent();
            paramAnonymousExpandableListView.setClass(FriendActivity.this, PublishActivity.class);
            paramAnonymousExpandableListView.putExtra("firend", ((Firend)((List)paramMap.get(paramList.get(paramAnonymousInt1))).get(paramAnonymousInt2)).getNick());
            FriendActivity.this.setResult(-1, paramAnonymousExpandableListView);
            FriendActivity.this.finish();
            return true;
          }
        });
        this.listView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
          public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
          {
            Log.d("first", paramAnonymousInt1);
            paramAnonymousInt2 = 0;
            while (true)
            {
              if (paramAnonymousInt2 >= paramList.size())
                return;
              if ((paramAnonymousInt2 == 0) && (paramAnonymousInt1 >= 0) && (paramAnonymousInt1 < FriendActivity.this.nums[paramAnonymousInt2]))
              {
                FriendActivity.this.textView.setText(((String)paramList.get(paramAnonymousInt2)).toUpperCase());
                return;
              }
              if ((paramAnonymousInt1 < FriendActivity.this.nums[paramAnonymousInt2]) && (paramAnonymousInt1 >= FriendActivity.this.nums[(paramAnonymousInt2 - 1)]))
              {
                FriendActivity.this.textView.setText(((String)paramList.get(paramAnonymousInt2)).toUpperCase());
                return;
              }
              paramAnonymousInt2 += 1;
            }
          }

          public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
          {
          }
        });
        return;
      }
      this.listView.expandGroup(i);
      i += 1;
    }
  }

  private void getJsonData(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    int i;
    try
    {
      paramJSONObject = paramJSONObject.getJSONObject("data").getJSONArray("info");
      i = 0;
      while (true)
      {
        if (i >= paramJSONObject.length())
        {
          Collections.sort(localArrayList, new FirendCompare());
          i = 0;
          if (i < localArrayList.size())
            break;
          return;
        }
        Firend localFirend = new Firend();
        localFirend.setNick(((JSONObject)paramJSONObject.get(i)).getString("nick"));
        localFirend.setName(((JSONObject)paramJSONObject.get(i)).getString("name"));
        localFirend.setHeadurl(((JSONObject)paramJSONObject.get(i)).getString("headurl").replaceAll("\\/", "/") + "/180");
        localArrayList.add(localFirend);
        i += 1;
      }
    }
    catch (JSONException paramJSONObject)
    {
      while (true)
        paramJSONObject.printStackTrace();
    }
    if (this.child.get(HypyUtil.cn2py(((Firend)localArrayList.get(i)).getNick()).substring(0, 1).toUpperCase()) != null)
      ((List)this.child.get(HypyUtil.cn2py(((Firend)localArrayList.get(i)).getNick()).substring(0, 1).toUpperCase())).add((Firend)localArrayList.get(i));
    while (true)
    {
      i += 1;
      break;
      Log.d("group", HypyUtil.cn2py(((Firend)localArrayList.get(i)).getNick()).substring(0, 1));
      this.group.add(HypyUtil.cn2py(((Firend)localArrayList.get(i)).getNick()).substring(0, 1).toUpperCase());
      paramJSONObject = new ArrayList();
      paramJSONObject.add((Firend)localArrayList.get(i));
      this.child.put(HypyUtil.cn2py(((Firend)localArrayList.get(i)).getNick()).substring(0, 1).toUpperCase(), paramJSONObject);
    }
  }

  private void getdate()
  {
    if (this.dialog == null)
    {
      this.dialog = new ProgressDialog(this);
      this.dialog.setMessage("请稍后...");
      this.dialog.show();
    }
    new PublishWeiBoAPI(new AccountModel(Util.getSharePersistent(getApplicationContext(), "ACCESS_TOKEN"))).mutual_list(this, this, null, 0, 0, 0, 10);
  }

  private View initview()
  {
    LinearLayout localLinearLayout = new LinearLayout(this);
    Object localObject1 = new LinearLayout.LayoutParams(-1, -1);
    localLinearLayout.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    localLinearLayout.setOrientation(1);
    Object localObject2 = new RelativeLayout(this);
    ((RelativeLayout)localObject2).setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
    ((RelativeLayout)localObject2).setGravity(0);
    ((RelativeLayout)localObject2).setBackgroundDrawable(BackGroudSeletor.getdrawble("up_bg2x", this));
    Object localObject4 = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject4).addRule(15);
    ((RelativeLayout.LayoutParams)localObject4).addRule(9, -1);
    ((RelativeLayout.LayoutParams)localObject4).addRule(15, -1);
    ((RelativeLayout.LayoutParams)localObject4).topMargin = 10;
    ((RelativeLayout.LayoutParams)localObject4).leftMargin = 10;
    ((RelativeLayout.LayoutParams)localObject4).bottomMargin = 10;
    Object localObject3 = new Button(this);
    ((Button)localObject3).setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[] { "return_btn2x", "return_btn_hover" }, this));
    ((Button)localObject3).setText("  返回");
    ((Button)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject4);
    ((Button)localObject3).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FriendActivity.this.finish();
      }
    });
    localObject4 = new TextView(this);
    ((TextView)localObject4).setText("好友列表");
    ((TextView)localObject4).setTextColor(-1);
    ((TextView)localObject4).setTextSize(24.0F);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(13, -1);
    ((TextView)localObject4).setLayoutParams(localLayoutParams);
    ((RelativeLayout)localObject2).addView((View)localObject4);
    ((RelativeLayout)localObject2).addView((View)localObject3);
    localLinearLayout.addView((View)localObject2);
    localObject2 = new LinearLayout(this);
    ((LinearLayout)localObject2).setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    ((LinearLayout)localObject2).setOrientation(0);
    ((LinearLayout)localObject2).setGravity(17);
    this.editText = new EditText(this);
    this.editText.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0F));
    this.editText.setPadding(20, 0, 10, 0);
    this.editText.setBackgroundDrawable(BackGroudSeletor.getdrawble("searchbg_", this));
    this.editText.setCompoundDrawablesWithIntrinsicBounds(BackGroudSeletor.getdrawble("search_", this), null, null, null);
    this.editText.setHint("搜索");
    this.editText.setTextSize(18.0F);
    this.editText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        FriendActivity.this.search(paramAnonymousEditable.toString());
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
    ((LinearLayout)localObject2).addView(this.editText);
    localLinearLayout.addView((View)localObject2);
    this.listView = new ExpandableListView(this);
    new FrameLayout.LayoutParams(-1, -1);
    this.listView.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.listView.setGroupIndicator(null);
    localObject1 = new LinearLayout(this);
    ((LinearLayout)localObject1).setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    this.textView = new TextView(this);
    ((LinearLayout)localObject1).setPadding(30, 0, 0, 0);
    ((LinearLayout)localObject1).setGravity(16);
    this.textView.setTextSize(18.0F);
    this.textView.setTextColor(-1);
    this.textView.setText("常用联系人");
    ((LinearLayout)localObject1).addView(this.textView);
    ((LinearLayout)localObject1).setBackgroundColor(Color.parseColor("#b0bac3"));
    this.letterListView = new LetterListView(this, this.group);
    this.letterListView.setOnTouchingLetterChangedListener(this);
    localObject2 = new RelativeLayout.LayoutParams(50, -1);
    localObject3 = new RelativeLayout(this);
    localObject4 = new RelativeLayout.LayoutParams(-1, -2);
    ((RelativeLayout.LayoutParams)localObject2).addRule(11);
    this.letterListView.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    ((RelativeLayout)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject4);
    ((RelativeLayout)localObject3).addView(this.listView);
    ((RelativeLayout)localObject3).addView((View)localObject1);
    ((RelativeLayout)localObject3).addView(this.letterListView);
    localLinearLayout.addView((View)localObject3);
    return localLinearLayout;
  }

  private int totle(int paramInt)
  {
    if (paramInt == 0)
      return ((List)this.child.get(this.group.get(paramInt))).size() + 1;
    return ((List)this.child.get(this.group.get(paramInt))).size() + 1 + totle(paramInt - 1);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    paramBundle = (LinearLayout)initview();
    getdate();
    setContentView(paramBundle);
  }

  public void onResult(Object paramObject)
  {
    if ((this.dialog != null) && (this.dialog.isShowing()))
      this.dialog.dismiss();
    int i;
    if ((paramObject != null) && (((ModelResult)paramObject).isSuccess()))
    {
      getJsonData((JSONObject)((ModelResult)paramObject).getObj());
      this.nums = new int[this.group.size()];
      i = 0;
    }
    while (true)
    {
      if (i >= this.nums.length)
      {
        this.letterListView.setB(this.group);
        this.adapter = new FriendAdapter(this, this.group, this.child);
        this.listView.setAdapter(this.adapter);
        ex(this.group, this.child);
        Log.d("发送成功", paramObject.toString());
        return;
      }
      this.nums[i] = totle(i);
      i += 1;
    }
  }

  protected void onStop()
  {
    super.onStop();
    if ((this.dialog != null) && (this.dialog.isShowing()))
      this.dialog.dismiss();
  }

  public void onTouchingLetterChanged(int paramInt)
  {
    this.listView.setSelectedGroup(paramInt);
  }

  public void search(String paramString)
  {
    this.newgourp.clear();
    this.newchild.clear();
    int i = 0;
    if (i >= this.group.size())
    {
      Log.d("size", this.newgourp.size() + "---" + this.newchild.size());
      this.nums = new int[this.newgourp.size()];
      i = 0;
    }
    while (true)
    {
      if (i >= this.nums.length)
      {
        this.letterListView.setB(this.newgourp);
        this.adapter.setChild(this.newchild);
        this.adapter.setGroup(this.newgourp);
        this.adapter.notifyDataSetChanged();
        ex(this.newgourp, this.newchild);
        return;
        int j = 0;
        if (j >= ((List)this.child.get(this.group.get(i))).size())
        {
          i += 1;
          break;
        }
        if (((Firend)((List)this.child.get(this.group.get(i))).get(j)).getNick().contains(paramString))
        {
          if (this.newchild.get(this.group.get(i)) != null)
            break label368;
          ArrayList localArrayList = new ArrayList();
          localArrayList.add((Firend)((List)this.child.get(this.group.get(i))).get(j));
          this.newchild.put((String)this.group.get(i), localArrayList);
          this.newgourp.add((String)this.group.get(i));
        }
        while (true)
        {
          j += 1;
          break;
          label368: ((List)this.newchild.get(this.group.get(i))).add((Firend)((List)this.child.get(this.group.get(i))).get(j));
        }
      }
      this.nums[i] = totle(i);
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.FriendActivity
 * JD-Core Version:    0.6.2
 */