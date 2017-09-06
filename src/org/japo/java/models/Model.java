/* 
 * Copyright 2017 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.models;

import java.io.Serializable;
import org.japo.java.libraries.UtilesDNI;
import org.japo.java.libraries.UtilesValidacion;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class Model implements Serializable {

    // Ciclos Formativos
    public static final String[] CICLOS = {
        "ESI", "ASI", "DAI", "SMR", "ASIR", "DAW", "DAM"
    };

    // Número de items
    public static final int NUM_ITEMS = 8;

    // Constantes de acceso
    public static final int POS_EXP = 0;
    public static final int POS_NOM = 1;
    public static final int POS_APE = 2;
    public static final int POS_DNI = 3;
    public static final int POS_CIC = 4;
    public static final int POS_CUR = 5;
    public static final int POS_TUR = 6;
    public static final int POS_REP = 7;

    // Expresiones regulares
    public static final String ER_EXP = "\\d{5,8}";
    public static final String ER_NOM = "[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ\\-\\. ]{2,20}";
    public static final String ER_APE = "[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ\\-\\. ]{2,20}";
    public static final String ER_CIC = "ESI|ASI|DAI|SMR|DAM|DAW|ASIR";
    public static final String ER_CUR = "[123]";
    public static final String ER_TUR = "[MVND]";

    // Valores por defecto
    public static final String DEF_EXP = "00000";
    public static final String DEF_NOM = "Nombre";
    public static final String DEF_APE = "Apellidos";
    public static final String DEF_DNI = "88888888Y";
    public static final String DEF_CIC = "DAW";
    public static final char DEF_CUR = '1';
    public static final char DEF_TUR = 'M';
    public static final boolean DEF_REP = false;

    // Campos de la entidad
    private String exp;
    private String nom;
    private String ape;
    private String dni;
    private String cic;
    private char cur;
    private char tur;
    private boolean rep;

    public Model() {
        exp = DEF_EXP;
        nom = DEF_NOM;
        ape = DEF_APE;
        dni = DEF_DNI;
        cic = DEF_CIC;
        cur = DEF_CUR;
        tur = DEF_TUR;
        rep = DEF_REP;
    }

    // Constructor Parametrizado
    public Model(String exp, String nom, String ape, String dni,
            String cic, char cur, char tur, boolean rep) {
        // Expediente
        if (UtilesValidacion.validarDato(exp, ER_EXP)) {
            this.exp = exp;
        } else {
            this.exp = DEF_EXP;
        }

        // Nombre
        if (UtilesValidacion.validarDato(nom, ER_NOM)) {
            this.nom = nom;
        } else {
            this.nom = DEF_NOM;
        }

        // Apellidos
        if (UtilesValidacion.validarDato(ape, ER_APE)) {
            this.ape = ape;
        } else {
            this.ape = DEF_APE;
        }

        // Nif
        if (UtilesDNI.validarDNI(dni)) {
            this.dni = dni;
        } else {
            this.dni = DEF_DNI;
        }

        // Ciclo
        if (UtilesValidacion.validarDato(cic, ER_CIC)) {
            this.cic = cic;
        } else {
            this.cic = DEF_CIC;
        }

        // Curso
        if (UtilesValidacion.validarDato(cur + "", ER_CUR)) {
            this.cur = cur;
        } else {
            this.cur = DEF_CUR;
        }

        // Turno
        if (UtilesValidacion.validarDato(tur + "", ER_TUR)) {
            this.tur = tur;
        } else {
            this.tur = DEF_TUR;
        }

        // Repetidor
        this.rep = rep;
    }

    // --- INICIO SETTERS / GETTERS
    //
    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        if (UtilesValidacion.validarDato(exp, ER_EXP)) {
            this.exp = exp;
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (UtilesValidacion.validarDato(nom, ER_NOM)) {
            this.nom = nom;
        }
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        if (UtilesValidacion.validarDato(ape, ER_APE)) {
            this.ape = ape;
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (UtilesDNI.validarDNI(dni)) {
            this.dni = dni;
        }
    }

    public String getCic() {
        return cic;
    }

    public void setCic(String cic) {
        if (UtilesValidacion.validarDato(cic, ER_CIC)) {
            this.cic = cic;
        }
    }

    public char getCur() {
        return cur;
    }

    public void setCur(char cur) {
        if (UtilesValidacion.validarDato(cur + "", ER_CUR)) {
            this.cur = cur;
        }
    }

    public char getTur() {
        return tur;
    }

    public void setTur(char tur) {
        if (UtilesValidacion.validarDato(tur + "", ER_TUR)) {
            this.tur = tur;
        }
    }

    public boolean isRep() {
        return rep;
    }

    public void setRep(boolean rep) {
        this.rep = rep;
    }
}
