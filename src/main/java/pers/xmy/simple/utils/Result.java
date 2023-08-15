package pers.xmy.simple.utils;

import lombok.Data;

/**
 * @author Yao
 * @version 1.0
 */

@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

}
