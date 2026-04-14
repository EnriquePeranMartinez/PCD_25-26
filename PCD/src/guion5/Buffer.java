package guion5;

import java.util.ArrayList;
import java.util.List;
import messagepassing.*;

class Buffer implements Runnable {
	private Channel pb;
	private Channel bc;
	private int capacidad = 0;
	private Selector s = null;
	private List<Object> buffer = null;
	
	
	public Buffer(Channel pb, Channel bc, int capacidad) {
		this.pb = pb;
		this.bc = bc;
		buffer = new ArrayList<Object>();
		this.capacidad = capacidad;
	}
	
	public void run() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException ex) {
			// manejar la excepción si se desea
		}
		s = new Selector();
		s.addSelectable(pb, false);
		s.addSelectable(bc, true); // El channel bc se usará para enviar
		if (capacidad == 0) {
			while (true) {
				Object o = pb.receive();
				bc.send(o);
			}
		} else {
			while (true) {
				pb.setGuardValue(buffer.size() < capacidad);
				bc.setGuardValue(buffer.size() > 0);
				switch (s.selectOrBlock()) {
				case 1:
					Object o = pb.receive();
					buffer.add(o);
					break;
				case 2:
					bc.send(buffer.remove(0));
					break;
				}
			}
		}
	}
}