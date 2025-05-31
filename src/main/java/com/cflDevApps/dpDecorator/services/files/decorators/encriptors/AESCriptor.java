package com.cflDevApps.dpDecorator.services.files.decorators.encriptors;

import com.cflDevApps.dpDecorator.contracts.files.File;
import com.cflDevApps.dpDecorator.contracts.files.Processable;
import org.springframework.stereotype.Service;

@Service("aesCriptor")
public class AESCriptor implements Processable {

    private File baseFile;

    private String data;

    @Override
    public File setBaseFile(File baseFile) {
        this.baseFile = baseFile;
        return this;
    }

    public String encrypt(String dataProcessed) {
        this.data = this.baseFile.getOriginalData().replaceAll("(?<=.{5}).", "*");
        return String.format("%s | Encrypted with AES: %s", dataProcessed, this.data);
    }

    @Override
    public String getOriginalData() {
        return this.data;
    }

    @Override
    public String process() {
       String dataProcessed = baseFile.process();
       return encrypt(dataProcessed);
    }
}
