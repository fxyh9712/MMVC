该项目实现了简易版的SpringMVC框架和封装阿里druid链接池。
由我和同学合作完成。我是prosay-fxyh。
Bean容器流程图和MVC流程图在resources文件夹里。

1.通过配置XML实现Bean容器的初始化测试：com.fxyh.test.ApplicationTest

2.通过注解实现mvc框架测试:
	数据库在resources/demo.sql
	com.fxyh.controller.TestController测试控制器
	localhost:8080/test/index.do 测试解析请求路径
	localhost:8080/test/test.do 测试查询数据，进行展示
	
-------------------------------**文件列表START**--------------------------------------<br />
<br />
├── build<br />
│   └── classes<br />
│       └── com<br />
│           └── fxyh<br />
│               ├── controller<br />
│               │   └── TestController.class<br />
│               ├── core<br />
│               │   ├── annotation<br />
│               │   │   ├── Autowired.class<br />
│               │   │   ├── Bean.class<br />
│               │   │   ├── Controller.class<br />
│               │   │   ├── Power.class<br />
│               │   │   ├── RequestMapping.class<br />
│               │   │   └── ResponseBody.class<br />
│               │   ├── ApplicationContext.class<br />
│               │   ├── ApplicationListener.class<br />
│               │   ├── BaseController.class<br />
│               │   ├── BeanContext.class<br />
│               │   ├── CustomView.class<br />
│               │   ├── DispatcherServlet.class<br />
│               │   ├── entity<br />
│               │   │   ├── BeanEntity.class<br />
│               │   │   ├── PageData.class<br />
│               │   │   └── Property.class<br />
│               │   ├── ReturnContent.class<br />
│               │   ├── ReturnType.class<br />
│               │   ├── Uploadable.class<br />
│               │   └── util<br />
│               │       ├── ClassScanner.class<br />
│               │       ├── Contants.class<br />
│               │       └── XmlUtil.class<br />
│               ├── db<br />
│               │   ├── BaseDao.class<br />
│               │   ├── DbUtil.class<br />
│               │   └── PRowMapper.class<br />
│               ├── entity<br />
│               │   └── User.class<br />
│               ├── service<br />
│               │   └── StudentService.class<br />
│               ├── test<br />
│               │   └── ApplicationTest.class<br />
│               └── util<br />
│                   └── SystemUtil.class<br />
├── README.md<br />
├── resources<br />
│   ├── beans.xml<br />
│   ├── Bean容器流程图.png<br />
│   ├── db.properties<br />
│   ├── demo.sql<br />
│   ├── log4j.properties<br />
│   └── MVC流程图.png<br />
├── src<br />
│   └── com<br />
│       └── fxyh<br />
│           ├── controller<br />
│           │   └── TestController.java<br />
│           ├── core<br />
│           │   ├── annotation<br />
│           │   │   ├── Autowired.java<br />
│           │   │   ├── Bean.java<br />
│           │   │   ├── Controller.java<br />
│           │   │   ├── Power.java<br />
│           │   │   ├── RequestMapping.java<br />
│           │   │   └── ResponseBody.java<br />
│           │   ├── ApplicationContext.java<br />
│           │   ├── ApplicationListener.java<br />
│           │   ├── BaseController.java<br />
│           │   ├── BeanContext.java<br />
│           │   ├── CustomView.java<br />
│           │   ├── DispatcherServlet.java<br />
│           │   ├── entity<br />
│           │   │   ├── BeanEntity.java<br />
│           │   │   ├── PageData.java<br />
│           │   │   └── Property.java<br />
│           │   ├── ReturnContent.java<br />
│           │   ├── ReturnType.java<br />
│           │   ├── Uploadable.java<br />
│           │   └── util<br />
│           │       ├── ClassScanner.java<br />
│           │       ├── Contants.java<br />
│           │       └── XmlUtil.java<br />
│           ├── db<br />
│           │   ├── BaseDao.java<br />
│           │   ├── DbUtil.java<br />
│           │   └── PRowMapper.java<br />
│           ├── entity<br />
│           │   └── User.java<br />
│           ├── service<br />
│           │   └── StudentService.java<br />
│           ├── test<br />
│           │   └── ApplicationTest.java<br />
│           └── util<br />
│               └── SystemUtil.java<br />
└── WebContent<br />
    ├── META-INF<br />
    │   └── MANIFEST.MF<br />
    └── WEB-INF<br />
        ├── lib<br />
        │   ├── dom4j.jar<br />
        │   ├── druid-1.0.12.jar<br />
        │   ├── fastjson-1.2.26.jar<br />
        │   ├── jstl-1.2.jar<br />
        │   ├── log4j-1.2.17.jar<br />
        │   └── mysql-jdbc-5.1.5.jar<br />
        ├── views<br />
        │   └── test<br />
        │       ├── index.jsp<br />
        │       └── test.jsp<br />
        └── web.xml<br />
<br />
-------------------------------**文件列表END**--------------------------------------<br />
更多测试demo，以后有空再更新。