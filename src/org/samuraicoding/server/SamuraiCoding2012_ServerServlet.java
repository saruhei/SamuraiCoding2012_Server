package org.samuraicoding.server;

import java.io.IOException;
import javax.servlet.http.*;

import org.samuraicoding.server.javaee.Match;

@SuppressWarnings("serial")
public class SamuraiCoding2012_ServerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("OK");
		try{
			int id = Integer.parseInt(req.getParameter("id"));
			String urls[] = req.getParameter("urls").split(",");
			Match match = new Match(id, urls);

		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		
	}
}
