package net.marioplus.demo.fx.fxdemo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * User
 *
 * @author marioplus12
 * @since 2021-09-21
 */
@Data
public class User {

    @ExcelProperty("id")
    private Long id;
    @ExcelProperty("name")
    private String name;
    @ExcelProperty("age")
    private Integer age;
}
