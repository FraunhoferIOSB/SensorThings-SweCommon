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
package de.fraunhofer.iosb.ilt.swe.common.simple.range;

import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorClass;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorList;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;
import de.fraunhofer.iosb.ilt.swe.common.constraint.AllowedTokens;
import de.fraunhofer.iosb.ilt.swe.common.simple.AbstractSimpleComponent;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hylke van der Schaaf
 */
@ConfigurableClass(jsonName = "CategoryRange")
public class CategoryRange extends AbstractSimpleComponent {

    @ConfigurableField(editor = EditorList.class, optional = true,
            profilesGui = MODE_VALUE,
            label = "Value", description = "The starting end ending values of this CategoryRange.")
    @EditorList.EdOptsList(editor = EditorString.class,
            profilesEdit = MODE_SIMPLE_EXPERT,
            minCount = 2, maxCount = 2, horizontal = true, labelText = "Range:")
    @EditorString.EdOptsString
    private List<String> value;

    @ConfigurableField(editor = EditorClass.class, optional = true,
            profilesGui = MODE_SIMPLE + "," + MODE_EXPERT,
            label = "Allowed Tokens", description = "A limited list of possible values.")
    private AllowedTokens constraint;

    //TODO
    private Map<String, String> codeSpace;

    public AllowedTokens getConstraint() {
        return constraint;
    }

    public List<String> getValue() {
        return value;
    }

}
