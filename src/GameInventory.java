import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameInventory {

    private JFrame frame;
    private JTextField nameField;
    private JTextField quantityField;
    private JButton addButton;
    private JButton removeButton;
    private JPanel itemsPanel;
    private JPanel selectedPanel = null;

    public GameInventory() {
        frame = new JFrame("Game Inventory");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nameField = new JTextField(15);
        quantityField = new JTextField(5);
        addButton = new JButton("Ajouter");
        removeButton = new JButton("Supprimer");

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Item :"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Quantité:"));
        inputPanel.add(quantityField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        itemsPanel = new JPanel();
        itemsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JScrollPane listScrollPane = new JScrollPane(itemsPanel);
        listScrollPane.setPreferredSize(new Dimension(550, 150));

        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(listScrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedItem();
            }
        });

        frame.setVisible(true);
    }

    private void addItem() {
        String name = nameField.getText().trim();
        String quantity = quantityField.getText().trim();

        if (!name.isEmpty() && !quantity.isEmpty()) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            itemPanel.setPreferredSize(new Dimension(150, 60));

            JLabel nameLabel = new JLabel(name);
            JLabel quantityLabel = new JLabel("Quantité: " + quantity);

            itemPanel.add(nameLabel);
            itemPanel.add(quantityLabel);


            itemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (selectedPanel != null) {
                        selectedPanel.setBackground(null);
                    }
                    selectedPanel = itemPanel;
                    selectedPanel.setBackground(Color.LIGHT_GRAY);
                }
            });

            itemsPanel.add(itemPanel);
            itemsPanel.revalidate();
            itemsPanel.repaint();

            nameField.setText("");
            quantityField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Remplissez les deux champs", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeSelectedItem() {
        if (selectedPanel != null) {
            itemsPanel.remove(selectedPanel);
            itemsPanel.revalidate();
            itemsPanel.repaint();
            selectedPanel = null;
        } else {
            JOptionPane.showMessageDialog(frame, "Sélectionnez un élément à supprimer", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameInventory();
            }
        });
    }
}
