package com.notification_service.kafka;

import com.notification_service.dto.OrderEvent;
import com.notification_service.service.EmailService;
import com.notification_service.service.SmsService;
import com.notification_service.service.WhatsAppService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private EmailService emailService;
    private SmsService smsService;
    private WhatsAppService whatsAppService;

    public NotificationConsumer(EmailService emailService, SmsService smsService, WhatsAppService whatsAppService) {
        this.emailService = emailService;
        this.smsService = smsService;
        this.whatsAppService = whatsAppService;
    }

    @KafkaListener(
            topics = "orders",
            groupId = "notification-group"
    )
    public void consume(OrderEvent event) {

        System.out.println("Received Order Event: " + event.getEmail());
        if (event.getStatus().equals("success")) {
            emailService.sendEmail(
                    event.getEmail(),
                    "Transaction Completed",
                    "Your order has been Placed successfully :"
                            +event.getOrderId());
            smsService.sendSms(
                    event.getMobile(),
                    "Transaction Completed");

            whatsAppService.sendWhatsApp(event.getMobile(),
                    "Transaction Completed");
        }else {
            emailService.sendEmail(
                    event.getEmail(),
                    "Transaction Incomplete",
                    "Your order has not been Placed :"
                            +event.getOrderId());

            smsService.sendSms(event.getMobile(),
                    "Transaction Incomplete");

            whatsAppService.sendWhatsApp(event.getMobile(),
                    "Transaction Incomplete");
        }

        String subject = "Order Status updated";

        String body =
                "Order Id : " + event.getOrderId() +
                        "\nStatus : " + event.getStatus();
    }
}
