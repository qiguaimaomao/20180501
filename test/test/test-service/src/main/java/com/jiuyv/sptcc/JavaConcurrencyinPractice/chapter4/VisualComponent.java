package com.jiuyv.sptcc.JavaConcurrencyinPractice.chapter4;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VisualComponent {
private final List<KeyListener> keyListeners=new CopyOnWriteArrayList<>();
private final List<MouseListener> mouseListeners=new CopyOnWriteArrayList<>();
public void addKeyListener(KeyListener e) {
	keyListeners.add(e);

}

public void removeKeyListener(KeyListener e) {
	keyListeners.remove(e);

}

public void addMouseListener(MouseListener e) {
	mouseListeners.add(e);

}

public void removeMouseListener(MouseListener e) {
	mouseListeners.remove(e);

}
}
