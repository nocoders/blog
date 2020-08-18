package com.crop.blog.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;

/**
 * mybatis plus generator 生成实体类，mapper，xml
 *
 * @author linmeng
 * @version 1.0
 * @date 7/8/2020 下午4:34
 */
public class MybatisPlusGenerator {
//    private static  final String projectPath = System.getProperty("user.dir");
    // 如果模板引擎是 velocity
    private static  final String templatePath = "/templates/mapper.xml.vm";
    public static void main(String[] args) {
        // 自定义需要填充的字段
        ArrayList<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("create_time",FieldFill.INSERT));
        tableFillList.add(new TableFill("update_time",FieldFill.INSERT_UPDATE));

        AutoGenerator mpg = new AutoGenerator()
                .setGlobalConfig(
                        // 全局配置：输出目录
                        new GlobalConfig()
                            // 文件输出目录
                            .setOutputDir("D:/ideaWorkSpace/blog/mapper/src/main/java")
                            // 是否覆盖文件
                            .setFileOverride(true)
                            // 是否开启二级缓存
                            .setEnableCache(false)
                            // xml resultMap
                            .setBaseResultMap(true)
                            // xml columnList
                            .setBaseColumnList(true)
                            // author
                            .setAuthor("linmeng")
                            // 各个文件名称
                            .setMapperName("%sMapper")
                            .setXmlName("%sMapper")
//                            .setServiceName("%sService")
//                            .setServiceImplName("%sServiceImpl")
//                            .setControllerName("%sController")
                            // 实体属性swagger注解
                            .setSwagger2(true)
                )
                .setDataSource(
                        new DataSourceConfig()
                                .setDbType(DbType.MYSQL)
                                .setTypeConvert(new MySqlTypeConvert(){
                                    @Override
                                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
//                                        if ("bigint".equals(fieldType.toLowerCase())){
//                                            return DbColumnType.LONG;
//                                        }
                                        return super.processTypeConvert(globalConfig, fieldType);
                                    }
                                })
                                .setUrl("jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowMultiQueries=true")
                                .setSchemaName("public")
                                .setDriverName("com.mysql.cj.jdbc.Driver")
                                .setUsername("root")
                                .setPassword("root"))
                .setPackageInfo(
                        new PackageConfig()
                                // 自定义包路径
                                .setParent("com.crop.blog")
                                // 包名
//                                .setController("controller")
//                                .setService("service")
//                                .setServiceImpl("service.impl")
                                .setEntity("model")
                                .setMapper("mapper"))
                .setStrategy(
                        new StrategyConfig()
                                // 全局大写命名
                                .setCapitalMode(true)
                                // 表前缀
                                .setTablePrefix("c_")
                                // 表名生成策略
                                .setNaming(NamingStrategy.underline_to_camel)
                                // 包括表
//                                .setInclude("t_canvas_application")
                                // 不包括表
//                                .setExclude("sdfj")
                                // 自定义实体父类
//                                .setSuperEntityClass(Type.class)
                                // 自定义实体，公共字段
//                                .setSuperEntityColumns("create_time","update_time")
                                .setTableFillList(tableFillList)
                                // 类命名策略
                                .setNaming(NamingStrategy.underline_to_camel)
                                //  字段命名策略
                                .setColumnNaming(NamingStrategy.underline_to_camel)
                                // lombok 注解
                                .setEntityLombokModel(true))
                .setCfg(
                        new InjectionConfig() {
                            @Override
                            public void initMap() {

                            }
                        }.setFileOutConfigList(Collections.singletonList(new FileOutConfig(templatePath) {
                            @Override
                            public String outputFile(TableInfo tableInfo) {
                                return  "D:/ideaWorkSpace/blog/mapper/src/main/resources/com.crop.blog.mapper"
                                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                            }
                        }))
                )
                .setTemplate(new TemplateConfig().setXml(null))
                .setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
