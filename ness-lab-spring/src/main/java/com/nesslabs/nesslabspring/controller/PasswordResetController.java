package com.nesslabs.nesslabspring.controller;

import com.nesslabs.nesslabspring.dto.PasswordResetRequest;
import com.nesslabs.nesslabspring.services.PasswordResetService;
import com.nesslabs.nesslabspring.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class PasswordResetController {

    //private final UserService userService;
    private final PasswordResetService passwordResetService;

//    @PostMapping("/password-reset-request")
//    public String resetRequest(@RequestBody PasswordResetRequest passwordResetRequest,
//                        final HttpServletRequest request) {
//        Optional<User> user = userService.findByEmail(passwordResetRequest.getEmail());
//        String passwordResetUrl = "";
//        if(user.isPresent()) {
//            String passwordResetToken = UUID.randomUUID().toString();
//            userService.createPasswordResetTokenForUser(user.get(),passwordResetToken);
//            passwordResetUrl = passwordResetEmailLink(user.get(),applicationUrl(request),passwordResetToken);
//        }
//        return passwordResetUrl;
//    }
//
//    @PostMapping("/reset-password")
//    public String resetPassword(@RequestBody PasswordResetRequest passwordResetRequest,
//                                @RequestParam("token") String passwordResetToken) {
//        String tokenValidationResult = userService.validatePasswordResetToken(passwordResetToken);
//        if(!tokenValidationResult.equalsIgnoreCase("valid")) {
//            return "Invalid password reset token";
//        }
//        User user = userService.findUserByPasswordToken(passwordResetToken);
//        if(user != null) {
//            userService.resetUserPassword(user,passwordResetRequest.getNewPassword());
//            return "Password has been reset successfully";
//        }
//        return "Invalid password reset token";
//    }
//
//
//
//
//    private String applicationUrl(HttpServletRequest request) {
//        return "http://"+request.getServerName()+":"
//                +request.getServerPort()+request.getContextPath();
//    }
//
//    private String passwordResetEmailLink(User user, String applicationUrl, String passwordResetToken) {
//        String url = applicationUrl+"/api/v1/reset-password?token="+passwordResetToken;
//        //eventListener.sendPasswordResetVerificationEmail(url);    -from tutorial, needs to be replaced
//        return url;
//    }

    @PostMapping("/password-reset-request")
    public String sendPasswordResetRequest(@RequestParam("email") String userEmail) {
        return passwordResetService.sendPasswordResetRequest(userEmail);
    }

    @GetMapping("")
    public String getMethod(@RequestParam("token") String token) {
        return "";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody PasswordResetRequest request) {
        return passwordResetService.resetPassword(request);
    }

}
