
package com.fluytec.fluytec.controller;



import com.fluytec.fluytec.dao.UsuarioDao;
import com.fluytec.fluytec.domain.Usuario;
import com.fluytec.fluytec.servicioMail.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EnviarEmailController {

    @Autowired
    SendMailService sendMailService;

    @Autowired
    UsuarioDao usuarioDao;
    
    @PostMapping("/enviaremail")
    public String enviaremail(@RequestParam("correo") String correo /*,
			@RequestParam("subject")String subject,@RequestParam("body") String body*/) {
        Usuario usuario1 = usuarioDao.findByCorreo(correo.toLowerCase());
        String mensaje = sendMailService.plantillaCambioContra().
                replace("URL_CAMBIO_CONTRASENA", "https://fluytec.herokuapp.com/cambioContraseña?id_usuarios=" + usuario1.getId_usuarios()).
                replace("nombre_usuario", usuario1.getNombre() + " " + usuario1.getApellido()); //body + "\n\n  correo de contacto;" +subject+"\n asunto"  + "\n email" + correo;
        String subject = "Cambio de contraseña usuario " + usuario1.getNombre() + " " + usuario1.getApellido();
        sendMailService.enviaremail("danielvargasvelasco@gmail.com", correo, subject, mensaje);
        return "index-fluytec";
    }
    
}
