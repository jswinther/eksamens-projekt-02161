/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.project.gui;

import java.time.format.DateTimeParseException;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;

import dtu.project.entities.Activity;
import dtu.project.entities.Event;
import dtu.project.entities.TimePeriod;
import dtu.project.entities.User;

/**
 *
 * @author Jonathan
 */
public class SchedulePanel extends PanelTemplate {

    /**
     * Creates new form SchedulePanel
     */
    public SchedulePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectUserComboBox = new javax.swing.JComboBox<>();
        selectUserLabel = new javax.swing.JLabel();
        activityListLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        activityList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        messageLabel = new javax.swing.JLabel();
        startDateLabel = new javax.swing.JLabel();
        endDateLabel = new javax.swing.JLabel();
        startDateTextField = new javax.swing.JTextField();
        endDateTextField = new javax.swing.JTextField();
        addPeriodButton = new javax.swing.JButton();
        editPeriodButton = new javax.swing.JButton();
        removePeriodButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        scheduleList = new javax.swing.JList<>();

        selectUserComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        selectUserLabel.setText("Select User");

        activityListLabel.setText("Select Activity (Optional)");

        jScrollPane1.setViewportView(activityList);

        messageTextArea.setColumns(20);
        messageTextArea.setRows(5);
        jScrollPane2.setViewportView(messageTextArea);

        messageLabel.setText("Message (Optional)");

        startDateLabel.setText("Start Date");

        endDateLabel.setText("End Date");

        startDateTextField.setText("2019-05-05 13:13");

        endDateTextField.setText("2019-05-05 14:14");

        addPeriodButton.setText("add Period");
        addPeriodButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPeriodButtonActionPerformed(evt);
            }
        });

        editPeriodButton.setText("edit Period");
        editPeriodButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPeriodButtonActionPerformed(evt);
            }
        });

        removePeriodButton.setText("remove Period");
        removePeriodButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removePeriodButtonActionPerformed(evt);
            }
        });

        scheduleList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                scheduleListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(scheduleList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectUserComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectUserLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(activityListLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startDateLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(endDateLabel)
                            .addComponent(endDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(messageLabel)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPeriodButton)
                    .addComponent(editPeriodButton)
                    .addComponent(removePeriodButton))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectUserLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectUserComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(activityListLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addPeriodButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editPeriodButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removePeriodButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(messageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startDateLabel)
                            .addComponent(endDateLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addPeriodButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPeriodButtonActionPerformed
        try {
            User u = PG.getUser(selectUserComboBox.getSelectedIndex());
            PG.addHours(u,
                    startDateTextField.getText(),
                    endDateTextField.getText(),
                    activityList.isSelectionEmpty() ? null : PG.getActivitiesAssignedTo(u).get(activityList.getSelectedIndex()),
                    messageTextArea.getText());
        } catch (Exception e) {
            System.err.println(e);
        }

        MF.updateAll();

    }//GEN-LAST:event_addPeriodButtonActionPerformed

    private void editPeriodButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPeriodButtonActionPerformed
        try {
            if (!scheduleList.isSelectionEmpty()) {
                User u = PG.getUser(selectUserComboBox.getSelectedIndex());
                Event p = PG.getUserSchedule(selectUserComboBox.getSelectedIndex()).get(scheduleList.getSelectedIndex());
                p.setActivity(activityList.isSelectionEmpty() ? null : PG.getActivitiesAssignedTo(u).get(activityList.getSelectedIndex()));
                p.setMessage(messageTextArea.getText());
                p.setTimePeriod(new TimePeriod(startDateTextField.getText(), startDateTextField.getText()));
            }
        } catch (DateTimeParseException e) {
            System.err.println(e);
        }

        MF.updateAll();
    }//GEN-LAST:event_editPeriodButtonActionPerformed

    private void removePeriodButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removePeriodButtonActionPerformed
        try {
            if (!scheduleList.isSelectionEmpty()) {
            	PG.getUserSchedule(selectUserComboBox.getSelectedIndex()).remove(scheduleList.getSelectedIndex());
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        MF.updateAll();
    }//GEN-LAST:event_removePeriodButtonActionPerformed

    private void scheduleListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_scheduleListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_scheduleListValueChanged

    @Override
    public void initFields() {
        User u = PG.getUser(selectUserComboBox.getSelectedIndex());
        selectUserComboBox.setModel(PG.getUserDefaultComboBoxModel());
        if (PG.getActivitiesAssignedTo(u).isEmpty()) {
            activityList.setModel(new DefaultComboBoxModel<>(new String[]{"empty"}));
            activityList.setEnabled(false);
        } else {
            activityList.setModel(PG.getUserActivitiesDefaultListModelContaining(u.toString(), ""));
            activityList.setEnabled(true);
        }

        if (PG.getUserSchedule(selectUserComboBox.getSelectedIndex()).isEmpty()) {
            scheduleList.setModel(new DefaultComboBoxModel<>(new String[]{"empty"}));
            scheduleList.setEnabled(false);
        } else {
            scheduleList.setModel(PG.getUserScheduleDefaultListModelContaining(u.toString(), ""));
            scheduleList.setEnabled(true);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> activityList;
    private javax.swing.JLabel activityListLabel;
    private javax.swing.JButton addPeriodButton;
    private javax.swing.JButton editPeriodButton;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JTextField endDateTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JButton removePeriodButton;
    private javax.swing.JList<String> scheduleList;
    private javax.swing.JComboBox<String> selectUserComboBox;
    private javax.swing.JLabel selectUserLabel;
    private javax.swing.JLabel startDateLabel;
    private javax.swing.JTextField startDateTextField;
    // End of variables declaration//GEN-END:variables
}
