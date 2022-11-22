package fr.cuib.mediathequeweb.security;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ApplicationBean {

    private String base;
    private String absolutePath;
    @Inject
    Pbkdf2PasswordHash pbkdf2PasswordHash;

    @PostConstruct
    private void initialize() {
        base = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext();
        absolutePath = httpServletRequest.getRequestURL().toString();
        absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iteration", "3072");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA256");
        parameters.put("Pbkdf2PasswordHash.KeySizeBytes", "64");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public Pbkdf2PasswordHash getPbkdf2PasswordHash() {
        return pbkdf2PasswordHash;
    }

    public void setPbkdf2PasswordHash(Pbkdf2PasswordHash pbkdf2PasswordHash) {
        this.pbkdf2PasswordHash = pbkdf2PasswordHash;
    }

    public String passwordHash(String password) {
        return pbkdf2PasswordHash.generate(password.toCharArray());
    }

    public boolean passwordVerify(String password, String passwordHash) {
        return pbkdf2PasswordHash.verify(password.toCharArray(), passwordHash);
    }

}
