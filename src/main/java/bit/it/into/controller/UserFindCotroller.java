package bit.it.into.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import bit.it.into.dto.MemberDTO;
import bit.it.into.dto.PwdVaildDTO;
import bit.it.into.service.LoginService;
import bit.it.into.service.MailSendService;
import bit.it.into.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@AllArgsConstructor
public class UserFindCotroller {
	
	private LoginService loginService;
	private UserService userService;
	
	@Autowired
	private MailSendService mailSendService;
	
	//idã��
	@RequestMapping("/idFind")
	public String idFind() {
		log.info("LoginController - idFind()");
		
		return "find/idFind";
	}
	
	//pwã��
	@RequestMapping("/pwFind")
	public String pwFind() {
		log.info("LoginController - pwFind()");
		
		return "find/pwFind";
	}
	
	@RequestMapping("/idEmailSend")
	public String idEmailSend(MemberDTO memberDTO, Model model, HttpServletResponse response) throws Exception {
		log.info("LoginController - idEmailSend()");
		
		//name�� ��Ī�Ǵ� �̸��� ȣ��
		String email = userService.nameCheck(memberDTO.getName());
		
		if(email.equals(memberDTO.getEmail())) {
			String authKey = mailSendService.idsendFindMail(memberDTO.getEmail());
			model.addAttribute("authKey", authKey);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�̸����� ���۵Ǿ����ϴ� �̸����� Ȯ�����ּ���.'); </script>");
			out.flush();
			
		} else {
			
			return "redirect:idFind";
		}
		
		return "forward:/idFind";
	}
	
	@RequestMapping("/verifyId")
	public String verifyId(MemberDTO memberDTO, Model model) {
		log.info("UserFindCotroller - verifyId()");
		
		List<MemberDTO> id =  userService.idInfo(memberDTO);
		
		model.addAttribute("id", id);
		
		return "find/idInfo";
	}
	
	
	@RequestMapping(value="/pwdEmailSend", method = RequestMethod.POST)
	public String pwdEmailSend(MemberDTO memberDTO, Model model) {
		log.info("UserFindCotroller - pwdEmailSend()");	
		
		//id�� ��Ī�Ǵ� �̸��� ȣ��
		String email = userService.idCheck(memberDTO.getId());
		
		if(loginService.hasUserById(memberDTO.getId())) {
			
			//��й�ȣ ã�⸦ ���� ������ȣ email���� 
			String authKey = mailSendService.pwsendFindMail(email);
			boolean mailsendcomplete = true;
			
			model.addAttribute("mailsendcomplete", mailsendcomplete);
			model.addAttribute("authKey", authKey);
					
		} else {
			
			return "redirect:pwFind";
		}
		
		return "forward:/pwFind";
	}
	
	@RequestMapping("/verifyPwd")
	public String verifyPwd(MemberDTO memberDTO) {
		log.info("UserFindCotroller - verifyPwd()");
		
		return "find/resetPwd";
	}
	
	//������ user ��й�ȣ ����
	@RequestMapping(value="/resetPwd", method = RequestMethod.POST)
	public String resetPwd(@Valid PwdVaildDTO pwdVaildDTO, Errors errors, Model model) {
		log.info("UserFindCotroller - resetPwd()");
		
		if (errors.hasErrors()) {
            
			Map<String, String> validatorResult = loginService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "forward:/verifyEmail";
        }
		
		Map<String, String> userInfo = new HashMap<>();
		
		userInfo.put("id", pwdVaildDTO.getId());
		userInfo.put("pw", pwdVaildDTO.getPw());
		
		userService.resetPwd(userInfo);
		
		return "redirect:/loginForm";
	}
	
}