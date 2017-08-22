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
package org.japo.java.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.japo.java.models.Model;
import org.japo.java.views.View;
import org.japo.java.libraries.UtilesApp;
import org.japo.java.libraries.UtilesSwing;
import org.japo.java.interfaces.IDataAccessController;
import org.japo.java.libraries.UtilesValidacion;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class Controller {

    // Propiedades Aplicación
    public static final String PRP_FICHERO_DATOS = "fichero_datos";

    // Fichero Propiedades Aplicación
    public static final String FICHERO_PRP = "app.properties";

    // Referencias
    private final Model model;
    private final View view;
    private final Properties prpApp;
    private final IDataAccessController dac;

    // Constructor Parametrizado
    public Controller(Model model, View view) {
        // Memorizar Referencias
        this.model = model;
        this.view = view;

        // Cargar Propiedades Aplicación
        this.prpApp = UtilesApp.cargarPropiedades(FICHERO_PRP);

        // *** Controlador de Persistencia ***
        this.dac = new DataAccessControllerJSON();
    }

    // Persistencia > Modelo > Vista
    public void procesarImportacion(ActionEvent evt) {
        try {
            // Fichero de Datos
            String fichero = prpApp.getProperty(PRP_FICHERO_DATOS);

            // Persistencia > Modelo
            dac.importarModelo(model, fichero);

            // Modelo > Vista
            sincronizarModeloVista(model, view);

            // Mensaje - Importación OK
            String msg = "Datos importados correctamente";
            JOptionPane.showMessageDialog(view, msg);
        } catch (Exception e) {
            // Mensaje - Importación NO
            String msg = "Error al importar los datos";
            JOptionPane.showMessageDialog(view, msg);
        }
    }

    // Vista > Modelo > Persistencia
    public void procesarExportacion(ActionEvent evt) {
        // Validar Datos Vista
        if (validarControlesSubjetivos(view)) {
            try {
                // Vista > Modelo
                sincronizarVistaModelo(view, model);

                // Fichero de Datos
                String fichero = prpApp.getProperty(PRP_FICHERO_DATOS);

                // Modelo > Persistencia
                dac.exportarModelo(model, fichero);

                // Mensaje - Exportación OK
                String msg = "Datos exportados correctamente";
                JOptionPane.showMessageDialog(view, msg);
            } catch (Exception e) {
                // Mensaje - Exportación NO
                String msg = "Error al exportar los datos";
                JOptionPane.showMessageDialog(view, msg);
            }
        } else {
            // Mensaje - Validación Pendiente
            JOptionPane.showMessageDialog(view, "Hay datos erróneos.");
        }
    }

    // Modelo > Vista 
    public void sincronizarModeloVista(Model model, View view) {
        view.txfExp.setText(model.getExp());
        view.txfNom.setText(model.getNom());
        view.txfApe.setText(model.getApe());
        view.txfDNI.setText(model.getDni());
        view.cbbCic.setSelectedItem(model.getCic());
        switch (model.getCur()) {
            case '1':
               view.rbtCur1.setSelected(true);
               break;
            case '2':
               view.rbtCur2.setSelected(true);
               break;
            default:
               view.rbtCur3.setSelected(true);
        }
        switch (model.getTur()) {
            case 'M':
               view.rbtTurM.setSelected(true);
               break;
            case 'V':
               view.rbtTurV.setSelected(true);
               break;
            case 'N':
               view.rbtTurN.setSelected(true);
               break;
            default:
               view.rbtTurD.setSelected(true);
        }
        view.cbxRep.setSelected(model.isRep());
    }

    // Vista (Subjetivo) > Modelo
    private void sincronizarVistaModelo(View view, Model model) {
        model.setExp(view.txfExp.getText());
        model.setNom(view.txfNom.getText());
        model.setApe(view.txfApe.getText());
        model.setDni(view.txfDNI.getText());
        model.setCic((String) view.cbbCic.getSelectedItem());
        if (view.rbtCur1.isSelected()) {
            model.setCur('1');
        } else if (view.rbtCur2.isSelected()) {
            model.setCur('2');
        } else {
            model.setCur('3');
        } 
        if (view.rbtTurM.isSelected()) {
            model.setTur('M');
        } else if (view.rbtTurV.isSelected()) {
            model.setTur('V');
        } else if (view.rbtTurN.isSelected()) {
            model.setTur('N');
        } else {
            model.setTur('D');
        } 
        model.setRep(view.cbxRep.isSelected());
    }

    // Validar Controles Subjetivos
    private boolean validarControlesSubjetivos(View view) {
        // Validar Controles
        boolean expOK = UtilesValidacion.validarCampoTexto(view.txfExp, Model.ER_EXP, "?");
        boolean nomOK = UtilesValidacion.validarCampoTexto(view.txfNom, Model.ER_NOM, "?");
        boolean apeOK = UtilesValidacion.validarCampoTexto(view.txfApe, Model.ER_APE, "?");
        boolean dniOK = UtilesValidacion.validarCampoDNI(view.txfDNI, "?");

        // Devolver Validación
        return expOK && nomOK && apeOK && dniOK;
    }

    // Propiedades Vista > Estado Vista
    public void restaurarEstadoVista(View view, Properties prp) {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(view, prp.getProperty(View.PRP_RUTA_FAVICON, "img/favicon.png"));

        // Establece Lnf
        UtilesSwing.establecerLnF(prp.getProperty(View.PRP_LOOK_AND_FEEL, UtilesSwing.WINDOWS));

        // Activa Singleton
        if (!UtilesApp.activarInstancia(prp.getProperty(View.PRP_PUERTO_BLOQUEO, UtilesApp.PUERTO_BLOQUEO))) {
            UtilesSwing.terminarPrograma(view);
        }

        // Activa otras propiedades
    }

    // Gestión Cierre Vista
    public void procesarCierreVista(WindowEvent evt) {
        // Memorizar Estado de la Applicación
        memorizarEstadoVista(prpApp);

        // Otras Acciones
    }

    // Estado Actual > Persistencia
    public void memorizarEstadoVista(Properties prpApp) {
//        // Actualiza Propiedades Estado Actual
//
//        // Guardar Estado Actual
//        UtilesApp.guardarPropiedades(prpApp);
    }
}
