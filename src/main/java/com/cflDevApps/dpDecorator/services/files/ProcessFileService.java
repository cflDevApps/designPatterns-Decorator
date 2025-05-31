package com.cflDevApps.dpDecorator.services.files;

import com.cflDevApps.dpDecorator.contracts.files.File;
import com.cflDevApps.dpDecorator.contracts.files.Processable;
import com.cflDevApps.dpDecorator.dtos.DataRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ProcessFileService {

    private final Map<String, Processable> processableMap;

    @Autowired
    public ProcessFileService(Map<String, Processable> processableMap) {
        this.processableMap = processableMap;
    }

    public String processFile(DataRequest dataRequest){
        File baseFile = new BaseFile(dataRequest.getActionType(), dataRequest.getData());

        File finalFileProcessor = baseFile;

        if(dataRequest.encryptorTypeIsPresent()){
            Processable encryptor = processableMap.get(dataRequest.getEncryptorType());
            encryptor.setBaseFile(baseFile);
            finalFileProcessor = encryptor;
        }

        if(dataRequest.compressorTypeIsPresent()){
            Processable compressor = processableMap.get(dataRequest.getCompressorType());
            compressor.setBaseFile(finalFileProcessor);
            finalFileProcessor = compressor;
        }

        return finalFileProcessor.process();
    }


}
