
### 1、什么是PageStatusHelper
一款**简单的、方便**，解决页面各种状态的框架。

### 与其他方案的区别、优点
-几乎每个页面都需要加载中、加载失败、没有数据、没有网络。( 而普遍套一层的方案麻烦这里提出一种贴的方案
“贴”？ 哪个View上要出现加载状态哪里贴，比如现在在ListView没有数据的时候加载一个提示数据为空的view，这时候我们就往ListView上贴，而不再套一层，而且使用方便 )

-单页面存在多个加载状态的时候，极其便利，因为他针对的是view

-杜绝数据未加载出来，出现点击错误显示异常

### 2、版本依赖
```Groovy
api 'firemonkey:pagestatushelper:0.7.6'
```

### 3、使用

## License MIT

Copyright (c) 2017-2018 余俊魁 

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

[1]: https://user-images.githubusercontent.com/2038071/31045150-a077cc8a-a5a2-11e7-8dc2-7a0e3a9f3e62.jpg
[2]: https://user-images.githubusercontent.com/2038071/29978804-45c321ba-8f75-11e7-9040-776d3b6dca1f.jpg
