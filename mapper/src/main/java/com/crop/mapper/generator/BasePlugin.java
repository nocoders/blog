package com.crop.mapper.generator;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.template.TemplateException;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.io.*;
import java.text.DateFormat;
import java.util.*;

/**
 * 开发公司：青岛海豚数据技术有限公司
 * 版权：青岛海豚数据技术有限公司
 * <p>
 * BasePlugin
 *
 * @author 刘志强
 * @created Create Time: 2019/1/16
 * github:https://github.com/2425358736/mybatis-generator-demo
 */
public class BasePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 实体类添加 swagger注解,lombok注解
     * @param topLevelClass
     * @param introspectedTable
     * @author linmeng
     * @date 31/8/2020 上午9:33 
     * @return boolean
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//        classAnnotation(topLevelClass,introspectedTable.getRemarks());
        Set<FullyQualifiedJavaType> set = new HashSet<FullyQualifiedJavaType>();
        set.add(new FullyQualifiedJavaType(Annotation.ApiModel.getClazz()));
        set.add(new FullyQualifiedJavaType(Annotation.DATA.getClazz()));
        topLevelClass.addImportedTypes(set);
        topLevelClass.addAnnotation(Annotation.ApiModel.getAnnotation()+"(value = \"" + topLevelClass.getType()+
                "\",description=\"" + introspectedTable.getRemarks() + "\")");
        topLevelClass.addAnnotation(Annotation.DATA.getAnnotation());

        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    /**
     * 接口注释 annotation注解添加
     * @param interfaze
     * @param topLevelClass
     * @param introspectedTable
     * @author linmeng
     * @date 31/8/2020 上午10:12
     * @return boolean
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

//        interfazeAnnotation(interfaze,introspectedTable.getRemarks());
        Set<FullyQualifiedJavaType> set = new HashSet<FullyQualifiedJavaType>();
        set.add(new FullyQualifiedJavaType(Annotation.Mapper.getClazz()));
        interfaze.addImportedTypes(set);
        interfaze.addAnnotation(Annotation.Mapper.getAnnotation());

        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    /**
     * 实体类字段适配
     * @param field
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @author linmeng
     * @date 31/8/2020 上午11:25
     * @return boolean
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
//        fieldAnnotation(field,introspectedColumn.getRemarks());

        // 追加ApiModelProperty注解
        topLevelClass.addImportedType(new FullyQualifiedJavaType(Annotation.ApiModelProperty.getClazz()));
        field.addAnnotation(Annotation.ApiModelProperty.getAnnotation() + "(value=\"" + introspectedColumn.getRemarks() + "\",name=\"" + introspectedColumn.getJavaProperty() + "\")");
        // 追加长度验证注解
        String a = field.getType().getShortName();
        if (StringUtils.equals("String", a)) {
            topLevelClass.addImportedType(new FullyQualifiedJavaType(Annotation.Length.getClazz()));
            field.addAnnotation(Annotation.Length.getAnnotation() + "(max = " + introspectedColumn.getLength() + ", message = \"" + introspectedColumn.getRemarks() + "名长度最长为" + introspectedColumn.getLength() + "\")");
        }
        // 追加日期格式化注解
        if (introspectedColumn.getJdbcTypeName() == "TIMESTAMP") {
            field.addAnnotation(Annotation.JsonFormat.getAnnotation() + "(pattern = \"yyyy-MM-dd HH:mm:ss\",timezone=\"GMT+8\")");
            topLevelClass.addImportedType(new FullyQualifiedJavaType(Annotation.JsonFormat.getClazz()));
        }
        // tinyint数据（Byte）转换成（Integer）类型
        if (StringUtils.equals("Byte", a)) {
            field.setType(new FullyQualifiedJavaType("java.lang.Integer"));
        }

        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    /**
     * set方法不生成
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @author linmeng
     * @date 31/8/2020 上午11:30
     * @return boolean
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * get 方法不生成
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @author linmeng
     * @date 31/8/2020 上午11:31
     * @return boolean
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * mapper 接口方法：根据主键删除数据
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @author linmeng
     * @date 31/8/2020 上午11:33
     * @return boolean
     */
    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method,"");

        return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }

    /**
     * mapper 接口方法：数据完全插入
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @author linmeng
     * @date 2/9/2020 下午4:24
     * @return boolean
     */
    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "插入数据库记录（不建议使用）");
        return super.clientInsertMethodGenerated(method, interfaze, introspectedTable);
    }

    /**
     * mapper 接口方法：数据选择插入
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @author linmeng
     * @date 2/9/2020 下午4:26
     * @return boolean
     */
    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method,"插入数据库记录（建议使用）");
        return super.clientInsertSelectiveMethodGenerated(method, interfaze, introspectedTable);
    }

    /**
     * mapper方法:主键数据查询
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @author linmeng
     * @date 2/9/2020 下午4:27
     * @return boolean
     */
    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "根据主键id查询");
        return super.clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }

    /**
     * mapper接口方法
     * UpdateByPrimaryKeySelective方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "修改数据(推荐使用)");
        return super.clientUpdateByPrimaryKeySelectiveMethodGenerated(method, interfaze, introspectedTable);
    }

    /**
     * mapper接口方法
     * UUpdateByPrimaryKey方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "修改数据");
        return super.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable);
    }
    /**
     * 方法注释生成
     * @param method
     * @param explain
     * @author linmeng
     * @date 31/8/2020 上午11:35
     * @return void
     */
    public static void methodAnnotation(Method method,String explain){
        // 生成注释
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(explain);
        method.addJavaDocLine(sb.toString());
        Parameter parm = method.getParameters().get(0);
        sb.setLength(0);
        sb.append(" * @param ");
        sb.append(parm.getName()).append(" 删除数据主键id");
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
    }
    /**
     * 实体类字段注释生成
     * @param field
     * @param explain
     * @author linmeng
     * @date 31/8/2020 上午11:26
     * @return void
     */
    public static void fieldAnnotation(Field field, String explain) {
        // 生成注释
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(explain);
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
        // 生成注释结束
    }
    /**
     * 类注释生成
     * @param topLevelClass
     * @param explain
     * @author linmeng
     * @date 31/8/2020 上午10:00
     * @return void
     */
    public static void classAnnotation(TopLevelClass topLevelClass, String explain){
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* "+explain);
        topLevelClass.addJavaDocLine("* " + topLevelClass.getType().getShortName());
        topLevelClass.addJavaDocLine("*");
        topLevelClass.addJavaDocLine("* @author linmeng");
        topLevelClass.addJavaDocLine("* @version 1.0");
        topLevelClass.addJavaDocLine("* @date " + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        topLevelClass.addJavaDocLine("*/");
    }
    /**
     * 接口注释生成
     * @param interfaze
     * @param explain
     * @author linmeng
     * @date 31/8/2020 上午10:01
     * @return void
     */
    public static void interfazeAnnotation(Interface interfaze, String explain) {
        // 生成注释
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine("* "+explain);
        interfaze.addJavaDocLine("* " + interfaze.getType().getShortName());
        interfaze.addJavaDocLine("*");
        interfaze.addJavaDocLine("* @author linmeng");
        interfaze.addJavaDocLine("* @date " + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        interfaze.addJavaDocLine("*/");
        // 生成注释结束
    }
}
