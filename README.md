# JavaHelpers
个人 Java 开发中积累的一些工具。

### 集成方式

#### 第一步：在你的项目 build.gradle 添加 maven 库：
```
allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
    }
}
```

#### 第二步：在你的模块 build.gradle 添加依赖库：
```
dependencies {
    ...
    api 'com.github.iWay7:JavaHelpers:1.0.26'
    api 'com.google.code.gson:gson:2.8.5'
}
```
