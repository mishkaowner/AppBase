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
public interface MainView extends BaseView
```

create an Implementation class for the View interface above

```sh
public class MainActivity extends BaseAbstractActivity implements MainView
```

And create the presenter for the view
---

```sh
public interface MainPresenter extends BasePresenter
```

create an Implementation class for the Presenter interface above

```sh
public class MainPresenterImpl extends BaseAbstractPresenter<MainView> implements MainPresenter
```
Once you finished, those classes will ask you to override methods...

### Todos

 - Write Test code in the example project

License
----
GPL v3.0
