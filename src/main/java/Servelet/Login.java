
package Servelet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.UsuariosJpaController;
import dto.Usuarios;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.JwtUtil;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    private static final Gson gson = new Gson();
    UsuariosJpaController usuDAO = new UsuariosJpaController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Leer el cuerpo JSON
        StringBuilder jsonBuffer = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        // Convertir JSON a objeto Java
        JsonObject jsonInput = gson.fromJson(jsonBuffer.toString(), JsonObject.class);
        String username = jsonInput.get("username").getAsString();
        String password = jsonInput.get("password").getAsString();

        // Configurar respuesta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        JsonObject jsonResponse = new JsonObject();
        
        Usuarios usuario = usuDAO.validar(new Usuarios(username,password));

        // Validación básica (puedes conectarlo a una base de datos aquí)
        if (usuario!=null) {
            String token =  JwtUtil.generarToken(username);
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("token", token);
        } else {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Usuario o contraseña incorrectos");
        }

        out.print(gson.toJson(jsonResponse));
        out.flush();
    }

}
