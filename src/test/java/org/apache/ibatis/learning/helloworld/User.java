package org.apache.ibatis.learning.helloworld;

import lombok.ToString;

/**
 * 描述:
 *
 * @author HGJ
 * @version 1.0
 * @create 2021-01-05 14:48
 */
@ToString
public class User {
    private Integer id;
    private String name;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
