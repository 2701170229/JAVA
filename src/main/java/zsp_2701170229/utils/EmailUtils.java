package zsp_2701170229.utils;


import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils {

	private final static String authorizationCode="khdotvxnelwgbdba"; 	//授权码
	private final static String senderEmail ="675927961@qq.com";		//发送人邮箱

	public static void main(String[] args) {
		EmailModel emailModel=new EmailModel();
		emailModel.setReceiverEmail("675927961@qq.com");
		emailModel.setText(EmailModel.register+"21212");
		sendEmail(emailModel);
	}
	//发送邮件
	public static void sendEmail(EmailModel emailModel){

		 try{
			 Properties props = new Properties();
			 emailModel.setText("找回密码");
			 // 开启debug调试
			 props.setProperty("mail.debug", "true");
			 // 发送服务器需要身份验证
			 props.setProperty("mail.smtp.auth", "true");
			 // 设置邮件服务器主机名
			 props.setProperty("mail.host", "smtp.qq.com");
			 // 发送邮件协议名称
			 props.setProperty("mail.transport.protocol", "smtp");

			 MailSSLSocketFactory sf = new MailSSLSocketFactory();
			 sf.setTrustAllHosts(true);
			 props.put("mail.smtp.ssl.enable", "true");
			 props.put("mail.smtp.ssl.socketFactory", sf);

			 Session session = Session.getInstance(props);

			 Message msg = new MimeMessage(session);
			 msg.setSubject(emailModel.getTitle());//标题
			 StringBuilder builder = new StringBuilder();
//			 builder.append("url = " + "http://blog.csdn.net/never_cxb/article/details/50524571");
			 builder.append(emailModel.getText());
			 msg.setText(builder.toString());
			 msg.setFrom(new InternetAddress(senderEmail));//发送人的邮箱地址

			 Transport transport = session.getTransport();
			 //发送人的邮箱地址 //你的邮箱密码或者授权码
			 transport.connect("smtp.qq.com", senderEmail, authorizationCode);

			 transport.sendMessage(msg, new Address[] { new InternetAddress(emailModel.getReceiverEmail()) });// 接收人的邮箱地址
			 transport.close();
		 }catch (Exception e){
		 	e.printStackTrace();
		 }
	 }
}
