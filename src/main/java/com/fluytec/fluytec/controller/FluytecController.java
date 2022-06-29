package com.fluytec.fluytec.controller;

import com.fluytec.fluytec.dao.UsuarioDao;
import com.fluytec.fluytec.dao.ventaDao;
import com.fluytec.fluytec.dao.InventarioDao;
import com.fluytec.fluytec.dao.RolesUsuariosDao;
import com.fluytec.fluytec.domain.Inventario;
import com.fluytec.fluytec.domain.RolesUsuarios;
import com.fluytec.fluytec.domain.Usuario;
import com.fluytec.fluytec.domain.Venta;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FluytecController {

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private ventaDao ventaDao;
    
    @Autowired
    private InventarioDao inventariosDao;
    
    @Autowired
    private RolesUsuariosDao rolesUsuariosDao;
    private String id_usuarios;
    
    @GetMapping("/Ejemplo1")
    public String Ejemplo1(Usuario usuario) {
        return "Ejemplo1";
    }

    @GetMapping("/")
    public String index(Usuario usuario) {
        return "index-fluytec";
    }

    @GetMapping("/login")
    public String login(Usuario usuario) {
        return "NuevoLogin";
    }

    @PostMapping("/login")
    public String iniciarSesion(Usuario usuario, Model model, Venta venta, Inventario inventario) {
        Usuario validar = usuarioDao.iniciarSesion(usuario.getId_usuarios(), usuario.getClave());
        if (validar == null) {
            return "redirect:/login";
        } else if (validar.getRoles().get(0).getRol().equals("administrador")) {
            List<Usuario> lstUsuarios;
            lstUsuarios = (List<Usuario>) usuarioDao.findAll();//select * from cliente
            model.addAttribute("lstUsuarios", lstUsuarios);
            return "index";
        } else if (validar.getRoles().get(0).getRol().equals("aux_de_bodega")) {
            List<Inventario> lstInventario = new ArrayList<>();
            lstInventario = (List<Inventario>) inventariosDao.findAll();
            model.addAttribute("lstInventario", lstInventario);
            return "index-bod";
        } else if (validar.getRoles().get(0).getRol().equals("vendedor")) {
            List<Venta> lstVenta = new ArrayList<>();
            lstVenta = (List<Venta>) ventaDao.findAll();
            model.addAttribute("lstVenta", lstVenta);
            return "index-ventas";
        }
        return "redirect:/login";
    }
    
    
    
    
    @GetMapping("/cambioContraseña")
    public String cambioContraseña(Model model, @RequestParam(name = "id_usuarios") String id_usuarios){
        model.addAttribute("id_usuarios", id_usuarios);
        this.id_usuarios = id_usuarios;
        return "cambioContraseña";
    }

    
    
    @PostMapping("/ModificarContraseña")
    public String ModificarContra(Model model , @RequestBody MultiValueMap<String, String> formData) {
        String clave = formData.get("contrasena_usuario").get(0);
        String id_usuarios = this.id_usuarios;
        Usuario usuario = usuarioDao.findById(Integer.parseInt(id_usuarios)).orElse(null);
        usuario.setClave(clave);
        //usuario.setContrasena_usuario(usuario.getContrasena_usuario());
        usuarioDao.save(usuario);
        model.addAttribute("usuario", usuario);
        return "redirect:login";
    }
    

    @GetMapping("/index")
    public String usuarios(Model model) {
        String salida = "Enviando informacion desde el controlador hacia la vista";
        Usuario obj = new Usuario();
        List<Usuario> lstUsuarios = new ArrayList<>();
        lstUsuarios = (List<Usuario>) usuarioDao.findAll();//select * from cliente

        model.addAttribute("lstUsuarios", lstUsuarios);
        model.addAttribute("usuario", obj);
        model.addAttribute("salida", salida);

        return "index";
    }

    @GetMapping("/registroUsuario")
    public String registroUsuario(Usuario usuario) {
        return "registroUsuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
        return "redirect:index";
    }

    @RequestMapping("/eliminarUsuario")
    public String eliminarUsuario(
            @RequestParam(name = "id_usuarios", defaultValue = "0") Integer id_usuarios) {
        usuarioDao.deleteById(id_usuarios);
        return "redirect:index";
    }

    @RequestMapping("/modificarUsuario")
    public String modificarUsuario(
            @RequestParam(name = "id_usuarios", defaultValue = "0") Integer id_usuarios, Model model) {
        Usuario usuario = usuarioDao.findById(id_usuarios).orElse(null);
        model.addAttribute("usuario", usuario);
        return "registroUsuario";
    }

    @GetMapping("/form")
    public String form(Model model, Inventario inventario) {
        List<Inventario> lstInventario = new ArrayList<>();
        lstInventario = (List<Inventario>) inventariosDao.findAll();
        model.addAttribute("lstInventario", lstInventario);
        return "form";
    }
    
    @GetMapping("/calendar")
    public String calendar() {
        return "calendar";
    }
    
    
    @GetMapping("/roles")
    public String roles(Model model) {
         String salida = "Enviando informacion desde el controlador hacia la vista";
        RolesUsuarios obj = new RolesUsuarios();
        List<RolesUsuarios> lstRolesUsuarios = new ArrayList<>();
        lstRolesUsuarios = (List<RolesUsuarios>) rolesUsuariosDao.findAll();//select * from cliente

        model.addAttribute("lstRolesUsuarios", lstRolesUsuarios);
        model.addAttribute("RolesUsuarios", obj);
        model.addAttribute("salida", salida);
        
        return "roles";
    }
    @PostMapping("/guardarRol")
    public String guardarRol(RolesUsuarios rolesUsuarios) {
        rolesUsuariosDao.save(rolesUsuarios);
        return "redirect:roles";
    }
    @RequestMapping("/modificarRol")
    public String modificarRol( 
            @RequestParam(name = "roles_id_rol", defaultValue = "0") Integer roles_id_rol, Model model) {
        RolesUsuarios rolesUsuarios = rolesUsuariosDao.findById(roles_id_rol).orElse(null);
        model.addAttribute("rolesUsuarios", rolesUsuarios);
        return "modificarRol";
    }
    

}
