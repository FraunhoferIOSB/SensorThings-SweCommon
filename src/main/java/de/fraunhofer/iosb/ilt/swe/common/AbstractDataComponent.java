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
package de.fraunhofer.iosb.ilt.swe.common;

import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorBoolean;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;

/**
 *
 * @author Hylke van der Schaaf
 */
public abstract class AbstractDataComponent extends AbstractSWEIdentifiable {

    @ConfigurableField(editor = EditorString.class, optional = true,
            profilesGui = MODE_EXPERT,
            label = "Definition", description = "A scoped namethat maps to a controlled term defined in a (web accessible) dictionary, registry or ontology.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String definition;

    @ConfigurableField(editor = EditorBoolean.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Optional", description = "A flag indicating if the component value can be omitted.")
    @EditorBoolean.EdOptsBool(dflt = false)
    private boolean optional;

    @ConfigurableField(editor = EditorBoolean.class, optional = true,
            profilesGui = MODE_EXPERT,
            label = "Updatable", description = "A flag indicating if the component value is fixed or can be updated.")
    @EditorBoolean.EdOptsBool(dflt = false)
    private boolean updatable;

    public String getDefinition() {
        return definition;
    }

    public Boolean isOptional() {
        return optional;
    }

    public Boolean isUpdatable() {
        return updatable;
    }

}
