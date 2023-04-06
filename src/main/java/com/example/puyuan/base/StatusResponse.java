package com.example.puyuan.base;

import com.example.puyuan.auth.AuthenticationResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse {
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String verificationCode;

    public static StatusResponse SUCCESS() {
        return StatusResponse.builder().status(StatusResponse.Result.SUCCESS.getValue()).build();
    }
    public static StatusResponse FAILED() {
        return StatusResponse.builder().status(StatusResponse.Result.FAILED.getValue()).build();
    }
    @Getter
    @AllArgsConstructor
    public enum Result {
        //成功
        SUCCESS("0"),
        //失敗
        FAILED("1");

        private final String value;
    }
}
