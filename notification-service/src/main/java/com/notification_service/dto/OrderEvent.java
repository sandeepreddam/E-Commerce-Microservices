package com.notification_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderEvent {

    private Long orderId;
    private String email;
    private String mobile;
    private String status;
}
