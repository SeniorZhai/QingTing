package fm.qingting.qtradio.view.im.chat;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.R.drawable;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.view.chatroom.ExpressionSpan;
import fm.qingting.qtradio.view.chatroom.expression.ExpressionItem;
import fm.qingting.qtradio.view.im.ChatMode;
import fm.qingting.utils.ExpressionUtil;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ScreenConfiguration;
import java.lang.reflect.Field;

public class ImChatInputView extends ViewGroupViewImpl
{
  private final ViewLayout editLayout = this.standardLayout.createChildLT(565, 79, 15, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout expressionLayout = this.standardLayout.createChildLT(49, 49, 37, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout inviteLayout = this.standardLayout.createChildLT(64, 49, 625, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(572, 8, 15, 81, ViewLayout.SCALE_FLAG_SLTCW);
  private EditText mEditText;
  private Button mExpressionButton;
  private boolean mHasText = false;
  private Button mInviteButton;
  private ImageView mLineView;
  private Button mPlusButton;
  private TextView mSendView;
  private final ViewLayout plusLayout = this.standardLayout.createChildLT(48, 48, 117, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 106, 720, 106, 0, 0, ViewLayout.FILL);

  public ImChatInputView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-1);
    View.OnClickListener local1 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramAnonymousView == ImChatInputView.this.mSendView)
          if (ImChatInputView.this.mHasText)
            ImChatInputView.this.sendDiscuss();
        do
        {
          return;
          if (paramAnonymousView == ImChatInputView.this.mExpressionButton)
          {
            ImChatInputView.this.dispatchActionEvent("expression", null);
            QTMSGManage.getInstance().sendStatistcsMessage("chatroom_express");
            return;
          }
          if (paramAnonymousView == ImChatInputView.this.mPlusButton)
          {
            ImChatInputView.this.dispatchActionEvent("expand", null);
            QTMSGManage.getInstance().sendStatistcsMessage("chatroom_func");
            return;
          }
        }
        while (paramAnonymousView != ImChatInputView.this.mInviteButton);
        ImChatInputView.this.dispatchActionEvent("inviteFriends", null);
        QTMSGManage.getInstance().sendStatistcsMessage("chatroom_invitefriends");
      }
    };
    this.mExpressionButton = new Button(paramContext);
    this.mExpressionButton.setBackgroundResource(2130837577);
    addView(this.mExpressionButton);
    this.mExpressionButton.setOnClickListener(local1);
    this.mPlusButton = new Button(paramContext);
    this.mPlusButton.setBackgroundResource(2130837589);
    addView(this.mPlusButton);
    this.mPlusButton.setOnClickListener(local1);
    this.mPlusButton.setVisibility(8);
    this.mInviteButton = new Button(paramContext);
    this.mInviteButton.setBackgroundResource(2130837587);
    addView(this.mInviteButton);
    this.mInviteButton.setOnClickListener(local1);
    this.mSendView = new TextView(paramContext);
    this.mSendView.setText("发送");
    this.mSendView.setTextColor(SkinManager.getTextColorNormal());
    this.mSendView.setGravity(17);
    addView(this.mSendView);
    this.mSendView.setOnClickListener(local1);
    this.mLineView = new ImageView(paramContext);
    this.mLineView.setBackgroundResource(2130837583);
    this.mLineView.setScaleType(ImageView.ScaleType.FIT_XY);
    addView(this.mLineView);
    this.mEditText = new EditText(paramContext);
    this.mEditText.setBackgroundResource(0);
    this.mEditText.setGravity(19);
    this.mEditText.setTextColor(SkinManager.getTextColorNormal());
    this.mEditText.setMaxLines(3);
    this.mEditText.clearFocus();
    this.mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          if (!CloudCenter.getInstance().isLogin(false))
          {
            EventDispacthManager.getInstance().dispatchAction("showLogin", null);
            ImChatInputView.this.mEditText.clearFocus();
            return;
          }
          ImChatInputView.this.mLineView.setImageResource(2130837584);
          return;
        }
        ImChatInputView.this.mLineView.setImageResource(2130837583);
      }
    });
    this.mEditText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if (paramAnonymousCharSequence.length() > 0)
        {
          if ((!ImChatInputView.this.mHasText) && (ChatMode.isGroup()))
          {
            ImChatInputView.this.mSendView.setVisibility(0);
            ImChatInputView.this.mInviteButton.setVisibility(4);
          }
          ImChatInputView.access$102(ImChatInputView.this, true);
          return;
        }
        if ((ImChatInputView.this.mHasText) && (ChatMode.isGroup()))
        {
          ImChatInputView.this.mSendView.setVisibility(4);
          ImChatInputView.this.mInviteButton.setVisibility(0);
        }
        ImChatInputView.access$102(ImChatInputView.this, false);
      }
    });
    addView(this.mEditText);
  }

  private boolean closeKeyBoard()
  {
    return ((InputMethodManager)getContext().getSystemService("input_method")).hideSoftInputFromWindow(getApplicationWindowToken(), 0);
  }

  private int getDesiredHeight()
  {
    if (this.mEditText.getMeasuredHeight() > this.editLayout.height)
      return this.standardLayout.height + this.mEditText.getMeasuredHeight() - this.editLayout.height;
    return this.standardLayout.height;
  }

  private void openKeyBoard()
  {
    ((InputMethodManager)getContext().getSystemService("input_method")).showSoftInput(this.mEditText, 0);
  }

  private void sendDiscuss()
  {
    dispatchActionEvent("sendDiscuss", this.mEditText.getText().toString());
    this.mEditText.setText("");
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = 0;
    if (this.mEditText.getMeasuredHeight() > this.editLayout.height)
      paramInt1 = this.mEditText.getMeasuredHeight() - this.editLayout.height;
    this.mExpressionButton.layout(this.expressionLayout.leftMargin, this.expressionLayout.topMargin + paramInt1, this.expressionLayout.getRight(), this.expressionLayout.getBottom() + paramInt1);
    this.mPlusButton.layout(this.plusLayout.leftMargin, this.plusLayout.topMargin + paramInt1, this.plusLayout.getRight(), this.plusLayout.getBottom() + paramInt1);
    this.mInviteButton.layout(this.inviteLayout.leftMargin, this.inviteLayout.topMargin + paramInt1, this.inviteLayout.getRight(), this.inviteLayout.getBottom() + paramInt1);
    this.mLineView.layout(this.lineLayout.leftMargin, this.lineLayout.topMargin + paramInt1, this.lineLayout.getRight(), this.lineLayout.getBottom() + paramInt1);
    this.mSendView.layout(this.inviteLayout.leftMargin, this.inviteLayout.topMargin + paramInt1, this.inviteLayout.getRight(), paramInt1 + this.inviteLayout.getBottom());
    if (this.mPlusButton.getVisibility() == 8);
    for (paramInt1 = this.expressionLayout.getRight() + this.editLayout.leftMargin; ; paramInt1 = this.plusLayout.getRight() + this.editLayout.leftMargin)
    {
      this.mEditText.layout(paramInt1, this.editLayout.topMargin, this.editLayout.getRight(), this.editLayout.topMargin + this.mEditText.getMeasuredHeight());
      return;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.expressionLayout.scaleToBounds(this.standardLayout);
    this.inviteLayout.scaleToBounds(this.standardLayout);
    this.plusLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.editLayout.scaleToBounds(this.standardLayout);
    this.expressionLayout.measureView(this.mExpressionButton);
    this.lineLayout.measureView(this.mLineView);
    this.plusLayout.measureView(this.mPlusButton);
    this.inviteLayout.measureView(this.mInviteButton);
    this.inviteLayout.measureView(this.mSendView);
    this.mSendView.setTextSize(0, SkinManager.getInstance().getMiddleTextSize());
    this.mEditText.setTextSize(0, SkinManager.getInstance().getMiddleTextSize());
    this.mEditText.setPadding(0, (int)(this.editLayout.height * 0.4F), 0, 0);
    if (this.mPlusButton.getVisibility() == 8);
    for (paramInt1 = this.editLayout.width - this.expressionLayout.getRight(); ; paramInt1 = this.editLayout.width - this.plusLayout.getRight())
    {
      this.mEditText.measure(View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824), View.MeasureSpec.makeMeasureSpec(this.editLayout.height, 0));
      setMeasuredDimension(this.standardLayout.width, getDesiredHeight());
      return;
    }
  }

  public void update(String paramString, Object paramObject)
  {
    int j = 0;
    if (paramString.equalsIgnoreCase("atTa"))
    {
      paramString = (String)paramObject;
      this.mEditText.getEditableText().append("@" + paramString + " ");
      this.mEditText.setSelection(this.mEditText.getText().length());
      this.mEditText.requestFocus();
      openKeyBoard();
    }
    int k;
    do
    {
      return;
      if (paramString.equalsIgnoreCase("closeKeyboard"))
      {
        this.mEditText.clearFocus();
        closeKeyBoard();
        return;
      }
      if (paramString.equalsIgnoreCase("openKeyboard"))
      {
        this.mEditText.requestFocus();
        openKeyBoard();
        return;
      }
      if (paramString.equalsIgnoreCase("enterReportSn"))
      {
        this.mEditText.setHint("输入歌名...");
        this.mEditText.requestFocus();
        openKeyBoard();
        return;
      }
      if (paramString.equalsIgnoreCase("exitReport"))
      {
        this.mEditText.setHint("");
        this.mEditText.setText("");
        return;
      }
      if (!paramString.equalsIgnoreCase("deleteExpression"))
        break;
      k = this.mEditText.getSelectionStart();
    }
    while (k == 0);
    while (true)
    {
      int i;
      while (true)
      {
        Editable localEditable;
        while (true)
        {
          try
          {
            paramString = this.mEditText.getEditableText();
            if (paramString.charAt(k - 1) == ']')
            {
              i = k - 2;
              if ((i > -1) && (paramString.charAt(i) != '['))
                break label742;
              paramObject = (ExpressionSpan[])paramString.getSpans(i, k - 1, ExpressionSpan.class);
              if (paramObject != null)
              {
                if (j < paramObject.length)
                {
                  paramString.removeSpan(paramObject[j]);
                  j += 1;
                  continue;
                }
                paramString.delete(i, k);
                return;
              }
            }
          }
          catch (Exception paramString)
          {
            paramString.printStackTrace();
            return;
          }
          paramString.delete(k - 1, k);
          return;
          if (!paramString.equalsIgnoreCase("addExpression"))
            break label635;
          paramObject = (ExpressionItem)paramObject;
          paramString = paramObject.getExpressionName();
          paramObject = paramObject.getExpressionIcon();
          i = this.mEditText.getSelectionStart();
          localEditable = this.mEditText.getEditableText();
          if ((i < 0) || (i >= localEditable.length()))
          {
            localEditable.append(paramString);
            try
            {
              j = Integer.parseInt(R.drawable.class.getDeclaredField(paramObject).get(null).toString());
              if (j == 0)
                break;
              k = ScreenConfiguration.getEmojiSize();
              localEditable.setSpan(new ExpressionSpan(getResources(), j, k, k, ExpressionUtil.getInstance().getOwnerId()), i, localEditable.length(), 17);
              return;
            }
            catch (NumberFormatException paramString)
            {
              paramString.printStackTrace();
              return;
            }
            catch (SecurityException paramString)
            {
              paramString.printStackTrace();
              return;
            }
            catch (IllegalArgumentException paramString)
            {
              paramString.printStackTrace();
              return;
            }
            catch (NoSuchFieldException paramString)
            {
              paramString.printStackTrace();
              return;
            }
            catch (IllegalAccessException paramString)
            {
              paramString.printStackTrace();
              return;
            }
          }
        }
        localEditable.insert(i, paramString);
        try
        {
          j = Integer.parseInt(R.drawable.class.getDeclaredField(paramObject).get(null).toString());
          if (j == 0)
            break;
          k = (int)(this.editLayout.width * 0.1F);
          localEditable.setSpan(new ExpressionSpan(getResources(), j, k, k, ExpressionUtil.getInstance().getOwnerId()), i, paramString.length() + i, 17);
          return;
        }
        catch (NumberFormatException paramString)
        {
          paramString.printStackTrace();
          return;
        }
        catch (SecurityException paramString)
        {
          paramString.printStackTrace();
          return;
        }
        catch (IllegalArgumentException paramString)
        {
          paramString.printStackTrace();
          return;
        }
        catch (NoSuchFieldException paramString)
        {
          paramString.printStackTrace();
          return;
        }
        catch (IllegalAccessException paramString)
        {
          paramString.printStackTrace();
          return;
        }
      }
      label635: if (!paramString.equalsIgnoreCase("changeMode"))
        break;
      this.mEditText.setText("");
      this.mEditText.setHint("");
      switch (ChatMode.getCurrentMode())
      {
      default:
        return;
      case 0:
        this.mPlusButton.setVisibility(8);
        this.mInviteButton.setVisibility(8);
        this.mSendView.setVisibility(0);
        return;
      case 1:
      }
      this.mPlusButton.setVisibility(8);
      this.mInviteButton.setVisibility(0);
      this.mSendView.setVisibility(4);
      return;
      label742: i -= 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.chat.ImChatInputView
 * JD-Core Version:    0.6.2
 */