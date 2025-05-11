package pi.device.register.api;

import java.net.URI;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

@Service
public class BuildRequest {
    private static final Logger log = LoggerFactory.getLogger(BuildRequest.class);

    public RequestEntity<String> makePostRequest(Map<String, String> deviceInfo){
        log.info("Begining to Build the API Request...");
        try{
            //Build the request Entity
            RequestEntity<String> requestEntity = RequestEntity
                .post(URI.create("https://4v4vgqzr03.execute-api.us-east-2.amazonaws.com/prod/register"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer OjsY5xQX5AYP4xYURwX2Uylw/xg1SNYkAL7kTljjDfv3bjtf6WS/6oVaVON0QpsO")
                .body("{\n" + //
                                        "  \"serial_number\": \"FVFDR8V7Q05F\"\n" + //
                                        "}");
           
            return requestEntity;
            
        } catch (RuntimeException e){
            log.error("Fatal error occured while attempting to build the request..", e.getMessage(), e);
            throw new RuntimeException("Fatal error occured while attempting to build the request..", e);
        }
        
    }


}
