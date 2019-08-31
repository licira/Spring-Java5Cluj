package hello.event.listener;

import hello.event.UserEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserListener implements ApplicationListener<UserEvent> {

    @Override
    public void onApplicationEvent(UserEvent event) {
        System.out.println("Do stuff with user - " + event.getMessage());
    }
}
