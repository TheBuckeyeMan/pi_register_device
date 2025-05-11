package pi.device.register.api;

import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

@Service
public class BuildRequest {
    private static final Logger log = LoggerFactory.getLogger(BuildRequest.class);

    public RequestEntity<String> makePostRequest(String deviceInfo, String apiKey, String apiEndpoint){
        log.info("Begining to Build the API Request...");
        try{
            //Build the request Entity
            RequestEntity<String> requestEntity = RequestEntity
                .post(URI.create(apiEndpoint))
                .header("Content-Type", "application/json")
                .header("x-api-key", apiKey)
                .body(deviceInfo.toString());
           
            return requestEntity;
            
        } catch (RuntimeException e){
            log.error("Fatal error occured while attempting to build the request..", e.getMessage(), e);
            throw new RuntimeException("Fatal error occured while attempting to build the request..", e);
        } 
    }
}
