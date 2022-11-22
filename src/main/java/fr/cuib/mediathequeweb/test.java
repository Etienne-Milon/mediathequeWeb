package fr.cuib.mediathequeweb;

import fr.cuib.mediathequeweb.security.ApplicationBean;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

public class test {
    public static void main(String[] args) {
        ApplicationBean ab = new ApplicationBean();
        ab.setPbkdf2PasswordHash(new Pbkdf2PasswordHashImpl());
        for(int i = 0; i < 10; i++){
            System.out.println(ab.passwordHash("abcdef123"));
        }
    }
}
