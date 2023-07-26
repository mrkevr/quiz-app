package dev.mrkevr.quizapp.api.model.generator;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class DocumentIdentifierGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		Random random = new Random();
		String first = String.format("%04d", random.nextInt(10000));
		String last = String.format("%04d", random.nextInt(10000));
		return first + "-" + last;
	}

}
