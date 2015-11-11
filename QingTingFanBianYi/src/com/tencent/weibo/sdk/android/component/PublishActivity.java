package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.weibo.sdk.android.api.WeiboAPI;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class PublishActivity extends Activity
  implements View.OnClickListener, HttpCallback
{
  private String accessToken;
  private ImageButton button_camera;
  private ImageButton button_conversation;
  private Button button_esc;
  private ImageButton button_friend;
  private ImageButton button_location;
  private Button button_send;
  private Context context;
  private ProgressDialog dialog;
  private EditText editText_text;
  private String edstring = "";
  private FrameLayout frameLayout_big;
  private FrameLayout frameLayout_icon;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      int i = paramAnonymousMessage.what;
      if (i == 5)
      {
        PublishActivity.this.frameLayout_big.setVisibility(0);
        PublishActivity.this.frameLayout_icon.setVisibility(0);
      }
      do
      {
        return;
        if (i == 0)
        {
          PublishActivity.this.popupWindow.showAsDropDown(PublishActivity.this.layout_set);
          paramAnonymousMessage = (InputMethodManager)PublishActivity.this.getSystemService("input_method");
          Log.d("alive", paramAnonymousMessage.isActive());
          if (paramAnonymousMessage.isActive())
          {
            paramAnonymousMessage.hideSoftInputFromWindow(PublishActivity.this.editText_text.getWindowToken(), 0);
            Log.d("alive", paramAnonymousMessage.isActive());
          }
        }
        if (i == 10)
          PublishActivity.this.button_location.setBackgroundDrawable(BackGroudSeletor.getdrawble("dingwei_icon_hover2x", PublishActivity.this));
      }
      while (i != 15);
      Toast.makeText(PublishActivity.this, "定位失败", 0).show();
      PublishActivity.this.button_location.setBackgroundDrawable(BackGroudSeletor.getdrawble("dingwei_icon2x", PublishActivity.this));
    }
  };
  private ImageView imageView_big;
  private ImageView imageView_bound;
  private ImageView imageView_delete;
  private ImageView imageView_icon;
  private LinearLayout layout_big_delete;
  private LinearLayout layout_imagebound;
  private LinearLayout layout_set;
  private Map<String, String> location;
  private int[] lyout = new int[2];
  private Bitmap mBitmap = null;
  private Location mLocation;
  private PopupWindow popupWindow;
  private TextView textView_num;
  private LinearLayout viewroot;
  private WeiboAPI weiboAPI;

  private int[] getarea(int[] paramArrayOfInt)
  {
    int[] arrayOfInt = new int[2];
    if (paramArrayOfInt != null)
    {
      if ((paramArrayOfInt[0] <= paramArrayOfInt[1]) || (paramArrayOfInt[0] < 300))
        break label83;
      arrayOfInt[0] = 300;
      arrayOfInt[1] = ((int)(paramArrayOfInt[1] / paramArrayOfInt[0] * 300.0F));
    }
    while (true)
    {
      Log.d("myarea", arrayOfInt[0] + "....." + arrayOfInt[1]);
      return arrayOfInt;
      label83: if ((paramArrayOfInt[0] > paramArrayOfInt[1]) && (paramArrayOfInt[0] < 300))
      {
        arrayOfInt[0] = paramArrayOfInt[0];
        arrayOfInt[1] = paramArrayOfInt[1];
      }
      else if ((paramArrayOfInt[0] < paramArrayOfInt[1]) && (paramArrayOfInt[1] >= 300))
      {
        arrayOfInt[0] = ((int)(paramArrayOfInt[0] / paramArrayOfInt[1] * 300.0F));
        arrayOfInt[1] = 300;
      }
      else if ((paramArrayOfInt[0] < paramArrayOfInt[1]) && (paramArrayOfInt[0] < 300))
      {
        arrayOfInt[0] = paramArrayOfInt[0];
        arrayOfInt[1] = paramArrayOfInt[1];
      }
      else if ((paramArrayOfInt[0] == paramArrayOfInt[1]) && (paramArrayOfInt[0] >= 300))
      {
        arrayOfInt[0] = 300;
        arrayOfInt[1] = 300;
      }
      else if ((paramArrayOfInt[0] == paramArrayOfInt[1]) && (paramArrayOfInt[0] < 300))
      {
        arrayOfInt[0] = paramArrayOfInt[0];
        arrayOfInt[1] = paramArrayOfInt[1];
      }
    }
  }

  private View initview()
  {
    this.viewroot = new LinearLayout(this);
    Object localObject1 = new LinearLayout.LayoutParams(-1, -1);
    Object localObject2 = new FrameLayout.LayoutParams(-2, -2);
    Object localObject3 = new RelativeLayout.LayoutParams(-2, -2);
    this.viewroot.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.viewroot.setOrientation(1);
    Object localObject5 = new LinearLayout.LayoutParams(-1, -2);
    Object localObject4 = new RelativeLayout.LayoutParams(-1, -2);
    localObject1 = new RelativeLayout(this);
    ((RelativeLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject4);
    ((RelativeLayout)localObject1).setBackgroundDrawable(BackGroudSeletor.getdrawble("up_bg2x", getApplication()));
    ((RelativeLayout)localObject1).setGravity(0);
    this.button_esc = new Button(this);
    ((RelativeLayout.LayoutParams)localObject3).addRule(9, -1);
    ((RelativeLayout.LayoutParams)localObject3).addRule(15, -1);
    ((RelativeLayout.LayoutParams)localObject3).topMargin = 10;
    ((RelativeLayout.LayoutParams)localObject3).leftMargin = 10;
    ((RelativeLayout.LayoutParams)localObject3).bottomMargin = 10;
    this.button_esc.setLayoutParams((ViewGroup.LayoutParams)localObject3);
    this.button_esc.setText("取消");
    this.button_esc.setClickable(true);
    this.button_esc.setId(5001);
    this.button_esc.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[] { "quxiao_btn2x", "quxiao_btn_hover" }, this));
    this.button_send = new Button(this);
    localObject3 = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject3).addRule(11, -1);
    ((RelativeLayout.LayoutParams)localObject3).addRule(15, -1);
    ((RelativeLayout.LayoutParams)localObject3).topMargin = 10;
    ((RelativeLayout.LayoutParams)localObject3).rightMargin = 10;
    ((RelativeLayout.LayoutParams)localObject3).bottomMargin = 10;
    this.button_send.setLayoutParams((ViewGroup.LayoutParams)localObject3);
    localObject3 = new LinearLayout(this);
    localObject4 = new LinearLayout.LayoutParams(-2, -2, 1.0F);
    ((LinearLayout)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject4);
    this.button_send.setText("发送");
    this.button_send.setClickable(true);
    this.button_send.setId(5002);
    this.button_send.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[] { "sent_btn_22x", "sent_btn_hover" }, this));
    ((RelativeLayout)localObject1).addView(this.button_esc);
    ((RelativeLayout)localObject1).addView(this.button_send);
    localObject3 = new LinearLayout(this);
    ((LinearLayout)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject5);
    ((LinearLayout)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject5);
    ((LinearLayout)localObject3).setOrientation(1);
    ((LinearLayout)localObject3).setBackgroundColor(-1);
    ((LinearLayout)localObject3).requestFocus();
    this.editText_text = new EditText(this);
    this.editText_text.setBackgroundColor(-1);
    this.editText_text.setMaxLines(4);
    this.editText_text.setMinLines(4);
    this.editText_text.setMinEms(4);
    this.editText_text.setMaxEms(4);
    this.editText_text.setFocusable(true);
    this.editText_text.requestFocus();
    this.editText_text.setText(this.edstring);
    this.editText_text.setSelection(this.edstring.length());
    this.editText_text.setScrollbarFadingEnabled(true);
    this.editText_text.setGravity(48);
    this.editText_text.setMovementMethod(ScrollingMovementMethod.getInstance());
    this.editText_text.setId(5003);
    this.frameLayout_icon = new FrameLayout(this);
    this.frameLayout_icon.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    LinearLayout localLinearLayout1 = new LinearLayout(this);
    localLinearLayout1.setGravity(21);
    localLinearLayout1.setLayoutParams(new LinearLayout.LayoutParams(54, 45));
    localLinearLayout1.setPadding(0, 0, 2, 0);
    this.imageView_icon = new ImageView(this);
    this.imageView_icon.setId(5004);
    this.imageView_bound = new ImageView(this);
    this.imageView_bound.setId(5005);
    this.imageView_bound.setLayoutParams(new LinearLayout.LayoutParams(54, 45));
    this.imageView_icon.setLayoutParams(new LinearLayout.LayoutParams(33, 33));
    this.imageView_bound.setImageDrawable(BackGroudSeletor.getdrawble("composeimageframe", this));
    this.frameLayout_icon.setVisibility(8);
    localLinearLayout1.addView(this.imageView_icon);
    this.frameLayout_icon.addView(localLinearLayout1);
    this.frameLayout_icon.addView(this.imageView_bound);
    ((LinearLayout)localObject3).addView(this.editText_text);
    ((LinearLayout)localObject3).addView(this.frameLayout_icon);
    this.layout_set = new LinearLayout(this);
    this.layout_set.setLayoutParams((ViewGroup.LayoutParams)localObject5);
    this.layout_set.setBackgroundDrawable(BackGroudSeletor.getdrawble("icon_bg2x", this));
    this.layout_set.setOrientation(0);
    this.layout_set.setGravity(16);
    this.layout_set.setPadding(10, 0, 30, 0);
    localObject5 = new LinearLayout(this);
    ((LinearLayout)localObject5).setOrientation(0);
    ((LinearLayout)localObject5).setLayoutParams((ViewGroup.LayoutParams)localObject4);
    localLinearLayout1 = new LinearLayout(this);
    localLinearLayout1.setGravity(1);
    localLinearLayout1.setLayoutParams((ViewGroup.LayoutParams)localObject4);
    LinearLayout localLinearLayout2 = new LinearLayout(this);
    localLinearLayout2.setGravity(1);
    localLinearLayout2.setLayoutParams((ViewGroup.LayoutParams)localObject4);
    LinearLayout localLinearLayout3 = new LinearLayout(this);
    localLinearLayout3.setGravity(1);
    localLinearLayout3.setLayoutParams((ViewGroup.LayoutParams)localObject4);
    LinearLayout localLinearLayout4 = new LinearLayout(this);
    localLinearLayout4.setGravity(1);
    localLinearLayout4.setLayoutParams((ViewGroup.LayoutParams)localObject4);
    this.button_friend = new ImageButton(this);
    this.button_friend.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    this.button_friend.setId(5006);
    this.button_conversation = new ImageButton(this);
    this.button_conversation.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    this.button_conversation.setId(5007);
    this.button_camera = new ImageButton(this);
    this.button_camera.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    this.button_camera.setId(5008);
    this.button_location = new ImageButton(this);
    this.button_location.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    this.button_location.setId(5009);
    this.button_friend.setBackgroundDrawable(BackGroudSeletor.getdrawble("haoyou_icon2x", this));
    this.button_conversation.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[] { "huati_icon2x", "huati_icon_hover2x" }, this));
    this.button_camera.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[] { "pic_icon2x", "pic_icon_hover2x" }, this));
    this.button_location.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[] { "dingwei_icon2x", "dingwei_icon_hover2x" }, this));
    localLinearLayout1.addView(this.button_friend);
    ((LinearLayout)localObject5).addView(localLinearLayout1);
    localLinearLayout2.addView(this.button_conversation);
    ((LinearLayout)localObject5).addView(localLinearLayout2);
    localLinearLayout3.addView(this.button_camera);
    ((LinearLayout)localObject5).addView(localLinearLayout3);
    localLinearLayout4.addView(this.button_location);
    ((LinearLayout)localObject5).addView(localLinearLayout4);
    this.textView_num = new TextView(this);
    this.textView_num.setText("140");
    this.textView_num.setTextColor(Color.parseColor("#999999"));
    this.textView_num.setGravity(5);
    this.textView_num.setLayoutParams((ViewGroup.LayoutParams)localObject4);
    this.textView_num.setId(5010);
    this.textView_num.setWidth(40);
    localObject2 = new LinearLayout(this);
    ((LinearLayout)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject4);
    ((LinearLayout)localObject2).setGravity(21);
    ((LinearLayout)localObject2).addView(this.textView_num);
    this.layout_set.addView((View)localObject5);
    this.layout_set.addView((View)localObject2);
    localObject2 = new LinearLayout(this);
    ((LinearLayout)localObject2).setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0F));
    ((LinearLayout)localObject2).setGravity(17);
    ((LinearLayout)localObject2).setBackgroundDrawable(BackGroudSeletor.getdrawble("bg", this));
    this.frameLayout_big = new FrameLayout(this);
    localObject4 = new FrameLayout.LayoutParams(-2, -2);
    this.frameLayout_big.setLayoutParams((ViewGroup.LayoutParams)localObject4);
    this.frameLayout_big.setPadding(10, 10, 0, 0);
    this.layout_imagebound = new LinearLayout(this);
    this.layout_imagebound.setPadding(2, 2, 2, 2);
    this.layout_imagebound.setBackgroundDrawable(BackGroudSeletor.getdrawble("pic_biankuang2x", this));
    this.layout_big_delete = new LinearLayout(this);
    localObject4 = new LinearLayout.LayoutParams(getarea(this.lyout)[0] + 10, getarea(this.lyout)[1] + 10);
    this.layout_big_delete.setLayoutParams((ViewGroup.LayoutParams)localObject4);
    this.layout_imagebound.setGravity(17);
    this.layout_imagebound.setId(5011);
    this.layout_imagebound.setLayoutParams(new LinearLayout.LayoutParams(getarea(this.lyout)[0], getarea(this.lyout)[1]));
    this.imageView_big = new ImageView(this);
    this.imageView_big.setId(5012);
    this.layout_imagebound.addView(this.imageView_big);
    this.imageView_delete = new ImageView(this);
    this.imageView_delete.setId(5013);
    this.imageView_delete.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    this.imageView_delete.setImageDrawable(BackGroudSeletor.getdrawble("close", this));
    this.layout_big_delete.addView(this.imageView_delete);
    this.frameLayout_big.addView(this.layout_imagebound);
    this.frameLayout_big.addView(this.layout_big_delete);
    this.frameLayout_big.setVisibility(8);
    ((LinearLayout)localObject2).addView(this.frameLayout_big);
    this.viewroot.addView((View)localObject1);
    this.viewroot.addView((View)localObject3);
    this.viewroot.addView(this.layout_set);
    this.viewroot.addView((View)localObject2);
    return this.viewroot;
  }

  private void setonclick()
  {
    this.button_esc.setOnClickListener(this);
    this.button_send.setOnClickListener(this);
    this.editText_text.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        try
        {
          PublishActivity.this.edstring = paramAnonymousEditable.toString();
          paramAnonymousEditable = 140 - paramAnonymousEditable.toString().getBytes("gbk").length / 2;
          Log.d("contentafter", paramAnonymousEditable);
          PublishActivity.this.textView_num.setText(paramAnonymousEditable);
          return;
        }
        catch (UnsupportedEncodingException paramAnonymousEditable)
        {
          paramAnonymousEditable.printStackTrace();
        }
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        try
        {
          Log.d("contentafter", paramAnonymousCharSequence.toString().getBytes("gbk").length);
          return;
        }
        catch (UnsupportedEncodingException paramAnonymousCharSequence)
        {
          paramAnonymousCharSequence.printStackTrace();
        }
      }
    });
    this.imageView_bound.setOnClickListener(this);
    this.imageView_delete.setOnClickListener(this);
    this.button_friend.setOnClickListener(this);
    this.button_conversation.setOnClickListener(this);
    this.button_camera.setOnClickListener(this);
    this.button_location.setOnClickListener(this);
  }

  private View showView()
  {
    LinearLayout localLinearLayout1 = new LinearLayout(this);
    localLinearLayout1.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    localLinearLayout1.setBackgroundDrawable(BackGroudSeletor.getdrawble("bg", this));
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setPadding(50, 50, 50, 50);
    localLinearLayout1.setGravity(17);
    localLinearLayout1.requestFocus();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    LinearLayout localLinearLayout2 = new LinearLayout(this);
    localLinearLayout2.setLayoutParams(localLayoutParams);
    localLinearLayout2.setPadding(0, 0, 0, 0);
    localLinearLayout2 = new LinearLayout(this);
    localLinearLayout2.setLayoutParams(localLayoutParams);
    localLinearLayout2.setPadding(0, 10, 0, 30);
    new LinearLayout(this);
    localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    Button localButton1 = new Button(this);
    localButton1.setId(5014);
    localButton1.setOnClickListener(this);
    localButton1.setLayoutParams(localLayoutParams);
    localButton1.setText("拍照");
    Object localObject = new String[2];
    localObject[0] = "btn1_";
    localObject[1] = "btn1_hover_";
    localButton1.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds((String[])localObject, this));
    Button localButton2 = new Button(this);
    localButton2.setId(5015);
    localButton2.setOnClickListener(this);
    localButton2.setLayoutParams(localLayoutParams);
    localButton2.setText("相册");
    localButton2.setBackgroundDrawable(BackGroudSeletor.createBgByImageIds((String[])localObject, this));
    localObject = new Button(this);
    ((Button)localObject).setId(5016);
    ((Button)localObject).setOnClickListener(this);
    ((Button)localObject).setLayoutParams(localLayoutParams);
    ((Button)localObject).setText("取消");
    ((Button)localObject).setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[] { "btn2_", "btn1_hover_" }, this));
    localLinearLayout2.addView(localButton2);
    localLinearLayout1.addView(localButton1);
    localLinearLayout1.addView(localLinearLayout2);
    localLinearLayout1.addView((View)localObject);
    return localLinearLayout1;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Object localObject1;
    Object localObject2;
    if ((paramInt1 == 1000) && (paramInt2 == -1) && (paramIntent != null))
    {
      paramIntent = paramIntent.getData();
      localObject1 = new String[1];
      localObject1[0] = "_data";
      paramIntent = getContentResolver().query(paramIntent, (String[])localObject1, null, null, null);
      paramIntent.moveToFirst();
      localObject1 = paramIntent.getString(paramIntent.getColumnIndex(localObject1[0]));
      Log.d("path", localObject1);
      localObject2 = new int[2];
    }
    do
    {
      try
      {
        localObject1 = new FileInputStream((String)localObject1);
        localObject2 = new BitmapFactory.Options();
        ((BitmapFactory.Options)localObject2).inJustDecodeBounds = false;
        ((BitmapFactory.Options)localObject2).inSampleSize = 6;
        localObject1 = BitmapFactory.decodeStream((InputStream)localObject1, null, (BitmapFactory.Options)localObject2);
        localObject2 = new ByteArrayOutputStream();
        ((Bitmap)localObject1).compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream)localObject2);
        this.mBitmap = ((Bitmap)localObject1);
        this.lyout[0] = ((Bitmap)localObject1).getWidth();
        this.lyout[1] = ((Bitmap)localObject1).getHeight();
        setContentView(initview());
        setonclick();
        this.imageView_icon.setImageDrawable(new BitmapDrawable((Bitmap)localObject1));
        this.imageView_big.setImageDrawable(new BitmapDrawable((Bitmap)localObject1));
        this.frameLayout_icon.setVisibility(0);
        this.frameLayout_big.setVisibility(0);
        paramIntent.close();
        if ((this.popupWindow != null) && (this.popupWindow.isShowing()))
        {
          this.popupWindow.dismiss();
          new Timer().schedule(new TimerTask()
          {
            public void run()
            {
              Object localObject = (InputMethodManager)PublishActivity.this.getSystemService("input_method");
              Log.d("mks", ((InputMethodManager)localObject).isActive());
              ((InputMethodManager)localObject).toggleSoftInput(0, 2);
              localObject = PublishActivity.this.handler.obtainMessage();
              ((Message)localObject).what = 5;
              PublishActivity.this.handler.sendMessage((Message)localObject);
            }
          }
          , 100L);
        }
        return;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        while (true)
          localFileNotFoundException.printStackTrace();
      }
      catch (IOException localIOException)
      {
        while (true)
          localIOException.printStackTrace();
      }
      if ((paramInt1 == 2000) && (paramInt2 == -1) && (paramIntent != null))
      {
        if ((this.popupWindow != null) && (this.popupWindow.isShowing()))
          this.popupWindow.dismiss();
        paramIntent = (Bitmap)paramIntent.getExtras().get("data");
        sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        paramIntent.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
        this.mBitmap = paramIntent;
        this.lyout[0] = paramIntent.getWidth();
        this.lyout[1] = paramIntent.getHeight();
        setContentView(initview());
        setonclick();
        this.imageView_icon.setImageDrawable(new BitmapDrawable(paramIntent));
        this.imageView_big.setImageDrawable(new BitmapDrawable(paramIntent));
        this.frameLayout_icon.setVisibility(0);
        this.frameLayout_big.setVisibility(0);
        return;
      }
      if ((paramInt1 == 5007) && (paramInt2 == -1) && (paramIntent != null))
      {
        this.edstring += paramIntent.getStringExtra("conversation");
        this.editText_text.setText(this.edstring);
        this.editText_text.setSelection(this.edstring.length());
        return;
      }
    }
    while ((paramInt1 != 5006) || (paramInt2 != -1) || (paramIntent == null));
    this.edstring = (this.edstring + "@" + paramIntent.getStringExtra("firend"));
    this.editText_text.setText(this.edstring);
    this.editText_text.setSelection(this.edstring.length());
  }

  public void onClick(View paramView)
  {
    final InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    switch (paramView.getId())
    {
    case 5003:
    case 5004:
    case 5010:
    case 5011:
    case 5012:
    default:
    case 5001:
    case 5002:
    case 5005:
    case 5006:
    case 5007:
    case 5008:
    case 5009:
    case 5013:
    case 5014:
    case 5015:
    case 5016:
    }
    do
    {
      do
      {
        double d1;
        double d2;
        do
        {
          return;
          localInputMethodManager.hideSoftInputFromWindow(this.editText_text.getWindowToken(), 0);
          finish();
          return;
          paramView = this.editText_text.getText().toString();
          if (("".equals(paramView)) && (this.frameLayout_icon.getVisibility() == 8))
          {
            Toast.makeText(this, "无内容发送", 0).show();
            return;
          }
          if ((this.dialog != null) && (!this.dialog.isShowing()))
            this.dialog.show();
          if (Integer.parseInt(this.textView_num.getText().toString()) < 0)
          {
            Toast.makeText(this, "请重新输入少于140个字的内容", 0).show();
            return;
          }
          d1 = 0.0D;
          d2 = 0.0D;
          if (this.mLocation != null)
          {
            d1 = this.mLocation.getLongitude();
            d2 = this.mLocation.getLatitude();
          }
          if (!this.frameLayout_icon.isShown())
          {
            this.weiboAPI.addWeibo(this.context, paramView, "json", d1, d2, 0, 0, this, null, 4);
            return;
          }
        }
        while (this.frameLayout_icon.getVisibility() != 0);
        this.weiboAPI.addPic(this.context, paramView, "json", d1, d2, this.mBitmap, 0, 0, this, null, 4);
        return;
        localInputMethodManager.toggleSoftInput(0, 2);
        return;
        localInputMethodManager.hideSoftInputFromWindow(this.editText_text.getWindowToken(), 0);
        paramView = new Intent();
        paramView.setClass(this, FriendActivity.class);
        startActivityForResult(paramView, 5006);
        return;
        localInputMethodManager.hideSoftInputFromWindow(this.editText_text.getWindowToken(), 0);
        paramView = new Intent();
        paramView.setClass(this, ConversationActivity.class);
        startActivityForResult(paramView, 5007);
        return;
        if ((this.popupWindow == null) || (!this.popupWindow.isShowing()))
          break;
        this.popupWindow.dismiss();
      }
      while (!localInputMethodManager.isActive());
      localInputMethodManager.toggleSoftInput(0, 2);
      return;
      this.popupWindow = new PopupWindow(showView(), -1, -1);
      this.popupWindow.setTouchable(true);
      new Timer().schedule(new TimerTask()
      {
        public void run()
        {
          Message localMessage = PublishActivity.this.handler.obtainMessage();
          localMessage.what = 0;
          PublishActivity.this.handler.sendMessage(localMessage);
        }
      }
      , 500L);
      return;
      new Thread(new Runnable()
      {
        public void run()
        {
          Looper.prepare();
          Message localMessage = PublishActivity.this.handler.obtainMessage();
          localMessage.what = 15;
          if (PublishActivity.this.mLocation == null)
          {
            PublishActivity.this.mLocation = Util.getLocation(PublishActivity.this.context);
            if (PublishActivity.this.mLocation != null)
              localMessage.what = 10;
          }
          PublishActivity.this.handler.sendMessage(localMessage);
          Looper.loop();
        }
      }).start();
      return;
      this.frameLayout_icon.setVisibility(4);
      this.frameLayout_big.setVisibility(8);
      return;
      this.edstring = this.editText_text.getText().toString();
      startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), 2000);
      return;
      this.edstring = this.editText_text.getText().toString();
      startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1000);
      return;
    }
    while ((this.popupWindow == null) || (!this.popupWindow.isShowing()));
    this.popupWindow.dismiss();
    this.editText_text.requestFocus();
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        if (localInputMethodManager.isActive())
          localInputMethodManager.toggleSoftInput(0, 2);
      }
    }
    , 100L);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    this.accessToken = Util.getSharePersistent(getApplicationContext(), "ACCESS_TOKEN");
    if ((this.accessToken == null) || ("".equals(this.accessToken)))
    {
      Toast.makeText(this, "请先授权", 0).show();
      finish();
      return;
    }
    this.context = getApplicationContext();
    this.weiboAPI = new WeiboAPI(new AccountModel(this.accessToken));
    this.lyout[0] = BackGroudSeletor.getdrawble("test2x", this).getMinimumWidth();
    this.lyout[1] = BackGroudSeletor.getdrawble("test2x", this).getMinimumHeight();
    paramBundle = (LinearLayout)initview();
    this.dialog = new ProgressDialog(this);
    this.dialog.setMessage("正在发送请稍后......");
    setContentView(paramBundle);
    setonclick();
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        ((InputMethodManager)PublishActivity.this.getSystemService("input_method")).showSoftInput(PublishActivity.this.editText_text, 2);
      }
    }
    , 400L);
  }

  public void onResult(Object paramObject)
  {
    if ((this.dialog != null) && (this.dialog.isShowing()))
      this.dialog.dismiss();
    ModelResult localModelResult;
    if (paramObject != null)
    {
      localModelResult = (ModelResult)paramObject;
      if (localModelResult.isExpires())
        Toast.makeText(this, localModelResult.getError_message(), 0).show();
    }
    else
    {
      return;
    }
    if (localModelResult.isSuccess())
    {
      Toast.makeText(this, "发送成功", 4000).show();
      Log.d("发送成功", paramObject.toString());
      finish();
      return;
    }
    Toast.makeText(this, ((ModelResult)paramObject).getError_message(), 4000).show();
  }

  protected void onResume()
  {
    super.onResume();
    final InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    if ((this.popupWindow != null) && (this.popupWindow.isShowing()))
    {
      Log.d("mkl", localInputMethodManager.isActive());
      localInputMethodManager.hideSoftInputFromWindow(this.editText_text.getWindowToken(), 0);
    }
    while (this.location != null)
    {
      this.button_location.setBackgroundDrawable(BackGroudSeletor.getdrawble("dingwei_icon_hover2x", this));
      return;
      new Timer().schedule(new TimerTask()
      {
        public void run()
        {
          localInputMethodManager.showSoftInput(PublishActivity.this.editText_text, 2);
        }
      }
      , 400L);
    }
    this.button_location.setBackgroundDrawable(BackGroudSeletor.getdrawble("dingwei_icon2x", this));
  }

  public Bitmap zoomImage(Bitmap paramBitmap, double paramDouble1, double paramDouble2)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale((float)paramDouble1 / f1, (float)paramDouble2 / f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, (int)f1, (int)f2, localMatrix, true);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.PublishActivity
 * JD-Core Version:    0.6.2
 */