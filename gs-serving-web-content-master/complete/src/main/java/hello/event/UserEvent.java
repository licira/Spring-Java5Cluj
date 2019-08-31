package hello.event;

import hello.entity.User;
import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent {

    private String message;

    public UserEvent(User source) {
        super(source);
        this.message = source.getUsername();
    }
    public String getMessage() {
        return message;
    }
}
