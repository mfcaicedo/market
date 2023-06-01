/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package co.unicauca.openmarket.client.presentation;

import co.unicauca.openmarket.client.domain.Category;
import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.services.ProductService;
import co.unicauca.openmarket.client.infra.Messages;
import static co.unicauca.openmarket.client.infra.Messages.successMessage;
import javax.swing.JOptionPane;
/**
 *
 * @author ALEXA
 */
public class GUIProducts extends javax.swing.JFrame {

    private ProductService productService;
    private boolean addOption;

    /**
     * Creates new form GUIProducts
     */
    public GUIProducts(ProductService productService) {
        initComponents();
        this.productService = productService;
        stateInitial();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSouth = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtCategory = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();

        pnlSouth.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlSouth.add(btnNuevo);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        pnlSouth.add(btnEditar);

        btnSave.setText("Grabar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        pnlSouth.add(btnSave);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlSouth.add(btnCancelar);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        pnlSouth.add(btnEliminar);

        btnFind.setText("Buscar");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        pnlSouth.add(btnFind);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        pnlSouth.add(btnCerrar);

        getContentPane().add(pnlSouth, java.awt.BorderLayout.SOUTH);

        pnlCenter.setBackground(new java.awt.Color(0, 102, 102));
        pnlCenter.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblId.setBackground(new java.awt.Color(255, 255, 255));
        lblId.setForeground(new java.awt.Color(255, 255, 255));
        lblId.setText("Digite ID del producto :");
        lblId.setName(""); // NOI18N

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre :");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Descripcion :");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ID Categoria :");

        txtCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoryActionPerformed(evt);
            }
        });

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("PRODUCTO");

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))))
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblId)))
                .addGap(33, 33, 33)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel8)
                .addGap(48, 48, 48)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        stateNew();
        txtName.requestFocus();
        addOption = true;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        addOption = false;
        stateEdit();
        txtId.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (txtName.getText().trim().equals("")) {
            Messages.showMessageDialog("Debe ingresar el nombre del producto", "Atención");
            txtName.requestFocus();
            return;
        }
        if (addOption) {
            //Agregar
            addProduct();

        } else {
            //Editar
            editProduct();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        stateInitial();
        cleanControls();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String id = txtId.getText().trim();
        if (id.equals("")) {
            Messages.showMessageDialog("Debe buscar el producto a eliminar", "Atención");
            txtId.requestFocus();
            return;
        }
        Long productId = Long.parseLong(id);
        if (Messages.showConfirmDialog("Está seguro que desea eliminar este producto?", "Confirmación") == JOptionPane.YES_NO_OPTION) {
            if (productService.deleteProduct(productId)) {
                Messages.showMessageDialog("Producto eliminado con éxito", "Atención");
                stateInitial();
                cleanControls();
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        GUIProductsFind instance = new GUIProductsFind(this, true, productService);
        instance.setVisible(true);
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryActionPerformed

    private void stateEdit() {
        btnNuevo.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(true);
        btnCancelar.setVisible(true);
        btnCerrar.setVisible(false);
        btnSave.setVisible(true);
        btnFind.setVisible(false);
        txtId.setEnabled(true);
        txtName.setEnabled(true);
        txtDescription.setEnabled(true);
        txtCategory.setEnabled(true);
    }
    private void stateInitial() {
        btnNuevo.setVisible(true);
        btnEditar.setVisible(true);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(false);
        btnCerrar.setVisible(true);
        btnSave.setVisible(false);
        btnFind.setVisible(true);
        txtId.setEnabled(false);
        txtName.setEnabled(false);
        txtDescription.setEnabled(false);
        txtCategory.setEnabled(false);

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblId;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlSouth;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
    private void stateNew() {
        btnNuevo.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(true);
        btnCerrar.setVisible(false);
        btnSave.setVisible(true);
        btnFind.setVisible(false);
        txtId.setEnabled(true);
        txtName.setEnabled(true);
        txtDescription.setEnabled(true);
        txtCategory.setEnabled(true);

    }

    private void cleanControls() {
        txtId.setText("");
        txtName.setText("");
        txtDescription.setText("");
        txtCategory.setText("");
        
    }

    private void addProduct() {
        try{
            Long id=Long.parseLong(this.txtId.getText());
            String name = txtName.getText().trim();
            String description = txtDescription.getText().trim();
            Long categoryId = Long.parseLong(this.txtCategory.getText());
            if (productService.saveProduct(id,name, description, categoryId)) {
                Messages.showMessageDialog("Se grabó con éxito", "Atención");
                cleanControls();
                stateInitial();
            } else {
                Messages.showMessageDialog("Error al grabar, lo siento mucho", "Atención");
            }
        }catch(Exception ex){
             successMessage(ex.getMessage(), "Atención");
        }
           
    }

    private void editProduct() {
        String id = txtId.getText().trim();
        if (id.equals("")) {
            Messages.showMessageDialog("Debe buscar el producto a editar", "Atención");
            txtId.requestFocus();
            return;
        }
        Long productId = Long.parseLong(id);
        String name=txtName.getText();
        String description=this.txtDescription.getText();
        Long categoryId=Long.parseLong(this.txtCategory.getText());
        try{
             if (productService.editProduct(productId,name,description,categoryId)) {
                Messages.showMessageDialog("Se editó con éxito", "Atención");
                cleanControls();
                stateInitial();
            } else {
                Messages.showMessageDialog("Error al editar, lo siento mucho", "Atención");
            }
        }catch(Exception ex){
            successMessage(ex.getMessage(), "Atención");
        }
            
    }
}

