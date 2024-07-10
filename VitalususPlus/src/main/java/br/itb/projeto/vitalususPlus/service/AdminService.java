package br.itb.projeto.vitalususPlus.service;

import br.itb.projeto.vitalususPlus.model.entity.Admin;
import br.itb.projeto.vitalususPlus.model.entity.Admin;
import br.itb.projeto.vitalususPlus.model.entity.Administrado;
import br.itb.projeto.vitalususPlus.model.entity.Usuario;
import br.itb.projeto.vitalususPlus.model.repository.AdminRepository;
import br.itb.projeto.vitalususPlus.model.repository.AdministradoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    private UsuarioService usuarioService;
    private AdministradoRepository administradoRepository;

    public AdminService(AdminRepository adminRepository, UsuarioService usuarioService, AdministradoRepository administradoRepository) {
        super();
        this.adminRepository = adminRepository;
        this.usuarioService = usuarioService;
        this.administradoRepository = administradoRepository;
    }
    public List<Admin> findAll(){
        List<Admin> listaAdministradores = adminRepository.findAll();
        return listaAdministradores;
    }
    public Admin findById(long id) {
        Optional<Admin> admin = this.adminRepository.findById(id);
        return admin.orElseThrow(() -> new RuntimeException(
                "Admin não encontrado"
        ));
    }
    public Admin save(Admin admin){
        admin.setId(null);
        if (admin.getListaUsuarios()==null){
            admin.setListaUsuarios(new ArrayList<>());
        }
        admin.setNumeroUsuarios(admin.getListaUsuarios().size());
        return adminRepository.save(admin);
    }
    public Admin updateFix(long id) {
        Optional<Admin> _admin = this.adminRepository.findById(id);
        if (_admin.isPresent()) {
            Admin adminUpdatado = _admin.get();
            if (adminUpdatado.getListaUsuarios() == null) {
                adminUpdatado.setListaUsuarios(new ArrayList<>());
            }
            adminUpdatado.setNumeroUsuarios(adminUpdatado.getListaUsuarios().size());
            return adminRepository.save(adminUpdatado);
        }
        return null;
    }
    public Admin addUsuariosAdministrados(long id, Admin admin) {
        Optional<Admin> _admin = this.adminRepository.findById(id);
        if (_admin.isPresent()) {
            Admin adminUpdatado = _admin.get();
            adminUpdatado.getListaUsuarios().addAll(admin.getListaUsuarios());
            if (adminUpdatado.getListaUsuarios() == null) {
                adminUpdatado.setListaUsuarios(new ArrayList<>());
            }
            adminUpdatado.setNumeroUsuarios(adminUpdatado.getListaUsuarios().size());
            return adminRepository.save(adminUpdatado);
        }
        return null;
    }
    public Admin removeUsuariosAdministrados(long id, Usuario usuario) {
        Optional<Admin> _admin = this.adminRepository.findById(id);
        if (_admin.isPresent()) {
            Admin adminUpdatado = _admin.get();
            List<Administrado> administrado = administradoRepository.findAllByUsuarioAndAdmin(usuario, adminUpdatado);
            administradoRepository.deleteAll(administrado);
            if (adminUpdatado.getListaUsuarios() == null) {
                adminUpdatado.setListaUsuarios(new ArrayList<>());
            }
            adminUpdatado.setNumeroUsuarios(adminUpdatado.getListaUsuarios().size());
            return adminRepository.save(adminUpdatado);
        }
        return null;
    }
}
