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
package de.fraunhofer.iosb.ilt.swe.common.constraint;

import de.fraunhofer.iosb.ilt.configurable.AbstractConfigurable;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorInt;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorList;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;
import static de.fraunhofer.iosb.ilt.swe.common.AbstractSWE.MODE_SIMPLE_EXPERT;
import java.util.List;

/**
 *
 * @author Hylke van der Schaaf
 */
@ConfigurableClass(
        jsonName = "AllowedValues",
        profilesEdit = MODE_SIMPLE_EXPERT)
public class AllowedTimes extends AbstractConfigurable<Void, Void> {

    @ConfigurableField(editor = EditorList.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Value", description = "The values that the user can choose from."
    )
    @EditorList.EdOptsList(editor = EditorString.class,
            profilesEdit = MODE_SIMPLE_EXPERT)
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private List<String> value;

    @ConfigurableField(editor = EditorList.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Intervals",
            description = "The intervals that the value must fall in.")
    @EditorList.EdOptsList(editor = EditorList.class, editorKey = "list-2",
            profilesEdit = MODE_SIMPLE_EXPERT)
    @EditorList.EdOptsList(editor = EditorString.class, myKey = "list-2",
            profilesEdit = MODE_SIMPLE_EXPERT,
            minCount = 2, maxCount = 2, horizontal = true, labelText = "Interval:")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private List<List<String>> interval;

    @ConfigurableField(editor = EditorInt.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Significant Figures",
            description = "The number of significant figures.")
    @EditorInt.EdOptsInt(min = 0, max = 100, step = 1, dflt = 1)
    private Integer significantFigures;

    public List<String> getValue() {
        return value;
    }

    public List<List<String>> getInterval() {
        return interval;
    }

    public Integer getSignificantFigures() {
        return significantFigures;
    }

}
