### 相关技术

#### android开发相关技术

在本系统中设计到的技术主要有以下几点：

- 利用retrofit读取api提供的资源
- 利用viewPager2实现类抖音界面
- 利用glide加载提供url的图片
- 利用lottie加载动画
- 利用intent传递activity之间消息

###  系统功能需求

本系统是一个短视频app，本app主要包含的功能为：视频信息流列表显示（包含封面图）

以及视频播放。在进行了需求分析之后，列举了系统所有的必做功能和可选功能如下：

- 显示每个视频必要的信息

- 类似抖音全屏item

- 从视频信息流点击某个视频封面进入播放页面

- 根据视频信息的url播放视频

- 单击视频窗口暂停/继续

- 双击视频窗口弹出点赞爱心图标

  

###   系统设计与实现

#### 总体设计

<img src="C:\Users\zlj\AppData\Roaming\Typora\typora-user-images\image-20200608110216654.png" alt="image-20200608110216654" style="zoom:67%;" />

#### 系统组成

##### layout文件

- activity_main.xml
- glide_item.xml
- video_item.xml

##### java文件

- MainActivity.java
- MyAdapter.java
- MyVideo.java
- videoActivity.java
- videoAPI.java
- VideoResponse.java

#### 模块设计

<img src="C:\Users\zlj\AppData\Roaming\Typora\typora-user-images\image-20200608105809462.png" alt="image-20200608105809462" style="zoom:67%;" />

  在本系统中的模块设计如上图所示，在MainAcitivity create的时候，调用getData函数，这时调用videoAPI里面的getVideo，然后会返回一个VideoResponse格式的response body。把按照json解析存在myVideo这个array里面，去bind ViewHolder从而渲染adapter。这样mainActivity也算是构建完了。在这里就实现，从后端读取数据，并且用glide显示封面的部分。

  然后在单击封面的时候添加事件，将信息存在bundle里面，用intent传到video的activity里面，在这个activity用videoView来承载视频，并且将头像以及其他文本信息显示在视频的上方。为了给视频添加双击事件，重写videoView变成myVideo类，并且给单击事件添加延时，判断是否有双击事件发生。

#### 关键代码的解释

##### 数据结构说明

MainActivity

```java
private List<VideoResponse>myVideos; //存api返回的信息
```

##### 函数说明

MainActivity

```java
public void onItemClick(int clickedItemIndex) // 对于每一个封面添加点击事件跳转
```

```java
private void getData() //获取数据 调用videoAPI
```

###  系统可能的扩展

所设计的系统的延展性很强 ，前端部分可以继续丰富，添加聊天、评论等其他功能，添加这些功能不会对app的整体框架有影响。

###  总结体会

 这个学期接触的移动互联网技术，让我体会到了和以前的大作业设计不同的地方。以往的发作业主要是进行pc端的开发，或者使用c++的图形化，或者是使用html那一套技术栈来实现。pc端的话大多数的设计都是展开的，因为界面比较大。但是对于移动互联网技术相关的产品，由于应用在手机这块小屏上，大多数的内容都是折叠的。综上，通过这个学期对于移动互联网技术的学习，真正体会到了其独特之处。
