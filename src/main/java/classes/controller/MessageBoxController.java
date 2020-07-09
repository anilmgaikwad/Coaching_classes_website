package classes.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import classes.SMS.SMSData;
import classes.service.SMSService;
import classes.service.StandardService;
import classes.service.StudentwrapperService;
import classes.service.SubjectService;
import classes.service.TeacherwrapperService;
import classes.wrapper.MessageBox;
import classes.wrapper.Studentwrapper;
import classes.wrapper.Teacherwrapper;


@Controller
@RequestMapping("/messagebox")
public class MessageBoxController {
	
	@Autowired
	private TeacherwrapperService teacherService;



	@Autowired
	private StandardService standardService;
	
	@Autowired
	private SMSService smsService;

	@PostMapping("/postMessage")
	public String postMessage(@ModelAttribute("messagebox") MessageBox theMessageBox, Model theModel) {
		theMessageBox.parseReceipentsStr(standardService, teacherService);
		List<Studentwrapper> studentReceipents = theMessageBox.getListOfStudents();
		for (Studentwrapper theStudentwrapper : studentReceipents) {
			for (int index = 1; index <= 2; index++) {
				String mobileNumber="";
					if(null != theStudentwrapper){
						if(1 == index)
							mobileNumber = theStudentwrapper.getMobile_number1();
						else
							mobileNumber = theStudentwrapper.getMobile_number2();
					}
				
				Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
				Matcher m = p.matcher(mobileNumber);
				if ((m.find() && m.group().equals(mobileNumber))) {
					SMSData smsData = new SMSData();
					smsData.setMobileNumber(mobileNumber);
					smsData.setMessageType("uni");
					smsData.setMessage(theMessageBox.getMessage());
					smsService.sendSMS(smsData);
				}
			}
		}
		
		List<Teacherwrapper> teacherReceipents = theMessageBox.getListOfTeachers();
		for (Teacherwrapper theTeacherwrapper : teacherReceipents) {
			for (int index = 1; index <= 2; index++) {
				String mobileNumber="";
		
				if (null != theTeacherwrapper) {
					if(1 == index)
						mobileNumber = theTeacherwrapper.getMobile_number1();
					else
						mobileNumber = theTeacherwrapper.getMobile_number2();
				}
				
				Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
				Matcher m = p.matcher(mobileNumber);
				if ((m.find() && m.group().equals(mobileNumber))) {
					SMSData smsData = new SMSData();
					smsData.setMobileNumber(mobileNumber);
					smsData.setMessageType("uni");
					smsData.setMessage(theMessageBox.getMessage());
					smsService.sendSMS(smsData);
				}
			}
		}
		return "admins";
	}
	
	@RequestMapping("/createMessage")
	public String createMessage(Model theModel) {

		MessageBox messageBox = new MessageBox();

		theModel.addAttribute("message", messageBox);

		return "message-box";
	}
}
