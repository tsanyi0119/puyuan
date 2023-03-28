package com.example.puyuan.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String status;

    public static AuthenticationResponse SUCCESS() {
        return AuthenticationResponse.builder().status(Result.SUCCESS.getValue()).build();
    }
    public static AuthenticationResponse FAILED() {
        return AuthenticationResponse.builder().status(Result.FAILED.getValue()).build();
    }
    @Getter
    @AllArgsConstructor
    public enum Result {
        /** 操作成功 */
        SUCCESS("0"),
        /** 操作失敗 */
        FAILED("1");

        /** 定義狀態碼 **/
        private final String value;
    }
}
