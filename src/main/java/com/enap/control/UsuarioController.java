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
    //private Estudiante estudianteEditado;
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
    private boolean verFormAsignaturas;
    private boolean verBtnRegUsuario;
    private String titulo;

    public UsuarioController() {
        us = new UsuarioDao();
        saveUsuario = new Usuario();
        asDao = new AsignaturaDao();
        docDao = new DocenteDao();
        cursoDao = new CursoDao();
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
        noVerForms();
        verTablaUsuarios = true;
        listarUsuarios();
    }

    public void btnVerDocentes() {
        titulo = "Docentes";
        verTablaAsignaturas = false;
        verTablaEstudiantes = false;
        verTablaUsuarios = false;
        noVerForms();
        verTablaDocentes = true;
        listarDocentes();
    }

    public void btnVerEstudiantes() {
        titulo = "Estudiantes";
        verTablaAsignaturas = false;
        verTablaDocentes = false;
        verTablaUsuarios = false;
        noVerForms();
        verTablaEstudiantes = true;
        listarEstudiantes();
    }

    public void btnVerAsignaturas() {
        titulo = "Asignaturas";
        verTablaUsuarios = false;
        verTablaEstudiantes = false;
        verTablaDocentes = false;
        noVerForms();
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
        if (verFormUsuario == true) {
            switch (saveUsuario.getTipo()) {
                case "docente":
                    verBtnRegUsuario = false;
                    verFormAsignaturas = false;
                    verFormEstudiante = false;
                    verFormDocente = true;
                    break;
                case "estudiante":
                    verBtnRegUsuario = false;
                    verFormAsignaturas = false;
                    verFormDocente = false;
                    verFormEstudiante = true;
                    break;
                default:
                    verFormDocente = false;
                    verFormEstudiante = false;
                    verFormAsignaturas = false;
                    verBtnRegUsuario = true;
                    break;
            }
        }
    }

    public void btnVerFormUsuario() {
        noVerTablas();
        listarCursos();
        verFormAsignaturas = false;
        verFormUsuario = true;
        titulo = "Crear Usuario";
    }
    
    public void btnVerFormAsignatura() {
        noVerTablas();
        listarDocentes();
        verFormDocente = false;
        verFormEstudiante = false;
        verFormUsuario = false;
        verFormAsignaturas = true;
        titulo = "Añadir Asignatura";
    }
    
    public void noVerTablas() {
        verTablaAsignaturas = false;
        verTablaDocentes = false;
        verTablaEstudiantes = false;
        verTablaUsuarios = false;
    }
    
    public void noVerForms() {
        verFormUsuario = false;
        verFormAsignaturas = false;
        verFormDocente = false;
        verFormEstudiante = false;
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
            us.create(saveUsuario);
            estudiante.setEstudianteID(saveUsuario.getIdentificacion());
            estDao.create(estudiante);
        } catch (Exception e) {
            System.out.println("Error al crear usuario estudiante:\n" + e.getMessage());
        }

        saveUsuario = new Usuario();
        estudiante = new Estudiante();
    }
    
    public void crearAsignatura() {
        try {
        asDao.create(asignatura);
        } catch (Exception e) {
            System.out.println("Error al añadir la asignatura:\n" + e.getMessage());
        }
        asignatura = new Asignatura();
    }

    public void listarCursos() {
        listaCurso = cursoDao.findAll();
    }
    
    // Optimizar este metodo para no tener que hace una consulta a la bd para traer los datos de un estudiante seleccionado
    // en la tabla
    public void pasarDatosEstudiante(Long id) {
        Query consulta = estDao.getEm().createNamedQuery("Estudiante.findByEstudianteID");
        consulta.setParameter("estudianteID", id);
        estudiante = (Estudiante) consulta.getSingleResult();
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

    public void setVerFormAsignaturas(boolean verFormCdecurso) {
        this.verFormAsignaturas = verFormCdecurso;
    }

    public boolean isVerFormAsignaturas() {
        return verFormAsignaturas;
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
