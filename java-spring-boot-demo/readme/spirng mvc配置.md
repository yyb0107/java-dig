### spring mvc 配置  (annotation方式)
---
 * #### 默认配置从哪里来
`spring-boot-autoconfigure-2.0.3.RELEASE.jar`里面的`WebMvcAutoConfiguration.class`和`WebMvcProperties`
 
* 为什么web项目需要创建一个src/main/webapp文件夹 

 因为在`spring-boot-2.0.3.RELEASE.jar`的`DocumentRoot`定义
```java
class DocumentRoot {
	private static final String[] COMMON_DOC_ROOTS = { "src/main/webapp", "public",
			"static" };
	...
```
 