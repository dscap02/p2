package it.unisa.control;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SecurityFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        // Inizializzazione del filtro
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String requestedPage = request.getParameter("page"); // Supponendo che il parametro si chiami "page"

        // Verifica se la richiesta sta tentando di accedere a file sensibili
        if (requestedPage != null && (requestedPage.contains("web.xml") || requestedPage.contains("context.xml"))) {
            // Se tenta di accedere a file sensibili, reindirizza o restituisci un errore 403 (accesso negato)
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            // Se la richiesta è sicura, passa al servlet successivo nella catena
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        // Pulizia del filtro
    }
}
