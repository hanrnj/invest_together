package bit.it.into.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.it.into.dto.CalculatorDTO;
import bit.it.into.security.CustomUser;
import bit.it.into.service.CalculatorService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class CalculateController {
	
	@Inject
	CalculatorService calculatorService;
	
	
	 @RequestMapping("/calculator")
	 public String calculator() {
		 
		 return "calculator/calculator";
		 //return "include/testmain";
	 }
	
	 @RequestMapping("/target")
	 public String targetPrice(Model model) {
		 
		 model.addAttribute("stockinfo", calculatorService.getStockinfo());
		 
		 return "calculator/targetPrice";
	 }
	 
	 @ResponseBody
	 @RequestMapping("/stockInfo/{code}")
	 public String stockInfo(@PathVariable int code) {
		
		 String strCode = String.format("%06d", code); 
		 String stockInfoXml = calculatorService.getStockInfoXml(strCode);
		 JSONObject stockInfoJson = XML.toJSONObject(stockInfoXml);
		
		 return stockInfoJson.toString();
	 }

	 
	 @RequestMapping("/myCalculator")
	 public String myTargetPrice(Model model, Authentication authentication, CalculatorDTO calculatorDTO) {
		 
		 if(authentication == null) {
			return "login/login_require";
	 	 }
		 
		 CustomUser user = (CustomUser)authentication.getPrincipal();
		 int user_num = user.getDto().getMember_num();
		 
		 model.addAttribute("symbolsList", calculatorService.getSymbolsList(user_num));
		 
		 return "calculator/myTargetPrice";
	 } 
	 
	 @ResponseBody
	 @GetMapping("/list")
	 public CalculatorDTO getList(@RequestParam("stockinfo_symbols") String stockinfo_symbols, Authentication authentication, CalculatorDTO calculatorDTO){
 
		 CustomUser user = (CustomUser)authentication.getPrincipal();
		 int user_num = user.getDto().getMember_num();
		 calculatorDTO.setMember_num(user_num);
		 log.info("/////////////////////list/////////////////////////////");
		 log.info(stockinfo_symbols);
		 
		 calculatorDTO.setStockinfo_symbols(stockinfo_symbols);
		 
		 return calculatorService.getList(calculatorDTO);

	 }
	 
	 @ResponseBody
	 @GetMapping("/delete")
	 public int delete(@RequestParam("stockinfo_symbols") String stockinfo_symbols, Authentication authentication, CalculatorDTO calculatorDTO){
 
		 log.info("/////////////////////delete/////////////////////////////");
		
		 CustomUser user = (CustomUser)authentication.getPrincipal();
		 int user_num = user.getDto().getMember_num();
		 calculatorDTO.setMember_num(user_num);
		 calculatorDTO.setStockinfo_symbols(stockinfo_symbols);
		 
		 int result = 1;
		 calculatorService.delete(calculatorDTO);
		 return result;
	 }
	 
	
 	@PostMapping("/writeCalculator")
	public String write(@Valid CalculatorDTO calculatorDTO, Authentication authentication, BindingResult result, Model model) throws Exception {
 		log.info("////////////////////writeCalculator///////////////////////");

		if( result.hasErrors() ) { 
			 List<ObjectError> list = result.getAllErrors(); 
			 
			 for( ObjectError error : list ) {
				 System.out.println(error); 
			 } 
			
			 model.addAttribute("msg", 2);
	         
			return "calculator/redirect"; 
		}
		
		 if(authentication == null) {
			return "login/login_require";
	 	 }
		 
		 CustomUser user = (CustomUser)authentication.getPrincipal();
		 int user_num = user.getDto().getMember_num();
		 calculatorDTO.setMember_num(user_num);

		 
		 int count = calculatorService.checkSymbols(calculatorDTO);
		 
		 if(count == 0) {
			 calculatorService.writeCalculator(calculatorDTO);
		 } else if (count > 0) {
			 calculatorService.updateCalculator(calculatorDTO);
		 }
		 
		 model.addAttribute("msg", 1);
		 
		 return "calculator/redirect";
	}
	
}
