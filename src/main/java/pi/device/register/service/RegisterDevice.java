package pi.device.register.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import pi.device.register.api.BuildRequest;
import pi.device.register.api.PostDeviceInfo;

@Service
public class RegisterDevice {
    private static final Logger log = LoggerFactory.getLogger(RegisterDevice.class);
    private final GetDeviceInfo getDeviceInfo;
    private final BuildRequest buildRequest;
    private final PostDeviceInfo postDeviceInfo;

    public RegisterDevice(GetDeviceInfo getDeviceInfo, BuildRequest buildRequest, PostDeviceInfo postDeviceInfo){
        this.getDeviceInfo = getDeviceInfo;
        this.buildRequest = buildRequest;
        this.postDeviceInfo = postDeviceInfo;
    }

    @Value("${pi.device.info.path}")
    private String deviceInfoFilePath;

    @Value("${aws.api-gateway.key}")
    private String apiKey;

    @Value("${aws.api-gateway.endpoint}")
    private String apiEndpoint;


    public void register(){
        log.info("Begining to Register the Device...");

        //Get the Device Information
        String deviceInfoFile = getDeviceInfo.getPiInfo(deviceInfoFilePath);

        //Build the API Request
        RequestEntity<String> request = buildRequest.makePostRequest(deviceInfoFile, apiKey, apiEndpoint);

        //Send the Request to the API
        postDeviceInfo.postDeviceInfo(request);
    }
}
