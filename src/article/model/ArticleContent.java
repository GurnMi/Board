package article.model;

public class ArticleContent {
	int number;
	String content;

	public ArticleContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArticleContent(int number, String content) {
		super();
		this.number = number;
		this.content = content;
	}

	@Override
	public String toString() {
		return "ArticleContent [number=" + number + ", content=" + content + "]";
	}

}
