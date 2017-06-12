# MVP Base for Android Applications...

[![Release](https://jitpack.io/v/mishkaowner/AppBase.svg)](https://jitpack.io/#mishkaowner/AppBase)

This project provides very simple base classes to implement MVP pattern for beginners.

  - Supports with or without ViewModel
  - Includes very useful libraries you would like to use
  - Check the example project which is included within

### Prerequisite

To use this library you will need to know how to use:

* [Android SDK](https://developer.android.com/studio/index.html) - Android... Obviously... haha
* [Dagger2](https://google.github.io/dagger/)- Dependency Injection library!
* [ButterKnife](http://jakewharton.github.io/butterknife/) - View Injection library!

### Installation

At your root gradle add Jitpack: 

```sh
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

and at project level add these dependencies:

```sh
compile 'com.github.mishkaowner:AppBase:master-SNAPSHOT'
annotationProcessor 'com.google.dagger:dagger-compiler:2.11'
annotationProcessor 'com.jakewharton:butterknife-compiler:8.6.0'
```

Create V (View) first!
---

```sh
public interface IMainActivity extends BaseView
```

create an Implementation class for the View interface above

```sh
public class MainActivity extends BaseAbstractActivity implements IMainActivity
```

And create the presenter for the view
---

```sh
public interface IMainActivityPresenter extends BasePresenter
```

create an Implementation class for the Presenter interface above

```sh
public class MainActivityPresenter extends BaseAbstractPresenter<IMainActivity> implements IMainActivityPresenter
```
Once you finished... the class will look like this
```sh
public class MainActivity extends BaseAbstractActivity implements IMainActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main; //Just put your base layout resource id here...
    }
    @Override
    public void inject() {
    }
    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
```
```sh
public class MainActivityPresenter extends BaseAbstractPresenter<IMainActivity> implements IMainActivityPresenter {
    public MainActivityPresenter(IMainActivity view) {
        super(view);
    }
}
```

But wait...where is the presenter?
This is where Dagger2 comes to rescue!

```sh
public class MainActivity extends BaseAbstractActivity implements IMainActivity {
    @Inject IMainActivityPresenter presenter;
    
    @Override
    public int getLayoutId() {
        return R.layout.activity_main; //Just put your base layout resource id here...
    }
    @Override
    public void inject() {
        MyApp.inject(this); //inject Presenter
    }
    @Override
    public BasePresenter getPresenter() {
        return presenter; //HERE 
    }
}
```
```sh
public class MainActivityPresenter extends BaseAbstractPresenter<IMainActivity> implements IMainActivityPresenter {
    @Inject
    public MainActivityPresenter(IMainActivity view) {
        super(view);
    }
}
```
And presenter and view is now interconnected and injected into each other :D

How to start the application? 
DO NOT OVERRIDE onResume or onCreate in the View!!!
Everything must start from Presenter.
Override OnResume or onCreate in the presenter impl class!

```sh
public class MainActivityPresenter extends BaseAbstractPresenter<IMainActivity> implements IMainActivityPresenter {
    @Inject
    public MainActivityPresenter(IMainActivity view) {
        super(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        view.setText("Hello World from Presenter!");
    }
}
```
There we go!

### Todos

 - Write Test code in the example project

License
----
GPL v3.0
