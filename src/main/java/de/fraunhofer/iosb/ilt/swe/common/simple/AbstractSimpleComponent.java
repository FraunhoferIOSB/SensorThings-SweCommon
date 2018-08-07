/*
 * Copyright (C) 2017 Fraunhofer Institut IOSB, Fraunhoferstr. 1, D 76131
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

import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorClass;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorList;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;
import de.fraunhofer.iosb.ilt.swe.common.AbstractDataComponent;
import de.fraunhofer.iosb.ilt.swe.common.util.NillValue;
import java.util.List;

/**
 *
 * @author Hylke van der Schaaf
 */
public abstract class AbstractSimpleComponent extends AbstractDataComponent {

    @ConfigurableField(editor = EditorString.class, optional = true,
            profilesGui = MODE_EXPERT,
            label = "Axis ID", description = "A string that uniquely identifies one of the reference frame’s axes along which the coordinate value is given.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String axisID;

    @ConfigurableField(editor = EditorString.class, optional = true,
            profilesGui = MODE_EXPERT,
            label = "Reference Frame", description = "The reference frame relative to which the coordinate value is given. Commonly an EPSG identifier.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String referenceFrame;

    @ConfigurableField(editor = EditorList.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "NilValues", description = "a list (i.e. one or more) of NIL values.")
    @EditorList.EdOptsList(editor = EditorClass.class,
            profilesEdit = MODE_SIMPLE_EXPERT)
    @EditorClass.EdOptsClass(clazz = NillValue.class)
    private List<NillValue> nilValues;

    // TODO
    private Object quality;

    public String getReferenceFrame() {
        return referenceFrame;
    }

    public void setReferenceFrame(String referenceFrame) {
        this.referenceFrame = referenceFrame;
    }

    public String getAxisID() {
        return axisID;
    }

    public void setAxisID(String axisID) {
        this.axisID = axisID;
    }

    public List<NillValue> getNilValues() {
        return nilValues;
    }

    public void setNilValues(List<NillValue> nilValues) {
        this.nilValues = nilValues;
    }

}
