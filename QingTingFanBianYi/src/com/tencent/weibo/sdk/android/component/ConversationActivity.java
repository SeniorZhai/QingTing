package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Toast;
import com.tencent.weibo.sdk.android.api.PublishWeiBoAPI;
import com.tencent.weibo.sdk.android.api.adapter.ConversationAdapter;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConversationActivity extends Activity
  implements HttpCallback
{
  private ConversationAdapter adapter;
  private ProgressDialog dialog;
  private EditText editText;
  private List<String> list;
  private ListView listView;

  private void click(final List<String> paramList)
  {
    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = new Intent();
        paramAnonymousAdapterView.setClass(ConversationActivity.this, PublishActivity.class);
        paramAnonymousAdapterView.putExtra("conversation", (String)paramList.get(paramAnonymousInt));
        ConversationActivity.this.setResult(-1, paramAnonymousAdapterView);
        ConversationActivity.this.finish();
      }
    });
  }

  private List<String> initData(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramJSONObject = paramJSONObject.getJSONObject("data").getJSONArray("info");
      int i = 0;
      while (true)
      {
        if (i >= paramJSONObject.length())
          return localArrayList;
        localArrayList.add("#" + paramJSONObject.getJSONObject(i).getString("text") + "#");
        i += 1;
      }
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
    return localArrayList;
  }

  private View initview()
  {
    LinearLayout localLinearLayout1 = new LinearLayout(this);
    Object localObject1 = new LinearLayout.LayoutParams(-1, -1);
    localLinearLayout1.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    localLinearLayout1.setOrientation(1);
    LinearLayout localLinearLayout2 = new LinearLayout(this);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    localLinearLayout2.setLayoutParams(localLayoutParams);
    localLinearLayout2.setOrientation(0);
    localLinearLayout2.setBackgroundDrawable(BackGroudSeletor.getdrawble("up_bg2x", this));
    localLinearLayout2.setPadding(20, 0, 20, 0);
    localLinearLayout2.setGravity(16);
    Object localObject2 = new LinearLayout(this);
    ((LinearLayout)localObject2).setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0F));
    ((LinearLayout)localObject2).setPadding(0, 0, 12, 0);
    this.editText = new EditText(this);
    this.editText.setSingleLine(true);
    this.editText.setLines(1);
    this.editText.setTextSize(18.0F);
    this.editText.setHint("搜索话题");
    this.editText.setPadding(20, 0, 10, 0);
    this.editText.setBackgroundDrawable(BackGroudSeletor.getdrawble("huati_input2x", this));
    this.editText.setCompoundDrawablesWithIntrinsicBounds(BackGroudSeletor.getdrawble("huati_icon_hover2x", this), null, null, null);
    this.editText.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    Button localButton = new Button(this);
    localButton.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[] { "sent_btn_22x", "sent_btn_hover" }, this));
    localButton.setTextColor(-1);
    localButton.setText("取消");
    ((LinearLayout)localObject2).addView(this.editText);
    localLinearLayout2.addView((View)localObject2);
    localLinearLayout2.addView(localButton);
    localLinearLayout1.addView(localLinearLayout2);
    localLinearLayout2 = new LinearLayout(this);
    localLinearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0F));
    this.listView = new ListView(this);
    this.listView.setDivider(BackGroudSeletor.getdrawble("table_lie_", this));
    this.listView.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.editText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        while (true)
        {
          if (i >= ConversationActivity.this.list.size())
          {
            ConversationActivity.this.adapter.setCvlist(localArrayList);
            ConversationActivity.this.adapter.notifyDataSetChanged();
            ConversationActivity.this.click(localArrayList);
            return;
          }
          if (((String)ConversationActivity.this.list.get(i)).contains(paramAnonymousEditable.toString()))
            localArrayList.add((String)ConversationActivity.this.list.get(i));
          i += 1;
        }
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }
    });
    localObject1 = new LinearLayout(this);
    localObject2 = new Button(this);
    ((LinearLayout)localObject1).setGravity(17);
    ((Button)localObject2).setLayoutParams(localLayoutParams);
    ((Button)localObject2).setText("abc");
    ((Button)localObject2).setTextColor(-16777216);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ConversationActivity.this.finish();
      }
    });
    ((LinearLayout)localObject1).addView((View)localObject2);
    localLinearLayout2.addView(this.listView);
    localLinearLayout1.addView(localLinearLayout2);
    return localLinearLayout1;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView((LinearLayout)initview());
    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        Toast.makeText(ConversationActivity.this, paramAnonymousInt, 100).show();
      }
    });
    if (this.dialog == null)
    {
      this.dialog = new ProgressDialog(this);
      this.dialog.setMessage("请稍后...");
      this.dialog.setCancelable(false);
    }
    this.dialog.show();
    new PublishWeiBoAPI(new AccountModel(Util.getSharePersistent(getApplicationContext(), "ACCESS_TOKEN"))).recent_used(this, this, null, 15, 1, 0);
  }

  public void onResult(Object paramObject)
  {
    if ((this.dialog != null) && (this.dialog.isShowing()))
      this.dialog.dismiss();
    if ((paramObject != null) && (((ModelResult)paramObject).isSuccess()))
    {
      Log.d("发送成功", ((ModelResult)paramObject).getObj().toString());
      this.list = initData((JSONObject)((ModelResult)paramObject).getObj());
      this.adapter = new ConversationAdapter(this, this.list);
      this.listView.setAdapter(this.adapter);
      click(this.list);
    }
  }

  protected void onStop()
  {
    super.onStop();
    if ((this.dialog != null) && (this.dialog.isShowing()))
      this.dialog.dismiss();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.ConversationActivity
 * JD-Core Version:    0.6.2
 */