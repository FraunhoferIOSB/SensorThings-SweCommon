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

import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorBigDecimal;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorInt;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorList;
import static de.fraunhofer.iosb.ilt.swe.common.AbstractSWE.MODE_SIMPLE_EXPERT;
import de.fraunhofer.iosb.ilt.swe.common.Utils;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Hylke van der Schaaf
 * @author Michael Jacoby
 */
@ConfigurableClass(
        jsonName = "AllowedValues",
        profilesEdit = MODE_SIMPLE_EXPERT)
public class AllowedValues extends AbstractConstraint {

    @ConfigurableField(editor = EditorList.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Values", description = "The values that the user can choose from.")
    @EditorList.EdOptsList(editor = EditorBigDecimal.class,
            profilesEdit = MODE_SIMPLE_EXPERT)
    @EditorBigDecimal.EdOptsBigDecimal(dflt = 0)
    private List<BigDecimal> value;

    @ConfigurableField(editor = EditorList.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Intervals", description = "The intervals that the value must fall in.")
    @EditorList.EdOptsList(editor = EditorList.class,
            editorKey = "list-2",
            profilesEdit = MODE_SIMPLE_EXPERT)
    @EditorList.EdOptsList(editor = EditorBigDecimal.class,
            myKey = "list-2",
            minCount = 2, maxCount = 2, horizontal = true, labelText = "Interval:",
            profilesEdit = MODE_SIMPLE_EXPERT)
    @EditorBigDecimal.EdOptsBigDecimal(dflt = 0)
    private List<List<BigDecimal>> interval;

    @ConfigurableField(editor = EditorInt.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Significant Figures", description = "The number of significant figures.")
    @EditorInt.EdOptsInt(min = 0, max = 100, step = 1, dflt = 0)
    private Integer significantFigures;

    public List<BigDecimal> getValue() {
        return value;
    }

    public List<List<BigDecimal>> getInterval() {
        return interval;
    }

    public Integer getSignificantFigures() {
        return significantFigures;
    }

    public boolean isValid(BigDecimal input) {
        if (Utils.isNullOrEmpty(value) && Utils.isNullOrEmpty(interval) && significantFigures == 0) {
            // This constraint is empty
            return true;
        }
        if (value != null) {
            for (BigDecimal item : value) {
                if (item.compareTo(input) == 0) {
                    return true;
                }
            }
        }
        if (interval != null) {
            for (List<BigDecimal> range : interval) {
                if (range.get(0).compareTo(input) < 0 && range.get(1).compareTo(input) > 0) {
                    return true;
                }
            }
        }
        // TODO: validate significantFigues
        return false;
    }

    public void setValue(List<BigDecimal> value) {
        this.value = value;
    }

    public void setInterval(List<List<BigDecimal>> interval) {
        this.interval = interval;
    }

    public void setSignificantFigures(Integer significantFigures) {
        this.significantFigures = significantFigures;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.value);
        hash = 17 * hash + Objects.hashCode(this.interval);
        hash = 17 * hash + Objects.hashCode(this.significantFigures);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AllowedValues other = (AllowedValues) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.interval, other.interval)) {
            return false;
        }
        return Objects.equals(this.significantFigures, other.significantFigures);
    }
}
