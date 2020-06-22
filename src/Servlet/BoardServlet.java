package Servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/Board")
@MultipartConfig(maxFileSize=1048576)  // 最大1M
public class BoardServlet extends HttpServlet {
	final File uploadDir = new File("C:\\pleiades\\workspace\\BulletinBoard\\WebContent\\upfile");  // ファイル保存先
	public void init() throws ServletException {
		uploadDir.mkdir();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String comment = req.getParameter("comment");

		System.out.println("---------------------------------------------------");
		System.out.println(name);
		System.out.println(mail);
		System.out.println(comment);

		LocalDateTime date1 = LocalDateTime.now();
		DateTimeFormatter dtformat1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String fdate1 = dtformat1.format(date1);
		System.out.println(fdate1);

		if(name.equals("のりあき") || mail.equals("null")){

			System.out.println("error");
			//DBからの戻り値がnull＝ログイン失敗と判断し
			//ログイン画面へリダイレクトする。
			String view = "http://localhost:8080/BulletinBoard?error=1";
			res.sendRedirect(view);

		}else {

			// ファイルの保存 ->
			String id = req.getParameter("id");
			Part fPart = req.getPart("file");
			String fName = (new StringBuilder(id)
					.append("_").append(System.currentTimeMillis())
					.append("_").append(fPart.getSubmittedFileName()
							).toString());
			save(fPart, new File(uploadDir, fName));


			//ログイン成功と判断し、セッションスコープにユーザ情報を格納
			HttpSession session = req.getSession();
			session.setAttribute("usr", name);

			Dao.BoardDao.selectPost(name, mail, comment, fdate1);

			String view = "http://localhost:8080/BulletinBoard";
			res.sendRedirect(view);
		}

	}
	public void save(Part in, File out) throws IOException {
		BufferedInputStream br
		= new BufferedInputStream(in.getInputStream());
		try (BufferedOutputStream bw =
				new BufferedOutputStream(new FileOutputStream(out))
				) {
			int len = 0;
			byte[] buff = new byte[1024];
			while ((len = br.read(buff)) != -1) {
				bw.write(buff, 0, len);
			}
		}
	}
}