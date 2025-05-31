package com.cflDevApps.dpDecorator.services.files;

import com.cflDevApps.dpDecorator.contracts.files.File;
import com.cflDevApps.dpDecorator.execeptions.ProcessFileException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Data
@NoArgsConstructor
@Slf4j
@Service("baseFile")
public class BaseFile implements File{

    private String actionType;

    private String data;

    public BaseFile(String actionType, String data) {
        this.actionType = actionType;
        this.data = data;
    }

    @Override
    public String getOriginalData() {
        return this.data;
    }
    @Override
    public String process() {
        try{
            log.info("Processing base file");
            // Simulate file processing logic
            // Simulating a delay for processing
            log.info("Base file processed successfully");
            log.info("{} : {}", this.actionType, this.data);
            return String.format("Sending message '%s' to: %s",this.data, this.actionType); // Return the processed data

        } catch (Exception e) {
            throw new ProcessFileException("Error processing base file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
