package pi.device.register.service;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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
            String deviceInfo = new String(Files.readAllBytes(deviceInfoFile.toPath()), StandardCharsets.UTF_8);

            return deviceInfo;
        } catch (IOException e){
            log.error("File Read Write Error occured.", e.getMessage(), e);
            throw new RuntimeException();
        } catch (Exception e){
            log.error("An Unexpected Error occured while attempting to get the device information", e.getMessage(), e);
            throw new RuntimeException("An Unexpected Error occured while attempting to get the device information", e);
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
