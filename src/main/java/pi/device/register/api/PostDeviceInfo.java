package pi.device.register.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostDeviceInfo {
    private static final Logger log = LoggerFactory.getLogger(PostDeviceInfo.class);

    public void postDeviceInfo(RequestEntity<String> requestEntity){
        log.info("Attempting to post the device information to aws...");
        try{
            //Get the rest templaye
            RestTemplate restTemplate = new RestTemplate();

            //Make the Request
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

            //Verify we were successful
            checkResponseCode(responseEntity);

        } catch (RuntimeException e){
            log.error("Fatal error occured while attempting to post the device information to aws", e.getMessage(), e);
            throw new RuntimeException("Fatal error occured while attempting to post the device information to aws", e);
        }
    }

    private void checkResponseCode(ResponseEntity<String> responseEntity){
        log.info("Checking the response code...");
        try{
            if (responseEntity.getStatusCode().is2xxSuccessful()){
                log.info("API Request was successful and device information has been posted to aws!");
                log.info("Response code of: " + responseEntity.getStatusCode().toString());
                log.info("Response Body of: " + responseEntity.getBody().toString());

            } else {
                log.error("The API Returned a NON 200 response code, as a result, the device information was NOT REGISTERED SUCCESSFULLY");
                throw new RuntimeException("The API Returned a NON 200 response code, as a result, the device information was NOT REGISTERED SUCCESSFULLY");
            }
        } catch (RuntimeException e){
            log.error("Error occured while attempting to check the response code", e.getMessage(), e);
            throw new RuntimeException("Error occured while attempting to check the response code", e);
        }
    }
}
