
package com.emran.MySQLDemo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@MappedSuperclass
public class BaseModel implements Serializable {

    @Id
    @Column(name="ID", unique = true)
    private Integer id;


    //Default constructor
    public BaseModel(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}


