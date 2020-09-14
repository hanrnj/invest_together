package bit.it.into.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bit.it.into.mail.MailUtils;

@Service()
public class MailSendService {
	
	private int size;
	
	 @Autowired
	 private JavaMailSenderImpl mailSender;

	    //����Ű ����
	    private String getKey(int size) {
	        this.size = size;
	        return getAuthCode();
	    }

	    //�����ڵ� ���� �߻�
	    private String getAuthCode() {
	        Random random = new Random();
	        StringBuffer buffer = new StringBuffer();
	        int num = 0;

	        while(buffer.length() < size) {
	            num = random.nextInt(10);
	            buffer.append(num);
	        }

	        return buffer.toString();
	    }

	    //�������� ������
	    @Transactional
	    public String sendAuthMail(String email) {
	        //6�ڸ� ���� ������ȣ ����
	        String authKey = getKey(6);

	        //�������� ������
	        try {
	            MailUtils sendMail = new MailUtils(mailSender);
	            sendMail.setSubject("ȸ������ �̸��� ����");
	            sendMail.setText(new StringBuffer().append("<h1>[�̸��� ����]</h1>")
	            .append("<p>�Ʒ� ��ũ�� Ŭ���Ͻø� �̸��� ������ �Ϸ�˴ϴ�.</p>")
	            .append("<a href='http://localhost:8282/into/authConfirm?email=")
	            .append(email)
	            .append("&authKey=")
	            .append(authKey)
	            .append("' target='_blenk'>�̸��� ���� Ȯ��</a>")
	            .toString());
	            sendMail.setFrom("hanrnj22@gmail.com", "������");
	            sendMail.setTo(email);
	            sendMail.send();
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	          return authKey;
	    }
}
