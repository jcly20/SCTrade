
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JCTrade {

    View view;
    ViewController viewController;
    //CreateTab createTab;
//    ReadTab readTab;
//    UpdateTab updateTab;
//    DeleteTab deleteTab;
    Model model;

    class ChangeTabChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {

            System.out.println("change tab");

            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList = model.selectAll();

            view.listModel.clear();
            for (String s : arrayList) {
                view.listModel.addElement(s);
            }
        }
    }


    class UpdateListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {

            System.out.println("update selection");

            if (!e.getValueIsAdjusting()) {
                int index = viewController.updateTab.updateList.getSelectedIndex();
                if (index != -1) {
                    String s = view.listModel.elementAt(index);
                    String[] words = s.trim().split("\\s+");
                    view.id[0] = Integer.parseInt(words[0]);
                    String name = words[1];
                    int age = Integer.parseInt(words[2]);
                    viewController.updateTab.updateTextFieldName.setText(name);
                    viewController.updateTab.updateTextFieldAge.setText(String.valueOf(age));
                    view.updateButton.setEnabled(true);
                }
            }
        }
    }


    class UpdateButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("update button");

            if (view.id[0] != -1) {
                String name = viewController.updateTab.updateTextFieldName.getText();
                int age = Integer.parseInt(viewController.updateTab.updateTextFieldAge.getText());
                model.updateRecord(view.id[0], name, age);
            }
        }
    }


    class DeleteButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("delete button");

            int i = view.jList.getSelectedIndex();
            String s = view.jList.getSelectedValue();
            String[] words = s.trim().split(" ");
            int id = Integer.parseInt(words[0]);
            view.listModel.remove(i);
            model.deleteRecord(id);
        }
    }


    class CreateButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("create button");

            String name = viewController.createTab.addTextFieldName.getText();
            int age = -1;
            try {age = Integer.parseInt(viewController.createTab.addTextFieldAge.getText());} catch(NumberFormatException nfe) {}
            if (!name.isEmpty() && !(age == -1)) {
                model.createRecord(name, age);
            }

        }
    }


    public void controller() {

        model = new Model();
        model.model();

        ViewController viewController = new ViewController();

        viewController.setChangeTabChangeLister(new ChangeTabChangeListener());
        viewController.updateTab.updateList.addListSelectionListener(new UpdateListSelectionListener());
        viewController.updateTab.updateButton.addActionListener(new UpdateButtonActionListener());
        viewController.deleteTab.deleteButton.addActionListener(new DeleteButtonActionListener());
        viewController.createTab.createButton.addActionListener(new CreateButtonActionListener());

    }

}

