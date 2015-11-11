package fm.qingting.qtradio.view.virtualchannels;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.UserProfile;

public class UploadVoiceInputFrameView extends ViewGroupViewImpl
  implements IEventHandler
{
  private static final int mNameMaxLength = 30;
  private final ViewLayout btnLayout = this.standardLayout.createChildLT(100, 55, 375, 102, ViewLayout.SCALE_FLAG_SLTCW);
  private TextView countText;
  private final ViewLayout editLayout = this.standardLayout.createChildLT(470, 90, 5, 5, ViewLayout.SCALE_FLAG_SLTCW);
  private EditText editText;
  private Button sendButton;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 160, 480, 160, 0, 0, ViewLayout.FILL);
  private final ViewLayout textLayout = this.standardLayout.createChildLT(480, 60, 0, 100, ViewLayout.SCALE_FLAG_SLTCW);

  public UploadVoiceInputFrameView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(Color.parseColor("#f4f4f4"));
    this.sendButton = new Button(paramContext);
    this.sendButton.setBackgroundResource(2130837905);
    this.sendButton.setText("发布");
    this.sendButton.setTextColor(Color.parseColor("#f1f4f1"));
    this.sendButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UploadVoiceInputFrameView.this.update("doSend", null);
      }
    });
    addView(this.sendButton);
    this.editText = new EditText(paramContext);
    String str3 = "我的录音";
    try
    {
      String str4 = InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_name;
      String str1 = str3;
      if (str4 != null)
      {
        str1 = str3;
        if (!str4.equalsIgnoreCase(""))
          str1 = str4 + "的录音";
      }
      this.editText.setHint("请输入录音名称... 例如：" + str1);
      this.editText.setText(str1);
      this.editText.setGravity(51);
      this.editText.setHintTextColor(SkinManager.getTextColorSubInfo());
      this.editText.setBackgroundResource(2130837904);
      this.editText.clearFocus();
      this.editText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(30) });
      this.editText.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable)
        {
        }

        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
        }

        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          UploadVoiceInputFrameView.this.countText.setText(String.format("%d / %d", new Object[] { Integer.valueOf(paramAnonymousCharSequence.length()), Integer.valueOf(30) }));
        }
      });
      addView(this.editText);
      this.countText = new TextView(paramContext);
      this.countText.setTextColor(-16777216);
      this.countText.setGravity(19);
      this.countText.setPadding(10, 0, 0, 0);
      this.countText.setText(String.format("%d / %d", new Object[] { Integer.valueOf(str1.length()), Integer.valueOf(30) }));
      addView(this.countText);
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        String str2 = str3;
      }
    }
  }

  public boolean dispatchKeyEventPreIme(KeyEvent paramKeyEvent)
  {
    int i = paramKeyEvent.getKeyCode();
    if (((i == 82) || (i == 4)) && (paramKeyEvent.getAction() == 1))
    {
      dispatchActionEvent("cancelSend", null);
      return true;
    }
    return false;
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.editLayout.layoutView(this.editText);
    this.textLayout.layoutView(this.countText);
    this.btnLayout.layoutView(this.sendButton);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.editLayout.scaleToBounds(this.standardLayout);
    this.textLayout.scaleToBounds(this.standardLayout);
    this.btnLayout.scaleToBounds(this.standardLayout);
    this.editLayout.measureView(this.editText);
    this.textLayout.measureView(this.countText);
    this.btnLayout.measureView(this.sendButton);
    this.editText.setTextSize(0, 0.3F * this.editLayout.height);
    this.countText.setTextSize(0, 0.4F * this.textLayout.height);
    this.sendButton.setTextSize(0, 0.5F * this.btnLayout.height);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("show"))
    {
      this.editText.requestFocus();
      ((InputMethodManager)getContext().getSystemService("input_method")).showSoftInput(this.editText, 0);
    }
    if (paramString.equalsIgnoreCase("hide"))
      ((InputMethodManager)getContext().getSystemService("input_method")).hideSoftInputFromWindow(getApplicationWindowToken(), 0);
    if (paramString.equalsIgnoreCase("doSend"))
    {
      paramString = this.editText.getText().toString().trim();
      if (paramString.length() > 0)
        dispatchActionEvent("doSend", paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.UploadVoiceInputFrameView
 * JD-Core Version:    0.6.2
 */