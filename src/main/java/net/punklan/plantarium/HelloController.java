package net.punklan.plantarium;


import io.micronaut.context.annotation.Value;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")
public class HelloController {

    private final Long requestDelay;
    private final String instanceId;

    public HelloController(@Value("${request-delay:0}") Long requestDelay,
                           @Value("${instance-id:}") String instanceId) {
        this.requestDelay = requestDelay;
        this.instanceId = instanceId;
    }

    @Get("/")
    public String sayHello() {
        if (requestDelay > 0) {
            try {
                Thread.sleep(requestDelay);
            } catch (InterruptedException e) {
                // Handle the exception appropriately
            }
        }

        String response = "Hello, World!";
        if (!instanceId.isEmpty()) {
            response += " from instance " + instanceId;
        }

        return response;
    }
}