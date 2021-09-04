package com.jef.context;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 上下文
 *
 * @author Jef
 * @date 2018/9/13 13:56
 */
public class REContext implements Serializable {
    private static final long serialVersionUID = -8239287896179836397L;
    private HashMap items = new HashMap();

    public void put(String key, Object value) {
        this.items.put(key, value);
    }

    public Object get(String key) {
        return this.items.get(key);
    }
}
