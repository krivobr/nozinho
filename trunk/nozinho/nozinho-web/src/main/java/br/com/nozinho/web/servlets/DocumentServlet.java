package br.com.nozinho.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/document.pdf")
public class DocumentServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2747653827661824502L;
	
	 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        byte[] content = (byte[]) request.getSession().getAttribute("reportBytes");
        response.setContentType("application/pdf");
        response.setContentLength(content.length);
        response.getOutputStream().write(content);
    }

}
