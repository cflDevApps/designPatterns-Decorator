package com.cflDevApps.dpDecorator.services.files.decorators.compressors;

import com.cflDevApps.dpDecorator.contracts.files.File;
import com.cflDevApps.dpDecorator.contracts.files.Processable;
import com.cflDevApps.dpDecorator.execeptions.ProcessFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service("zipCompressor")
public class ZipCopressor implements Processable {

    private File baseFile;

    @Override
    public File setBaseFile(File baseFile) {
        this.baseFile = baseFile;
        return this;
    }

    public String compress(String data) {
        try{
            log.info("Compressing file using ZipCompressor");
            String dataCompressed = this.baseFile.getOriginalData().toLowerCase().substring(0,4);
            log.info("File compressed to ZIP successfully.");
            return String.format("%s | Data compressed using ZIP + %s", data, dataCompressed);

        } catch (Exception e) {
            log.error("Compression interrupted: {}", e.getMessage());
            throw new ProcessFileException("Error during ZIP compression", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @Override
    public String getOriginalData() {
        return this.baseFile.getOriginalData();
    }

    @Override
    public String process() {
        String processed = baseFile.process();
        return compress(processed);

    }
}
