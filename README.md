# baole-search
es搜索客户端接入说明
es客户端，直连dsl太难维度，spring-data-es提供的客户端不好用，所以，本人自己封装了es使用客户端，并且在某二线厂已经过检验，完全ok

1.下载代码，配置es的地址：配置类：SearchConfig.java
2.新建的每个es索引，实现EsQueryIndex接口，这个接口的主要作用就是根据不同的索引和实体类，将es数据转为具体对象


