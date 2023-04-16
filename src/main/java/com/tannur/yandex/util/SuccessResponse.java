package com.tannur.yandex.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class SuccessResponse {
    private int status;
    private Map<String, Integer> data;
}
