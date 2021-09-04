package com.jef.entity;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 复杂对象
 *
 * @author Jef
 * @date 2020/12/5
 */
public class ComplexObject {

    /** 
     * 管理员邮件
     */
    private Properties adminEmails;

    private Map<String, Object> someMap;

    private List<String> someList;

    private Set<String> someSet;

    public Properties getAdminEmails() {
        return adminEmails;
    }

    public void setAdminEmails(Properties adminEmails) {
        this.adminEmails = adminEmails;
    }

    public Map<String, Object> getSomeMap() {
        return someMap;
    }

    public void setSomeMap(Map<String, Object> someMap) {
        this.someMap = someMap;
    }

    public List<String> getSomeList() {
        return someList;
    }

    public void setSomeList(List<String> someList) {
        this.someList = someList;
    }

    public Set<String> getSomeSet() {
        return someSet;
    }

    public void setSomeSet(Set<String> someSet) {
        this.someSet = someSet;
    }
}