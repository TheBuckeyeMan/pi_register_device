package pi.device.register.service;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetDeviceInfo {
    private static final Logger log = LoggerFactory.getLogger(GetDeviceInfo.class);

    public String getPiInfo(String deviceInfoFilePath){
        log.info("Attemptiong to get the required device information...");
        try{
            File deviceInfoFile = new File(deviceInfoFilePath);
            
            //Check if the file Exists
            validateDeviceInfoFile(deviceInfoFile);

            // Get the Contents of the file
            String deviceInfo = deviceInfoFile.toString();

            return deviceInfo;
        } catch (RuntimeException e){
            log.error("Error occured while attempting to get the device information", e.getMessage(), e);
            throw new RuntimeException();
        }
    }

    private void validateDeviceInfoFile(File deviceInfoFile){
        log.info("Begining to check if the device info path: " + deviceInfoFile.getAbsolutePath() + " Exists at the specified location...");
        try{
            if (!deviceInfoFile.exists()){
                log.error("The file located at: " + deviceInfoFile.getAbsolutePath() + " does NOT EXIST. DEVICE NOT REGISTERING WITH CLOUD SERVICE");
                throw new RuntimeException("The file located at: " + deviceInfoFile.getAbsolutePath() + " does NOT EXIST. DEVICE NOT REGISTERING WITH CLOUD SERVICE");
            }
        } catch (RuntimeException e){
            log.error("Fatal error occured while attempting to check if the device info file exists", e.getMessage(), e);
        }
    }
}
