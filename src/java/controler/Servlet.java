package controler;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Tradutor;

/**
 *
 * @author Pedro
 */
@WebServlet(urlPatterns = {"/traduzido"})
public class Servlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext context = getServletContext();
        
        String fullPath = context.getRealPath("/WEB-INF/dicionario.txt");
                
        Tradutor t = new Tradutor(fullPath);        
                     
        request.setAttribute("traduzido", t.traduzir(request.getParameter("texto"),request.getParameter("idioma")) );
        
        request.setAttribute("texto", request.getParameter("texto"));
        
        request.getRequestDispatcher("resposta.jsp").forward(request, response);
    }

}
