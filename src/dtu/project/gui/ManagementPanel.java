/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.project.gui;

import dtu.project.app.ProjectApp;

/**
 *
 * @author Jonathan
 */
public class ManagementPanel extends PanelTemplate {

    /**
     * Creates new form ProjectPanel
     */
    public ManagementPanel() {
        initComponents();
        
    }
    
    @Override
    public void setup(ProjectApp PA, MainFrame MF) {
        projectPanel1.setup(PA, MF);
        activityPanel1.setup(PA, MF);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        projectPanel1 = new dtu.project.gui.ProjectPanel();
        activityPanel1 = new dtu.project.gui.ActivityPanel();

        jTabbedPane1.addTab("Project Panel", projectPanel1);
        jTabbedPane1.addTab("Activity Panel", activityPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private dtu.project.gui.ActivityPanel activityPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private dtu.project.gui.ProjectPanel projectPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void initFields() {
        projectPanel1.initFields();
        activityPanel1.initFields();
    }
}
