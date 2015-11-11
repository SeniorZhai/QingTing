package fm.qingting.framework.view;

public abstract interface IViewEventListener
{
  public abstract void viewDidClosed(IView paramIView);

  public abstract void viewDidOpened(IView paramIView);

  public abstract void viewWillClose(IView paramIView);

  public abstract void viewWillOpen(IView paramIView);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.IViewEventListener
 * JD-Core Version:    0.6.2
 */