package com.example.demo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/4/30
 */
public class BaseEntity {
    public String toString() {
        try {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        } catch (Exception var2) {
            var2.printStackTrace();
            return super.toString();
        }
    }
}
