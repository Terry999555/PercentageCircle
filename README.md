### 一款开源的计数圆控件
----------


#### ParcentageCircle
* 作者: **到了我的周末**
* 概述:  项目中需要一个控件，来显示用户**抽烟的口数**，和**单位**。并且显示当前的数目占用户设定计划数目的**百分比**。
* 效果:  一款用来显**示计数的控**件，可以显示单位和数目，并且用不同的颜色来显示，当前数目达到的**百分比**。

----
#### 简介
![@效果图|center|160*260](http://omflg86rs.bkt.clouddn.com/blog/20171101-170520988.jpg)
* 用户目前数量。
* 设置单位。
* 根据颜色来判断目前所占总数的大概百分比。


#### 特点
1.  **项目中使用**
可以在**代码**或**xml**中设置 当前数目和背景的颜色，当前**数目**和**单位**的**字体**，**颜色**，**大小**。
![@如图xml|center|300*150](http://omflg86rs.bkt.clouddn.com/blog/20171101-170059997.jpg)
![@代码中|center|300*250](http://omflg86rs.bkt.clouddn.com/blog/20171101-171709629.jpg)

使用简单，可以直接在xml中或者代码中设置对应的属性。

2. **再次开发**
* 项目在Github上开源: https://github.com/Terry999555/PercentageCircle.git
* 详细讲解见博客:  http://www.jianshu.com/p/bdccd0894d67
可以在基础上更改自己的想要的效果。

#### 具体使用
Gradle 引入依赖
* 在项目中的 build.Gradle
``` ruby
dependencies {
    compile 'com.cyy:PercentageCircle:1.0.2'
}
```
