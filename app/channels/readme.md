### 需求

我们先看几个案例：
1. 渠道A需要启动图a，但是渠道B上需要启动图b，渠道C...渠道D...；
2. 渠道A的名称是StringA，渠道B的名称是StringB，渠道C...渠道D...；
3. 一个页面的按钮点击跳转逻辑在渠道A和渠道B上需要定制。

以上的需求还可以自由组合穿插，反反复复的炒冷饭。手动修改或者切分支完成不是不可以，但是消耗大量的精力。

怎么办？我们可以借助gradle这个工具，帮我们完成这些差异化的工作。

### gradle差异化构建变种

1. 新建一个项目，看下app下的build.gradle文件：
```
apply plugin: 'com.android.application'

android {
    compileSdkVersion 27
    defaultConfig {
        applicationId "com.rjp.eaction"
        minSdkVersion 15
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    dependencies {
        implementation fileTree(dir: 'libs', include: ['*.jar'])
        implementation 'com.android.support.constraint:constraint-layout:1.1.0'
        implementation 'com.android.support:support-annotations:27.1.1'
        testImplementation 'junit:junit:4.12'
        androidTestImplementation 'com.android.support.test:runner:1.0.2'
        androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    }
}
```
这个时候还不支持变种包，那么现在我们来增加两个定制的渠道，需要用到productFlavors：
```
apply plugin: 'com.android.application'

android {
    compileSdkVersion 27
    defaultConfig {
        applicationId "com.rjp.eaction"
        minSdkVersion 15
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"
        flavorDimensions "versionCode"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

    productFlavors {
        channel_A{
            applicationId "com.rjp.eaction.A"
        }

        channel_B{
            applicationId "com.rjp.eaction.B"
        }
    }

    dependencies {
        implementation fileTree(dir: 'libs', include: ['*.jar'])
        implementation 'com.android.support.constraint:constraint-layout:1.1.0'
        implementation 'com.android.support:support-annotations:27.1.1'
        testImplementation 'junit:junit:4.12'
        androidTestImplementation 'com.android.support.test:runner:1.0.2'
        androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    }
}
```
我们增加了channel_A和channel_B两个渠道，而且都是只指定了applicationId，这里的applicationId可不是乱写的，而是gradle的预留字段，代表当前apk的包名。

### 打包

修改完之后怎么切换渠道呢？毕竟代码只有一份。我们点开Android Studio的侧边栏，有一个Build Variants选项，点开如下图：


可以看到有四个选项。这里的选项个数是build.gradle里buildTypes个数和productFlavors个数的组合数。buildTypes下默认有一个debug，加上配置的release一共有2个；productFlavors下我们配置了2个，所以Build Variant我们一共有4种组合。

不信我们增加一个channel_C看下效果：


同理，我们增加一个buildTypes也是同样的效果。

以上是开发阶段我们修改了变种渠道之后如何查看修改效果的方法。正式打包如何操作呢？

我们点开Android Studio右侧边栏的Gradle选项，找到当前项目的Tasks目录，点开other：


数一数，渠道A的debug包和release包，渠道B的debug包和release包，渠道C的debug包和release包。六个包，双击就可以产出。

（注意：正式包需要事先在gradle文件配置好签名，否则打出的都是未签名包）
（注意：使用build打包就不关心Build Variants下的配置了，两者一个开发阶段一个发包阶段，互不干扰）

### 测试

我们在MainActivity里面加一个TextView显示当前包的包名，看下修改是否生效。

我们分别双击assembleChannel_ADebug、assembleChannel_BDebug和assembleChannel_CDebug，找到app下的apk输出文件：


可以看到，全部构建成功，如果你配置了正式包的签名，双击也能生成release文件夹。

接下来安装这三个apk到手机上，打开就能看到包名的差异了。

### 进阶

是不是只能修改applicationId呢？不是的。只要是defaultConfig下的参数都可以修改，而且productFlavors下的参数是会覆盖defaultConfig下的配置。

那么你可能觉得，好像用处不是很大？也不是的。只要是资源我们全部可以修改。

资源也就是res文件夹下面的所有内容都是可以差异化的，包括string、drawable和layout等等。

### sourceSets

sourceSets非常强大，看名字，可以理解为指定资源的集合。

我们先在app下新建一个文件夹，名字随意，我们这里叫channels，然后在channels下新建三个文件夹分别放置A、B、C三个渠道的资源文件：


这个res文件就是我们app下的src下的main下的res资源文件夹的一个扩展，但是这个res文件会覆盖app下的res的相同文件。比如我们当前这个strings.xml里面只是修改了app_name：
```
<resources>
    <string name="app_name">Channel_A</string>
</resources>

<resources>
    <string name="app_name">Channel_B</string>
</resources>

<resources>
    <string name="app_name">Channel_C</string>
</resources>
```
三个文件下app_name都不一样。这个文件是增加了，但是怎么使用呢：
```
apply plugin: 'com.android.application'

android {
    compileSdkVersion 27
    defaultConfig {
        applicationId "com.rjp.eaction"
        minSdkVersion 15
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"
        flavorDimensions "versionCode"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

    productFlavors {
        channel_A {
            applicationId "com.rjp.eaction.A"
        }

        channel_B {
            applicationId "com.rjp.eaction.B"
        }

        channel_C {
            applicationId "com.rjp.eaction.C"
        }
    }

    sourceSets{
        channel_A {
            res.srcDirs = ['channels/channel_A/res']
        }

        channel_B {
            res.srcDirs = ['channels/channel_B/res']
        }

        channel_C {
            res.srcDirs = ['channels/channel_C/res']
        }
    }

    dependencies {
        implementation fileTree(dir: 'libs', include: ['*.jar'])
        implementation 'com.android.support.constraint:constraint-layout:1.1.0'
        implementation 'com.android.support:support-annotations:27.1.1'
        testImplementation 'junit:junit:4.12'
        androidTestImplementation 'com.android.support.test:runner:1.0.2'
        androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    }
}
```
魔力就在这，我们在sourceSets下指定了渠道A的res的srcDirs文件夹位置，重新build项目，我们可以看到，channel_A下的res文件夹已经带上了资源的标识：


这个时候你能想到的能差异化的资源，都可以放到这个下面，什么color、图片、动画。项目在你手中仿佛是一个橡皮泥，你想它变啥样它就变啥样。

### 再进阶

是不是只有资源文件能够差异化呢？很明显不是的，我们的java文件也是可以差异化的，但是这个java文件的差异化比较笨，需要复制多份代码，但是对于需要切换分支付出的代价，这个拷贝代码的付出几乎可以忽略不计了。

同样我们需要先新建java代码文件夹：


sourceSets下只要稍加改动：
```
sourceSets{
        channel_A {
            res.srcDirs = ['channels/channel_A/res']
            java.srcDirs = ['channels/channel_A/java']
        }

        channel_B {
            res.srcDirs = ['channels/channel_B/res']
            java.srcDirs = ['channels/channel_B/java']
        }

        channel_C {
            res.srcDirs = ['channels/channel_C/res']
            java.srcDirs = ['channels/channel_C/java']
        }
    }
```
可以看到，我们同样指定了java代码的srcDirs扩展目录。这样我们就可以编写出差异化的界面了。我们现在有三个详情页，你可以随意修改代码，但是要注意一点，java目录下的包文件路径要保持一致，否则在切换build variants的时候仍然需要手动修改类的引用路径。

上面因为三个DetailActivity的路径一致，所以我们只需要在app下的AndroidManifest.xml注册就行，但是很多时候，我们的差异包下面单独有一个PayActivity需要注册，而其他的渠道根本用不到这个PayActivity的时候，这个PayActivity上哪注册呢？

没关系，AndroidManifest文件也是支持差异化的，再对sourceSets做一点改动：
```
sourceSets {
        channel_A {
            manifest.srcFile 'channels/channel_A/AndroidManifest.xml'
            res.srcDirs = ['channels/channel_A/res']
            java.srcDirs = ['channels/channel_A/java']
        }

        channel_B {
            res.srcDirs = ['channels/channel_B/res']
            java.srcDirs = ['channels/channel_B/java']
        }

        channel_C {
            res.srcDirs = ['channels/channel_C/res']
            java.srcDirs = ['channels/channel_C/java']
        }
    }
```
channel_A的注册文件内容：
```
<manifest
    xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.rjp.eaction"
    >

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme"
        >
        <activity android:name=".PayActivity">
        </activity>
    </application>

</manifest>
```

这里我只对channel_A进行了修改，就是为了说明差异化，这些都不是必须要修改的。到这里，我们已经完全对app进行了差异化管理，包的目录结构也已经完整，最后看一眼我们的差异化目录结构：



### 成果

最终打出三个debug包，我们整体看一遍效果。

debugA：

debugB：

debugC：
