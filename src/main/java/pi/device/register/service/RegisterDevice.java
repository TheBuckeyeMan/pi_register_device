package pi.device.register.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RegisterDevice {
    private static final Logger log = LoggerFactory.getLogger(RegisterDevice.class);
    private final GetDeviceInfo getDeviceInfo;

    public RegisterDevice(GetDeviceInfo getDeviceInfo){
        this.getDeviceInfo = getDeviceInfo;
    }

    @Value("${pi.device.info.path}")
    private String deviceInfoFilePath;

    public void register(){
        log.info("Begining to Register the Device...");
        getDeviceInfo.getPiInfo(deviceInfoFilePath);
    }

    
}
