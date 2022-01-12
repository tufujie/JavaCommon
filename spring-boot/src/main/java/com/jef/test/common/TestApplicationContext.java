package com.jef.test.common;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.config.NamedBeanHolder;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class TestApplicationContext extends DefaultResourceLoader implements ApplicationContext {

    private final TestApplicationContext.StubBeanFactory beanFactory = new TestApplicationContext.StubBeanFactory();
    private final String id = ObjectUtils.identityToString(this);
    private final String displayName = ObjectUtils.identityToString(this);
    private final long startupDate = System.currentTimeMillis();
    private final Environment environment = new MockEnvironment();
    private final MessageSource messageSource = new DelegatingMessageSource();

    public TestApplicationContext() {

    }

    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    public String getId() {
        return this.id;
    }

    public String getApplicationName() {
        return "";
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public long getStartupDate() {
        return this.startupDate;
    }

    public ApplicationContext getParent() {
        return null;
    }

    public Environment getEnvironment() {
        return this.environment;
    }

    public void addBean(String name, Object bean) {
        this.beanFactory.addBean(name, bean);
    }

    public void addBeans(@Nullable List<?> beans) {
        if (beans != null) {
            Iterator var2 = beans.iterator();

            while (var2.hasNext()) {
                Object bean = var2.next();
                String name = bean.getClass().getName() + "#" + ObjectUtils.getIdentityHexString(bean);
                this.beanFactory.addBean(name, bean);
            }
        }

    }

    public Object getBean(String name) throws BeansException {
        return this.beanFactory.getBean(name);
    }

    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return this.beanFactory.getBean(name, requiredType);
    }

    public Object getBean(String name, Object... args) throws BeansException {
        return this.beanFactory.getBean(name, args);
    }

    public <T> T getBean(Class<T> requiredType) throws BeansException {
        if (Environment.class.equals(requiredType)) {
            return (T) environment;
        }
        return this.beanFactory.getBean(requiredType);
    }

    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {

        return this.beanFactory.getBean(requiredType, args);
    }

    public <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType) {
        return this.beanFactory.getBeanProvider(requiredType);
    }

    public <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType) {
        return this.beanFactory.getBeanProvider(requiredType);
    }

    public boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return this.beanFactory.isSingleton(name);
    }

    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        return this.beanFactory.isPrototype(name);
    }

    public boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException {
        return this.beanFactory.isTypeMatch(name, typeToMatch);
    }

    public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException {
        return this.beanFactory.isTypeMatch(name, typeToMatch);
    }

    public Class<?> getType(String s, boolean b) throws NoSuchBeanDefinitionException {
        return null;
    }

    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return this.beanFactory.getType(name);
    }


    public String[] getAliases(String name) {
        return this.beanFactory.getAliases(name);
    }

    public boolean containsBeanDefinition(String beanName) {
        return this.beanFactory.containsBeanDefinition(beanName);
    }

    public int getBeanDefinitionCount() {
        return this.beanFactory.getBeanDefinitionCount();
    }

    public String[] getBeanDefinitionNames() {
        return this.beanFactory.getBeanDefinitionNames();
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(Class<T> aClass, boolean b) {
        return null;
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(ResolvableType resolvableType, boolean b) {
        return null;
    }

    public String[] getBeanNamesForType(@Nullable ResolvableType type) {
        return this.beanFactory.getBeanNamesForType(type);
    }

    @Override
    public String[] getBeanNamesForType(ResolvableType resolvableType, boolean b, boolean b1) {
        return new String[0];
    }

    public String[] getBeanNamesForType(@Nullable Class<?> type) {
        return this.beanFactory.getBeanNamesForType(type);
    }

    public String[] getBeanNamesForType(@Nullable Class<?> type, boolean includeNonSingletons, boolean allowEagerInit) {
        return this.beanFactory.getBeanNamesForType(type, includeNonSingletons, allowEagerInit);
    }

    public <T> Map<String, T> getBeansOfType(@Nullable Class<T> type) throws BeansException {
        return this.beanFactory.getBeansOfType(type);
    }

    public <T> Map<String, T> getBeansOfType(@Nullable Class<T> type, boolean includeNonSingletons, boolean allowEagerInit) throws BeansException {
        return this.beanFactory.getBeansOfType(type, includeNonSingletons, allowEagerInit);
    }

    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {
        return this.beanFactory.getBeanNamesForAnnotation(annotationType);
    }

    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) throws BeansException {
        return this.beanFactory.getBeansWithAnnotation(annotationType);
    }

    @Nullable
    public <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType) throws NoSuchBeanDefinitionException {
        return this.beanFactory.findAnnotationOnBean(beanName, annotationType);
    }

    @Override
    public <A extends Annotation> A findAnnotationOnBean(String s, Class<A> aClass, boolean b) throws NoSuchBeanDefinitionException {
        return null;
    }

    public BeanFactory getParentBeanFactory() {
        return null;
    }

    public boolean containsLocalBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    public String getMessage(String code, @Nullable Object[] args, @Nullable String defaultMessage, Locale locale) {
        return this.messageSource.getMessage(code, args, defaultMessage, locale);
    }

    public String getMessage(String code, @Nullable Object[] args, Locale locale) throws NoSuchMessageException {
        return this.messageSource.getMessage(code, args, locale);
    }

    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return this.messageSource.getMessage(resolvable, locale);
    }


    @Nullable
    public ClassLoader getClassLoader() {
        return ClassUtils.getDefaultClassLoader();
    }

    public void publishEvent(ApplicationEvent event) {
    }

    public void publishEvent(Object event) {
    }

    @Override
    public Resource[] getResources(String s) throws IOException {
        return new Resource[0];
    }

    private class StubBeanFactory extends StaticListableBeanFactory implements AutowireCapableBeanFactory {
        private StubBeanFactory() {
        }

        public Object initializeBean(Object existingBean, String beanName) throws BeansException {
            if (existingBean instanceof ApplicationContextAware) {
                ((ApplicationContextAware) existingBean).setApplicationContext(TestApplicationContext.this);
            }

            return existingBean;
        }

        public <T> T createBean(Class<T> beanClass) {
            return BeanUtils.instantiateClass(beanClass);
        }

        public Object createBean(Class<?> beanClass, int autowireMode, boolean dependencyCheck) {
            return BeanUtils.instantiateClass(beanClass);
        }

        public Object autowire(Class<?> beanClass, int autowireMode, boolean dependencyCheck) {
            return BeanUtils.instantiateClass(beanClass);
        }

        public void autowireBean(Object existingBean) throws BeansException {
        }

        public void autowireBeanProperties(Object existingBean, int autowireMode, boolean dependencyCheck) {
        }

        public Object configureBean(Object existingBean, String beanName) {
            return existingBean;
        }

        public <T> NamedBeanHolder<T> resolveNamedBean(Class<T> requiredType) throws BeansException {
            throw new UnsupportedOperationException("Dependency resolution not supported");
        }

        @Override
        public Object resolveBeanByName(String s, DependencyDescriptor dependencyDescriptor) throws BeansException {
            return null;
        }

        @Nullable
        public Object resolveDependency(DependencyDescriptor descriptor, @Nullable String requestingBeanName) {
            throw new UnsupportedOperationException("Dependency resolution not supported");
        }

        @Nullable
        public Object resolveDependency(DependencyDescriptor descriptor, @Nullable String requestingBeanName, @Nullable Set<String> autowiredBeanNames, @Nullable TypeConverter typeConverter) {
            throw new UnsupportedOperationException("Dependency resolution not supported");
        }

        public void applyBeanPropertyValues(Object existingBean, String beanName) throws BeansException {
        }

        public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
            return existingBean;
        }

        public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
            return existingBean;
        }

        public void destroyBean(Object existingBean) {
        }

    }
}