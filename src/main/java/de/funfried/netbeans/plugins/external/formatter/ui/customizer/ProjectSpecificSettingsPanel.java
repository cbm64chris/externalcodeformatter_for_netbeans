/*
 * Copyright (c) 2013 markiewb.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * Contributors:
 * markiewb - initial API and implementation and/or initial documentation
 */
package de.funfried.netbeans.plugins.external.formatter.ui.customizer;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.prefs.Preferences;

import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

import org.netbeans.api.options.OptionsDisplayer;
import org.openide.util.NbBundle;

import de.funfried.netbeans.plugins.external.formatter.ui.options.ExternalFormatterPanel;
import de.funfried.netbeans.plugins.external.formatter.ui.options.Settings;

/**
 * {@link VerifiableConfigPanel} {@link JPanel} implementation for project
 * specific external formatting properties panel.
 *
 * @author markiewb
 * @author bahlef
 */
@NbBundle.Messages({ "ProjectSpecificSettingsPanel.cbOverrideGlobalSettings.text=Override global settings",
		"ProjectSpecificSettingsPanel.lblJumpToGlobalOptions.text=<html><a href=\"#\">Configure global options...</a>" })
public class ProjectSpecificSettingsPanel extends JPanel implements VerifiableConfigPanel {
	private static final long serialVersionUID = 1L;

	private transient final Preferences projectPreferences;

	private final ExternalFormatterPanel innerComponent;

	ProjectSpecificSettingsPanel(ExternalFormatterPanel innerComponent, Preferences projectPreferences) {
		initComponents();
		this.innerComponent = innerComponent;
		this.projectPreferences = projectPreferences;

		innerPanel.setLayout(new BorderLayout());
		innerPanel.add(this.innerComponent, BorderLayout.CENTER);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        scrollContainer = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        cbOverrideGlobalSettings = new javax.swing.JCheckBox();
        lblJumpToGlobalOptions = new javax.swing.JLabel();
        innerPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        scrollPane.setPreferredSize(new java.awt.Dimension(100, 200));

        scrollContainer.setLayout(new java.awt.BorderLayout());

        headerPanel.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(cbOverrideGlobalSettings, org.openide.util.NbBundle.getMessage(ProjectSpecificSettingsPanel.class, "ProjectSpecificSettingsPanel.cbOverrideGlobalSettings.text")); // NOI18N
        cbOverrideGlobalSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOverrideGlobalSettingsActionPerformed(evt);
            }
        });
        headerPanel.add(cbOverrideGlobalSettings, java.awt.BorderLayout.WEST);

        lblJumpToGlobalOptions.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblJumpToGlobalOptions, org.openide.util.NbBundle.getMessage(ProjectSpecificSettingsPanel.class, "ProjectSpecificSettingsPanel.lblJumpToGlobalOptions.text")); // NOI18N
        lblJumpToGlobalOptions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblJumpToGlobalOptionsMouseClicked(evt);
            }
        });
        headerPanel.add(lblJumpToGlobalOptions, java.awt.BorderLayout.CENTER);

        scrollContainer.add(headerPanel, java.awt.BorderLayout.NORTH);
        scrollContainer.add(innerPanel, java.awt.BorderLayout.CENTER);

        scrollPane.setViewportView(scrollContainer);

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

	private void cbOverrideGlobalSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOverrideGlobalSettingsActionPerformed
		innerPanel.setVisible(cbOverrideGlobalSettings.isSelected());
		fireChangedListener();
	}//GEN-LAST:event_cbOverrideGlobalSettingsActionPerformed

	private void lblJumpToGlobalOptionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJumpToGlobalOptionsMouseClicked
		OptionsDisplayer.getDefault().open("Java" + "/de.funfried.netbeans.plugins.external.formatter.ui.options");
	}//GEN-LAST:event_lblJumpToGlobalOptionsMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbOverrideGlobalSettings;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel innerPanel;
    private javax.swing.JLabel lblJumpToGlobalOptions;
    private javax.swing.JPanel scrollContainer;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load() {
		boolean useProjectSettings = projectPreferences.getBoolean(Settings.USE_PROJECT_SETTINGS, false);
		cbOverrideGlobalSettings.setSelected(useProjectSettings);
		innerPanel.setVisible(useProjectSettings);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void store() {
		projectPreferences.putBoolean(Settings.USE_PROJECT_SETTINGS, cbOverrideGlobalSettings.isSelected());
	}

	private transient final Collection<ChangeListener> changeListeners = new ArrayList<>();

	/**
	 * Adds a {@link ChangeListener} to this {@link ProjectSpecificSettingsPanel}.
	 *
	 * @param listener the {@link ChangeListener}
	 */
	public void addChangeListener(ChangeListener listener) {
		changeListeners.add(listener);
	}

	private void fireChangedListener() {
		for (ChangeListener changeListener : changeListeners) {
			changeListener.stateChanged(null);
		}
	}

	/**
	 * Removes a {@link ChangeListener} to this {@link ProjectSpecificSettingsPanel}.
	 *
	 * @param listener the {@link ChangeListener}
	 */
	public void removeChangeListener(ChangeListener listener) {
		changeListeners.remove(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid() {
		boolean result = true;
		if (cbOverrideGlobalSettings.isSelected()) {
			return innerComponent.valid();
		}

		return result;
	}
}
