# OAsystem
协同办公平台
# 后端
- 统一使用Intellij IDEA作为代码编写工具
- 使用maven作为项目构建工具
- 统一使用阿里巴巴java编程规范
- api接口使用 restful 风格
- mybatis框架使用 xml 和 接口 映射的模式,需要安装free mybatis plugin插件
- 实体类使用@Data注解,需要引入lombok依赖和安装lombok插件


- ## 注意事项
  + ### github
    - pom文件发生了修改的话一定要在commit的时候备注好，项目的其他人拉取更新时，检查一下pom是否发生修改，如果修改，刷新下pom文件重新引入jar包
    - 模块化commit
        + 当一些功能相同的操作完成后都需要commit到本地仓库一下备注这个模块发生了修改，再去修改其他模块的功能
    - 添加进数据库的数据，都写在table.sql中，以便于在表结构改变时重新执行sql语句，添加数据