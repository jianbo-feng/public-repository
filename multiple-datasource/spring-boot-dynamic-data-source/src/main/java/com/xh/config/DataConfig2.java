package com.xh.config;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 使用ImportBeanDefinitionRegistrar动态加载BeanDefinition
 */
public class DataConfig2 implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private TestBean testBean;

    @Override
    public void setEnvironment(Environment environment) {
        initTestBean(environment);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {

    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        /*BeanDefinition testBean2 = BeanDefinitionBuilder.rootBeanDefinition(TestBean.class)
                .addConstructorArgValue("testBean2").getBeanDefinition();
        registry.registerBeanDefinition("bean2", testBean2);*/

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(TestBean.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("name", "1121212");
        mpv.addPropertyValue("desc", "asdasdfsadf");
        //注册 - BeanDefinitionRegistry
        registry.registerBeanDefinition("bean2", beanDefinition);
    }

    private void initTestBean(Environment env) {
        testBean = new TestBean();
        testBean.setName(env.getProperty("spring.datasource.nodes", ""));
        testBean.setDesc(env.getProperty("spring.datasource.defaultNode", ""));
    }

}
