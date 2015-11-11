package fm.qingting.qtradio.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import java.util.Random;

public class ZanyEditText extends EditText
{
  private Random r = new Random();

  public ZanyEditText(Context paramContext)
  {
    super(paramContext);
  }

  public ZanyEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public ZanyEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    return new ZanyInputConnection(super.onCreateInputConnection(paramEditorInfo), true);
  }

  private class ZanyInputConnection extends InputConnectionWrapper
  {
    public ZanyInputConnection(InputConnection paramBoolean, boolean arg3)
    {
      super(bool);
    }

    public boolean deleteSurroundingText(int paramInt1, int paramInt2)
    {
      if ((paramInt1 == 1) && (paramInt2 == 0))
        return (sendKeyEvent(new KeyEvent(0, 67))) && (sendKeyEvent(new KeyEvent(1, 67)));
      return super.deleteSurroundingText(paramInt1, paramInt2);
    }

    public boolean sendKeyEvent(KeyEvent paramKeyEvent)
    {
      if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getKeyCode() == 67))
      {
        String str = ZanyEditText.this.getText().toString();
        if ((str != null) && (str.length() > 0))
        {
          int i = ZanyEditText.this.getSelectionEnd();
          str = str.substring(0, i - 1) + str.substring(i);
          ZanyEditText.this.setText(str);
          ZanyEditText.this.setSelection(i - 1);
          ZanyEditText.this.requestFocus();
        }
      }
      return super.sendKeyEvent(paramKeyEvent);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.ZanyEditText
 * JD-Core Version:    0.6.2
 */