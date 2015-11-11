package fm.qingting.framework.controller;

import android.content.Context;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.INavigationSetting.Mode;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.IViewEventListener;
import java.lang.ref.SoftReference;

public abstract class ViewController
  implements IViewEventListener
{
  protected boolean available = true;
  protected SoftReference<Context> contextRef;
  public String controllerName = "controller";
  protected IEventHandler eventHandler;
  private INavigationSetting.Mode mMode = INavigationSetting.Mode.NORMAL;
  private NavigationController navigationController = null;
  protected IView shellView;
  private IView topBarView;
  protected IView view;
  protected ViewEventHandler viewActionHandler;

  public ViewController(Context paramContext)
  {
    this(paramContext, null);
  }

  public ViewController(Context paramContext, IView paramIView)
  {
    this.contextRef = new SoftReference(paramContext);
    this.viewActionHandler = new ViewEventHandler();
    if (paramIView != null)
      attachView(paramIView);
  }

  public void attachView(IView paramIView)
  {
    detachView();
    this.view = paramIView;
    if (this.view != null)
    {
      this.view.setEventHandler(this.viewActionHandler);
      this.view.addViewEventListener(this);
    }
    onAttachView();
  }

  public void config(String paramString, Object paramObject)
  {
  }

  public void controllerDidPopped()
  {
  }

  public void controllerDidPushed()
  {
  }

  public void controllerReappeared()
  {
  }

  public void destroy()
  {
    onDestroy();
    detachView();
    this.eventHandler = null;
    this.viewActionHandler = null;
    this.contextRef = null;
    this.navigationController = null;
    this.topBarView = null;
  }

  public void detachView()
  {
    IView localIView = this.view;
    if (this.view != null)
    {
      this.view.setEventHandler(null);
      this.view.removeViewEventListener(this);
    }
    this.view = null;
    onDetachView(localIView);
  }

  protected void dispatchEvent(String paramString, Object paramObject)
  {
    if (this.eventHandler == null)
      return;
    this.eventHandler.onEvent(this, paramString, paramObject);
  }

  protected Context getContext()
  {
    if (this.contextRef != null)
      return (Context)this.contextRef.get();
    return null;
  }

  public INavigationSetting.Mode getNavigationBarMode()
  {
    return this.mMode;
  }

  protected final NavigationController getNavigationController()
  {
    return this.navigationController;
  }

  public IView getShellView()
  {
    return this.shellView;
  }

  public IView getTopBar()
  {
    return this.topBarView;
  }

  public Object getValue(String paramString, Object paramObject)
  {
    return null;
  }

  public IView getView()
  {
    return this.view;
  }

  public boolean hasMiniPlayer()
  {
    return false;
  }

  protected void onAttachView()
  {
  }

  protected void onAvailableChanged()
  {
  }

  protected void onDestroy()
  {
  }

  protected void onDetachView(IView paramIView)
  {
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (this.eventHandler != null)
      this.eventHandler.onEvent(this, paramString, paramObject2);
  }

  public void setAvailable(boolean paramBoolean)
  {
    this.available = paramBoolean;
    onAvailableChanged();
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.eventHandler = paramIEventHandler;
  }

  protected void setNavigationBar(IView paramIView)
  {
    this.topBarView = paramIView;
  }

  protected void setNavigationBarMode(INavigationSetting.Mode paramMode)
  {
    this.mMode = paramMode;
  }

  protected final void setNavigationController(NavigationController paramNavigationController)
  {
    this.navigationController = paramNavigationController;
  }

  public void setShellView(IView paramIView)
  {
    this.shellView = paramIView;
  }

  public void stopActivate()
  {
  }

  public void viewDidClosed(IView paramIView)
  {
  }

  public void viewDidOpened(IView paramIView)
  {
  }

  public void viewWillClose(IView paramIView)
  {
  }

  public void viewWillOpen(IView paramIView)
  {
  }

  protected class ViewEventHandler
    implements IEventHandler
  {
    protected ViewEventHandler()
    {
    }

    public void onEvent(Object paramObject1, String paramString, Object paramObject2)
    {
      ViewController.this.onViewEvent(paramObject1, paramString, paramObject2);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.ViewController
 * JD-Core Version:    0.6.2
 */