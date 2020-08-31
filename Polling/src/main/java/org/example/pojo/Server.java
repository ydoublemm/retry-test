package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by caojidasabi on 2020/8/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"currWeight"})
public class Server {

    /**
     * 配置权重
     */
    private Integer weight;

    /**
     * 当前权重
     */
    private Integer currWeight;

    /**
     * ip
     */
    private String ip;



}
