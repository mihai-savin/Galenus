package ro.sci.gms.dao.inmemory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import ro.sci.gms.dao.AppointmentDAO;
import ro.sci.gms.domain.Appointment;
import ro.sci.gms.domain.User;

@Repository
public class IMAppointmentDAO extends IMBaseDAO<Appointment> implements AppointmentDAO {

	public Collection<Appointment> searchById(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}

		Collection<Appointment> all = new LinkedList<Appointment>(getAll());
		for (Iterator<Appointment> it = all.iterator(); it.hasNext();) {
			Appointment apt = it.next();
		}
		return all;
	}

	public Collection<Appointment> getAll(User user) {

		Collection<Appointment> all = new LinkedList<Appointment>(getAll());
		Collection<Appointment> usersAppointments = new LinkedList<Appointment>();

		for (Appointment appointment : all) {
			if ((appointment.getDoctor().equals(user)) || (appointment.getPatient().equals(user))) {
				usersAppointments.add(appointment);
			}
		}

		return usersAppointments;
	}
}
