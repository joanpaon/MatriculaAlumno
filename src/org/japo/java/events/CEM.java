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
package org.japo.java.events;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.japo.java.controllers.Controller;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class CEM implements ChangeListener {

    // Referencia al Controller
    private final Controller control;

    // Constructor
    public CEM(Controller control) {
        this.control = control;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // control.procesarCambio(e);
    }

}
