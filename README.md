
### 1、什么是PageStatusHelper
一款**简单的、方便**，解决页面各种状态的框架。

### 与其他方案的区别、优点
-几乎每个页面都需要加载中、加载失败、没有数据、没有网络。( 而普遍套一层的方案麻烦这里提出一种贴的方案
“贴”？ 哪个View上要出现加载状态哪里贴，比如现在在ListView没有数据的时候加载一个提示数据为空的view，这时候我们就往ListView上贴，而不再套一层，而且使用方便 )

-单页面存在多个加载状态的时候，极其便利，因为他针对的是view

-杜绝数据未加载出来，出现点击错误显示异常

### 少废话先看效果

**单页面多个接口**

![demo3](https://github.com/YuJunKui1995/PageStatusHelper/blob/master/gif/simple3.gif)
![demo4](https://github.com/YuJunKui1995/PageStatusHelper/blob/master/gif/simple4.gif)

**绑定部分区域加载**

![demo2](https://github.com/YuJunKui1995/PageStatusHelper/blob/master/gif/simple2.gif)


**防止页面数据未加载出来点击错误 或者数据不雅**

![demo1](https://github.com/YuJunKui1995/PageStatusHelper/blob/master/gif/simple1.gif)


### 2、版本依赖
```Groovy
//添加jitpack仓库
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
//添加库
dependencies {
  implementation 'com.github.YuJunKui1995:PageStatusHelper:v1.1.0'
}
```

### 3、使用

**1、简单使用 三行代码(简单模式下所有的状态view皆为TextView)**
```java
statusHelper = new PageStatusHelper(Context context)
//需要把加载状态绑定到那个view
statusHelper.bindView(View contentView);
statusHelper.refreshPageStatus(@PageStatusValue int pageStatusValue)

refreshPageStatus方法有如下类型
ERROR、 NO_LOGIN =、 LOADING、 NET_WORK(网络异常)、 EMPTY 、 CONTENT(正常内容)
```

**2、定制使用使用**
```java
//可通过Builder对象对各个状态进行定制,text image layout view 等等都可以
statusHelper = new PageStatusHelper(Context context
                 , new Builder(Context context)
                 .setErrorImage(int imageId)
                 .setLoadingView(View view)
                 .setEmptyImage(int imageId)
                 .setNetworkLayout(int layoutId)
                 .setNoLoginText(String text)
         );
```

### 4、目前已知的问题

1、bindView的父布局为ViewPage下异常

2、在Fragment使用时bindView是根布局，而这个Fragment使用的地方需要show hide Fragment(常见为主页tab)
以上两个问题都可以通过在bindView包裹一层父布局解决

### 5、版本优化
1.0.0 版本
重构view的绘制流程用来支持对根布局的属性支持

约束布局使用不在需要每个view都设置id

约束布局中属性不全的优化支持，约束布局自定义view或layout的优化支持

相对布局的完成支持

瞬间多次调用(断网状态)的绘制优化

## License MIT

Copyright (c) 2017-2020 YuJunKui

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

[1]: https://user-images.githubusercontent.com/2038071/31045150-a077cc8a-a5a2-11e7-8dc2-7a0e3a9f3e62.jpg
[2]: https://user-images.githubusercontent.com/2038071/29978804-45c321ba-8f75-11e7-9040-776d3b6dca1f.jpg
