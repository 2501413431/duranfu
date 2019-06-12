package com.example.demo.common.datasSource;

import com.example.demo.common.datasSource.DatabaseType;

/**
 * 保存一个线程安全的DatabaseType容器
 */
public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDataBaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getDataBaseType() {
        return contextHolder.get();
    }
}
