package com.cflDevApps.dpDecorator.services.files.decorators.compressors;

import com.cflDevApps.dpDecorator.contracts.files.File;
import com.cflDevApps.dpDecorator.contracts.files.Processable;
import com.cflDevApps.dpDecorator.execeptions.ProcessFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service("rarCompressor")
public class RarCompressor implements Processable {

    private File baseFile;

    @Override
    public File setBaseFile(File baseFile) {
        this.baseFile = baseFile;
        return this;
    }

    @Override
    public String getOriginalData() {
        return this.baseFile.getOriginalData();
    }

    @Override
    public String process() {
        String data = baseFile.process();
        return compress(data);
    }

    public String compress(String data) {
        try{
            log.info("Compressing file using RAR compression");
            String dataCompressed = this.baseFile.getOriginalData().toLowerCase().substring(0,5);
            return String.format("%s | Data compressed using RAR + %s", data, dataCompressed);
        } catch (Exception e) {
            log.error("Error during RAR compression", e);
            throw new ProcessFileException("Error during RAR compression", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
