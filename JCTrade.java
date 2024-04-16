
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JCTrade {

    View view;
    Model model;

    class ChangeTabChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {

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
            if (!e.getValueIsAdjusting()) {
                int index = view.updateList.getSelectedIndex();
                if (index != -1) {
                    String s = view.listModel.elementAt(index);
                    String[] words = s.trim().split("\\s+");
                    view.id[0] = Integer.parseInt(words[0]);
                    String name = words[1];
                    int age = Integer.parseInt(words[2]);
                    view.updateTextFieldName.setText(name);
                    view.updateTextFieldAge.setText(String.valueOf(age));
                    view.updateButton.setEnabled(true);
                }
            }
        }
    }


    class UpdateButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.id[0] != -1) {
                String name = view.updateTextFieldName.getText();
                int age = Integer.parseInt(view.updateTextFieldAge.getText());
                model.updateRecord(view.id[0], name, age);
            }
        }
    }


    class DeleteButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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

            String name = view.addTextFieldName.getText();
            int age = -1;
            try {age = Integer.parseInt(view.addTextFieldAge.getText());} catch(NumberFormatException nfe) {}
            if (!name.isEmpty() && !(age == -1)) {
                model.createRecord(name, age);
            }

        }
    }


    public void controller() {

        model = new Model();
        model.model();

        view = new View();
        view.view();

        view.setChangeTabChangeLister(new ChangeTabChangeListener());
        view.updateList.addListSelectionListener(new UpdateListSelectionListener());
        view.updateButton.addActionListener(new UpdateButtonActionListener());
        view.deleteButton.addActionListener(new DeleteButtonActionListener());
        view.createButton.addActionListener(new CreateButtonActionListener());

    }

}

