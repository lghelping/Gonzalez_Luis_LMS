/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 * The GUI package contains classes related to the graphical user interface of the library management system.
 */

package GUI;

import javax.swing.table.DefaultTableModel;
import library_managament_system.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import library_managament_system.DatabaseConnection;
import javax.swing.JOptionPane;


/**
 * The Borrow_book class represents the graphical user interface for borrowing books
 * in the library management system. Users can enter the book barcode and choose
 * the due date for borrowing a book. The class interacts with the database to check
 * the availability of the book, update its status, and display appropriate messages
 * based on the outcomes.
 * <p>
 * This class includes methods for updating the status and due date of a book, as well
 * as handling user actions such as clicking the "Borrow Book" button or navigating
 * back to the home screen.
 * <p>
 * The GUI components include text fields for entering the book barcode, a date chooser
 * for selecting the due date, and buttons for updating the due date and returning to
 * the home screen.
 * <p>
 * This class relies on the DatabaseConnection class for establishing a connection to
 * the database.
 * <p>
 * The Borrow_book class is part of the GUI package in the library management system.
 *


public class Borrow_book extends javax.swing.JFrame {

    private Connection connection;

    /**
     * Creates new form Borrow_book
     */
    public Borrow_book() {
        initComponents();
        connection = new DatabaseConnection().getConnection(); // Create an instance of DatabaseConnection and get the connection

    }

    /**
     * Checks the availability of a book by its barcode and updates its status
     * and due date if available. Displays appropriate messages based on the
     * book's current status and existence in the library.
     *
     * The barcode of the book to be checked and updated. The selected due date
     * for borrowing the book.
     */
    private void updateBookStatusAndDueDate(String bookBarcode, java.util.Date selectedDueDate) {
        // it takes the book bar code as parameter
        String sql = "SELECT book_barcode, book_duedate FROM library_books WHERE book_barcode = ?";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookBarcode);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                java.sql.Date dueDate = resultSet.getDate("book_duedate");

                if (dueDate != null) {
                    // Book is already borrowed, display an error message
                    JOptionPane.showMessageDialog(this, "Book with barcode " + bookBarcode + " is already borrowed.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Book is available for borrowing, update the due date and status
                    performUpdateBookStatusAndDueDate(bookBarcode, selectedDueDate);
                }
            } else {
                // Book not found in the library, display an error message
                JOptionPane.showMessageDialog(this, "Book with barcode " + bookBarcode + " not found in the library.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs the actual update of a book's status and due date in the
     * database. Executes SQL update statements to modify the book's record with
     * the provided barcode and due date. Displays success or failure messages
     * based on the outcome of the update operation.
     *
     * The barcode of the book to be updated. The selected due date for
     * borrowing the book.
     */
    private void performUpdateBookStatusAndDueDate(String bookBarcode, java.util.Date selectedDueDate) {
        // Add the logic to update the due date and status here
        // You should call your SQL update statements to update the database

        // For example:
        String updateSql = "UPDATE library_books SET book_duedate = ?, book_status = 'checked out' WHERE book_barcode = ?";
        try ( PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
            updateStatement.setDate(1, new java.sql.Date(selectedDueDate.getTime()));
            updateStatement.setString(2, bookBarcode);

            int rowsUpdated = updateStatement.executeUpdate();
            // Display success or failure messages based on the outcome of the update operation
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Book with barcode " + bookBarcode + " has been borrowed.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update book status and due date.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        DueDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        Search_Book = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        UpdateDueDatebutton = new javax.swing.JButton();
        backtohome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Library managament system");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Enter the book barcode to borrow book");

        Search_Book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_BookActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Chose the due date for book");

        UpdateDueDatebutton.setText("Borrow Book");
        UpdateDueDatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateDueDatebuttonActionPerformed(evt);
            }
        });

        backtohome.setText("Back");
        backtohome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backtohomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Search_Book)
                            .addComponent(DueDate, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(309, 309, 309)
                        .addComponent(UpdateDueDatebutton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(backtohome)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backtohome)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Search_Book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(DueDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86)
                .addComponent(UpdateDueDatebutton)
                .addContainerGap(188, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles the action when the "Update Due Date" button is clicked.
     * Retrieves the book barcode and selected due date from the user input.
     * Validates the input, and if the book barcode is not empty, calls the
     * method to update the book status and due date.
     *
     * 
     */
    private void UpdateDueDatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateDueDatebuttonActionPerformed

        // Get the book barcode and selected due date
        String bookBarcode = Search_Book.getText();
        java.util.Date selectedDueDate = DueDate.getDate();

        // Validate input and update book status and due date if the book barcode is not empty
        if (bookBarcode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a book barcode.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            updateBookStatusAndDueDate(bookBarcode, selectedDueDate);
        }

    }//GEN-LAST:event_UpdateDueDatebuttonActionPerformed

    private void Search_BookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_BookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Search_BookActionPerformed

    private void backtohomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backtohomeActionPerformed
        // TODO add your handling code here:
        Home backobj = new Home();
        backobj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backtohomeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DueDate;
    private javax.swing.JTextField Search_Book;
    private javax.swing.JButton UpdateDueDatebutton;
    private javax.swing.JButton backtohome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
