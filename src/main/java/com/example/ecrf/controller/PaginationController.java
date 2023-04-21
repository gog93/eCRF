package com.example.ecrf.controller;

import com.example.ecrf.model.eCRF;
import com.example.ecrf.service.eCRFService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eCRF")
public class PaginationController {
    private final eCRFService service;

    @GetMapping("/draft")
    public Page<eCRF> allDraft(@RequestParam(name = "search", required = false) String search,
                               Pageable pageable) {
        Page<eCRF> byStatus = service.findByStatus(search, pageable);

        return byStatus;
    }

    @GetMapping("/sent")
    public Page<eCRF> allSent(@RequestParam(name = "search", required = false) String search, Pageable pageable) {
        Page<eCRF> byStatus = service.findBySend(search, pageable);

        return byStatus;
    }

    @GetMapping("/delete")
    public Page<eCRF> allDelete(@RequestParam(name = "search", required = false) String search, Pageable pageable) {

        Page<eCRF> byStatus = service.findByDelete(search, pageable);

        return byStatus;
    }


    @GetMapping("/changeStatus/delete/{id}")
    public eCRF changeStatusDelete(@PathVariable("id") Long id) {

        return service.changeStatusDelete(id);
    }

    @GetMapping("/changeStatus/sent/{id}")
    public eCRF changeStatusSend(@PathVariable("id") Long id) {

        return service.changeStatusSend(id);
    }

    @GetMapping("/changeStatus/restore/{id}")
    public eCRF changeStatusDraft(@PathVariable("id") Long id) {

        return service.changeStatusDraft(id);
    }

}
