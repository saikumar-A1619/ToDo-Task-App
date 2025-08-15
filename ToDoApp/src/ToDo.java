import javax.swing.*;
import java.awt.*;

public class ToDo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDo().createAndShowGUI());
    }

    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInput;

    public void createAndShowGUI() {
        // Frame setup
        JFrame frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // List Model
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Bottom Panel for input & buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        taskInput = new JTextField();
        bottomPanel.add(taskInput, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Task");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Add Task Action
        addButton.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskInput.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a task.");
            }
        });

        // Delete Task Action
        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a task to delete.");
            }
        });

        // Press Enter to add task
        taskInput.addActionListener(e -> addButton.doClick());

        // Show frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

