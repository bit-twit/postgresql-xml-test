package org.bittwit.postgresql.xml.test;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public class PostgreSQLXmlType implements UserType {

    private final int[] sqlTypesSupported = new int[] { Types.VARCHAR };

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return (String) cached;
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if (value == null)
            return null;
        return new String((String) value);
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (String) value;
    }

    @Override
    public boolean equals(Object arg0, Object arg1) throws HibernateException {
        if (arg0 == null) {
            return arg1 == null;
        } else {
            return arg0.equals(arg1);
        }
    }

    @Override
    public int hashCode(Object arg0) throws HibernateException {
        return arg0 == null ? null : arg0.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor si, Object owner)
            throws HibernateException, SQLException {
        assert(names.length == 1);
        String xmldoc = rs.getString( names[0] );
        return rs.wasNull() ? null : xmldoc;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor si)
            throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            st.setObject(index, value, Types.OTHER);
        }
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class returnedClass() {
        return String.class;
    }

    @Override
    public int[] sqlTypes() {
        return sqlTypesSupported;
    }

}
