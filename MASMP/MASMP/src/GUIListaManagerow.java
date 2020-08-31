import javax.swing.*;
import java.util.List;

/**
 *
 * @author AdekObiadek
 */
public class GUIListaManagerow extends javax.swing.JFrame {

    /**
     * Creates new form Test
     */
    public ObiektSportowy obiektSportowy;
    /**
     * Creates new form ListaManagerow
     */
    public GUIListaManagerow() {
        initComponents();
    }

    public GUIListaManagerow(ObiektSportowy obiektSportowy) {
        this.obiektSportowy = obiektSportowy;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListListaManagerow = new javax.swing.JList < Manager > ();
        jLabelManagerowie = new javax.swing.JLabel();
        jButtonExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lista managerów");
        setLocationByPlatform(true);
        setResizable(false);

        jListListaManagerow.setModel(new javax.swing.AbstractListModel < Manager > () {
            List list = obiektSportowy.getManagerList();
            public int getSize() {
                return list.size();
            }
            public Manager getElementAt(int i) {
                return (Manager) list.get(i);
            }
        });
        jScrollPane1.setViewportView(jListListaManagerow);

        jLabelManagerowie.setText("Lista managerów placówki " + obiektSportowy);

        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(78, 78, 78)
                                                .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabelManagerowie)))
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelManagerowie)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonExit)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    } // </editor-fold>

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {
        setVisible(false);
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButtonExit;
    private javax.swing.JLabel jLabelManagerowie;
    private JList < Manager > jListListaManagerow;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration
}