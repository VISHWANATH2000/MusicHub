package com.app.musicApplicaion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.musicApplicaion.entity.Feedback;
import com.app.musicApplicaion.service.FeedbackService;

@Controller
@RequestMapping("/submitFeedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public String submitFeedback(@RequestParam String name, 
                                 @RequestParam String email,
                                 @RequestParam String message) {
        Feedback feedback = new Feedback(name, email, message);
        feedbackService.saveFeedback(feedback);
        return "redirect:/contact?success";
    }
}
