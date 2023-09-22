package com.jef.api.resp;

import lombok.*;

/**
 * @author tufujie
 * @date 2023/9/22
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThirdSystemBaseResp<T> {

    private String code;

    private String msg;

    private boolean success;

    private T data;
}