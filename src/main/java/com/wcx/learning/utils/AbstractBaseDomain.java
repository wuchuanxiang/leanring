package com.wcx.learning.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用的领域模型
 * <p>Title: AbstractBaseDomain</p>
 * <p>Description: </p>
 *
 * @author bjyxjt
 * @version 1.0.0
 * @date 2019/1/23 15:50
 */
@Data
public abstract class AbstractBaseDomain implements Serializable {
    /**
     * 该注解需要保留，用于 tk.mybatis 回显 ID
     */
    private Long id;



}
