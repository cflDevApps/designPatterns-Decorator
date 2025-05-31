package com.cflDevApps.dpDecorator.controlers;

import com.cflDevApps.dpDecorator.contracts.files.Processable;
import com.cflDevApps.dpDecorator.dtos.DataRequest;
import com.cflDevApps.dpDecorator.services.files.ProcessFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/files")
public class FilesController {

    private final ProcessFileService processRequestService;

    @Autowired
    public FilesController(ProcessFileService processRequestService) {
        this.processRequestService = processRequestService;
    }


    @GetMapping
    public String files(Model model) {
        model.addAttribute("title", "Files Compress");
        return "file-compress-form";
    }


    @PostMapping("/process")
    public String filesPost(Model model, DataRequest dataRequest) {
        model.addAttribute("title", "Files Process Results");

        String result = processRequestService.processFile(dataRequest);

        log.info("File processed result: {}", result);

        model.addAttribute("result", result);

        return "file-process-result";
    }

}
