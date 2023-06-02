/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package co.unicauca.openmarket.client.presentation;

import co.unicauca.openmarket.client.domain.Category;
import co.unicauca.openmarket.client.domain.Location;
import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.User;
import co.unicauca.openmarket.client.domain.services.CategoryService;
import co.unicauca.openmarket.client.domain.services.LocationService;
import co.unicauca.openmarket.client.domain.services.ProductService;
import co.unicauca.openmarket.client.infra.Messages;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Personal
 */
public class JDialogAddProduct extends javax.swing.JDialog {

    private CategoryService categoryService;
    private LocationService locationService;
    private ProductService productService;
    private Product productUpdate;
    private User userLogin;
    
    JComboBox<String> comboBoxCategorys;
    List<Location> lstLocation;
    List<Category> lstCategory;

    /**
     * Creates new form JDialogAddProduct
     */
    public JDialogAddProduct(java.awt.Frame parent, boolean modal,
            CategoryService categoryService, LocationService locationService,
            User userLogin, ProductService productService, Product productUpdate) {
        super(parent, modal);
        initComponents();
        this.categoryService = categoryService;
        this.locationService = locationService;
        this.productService = productService;
        this.userLogin = userLogin;
        this.productUpdate= productUpdate;
        this.lstLocation= new ArrayList<>();
        this.lstCategory= new ArrayList<>();
        comboBoxCategory();
        comboBoxLocation();
        if(productUpdate!=null){
            setFieldsInit();
        }
    }

    private void comboBoxCategory() {
        this.lstCategory = this.categoryService.findAllCategories();

        DefaultComboBoxModel model = (DefaultComboBoxModel) this.jComboBoxCategory.getModel();
        this.jComboBoxCategory.setModel(model);

        for (Category category : lstCategory) {
            model.addElement(category.getName());
        }

    }
    
    private void comboBoxLocation() {
        this.lstLocation = this.locationService.findAll();
        DefaultComboBoxModel model = (DefaultComboBoxModel) this.jComboBoxLocation.getModel();
        this.jComboBoxLocation.setModel(model);

        for (Location location : lstLocation) {
            model.addElement(location.getPlace());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldDescrip = new javax.swing.JTextField();
        jTextFieldPrice = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldStock = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxCategory = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButtonAgregar = new javax.swing.JButton();
        jComboBoxLocation = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("FORMULARIO DE PRODUCTO");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setText("Categoria:");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setText("Ubicacion:");

        jTextFieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("Nombre: ");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setText("Descripcion:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setText("Precio:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setText("Stock:");

        jButtonAgregar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonAgregar.setText("Guardar producto");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jComboBoxLocation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldPrice)
                            .addComponent(jTextFieldStock)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldDescrip)
                            .addComponent(jTextFieldName)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jButtonAgregar)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(jTextFieldDescrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonAgregar)
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNameActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        if(productUpdate!=null){
            try {
                updateProduct();
                
            } catch (Exception ex) {
                Logger.getLogger(JDialogAddProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
            createProduct();
        } catch (Exception ex) {
            Logger.getLogger(JDialogAddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        

    }//GEN-LAST:event_jButtonAgregarActionPerformed
    
    private void setFieldsInit(){
        this.jTextFieldName.setText(productUpdate.getName());
        
        this.jTextFieldDescrip.setText(productUpdate.getDescription());
        this.jTextFieldPrice.setText(productUpdate.getPrice().toString());
        this.jTextFieldStock.setText(productUpdate.getStock().toString());
        
        this.jTextFieldName.setEditable(false);
        this.jTextFieldDescrip.setEditable(false);
        this.jTextFieldPrice.setEditable(false);
        this.jComboBoxCategory.setEnabled(false);
        this.jComboBoxLocation.setEnabled(false);
        
    }
    
    private void updateProduct() throws Exception{
        
        int newStock = Integer.parseInt(this.jTextFieldStock.getText());
        this.productUpdate.setStock(newStock);
        
        if(this.productService.editProduct(productUpdate)){
            Messages.showMessageDialog("Producto Actualizado exitosamente", "Atención");
            this.dispose();
        }else{
            Messages.showMessageDialog("ERROR, Verifica informacion", "Atención");
        }
        
        
    }
    
    
    
    private void createProduct() throws Exception{
        Product newProduct = new Product();
        newProduct.setName(this.jTextFieldName.getText());
        newProduct.setDescription(this.jTextFieldDescrip.getText());
        newProduct.setPrice(Double.parseDouble(this.jTextFieldPrice.getText()));
        newProduct.setState("Disponible");
        newProduct.setStock(Integer.parseInt(this.jTextFieldStock.getText()));
        
        int categoryPos = this.jComboBoxCategory.getSelectedIndex();
        newProduct.setCategoryId(this.lstCategory.get(categoryPos).getCategoryId());
        int locationPos = this.jComboBoxLocation.getSelectedIndex();
        newProduct.setLocation((long)this.lstLocation.get(locationPos).getLocationId());
        newProduct.setUserSellerId(userLogin.getUserId());
        
       
        if(this.productService.saveProduct(newProduct)){
            Messages.showMessageDialog("Producto Creado exitosamente", "Atención");
            this.dispose();
        }else{
            Messages.showMessageDialog("ERROR, Verifica informacion", "Atención");
        }
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JDialogAddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JDialogAddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JDialogAddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JDialogAddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                JDialogAddProduct dialog = new JDialogAddProduct(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JComboBox<String> jComboBoxCategory;
    private javax.swing.JComboBox<String> jComboBoxLocation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldDescrip;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPrice;
    private javax.swing.JTextField jTextFieldStock;
    // End of variables declaration//GEN-END:variables
}
