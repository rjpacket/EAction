### 实现一个简单的打分RatingBar控件

#### 1.drawable资源文件编写

```rating_bar_background
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@android:id/background"
        android:drawable="@drawable/icon_gray">
    </item>
    <item
        android:id="@android:id/secondaryProgress"
        android:drawable="@drawable/icon_gray">
    </item>
    <item
        android:id="@android:id/progress"
        android:drawable="@drawable/icon_blue">
    </item>
</layer-list>
```

背景为灰色图标，进度用蓝色的图标填充，如果不设置剩下进度条secondaryProgress的填充图标，那么只能显示整数个进度，像2.5这种浮点型的进度看不出来，所以secondaryProgress也用灰色图标，就会覆盖蓝色的图标。

#### 2.布局中使用

```
    <RatingBar
        android:id="@+id/rb"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:clickable="true"
        android:isIndicator="false"
        android:maxHeight="15dp"
        android:minHeight="15dp"
        android:rating="3.5"
        android:stepSize="0.5"
        android:progressDrawable="@drawable/rating_bar_background"
        android:numStars="5"
        />
```

maxHeight和minHeight需要同时设置，才能显示固定高度，numStars设置总个数，rating就是当前进度。

这是一个简单的打分控件实现，没有炫酷的效果。支付宝有一个炫酷的打分效果，下一节我们自定义来实现试试。
