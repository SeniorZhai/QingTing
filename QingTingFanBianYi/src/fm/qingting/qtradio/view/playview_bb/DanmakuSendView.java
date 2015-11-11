package fm.qingting.qtradio.view.playview_bb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.View.MeasureSpec;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import java.util.HashMap;

@SuppressLint({"NewApi"})
public class DanmakuSendView extends ViewGroupViewImpl
  implements IEventHandler, INavigationBarListener
{
  private final String TAG = "send";
  private final ViewLayout editTextLayout = this.standardLayout.createChildLT(700, 100, 10, 98, ViewLayout.SCALE_FLAG_SLTCW);
  private Bitmap image;
  private final ViewLayout imageLayout = this.standardLayout.createChildLT(360, 287, 10, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private EditText mEditText;
  private ImageView mImageView;
  private NavigationBarView mNaviView;
  private TextView mTextCountView;
  private final ViewLayout naviLayout = this.standardLayout.createChildLT(720, 98, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout textCountLayout = this.standardLayout.createChildLT(700, 50, 10, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private String uri;

  public DanmakuSendView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-1);
    this.mNaviView = new NavigationBarView(paramContext);
    addView(this.mNaviView);
    this.mNaviView.setBarListener(this);
    this.mNaviView.setLeftItem("取消", SkinManager.getTextColorRecommend(), SkinManager.getTextColorRecommend());
    this.mNaviView.setRightItem("发送", SkinManager.getTextColorHighlight(), SkinManager.getTextColorHighlight());
    this.mNaviView.setTitleItem(new NavigationBarItem("发布图片", SkinManager.getTextColorRecommend()));
    this.mNaviView.setBackgroundColor(-526345);
    this.mEditText = new EditText(paramContext);
    addView(this.mEditText);
    this.mEditText.setBackgroundDrawable(null);
    this.mEditText.requestFocus();
    this.mEditText.setHint("图片描述...");
    this.mEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(60) });
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
        paramAnonymousInt1 = DanmakuSendView.this.mEditText.getText().length();
        DanmakuSendView.this.mTextCountView.setText(60 - paramAnonymousInt1 + "字");
      }
    });
    this.mImageView = new ImageView(paramContext);
    addView(this.mImageView);
    this.mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    this.mTextCountView = new TextView(paramContext);
    this.mTextCountView.setText("60字");
    this.mTextCountView.setGravity(5);
    addView(this.mTextCountView);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
  }

  public void onItemClick(int paramInt)
  {
    switch (paramInt)
    {
    case 2:
    default:
    case 3:
    }
    while (true)
    {
      ((InputMethodManager)getContext().getSystemService("input_method")).hideSoftInputFromWindow(getApplicationWindowToken(), 0);
      ControllerManager.getInstance().popLastController();
      return;
      IMAgent.getInstance().sendBarrage(this.mEditText.getText().toString(), this.uri);
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.naviLayout.layoutView(this.mNaviView);
    this.mEditText.layout(this.editTextLayout.leftMargin, this.naviLayout.height, this.editTextLayout.getRight(), this.naviLayout.height + this.mEditText.getMeasuredHeight());
    this.mImageView.layout(this.imageLayout.leftMargin, this.naviLayout.height + this.mEditText.getMeasuredHeight(), this.imageLayout.getRight(), this.naviLayout.height + this.mEditText.getMeasuredHeight() + this.imageLayout.height);
    this.mTextCountView.layout(this.textCountLayout.leftMargin, this.standardLayout.height - this.textCountLayout.height, this.textCountLayout.getRight(), this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.naviLayout.scaleToBounds(this.standardLayout);
    this.naviLayout.measureView(this.mNaviView);
    this.editTextLayout.scaleToBounds(this.standardLayout);
    this.mEditText.measure(this.editTextLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.editTextLayout.height, 0));
    this.imageLayout.scaleToBounds(this.standardLayout);
    this.imageLayout.measureView(this.mImageView);
    this.textCountLayout.scaleToBounds(this.standardLayout);
    this.textCountLayout.measureView(this.mTextCountView);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (HashMap)paramObject;
      this.image = ((Bitmap)paramString.get("image"));
      this.uri = ((String)paramString.get("uri"));
      this.mImageView.setImageBitmap(this.image);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview_bb.DanmakuSendView
 * JD-Core Version:    0.6.2
 */