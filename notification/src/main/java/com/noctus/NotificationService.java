package com.noctus;

import com.noctus.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void sendNotification(@RequestBody NotificationRequest request) {
        Notification notification = Notification.builder()
                .message(request.getMessage())
                .toCustomerId(request.getToCustomerId())
                .toCustomerEmail(request.getToCustomerEmail())
                .sender("noctus")
                .sentAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }
}
