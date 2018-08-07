/*
 * Copyright (C) 2018 Fraunhofer Institut IOSB, Fraunhoferstr. 1, D 76131
 * Karlsruhe, Germany.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.fraunhofer.iosb.ilt.swe.common.simple;

import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorBoolean;
import static de.fraunhofer.iosb.ilt.swe.common.AbstractSWE.MODE_VALUE;

/**
 *
 * @author Hylke van der Schaaf
 */
@ConfigurableClass(jsonName = "Boolean")
public class SweBoolean extends AbstractSimpleComponent {

    @ConfigurableField(editor = EditorBoolean.class, optional = true,
            profilesGui = MODE_VALUE,
            label = "Value", description = "The value of this Boolean Component.")
    @EditorBoolean.EdOptsBool(dflt = false)
    private Boolean value;

}
