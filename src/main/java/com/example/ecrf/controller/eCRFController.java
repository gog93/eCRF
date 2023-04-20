package com.example.ecrf.controller;

import com.example.ecrf.dto.RequesteCRF;
import com.example.ecrf.model.eCRF;
import com.example.ecrf.model.eCRFStatus;
import com.example.ecrf.service.eCRFService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/eCRF")
@RequiredArgsConstructor
public class eCRFController {
    private final eCRFService service;

    @GetMapping
    public String eCRFGet() {

//        model.addAttribute("eCRFEs", all);
        return "index";
    }

    @GetMapping("eCRF1/Filling_status/{id}")
    public String eCRFGet(@PathVariable("id") String filling_status) {

//        model.addAttribute("eCRFEs", all);
        return "edit";
    }

    @PostMapping("/changeStatus/send")
    public String changeStatusSend(@RequestParam( value = "eCRFId", required = false) Long id, RequesteCRF eCRF, Model model) {
        model.addAttribute("eCRF", eCRF);
        service.changeStatusSend(id);
        return "redirect:/eCRF";
    }

    @PostMapping("/changeStatus/delete")
    public String changeStatusDelete(@RequestParam( value = "eCRFId", required = false) Long id, Model model) {
        service.changeStatusDelete(id);
        return "redirect:/eCRF";
    }

    @PostMapping("/medical.org/addnewecrf")
    public String addNewCaseFamily(RequesteCRF eCRF, Model model) {
        model.addAttribute("eCRF", eCRF);
        try {
            service.create(eCRF);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/eCRF";
    }
}
