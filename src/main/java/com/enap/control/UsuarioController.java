package com.enap.control;

import com.enap.dao.AsignaturaDao;
import com.enap.dao.CursoDao;
import com.enap.dao.DocenteDao;
import com.enap.dao.EstudianteDao;
import com.enap.dao.UsuarioDao;
import com.enap.modelo.Asignatura;
import com.enap.modelo.Curso;
import com.enap.modelo.Docente;
import com.enap.modelo.Estudiante;
import com.enap.modelo.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Marco.ENAP
 */
public class UsuarioController implements Serializable {

//    ||||||||||||||||||||||||||||||||||
//    ||           Inyecciones        ||
//    ||||||||||||||||||||||||||||||||||
    @Inject
    private UsuarioDao us;
    @Inject
    private AsignaturaDao asDao;
    @Inject
    private DocenteDao docDao;
    @Inject
    private EstudianteDao estDao;
    @Inject
    private CursoDao cursoDao;

//    ||||||||||||||||||||||||||||||||||
//    ||           Colecciones        ||
//    ||||||||||||||||||||||||||||||||||
    private List<Usuario> listaUsuarios;
    private List<Docente> listaDocentes;
    private List<Estudiante> listaEstudiantes;
    private List<Asignatura> listaAsignaturas;
    private List<Curso> listaCurso;

//    ||||||||||||||||||||||||||||||||||
//    ||        Objetos de negocio    ||
//    ||||||||||||||||||||||||||||||||||
    private Usuario usuario;
    private Usuario saveUsuario;
    private Asignatura asignatura;
    private Docente docente;
    private Estudiante estudiante;
    private Curso curso;

//||||||||||||||||||||||||||||||||||
//||     Variables de Control     ||
//||||||||||||||||||||||||||||||||||
    private boolean verTablaUsuarios;
    private boolean verTablaAsignaturas;
    private boolean verTablaDocentes;
    private boolean verTablaEstudiantes;
    private boolean verFormUsuario;
    private boolean verFormDocente;
    private boolean verFormEstudiante;
    private boolean verFormCdecurso;
    private boolean verBtnRegUsuario;
    private String titulo;

    public UsuarioController() {
        us = new UsuarioDao();
        saveUsuario = new Usuario();
        asDao = new AsignaturaDao();
        docDao = new DocenteDao();
        usuario = new Usuario();
        docente = new Docente();
        estudiante = new Estudiante();
        asignatura = new Asignatura();
        curso = new Curso();
        titulo = "Panel Principal";
        verBtnRegUsuario = true;
        //listarUsuarios();
    }

//    ||||||||||||||||||||||||||||||||||
//    ||       Lógica de Negocio      ||
//    ||||||||||||||||||||||||||||||||||
    public void login() throws IOException {
        Query consulta = us.getEm().createNamedQuery("Usuario.findUserAndPass");
        consulta.setParameter("identificacion", usuario.getIdentificacion());
        consulta.setParameter("contrasena", usuario.getContrasena());

        try {
            usuario = (Usuario) consulta.getSingleResult();
            FacesContext.getCurrentInstance().getExternalContext().redirect("vistas/admin.xhtml");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido! " + usuario.getNombre(), "Inicio de sesion correcto"));
        } catch (NoResultException nre) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Identificacion o Contraseña Incorrectas", "Revise sus datos e intente de nuevo"));
        }
    }

    public void btnVerUsuarios() {
        titulo = "Usuarios";
        verTablaAsignaturas = false;
        verTablaEstudiantes = false;
        verTablaDocentes = false;
        verFormUsuario = false;
        verTablaUsuarios = true;
        listarUsuarios();
    }

    public void btnVerDocentes() {
        titulo = "Docentes";
        verTablaAsignaturas = false;
        verTablaEstudiantes = false;
        verTablaUsuarios = false;
        verFormUsuario = false;
        verTablaDocentes = true;
        listarDocentes();
    }

    public void btnVerEstudiantes() {
        titulo = "Estudiantes";
        verTablaAsignaturas = false;
        verTablaDocentes = false;
        verTablaUsuarios = false;
        verFormUsuario = false;
        verTablaEstudiantes = true;
        listarEstudiantes();
    }

    public void btnVerAsignaturas() {
        titulo = "Asignaturas";
        verTablaUsuarios = false;
        verTablaEstudiantes = false;
        verTablaDocentes = false;
        verFormUsuario = false;
        verTablaAsignaturas = true;
        listarAsignaturas();
    }

    public void listarUsuarios() {
        listaUsuarios = us.findAll();
    }

    public void listarDocentes() {

        try {
            //listaDocentes = docDao.getEm().createQuery("SELECT d FROM Docente d JOIN FETCH d.usuario u ON d.docenteID=u.identificacion", Docente.class).getResultList();
            listaDocentes = docDao.findAll();
        } catch (Exception e) {
            System.out.println("Error listar docentes:\n" + e.getMessage());
        }
    }

    public void listarEstudiantes() {
        try {
            //listaEstudiantes = estDao.getEm().createQuery("SELECT e FROM Estudiante e JOIN FETCH e.usuario u ON e.estudianteID=u.identificacion").getResultList();
            listaEstudiantes = estDao.findAll();
        } catch (Exception e) {
            System.out.println("Error listar estudiantes:\n" + e.getMessage());
        }
    }

    public void listarAsignaturas() {
        try {
            listaAsignaturas = asDao.findAll();
        } catch (Exception e) {
            System.out.println("Error listar asignaturas:\n" + e.getMessage());
        }
    }

    public void opcionesFormUsuario() {
        switch (saveUsuario.getTipo()) {
            case "docente":
                verBtnRegUsuario = false;
                verFormCdecurso = false;
                verFormEstudiante = false;
                verFormDocente = true;
                break;
            case "estudiante":
                verBtnRegUsuario = false;
                verFormCdecurso = false;
                verFormDocente = false;
                verFormEstudiante = true;
                break;
            case "cdecurso":
                verBtnRegUsuario = false;
                verFormDocente = false;
                verFormEstudiante = false;
                verFormCdecurso = true;
                break;
            default:
                verFormDocente = false;
                verFormEstudiante = false;
                verFormCdecurso = false;
                verBtnRegUsuario = true;
                break;
        }
    }

    public void btnVerFormUsuario() {
        verTablaUsuarios = false;
        verTablaEstudiantes = false;
        verTablaDocentes = false;
        verTablaAsignaturas = false;
        verFormUsuario = true;
        titulo = "Crear Usuario";
    }

    public void crearUsuario() {
        usuario.setIdentificacion(saveUsuario.getIdentificacion());
        usuario.setContrasena(saveUsuario.getContrasena());
        usuario.setTipo(saveUsuario.getTipo());
        usuario.setNombre(saveUsuario.getNombre());
        usuario.setApellido(saveUsuario.getApellido());
        usuario.setEmail(saveUsuario.getEmail());
        us.create(usuario);
        usuario = new Usuario();
        saveUsuario = new Usuario();
    }

    public void crearDocente() {

        try {
            docente.setUsuario(saveUsuario);
            us.create(saveUsuario);
            docente.setDocenteID(saveUsuario.getIdentificacion());
            docDao.create(docente);
        } catch (Exception e) {
            System.out.println("Error al crear usuario docente:\n" + e.getMessage());
        }

        saveUsuario = new Usuario();
        docente = new Docente();
    }

    public void crearEstudiante() {
        try {
            estudiante.setUsuario(saveUsuario);
            System.out.println(estudiante.getUsuario().getIdentificacion());
            System.out.println(estudiante.getUsuario().getNombre());
            System.out.println(estudiante.getUsuario().getApellido());
            System.out.println(estudiante.getCurso().getCursoID());
            System.out.println(estudiante.getCurso().getNombre());
        } catch (Exception e) {
            System.out.println("Error al crear usuario estudiante:\n" + e.getMessage());
        }
    }

    public void listarCursos() {
        cursoDao = new CursoDao();
        listaCurso = cursoDao.findAll();
    }

//    ||||||||||||||||||||||||||||||||||
//    ||       Getters & Setters      ||
//    ||||||||||||||||||||||||||||||||||
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public UsuarioDao getUs() {
        return us;
    }

    public void setUs(UsuarioDao us) {
        this.us = us;
    }

    public AsignaturaDao getAsDao() {
        return asDao;
    }

    public void setAsDao(AsignaturaDao asDao) {
        this.asDao = asDao;
    }

    public void setDocDao(DocenteDao docDao) {
        this.docDao = docDao;
    }

    public DocenteDao getDocDao() {
        return docDao;
    }

    public void setEstDao(EstudianteDao estDao) {
        this.estDao = estDao;
    }

    public EstudianteDao getEstDao() {
        return estDao;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Asignatura> getListaAsignaturas() {
        return listaAsignaturas;
    }

    public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }

    public void setListaDocentes(List<Docente> listaDocentes) {
        this.listaDocentes = listaDocentes;
    }

    public List<Docente> getListaDocentes() {
        return listaDocentes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public boolean isVerTablaUsuarios() {
        return verTablaUsuarios;
    }

    public void setVerTablaUsuarios(boolean verTablaUsuarios) {
        this.verTablaUsuarios = verTablaUsuarios;
    }

    public boolean isVerTablaAsignaturas() {
        return verTablaAsignaturas;
    }

    public void setVerTablaAsignaturas(boolean verTablaAsignaturas) {
        this.verTablaAsignaturas = verTablaAsignaturas;
    }

    public void setVerTablaDocentes(boolean verTablaDocentes) {
        this.verTablaDocentes = verTablaDocentes;
    }

    public boolean isVerTablaDocentes() {
        return verTablaDocentes;
    }

    public void setVerTablaEstudiantes(boolean verTablaEstudiantes) {
        this.verTablaEstudiantes = verTablaEstudiantes;
    }

    public boolean isVerTablaEstudiantes() {
        return verTablaEstudiantes;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getSaveUsuario() {
        return saveUsuario;
    }

    public void setSaveUsuario(Usuario saveUsuario) {
        this.saveUsuario = saveUsuario;
    }

    public boolean isVerFormUsuario() {
        return verFormUsuario;
    }

    public void setVerFormUsuario(boolean verFormUsuario) {
        this.verFormUsuario = verFormUsuario;
    }

    public boolean isVerFormDocente() {
        return verFormDocente;
    }

    public void setVerFormDocente(boolean verFormDocente) {
        this.verFormDocente = verFormDocente;
    }

    public void setVerFormEstudiante(boolean verFormEstudiante) {
        this.verFormEstudiante = verFormEstudiante;
    }

    public boolean isVerFormEstudiante() {
        return verFormEstudiante;
    }

    public void setVerFormCdecurso(boolean verFormCdecurso) {
        this.verFormCdecurso = verFormCdecurso;
    }

    public boolean isVerFormCdecurso() {
        return verFormCdecurso;
    }

    public void setVerBtnRegUsuario(boolean verBtnRegUsuario) {
        this.verBtnRegUsuario = verBtnRegUsuario;
    }

    public boolean isVerBtnRegUsuario() {
        return verBtnRegUsuario;
    }

    public List<Curso> getListaCurso() {
        return listaCurso;
    }

    public void setListaCurso(List<Curso> listaCurso) {
        this.listaCurso = listaCurso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
