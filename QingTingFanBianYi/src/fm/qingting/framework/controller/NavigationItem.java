package fm.qingting.framework.controller;

class NavigationItem
{
  public ISwitchAnimation popAnimation;
  public ViewController viewController;

  public NavigationItem(ViewController paramViewController, ISwitchAnimation paramISwitchAnimation)
  {
    this.viewController = paramViewController;
    this.popAnimation = paramISwitchAnimation;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.NavigationItem
 * JD-Core Version:    0.6.2
 */