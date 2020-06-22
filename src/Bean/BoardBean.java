package Bean;

public class BoardBean {
	private String name;
	private String mail;
	private String comment;
	private String post;
	private String edit;

	public BoardBean(String name, String mail, String comment, String post, String edit) {
		super();
		this.name = name;
		this.mail = mail;
		this.comment = comment;
		this.post = post;
		this.edit = edit;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}

}
