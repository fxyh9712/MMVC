该项目实现了简易版的SpringMVC框架和封装阿里druid链接池。
由我和同学合作完成。我是prosay-fxyh。
Bean容器流程图和MVC流程图在resources文件夹里。

1.通过配置XML实现Bean容器的初始化测试：com.fxyh.test.ApplicationTest

2.通过注解实现mvc框架测试:
	数据库在resources/demo.sql
	com.fxyh.controller.TestController测试控制器
	localhost:8080/test/index.do 测试解析请求路径
	localhost:8080/test/test.do 测试查询数据，进行展示
	
-------------------------------文件列表START--------------------------------------
├── build
│   └── classes
│       └── com
│           └── fxyh
│               ├── controller
│               │   └── TestController.class
│               ├── core
│               │   ├── annotation
│               │   │   ├── Autowired.class
│               │   │   ├── Bean.class
│               │   │   ├── Controller.class
│               │   │   ├── Power.class
│               │   │   ├── RequestMapping.class
│               │   │   └── ResponseBody.class
│               │   ├── ApplicationContext.class
│               │   ├── ApplicationListener.class
│               │   ├── BaseController.class
│               │   ├── BeanContext.class
│               │   ├── CustomView.class
│               │   ├── DispatcherServlet.class
│               │   ├── entity
│               │   │   ├── BeanEntity.class
│               │   │   ├── PageData.class
│               │   │   └── Property.class
│               │   ├── ReturnContent.class
│               │   ├── ReturnType.class
│               │   ├── Uploadable.class
│               │   └── util
│               │       ├── ClassScanner.class
│               │       ├── Contants.class
│               │       └── XmlUtil.class
│               ├── db
│               │   ├── BaseDao.class
│               │   ├── DbUtil.class
│               │   └── PRowMapper.class
│               ├── entity
│               │   └── User.class
│               ├── service
│               │   └── StudentService.class
│               ├── test
│               │   └── ApplicationTest.class
│               └── util
│                   └── SystemUtil.class
├── README.md
├── resources
│   ├── beans.xml
│   ├── Bean容器流程图.png
│   ├── db.properties
│   ├── demo.sql
│   ├── log4j.properties
│   └── MVC流程图.png
├── src
│   └── com
│       └── fxyh
│           ├── controller
│           │   └── TestController.java
│           ├── core
│           │   ├── annotation
│           │   │   ├── Autowired.java
│           │   │   ├── Bean.java
│           │   │   ├── Controller.java
│           │   │   ├── Power.java
│           │   │   ├── RequestMapping.java
│           │   │   └── ResponseBody.java
│           │   ├── ApplicationContext.java
│           │   ├── ApplicationListener.java
│           │   ├── BaseController.java
│           │   ├── BeanContext.java
│           │   ├── CustomView.java
│           │   ├── DispatcherServlet.java
│           │   ├── entity
│           │   │   ├── BeanEntity.java
│           │   │   ├── PageData.java
│           │   │   └── Property.java
│           │   ├── ReturnContent.java
│           │   ├── ReturnType.java
│           │   ├── Uploadable.java
│           │   └── util
│           │       ├── ClassScanner.java
│           │       ├── Contants.java
│           │       └── XmlUtil.java
│           ├── db
│           │   ├── BaseDao.java
│           │   ├── DbUtil.java
│           │   └── PRowMapper.java
│           ├── entity
│           │   └── User.java
│           ├── service
│           │   └── StudentService.java
│           ├── test
│           │   └── ApplicationTest.java
│           └── util
│               └── SystemUtil.java
└── WebContent
    ├── META-INF
    │   └── MANIFEST.MF
    └── WEB-INF
        ├── lib
        │   ├── dom4j.jar
        │   ├── druid-1.0.12.jar
        │   ├── fastjson-1.2.26.jar
        │   ├── jstl-1.2.jar
        │   ├── log4j-1.2.17.jar
        │   └── mysql-jdbc-5.1.5.jar
        ├── views
        │   └── test
        │       ├── index.jsp
        │       └── test.jsp
        └── web.xml

-------------------------------文件列表END--------------------------------------
更多测试demo，以后有空再更新。