package servelet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import db.PostDBUtil;
import model.User;

/**
 * Servlet implementation class PostOperations
 */
@WebServlet("/PostOperations")
public class PostOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostOperations() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Resource(name="jdbc/project")
    private DataSource datasource;
    private PostDBUtil postdb
    ;
    
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			postdb = new PostDBUtil(datasource);
		}catch(Exception ex) {
			throw new ServletException(ex);
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("PostOperations");
		
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		
		String edit = request.getParameter("edit");
		String del = request.getParameter("del");
		String like = request.getParameter("like");

		boolean done = false;
		
		if(edit != null) {
			done = user.editPost(edit, postdb);
		}
			
		if(del != null) {
			done = user.delPost(del, postdb);
		}
		
		if(like != null) {
			done = user.likePost(like, postdb);
		}
	
		if(done) {
			response.sendRedirect("Profile");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
