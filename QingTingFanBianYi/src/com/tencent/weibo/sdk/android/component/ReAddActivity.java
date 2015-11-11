package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.weibo.sdk.android.api.WeiboAPI;
import com.tencent.weibo.sdk.android.api.adapter.GalleryAdapter;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.ImageInfo;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import java.util.ArrayList;
import org.json.JSONObject;

public class ReAddActivity extends Activity
{
  private String accessToken;
  private WeiboAPI api;
  private EditText content = null;
  private String contentStr = "";
  private Gallery gallery;
  private RelativeLayout galleryLayout = null;
  private ArrayList<ImageInfo> imageList = new ArrayList();
  private LinearLayout layout = null;
  private PopupWindow loadingWindow = null;
  private HttpCallback mCallBack = new HttpCallback()
  {
    public void onResult(Object paramAnonymousObject)
    {
      paramAnonymousObject = (ModelResult)paramAnonymousObject;
      if (paramAnonymousObject.isExpires())
      {
        Toast.makeText(ReAddActivity.this, paramAnonymousObject.getError_message(), 0).show();
        return;
      }
      if (paramAnonymousObject.isSuccess())
      {
        Toast.makeText(ReAddActivity.this, "转播成功", 0).show();
        ReAddActivity.this.finish();
        return;
      }
      Toast.makeText(ReAddActivity.this, paramAnonymousObject.getError_message(), 0).show();
      ReAddActivity.this.finish();
    }
  };
  private Handler mHandler = null;
  private String musicAuthor = "";
  private String musicPath = "";
  private String musicTitle = "";
  private String picPath = "";
  private ProgressBar progressBar = null;
  private TextView textView_num;
  private HttpCallback videoCallBack = new HttpCallback()
  {
    public void onResult(Object paramAnonymousObject)
    {
      paramAnonymousObject = (ModelResult)paramAnonymousObject;
      if (paramAnonymousObject != null)
        if ((paramAnonymousObject.isExpires()) || (!paramAnonymousObject.isSuccess()));
      while ((ReAddActivity.this.loadingWindow == null) || (!ReAddActivity.this.loadingWindow.isShowing()))
        try
        {
          paramAnonymousObject = ((JSONObject)paramAnonymousObject.getObj()).getJSONObject("data");
          ImageInfo localImageInfo = new ImageInfo();
          localImageInfo.setImagePath(paramAnonymousObject.getString("minipic"));
          localImageInfo.setImageName(paramAnonymousObject.getString("title"));
          localImageInfo.setPlayPath(paramAnonymousObject.getString("real"));
          ReAddActivity.this.imageList.add(localImageInfo);
          paramAnonymousObject = new GalleryAdapter(ReAddActivity.this.getApplicationContext(), ReAddActivity.this.loadingWindow, ReAddActivity.this.imageList);
          ReAddActivity.this.gallery.setAdapter(paramAnonymousObject);
          return;
        }
        catch (Exception paramAnonymousObject)
        {
          paramAnonymousObject.printStackTrace();
          return;
        }
      ReAddActivity.this.loadingWindow.dismiss();
    }
  };
  private String videoPath = "";

  public View initLayout()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    Object localObject2 = new RelativeLayout.LayoutParams(-1, -2);
    Object localObject1 = new RelativeLayout.LayoutParams(-2, -2);
    this.layout = new LinearLayout(this);
    this.layout.setLayoutParams(localLayoutParams);
    this.layout.setOrientation(1);
    this.layout.setBackgroundDrawable(BackGroudSeletor.getdrawble("readd_bg", getApplication()));
    RelativeLayout localRelativeLayout = new RelativeLayout(this);
    localRelativeLayout.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    localRelativeLayout.setBackgroundDrawable(BackGroudSeletor.getdrawble("up_bg2x", getApplication()));
    localRelativeLayout.setGravity(0);
    localObject2 = new Button(this);
    Object localObject3 = getApplication();
    ((Button)localObject2).setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[] { "quxiao_btn2x", "quxiao_btn_hover" }, (Context)localObject3));
    ((Button)localObject2).setText("取消");
    ((RelativeLayout.LayoutParams)localObject1).addRule(9, -1);
    ((RelativeLayout.LayoutParams)localObject1).addRule(15, -1);
    ((RelativeLayout.LayoutParams)localObject1).topMargin = 10;
    ((RelativeLayout.LayoutParams)localObject1).leftMargin = 10;
    ((RelativeLayout.LayoutParams)localObject1).bottomMargin = 10;
    ((Button)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject1);
    ((Button)localObject2).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ReAddActivity.this.finish();
      }
    });
    localRelativeLayout.addView((View)localObject2);
    localObject1 = new TextView(this);
    ((TextView)localObject1).setText("转播");
    ((TextView)localObject1).setTextColor(-1);
    ((TextView)localObject1).setTextSize(24.0F);
    localObject2 = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject2).addRule(13, -1);
    ((TextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    localRelativeLayout.addView((View)localObject1);
    localObject1 = new Button(this);
    localObject2 = getApplication();
    ((Button)localObject1).setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[] { "sent_btn2x", "sent_btn_hover" }, (Context)localObject2));
    ((Button)localObject1).setText("转播");
    localObject2 = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject2).addRule(11, -1);
    ((RelativeLayout.LayoutParams)localObject2).addRule(15, -1);
    ((RelativeLayout.LayoutParams)localObject2).topMargin = 10;
    ((RelativeLayout.LayoutParams)localObject2).rightMargin = 10;
    ((RelativeLayout.LayoutParams)localObject2).bottomMargin = 10;
    ((Button)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    ((Button)localObject1).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ReAddActivity.this.reAddWeibo();
      }
    });
    localRelativeLayout.addView((View)localObject1);
    localObject2 = new RelativeLayout(this);
    ((RelativeLayout)localObject2).setLayoutParams(new RelativeLayout.LayoutParams(-1, 240));
    localObject3 = new RelativeLayout(this);
    localObject1 = new RelativeLayout.LayoutParams(440, -1);
    ((RelativeLayout.LayoutParams)localObject1).addRule(13);
    ((RelativeLayout.LayoutParams)localObject1).topMargin = 50;
    ((RelativeLayout)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject1);
    ((RelativeLayout)localObject3).setBackgroundDrawable(BackGroudSeletor.getdrawble("input_bg", getApplication()));
    this.textView_num = new TextView(this);
    TextView localTextView = this.textView_num;
    if (this.contentStr == null);
    for (localObject1 = "140"; ; localObject1 = String.valueOf(140 - this.contentStr.length()))
    {
      localTextView.setText((CharSequence)localObject1);
      this.textView_num.setTextColor(Color.parseColor("#999999"));
      this.textView_num.setGravity(5);
      this.textView_num.setTextSize(18.0F);
      localObject1 = new RelativeLayout.LayoutParams(-2, -2);
      ((RelativeLayout.LayoutParams)localObject1).addRule(12, -1);
      ((RelativeLayout.LayoutParams)localObject1).addRule(11, -1);
      ((RelativeLayout.LayoutParams)localObject1).rightMargin = 10;
      this.textView_num.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      ((RelativeLayout)localObject3).addView(this.textView_num);
      this.content = new EditText(this);
      localObject1 = new RelativeLayout.LayoutParams(-1, -2);
      ((RelativeLayout.LayoutParams)localObject1).addRule(14);
      ((RelativeLayout.LayoutParams)localObject1).addRule(10);
      this.content.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      this.content.setMaxLines(4);
      this.content.setMinLines(4);
      this.content.setScrollbarFadingEnabled(true);
      this.content.setGravity(48);
      this.content.setMovementMethod(ScrollingMovementMethod.getInstance());
      this.content.setText(this.contentStr);
      this.content.setSelection(this.contentStr.length());
      this.content.setBackgroundDrawable(null);
      this.content.addTextChangedListener(new TextWatcher()
      {
        private int selectionEnd;
        private int selectionStart;
        private CharSequence temp;

        public void afterTextChanged(Editable paramAnonymousEditable)
        {
          this.selectionStart = ReAddActivity.this.content.getSelectionStart();
          this.selectionEnd = ReAddActivity.this.content.getSelectionEnd();
          if (this.temp.length() > 140)
          {
            Toast.makeText(ReAddActivity.this, "最多可输入140字符", 0).show();
            paramAnonymousEditable.delete(this.selectionStart - 1, this.selectionEnd);
            int i = this.selectionStart;
            ReAddActivity.this.content.setText(paramAnonymousEditable);
            ReAddActivity.this.content.setSelection(i);
            return;
          }
          ReAddActivity.this.textView_num.setText(String.valueOf(140 - paramAnonymousEditable.length()));
        }

        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          this.temp = paramAnonymousCharSequence;
        }

        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
        }
      });
      ((RelativeLayout)localObject3).addView(this.content);
      ((RelativeLayout)localObject2).addView((View)localObject3);
      this.galleryLayout = new RelativeLayout(this);
      this.galleryLayout.setLayoutParams(localLayoutParams);
      this.gallery = new Gallery(this);
      localObject1 = new RelativeLayout.LayoutParams(303, 203);
      ((RelativeLayout.LayoutParams)localObject1).addRule(14, -1);
      ((RelativeLayout.LayoutParams)localObject1).addRule(10, -1);
      ((RelativeLayout.LayoutParams)localObject1).topMargin = 50;
      this.gallery.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      this.gallery.setBackgroundDrawable(BackGroudSeletor.getdrawble("pic_biankuang2x", getApplication()));
      requestForGallery();
      this.galleryLayout.addView(this.gallery);
      this.layout.addView(localRelativeLayout);
      this.layout.addView((View)localObject2);
      if ((this.picPath != null) && (!"".equals(this.picPath)) && (this.videoPath != null) && (!"".equals(this.videoPath)))
        this.layout.addView(this.galleryLayout);
      return this.layout;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().setFlags(1024, 1024);
    requestWindowFeature(1);
    paramBundle = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
    BackGroudSeletor.setPix(paramBundle.widthPixels + "x" + paramBundle.heightPixels);
    this.accessToken = Util.getSharePersistent(getApplicationContext(), "ACCESS_TOKEN");
    if ((this.accessToken == null) || ("".equals(this.accessToken)))
    {
      Toast.makeText(this, "请先授权", 0).show();
      finish();
      return;
    }
    paramBundle = getIntent().getExtras();
    if (paramBundle != null)
    {
      this.contentStr = paramBundle.getString("content");
      this.videoPath = paramBundle.getString("video_url");
      this.picPath = paramBundle.getString("pic_url");
      this.musicPath = paramBundle.getString("music_url");
      this.musicTitle = paramBundle.getString("music_title");
      this.musicAuthor = paramBundle.getString("music_author");
    }
    this.api = new WeiboAPI(new AccountModel(this.accessToken));
    setContentView(initLayout());
  }

  protected void reAddWeibo()
  {
    this.contentStr = this.content.getText().toString();
    this.api.reAddWeibo(getApplicationContext(), this.contentStr, this.picPath, this.videoPath, this.musicPath, this.musicTitle, this.musicAuthor, this.mCallBack, null, 4);
  }

  public ArrayList<ImageInfo> requestForGallery()
  {
    if (this.picPath != null)
    {
      ImageInfo localImageInfo = new ImageInfo();
      localImageInfo.setImagePath(this.picPath);
      this.imageList.add(localImageInfo);
    }
    if (this.videoPath != null)
    {
      new ImageInfo();
      this.api.getVideoInfo(getApplicationContext(), this.videoPath, this.videoCallBack, null, 4);
    }
    return this.imageList;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.ReAddActivity
 * JD-Core Version:    0.6.2
 */