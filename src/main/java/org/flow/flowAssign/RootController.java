package org.flow.flowAssign;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping("/")
    public String redirectToExtensionSetting() {
        return "redirect:/extensionSetting";
    }
}
