/*
 * Copyright (C) 2018 Fraunhofer IOSB.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see <http://www.gnu.org/licenses/>.
 */
package de.fraunhofer.iosb.ilt.swe.common.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.fraunhofer.iosb.ilt.configurable.ConfigEditor;
import de.fraunhofer.iosb.ilt.swe.common.AbstractSWE;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hylke van der Schaaf
 */
public class ExampleGui extends javax.swing.JFrame {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleGui.class.getName());
    private ConfigEditor editor;
    private ConfigEditor editorValues;

    /**
     * Creates new form ExampleGuiSwing
     */
    public ExampleGui() {
        initComponents();
        addEditorToGui();
    }

    private void addEditorToGui() {
        TaskingCapability taskingCapability = new TaskingCapability();
        editor = taskingCapability.getConfigEditor(null, null);
        toggledMode();
        panelEditor.add(editor.getGuiFactorySwing().getComponent(), BorderLayout.NORTH);
    }

    private void useConfig() {
        JsonElement config = editor.getConfig();
        TaskingCapability taskingCapability = new TaskingCapability();
        taskingCapability.configure(config, null, null);
    }

    private void printConfig() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement config = editor.getConfig();
        String jsonString = gson.toJson(config);
        jsonTextArea.setText(jsonString);
        LOGGER.info("Our configuration is:\n{}", jsonString);
    }

    public void loadConfig() {
        loadConfig(jsonTextArea.getText());
    }

    public void loadConfig(String jsonString) {
        JsonElement config = new JsonParser().parse(jsonString);
        editor.setConfig(config);
    }

    public void toggledMode() {
        // Ensure all fields are read-in, so no user-text is lost.
        editor.getConfig();
        if (jCheckBoxProfile.isSelected()) {
            editor.setProfile(AbstractSWE.MODE_SIMPLE);
        } else {
            editor.setProfile(AbstractSWE.MODE_EXPERT);
        }
    }

    public void copyEditorToValuesEditor() {
        JsonElement config = editor.getConfig();
        TaskingCapability taskingCapability = new TaskingCapability();
        editorValues = taskingCapability.getConfigEditor(null, null);
        editorValues.setProfile(AbstractSWE.MODE_VALUE);
        editorValues.setConfig(config);
        panelValueEditor.removeAll();
        panelValueEditor.add(editorValues.getGuiFactorySwing().getComponent(), BorderLayout.NORTH);
    }

    public void printValues() {
        if (editorValues == null) {
            return;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement config = editorValues.getConfig();

        TaskingCapability taskingCapability = new TaskingCapability();
        taskingCapability.configure(config, null, null);
        JsonElement valueJson = taskingCapability.getValueJson();

        String jsonString = gson.toJson(valueJson);
        jsonValueTextArea.setText(jsonString);
    }

    public void loadValues() {
        if (editorValues == null) {
            return;
        }
        JsonElement config = editorValues.getConfig();

        TaskingCapability taskingCapability = new TaskingCapability();
        taskingCapability.configure(config, null, null);
        JsonElement valueJson = new JsonParser().parse(jsonValueTextArea.getText());

        editorValues = taskingCapability.getConfigEditor(null, null);
        editorValues.setProfile(AbstractSWE.MODE_VALUE);
        panelValueEditor.removeAll();
        panelValueEditor.add(editorValues.getGuiFactorySwing().getComponent(), BorderLayout.NORTH);
        taskingCapability.setValueJson(valueJson);
    }

    public void validateValues() {
        if (editorValues == null) {
            return;
        }
        JsonElement config = editorValues.getConfig();
        TaskingCapability taskingCapability = new TaskingCapability();
        taskingCapability.configure(config, null, null);
        if (taskingCapability.valueIsValid()) {
            JOptionPane.showMessageDialog(this, "Everything checks out.");
        } else {
            JOptionPane.showMessageDialog(this, "Something is wrong.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonLoad = new javax.swing.JButton();
        jCheckBoxProfile = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelEditor = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        panelValueEditor = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSplitPane3 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jsonTextArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jsonValueTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SensorThings SWE-Common Example");

        jSplitPane1.setDividerLocation(500);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.5);

        jSplitPane2.setDividerLocation(450);
        jSplitPane2.setResizeWeight(0.5);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setText("To JSON ↓");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonSave, gridBagConstraints);

        jButtonLoad.setText("Load JSON ↑");
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonLoad, gridBagConstraints);

        jCheckBoxProfile.setSelected(true);
        jCheckBoxProfile.setText("Simple Mode");
        jCheckBoxProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxProfileActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        jPanel1.add(jCheckBoxProfile, gridBagConstraints);

        panelEditor.setLayout(new java.awt.BorderLayout());
        jScrollPane2.setViewportView(panelEditor);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane2, gridBagConstraints);

        jSplitPane2.setLeftComponent(jPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButton2.setText("Value ↓");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButton2, gridBagConstraints);

        panelValueEditor.setLayout(new java.awt.BorderLayout());
        jScrollPane3.setViewportView(panelValueEditor);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane3, gridBagConstraints);

        jButton3.setText("Structure ⤴");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButton3, gridBagConstraints);

        jButton4.setText("Value ↑");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButton4, gridBagConstraints);

        jButton1.setText("Validate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButton1, gridBagConstraints);

        jSplitPane2.setRightComponent(jPanel2);

        jSplitPane1.setTopComponent(jSplitPane2);

        jSplitPane3.setDividerLocation(450);
        jSplitPane3.setResizeWeight(0.5);

        jsonTextArea.setColumns(20);
        jsonTextArea.setRows(5);
        jScrollPane1.setViewportView(jsonTextArea);

        jSplitPane3.setLeftComponent(jScrollPane1);

        jsonValueTextArea.setColumns(20);
        jsonValueTextArea.setRows(5);
        jScrollPane4.setViewportView(jsonValueTextArea);

        jSplitPane3.setRightComponent(jScrollPane4);

        jSplitPane1.setRightComponent(jSplitPane3);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        printConfig();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        loadConfig();
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jCheckBoxProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxProfileActionPerformed
        toggledMode();
    }//GEN-LAST:event_jCheckBoxProfileActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        copyEditorToValuesEditor();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        printValues();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        loadValues();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        validateValues();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            LOGGER.error("", ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            ExampleGui gui = new ExampleGui();
            gui.setSize(900, 700);
            gui.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBoxProfile;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTextArea jsonTextArea;
    private javax.swing.JTextArea jsonValueTextArea;
    private javax.swing.JPanel panelEditor;
    private javax.swing.JPanel panelValueEditor;
    // End of variables declaration//GEN-END:variables
}
