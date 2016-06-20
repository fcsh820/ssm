	shift+f6 重命名
	Ctrl+O 选择父类的方法进行重写
	Ctrl+Shift+Enter 自动补全末尾的字符。分号、括号、if/for的花括号。
    Ctrl+J 然后按下p键,弹出4个以p开头的快捷键组合
    Alt+Shift+Insert 列编辑
    Ctrl + B 跳转到定义处。Ctrl + Alt + B 跳转到方法实现处
    Ctrl+X，删除行
    Ctrl+D，复制行
    Ctrl + / 注释 / 反注释。Ctrl + Shift + / 多行语句的注释
    Ctrl + ]/[ 跳转到代码块结束 / 开始处
    Ctrl+F12 显示当前文件的结构
    Alt+Shift+Up/Down，上/下移一行
    Ctrl+Shift+U，大小写转化
    Shift+Enter 向下插入新行
    alt+insert Generate
    Ctrl+Alt+L 自动格式化代码
    Ctrl+W 按语法选中代码的以。反向的Ctrl+Shift+W了
    Ctrl+U，转到父类
    Ctrl+Shift+Up/Down，向上/下移动语句
    Alt+Up/Down，在方法间快速移动定位
    Ctrl + Alt + T 用if、while、try catch来围绕选中的代码行
    Ctrl+P 显示参数信息
    Ctrl + Z ，撤销。Ctrl + Shift + Z ，取消撤销

规范：
    1、mysql表名、字段名，单词全部小写，下划线分隔
    2、url全部小写，单词下划线分隔
    3、po、vo、方法等，pascal，首字母小写，后面单词首字母大写

可使用技术：
    HTTL (Hyper-Text Template Language) 是一个高性能的开源JAVA模板引擎， 适用于动态HTML页面输出， 可替代JSP页面， 指令和Velocity相似。 http://httl.github.io/zh/
    注解事务 @Transactional
    apache shiro 是功能强大并且容易集成的开源权限框架
    JFinal 极速开发

参考资料：
	*https://git.oschina.net/wangzhixuan/spring-shiro-training.git  springmvc-spring-mybatis-shiro-easyui权限系统 
	*http://shiro.carp.mopaasapp.com/index test test
	*http://blog.csdn.net/u012750578/article/details/18996109 UI
	http://blog.csdn.net/u013517797/article/category/5670995/2 【springmvc+mybatis项目实战】

配置
    1、需引入tomcat下的两个包
        jsp-api.jar
        servlet-api.jar

	2、黑色主题 Darcula
        设置方式：FILE--Settings--Edit--Colors&Fonts--Scheme name

    3、Intellij IDEA 添加jar包
        Project Structure...-->Libraries-->+

    4、自动生成serialVersionUID
        Setting->Inspections->java->Serialization issues->Serializable class without ’serialVersionUID’
        在你的class中：光标定位在类名前，按 Alt+Enter 就会提示自动创建 serialVersionUID 了。

测试
    1、在类名上按Alt+Enter，Create Test

插件
    1、添加JUnit插件
        Settings-Plugins-Browse repositories-搜索JunitGenerator V2.0版本-鼠标右击-选择Download and Install-Restart
        在需要进行单元测试的类中Alt+Insert，则会弹出JUnit Test菜单

遇到的问题
	1、Error:java: Compilation failed: internal java compiler error
	    setting->Compiler->Java Compiler 设置相应Module的target byte code version的合适版本就行来。

	2、@Override is not allowed when implementing interface method
      	    File-->Project Structure...-->Module-->选中模型-->Language Level
      	    选择6-@Override in interfaces

    3、json 406错误
        url为*.htm时会报406错误，url改为*.json或*.do正常。
        springmvc不同的url后缀返回不同格式的数据 http://liyonghui160com.iteye.com/blog/2158982


