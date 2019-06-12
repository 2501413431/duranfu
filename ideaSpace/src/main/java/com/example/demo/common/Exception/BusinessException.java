package com.example.demo.common.Exception;

import com.example.demo.common.enums.SysStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/4/26
 */
@Setter
@Getter
public class BusinessException extends Exception {
    private static final long serialVersionUID = 1L;

    private String code = SysStatus.失败.getCode();
    private String msg;

    public BusinessException() {
        super("{code: "+SysStatus.失败.getCode()+", msg: }");
    }

    public BusinessException(String code, String msg) {
        super("{code: "+code+", msg: "+msg+"}");
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String code, String msg, Throwable cause) {
        super("{code: "+code+", msg: "+msg+"}", cause);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        super("{code: "+SysStatus.失败.getCode()+", msg: "+msg+"}");
        this.msg = msg;
    }

    public BusinessException(Throwable cause) {
        super("{code: "+SysStatus.失败.getCode()+", msg: }", cause);
    }

    public BusinessException(String msg, Throwable cause) {
        super("{code: "+SysStatus.失败.getCode()+", msg: "+msg+"}", cause);
        this.msg = msg;
    }
}
