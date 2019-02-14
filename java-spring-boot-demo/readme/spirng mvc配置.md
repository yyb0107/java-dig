### spring mvc 配置  (annotation方式)
---
 ###### 默认配置从哪里来
`spring-boot-autoconfigure-2.0.3.RELEASE.jar`里面的`WebMvcAutoConfiguration.class`和`WebMvcProperties`，这些值都可以在application.yml配置文件定义

如果项目没有显示`@EnableWebMvc`,那springboot默认加载WebMvcAutoConfiguration类

spring mvc对请求处理有三种方式
* controller中声明的requestMapping
* 静态资源ResourceHttpRequestHandler
* DefaultServletHttpRequestHandler

###### 为什么从controller跳转含有/WEB-INF/**的路径会失败

查看`ResourceHttpRequestHandler.java`源码，可以看到
```java
if (path.contains("WEB-INF") || path.contains("META-INF")) {
	if (logger.isTraceEnabled()) {
		logger.trace("Path contains \"WEB-INF\" or \"META-INF\".");
	}
	return true;
}
```

 
###### 为什么web项目可以访问webapp下的内容 

 因为在`spring-boot-2.0.3.RELEASE.jar`的`DocumentRoot`定义
```java
class DocumentRoot {
	private static final String[] COMMON_DOC_ROOTS = { "src/main/webapp", "public",
			"static" };
	...
```

 