package hello.event.publisher;

import hello.entity.User;
import hello.event.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(final User user) {
        UserEvent customSpringEvent = new UserEvent(user);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
