package android.support.v4.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

public class DialogFragment extends Fragment
  implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener
{
  private static final String SAVED_BACK_STACK_ID = "android:backStackId";
  private static final String SAVED_CANCELABLE = "android:cancelable";
  private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
  private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
  private static final String SAVED_STYLE = "android:style";
  private static final String SAVED_THEME = "android:theme";
  public static final int STYLE_NORMAL = 0;
  public static final int STYLE_NO_FRAME = 2;
  public static final int STYLE_NO_INPUT = 3;
  public static final int STYLE_NO_TITLE = 1;
  int mBackStackId = -1;
  boolean mCancelable = true;
  Dialog mDialog;
  boolean mDismissed;
  boolean mShownByMe;
  boolean mShowsDialog = true;
  int mStyle = 0;
  int mTheme = 0;
  boolean mViewDestroyed;

  public void dismiss()
  {
    dismissInternal(false);
  }

  public void dismissAllowingStateLoss()
  {
    dismissInternal(true);
  }

  void dismissInternal(boolean paramBoolean)
  {
    if (this.mDismissed)
      return;
    this.mDismissed = true;
    this.mShownByMe = false;
    if (this.mDialog != null)
    {
      this.mDialog.dismiss();
      this.mDialog = null;
    }
    this.mViewDestroyed = true;
    if (this.mBackStackId >= 0)
    {
      getFragmentManager().popBackStack(this.mBackStackId, 1);
      this.mBackStackId = -1;
      return;
    }
    FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
    localFragmentTransaction.remove(this);
    if (paramBoolean)
    {
      localFragmentTransaction.commitAllowingStateLoss();
      return;
    }
    localFragmentTransaction.commit();
  }

  public Dialog getDialog()
  {
    return this.mDialog;
  }

  public LayoutInflater getLayoutInflater(Bundle paramBundle)
  {
    if (!this.mShowsDialog)
      return super.getLayoutInflater(paramBundle);
    this.mDialog = onCreateDialog(paramBundle);
    switch (this.mStyle)
    {
    default:
    case 3:
    case 1:
    case 2:
    }
    while (this.mDialog != null)
    {
      return (LayoutInflater)this.mDialog.getContext().getSystemService("layout_inflater");
      this.mDialog.getWindow().addFlags(24);
      this.mDialog.requestWindowFeature(1);
    }
    return (LayoutInflater)this.mActivity.getSystemService("layout_inflater");
  }

  public boolean getShowsDialog()
  {
    return this.mShowsDialog;
  }

  public int getTheme()
  {
    return this.mTheme;
  }

  public boolean isCancelable()
  {
    return this.mCancelable;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    if (!this.mShowsDialog);
    do
    {
      do
      {
        return;
        View localView = getView();
        if (localView != null)
        {
          if (localView.getParent() != null)
            throw new IllegalStateException("DialogFragment can not be attached to a container view");
          this.mDialog.setContentView(localView);
        }
        this.mDialog.setOwnerActivity(getActivity());
        this.mDialog.setCancelable(this.mCancelable);
        this.mDialog.setOnCancelListener(this);
        this.mDialog.setOnDismissListener(this);
      }
      while (paramBundle == null);
      paramBundle = paramBundle.getBundle("android:savedDialogState");
    }
    while (paramBundle == null);
    this.mDialog.onRestoreInstanceState(paramBundle);
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    if (!this.mShownByMe)
      this.mDismissed = false;
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (this.mContainerId == 0);
    for (boolean bool = true; ; bool = false)
    {
      this.mShowsDialog = bool;
      if (paramBundle != null)
      {
        this.mStyle = paramBundle.getInt("android:style", 0);
        this.mTheme = paramBundle.getInt("android:theme", 0);
        this.mCancelable = paramBundle.getBoolean("android:cancelable", true);
        this.mShowsDialog = paramBundle.getBoolean("android:showsDialog", this.mShowsDialog);
        this.mBackStackId = paramBundle.getInt("android:backStackId", -1);
      }
      return;
    }
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    return new Dialog(getActivity(), getTheme());
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    if (this.mDialog != null)
    {
      this.mViewDestroyed = true;
      this.mDialog.dismiss();
      this.mDialog = null;
    }
  }

  public void onDetach()
  {
    super.onDetach();
    if ((!this.mShownByMe) && (!this.mDismissed))
      this.mDismissed = true;
  }

  public void onDismiss(DialogInterface paramDialogInterface)
  {
    if (!this.mViewDestroyed)
      dismissInternal(true);
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.mDialog != null)
    {
      Bundle localBundle = this.mDialog.onSaveInstanceState();
      if (localBundle != null)
        paramBundle.putBundle("android:savedDialogState", localBundle);
    }
    if (this.mStyle != 0)
      paramBundle.putInt("android:style", this.mStyle);
    if (this.mTheme != 0)
      paramBundle.putInt("android:theme", this.mTheme);
    if (!this.mCancelable)
      paramBundle.putBoolean("android:cancelable", this.mCancelable);
    if (!this.mShowsDialog)
      paramBundle.putBoolean("android:showsDialog", this.mShowsDialog);
    if (this.mBackStackId != -1)
      paramBundle.putInt("android:backStackId", this.mBackStackId);
  }

  public void onStart()
  {
    super.onStart();
    if (this.mDialog != null)
    {
      this.mViewDestroyed = false;
      this.mDialog.show();
    }
  }

  public void onStop()
  {
    super.onStop();
    if (this.mDialog != null)
      this.mDialog.hide();
  }

  public void setCancelable(boolean paramBoolean)
  {
    this.mCancelable = paramBoolean;
    if (this.mDialog != null)
      this.mDialog.setCancelable(paramBoolean);
  }

  public void setShowsDialog(boolean paramBoolean)
  {
    this.mShowsDialog = paramBoolean;
  }

  public void setStyle(int paramInt1, int paramInt2)
  {
    this.mStyle = paramInt1;
    if ((this.mStyle == 2) || (this.mStyle == 3))
      this.mTheme = 16973913;
    if (paramInt2 != 0)
      this.mTheme = paramInt2;
  }

  public int show(FragmentTransaction paramFragmentTransaction, String paramString)
  {
    this.mDismissed = false;
    this.mShownByMe = true;
    paramFragmentTransaction.add(this, paramString);
    this.mViewDestroyed = false;
    this.mBackStackId = paramFragmentTransaction.commit();
    return this.mBackStackId;
  }

  public void show(FragmentManager paramFragmentManager, String paramString)
  {
    this.mDismissed = false;
    this.mShownByMe = true;
    paramFragmentManager = paramFragmentManager.beginTransaction();
    paramFragmentManager.add(this, paramString);
    paramFragmentManager.commit();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.app.DialogFragment
 * JD-Core Version:    0.6.2
 */