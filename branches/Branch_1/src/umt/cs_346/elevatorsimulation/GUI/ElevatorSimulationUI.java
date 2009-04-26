/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ElevatorSimulationUI.java
 *
 * Created on Apr 17, 2009, 9:21:07 PM
 */

package umt.cs_346.elevatorsimulation.GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import umt.cs_346.elevatorsimulation.elevator.ElevatorList;
import umt.cs_346.elevatorsimulation.tabs.*;
import umt.cs_346.elevatorsimulation.constants.Constants;


/**
 *
 * @author Chris Hanshew
 */
public class ElevatorSimulationUI extends javax.swing.JFrame {

    private String action;

    /** Creates new form ElevatorSimulationUI */
    public ElevatorSimulationUI(ElevatorList elevators) {
        initComponents();
        this.setTitle(Constants.TITLE);
        this.setVisible(true);
    }
    public void addGUIComponents(ElevatorList elevators){
        if(tabbedPane.getComponentCount() == 0){
            tabbedPane.add("Elevators", new ElevatorTab(elevators));
            addFloorButtons(elevators.get(0).getFloors());
            addMaintenceButtons(elevators.size());
        }else{
            tabbedPane.remove(0);
            repaint();
            addGUIComponents(elevators);
        }
    }
  
    private void addFloorButtons(int buttonCount){
        JButton button = null;
        floorButtonPanel.setLayout(Constants.GUI_BUTTON_LAYOUT);
        for(int i = 0; i < buttonCount; i++){
            button = new JButton();
            //button.setPreferredSize(new Dimension(50, 30));
            button.setText(Integer.valueOf(i + 1).toString());
            button.addActionListener(new floorButtonListener());
            floorButtonPanel.add(button);
        }
    }

    private void addMaintenceButtons(int buttonCount){
        JButton button = null;
        maintenceButtonPanel.setLayout(Constants.GUI_BUTTON_LAYOUT);
         for(int i = 0; i < buttonCount; i++){
            button = new JButton();
            //button.setPreferredSize(new Dimension(50, 30));
            button.setText(Integer.valueOf(i + 1).toString());
            button.addActionListener(new maintenceButtonListener());
            maintenceButtonPanel.add(button);
        }
    }
    
    public String getAction(){
        return action;
    }
    public void resetAction(){
        action = null;
    }
    public void consoleAppend(String s){
        String previous = consoleOut.getText() + "\n";
        consoleOut.setText(previous + Constants.currentTime() + ": " +  s);
        //console.repaint();
    }
    public void consoleExecute(String s){

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        lblSimParam = new javax.swing.JLabel();
        lblNumElevators = new javax.swing.JLabel();
        fldNumElevators = new javax.swing.JTextField();
        lblNumFloors = new javax.swing.JLabel();
        fldNumFloors = new javax.swing.JTextField();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        lblFloorButtons = new javax.swing.JLabel();
        floorButtonPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        consoleOut = new javax.swing.JTextPane();
        console = new javax.swing.JTextField();
        lblMaintence = new javax.swing.JLabel();
        maintenceButtonPanel = new javax.swing.JPanel();
        btnSetParameters = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblSimParam.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblSimParam.setText("Simulation Parameters");

        lblNumElevators.setText("Number of Elevators:");

        lblNumFloors.setText("Number of Floors:");

        fldNumFloors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldNumFloorsActionPerformed(evt);
            }
        });

        btnStart.setText("Start");
        btnStart.setMaximumSize(new java.awt.Dimension(55, 23));
        btnStart.setMinimumSize(new java.awt.Dimension(55, 23));
        btnStart.setPreferredSize(new java.awt.Dimension(55, 23));
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        lblFloorButtons.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFloorButtons.setText("Floor Buttons");

        floorButtonPanel.setDoubleBuffered(false);
        floorButtonPanel.setPreferredSize(new java.awt.Dimension(80, 418));

        javax.swing.GroupLayout floorButtonPanelLayout = new javax.swing.GroupLayout(floorButtonPanel);
        floorButtonPanel.setLayout(floorButtonPanelLayout);
        floorButtonPanelLayout.setHorizontalGroup(
            floorButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );
        floorButtonPanelLayout.setVerticalGroup(
            floorButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(consoleOut);

        console.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                consoleCommand(evt);
            }
        });

        lblMaintence.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMaintence.setText("Maintenence");

        maintenceButtonPanel.setPreferredSize(new java.awt.Dimension(80, 418));

        javax.swing.GroupLayout maintenceButtonPanelLayout = new javax.swing.GroupLayout(maintenceButtonPanel);
        maintenceButtonPanel.setLayout(maintenceButtonPanelLayout);
        maintenceButtonPanelLayout.setHorizontalGroup(
            maintenceButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        maintenceButtonPanelLayout.setVerticalGroup(
            maintenceButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        btnSetParameters.setText("Set Parameters");
        btnSetParameters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetParametersActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel1.setText("Simulation Control");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSimParam)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumElevators)
                            .addComponent(lblNumFloors))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fldNumFloors)
                            .addComponent(fldNumElevators, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(floorButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(lblFloorButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maintenceButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaintence)))
                    .addComponent(btnSetParameters))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                    .addComponent(console, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(console, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSimParam)
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumElevators)
                            .addComponent(fldNumElevators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumFloors)
                            .addComponent(fldNumFloors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSetParameters)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFloorButtons)
                            .addComponent(lblMaintence, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maintenceButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                            .addComponent(floorButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fldNumFloorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldNumFloorsActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fldNumFloorsActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        action = "stop";
}//GEN-LAST:event_btnStopActionPerformed

    private void consoleCommand(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consoleCommand
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            action = console.getText();
            console.setText("");
        }
        
    }//GEN-LAST:event_consoleCommand

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        action = "start";
}//GEN-LAST:event_btnStartActionPerformed

    private void btnSetParametersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetParametersActionPerformed
         action = "set " + "-" + fldNumElevators.getText() + "-" + fldNumFloors.getText();
    }//GEN-LAST:event_btnSetParametersActionPerformed

    public class floorButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();
            action = "request -" + button.getText();
        }
    }
    public class maintenceButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();
            action = "maintenance -" + button.getText();
        }
    }
    /**
    * @param args the command line arguments
    */
    /*
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ElevatorSimulationUI().setVisible(true);
            }
        });
    }
    */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSetParameters;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JTextField console;
    private javax.swing.JTextPane consoleOut;
    private javax.swing.JTextField fldNumElevators;
    private javax.swing.JTextField fldNumFloors;
    private javax.swing.JPanel floorButtonPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFloorButtons;
    private javax.swing.JLabel lblMaintence;
    private javax.swing.JLabel lblNumElevators;
    private javax.swing.JLabel lblNumFloors;
    private javax.swing.JLabel lblSimParam;
    private javax.swing.JPanel maintenceButtonPanel;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

}
