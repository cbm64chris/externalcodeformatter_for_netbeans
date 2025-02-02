/*
 * Copyright (c) 2020 bahlef.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * Contributors:
 * bahlef - initial API and implementation and/or initial documentation
 */

package de.funfried.netbeans.plugins.external.formatter.javascript.eclipse.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.lang3.StringUtils;
import org.netbeans.api.project.Project;
import org.openide.awt.Mnemonics;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.util.NbBundle;
import org.xml.sax.SAXException;

import de.funfried.netbeans.plugins.external.formatter.eclipse.xml.ConfigReader;
import de.funfried.netbeans.plugins.external.formatter.exceptions.ConfigReadException;
import de.funfried.netbeans.plugins.external.formatter.javascript.eclipse.EclipseJavascriptFormatterSettings;
import de.funfried.netbeans.plugins.external.formatter.ui.options.AbstractFormatterOptionsPanel;

/**
 *
 * @author bahlef
 */
public class EclipseJavascriptFormatterOptionsPanel extends AbstractFormatterOptionsPanel {
	/** {@link Logger} of this class. */
	private static final Logger log = Logger.getLogger(EclipseJavascriptFormatterOptionsPanel.class.getName());

	/**
	 * Creates new form {@link EclipseJavascriptFormatterOptionsPanel}.
	 *
	 * @param project the {@link Project} if the panel is used to modify project
	 *        specific settings, otherwise {@code null}
	 */
	public EclipseJavascriptFormatterOptionsPanel(Project project) {
		super(project);

		initComponents();

		if (project != null) {
			lblFormatterFile.setToolTipText(NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.lblFormatterFile.toolTipText.projectSpecific"));
			browseButton.setToolTipText(NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.browseButton.toolTipText.projectSpecific"));
		}
	}

	/**
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFormatterFile = new JLabel();
        formatterLocField = new JTextField();
        browseButton = new JButton();
        errorLabel = new JLabel();
        jLabel2 = new JLabel();
        cbProfile = new JComboBox<>();
        lblProfile = new JLabel();
        cbUseProjectPref = new JCheckBox();
        lblLinefeed = new JLabel();
        cbLinefeed = new JComboBox<>();

        lblFormatterFile.setHorizontalAlignment(SwingConstants.RIGHT);
        Mnemonics.setLocalizedText(lblFormatterFile, NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.lblFormatterFile.text")); // NOI18N
        lblFormatterFile.setToolTipText(NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.lblFormatterFile.toolTipText")); // NOI18N

        formatterLocField.setText(NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.formatterLocField.text")); // NOI18N
        formatterLocField.setPreferredSize(formatterLocField.getMinimumSize());
        formatterLocField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent evt) {
                formatterLocFieldFocusLost(evt);
            }
        });
        formatterLocField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                formatterLocFieldActionPerformed(evt);
            }
        });

        Mnemonics.setLocalizedText(browseButton, NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.browseButton.text")); // NOI18N
        browseButton.setToolTipText(NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.browseButton.toolTipText")); // NOI18N
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        errorLabel.setForeground(new Color(255, 51, 51));
        Mnemonics.setLocalizedText(errorLabel, NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.errorLabel.text")); // NOI18N

        Mnemonics.setLocalizedText(jLabel2, NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.jLabel2.text")); // NOI18N
        jLabel2.setEnabled(false);

        cbProfile.setEnabled(false);
        cbProfile.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                cbProfileItemStateChanged(evt);
            }
        });

        lblProfile.setHorizontalAlignment(SwingConstants.RIGHT);
        Mnemonics.setLocalizedText(lblProfile, NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.lblProfile.text")); // NOI18N
        lblProfile.setToolTipText(NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.lblProfile.toolTipText")); // NOI18N

        cbUseProjectPref.setSelected(true);
        Mnemonics.setLocalizedText(cbUseProjectPref, NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.cbUseProjectPref.text")); // NOI18N
        cbUseProjectPref.setToolTipText(NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.cbUseProjectPref.toolTipText")); // NOI18N

        lblLinefeed.setHorizontalAlignment(SwingConstants.RIGHT);
        Mnemonics.setLocalizedText(lblLinefeed, NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.lblLinefeed.text")); // NOI18N
        lblLinefeed.setToolTipText(NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.lblLinefeed.toolTipText")); // NOI18N

        cbLinefeed.setModel(new DefaultComboBoxModel<>(new String[] { "System", "\\n", "\\r\\n", "\\r" }));
        cbLinefeed.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                cbLinefeedItemStateChanged(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFormatterFile)
                    .addComponent(lblProfile, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLinefeed, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(errorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(formatterLocField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(cbLinefeed, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbUseProjectPref)
                            .addComponent(cbProfile, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFormatterFile)
                    .addComponent(formatterLocField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cbProfile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProfile))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbUseProjectPref)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLinefeed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLinefeed))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        formatterLocField.getDocument().addDocumentListener(new DocumentListener() {
            /**
            * {@inheritDoc}
            */
            @Override
            public void insertUpdate(DocumentEvent e) {
                fireChangedListener();
            }

            /**
            * {@inheritDoc}
            */
            @Override
            public void removeUpdate(DocumentEvent e) {
                fireChangedListener();
            }

            /**
            * {@inheritDoc}
            */
            @Override
            public void changedUpdate(DocumentEvent e) {
                fireChangedListener();
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void formatterLocFieldActionPerformed(ActionEvent evt) {//GEN-FIRST:event_formatterLocFieldActionPerformed
		loadEclipseFormatterFileForPreview(formatterLocField.getText(), getSelectedProfile());
		fireChangedListener();
    }//GEN-LAST:event_formatterLocFieldActionPerformed

    private void browseButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
		// The default dir to use if no value is stored
		File home = new File(System.getProperty("user.home"));
		String formatterFilePath = formatterLocField.getText();
		if (StringUtils.isNotBlank(formatterFilePath)) {
			File formatterFile = new File(formatterFilePath);
			if (formatterFile.canRead()) {
				home = formatterFile;
			}
		}

		final FileNameExtensionFilter fileNameExtensionFilterXML = new FileNameExtensionFilter("Eclipse formatter (*.xml)", "xml");
		final FileNameExtensionFilter fileNameExtensionFilterEPF = new FileNameExtensionFilter("Workspace mechanic (*.epf)", "epf");
		final FileFilter fileNameExtensionFilterProjectSetting = new FileFilter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}
				return EclipseJavascriptFormatterSettings.PROJECT_PREF_FILE.equals(f.getName());
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public String getDescription() {
				return "Eclipse project settings (" + EclipseJavascriptFormatterSettings.PROJECT_PREF_FILE + ")";
			}
		};
		//Now build a file chooser and invoke the dialog in one line of code
		//"user-dir" is our unique key
		File toAdd = new FileChooserBuilder("user-dir").setFileHiding(false).setFilesOnly(true).setTitle("Choose configuration ...").setDefaultWorkingDirectory(home).setApproveText("Choose")
		  .addFileFilter(fileNameExtensionFilterProjectSetting).addFileFilter(fileNameExtensionFilterXML).addFileFilter(fileNameExtensionFilterEPF).setFileFilter(fileNameExtensionFilterXML)
		  .showOpenDialog();
		//Result will be null if the user clicked cancel or closed the dialog w/o OK
		if (toAdd != null) {
			loadEclipseFormatterFileForPreview(toAdd.getAbsolutePath(), getSelectedProfile());
			fireChangedListener();
		}
    }//GEN-LAST:event_browseButtonActionPerformed

    private void cbProfileItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_cbProfileItemStateChanged
		if (ItemEvent.SELECTED == evt.getStateChange()) {
			cbProfile.setToolTipText(Objects.toString(cbProfile.getSelectedItem(), null));

			fireChangedListener();
		}
    }//GEN-LAST:event_cbProfileItemStateChanged

    private void cbLinefeedItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_cbLinefeedItemStateChanged
		if (ItemEvent.SELECTED == evt.getStateChange()) {
			cbLinefeed.setToolTipText(Objects.toString(cbLinefeed.getSelectedItem(), null));

			fireChangedListener();
		}
    }//GEN-LAST:event_cbLinefeedItemStateChanged

    private void formatterLocFieldFocusLost(FocusEvent evt) {//GEN-FIRST:event_formatterLocFieldFocusLost
		loadEclipseFormatterFileForPreview(formatterLocField.getText(), getSelectedProfile());

		if (!valid()) {
			fireChangedListener();
		}
    }//GEN-LAST:event_formatterLocFieldFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton browseButton;
    private JComboBox<String> cbLinefeed;
    private JComboBox<String> cbProfile;
    private JCheckBox cbUseProjectPref;
    private JLabel errorLabel;
    private JTextField formatterLocField;
    private JLabel jLabel2;
    private JLabel lblFormatterFile;
    private JLabel lblLinefeed;
    private JLabel lblProfile;
    // End of variables declaration//GEN-END:variables

	private String getLinefeed() {
		if (0 == cbLinefeed.getSelectedIndex()) {
			return "";
		}
		return cbLinefeed.getSelectedItem().toString();
	}

	/**
	 * Returns the selected Eclipse formatter profile.
	 *
	 * @return the selected Eclipse formatter profile
	 */
	private String getSelectedProfile() {
		if (null != cbProfile.getSelectedItem()) {
			return cbProfile.getSelectedItem().toString();
		} else {
			return "";
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load(Preferences preferences) {
		String eclipseFormatterLocation = preferences.get(EclipseJavascriptFormatterSettings.ECLIPSE_FORMATTER_CONFIG_FILE_LOCATION, "");
		String eclipseFormatterProfile = preferences.get(EclipseJavascriptFormatterSettings.ECLIPSE_FORMATTER_ACTIVE_PROFILE, "");
		boolean useProjectPrefs = preferences.getBoolean(EclipseJavascriptFormatterSettings.USE_PROJECT_PREFS, true);
		String eclipseLineFeed = preferences.get(EclipseJavascriptFormatterSettings.LINEFEED, "");

		loadEclipseFormatterFileForPreview(eclipseFormatterLocation, eclipseFormatterProfile);

		cbUseProjectPref.setSelected(useProjectPrefs);

		if (StringUtils.isBlank(eclipseLineFeed)) {
			//default = system-dependend LF
			cbLinefeed.setSelectedIndex(0);
		} else {
			cbLinefeed.setSelectedItem(eclipseLineFeed);
		}

		cbLinefeed.setToolTipText(Objects.toString(cbLinefeed.getSelectedItem(), null));
	}

	private void loadEclipseFormatterFileForPreview(String formatterFile, String activeProfile) {
		String filePath;

		try {
			URL url = new URL(formatterFile);

			filePath = url.toString();
		} catch (IOException ex) {
			Path formatterFilePath = Paths.get(formatterFile);
			if (!formatterFilePath.isAbsolute() && project != null) {
				formatterFilePath = Paths.get(project.getProjectDirectory().getPath()).resolve(formatterFilePath);
			}

			filePath = formatterFilePath.toAbsolutePath().toString();
		}

		formatterLocField.setText(formatterFile);

		cbProfile.setEnabled(false);

		cbProfile.removeAllItems();

		try {
			//only xml configurations contain profiles
			if (EclipseJavascriptFormatterSettings.isXMLConfigurationFile(filePath)) {
				List<String> profileNames = ConfigReader.getProfileNames(ConfigReader.readContentFromFilePath(filePath));
				cbProfile.addItem(NbBundle.getMessage(EclipseJavascriptFormatterOptionsPanel.class, "EclipseJavascriptFormatterOptionsPanel.chooseProfile"));

				String entryToSelect = null;
				for (String profileName : profileNames) {
					cbProfile.addItem(profileName);
					if (activeProfile != null && activeProfile.equals(profileName)) {
						entryToSelect = profileName;
					}
				}
				selectProfileOrFallback(entryToSelect, profileNames);
				cbProfile.setEnabled(true);
			}

			formatterLocField.setToolTipText(filePath);
		} catch (IOException | SAXException | ConfigReadException ex) {
			log.log(Level.INFO, "Could not parse formatter config", ex);
		}
	}

	private void selectProfileOrFallback(String entryToSelect, List<String> profiles) {
		if (null != entryToSelect) {
			cbProfile.setSelectedItem(entryToSelect);
		} else if (profiles.size() == 1) {
			//only one entry (excl. default) -> choose the only valid item
			cbProfile.setSelectedIndex(1);
		} else {
			//fallback: ===choose profile==
			cbProfile.setSelectedIndex(0);
		}

		cbProfile.setToolTipText(Objects.toString(cbProfile.getSelectedItem(), null));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void store(Preferences preferences) {
		preferences.put(EclipseJavascriptFormatterSettings.ECLIPSE_FORMATTER_CONFIG_FILE_LOCATION, formatterLocField.getText());
		preferences.put(EclipseJavascriptFormatterSettings.ECLIPSE_FORMATTER_ACTIVE_PROFILE, getSelectedProfile());
		preferences.putBoolean(EclipseJavascriptFormatterSettings.USE_PROJECT_PREFS, cbUseProjectPref.isSelected());
		preferences.put(EclipseJavascriptFormatterSettings.LINEFEED, getLinefeed());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid() {
		errorLabel.setText("");

		String fileName = formatterLocField.getText();
		if (StringUtils.isBlank(fileName) && cbUseProjectPref.isSelected()) {
			// use configuration from .settings
			return true;
		}

		boolean fileExists;
		try {
			new URL(fileName);

			fileExists = true;
		} catch (IOException ex) {
			fileExists = new File(fileName).exists();
		}

		boolean isXML = EclipseJavascriptFormatterSettings.isXMLConfigurationFile(fileName);
		boolean isEPF = EclipseJavascriptFormatterSettings.isWorkspaceMechanicFile(fileName);
		boolean isProjectSetting = EclipseJavascriptFormatterSettings.isProjectSetting(fileName);

		if (!fileExists || (!isXML && !isEPF && !isProjectSetting) || cbProfile.getSelectedIndex() < 0) {
			errorLabel.setText("Invalid file. Please enter a valid configuration file.");
			return false;
		} else {
			return !isXML || cbProfile.getSelectedIndex() != 0;
		}
	}
}
