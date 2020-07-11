package zsp_2701170229.utils;

/**
 * 邮箱找回密码
 */
public class EmailModel {
	private String title;//标题
	private String receiverEmail;//接收人邮箱
	private String text;//发送内容

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static String register="您找回密码所需的验证码是:";



}
