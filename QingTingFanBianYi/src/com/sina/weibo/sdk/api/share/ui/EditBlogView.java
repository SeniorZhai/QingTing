package com.sina.weibo.sdk.api.share.ui;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EditBlogView extends EditText
{
  private boolean canSelectionChanged = true;
  private int count;
  private Context ctx;
  private List<OnSelectionListener> listeners;
  private OnEnterListener mOnEnterListener;

  public EditBlogView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public EditBlogView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public EditBlogView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  private void init()
  {
    this.ctx = getContext();
    this.listeners = new ArrayList();
  }

  public int correctPosition(int paramInt)
  {
    if (paramInt == -1);
    Editable localEditable;
    Object[] arrayOfObject;
    do
    {
      do
      {
        return paramInt;
        localEditable = getText();
      }
      while (paramInt >= localEditable.length());
      arrayOfObject = localEditable.getSpans(paramInt, paramInt, ImageSpan.class);
    }
    while ((arrayOfObject == null) || (arrayOfObject.length == 0) || (paramInt == localEditable.getSpanStart(arrayOfObject[0])));
    return localEditable.getSpanEnd(arrayOfObject[0]);
  }

  public void enableSelectionChanged(boolean paramBoolean)
  {
    this.canSelectionChanged = paramBoolean;
  }

  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    return new InputConnectionWrapper(super.onCreateInputConnection(paramEditorInfo), false)
    {
      public boolean commitText(CharSequence paramAnonymousCharSequence, int paramAnonymousInt)
      {
        Editable localEditable = EditBlogView.this.getEditableText();
        new String(localEditable.toString());
        int n = Selection.getSelectionStart(localEditable);
        int i1 = Selection.getSelectionEnd(localEditable);
        if ((n != -1) && (i1 != -1))
        {
          int i = EditBlogView.this.correctPosition(n);
          int k = EditBlogView.this.correctPosition(i1);
          int m = k;
          int j = i;
          if (i > k)
          {
            j = k;
            m = i;
          }
          if ((j != n) || (m != i1))
            Selection.setSelection(localEditable, j, m);
          if (j != m)
            EditBlogView.this.getText().delete(j, m);
        }
        return super.commitText(paramAnonymousCharSequence, paramAnonymousInt);
      }

      public boolean setComposingText(CharSequence paramAnonymousCharSequence, int paramAnonymousInt)
      {
        Editable localEditable = EditBlogView.this.getEditableText();
        new String(localEditable.toString());
        int n = Selection.getSelectionStart(localEditable);
        int i1 = Selection.getSelectionEnd(localEditable);
        if ((n != -1) && (i1 != -1))
        {
          int i = EditBlogView.this.correctPosition(n);
          int k = EditBlogView.this.correctPosition(i1);
          int m = k;
          int j = i;
          if (i > k)
          {
            j = k;
            m = i;
          }
          if ((j != n) || (m != i1))
            Selection.setSelection(localEditable, j, m);
          if (j != m)
            EditBlogView.this.getText().delete(j, m);
        }
        return super.setComposingText(paramAnonymousCharSequence, paramAnonymousInt);
      }
    };
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 66) && (this.mOnEnterListener != null))
      this.mOnEnterListener.onEnterKey();
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onSelectionChanged(int paramInt1, int paramInt2)
  {
    super.onSelectionChanged(paramInt1, paramInt2);
    if ((!this.canSelectionChanged) || (this.listeners == null) || (this.listeners.isEmpty()));
    while (true)
    {
      return;
      Iterator localIterator = this.listeners.iterator();
      while (localIterator.hasNext())
        ((OnSelectionListener)localIterator.next()).onSelectionChanged(paramInt1, paramInt2);
    }
  }

  public void setOnEnterListener(OnEnterListener paramOnEnterListener)
  {
    this.mOnEnterListener = paramOnEnterListener;
  }

  public void setOnSelectionListener(OnSelectionListener paramOnSelectionListener)
  {
    this.listeners.add(paramOnSelectionListener);
  }

  public static abstract interface OnEnterListener
  {
    public abstract void onEnterKey();
  }

  public static abstract interface OnSelectionListener
  {
    public abstract void onSelectionChanged(int paramInt1, int paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.api.share.ui.EditBlogView
 * JD-Core Version:    0.6.2
 */