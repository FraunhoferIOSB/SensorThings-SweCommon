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

import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;
import static de.fraunhofer.iosb.ilt.swe.common.AbstractSWE.MODE_SIMPLE_EXPERT;

/**
 *
 * @author Hylke van der Schaaf
 */
@ConfigurableClass(profilesEdit = MODE_SIMPLE_EXPERT)
public abstract class AbstractSWEIdentifiable extends AbstractSWE {

    @ConfigurableField(editor = EditorString.class, optional = true,
            profilesGui = MODE_EXPERT,
            label = "Identifier", description = "A unique identifier.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String identifier;

    @ConfigurableField(editor = EditorString.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT_VALUE,
            label = "Label", description = "A short descriptive name.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String label;

    @ConfigurableField(editor = EditorString.class, optional = true,
            profilesGui = MODE_EXPERT,
            label = "Description", description = "A longer description.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String description;

    public String getIdentifier() {
        return identifier;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

}
