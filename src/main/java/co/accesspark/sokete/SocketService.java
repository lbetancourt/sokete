package co.accesspark.sokete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

@Service
public class SocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendPeriodicUpdates() {
        String time = LocalTime.now().toString();
        String message = "Dato actualizado a las: " + time;
        System.out.println("Enviando: " + message);
        messagingTemplate.convertAndSend("/topic/updates", Optional.of(Map.of("content", message)));
    }
}
