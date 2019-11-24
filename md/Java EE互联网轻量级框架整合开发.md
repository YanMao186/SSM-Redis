## 为什么选择SSM框架+Redis的开发模式

- **Spring**的核心是**IOC**(**控制反转**)，它是一个容器，方便组装和管理各类系统内外资源，同时支持**AOP**(**面向切面**)，这是对面向对象的补充，目前广泛用于日志和数据库事务控制，减少了大量的重复代码，使得程序更为清晰。因为**Spring**可以使模块**解耦**，控制对象之间的协作，所以**Spring**框架是目前**Java**最流行的框架，几乎没有之一。
- 对于**Hibernate**而言，在需要存储过程或者复杂**SQL**时，它的映射关系几乎完全用不上，所有的问题都需要自己敲代码处理。作为全映射的框架，它的**致命缺点是没有办法完全掌握数据库的SQL**，而**优化SQL是高并发，高响应系统的必然要求**，这是互联网系统的的普遍特性，所以**Hibernate**在互联网系统中被排除了。而**MyBatis**，它需要编写**SQL**，提供映射规则，不过它加入了**动态SQL**，**自动映射**，接口编程等功能使得它简单易用，同时支持**SQL**优化，动态绑定，并**满足高并发和高响应**的要求，所以它成为了最流行的**Java**互联网持久框架。
- **NoSQL**的成功在于，首先它是**基于内存**的，而内存的速度是磁盘读取速度的几十倍到上百倍，所以**NoSQL**工具的速度要比数据库的速度快得多，**满足了高响应的要求**。即使**NoSQL**将数据放在磁盘中，它也是一种**半结构化**的数据格式，读取到解析的复杂度远比数据库要简单，这是因为数据库存储的是经过结构化，多范式等有复杂规则的数据，还原为内存结构的速度较慢。**NoSQL在很大程度上满足了高并发，快速读/写和响应的要求**，所以它也是**Java**互联网系统的利器。

## 第一章 认识SSM框架和Redis

### Spring框架

#### Spring IOC简介

- **IOC**是一个容器，在**Spring**中，它会认为一切**Java**资源都是**Java Bean**，容器的目标就是管理这些**Bean**和它们之间的关系。所以在**Spring IOC**里面装载的各种**Bean**，也可以理解为**Java**的各种资源，包括**Java Bean**的创建，事件，行为等，它们又被**IOC**容器管理。除此之外，各个**Java Bean**之间会存在一定的依赖关系，比如班级是依赖于老师和学生组成的，假设老师，学生都是**Java Bean**，那么显然两者之间形成了依赖关系。这些**Spring IOC**容器都能够对其进行管理，只是**Spring IOC**管理对象和其依赖关系，采用的不是人为的主动创建，而是**Spring IOC**自己通过描述创建的，也就是说**Spring**是依靠描述来完成对象的创建及其依赖关系的。
- 接口不再依赖于某个实现类，需要使用某个实现类时我们通过配置信息就可以完成了，依赖关系也可以通过配置完成，从而完全可以即插即拔地管理它们之间的关系。
- 你不需要去找资源，只要向**Spring IOC**容器描述所需资源，**Spring IOC**自己会找到你所需的资源，这就是**Spring IOC**的理念。这样就把**Bean**之间的依赖关系解耦了，更容易写出结构清晰的程序。

#### Spring AOP

- **IOC**的目标就是为了管理**Bean**，而**Bean**是**Java**面向对象(**OOP**)的基础设计，比如声明一个用户类都是基于面向对象的概念。
- 有些情况是面向对象没有办法处理的，举个例子，生产部门的订单，生产部门，财务部门三者符合**OOP**的设计理念。订单发出，生产部门审核通过准备付款，但是财务部门发现订单的价格超支了，需要取消订单。显然超支限定已经不只是影响财务部门，还会影响生产部门之前所作的审批，需要把它们作废。我们把预算超支这个条件称为切面，它影响了订单，生成部门和财务部门3个**OOP**对象。
- **Spring AOP**常用于数据库事务的编程，很多情况都如同上面的例子，我们在做完第一步数据库数据更新后，不知道下一步是否会成功，如果下一步失败，会使用数据库事务的回滚功能去回滚事务，使得第一步的数据库更新也作废。在**Spring AOP**实现的数据库事务管理中，是以异常作为消息的。在默认情况下，只要**Spring**接受到了异常消息，它就会将数据库的事务回滚，从而保证数据的一致性。
- 在**Spring IOC**中有些东西被**Spring**屏蔽了，不需要关注它，只需关注业务代码，知道只要发生了异常，**Spring**就会回滚事务就足够了。

### MyBatis简介

- **MyBatis**的优势在于灵活，它几乎可以代替**JDBC**，同时提供了接口编程。目前**MyBatis**的数据访问层**DAO**是不需要实现类的，它只是一个**接口**和**XML**(或**注解**)。**MyBatis**提供自动映射，动态**SQL**，级联，缓存，注解，代码和**SQL**等特性，使用方便，同时可以对**SQL**进行优化。

#### Hibernate简介

- 要将**POJO**和数据库映射起来需要给这些框架提供映射规则。

- 在**MyBatis**或**Hibernate**中可以通过**XML**或**注解**提供映射规则，因为在**MyBatis**中注解方式会受到一定的限制，所以**MyBatis**通常使用**XML**方式实现映射关系。

- 我们把**POJO**对象和数据库表相互映射的框架称为``对象关系映射(**ORM**)框架``。无论**MyBatis**或**Hibernate**都可以称为**ORM**框架，只是**Hibernate**的设计理念是完全面向**POJO**的。而**Mybatis**不是。**Hibernate**基本不在需要编写**SQL**就可以通过映射关系来操作数据库，是一种全表映射的体现；而**MyBatis**则不同，它需要我们提供**SQL**去运行。

- ````java
  public class Role implements Serializable {
      private Integer id;
      private String rolename;
      private String note;
      setting && getting
  }
  ````

- Hibernate映射文件

- ````xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE hibernate-mapping PUBLIC
          "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
          "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
  <!-- 配置表与实体对象的关系 -->
  <hibernate-mapping>
      <!--
          class元素: 配置实体与表的对应关系的
              name: 完整类名
              table:数据库表名
       -->
      <class name="com.ym.pojo.Role" table="t_role" >
          <!-- id元素:配置主键映射的属性
                  name: 填写主键对应属性名
                  column(可选): 填写表中的主键列名.默认值:列名会默认使用属性名
                  type(可选):填写列(属性)的类型.hibernate会自动检测实体的属性类型.
                          每个类型有三种填法: java类型|hibernate类型|数据库类型
                  not-null(可选):配置该属性(列)是否不能为空. 默认值:false
                  length(可选):配置数据库中列的长度. 默认值:使用数据库类型的最大长度
           -->
          <id name="id"  >
              <!-- generator:主键生成策略 -->
              <generator class="native"></generator>
          </id>
          <!-- property元素:除id之外的普通属性映射
                  name: 填写属性名
                  column(可选): 填写列名
                  type(可选):填写列(属性)的类型.hibernate会自动检测实体的属性类型.
                          每个类型有三种填法: java类型|hibernate类型|数据库类型
                  not-null(可选):配置该属性(列)是否不能为空. 默认值:false
                  length(可选):配置数据库中列的长度. 默认值:使用数据库类型的最大长度
           -->
          <property name="roleName" column="role_name" >
              <!--  <column name="cust_name" sql-type="varchar" ></column> -->
          </property>
          <property name="note" column="note" ></property>
      </class>
  </hibernate-mapping>
  
  ````

- Hibernate通过Session操作数据库数据

- ````java
  Session session = null;
  Transaction tx = null;
  try {
      //打开session
      session = HibernateUtil.getSessionFactory().openSession();
      //事务
      tx = session.beginTransaction();
      //POJO
      Role role = new Role();
      role.setId(1);
      role.setRoleName("rolename1");
      role.setNote("note1");
      //保存
      session.save(role);
      //查询
      Role role2 = (Role)session.get(Role.class,1);
      role2.setNote("修改备注");
      //更新
      session.update(role2);
      System.err.println(role2.getRoleName());
      删除
      session.delete(role2);
      //提交事务
      tx.commit();
  }catch(Exception e) {
      if (tx != null && tx.isActive()) {
          //回滚事务
          tx.rollback();
      }
      e.printStackTrace();
  }finally {
      if (session != null && session.isOpen()) {
          session.close();
      }
  }
  ````

- 这里我们没有看到**SQL**，那是因为**Hibernate**会根据映射关系来生成对应的**SQL**。

#### MyBatis

- 与**Hibernate**消除**SQL**不同，**MyBatis**不屏蔽**SQL**。不屏蔽**SQL**的优势在于，程序员可以自己制定**SQL**规则，无须**Hibernate**自动生成规则，这样能够更加精确地定义**SQL**，从而优化性能。``它更符合移动互联网高并发，大数据，高性能，高响应的要求。``

- MyBatis映射文件

- ````xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
  <mapper namespace="com.ym.mapper.RoleMapper">
  
      <!-- resultMap用于实现映射结果集 -->
      <resultMap id="roleMap" type="com.ym.pojo.Role">
          <id column="id" property="id" jdbcType="INTEGER"/>
          <result column="role_name" property="rolename" jdbcType="VARCHAR"/>
          <result column="note" property="note" jdbcType="VARCHAR"/>
      </resultMap>
  
      <select id="getRole" parameterType="roleMap">
         select * from role_name where id = #{id}
      </select>
  
      <delete id="deleteRole" parameterType="int">
          delete from role_name where id = #{id}
      </delete>
  
      <insert id="insertRole" parameterType="com.ym.pojo.Role">
          insert into t_role(role_name,note) values (#{roleName},#{note})
      </insert>
  
      <!-- 更新客户信息的方法 -->
      <update id="updateRole" parameterType="com.ym.pojo.Role">
          update t_role set role_name = #{roleName},note = #{note} where id = #{id}
      </update>
  </mapper>
  ````

- **resultMap**元素用于定义映射规则，而实际上**MyBatis**在满足一定的规则下，完成自动映射，而``增，删，改，查``对应着**instrt**,**delete**,**update**,**select**四个元素，**mapper**元素中的**namespace**属性，它要和一个接口的全限定名保持一致，而里面的**SQL**的**id**也需要和接口定义的方法名完全保持一致。

- 定义**MyBatis**的映射文件

- ````java
  public interface RoleMapper {
      public Role getRole(Integer id);
      public int deleteRole(Integer id);
      public int insertRole(Role role);
      public int updateRole(Role role);
  }
  ````

- **MyBatis**对角色类的增，删，改，查

- ````java
  SqlSession sqlsession = null;
  try {
      sqlsession MyBatisUtil.getSqlSession();
      RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
      Role role = roleMapper.getRole(1);//查询
      System.out.println(role.getRoleName());
      role.setRoleName("update_role_name");
      roleMapper.updateRole(role);//更新
      Role role2 = new Role();
      role2.setNote("note2");
      role2.setRoleName("role2");
      roleMapper.insertRole(role);//插入
      roleMapper.deleteRole(5)//删除
          sqlSession.commit();//提交事务
  }catch(Exception e) {
      e.printStackTrace();
      if (sqlSession != null ) {
          sqlSession.rollback();//回滚事务
      }
  }finally {
      if (sqlSession != null) {
          sqlSession.close();
      }
  }
  ````

- **MyBatis**与**Hibernate**，其区别在于，**MyBatis**需要提供接口和**SQL**，这意味着它的工作量会比**Hibernate**大，但是由于自定义**SQL**，映射关系，所以其灵活性，可优化性就超过了**Hibernate**。

#### Hibernate和MyBatis的区别

- 对于映射层而言**Hibernate**的配置不需要接口和**SQL**，相反**MyBatis**是需要的。
- 对于**Hibernate**而言，不需要编写大量的**SQL**，就可以完全映射，同时提供了日志，缓存，级联(级联比**MyBatis**强大)等特性。
- 由于无须**SQL**，当多表关联超过**3个**的时候，通过**Hibernate**的级联会造成太多性能的丢失。
- **Mybatis**可以自由书写**SQL**，支持动态**SQL**，处理列表，动态生成表明，支持存储过程。
- 但是**MyBatis**也有缺陷，它要编写**SQL**和映射规则，其工作量稍微大于**Hibernate**。其次，它支持的工具也有限，不像**Hibernate**那样有许多的插件可以帮助生成映射代码和关联关系。

### Spring MVC简介

- **MVC**模式把应用程序(输入逻辑，业务逻辑和UI逻辑)分成不同的方面，同时提供这些元素之间的耦合。
  - **Model**(模型)，封装了应用程序的数据和由它们组成的**POJO**。
  - **View**(视图),负责把模型数据渲染到视图上，将数据以一定的形式展现给用户。
  - **Controller**(控制器)，负责处理用户请求，并建立适当的模型把它传递给视图渲染。
- **Spring MVC**的重点在于它的流程和一些重要的注解，包括控制器，视图解析器，视图等重要内容。

### NoSQL-Redis

- **Redis**是当前互联网世界最为流行的**NoSQL**(Not Only SQL)。**NoSQL**在互联网系统中的作用很大，因为它可以在很大程度上提高互联网系统的性能。它具备一定``持久层``的功能，也可以作为一种缓存工具，``作为持久层，它存储的数据是半结构化的``，这就意味着计算机在读入内存中有更少的规则，读入速度更快。``对于那些结构化，多范式规则的数据库系统而言，它更具性能优势``。作为缓存，它可以支持大数据存入内存中，只要命中率高，它就能快速响应，因为在内存中的数据读/写比数据库读/写磁盘的速度快几十到几百倍。
- **Redis**成为主要的**NoSQL**工具，其原因如下：
  - **响应快速**：**Redis**响应非常快，每秒可以执行大约**110000个写入操作**，或者**81000个读操作**，其速度远超数据库。
  - **支持六种数据类型**：**String**，**hash**，**list**，**set**，**zset**和**基数**。对于**Redis**而言，虽然只有**6种**数据类型，但是有两大好处：**一方面可以满足存储各种数据结构体的需要**；另一方面数据类型少，**使得规则就少，需要判断和逻辑就少，这样读/写的速度就更快**。
  - **操作都是原子性的**：在**高并发**的场合可以考虑使用**Redis**的事务，处理一些需要**锁**的业务。
  - **MultiUtility**：**Redis**可以在如**缓存**，**消息传递队列**中使用(Redis支持“发布+订阅”的消息模式)。