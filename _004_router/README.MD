我们希望的通过路由实现从
A Activity 跳转到 B Activity 的调用语法应该是

A Activity 
```java
Router.getInstance(ActivityA.this).navigateTo("scheme://user/favorite/user_collection&user_id=10&user_name=George");
```

那么这个navigateTo方法里就应该做到：
1. 解析url参数，根据path找到对应的componentName，其实就是对应的className
2. 从url参数中提取queryParameters，String,int,以及各种自定义的数据类型（应该有一个key-value的map）
3. new一个Intent出来，根据第一步的结果setClass，根据第二步的参数map，开始往Intent中添加参数
4. Intent去startActivity或者startActivityForResult

在AnnotationProcessor处理一个被注解的Activity的时候，应该先根据注解中的path找到对应的class,建立 path -> class的字典

[参考了ActivityRouter的代码](https://github.com/mzule/ActivityRouter)

Router在跳转的过程中只负责从应用层调用到开启native startActivity的过程，所以从A -> B -> A这个过程，能够拦截的只有A -> B这一段，所以callback还是在onActivityResult中添加。


module能单独作为Application编译