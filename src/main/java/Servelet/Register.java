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
import org.json.JSONObject;
import utils.BcryptHash;

/**
 *
 * @author acedo
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UsuariosJpaController usuarioDAO = new UsuariosJpaController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String usuario = request.getParameter("logiUsua");
        String clave = request.getParameter("logiPass");

        JSONObject json = new JSONObject();

        try {
            if (usuario == null || clave == null || usuario.isEmpty() || clave.isEmpty()) {
                json.put("status", "error");
                json.put("message", "Usuario y clave no pueden estar vacíos.");
            } else {
                // ⚠️ NUEVA validación solo del usuario
                if (usuarioDAO.existeUsuario(usuario)) {
                    json.put("status", "error");
                    json.put("message", "El usuario ya está registrado.");
                } else {
                    String claveHash = BcryptHash.hashPassword(clave);
                    Usuarios nuevoUsuario = new Usuarios(usuario, claveHash);
                    usuarioDAO.create(nuevoUsuario);

                    json.put("status", "success");
                    json.put("message", "Usuario registrado exitosamente.");
                    json.put("usuario", usuario);
                }
            }
        } catch (Exception e) {
            json.put("status", "error");
            json.put("message", "Error interno: " + e.getMessage());
        }

        out.print(json.toString());
        out.flush();
    }
}
