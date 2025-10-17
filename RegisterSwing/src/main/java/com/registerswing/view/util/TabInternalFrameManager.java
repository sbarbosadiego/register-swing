package com.registerswing.view.util;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

public class TabInternalFrameManager {

    private final JTabbedPane tabbedPane;
    private final JDesktopPane desktopPane;
    private final List<JInternalFrame> frames;

    public TabInternalFrameManager(JTabbedPane tabbedPane, JDesktopPane desktopPane) {
        this.tabbedPane = tabbedPane;
        this.desktopPane = desktopPane;
        this.frames = new ArrayList<>();
        setupTabChangeListener();
    }

    public List<JInternalFrame> getAllFrames() {
        return new ArrayList<>(frames);
    }

    private void setupTabChangeListener() {
        tabbedPane.addChangeListener(this::handleTabChange);
    }

    private void handleTabChange(ChangeEvent e) {
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < frames.size()) {
            JInternalFrame frame = frames.get(selectedIndex);
            try {
                frame.setSelected(true);
                frame.toFront();
            } catch (PropertyVetoException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addFrameWithTab(JInternalFrame frame) {
        String title = frame.getTitle() != null ? frame.getTitle() : "Sem título";

        JPanel tabPanel = createTabPanel(title, frame);

        tabbedPane.addTab(title, null);
        int tabIndex = tabbedPane.getTabCount() - 1;
        tabbedPane.setTabComponentAt(tabIndex, tabPanel);

        frames.add(frame);
        desktopPane.add(frame);
        frame.setVisible(true);

        setupFrameListeners(frame);
        tabbedPane.setSelectedIndex(tabIndex);
    }

    private JPanel createTabPanel(String title, JInternalFrame frame) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel label = new JLabel(title);
        JButton closeButton = new JButton("X");
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(e -> closeFrame(frame));

        panel.add(label, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.EAST);

        return panel;
    }

    private void setupFrameListeners(JInternalFrame frame) {
        frame.addPropertyChangeListener("title", evt -> {
            String newTitle = (String) evt.getNewValue();
            int index = frames.indexOf(frame);
            if (index >= 0) {
                tabbedPane.setTitleAt(index, newTitle);
                JPanel panel = (JPanel) tabbedPane.getTabComponentAt(index);
                ((JLabel) panel.getComponent(0)).setText(newTitle);
            }
        });

        frame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                removeFrame(frame);
            }

            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                int index = frames.indexOf(frame);
                if (index >= 0 && index != tabbedPane.getSelectedIndex()) {
                    tabbedPane.setSelectedIndex(index);
                }
            }

            @Override
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
                try {
                    frame.setIcon(false); // Impede minimização
                } catch (PropertyVetoException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void closeFrame(JInternalFrame frame) {
        try {
            frame.setClosed(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    private void removeFrame(JInternalFrame frame) {
        int index = frames.indexOf(frame);
        if (index >= 0) {
            frames.remove(index);
            tabbedPane.removeTabAt(index);
        }
    }
}
