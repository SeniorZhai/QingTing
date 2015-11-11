package com.tencent.stat;

import android.app.ListActivity;

public class EasyListActivity extends ListActivity
{
  protected void onPause()
  {
    super.onPause();
    StatService.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    StatService.onResume(this);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.EasyListActivity
 * JD-Core Version:    0.6.2
 */