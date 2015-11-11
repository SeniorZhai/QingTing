package fm.qingting.framework.controller;

import fm.qingting.framework.view.IView;
import java.util.ArrayList;
import java.util.List;

public class ViewControllerStack
{
  private List<ViewController> controllerStack = new ArrayList();

  public void clear()
  {
    this.controllerStack.clear();
  }

  public List<ViewController> getAllControllers()
  {
    return this.controllerStack.subList(0, this.controllerStack.size());
  }

  public List<ViewController> getAllHiddenControllers()
  {
    return this.controllerStack.subList(0, this.controllerStack.size() - 1);
  }

  public int getCount()
  {
    return this.controllerStack.size();
  }

  public ViewController getLastViewController()
  {
    if (this.controllerStack.size() == 0)
      return null;
    return (ViewController)this.controllerStack.get(this.controllerStack.size() - 1);
  }

  public ViewController getRootViewController()
  {
    if (this.controllerStack.size() == 0)
      return null;
    return (ViewController)this.controllerStack.get(0);
  }

  public ViewController getViewController(int paramInt)
  {
    if (paramInt < 0)
      return null;
    if (paramInt >= getCount())
      return getLastViewController();
    return (ViewController)this.controllerStack.get(paramInt);
  }

  public List<ViewController> popToRootViewController()
  {
    ArrayList localArrayList = new ArrayList();
    if (this.controllerStack.size() <= 1)
      return localArrayList;
    int j = this.controllerStack.size();
    int i = 1;
    while (true)
    {
      if (i >= j)
      {
        localViewController = (ViewController)this.controllerStack.get(0);
        localViewController.getView().setActivate(true);
        this.controllerStack = new ArrayList();
        this.controllerStack.add(localViewController);
        return localArrayList;
      }
      ViewController localViewController = (ViewController)this.controllerStack.get(i);
      localViewController.getView().setActivate(false);
      localArrayList.add(localViewController);
      i += 1;
    }
  }

  public ViewController popViewController()
  {
    Object localObject;
    if (this.controllerStack.size() == 0)
      localObject = null;
    ViewController localViewController;
    do
    {
      return localObject;
      localViewController = (ViewController)this.controllerStack.remove(this.controllerStack.size() - 1);
      localViewController.getView().setActivate(false);
      localObject = localViewController;
    }
    while (this.controllerStack.size() <= 0);
    ((ViewController)this.controllerStack.get(this.controllerStack.size() - 1)).getView().setActivate(true);
    return localViewController;
  }

  public void pushViewController(ViewController paramViewController)
  {
    if (paramViewController == null);
    do
    {
      return;
      if (this.controllerStack.size() > 0)
        ((ViewController)this.controllerStack.get(this.controllerStack.size() - 1)).getView().setActivate(false);
      this.controllerStack.add(paramViewController);
      paramViewController = paramViewController.getView();
    }
    while (paramViewController == null);
    paramViewController.setActivate(true);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.ViewControllerStack
 * JD-Core Version:    0.6.2
 */