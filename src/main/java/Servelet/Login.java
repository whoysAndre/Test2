/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servelet;

import dao.UsuariosJpaController;
import dto.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author acedo
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;
    UsuariosJpaController usuarioDAO = new UsuariosJpaController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtiene los datos desde el formulario
        String usuario = request.getParameter("usuario"); // Debe ser el name="usuario" en tu Html
        String clave = request.getParameter("clave");     // Debe ser el name="clave" en tu HTML

        // Crea una instancia del DTO con los datos recibidos
        Usuarios credenciales = new Usuarios(usuario, clave);

        // Llama al método validar del DAO
        Usuarios dto = usuarioDAO.validar(credenciales);

        if (dto != null) {
            // Login exitoso
            HttpSession session = request.getSession();
            session.setAttribute("usuario", dto);
            response.sendRedirect("bienvenido.html");
        } else {
            // Fallo de autenticación
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<script>alert('Usuario o contraseña incorrectos'); location='index.html';</script>");
            }
        }

    }

}
